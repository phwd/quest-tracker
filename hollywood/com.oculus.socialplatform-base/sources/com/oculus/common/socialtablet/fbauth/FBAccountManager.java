package com.oculus.common.socialtablet.fbauth;

import X.AbstractC10551og;
import X.AbstractC12361xL;
import X.AbstractC13251zE;
import X.AnonymousClass219;
import X.C13011yj;
import X.C13261zF;
import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.oculus.common.socialtablet.util.ExecutorUtil;
import java.util.Objects;
import java.util.Optional;

public final class FBAccountManager {
    public static final String TAG = "FBAccountManager";
    @Nullable
    public static Application sApplication;
    @Nullable
    public static FBUser sUser;
    @Nullable
    public static AbstractC13251zE<FBUser> sUserSingle;

    public static final class FbConnectAccountConstants {
        public static final String COLUMN_KEY_PROFILE_PICTURE = "profile_picture";
        public static final String COLUMN_KEY_USER_ID = "userid";
        public static final String COLUMN_KEY_USER_NAME = "user_name";
        public static final Uri URI = Uri.parse(URI_STRING);
        public static final String URI_STRING = "content://com.oculus.horizon.fbconnect/account";
    }

    public static void destroy() {
        sApplication = null;
        sUserSingle = null;
        sUser = null;
    }

    public static Optional<FBUser> getUser() {
        return Optional.ofNullable(sUser);
    }

    public static void initialize(Application application) {
        if (Objects.equals(application, sApplication) || sApplication == null) {
            sApplication = application;
            return;
        }
        throw new RuntimeException("FBAccountManager.initialize with different values.");
    }

    public static /* synthetic */ void lambda$queryUser$0(AbstractC10551og r8) throws Exception {
        Cursor query = sApplication.getContentResolver().query(FbConnectAccountConstants.URI, new String[]{"userid"}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    FBUser fBUser = new FBUser(query.getString(query.getColumnIndexOrThrow("userid")), query.getString(query.getColumnIndexOrThrow("user_name")), query.getString(query.getColumnIndexOrThrow("profile_picture")));
                    sUser = fBUser;
                    r8.onSuccess(fBUser);
                    query.close();
                    return;
                }
            } catch (Throwable unused) {
            }
        }
        throw new RuntimeException("ContentProvider query failed to get user Facebook account.");
        throw th;
    }

    public static AbstractC13251zE<FBUser> queryUser() {
        AbstractC13251zE<FBUser> r0 = sUserSingle;
        if (r0 != null) {
            return r0;
        }
        Objects.requireNonNull(sApplication, "FBAccountManager.queryUser: Must initialize first.");
        AbstractC13251zE A00 = AbstractC13251zE.A00($$Lambda$FBAccountManager$5su5CFQqOhxEWRKiK7ngSxi03ic2.INSTANCE);
        AbstractC12361xL r2 = ExecutorUtil.SCHEDULER;
        AnonymousClass219.A01(r2, "scheduler is null");
        C13261zF r02 = new C13261zF(new C13011yj(A00, r2));
        sUserSingle = r02;
        return r02;
    }
}
