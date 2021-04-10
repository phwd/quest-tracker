package X;

import android.util.Base64;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.1P5  reason: invalid class name */
public final class AnonymousClass1P5 {
    public int A00;
    public int A01;
    public String A02;
    @Nullable
    public List<InetAddress> A03;
    public List<String> A04;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AnonymousClass1P5 r5 = (AnonymousClass1P5) obj;
            if (!A00().equals(r5.A00()) || !this.A02.equals(r5.A02)) {
                return false;
            }
        }
        return true;
    }

    public final List<InetAddress> A00() {
        List<InetAddress> list = this.A03;
        ArrayList arrayList = list;
        if (list == null) {
            ArrayList arrayList2 = new ArrayList();
            for (String str : this.A04) {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getByAddress(Base64.decode(str, 0));
                } catch (IllegalArgumentException | UnknownHostException unused) {
                }
                if (inetAddress != null) {
                    arrayList2.add(inetAddress);
                }
            }
            this.A03 = arrayList2;
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public final int hashCode() {
        return (this.A02.hashCode() * 31) + A00().hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AE{'");
        sb.append(this.A02);
        sb.append('\'');
        sb.append(", ");
        sb.append(A00().toString());
        sb.append(", ");
        sb.append(this.A01);
        sb.append(", ");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass1P5() {
        this("", null, 0, 0);
    }

    public AnonymousClass1P5(String str, @Nullable List<InetAddress> list, int i, int i2) {
        this.A02 = str;
        this.A03 = list;
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (InetAddress inetAddress : list) {
                arrayList.add(Base64.encodeToString(inetAddress.getAddress(), 0));
            }
            this.A04 = arrayList;
        }
        this.A01 = i;
        this.A00 = i2;
    }
}
