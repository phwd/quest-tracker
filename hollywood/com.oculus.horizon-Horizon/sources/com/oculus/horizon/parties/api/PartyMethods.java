package com.oculus.horizon.parties.api;

import com.facebook.ultralight.Dependencies;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.http.core.annotations.OculusRestAdapter;
import javax.inject.Inject;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID"})
public class PartyMethods {
    public final Methods mMethods;

    public interface Methods {
        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void getPartyStatus(@Query("q") String str, Callback<PartyStatusResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void joinParty(@Query("q") String str, @Query("query_params") PartyJoinMutationParams partyJoinMutationParams, @Body String str2, Callback<PartyJoinResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void leaveParty(@Query("q") String str, @Query("query_params") PartyLeaveMutationParams partyLeaveMutationParams, @Body String str2, Callback<PartyLeaveResponse> callback);
    }

    @Inject
    public PartyMethods(@OculusRestAdapter RestAdapter restAdapter) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
