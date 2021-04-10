package com.oculus.android;

import android.app.job.JobScheduler;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import javax.inject.Provider;

public class AndroidModule extends AbstractLibraryModule {

    public static final class UL_id {
        public static final int $ul_$xXXandroid_app_job_JobScheduler$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_job_JobScheduler$xXXBINDING_ID : UL.id.dynamicId(Key.get(JobScheduler.class)));
        public static final int $ul_$xXXandroid_bluetooth_BluetoothAdapter$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_bluetooth_BluetoothAdapter$xXXBINDING_ID : UL.id.dynamicId(Key.get(BluetoothAdapter.class)));
        public static final int $ul_$xXXandroid_bluetooth_BluetoothManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_bluetooth_BluetoothManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(BluetoothManager.class)));
        public static final int $ul_$xXXandroid_bluetooth_le_BluetoothLeScanner$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_bluetooth_le_BluetoothLeScanner$xXXBINDING_ID : UL.id.dynamicId(Key.get(BluetoothLeScanner.class)));
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Candroid_app_job_JobScheduler$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXandroid_app_job_JobScheduler$xXXBINDING_ID, $ul_injector);
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Candroid_bluetooth_BluetoothAdapter$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXandroid_bluetooth_BluetoothAdapter$xXXBINDING_ID, $ul_injector);
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Candroid_bluetooth_BluetoothManager$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXandroid_bluetooth_BluetoothManager$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Candroid_app_job_JobScheduler$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXandroid_app_job_JobScheduler$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Candroid_bluetooth_BluetoothManager$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXandroid_bluetooth_BluetoothManager$xXXBINDING_ID, $ul_injector);
    }

    static class AutoGeneratedBindingsForAndroidModule {
        AutoGeneratedBindingsForAndroidModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(com.facebook.common.android.AndroidModule.class);
                binder.require(BundledAndroidModule.class);
                binder.bind(JobScheduler.class).toProvider(new JobSchedulerMethodAutoProvider());
                binder.bind(BluetoothAdapter.class).toProvider(new BluetoothAdapterMethodAutoProvider());
                binder.bind(BluetoothManager.class).toProvider(new BluetoothManagerMethodAutoProvider());
                binder.bind(BluetoothLeScanner.class).toProvider(new BluetoothLeScannerMethodAutoProvider());
            }
        }
    }

    public static final JobScheduler $ul_$xXXandroid_app_job_JobScheduler$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (JobScheduler) UL.factorymap.get(UL_id.$ul_$xXXandroid_app_job_JobScheduler$xXXBINDING_ID, $ul_injector);
    }

    public static final JobScheduler $ul_$xXXandroid_app_job_JobScheduler$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return provideJobScheduler(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXACCESS_METHOD($ul_injector));
    }

    public static final BluetoothAdapter $ul_$xXXandroid_bluetooth_BluetoothAdapter$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (BluetoothAdapter) UL.factorymap.get(UL_id.$ul_$xXXandroid_bluetooth_BluetoothAdapter$xXXBINDING_ID, $ul_injector);
    }

    public static final BluetoothAdapter $ul_$xXXandroid_bluetooth_BluetoothAdapter$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return provideBluetoothAdapter();
    }

    public static final BluetoothManager $ul_$xXXandroid_bluetooth_BluetoothManager$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (BluetoothManager) UL.factorymap.get(UL_id.$ul_$xXXandroid_bluetooth_BluetoothManager$xXXBINDING_ID, $ul_injector);
    }

    public static final BluetoothManager $ul_$xXXandroid_bluetooth_BluetoothManager$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return provideBluetoothManager(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD($ul_injector));
    }

    public static final BluetoothLeScanner $ul_$xXXandroid_bluetooth_le_BluetoothLeScanner$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (BluetoothLeScanner) UL.factorymap.get(UL_id.$ul_$xXXandroid_bluetooth_le_BluetoothLeScanner$xXXBINDING_ID, $ul_injector);
    }

    public static final BluetoothLeScanner $ul_$xXXandroid_bluetooth_le_BluetoothLeScanner$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return provideBluetoothLeScanner($ul_$xXXandroid_bluetooth_BluetoothAdapter$xXXACCESS_METHOD($ul_injector));
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Candroid_bluetooth_le_BluetoothLeScanner$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXandroid_bluetooth_le_BluetoothLeScanner$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Candroid_bluetooth_BluetoothAdapter$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXandroid_bluetooth_BluetoothAdapter$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Candroid_bluetooth_le_BluetoothLeScanner$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXandroid_bluetooth_le_BluetoothLeScanner$xXXBINDING_ID, $ul_injector);
    }

    static JobScheduler provideJobScheduler(@UnsafeContextInjection Context context) {
        return (JobScheduler) context.getSystemService("jobscheduler");
    }

    static BluetoothManager provideBluetoothManager(@ForAppContext Context context) {
        return (BluetoothManager) context.getSystemService("bluetooth");
    }

    static BluetoothAdapter provideBluetoothAdapter() {
        return BluetoothAdapter.getDefaultAdapter();
    }

    static BluetoothLeScanner provideBluetoothLeScanner(BluetoothAdapter adapter) {
        return adapter.getBluetoothLeScanner();
    }
}
