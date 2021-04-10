package X;

import com.oculus.localmedia.MediaProviderUtils;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

/* renamed from: X.0kM  reason: invalid class name */
public final class AnonymousClass0kM {
    public static final Charset A02 = Charset.forName("UTF-8");
    public int A00 = 0;
    public ByteArrayOutputStream A01;

    public final void A00(String str, byte b) throws AnonymousClass0kL {
        if (this.A00 < 255) {
            byte[] bytes = str.getBytes(A02);
            int length = bytes.length;
            if (length <= 255) {
                this.A01.write(b);
                this.A01.write(length & MediaProviderUtils.JPEG_HEADER);
                this.A01.write(bytes, 0, length);
                this.A00++;
                return;
            }
            throw new AnonymousClass0kL("String size (UTF-8 encoded) cannot exceed 255");
        }
        throw new AnonymousClass0kL("Total number of entries cannot exceed 255");
    }

    public AnonymousClass0kM() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.A01 = byteArrayOutputStream;
        byteArrayOutputStream.write(-5);
        this.A01.write(0);
    }
}
