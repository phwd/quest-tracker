package X;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.2ao  reason: invalid class name */
public final class AnonymousClass2ao {
    public static int A05;
    public int A00 = -1;
    public int A01 = 0;
    public ArrayList<AnonymousClass2bC> A02 = null;
    public ArrayList<AnonymousClass2ac> A03 = new ArrayList<>();
    public int A04 = -1;

    public final int A00(C15022am r6, int i) {
        int A002;
        C14982ai r0;
        ArrayList<AnonymousClass2ac> arrayList = this.A03;
        if (arrayList.size() == 0) {
            return 0;
        }
        C14932ab r2 = (C14932ab) arrayList.get(0).A0d;
        r6.A0A();
        r2.A0J(r6, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.get(i2).A0J(r6, false);
        }
        if (i == 0) {
            if (r2.A00 > 0) {
                C14972ah.A00(r2, r6, arrayList, 0);
            }
        } else if (i == 1 && r2.A04 > 0) {
            C14972ah.A00(r2, r6, arrayList, 1);
        }
        try {
            r6.A09();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.A02 = new ArrayList<>();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            this.A02.add(new AnonymousClass2bC(this, arrayList.get(i3)));
        }
        if (i == 0) {
            A002 = C15022am.A00(r2.A0a);
            r0 = r2.A0b;
        } else {
            A002 = C15022am.A00(r2.A0c);
            r0 = r2.A0W;
        }
        int A003 = C15022am.A00(r0);
        r6.A0A();
        return A003 - A002;
    }

    public final void A01(int i, AnonymousClass2ao r5) {
        Iterator<AnonymousClass2ac> it = this.A03.iterator();
        while (it.hasNext()) {
            AnonymousClass2ac next = it.next();
            if (!r5.A03.contains(next)) {
                r5.A03.add(next);
            }
            int i2 = r5.A00;
            if (i == 0) {
                next.A07 = i2;
            } else {
                next.A0U = i2;
            }
        }
        this.A04 = r5.A00;
    }

    public final void A02(ArrayList<AnonymousClass2ao> arrayList) {
        int size = this.A03.size();
        if (this.A04 != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                AnonymousClass2ao r2 = arrayList.get(i);
                if (this.A04 == r2.A00) {
                    A01(this.A01, r2);
                }
            }
        } else if (size == 0) {
            arrayList.remove(this);
        }
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        int i = this.A01;
        if (i == 0) {
            str = "Horizontal";
        } else if (i == 1) {
            str = "Vertical";
        } else if (i == 2) {
            str = "Both";
        } else {
            str = "Unknown";
        }
        sb.append(str);
        sb.append(" [");
        sb.append(this.A00);
        sb.append("] <");
        String obj = sb.toString();
        Iterator<AnonymousClass2ac> it = this.A03.iterator();
        while (it.hasNext()) {
            obj = AnonymousClass006.A09(obj, HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, it.next().A0j);
        }
        return AnonymousClass006.A07(obj, " >");
    }

    public AnonymousClass2ao(int i) {
        int i2 = A05;
        A05 = i2 + 1;
        this.A00 = i2;
        this.A01 = i;
    }
}
