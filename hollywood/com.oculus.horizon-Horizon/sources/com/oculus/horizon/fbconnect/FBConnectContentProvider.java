package com.oculus.horizon.fbconnect;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0L8;
import X.AnonymousClass0NO;
import X.AnonymousClass117;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.internal.ImageRequest;
import com.facebook.ultralight.Eager;
import com.oculus.auth.credentials.CredentialsManager;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.content.OculusPublicContentProvider;
import com.oculus.horizon.fbconnect.contract.FBConnectContent;
import com.oculus.horizon.linkedaccounts.provider.verifier.LinkedAccountsCallerVerifier;
import com.oculus.http.core.base.ApiErrorCodes;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.inject.Inject;

@Deprecated
public class FBConnectContentProvider extends OculusPublicContentProvider {
    public static final int DEFAULT_PROFILE_PICTURE_SIZE = 72;
    public static final String TAG = "FBConnectContentProvider";
    @Inject
    @Eager
    public CredentialsManager mCredentialsManager;
    @Inject
    @Eager
    public FBConnectHelper mFBConnectHelper;
    @Inject
    @Eager
    public LinkedAccountsCallerVerifier mLinkedAccountsCallerVerifier;
    @Inject
    @Eager
    public FBConnectLogger mLogger;

    @Override // X.AbstractC09361bk
    @Nullable
    public String doGetType(Uri uri) {
        return null;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        FBConnectLogger fBConnectLogger;
        String str3;
        String str4;
        if (uri == null) {
            AnonymousClass0NO.A08(TAG, "Got unexpected null URI");
            return null;
        }
        if (FBConnectContent.Account.CONTENT_URI.equals(uri)) {
            if (this.mCredentialsManager.getCredentials() == null) {
                fBConnectLogger = this.mLogger;
                str3 = FBConnectLogger.MESSAGE_NO_CREDENTIALS;
            } else {
                AccessToken currentFBAccessToken = this.mFBConnectHelper.getCurrentFBAccessToken();
                Profile currentFBProfile = this.mFBConnectHelper.getCurrentFBProfile();
                if (currentFBAccessToken == null) {
                    fBConnectLogger = this.mLogger;
                    str3 = FBConnectLogger.MESSAGE_NO_ACCESS_TOKEN;
                } else {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    arrayList.add("userid");
                    arrayList2.add(currentFBAccessToken.userId);
                    arrayList.add(FBConnectContent.Account.ACCESS_TOKEN);
                    arrayList2.add(currentFBAccessToken.token);
                    if (currentFBProfile != null) {
                        arrayList.add("user_name");
                        arrayList2.add(currentFBProfile.name);
                        arrayList.add(FBConnectContent.Account.PROFILE_PICTURE);
                        arrayList2.add(ImageRequest.getProfilePictureUri(currentFBProfile.id, 72, 72));
                        str4 = FBConnectLogger.MESSAGE_WITH_PROFILE;
                    } else {
                        Profile.fetchProfileForCurrentAccessToken();
                        str4 = FBConnectLogger.MESSAGE_WITHOUT_PROFILE;
                    }
                    MatrixCursor matrixCursor = new MatrixCursor((String[]) arrayList.toArray(new String[arrayList.size()]), 1);
                    matrixCursor.addRow(arrayList2.toArray());
                    this.mLogger.reportProviderQuerySuccess(this, str4);
                    return matrixCursor;
                }
            }
        } else if (uri.toString().startsWith(FBConnectContent.AppScopedAccessToken.URI.toString())) {
            AnonymousClass0DC<AccessToken> fetchFBTokenAsync = this.mFBConnectHelper.fetchFBTokenAsync(uri.getQueryParameter(FBConnectContent.AppScopedAccessToken.OVERRIDE_OC_APP_ID));
            try {
                fetchFBTokenAsync.A0H();
                AccessToken A0G = fetchFBTokenAsync.A0G();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                arrayList3.add("access_token");
                arrayList4.add(A0G.token);
                arrayList3.add("fb_app_id");
                arrayList4.add(A0G.applicationId);
                arrayList3.add("userid");
                arrayList4.add(A0G.userId);
                MatrixCursor matrixCursor2 = new MatrixCursor((String[]) arrayList3.toArray(new String[arrayList3.size()]), 1);
                matrixCursor2.addRow(arrayList4.toArray());
                this.mLogger.reportProviderQuerySuccess(this, FBConnectLogger.MESSAGE_GENERATE_TOKEN);
                return matrixCursor2;
            } catch (InterruptedException e) {
                AnonymousClass0NO.A0H(TAG, e, "Generate access token fetch task was interrupted.");
                return null;
            }
        } else {
            fBConnectLogger = this.mLogger;
            str3 = FBConnectLogger.MESSAGE_UNSUPPORTED_URI;
        }
        fBConnectLogger.reportProviderQueryFailure(this, str3);
        return null;
    }

    @Override // X.AbstractC09361bk
    public int doDelete(Uri uri, String str, String[] strArr) {
        FBConnectLogger fBConnectLogger;
        String str2;
        if (!FBConnectContent.Account.CONTENT_URI.equals(uri)) {
            fBConnectLogger = this.mLogger;
            str2 = FBConnectLogger.MESSAGE_UNSUPPORTED_URI;
        } else if (this.mCredentialsManager.getCredentials() == null) {
            fBConnectLogger = this.mLogger;
            str2 = FBConnectLogger.MESSAGE_NO_CREDENTIALS;
        } else {
            this.mFBConnectHelper.clearCurrentFBAccessToken();
            this.mLogger.reportProviderDeleteSuccess(this);
            return 1;
        }
        fBConnectLogger.reportProviderDeleteFailure(this, str2);
        return 0;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        FBConnectLogger fBConnectLogger;
        String str;
        if (!FBConnectContent.Account.CONTENT_URI.equals(uri)) {
            fBConnectLogger = this.mLogger;
            str = FBConnectLogger.MESSAGE_UNSUPPORTED_URI;
        } else if (this.mCredentialsManager.getCredentials() == null) {
            fBConnectLogger = this.mLogger;
            str = FBConnectLogger.MESSAGE_NO_CREDENTIALS;
        } else {
            String asString = contentValues.getAsString("userid");
            String asString2 = contentValues.getAsString(FBConnectContent.Account.ACCESS_TOKEN);
            if (!AnonymousClass0L8.A00(asString, asString2)) {
                if (this.mFBConnectHelper.setCurrentFBAccount(asString, asString2)) {
                    this.mLogger.reportProviderInsertSuccess(this);
                }
                return uri;
            }
            throw new IllegalArgumentException("Invalid Facebook user ID and/or access token.");
        }
        fBConnectLogger.reportProviderInsertFailure(this, str);
        return null;
    }

    @Override // X.AbstractC09361bk
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.content.PublicContentProvider, X.AbstractC09361bk
    public final boolean onCheckPermissions() {
        boolean A00 = this.mLinkedAccountsCallerVerifier.A00();
        this.mLogger.reportProviderPermissionCheck(this, A00);
        return A00;
    }

    public static final void _UL_injectMe(Context context, FBConnectContentProvider fBConnectContentProvider) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), fBConnectContentProvider);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r1, FBConnectContentProvider fBConnectContentProvider) {
        fBConnectContentProvider.mCredentialsManager = CredentialsModule._UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_ACCESS_METHOD(r1);
        fBConnectContentProvider.mFBConnectHelper = FBConnectHelper._UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectHelper_ULSEP_ACCESS_METHOD(r1);
        fBConnectContentProvider.mLinkedAccountsCallerVerifier = (LinkedAccountsCallerVerifier) AnonymousClass117.A00(ApiErrorCodes.ERROR_CODE_FAILED_AUTHENTICATION_WRONG_PASSWORD, r1);
        fBConnectContentProvider.mLogger = FBConnectLogger._UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectLogger_ULSEP_ACCESS_METHOD(r1);
    }

    @Override // com.oculus.content.OculusPublicContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
    }
}
