package com.oculus.horizon.abuse_prevention;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.inject.name.Named;
import com.oculus.config.trusted_user.TrustedUserModule;
import com.oculus.unlockulus_helper.UnlockulusHelper;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_java_lang_Boolean_ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_is_ULUNDERSCORE_trusted_ULUNDERSCORE_user_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unlockulus_ULUNDERSCORE_helper_UnlockulusHelper_ULSEP_BINDING_ID"})
public class FileReceiverManager {
    public static final String ACTION_REQUEST_EVIDENCE_VIDEO = "com.oculus.horizon.abuse_prevention.ACTION_REQUEST_EVIDENCE_VIDEO";
    public static final String AUTHORITY = "com.oculus.horizon.abuse_prevention.fileprovider";
    public static final String KEY_RECORDING_UUID = "recording_uuid";
    public static final String TAG = "FileReceiver";
    public static final String TARGET_PACKAGE = "com.oculus.vrshell";
    @Inject
    @Named(TrustedUserModule.IS_TRUSTED_USER_GK)
    public final Provider<Boolean> mIsTrustedUser;
    @Inject
    @Eager
    public final UnlockulusHelper mUnlockulusHelper;

    @Inject
    public FileReceiverManager(AbstractC06640p5 r2) {
        this.mIsTrustedUser = TrustedUserModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_is_ULUNDERSCORE_trusted_ULUNDERSCORE_user_ULSEP_ACCESS_METHOD(r2);
        this.mUnlockulusHelper = (UnlockulusHelper) AnonymousClass117.A00(296, r2);
    }
}
