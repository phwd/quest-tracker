package com.oculus.common.fbaccountsmanager;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import java.util.Optional;

@TargetApi(25)
public class MessengerVrAccountsContentProviderClient {
    public static boolean hasCheckedHasAcknowledgedMessenger(Context context) {
        return MessengerVrAccountsContentProviderUtil.queryContentProvider(context, new String[]{"has_checked_has_acknowledged_messenger"}).flatMap($$Lambda$IGMAWJjBMMudfnOmHfrmk49OuSo2.INSTANCE).orElse((U) false).booleanValue();
    }

    public static boolean hasSeenNewUserAuthenticationDialog(Context context) {
        return MessengerVrAccountsContentProviderUtil.queryContentProvider(context, new String[]{"has_seen_new_user_authentication_dialog"}).flatMap($$Lambda$0jEPQO9HpSFRowGgWvipvLoftk2.INSTANCE).orElse((U) false).booleanValue();
    }

    public static boolean isMessengerAuthenticated(Context context) {
        return MessengerVrAccountsContentProviderUtil.queryContentProvider(context, new String[]{"messenger_is_authenticated"}).flatMap($$Lambda$DDuCzW9wSyB040FvvEOcpJysE2.INSTANCE).orElse((U) false).booleanValue();
    }

    public static Optional<MessengerVrAccountResult> getAccountData(Context context) {
        return MessengerVrAccountsContentProviderUtil.queryContentProvider(context, new String[]{"user_id", "user_name", "user_profile_picture_url", "messenger_is_authenticated", "has_checked_has_acknowledged_messenger"});
    }

    public static boolean setHasCheckedHasAcknowledgedMessenger(Context context, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("has_checked_has_acknowledged_messenger", Boolean.valueOf(z));
        if (context.getContentResolver().update(SharedConstants.CONTENT_PROVIDER_URI, contentValues, null, null) != 1) {
            return false;
        }
        return true;
    }

    public static boolean setHasSeenNewUserAuthenticationDialog(Context context, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("has_seen_new_user_authentication_dialog", Boolean.valueOf(z));
        if (context.getContentResolver().update(SharedConstants.CONTENT_PROVIDER_URI, contentValues, null, null) != 1) {
            return false;
        }
        return true;
    }

    public static boolean setIsMessengerAuthenticated(Context context, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("messenger_is_authenticated", Boolean.valueOf(z));
        if (context.getContentResolver().update(SharedConstants.CONTENT_PROVIDER_URI, contentValues, null, null) != 1) {
            return false;
        }
        return true;
    }
}
