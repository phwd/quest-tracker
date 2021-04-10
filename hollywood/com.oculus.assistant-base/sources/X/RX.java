package X;

import java.util.ArrayList;
import java.util.List;

public final class RX {
    public final Object A00;
    public final List A01 = new ArrayList();

    public final void A00(String str, Object obj) {
        List list = this.A01;
        RZ.A01(str);
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + valueOf.length());
        sb.append(str);
        sb.append("=");
        sb.append(valueOf);
        list.add(sb.toString());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(this.A00.getClass().getSimpleName());
        sb.append('{');
        List list = this.A01;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append((String) list.get(i));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public RX(Object obj) {
        RZ.A01(obj);
        this.A00 = obj;
    }
}
