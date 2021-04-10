package com.android.internal.os;

import com.android.internal.os.PowerProfileProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PowerProfileProtoOrBuilder extends MessageLiteOrBuilder {
    double getAmbientDisplay();

    double getAudio();

    double getBatteryCapacity();

    double getBluetoothActive();

    double getBluetoothAtCmd();

    double getBluetoothControllerIdle();

    double getBluetoothControllerOperatingVoltage();

    double getBluetoothControllerRx();

    double getBluetoothControllerTx();

    double getBluetoothOn();

    double getCamera();

    double getCpuActive();

    PowerProfileProto.CpuCluster getCpuCluster(int i);

    int getCpuClusterCount();

    List<PowerProfileProto.CpuCluster> getCpuClusterList();

    double getCpuIdle();

    double getCpuSuspend();

    double getFlashlight();

    double getGpsOn();

    double getGpsOperatingVoltage();

    double getGpsSignalQualityBased(int i);

    int getGpsSignalQualityBasedCount();

    List<Double> getGpsSignalQualityBasedList();

    double getMemory();

    double getModemControllerIdle();

    double getModemControllerOperatingVoltage();

    double getModemControllerRx();

    double getModemControllerSleep();

    double getModemControllerTx(int i);

    int getModemControllerTxCount();

    List<Double> getModemControllerTxList();

    double getRadioActive();

    double getRadioOn();

    double getRadioScanning();

    double getScreenFull();

    double getScreenOn();

    double getVideo();

    double getWifiActive();

    double getWifiBatchedScan();

    double getWifiControllerIdle();

    double getWifiControllerOperatingVoltage();

    double getWifiControllerRx();

    double getWifiControllerTx();

    double getWifiControllerTxLevels(int i);

    int getWifiControllerTxLevelsCount();

    List<Double> getWifiControllerTxLevelsList();

    double getWifiOn();

    double getWifiScan();

    boolean hasAmbientDisplay();

    boolean hasAudio();

    boolean hasBatteryCapacity();

    boolean hasBluetoothActive();

    boolean hasBluetoothAtCmd();

    boolean hasBluetoothControllerIdle();

    boolean hasBluetoothControllerOperatingVoltage();

    boolean hasBluetoothControllerRx();

    boolean hasBluetoothControllerTx();

    boolean hasBluetoothOn();

    boolean hasCamera();

    boolean hasCpuActive();

    boolean hasCpuIdle();

    boolean hasCpuSuspend();

    boolean hasFlashlight();

    boolean hasGpsOn();

    boolean hasGpsOperatingVoltage();

    boolean hasMemory();

    boolean hasModemControllerIdle();

    boolean hasModemControllerOperatingVoltage();

    boolean hasModemControllerRx();

    boolean hasModemControllerSleep();

    boolean hasRadioActive();

    boolean hasRadioOn();

    boolean hasRadioScanning();

    boolean hasScreenFull();

    boolean hasScreenOn();

    boolean hasVideo();

    boolean hasWifiActive();

    boolean hasWifiBatchedScan();

    boolean hasWifiControllerIdle();

    boolean hasWifiControllerOperatingVoltage();

    boolean hasWifiControllerRx();

    boolean hasWifiControllerTx();

    boolean hasWifiOn();

    boolean hasWifiScan();
}
