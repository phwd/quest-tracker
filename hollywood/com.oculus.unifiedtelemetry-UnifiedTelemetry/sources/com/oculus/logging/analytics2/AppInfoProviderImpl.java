package com.oculus.logging.analytics2;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.QC;
import X.SS;
import android.content.pm.PackageInfo;
import com.facebook.ultralight.Dependencies;
import com.oculus.base.app.AppInfo;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID"})
public class AppInfoProviderImpl implements SS {
    public QC _UL_mInjectionContext;

    @Override // X.SS
    public final String A2H() {
        return ((AppInfo) AbstractC0096Hu.A03(1, 111, this._UL_mInjectionContext)).appId;
    }

    @Override // X.SS
    public final String A2I() {
        return ((PackageInfo) AbstractC0096Hu.A03(0, 89, this._UL_mInjectionContext)).versionName;
    }

    @Override // X.SS
    public final int A2J() {
        return ((PackageInfo) AbstractC0096Hu.A03(0, 89, this._UL_mInjectionContext)).versionCode;
    }

    @Inject
    public AppInfoProviderImpl(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(2, xu);
    }
}
