package X;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class US extends AnonymousClass9N {
    public final Charset A00;
    public final /* synthetic */ AnonymousClass9I A01;

    public US(AnonymousClass9I r2, Charset charset) {
        this.A01 = r2;
        if (charset != null) {
            this.A00 = charset;
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass9N
    public final Reader A00() throws IOException {
        return new InputStreamReader(this.A01.A01(), this.A00);
    }

    @Override // X.AnonymousClass9N
    public final String A01() throws IOException {
        return new String(this.A01.A02(), this.A00);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A01.toString());
        sb.append(".asCharSource(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }
}
