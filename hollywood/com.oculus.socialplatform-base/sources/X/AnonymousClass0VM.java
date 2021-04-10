package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0VM  reason: invalid class name */
public abstract class AnonymousClass0VM implements Closeable, AbstractC01000Pb, AnonymousClass0mX {
    public static final String[] A01 = {"encoded_size", "encoded_width", "encoded_height", "uri_source", "image_format", "bitmap_config"};
    public Map<String, Object> A00 = new HashMap();

    public abstract int A00();

    public abstract boolean A03();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public final void A02(Map<String, Object> map) {
        if (map != null) {
            String[] strArr = A01;
            for (String str : strArr) {
                Object obj = map.get(str);
                if (obj != null) {
                    this.A00.put(str, obj);
                }
            }
        }
    }

    public C03410mW A01() {
        return C03410mW.A03;
    }

    @Override // X.AbstractC01000Pb
    @Nonnull
    public final Map<String, Object> A3v() {
        return this.A00;
    }

    @Override // java.lang.Object
    public final void finalize() throws Throwable {
        if (!A03()) {
            AnonymousClass0J5.A05("CloseableImage", "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }
}
