package X;

import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.2b3  reason: invalid class name */
public class AnonymousClass2b3 implements Comparable {
    public AnonymousClass2as A00;
    public C15032at A01;
    public final /* synthetic */ AnonymousClass2as A02;

    public AnonymousClass2b3(AnonymousClass2as r1, AnonymousClass2as r2) {
        this.A02 = r1;
        this.A00 = r2;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.A01.A04 - ((C15032at) obj).A04;
    }

    public final String toString() {
        String str = "[ ";
        if (this.A01 != null) {
            int i = 0;
            do {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(this.A01.A0A[i]);
                sb.append(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
                str = sb.toString();
                i++;
            } while (i < 9);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("] ");
        sb2.append(this.A01);
        return sb2.toString();
    }
}
