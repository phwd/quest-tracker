package com.oculus.xrinput.client;

import android.content.Context;
import android.hardware.input.InputManager;
import android.util.Log;
import android.view.InputDevice;
import com.oculus.vrapi.ContextHelper;
import java.util.ArrayList;

public class GamepadListener implements InputManager.InputDeviceListener {
    private static final String TAG = "GamepadListener";
    public static GamepadListener gamePadListener = null;
    private ArrayList<Integer> gamePadDeviceIds = new ArrayList<>();

    private static native void nativeGamepadAdded(int i, int i2);

    private static native void nativeGamepadRemoved(int i);

    private static boolean IsRemote(InputDevice device) {
        return device.getSources() == 16778001;
    }

    private static boolean IsGamepad(InputDevice device) {
        if (IsRemote(device)) {
            return false;
        }
        if ((device.getSources() & 1025) == 1025 || (device.getSources() & 16777232) == 16777232) {
            return true;
        }
        return false;
    }

    private void addGamepad(int deviceId, int productId) {
        if (!this.gamePadDeviceIds.contains(Integer.valueOf(deviceId))) {
            this.gamePadDeviceIds.add(new Integer(deviceId));
            nativeGamepadAdded(deviceId, productId);
        }
    }

    private void removeGamepad(int deviceId) {
        if (this.gamePadDeviceIds.contains(Integer.valueOf(deviceId))) {
            this.gamePadDeviceIds.remove(new Integer(deviceId));
            nativeGamepadRemoved(deviceId);
        }
    }

    private static void startListener(final Context context) {
        if (gamePadListener == null) {
            gamePadListener = new GamepadListener();
        }
        int[] deviceIds = ((InputManager) context.getSystemService("input")).getInputDeviceIds();
        for (int i = 0; i < deviceIds.length; i++) {
            InputDevice inputDevice = InputDevice.getDevice(deviceIds[i]);
            if (inputDevice != null && IsGamepad(inputDevice)) {
                Log.d(TAG, "Adding Device: " + inputDevice.getName() + " productId: " + inputDevice.getProductId() + " sources: " + inputDevice.getSources());
                gamePadListener.addGamepad(deviceIds[i], inputDevice.getProductId());
            }
        }
        ContextHelper.runOnUiThread(context, new Runnable() {
            /* class com.oculus.xrinput.client.GamepadListener.AnonymousClass1 */

            public void run() {
                ((InputManager) context.getSystemService("input")).registerInputDeviceListener(GamepadListener.gamePadListener, null);
            }
        });
    }

    private static void stopListener(final Context context) {
        ContextHelper.runOnUiThread(context, new Runnable() {
            /* class com.oculus.xrinput.client.GamepadListener.AnonymousClass2 */

            public void run() {
                ((InputManager) context.getSystemService("input")).unregisterInputDeviceListener(GamepadListener.gamePadListener);
            }
        });
        gamePadListener.gamePadDeviceIds.clear();
    }

    public void onInputDeviceChanged(int deviceId) {
    }

    public void onInputDeviceAdded(int deviceId) {
        InputDevice inputDevice = InputDevice.getDevice(deviceId);
        if (inputDevice != null && IsGamepad(inputDevice)) {
            Log.d(TAG, "Adding Device: " + inputDevice.getName() + " pid: " + inputDevice.getProductId() + " sources: " + inputDevice.getSources());
            addGamepad(deviceId, inputDevice.getProductId());
        }
    }

    public void onInputDeviceRemoved(int deviceId) {
        Log.d(TAG, "Removing Device: " + deviceId);
        removeGamepad(deviceId);
    }
}
