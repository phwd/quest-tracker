package X;

import java.io.IOException;
import java.util.List;
import okhttp3.Connection;

/* renamed from: X.0PX  reason: invalid class name */
public interface AnonymousClass0PX {
    public int A00;
    public final C05700ke A01;
    public final C01990Pw A02;
    public final C05360jA A03;
    public final AbstractC05270iw A04;
    public final int A05;
    public final List<AbstractC05850lW> A06;

    final default C05660kD A00(C05700ke r4) throws IOException {
        return A01(r4, this.A03, this.A04, this.A02);
    }

    final default C05660kD A01(C05700ke r16, C05360jA r17, AbstractC05270iw r18, C01990Pw r19) throws IOException {
        String str;
        StringBuilder sb;
        int i = this.A05;
        List<AbstractC05850lW> list = this.A06;
        if (i < list.size()) {
            int i2 = this.A00 + 1;
            this.A00 = i2;
            AbstractC05270iw r7 = this.A04;
            if (r7 != null) {
                C05890la r3 = r16.A03;
                String str2 = r3.A02;
                C05890la r2 = this.A02.A0D.A02.A09;
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
                AnonymousClass0PX r8 = new AnonymousClass0PX(list, r17, r18, r19, i + 1, r16);
                AbstractC05850lW r22 = list.get(i);
                C05660kD A5K = r22.A5K(r8);
                if (r18 != null && i + 1 < list.size() && r8.A00 != 1) {
                    str = "network interceptor " + r22 + " must call proceed() exactly once";
                    throw new IllegalStateException(str);
                } else if (A5K != null) {
                    return A5K;
                } else {
                    throw new NullPointerException("interceptor " + r22 + " returned null");
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

    default AnonymousClass0PX(List<AbstractC05850lW> list, C05360jA r2, AbstractC05270iw r3, Connection connection, int i, C05700ke r6) {
        this.A06 = list;
        this.A02 = connection;
        this.A03 = r2;
        this.A04 = r3;
        this.A05 = i;
        this.A01 = r6;
    }
}
