package com.oculus.common.fbauth;

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import java.io.IOException;
import java.util.Objects;

public class FBAuthManager {
    private static final String COLUMN_KEY_ACCESS_TOKEN = "access_token";
    private static final String COLUMN_KEY_FB_APP_ID = "fb_app_id";
    private static final String PARAM_KEY_OVERRIDE_OC_APP_ID = "override_oc_app_id";
    private static final String TAG = "FBAuthManager";
    private static final String URI_STRING = "content://com.oculus.horizon.fbconnect/app_scoped_access_token";
    @Nullable
    private static Single<String> sAccessTokenSingle;
    @Nullable
    private static Application sApplication;
    @Nullable
    private static String sFbAppId;
    @Nullable
    private static String sOcAppId;

    public static void initialize(Application application, String str, String str2) {
        if (!(!Objects.equals(sApplication, application) || !Objects.equals(sFbAppId, str) || !Objects.equals(sOcAppId, str2)) || sApplication == null || sFbAppId == null || sOcAppId == null) {
            sApplication = application;
            sFbAppId = str;
            sOcAppId = str2;
            return;
        }
        throw new RuntimeException("FBAuthManager.initialize is initialized to different values");
    }

    public static void destroy() {
        Log.d(TAG, "Destroying FBAuthManager");
        sAccessTokenSingle = null;
        sApplication = null;
        sFbAppId = null;
        sOcAppId = null;
    }

    public static synchronized Single<String> queryAccessToken() {
        synchronized (FBAuthManager.class) {
            if (sAccessTokenSingle != null) {
                return sAccessTokenSingle;
            }
            Log.d(TAG, "Logging user in.");
            sAccessTokenSingle = Single.create($$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA.INSTANCE).subscribeOn(ExecutorUtil.getScheduler()).cache();
            return sAccessTokenSingle;
        }
    }

    static /* synthetic */ void lambda$queryAccessToken$0(SingleEmitter singleEmitter) throws Exception {
        singleEmitter.onSuccess(generateAccessToken());
        Log.d(TAG, "Successfully logged in.");
    }

    public static synchronized String generateAccessToken() throws IOException {
        String string;
        synchronized (FBAuthManager.class) {
            Objects.requireNonNull(sApplication, "FBAuthManager.queryAccessToken: Must call initialize first.");
            Objects.requireNonNull(sFbAppId, "FBAuthManager.queryAccessToken: Must call initialize first.");
            Objects.requireNonNull(sOcAppId, "FBAuthManager.queryAccessToken: Must call initialize first.");
            Cursor query = sApplication.getContentResolver().query(Uri.parse(URI_STRING).buildUpon().appendQueryParameter("override_oc_app_id", sOcAppId).build(), null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        if (sFbAppId.equals(query.getString(query.getColumnIndexOrThrow("fb_app_id")))) {
                            string = query.getString(query.getColumnIndexOrThrow("access_token"));
                            if (query != null) {
                                query.close();
                            }
                        } else {
                            throw new IOException("Something went wrong when generating access token: Returned fb_app_id doesn't match Messenger VR FB app ID.");
                        }
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            throw new IOException("ContentProvider query failed to generate access token.");
        }
        return string;
        throw th;
    }
}
