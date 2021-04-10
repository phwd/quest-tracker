package X;

import com.facebook.cache.disk.DiskStorage;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1oV  reason: invalid class name */
public interface AnonymousClass1oV {
    Collection<DiskStorage.Entry> A3N() throws IOException;

    @Nullable
    AnonymousClass1oH A4I(String str, Object obj) throws IOException;

    AnonymousClass1oM A4r(String str, Object obj) throws IOException;

    boolean A4y();

    void A7g();

    long A88(AnonymousClass1oE v) throws IOException;
}
