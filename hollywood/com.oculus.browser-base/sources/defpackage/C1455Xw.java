package defpackage;

import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* renamed from: Xw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1455Xw extends AbstractC5583xM {
    public final float q;
    public List r = new ArrayList();
    public final Rect s = new Rect(0, 0, 1, 1);
    public final RectF t = new RectF();
    public final Rect u = new Rect();
    public final /* synthetic */ CompositorViewHolder v;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1455Xw(CompositorViewHolder compositorViewHolder, View view) {
        super(view);
        this.v = compositorViewHolder;
        this.q = compositorViewHolder.getContext().getResources().getDisplayMetrics().density;
    }

    @Override // defpackage.AbstractC5583xM
    public int o(float f, float f2) {
        if (this.r == null) {
            return Integer.MIN_VALUE;
        }
        for (int i = 0; i < this.r.size(); i++) {
            float f3 = this.q;
            if (((Bv1) this.r.get(i)).a(f / f3, f2 / f3)) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    @Override // defpackage.AbstractC5583xM
    public void p(List list) {
        if (this.v.K != null) {
            this.r.clear();
            D70 d70 = this.v.K;
            List list2 = this.r;
            AbstractC3838n70 n70 = (AbstractC3838n70) d70;
            for (int i = 0; i < n70.m0.size(); i++) {
                if (((LO0) n70.m0.get(i)).u()) {
                    ((LO0) n70.m0.get(i)).s(list2);
                }
            }
            for (int i2 = 0; i2 < this.r.size(); i2++) {
                list.add(Integer.valueOf(i2));
            }
        }
    }

    @Override // defpackage.AbstractC5583xM
    public boolean s(int i, int i2, Bundle bundle) {
        if (i2 != 16) {
            return false;
        }
        ((Bv1) this.r.get(i)).c(SystemClock.uptimeMillis());
        return true;
    }

    @Override // defpackage.AbstractC5583xM
    public void t(int i, AccessibilityEvent accessibilityEvent) {
        List list = this.r;
        if (list == null || list.size() <= i) {
            accessibilityEvent.setContentDescription("");
            return;
        }
        accessibilityEvent.setContentDescription(((Bv1) this.r.get(i)).b());
        accessibilityEvent.setClassName(CompositorViewHolder.class.getName());
    }

    @Override // defpackage.AbstractC5583xM
    public void v(int i, D d) {
        List list = this.r;
        if (list == null || list.size() <= i) {
            d.b.setBoundsInParent(this.s);
            d.b.setContentDescription("");
            return;
        }
        Bv1 bv1 = (Bv1) this.r.get(i);
        bv1.d(this.t);
        this.t.roundOut(this.u);
        Rect rect = this.u;
        float f = this.q;
        rect.left = (int) (((float) rect.left) * f);
        rect.top = (int) (((float) rect.top) * f);
        rect.right = (int) (((float) rect.right) * f);
        rect.bottom = (int) (((float) rect.bottom) * f);
        if (rect.width() == 0) {
            Rect rect2 = this.u;
            rect2.right = rect2.left + 1;
        }
        if (this.u.height() == 0) {
            Rect rect3 = this.u;
            rect3.bottom = rect3.top + 1;
        }
        d.b.setBoundsInParent(this.u);
        d.b.setContentDescription(bv1.b());
        d.b.addAction(16);
        d.b.addAction(1);
        d.b.addAction(32);
    }
}
