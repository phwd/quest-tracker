package X;

import com.facebook.assistant.oacr.Oacr;
import com.facebook.assistant.oacr.OacrUtil;
import java.nio.ByteBuffer;

/* renamed from: X.fp  reason: case insensitive filesystem */
public final class C0709fp implements Oacr.DeviceContextProvider {
    public final /* synthetic */ C0740gP A00;

    public C0709fp(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // com.facebook.assistant.oacr.Oacr.DeviceContextProvider
    public final ByteBuffer build() {
        return OacrUtil.serialize("com.facebook.messenger.assistant.thrift.DeviceContext", this.A00.A0t.A01().A04());
    }
}
