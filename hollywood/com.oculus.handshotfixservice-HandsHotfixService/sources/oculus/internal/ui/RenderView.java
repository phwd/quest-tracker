package oculus.internal.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import junit.framework.Assert;

/* access modifiers changed from: package-private */
public class RenderView {
    private static final String TAG = "RenderView";

    RenderView() {
    }

    public static Rect getBoundsForView(View view, Context context) {
        if (view.isAttachedToWindow()) {
            Assert.assertTrue(view.getDisplay() != null);
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            measureLargest(view, new Rect(0, 0, metrics.widthPixels, metrics.heightPixels));
            int[] locationInWindowCoords = new int[2];
            view.getLocationInWindow(locationInWindowCoords);
            int left = locationInWindowCoords[0];
            int top = locationInWindowCoords[1];
            return new Rect(left, top, view.getMeasuredWidth() + left, view.getMeasuredHeight() + top);
        }
        Log.e(TAG, "Tried to get bounds on view that isn't attached to the window");
        return new Rect();
    }

    private static void measureLargest(View view, Rect displaySize) {
        int widthSpecUnspecified = View.MeasureSpec.makeMeasureSpec(displaySize.width(), 0);
        int heightSpecUnspecified = View.MeasureSpec.makeMeasureSpec(displaySize.height(), 0);
        int widthSpecAtMost = View.MeasureSpec.makeMeasureSpec(displaySize.width(), Integer.MIN_VALUE);
        int heightSpecAtMost = View.MeasureSpec.makeMeasureSpec(displaySize.height(), Integer.MIN_VALUE);
        view.measure(widthSpecUnspecified, heightSpecUnspecified);
        int unspecWidth = view.getMeasuredWidth();
        int unspecHeight = view.getMeasuredHeight();
        view.measure(widthSpecAtMost, heightSpecAtMost);
        if (unspecWidth > view.getMeasuredWidth() || unspecHeight > view.getMeasuredHeight()) {
            view.measure(view.getMeasuredWidth() > unspecWidth ? widthSpecAtMost : widthSpecUnspecified, view.getMeasuredHeight() > unspecHeight ? heightSpecAtMost : heightSpecUnspecified);
        }
    }
}
