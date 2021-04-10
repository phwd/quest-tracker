package org.chromium.components.payments;

import J.N;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PaymentRequestSpec {

    /* renamed from: a  reason: collision with root package name */
    public long f10875a;

    public PaymentRequestSpec(C1523Yz0 yz0, C2788gz0 gz0, Collection collection, String str) {
        ByteBuffer b = yz0.b();
        ByteBuffer b2 = gz0.b();
        ByteBuffer[] byteBufferArr = new ByteBuffer[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            byteBufferArr[i] = ((AbstractC4340q31) it.next()).b();
            i++;
        }
        this.f10875a = N.MAELBHG4(b, b2, byteBufferArr, str);
    }

    public Map a() {
        byte[][] MFWoiqvU;
        C4931ta taVar = new C4931ta();
        for (byte[] bArr : N.MFWoiqvU(this.f10875a)) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            CC[] ccArr = C1401Wz0.b;
            C1401Wz0 d = C1401Wz0.d(new C4709sD(new C2740gj0(wrap, new ArrayList())));
            taVar.put(d.d, d);
        }
        return taVar;
    }

    public Map b() {
        C4931ta taVar = new C4931ta();
        C2788gz0 c = c();
        if (c.g != null) {
            int i = 0;
            while (true) {
                C2959hz0[] hz0Arr = c.g;
                if (i >= hz0Arr.length) {
                    break;
                }
                C2959hz0 hz0 = hz0Arr[i];
                taVar.put(hz0.f.d, hz0);
                i++;
            }
        }
        return taVar;
    }

    public C2788gz0 c() {
        ByteBuffer wrap = ByteBuffer.wrap(N.MNkVMjnE(this.f10875a));
        CC[] ccArr = C2788gz0.b;
        return C2788gz0.d(new C4709sD(new C2740gj0(wrap, new ArrayList())));
    }

    public C1523Yz0 d() {
        ByteBuffer wrap = ByteBuffer.wrap(N.Mc7EwNM$(this.f10875a));
        CC[] ccArr = C1523Yz0.b;
        return C1523Yz0.d(new C4709sD(new C2740gj0(wrap, new ArrayList())));
    }

    public List e() {
        List list;
        C3337kB0[] kb0Arr = c().f;
        if (kb0Arr != null) {
            list = Arrays.asList(kb0Arr);
        } else {
            list = new ArrayList();
        }
        return Collections.unmodifiableList(list);
    }

    public C1035Qz0 f() {
        return c().d;
    }

    public boolean g() {
        return this.f10875a == 0;
    }

    public final long getNativePointer() {
        return this.f10875a;
    }
}
