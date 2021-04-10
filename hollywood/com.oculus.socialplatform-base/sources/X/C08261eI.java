package X;

import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.IOException;
import java.nio.ByteBuffer;

/* renamed from: X.1eI  reason: invalid class name and case insensitive filesystem */
public class C08261eI implements AbstractC08271eJ {
    public final /* synthetic */ ByteBuffer A00;

    public C08261eI(ByteBuffer byteBuffer) {
        this.A00 = byteBuffer;
    }

    @Override // X.AbstractC08271eJ
    public final ImageHeaderParser$ImageType A56(AbstractC08251eH r2) throws IOException {
        return r2.A58(this.A00);
    }
}
