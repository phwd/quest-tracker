package defpackage;

import android.util.Pair;
import android.util.SparseArray;
import android.widget.BaseAdapter;

/* renamed from: pl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4284pl0 extends BaseAdapter {
    public final C4935tb0 F;
    public final SparseArray G = new SparseArray();
    public final AbstractC2648g90 H;

    public C4284pl0(C4935tb0 tb0) {
        this.F = tb0;
        C4113ol0 ol0 = new C4113ol0(this);
        this.H = ol0;
        tb0.F.b(ol0);
    }

    public void a(int i, AbstractC5105ub0 ub0, YH0 yh0) {
        this.G.put(i, new Pair(ub0, yh0));
    }

    public int getCount() {
        return this.F.size();
    }

    public Object getItem(int i) {
        return this.F.G.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return ((C4765sb0) this.F.G.get(i)).f11283a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r8, android.view.View r9, android.view.ViewGroup r10) {
        /*
        // Method dump skipped, instructions count: 240
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4284pl0.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public int getViewTypeCount() {
        return Math.max(1, this.G.size());
    }
}
