package com.oculus.appmanager.downloader;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.UL;
import com.oculus.downloader.contract.DownloaderContract;
import javax.inject.Inject;

public class OculusDownloadService extends IntentService {
    private static final Class<?> TAG = OculusDownloadService.class;
    @Inject
    @Eager
    private OculusFileDownloader mOculusDownloader;

    private static final void _UL_injectMe(Context context, OculusDownloadService oculusDownloadService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusDownloadService);
        } else {
            FbInjector.injectMe(OculusDownloadService.class, oculusDownloadService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusDownloadService oculusDownloadService) {
        oculusDownloadService.mOculusDownloader = OculusFileDownloader._UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        doHandleIntent(intent);
    }

    public OculusDownloadService() {
        super("FileDownloaderService");
    }

    /* access modifiers changed from: protected */
    public void doHandleIntent(Intent intent) {
        BLog.d(TAG, "doHandleIntent(): %s", intent.getAction());
        String action = intent.getAction();
        if (!DownloaderContract.Contract.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
            BLog.e(TAG, "unrecognized action: %s", action);
        } else if (intent.hasExtra(DownloaderContract.Contract.EXTRA_DOWNLOAD_ID)) {
            onDownloadComplete(intent.getLongExtra(DownloaderContract.Contract.EXTRA_DOWNLOAD_ID, 0));
        } else {
            BLog.e(TAG, "No download_id in intent");
        }
    }

    private void onDownloadComplete(long j) {
        this.mOculusDownloader.onDownloadComplete(j);
    }
}
