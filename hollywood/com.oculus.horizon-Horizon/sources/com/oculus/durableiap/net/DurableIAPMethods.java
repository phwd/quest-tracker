package com.oculus.durableiap.net;

import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.common.DsatAuthorization;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID"})
public class DurableIAPMethods {
    public final Methods mMethods;

    public interface Methods {
        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        @Headers({DsatAuthorization.ENABLE})
        IAPEntitlementsResponse getDurableIAP(@Query("doc") String str);
    }

    @Inject
    public DurableIAPMethods(@OculusRestAdapter RestAdapter restAdapter) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
