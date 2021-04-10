package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;

/* renamed from: ak0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1709ak0 extends QW implements AbstractC1552Zj0 {
    public C1709ak0(CoreImpl coreImpl, int i) {
        super(coreImpl, i);
    }

    @Override // defpackage.AbstractC1552Zj0
    public AbstractC1552Zj0 N() {
        return new C1709ak0(this);
    }

    @Override // defpackage.AbstractC1552Zj0
    public ResultAnd W(C1369Wj0 wj0) {
        CoreImpl coreImpl = this.G;
        Objects.requireNonNull(coreImpl);
        ResultAnd resultAnd = (ResultAnd) N.MEHdfwD0(coreImpl, this.F, wj0.f9606a);
        int i = resultAnd.f10995a;
        if (i == 0 || i == 17) {
            C1430Xj0 xj0 = (C1430Xj0) resultAnd.b;
            int[] iArr = xj0.b;
            if (iArr == null || iArr.length == 0) {
                xj0.c = new ArrayList(0);
            } else {
                xj0.c = new ArrayList(iArr.length);
                for (int i2 : iArr) {
                    xj0.c.add(new Rp1(coreImpl, i2));
                }
            }
            return resultAnd;
        }
        throw new C5475wl0(resultAnd.f10995a);
    }

    @Override // defpackage.AbstractC1552Zj0
    public void c(ByteBuffer byteBuffer, List list, C1491Yj0 yj0) {
        ByteBuffer byteBuffer2;
        int i;
        CoreImpl coreImpl = this.G;
        Objects.requireNonNull(coreImpl);
        if (list == null || list.isEmpty()) {
            byteBuffer2 = null;
        } else {
            byteBuffer2 = coreImpl.a(list.size() * 4);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                PW pw = (PW) it.next();
                byteBuffer2.putInt(pw.a() ? ((QW) pw).F : 0);
            }
            byteBuffer2.position(0);
        }
        int i2 = this.F;
        if (byteBuffer == null) {
            i = 0;
        } else {
            i = byteBuffer.limit();
        }
        int Mkun8eIV = N.Mkun8eIV(coreImpl, i2, byteBuffer, i, byteBuffer2, yj0.f9606a);
        if (Mkun8eIV != 0) {
            throw new C5475wl0(Mkun8eIV);
        } else if (list != null) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                PW pw2 = (PW) it2.next();
                if (pw2.a()) {
                    ((QW) pw2).F = 0;
                }
            }
        }
    }

    public C1709ak0(QW qw) {
        super(qw);
    }
}
