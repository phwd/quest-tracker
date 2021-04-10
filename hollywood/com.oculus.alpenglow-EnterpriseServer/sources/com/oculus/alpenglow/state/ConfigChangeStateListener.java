package com.oculus.alpenglow.state;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import android.app.job.JobService;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.ConfigChangeListener;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.logging.DeviceStateLogger;
import com.oculus.executors.OculusThreadExecutor;

@Dependencies({"_UL__ULSEP_com_oculus_alpenglow_state_DeviceStateManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_logging_DeviceStateLogger_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ConfigChangeStateListener implements ConfigChangeListener {
    public static final String TAG = "EnterpriseServer.ConfigChangeStateListener";
    public static volatile ConfigChangeStateListener _UL__ULSEP_com_oculus_alpenglow_state_ConfigChangeStateListener_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // com.oculus.alpenglow.config.ConfigChangeListener
    public final void A5z(Device device, Device device2) {
        ((DeviceStateManager) AnonymousClass0Lh.A03(0, 27, this._UL_mInjectionContext)).A00(null, ((DeviceStateLogger) AnonymousClass0Lh.A03(1, 130, this._UL_mInjectionContext)).A01("config changed")).executeOnExecutor(OculusThreadExecutor.A00(), new JobService[0]);
    }

    @Inject
    public ConfigChangeStateListener(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(2, r3);
    }
}
