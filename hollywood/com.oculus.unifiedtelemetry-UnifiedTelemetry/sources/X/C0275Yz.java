package X;

import com.facebook.analytics2.logger.EventBatchListener;
import com.facebook.infer.annotation.Nullsafe;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.Yz  reason: case insensitive filesystem */
public final class C0275Yz extends GG<ByteArrayOutputStream> {
    @Override // X.GG
    @Nonnull
    public final GF A04(@Nullable String str, @Nullable GF gf) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Fz<T>.BatchLock A02 = C0271Yv.A01().A02(byteArrayOutputStream);
        if (A02.A0B(this)) {
            Iterator<EventBatchListener> it = this.A07.iterator();
            if (!it.hasNext()) {
                return new Z0(this, byteArrayOutputStream, A02);
            }
            it.next();
            throw null;
        }
        throw new IllegalStateException("Couldn't lock newly created batch");
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.GG
    @Nullable
    public final ByteArrayOutputStream A05() {
        Z0 z0 = this.A02;
        if (z0 == null) {
            return null;
        }
        return z0.A00;
    }

    public C0275Yz(int i, int i2, Fx fx, MG mg) {
        super(i, i2, fx, mg);
    }
}
