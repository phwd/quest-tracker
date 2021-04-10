package defpackage;

import com.oculus.os.Version;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* renamed from: Fe  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0317Fe implements HS {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f8026a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public boolean h;
    public String i;
    public int j;
    public CharSequence k;
    public int l;
    public CharSequence m;
    public ArrayList n;
    public ArrayList o;
    public boolean p;
    public final KS q;
    public boolean r;
    public int s;

    public C0317Fe(KS ks) {
        ks.P();
        C3721mS mSVar = ks.n;
        if (mSVar != null) {
            mSVar.G.getClassLoader();
        }
        this.f8026a = new ArrayList();
        this.h = true;
        this.p = false;
        this.s = -1;
        this.q = ks;
    }

    public static boolean o(C2186dT dTVar) {
        AbstractComponentCallbacksC3550lS lSVar = dTVar.b;
        if (lSVar == null || !lSVar.P || lSVar.k0 == null || lSVar.e0 || lSVar.d0) {
            return false;
        }
        C3038iS iSVar = lSVar.n0;
        return false;
    }

    @Override // defpackage.HS
    public boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (KS.R(2)) {
            String str = "Run: " + this;
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.g) {
            return true;
        }
        KS ks = this.q;
        if (ks.d == null) {
            ks.d = new ArrayList();
        }
        ks.d.add(this);
        return true;
    }

    public C0317Fe b(AbstractComponentCallbacksC3550lS lSVar, String str) {
        i(0, lSVar, null, 1);
        return this;
    }

    public void c(C2186dT dTVar) {
        this.f8026a.add(dTVar);
        dTVar.c = this.b;
        dTVar.d = this.c;
        dTVar.e = this.d;
        dTVar.f = this.e;
    }

    public void d(int i2) {
        if (this.g) {
            if (KS.R(2)) {
                String str = "Bump nesting in " + this + " by " + i2;
            }
            int size = this.f8026a.size();
            for (int i3 = 0; i3 < size; i3++) {
                C2186dT dTVar = (C2186dT) this.f8026a.get(i3);
                AbstractComponentCallbacksC3550lS lSVar = dTVar.b;
                if (lSVar != null) {
                    lSVar.V += i2;
                    if (KS.R(2)) {
                        StringBuilder i4 = AbstractC2531fV.i("Bump nesting of ");
                        i4.append(dTVar.b);
                        i4.append(" to ");
                        i4.append(dTVar.b.V);
                        i4.toString();
                    }
                }
            }
        }
    }

    public int e() {
        return g(false);
    }

    public int f() {
        return g(true);
    }

    public int g(boolean z) {
        if (!this.r) {
            if (KS.R(2)) {
                String str = "Commit: " + this;
                PrintWriter printWriter = new PrintWriter(new C1342Wa0("FragmentManager"));
                j("  ", printWriter, true);
                printWriter.close();
            }
            this.r = true;
            if (this.g) {
                this.s = this.q.i.getAndIncrement();
            } else {
                this.s = -1;
            }
            this.q.B(this, z);
            return this.s;
        }
        throw new IllegalStateException("commit already called");
    }

    public void h() {
        if (!this.g) {
            this.h = false;
            this.q.E(this, false);
            return;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public void i(int i2, AbstractComponentCallbacksC3550lS lSVar, String str, int i3) {
        Class<?> cls = lSVar.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            StringBuilder i4 = AbstractC2531fV.i("Fragment ");
            i4.append(cls.getCanonicalName());
            i4.append(" must be a public static class to be  properly recreated from instance state.");
            throw new IllegalStateException(i4.toString());
        }
        if (str != null) {
            String str2 = lSVar.c0;
            if (str2 == null || str.equals(str2)) {
                lSVar.c0 = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + lSVar + ": was " + lSVar.c0 + " now " + str);
            }
        }
        if (i2 != 0) {
            if (i2 != -1) {
                int i5 = lSVar.a0;
                if (i5 == 0 || i5 == i2) {
                    lSVar.a0 = i2;
                    lSVar.b0 = i2;
                } else {
                    throw new IllegalStateException("Can't change container ID of fragment " + lSVar + ": was " + lSVar.a0 + " now " + i2);
                }
            } else {
                throw new IllegalArgumentException("Can't add fragment " + lSVar + " with tag " + str + " to container view with no id");
            }
        }
        c(new C2186dT(i3, lSVar));
        lSVar.W = this.q;
    }

    public void j(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.i);
            printWriter.print(" mIndex=");
            printWriter.print(this.s);
            printWriter.print(" mCommitted=");
            printWriter.println(this.r);
            if (this.f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f));
            }
            if (!(this.b == 0 && this.c == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.c));
            }
            if (!(this.d == 0 && this.e == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.e));
            }
            if (!(this.j == 0 && this.k == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.j));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.k);
            }
            if (!(this.l == 0 && this.m == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.l));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.m);
            }
        }
        if (!this.f8026a.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            int size = this.f8026a.size();
            for (int i2 = 0; i2 < size; i2++) {
                C2186dT dTVar = (C2186dT) this.f8026a.get(i2);
                switch (dTVar.f9783a) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                        str2 = "ATTACH";
                        break;
                    case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                        str2 = "OP_SET_MAX_LIFECYCLE";
                        break;
                    default:
                        StringBuilder i3 = AbstractC2531fV.i("cmd=");
                        i3.append(dTVar.f9783a);
                        str2 = i3.toString();
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(dTVar.b);
                if (z) {
                    if (!(dTVar.c == 0 && dTVar.d == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(dTVar.c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(dTVar.d));
                    }
                    if (dTVar.e != 0 || dTVar.f != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(dTVar.e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(dTVar.f));
                    }
                }
            }
        }
    }

    public void k() {
        int size = this.f8026a.size();
        for (int i2 = 0; i2 < size; i2++) {
            C2186dT dTVar = (C2186dT) this.f8026a.get(i2);
            AbstractComponentCallbacksC3550lS lSVar = dTVar.b;
            if (lSVar != null) {
                int i3 = this.f;
                if (!(lSVar.n0 == null && i3 == 0)) {
                    lSVar.t();
                    lSVar.n0.e = i3;
                }
            }
            switch (dTVar.f9783a) {
                case 1:
                    lSVar.Y0(dTVar.c);
                    this.q.m0(lSVar, false);
                    this.q.b(lSVar);
                    break;
                case 2:
                default:
                    StringBuilder i4 = AbstractC2531fV.i("Unknown cmd: ");
                    i4.append(dTVar.f9783a);
                    throw new IllegalArgumentException(i4.toString());
                case 3:
                    lSVar.Y0(dTVar.d);
                    this.q.g0(lSVar);
                    break;
                case 4:
                    lSVar.Y0(dTVar.d);
                    this.q.Q(lSVar);
                    break;
                case 5:
                    lSVar.Y0(dTVar.c);
                    this.q.m0(lSVar, false);
                    this.q.q0(lSVar);
                    break;
                case 6:
                    lSVar.Y0(dTVar.d);
                    this.q.k(lSVar);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    lSVar.Y0(dTVar.c);
                    this.q.m0(lSVar, false);
                    this.q.d(lSVar);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    this.q.o0(lSVar);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    this.q.o0(null);
                    break;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    this.q.n0(lSVar, dTVar.h);
                    break;
            }
            if (!(this.p || dTVar.f9783a == 1 || lSVar == null)) {
                this.q.Y(lSVar);
            }
        }
        if (!this.p) {
            KS ks = this.q;
            ks.Z(ks.m, true);
        }
    }

    public void l(boolean z) {
        for (int size = this.f8026a.size() - 1; size >= 0; size--) {
            C2186dT dTVar = (C2186dT) this.f8026a.get(size);
            AbstractComponentCallbacksC3550lS lSVar = dTVar.b;
            if (lSVar != null) {
                int i2 = this.f;
                int i3 = 8194;
                if (i2 != 4097) {
                    i3 = i2 != 4099 ? i2 != 8194 ? 0 : 4097 : 4099;
                }
                if (!(lSVar.n0 == null && i3 == 0)) {
                    lSVar.t();
                    lSVar.n0.e = i3;
                }
            }
            switch (dTVar.f9783a) {
                case 1:
                    lSVar.Y0(dTVar.f);
                    this.q.m0(lSVar, true);
                    this.q.g0(lSVar);
                    break;
                case 2:
                default:
                    StringBuilder i4 = AbstractC2531fV.i("Unknown cmd: ");
                    i4.append(dTVar.f9783a);
                    throw new IllegalArgumentException(i4.toString());
                case 3:
                    lSVar.Y0(dTVar.e);
                    this.q.b(lSVar);
                    break;
                case 4:
                    lSVar.Y0(dTVar.e);
                    this.q.q0(lSVar);
                    break;
                case 5:
                    lSVar.Y0(dTVar.f);
                    this.q.m0(lSVar, true);
                    this.q.Q(lSVar);
                    break;
                case 6:
                    lSVar.Y0(dTVar.e);
                    this.q.d(lSVar);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    lSVar.Y0(dTVar.f);
                    this.q.m0(lSVar, true);
                    this.q.k(lSVar);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    this.q.o0(null);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    this.q.o0(lSVar);
                    break;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    this.q.n0(lSVar, dTVar.g);
                    break;
            }
            if (!(this.p || dTVar.f9783a == 3 || lSVar == null)) {
                this.q.Y(lSVar);
            }
        }
        if (!this.p && z) {
            KS ks = this.q;
            ks.Z(ks.m, true);
        }
    }

    public boolean m(int i2) {
        int size = this.f8026a.size();
        for (int i3 = 0; i3 < size; i3++) {
            AbstractComponentCallbacksC3550lS lSVar = ((C2186dT) this.f8026a.get(i3)).b;
            int i4 = lSVar != null ? lSVar.b0 : 0;
            if (i4 != 0 && i4 == i2) {
                return true;
            }
        }
        return false;
    }

    public boolean n(ArrayList arrayList, int i2, int i3) {
        if (i3 == i2) {
            return false;
        }
        int size = this.f8026a.size();
        int i4 = -1;
        for (int i5 = 0; i5 < size; i5++) {
            AbstractComponentCallbacksC3550lS lSVar = ((C2186dT) this.f8026a.get(i5)).b;
            int i6 = lSVar != null ? lSVar.b0 : 0;
            if (!(i6 == 0 || i6 == i4)) {
                for (int i7 = i2; i7 < i3; i7++) {
                    C0317Fe fe = (C0317Fe) arrayList.get(i7);
                    int size2 = fe.f8026a.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        AbstractComponentCallbacksC3550lS lSVar2 = ((C2186dT) fe.f8026a.get(i8)).b;
                        if ((lSVar2 != null ? lSVar2.b0 : 0) == i6) {
                            return true;
                        }
                    }
                }
                i4 = i6;
            }
        }
        return false;
    }

    public C0317Fe p(AbstractComponentCallbacksC3550lS lSVar) {
        KS ks = lSVar.W;
        if (ks == null || ks == this.q) {
            c(new C2186dT(3, lSVar));
            return this;
        }
        StringBuilder i2 = AbstractC2531fV.i("Cannot remove Fragment attached to a different FragmentManager. Fragment ");
        i2.append(lSVar.toString());
        i2.append(" is already attached to a FragmentManager.");
        throw new IllegalStateException(i2.toString());
    }

    public C0317Fe q(int i2, AbstractComponentCallbacksC3550lS lSVar) {
        if (i2 != 0) {
            i(i2, lSVar, null, 2);
            return this;
        }
        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    public C0317Fe r(AbstractComponentCallbacksC3550lS lSVar, EnumC3328k80 k80) {
        if (lSVar.W == this.q) {
            EnumC3328k80 k802 = EnumC3328k80.CREATED;
            if (k80.compareTo(k802) >= 0) {
                c(new C2186dT(10, lSVar, k80));
                return this;
            }
            throw new IllegalArgumentException("Cannot set maximum Lifecycle below " + k802);
        }
        StringBuilder i2 = AbstractC2531fV.i("Cannot setMaxLifecycle for Fragment not attached to FragmentManager ");
        i2.append(this.q);
        throw new IllegalArgumentException(i2.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.s >= 0) {
            sb.append(" #");
            sb.append(this.s);
        }
        if (this.i != null) {
            sb.append(" ");
            sb.append(this.i);
        }
        sb.append("}");
        return sb.toString();
    }
}
