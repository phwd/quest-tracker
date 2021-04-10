package oculus.internal;

public interface NightShiftAdapterInterface {
    int getAutoMode();

    int getColorTemperature();

    int getCustomEndTime();

    int getCustomStartTime();

    int getMaximumColorTemperature();

    int getMinimumColorTemperature();

    boolean isActivated();

    boolean setActivated(boolean z);

    boolean setAutoMode(int i);

    boolean setColorTemperature(int i);

    boolean setCustomEndTime(int i);

    boolean setCustomStartTime(int i);
}
