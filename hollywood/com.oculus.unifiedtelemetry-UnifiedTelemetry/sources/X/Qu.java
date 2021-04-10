package X;

import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

public class Qu implements TypedInput {
    public final /* synthetic */ AbstractC0358df A00;

    public Qu(AbstractC0358df dfVar) {
        this.A00 = dfVar;
    }

    @Override // retrofit.mime.TypedInput
    public final InputStream in() throws IOException {
        return this.A00.A02().A37();
    }

    @Override // retrofit.mime.TypedInput
    public final long length() {
        return this.A00.A00();
    }

    @Override // retrofit.mime.TypedInput
    public final String mimeType() {
        C0366dn A01 = this.A00.A01();
        if (A01 == null) {
            return null;
        }
        return A01.toString();
    }
}
