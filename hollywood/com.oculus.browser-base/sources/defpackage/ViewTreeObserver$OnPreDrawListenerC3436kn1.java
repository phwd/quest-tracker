package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: kn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnPreDrawListenerC3436kn1 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public AbstractC2924hn1 F;
    public ViewGroup G;

    public ViewTreeObserver$OnPreDrawListenerC3436kn1(AbstractC2924hn1 hn1, ViewGroup viewGroup) {
        this.F = hn1;
        this.G = viewGroup;
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x0242  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01ef A[EDGE_INSN: B:133:0x01ef->B:88:0x01ef ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0215  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreDraw() {
        /*
        // Method dump skipped, instructions count: 692
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ViewTreeObserver$OnPreDrawListenerC3436kn1.onPreDraw():boolean");
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        this.G.getViewTreeObserver().removeOnPreDrawListener(this);
        this.G.removeOnAttachStateChangeListener(this);
        AbstractC3607ln1.c.remove(this.G);
        ArrayList arrayList = (ArrayList) AbstractC3607ln1.a().get(this.G);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((AbstractC2924hn1) it.next()).y(this.G);
            }
        }
        this.F.j(true);
    }
}
