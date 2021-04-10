package X;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit.mime.TypedInput;

/* renamed from: X.1Sv  reason: invalid class name */
public class AnonymousClass1Sv implements TypedInput {
    public final /* synthetic */ ResponseBody A00;

    public AnonymousClass1Sv(ResponseBody responseBody) {
        this.A00 = responseBody;
    }

    @Override // retrofit.mime.TypedInput
    public final InputStream in() throws IOException {
        return this.A00.byteStream();
    }

    @Override // retrofit.mime.TypedInput
    public final long length() {
        return this.A00.contentLength();
    }

    @Override // retrofit.mime.TypedInput
    public final String mimeType() {
        MediaType contentType = this.A00.contentType();
        if (contentType == null) {
            return null;
        }
        return contentType.toString();
    }
}
