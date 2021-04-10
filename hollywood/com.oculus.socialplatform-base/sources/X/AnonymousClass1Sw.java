package X;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit.mime.TypedOutput;

/* renamed from: X.1Sw  reason: invalid class name */
public class AnonymousClass1Sw extends RequestBody {
    public final /* synthetic */ MediaType A00;
    public final /* synthetic */ TypedOutput A01;

    public AnonymousClass1Sw(MediaType mediaType, TypedOutput typedOutput) {
        this.A00 = mediaType;
        this.A01 = typedOutput;
    }

    @Override // okhttp3.RequestBody
    public final long contentLength() {
        return this.A01.length();
    }

    @Override // okhttp3.RequestBody
    public final MediaType contentType() {
        return this.A00;
    }

    @Override // okhttp3.RequestBody
    public final void writeTo(BufferedSink bufferedSink) throws IOException {
        this.A01.writeTo(bufferedSink.outputStream());
    }
}
