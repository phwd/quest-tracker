package com.oculus.downloader.contract;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.secure.context.SecureContextHelper;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.downloader.contract.ContractModule;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import java.io.File;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class DownloaderContract {
    public static final String EXTERNAL_DOWNLOAD_SUB_DIRECTORY = (Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + OCULUS_DOWNLOAD_SUB_FOLDER);
    private static final String OCULUS_DOWNLOAD_SUB_FOLDER = "/oculus_downloaded_apks";
    public static final String PACKAGE_NAME = "com.android.providers.downloads";
    private static final String SERVICE_CLASS = "com.oculus.appmanager.downloader.OculusDownloadService";
    private InjectionContext _UL_mInjectionContext;
    @UnsafeContextInjection
    @Inject
    @Eager
    private final Context mContext;

    public static class Contract {
        public static String ACTION_DOWNLOAD_COMPLETE = "download_complete";
        public static String EXTRA_DOWNLOAD_ID = "download_id";
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_downloader_contract_DownloaderContract_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(ContractModule.UL_id._UL__ULSEP_com_oculus_downloader_contract_DownloaderContract_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final DownloaderContract _UL__ULSEP_com_oculus_downloader_contract_DownloaderContract_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (DownloaderContract) UL.factorymap.get(ContractModule.UL_id._UL__ULSEP_com_oculus_downloader_contract_DownloaderContract_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final DownloaderContract _UL__ULSEP_com_oculus_downloader_contract_DownloaderContract_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new DownloaderContract(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_downloader_contract_DownloaderContract_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(ContractModule.UL_id._UL__ULSEP_com_oculus_downloader_contract_DownloaderContract_ULSEP_BINDING_ID, injectorLike);
    }

    public static String getAbsoluteDownloadPath(String str) {
        return new File(EXTERNAL_DOWNLOAD_SUB_DIRECTORY, str).getAbsolutePath();
    }

    @Inject
    public DownloaderContract(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        this.mContext = BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onDownloadComplete(long j) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this.mContext.getApplicationContext(), SERVICE_CLASS));
        intent.setAction(Contract.ACTION_DOWNLOAD_COMPLETE);
        intent.putExtra(Contract.EXTRA_DOWNLOAD_ID, j);
        if (SecureContextHelper.get().internal().launchService(intent, this.mContext.getApplicationContext()) == null) {
            ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError("OculusDownloadService not found", String.valueOf(this.mContext.getApplicationContext().getPackageManager().queryIntentServices(intent, 0)));
        }
    }
}