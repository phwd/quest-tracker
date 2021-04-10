package com.oculus.userserver.managerservice;

import X.BZ;
import X.C00179c;
import X.Hw;
import X.IX;
import X.Mi;
import X.Om;
import X.SZ;
import X.TW;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.Binder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.binder.BindingStrategy;
import com.oculus.userserver.api.user.OculusUser;
import com.oculus.userserver.api.user.SparseOculusUser;
import com.oculus.userserver.managerservice.OumsTelemetry;
import com.oculus.userserver.managerservice.callerverifiers.SignatureVerifier;
import com.oculus.userserver.os.FrameworkWrapper;
import com.squareup.okhttp.internal.DiskLruCache;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_userserver_managerservice_PictureApplier_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_managerservice_callerverifiers_SignatureVerifier_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_managerservice_OculusUserStore_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_managerservice_OculusUserBackend_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_managerservice_UserSyncScheduler_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OculusUserManagerImpl {
    public static final String NEW_USER_NAME = "New User";
    public static final String STATUS_SUSPENDED = "SUSPENDED";
    public static final String TAG = "OculusUserManagerImpl";
    public static volatile OculusUserManagerImpl _UL__ULSEP_com_oculus_userserver_managerservice_OculusUserManagerImpl_ULSEP_INSTANCE;
    public Om _UL_mInjectionContext;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    @Eager
    public final SignatureVerifier mSignatureVerifier;

    public final synchronized void A04() {
        this.mSignatureVerifier.A01(SignatureVerifier.APP_SYSTEM_UTILITIES, SignatureVerifier.APP_HOME, SignatureVerifier.APP_SYSTEMUX);
        if (Binder.getCallingUserHandle() == FrameworkWrapper.SYSTEM) {
            OculusUserInternal A01 = A01(this).A01(0);
            if (A01 != null) {
                if (TextUtils.isEmpty(A01.mAccountId)) {
                    try {
                        String str = new AuthServiceClient(this.mContext, BindingStrategy.DEFAULT).A00().mUserId;
                        if (TextUtils.isEmpty(str)) {
                            Mi.A01(TAG, "Owner user is not logged in");
                        } else {
                            A01(this).A05(0, str, "");
                        }
                    } catch (RemoteException | InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                }
                OculusUser A00 = A00(this);
                if (A00 == null) {
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        UserManager userManager = (UserManager) this.mContext.getSystemService(UserManager.class);
                        UserInfo A012 = FrameworkWrapper.A01(userManager);
                        if (A012 == null) {
                            Mi.A00(TAG, "Could not create user.");
                            OumsTelemetry.A00(OumsTelemetry.EVENT_FRAMEWORK_FAILURE, OumsTelemetry.FrameworkCallType.CREATE_USER, OumsTelemetry.NULL_ID);
                        } else {
                            FrameworkWrapper.A07(userManager, UserHandle.of(A012.id));
                            OumsTelemetry.A00(OumsTelemetry.EVENT_FRAMEWORK_SUCCESS, OumsTelemetry.FrameworkCallType.CREATE_USER, A012.id);
                            A00 = new OculusUser(A012.id, A012.name, "", A012.creationTime, false);
                            Binder.restoreCallingIdentity(clearCallingIdentity);
                        }
                    } finally {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                    }
                }
                OculusUserStore A013 = A01(this);
                OculusUserInternal oculusUserInternal = new OculusUserInternal(A00, null, null);
                synchronized (A013) {
                    int i = oculusUserInternal.mOculusUser.mUserId;
                    if (A013.mCache.containsKey(Integer.valueOf(i))) {
                        Mi.A05(OculusUserStore.TAG, "Inserting duplicate user, userId: %d", Integer.valueOf(i));
                    }
                    A013.mCache.put(Integer.valueOf(i), oculusUserInternal);
                    OculusUserStore.A00(A013);
                }
                int i2 = A00.mUserId;
                if (!FrameworkWrapper.A08((ActivityManager) this.mContext.getSystemService(ActivityManager.class), i2)) {
                    Mi.A04(TAG, "Could not switch to new user %d", Integer.valueOf(i2));
                    OumsTelemetry.A00(OumsTelemetry.EVENT_FRAMEWORK_FAILURE, OumsTelemetry.FrameworkCallType.SWITCH_NEW_USER, i2);
                } else {
                    OumsTelemetry.A00(OumsTelemetry.EVENT_FRAMEWORK_SUCCESS, OumsTelemetry.FrameworkCallType.SWITCH_NEW_USER, i2);
                }
            } else {
                throw new IllegalStateException("Owner user is missing");
            }
        } else {
            throw new IllegalStateException("createUserAndSwitch() was not called by device owner");
        }
    }

    public final synchronized void A05() {
        this.mSignatureVerifier.A01(SignatureVerifier.APP_SYSTEM_UTILITIES);
        OculusUser A00 = A00(this);
        if (A00 != null) {
            A03(this, A00.mUserId);
        }
    }

    /* JADX INFO: finally extract failed */
    public final synchronized void A06() {
        int i;
        int i2;
        boolean z;
        String str;
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            List<UserInfo> A03 = FrameworkWrapper.A03((UserManager) this.mContext.getSystemService(UserManager.class));
            Binder.restoreCallingIdentity(clearCallingIdentity);
            C00179c r2 = new C00179c();
            for (UserInfo userInfo : A03) {
                r2.A00(Integer.valueOf(userInfo.id));
            }
            ImmutableSet A02 = r2.build();
            TW A0H = A02.iterator();
            int i3 = 0;
            int i4 = 0;
            while (A0H.hasNext()) {
                int intValue = ((Integer) A0H.next()).intValue();
                if (intValue != 0 && A01(this).A01(intValue) == null) {
                    if (A02(intValue)) {
                        i3++;
                    } else {
                        i4++;
                    }
                }
            }
            int i5 = 0;
            for (OculusUserInternal oculusUserInternal : A01(this).A02()) {
                int i6 = oculusUserInternal.mOculusUser.mUserId;
                if (i6 != 0 && !A02.contains(Integer.valueOf(i6))) {
                    A01(this).A03(i6);
                    i5++;
                }
            }
            try {
                ImmutableMap<String, String> A022 = ((OculusUserBackend) BZ.A02(1, 8, this._UL_mInjectionContext)).A02();
                if (A022 != null) {
                    i2 = 0;
                    i = 0;
                    for (OculusUserInternal oculusUserInternal2 : A01(this).A02()) {
                        int i7 = oculusUserInternal2.mOculusUser.mUserId;
                        if (i7 == 0 || (str = oculusUserInternal2.mAccountId) == null || A022.containsKey(str)) {
                            A01(this).A04(oculusUserInternal2.mOculusUser.mUserId, new SparseOculusUser(new SparseOculusUser.Builder()), STATUS_SUSPENDED.equals(A022.get(oculusUserInternal2.mAccountId)));
                        } else if (A03(this, i7)) {
                            i2++;
                        } else {
                            i++;
                        }
                    }
                    z = true;
                    OumsTelemetry.A01(z, i2, i, i3, i4, i5);
                }
            } catch (IllegalStateException e) {
                Mi.A06(TAG, e, "Error fetching backend users");
            }
            i2 = 0;
            i = 0;
            z = false;
            OumsTelemetry.A01(z, i2, i, i3, i4, i5);
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    public final synchronized void A07(int i) {
        OculusUserInternal A01;
        this.mSignatureVerifier.A01(SignatureVerifier.APP_SYSTEM_UTILITIES, SignatureVerifier.APP_HOME, SignatureVerifier.APP_SYSTEMUX);
        if (Binder.getCallingUserHandle() == FrameworkWrapper.SYSTEM) {
            if (!Hw.A01("debug.oculus.disable_device_identity_features").equals(DiskLruCache.VERSION_1) && (A01 = A01(this).A01(i)) != null && !TextUtils.isEmpty(A01.mAccountId)) {
                OculusUserBackend.A01((OculusUserBackend) BZ.A02(1, 8, this._UL_mInjectionContext), A01.mAccountId);
            }
            if (!A03(this, i)) {
                Mi.A04(TAG, "Could not complete removeUser(%d)", Integer.valueOf(i));
                OumsTelemetry.A00(OumsTelemetry.EVENT_FRAMEWORK_FAILURE, OumsTelemetry.FrameworkCallType.REMOVE_TARGET, i);
            } else {
                OumsTelemetry.A00(OumsTelemetry.EVENT_FRAMEWORK_SUCCESS, OumsTelemetry.FrameworkCallType.REMOVE_TARGET, i);
            }
        } else {
            throw new IllegalArgumentException("removeUser can only be called by the system user.");
        }
    }

    /* JADX INFO: finally extract failed */
    public static OculusUserStore A01(OculusUserManagerImpl oculusUserManagerImpl) {
        if (((OculusUserStore) BZ.A02(0, 19, oculusUserManagerImpl._UL_mInjectionContext)).A01(0) == null) {
            OculusUserStore oculusUserStore = (OculusUserStore) BZ.A02(0, 19, oculusUserManagerImpl._UL_mInjectionContext);
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                UserInfo A02 = FrameworkWrapper.A02((UserManager) oculusUserManagerImpl.mContext.getSystemService(UserManager.class));
                Binder.restoreCallingIdentity(clearCallingIdentity);
                List<OculusUserInternal> singletonList = Collections.singletonList(new OculusUserInternal(new OculusUser(A02.id, A02.name, "", A02.creationTime, false), null, null));
                synchronized (oculusUserStore) {
                    oculusUserStore.mCache.clear();
                    for (OculusUserInternal oculusUserInternal : singletonList) {
                        oculusUserStore.mCache.put(Integer.valueOf(oculusUserInternal.mOculusUser.mUserId), oculusUserInternal);
                    }
                    OculusUserStore.A00(oculusUserStore);
                }
            } catch (Throwable th) {
                Binder.restoreCallingIdentity(clearCallingIdentity);
                throw th;
            }
        }
        return (OculusUserStore) BZ.A02(0, 19, oculusUserManagerImpl._UL_mInjectionContext);
    }

    public static boolean A03(OculusUserManagerImpl oculusUserManagerImpl, int i) {
        if (i != 0) {
            boolean A02 = oculusUserManagerImpl.A02(i);
            if (A02) {
                A01(oculusUserManagerImpl).A03(i);
            }
            return A02;
        }
        throw new IllegalArgumentException("system user cannot be removed.");
    }

    @Inject
    public OculusUserManagerImpl(SZ sz) {
        this._UL_mInjectionContext = new Om(4, sz);
        this.mSignatureVerifier = (SignatureVerifier) IX.A00(16, sz);
        this.mContext = (Context) IX.A00(9, sz);
    }

    @Nullable
    public static OculusUser A00(OculusUserManagerImpl oculusUserManagerImpl) {
        for (OculusUserInternal oculusUserInternal : A01(oculusUserManagerImpl).A02()) {
            OculusUser oculusUser = oculusUserInternal.mOculusUser;
            if (oculusUser.mUserId != 0 && oculusUserInternal.mAccountId == null) {
                return oculusUser;
            }
        }
        return null;
    }

    private boolean A02(int i) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (i == ActivityManager.getCurrentUser() && !FrameworkWrapper.A08((ActivityManager) this.mContext.getSystemService(ActivityManager.class), 0)) {
                return false;
            }
            boolean A0A = FrameworkWrapper.A0A((UserManager) this.mContext.getSystemService(UserManager.class), i);
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return A0A;
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
}
