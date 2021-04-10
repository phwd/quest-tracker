package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: Xt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractView$OnLayoutChangeListenerC1450Xt0 extends AbstractC1389Wt0 implements View.OnLayoutChangeListener {
    public boolean Y;

    public AbstractView$OnLayoutChangeListenerC1450Xt0(AbstractC0292Et0 et0, int i, int i2, Context context, ViewGroup viewGroup, IJ ij, int i3, int i4) {
        super(et0, i, i2, context, viewGroup, ij, i3, i4);
    }

    @Override // defpackage.AbstractC1389Wt0, defpackage.AbstractC3631lv1
    public void j() {
        super.j();
        this.L.addOnLayoutChangeListener(this);
    }

    public abstract TextView o();

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        TextView o = o();
        if (!this.Y && o != null) {
            this.Y = true;
            if (o.getPaint().measureText(o.getText().toString()) < ((float) o.getWidth()) * 0.5f) {
                o.setGravity(LocalizationUtils.isLayoutRtl() ? 5 : 3);
            }
        }
    }
}
