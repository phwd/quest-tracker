package com.oculus.horizon.notifications.core;

import X.AnonymousClass0MD;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationsProperties implements Parcelable {
    public static final String AUI_NOTIFICATION_CTA = "com.oculus.horizon.AUI_NOTIFICATION_CTA";
    public static final Parcelable.Creator<NotificationsProperties> CREATOR = new Parcelable.Creator<NotificationsProperties>() {
        /* class com.oculus.horizon.notifications.core.NotificationsProperties.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public NotificationsProperties createFromParcel(Parcel parcel) {
            return new NotificationsProperties(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public NotificationsProperties[] newArray(int i) {
            return new NotificationsProperties[i];
        }
    };
    public static final String DEEPLINK_URI_KEY = "uri";
    public static final String EXTRA_ACTION_ON_CLICK_KEY = "action_on_click";
    public static final String EXTRA_CUSTOM_DATA_KEY = "extra_custom_data";
    public static final String EXTRA_NOTIFICATION_CATEGORY_KEY = "category";
    public static final String EXTRA_NOTIFICATION_FBID_KEY = "oculus_notification_fbid";
    public static final String EXTRA_NOTIFICATION_HIGH_PRI = "high_priority";
    public static final String EXTRA_NOTIFICATION_NDID_KEY = "oculus_notification_ndid";
    public static final String EXTRA_NOTIFICATION_TYPE_KEY = "oculus_notification_type";
    public static final String EXTRA_NOTIFICATION_UUID_KEY = "oculus_notification_uuid";
    public static final String HORIZON_PACKAGE = "com.oculus.horizon";
    public static final String IMAGE_URI_KEY = "imageUri";
    public static final String INTENT_DATA_EXTRA_KEY = "intent_data";
    public static final String INTENT_DATA_URI_KEY = "uri";
    public static final String MSG_NOTIFICATION_FEED_KEY = "is_in_feed";
    public static final String NOTIFICATION_PROPERTIES = "notification_properties";
    public static final String TAG = "NotificationsProperties";
    public static final String VR_NOTIFICATION_TAG = "vr_notification";
    public String mCustomDataString;
    @Nullable
    public String mDeeplink;
    @Nullable
    public String mDestination;
    public String mFBid;
    @Nullable
    public String mNDID;
    public String mType;
    public String mUUID;

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r1.equals(r5.mFBid) == false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        // Method dump skipped, instructions count: 105
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.notifications.core.NotificationsProperties.equals(java.lang.Object):boolean");
    }

    public boolean getActionOnClick() {
        try {
            String string = getCustomDataJson().getString(EXTRA_ACTION_ON_CLICK_KEY);
            if (string.isEmpty()) {
                return false;
            }
            return Boolean.parseBoolean(string);
        } catch (JSONException e) {
            AnonymousClass0MD.A09(TAG, "Error parsing custom JSON data for key %s", EXTRA_ACTION_ON_CLICK_KEY, e);
            return false;
        }
    }

    @Nullable
    public String getCategory() {
        try {
            return getCustomDataJson().getString("category");
        } catch (JSONException e) {
            AnonymousClass0MD.A09(TAG, "Error parsing custom JSON data for key %s", "category", e);
            return null;
        }
    }

    public JSONObject getCustomDataJson() {
        try {
            return new JSONObject(this.mCustomDataString);
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    public String getCustomDataString() {
        return this.mCustomDataString;
    }

    @Nullable
    public String getDeeplink() {
        if (TextUtils.isEmpty(this.mDeeplink)) {
            return null;
        }
        return this.mDeeplink;
    }

    @Nullable
    public String getDestination() {
        return this.mDestination;
    }

    public JSONObject getDestinationJson() {
        String str = this.mDestination;
        if (str != null) {
            try {
                return new JSONObject(str);
            } catch (JSONException e) {
                AnonymousClass0MD.A07(TAG, "Unable to parse JSON for destination", e);
            }
        }
        return new JSONObject();
    }

    public String getFBid() {
        return this.mFBid;
    }

    @Nullable
    public Uri getImageUri() {
        HashSet hashSet = new HashSet(Arrays.asList(IMAGE_URI_KEY, "sender_photo", "accepter_photo", "friend_photo", "application_photo"));
        try {
            JSONObject customDataJson = getCustomDataJson();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (customDataJson.has(str)) {
                    return Uri.parse(customDataJson.getString(str));
                }
            }
            return null;
        } catch (JSONException e) {
            AnonymousClass0MD.A07(TAG, "Unable to find key for any image uris in custom data", e);
            return null;
        }
    }

    @Nullable
    public String getNDID() {
        return this.mNDID;
    }

    @Nullable
    public String getOculusButtonOverrideUri() {
        try {
            JSONObject customDataJson = getCustomDataJson();
            if (customDataJson.has(NotificationBuilder.EXTRA_OCULUS_BUTTON_OVERRIDE_URI)) {
                return customDataJson.getString(NotificationBuilder.EXTRA_OCULUS_BUTTON_OVERRIDE_URI);
            }
            return null;
        } catch (JSONException e) {
            AnonymousClass0MD.A07(TAG, "Unable to find key for any image uris in custom data", e);
            return null;
        }
    }

    public String getType() {
        return this.mType;
    }

    public String getUUID() {
        return this.mUUID;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        String str = this.mFBid;
        int i4 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i5 = i * 31;
        String str2 = this.mUUID;
        if (str2 != null) {
            i2 = str2.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (i5 + i2) * 31;
        String str3 = this.mType;
        if (str3 != null) {
            i3 = str3.hashCode();
        } else {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        String str4 = this.mCustomDataString;
        if (str4 != null) {
            i4 = str4.hashCode();
        }
        return i7 + i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        if (java.lang.Boolean.parseBoolean(r1) == true) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean isFeedNotif() {
        /*
            r5 = this;
            java.lang.String r4 = "is_in_feed"
            r2 = 1
            r3 = 0
            org.json.JSONObject r0 = r5.getCustomDataJson()     // Catch:{ JSONException -> 0x002a }
            java.lang.String r1 = r0.getString(r4)     // Catch:{ JSONException -> 0x002a }
            boolean r0 = r1.isEmpty()     // Catch:{ JSONException -> 0x002a }
            if (r0 == 0) goto L_0x0017
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)     // Catch:{ JSONException -> 0x002a }
            return r0
        L_0x0017:
            int r0 = java.lang.Integer.parseInt(r1)     // Catch:{ JSONException -> 0x002a }
            if (r0 == r2) goto L_0x0024
            boolean r1 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ JSONException -> 0x002a }
            r0 = 0
            if (r1 != r2) goto L_0x0025
        L_0x0024:
            r0 = 1
        L_0x0025:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ JSONException -> 0x002a }
            return r0
        L_0x002a:
            r0 = move-exception
            java.lang.String r2 = "NotificationsProperties"
            java.lang.Object[] r1 = new java.lang.Object[]{r4, r0}
            java.lang.String r0 = "Error parsing custom JSON data for key %s"
            X.AnonymousClass0MD.A09(r2, r0, r1)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.notifications.core.NotificationsProperties.isFeedNotif():java.lang.Boolean");
    }

    public Boolean isHighPri() {
        try {
            JSONObject customDataJson = getCustomDataJson();
            if (customDataJson.has(EXTRA_NOTIFICATION_HIGH_PRI)) {
                return Boolean.valueOf(customDataJson.getBoolean(EXTRA_NOTIFICATION_HIGH_PRI));
            }
            return true;
        } catch (JSONException unused) {
            AnonymousClass0MD.A04(TAG, "Notification priority not set");
            return true;
        }
    }

    public void setCustomDataString(@Nullable String str) {
        if (str == null) {
            str = "";
        }
        this.mCustomDataString = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mFBid);
        parcel.writeString(this.mUUID);
        parcel.writeString(this.mType);
        parcel.writeString(this.mCustomDataString);
        parcel.writeString(this.mNDID);
        parcel.writeString(this.mDeeplink);
        parcel.writeString(this.mDestination);
    }

    public Set<String> getCustomDataKeys() {
        Iterator<String> keys = getCustomDataJson().keys();
        HashSet hashSet = new HashSet();
        while (keys.hasNext()) {
            hashSet.add(keys.next());
        }
        return hashSet;
    }

    @Nullable
    public Uri getDeeplinkUri() {
        if (getDeeplink() != null) {
            return Uri.parse(this.mDeeplink);
        }
        AnonymousClass0MD.A05(TAG, "No Deeplink sent from server, returning null");
        return null;
    }

    public void setFBid(String str) {
        this.mFBid = str;
    }

    public void setType(@Nullable String str) {
        this.mType = str;
    }

    public void setUUID(String str) {
        this.mUUID = str;
    }

    public NotificationsProperties(Parcel parcel) {
        this.mFBid = parcel.readString();
        this.mUUID = parcel.readString();
        this.mType = parcel.readString();
        this.mCustomDataString = parcel.readString();
        this.mNDID = parcel.readString();
        this.mDeeplink = parcel.readString();
        this.mDestination = parcel.readString();
    }

    public NotificationsProperties(String str, String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        this.mFBid = str;
        this.mUUID = str2;
        this.mType = str3;
        this.mCustomDataString = str4 == null ? "" : str4;
        this.mNDID = str5;
        this.mDeeplink = str6;
        this.mDestination = str7;
    }
}
