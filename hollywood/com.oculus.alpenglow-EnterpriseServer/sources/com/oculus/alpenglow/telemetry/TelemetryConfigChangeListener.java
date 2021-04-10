package com.oculus.alpenglow.telemetry;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.ConfigChangeListener;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSConfigTelemetryState;
import com.oculus.os.SettingsManager;
import com.oculus.os.Version;

@Dependencies({"_UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID"})
@ApplicationScoped
public class TelemetryConfigChangeListener implements ConfigChangeListener {
    public static final String TAG = "EnterpriseServer.TelemetryConfigChangeListener";
    public static volatile TelemetryConfigChangeListener _UL__ULSEP_com_oculus_alpenglow_telemetry_TelemetryConfigChangeListener_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // com.oculus.alpenglow.config.ConfigChangeListener
    public final void A5z(Device device, Device device2) {
        Device.ManagementInfo A3y;
        Device.ManagementInfo.DeviceConfig A3Q;
        Device.ManagementInfo.DeviceConfig.OsConfig A4D;
        GraphQLHWMOculusOSConfigTelemetryState graphQLHWMOculusOSConfigTelemetryState = null;
        if (!(device2 == null || (A3y = device2.A3y()) == null || (A3Q = A3y.A3Q()) == null || (A4D = A3Q.A4D()) == null)) {
            graphQLHWMOculusOSConfigTelemetryState = A4D.A4d();
        }
        if (graphQLHWMOculusOSConfigTelemetryState == GraphQLHWMOculusOSConfigTelemetryState.UNSET_OR_UNRECOGNIZED_ENUM_VALUE) {
            AnonymousClass0NK.A06(TAG, "Bad telemetry state in config, value = (%s)", graphQLHWMOculusOSConfigTelemetryState);
        } else if (Version.CURRENT_SDK_VERSION < 20) {
            AnonymousClass0NK.A01(TAG, "Cannot set telemetry state setting, sdk version below 20 not supported.");
        } else if (graphQLHWMOculusOSConfigTelemetryState != null) {
            ((SettingsManager) AnonymousClass0Lh.A03(0, 105, this._UL_mInjectionContext)).setString("telemetry_state", graphQLHWMOculusOSConfigTelemetryState.toString());
        }
    }

    @Inject
    public TelemetryConfigChangeListener(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
