package com.oculus.library.net;

import X.AbstractC06640p5;
import X.AnonymousClass0I1;
import X.AnonymousClass0QC;
import androidx.annotation.VisibleForTesting;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.common.DsatAuthorization;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID"})
public class LibraryMethods {
    public AnonymousClass0QC _UL_mInjectionContext;
    public final Float mLayoutScale = Float.valueOf(Float.parseFloat(AnonymousClass0I1.A03("ro.ovr.layoutscale", "1.f")));
    public final Methods mMethods;

    @VisibleForTesting
    public interface Methods {
        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        GenerateUpdateInfoResponse generateEntitlementUpdateData(@Query("doc") String str, @Query("variables") String str2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        @Headers({DsatAuthorization.ENABLE})
        GenerateUpdateInfoResponse generateEntitlementUpdateDataDsat(@Query("doc") String str, @Query("variables") String str2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        GenerateBinaryInfoResponse genereateEntitlementBinaryData(@Query("doc") String str, @Query("variables") String str2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        ActiveEntitlementsResponse getActiveEntitlements(@Query("doc") String str, @Query("variables") String str2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        @Headers({DsatAuthorization.ENABLE})
        ActiveEntitlementsResponse getActiveEntitlementsDsat(@Query("doc") String str, @Query("variables") String str2);
    }

    @Inject
    public LibraryMethods(AbstractC06640p5 r3, @OculusRestAdapter RestAdapter restAdapter) {
        this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
