package com.oculus.horizon.service_media.vrcast;

import android.os.Bundle;
import com.oculus.horizon.service_media.contract.OVRMediaServiceContract;
import com.oculus.vrcast.VrCastController;
import java.util.ArrayList;
import java.util.List;

public class VrCastBundleConverter {
    public static Bundle deviceWithStatusToBundle(VrCastController.VrShellCastDevice vrShellCastDevice) {
        Bundle bundle = new Bundle();
        bundle.putString(OVRMediaServiceContract.KEY_DEVICE_ID, vrShellCastDevice.id);
        bundle.putString(OVRMediaServiceContract.KEY_DEVICE_NAME, vrShellCastDevice.name);
        bundle.putInt(OVRMediaServiceContract.KEY_DEVICE_TYPE, vrShellCastDevice.type.ordinal());
        bundle.putInt(OVRMediaServiceContract.KEY_DEVICE_STATUS, vrShellCastDevice.state.ordinal());
        return bundle;
    }

    public static Bundle devicesToBundle(List<VrCastController.VrShellCastDevice> list) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(OVRMediaServiceContract.KEY_CAST_DEVICE_LIST, toBundleList(list));
        return bundle;
    }

    public static ArrayList<Bundle> toBundleList(List<VrCastController.VrShellCastDevice> list) {
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        for (VrCastController.VrShellCastDevice vrShellCastDevice : list) {
            Bundle bundle = new Bundle();
            bundle.putString(OVRMediaServiceContract.KEY_DEVICE_ID, vrShellCastDevice.id);
            bundle.putString(OVRMediaServiceContract.KEY_DEVICE_NAME, vrShellCastDevice.name);
            bundle.putInt(OVRMediaServiceContract.KEY_DEVICE_TYPE, vrShellCastDevice.type.ordinal());
            arrayList.add(bundle);
        }
        return arrayList;
    }
}
