package X;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: X.2ai  reason: invalid class name and case insensitive filesystem */
public final class C14982ai {
    public int A00 = -1;
    public int A01;
    public int A02 = 0;
    public C15032at A03;
    public C14982ai A04;
    public HashSet<C14982ai> A05 = null;
    public boolean A06;
    public final AnonymousClass2ac A07;
    public final Integer A08;

    public final int A00() {
        if (!this.A06) {
            return 0;
        }
        return this.A01;
    }

    public final int A01() {
        C14982ai r0;
        if (this.A07.A0Q == 8) {
            return 0;
        }
        int i = this.A00;
        if (i <= -1 || (r0 = this.A04) == null || r0.A07.A0Q != 8) {
            return this.A02;
        }
        return i;
    }

    public final void A02() {
        HashSet<C14982ai> hashSet;
        C14982ai r0 = this.A04;
        if (!(r0 == null || (hashSet = r0.A05) == null)) {
            hashSet.remove(this);
            if (this.A04.A05.size() == 0) {
                this.A04.A05 = null;
            }
        }
        this.A05 = null;
        this.A04 = null;
        this.A02 = 0;
        this.A00 = -1;
        this.A06 = false;
        this.A01 = 0;
    }

    public final void A03() {
        C15032at r0 = this.A03;
        if (r0 == null) {
            this.A03 = new C15032at(AnonymousClass007.A00);
        } else {
            r0.A00();
        }
    }

    public final void A04(int i) {
        this.A01 = i;
        this.A06 = true;
    }

    public final void A05(int i, ArrayList<AnonymousClass2ao> arrayList, AnonymousClass2ao r5) {
        HashSet<C14982ai> hashSet = this.A05;
        if (hashSet != null) {
            Iterator<C14982ai> it = hashSet.iterator();
            while (it.hasNext()) {
                AnonymousClass2ar.A00(it.next().A07, i, arrayList, r5);
            }
        }
    }

    public final void A06(C14982ai r3, int i, int i2) {
        if (r3 == null) {
            A02();
            return;
        }
        this.A04 = r3;
        HashSet<C14982ai> hashSet = r3.A05;
        if (hashSet == null) {
            hashSet = new HashSet<>();
            r3.A05 = hashSet;
        }
        hashSet.add(this);
        if (i > 0) {
            this.A02 = i;
        } else {
            this.A02 = 0;
        }
        this.A00 = i2;
    }

    public final boolean A07() {
        C14982ai r0;
        HashSet<C14982ai> hashSet = this.A05;
        if (hashSet != null) {
            Iterator<C14982ai> it = hashSet.iterator();
            while (it.hasNext()) {
                C14982ai next = it.next();
                Integer num = next.A08;
                switch (num.intValue()) {
                    case 0:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        r0 = null;
                        break;
                    case 1:
                        r0 = next.A07.A0b;
                        break;
                    case 2:
                        r0 = next.A07.A0W;
                        break;
                    case 3:
                        r0 = next.A07.A0a;
                        break;
                    case 4:
                        r0 = next.A07.A0c;
                        break;
                    default:
                        throw new AssertionError(AnonymousClass2Iq.A00(num));
                }
                if (r0.A04 != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        return AnonymousClass006.A09(this.A07.A0j, ":", AnonymousClass2Iq.A00(this.A08));
    }

    public C14982ai(AnonymousClass2ac r2, Integer num) {
        this.A07 = r2;
        this.A08 = num;
    }
}
