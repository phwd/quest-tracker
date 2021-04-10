package com.oculus.horizon.auth.shared_datastore;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.CredentialsChangedHandler;
import com.oculus.auth.storage.AuthDatastore;
import java.util.Set;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_horizon_auth_shared_ULUNDERSCORE_datastore_HorizonAuthDatastoreSharedPrefs_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_horizon_profile_UserProfileSharedPrefs_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULSEP_BINDING_ID"})
public class HorizonAuthDatastore implements AuthDatastore {
    public static final String KEY_ACCESS_TOKEN = "access_token";
    @VisibleForTesting
    public static final String KEY_CREDENTIALS_UPDATE_TIME_MILLIS = "key_up_credentials_update_time_millis";
    @VisibleForTesting
    public static final String KEY_DEVICE_SCOPED_ACCESS_TOKEN_EXPIRATION_TIME_BOOT_COUNT = "device_scoped_access_token_expiration_time_boot_count";
    @VisibleForTesting
    public static final String KEY_DEVICE_SCOPED_ACCESS_TOKEN_EXPIRATION_TIME_ELAPSED_REALTIME = "device_scoped_access_token_expiration_time_elapsed_realtime";
    @VisibleForTesting
    public static final String KEY_DEVICE_SCOPED_ACCESS_TOKEN_VALUE = "device_scoped_access_token_value";
    public static final String KEY_UID = "uid";
    public static final String KEY_UP_ACCESS_TOKEN = "key_access_token";
    public static final String KEY_UP_USER_ID = "key_user_id";
    public static final String KEY_UP_USER_OBJECT = "key_user_object";
    public static final String TAG = "HorizonAuthDatastore";
    public AnonymousClass0QC _UL_mInjectionContext;

    private void A00() {
        ((Set) AnonymousClass0J2.A03(2, 447, this._UL_mInjectionContext)).size();
        for (CredentialsChangedHandler credentialsChangedHandler : (Set) AnonymousClass0J2.A03(2, 447, this._UL_mInjectionContext)) {
            credentialsChangedHandler.onCredentialsChanged();
        }
    }

    private void A01() {
        ((Set) AnonymousClass0J2.A03(2, 447, this._UL_mInjectionContext)).size();
        for (CredentialsChangedHandler credentialsChangedHandler : (Set) AnonymousClass0J2.A03(2, 447, this._UL_mInjectionContext)) {
            credentialsChangedHandler.onDeviceScopedCredentialsChanged();
        }
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    @Nullable
    public final void clear() {
        ((SharedPreferences) AnonymousClass0J2.A03(0, 218, this._UL_mInjectionContext)).edit().clear().apply();
        A00();
        A01();
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public final void clearCredentials() {
        ((SharedPreferences) AnonymousClass0J2.A03(0, 218, this._UL_mInjectionContext)).edit().remove("access_token").remove(KEY_DEVICE_SCOPED_ACCESS_TOKEN_VALUE).remove(KEY_DEVICE_SCOPED_ACCESS_TOKEN_EXPIRATION_TIME_BOOT_COUNT).remove(KEY_DEVICE_SCOPED_ACCESS_TOKEN_EXPIRATION_TIME_ELAPSED_REALTIME).apply();
        A00();
        A01();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00a0, code lost:
        if (r0 == null) goto L_0x00a2;
     */
    @Override // com.oculus.auth.storage.AuthDatastore
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.oculus.auth.credentials.Credentials getCredentials() {
        /*
        // Method dump skipped, instructions count: 211
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.auth.shared_datastore.HorizonAuthDatastore.getCredentials():com.oculus.auth.credentials.Credentials");
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public final long getCredentialsUpdateTimeMillis() {
        return ((SharedPreferences) AnonymousClass0J2.A03(0, 218, this._UL_mInjectionContext)).getLong(KEY_CREDENTIALS_UPDATE_TIME_MILLIS, 0);
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    @Nullable
    public final String getUserId() {
        return ((SharedPreferences) AnonymousClass0J2.A03(0, 218, this._UL_mInjectionContext)).getString("uid", null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x003e, code lost:
        if (com.google.common.base.Objects.equal(r12.mAccessToken, r3) == false) goto L_0x0040;
     */
    @Override // com.oculus.auth.storage.AuthDatastore
    @android.annotation.SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void storeCredentials(com.oculus.auth.credentials.Credentials r12) {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.auth.shared_datastore.HorizonAuthDatastore.storeCredentials(com.oculus.auth.credentials.Credentials):void");
    }

    @Inject
    public HorizonAuthDatastore(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }
}
