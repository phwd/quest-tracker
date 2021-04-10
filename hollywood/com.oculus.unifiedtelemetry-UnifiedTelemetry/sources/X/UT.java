package X;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public final class UT extends AnonymousClass9L {
    public final Charset A00;
    public final /* synthetic */ AnonymousClass9F A01;

    public UT(AnonymousClass9F r2, Charset charset) {
        this.A01 = r2;
        if (charset != null) {
            this.A00 = charset;
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass9L
    public final Writer A00() throws IOException {
        return new OutputStreamWriter(this.A01.A00(), this.A00);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A01.toString());
        sb.append(".asCharSink(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }
}
