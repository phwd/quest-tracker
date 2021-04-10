package com.oculus.friendlistcontentprovider;

import X.AbstractC06640p5;
import X.AnonymousClass0CC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0bQ;
import X.AnonymousClass117;
import X.C02780bN;
import X.C02870bf;
import X.C02880bg;
import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableSet;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.content.OculusFbPermissionsContentProvider;
import com.oculus.unlockulus_helper.UnlockulusHelper;
import javax.inject.Provider;

public abstract class OculusLoggedInUserFbPermissionsContentProvider extends OculusFbPermissionsContentProvider {
    public static final String TAG = "OculusLoggedInUserFbPermissionsContentProvider";
    @Inject
    public Provider<Credentials> mCredentialsProvider;
    public C02870bf mTrustedApp;
    @Inject
    @Eager
    public UnlockulusHelper mUnlockulusHelper;
    public ImmutableSet<String> mWhitelistedPackages;

    public abstract ImmutableSet<String> getWhitelistedPackages();

    private ImmutableSet<AnonymousClass0bQ> getAppSignatures(Context context) {
        AnonymousClass0CC r1 = new AnonymousClass0CC();
        if (this.mUnlockulusHelper.A00(context)) {
            r1.A04(C02780bN.A01);
        }
        r1.A04(C02780bN.A0L);
        return r1.build();
    }

    @Nullable
    public final String getLoggedInUserId() {
        Credentials credentials = this.mCredentialsProvider.get();
        if (credentials != null) {
            return credentials.mUserId;
        }
        AnonymousClass0NO.A08(TAG, "User is not signed in");
        return null;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AbstractC09361bk
    public final boolean onCheckPermissions() {
        if (this.mCredentialsProvider.get() == null) {
            AnonymousClass0NO.A08(TAG, "User is not signed in");
        } else if (!super.onCheckPermissions() && !super.onCheckPermissions() && !this.mTrustedApp.A06(getContext())) {
            return false;
        } else {
            return true;
        }
        return false;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AbstractC09361bk
    public final boolean onCheckReadOnlyPermissions() {
        if (this.mCredentialsProvider.get() == null) {
            AnonymousClass0NO.A08(TAG, "User is not signed in");
        } else if (super.onCheckReadOnlyPermissions() || this.mTrustedApp.A06(getContext())) {
            return true;
        }
        return false;
    }

    public static final void _UL_injectMe(Context context, OculusLoggedInUserFbPermissionsContentProvider oculusLoggedInUserFbPermissionsContentProvider) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), oculusLoggedInUserFbPermissionsContentProvider);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r1, OculusLoggedInUserFbPermissionsContentProvider oculusLoggedInUserFbPermissionsContentProvider) {
        oculusLoggedInUserFbPermissionsContentProvider.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r1);
        oculusLoggedInUserFbPermissionsContentProvider.mUnlockulusHelper = (UnlockulusHelper) AnonymousClass117.A00(296, r1);
    }

    @Override // com.oculus.content.OculusFbPermissionsContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
        this.mWhitelistedPackages = getWhitelistedPackages();
        this.mTrustedApp = C02880bg.A02(getAppSignatures(getContext()), this.mWhitelistedPackages);
    }
}
