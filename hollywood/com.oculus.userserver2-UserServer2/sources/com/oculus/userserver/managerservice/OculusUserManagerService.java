package com.oculus.userserver.managerservice;

import X.AbstractC0054Ej;
import X.AnonymousClass06;
import X.BZ;
import X.C0052Ec;
import X.FR;
import X.FW;
import X.FY;
import X.FZ;
import X.Hw;
import X.IX;
import X.Mi;
import X.XO;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserManager;
import android.text.TextUtils;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Objects;
import com.google.common.collect.RegularImmutableMap;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.http.core.base.ApiError;
import com.oculus.userserver.api.AccountAlreadyOnDeviceException;
import com.oculus.userserver.api.AccountConflictException;
import com.oculus.userserver.api.ExceptionUtils;
import com.oculus.userserver.api.user.OculusUser;
import com.oculus.userserver.api.user.OculusUserBundler;
import com.oculus.userserver.api.user.SparseOculusUser;
import com.oculus.userserver.managerservice.IOculusUserManager;
import com.oculus.userserver.managerservice.OumsTelemetry;
import com.oculus.userserver.managerservice.PictureApplier;
import com.oculus.userserver.managerservice.callerverifiers.SignatureVerifier;
import com.oculus.userserver.net.AddToDeviceResponse;
import com.oculus.userserver.net.OculusUserMethods;
import com.oculus.userserver.os.FrameworkWrapper;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import retrofit.RetrofitError;

public class OculusUserManagerService extends Service {
    @Inject
    @Eager
    public OculusUserManagerImpl mOculusUserManager;
    public final IOculusUserManager mService = new IOculusUserManager.Stub() {
        /* class com.oculus.userserver.managerservice.OculusUserManagerService.AnonymousClass1 */

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        @Deprecated
        public final int A23() {
            return 0;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        @Deprecated
        public final void A2J() {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        @Deprecated
        public final void A3h() {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final boolean A17() {
            OculusUserManagerImpl oculusUserManagerImpl = OculusUserManagerService.this.mOculusUserManager;
            oculusUserManagerImpl.mSignatureVerifier.A01(SignatureVerifier.APP_SYSTEM_UTILITIES, SignatureVerifier.APP_HOME, SignatureVerifier.APP_SYSTEMUX);
            if (OculusUserManagerImpl.A00(oculusUserManagerImpl) != null) {
                return true;
            }
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return FrameworkWrapper.A09((UserManager) oculusUserManagerImpl.mContext.getSystemService(UserManager.class));
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A1I() {
            OculusUserManagerService.this.mOculusUserManager.A04();
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final Bundle A1y() {
            OculusUserManagerImpl oculusUserManagerImpl = OculusUserManagerService.this.mOculusUserManager;
            oculusUserManagerImpl.mSignatureVerifier.A01(SignatureVerifier.APP_SYSTEM_UTILITIES, SignatureVerifier.APP_HOME, SignatureVerifier.APP_STORE, SignatureVerifier.APP_SYSTEMUX);
            OculusUserInternal A01 = OculusUserManagerImpl.A01(oculusUserManagerImpl).A01(FrameworkWrapper.A00(Binder.getCallingUserHandle()));
            if (A01 != null) {
                return OculusUserBundler.A00(A01.mOculusUser);
            }
            throw new IllegalStateException("getSelf() was not called by a device user.");
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final List<Bundle> A24() {
            OculusUserManagerImpl oculusUserManagerImpl = OculusUserManagerService.this.mOculusUserManager;
            oculusUserManagerImpl.mSignatureVerifier.A01(SignatureVerifier.APP_SYSTEM_UTILITIES, SignatureVerifier.APP_HOME, SignatureVerifier.APP_OCMS, SignatureVerifier.APP_SYSTEMUX);
            List<OculusUserInternal> A02 = OculusUserManagerImpl.A01(oculusUserManagerImpl).A02();
            ArrayList arrayList = new ArrayList(A02.size());
            for (OculusUserInternal oculusUserInternal : A02) {
                OculusUser oculusUser = oculusUserInternal.mOculusUser;
                if (oculusUser.mUserId == 0 || oculusUserInternal.mAccountId != null) {
                    arrayList.add(OculusUserBundler.A00(oculusUser));
                }
            }
            return arrayList;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3C() {
            ((UserSyncScheduler) BZ.A02(3, 0, OculusUserManagerService.this.mOculusUserManager._UL_mInjectionContext)).A00();
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3E(String str, String str2) {
            String str3;
            OculusUserManagerImpl oculusUserManagerImpl = OculusUserManagerService.this.mOculusUserManager;
            synchronized (oculusUserManagerImpl) {
                oculusUserManagerImpl.mSignatureVerifier.A01(SignatureVerifier.APP_HORIZON);
                int A00 = FrameworkWrapper.A00(Binder.getCallingUserHandle());
                OculusUserInternal A01 = OculusUserManagerImpl.A01(oculusUserManagerImpl).A01(A00);
                if (A01 == null || TextUtils.isEmpty(A01.mAccountId)) {
                    OculusUserStore A012 = OculusUserManagerImpl.A01(oculusUserManagerImpl);
                    synchronized (A012) {
                        for (OculusUserInternal oculusUserInternal : A012.mCache.values()) {
                            if (Objects.equal(oculusUserInternal.mAccountId, str)) {
                                ExceptionUtils.A00(AccountAlreadyOnDeviceException.class, "Account already on device");
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    }
                } else if (!Objects.equal(str, A01.mAccountId)) {
                    ExceptionUtils.A00(AccountConflictException.class, "User already logged in with different account");
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                if (!Hw.A01("debug.oculus.disable_device_identity_features").equals(DiskLruCache.VERSION_1)) {
                    try {
                        try {
                            AddToDeviceResponse addToDevice = ((OculusUserMethods) BZ.A02(0, 61, ((OculusUserBackend) BZ.A02(1, 8, oculusUserManagerImpl._UL_mInjectionContext))._UL_mInjectionContext)).mMethods.addToDevice(AnonymousClass06.A03("Bearer ", str2), OculusUserMethods.Queries.ADD_TO_DEVICE, GraphQLParamsHelper.A00(RegularImmutableMap.A03), "");
                            if (!TextUtils.isEmpty(addToDevice.mSyncId)) {
                                str3 = addToDevice.mSyncId;
                            } else {
                                throw new IllegalStateException(AnonymousClass06.A03("Backend returned null or empty sync ID: ", addToDevice.mSyncId));
                            }
                        } catch (RetrofitError e) {
                            throw new ApiError(e);
                        }
                    } catch (ApiError e2) {
                        Mi.A06(OculusUserBackend.TAG, e2, "Error adding user to device");
                        throw new IllegalStateException(e2);
                    }
                } else {
                    str3 = "";
                }
                OculusUserManagerImpl.A01(oculusUserManagerImpl).A05(A00, str, str3);
            }
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3I() {
            OculusUserInternal A01;
            OculusUserManagerImpl oculusUserManagerImpl = OculusUserManagerService.this.mOculusUserManager;
            synchronized (oculusUserManagerImpl) {
                oculusUserManagerImpl.mSignatureVerifier.A01(SignatureVerifier.APP_SYSTEM_UTILITIES, SignatureVerifier.APP_HOME, SignatureVerifier.APP_SYSTEMUX, SignatureVerifier.APP_FIRST_TIME_NUX);
                int A00 = FrameworkWrapper.A00(Binder.getCallingUserHandle());
                if (!Hw.A01("debug.oculus.disable_device_identity_features").equals(DiskLruCache.VERSION_1) && (A01 = OculusUserManagerImpl.A01(oculusUserManagerImpl).A01(A00)) != null && !TextUtils.isEmpty(A01.mAccountId)) {
                    OculusUserBackend.A01((OculusUserBackend) BZ.A02(1, 8, oculusUserManagerImpl._UL_mInjectionContext), null);
                }
                if (!OculusUserManagerImpl.A03(oculusUserManagerImpl, A00)) {
                    Mi.A04(OculusUserManagerImpl.TAG, "Could not complete removeSelf() for user %d", Integer.valueOf(A00));
                    OumsTelemetry.A00(OumsTelemetry.EVENT_FRAMEWORK_FAILURE, OumsTelemetry.FrameworkCallType.REMOVE_SELF, A00);
                } else {
                    OumsTelemetry.A00(OumsTelemetry.EVENT_FRAMEWORK_SUCCESS, OumsTelemetry.FrameworkCallType.REMOVE_SELF, A00);
                }
            }
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3J() {
            OculusUserManagerService.this.mOculusUserManager.A05();
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3K(int i) {
            OculusUserManagerService.this.mOculusUserManager.A07(i);
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3r(Bundle bundle) {
            String str;
            String str2;
            OculusUserManagerImpl oculusUserManagerImpl = OculusUserManagerService.this.mOculusUserManager;
            synchronized (oculusUserManagerImpl) {
                boolean z = false;
                oculusUserManagerImpl.mSignatureVerifier.A01(SignatureVerifier.APP_HORIZON);
                SparseOculusUser.Builder builder = new SparseOculusUser.Builder();
                if (bundle.containsKey("user_id")) {
                    builder.mUserId = Integer.valueOf(bundle.getInt("user_id"));
                }
                if (bundle.getString("user_name") != null) {
                    builder.mUserName = bundle.getString("user_name");
                }
                if (bundle.getString(OculusUserBundler.KEY_PICTURE_URI) != null) {
                    builder.mPictureUri = bundle.getString(OculusUserBundler.KEY_PICTURE_URI);
                }
                SparseOculusUser sparseOculusUser = new SparseOculusUser(builder);
                int A00 = FrameworkWrapper.A00(Binder.getCallingUserHandle());
                Integer num = sparseOculusUser.mUserId;
                if (num != null) {
                    A00 = num.intValue();
                }
                OculusUserInternal A01 = OculusUserManagerImpl.A01(oculusUserManagerImpl).A01(A00);
                if (A01 != null) {
                    z = A01.mOculusUser.mIsSuspended;
                }
                OculusUserManagerImpl.A01(oculusUserManagerImpl).A04(A00, sparseOculusUser, z);
                OculusUserInternal A012 = OculusUserManagerImpl.A01(oculusUserManagerImpl).A01(A00);
                if (A012 != null) {
                    if (A01 != null) {
                        str = A01.mOculusUser.mUserName;
                    } else {
                        str = null;
                    }
                    if (!Objects.equal(str, A012.mOculusUser.mUserName)) {
                        long clearCallingIdentity = Binder.clearCallingIdentity();
                        FrameworkWrapper.A06((UserManager) oculusUserManagerImpl.mContext.getSystemService(UserManager.class), A00, A012.mOculusUser.mUserName);
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                    }
                    if (A01 != null) {
                        str2 = A01.mOculusUser.mPictureUri;
                    } else {
                        str2 = null;
                    }
                    if (!Objects.equal(str2, A012.mOculusUser.mPictureUri)) {
                        PictureApplier.AnonymousClass1 r3 = new Callable<Void>(A00) {
                            /* class com.oculus.userserver.managerservice.PictureApplier.AnonymousClass1 */
                            public final /* synthetic */ int val$targetUserId;

                            {
                                this.val$targetUserId = r2;
                            }

                            /* Return type fixed from 'java.lang.Object' to match base method */
                            @Override // java.util.concurrent.Callable
                            public final Void call() throws Exception {
                                PictureApplier pictureApplier = PictureApplier.this;
                                int i = this.val$targetUserId;
                                OculusUserInternal A01 = OculusUserManagerImpl.A01((OculusUserManagerImpl) BZ.A03(44, pictureApplier._UL_mInjectionContext)).A01(i);
                                if (A01 == null) {
                                    Mi.A05(PictureApplier.TAG, "User no longer exists: %d, bailing out...", Integer.valueOf(i));
                                    return null;
                                }
                                String str = A01.mOculusUser.mPictureUri;
                                if (str == null) {
                                    return null;
                                }
                                try {
                                    if (str.isEmpty()) {
                                        return null;
                                    }
                                    XO xo = new XO();
                                    xo.A01(str);
                                    InputStream A28 = new C0052Ec((AbstractC0054Ej) BZ.A03(5, pictureApplier._UL_mInjectionContext), xo.A00(), false).A00().A0B.A01().A28();
                                    try {
                                        BZ.A03(73, pictureApplier._UL_mInjectionContext);
                                        Bitmap decodeStream = BitmapFactory.decodeStream(A28);
                                        if (A28 != null) {
                                            A28.close();
                                        }
                                        if (decodeStream == null) {
                                            return null;
                                        }
                                        FrameworkWrapper.A05((UserManager) ((Context) BZ.A03(9, ((UserManagerAccessor) BZ.A03(81, pictureApplier._UL_mInjectionContext))._UL_mInjectionContext)).getSystemService(UserManager.class), i, decodeStream);
                                        decodeStream.recycle();
                                        return null;
                                    } catch (Throwable unused) {
                                    }
                                } catch (IOException e) {
                                    Mi.A02(PictureApplier.TAG, "Error downloading picture", e);
                                    return null;
                                }
                                throw th;
                            }
                        };
                        ExecutorService executorService = FY.A0A;
                        FZ fz = new FZ();
                        try {
                            executorService.execute(new FW(fz, r3));
                        } catch (Exception e) {
                            fz.A00(new FR(e));
                        }
                    }
                }
            }
        }
    };

    public final IBinder onBind(Intent intent) {
        return (IBinder) this.mService;
    }

    public final void onCreate() {
        super.onCreate();
        this.mOculusUserManager = (OculusUserManagerImpl) IX.A00(44, BZ.get(this));
    }
}
