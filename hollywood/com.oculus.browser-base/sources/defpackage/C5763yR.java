package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* renamed from: yR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5763yR {

    /* renamed from: a  reason: collision with root package name */
    public final LinearLayout f11680a;
    public final View b;
    public final int c;
    public final ArrayList d = a();

    public C5763yR(LinearLayout linearLayout, View view, Runnable runnable) {
        this.f11680a = linearLayout;
        this.b = view;
        this.c = linearLayout.getChildCount();
        linearLayout.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC4913tR(this, runnable));
    }

    public final ArrayList a() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < this.f11680a.getChildCount(); i2++) {
            arrayList.add(Integer.valueOf(i));
            i += this.f11680a.getChildAt(i2).getMeasuredHeight();
        }
        arrayList.add(Integer.valueOf(i));
        return arrayList;
    }

    public final void b() {
        ViewGroup viewGroup = (ViewGroup) this.f11680a.getParent();
        if (this.f11680a.getParent() != null) {
            View view = this.b;
            if (view != null) {
                viewGroup.requestChildFocus(this.f11680a, view);
            }
            int max = Math.max(0, this.f11680a.getMeasuredHeight() - (viewGroup.getBottom() - viewGroup.getTop()));
            if (viewGroup.getScrollY() > max) {
                viewGroup.setScrollY(max);
            }
        }
    }
}
