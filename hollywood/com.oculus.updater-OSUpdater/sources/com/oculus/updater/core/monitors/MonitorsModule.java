package com.oculus.updater.core.monitors;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;

@InjectorModule
public abstract class MonitorsModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(BatteryMonitor.class)));
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(CheckPeriodMonitor.class)));
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(ConditionManager.class)));
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(IdleMonitor.class)));
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(SystemReceiver.class)));
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(SystemUpdatePolicyMonitor.class)));
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(WifiMonitor.class)));
    }
}
