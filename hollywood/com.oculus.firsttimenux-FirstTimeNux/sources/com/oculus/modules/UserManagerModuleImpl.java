package com.oculus.modules;

import android.content.Context;
import android.os.UserManager;
import android.util.Log;
import android.util.Pair;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.UserManagerModule;
import com.oculus.os.PreferencesManager;
import com.oculus.panellib.ShellIPC;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.userserver.api.OculusUserManager;
import com.oculus.userserver.api.sharing.SharingManager;
import com.oculus.userserver.api.user.OculusUser;
import java.text.DateFormat;
import java.util.concurrent.Callable;

public class UserManagerModuleImpl extends UserManagerModule {
    private static final String APP_SHARING_UPSELL_KEY = "app_sharing_upsell_key";
    private static final String LOCAL_ACCOUNT_MODE_ENABLED = "local_account_mode_enabled";
    private static final String MULTI_USER_ACCOUNTS_NUX_PREF_KEY = "multi_user_accounts_nux_pref_key";
    private static final String MULTI_USER_BETA_ENABLED_PREF_KEY = "multi_user_beta_enabled_key";
    private static final String MULTI_USER_LOCK_PATTERN_PREF_KEY = "multi_user_lock_pattern_prompt_seen_key";
    private static final String TAG = UserManagerModuleImpl.class.getSimpleName();
    private final Context mContext;
    PreferencesManager mPref = new PreferencesManager();

    public UserManagerModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void launchUnlockPatternImpl(String flow) {
        ShellIPC.launchDeepURI("systemux://unlockpattern", "&flow=" + flow);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void launchUnlockPatternWithReturnUriImpl(String flow, String returnUri) {
        StringBuilder deeplinkParams = new StringBuilder();
        if (!flow.isEmpty()) {
            deeplinkParams.append("&flow=" + flow);
        }
        if (!returnUri.isEmpty()) {
            deeplinkParams.append("&return_uri=" + returnUri);
        }
        ShellIPC.launchDeepURI("systemux://unlockpattern", deeplinkParams.toString());
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public boolean marshallJSConstantIsMultiUserEnabled() {
        boolean ret = OculusUserManager.isMultiUserEnabled();
        Log.d(TAG, "isMultiUserEnabled returning: " + ret);
        return ret;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void isSystemUserImpl(Resolver<Boolean> resolver) {
        resolver.resolve(Boolean.valueOf(((UserManager) this.mContext.getSystemService(ServiceContract.EXTRA_USER)).isSystemUser()));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r4 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0058, code lost:
        r3 = th;
     */
    @Override // com.oculus.modules.codegen.UserManagerModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getSelfImpl(com.oculus.modules.codegen.Resolver<com.oculus.modules.codegen.UserManagerModule.OculusUserType> r8) {
        /*
            r7 = this;
            com.oculus.userserver.api.OculusUserManager r0 = new com.oculus.userserver.api.OculusUserManager     // Catch:{ RemoteException -> 0x0020 }
            android.content.Context r3 = r7.mContext     // Catch:{ RemoteException -> 0x0020 }
            r0.<init>(r3)     // Catch:{ RemoteException -> 0x0020 }
            r4 = 0
            com.oculus.userserver.api.user.OculusUser r2 = r0.getSelf()     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            com.oculus.modules.codegen.UserManagerModule$OculusUserType r3 = r7.convertUser(r2)     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            r8.resolve(r3)     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            if (r0 == 0) goto L_0x001a
            if (r4 == 0) goto L_0x003d
            r0.close()     // Catch:{ Throwable -> 0x001b }
        L_0x001a:
            return
        L_0x001b:
            r3 = move-exception
            r4.addSuppressed(r3)
            goto L_0x001a
        L_0x0020:
            r1 = move-exception
            java.lang.String r3 = com.oculus.modules.UserManagerModuleImpl.TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "RemoteException when calling getSelf(): "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r4 = r4.toString()
            android.util.Log.w(r3, r4)
            r8.reject(r1)
            goto L_0x001a
        L_0x003d:
            r0.close()
            goto L_0x001a
        L_0x0041:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r4 = move-exception
            r6 = r4
            r4 = r3
            r3 = r6
        L_0x0047:
            if (r0 == 0) goto L_0x004e
            if (r4 == 0) goto L_0x0054
            r0.close()     // Catch:{ Throwable -> 0x004f }
        L_0x004e:
            throw r3
        L_0x004f:
            r5 = move-exception
            r4.addSuppressed(r5)
            goto L_0x004e
        L_0x0054:
            r0.close()
            goto L_0x004e
        L_0x0058:
            r3 = move-exception
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.UserManagerModuleImpl.getSelfImpl(com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    @Override // com.oculus.modules.codegen.UserManagerModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getUsersImpl(com.oculus.modules.codegen.Resolver<java.util.List<com.oculus.modules.codegen.UserManagerModule.OculusUserType>> r11) {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.UserManagerModuleImpl.getUsersImpl(com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: package-private */
    public UserManagerModule.OculusUserType convertUser(OculusUser user) {
        UserManagerModule.OculusUserType ret = new UserManagerModule.OculusUserType();
        ret.id = (double) user.getUserId();
        ret.name = user.getUserName();
        ret.pictureUri = user.getPictureUri();
        ret.dateCreated = formatDateCreated(user.getCreationTime());
        return ret;
    }

    private String formatDateCreated(long creationTime) {
        if (creationTime <= 0) {
            return "";
        }
        return DateFormat.getDateInstance(2).format(Long.valueOf(creationTime));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (r1 != null) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        if (r4 != null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004c, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        r4.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0052, code lost:
        r1.close();
     */
    @Override // com.oculus.modules.codegen.UserManagerModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void canAddMoreUsersImpl(com.oculus.modules.codegen.Resolver<java.lang.Boolean> r7) {
        /*
            r6 = this;
            r0 = 0
            com.oculus.userserver.api.OculusUserManager r1 = new com.oculus.userserver.api.OculusUserManager     // Catch:{ RemoteException -> 0x0021 }
            android.content.Context r3 = r6.mContext     // Catch:{ RemoteException -> 0x0021 }
            r1.<init>(r3)     // Catch:{ RemoteException -> 0x0021 }
            r4 = 0
            boolean r0 = r1.canAddMoreUsers()     // Catch:{ Throwable -> 0x0042 }
            if (r1 == 0) goto L_0x0014
            if (r4 == 0) goto L_0x003e
            r1.close()     // Catch:{ Throwable -> 0x001c }
        L_0x0014:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            r7.resolve(r3)
        L_0x001b:
            return
        L_0x001c:
            r3 = move-exception
            r4.addSuppressed(r3)
            goto L_0x0014
        L_0x0021:
            r2 = move-exception
            java.lang.String r3 = com.oculus.modules.UserManagerModuleImpl.TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "RemoteException when calling canAddMoreUsers(): "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            android.util.Log.w(r3, r4)
            r7.reject(r2)
            goto L_0x001b
        L_0x003e:
            r1.close()
            goto L_0x0014
        L_0x0042:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r3 = move-exception
            if (r1 == 0) goto L_0x004c
            if (r4 == 0) goto L_0x0052
            r1.close()     // Catch:{ Throwable -> 0x004d }
        L_0x004c:
            throw r3
        L_0x004d:
            r5 = move-exception
            r4.addSuppressed(r5)
            goto L_0x004c
        L_0x0052:
            r1.close()
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.UserManagerModuleImpl.canAddMoreUsersImpl(com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void addUserImpl() {
        Log.i(TAG, "Device owner initated adding new user. Calling createUserAndSwitch()");
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.UserManagerModuleImpl.AnonymousClass1 */

            /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
                r2 = th;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x0051, code lost:
                r2 = th;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:29:0x0052, code lost:
                r3 = null;
             */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() {
                /*
                    r6 = this;
                    r4 = 0
                    com.oculus.userserver.api.OculusUserManager r0 = new com.oculus.userserver.api.OculusUserManager     // Catch:{ RemoteException -> 0x001d }
                    com.oculus.modules.UserManagerModuleImpl r2 = com.oculus.modules.UserManagerModuleImpl.this     // Catch:{ RemoteException -> 0x001d }
                    android.content.Context r2 = com.oculus.modules.UserManagerModuleImpl.access$000(r2)     // Catch:{ RemoteException -> 0x001d }
                    r0.<init>(r2)     // Catch:{ RemoteException -> 0x001d }
                    r2 = 0
                    r0.createUserAndSwitch()     // Catch:{ Throwable -> 0x003d, all -> 0x0051 }
                    if (r0 == 0) goto L_0x0017
                    if (r4 == 0) goto L_0x0039
                    r0.close()     // Catch:{ Throwable -> 0x0018 }
                L_0x0017:
                    return r4
                L_0x0018:
                    r3 = move-exception
                    r2.addSuppressed(r3)
                    goto L_0x0017
                L_0x001d:
                    r1 = move-exception
                    java.lang.String r2 = com.oculus.modules.UserManagerModuleImpl.access$100()
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r5 = "RemoteException when calling createUserAndSwitch(): "
                    java.lang.StringBuilder r3 = r3.append(r5)
                    java.lang.StringBuilder r3 = r3.append(r1)
                    java.lang.String r3 = r3.toString()
                    android.util.Log.w(r2, r3)
                    goto L_0x0017
                L_0x0039:
                    r0.close()
                    goto L_0x0017
                L_0x003d:
                    r3 = move-exception
                    throw r3     // Catch:{ all -> 0x003f }
                L_0x003f:
                    r2 = move-exception
                L_0x0040:
                    if (r0 == 0) goto L_0x0047
                    if (r3 == 0) goto L_0x004d
                    r0.close()     // Catch:{ Throwable -> 0x0048 }
                L_0x0047:
                    throw r2
                L_0x0048:
                    r5 = move-exception
                    r3.addSuppressed(r5)
                    goto L_0x0047
                L_0x004d:
                    r0.close()
                    goto L_0x0047
                L_0x0051:
                    r2 = move-exception
                    r3 = r4
                    goto L_0x0040
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.UserManagerModuleImpl.AnonymousClass1.call():java.lang.Void");
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void removeUserImpl(double _userId, final Resolver<Void> resolver) {
        final int userId = (int) _userId;
        Log.i(TAG, "Device owner requested removal of user with id " + userId);
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.UserManagerModuleImpl.AnonymousClass2 */

            /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
                r3 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
                r3 = r2;
                r2 = r3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:29:0x0052, code lost:
                r2 = th;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:30:0x0053, code lost:
                r3 = null;
             */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() {
                /*
                    r7 = this;
                    r4 = 0
                    com.oculus.userserver.api.OculusUserManager r0 = new com.oculus.userserver.api.OculusUserManager     // Catch:{ RemoteException -> 0x0025, RuntimeException -> 0x0039 }
                    com.oculus.modules.UserManagerModuleImpl r2 = com.oculus.modules.UserManagerModuleImpl.this     // Catch:{ RemoteException -> 0x0025, RuntimeException -> 0x0039 }
                    android.content.Context r2 = com.oculus.modules.UserManagerModuleImpl.access$000(r2)     // Catch:{ RemoteException -> 0x0025, RuntimeException -> 0x0039 }
                    r0.<init>(r2)     // Catch:{ RemoteException -> 0x0025, RuntimeException -> 0x0039 }
                    r2 = 0
                    int r3 = r0     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
                    r0.removeUser(r3)     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
                    com.oculus.modules.codegen.Resolver r3 = r8     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
                    r5 = 0
                    r3.resolve(r5)     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
                    if (r0 == 0) goto L_0x001f
                    if (r4 == 0) goto L_0x0035
                    r0.close()     // Catch:{ Throwable -> 0x0020 }
                L_0x001f:
                    return r4
                L_0x0020:
                    r3 = move-exception
                    r2.addSuppressed(r3)
                    goto L_0x001f
                L_0x0025:
                    r1 = move-exception
                L_0x0026:
                    java.lang.String r2 = com.oculus.modules.UserManagerModuleImpl.access$100()
                    java.lang.String r3 = "Exception when calling removeUser(): "
                    android.util.Log.w(r2, r3, r1)
                    com.oculus.modules.codegen.Resolver r2 = r8
                    r2.reject(r1)
                    goto L_0x001f
                L_0x0035:
                    r0.close()
                    goto L_0x001f
                L_0x0039:
                    r1 = move-exception
                    goto L_0x0026
                L_0x003b:
                    r2 = move-exception
                    throw r2     // Catch:{ all -> 0x003d }
                L_0x003d:
                    r3 = move-exception
                    r6 = r3
                    r3 = r2
                    r2 = r6
                L_0x0041:
                    if (r0 == 0) goto L_0x0048
                    if (r3 == 0) goto L_0x004e
                    r0.close()     // Catch:{ Throwable -> 0x0049 }
                L_0x0048:
                    throw r2
                L_0x0049:
                    r5 = move-exception
                    r3.addSuppressed(r5)
                    goto L_0x0048
                L_0x004e:
                    r0.close()
                    goto L_0x0048
                L_0x0052:
                    r2 = move-exception
                    r3 = r4
                    goto L_0x0041
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.UserManagerModuleImpl.AnonymousClass2.call():java.lang.Void");
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void removeSelfImpl(final Resolver<Void> resolver) {
        Log.i(TAG, "Secondary user requested removal of their account");
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.UserManagerModuleImpl.AnonymousClass3 */

            /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
                r3 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
                r3 = r2;
                r2 = r3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:29:0x0050, code lost:
                r2 = th;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:30:0x0051, code lost:
                r3 = null;
             */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() {
                /*
                    r7 = this;
                    r4 = 0
                    com.oculus.userserver.api.OculusUserManager r0 = new com.oculus.userserver.api.OculusUserManager     // Catch:{ RemoteException -> 0x0023, RuntimeException -> 0x0037 }
                    com.oculus.modules.UserManagerModuleImpl r2 = com.oculus.modules.UserManagerModuleImpl.this     // Catch:{ RemoteException -> 0x0023, RuntimeException -> 0x0037 }
                    android.content.Context r2 = com.oculus.modules.UserManagerModuleImpl.access$000(r2)     // Catch:{ RemoteException -> 0x0023, RuntimeException -> 0x0037 }
                    r0.<init>(r2)     // Catch:{ RemoteException -> 0x0023, RuntimeException -> 0x0037 }
                    r2 = 0
                    r0.removeSelf()     // Catch:{ Throwable -> 0x0039, all -> 0x0050 }
                    com.oculus.modules.codegen.Resolver r3 = r3     // Catch:{ Throwable -> 0x0039, all -> 0x0050 }
                    r5 = 0
                    r3.resolve(r5)     // Catch:{ Throwable -> 0x0039, all -> 0x0050 }
                    if (r0 == 0) goto L_0x001d
                    if (r4 == 0) goto L_0x0033
                    r0.close()     // Catch:{ Throwable -> 0x001e }
                L_0x001d:
                    return r4
                L_0x001e:
                    r3 = move-exception
                    r2.addSuppressed(r3)
                    goto L_0x001d
                L_0x0023:
                    r1 = move-exception
                L_0x0024:
                    java.lang.String r2 = com.oculus.modules.UserManagerModuleImpl.access$100()
                    java.lang.String r3 = "Exception when calling removeSelf(): "
                    android.util.Log.w(r2, r3, r1)
                    com.oculus.modules.codegen.Resolver r2 = r3
                    r2.reject(r1)
                    goto L_0x001d
                L_0x0033:
                    r0.close()
                    goto L_0x001d
                L_0x0037:
                    r1 = move-exception
                    goto L_0x0024
                L_0x0039:
                    r2 = move-exception
                    throw r2     // Catch:{ all -> 0x003b }
                L_0x003b:
                    r3 = move-exception
                    r6 = r3
                    r3 = r2
                    r2 = r6
                L_0x003f:
                    if (r0 == 0) goto L_0x0046
                    if (r3 == 0) goto L_0x004c
                    r0.close()     // Catch:{ Throwable -> 0x0047 }
                L_0x0046:
                    throw r2
                L_0x0047:
                    r5 = move-exception
                    r3.addSuppressed(r5)
                    goto L_0x0046
                L_0x004c:
                    r0.close()
                    goto L_0x0046
                L_0x0050:
                    r2 = move-exception
                    r3 = r4
                    goto L_0x003f
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.UserManagerModuleImpl.AnonymousClass3.call():java.lang.Void");
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void hasSeenPatternPromptImpl(Resolver<Boolean> resolver) {
        Pair<Boolean, Boolean> lockPatternPref = this.mPref.getBoolean(MULTI_USER_LOCK_PATTERN_PREF_KEY);
        resolver.resolve(Boolean.valueOf(((Boolean) lockPatternPref.first).booleanValue() && ((Boolean) lockPatternPref.second).booleanValue()));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void hasSeenAppSharingUpsellImpl(Resolver<Boolean> resolver) {
        if (((Boolean) this.mPref.getBoolean(APP_SHARING_UPSELL_KEY).first).booleanValue()) {
            resolver.resolve(Boolean.valueOf(((Boolean) this.mPref.getBoolean(APP_SHARING_UPSELL_KEY).second).booleanValue()));
        } else {
            resolver.reject(new Exception("Unable to successfully retrieve APP_SHARING_UPSELL_KEY"));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void setHasSeenAppSharingUpsellImpl(boolean hasSeen) {
        this.mPref.set(APP_SHARING_UPSELL_KEY, hasSeen);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void setHasSeenPatternPromptImpl(boolean seen) {
        this.mPref.set(MULTI_USER_LOCK_PATTERN_PREF_KEY, seen);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void isLibrarySharingEnabledImpl(Resolver<Boolean> resolver) {
        resolver.resolve(Boolean.valueOf(new SharingManager(this.mContext).getLibrarySharingEnabled()));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void setIsLibrarySharingEnabledImpl(final boolean isEnabled, final Resolver<UserManagerModule.LibrarySharingResultCode> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.UserManagerModuleImpl.AnonymousClass4 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    new SharingManager(UserManagerModuleImpl.this.mContext).setLibrarySharingEnabled(isEnabled);
                    resolver.resolve(UserManagerModule.LibrarySharingResultCode.RESULT_OK);
                    return null;
                } catch (SharingManager.SharingManagerException e) {
                    Log.w(UserManagerModuleImpl.TAG, "SharingManagerException when calling setLibrarySharingEnabled(): " + e);
                    resolver.resolve(UserManagerModuleImpl.this.getLibrarySharingResultCodeFromString(e.getCode().name()));
                    return null;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private UserManagerModule.LibrarySharingResultCode getLibrarySharingResultCodeFromString(String resultCode) {
        try {
            return UserManagerModule.LibrarySharingResultCode.valueOf(resultCode);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Illegal library sharing result code " + resultCode);
            return UserManagerModule.LibrarySharingResultCode.ERROR_UNKNOWN;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void getHasSeenMuAccountsNuxImpl(Resolver<Boolean> resolver) {
        if (((Boolean) this.mPref.getBoolean(MULTI_USER_ACCOUNTS_NUX_PREF_KEY).first).booleanValue()) {
            resolver.resolve(Boolean.valueOf(((Boolean) this.mPref.getBoolean(MULTI_USER_ACCOUNTS_NUX_PREF_KEY).second).booleanValue()));
        } else {
            resolver.reject(new Exception("Unable to successfully retrieve MULTI_USER_ACCOUNTS_NUX_PREF_KEY"));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void setHasSeenMuAccountsNuxImpl(boolean hasSeenMuNux) {
        this.mPref.set(MULTI_USER_ACCOUNTS_NUX_PREF_KEY, hasSeenMuNux);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void getIsMultiUserBetaEnabledImpl(Resolver<Boolean> resolver) {
        if (((Boolean) this.mPref.getBoolean(MULTI_USER_BETA_ENABLED_PREF_KEY).first).booleanValue()) {
            resolver.resolve(Boolean.valueOf(((Boolean) this.mPref.getBoolean(MULTI_USER_BETA_ENABLED_PREF_KEY).second).booleanValue()));
        } else {
            resolver.reject(new Exception("Unable to successfully retrieve MULTI_USER_BETA_ENABLED_PREF_KEY"));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void setIsMultiUserBetaEnabledImpl(boolean isEnabled) {
        this.mPref.set(MULTI_USER_BETA_ENABLED_PREF_KEY, isEnabled);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void isLocalAccountModeEnabledImpl(Resolver<Boolean> resolver) {
        if (((Boolean) this.mPref.getBoolean(LOCAL_ACCOUNT_MODE_ENABLED).first).booleanValue()) {
            resolver.resolve(Boolean.valueOf(((Boolean) this.mPref.getBoolean(LOCAL_ACCOUNT_MODE_ENABLED).second).booleanValue()));
        } else {
            resolver.reject(new Exception("Unable to successfully retrieve LOCAL_ACCOUNT_MODE_ENABLED"));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserManagerModule
    public void setIsLocalAccountModeEnabledImpl(boolean isEnabled) {
        this.mPref.set(LOCAL_ACCOUNT_MODE_ENABLED, isEnabled);
    }
}
