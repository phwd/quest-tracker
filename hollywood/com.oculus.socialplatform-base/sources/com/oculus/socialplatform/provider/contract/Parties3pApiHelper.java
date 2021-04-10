package com.oculus.socialplatform.provider.contract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.oculus.socialplatform.provider.contract.SocialPlatformContent;
import com.oculus.util.constants.MicrophoneMuteState;
import com.oculus.util.constants.PlatformPluginConstants;
import javax.annotation.Nullable;

public class Parties3pApiHelper {
    @VisibleForTesting
    public static final String BUNDLE_STATE_KEY = "system_voip_state";
    @VisibleForTesting
    public static final String BUNDLE_VOLUME_KEY = "volume";
    public static final String TAG = "Parties3pApiHelper";
    public static boolean mPassesMicSwitcherGK;

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String[], java.lang.String, android.database.Cursor] */
    public static final Bundle getSystemVoipData(Context context) throws SecurityException, IllegalArgumentException, Exception {
        Cursor cursor = 0;
        try {
            cursor = context.getContentResolver().query(SocialPlatformContent.Parties3pApi.Queries.GET_DATA, cursor, cursor, cursor, cursor);
            return getPartyStateBundle(cursor);
        } finally {
            if (cursor != 0) {
                cursor.close();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String[], java.lang.String, android.database.Cursor] */
    public static String getSystemVoipMicrophoneMuted(Context context) throws IllegalArgumentException, Exception {
        Cursor cursor = 0;
        try {
            cursor = context.getContentResolver().query(SocialPlatformContent.Parties3pApi.Queries.GET_MIC_MUTE_STATE, cursor, cursor, cursor, cursor);
            return getStringFromCursor(cursor, "mic_mute_state");
        } finally {
            if (cursor != 0) {
                cursor.close();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String[], java.lang.String, android.database.Cursor] */
    public static String getSystemVoipStatus(Context context) throws IllegalArgumentException, Exception {
        Cursor cursor = 0;
        try {
            cursor = context.getContentResolver().query(SocialPlatformContent.Parties3pApi.Queries.GET_STATUS, cursor, cursor, cursor, cursor);
            return getStringFromCursor(cursor, "status");
        } finally {
            if (cursor != 0) {
                cursor.close();
            }
        }
    }

    public static final Bundle callbackToSyncPartyUI(Context context, String str, Uri uri) throws SecurityException, IllegalArgumentException, Exception {
        ContentValues contentValues = new ContentValues();
        Uri build = uri.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, str).build();
        if (context.getContentResolver().update(build, contentValues, null, null) == 1) {
            context.getContentResolver().notifyChange(build, null);
            return new Bundle();
        }
        throw new IllegalArgumentException();
    }

    public static float getFloatFromCursor(@Nullable Cursor cursor, String str) throws IllegalArgumentException {
        if (cursor == null || !cursor.moveToNext()) {
            throw new IllegalArgumentException();
        }
        Float valueOf = Float.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow(str)));
        if (valueOf != null) {
            return valueOf.floatValue();
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String[], java.lang.String, android.database.Cursor] */
    public static final Bundle getPartyChatVolume(Context context, String str) throws SecurityException, IllegalArgumentException, Exception {
        Cursor cursor = 0;
        try {
            cursor = context.getContentResolver().query(SocialPlatformContent.Parties3pApi.Queries.GET_VOLUME.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, str).build(), cursor, cursor, cursor, cursor);
            float floatFromCursor = getFloatFromCursor(cursor, "volume");
            Bundle bundle = new Bundle();
            bundle.putFloat("volume", floatFromCursor);
            return bundle;
        } finally {
            if (cursor != 0) {
                cursor.close();
            }
        }
    }

    public static Bundle getPartyStateBundle(@Nullable Cursor cursor) throws IllegalArgumentException {
        if (cursor == null || !cursor.moveToNext()) {
            throw new IllegalArgumentException();
        }
        String string = cursor.getString(cursor.getColumnIndexOrThrow(SocialPlatformContent.Parties3pApi.Out.STATE_JSON));
        Bundle bundle = new Bundle();
        bundle.putString("system_voip_state", string);
        return bundle;
    }

    public static String getStringFromCursor(@Nullable Cursor cursor, String str) throws IllegalArgumentException {
        if (cursor == null || !cursor.moveToNext()) {
            throw new IllegalArgumentException();
        }
        String string = cursor.getString(cursor.getColumnIndexOrThrow(str));
        if (string != null) {
            return string;
        }
        throw new IllegalArgumentException();
    }

    public static final boolean isPackageAllowedToModifySystemVoipSuppressed(String str) {
        if (str.contains(PlatformPluginConstants.FB_HORIZON_PACKAGE_NAME) || str.equals(PlatformPluginConstants.FB_HORIZON_RELEASE_PACKAGE_NAME)) {
            return true;
        }
        return false;
    }

    public static final void onAppSwitch(Context context, String str, String str2, boolean z) throws SecurityException, Exception {
        Uri build = SocialPlatformContent.Parties3pApi.Updates.ON_APP_SWITCH.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, str).build();
        ContentValues contentValues = new ContentValues();
        contentValues.put("package_name", str2);
        if (mPassesMicSwitcherGK) {
            z = false;
        }
        contentValues.put("exclusive_mic", Boolean.valueOf(z));
        context.getContentResolver().update(build, contentValues, null, null);
    }

    public static final void runPartyStateEnforcer(Context context, String str) throws SecurityException, Exception {
        context.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.RUN_PARTY_ENFORCER.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, str).build(), new ContentValues(), null, null);
    }

    public static final Bundle setPartyChatVolume(Context context, String str, float f) throws SecurityException, IllegalArgumentException, Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("volume", Float.valueOf(f));
        if (context.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.SET_VOLUME.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, str).build(), contentValues, null, null) == 1) {
            return new Bundle();
        }
        throw new IllegalArgumentException();
    }

    public static synchronized Bundle setSystemVoipSuppressed(Context context, String str, boolean z) throws IllegalArgumentException, Exception {
        Bundle bundle;
        synchronized (Parties3pApiHelper.class) {
            if (!z || !mPassesMicSwitcherGK || isPackageAllowedToModifySystemVoipSuppressed(str)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SocialPlatformContent.Parties3pApi.In.VOIP_SUPPRESSED, Boolean.valueOf(z));
                if (context.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.SET_SUPPRESSED, contentValues, null, null) != 1) {
                    throw new IllegalArgumentException();
                }
            }
            bundle = new Bundle();
        }
        return bundle;
    }

    public static final Bundle setVoipConnectionStatus(Context context, String str, String str2) throws SecurityException, IllegalArgumentException, Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("voip_connection_status", str2);
        Uri build = SocialPlatformContent.Parties3pApi.Updates.SET_VOIP_CONNECTION_STATUS.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, str).build();
        if (context.getContentResolver().update(build, contentValues, null, null) == 1) {
            context.getContentResolver().notifyChange(build, null);
            return new Bundle();
        }
        throw new IllegalArgumentException();
    }

    public static Bundle startPartyChat(Context context, String str, long j) throws SecurityException, IllegalArgumentException, Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", Long.valueOf(j));
        if (context.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.START_CHAT.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, str).build(), contentValues, null, null) == 1) {
            return new Bundle();
        }
        throw new IllegalArgumentException();
    }

    public static Bundle stopPartyChat(Context context, String str) throws SecurityException, Exception {
        if (context.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.STOP_CHAT.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, str).build(), null, null, null) == 1) {
            return new Bundle();
        }
        throw new IllegalArgumentException();
    }

    public static Bundle setSystemVoipMicrophoneMuted(Context context, String str, int i) throws IllegalArgumentException, SecurityException, Exception {
        MicrophoneMuteState.validate(i);
        ContentValues contentValues = new ContentValues();
        contentValues.put("mic_mute_state", Integer.valueOf(i));
        if (context.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.SET_MIC_MUTE_STATE.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, str).build(), contentValues, null, null) == 1) {
            return new Bundle();
        }
        throw new IllegalArgumentException();
    }

    public static void setMicSwitcherGKState(boolean z) {
        mPassesMicSwitcherGK = z;
    }
}
