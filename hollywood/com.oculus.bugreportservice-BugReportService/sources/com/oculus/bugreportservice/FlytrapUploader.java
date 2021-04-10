package com.oculus.bugreportservice;

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
    private Context mContext;

    public FlytrapUploader(Context context) {
        this.mContext = context;
    }

    public boolean upload(BugReport bugReport) {
        try {
            String accessTokenBlocking = AuthManager2.getInstance(this.mContext).getAccessTokenBlocking(false);
            if (accessTokenBlocking == null) {
                Log.w("FlytrapUploader", "Couldn't get auth token");
                recordTelemetry(bugReport.id, false, "Auth token unavailable");
                return false;
            }
            BugFileUtils bugFileUtils = new BugFileUtils(this.mContext);
            SystemInfoUtils systemInfoUtils = new SystemInfoUtils(this.mContext);
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(1, TimeUnit.MINUTES);
            okHttpClient.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
            okHttpClient.setReadTimeout(2, TimeUnit.MINUTES);
            okHttpClient.setWriteTimeout(2, TimeUnit.MINUTES);
            Request.Builder builder = new Request.Builder();
            builder.url("https://graph.oculus.com/report_bug");
            builder.addHeader("Authorization", "Bearer " + accessTokenBlocking);
            MultipartBuilder multipartBuilder = new MultipartBuilder();
            multipartBuilder.addFormDataPart("app_id", bugReport.appId);
            multipartBuilder.addFormDataPart("category_id", bugReport.categoryId);
            multipartBuilder.addFormDataPart("session_id", bugReport.sessionId);
            multipartBuilder.addFormDataPart("description", bugReport.description);
            multipartBuilder.type(MultipartBuilder.FORM);
            Log.d("FlytrapUploader", "category id " + bugReport.categoryId);
            Extradata readFromFile = Extradata.readFromFile(new File(bugFileUtils.reportFilename(bugReport.id, "extradata.json")));
            if (!(bugReport.screenshot == null || readFromFile == null || !readFromFile.shouldUploadScreenshot)) {
                multipartBuilder.addFormDataPart("screenshot", bugReport.screenshot.getName(), RequestBody.create(MediaType.parse("image/png"), bugReport.screenshot));
            }
            if (!(bugReport.pastAudioData == null || readFromFile == null || !readFromFile.shouldUploadPastAudioData)) {
                multipartBuilder.addFormDataPart("pastAudioData", bugReport.pastAudioData.getName(), RequestBody.create(MediaType.parse("application/zip"), bugReport.pastAudioData));
            }
            String systemDetails = systemInfoUtils.getSystemDetails();
            String packagesInfo = systemInfoUtils.getPackagesInfo();
            File file = new File(bugFileUtils.reportFilename(bugReport.id, "details.txt"));
            File file2 = new File(bugFileUtils.reportFilename(bugReport.id, "package.txt"));
            if (FileUtils.writeAllText(file2, packagesInfo)) {
                multipartBuilder.addFormDataPart("packages", "oculus-packages.txt", RequestBody.create(MediaType.parse("text/plain"), file2));
            }
            if (FileUtils.writeAllText(file, systemDetails)) {
                multipartBuilder.addFormDataPart("details", "oculus-details.txt", RequestBody.create(MediaType.parse("text/plain"), file));
            }
            if (!(bugReport.logs == null || readFromFile == null || !readFromFile.shouldUploadLogs)) {
                multipartBuilder.addFormDataPart("logs", bugReport.logs.getName(), RequestBody.create(MediaType.parse("application/zip"), bugReport.logs));
            }
            File file3 = new File(bugFileUtils.reportFilename(bugReport.id, "description.mp4"));
            if (file3.exists() && file3.length() > 0) {
                multipartBuilder.addFormDataPart("descriptionRecording", file3.getName(), RequestBody.create(MediaType.parse("audio/mp4"), file3));
            }
            if (bugReport.extraMedia != null) {
                for (int i = 0; i < bugReport.extraMedia.size(); i++) {
                    File file4 = (File) bugReport.extraMedia.get(i);
                    MediaType parseExtension = parseExtension(file4);
                    if (parseExtension == null) {
                        Log.e("FlytrapUploader", "Unable to parse file type for " + file4.getName());
                    } else {
                        RequestBody create = RequestBody.create(parseExtension, file4);
                        multipartBuilder.addFormDataPart("extra_media_" + i, file4.getName(), create);
                    }
                }
            }
            builder.post(multipartBuilder.build());
            Response execute = okHttpClient.newCall(builder.build()).execute();
            Log.d("FlytrapUploader", "Flytrap response: " + execute);
            recordTelemetry(bugReport.id, true, "");
            return true;
        } catch (Exception e) {
            Log.w("FlytrapUploader", "Couldn't upload to flytrap", e);
            recordTelemetry(bugReport.id, false, e.getMessage());
            return false;
        }
    }

    private void recordTelemetry(String str, boolean z, String str2) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_bugreport");
        analyticsEvent.setExtra("state", z ? "upload_succeeded" : "upload_failed").setExtra("error", str2);
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(analyticsEvent, false);
    }

    private static MediaType parseExtension(File file) {
        try {
            String name = file.getName();
            String substring = name.substring(name.lastIndexOf("."));
            char c = 65535;
            switch (substring.hashCode()) {
                case 1472726:
                    if (substring.equals(".gif")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1475827:
                    if (substring.equals(".jpg")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1478659:
                    if (substring.equals(".mp4")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1481531:
                    if (substring.equals(".png")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1487870:
                    if (substring.equals(".wav")) {
                        c = 4;
                        break;
                    }
                    break;
                case 45750678:
                    if (substring.equals(".jpeg")) {
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
            if (c == 4) {
                return MediaType.parse("audio/wav");
            }
            if (c == 5) {
                return MediaType.parse("video/mp4");
            }
            Log.e("FlytrapUploader", "Unable to parse extra media extension: " + substring);
            return null;
        } catch (Exception unused) {
            Log.w("FlytrapUploader", "Unable to parse extra media");
            return null;
        }
    }
}
