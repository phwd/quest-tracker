package com.oculus.appsafety;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.okhttp.MediaType;
import com.android.okhttp.OkHttpClient;
import com.android.okhttp.Protocol;
import com.android.okhttp.Request;
import com.android.okhttp.RequestBody;
import com.android.okhttp.Response;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.oculus.os.PackageMetadata;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import oculus.internal.AuthManager2;
import org.json.JSONException;
import org.json.JSONObject;

public class BinaryCheckJobService extends JobService {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String CERT_FINGERPRINTS = "cert_fingerprints";
    private static final String GRAPH_ENDPOINT = "https://graph.oculus.com/app_binary_check";
    private static final int INITIAL_BACK_DURATION_MS = 3000;
    private static final int JOB_ID = 123;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String ORIGINAL_SCHEDULE_TIME = "original_schedule_time";
    private static final String PACKAGE_INFO = "package_info";
    private static final String PACKAGE_NAME = "package_name";
    private static final String RESULT = "result";
    private static final String TAG = BinaryCheckJobService.class.getSimpleName();
    private static final String UPDATE_OCMS_ACTION = "com.oculus.ocms.BINARY_CHECK_UPDATE";
    private static Map<String, PersistableBundle> sBinariesToCheck = null;
    private static BroadcastReceiver sPackageRemovedBroadcastReceiver = null;
    private BinaryCheckAsyncTask mTask = null;

    /* access modifiers changed from: private */
    public static void scheduleJob(Context context) {
        ((JobScheduler) context.getSystemService(JobScheduler.class)).schedule(new JobInfo.Builder(123, new ComponentName(context, BinaryCheckJobService.class)).setRequiredNetworkType(1).setBackoffCriteria(3000, 1).build());
        LoggingHelper.logBinaryCheckEvent(context, "", "schedule_job");
    }

    public static void addPackageToCheck(Context context, PackageMetadata packageMetadata) {
        PersistableBundle taskInfo = new PersistableBundle();
        taskInfo.putStringArray(CERT_FINGERPRINTS, (String[]) Stream.of((Object[]) packageMetadata.signatures).map($$Lambda$BinaryCheckJobService$z0xGWwiJcw9XhBIezugApnAZ3g.INSTANCE).map($$Lambda$3Q9D5ethWmHHaKd02rM4coTfHUw.INSTANCE).toArray($$Lambda$BinaryCheckJobService$VPGl9uDHqQaN5tT1uQnGQpG7N6A.INSTANCE));
        taskInfo.putLong(ORIGINAL_SCHEDULE_TIME, System.currentTimeMillis());
        setupBinariesToCheck(context);
        sBinariesToCheck.put(packageMetadata.packageIdentifier, taskInfo);
        LoggingHelper.logBinaryCheckEvent(context, packageMetadata.packageIdentifier, "added_to_job");
        if (((JobScheduler) context.getSystemService(JobScheduler.class)).getPendingJob(123) == null) {
            scheduleJob(context);
        }
    }

    static /* synthetic */ String[] lambda$addPackageToCheck$1(int x$0) {
        return new String[x$0];
    }

    private static synchronized void setupBinariesToCheck(Context context) {
        synchronized (BinaryCheckJobService.class) {
            if (sBinariesToCheck == null) {
                sBinariesToCheck = new ConcurrentHashMap();
            }
            if (sPackageRemovedBroadcastReceiver == null) {
                initPackageRemovedBroadcastReceiver(context);
            }
        }
    }

    public boolean onStartJob(JobParameters params) {
        BinaryCheckAsyncTask binaryCheckAsyncTask = this.mTask;
        if (binaryCheckAsyncTask != null && binaryCheckAsyncTask.getStatus() != AsyncTask.Status.FINISHED) {
            return false;
        }
        this.mTask = new BinaryCheckAsyncTask(this, params);
        this.mTask.execute(new Void[0]);
        return true;
    }

    public boolean onStopJob(JobParameters params) {
        BinaryCheckAsyncTask binaryCheckAsyncTask = this.mTask;
        if (!(binaryCheckAsyncTask == null || binaryCheckAsyncTask.getStatus() == AsyncTask.Status.FINISHED)) {
            this.mTask.cancel(true);
            LoggingHelper.logBinaryCheckEvent(this, "", "job_stopped");
        }
        this.mTask = null;
        return true;
    }

    public void onDestroy() {
        if (sPackageRemovedBroadcastReceiver != null) {
            getApplicationContext().unregisterReceiver(sPackageRemovedBroadcastReceiver);
            sPackageRemovedBroadcastReceiver = null;
        }
    }

    private static void initPackageRemovedBroadcastReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_REMOVED");
        filter.addDataScheme("package");
        sPackageRemovedBroadcastReceiver = new BroadcastReceiver() {
            /* class com.oculus.appsafety.BinaryCheckJobService.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                if (intent == null || !TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_REMOVED")) {
                    Log.d(BinaryCheckJobService.TAG, "PackageRemovedBroadcastReceiver received unexpected action");
                    return;
                }
                Uri packageNameUri = intent.getData();
                if (packageNameUri != null) {
                    String packageName = packageNameUri.getSchemeSpecificPart();
                    String str = BinaryCheckJobService.TAG;
                    Log.d(str, packageName + " was uninstalled; remove from queue");
                    LoggingHelper.logBinaryCheckEvent(context, packageName, "package_uninstalled_and_removed_from_queue");
                    BinaryCheckJobService.sBinariesToCheck.remove(packageName);
                }
            }
        };
        context.getApplicationContext().registerReceiver(sPackageRemovedBroadcastReceiver, filter);
    }

    /* access modifiers changed from: private */
    public static class HttpStatusCodeException extends RuntimeException {
        public final int httpStatusCode;
        public final String responseBody;

        public HttpStatusCodeException(int httpStatusCode2, String responseBody2, String message) {
            super(message);
            this.responseBody = responseBody2;
            this.httpStatusCode = httpStatusCode2;
        }
    }

    private class BinaryCheckAsyncTask extends AsyncTask<Void, Void, Void> {
        private String mAccessToken;
        private Context mContext;
        private JobParameters mJobParams;

        public BinaryCheckAsyncTask(Context context, JobParameters jobParams) {
            this.mContext = context;
            this.mJobParams = jobParams;
        }

        public Void doInBackground(Void... p) {
            this.mAccessToken = getAccessToken();
            if (this.mAccessToken == null) {
                BinaryCheckJobService.this.jobFinished(this.mJobParams, true);
                LoggingHelper.logBinaryCheckEvent(this.mContext, "", "job_failed_to_get_access_token");
                return null;
            }
            if (BinaryCheckJobService.sBinariesToCheck != null) {
                for (String packageName : BinaryCheckJobService.sBinariesToCheck.keySet()) {
                    PersistableBundle taskInfo = (PersistableBundle) BinaryCheckJobService.sBinariesToCheck.get(packageName);
                    String result = checkBinaryForPackage(packageName, taskInfo);
                    if (result != null) {
                        sendResultToOCMS(packageName, taskInfo, result);
                        BinaryCheckJobService.sBinariesToCheck.remove(packageName, taskInfo);
                    } else {
                        BinaryCheckJobService.this.jobFinished(this.mJobParams, true);
                        return null;
                    }
                }
                if (!BinaryCheckJobService.sBinariesToCheck.isEmpty()) {
                    BinaryCheckJobService.scheduleJob(this.mContext);
                }
            }
            BinaryCheckJobService.this.jobFinished(this.mJobParams, false);
            return null;
        }

        private void sendResultToOCMS(String packageName, PersistableBundle taskInfo, String result) {
            Intent intent = new Intent(BinaryCheckJobService.UPDATE_OCMS_ACTION);
            intent.setPackage("com.oculus.ocms");
            Bundle extras = new Bundle();
            extras.putLong(BinaryCheckJobService.ORIGINAL_SCHEDULE_TIME, taskInfo.getLong(BinaryCheckJobService.ORIGINAL_SCHEDULE_TIME));
            extras.putString("package_name", packageName);
            extras.putString(BinaryCheckJobService.RESULT, result);
            intent.putExtras(extras);
            this.mContext.startService(intent);
            LoggingHelper.logBinaryCheckEvent(this.mContext, packageName, "try_start_ocms_intent_service");
        }

        private String checkBinaryForPackage(String packageName, PersistableBundle taskInfo) {
            try {
                String str = BinaryCheckJobService.TAG;
                Log.d(str, "Start async binary check for: " + packageName);
                LoggingHelper.logBinaryCheckEvent(this.mContext, packageName, "binary_check_http_request");
                String result = getBinaryCheckResult(this.mAccessToken, packageName, taskInfo);
                String str2 = BinaryCheckJobService.TAG;
                Log.d(str2, "Binary Check result: " + result);
                Context context = this.mContext;
                LoggingHelper.logBinaryCheckEvent(context, packageName, "binary_check_http_request_result_" + result);
                return result;
            } catch (IOException e) {
                Log.e(BinaryCheckJobService.TAG, "Failed to send request", e);
                LoggingHelper.logBinaryCheckEvent(this.mContext, packageName, "binary_check_http_request_send_fail", e);
                return null;
            } catch (JSONException e2) {
                Log.e(BinaryCheckJobService.TAG, "Failed to parse response", e2);
                LoggingHelper.logBinaryCheckEvent(this.mContext, packageName, "binary_check_http_request_json_parse_response_fail", e2);
                return null;
            } catch (HttpStatusCodeException e3) {
                Log.e(BinaryCheckJobService.TAG, "Graph Request Error", e3);
                int i = e3.httpStatusCode;
                if (i == 400 || i != 500) {
                }
                Context context2 = this.mContext;
                LoggingHelper.logBinaryCheckEvent(context2, packageName, "binary_check_http_request_bad_http_status_code_fail_" + e3.httpStatusCode, e3);
                return null;
            } catch (Exception e4) {
                Log.e(BinaryCheckJobService.TAG, "Binary Check Exception", e4);
                LoggingHelper.logBinaryCheckEvent(this.mContext, packageName, "binary_check_unexpected_exception", e4);
                return null;
            }
        }

        private String getAccessToken() {
            try {
                return AuthManager2.getInstance(this.mContext).getAccessTokenBlocking(false);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        private String getBinaryCheckResult(String accessToken, String packageName, PersistableBundle taskInfo) throws IOException, JSONException {
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(30, TimeUnit.SECONDS);
            client.setReadTimeout(30, TimeUnit.SECONDS);
            client.setWriteTimeout(30, TimeUnit.SECONDS);
            client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
            Response res = client.newCall(new Request.Builder().url(BinaryCheckJobService.GRAPH_ENDPOINT).post(RequestBody.create(BinaryCheckJobService.JSON, gsonBuilder.create().toJson(new BinaryCheckRequestObject(accessToken, packageName, taskInfo.getStringArray(BinaryCheckJobService.CERT_FINGERPRINTS))))).build()).execute();
            String responseBody = res.body().source().readString(StandardCharsets.UTF_8);
            if (res.code() == 200) {
                return parseResponseBody(responseBody);
            }
            throw new HttpStatusCodeException(res.code(), responseBody, String.format("Got response (%s): %s", String.valueOf(res.code()), responseBody));
        }

        private String parseResponseBody(String responseBody) throws JSONException {
            return new JSONObject(responseBody).getString(BinaryCheckJobService.RESULT);
        }
    }
}
