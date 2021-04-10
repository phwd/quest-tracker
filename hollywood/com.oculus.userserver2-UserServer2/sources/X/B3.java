package X;

import java.io.IOException;

public abstract class B3 extends Rn {
    public AnonymousClass9p A00;

    public B3(int i) {
        super(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001d, code lost:
        if (r3 == null) goto L_0x001f;
     */
    @Override // X.Rn
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass9p A04() throws java.io.IOException, X.C0124Ro {
        /*
        // Method dump skipped, instructions count: 139
        */
        throw new UnsupportedOperationException("Method not decompiled: X.B3.A04():X.9p");
    }

    @Override // X.Rn
    public final String A08() throws IOException, C0124Ro {
        return ((AnonymousClass6z) this).A02.A01;
    }

    @Override // X.Rn
    public final String A09() throws IOException, C0124Ro {
        Object obj;
        AnonymousClass6z r4 = (AnonymousClass6z) this;
        AnonymousClass9p r3 = ((B3) r4).A00;
        if (r3 == AnonymousClass9p.VALUE_STRING || r3 == AnonymousClass9p.FIELD_NAME) {
            C00239v r0 = r4.A03;
            obj = r0.A02[r4.A00];
            if (obj instanceof String) {
                return (String) obj;
            }
        } else if (r3 == null) {
            return null;
        } else {
            int i = AnonymousClass9t.A00[r3.ordinal()];
            if (i != 7 && i != 8) {
                return r3.asString();
            }
            C00239v r02 = r4.A03;
            obj = r02.A02[r4.A00];
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    @Override // java.io.Closeable, X.Rn, java.lang.AutoCloseable
    public final void close() throws IOException {
        AnonymousClass6z r1 = (AnonymousClass6z) this;
        if (!r1.A04) {
            r1.A04 = true;
        }
    }

    @Override // X.Rn
    public final int A01() throws IOException, C0124Ro {
        String A09 = A09();
        if (A09 == null) {
            return 0;
        }
        return A09.length();
    }

    @Override // X.Rn
    public final int A02() throws IOException, C0124Ro {
        return 0;
    }

    @Override // X.Rn
    public final boolean A0B() {
        return false;
    }

    @Override // X.Rn
    public final char[] A0C() throws IOException, C0124Ro {
        String A09 = A09();
        if (A09 == null) {
            return null;
        }
        return A09.toCharArray();
    }
}
