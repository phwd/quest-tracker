package com.oculus.common.fbaccountsmanager;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.oculus.horizon.fbconnect.contract.FBConnectContent;
import java.util.Optional;

/* access modifiers changed from: package-private */
@TargetApi(25)
public class MessengerVrAccountsContentProviderUtil {
    private static final String TAG = "MessengerVrAccountsContentProviderUtil";

    MessengerVrAccountsContentProviderUtil() {
    }

    static Optional<MessengerVrAccountResult> queryContentProvider(Context context, String[] strArr) {
        if (strArr.length == 0) {
            Log.d(TAG, "No projection is provided, returning nothing.");
            return Optional.empty();
        }
        try {
            Cursor query = context.getContentResolver().query(SharedConstants.CONTENT_PROVIDER_URI, strArr, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToNext()) {
                        MessengerVrAccountResult messengerVrAccountResult = new MessengerVrAccountResult();
                        for (String str : strArr) {
                            int columnIndex = query.getColumnIndex(str);
                            if (columnIndex < 0) {
                                Log.d(TAG, "Column " + str + " was not present.");
                            } else {
                                char c = 65535;
                                boolean z = true;
                                switch (str.hashCode()) {
                                    case -2038152322:
                                        if (str.equals(SharedConstants.COLUMN_KEY_HAS_CHECKED_HAS_ACKNOWLEDGED_MESSENGER)) {
                                            c = 5;
                                            break;
                                        }
                                        break;
                                    case -465235578:
                                        if (str.equals("messenger_is_authenticated")) {
                                            c = 3;
                                            break;
                                        }
                                        break;
                                    case -147132913:
                                        if (str.equals("user_id")) {
                                            c = 0;
                                            break;
                                        }
                                        break;
                                    case 27920537:
                                        if (str.equals("has_seen_new_user_authentication_dialog")) {
                                            c = 4;
                                            break;
                                        }
                                        break;
                                    case 339340927:
                                        if (str.equals(FBConnectContent.Account.USER_NAME)) {
                                            c = 1;
                                            break;
                                        }
                                        break;
                                    case 1334573732:
                                        if (str.equals("user_profile_picture_url")) {
                                            c = 2;
                                            break;
                                        }
                                        break;
                                }
                                if (c == 0) {
                                    messengerVrAccountResult.setUserId(query.getString(columnIndex));
                                } else if (c == 1) {
                                    messengerVrAccountResult.setUserName(query.getString(columnIndex));
                                } else if (c == 2) {
                                    messengerVrAccountResult.setUserProfilePictureUrl(query.getString(columnIndex));
                                } else if (c == 3) {
                                    if (query.getInt(columnIndex) != 1) {
                                        z = false;
                                    }
                                    messengerVrAccountResult.setMessengerIsAuthenticated(Boolean.valueOf(z));
                                } else if (c == 4) {
                                    if (query.getInt(columnIndex) != 1) {
                                        z = false;
                                    }
                                    messengerVrAccountResult.setHasSeenNewUserAuthenticationDialog(Boolean.valueOf(z));
                                } else if (c == 5) {
                                    if (query.getInt(columnIndex) != 1) {
                                        z = false;
                                    }
                                    messengerVrAccountResult.setHasCheckedHasAcknowledgedMessenger(Boolean.valueOf(z));
                                }
                            }
                        }
                        Optional<MessengerVrAccountResult> of = Optional.of(messengerVrAccountResult);
                        if (query != null) {
                            query.close();
                        }
                        return of;
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            Log.d(TAG, "Query " + SharedConstants.CONTENT_PROVIDER_URI + " returns no result.");
            Optional<MessengerVrAccountResult> empty = Optional.empty();
            if (query != null) {
                query.close();
            }
            return empty;
        } catch (Throwable th2) {
            Log.e(TAG, "Query " + SharedConstants.CONTENT_PROVIDER_URI + " failed with exception.", th2);
            return Optional.empty();
        }
        throw th;
    }
}
