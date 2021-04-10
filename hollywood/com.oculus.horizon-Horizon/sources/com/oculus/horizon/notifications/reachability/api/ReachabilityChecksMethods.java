package com.oculus.horizon.notifications.reachability.api;

import com.facebook.ultralight.Dependencies;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.http.core.annotations.OculusRestAdapter;
import javax.inject.Inject;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID"})
public class ReachabilityChecksMethods {
    public final Methods mMethods;

    public interface Methods {
        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void mutateReachabilityChecks(@Query("q") String str, @Query("query_params") ReachabilityChecksMutationParams reachabilityChecksMutationParams, @Body String str2, Callback<Void> callback);
    }

    @Inject
    public ReachabilityChecksMethods(@OculusRestAdapter RestAdapter restAdapter) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
