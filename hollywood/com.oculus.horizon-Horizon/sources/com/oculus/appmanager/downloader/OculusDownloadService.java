package com.oculus.appmanager.downloader;

import X.AnonymousClass0J2;
import X.AnonymousClass117;
import android.app.IntentService;
import com.facebook.ultralight.Eager;
import javax.inject.Inject;

public class OculusDownloadService extends IntentService {
    public static final Class<?> TAG = OculusDownloadService.class;
    @Inject
    @Eager
    public OculusFileDownloader mOculusDownloader;

    public OculusDownloadService() {
        super("FileDownloaderService");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0050, code lost:
        if (r0 == false) goto L_0x0052;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onHandleIntent(android.content.Intent r20) {
        /*
        // Method dump skipped, instructions count: 292
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.appmanager.downloader.OculusDownloadService.onHandleIntent(android.content.Intent):void");
    }

    public final void onCreate() {
        super.onCreate();
        this.mOculusDownloader = (OculusFileDownloader) AnonymousClass117.A00(429, AnonymousClass0J2.get(this));
    }
}
