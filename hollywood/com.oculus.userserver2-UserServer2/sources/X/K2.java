package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

public class K2 implements TypedInput {
    public final /* synthetic */ XJ A00;

    public K2(XJ xj) {
        this.A00 = xj;
    }

    @Override // retrofit.mime.TypedInput
    public final InputStream in() throws IOException {
        return this.A00.A01().A28();
    }

    @Override // retrofit.mime.TypedInput
    public final long length() {
        return this.A00.A00();
    }

    @Override // retrofit.mime.TypedInput
    public final String mimeType() {
        XR A002;
        XJ xj = this.A00;
        if (!(xj instanceof ET)) {
            A002 = ((C0050Ea) xj).A01;
        } else {
            String A003 = ((ET) xj).A00.A00(HttpRequestMultipart.CONTENT_TYPE);
            if (A003 == null) {
                return null;
            }
            A002 = XR.A00(A003);
        }
        if (A002 != null) {
            return A002.toString();
        }
        return null;
    }
}
