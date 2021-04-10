package com.oculus.vrshell;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.vrshell.util.BluetoothUtils;
import java.util.HashMap;
import java.util.Map;

public abstract class ShellBaseBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = LoggingUtil.tag(ShellBaseBroadcastReceiver.class);

    /* access modifiers changed from: protected */
    public void broadcastIntentToShellEnvOverlayService(Context context, Intent intent) {
        Intent intent2 = new Intent(intent);
        intent2.setClass(context, ShellEnvOverlayService.class);
        intent2.setAction(ShellEnvOverlayService.INTENT_SHELL_ENVIRONMENT_BROADCAST_INTENT);
        intent2.putExtra(ShellEnvOverlayService.INTENT_SHELL_ENVIRONMENT_BROADCAST_INTENT_ACTION, intent.getAction());
        Map<String, String> prepareInitialEnvironmentFor = prepareInitialEnvironmentFor(context, intent);
        for (String str : prepareInitialEnvironmentFor.keySet()) {
            intent2.putExtra(str, prepareInitialEnvironmentFor.get(str));
        }
        context.startService(intent2);
    }

    /* access modifiers changed from: protected */
    public Map<String, String> prepareInitialEnvironmentFor(Context context, Intent intent) {
        int i;
        String str;
        HashMap hashMap = new HashMap();
        if (intent.getAction().equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
            Log.d(TAG, "Received BOND_STATE_CHANGED event");
            Bundle extras = intent.getExtras();
            if (extras == null) {
                i = -1;
            } else {
                i = extras.getInt("android.bluetooth.device.extra.BOND_STATE", -1);
            }
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice == null) {
                str = "<null>";
            } else {
                str = bluetoothDevice.getName();
            }
            Log.d(TAG, "Bluetooth bond state changed to " + i + " for " + str);
            String bluetoothBondStateToString = BluetoothUtils.bluetoothBondStateToString(bluetoothDevice.getBondState());
            if (bluetoothBondStateToString == null) {
                Log.d(TAG, "Unhandled bond state: " + i);
            }
            if (!(bluetoothBondStateToString == null || i == -1 || bluetoothDevice == null)) {
                hashMap.put("bond_state", bluetoothBondStateToString);
                addBluetoothDeviceDetailsToEnvironment(context, intent, hashMap, bluetoothDevice);
            }
        } else if (intent.getAction().equals("android.bluetooth.device.action.ACL_CONNECTED") || intent.getAction().equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice2 != null) {
                addBluetoothDeviceDetailsToEnvironment(context, intent, hashMap, bluetoothDevice2);
            }
        } else if (intent.getAction().equals("com.oculus.vrshell.intent.action.RENDER_KEYBOARD_START") || intent.getAction().equals("com.oculus.vrshell.intent.action.RENDER_KEYBOARD_STOP")) {
            hashMap.put("hardwareId", intent.getStringExtra("hardwareId"));
        }
        return hashMap;
    }

    private static void addBluetoothDeviceDetailsToEnvironment(Context context, Intent intent, Map<String, String> map, BluetoothDevice bluetoothDevice) {
        String name = bluetoothDevice.getName();
        String str = "<unknown>";
        if (TextUtils.isEmpty(name)) {
            Log.d(TAG, "Device name is null, passing intent data with the name field set to 'unknown'");
            name = str;
        }
        BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
        if (bluetoothClass == null) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_vrshell_btclass_error");
            String address = bluetoothDevice.getAddress();
            String bluetoothDeviceTypeToString = BluetoothUtils.bluetoothDeviceTypeToString(bluetoothDevice.getType());
            String bluetoothBondStateToString = BluetoothUtils.bluetoothBondStateToString(bluetoothDevice.getBondState());
            if (address != null) {
                str = address;
            }
            analyticsEvent.setExtra("bluetooth_device_address", str);
            analyticsEvent.setExtra("intent_action", intent.getAction());
            analyticsEvent.setExtra("bluetooth_device_name", name);
            if (bluetoothDeviceTypeToString == null) {
                bluetoothDeviceTypeToString = "Invalid device type " + bluetoothDevice.getType();
            }
            analyticsEvent.setExtra("bluetooth_device_type", bluetoothDeviceTypeToString);
            if (bluetoothBondStateToString == null) {
                bluetoothBondStateToString = "Invalid bond state " + bluetoothDevice.getBondState();
            }
            analyticsEvent.setExtra("bluetooth_device_bondstate", bluetoothBondStateToString);
            UnifiedTelemetryLogger.getInstance(context).reportEvent(analyticsEvent, false);
            Log.w(TAG, "Null bluetooth class for device " + name + " (" + address + "), skipping adding this device to environment");
            return;
        }
        String num = Integer.toString(bluetoothClass.getDeviceClass());
        String num2 = Integer.toString(bluetoothClass.getMajorDeviceClass());
        String num3 = Integer.toString(bluetoothDevice.getType());
        Log.d(TAG, "Bluetooth device has properties:  name=" + name + "  device_class=" + num + "  major_device_class=" + num2 + "  type=" + num3);
        map.put(ServiceContract.EXTRA_NAME, name);
        map.put("device_class", num);
        map.put("major_device_class", num2);
        map.put(DialogDefinitionBase.DIALOG_TYPE_KEY, num3);
    }
}
