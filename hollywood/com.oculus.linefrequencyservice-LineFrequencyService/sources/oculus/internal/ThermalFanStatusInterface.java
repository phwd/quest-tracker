package oculus.internal;

public interface ThermalFanStatusInterface {

    public interface ThermalFanStatusUpdateCallback {
        void fanStatusUpdate(boolean z);
    }

    void onDestroy();

    void registerCallback(ThermalFanStatusUpdateCallback thermalFanStatusUpdateCallback);
}
