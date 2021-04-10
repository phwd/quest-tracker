package androidx.core.view;

import android.view.View;
import androidx.annotation.NonNull;

public class NestedScrollingParentHelper {
    private int mNestedScrollAxesNonTouch;
    private int mNestedScrollAxesTouch;

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i, int i2) {
        if (i2 == 1) {
            this.mNestedScrollAxesNonTouch = i;
        } else {
            this.mNestedScrollAxesTouch = i;
        }
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxesTouch | this.mNestedScrollAxesNonTouch;
    }

    public void onStopNestedScroll(@NonNull View view, int i) {
        if (i == 1) {
            this.mNestedScrollAxesNonTouch = 0;
        } else {
            this.mNestedScrollAxesTouch = 0;
        }
    }
}
