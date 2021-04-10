package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;

/* renamed from: HU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HU0 extends QW implements GU0 {
    public HU0(CoreImpl coreImpl, int i) {
        super(coreImpl, i);
    }

    @Override // defpackage.GU0
    public ByteBuffer B(long j, long j2, FU0 fu0) {
        CoreImpl coreImpl = this.G;
        Objects.requireNonNull(coreImpl);
        ResultAnd resultAnd = (ResultAnd) N.Mm6zKFIo(coreImpl, this.F, j, j2, fu0.f9606a);
        if (resultAnd.f10995a == 0) {
            return (ByteBuffer) resultAnd.b;
        }
        throw new C5475wl0(resultAnd.f10995a);
    }

    @Override // defpackage.GU0
    public void w(ByteBuffer byteBuffer) {
        CoreImpl coreImpl = this.G;
        Objects.requireNonNull(coreImpl);
        int MC_p8PYl = N.MC_p8PYl(coreImpl, byteBuffer);
        if (MC_p8PYl != 0) {
            throw new C5475wl0(MC_p8PYl);
        }
    }

    public HU0(QW qw) {
        super(qw);
    }
}
