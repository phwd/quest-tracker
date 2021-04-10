package X;

import java.io.IOException;
import java.util.List;
import okhttp3.Connection;

public interface L3 {
    public int A00;
    public final C0362dj A01;
    public final L6 A02;
    public final C0350dM A03;
    public final dL A04;
    public final int A05;
    public final List<Cdo> A06;

    final default C0359dg A00(C0362dj djVar) throws IOException {
        return A01(djVar, this.A03, this.A04, this.A02);
    }

    final default C0359dg A01(C0362dj djVar, C0350dM dMVar, dL dLVar, L6 l6) throws IOException {
        String str;
        StringBuilder sb;
        int i = this.A05;
        List<Cdo> list = this.A06;
        if (i < list.size()) {
            int i2 = this.A00 + 1;
            this.A00 = i2;
            dL dLVar2 = this.A04;
            if (dLVar2 != null) {
                C0367dp dpVar = djVar.A03;
                String str2 = dpVar.A02;
                C0367dp dpVar2 = this.A02.A0D.A02.A0A;
                if (!str2.equals(dpVar2.A02) || dpVar.A00 != dpVar2.A00) {
                    sb = new StringBuilder();
                    sb.append("network interceptor ");
                    sb.append(list.get(i - 1));
                    sb.append(" must retain the same host and port");
                    str = sb.toString();
                    throw new IllegalStateException(str);
                }
            }
            if (dLVar2 == null || i2 <= 1) {
                L3 l3 = new L3(list, dMVar, dLVar, l6, i + 1, djVar);
                Cdo doVar = list.get(i);
                C0359dg A38 = doVar.A38(l3);
                if (dLVar != null && i + 1 < list.size() && l3.A00 != 1) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("network interceptor ");
                    sb2.append(doVar);
                    sb2.append(" must call proceed() exactly once");
                    str = sb2.toString();
                    throw new IllegalStateException(str);
                } else if (A38 != null) {
                    return A38;
                } else {
                    StringBuilder sb3 = new StringBuilder("interceptor ");
                    sb3.append(doVar);
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

    default L3(List<Cdo> list, C0350dM dMVar, dL dLVar, Connection connection, int i, C0362dj djVar) {
        this.A06 = list;
        this.A02 = connection;
        this.A03 = dMVar;
        this.A04 = dLVar;
        this.A05 = i;
        this.A01 = djVar;
    }
}
