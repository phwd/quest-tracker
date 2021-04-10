package defpackage;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;

/* renamed from: QI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QI0 extends EK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RI0 f8753a;

    public QI0(RI0 ri0) {
        this.f8753a = ri0;
    }

    @Override // defpackage.EK0
    public void f(Rect rect, int i, RecyclerView recyclerView) {
        rect.set(0, 0, 0, 0);
        AbstractC5750yK0 yk0 = recyclerView.T;
        int dimensionPixelSize = this.f8753a.f8825a.getResources().getDimensionPixelSize(R.dimen.f26730_resource_name_obfuscated_RES_2131166292);
        rect.left = dimensionPixelSize;
        if (i == yk0.b() - 1) {
            rect.right = dimensionPixelSize;
        }
    }
}
