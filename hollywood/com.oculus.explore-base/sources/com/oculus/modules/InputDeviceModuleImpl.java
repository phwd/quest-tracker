package com.oculus.modules;

import android.view.InputDevice;
import com.oculus.modules.codegen.InputDeviceModule;
import com.oculus.modules.codegen.Resolver;
import java.util.ArrayList;
import java.util.List;

public class InputDeviceModuleImpl extends InputDeviceModule {
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.InputDeviceModule
    public void getGameControllerIdListImpl(Resolver<List<String>> resolver) {
        int[] deviceIds = InputDevice.getDeviceIds();
        List<String> list = new ArrayList<>();
        for (int deviceId : deviceIds) {
            int sources = InputDevice.getDevice(deviceId).getSources();
            if ((sources & 1025) == 1025 || (sources & 16777232) == 16777232) {
                list.add(Integer.toString(deviceId));
            }
        }
        resolver.resolve(list);
    }
}
