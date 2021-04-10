package X;

import com.facebook.hyperthrift.HyperThriftBase;
import com.facebook.messenger.assistant.thrift.MCValueUnion;

/* renamed from: X.md  reason: case insensitive filesystem */
public final class C0875md extends HyperThriftBase.Builder {
    public C0875md() {
        super(4);
    }

    public final MCValueUnion A04() {
        Object[] A03 = A03();
        MCValueUnion mCValueUnion = new MCValueUnion();
        mCValueUnion.A02("com.facebook.messenger.assistant.thrift.MCValueUnion", A03);
        mCValueUnion.A01(this.A00);
        return mCValueUnion;
    }
}
