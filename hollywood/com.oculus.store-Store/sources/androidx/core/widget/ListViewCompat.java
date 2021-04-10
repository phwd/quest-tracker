package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.NonNull;

public final class ListViewCompat {
    public static void scrollListBy(@NonNull ListView listView, int y) {
        View firstView;
        if (Build.VERSION.SDK_INT >= 19) {
            listView.scrollListBy(y);
            return;
        }
        int firstPosition = listView.getFirstVisiblePosition();
        if (firstPosition != -1 && (firstView = listView.getChildAt(0)) != null) {
            listView.setSelectionFromTop(firstPosition, firstView.getTop() - y);
        }
    }

    public static boolean canScrollList(@NonNull ListView listView, int direction) {
        if (Build.VERSION.SDK_INT >= 19) {
            return listView.canScrollList(direction);
        }
        int childCount = listView.getChildCount();
        if (childCount == 0) {
            return false;
        }
        int firstPosition = listView.getFirstVisiblePosition();
        if (direction > 0) {
            return firstPosition + childCount < listView.getCount() || listView.getChildAt(childCount + -1).getBottom() > listView.getHeight() - listView.getListPaddingBottom();
        }
        return firstPosition > 0 || listView.getChildAt(0).getTop() < listView.getListPaddingTop();
    }

    private ListViewCompat() {
    }
}
