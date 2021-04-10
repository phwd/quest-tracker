package com.oculus.alpenglow.state;

import X.C03170bk;
import com.oculus.alpenglow.graphql.calls.HWMOculusDevicePeripheralInput;
import com.oculus.os.Controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PeripheralStateCollector {
    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Lcom/oculus/os/Controllers;)Ljava/util/List<Lcom/oculus/alpenglow/graphql/calls/HWMOculusDevicePeripheralInput;>; */
    public static List A00(Controllers controllers) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        try {
            int[] deviceTypes = controllers.getDeviceTypes();
            Arrays.sort(deviceTypes);
            Controllers.VerifyConnectableResult[] verifyConnectable = controllers.verifyConnectable(deviceTypes, 0, (int) TimeUnit.DAYS.toSeconds(1));
            for (int i = 0; i < deviceTypes.length; i++) {
                HWMOculusDevicePeripheralInput hWMOculusDevicePeripheralInput = new HWMOculusDevicePeripheralInput();
                int i2 = deviceTypes[i];
                hWMOculusDevicePeripheralInput.A05("identifier", controllers.getPairedDevice(i2));
                C03170bk.A00(hWMOculusDevicePeripheralInput.A03(), "battery_level", Integer.valueOf(controllers.getBatteryLevel(i2)));
                if (!(verifyConnectable[i] == Controllers.VerifyConnectableResult.CONNECTED || verifyConnectable[i] == Controllers.VerifyConnectableResult.RECENTLY_CONNECTED || verifyConnectable[i] == Controllers.VerifyConnectableResult.UPDATING)) {
                    z = false;
                    if (verifyConnectable[i] != Controllers.VerifyConnectableResult.UPDATE_PENDING) {
                        C03170bk.A00(hWMOculusDevicePeripheralInput.A03(), "recently_connected", Boolean.valueOf(z));
                        arrayList.add(hWMOculusDevicePeripheralInput);
                    }
                }
                z = true;
                C03170bk.A00(hWMOculusDevicePeripheralInput.A03(), "recently_connected", Boolean.valueOf(z));
                arrayList.add(hWMOculusDevicePeripheralInput);
            }
        } catch (InterruptedException unused) {
        }
        return arrayList;
    }
}
