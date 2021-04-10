package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: CT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CT extends AbstractC5257vT {
    public static boolean w(AbstractC2924hn1 hn1) {
        return !AbstractC5257vT.k(hn1.M) || !AbstractC5257vT.k(null) || !AbstractC5257vT.k(null);
    }

    @Override // defpackage.AbstractC5257vT
    public void a(Object obj, View view) {
        if (obj != null) {
            ((AbstractC2924hn1) obj).b(view);
        }
    }

    @Override // defpackage.AbstractC5257vT
    public void b(Object obj, ArrayList arrayList) {
        AbstractC2924hn1 hn1 = (AbstractC2924hn1) obj;
        if (hn1 != null) {
            int i = 0;
            if (hn1 instanceof C4291pn1) {
                C4291pn1 pn1 = (C4291pn1) hn1;
                int size = pn1.c0.size();
                while (i < size) {
                    b(pn1.J(i), arrayList);
                    i++;
                }
            } else if (!w(hn1) && AbstractC5257vT.k(hn1.N)) {
                int size2 = arrayList.size();
                while (i < size2) {
                    hn1.b((View) arrayList.get(i));
                    i++;
                }
            }
        }
    }

    @Override // defpackage.AbstractC5257vT
    public void c(ViewGroup viewGroup, Object obj) {
        AbstractC2924hn1 hn1 = (AbstractC2924hn1) obj;
        if (!AbstractC3607ln1.c.contains(viewGroup)) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (viewGroup.isLaidOut()) {
                AbstractC3607ln1.c.add(viewGroup);
                AbstractC2924hn1 k = hn1.clone();
                ArrayList arrayList = (ArrayList) AbstractC3607ln1.a().getOrDefault(viewGroup, null);
                if (arrayList != null && arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((AbstractC2924hn1) it.next()).v(viewGroup);
                    }
                }
                if (k != null) {
                    k.i(viewGroup, true);
                }
                C5859z.a(viewGroup.getTag(R.id.transition_current_scene));
                viewGroup.setTag(R.id.transition_current_scene, null);
                if (k != null) {
                    ViewTreeObserver$OnPreDrawListenerC3436kn1 kn1 = new ViewTreeObserver$OnPreDrawListenerC3436kn1(k, viewGroup);
                    viewGroup.addOnAttachStateChangeListener(kn1);
                    viewGroup.getViewTreeObserver().addOnPreDrawListener(kn1);
                }
            }
        }
    }

    @Override // defpackage.AbstractC5257vT
    public boolean e(Object obj) {
        return obj instanceof AbstractC2924hn1;
    }

    @Override // defpackage.AbstractC5257vT
    public Object g(Object obj) {
        if (obj != null) {
            return ((AbstractC2924hn1) obj).clone();
        }
        return null;
    }

    @Override // defpackage.AbstractC5257vT
    public Object l(Object obj, Object obj2, Object obj3) {
        C4291pn1 pn1 = new C4291pn1();
        if (obj != null) {
            pn1.I((AbstractC2924hn1) obj);
        }
        if (obj2 != null) {
            pn1.I((AbstractC2924hn1) obj2);
        }
        if (obj3 != null) {
            pn1.I((AbstractC2924hn1) obj3);
        }
        return pn1;
    }

    @Override // defpackage.AbstractC5257vT
    public void m(Object obj, View view) {
        if (obj != null) {
            ((AbstractC2924hn1) obj).x(view);
        }
    }

    @Override // defpackage.AbstractC5257vT
    public void n(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        int i;
        AbstractC2924hn1 hn1 = (AbstractC2924hn1) obj;
        int i2 = 0;
        if (hn1 instanceof C4291pn1) {
            C4291pn1 pn1 = (C4291pn1) hn1;
            int size = pn1.c0.size();
            while (i2 < size) {
                n(pn1.J(i2), arrayList, arrayList2);
                i2++;
            }
        } else if (!w(hn1)) {
            ArrayList arrayList3 = hn1.N;
            if (arrayList3.size() == arrayList.size() && arrayList3.containsAll(arrayList)) {
                if (arrayList2 == null) {
                    i = 0;
                } else {
                    i = arrayList2.size();
                }
                while (i2 < i) {
                    hn1.b((View) arrayList2.get(i2));
                    i2++;
                }
                int size2 = arrayList.size();
                while (true) {
                    size2--;
                    if (size2 >= 0) {
                        hn1.x((View) arrayList.get(size2));
                    } else {
                        return;
                    }
                }
            }
        }
    }

    @Override // defpackage.AbstractC5257vT
    public void o(Object obj, View view, ArrayList arrayList) {
        ((AbstractC2924hn1) obj).a(new C5597xT(this, view, arrayList));
    }

    @Override // defpackage.AbstractC5257vT
    public void p(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3) {
        ((AbstractC2924hn1) obj).a(new C5767yT(this, obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // defpackage.AbstractC5257vT
    public void q(Object obj, Rect rect) {
        if (obj != null) {
            ((AbstractC2924hn1) obj).B(new BT(this, rect));
        }
    }

    @Override // defpackage.AbstractC5257vT
    public void r(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            j(view, rect);
            ((AbstractC2924hn1) obj).B(new C5427wT(this, rect));
        }
    }

    @Override // defpackage.AbstractC5257vT
    public void s(AbstractComponentCallbacksC3550lS lSVar, Object obj, C3089im imVar, Runnable runnable) {
        AbstractC2924hn1 hn1 = (AbstractC2924hn1) obj;
        imVar.a(new C5937zT(this, hn1));
        hn1.a(new AT(this, runnable));
    }

    @Override // defpackage.AbstractC5257vT
    public void t(Object obj, View view, ArrayList arrayList) {
        C4291pn1 pn1 = (C4291pn1) obj;
        ArrayList arrayList2 = pn1.N;
        arrayList2.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            AbstractC5257vT.d(arrayList2, (View) arrayList.get(i));
        }
        arrayList2.add(view);
        arrayList.add(view);
        b(pn1, arrayList);
    }

    @Override // defpackage.AbstractC5257vT
    public void u(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        C4291pn1 pn1 = (C4291pn1) obj;
        if (pn1 != null) {
            pn1.N.clear();
            pn1.N.addAll(arrayList2);
            n(pn1, arrayList, arrayList2);
        }
    }

    @Override // defpackage.AbstractC5257vT
    public Object v(Object obj) {
        if (obj == null) {
            return null;
        }
        C4291pn1 pn1 = new C4291pn1();
        pn1.I((AbstractC2924hn1) obj);
        return pn1;
    }
}
