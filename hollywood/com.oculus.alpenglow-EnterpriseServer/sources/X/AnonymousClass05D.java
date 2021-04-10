package X;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.05D  reason: invalid class name */
public class AnonymousClass05D implements View.OnLayoutChangeListener {
    public final /* synthetic */ SearchView A00;

    public AnonymousClass05D(SearchView searchView) {
        this.A00 = searchView;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.A00.adjustDropDownSizeAndPosition();
    }
}
