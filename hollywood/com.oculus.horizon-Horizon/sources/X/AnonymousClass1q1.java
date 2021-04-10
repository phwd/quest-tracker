package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1q1  reason: invalid class name */
public abstract class AnonymousClass1q1 implements Closeable {
    public static final String[] A01 = {"encoded_size", "encoded_width", "encoded_height", "uri_source", "image_format", "bitmap_config"};
    public Map<String, Object> A00 = new HashMap();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        AnonymousClass1qa<Bitmap> r1;
        AnonymousClass1qH r2 = (AnonymousClass1qH) this;
        synchronized (r2) {
            r1 = r2.A00;
            r2.A00 = null;
            r2.A04 = null;
        }
        if (r1 != null) {
            r1.close();
        }
    }

    @Override // java.lang.Object
    public final void finalize() throws Throwable {
        boolean z;
        AnonymousClass1qH r2 = (AnonymousClass1qH) this;
        synchronized (r2) {
            z = false;
            if (r2.A00 == null) {
                z = true;
            }
        }
        if (!z) {
            C01080Kb.A05("CloseableImage", "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }
}
