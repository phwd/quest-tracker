package com.oculus.friendlistcontentprovider;

import X.AnonymousClass0MD;
import X.AnonymousClass0NC;
import X.AnonymousClass0VF;
import X.AnonymousClass0k6;
import X.AnonymousClass0kO;
import X.AnonymousClass0lg;
import X.C02920k8;
import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableSet;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.content.OculusFbPermissionsContentProvider;
import com.oculus.unlockulus_helper.UnlockulusHelper;
import com.oculus.unlockulus_helper.inject.UnlockulusModule;
import java.util.Collections;
import java.util.HashMap;
import javax.inject.Provider;

public abstract class OculusLoggedInUserFbPermissionsContentProvider extends OculusFbPermissionsContentProvider {
    public static final String TAG = "OculusLoggedInUserFbPermissionsContentProvider";
    @Inject
    public Provider<Credentials> mCredentialsProvider;
    public AnonymousClass0kO mTrustedApp;
    @Inject
    @Eager
    public UnlockulusHelper mUnlockulusHelper;
    public ImmutableSet<String> mWhitelistedPackages;

    public abstract ImmutableSet<String> getWhitelistedPackages();

    private ImmutableSet<C02920k8> getAppSignatures(Context context) {
        AnonymousClass0NC r2 = new AnonymousClass0NC();
        if (this.mUnlockulusHelper.isEmployeeWithWhitelistedDevice(context, false)) {
            r2.A04(AnonymousClass0k6.A01);
        }
        r2.A04(AnonymousClass0k6.A0L);
        return r2.build();
    }

    @Nullable
    public final String getLoggedInUserId() {
        Credentials credentials = this.mCredentialsProvider.get();
        if (credentials != null) {
            return credentials.mUserId;
        }
        AnonymousClass0MD.A04(TAG, "User is not signed in");
        return null;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AnonymousClass0jF
    public final boolean onCheckPermissions() {
        if (this.mCredentialsProvider.get() == null) {
            AnonymousClass0MD.A04(TAG, "User is not signed in");
        } else if (!super.onCheckPermissions() && !super.onCheckPermissions() && !this.mTrustedApp.A03(getContext())) {
            return false;
        } else {
            return true;
        }
        return false;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AnonymousClass0jF
    public final boolean onCheckReadOnlyPermissions() {
        if (this.mCredentialsProvider.get() == null) {
            AnonymousClass0MD.A04(TAG, "User is not signed in");
        } else if (super.onCheckReadOnlyPermissions() || this.mTrustedApp.A03(getContext())) {
            return true;
        }
        return false;
    }

    public static final void _UL_injectMe(Context context, OculusLoggedInUserFbPermissionsContentProvider oculusLoggedInUserFbPermissionsContentProvider) {
        _UL_staticInjectMe((AnonymousClass0lg) AnonymousClass0VF.get(context), oculusLoggedInUserFbPermissionsContentProvider);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r1, OculusLoggedInUserFbPermissionsContentProvider oculusLoggedInUserFbPermissionsContentProvider) {
        oculusLoggedInUserFbPermissionsContentProvider.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r1);
        oculusLoggedInUserFbPermissionsContentProvider.mUnlockulusHelper = UnlockulusModule._UL__ULSEP_com_oculus_unlockulus_ULUNDERSCORE_helper_UnlockulusHelper_ULSEP_ACCESS_METHOD(r1);
    }

    @Override // com.oculus.content.OculusFbPermissionsContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
        this.mWhitelistedPackages = getWhitelistedPackages();
        ImmutableSet<C02920k8> appSignatures = getAppSignatures(getContext());
        ImmutableSet<String> immutableSet = this.mWhitelistedPackages;
        HashMap hashMap = new HashMap();
        for (C02920k8 r1 : appSignatures) {
            hashMap.put(r1, Collections.unmodifiableSet(immutableSet));
        }
        this.mTrustedApp = new AnonymousClass0kO(Collections.unmodifiableMap(hashMap));
    }
}
