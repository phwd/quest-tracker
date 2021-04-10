package org.chromium.mojo.system.impl;

import J.N;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.chromium.mojo.system.ResultAnd;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CoreImpl implements SA {

    /* renamed from: a  reason: collision with root package name */
    public final ThreadLocal f10996a = new ThreadLocal();
    public final int b = N.MBIRtXF8(this, ByteBuffer.allocateDirect(8), 8);

    public CoreImpl(TA ta) {
    }

    public static ResultAnd newNativeCreationResult(int i, int i2, int i3) {
        return new ResultAnd(i, new UA(Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    public static ResultAnd newReadMessageResult(int i, byte[] bArr, int[] iArr) {
        C1430Xj0 xj0 = new C1430Xj0();
        if (i == 0) {
            xj0.f9230a = bArr;
            xj0.b = iArr;
        }
        return new ResultAnd(i, xj0);
    }

    public static ResultAnd newResultAndBuffer(int i, ByteBuffer byteBuffer) {
        return new ResultAnd(i, byteBuffer);
    }

    public static ResultAnd newResultAndInteger(int i, int i2) {
        return new ResultAnd(i, Integer.valueOf(i2));
    }

    public final ByteBuffer a(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i + this.b);
        int i2 = this.b;
        if (i2 != 0) {
            allocateDirect.position(i2);
            allocateDirect = allocateDirect.slice();
        }
        return allocateDirect.order(ByteOrder.nativeOrder());
    }

    public C1576Zv0 b(AbstractC1308Vj0 vj0) {
        ResultAnd resultAnd = (ResultAnd) N.MZhgS7uU(this, null);
        if (resultAnd.f10995a == 0) {
            return new C1576Zv0(new C1709ak0(this, ((Integer) ((UA) resultAnd.b).f9384a).intValue()), new C1709ak0(this, ((Integer) ((UA) resultAnd.b).b).intValue()));
        }
        throw new C5475wl0(resultAnd.f10995a);
    }
}
