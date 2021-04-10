package com.oculus.bugreporter;

import android.content.Context;
import android.util.Log;
import com.android.okhttp.MediaType;
import com.android.okhttp.MultipartBuilder;
import com.android.okhttp.OkHttpClient;
import com.android.okhttp.Protocol;
import com.android.okhttp.Request;
import com.android.okhttp.RequestBody;
import com.android.okhttp.Response;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import oculus.internal.AuthManager2;
import oculus.internal.FileUtils;

public class FlytrapUploader {
    private static final String FLYTRAP_ENDPOINT = "https://graph.oculus.com/report_bug";
    private static final String OCULUS_CATEGORY_ID = "1581534861914814";
    private static final String TAG = "FlytrapUploader";
    private Context mContext;

    public FlytrapUploader(Context context) {
        this.mContext = context;
    }

    public boolean upload(BugReport report) {
        File descriptionRecordingFile;
        File detailsFile;
        try {
            String token = AuthManager2.getInstance(this.mContext).getAccessTokenBlocking(false);
            if (token == null) {
                Log.w(TAG, "Couldn't get auth token");
                recordTelemetry(report.id, false, "Auth token unavailable");
                return false;
            }
            BugFileUtils bugFileUtils = new BugFileUtils(this.mContext);
            SystemInfoUtils systemInfoUtils = new SystemInfoUtils(this.mContext);
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(1, TimeUnit.MINUTES);
            client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
            client.setReadTimeout(2, TimeUnit.MINUTES);
            client.setWriteTimeout(2, TimeUnit.MINUTES);
            Request.Builder requestBuilder = new Request.Builder().url(FLYTRAP_ENDPOINT).addHeader("Authorization", "Bearer " + token);
            MultipartBuilder bodyBuilder = new MultipartBuilder().addFormDataPart("app_id", report.appId).addFormDataPart("category_id", report.categoryId).addFormDataPart("session_id", report.sessionId).addFormDataPart(BugReportProvider.DESCRIPTION, report.description).type(MultipartBuilder.FORM);
            Log.d(TAG, "category id " + report.categoryId);
            Extradata extradata = Extradata.readFromFile(new File(bugFileUtils.reportFilename(report.id, BugFileUtils.EXTRADATA)));
            if (!(report.screenshot == null || extradata == null || !extradata.shouldUploadScreenshot)) {
                bodyBuilder.addFormDataPart(BugReportProvider.SCREENSHOT, report.screenshot.getName(), RequestBody.create(MediaType.parse("image/png"), report.screenshot));
            }
            if (!(report.pastAudioData == null || extradata == null || !extradata.shouldUploadPastAudioData)) {
                bodyBuilder.addFormDataPart(BugReportProvider.PASTAUDIODATA, report.pastAudioData.getName(), RequestBody.create(MediaType.parse("application/zip"), report.pastAudioData));
            }
            String systemDetails = systemInfoUtils.getSystemDetails();
            String packagesInfo = systemInfoUtils.getPackagesInfo();
            File detailsFile2 = new File(bugFileUtils.reportFilename(report.id, BugFileUtils.DETAILS));
            File packagesFile = new File(bugFileUtils.reportFilename(report.id, BugFileUtils.PACKAGES));
            if (FileUtils.writeAllText(packagesFile, packagesInfo)) {
                bodyBuilder.addFormDataPart("packages", "oculus-packages.txt", RequestBody.create(MediaType.parse("text/plain"), packagesFile));
            }
            if (FileUtils.writeAllText(detailsFile2, systemDetails)) {
                bodyBuilder.addFormDataPart("details", "oculus-details.txt", RequestBody.create(MediaType.parse("text/plain"), detailsFile2));
            }
            if (report.logs != null) {
                bodyBuilder.addFormDataPart(BugReportProvider.LOGS, report.logs.getName(), RequestBody.create(MediaType.parse("application/zip"), report.logs));
            }
            File descriptionRecordingFile2 = new File(bugFileUtils.reportFilename(report.id, BugFileUtils.DESCRIPTION_RECORDING));
            if (descriptionRecordingFile2.exists() && descriptionRecordingFile2.length() > 0) {
                bodyBuilder.addFormDataPart(BugReportProvider.DESCRIPTION_RECORDING, descriptionRecordingFile2.getName(), RequestBody.create(MediaType.parse("audio/mp4"), descriptionRecordingFile2));
            }
            if (report.extraMedia != null) {
                int i = 0;
                while (i < report.extraMedia.size()) {
                    File file = report.extraMedia.get(i);
                    MediaType mediaType = parseExtension(file);
                    if (mediaType == null) {
                        StringBuilder sb = new StringBuilder();
                        detailsFile = detailsFile2;
                        sb.append("Unable to parse file type for ");
                        sb.append(file.getName());
                        Log.e(TAG, sb.toString());
                        descriptionRecordingFile = descriptionRecordingFile2;
                    } else {
                        detailsFile = detailsFile2;
                        RequestBody media = RequestBody.create(mediaType, file);
                        StringBuilder sb2 = new StringBuilder();
                        descriptionRecordingFile = descriptionRecordingFile2;
                        sb2.append("extra_media_");
                        sb2.append(i);
                        bodyBuilder.addFormDataPart(sb2.toString(), file.getName(), media);
                    }
                    i++;
                    detailsFile2 = detailsFile;
                    descriptionRecordingFile2 = descriptionRecordingFile;
                }
            }
            requestBuilder.post(bodyBuilder.build());
            Response res = client.newCall(requestBuilder.build()).execute();
            Log.d(TAG, "Flytrap response: " + ((Object) res));
            recordTelemetry(report.id, true, "");
            return true;
        } catch (Exception e) {
            Log.w(TAG, "Couldn't upload to flytrap", e);
            recordTelemetry(report.id, false, e.getMessage());
            return false;
        }
    }

    private void recordTelemetry(String id, boolean success, String error) {
        AnalyticsEvent event = new AnalyticsEvent(Constants.EVENT_BUGREPORT);
        event.setExtra(Constants.EXTRA_STATE, success ? Constants.STATE_UPLOAD_SUCCEEDED : Constants.STATE_UPLOAD_FAILED).setExtra("error", error);
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(event, false);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static MediaType parseExtension(File file) {
        try {
            String filename = file.getName();
            String extension = filename.substring(filename.lastIndexOf("."));
            char c = 65535;
            switch (extension.hashCode()) {
                case 1472726:
                    if (extension.equals(".gif")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1475827:
                    if (extension.equals(".jpg")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1481531:
                    if (extension.equals(".png")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1487870:
                    if (extension.equals(".wav")) {
                        c = 4;
                        break;
                    }
                    break;
                case 45750678:
                    if (extension.equals(".jpeg")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                return MediaType.parse("image/png");
            }
            if (c == 1 || c == 2) {
                return MediaType.parse("image/jpeg");
            }
            if (c == 3) {
                return MediaType.parse("image/gif");
            }
            if (c != 4) {
                return null;
            }
            return MediaType.parse("audio/wav");
        } catch (Exception e) {
            Log.w(TAG, "Unable to parse extra media");
            return null;
        }
    }
}
