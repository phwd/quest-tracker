package com.oculus.panelapp.dialog.commonsystemdialogs.localstream;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.R;
import com.oculus.vrcast.VrCastController;
import com.oculus.vrshell.util.CallerInfoHelper;

public final class LocalStreamUtils {
    private static final String HORIZON_CAST_TO_BROWSER_KEY = "cast_option_www";
    private static final String HORIZON_COMPONENT_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String HORIZON_DEVICE_ID_KEY = "deviceid";
    private static final String HORIZON_MESSAGE_TYPE_KEY = "message_type";
    private static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String HORIZON_START_LOCAL_STREAM_ACTION = "com.oculus.horizon.START_LOCAL_STREAM";
    private static final String HORIZON_STOP_LOCAL_STREAM_ACTION = "com.oculus.horizon.STOP_LOCAL_STREAM";
    private static final String HORIZON_VRCAST_START_FROM_AUI_ACTION = "com.oculus.horizon.VRCAST_START_FROM_AUI";
    private static final String TAG = LoggingUtil.tag(LocalStreamUtils.class);

    public static void broadcastLocalStreamStopIntent(Context context) {
        context.startService(createLocalStreamIntent(context, HORIZON_STOP_LOCAL_STREAM_ACTION, ":stopCasting()"));
    }

    public static void startCastingToTwilight(Context context) {
        Log.i(TAG, "startCastingToTwilight()");
        context.startService(createLocalStreamIntent(context, HORIZON_START_LOCAL_STREAM_ACTION, ":startTwilightCasting()"));
    }

    public static void startCastingToBrowser(Context context) {
        Log.i(TAG, "startCastingToBrowser()");
        Intent createLocalStreamIntent = createLocalStreamIntent(context, HORIZON_START_LOCAL_STREAM_ACTION, ":startBrowserCasting()");
        createLocalStreamIntent.putExtra(HORIZON_CAST_TO_BROWSER_KEY, true);
        context.startService(createLocalStreamIntent);
    }

    public static void startCastingToDevice(Context context, String str) {
        Log.i(TAG, String.format("startCastingToDevice(%s)", str));
        Intent createLocalStreamIntent = createLocalStreamIntent(context, HORIZON_VRCAST_START_FROM_AUI_ACTION, ":startDeviceCasting()");
        createLocalStreamIntent.putExtra(HORIZON_DEVICE_ID_KEY, str);
        context.startService(createLocalStreamIntent);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamUtils$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrcast$VrCastController$DeviceType = new int[VrCastController.DeviceType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.vrcast.VrCastController$DeviceType[] r0 = com.oculus.vrcast.VrCastController.DeviceType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamUtils.AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$DeviceType = r0
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamUtils.AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$DeviceType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrcast.VrCastController$DeviceType r1 = com.oculus.vrcast.VrCastController.DeviceType.Chromecast     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamUtils.AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$DeviceType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrcast.VrCastController$DeviceType r1 = com.oculus.vrcast.VrCastController.DeviceType.Miracast     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamUtils.AnonymousClass1.<clinit>():void");
        }
    }

    public static String getIconAssetPathForDevice(VrCastController.VrShellCastDevice vrShellCastDevice) {
        return AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$DeviceType[vrShellCastDevice.type.ordinal()] != 1 ? "apk://com.oculus.systemux/assets/oc_icon_headset_casting_filled_24_dadada.png" : "apk://com.oculus.systemux/assets/ic_chromecast.png";
    }

    public static String getDeviceTypeText(VrCastController.VrShellCastDevice vrShellCastDevice, Resources resources) {
        if (AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$DeviceType[vrShellCastDevice.type.ordinal()] != 1) {
            return resources.getString(R.string.local_stream_dialog_device_type_miracast);
        }
        return resources.getString(R.string.local_stream_dialog_device_type_chromecast);
    }

    private static Intent createLocalStreamIntent(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", HORIZON_COMPONENT_NAME));
        intent.putExtra(HORIZON_MESSAGE_TYPE_KEY, str);
        CallerInfoHelper.attachCallerInfo(intent, context, LocalStreamUtils.class.getSimpleName() + str2);
        return intent;
    }
}
