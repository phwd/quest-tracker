package com.oculus.modules;

import X.AbstractC057411o;
import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.AnonymousClass13N;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.socialtablet.fbauth.FBAccountManager;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class SocialModule extends RCTBaseJavaModule {
    public static final String ACTION_UPDATE_CURRENT_FACEBOOK_ACCOUNT = "com.oculus.fbconnect.UPDATE_CURRENT_FACEBOOK_ACCOUNT";
    public static final AnonymousClass13N GSON;
    public static final String HORIZON_ACCESS_TOKEN_KEY = "accesstoken";
    public static final Uri HORIZON_CONTENT_URI = Uri.parse(FBAccountManager.FbConnectAccountConstants.URI_STRING);
    public static final String HORIZON_PROFILE_PICTURE_KEY = "profile_picture";
    public static final String HORIZON_UID_KEY = "userid";
    public static final String HORIZON_USER_NAME_KEY = "user_name";
    public static final String MESSENGER_VR_FB_ID = "581956559359077";
    public static final String MESSENGER_VR_OC_ID = "3534234083363713";
    public static final String MODULE_NAME = new String("Social");
    public static final String SOCIAL_BUNDLE_FB_ID = "461177937822158";
    public static final String SOCIAL_BUNDLE_OC_ID = "3519383184769243";
    public static final String TAG = "SocialModule";
    public static ContentObserver mFBConnectContentObserver;
    public static ContentResolver mFBConnectContentResolver;
    public static SocialModule mInstance;
    public static final ReentrantLock mInstanceLock = new ReentrantLock(true);
    public Context mContext = null;
    public int onFacebookConnectSuccessCallbackID;

    public static class AppScopedAccessToken {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String AUTHORITY = "com.oculus.horizon.fbconnect";
        public static final String FB_APP_ID = "fb_app_id";
        public static final String OVERRIDE_OC_APP_ID = "override_oc_app_id";
        public static final Uri URI = Uri.parse(FBAuthManager.URI_STRING);
    }

    public static class FacebookAccount {
        public String accessToken;
        public String name;
        public String profilePicture;
        public String uid;

        public FacebookAccount(String str, String str2, String str3, String str4) {
            this.uid = str;
            this.name = str2;
            this.accessToken = str3;
            this.profilePicture = str4;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0031, code lost:
        r0 = move-exception;
     */
    static {
        /*
            java.lang.String r1 = "Social"
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1)
            com.oculus.modules.SocialModule.MODULE_NAME = r0
            java.lang.String r2 = "SocialModule"
            com.oculus.modules.SocialModule.TAG = r2
            java.lang.String r0 = "content://com.oculus.horizon.fbconnect/account"
            android.net.Uri r0 = android.net.Uri.parse(r0)
            com.oculus.modules.SocialModule.HORIZON_CONTENT_URI = r0
            java.lang.String r0 = "GSON"
            com.oculus.panellib.SystraceBlock r1 = new com.oculus.panellib.SystraceBlock
            r1.<init>(r2, r0)
            X.13N r0 = new X.13N     // Catch:{ all -> 0x002f }
            r0.<init>()     // Catch:{ all -> 0x002f }
            com.oculus.modules.SocialModule.GSON = r0     // Catch:{ all -> 0x002f }
            r1.close()
            r1 = 1
            java.util.concurrent.locks.ReentrantLock r0 = new java.util.concurrent.locks.ReentrantLock
            r0.<init>(r1)
            com.oculus.modules.SocialModule.mInstanceLock = r0
            return
        L_0x002f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x0035 }
        L_0x0035:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.SocialModule.<clinit>():void");
    }

    public static List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("checkFBConnectAccount", ""));
        arrayList.add(new Pair("fetchFBConnectAccount", "i"));
        arrayList.add(new Pair("onFacebookConnectSuccess", "i"));
        arrayList.add(new Pair("fetchSocialBundleAccessToken", "i"));
        arrayList.add(new Pair("fetchMessengerVrAccessToken", "i"));
        return arrayList;
    }

    public static void notifyFriendsChanged() {
        notifyNative("onFriendsChangedEvent");
    }

    public static void notifyNative(String str) {
        mInstanceLock.lock();
        try {
            SocialModule socialModule = mInstance;
            if (socialModule != null && socialModule.IsNativeLoaded()) {
                RCTBaseJavaModule.nativeEmitEvent(socialModule.RVRCtxTag, str);
            }
        } finally {
            mInstanceLock.unlock();
        }
    }

    public static void notifyPartyChanged() {
        notifyNative("onPartyChangedEvent");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void unregisterFBConnectContentObserver() {
        ContentObserver contentObserver = mFBConnectContentObserver;
        if (contentObserver != null) {
            mFBConnectContentResolver.unregisterContentObserver(contentObserver);
            mFBConnectContentObserver = null;
        }
    }

    public void checkFBConnectAccount() {
        this.mContext.sendBroadcast(new Intent(ACTION_UPDATE_CURRENT_FACEBOOK_ACCOUNT));
    }

    public void fetchMessengerVrAccessToken(int i) {
        fetchAccessToken("3534234083363713", "581956559359077", i);
    }

    public void fetchSocialBundleAccessToken(int i) {
        fetchAccessToken(SOCIAL_BUNDLE_OC_ID, SOCIAL_BUNDLE_FB_ID, i);
    }

    public void shutdownModule() {
        mInstanceLock.lock();
        try {
            mInstance = null;
        } finally {
            mInstanceLock.unlock();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SocialModule(android.content.Context r4) {
        /*
            r3 = this;
            r3.<init>()
            r0 = 0
            r3.mContext = r0
            r3.mContext = r4
            java.lang.String r2 = "SocialModule"
            java.lang.String r0 = "getContentResolver"
            com.oculus.panellib.SystraceBlock r1 = new com.oculus.panellib.SystraceBlock
            r1.<init>(r2, r0)
            android.content.ContentResolver r0 = r4.getContentResolver()     // Catch:{ all -> 0x002a }
            com.oculus.modules.SocialModule.mFBConnectContentResolver = r0     // Catch:{ all -> 0x002a }
            r1.close()
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.SocialModule.mInstanceLock
            r0.lock()
            com.oculus.modules.SocialModule.mInstance = r3     // Catch:{ all -> 0x0025 }
            r0.unlock()
            return
        L_0x0025:
            r1 = move-exception
            r0.unlock()
            throw r1
        L_0x002a:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002c }
        L_0x002c:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x0030 }
        L_0x0030:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.SocialModule.<init>(android.content.Context):void");
    }

    public static /* synthetic */ String access$300() {
        return "SocialModule";
    }

    private void fetchAccessToken(final String str, final String str2, final int i) {
        ThreadExecutor.getInstance().execute(new Callable<String>() {
            /* class com.oculus.modules.SocialModule.AnonymousClass4 */

            /* JADX WARNING: Removed duplicated region for block: B:18:0x0056  */
            /* JADX WARNING: Removed duplicated region for block: B:24:0x0064  */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.String call() {
                /*
                // Method dump skipped, instructions count: 115
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.SocialModule.AnonymousClass4.call():java.lang.String");
            }
        }, new AbstractC057411o<String>() {
            /* class com.oculus.modules.SocialModule.AnonymousClass5 */

            @Override // X.AbstractC057411o
            public void onFailure(@NonNull Throwable th) {
                AnonymousClass0MD.A07("SocialModule", "Exception while fetching access token", th);
                RCTBaseJavaModule.nativeInvokeCallback(SocialModule.this.RVRCtxTag, i, "[]");
            }

            public void onSuccess(@Nullable String str) {
                RCTBaseJavaModule.nativeInvokeCallback(SocialModule.this.RVRCtxTag, i, AnonymousClass006.A09("[", str, "]"));
            }
        });
    }

    private void registerFBConnectContentObserver() {
        AnonymousClass3 r3 = new ContentObserver(new Handler(Looper.getMainLooper())) {
            /* class com.oculus.modules.SocialModule.AnonymousClass3 */

            public void onChange(boolean z) {
                SocialModule socialModule = SocialModule.this;
                socialModule.fetchFBConnectAccount(socialModule.onFacebookConnectSuccessCallbackID);
                SocialModule.this.unregisterFBConnectContentObserver();
            }
        };
        mFBConnectContentObserver = r3;
        mFBConnectContentResolver.registerContentObserver(HORIZON_CONTENT_URI, false, r3);
    }

    public static void registerFriendsChangeListener(Context context) {
        context.getContentResolver().registerContentObserver(Uri.parse("content://com.oculus.horizon.social"), true, new ContentObserver(new Handler()) {
            /* class com.oculus.modules.SocialModule.AnonymousClass7 */

            public boolean deliverSelfNotifications() {
                return false;
            }

            public void onChange(boolean z) {
                onChange(z, null);
            }

            public void onChange(boolean z, Uri uri) {
                SocialModule.notifyFriendsChanged();
            }
        });
    }

    public static void registerPartyChangeListener(Context context) {
        context.getContentResolver().registerContentObserver(Uri.parse("content://com.oculus.horizon.parties"), true, new ContentObserver(new Handler()) {
            /* class com.oculus.modules.SocialModule.AnonymousClass6 */

            public boolean deliverSelfNotifications() {
                return false;
            }

            public void onChange(boolean z) {
                onChange(z, null);
            }

            public void onChange(boolean z, Uri uri) {
                SocialModule.notifyPartyChanged();
            }
        });
    }

    public void fetchFBConnectAccount(final int i) {
        ThreadExecutor.getInstance().execute(new Callable<String>() {
            /* class com.oculus.modules.SocialModule.AnonymousClass1 */

            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0060, code lost:
                if (0 == 0) goto L_0x0065;
             */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.String call() {
                /*
                // Method dump skipped, instructions count: 102
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.SocialModule.AnonymousClass1.call():java.lang.String");
            }
        }, new AbstractC057411o<String>() {
            /* class com.oculus.modules.SocialModule.AnonymousClass2 */

            @Override // X.AbstractC057411o
            public void onFailure(@NonNull Throwable th) {
                AnonymousClass0MD.A07("SocialModule", "Exception while fetching Facebook Connect account", th);
            }

            public void onSuccess(@Nullable String str) {
                RCTBaseJavaModule.nativeInvokeCallback(SocialModule.this.RVRCtxTag, i, AnonymousClass006.A09("[", str, "]"));
            }
        });
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void onFacebookConnectSuccess(int i) {
        registerFBConnectContentObserver();
        this.onFacebookConnectSuccessCallbackID = i;
        this.mContext.sendBroadcast(new Intent(ACTION_UPDATE_CURRENT_FACEBOOK_ACCOUNT));
    }
}
