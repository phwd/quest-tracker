package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0ib  reason: invalid class name and case insensitive filesystem */
public final class C02340ib extends RequestBody {
    public static final MediaType A02 = MediaType.parse("application/octet-stream");
    public final MediaType A00;
    public final byte[] A01;

    @Override // okhttp3.RequestBody
    public final long contentLength() {
        return (long) this.A01.length;
    }

    @Override // okhttp3.RequestBody
    public final void writeTo(BufferedSink bufferedSink) throws IOException {
        bufferedSink.write(this.A01);
    }

    public C02340ib(byte[] bArr, @Nullable String str) {
        MediaType mediaType;
        this.A01 = bArr;
        if (str != null) {
            mediaType = MediaType.parse(str);
        } else {
            mediaType = A02;
        }
        this.A00 = mediaType;
    }

    @Override // okhttp3.RequestBody
    public final MediaType contentType() {
        return this.A00;
    }
}
