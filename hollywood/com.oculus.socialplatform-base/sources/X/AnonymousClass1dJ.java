package X;

import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* renamed from: X.1dJ  reason: invalid class name */
public final class AnonymousClass1dJ implements AnonymousClass1dN<InputStream, AnonymousClass1gA> {
    public final AnonymousClass1dN<ByteBuffer, AnonymousClass1gA> A00;
    public final AnonymousClass1hX A01;
    public final List<AbstractC08251eH> A02;

    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final AnonymousClass1fR<AnonymousClass1gA> A2V(@NonNull InputStream inputStream, int i, int i2, @NonNull AnonymousClass1cO r8) throws IOException {
        InputStream inputStream2 = inputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream2.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (byteArray == null) {
                return null;
            }
            return this.A00.A2V(ByteBuffer.wrap(byteArray), i, i2, r8);
        } catch (IOException e) {
            if (!Log.isLoggable("StreamGifDecoder", 5)) {
                return null;
            }
            Log.w("StreamGifDecoder", "Error reading data from stream", e);
            return null;
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull InputStream inputStream, @NonNull AnonymousClass1cO r5) throws IOException {
        ImageHeaderParser$ImageType A012;
        InputStream inputStream2 = inputStream;
        if (!((Boolean) r5.A00(C09141gl.A01)).booleanValue()) {
            List<AbstractC08251eH> list = this.A02;
            AnonymousClass1hX r1 = this.A01;
            if (inputStream2 == null) {
                A012 = ImageHeaderParser$ImageType.UNKNOWN;
            } else {
                if (!inputStream2.markSupported()) {
                    inputStream2 = new C06741ax(inputStream2, r1);
                }
                inputStream2.mark(5242880);
                A012 = C08201eC.A01(list, new C08231eF(inputStream2));
            }
            if (A012 != ImageHeaderParser$ImageType.GIF) {
                return false;
            }
            return true;
        }
        return false;
    }

    public AnonymousClass1dJ(List<AbstractC08251eH> list, AnonymousClass1dN<ByteBuffer, AnonymousClass1gA> r2, ArrayPool arrayPool) {
        this.A02 = list;
        this.A00 = r2;
        this.A01 = arrayPool;
    }
}
