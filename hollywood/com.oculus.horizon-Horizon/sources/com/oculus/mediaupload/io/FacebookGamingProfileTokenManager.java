package com.oculus.mediaupload.io;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_mediaupload_io_MediaUploaderDB_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_api_OculusShareMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_io_MediaUploaderNotifications_ULSEP_BINDING_ID"})
public class FacebookGamingProfileTokenManager {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String HOST = "mediaupload";
    public static final String IMMEDIATE_AUTO_SYNC = "IMMEDIATE";
    public static final String OC_FACEBOOK_GAMING_APP_ID = "2700392956712059";
    public static final String SCHEME = "oculus";
    public AnonymousClass0QC _UL_mInjectionContext;

    public enum TokenValidationError {
        TOKEN_IS_MISSING,
        TOKEN_IS_INVALID,
        WRONG_SCHEME,
        WRONG_HOST,
        MISSING_ACCESS_TOKEN
    }

    public static class TokenValidationException extends Throwable {
        public final TokenValidationError error;

        public TokenValidationException(TokenValidationError tokenValidationError) {
            this.error = tokenValidationError;
        }
    }

    @Inject
    public FacebookGamingProfileTokenManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }
}
