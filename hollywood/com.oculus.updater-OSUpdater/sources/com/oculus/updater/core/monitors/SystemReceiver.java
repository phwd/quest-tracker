package com.oculus.updater.core.monitors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.common.init.INeedInit;
import com.oculus.updater.core.os.contract.OSContractModule;
import com.oculus.updater.core.os.contract.OSUpdateServiceContract;

@Dependencies
@ApplicationScoped
public class SystemReceiver implements INeedInit {
    private static final String TAG = "SystemReceiver";
    private static volatile SystemReceiver _UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedFactoryMethod
    public static final SystemReceiver _UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_INSTANCE == null) {
            synchronized (SystemReceiver.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_INSTANCE = new SystemReceiver(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_INSTANCE;
    }

    @Inject
    public SystemReceiver(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
    }

    @Override // com.oculus.common.init.INeedInit
    public void init() {
        BLog.d(TAG, "SystemReceiver init called");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.app.action.SYSTEM_UPDATE_POLICY_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        ((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID, this._UL_mInjectionContext)).registerReceiver(new SystemBroadcastReceiver(), intentFilter);
    }

    static class SystemBroadcastReceiver extends BroadcastReceiver implements InjectableComponentWithoutContext {
        private InjectionContext _UL_mInjectionContext;

        SystemBroadcastReceiver() {
        }

        private static final void _UL_injectMe(Context context, SystemBroadcastReceiver systemBroadcastReceiver) {
            if (UL.USE_STATIC_DI) {
                _UL_staticInjectMe(FbInjector.get(context), systemBroadcastReceiver);
            } else {
                FbInjector.injectMe(SystemBroadcastReceiver.class, (InjectableComponentWithoutContext) systemBroadcastReceiver, context);
            }
        }

        public static final void _UL_staticInjectMe(InjectorLike injectorLike, SystemBroadcastReceiver systemBroadcastReceiver) {
            systemBroadcastReceiver._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                _UL_injectMe(context, this);
                BLog.d(SystemReceiver.TAG, "SystemBroadcastReceiver: onReceive action = %s", action);
                char c = 65535;
                switch (action.hashCode()) {
                    case -2128145023:
                        if (action.equals("android.intent.action.SCREEN_OFF")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -1538406691:
                        if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1454123155:
                        if (action.equals("android.intent.action.SCREEN_ON")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -1172645946:
                        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -343630553:
                        if (action.equals("android.net.wifi.STATE_CHANGE")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 348300185:
                        if (action.equals("android.app.action.SYSTEM_UPDATE_POLICY_CHANGED")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1947666138:
                        if (action.equals("android.intent.action.ACTION_SHUTDOWN")) {
                            c = 4;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        ((OSUpdateServiceContract) FbInjector.lazyInstance(0, OSContractModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifySystemEvent("system_battery_changed");
                        return;
                    case 1:
                        if (intent.getExtras() != null) {
                            ((OSUpdateServiceContract) FbInjector.lazyInstance(0, OSContractModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifySystemEvent("system_network_changed", intent.getExtras());
                            return;
                        } else {
                            ((OSUpdateServiceContract) FbInjector.lazyInstance(0, OSContractModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifySystemEvent("system_network_changed");
                            return;
                        }
                    case 2:
                        ((OSUpdateServiceContract) FbInjector.lazyInstance(0, OSContractModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifySystemEvent("system_connectivity_changed");
                        return;
                    case 3:
                        ((OSUpdateServiceContract) FbInjector.lazyInstance(0, OSContractModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifySystemEvent("system_update_policy_changed");
                        return;
                    case 4:
                        ((OSUpdateServiceContract) FbInjector.lazyInstance(0, OSContractModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifySystemEvent("system_shutdown");
                        return;
                    case 5:
                    case 6:
                        ((OSUpdateServiceContract) FbInjector.lazyInstance(0, OSContractModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifySystemEvent("system_interactive_changed");
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
