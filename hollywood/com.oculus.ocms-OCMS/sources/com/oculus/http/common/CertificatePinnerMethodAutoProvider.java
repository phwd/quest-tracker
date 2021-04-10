package com.oculus.http.common;

import com.facebook.annotations.Generated;
import com.facebook.common.manifest.ManifestModule;
import com.facebook.inject.AbstractProvider;
import okhttp3.CertificatePinner;

@Generated({"By: InjectorProcessor"})
public class CertificatePinnerMethodAutoProvider extends AbstractProvider<CertificatePinner> {
    @Override // javax.inject.Provider
    public CertificatePinner get() {
        return HttpModule.provideCertificatePinner(ManifestModule._UL__ULSEP_com_facebook_common_manifest_AppBuildInfo_ULSEP_ACCESS_METHOD(this));
    }
}
