package com.oculus.companion.gattble.phonenotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.oculus.companion.gattble.bluetooth.BluetoothGattCharacteristicWriteOperation;
import com.oculus.companion.gattble.bluetooth.BluetoothServiceDelegate;
import com.oculus.companion.gattble.bluetooth.BluetoothServiceHandler;
import com.oculus.companion.gattble.phonenotifications.IOSAppAttributes;
import com.oculus.companion.gattble.phonenotifications.IOSNotification;
import com.oculus.companion.server.R;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import oculus.internal.NotificationBuilderCompat;
import oculus.internal.NotificationChannelCompat;
import org.json.JSONException;
import org.json.JSONObject;

public class IOSNotificationServiceHandler extends BluetoothServiceHandler implements SettingsObserverCallback {
    private static final String TAG = "IOSNotificationServiceHandler";
    private static final UUID UUID_CHARACTERISTIC_CONTROL_POINT = UUID.fromString("69D1D8F3-45E1-49A8-9821-9BBDFDAAD9D9");
    private static final UUID UUID_CHARACTERISTIC_DATA_SOURCE = UUID.fromString("22EAC6E9-24D6-4BB5-BE44-B36ACE7C7BFB");
    private static final UUID UUID_CHARACTERISTIC_NOTIFICATION_SOURCE = UUID.fromString("9FBF120D-6301-42D9-8C58-25E699A21DBD");
    public static final UUID UUID_SERVICE = UUID.fromString("7905F431-B5CE-4E99-A40F-4B1E122D00D0");
    private LinkedList<IOSAppAttributesQuery> mAppAttributesQueries = new LinkedList<>();
    private HashMap<String, String> mAppIdToNameMap;
    private Context mContext = null;
    private LinkedList<IOSNotification.Builder> mNotificationBuilders = new LinkedList<>();
    private int mPayloadFragmentExpectedForCommandID;
    private SettingsManager mSettingsManager;
    private Telemetry mTelemetry = null;

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothServiceHandler
    public void onCharacteristicRead(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
    }

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothServiceHandler
    public void onCharacteristicWrite(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
    }

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothServiceHandler
    public void onDescriptorRead(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
    }

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothServiceHandler
    public void onDescriptorWrite(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
    }

    public IOSNotificationServiceHandler(BluetoothServiceDelegate bluetoothServiceDelegate, Context context) {
        super(bluetoothServiceDelegate);
        Log.d(TAG, "Creating a notification service handler");
        this.mContext = context;
        this.mSettingsManager = new SettingsManager();
        this.mTelemetry = new Telemetry(context);
        this.mAppIdToNameMap = getAppIdToNameMapping(this.mSettingsManager);
        this.mPayloadFragmentExpectedForCommandID = -1;
        submitNewConnectionNotificationIfEnabled();
        this.mSettingsManager.registerSettingsObserver("phone_notification_enabled", this, new Handler(Looper.getMainLooper()));
    }

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothServiceHandler
    public void onDisconnect() {
        this.mSettingsManager.unregisterSettingsObserver("phone_notification_enabled", this);
    }

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothServiceHandler
    public UUID getServiceUUID() {
        return UUID_SERVICE;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.companion.gattble.bluetooth.BluetoothServiceHandler
    public List<UUID> getCharacteristicsToSubscribe() {
        return Arrays.asList(UUID_CHARACTERISTIC_NOTIFICATION_SOURCE, UUID_CHARACTERISTIC_DATA_SOURCE);
    }

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothServiceHandler
    public void onCharacteristicChanged(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        if (uuid.equals(UUID_CHARACTERISTIC_NOTIFICATION_SOURCE)) {
            processNotificationSourceCharacteristicChange(bluetoothGattCharacteristic);
        } else if (uuid.equals(UUID_CHARACTERISTIC_DATA_SOURCE)) {
            processDataSourceCharacteristicChange(bluetoothGattCharacteristic);
        } else {
            String str = TAG;
            Log.e(str, "Unrecognized characteristic change on " + uuid.toString());
        }
    }

    private void processNotificationSourceCharacteristicChange(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        try {
            IOSNotification.Builder builder = new IOSNotification.Builder(bluetoothGattCharacteristic.getValue());
            if (!builder.hasEventFlag(EventFlag.PRE_EXISTING)) {
                int i = AnonymousClass1.$SwitchMap$com$oculus$companion$gattble$phonenotifications$EventID[builder.getEventID().ordinal()];
                if (i == 1) {
                    this.mNotificationBuilders.addLast(builder);
                } else if (i != 2) {
                    return;
                }
                this.mBluetoothServiceDelegate.addBluetoothGattOperation(new BluetoothGattCharacteristicWriteOperation(UUID_SERVICE, UUID_CHARACTERISTIC_CONTROL_POINT, builder.buildGetNotificationAttributesCommand()));
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable to parse payload from Notification Source", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.companion.gattble.phonenotifications.IOSNotificationServiceHandler$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$companion$gattble$phonenotifications$EventID = new int[EventID.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.companion.gattble.phonenotifications.EventID[] r0 = com.oculus.companion.gattble.phonenotifications.EventID.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.companion.gattble.phonenotifications.IOSNotificationServiceHandler.AnonymousClass1.$SwitchMap$com$oculus$companion$gattble$phonenotifications$EventID = r0
                int[] r0 = com.oculus.companion.gattble.phonenotifications.IOSNotificationServiceHandler.AnonymousClass1.$SwitchMap$com$oculus$companion$gattble$phonenotifications$EventID     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.companion.gattble.phonenotifications.EventID r1 = com.oculus.companion.gattble.phonenotifications.EventID.NOTIFICATION_ADDED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.companion.gattble.phonenotifications.IOSNotificationServiceHandler.AnonymousClass1.$SwitchMap$com$oculus$companion$gattble$phonenotifications$EventID     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.companion.gattble.phonenotifications.EventID r1 = com.oculus.companion.gattble.phonenotifications.EventID.NOTIFICATION_MODIFIED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.companion.gattble.phonenotifications.IOSNotificationServiceHandler.AnonymousClass1.$SwitchMap$com$oculus$companion$gattble$phonenotifications$EventID     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.companion.gattble.phonenotifications.EventID r1 = com.oculus.companion.gattble.phonenotifications.EventID.NOTIFICATION_REMOVED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.companion.gattble.phonenotifications.IOSNotificationServiceHandler.AnonymousClass1.$SwitchMap$com$oculus$companion$gattble$phonenotifications$EventID     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.companion.gattble.phonenotifications.EventID r1 = com.oculus.companion.gattble.phonenotifications.EventID.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.gattble.phonenotifications.IOSNotificationServiceHandler.AnonymousClass1.<clinit>():void");
        }
    }

    private void processDataSourceCharacteristicChange(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (!this.mNotificationBuilders.isEmpty() || !this.mAppAttributesQueries.isEmpty()) {
            try {
                byte[] value = bluetoothGattCharacteristic.getValue();
                if (value.length == 0) {
                    Log.e(TAG, "No data in payload from Data Source");
                    return;
                }
                int readInt8 = ANCSByteArrayInputStream.readInt8(value);
                if (isPayloadForCommandID(readInt8, 0)) {
                    processCommandGetNotificationAttributes(value);
                } else if (isPayloadForCommandID(readInt8, 1)) {
                    processCommandGetAppAttributes(value);
                } else {
                    Log.e(TAG, "Unexpected commandID in payload from Data Source");
                }
            } catch (IOException e) {
                Log.e(TAG, "Unable to parse payload from Data Source", e);
            }
        } else {
            Log.w(TAG, "Unexpected Data Source characteristic change: Not expecting anything from Data Source");
        }
    }

    private boolean isPayloadForCommandID(int i, int i2) {
        int i3 = this.mPayloadFragmentExpectedForCommandID;
        return i3 == i2 || (i3 == -1 && i == i2);
    }

    private void processCommandGetNotificationAttributes(byte[] bArr) throws IOException {
        IOSNotification.Builder peek = this.mNotificationBuilders.peek();
        peek.appendDataSourcePayload(bArr);
        IOSNotification build = peek.build();
        boolean z = false;
        if (build == null) {
            this.mPayloadFragmentExpectedForCommandID = 0;
            return;
        }
        this.mNotificationBuilders.removeFirst();
        String str = build.appID;
        String str2 = this.mAppIdToNameMap.get(str);
        if (str2 == null) {
            Iterator<IOSAppAttributesQuery> it = this.mAppAttributesQueries.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                IOSAppAttributesQuery next = it.next();
                if (str.equals(next.iosAppAttributesBuilder.getAppID())) {
                    String str3 = TAG;
                    Log.i(str3, "Already requested app attributes for " + build.toString());
                    next.pendingNotifications.addLast(build);
                    z = true;
                    break;
                }
            }
            if (!z) {
                String str4 = TAG;
                Log.i(str4, "Requesting app attributes for " + build.toString());
                IOSAppAttributesQuery iOSAppAttributesQuery = new IOSAppAttributesQuery(new IOSAppAttributes.Builder(str));
                iOSAppAttributesQuery.pendingNotifications.addLast(build);
                this.mAppAttributesQueries.addLast(iOSAppAttributesQuery);
                this.mBluetoothServiceDelegate.addBluetoothGattOperation(new BluetoothGattCharacteristicWriteOperation(UUID_SERVICE, UUID_CHARACTERISTIC_CONTROL_POINT, iOSAppAttributesQuery.iosAppAttributesBuilder.buildGetNotificationAttributesCommand()));
            }
        } else {
            String str5 = TAG;
            Log.i(str5, "Received " + build.toString() + " for " + str2);
            if (shouldSubmitNotification(build, str2)) {
                this.mTelemetry.recordPhoneNotificationsReceivedEvent();
                submitNotification(build, str2);
            }
        }
        this.mPayloadFragmentExpectedForCommandID = -1;
    }

    private void processCommandGetAppAttributes(byte[] bArr) throws IOException {
        IOSAppAttributesQuery peek = this.mAppAttributesQueries.peek();
        IOSAppAttributes.Builder builder = peek.iosAppAttributesBuilder;
        builder.appendDataSourcePayload(bArr);
        IOSAppAttributes build = builder.build();
        if (build == null) {
            this.mPayloadFragmentExpectedForCommandID = 1;
            return;
        }
        this.mAppAttributesQueries.removeFirst();
        String str = build.appID;
        String str2 = build.displayName;
        this.mAppIdToNameMap.put(str, str2);
        Iterator<IOSNotification> it = peek.pendingNotifications.iterator();
        while (it.hasNext()) {
            IOSNotification next = it.next();
            String str3 = TAG;
            Log.i(str3, "Received " + next.toString() + " for " + str2);
            if (shouldSubmitNotification(next, str2)) {
                this.mTelemetry.recordPhoneNotificationsReceivedEvent();
                submitNotification(next, str2);
            }
        }
        this.mPayloadFragmentExpectedForCommandID = -1;
    }

    private void submitNewConnectionNotificationIfEnabled() {
        if (this.mSettingsManager.getBoolean("phone_notification_enabled", false)) {
            String packageName = this.mContext.getPackageName();
            Bundle bundle = new Bundle();
            bundle.putString("action_text", this.mContext.getString(R.string.phone_notification_ios_connected_cta));
            bundle.putString("large_image_type", "icon");
            Notification.Builder builder = new NotificationBuilderCompat().getBuilder(this.mContext, packageName);
            builder.setContentTitle(this.mContext.getString(R.string.phone_notification_ios_connected_title)).setContentText(this.mContext.getString(R.string.phone_notification_ios_connected_message)).setPriority(1).setSmallIcon(R.drawable.ic_phone_notification).addExtras(bundle);
            NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat();
            notificationChannelCompat.setupNotificationChannel(this.mContext, packageName, "phone_notifications_channel", "Phone notifications service");
            notificationChannelCompat.setChannelIdForNotification(packageName, builder);
            ((NotificationManager) this.mContext.getSystemService("notification")).notify(0, builder.build());
        }
    }

    public void onSettingChange(String str) {
        if ("phone_notification_enabled".equals(str)) {
            submitNewConnectionNotificationIfEnabled();
        }
    }

    private void submitNotification(IOSNotification iOSNotification, String str) {
        String packageName = this.mContext.getPackageName();
        Bundle bundle = new Bundle();
        bundle.putString("oculus_notification_type", "phone_notification");
        bundle.putString("phone_notif_app_name", str);
        bundle.putBoolean("vrshell_aui_persist", true);
        bundle.putString("large_image_type", "icon");
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", Uri.parse("systemux://notifications"));
        PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, 0, intent, 0);
        Notification.Builder builder = new NotificationBuilderCompat().getBuilder(this.mContext, packageName);
        builder.setContentTitle(iOSNotification.title).setContentText(iOSNotification.message).setPriority(1).setSmallIcon(R.drawable.ic_phone_notification).setContentIntent(broadcast).addExtras(bundle);
        NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat();
        notificationChannelCompat.setupNotificationChannel(this.mContext, packageName, "phone_notifications_channel", "Phone notifications service");
        notificationChannelCompat.setChannelIdForNotification(packageName, builder);
        ((NotificationManager) this.mContext.getSystemService("notification")).notify(iOSNotification.notificationUID, builder.build());
    }

    private boolean shouldSubmitNotification(IOSNotification iOSNotification, String str) {
        if (!this.mSettingsManager.getBoolean("phone_notification_enabled", false)) {
            PhoneNotificationEvent phoneNotificationEvent = PhoneNotificationEvent.PHONE_NOTIFICATIONS_DISABLED;
            String str2 = TAG;
            Log.v(str2, "Dropping phone notification for reason: " + phoneNotificationEvent.getDescription());
            this.mTelemetry.recordPhoneNotificationsDroppedEvent(phoneNotificationEvent);
            return false;
        } else if (doesNotificationPassAppFilter(iOSNotification, str)) {
            return true;
        } else {
            PhoneNotificationEvent phoneNotificationEvent2 = PhoneNotificationEvent.APPLICATION_DOES_NOT_PASS_FILTER;
            String str3 = TAG;
            Log.v(str3, "Dropping phone notification for reason: " + phoneNotificationEvent2.getDescription());
            this.mTelemetry.recordPhoneNotificationsDroppedEvent(phoneNotificationEvent2);
            return false;
        }
    }

    private boolean doesNotificationPassAppFilter(IOSNotification iOSNotification, String str) {
        String string = this.mSettingsManager.getString("phone_notification_apps", "");
        if (string.equals("")) {
            Log.e(TAG, "There was an error reading phone notification app filtering settings from SettingsManager. Returning");
            return false;
        }
        new JSONObject();
        try {
            JSONObject jSONObject = new JSONObject(string);
            try {
                if (!jSONObject.has(iOSNotification.appID)) {
                    boolean z = this.mSettingsManager.getBoolean("phone_notification_allow_all_apps", false);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("enabled", z);
                    jSONObject2.put("name", str);
                    jSONObject.put(iOSNotification.appID, jSONObject2);
                    this.mSettingsManager.setString("phone_notification_apps", jSONObject.toString());
                }
            } catch (JSONException e) {
                String str2 = TAG;
                Log.w(str2, "Error while handling an incoming notification from an unknown app: " + e.toString());
            }
            try {
                return jSONObject.getJSONObject(iOSNotification.appID).getBoolean("enabled");
            } catch (JSONException unused) {
                jSONObject.remove(iOSNotification.appID);
                this.mSettingsManager.setString("phone_notification_apps", jSONObject.toString());
                Log.w(TAG, String.format("%s not in OSSettings PHONE_NOTIFICATION_APPS filter object.", iOSNotification.appID));
                return false;
            }
        } catch (JSONException e2) {
            String str3 = TAG;
            Log.e(str3, "Error parsing PHONE_NOTIFICATION_APPS setting string: " + e2.getMessage());
            return false;
        }
    }

    private static HashMap<String, String> getAppIdToNameMapping(SettingsManager settingsManager) {
        HashMap<String, String> hashMap = new HashMap<>();
        String string = settingsManager.getString("phone_notification_apps", "");
        if (string.equals("")) {
            return hashMap;
        }
        new JSONObject();
        try {
            JSONObject jSONObject = new JSONObject(string);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(next);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("name");
                    if (!optString.equals("")) {
                        hashMap.put(next, optString);
                    }
                }
            }
            return hashMap;
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "Error parsing PHONE_NOTIFICATION_APPS setting string: " + e.getMessage());
            return hashMap;
        }
    }

    /* access modifiers changed from: private */
    public static class IOSAppAttributesQuery {
        final IOSAppAttributes.Builder iosAppAttributesBuilder;
        final LinkedList<IOSNotification> pendingNotifications = new LinkedList<>();

        IOSAppAttributesQuery(IOSAppAttributes.Builder builder) {
            this.iosAppAttributesBuilder = builder;
        }
    }
}
