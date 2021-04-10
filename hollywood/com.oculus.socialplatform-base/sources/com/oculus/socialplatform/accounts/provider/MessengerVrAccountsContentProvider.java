package com.oculus.socialplatform.accounts.provider;

import X.AnonymousClass0MD;
import X.AnonymousClass0Ud;
import X.AnonymousClass0Ue;
import X.C03070ky;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import androidx.annotation.Nullable;
import com.oculus.content.OculusFbPermissionsContentProvider;
import com.oculus.socialplatform.accounts.provider.AccountGraphQLUtil;
import com.oculus.socialplatform.accounts.provider.Constants;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import okhttp3.OkHttpClient;

@TargetApi(25)
public class MessengerVrAccountsContentProvider extends OculusFbPermissionsContentProvider {
    public static final String AUTHORITY = "com.oculus.socialplatform.messengervraccounts";
    public static final int CONTENT_PATH_V1 = 1;
    public static final String PREFERENCE_FILE = "com.oculus.socialplatform.MESSENGERVRACCOUNTS_PREFERENCE_FILE";
    public static final String TAG = "MessengerVrAccountsContentProvider";
    public static final UriMatcher URI_MATCHER;
    @Nullable
    public OkHttpClient mOkHttpClient;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        URI_MATCHER = uriMatcher;
        uriMatcher.addURI(AUTHORITY, "/v1", 1);
    }

    private void populateUserColumns(Context context, MatrixCursor matrixCursor, MatrixCursor.RowBuilder rowBuilder) {
        synchronized (this) {
            if (this.mOkHttpClient == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.certificatePinner(AnonymousClass0Ud.A00(Build.TIME));
                builder.interceptors.add(new C03070ky(AnonymousClass0Ue.A00));
                this.mOkHttpClient = builder.build();
            }
        }
        try {
            AccountGraphQLUtil.fetchUserData(this.mOkHttpClient, FbConnectAppScopedAccessTokenHelper.queryAccessToken(context)).ifPresent(new Consumer(matrixCursor, rowBuilder) {
                /* class com.oculus.socialplatform.accounts.provider.$$Lambda$MessengerVrAccountsContentProvider$x3R9NBT6hNt2uNS2ok1b6nOjnUU */
                public final /* synthetic */ MatrixCursor f$0;
                public final /* synthetic */ MatrixCursor.RowBuilder f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    MessengerVrAccountsContentProvider.lambda$populateUserColumns$1(this.f$0, this.f$1, (AccountGraphQLUtil.Result) obj);
                }
            });
        } catch (IOException e) {
            AnonymousClass0MD.A0C(TAG, e, "Failed fetching user profile picture url");
        }
    }

    private synchronized boolean updateFields(ContentValues contentValues) {
        SharedPreferences.Editor edit;
        edit = ((Context) Objects.requireNonNull(getContext(), "getContext() should not return null.")).getSharedPreferences(PREFERENCE_FILE, 0).edit();
        if (contentValues.containsKey("messenger_is_authenticated")) {
            putValueToEditor(contentValues, edit, "messenger_is_authenticated");
        }
        if (contentValues.containsKey("has_seen_new_user_authentication_dialog")) {
            putValueToEditor(contentValues, edit, "has_seen_new_user_authentication_dialog");
        }
        if (contentValues.containsKey("has_checked_has_acknowledged_messenger")) {
            putValueToEditor(contentValues, edit, "has_checked_has_acknowledged_messenger");
        }
        return edit.commit();
    }

    @Override // X.AnonymousClass0jF
    public int doDelete(Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public String doGetType(Uri uri) {
        return null;
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public Uri doInsert(Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    public String getFbPermission() {
        return "com.oculus.socialplatform.fbpermission.MESSENGER_VR_ACCOUNTS_CONTENT_PROVIDER_WRITE_ONLY";
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    public String getReadOnlyFbPermission() {
        return "com.oculus.socialplatform.fbpermission.MESSENGER_VR_ACCOUNTS_CONTENT_PROVIDER_READ_ONLY";
    }

    public static synchronized void getValueFromPreferencesToRowBuilder(MatrixCursor.RowBuilder rowBuilder, SharedPreferences sharedPreferences, String str) {
        synchronized (MessengerVrAccountsContentProvider.class) {
            int i = 0;
            if (sharedPreferences.getBoolean(str, false)) {
                i = 1;
            }
            rowBuilder.add(str, Integer.valueOf(i));
        }
    }

    public static /* synthetic */ void lambda$populateUserColumns$1(MatrixCursor matrixCursor, MatrixCursor.RowBuilder rowBuilder, AccountGraphQLUtil.Result result) {
        if (matrixCursor.getColumnIndex("user_name") >= 0) {
            rowBuilder.add("user_name", result.mUserName);
        }
        if (matrixCursor.getColumnIndex("user_profile_picture_url") >= 0) {
            Optional.ofNullable(result.mUserProfilePictureUrl).ifPresent(new Consumer(rowBuilder) {
                /* class com.oculus.socialplatform.accounts.provider.$$Lambda$MessengerVrAccountsContentProvider$KZf92CJjqEAm0FuMSYXm5n4W4 */
                public final /* synthetic */ MatrixCursor.RowBuilder f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    MessengerVrAccountsContentProvider.lambda$populateUserColumns$0(this.f$0, (String) obj);
                }
            });
        }
    }

    public static synchronized void putValueToEditor(ContentValues contentValues, SharedPreferences.Editor editor, String str) {
        synchronized (MessengerVrAccountsContentProvider.class) {
            Boolean asBoolean = contentValues.getAsBoolean(str);
            if (asBoolean != null) {
                editor.putBoolean(str, asBoolean.booleanValue());
            }
        }
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public Cursor doQuery(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        if (URI_MATCHER.match(uri) == 1) {
            uri.getPath();
            if (!(strArr == null || strArr.length == 0)) {
                Context context = (Context) Objects.requireNonNull(getContext(), "getContext() should not return null.");
                MatrixCursor matrixCursor = new MatrixCursor(strArr);
                MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
                if (matrixCursor.getColumnIndex("messenger_is_authenticated") >= 0 || matrixCursor.getColumnIndex("has_seen_new_user_authentication_dialog") >= 0 || matrixCursor.getColumnIndex("has_checked_has_acknowledged_messenger") >= 0) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE, 0);
                    if (matrixCursor.getColumnIndex("messenger_is_authenticated") >= 0) {
                        getValueFromPreferencesToRowBuilder(newRow, sharedPreferences, "messenger_is_authenticated");
                    }
                    if (matrixCursor.getColumnIndex("has_seen_new_user_authentication_dialog") >= 0) {
                        getValueFromPreferencesToRowBuilder(newRow, sharedPreferences, "has_seen_new_user_authentication_dialog");
                    }
                    if (matrixCursor.getColumnIndex("has_checked_has_acknowledged_messenger") >= 0) {
                        getValueFromPreferencesToRowBuilder(newRow, sharedPreferences, "has_checked_has_acknowledged_messenger");
                    }
                }
                if (matrixCursor.getColumnIndex("user_id") >= 0) {
                    populateUserIdColumns(context, newRow);
                }
                if (matrixCursor.getColumnIndex("user_name") >= 0 || matrixCursor.getColumnIndex("user_profile_picture_url") >= 0) {
                    populateUserColumns(context, matrixCursor, newRow);
                }
                return matrixCursor;
            }
        } else {
            uri.getPath();
        }
        return null;
    }

    @Override // X.AnonymousClass0jF
    public int doUpdate(Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        if (URI_MATCHER.match(uri) == 1) {
            uri.getPath();
            if (contentValues != null && ((contentValues.containsKey("messenger_is_authenticated") || contentValues.containsKey("has_seen_new_user_authentication_dialog") || contentValues.containsKey("has_checked_has_acknowledged_messenger")) && updateFields(contentValues))) {
                ((Context) Objects.requireNonNull(getContext(), "getContext() should not return null.")).getContentResolver().notifyChange(uri, null);
                return 1;
            }
        }
        return 0;
    }

    public static void populateUserIdColumns(Context context, MatrixCursor.RowBuilder rowBuilder) {
        int columnIndex;
        Cursor query = context.getContentResolver().query(Constants.FbConnect.FB_CONNECT_ACCOUNT_CONTENT_PROVIDER_URI, null, null, null, null);
        if (query != null) {
            try {
                if (query.moveToNext() && (columnIndex = query.getColumnIndex("userid")) >= 0) {
                    rowBuilder.add("user_id", query.getString(columnIndex));
                }
                query.close();
                return;
            } catch (Throwable unused) {
            }
        } else {
            return;
        }
        throw th;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AnonymousClass0jF
    public boolean onCheckPermissions() {
        if (Binder.getCallingUid() == 0 || super.onCheckPermissions()) {
            return true;
        }
        return false;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AnonymousClass0jF
    public boolean onCheckReadOnlyPermissions() {
        if (Binder.getCallingUid() == 0 || super.onCheckReadOnlyPermissions()) {
            return true;
        }
        return false;
    }
}
