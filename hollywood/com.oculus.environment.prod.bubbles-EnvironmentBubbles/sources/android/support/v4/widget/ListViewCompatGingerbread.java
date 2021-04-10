package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

/* access modifiers changed from: package-private */
public class ListViewCompatGingerbread {
    ListViewCompatGingerbread() {
    }

    static void scrollListBy(ListView listView, int i) {
        View childAt;
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (firstVisiblePosition != -1 && (childAt = listView.getChildAt(0)) != null) {
            listView.setSelectionFromTop(firstVisiblePosition, childAt.getTop() - i);
        }
    }
}
