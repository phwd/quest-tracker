package oculus.internal;

import android.content.Context;
import android.os.PowerManager;

public class BrightnessControl {
    private PowerManager pm;

    public BrightnessControl(Context context) {
        this.pm = (PowerManager) context.getSystemService("power");
    }

    public void setBacklightBrightness(int brightness) {
    }
}
