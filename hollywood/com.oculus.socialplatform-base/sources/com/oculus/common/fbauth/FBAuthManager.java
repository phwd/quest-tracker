package com.oculus.common.fbauth;

import X.AbstractC12361xL;
import X.AbstractC13251zE;
import X.AnonymousClass219;
import X.C13011yj;
import X.C13261zF;
import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.Objects;

public class FBAuthManager {
    public static final String COLUMN_KEY_ACCESS_TOKEN = "access_token";
    public static final String COLUMN_KEY_FB_APP_ID = "fb_app_id";
    public static final String PARAM_KEY_OVERRIDE_OC_APP_ID = "override_oc_app_id";
    public static final String TAG = "FBAuthManager";
    public static final String URI_STRING = "content://com.oculus.horizon.fbconnect/app_scoped_access_token";
    @Nullable
    public static AbstractC13251zE<String> sAccessTokenSingle;
    @Nullable
    public static Application sApplication;
    @Nullable
    public static String sFbAppId;
    @Nullable
    public static String sOcAppId;

    public static void destroy() {
        sAccessTokenSingle = null;
        sApplication = null;
        sFbAppId = null;
        sOcAppId = null;
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
                            query.close();
                        } else {
                            throw new IOException("Something went wrong when generating access token: Returned fb_app_id doesn't match Messenger VR FB app ID.");
                        }
                    }
                } catch (Throwable unused) {
                }
            }
            throw new IOException("ContentProvider query failed to generate access token.");
        }
        return string;
        throw th;
    }

    public static void initialize(Application application, String str, String str2) {
        if ((Objects.equals(sApplication, application) && Objects.equals(sFbAppId, str) && Objects.equals(sOcAppId, str2)) || sApplication == null || sFbAppId == null || sOcAppId == null) {
            sApplication = application;
            sFbAppId = str;
            sOcAppId = str2;
            return;
        }
        throw new RuntimeException("FBAuthManager.initialize is initialized to different values");
    }

    public static synchronized AbstractC13251zE<String> queryAccessToken() {
        AbstractC13251zE<String> r0;
        synchronized (FBAuthManager.class) {
            r0 = sAccessTokenSingle;
            if (r0 == null) {
                AbstractC13251zE A00 = AbstractC13251zE.A00($$Lambda$FBAuthManager$OkL1ssrWM0fSHr8UHbgXk7WRvdA2.INSTANCE);
                AbstractC12361xL r2 = ExecutorUtil.SCHEDULER;
                AnonymousClass219.A01(r2, "scheduler is null");
                r0 = new C13261zF<>(new C13011yj(A00, r2));
                sAccessTokenSingle = r0;
            }
        }
        return r0;
    }
}
