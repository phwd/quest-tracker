package com.oculus.profileapi;

import X.AbstractC06640p5;
import X.C003108z;
import android.content.Context;
import android.database.Cursor;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.provider.OculusContent;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class OVRProfile {
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;

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

    public static ProfilePrefs A00(OVRProfile oVRProfile) {
        boolean z;
        boolean z2;
        String string;
        String str = null;
        String str2 = null;
        Cursor query = oVRProfile.mContext.getContentResolver().query(OculusContent.Profile.CONTENT_URI, null, null, null, null);
        if (query == null) {
            z = false;
            z2 = false;
            string = null;
        } else {
            query.moveToFirst();
            int columnIndex = query.getColumnIndex(OculusContent.Profile.IS_LOGGED_IN);
            if (columnIndex < 0) {
                z = false;
            } else {
                z = false;
                if (query.getInt(columnIndex) > 0) {
                    z = true;
                }
            }
            int columnIndex2 = query.getColumnIndex(OculusContent.Profile.IS_AUTO_UPDATES_ENABLED);
            if (columnIndex2 < 0) {
                z2 = false;
            } else {
                z2 = false;
                if (query.getInt(columnIndex2) > 0) {
                    z2 = true;
                }
            }
            int columnIndex3 = query.getColumnIndex("id");
            if (columnIndex3 >= 0) {
                str2 = query.getString(columnIndex3);
            }
            int columnIndex4 = query.getColumnIndex("email");
            if (columnIndex4 < 0) {
                string = null;
            } else {
                string = query.getString(columnIndex4);
            }
            int columnIndex5 = query.getColumnIndex("facebook_email");
            if (columnIndex5 >= 0) {
                str = query.getString(columnIndex5);
            }
        }
        return new ProfilePrefs(z, z2, str2, string, str);
    }

    @Inject
    public OVRProfile(AbstractC06640p5 r2) {
        this.mContext = C003108z.A02(r2);
    }
}
