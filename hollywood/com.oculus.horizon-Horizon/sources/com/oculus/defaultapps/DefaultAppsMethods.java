package com.oculus.defaultapps;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.defaultapps.graphql.DefaultAppsGraphQLQuery;
import com.oculus.defaultapps.net.DefaultAppsConfigResponse;
import com.oculus.defaultapps.net.DefaultAppsRequest;
import com.oculus.defaultapps.net.DefaultAppsResponse;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.common.DsatAuthorization;
import com.oculus.managed.ManagedMode;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_defaultapps_graphql_DefaultAppsGraphQLQuery_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID"})
public class DefaultAppsMethods {
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final DefaultAppsGraphQLQuery mGraphQLQuery;
    public Methods mMethods;

    public interface Methods {
        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        DefaultAppsResponse getDefaultApps(@Query("doc") String str, @Query("variables") String str2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        DefaultAppsConfigResponse getDefaultAppsConfig(@Query("doc") String str, @Query("variables") String str2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        @Headers({DsatAuthorization.ENABLE})
        DefaultAppsResponse getDefaultAppsDsat(@Query("doc") String str, @Query("variables") String str2);
    }

    public final DefaultAppsResponse A00(DefaultAppsRequest defaultAppsRequest) {
        boolean z = ((ManagedMode) AnonymousClass0J2.A03(0, 380, this._UL_mInjectionContext)).isEnterpriseModeEnabled;
        Methods methods = this.mMethods;
        String str = this.mGraphQLQuery.mDefaultAppsQuery;
        String A06 = GraphQLParamsHelper.GSON_CONVERTER.A06(defaultAppsRequest.mRequestParams);
        if (z) {
            return methods.getDefaultAppsDsat(str, A06);
        }
        return methods.getDefaultApps(str, A06);
    }

    @Inject
    public DefaultAppsMethods(AbstractC06640p5 r3, @OculusRestAdapter RestAdapter restAdapter) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mGraphQLQuery = (DefaultAppsGraphQLQuery) AnonymousClass117.A00(539, r3);
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
