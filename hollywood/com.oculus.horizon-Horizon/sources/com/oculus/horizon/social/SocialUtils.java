package com.oculus.horizon.social;

import X.AbstractC06640p5;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.base.Function;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.horizon.api.common.user.BasicUser;
import com.oculus.horizon.social.model.FriendRequest;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID"})
public class SocialUtils {
    public static final Class<?> TAG = SocialUtils.class;
    public static final Function<FriendRequest, BasicUser> USER_OF_FRIEND_REQUEST = new Function<FriendRequest, BasicUser>() {
        /* class com.oculus.horizon.social.SocialUtils.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
        @Override // com.google.common.base.Function
        public final BasicUser apply(FriendRequest friendRequest) {
            return friendRequest.user;
        }
    };
    @Inject
    public final Provider<Credentials> mCredentialsProvider;

    @Inject
    public SocialUtils(AbstractC06640p5 r2) {
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r2);
    }
}
