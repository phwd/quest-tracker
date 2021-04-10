package X;

import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

/* renamed from: X.0Vq  reason: invalid class name */
public class AnonymousClass0Vq implements TypedInput {
    public final /* synthetic */ AbstractC05650kC A00;

    public AnonymousClass0Vq(AbstractC05650kC r1) {
        this.A00 = r1;
    }

    @Override // retrofit.mime.TypedInput
    public final InputStream in() throws IOException {
        return this.A00.A02().A5H();
    }

    @Override // retrofit.mime.TypedInput
    public final long length() {
        return this.A00.A00();
    }

    @Override // retrofit.mime.TypedInput
    public final String mimeType() {
        C05820lT A01 = this.A00.A01();
        if (A01 == null) {
            return null;
        }
        return A01.toString();
    }
}
