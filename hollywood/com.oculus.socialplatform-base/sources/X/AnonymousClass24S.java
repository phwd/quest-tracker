package X;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;

/* renamed from: X.24S  reason: invalid class name */
public final class AnonymousClass24S {
    public int A00;
    public ArrayList<AnonymousClass251> A01;
    public Map<Short, Integer> A02;
    public int A03;

    public final AnonymousClass251 A00(short s) {
        int intValue;
        Integer num = this.A02.get(Short.valueOf(s));
        if (num == null || (intValue = num.intValue()) >= this.A03) {
            return null;
        }
        return this.A01.get(intValue);
    }

    public final void A01(AnonymousClass251 r4) {
        ArrayList<AnonymousClass251> arrayList = this.A01;
        int i = this.A03;
        arrayList.add(i, r4);
        this.A00 += r4.A01.length + 4;
        this.A03 = i + 1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("extensions{extensions=");
        sb.append(Arrays.toString(this.A01.toArray()));
        sb.append(", idx=");
        sb.append(this.A03);
        sb.append(", totalNetworkBytes=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass24S() {
        this.A03 = 0;
        this.A00 = 0;
        this.A01 = new ArrayList<>();
    }

    public AnonymousClass24S(byte[] bArr) throws AnonymousClass25A {
        int length = bArr.length;
        this.A02 = new HashMap();
        int i = 0;
        this.A00 = 0;
        this.A03 = 0;
        ArrayList arrayList = new ArrayList();
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        while (i < length) {
            short s = wrap.getShort();
            byte[] bArr2 = new byte[2];
            wrap.get(bArr2);
            int A012 = AnonymousClass24K.A01(bArr2);
            byte[] bArr3 = new byte[A012];
            wrap.get(bArr3);
            arrayList.add(new AnonymousClass251(s, bArr3));
            this.A02.put(Short.valueOf(s), Integer.valueOf(this.A03));
            this.A03++;
            i += A012 + 4;
        }
        if (i == length) {
            this.A00 = i;
            this.A01 = new ArrayList<>();
            this.A01 = new ArrayList<>(arrayList);
            return;
        }
        throw new AnonymousClass25A((byte) 80, new SSLException("Error while parsing extension"));
    }
}
