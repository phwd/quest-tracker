package com.oculus.headlesshorizon.remotelaunch;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass03h;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass1MZ;
import X.C02710bE;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.INeedInit;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.common.vrshell.Constants;
import com.oculus.horizon.R;
import com.oculus.horizon.api.graphql.GraphQLSocialQuery;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import com.oculus.horizon.notifications.legacy.contract.NotificationsContract;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
import com.oculus.horizon.remotelaunchlogger.RemoteLaunchLogger;
import com.oculus.horizon.social.SocialMethods;
import com.oculus.horizon.social.api.GetQueuedRemoteLaunchesResponse;
import com.oculus.horizon.social.request.GetQueuedRemoteLaunchesParams;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_social_SocialMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platformsdk_DeeplinkIntentUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_remotelaunchlogger_RemoteLaunchLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_notifications_core_NotificationBuilder_ULSEP_BINDING_ID"})
@ApplicationScoped
public class QueuedRemoteLaunchProcessor implements INeedInit {
    public static final String NOTIF_CATEGORY = "oculus_category";
    public static final String NOTIF_CATEGORY_SYSTEM = "system";
    public static final String NOTIF_PERSIST_FLAG = "vrshell_aui_persist";
    public static final String SOURCE_NOTIF = "queued_remote_launch_notification";
    public static final String SOURCE_WIFI = "queued_remote_launch_wifi_hook";
    public static final String TAG = "QueuedRemoteLaunchProcessor";
    public static volatile QueuedRemoteLaunchProcessor _UL__ULSEP_com_oculus_headlesshorizon_remotelaunch_QueuedRemoteLaunchProcessor_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Nullable
    public String mLastSuccessfulQueuedRemoteLaunchId = null;

    private void A01(String str) {
        String string;
        if (str == null || str.isEmpty()) {
            string = ((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).getString(R.string.notifications_remote_launch_failed_title_generic);
        } else {
            string = ((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).getString(R.string.notifications_remote_launch_failed_title_with_app, str);
        }
        A02(string, ((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).getString(R.string.notifications_remote_launch_failed_message));
    }

    private void A00() {
        A02(((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).getString(R.string.notifications_remote_launch_system_action_failed_title), ((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).getString(R.string.notifications_remote_launch_system_action_failed_message));
    }

    private void A02(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("vrshell_aui_persist", true);
        int incrementAndGet = ((NotificationBuilder) AnonymousClass0J2.A03(4, 14, this._UL_mInjectionContext)).mNextNotificationId.incrementAndGet();
        AnonymousClass03h r2 = new AnonymousClass03h((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext), NOTIF_CATEGORY_SYSTEM);
        r2.A0E = AnonymousClass03h.A00(str);
        r2.A0D = AnonymousClass03h.A00(str2);
        r2.A06 = 1;
        r2.A09.icon = R.drawable.status_icon;
        r2.A05(bundle);
        ((NotificationManager) ((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).getSystemService("notification")).notify(incrementAndGet, r2.A01());
    }

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        ((ConnectivityManager) ((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).getSystemService("connectivity")).registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
            /* class com.oculus.headlesshorizon.remotelaunch.QueuedRemoteLaunchProcessor.AnonymousClass1 */

            public final void onAvailable(Network network) {
                super.onAvailable(network);
                QueuedRemoteLaunchProcessor.this.A03(QueuedRemoteLaunchProcessor.SOURCE_WIFI, BuildSerialUtil.A00(), null);
            }
        });
    }

    @Inject
    public QueuedRemoteLaunchProcessor(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(5, r3);
    }

    public final synchronized void A03(String str, String str2, @Nullable String str3) {
        GetQueuedRemoteLaunchesResponse.Viewer viewer;
        GetQueuedRemoteLaunchesResponse.User user;
        Intent A01;
        RemoteLaunchLogger.Builder builder = new RemoteLaunchLogger.Builder();
        builder.source = str;
        builder.deviceSerial = str2;
        builder.queuedRemoteLaunchIdFromNotif = str3;
        try {
            GetQueuedRemoteLaunchesResponse queuedRemoteLaunchesSync = ((SocialMethods) AnonymousClass0J2.A03(1, 500, this._UL_mInjectionContext)).mMethods.getQueuedRemoteLaunchesSync(GraphQLSocialQuery.GET_QUEUED_REMOTE_LAUNCHES, new GetQueuedRemoteLaunchesParams(str2));
            if (queuedRemoteLaunchesSync == null || (viewer = queuedRemoteLaunchesSync.viewer) == null || (user = viewer.user) == null) {
                builder.errorReason = RemoteLaunchLogger.ERROR_REASON_GRAPHQL_ERROR;
                builder.errorMessage = "response or viewer or user was null";
                ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                AnonymousClass0NO.A08(TAG, "response or viewer or user was null");
            } else {
                GetQueuedRemoteLaunchesResponse.QueuedRemoteLaunch queuedRemoteLaunch = user.latest_queued_remote_launch;
                if (queuedRemoteLaunch == null) {
                    builder.errorReason = RemoteLaunchLogger.ERROR_REASON_POSSIBLY_EXPIRED_LAUNCH;
                    builder.errorMessage = "latest_queued_remote_launch was null";
                    ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                } else {
                    String str4 = queuedRemoteLaunch.id;
                    builder.processedQueuedRemoteLaunchId = str4;
                    String str5 = this.mLastSuccessfulQueuedRemoteLaunchId;
                    if (str5 == null || !str4.equals(str5)) {
                        GetQueuedRemoteLaunchesResponse.DeeplinkTarget deeplinkTarget = queuedRemoteLaunch.deeplink_target;
                        if (deeplinkTarget != null) {
                            String str6 = deeplinkTarget.system_route_params_mobile;
                            if (str6 == null) {
                                List<GetQueuedRemoteLaunchesResponse.VRAppsForDeeplinkTarget> list = deeplinkTarget.vr_apps_for_deeplink_target;
                                if (list != null) {
                                    String str7 = deeplinkTarget.launch_params_as_json_string;
                                    builder.deeplinkMessage = str7;
                                    Iterator<GetQueuedRemoteLaunchesResponse.VRAppsForDeeplinkTarget> it = list.iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            builder.errorReason = RemoteLaunchLogger.ERROR_REASON_LAUNCH_FAILED;
                                            builder.errorMessage = "No valid apps to launch with";
                                            ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                                            break;
                                        }
                                        GetQueuedRemoteLaunchesResponse.VRAppsForDeeplinkTarget next = it.next();
                                        String str8 = next.id;
                                        String str9 = next.package_name;
                                        if (str8 != null && str9 != null && (A01 = ((DeeplinkIntentUtils) AnonymousClass0J2.A03(2, 66, this._UL_mInjectionContext)).A01(str9, str7)) != null) {
                                            builder.appId = str8;
                                            try {
                                                AnonymousClass0QC r5 = this._UL_mInjectionContext;
                                                if (((DeeplinkIntentUtils) AnonymousClass0J2.A03(2, 66, r5)).A03(A01, (Context) AnonymousClass0J2.A03(0, 80, r5))) {
                                                    this.mLastSuccessfulQueuedRemoteLaunchId = str4;
                                                    RemoteLaunchLogger.A00((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext), RemoteLaunchLogger.EVENT_LAUNCH_SUCCESS, builder).A5L();
                                                } else {
                                                    A01(next.display_name);
                                                    builder.errorReason = RemoteLaunchLogger.ERROR_REASON_LAUNCH_FAILED;
                                                    builder.errorMessage = "Launch failed with result false";
                                                    ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                                                }
                                            } catch (SecurityException e) {
                                                A01(next.display_name);
                                                String message = e.getMessage();
                                                if (message == null) {
                                                    message = "";
                                                }
                                                builder.errorReason = RemoteLaunchLogger.ERROR_REASON_LAUNCH_FAILED;
                                                builder.errorMessage = AnonymousClass006.A05("Secure launch failed with exception: ", message);
                                                ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                                            }
                                        }
                                    }
                                } else {
                                    builder.errorReason = RemoteLaunchLogger.ERROR_REASON_POSSIBLY_EXPIRED_LAUNCH;
                                    builder.errorMessage = "No apps returned in query";
                                    ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                                }
                            } else if (str6.isEmpty()) {
                                builder.errorReason = RemoteLaunchLogger.ERROR_REASON_EMPTY_SYSTEM_ROUTE;
                                builder.errorMessage = "Empty system route provided forRemoteLaunchLogger a system action based deeplink target";
                                ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                            } else {
                                try {
                                    Intent intent = new Intent();
                                    intent.setPackage("com.oculus.vrshell");
                                    intent.setAction(Constants.ACTION_LAUNCH);
                                    JSONObject jSONObject = new JSONObject(str6);
                                    Iterator<String> keys = jSONObject.keys();
                                    while (keys.hasNext()) {
                                        String next2 = keys.next();
                                        String optString = jSONObject.optString(next2);
                                        if (optString != null) {
                                            intent.putExtra(next2, optString);
                                        }
                                    }
                                    try {
                                        Context context = (Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext);
                                        intent.getPackage();
                                        if (intent.getPackage() != null) {
                                            if (C02710bE.A00.contains(intent.getPackage()) && AnonymousClass1MZ.A00().A03(intent, context)) {
                                                this.mLastSuccessfulQueuedRemoteLaunchId = str4;
                                                RemoteLaunchLogger.A00((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext), RemoteLaunchLogger.EVENT_LAUNCH_SUCCESS, builder).A5L();
                                            }
                                        }
                                        A00();
                                        builder.errorReason = RemoteLaunchLogger.ERROR_REASON_LAUNCH_FAILED;
                                        builder.errorMessage = "System action launch failed with result false";
                                        ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                                    } catch (SecurityException e2) {
                                        A00();
                                        String message2 = e2.getMessage();
                                        if (message2 == null) {
                                            message2 = "";
                                        }
                                        builder.errorReason = RemoteLaunchLogger.ERROR_REASON_LAUNCH_FAILED;
                                        builder.errorMessage = AnonymousClass006.A05("Secure system action launch failed with exception: ", message2);
                                        ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                                    }
                                } catch (JSONException unused) {
                                    builder.errorReason = RemoteLaunchLogger.ERROR_REASON_LAUNCH_FAILED;
                                    builder.errorMessage = "Could not make system route intent";
                                    ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                                }
                            }
                        } else {
                            builder.errorReason = RemoteLaunchLogger.ERROR_REASON_POSSIBLY_EXPIRED_LAUNCH;
                            builder.errorMessage = "deeplink_target was null";
                            ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                        }
                    } else {
                        AnonymousClass0NO.A0E(TAG, "Not processing duplicate ID %s", str4);
                        builder.errorReason = RemoteLaunchLogger.ERROR_REASON_DUPLICATE_LAUNCH;
                        ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                    }
                }
            }
        } catch (Exception e3) {
            String message3 = e3.getMessage();
            if (message3 == null) {
                message3 = "";
            }
            builder.errorReason = RemoteLaunchLogger.ERROR_REASON_GRAPHQL_ERROR;
            builder.errorMessage = AnonymousClass006.A05("Error when fetching queued remote launch ", message3);
            ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
            AnonymousClass0NO.A0B(TAG, "Error when fetching queued remote launch", e3);
        }
    }
}
