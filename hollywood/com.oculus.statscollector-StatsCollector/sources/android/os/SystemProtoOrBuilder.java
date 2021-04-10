package android.os;

import android.os.SystemProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface SystemProtoOrBuilder extends MessageLiteOrBuilder {
    SystemProto.Battery getBattery();

    SystemProto.BatteryDischarge getBatteryDischarge();

    SystemProto.BatteryLevelStep getChargeStep(int i);

    int getChargeStepCount();

    List<SystemProto.BatteryLevelStep> getChargeStepList();

    long getChargeTimeRemainingMs();

    long getCpuFrequency(int i);

    int getCpuFrequencyCount();

    List<Long> getCpuFrequencyList();

    SystemProto.DataConnection getDataConnection(int i);

    int getDataConnectionCount();

    List<SystemProto.DataConnection> getDataConnectionList();

    SystemProto.BatteryLevelStep getDischargeStep(int i);

    int getDischargeStepCount();

    List<SystemProto.BatteryLevelStep> getDischargeStepList();

    long getDischargeTimeRemainingMs();

    ControllerActivityProto getGlobalBluetoothController();

    ControllerActivityProto getGlobalModemController();

    SystemProto.GlobalNetwork getGlobalNetwork();

    SystemProto.GlobalWifi getGlobalWifi();

    ControllerActivityProto getGlobalWifiController();

    SystemProto.KernelWakelock getKernelWakelock(int i);

    int getKernelWakelockCount();

    List<SystemProto.KernelWakelock> getKernelWakelockList();

    SystemProto.Misc getMisc();

    SystemProto.PhoneSignalStrength getPhoneSignalStrength(int i);

    int getPhoneSignalStrengthCount();

    List<SystemProto.PhoneSignalStrength> getPhoneSignalStrengthList();

    SystemProto.PowerUseItem getPowerUseItem(int i);

    int getPowerUseItemCount();

    List<SystemProto.PowerUseItem> getPowerUseItemList();

    SystemProto.PowerUseSummary getPowerUseSummary();

    SystemProto.ResourcePowerManager getResourcePowerManager(int i);

    int getResourcePowerManagerCount();

    List<SystemProto.ResourcePowerManager> getResourcePowerManagerList();

    SystemProto.ScreenBrightness getScreenBrightness(int i);

    int getScreenBrightnessCount();

    List<SystemProto.ScreenBrightness> getScreenBrightnessList();

    TimerProto getSignalScanning();

    SystemProto.TimeRemainingCase getTimeRemainingCase();

    SystemProto.WakeupReason getWakeupReason(int i);

    int getWakeupReasonCount();

    List<SystemProto.WakeupReason> getWakeupReasonList();

    SystemProto.WifiMulticastWakelockTotal getWifiMulticastWakelockTotal();

    SystemProto.WifiSignalStrength getWifiSignalStrength(int i);

    int getWifiSignalStrengthCount();

    List<SystemProto.WifiSignalStrength> getWifiSignalStrengthList();

    SystemProto.WifiState getWifiState(int i);

    int getWifiStateCount();

    List<SystemProto.WifiState> getWifiStateList();

    SystemProto.WifiSupplicantState getWifiSupplicantState(int i);

    int getWifiSupplicantStateCount();

    List<SystemProto.WifiSupplicantState> getWifiSupplicantStateList();

    boolean hasBattery();

    boolean hasBatteryDischarge();

    boolean hasChargeTimeRemainingMs();

    boolean hasDischargeTimeRemainingMs();

    boolean hasGlobalBluetoothController();

    boolean hasGlobalModemController();

    boolean hasGlobalNetwork();

    boolean hasGlobalWifi();

    boolean hasGlobalWifiController();

    boolean hasMisc();

    boolean hasPowerUseSummary();

    boolean hasSignalScanning();

    boolean hasWifiMulticastWakelockTotal();
}
