package com.oculus.appmanager.downloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.UL;
import com.oculus.downloader.contract.DownloaderContract;
import javax.inject.Inject;

public class OculusDownloadManagerReceiver extends BroadcastReceiver implements InjectableComponentWithoutContext {
    private static final Class<?> TAG = OculusDownloadManagerReceiver.class;
    @Inject
    @Eager
    private DownloaderContract mDownloaderContract;

    private static final void _UL_injectMe(Context context, OculusDownloadManagerReceiver oculusDownloadManagerReceiver) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusDownloadManagerReceiver);
        } else {
            FbInjector.injectMe(OculusDownloadManagerReceiver.class, (InjectableComponentWithoutContext) oculusDownloadManagerReceiver, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusDownloadManagerReceiver oculusDownloadManagerReceiver) {
        oculusDownloadManagerReceiver.mDownloaderContract = DownloaderContract._UL__ULSEP_com_oculus_downloader_contract_DownloaderContract_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onReceive(Context context, Intent intent) {
        _UL_injectMe(context, this);
        long longExtra = intent.getLongExtra("extra_download_id", 0);
        BLog.d(TAG, "Download complete: %d", Long.valueOf(longExtra));
        this.mDownloaderContract.onDownloadComplete(longExtra);
    }
}
