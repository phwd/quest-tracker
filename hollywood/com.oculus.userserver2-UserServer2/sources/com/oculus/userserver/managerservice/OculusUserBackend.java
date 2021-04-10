package com.oculus.userserver.managerservice;

import X.AnonymousClass06;
import X.BZ;
import X.FZ;
import X.Mi;
import X.Om;
import X.QN;
import X.SZ;
import X.TW;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.Binder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.authapi.inject.AuthServiceClientAuxiliaryProvider;
import com.oculus.authapi.inject.CallerInfoProviderImpl;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.http.core.base.ApiError;
import com.oculus.multiuser.BindAsUserStrategy;
import com.oculus.userserver.managerservice.DsatFetcherForUser;
import com.oculus.userserver.net.FetchDeviceUsersResponse;
import com.oculus.userserver.net.OculusUserMethods;
import com.oculus.userserver.os.FrameworkWrapper;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import retrofit.RetrofitError;

@Dependencies({"_UL__ULSEP_com_oculus_userserver_net_OculusUserMethods_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_managerservice_DsatFetcherForUser_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID"})
@ThreadSafe
public class OculusUserBackend {
    public static final String TAG = "OculusUserBackend";
    public Om _UL_mInjectionContext;

    /* JADX INFO: finally extract failed */
    public static void A01(@Nullable OculusUserBackend oculusUserBackend, String str) {
        ApiError apiError;
        String str2;
        try {
            BindAsUserStrategy bindAsUserStrategy = new BindAsUserStrategy(Binder.getCallingUserHandle());
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                str2 = new AuthServiceClient((Context) BZ.A02(0, 1, ((AuthServiceClientAuxiliaryProvider) BZ.A02(3, 12, oculusUserBackend._UL_mInjectionContext))._UL_mInjectionContext), bindAsUserStrategy).A00().mAccessToken;
            } catch (Throwable th) {
                Binder.restoreCallingIdentity(clearCallingIdentity);
                throw th;
            }
            try {
                Binder.restoreCallingIdentity(clearCallingIdentity);
                if (TextUtils.isEmpty(str2)) {
                    throw new IllegalStateException("User not logged in");
                } else if (TextUtils.isEmpty(str)) {
                    try {
                        OculusUserMethods.Methods methods = ((OculusUserMethods) BZ.A02(0, 61, oculusUserBackend._UL_mInjectionContext)).mMethods;
                        String A03 = AnonymousClass06.A03("Bearer ", str2);
                        String A00 = BuildSerialUtil.A00();
                        QN.A01("serial", A00);
                        methods.removeFromDevice(A03, OculusUserMethods.Queries.REMOVE_FROM_DEVICE, GraphQLParamsHelper.A00(RegularImmutableMap.A00(1, new Object[]{"serial", A00})), "");
                    } catch (RetrofitError e) {
                        throw new ApiError(e);
                    }
                } else {
                    try {
                        OculusUserMethods.Methods methods2 = ((OculusUserMethods) BZ.A02(0, 61, oculusUserBackend._UL_mInjectionContext)).mMethods;
                        String A032 = AnonymousClass06.A03("Bearer ", str2);
                        String A002 = BuildSerialUtil.A00();
                        QN.A01("allowed_user_id", str);
                        QN.A01("serial", A002);
                        methods2.removeFromDevice(A032, OculusUserMethods.Queries.REMOVE_USER_FROM_DEVICE, GraphQLParamsHelper.A00(RegularImmutableMap.A00(2, new Object[]{"allowed_user_id", str, "serial", A002})), "");
                    } catch (RetrofitError e2) {
                        throw new ApiError(e2);
                    }
                }
            } catch (RemoteException | InterruptedException e3) {
                apiError = e3;
                Mi.A06(TAG, apiError, "Error removing user from device");
                throw new IllegalStateException(apiError);
            }
        } catch (ApiError e4) {
            ApiError.FBApiErrorResponse fBApiErrorResponse = e4.fbApiErrorResponse;
            apiError = e4;
            if (fBApiErrorResponse != null) {
                ApiError.FBApiErrorResponse.Error error = fBApiErrorResponse.error;
                apiError = e4;
                if (error != null) {
                    apiError = e4;
                    if (error.error_subcode == 2807008) {
                        Mi.A01(TAG, "User already removed");
                        return;
                    }
                }
            }
            Mi.A06(TAG, apiError, "Error removing user from device");
            throw new IllegalStateException(apiError);
        }
    }

    @Inject
    public OculusUserBackend(SZ sz) {
        this._UL_mInjectionContext = new Om(4, sz);
    }

    @Nullable
    public static String A00(OculusUserBackend oculusUserBackend, UserHandle userHandle) {
        TResult tresult;
        Throwable th;
        boolean z;
        TResult tresult2;
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            DsatFetcherForUser dsatFetcherForUser = (DsatFetcherForUser) BZ.A02(2, 42, oculusUserBackend._UL_mInjectionContext);
            try {
                FZ fz = new FZ();
                Om om = dsatFetcherForUser._UL_mInjectionContext;
                new DsatFetcherForUser.OVRAuthForUser((Context) BZ.A02(1, 9, om), (CallerInfoProviderImpl) BZ.A02(0, 28, om), userHandle).A00(
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0032: INVOKE  
                      (wrap: com.oculus.userserver.managerservice.DsatFetcherForUser$OVRAuthForUser : 0x002a: CONSTRUCTOR  (r1v5 com.oculus.userserver.managerservice.DsatFetcherForUser$OVRAuthForUser) = 
                      (wrap: android.content.Context : 0x001d: CHECK_CAST (r2v3 android.content.Context) = (android.content.Context) (wrap: java.lang.Object : 0x0019: INVOKE  (r2v2 java.lang.Object) = (1 int), (9 int), (r3v0 'om' X.Om) type: STATIC call: X.BZ.A02(int, int, X.Om):java.lang.Object))
                      (wrap: com.oculus.authapi.inject.CallerInfoProviderImpl : 0x0026: CHECK_CAST (r0v9 com.oculus.authapi.inject.CallerInfoProviderImpl) = (com.oculus.authapi.inject.CallerInfoProviderImpl) (wrap: java.lang.Object : 0x0022: INVOKE  (r0v8 java.lang.Object) = (0 int), (28 int), (r3v0 'om' X.Om) type: STATIC call: X.BZ.A02(int, int, X.Om):java.lang.Object))
                      (r9v0 'userHandle' android.os.UserHandle)
                     call: com.oculus.userserver.managerservice.DsatFetcherForUser.OVRAuthForUser.<init>(android.content.Context, com.oculus.authapi.inject.CallerInfoProviderImpl, android.os.UserHandle):void type: CONSTRUCTOR)
                      (wrap: com.oculus.userserver.managerservice.DsatFetcherForUser$1 : 0x002f: CONSTRUCTOR  (r0v10 com.oculus.userserver.managerservice.DsatFetcherForUser$1) = (r5v1 'dsatFetcherForUser' com.oculus.userserver.managerservice.DsatFetcherForUser), (r4v0 'fz' X.FZ) call: com.oculus.userserver.managerservice.DsatFetcherForUser.1.<init>(com.oculus.userserver.managerservice.DsatFetcherForUser, X.FZ):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.oculus.authapi.OVRAuth.A00(com.oculus.authapi.AuthResultCallback):void in method: com.oculus.userserver.managerservice.OculusUserBackend.A00(com.oculus.userserver.managerservice.OculusUserBackend, android.os.UserHandle):java.lang.String, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:306)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:69)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:306)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:69)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002f: CONSTRUCTOR  (r0v10 com.oculus.userserver.managerservice.DsatFetcherForUser$1) = (r5v1 'dsatFetcherForUser' com.oculus.userserver.managerservice.DsatFetcherForUser), (r4v0 'fz' X.FZ) call: com.oculus.userserver.managerservice.DsatFetcherForUser.1.<init>(com.oculus.userserver.managerservice.DsatFetcherForUser, X.FZ):void type: CONSTRUCTOR in method: com.oculus.userserver.managerservice.OculusUserBackend.A00(com.oculus.userserver.managerservice.OculusUserBackend, android.os.UserHandle):java.lang.String, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                    	... 24 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.userserver.managerservice.DsatFetcherForUser, state: GENERATED_AND_UNLOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                    	... 30 more
                    */
                /*
                // Method dump skipped, instructions count: 124
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.userserver.managerservice.OculusUserBackend.A00(com.oculus.userserver.managerservice.OculusUserBackend, android.os.UserHandle):java.lang.String");
            }

            /* JADX INFO: finally extract failed */
            public final ImmutableMap<String, String> A02() {
                String str;
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    ImmutableList.Builder A02 = ImmutableList.A02();
                    for (UserInfo userInfo : FrameworkWrapper.A03((UserManager) ((Context) BZ.A02(1, 1, this._UL_mInjectionContext)).getSystemService(UserManager.class))) {
                        UserHandle of = UserHandle.of(userInfo.id);
                        if (((UserManager) ((Context) BZ.A02(1, 1, this._UL_mInjectionContext)).getSystemService(UserManager.class)).isUserUnlocked(of)) {
                            A02.add((Object) of);
                        }
                    }
                    ImmutableList build = A02.build();
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    TW A0H = build.iterator();
                    while (true) {
                        if (!A0H.hasNext()) {
                            str = null;
                            break;
                        }
                        str = A00(this, (UserHandle) A0H.next());
                        if (!TextUtils.isEmpty(str)) {
                            break;
                        }
                    }
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            try {
                                FetchDeviceUsersResponse fetchDeviceUsers = ((OculusUserMethods) BZ.A02(0, 61, this._UL_mInjectionContext)).mMethods.fetchDeviceUsers(AnonymousClass06.A03("Bearer ", str), OculusUserMethods.Queries.USERS_ON_DEVICE);
                                List<FetchDeviceUsersResponse.UserOnDevice> list = fetchDeviceUsers.mUsersOnDevice;
                                if (list == null || list.isEmpty()) {
                                    throw new IllegalStateException("Backend returned no users on device");
                                }
                                ImmutableMap.Builder builder = new ImmutableMap.Builder(4);
                                for (FetchDeviceUsersResponse.UserOnDevice userOnDevice : fetchDeviceUsers.mUsersOnDevice) {
                                    if (!TextUtils.isEmpty(userOnDevice.mId)) {
                                        builder.put(userOnDevice.mId, userOnDevice.mStatus);
                                    } else {
                                        throw new IllegalStateException(AnonymousClass06.A03("Backend returned null or empty ID: ", userOnDevice.mId));
                                    }
                                }
                                return builder.build();
                            } catch (RetrofitError e) {
                                throw new ApiError(e);
                            }
                        } catch (ApiError e2) {
                            Mi.A06(TAG, e2, "Error fetching device users");
                            throw new IllegalStateException(e2);
                        }
                    } else {
                        throw new IllegalStateException("Error fetching DSAT");
                    }
                } catch (Throwable th) {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    throw th;
                }
            }
        }
