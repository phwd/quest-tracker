package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* renamed from: k3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3312k3 {

    /* renamed from: a  reason: collision with root package name */
    public C5903zE0 f10255a = new C5903zE0(30);
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final C5410wK0 d;
    public final C1745at0 e;
    public int f = 0;

    public C3312k3(C5410wK0 wk0) {
        this.d = wk0;
        this.e = new C1745at0(this);
    }

    public final boolean a(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            C3141j3 j3Var = (C3141j3) this.c.get(i2);
            int i3 = j3Var.f10183a;
            if (i3 == 8) {
                if (f(j3Var.d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = j3Var.b;
                int i5 = j3Var.d + i4;
                while (i4 < i5) {
                    if (f(i4, i2 + 1) == i) {
                        return true;
                    }
                    i4++;
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    public void b() {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            this.d.a((C3141j3) this.c.get(i));
        }
        l(this.c);
        this.f = 0;
    }

    public void c() {
        b();
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            C3141j3 j3Var = (C3141j3) this.b.get(i);
            int i2 = j3Var.f10183a;
            if (i2 == 1) {
                this.d.a(j3Var);
                this.d.d(j3Var.b, j3Var.d);
            } else if (i2 == 2) {
                this.d.a(j3Var);
                C5410wK0 wk0 = this.d;
                int i3 = j3Var.b;
                int i4 = j3Var.d;
                wk0.f11540a.W(i3, i4, true);
                RecyclerView recyclerView = wk0.f11540a;
                recyclerView.T0 = true;
                recyclerView.Q0.c += i4;
            } else if (i2 == 4) {
                this.d.a(j3Var);
                this.d.c(j3Var.b, j3Var.d, j3Var.c);
            } else if (i2 == 8) {
                this.d.a(j3Var);
                this.d.e(j3Var.b, j3Var.d);
            }
        }
        l(this.b);
        this.f = 0;
    }

    public final void d(C3141j3 j3Var) {
        int i;
        int i2 = j3Var.f10183a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int m = m(j3Var.b, i2);
        int i3 = j3Var.b;
        int i4 = j3Var.f10183a;
        if (i4 == 2) {
            i = 0;
        } else if (i4 == 4) {
            i = 1;
        } else {
            throw new IllegalArgumentException("op should be remove or update." + j3Var);
        }
        int i5 = 1;
        for (int i6 = 1; i6 < j3Var.d; i6++) {
            int m2 = m((i * i6) + j3Var.b, j3Var.f10183a);
            int i7 = j3Var.f10183a;
            if (i7 == 2 ? m2 == m : i7 == 4 && m2 == m + 1) {
                i5++;
            } else {
                C3141j3 h = h(i7, m, i5, j3Var.c);
                e(h, i3);
                k(h);
                if (j3Var.f10183a == 4) {
                    i3 += i5;
                }
                i5 = 1;
                m = m2;
            }
        }
        Object obj = j3Var.c;
        k(j3Var);
        if (i5 > 0) {
            C3141j3 h2 = h(j3Var.f10183a, m, i5, obj);
            e(h2, i3);
            k(h2);
        }
    }

    public void e(C3141j3 j3Var, int i) {
        this.d.a(j3Var);
        int i2 = j3Var.f10183a;
        if (i2 == 2) {
            C5410wK0 wk0 = this.d;
            int i3 = j3Var.d;
            wk0.f11540a.W(i, i3, true);
            RecyclerView recyclerView = wk0.f11540a;
            recyclerView.T0 = true;
            recyclerView.Q0.c += i3;
        } else if (i2 == 4) {
            this.d.c(i, j3Var.d, j3Var.c);
        } else {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    public int f(int i, int i2) {
        int size = this.c.size();
        while (i2 < size) {
            C3141j3 j3Var = (C3141j3) this.c.get(i2);
            int i3 = j3Var.f10183a;
            if (i3 == 8) {
                int i4 = j3Var.b;
                if (i4 == i) {
                    i = j3Var.d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (j3Var.d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = j3Var.b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = j3Var.d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += j3Var.d;
                }
            }
            i2++;
        }
        return i;
    }

    public boolean g() {
        return this.b.size() > 0;
    }

    public C3141j3 h(int i, int i2, int i3, Object obj) {
        C3141j3 j3Var = (C3141j3) this.f10255a.a();
        if (j3Var == null) {
            return new C3141j3(i, i2, i3, obj);
        }
        j3Var.f10183a = i;
        j3Var.b = i2;
        j3Var.d = i3;
        j3Var.c = obj;
        return j3Var;
    }

    public final void i(C3141j3 j3Var) {
        this.c.add(j3Var);
        int i = j3Var.f10183a;
        if (i == 1) {
            this.d.d(j3Var.b, j3Var.d);
        } else if (i == 2) {
            C5410wK0 wk0 = this.d;
            wk0.f11540a.W(j3Var.b, j3Var.d, false);
            wk0.f11540a.T0 = true;
        } else if (i == 4) {
            this.d.c(j3Var.b, j3Var.d, j3Var.c);
        } else if (i == 8) {
            this.d.e(j3Var.b, j3Var.d);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + j3Var);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:172:0x0009 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x011a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void j() {
        /*
        // Method dump skipped, instructions count: 656
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3312k3.j():void");
    }

    public void k(C3141j3 j3Var) {
        j3Var.c = null;
        this.f10255a.b(j3Var);
    }

    public void l(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            k((C3141j3) list.get(i));
        }
        list.clear();
    }

    public final int m(int i, int i2) {
        int i3;
        int i4;
        for (int size = this.c.size() - 1; size >= 0; size--) {
            C3141j3 j3Var = (C3141j3) this.c.get(size);
            int i5 = j3Var.f10183a;
            if (i5 == 8) {
                int i6 = j3Var.b;
                int i7 = j3Var.d;
                if (i6 < i7) {
                    i4 = i6;
                    i3 = i7;
                } else {
                    i3 = i6;
                    i4 = i7;
                }
                if (i < i4 || i > i3) {
                    if (i < i6) {
                        if (i2 == 1) {
                            j3Var.b = i6 + 1;
                            j3Var.d = i7 + 1;
                        } else if (i2 == 2) {
                            j3Var.b = i6 - 1;
                            j3Var.d = i7 - 1;
                        }
                    }
                } else if (i4 == i6) {
                    if (i2 == 1) {
                        j3Var.d = i7 + 1;
                    } else if (i2 == 2) {
                        j3Var.d = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        j3Var.b = i6 + 1;
                    } else if (i2 == 2) {
                        j3Var.b = i6 - 1;
                    }
                    i--;
                }
            } else {
                int i8 = j3Var.b;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= j3Var.d;
                    } else if (i5 == 2) {
                        i += j3Var.d;
                    }
                } else if (i2 == 1) {
                    j3Var.b = i8 + 1;
                } else if (i2 == 2) {
                    j3Var.b = i8 - 1;
                }
            }
        }
        for (int size2 = this.c.size() - 1; size2 >= 0; size2--) {
            C3141j3 j3Var2 = (C3141j3) this.c.get(size2);
            if (j3Var2.f10183a == 8) {
                int i9 = j3Var2.d;
                if (i9 == j3Var2.b || i9 < 0) {
                    this.c.remove(size2);
                    k(j3Var2);
                }
            } else if (j3Var2.d <= 0) {
                this.c.remove(size2);
                k(j3Var2);
            }
        }
        return i;
    }
}
