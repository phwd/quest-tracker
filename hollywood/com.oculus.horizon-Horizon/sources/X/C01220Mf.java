package X;

import java.io.IOException;
import java.util.List;
import okhttp3.Connection;

/* renamed from: X.0Mf  reason: invalid class name and case insensitive filesystem */
public final class C01220Mf implements AbstractC08390wT {
    public int A00;
    public final C01250Mm A01;
    public final C08090vs A02;
    public final AbstractC08080vr A03;
    public final int A04;
    public final List<AbstractC08380wS> A05;
    public final C08330wN A06;

    public final C08220wC A00(C08330wN r16, C08090vs r17, AbstractC08080vr r18, C01250Mm r19) throws IOException {
        String str;
        StringBuilder sb;
        int i = this.A04;
        List<AbstractC08380wS> list = this.A05;
        if (i < list.size()) {
            int i2 = this.A00 + 1;
            this.A00 = i2;
            AbstractC08080vr r7 = this.A03;
            if (r7 != null) {
                C08400wU r3 = r16.A03;
                String str2 = r3.A02;
                C08400wU r2 = this.A01.A0D.A02.A0A;
                if (!str2.equals(r2.A02) || r3.A00 != r2.A00) {
                    sb = new StringBuilder();
                    sb.append("network interceptor ");
                    sb.append(list.get(i - 1));
                    sb.append(" must retain the same host and port");
                    str = sb.toString();
                    throw new IllegalStateException(str);
                }
            }
            if (r7 == null || i2 <= 1) {
                C01220Mf r8 = new C01220Mf(list, r17, r18, r19, i + 1, r16);
                AbstractC08380wS r22 = list.get(i);
                C08220wC intercept = r22.intercept(r8);
                if (r18 != null && i + 1 < list.size() && r8.A00 != 1) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("network interceptor ");
                    sb2.append(r22);
                    sb2.append(" must call proceed() exactly once");
                    str = sb2.toString();
                    throw new IllegalStateException(str);
                } else if (intercept != null) {
                    return intercept;
                } else {
                    StringBuilder sb3 = new StringBuilder("interceptor ");
                    sb3.append(r22);
                    sb3.append(" returned null");
                    throw new NullPointerException(sb3.toString());
                }
            } else {
                sb = new StringBuilder();
                sb.append("network interceptor ");
                sb.append(list.get(i - 1));
                sb.append(" must call proceed() exactly once");
                str = sb.toString();
                throw new IllegalStateException(str);
            }
        } else {
            throw new AssertionError();
        }
    }

    @Override // X.AbstractC08390wT
    public final C08220wC A7Z(C08330wN r4) throws IOException {
        return A00(r4, this.A02, this.A03, this.A01);
    }

    public C01220Mf(List<AbstractC08380wS> list, C08090vs r2, AbstractC08080vr r3, Connection connection, int i, C08330wN r6) {
        this.A05 = list;
        this.A01 = connection;
        this.A02 = r2;
        this.A03 = r3;
        this.A04 = i;
        this.A06 = r6;
    }

    @Override // X.AbstractC08390wT
    public final C08330wN A8H() {
        return this.A06;
    }
}
