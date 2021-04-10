package libraries.marauder.analytics;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import libraries.debug.log.BLog;
import libraries.marauder.analytics.request.protocol.AnalyticsClient;
import libraries.marauder.analytics.request.protocol.AnalyticsService;

public class AnalyticsUploader {
    public static final Class<?> TAG = AnalyticsUploader.class;
    public final String mAppId;
    public final File mDirectory;
    public final String mLoginSecret;
    public final String mUrl;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003a A[SYNTHETIC, Splitter:B:19:0x003a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readFile(java.io.File r7) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r5 = "IOException thrown while closing Closeable."
            r3 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0036 }
            r1.<init>(r7)     // Catch:{ all -> 0x0036 }
            java.lang.String r0 = "UTF-8"
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ all -> 0x0036 }
            r4.<init>(r1, r0)     // Catch:{ all -> 0x0036 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0034 }
            r3.<init>()     // Catch:{ all -> 0x0034 }
            r0 = 1024(0x400, float:1.435E-42)
            char[] r2 = new char[r0]     // Catch:{ all -> 0x0034 }
        L_0x0018:
            int r1 = r4.read(r2)     // Catch:{ all -> 0x0034 }
            r0 = -1
            if (r1 == r0) goto L_0x0024
            r0 = 0
            r3.append(r2, r0, r1)     // Catch:{ all -> 0x0034 }
            goto L_0x0018
        L_0x0024:
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0034 }
            r4.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0033
        L_0x002c:
            r1 = move-exception
            java.lang.Class<libraries.marauder.analytics.AnalyticsUploader> r0 = libraries.marauder.analytics.AnalyticsUploader.class
            libraries.debug.log.BLog.w(r0, r1, r5)
            return r2
        L_0x0033:
            return r2
        L_0x0034:
            r2 = move-exception
            goto L_0x0038
        L_0x0036:
            r2 = move-exception
            r4 = r3
        L_0x0038:
            if (r4 == 0) goto L_0x0044
            r4.close()     // Catch:{ IOException -> 0x003e }
            throw r2
        L_0x003e:
            r1 = move-exception
            java.lang.Class<libraries.marauder.analytics.AnalyticsUploader> r0 = libraries.marauder.analytics.AnalyticsUploader.class
            libraries.debug.log.BLog.w(r0, r1, r5)
        L_0x0044:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: libraries.marauder.analytics.AnalyticsUploader.readFile(java.io.File):java.lang.String");
    }

    private int uploadBatchInternal(File file) {
        int i;
        BLog.v(AnalyticsUploader.class, "Uploading file %s", file.toString());
        try {
            i = uploadBatch(readFile(file));
            if (i == 200) {
                BLog.v(AnalyticsUploader.class, "Successfully uploaded file %s", file.toString());
                if (!file.delete()) {
                    BLog.w(AnalyticsUploader.class, "File %s was not deleted", file);
                }
                return i;
            }
        } catch (IOException e) {
            BLog.e(AnalyticsUploader.class, "Unable to read file %s", file, e);
            i = -1;
        }
        BLog.e(AnalyticsUploader.class, "Failed to upload file %s with HTTP response %d", file.toString(), Integer.valueOf(i));
        if (i == 403) {
            BLog.d(AnalyticsUploader.class, "It looks like the Facebook Marauder endpoint might be rate-limiting your log uploads. If you're on fbguest, try switching to lighthouse instead.");
            return i;
        }
        return i;
    }

    public boolean uploadAllBatches() {
        String str;
        BLog.v(AnalyticsUploader.class, "Attempting to upload analytics");
        if (!this.mDirectory.exists()) {
            str = "No analytics directory exists, nothing to do";
        } else {
            File[] listFiles = this.mDirectory.listFiles();
            if (listFiles == null) {
                str = "Analytics directory error";
            } else {
                for (File file : listFiles) {
                    if (!uploadBatch(file)) {
                        return false;
                    }
                }
                return true;
            }
        }
        BLog.v(AnalyticsUploader.class, str);
        return true;
    }

    public AnalyticsUploader() {
        this.mUrl = AnalyticsService.LOGGING_ENDPOINT;
        this.mDirectory = null;
        this.mAppId = "";
        this.mLoginSecret = "";
    }

    public AnalyticsUploader(Context context, String str, String str2, String str3) {
        this.mUrl = str;
        this.mDirectory = AnalyticsUtil.getAnalyticsDirectory(context);
        this.mAppId = str2;
        this.mLoginSecret = str3;
    }

    private int uploadBatch(String str) {
        return AnalyticsClient.getInstance().sendBatchLogs(this.mUrl, str, this.mAppId, this.mLoginSecret);
    }

    public boolean uploadBatch(File file) {
        return uploadBatchInternal(file) == 200;
    }
}
