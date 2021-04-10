package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: X80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X80 extends C4284pl0 {
    public X80(C4935tb0 tb0) {
        super(tb0);
    }

    public boolean areAllItemsEnabled() {
        for (int i = 0; i < getCount(); i++) {
            if (!isEnabled(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.C4284pl0
    public long getItemId(int i) {
        return (long) ((C4765sb0) this.F.G.get(i)).b.f(Y80.f);
    }

    @Override // defpackage.C4284pl0
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        view2.setOnClickListener(new W80(this, i, viewGroup));
        view2.setEnabled(isEnabled(i));
        return view2;
    }

    public boolean isEnabled(int i) {
        return ((C4765sb0) this.F.G.get(i)).f11283a != 0 && ((C4765sb0) this.F.G.get(i)).b.h(Y80.g);
    }
}
