package com.oculus.userserver.net;

import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.userserver.http.OculusApi;
import javax.annotation.concurrent.ThreadSafe;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_userserver_http_OculusApi_ULSEP_BINDING_ID"})
@ThreadSafe
public class OculusUserMethods {
    public final Methods mMethods;

    @ThreadSafe
    public interface Methods {
        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        AddToDeviceResponse addToDevice(@Header("Authorization") String str, @Query("q") String str2, @Query("query_params") String str3, @Body String str4);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FetchDeviceUsersResponse fetchDeviceUsers(@Header("Authorization") String str, @Query("doc") String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Response removeFromDevice(@Header("Authorization") String str, @Query("q") String str2, @Query("query_params") String str3, @Body String str4);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Response setFamilyDevice(@Header("Authorization") String str, @Query("q") String str2, @Query("query_params") String str3, @Body String str4);
    }

    public static final class Queries {
        public static final String ADD_TO_DEVICE = "Mutation AddToDevice : AddToDevicePayload {   add_to_device(<input>) {    sid,  }}";
        public static final String REMOVE_FROM_DEVICE = "Mutation RemoveFromDevice : RemoveFromDevicePayload {   remove_from_device(<input>) {    user {      id,    },  }}";
        public static final String REMOVE_USER_FROM_DEVICE = "Mutation RemoveUserFromDevice : RemoveUserFromDevicePayload {   remove_user_from_device(<input>) {    removed_user {      id,    },  }}";
        public static final String SET_FAMILY_DEVICE = "Mutation SetFamilyDevice : OCSetFamilyDeviceMutationResponsePayload {   set_family_device(<input>) {    user {      id,    }  }}";
        public static final String USERS_ON_DEVICE = "{users_on_device() { id, user_status }}";
    }

    @Inject
    public OculusUserMethods(@OculusApi RestAdapter restAdapter) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
