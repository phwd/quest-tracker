package com.oculus.profileapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.profileapi.ProfileapiModule;
import com.oculus.provider.OculusContent;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class OVRProfile {
    @UnsafeContextInjection
    @Inject
    @Eager
    private final Context mContext;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_profileapi_OVRProfile_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(ProfileapiModule.UL_id._UL__ULSEP_com_oculus_profileapi_OVRProfile_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final OVRProfile _UL__ULSEP_com_oculus_profileapi_OVRProfile_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OVRProfile) UL.factorymap.get(ProfileapiModule.UL_id._UL__ULSEP_com_oculus_profileapi_OVRProfile_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OVRProfile _UL__ULSEP_com_oculus_profileapi_OVRProfile_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new OVRProfile(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_profileapi_OVRProfile_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(ProfileapiModule.UL_id._UL__ULSEP_com_oculus_profileapi_OVRProfile_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public OVRProfile(InjectorLike injectorLike) {
        this.mContext = BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public static class ProfilePrefs {
        @Nullable
        public final String email;
        @Nullable
        public final String facebook_email;
        public final boolean isAutoUpdatesEnabled;
        public final boolean isLoggedIn;
        @Nullable
        public final String user_id;

        public ProfilePrefs(boolean z, boolean z2, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            this.isLoggedIn = z;
            this.isAutoUpdatesEnabled = z2;
            this.email = str2;
            this.facebook_email = str3;
            this.user_id = str;
        }
    }

    private ProfilePrefs fetch() {
        Cursor query = this.mContext.getContentResolver().query(OculusContent.Profile.CONTENT_URI, null, null, null, null);
        if (query == null) {
            return new ProfilePrefs(false, false, null, null, null);
        }
        query.moveToFirst();
        return new ProfilePrefs(getIntAsBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.IS_LOGGED_IN), false), getIntAsBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.IS_AUTO_UPDATES_ENABLED), false), getNullableStringWithDefault(query, query.getColumnIndex("id"), null), getNullableStringWithDefault(query, query.getColumnIndex("email"), null), getNullableStringWithDefault(query, query.getColumnIndex("facebook_email"), null));
    }

    public boolean isUserLoggedIn() {
        return fetch().isLoggedIn;
    }

    public boolean isAutoUpdatesEnabled() {
        return fetch().isAutoUpdatesEnabled;
    }

    @Nullable
    public String userId() {
        return fetch().user_id;
    }

    @Nullable
    public String email() {
        return fetch().email;
    }

    @Nullable
    public String facebookEmail() {
        return fetch().facebook_email;
    }

    private static void updateStringPreference(Context context, String str, String str2) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createStringContentValues(str, str2), null, null);
    }

    private static ContentValues createStringContentValues(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, str2);
        return contentValues;
    }

    private static void updateIntPreference(Context context, String str, int i) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createIntContentValues(str, i), null, null);
    }

    private static ContentValues createIntContentValues(String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, Integer.valueOf(i));
        return contentValues;
    }

    private static void updateBooleanPreference(Context context, String str, boolean z) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createContentValues(str, z), null, null);
    }

    private static ContentValues createContentValues(String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, Boolean.valueOf(z));
        return contentValues;
    }

    private static boolean getIntAsBooleanWithDefault(Cursor cursor, int i, boolean z) {
        if (i < 0) {
            return z;
        }
        return cursor.getInt(i) > 0;
    }

    private static int getIntWithDefault(Cursor cursor, int i, int i2) {
        return i < 0 ? i2 : cursor.getInt(i);
    }

    private static String getStringWithDefault(Cursor cursor, int i, String str) {
        return i < 0 ? str : cursor.getString(i);
    }

    @Nullable
    private static String getNullableStringWithDefault(Cursor cursor, int i, @Nullable String str) {
        return i < 0 ? str : cursor.getString(i);
    }
}
