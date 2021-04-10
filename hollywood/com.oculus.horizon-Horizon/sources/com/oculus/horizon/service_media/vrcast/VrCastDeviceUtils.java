package com.oculus.horizon.service_media.vrcast;

import com.oculus.vrcast.VrCastController;

public class VrCastDeviceUtils {
    public static boolean isConnecting(VrCastController.VrShellCastDevice vrShellCastDevice) {
        if (vrShellCastDevice != null) {
            return vrShellCastDevice.state == VrCastController.VrShellCastDevice.State.CONNECTING_TO_PEER || vrShellCastDevice.state == VrCastController.VrShellCastDevice.State.CONNECTION_INITIATED || vrShellCastDevice.state == VrCastController.VrShellCastDevice.State.CONNECTION_SUCCESS || vrShellCastDevice.state == VrCastController.VrShellCastDevice.State.STARTING_SESSION;
        }
        return false;
    }

    public static boolean isCasting(VrCastController.VrShellCastDevice vrShellCastDevice) {
        if (vrShellCastDevice == null || vrShellCastDevice.state != VrCastController.VrShellCastDevice.State.CASTING) {
            return false;
        }
        return true;
    }
}
