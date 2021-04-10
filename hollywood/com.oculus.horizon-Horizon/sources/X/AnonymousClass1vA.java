package X;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;

/* renamed from: X.1vA  reason: invalid class name */
public final class AnonymousClass1vA {
    public int A00;
    public ArrayList<C10851vk> A01;
    public Map<Short, Integer> A02;
    public int A03;

    public final C10851vk A00(short s) {
        int intValue;
        Integer num = this.A02.get(Short.valueOf(s));
        if (num == null || (intValue = num.intValue()) >= this.A03) {
            return null;
        }
        return this.A01.get(intValue);
    }

    public final void A01(C10851vk r4) {
        ArrayList<C10851vk> arrayList = this.A01;
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

    public AnonymousClass1vA() {
        this.A03 = 0;
        this.A00 = 0;
        this.A01 = new ArrayList<>();
    }

    public AnonymousClass1vA(byte[] bArr) throws AnonymousClass1v5 {
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
            int A012 = AnonymousClass1ut.A01(bArr2);
            byte[] bArr3 = new byte[A012];
            wrap.get(bArr3);
            arrayList.add(new C10851vk(s, bArr3));
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
        throw new AnonymousClass1v5((byte) 80, new SSLException("Error while parsing extension"));
    }
}
