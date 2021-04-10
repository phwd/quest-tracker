package X;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.1p6  reason: invalid class name and case insensitive filesystem */
public class View$OnLayoutChangeListenerC10681p6 implements View.OnLayoutChangeListener {
    public final /* synthetic */ SearchView A00;

    public View$OnLayoutChangeListenerC10681p6(SearchView searchView) {
        this.A00 = searchView;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.A00.adjustDropDownSizeAndPosition();
    }
}
