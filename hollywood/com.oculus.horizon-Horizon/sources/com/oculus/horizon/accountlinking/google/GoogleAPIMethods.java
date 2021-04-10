package com.oculus.horizon.accountlinking.google;

import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.accountlinking.google.annotation.GoogleRestAdapterBuilder;
import com.oculus.horizon.api.accountlinking.RefreshAccessTokenResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_Builder_ULSEP_com_oculus_horizon_accountlinking_google_annotation_GoogleRestAdapterBuilder_ULSEP_BINDING_ID"})
public class GoogleAPIMethods {
    public static final String CLIENT_ID = "836984125408-139ejjutu9rc641k95ct6j6d4ahigg8a.apps.googleusercontent.com";
    public static final String ENDPOINT_GOOGLE_ACCOUNTS = "https://accounts.google.com";
    public static final String ENDPOINT_GOOGLE_API = "https://www.googleapis.com";
    public static final String GRANT_TYPE = "refresh_token";
    public final APIMethods mAPIMethods;
    public final OAuthMethods mOAuthMethods;

    public interface APIMethods {
        @POST("/oauth2/v4/token")
        @FormUrlEncoded
        void refreshAccessToken(@Field("client_id") String str, @Field("refresh_token") String str2, @Field("grant_type") String str3, Callback<RefreshAccessTokenResponse> callback);
    }

    public interface OAuthMethods {
        @POST("/o/oauth2/revoke")
        @FormUrlEncoded
        void revokeToken(@Field("token") String str, Callback<Object> callback);
    }

    @Inject
    public GoogleAPIMethods(@GoogleRestAdapterBuilder RestAdapter.Builder builder) {
        builder.setEndpoint(ENDPOINT_GOOGLE_API);
        this.mAPIMethods = (APIMethods) builder.build().create(APIMethods.class);
        builder.setEndpoint(ENDPOINT_GOOGLE_ACCOUNTS);
        this.mOAuthMethods = (OAuthMethods) builder.build().create(OAuthMethods.class);
    }
}
