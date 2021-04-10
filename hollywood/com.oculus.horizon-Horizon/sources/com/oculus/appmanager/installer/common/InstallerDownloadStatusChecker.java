package com.oculus.appmanager.installer.common;

import android.content.Context;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class InstallerDownloadStatusChecker {
    public static final String TAG = "com.oculus.appmanager.installer.common.InstallerDownloadStatusChecker";
    public final Context mContext;
    public final IErrorReporter mErrorReporter;

    @Inject
    public InstallerDownloadStatusChecker(@ForAppContext Context context, IErrorReporter iErrorReporter) {
        this.mContext = context;
        this.mErrorReporter = iErrorReporter;
    }
}
