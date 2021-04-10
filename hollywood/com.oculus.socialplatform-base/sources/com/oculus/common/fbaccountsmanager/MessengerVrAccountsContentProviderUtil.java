package com.oculus.common.fbaccountsmanager;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.Optional;

@TargetApi(25)
public class MessengerVrAccountsContentProviderUtil {
    public static final String TAG = "MessengerVrAccountsContentProviderUtil";

    public static Optional<MessengerVrAccountResult> queryContentProvider(Context context, String[] strArr) {
        Optional<MessengerVrAccountResult> optional;
        int length = strArr.length;
        if (length == 0) {
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
                            if (columnIndex >= 0) {
                                char c = 65535;
                                boolean z = true;
                                switch (str.hashCode()) {
                                    case -2038152322:
                                        if (str.equals("has_checked_has_acknowledged_messenger")) {
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
                                        if (str.equals("user_name")) {
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
                                    messengerVrAccountResult.mUserId = query.getString(columnIndex);
                                } else if (c == 1) {
                                    messengerVrAccountResult.mUserName = query.getString(columnIndex);
                                } else if (c == 2) {
                                    messengerVrAccountResult.mUserProfilePictureUrl = query.getString(columnIndex);
                                } else if (c == 3) {
                                    if (query.getInt(columnIndex) != 1) {
                                        z = false;
                                    }
                                    messengerVrAccountResult.mMessengerIsAuthenticated = Boolean.valueOf(z);
                                } else if (c == 4) {
                                    if (query.getInt(columnIndex) != 1) {
                                        z = false;
                                    }
                                    messengerVrAccountResult.mHasSeenNewUserAuthenticationDialog = Boolean.valueOf(z);
                                } else if (c == 5) {
                                    if (query.getInt(columnIndex) != 1) {
                                        z = false;
                                    }
                                    messengerVrAccountResult.mHasCheckedHasAcknowledgedMessenger = Boolean.valueOf(z);
                                }
                            }
                        }
                        optional = Optional.of(messengerVrAccountResult);
                        query.close();
                        return optional;
                    }
                } catch (Throwable unused) {
                }
            }
            optional = Optional.empty();
            if (query == null) {
                return optional;
            }
            query.close();
            return optional;
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Query ");
            sb.append(SharedConstants.CONTENT_PROVIDER_URI);
            sb.append(" failed with exception.");
            Log.e(TAG, sb.toString(), th);
            return Optional.empty();
        }
        throw th;
    }
}
