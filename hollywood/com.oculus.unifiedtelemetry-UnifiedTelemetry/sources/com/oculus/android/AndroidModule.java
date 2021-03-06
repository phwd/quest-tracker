package com.oculus.android;

import X.AbstractC0247Xu;
import X.C0515sp;
import X.I0;
import android.bluetooth.BluetoothAdapter;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public class AndroidModule extends I0 {

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForAndroidModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_android_app_job_JobScheduler_ULSEP_BINDING_ID = 2098;
        public static final int _UL__ULSEP_android_bluetooth_BluetoothAdapter_ULSEP_BINDING_ID = 84;
        public static final int _UL__ULSEP_android_bluetooth_BluetoothManager_ULSEP_BINDING_ID = 2137;
        public static final int _UL__ULSEP_android_bluetooth_le_BluetoothLeScanner_ULSEP_BINDING_ID = 2093;
    }

    @AutoGeneratedAccessMethod
    public static final BluetoothAdapter A01(AbstractC0247Xu xu) {
        return (BluetoothAdapter) C0515sp.A00(84, xu);
    }

    @AutoGeneratedFactoryMethod
    public static final BluetoothAdapter A00() {
        return BluetoothAdapter.getDefaultAdapter();
    }
}
