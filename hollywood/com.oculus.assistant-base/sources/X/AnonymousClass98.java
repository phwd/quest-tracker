package X;

import java.util.LinkedList;

/* renamed from: X.98  reason: invalid class name */
public final class AnonymousClass98 {
    public int A00;
    public boolean A01 = false;
    public LinkedList A02 = new LinkedList();
    public final AnonymousClass98 A03;
    public final int A04;

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (AnonymousClass98 r1 = this; r1.A03 != null; r1 = r1.A03) {
            sb.appendCodePoint(r1.A04);
        }
        sb.reverse();
        return sb.toString();
    }

    public AnonymousClass98(int i, AnonymousClass98 r3) {
        this.A03 = r3;
        this.A04 = i;
    }
}
