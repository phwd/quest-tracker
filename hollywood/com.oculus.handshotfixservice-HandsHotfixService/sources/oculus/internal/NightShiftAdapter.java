package oculus.internal;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.util.Log;
import java.time.LocalTime;

public class NightShiftAdapter implements NightShiftAdapterInterface {
    private static final String TAG = "NightShiftAdapter";
    private Context mContext;
    private ColorDisplayManager mManager = new ColorDisplayManager();

    public NightShiftAdapter(Context context) {
        this.mContext = context;
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public boolean isActivated() {
        try {
            return this.mManager.isNightDisplayActivated();
        } catch (Exception e) {
            Log.e(TAG, "Failed to get Night Shift setting: " + e.getMessage());
            return false;
        }
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public boolean setActivated(boolean activated) {
        try {
            return this.mManager.setNightDisplayActivated(activated);
        } catch (Exception e) {
            Log.e(TAG, "Failed to set Night Shift setting: " + e.getMessage());
            return false;
        }
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public int getAutoMode() {
        return this.mManager.getNightDisplayAutoMode();
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public boolean setAutoMode(int autoMode) {
        return this.mManager.setNightDisplayAutoMode(autoMode);
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public int getCustomStartTime() {
        LocalTime time = this.mManager.getNightDisplayCustomStartTime();
        return (time.getHour() * 60) + time.getMinute();
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public boolean setCustomStartTime(int minuteOfDay) {
        return this.mManager.setNightDisplayCustomStartTime(LocalTime.of(minuteOfDay / 60, minuteOfDay % 60));
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public int getCustomEndTime() {
        LocalTime time = this.mManager.getNightDisplayCustomEndTime();
        return (time.getHour() * 60) + time.getMinute();
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public boolean setCustomEndTime(int minuteOfDay) {
        return this.mManager.setNightDisplayCustomEndTime(LocalTime.of(minuteOfDay / 60, minuteOfDay % 60));
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public int getMinimumColorTemperature() {
        ColorDisplayManager colorDisplayManager = this.mManager;
        return ColorDisplayManager.getMinimumColorTemperature(this.mContext);
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public int getMaximumColorTemperature() {
        ColorDisplayManager colorDisplayManager = this.mManager;
        return ColorDisplayManager.getMaximumColorTemperature(this.mContext);
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public int getColorTemperature() {
        return this.mManager.getNightDisplayColorTemperature();
    }

    @Override // oculus.internal.NightShiftAdapterInterface
    public boolean setColorTemperature(int temperature) {
        return this.mManager.setNightDisplayColorTemperature(temperature);
    }
}
