package com.oculus.license;

import androidx.annotation.VisibleForTesting;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.base.ApiException;
import com.oculus.license.LicenseModule;
import javax.inject.Provider;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.GET;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID"})
public class LicenseMethods {
    private static final String QUERY = "query FetchEncodedLicense($app_id: ID!) {  app_store_item(id: $app_id) {    ... on Application {      entitlement {      ... on Entitlement {        license {          encoded        }      }    }  }}}";
    private final Methods mMethods;

    @VisibleForTesting
    public interface Methods {
        @GET("/graphql")
        void getLicenseInfo(@Query("doc") String str, @Query("variables") String str2, Callback<LicenseInfoResponse> callback);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_license_LicenseMethods_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(LicenseModule.UL_id._UL__ULSEP_com_oculus_license_LicenseMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final LicenseMethods _UL__ULSEP_com_oculus_license_LicenseMethods_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (LicenseMethods) UL.factorymap.get(LicenseModule.UL_id._UL__ULSEP_com_oculus_license_LicenseMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final LicenseMethods _UL__ULSEP_com_oculus_license_LicenseMethods_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new LicenseMethods(ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_license_LicenseMethods_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(LicenseModule.UL_id._UL__ULSEP_com_oculus_license_LicenseMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public LicenseMethods(@OculusRestAdapter RestAdapter restAdapter) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }

    public void getLicenseInfo(LicenseInfoRequest licenseInfoRequest, Callback<LicenseInfoResponse> callback) throws ApiException {
        try {
            this.mMethods.getLicenseInfo(QUERY, licenseInfoRequest.toJsonParam(), callback);
        } catch (RetrofitError e) {
            throw new ApiException(e, new ApiError(e));
        }
    }
}