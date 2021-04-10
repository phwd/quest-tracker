package com.android.server.job;

import com.android.server.job.StateControllerProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface StateControllerProtoOrBuilder extends MessageLiteOrBuilder {
    StateControllerProto.BackgroundJobsController getBackground();

    StateControllerProto.BatteryController getBattery();

    StateControllerProto.ConnectivityController getConnectivity();

    StateControllerProto.ContentObserverController getContentObserver();

    StateControllerProto.ControllerCase getControllerCase();

    StateControllerProto.DeviceIdleJobsController getDeviceIdle();

    StateControllerProto.IdleController getIdle();

    StateControllerProto.QuotaController getQuota();

    StateControllerProto.StorageController getStorage();

    StateControllerProto.TimeController getTime();

    boolean hasBackground();

    boolean hasBattery();

    boolean hasConnectivity();

    boolean hasContentObserver();

    boolean hasDeviceIdle();

    boolean hasIdle();

    boolean hasQuota();

    boolean hasStorage();

    boolean hasTime();
}
