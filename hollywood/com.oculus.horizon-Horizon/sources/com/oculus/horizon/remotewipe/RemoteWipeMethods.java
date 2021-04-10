package com.oculus.horizon.remotewipe;

import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.base.ApiException;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID"})
public class RemoteWipeMethods {
    public static final String WIPE_PENDING = "WIPE_PENDING";
    public final Methods mMethods;

    public interface Methods {
        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        DeviceManifestWipeStatusResponse getDeviceManifestWipeStatus(@Query("q") String str, @Query("query_params") String str2);

        @POST("/device_remote_wipe_completed")
        Response sendCompleted(@Query("device_serial") String str, @Query("failed") Boolean bool, @Query("failureReason") String str2);
    }

    @Inject
    public RemoteWipeMethods(@OculusRestAdapter RestAdapter restAdapter) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }

    public final boolean A00() throws ApiException {
        DeviceManifestWipeStatusRequest deviceManifestWipeStatusRequest = new DeviceManifestWipeStatusRequest(BuildSerialUtil.A00());
        try {
            DeviceManifestWipeStatusResponse deviceManifestWipeStatus = this.mMethods.getDeviceManifestWipeStatus(RemoteWipeQueries.DEVICE_MANIFEST_WIPE_STATUS, GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A02("serial_number", deviceManifestWipeStatusRequest.mSerialNumber)));
            if (deviceManifestWipeStatus.deviceManifests.nodes.isEmpty() || !WIPE_PENDING.equals(deviceManifestWipeStatus.deviceManifests.nodes.get(0).deviceWipeStatus)) {
                return false;
            }
            return true;
        } catch (RetrofitError e) {
            throw new ApiException(e, new ApiError(e));
        }
    }
}
