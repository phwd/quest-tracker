package X;

import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

/* renamed from: X.0UK  reason: invalid class name */
public class AnonymousClass0UK implements TypedInput {
    public final /* synthetic */ AbstractC08210wB A00;

    public AnonymousClass0UK(AbstractC08210wB r1) {
        this.A00 = r1;
    }

    @Override // retrofit.mime.TypedInput
    public final InputStream in() throws IOException {
        return this.A00.A03().A4q();
    }

    @Override // retrofit.mime.TypedInput
    public final long length() {
        return this.A00.A00();
    }

    @Override // retrofit.mime.TypedInput
    public final String mimeType() {
        C08370wR A02 = this.A00.A02();
        if (A02 == null) {
            return null;
        }
        return A02.toString();
    }
}
