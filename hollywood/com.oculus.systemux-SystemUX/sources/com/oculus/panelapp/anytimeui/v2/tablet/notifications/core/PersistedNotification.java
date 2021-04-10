package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.common.notifications.R;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.vrshell.notifications.NotificationsType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class PersistedNotification<T extends JSONObject> extends AbstractVRNotification implements IBaseVRNotification {
    private static final String CATEGORY_FIELD = "category";
    public static final String DESTINATION_LAUNCH_PARAMS_KEY = "launch_params";
    public static final String DESTINATION_PKG_KEY = "package";
    private static final String EXTRAS_FIELD = "extra_data";
    private static HashSet<String> IMAGE_KEYS = new HashSet<>(Arrays.asList("imageUri", "sender_photo", "accepter_photo", "friend_photo", "application_photo", "user_photo"));
    private static final String MESSAGE_FIELD = "message";
    private static final String RENDERED_NOTIFICATION_FIELD = "rendered_notification";
    private static final String TAG = PersistedNotification.class.getSimpleName();
    private static final String TITLE_FIELD = "title";
    private static ArrayMap<String, Bitmap> mImageCache = new ArrayMap<>();
    private Drawable mIcon;
    private String mIconUri;
    private JSONObject mRaw;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public boolean isDismissable() {
        return false;
    }

    public PersistedNotification(JSONObject jSONObject) {
        this.mRaw = jSONObject;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public long getId() {
        try {
            return Long.parseLong(this.mRaw.getString("id"));
        } catch (JSONException e) {
            Log.e(TAG, "Unable to retrieve id from PersistedNotification", e);
            return 0;
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getFBID() {
        try {
            return this.mRaw.getString("id");
        } catch (JSONException e) {
            Log.e(TAG, "Unable to retrieve fbid from PersistedNotification", e);
            return "";
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getNDID() {
        try {
            return this.mRaw.getString("ndid");
        } catch (JSONException e) {
            Log.e(TAG, "Unable to retrieve ndid from PersistedNotification", e);
            return "";
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getNotificationType() {
        String str;
        try {
            str = this.mRaw.getString("notification_type");
        } catch (JSONException e) {
            Log.e(TAG, "Unable to retrieve notification_type from PersistedNotification", e);
            str = "";
        }
        try {
            str = this.mRaw.getString("xnotif_type");
        } catch (JSONException e2) {
            Log.e(TAG, "Unable to retrieve xnotif_type from PersistedNotification", e2);
        }
        if (TextUtils.isEmpty("")) {
            return str;
        }
        return "";
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public NotificationsType getCategory() {
        return getCategoryV2();
    }

    private NotificationsType getCategoryV2() {
        NotificationsType notificationsType;
        try {
            notificationsType = NotificationsType.mapFromUri(getRenderedNotification(this.mRaw).getString(CATEGORY_FIELD));
        } catch (JSONException e) {
            Log.e(TAG, "Unable to retrieve category from PersistedNotification", e);
            notificationsType = null;
        }
        return notificationsType != null ? notificationsType : NotificationsType.SOCIAL;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getTitle() {
        try {
            return getRenderedNotification(this.mRaw).getString("title");
        } catch (JSONException e) {
            Log.e(TAG, "Unable to retrieve title from PersistedNotification", e);
            return "";
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getShortText() {
        return getTitle();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getLongText() {
        try {
            return getRenderedNotification(this.mRaw).getString("message");
        } catch (JSONException e) {
            Log.e(TAG, "Unable to retrieve message from PersistedNotification", e);
            return "";
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public Drawable getPrimaryIcon(Context context) {
        Drawable drawable = this.mIcon;
        if (drawable != null) {
            return drawable;
        }
        if (mImageCache.get(getIconUri()) != null) {
            this.mIcon = getRoundDrawableFromBitmap(context, mImageCache.get(getIconUri()));
            return this.mIcon;
        }
        String iconUri = getIconUri();
        try {
            Bitmap bitmap = (Bitmap) new DownloadImageTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, iconUri).get();
            this.mIcon = getRoundDrawableFromBitmap(context, bitmap);
            mImageCache.put(getIconUri(), bitmap);
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException fetching icon", e);
        } catch (InterruptedException e2) {
            Log.e(TAG, "InterruptedException fetching icon", e2);
        } catch (NullPointerException e3) {
            Log.e(TAG, "NullPointerException fetching icon", e3);
        }
        if (this.mIcon == null) {
            this.mIcon = getRoundDrawableFromBitmap(context, ((BitmapDrawable) Icon.createWithResource(context.getPackageName(), R.drawable.ic_notifications_default).loadDrawable(context)).getBitmap());
        }
        return this.mIcon;
    }

    public String getIconUri() {
        if (TextUtils.isEmpty(this.mIconUri)) {
            String str = "";
            JSONObject notificationExtras = getNotificationExtras(getRenderedNotification(this.mRaw));
            try {
                Iterator<String> it = IMAGE_KEYS.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    if (notificationExtras.has(next)) {
                        str = notificationExtras.getString(next);
                        break;
                    }
                }
            } catch (JSONException e) {
                Log.e(TAG, "Error loading the appplication photo icon", e);
            }
            this.mIconUri = str;
        }
        return this.mIconUri;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public long getPostedTimeSeconds() {
        try {
            return Long.parseLong(this.mRaw.getString("send_time"));
        } catch (JSONException e) {
            Log.e(TAG, "Unable to retrieve posted_time from PersistedNotification", e);
            return 0;
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public Parcelable getPrimaryAction() {
        Uri parse = Uri.parse("systemux://notifications");
        try {
            Uri parse2 = Uri.parse(getRenderedNotification(this.mRaw).getString("deeplink_uri"));
            if (parse.getScheme().equals("systemux")) {
                parse = parse2;
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing deeplink URI from notif", e);
        }
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", baseUriFromFull(parse));
        intent.putExtra(AssistantComponentContract.Components.RemoteImageViewComponent.URI, uriExtrasFromFull(parse));
        return intent;
    }

    @Nullable
    public JSONObject getLaunchDestination() {
        try {
            return getRenderedNotification(this.mRaw).getJSONObject("destination");
        } catch (Exception e) {
            Log.e(TAG, "Could not launch the destination", e);
            return null;
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public Parcelable[] getAllActions() {
        return new Parcelable[]{getPrimaryAction()};
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    @Bindable
    public AbstractVRNotification.NotificationSeenState getSeenState() {
        if (this.mSeenState == null) {
            try {
                this.mSeenState = AbstractVRNotification.NotificationSeenState.valueOf(this.mRaw.getString("notification_seen_state"));
                return this.mSeenState;
            } catch (JSONException e) {
                Log.e(TAG, "Exception in retrieving notification read state", e);
                this.mSeenState = AbstractVRNotification.NotificationSeenState.UNSEEN_AND_UNREAD;
            }
        }
        return this.mSeenState;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public JSONObject getRaw() {
        return this.mRaw;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PersistedNotification) || getId() != ((PersistedNotification) obj).getId()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return String.format(Locale.ROOT, "PersistedNotification{id: %d}", Long.valueOf(getId()));
    }

    @Nullable
    public String getDeeplinkUri() {
        try {
            return getRenderedNotification(this.mRaw).getString("deeplink_uri");
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing deeplink URI from notif", e);
            return null;
        }
    }

    private static String baseUriFromFull(Uri uri) {
        return TextUtils.join("://", new String[]{uri.getScheme(), uri.getHost()});
    }

    private static Uri uriExtrasFromFull(Uri uri) {
        String str = null;
        String path = uri.getPath() != null ? uri.getPath() : null;
        if (uri.getQuery() != null && (str = uri.getQueryParameter(AssistantComponentContract.Components.RemoteImageViewComponent.URI)) == null) {
            str = uri.getQuery();
        }
        if (path == null || path.equals("/")) {
            if (("" + str) != null) {
                path = str;
            } else {
                path = "";
            }
        }
        if (path == null) {
            return Uri.parse("");
        }
        return Uri.parse(path);
    }

    private static JSONObject getRenderedNotification(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            return jSONObject.getJSONObject(RENDERED_NOTIFICATION_FIELD);
        } catch (JSONException e) {
            Log.e(TAG, "Exception retrieving rendered notifications", e);
            return jSONObject2;
        }
    }

    private static JSONObject getNotificationExtras(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            return (JSONObject) new JSONTokener(jSONObject.getString(EXTRAS_FIELD)).nextValue();
        } catch (JSONException e) {
            Log.e(TAG, "Exception retrieving notifications extras", e);
        } catch (ClassCastException e2) {
            Log.e(TAG, "Exception in retrieving notification extras as JSONObject", e2);
        }
        return jSONObject2;
    }
}
