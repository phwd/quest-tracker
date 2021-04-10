package com.oculus.horizon.accountlinking.dropbox;

import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.accountlinking.dropbox.annotation.DropboxRestAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.POST;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_horizon_accountlinking_dropbox_annotation_DropboxRestAdapter_ULSEP_BINDING_ID"})
public class DropboxAPIMethods {
    public Methods mMethods;

    public interface Methods {
        @POST("/auth/token/revoke")
        void revokeAccessToken(@Body String str, Callback<Object> callback);
    }

    @Inject
    public DropboxAPIMethods(@DropboxRestAdapter RestAdapter restAdapter) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
