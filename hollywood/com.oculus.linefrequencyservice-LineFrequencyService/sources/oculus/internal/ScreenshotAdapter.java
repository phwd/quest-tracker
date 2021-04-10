package oculus.internal;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.SurfaceControl;

public class ScreenshotAdapter implements ScreenshotAdapterInterface {
    @Override // oculus.internal.ScreenshotAdapterInterface
    public Bitmap screenshot(int width, int height) {
        return SurfaceControl.screenshot(new Rect(), width, height, 0);
    }
}
