package com.oculus.userserver.managerservice;

import X.AnonymousClass06;
import X.BZ;
import X.Mi;
import X.Om;
import X.QN;
import X.SZ;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.common.collect.RegularImmutableMap;
import com.oculus.content.OculusPublicContentProvider;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.http.core.base.ApiError;
import com.oculus.userserver.api.sharing.SharingManager;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import com.oculus.userserver.managerservice.callerverifiers.SignatureVerifier;
import com.oculus.userserver.net.OculusUserMethods;
import javax.annotation.Nullable;
import retrofit.RetrofitError;

public class SharingProvider extends OculusPublicContentProvider {
    public Om _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(SZ sz, SharingProvider sharingProvider) {
        sharingProvider._UL_mInjectionContext = new Om(2, sz);
    }

    @Override // X.AbstractC0195ed
    public int doDelete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // X.AbstractC0195ed
    public String doGetType(Uri uri) {
        return null;
    }

    @Override // X.AbstractC0195ed
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // X.AbstractC0195ed
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // X.AbstractC0195ed
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    private Bundle getLibrarySharingEnabled() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SharingManagerContract.ARGUMENT_IS_ENABLED, ((SharingStore) BZ.A02(0, 20, ((SharingManagerImpl) BZ.A02(0, 40, this._UL_mInjectionContext))._UL_mInjectionContext)).mPrefs.getBoolean(SharingStore.KEY_SHARING_ENABLED, false));
        return bundle;
    }

    private Bundle setLibrarySharingEnabled(@Nullable Bundle bundle) {
        if (bundle == null) {
            throw new NullPointerException("Arguments bundle is null");
        } else if (bundle.containsKey(SharingManagerContract.ARGUMENT_IS_ENABLED)) {
            boolean z = bundle.getBoolean(SharingManagerContract.ARGUMENT_IS_ENABLED);
            SharingManagerContract.ResultCode resultCode = SharingManagerContract.ResultCode.RESULT_OK;
            try {
                SharingManagerImpl sharingManagerImpl = (SharingManagerImpl) BZ.A02(0, 40, this._UL_mInjectionContext);
                try {
                    OculusUserBackend oculusUserBackend = (OculusUserBackend) BZ.A02(1, 8, sharingManagerImpl._UL_mInjectionContext);
                    String A00 = OculusUserBackend.A00(oculusUserBackend, Binder.getCallingUserHandle());
                    if (!TextUtils.isEmpty(A00)) {
                        try {
                            OculusUserMethods.Methods methods = ((OculusUserMethods) BZ.A02(0, 61, oculusUserBackend._UL_mInjectionContext)).mMethods;
                            String A03 = AnonymousClass06.A03("Bearer ", A00);
                            String bool = Boolean.toString(z);
                            QN.A01("set_family_sharing_enabled", bool);
                            methods.setFamilyDevice(A03, OculusUserMethods.Queries.SET_FAMILY_DEVICE, GraphQLParamsHelper.A00(RegularImmutableMap.A00(1, new Object[]{"set_family_sharing_enabled", bool})), "");
                            ((SharingStore) BZ.A02(0, 20, sharingManagerImpl._UL_mInjectionContext)).mPrefs.edit().putBoolean(SharingStore.KEY_SHARING_ENABLED, z).apply();
                            Bundle bundle2 = new Bundle();
                            bundle2.putInt(SharingManagerContract.RESULT_CODE, resultCode.getCode());
                            return bundle2;
                        } catch (RetrofitError e) {
                            throw new ApiError(e);
                        }
                    } else {
                        throw new IllegalStateException("User not logged in or not identify");
                    }
                } catch (ApiError e2) {
                    Mi.A02(SharingManagerImpl.TAG, "Received network error", e2);
                    throw new SharingManager.SharingManagerException(SharingManagerContract.ResultCode.NETWORK_ERROR);
                } catch (RuntimeException e3) {
                    Mi.A02(SharingManagerImpl.TAG, "Received client error", e3);
                    throw new SharingManager.SharingManagerException(SharingManagerContract.ResultCode.ERROR_UNKNOWN);
                }
            } catch (SharingManager.SharingManagerException e4) {
                resultCode = e4.mCode;
            }
        } else {
            throw new IllegalArgumentException("Argument enabled is missing");
        }
    }

    @Override // X.AbstractC0195ed
    @Nullable
    public Bundle doCall(@Nullable String str, @Nullable String str2, @Nullable Bundle bundle) {
        String str3;
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -1122460303) {
                if (hashCode == 1826973309 && str.equals(SharingManagerContract.METHOD_SET_LIBRARY_SHARING_ENABLED)) {
                    return setLibrarySharingEnabled(bundle);
                }
            } else if (str.equals(SharingManagerContract.METHOD_GET_LIBRARY_SHARING_ENABLED)) {
                return getLibrarySharingEnabled();
            }
            str3 = "Unknown method";
        } else {
            str3 = "Method is null";
        }
        throw new IllegalArgumentException(str3);
    }

    @Override // com.facebook.secure.content.PublicContentProvider, X.AbstractC0195ed
    public boolean onCheckPermissions() {
        ((SignatureVerifier) BZ.A02(1, 16, this._UL_mInjectionContext)).A01(SignatureVerifier.APP_SYSTEM_UTILITIES, SignatureVerifier.APP_SYSTEMUX, SignatureVerifier.APP_HORIZON, SignatureVerifier.APP_OCMS, SignatureVerifier.APP_HOME);
        return true;
    }

    public static final void _UL_injectMe(Context context, SharingProvider sharingProvider) {
        _UL_staticInjectMe((SZ) BZ.get(context), sharingProvider);
    }

    @Override // com.oculus.content.OculusPublicContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
    }
}
