package com.oculus.downloader.contract;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.C003108z;
import X.C02600ao;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.errorreporting.interfaces.IErrorReporter;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class DownloaderContract {
    public static final String EXTERNAL_DOWNLOAD_SUB_DIRECTORY;
    public static final String OCULUS_DOWNLOAD_SUB_FOLDER = "/oculus_downloaded_apks";
    public static final String PACKAGE_NAME = "com.android.providers.downloads";
    public static final String SERVICE_CLASS = "com.oculus.appmanager.downloader.OculusDownloadService";
    public AnonymousClass0QC _UL_mInjectionContext;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;

    public static class Contract {
        public static final String ACTION_DOWNLOAD_COMPLETE = "download_complete";
        public static final String EXTRA_DOWNLOAD_ID = "download_id";
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append("/");
        sb.append(Environment.DIRECTORY_DOWNLOADS);
        sb.append(OCULUS_DOWNLOAD_SUB_FOLDER);
        EXTERNAL_DOWNLOAD_SUB_DIRECTORY = sb.toString();
    }

    public final void A00(long j) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this.mContext.getApplicationContext(), SERVICE_CLASS));
        intent.setAction(Contract.ACTION_DOWNLOAD_COMPLETE);
        intent.putExtra("download_id", j);
        if (C02600ao.A00().A06().A00(intent, this.mContext.getApplicationContext()) == null) {
            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A96("OculusDownloadService not found", String.valueOf(this.mContext.getApplicationContext().getPackageManager().queryIntentServices(intent, 0)));
        }
    }

    @Inject
    public DownloaderContract(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mContext = C003108z.A02(r3);
    }
}
