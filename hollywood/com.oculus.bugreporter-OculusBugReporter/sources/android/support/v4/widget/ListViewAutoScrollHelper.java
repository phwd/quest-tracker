package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.widget.ListView;

public class ListViewAutoScrollHelper extends AutoScrollHelper {
    private final ListView mTarget;

    public ListViewAutoScrollHelper(@NonNull ListView target) {
        super(target);
        this.mTarget = target;
    }

    @Override // android.support.v4.widget.AutoScrollHelper
    public void scrollTargetBy(int deltaX, int deltaY) {
        ListViewCompat.scrollListBy(this.mTarget, deltaY);
    }

    @Override // android.support.v4.widget.AutoScrollHelper
    public boolean canTargetScrollHorizontally(int direction) {
        return false;
    }

    @Override // android.support.v4.widget.AutoScrollHelper
    public boolean canTargetScrollVertically(int direction) {
        ListView target = this.mTarget;
        int itemCount = target.getCount();
        if (itemCount == 0) {
            return false;
        }
        int childCount = target.getChildCount();
        int firstPosition = target.getFirstVisiblePosition();
        int lastPosition = firstPosition + childCount;
        if (direction > 0) {
            if (lastPosition < itemCount || target.getChildAt(childCount - 1).getBottom() > target.getHeight()) {
                return true;
            }
            return false;
        } else if (direction >= 0) {
            return false;
        } else {
            if (firstPosition > 0 || target.getChildAt(0).getTop() < 0) {
                return true;
            }
            return false;
        }
    }
}
