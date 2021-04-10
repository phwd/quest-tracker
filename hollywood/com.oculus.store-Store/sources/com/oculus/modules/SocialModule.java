package com.oculus.modules;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.debug.log.BLog;
import com.facebook.debug.log.LoggingUtil;
import com.google.common.util.concurrent.FutureCallback;
import com.google.gson.Gson;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class SocialModule extends RCTBaseJavaModule {
    private static final String ACTION_UPDATE_CURRENT_FACEBOOK_ACCOUNT = "com.oculus.fbconnect.UPDATE_CURRENT_FACEBOOK_ACCOUNT";
    private static final Gson GSON;
    private static final String HORIZON_ACCESS_TOKEN_KEY = "accesstoken";
    private static final Uri HORIZON_CONTENT_URI = Uri.parse("content://com.oculus.horizon.fbconnect/account");
    private static final String HORIZON_PROFILE_PICTURE_KEY = "profile_picture";
    private static final String HORIZON_UID_KEY = "userid";
    private static final String HORIZON_USER_NAME_KEY = "user_name";
    private static final String MESSENGER_VR_FB_ID = "581956559359077";
    private static final String MESSENGER_VR_OC_ID = "3534234083363713";
    private static final String MODULE_NAME = new String("Social");
    private static final String SOCIAL_BUNDLE_FB_ID = "461177937822158";
    private static final String SOCIAL_BUNDLE_OC_ID = "3519383184769243";
    private static final String TAG = SocialModule.class.getSimpleName();
    private static ContentObserver mFBConnectContentObserver;
    private static ContentResolver mFBConnectContentResolver;
    private static SocialModule mInstance = null;
    private static final ReentrantLock mInstanceLock = new ReentrantLock(true);
    private Context mContext = null;
    private int onFacebookConnectSuccessCallbackID;

    public static class AppScopedAccessToken {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String AUTHORITY = "com.oculus.horizon.fbconnect";
        public static final String FB_APP_ID = "fb_app_id";
        public static final String OVERRIDE_OC_APP_ID = "override_oc_app_id";
        public static final Uri URI = Uri.parse("content://com.oculus.horizon.fbconnect/app_scoped_access_token");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005c, code lost:
        r1 = th;
     */
    static {
        /*
            r2 = 0
            java.lang.String r1 = new java.lang.String
            java.lang.String r3 = "Social"
            r1.<init>(r3)
            com.oculus.modules.SocialModule.MODULE_NAME = r1
            java.lang.Class<com.oculus.modules.SocialModule> r1 = com.oculus.modules.SocialModule.class
            java.lang.String r1 = r1.getSimpleName()
            com.oculus.modules.SocialModule.TAG = r1
            java.lang.String r1 = "content://com.oculus.horizon.fbconnect/account"
            android.net.Uri r1 = android.net.Uri.parse(r1)
            com.oculus.modules.SocialModule.HORIZON_CONTENT_URI = r1
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r1 = com.oculus.modules.SocialModule.TAG
            java.lang.String r3 = "GSON"
            r0.<init>(r1, r3)
            com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            r1.<init>()     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            com.oculus.modules.SocialModule.GSON = r1     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            if (r0 == 0) goto L_0x0031
            if (r2 == 0) goto L_0x0041
            r0.close()     // Catch:{ Throwable -> 0x003c }
        L_0x0031:
            com.oculus.modules.SocialModule.mInstance = r2
            java.util.concurrent.locks.ReentrantLock r1 = new java.util.concurrent.locks.ReentrantLock
            r2 = 1
            r1.<init>(r2)
            com.oculus.modules.SocialModule.mInstanceLock = r1
            return
        L_0x003c:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x0031
        L_0x0041:
            r0.close()
            goto L_0x0031
        L_0x0045:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L_0x004b:
            if (r0 == 0) goto L_0x0052
            if (r2 == 0) goto L_0x0058
            r0.close()     // Catch:{ Throwable -> 0x0053 }
        L_0x0052:
            throw r1
        L_0x0053:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x0052
        L_0x0058:
            r0.close()
            goto L_0x0052
        L_0x005c:
            r1 = move-exception
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.SocialModule.<clinit>():void");
    }

    /* access modifiers changed from: private */
    public static class FacebookAccount {
        private String accessToken;
        private String name;
        private String profilePicture;
        private String uid;

        public FacebookAccount(String uid2, String name2, String accessToken2, String profilePicture2) {
            this.uid = uid2;
            this.name = name2;
            this.accessToken = accessToken2;
            this.profilePicture = profilePicture2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0052, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SocialModule(android.content.Context r6) {
        /*
            r5 = this;
            r2 = 0
            r5.<init>()
            r5.mContext = r2
            r5.mContext = r6
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r1 = com.oculus.modules.SocialModule.TAG
            java.lang.String r3 = "getContentResolver"
            r0.<init>(r1, r3)
            android.content.ContentResolver r1 = r6.getContentResolver()     // Catch:{ Throwable -> 0x0034, all -> 0x0052 }
            com.oculus.modules.SocialModule.mFBConnectContentResolver = r1     // Catch:{ Throwable -> 0x0034, all -> 0x0052 }
            if (r0 == 0) goto L_0x001e
            if (r2 == 0) goto L_0x0030
            r0.close()     // Catch:{ Throwable -> 0x002b }
        L_0x001e:
            java.util.concurrent.locks.ReentrantLock r1 = com.oculus.modules.SocialModule.mInstanceLock
            r1.lock()
            com.oculus.modules.SocialModule.mInstance = r5     // Catch:{ all -> 0x004b }
            java.util.concurrent.locks.ReentrantLock r1 = com.oculus.modules.SocialModule.mInstanceLock
            r1.unlock()
            return
        L_0x002b:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x001e
        L_0x0030:
            r0.close()
            goto L_0x001e
        L_0x0034:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L_0x003a:
            if (r0 == 0) goto L_0x0041
            if (r2 == 0) goto L_0x0047
            r0.close()     // Catch:{ Throwable -> 0x0042 }
        L_0x0041:
            throw r1
        L_0x0042:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x0041
        L_0x0047:
            r0.close()
            goto L_0x0041
        L_0x004b:
            r1 = move-exception
            java.util.concurrent.locks.ReentrantLock r2 = com.oculus.modules.SocialModule.mInstanceLock
            r2.unlock()
            throw r1
        L_0x0052:
            r1 = move-exception
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.SocialModule.<init>(android.content.Context):void");
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("checkFBConnectAccount", ""));
        list.add(new Pair<>("fetchFBConnectAccount", "i"));
        list.add(new Pair<>("onFacebookConnectSuccess", "i"));
        list.add(new Pair<>("fetchSocialBundleAccessToken", "i"));
        list.add(new Pair<>("fetchMessengerVrAccessToken", "i"));
        return list;
    }

    public void shutdownModule() {
        mInstanceLock.lock();
        try {
            mInstance = null;
        } finally {
            mInstanceLock.unlock();
        }
    }

    public void checkFBConnectAccount() {
        this.mContext.sendBroadcast(new Intent(ACTION_UPDATE_CURRENT_FACEBOOK_ACCOUNT));
    }

    public void fetchFBConnectAccount(final int callbackID) {
        ThreadExecutor.getInstance().execute(new Callable<String>() {
            /* class com.oculus.modules.SocialModule.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public String call() {
                String[] projection = {"userid", "user_name", "accesstoken", "profile_picture"};
                String facebookAccountString = LoggingUtil.NO_HASHCODE;
                Cursor cursor = null;
                try {
                    Cursor cursor2 = SocialModule.mFBConnectContentResolver.query(SocialModule.HORIZON_CONTENT_URI, projection, null, null, null);
                    if (cursor2 != null) {
                        int userIdColumnIndex = cursor2.getColumnIndex("userid");
                        int userNameColumnIndex = cursor2.getColumnIndex("user_name");
                        int accessTokenColumnIndex = cursor2.getColumnIndex("accesstoken");
                        int profilePictureColumnIndex = cursor2.getColumnIndex("profile_picture");
                        if (cursor2.moveToNext()) {
                            facebookAccountString = SocialModule.GSON.toJson(new FacebookAccount(cursor2.getString(userIdColumnIndex), cursor2.getString(userNameColumnIndex), cursor2.getString(accessTokenColumnIndex), cursor2.getString(profilePictureColumnIndex)));
                        }
                    } else {
                        BLog.e(SocialModule.TAG, "Cursor was null");
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return facebookAccountString;
                } catch (Exception e) {
                    BLog.e(SocialModule.TAG, "Failed to query ContentProvider", e);
                    if (0 != 0) {
                        cursor.close();
                    }
                    return facebookAccountString;
                } catch (Throwable th) {
                    if (0 != 0) {
                        cursor.close();
                    }
                    return facebookAccountString;
                }
            }
        }, new FutureCallback<String>() {
            /* class com.oculus.modules.SocialModule.AnonymousClass2 */

            public void onSuccess(@Nullable String facebookAccountString) {
                SocialModule.nativeInvokeCallback(SocialModule.this.RVRCtxTag, callbackID, "[" + facebookAccountString + "]");
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(@NonNull Throwable throwable) {
                BLog.e(SocialModule.TAG, "Exception while fetching Facebook Connect account", throwable);
            }
        });
    }

    public void onFacebookConnectSuccess(int callbackID) {
        registerFBConnectContentObserver();
        this.onFacebookConnectSuccessCallbackID = callbackID;
        this.mContext.sendBroadcast(new Intent(ACTION_UPDATE_CURRENT_FACEBOOK_ACCOUNT));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void unregisterFBConnectContentObserver() {
        if (mFBConnectContentObserver != null) {
            mFBConnectContentResolver.unregisterContentObserver(mFBConnectContentObserver);
            mFBConnectContentObserver = null;
        }
    }

    private void registerFBConnectContentObserver() {
        mFBConnectContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
            /* class com.oculus.modules.SocialModule.AnonymousClass3 */

            public void onChange(boolean selfChange) {
                SocialModule.this.fetchFBConnectAccount(SocialModule.this.onFacebookConnectSuccessCallbackID);
                SocialModule.this.unregisterFBConnectContentObserver();
            }
        };
        mFBConnectContentResolver.registerContentObserver(HORIZON_CONTENT_URI, false, mFBConnectContentObserver);
    }

    private static void notifyNative(String eventName) {
        mInstanceLock.lock();
        try {
            if (mInstance != null && mInstance.IsNativeLoaded()) {
                nativeEmitEvent(mInstance.RVRCtxTag, eventName);
            }
        } finally {
            mInstanceLock.unlock();
        }
    }

    public static void notifyPartyChanged() {
        notifyNative("onPartyChangedEvent");
    }

    public static void notifyFriendsChanged() {
        notifyNative("onFriendsChangedEvent");
    }

    public void fetchSocialBundleAccessToken(int callbackID) {
        fetchAccessToken(SOCIAL_BUNDLE_OC_ID, SOCIAL_BUNDLE_FB_ID, callbackID);
    }

    public void fetchMessengerVrAccessToken(int callbackID) {
        fetchAccessToken(MESSENGER_VR_OC_ID, MESSENGER_VR_FB_ID, callbackID);
    }

    private void fetchAccessToken(final String ocAppId, final String fbAppId, final int callbackID) {
        ThreadExecutor.getInstance().execute(new Callable<String>() {
            /* class com.oculus.modules.SocialModule.AnonymousClass4 */

            @Override // java.util.concurrent.Callable
            public String call() {
                Cursor cursor = null;
                String accessToken = null;
                String error = null;
                AppScopedAccessToken.URI.buildUpon();
                try {
                    Cursor cursor2 = SocialModule.mFBConnectContentResolver.query(AppScopedAccessToken.URI.buildUpon().appendQueryParameter("override_oc_app_id", ocAppId).build(), null, null, null, null);
                    if (cursor2 == null || !cursor2.moveToFirst()) {
                        error = "Cursor for app scoped access token was null";
                    } else {
                        int accessTokenIndex = cursor2.getColumnIndexOrThrow("access_token");
                        if (cursor2.getString(cursor2.getColumnIndexOrThrow("fb_app_id")).equals(fbAppId)) {
                            accessToken = cursor2.getString(accessTokenIndex);
                        } else {
                            error = "Incorrect app scoped access token returned";
                        }
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return SocialModule.GSON.toJson(new AppScopedAccessTokenResponse(accessToken, error));
                } catch (Exception e) {
                    String error2 = e.getMessage();
                    if (0 != 0) {
                        cursor.close();
                    }
                    return SocialModule.GSON.toJson(new AppScopedAccessTokenResponse(null, error2));
                } catch (Throwable th) {
                    if (0 != 0) {
                        cursor.close();
                    }
                    return SocialModule.GSON.toJson(new AppScopedAccessTokenResponse(null, null));
                }
            }
        }, new FutureCallback<String>() {
            /* class com.oculus.modules.SocialModule.AnonymousClass5 */

            public void onSuccess(@Nullable String accessToken) {
                SocialModule.nativeInvokeCallback(SocialModule.this.RVRCtxTag, callbackID, "[" + accessToken + "]");
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(@NonNull Throwable throwable) {
                BLog.e(SocialModule.TAG, "Exception while fetching access token", throwable);
                SocialModule.nativeInvokeCallback(SocialModule.this.RVRCtxTag, callbackID, "[]");
            }
        });
    }

    public static void registerPartyChangeListener(Context context) {
        context.getContentResolver().registerContentObserver(Uri.parse("content://com.oculus.horizon.parties"), true, new ContentObserver(new Handler()) {
            /* class com.oculus.modules.SocialModule.AnonymousClass6 */

            public boolean deliverSelfNotifications() {
                return false;
            }

            public void onChange(boolean selfChange) {
                onChange(selfChange, null);
            }

            public void onChange(boolean selfChange, Uri uri) {
                SocialModule.notifyPartyChanged();
            }
        });
    }

    public static void registerFriendsChangeListener(Context context) {
        context.getContentResolver().registerContentObserver(Uri.parse("content://com.oculus.horizon.social"), true, new ContentObserver(new Handler()) {
            /* class com.oculus.modules.SocialModule.AnonymousClass7 */

            public boolean deliverSelfNotifications() {
                return false;
            }

            public void onChange(boolean selfChange) {
                onChange(selfChange, null);
            }

            public void onChange(boolean selfChange, Uri uri) {
                SocialModule.notifyFriendsChanged();
            }
        });
    }
}
