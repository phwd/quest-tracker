package com.oculus.headlesshorizon.notifications.receivers;

import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import X.C03030bw;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.oculus.common.vrshell.Constants;
import com.oculus.horizon.notifications.core.NotificationsProperties;
import com.oculus.horizon.notifications.core.SocialNotificationType;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
import com.oculus.horizon.push.PushTokenSharedPreferencesHelper;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import org.json.JSONException;
import org.json.JSONObject;

public class AUINotificationReceiver extends BroadcastReceiver implements AnonymousClass0QB {
    public static final String DESTINATION_ERROR_EVENT = "oculus_hmd_notification_destination_launch_failed";
    public static final String DESTINATION_SUCCESS_EVENT = "oculus_hmd_notification_destination_launched";
    public static final String EXTRA_DEEPLINK = "deeplink";
    public static final String EXTRA_LAUNCH_PARAMS = "launch_params";
    public static final String EXTRA_NOTIFICATION_ID = "notification_id";
    public static final String EXTRA_NOTIFICATION_NDID = "notification_ndid";
    public static final String EXTRA_NOTIFICATION_TYPE = "notification_type";
    public static final String EXTRA_PACKAGE = "package";
    public static final String FEED_DESTINATION_ACTION = "com.oculus.horizon.AUI_NOTIFICATION_DESTINATION_LAUNCH";
    public static final HashSet<String> MIGRATED_NOTIF_TYPES = new HashSet<>(Arrays.asList(SocialNotificationType.FRIEND_REQUEST_RECEIVED.graphQLName, SocialNotificationType.FRIEND_REQUEST_ACCEPTED.graphQLName, SocialNotificationType.ACHIEVEMENT_UNLOCKED.graphQLName));
    public static final String TAG = "AUINotificationReceiver";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static Intent A00(Uri uri) {
        String str;
        Uri parse;
        Intent intent = new Intent(Constants.ACTION_LAUNCH);
        intent.setPackage("com.oculus.vrshell");
        if (!"dialog".equals(uri.getHost())) {
            intent.putExtra("intent_data", TextUtils.join("://", new String[]{uri.getScheme(), uri.getHost()}));
            String str2 = null;
            if (uri.getPath() != null) {
                str = uri.getPath();
            } else {
                str = null;
            }
            if (uri.getQuery() != null && (str2 = uri.getQueryParameter("uri")) == null) {
                str2 = uri.getQuery();
            }
            if (str == null || str.equals("/")) {
                str = "";
                if (AnonymousClass006.A05("", str2) != null) {
                    str = str2;
                    if (str2 == null) {
                        parse = Uri.parse("");
                    }
                }
            }
            parse = Uri.parse(str);
        } else if (Arrays.asList("social-confirm-join-party", "create-vr-invite", "social-party-privacy").contains(uri.getHost())) {
            intent.putExtra("intent_data", uri.buildUpon().clearQuery().build().toString());
            if (!TextUtils.isEmpty(uri.getQuery())) {
                parse = C03030bw.A00(uri.getQuery());
            }
            return intent;
        } else {
            intent.putExtra("intent_data", uri.toString());
            return intent;
        }
        intent.putExtra("uri", parse);
        return intent;
    }

    private Event A01(Exception exc, Context context, @Nullable String str) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A22(DESTINATION_ERROR_EVENT);
        A22.A14("push_token_id", PushTokenSharedPreferencesHelper.A00(context));
        A22.A15("exception_type", exc.getClass().toString());
        if (TextUtils.isEmpty(str)) {
            str = "No Deeplink Specified";
        }
        A22.A15("fallback_deeplink", str);
        if (exc.getMessage() != null) {
            A22.A15("exception", exc.getMessage());
        }
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        A22.A15("stack_trace", stringWriter.toString());
        return A22;
    }

    private void A02(Context context, String str, @Nullable String str2) throws DestinationLaunchException {
        String str3;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || packageManager.getLaunchIntentForPackage(str) == null) {
            str3 = "No package exists for intended destination";
        } else {
            Intent A02 = ((DeeplinkIntentUtils) AnonymousClass0J2.A03(2, 66, this._UL_mInjectionContext)).A02(str, str2);
            if (A02 == null) {
                str3 = AnonymousClass006.A05("No destination launch intent found for ", str);
            } else if (!((DeeplinkIntentUtils) AnonymousClass0J2.A03(2, 66, this._UL_mInjectionContext)).A03(A02, context)) {
                str3 = "Destination did not launch appropriately";
            } else {
                return;
            }
        }
        throw new DestinationLaunchException(str3);
    }

    public static void A03(Event event, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        if (!TextUtils.isEmpty(str)) {
            event.A15("destination", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            event.A15("notification_id", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            event.A15("notification_ndid", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            event.A15("notification_type", str4);
        }
    }

    public final void onReceive(Context context, Intent intent) {
        Uri uri;
        Event event;
        String str;
        String str2;
        String str3;
        this._UL_mInjectionContext = new AnonymousClass0QC(3, AnonymousClass0J2.get(context));
        if (!TextUtils.isEmpty(intent.getAction()) && intent.getExtras() != null) {
            if (FEED_DESTINATION_ACTION.equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                String string = extras.getString("package");
                String string2 = extras.getString(EXTRA_LAUNCH_PARAMS);
                String string3 = extras.getString("deeplink");
                String string4 = extras.getString("notification_id", "NONE");
                String string5 = extras.getString("notification_ndid", "NONE");
                String string6 = extras.getString("notification_type", "NONE");
                try {
                    str3 = new JSONObject().put("package", string).put(EXTRA_LAUNCH_PARAMS, string2).toString();
                } catch (JSONException e) {
                    AnonymousClass0NO.A0B(TAG, "unable to reconstruct destination", e);
                    str3 = null;
                }
                try {
                    if (!TextUtils.isEmpty(string)) {
                        A02(context, string, string2);
                        Event A22 = ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A22(DESTINATION_SUCCESS_EVENT);
                        A22.A14("push_token_id", PushTokenSharedPreferencesHelper.A00(context));
                        A03(A22, str3, string4, string5, string6);
                        A22.A5L();
                        return;
                    }
                    throw new DestinationLaunchException("Package for destination was passed as null");
                } catch (Exception e2) {
                    Event A01 = A01(e2, context, str3);
                    A01.A15("item_type", "feed_item");
                    A01.A5L();
                    AnonymousClass0NO.A0B(TAG, "exception launching destination", e2);
                    if (TextUtils.isEmpty(string3)) {
                        event = A01(new DestinationLaunchException("No backup deeplink found"), context, str3);
                        event.A15("item_type", "feed_item");
                        event.A5L();
                        return;
                    }
                    uri = C03030bw.A00(string3);
                }
            } else {
                NotificationsProperties notificationsProperties = (NotificationsProperties) intent.getParcelableExtra(NotificationsProperties.NOTIFICATION_PROPERTIES);
                if (notificationsProperties != null) {
                    notificationsProperties.A05();
                    if (!TextUtils.isEmpty(notificationsProperties.A05().optString("package", ""))) {
                        try {
                            String string7 = notificationsProperties.A05().getString("package");
                            String string8 = notificationsProperties.A05().getString(EXTRA_LAUNCH_PARAMS);
                            PackageManager packageManager = context.getPackageManager();
                            if (packageManager == null || packageManager.getLaunchIntentForPackage(string7) == null) {
                                throw new DestinationLaunchException("No package exists for intended destination");
                            }
                            A02(context, string7, string8);
                            Event A222 = ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A22(DESTINATION_SUCCESS_EVENT);
                            A222.A14("push_token_id", PushTokenSharedPreferencesHelper.A00(context));
                            A222.A15("item_type", "push_item");
                            A03(A222, notificationsProperties.mDestination, notificationsProperties.mFBid, notificationsProperties.mNDID, notificationsProperties.mType);
                            A222.A5L();
                        } catch (Exception e3) {
                            if (TextUtils.isEmpty(notificationsProperties.mDeeplink)) {
                                str = null;
                            } else {
                                str = notificationsProperties.mDeeplink;
                            }
                            Event A012 = A01(e3, context, str);
                            A012.A15("item_type", "push_item");
                            A03(A012, notificationsProperties.mDestination, notificationsProperties.mFBid, notificationsProperties.mNDID, notificationsProperties.mType);
                            A012.A5L();
                            AnonymousClass0NO.A0B(TAG, "Exception in trying to launch destination", e3);
                            if (notificationsProperties.A00() == null) {
                                DestinationLaunchException destinationLaunchException = new DestinationLaunchException("No backup deeplink found");
                                if (TextUtils.isEmpty(notificationsProperties.mDeeplink)) {
                                    str2 = null;
                                } else {
                                    str2 = notificationsProperties.mDeeplink;
                                }
                                event = A01(destinationLaunchException, context, str2);
                                event.A5L();
                                return;
                            }
                            context.sendBroadcast(A00(notificationsProperties.A00()));
                            notificationsProperties.A00();
                        }
                    } else {
                        uri = notificationsProperties.A00();
                        if (uri == null) {
                            return;
                        }
                        context.sendBroadcast(A00(uri));
                    }
                }
            }
        }
    }

    public static class DestinationLaunchException extends Exception {
        public DestinationLaunchException(String str) {
            super(str);
        }
    }
}
