package defpackage;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* renamed from: VD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VD extends EW0 {
    public static TimeInterpolator h;
    public ArrayList i = new ArrayList();
    public ArrayList j = new ArrayList();
    public ArrayList k = new ArrayList();
    public ArrayList l = new ArrayList();
    public ArrayList m = new ArrayList();
    public ArrayList n = new ArrayList();
    public ArrayList o = new ArrayList();
    public ArrayList p = new ArrayList();
    public ArrayList q = new ArrayList();
    public ArrayList r = new ArrayList();
    public ArrayList s = new ArrayList();

    @Override // defpackage.EW0
    public boolean b(XK0 xk0, int i2, int i3, int i4, int i5) {
        View view = xk0.G;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) xk0.G.getTranslationY());
        o(xk0);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            d(xk0);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX((float) (-i6));
        }
        if (i7 != 0) {
            view.setTranslationY((float) (-i7));
        }
        this.k.add(new UD(xk0, translationX, translationY, i4, i5));
        return true;
    }

    @Override // defpackage.EW0
    public void f(XK0 xk0) {
        View view = xk0.G;
        view.animate().cancel();
        int size = this.k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (((UD) this.k.get(size)).f9012a == xk0) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                d(xk0);
                this.k.remove(size);
            }
        }
        m(this.l, xk0);
        if (this.i.remove(xk0)) {
            view.setAlpha(1.0f);
            d(xk0);
        }
        if (this.j.remove(xk0)) {
            view.setAlpha(1.0f);
            d(xk0);
        }
        int size2 = this.o.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            ArrayList arrayList = (ArrayList) this.o.get(size2);
            m(arrayList, xk0);
            if (arrayList.isEmpty()) {
                this.o.remove(size2);
            }
        }
        int size3 = this.n.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            ArrayList arrayList2 = (ArrayList) this.n.get(size3);
            int size4 = arrayList2.size();
            while (true) {
                size4--;
                if (size4 < 0) {
                    break;
                } else if (((UD) arrayList2.get(size4)).f9012a == xk0) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    d(xk0);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.n.remove(size3);
                    }
                }
            }
        }
        int size5 = this.m.size();
        while (true) {
            size5--;
            if (size5 >= 0) {
                ArrayList arrayList3 = (ArrayList) this.m.get(size5);
                if (arrayList3.remove(xk0)) {
                    view.setAlpha(1.0f);
                    d(xk0);
                    if (arrayList3.isEmpty()) {
                        this.m.remove(size5);
                    }
                }
            } else {
                this.r.remove(xk0);
                this.p.remove(xk0);
                this.s.remove(xk0);
                this.q.remove(xk0);
                l();
                return;
            }
        }
    }

    @Override // defpackage.EW0
    public void g() {
        int size = this.k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            UD ud = (UD) this.k.get(size);
            View view = ud.f9012a.G;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            d(ud.f9012a);
            this.k.remove(size);
        }
        int size2 = this.i.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            d((XK0) this.i.get(size2));
            this.i.remove(size2);
        }
        int size3 = this.j.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            XK0 xk0 = (XK0) this.j.get(size3);
            xk0.G.setAlpha(1.0f);
            d(xk0);
            this.j.remove(size3);
        }
        int size4 = this.l.size();
        while (true) {
            size4--;
            if (size4 < 0) {
                break;
            }
            TD td = (TD) this.l.get(size4);
            XK0 xk02 = td.f8946a;
            if (xk02 != null) {
                n(td, xk02);
            }
            XK0 xk03 = td.b;
            if (xk03 != null) {
                n(td, xk03);
            }
        }
        this.l.clear();
        if (h()) {
            int size5 = this.n.size();
            while (true) {
                size5--;
                if (size5 < 0) {
                    break;
                }
                ArrayList arrayList = (ArrayList) this.n.get(size5);
                int size6 = arrayList.size();
                while (true) {
                    size6--;
                    if (size6 >= 0) {
                        UD ud2 = (UD) arrayList.get(size6);
                        View view2 = ud2.f9012a.G;
                        view2.setTranslationY(0.0f);
                        view2.setTranslationX(0.0f);
                        d(ud2.f9012a);
                        arrayList.remove(size6);
                        if (arrayList.isEmpty()) {
                            this.n.remove(arrayList);
                        }
                    }
                }
            }
            int size7 = this.m.size();
            while (true) {
                size7--;
                if (size7 < 0) {
                    break;
                }
                ArrayList arrayList2 = (ArrayList) this.m.get(size7);
                int size8 = arrayList2.size();
                while (true) {
                    size8--;
                    if (size8 >= 0) {
                        XK0 xk04 = (XK0) arrayList2.get(size8);
                        xk04.G.setAlpha(1.0f);
                        d(xk04);
                        arrayList2.remove(size8);
                        if (arrayList2.isEmpty()) {
                            this.m.remove(arrayList2);
                        }
                    }
                }
            }
            int size9 = this.o.size();
            while (true) {
                size9--;
                if (size9 >= 0) {
                    ArrayList arrayList3 = (ArrayList) this.o.get(size9);
                    int size10 = arrayList3.size();
                    while (true) {
                        size10--;
                        if (size10 >= 0) {
                            TD td2 = (TD) arrayList3.get(size10);
                            XK0 xk05 = td2.f8946a;
                            if (xk05 != null) {
                                n(td2, xk05);
                            }
                            XK0 xk06 = td2.b;
                            if (xk06 != null) {
                                n(td2, xk06);
                            }
                            if (arrayList3.isEmpty()) {
                                this.o.remove(arrayList3);
                            }
                        }
                    }
                } else {
                    k(this.r);
                    k(this.q);
                    k(this.p);
                    k(this.s);
                    e();
                    return;
                }
            }
        }
    }

    @Override // defpackage.EW0
    public boolean h() {
        return !this.j.isEmpty() || !this.l.isEmpty() || !this.k.isEmpty() || !this.i.isEmpty() || !this.q.isEmpty() || !this.r.isEmpty() || !this.p.isEmpty() || !this.s.isEmpty() || !this.n.isEmpty() || !this.m.isEmpty() || !this.o.isEmpty();
    }

    public void k(List list) {
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                ((XK0) list.get(size)).G.animate().cancel();
            } else {
                return;
            }
        }
    }

    public void l() {
        if (!h()) {
            e();
        }
    }

    public final void m(List list, XK0 xk0) {
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                TD td = (TD) list.get(size);
                if (n(td, xk0) && td.f8946a == null && td.b == null) {
                    list.remove(td);
                }
            } else {
                return;
            }
        }
    }

    public final boolean n(TD td, XK0 xk0) {
        if (td.b == xk0) {
            td.b = null;
        } else if (td.f8946a != xk0) {
            return false;
        } else {
            td.f8946a = null;
        }
        xk0.G.setAlpha(1.0f);
        xk0.G.setTranslationX(0.0f);
        xk0.G.setTranslationY(0.0f);
        d(xk0);
        return true;
    }

    public final void o(XK0 xk0) {
        if (h == null) {
            h = new ValueAnimator().getInterpolator();
        }
        xk0.G.animate().setInterpolator(h);
        f(xk0);
    }
}
