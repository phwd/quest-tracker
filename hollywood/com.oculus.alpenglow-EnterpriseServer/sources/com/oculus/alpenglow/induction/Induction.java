package com.oculus.alpenglow.induction;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.C04910hv;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.ConfigurationStore;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.constants.Constants;
import com.oculus.alpenglow.logging.ConfigLogger;
import com.oculus.alpenglow.logging.LoggerConstants;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.os.SettingsManager;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_config_ConfigurationStore_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_secure_context_SecureContextHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_logging_ConfigLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_induction_WifiProvisioning_ULSEP_BINDING_ID"})
@ApplicationScoped
public class Induction {
    public static final String TAG = "EnterpriseServer.Induction";
    public static volatile Induction _UL__ULSEP_com_oculus_alpenglow_induction_Induction_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;

    public final CompletableFuture<Device> A00() {
        if (((SettingsManager) AnonymousClass0Lh.A03(1, 105, this._UL_mInjectionContext)).getInt("managed_device", -1) == 0) {
            CompletableFuture<Device> completableFuture = new CompletableFuture<>();
            completableFuture.completeExceptionally(new RuntimeException("Enterprise mode not enabled"));
            return completableFuture;
        }
        if (((SettingsManager) AnonymousClass0Lh.A03(1, 105, this._UL_mInjectionContext)).getInt("managed_device", -1) == -1 && (!FirstTimeNuxManager.getFirstTimeNuxPreOtaComplete())) {
            ((WifiProvisioning) AnonymousClass0Lh.A03(5, 87, this._UL_mInjectionContext)).A00();
        }
        return ((ConfigurationStore) AnonymousClass0Lh.A03(2, 97, this._UL_mInjectionContext)).A01(((ConfigLogger) AnonymousClass0Lh.A03(4, 45, this._UL_mInjectionContext)).A01(LoggerConstants.CONFIGURATION_FETCH_REASON_INDUCTION, LoggerConstants.CONFIGURATION_FETCH_TYPE_SILENT), new ConfigurationStore.RetryCallback() {
            /* class com.oculus.alpenglow.induction.$$Lambda$Induction$Le4m5G0QlWi4pQ6BDbaTsj5Wh3k2 */

            @Override // com.oculus.alpenglow.config.ConfigurationStore.RetryCallback
            public final void A60() {
                Induction.this.A01();
            }
        }).thenApply((Function<? super Device, ? extends U>) new Function() {
            /* class com.oculus.alpenglow.induction.$$Lambda$Induction$YJdms9dXyCoi9BHvLciw2iNyOQ2 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Device device = (Device) obj;
                Induction.this.A02(device);
                return device;
            }
        });
    }

    public final /* synthetic */ void A01() {
        if (((SettingsManager) AnonymousClass0Lh.A03(1, 105, this._UL_mInjectionContext)).getInt("managed_device", -1) == -1 && (!FirstTimeNuxManager.getFirstTimeNuxPreOtaComplete())) {
            ((WifiProvisioning) AnonymousClass0Lh.A03(5, 87, this._UL_mInjectionContext)).A00();
        }
    }

    public final /* synthetic */ void A02(Device device) {
        SettingsManager settingsManager = (SettingsManager) AnonymousClass0Lh.A03(1, 105, this._UL_mInjectionContext);
        int i = settingsManager.getInt("managed_device", -1);
        int i2 = 2;
        if (i == -1) {
            if (device != null) {
                boolean A3p = device.A3p();
                if (!A3p) {
                    if (!(!FirstTimeNuxManager.getFirstTimeNuxPreOtaComplete())) {
                        i2 = 0;
                    }
                }
                settingsManager.setInt("managed_device", i2);
                if (!A3p) {
                    WifiManager wifiManager = (WifiManager) ((Context) AnonymousClass0Lh.A03(0, 4, ((WifiProvisioning) AnonymousClass0Lh.A03(5, 87, this._UL_mInjectionContext))._UL_mInjectionContext)).getSystemService(WifiManager.class);
                    if (wifiManager == null) {
                        AnonymousClass0NK.A01(WifiProvisioning.TAG, "could not access wifi manager");
                    } else {
                        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
                        if (configuredNetworks != null) {
                            Optional<WifiConfiguration> findFirst = configuredNetworks.stream().filter($$Lambda$WifiProvisioning$HBImXg6ECWUu7m0Gk60rWYWG8mA2.INSTANCE).findFirst();
                            if (findFirst.isPresent()) {
                                wifiManager.removeNetwork(findFirst.get().networkId);
                            }
                        }
                    }
                    if (!FirstTimeNuxManager.getFirstTimeNuxPreOtaComplete()) {
                        return;
                    }
                } else if (!FirstTimeNuxManager.getFirstTimeNuxPreOtaComplete()) {
                    Intent intent = new Intent();
                    intent.setComponent(ComponentName.createRelative("com.oculus.companion.server", Constants.COMPANION_SERVER_SERVICE));
                    intent.setAction(Constants.COMPANION_ACTION_OKAY_TO_REBOOT);
                    ((C04910hv) AnonymousClass0Lh.A03(3, 15, this._UL_mInjectionContext)).A03().A00(intent, (Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext));
                    return;
                } else {
                    return;
                }
            }
            AnonymousClass0NK.A01(TAG, "Unable to determine whether device is managed");
            return;
        } else if (i != 0) {
            return;
        }
        throw new RuntimeException("Enterprise mode not enabled");
    }

    @Inject
    public Induction(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(6, r3);
    }
}
