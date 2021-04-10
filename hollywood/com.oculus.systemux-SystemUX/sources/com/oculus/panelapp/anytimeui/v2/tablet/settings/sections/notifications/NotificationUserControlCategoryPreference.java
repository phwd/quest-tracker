package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications;

import android.util.Log;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationUserControlCategoryPreference {
    private static final String MEDIUM_EMAIL = "EMAIL";
    private static final String MEDIUM_PUSH = "PUSH";
    private static final String MEDIUM_XR_DEVICE = "XR_DEVICE";
    private static final String TAG = LoggingUtil.tag(NotificationUserControlCategoryPreference.class);
    private final String mDescription;
    private boolean mEnabled;
    private final String mMedium;
    private final String mTitle;

    public NotificationUserControlCategoryPreference(String str, String str2, String str3, boolean z) {
        this.mTitle = str;
        this.mDescription = str2;
        this.mMedium = str3;
        this.mEnabled = z;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(Boolean bool) {
        this.mEnabled = bool.booleanValue();
    }

    public String getMedium() {
        return this.mMedium;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0040  */
    @androidx.annotation.DrawableRes
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getIcon() {
        /*
            r4 = this;
            java.lang.String r0 = r4.mMedium
            int r1 = r0.hashCode()
            r2 = 2467610(0x25a71a, float:3.457858E-39)
            r3 = 1
            if (r1 == r2) goto L_0x002b
            r2 = 66081660(0x3f0537c, float:1.4125099E-36)
            if (r1 == r2) goto L_0x0021
            r2 = 1460967227(0x5714973b, float:1.63377251E14)
            if (r1 == r2) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r1 = "XR_DEVICE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0035
            r0 = 2
            goto L_0x0036
        L_0x0021:
            java.lang.String r1 = "EMAIL"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0035
            r0 = 0
            goto L_0x0036
        L_0x002b:
            java.lang.String r1 = "PUSH"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0035
            r0 = r3
            goto L_0x0036
        L_0x0035:
            r0 = -1
        L_0x0036:
            if (r0 == 0) goto L_0x0040
            if (r0 == r3) goto L_0x003d
            int r0 = com.oculus.panelapp.anytimeui.R.drawable.oc_icon_headset_filled_24_d2d2d2
            return r0
        L_0x003d:
            int r0 = com.oculus.panelapp.anytimeui.R.drawable.oc_icon_mobile_filled_24_d2d2d2
            return r0
        L_0x0040:
            int r0 = com.oculus.panelapp.anytimeui.R.drawable.oc_icon_email_filled_24_d2d2d2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationUserControlCategoryPreference.getIcon():int");
    }

    public static NotificationUserControlCategoryPreference fromJSON(JSONObject jSONObject) {
        try {
            return new NotificationUserControlCategoryPreference(jSONObject.getString("title"), jSONObject.getString(AssistantDialogContract.Dialog.DESCRIPTION), jSONObject.getString("medium"), jSONObject.getBoolean(SharingManagerContract.ARGUMENT_IS_ENABLED));
        } catch (JSONException e) {
            Log.d(TAG, "Error parsing json", e);
            return null;
        }
    }
}
