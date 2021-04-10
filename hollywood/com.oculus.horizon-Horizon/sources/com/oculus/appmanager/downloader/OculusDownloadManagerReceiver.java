package com.oculus.appmanager.downloader;

import X.AnonymousClass0J2;
import X.AnonymousClass0QB;
import X.AnonymousClass117;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Eager;
import com.oculus.downloader.contract.DownloaderContract;
import javax.inject.Inject;

public class OculusDownloadManagerReceiver extends BroadcastReceiver implements AnonymousClass0QB {
    public static final Class<?> TAG = OculusDownloadManagerReceiver.class;
    @Inject
    @Eager
    public DownloaderContract mDownloaderContract;

    public final void onReceive(Context context, Intent intent) {
        this.mDownloaderContract = (DownloaderContract) AnonymousClass117.A00(571, AnonymousClass0J2.get(context));
        this.mDownloaderContract.A00(intent.getLongExtra("extra_download_id", 0));
    }
}
