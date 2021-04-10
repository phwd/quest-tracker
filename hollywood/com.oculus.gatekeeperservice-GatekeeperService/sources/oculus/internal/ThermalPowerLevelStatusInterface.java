package oculus.internal;

public interface ThermalPowerLevelStatusInterface {

    public interface ThermalPowerLevelStatusUpdateCallback {
        void powerLevelStatusUpdate(int i);
    }

    boolean isSupported();

    void onDestroy();

    void registerCallback(ThermalPowerLevelStatusUpdateCallback thermalPowerLevelStatusUpdateCallback);
}
