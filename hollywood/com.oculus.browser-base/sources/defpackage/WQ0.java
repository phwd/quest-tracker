package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import com.oculus.browser.R;

/* renamed from: WQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WQ0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ SearchView F;

    public WQ0(SearchView searchView) {
        this.F = searchView;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9;
        int i10;
        SearchView searchView = this.F;
        if (searchView.g0.getWidth() > 1) {
            Resources resources = searchView.getContext().getResources();
            int paddingLeft = searchView.a0.getPaddingLeft();
            Rect rect = new Rect();
            boolean a2 = AbstractC4826sv1.a(searchView);
            if (searchView.u0) {
                i9 = resources.getDimensionPixelSize(R.dimen.f16070_resource_name_obfuscated_RES_2131165226) + resources.getDimensionPixelSize(R.dimen.f16060_resource_name_obfuscated_RES_2131165225);
            } else {
                i9 = 0;
            }
            searchView.V.getDropDownBackground().getPadding(rect);
            if (a2) {
                i10 = -rect.left;
            } else {
                i10 = paddingLeft - (rect.left + i9);
            }
            searchView.V.setDropDownHorizontalOffset(i10);
            searchView.V.setDropDownWidth((((searchView.g0.getWidth() + rect.left) + rect.right) + i9) - paddingLeft);
        }
    }
}
