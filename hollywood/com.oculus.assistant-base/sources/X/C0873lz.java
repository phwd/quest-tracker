package X;

import com.facebook.hyperthrift.HyperThriftBase;
import com.facebook.messenger.assistant.thrift.DeviceContext;

/* renamed from: X.lz  reason: case insensitive filesystem */
public final class C0873lz extends HyperThriftBase.Builder {
    public C0873lz() {
        super(53);
    }

    public final DeviceContext A04() {
        Object[] A03 = A03();
        HyperThriftBase.Builder.A01(A03, 9);
        HyperThriftBase.Builder.A01(A03, 22);
        HyperThriftBase.Builder.A01(A03, 30);
        HyperThriftBase.Builder.A01(A03, 34);
        HyperThriftBase.Builder.A01(A03, 37);
        HyperThriftBase.Builder.A01(A03, 46);
        HyperThriftBase.Builder.A01(A03, 47);
        HyperThriftBase.Builder.A01(A03, 51);
        DeviceContext deviceContext = new DeviceContext();
        deviceContext.A02("com.facebook.messenger.assistant.thrift.DeviceContext", A03);
        return deviceContext;
    }
}
