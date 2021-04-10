package defpackage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: t80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4865t80 extends AbstractC3499l80 {

    /* renamed from: a  reason: collision with root package name */
    public C2346eO f11325a = new C2346eO();
    public EnumC3328k80 b;
    public final WeakReference c;
    public int d = 0;
    public boolean e = false;
    public boolean f = false;
    public ArrayList g = new ArrayList();

    public C4865t80(AbstractC4524r80 r80) {
        this.c = new WeakReference(r80);
        this.b = EnumC3328k80.INITIALIZED;
    }

    public static EnumC3328k80 d(EnumC3157j80 j80) {
        int ordinal = j80.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    return EnumC3328k80.RESUMED;
                }
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        if (ordinal == 5) {
                            return EnumC3328k80.DESTROYED;
                        }
                        throw new IllegalArgumentException("Unexpected event value " + j80);
                    }
                }
            }
            return EnumC3328k80.STARTED;
        }
        return EnumC3328k80.CREATED;
    }

    public static EnumC3328k80 f(EnumC3328k80 k80, EnumC3328k80 k802) {
        return (k802 == null || k802.compareTo(k80) >= 0) ? k80 : k802;
    }

    public static EnumC3157j80 j(EnumC3328k80 k80) {
        int ordinal = k80.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return EnumC3157j80.ON_CREATE;
        }
        if (ordinal == 2) {
            return EnumC3157j80.ON_START;
        }
        if (ordinal == 3) {
            return EnumC3157j80.ON_RESUME;
        }
        if (ordinal != 4) {
            throw new IllegalArgumentException("Unexpected state value " + k80);
        }
        throw new IllegalArgumentException();
    }

    @Override // defpackage.AbstractC3499l80
    public void a(AbstractC4354q80 q80) {
        Object obj;
        AbstractC4524r80 r80;
        EnumC3328k80 k80 = this.b;
        EnumC3328k80 k802 = EnumC3328k80.DESTROYED;
        if (k80 != k802) {
            k802 = EnumC3328k80.INITIALIZED;
        }
        C4695s80 s80 = new C4695s80(q80, k802);
        C2346eO eOVar = this.f11325a;
        C4568rO0 ro0 = (C4568rO0) eOVar.f9850J.get(q80);
        if (ro0 != null) {
            obj = ro0.G;
        } else {
            HashMap hashMap = eOVar.f9850J;
            C4568rO0 ro02 = new C4568rO0(q80, s80);
            eOVar.I++;
            C4568rO0 ro03 = eOVar.G;
            if (ro03 == null) {
                eOVar.F = ro02;
                eOVar.G = ro02;
            } else {
                ro03.H = ro02;
                ro02.I = ro03;
                eOVar.G = ro02;
            }
            hashMap.put(q80, ro02);
            obj = null;
        }
        if (((C4695s80) obj) == null && (r80 = (AbstractC4524r80) this.c.get()) != null) {
            boolean z = this.d != 0 || this.e;
            EnumC3328k80 c2 = c(q80);
            this.d++;
            while (s80.f11253a.compareTo((Enum) c2) < 0 && this.f11325a.f9850J.containsKey(q80)) {
                this.g.add(s80.f11253a);
                s80.a(r80, j(s80.f11253a));
                h();
                c2 = c(q80);
            }
            if (!z) {
                i();
            }
            this.d--;
        }
    }

    @Override // defpackage.AbstractC3499l80
    public void b(AbstractC4354q80 q80) {
        C2346eO eOVar = this.f11325a;
        C4568rO0 ro0 = (C4568rO0) eOVar.f9850J.get(q80);
        if (ro0 != null) {
            eOVar.I--;
            if (!eOVar.H.isEmpty()) {
                for (AbstractC5078uO0 uo0 : eOVar.H.keySet()) {
                    uo0.a(ro0);
                }
            }
            C4568rO0 ro02 = ro0.I;
            if (ro02 != null) {
                ro02.H = ro0.H;
            } else {
                eOVar.F = ro0.H;
            }
            C4568rO0 ro03 = ro0.H;
            if (ro03 != null) {
                ro03.I = ro02;
            } else {
                eOVar.G = ro02;
            }
            ro0.H = null;
            ro0.I = null;
        }
        eOVar.f9850J.remove(q80);
    }

    public final EnumC3328k80 c(AbstractC4354q80 q80) {
        C2346eO eOVar = this.f11325a;
        EnumC3328k80 k80 = null;
        C4568rO0 ro0 = eOVar.f9850J.containsKey(q80) ? ((C4568rO0) eOVar.f9850J.get(q80)).I : null;
        EnumC3328k80 k802 = ro0 != null ? ((C4695s80) ro0.G).f11253a : null;
        if (!this.g.isEmpty()) {
            ArrayList arrayList = this.g;
            k80 = (EnumC3328k80) arrayList.get(arrayList.size() - 1);
        }
        return f(f(this.b, k802), k80);
    }

    public void e(EnumC3157j80 j80) {
        g(d(j80));
    }

    public final void g(EnumC3328k80 k80) {
        if (this.b != k80) {
            this.b = k80;
            if (this.e || this.d != 0) {
                this.f = true;
                return;
            }
            this.e = true;
            i();
            this.e = false;
        }
    }

    public final void h() {
        ArrayList arrayList = this.g;
        arrayList.remove(arrayList.size() - 1);
    }

    public final void i() {
        EnumC3157j80 j80;
        EnumC3328k80 k80;
        AbstractC4524r80 r80 = (AbstractC4524r80) this.c.get();
        if (r80 != null) {
            while (true) {
                C2346eO eOVar = this.f11325a;
                if (!(eOVar.I == 0 || (((C4695s80) eOVar.F.G).f11253a == (k80 = ((C4695s80) eOVar.G.G).f11253a) && this.b == k80))) {
                    this.f = false;
                    if (this.b.compareTo((Enum) ((C4695s80) eOVar.F.G).f11253a) < 0) {
                        C2346eO eOVar2 = this.f11325a;
                        C4398qO0 qo0 = new C4398qO0(eOVar2.G, eOVar2.F);
                        eOVar2.H.put(qo0, Boolean.FALSE);
                        while (qo0.hasNext() && !this.f) {
                            Map.Entry entry = (Map.Entry) qo0.next();
                            C4695s80 s80 = (C4695s80) entry.getValue();
                            while (s80.f11253a.compareTo((Enum) this.b) > 0 && !this.f && this.f11325a.contains(entry.getKey())) {
                                EnumC3328k80 k802 = s80.f11253a;
                                int ordinal = k802.ordinal();
                                if (ordinal == 0) {
                                    throw new IllegalArgumentException();
                                } else if (ordinal != 1) {
                                    if (ordinal == 2) {
                                        j80 = EnumC3157j80.ON_DESTROY;
                                    } else if (ordinal == 3) {
                                        j80 = EnumC3157j80.ON_STOP;
                                    } else if (ordinal == 4) {
                                        j80 = EnumC3157j80.ON_PAUSE;
                                    } else {
                                        throw new IllegalArgumentException("Unexpected state value " + k802);
                                    }
                                    this.g.add(d(j80));
                                    s80.a(r80, j80);
                                    h();
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                        }
                    }
                    C4568rO0 ro0 = this.f11325a.G;
                    if (!this.f && ro0 != null && this.b.compareTo((Enum) ((C4695s80) ro0.G).f11253a) > 0) {
                        C4738sO0 a2 = this.f11325a.a();
                        while (a2.hasNext() && !this.f) {
                            Map.Entry entry2 = (Map.Entry) a2.next();
                            C4695s80 s802 = (C4695s80) entry2.getValue();
                            while (s802.f11253a.compareTo((Enum) this.b) < 0 && !this.f && this.f11325a.contains(entry2.getKey())) {
                                this.g.add(s802.f11253a);
                                s802.a(r80, j(s802.f11253a));
                                h();
                            }
                        }
                    }
                } else {
                    this.f = false;
                    return;
                }
            }
        } else {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
    }
}
