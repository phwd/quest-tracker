package X;

import java.io.IOException;
import java.util.List;
import okhttp3.Connection;

public interface EU {
    public int A00;
    public final XN A01;
    public final EX A02;
    public final X0 A03;
    public final AbstractC0174Wz A04;
    public final int A05;
    public final List<XS> A06;

    final default XK A00(XN xn) throws IOException {
        return A01(xn, this.A03, this.A04, this.A02);
    }

    final default XK A01(XN xn, X0 x0, AbstractC0174Wz wz, EX ex) throws IOException {
        String str;
        StringBuilder sb;
        int i = this.A05;
        List<XS> list = this.A06;
        if (i < list.size()) {
            int i2 = this.A00 + 1;
            this.A00 = i2;
            AbstractC0174Wz wz2 = this.A04;
            if (wz2 != null) {
                XT xt = xn.A03;
                String str2 = xt.A02;
                XT xt2 = this.A02.A0D.A02.A0A;
                if (!str2.equals(xt2.A02) || xt.A00 != xt2.A00) {
                    sb = new StringBuilder();
                    sb.append("network interceptor ");
                    sb.append(list.get(i - 1));
                    sb.append(" must retain the same host and port");
                    str = sb.toString();
                    throw new IllegalStateException(str);
                }
            }
            if (wz2 == null || i2 <= 1) {
                EU eu = new EU(list, x0, wz, ex, i + 1, xn);
                XS xs = list.get(i);
                XK A29 = xs.A29(eu);
                if (wz != null && i + 1 < list.size() && eu.A00 != 1) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("network interceptor ");
                    sb2.append(xs);
                    sb2.append(" must call proceed() exactly once");
                    str = sb2.toString();
                    throw new IllegalStateException(str);
                } else if (A29 != null) {
                    return A29;
                } else {
                    StringBuilder sb3 = new StringBuilder("interceptor ");
                    sb3.append(xs);
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

    default EU(List<XS> list, X0 x0, AbstractC0174Wz wz, Connection connection, int i, XN xn) {
        this.A06 = list;
        this.A02 = connection;
        this.A03 = x0;
        this.A04 = wz;
        this.A05 = i;
        this.A01 = xn;
    }
}
