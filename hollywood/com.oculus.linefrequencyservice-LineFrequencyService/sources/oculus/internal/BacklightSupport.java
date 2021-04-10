package oculus.internal;

import android.content.Context;
import android.hardware.display.DisplayManager;

public class BacklightSupport implements BacklightSupportInterface {
    private Context mContext;

    public BacklightSupport(Context context) {
        this.mContext = context;
    }

    @Override // oculus.internal.BacklightSupportInterface
    public void setTemporaryBrightness(int brightness) {
        ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).setTemporaryBrightness(brightness);
    }
}
