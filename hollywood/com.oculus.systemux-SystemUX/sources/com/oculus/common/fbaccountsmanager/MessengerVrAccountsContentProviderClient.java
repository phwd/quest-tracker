package com.oculus.common.fbaccountsmanager;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import com.oculus.horizon.fbconnect.contract.FBConnectContent;
import java.util.Optional;

@TargetApi(25)
public class MessengerVrAccountsContentProviderClient {
    public static Optional<MessengerVrAccountResult> getAccountData(Context context) {
        return MessengerVrAccountsContentProviderUtil.queryContentProvider(context, new String[]{"user_id", FBConnectContent.Account.USER_NAME, "user_profile_picture_url", "messenger_is_authenticated", SharedConstants.COLUMN_KEY_HAS_CHECKED_HAS_ACKNOWLEDGED_MESSENGER});
    }

    public static boolean isMessengerAuthenticated(Context context) {
        return MessengerVrAccountsContentProviderUtil.queryContentProvider(context, new String[]{"messenger_is_authenticated"}).flatMap($$Lambda$DDuCzW9wSyB040FvvEOcpJysE.INSTANCE).orElse((U) false).booleanValue();
    }

    public static boolean setIsMessengerAuthenticated(Context context, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("messenger_is_authenticated", Boolean.valueOf(z));
        return context.getContentResolver().update(SharedConstants.CONTENT_PROVIDER_URI, contentValues, null, null) == 1;
    }

    public static boolean hasSeenNewUserAuthenticationDialog(Context context) {
        return MessengerVrAccountsContentProviderUtil.queryContentProvider(context, new String[]{"has_seen_new_user_authentication_dialog"}).flatMap($$Lambda$0jEPQO9HpSFRowGgWvipvLoftk.INSTANCE).orElse((U) false).booleanValue();
    }

    public static boolean setHasSeenNewUserAuthenticationDialog(Context context, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("has_seen_new_user_authentication_dialog", Boolean.valueOf(z));
        return context.getContentResolver().update(SharedConstants.CONTENT_PROVIDER_URI, contentValues, null, null) == 1;
    }

    public static boolean hasCheckedHasAcknowledgedMessenger(Context context) {
        return MessengerVrAccountsContentProviderUtil.queryContentProvider(context, new String[]{SharedConstants.COLUMN_KEY_HAS_CHECKED_HAS_ACKNOWLEDGED_MESSENGER}).flatMap($$Lambda$IGMAWJjBMMudfnOmHfrmk49OuSo.INSTANCE).orElse((U) false).booleanValue();
    }

    public static boolean setHasCheckedHasAcknowledgedMessenger(Context context, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SharedConstants.COLUMN_KEY_HAS_CHECKED_HAS_ACKNOWLEDGED_MESSENGER, Boolean.valueOf(z));
        return context.getContentResolver().update(SharedConstants.CONTENT_PROVIDER_URI, contentValues, null, null) == 1;
    }
}
