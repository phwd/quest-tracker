package defpackage;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: aJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1644aJ0 extends AbstractC0966Pv0 {
    public ArrayList c;

    public C1644aJ0(ArrayList arrayList) {
        this.c = arrayList;
    }

    @Override // defpackage.AbstractC0966Pv0
    public void d(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // defpackage.AbstractC0966Pv0
    public int e() {
        return this.c.size();
    }

    @Override // defpackage.AbstractC0966Pv0
    public Object g(ViewGroup viewGroup, int i) {
        View view = (View) this.c.get(i);
        viewGroup.addView(view);
        return view;
    }

    @Override // defpackage.AbstractC0966Pv0
    public boolean h(View view, Object obj) {
        return view == obj;
    }
}
