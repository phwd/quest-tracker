package X;

import com.facebook.cache.disk.DiskStorage;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lT  reason: invalid class name */
public interface AnonymousClass1lT {
    Collection<DiskStorage.Entry> A3t() throws IOException;

    @Nullable
    AnonymousClass1m2 A4n(String str, Object obj) throws IOException;

    AnonymousClass1mL A5m(String str, Object obj) throws IOException;

    boolean A5w();

    void A8k();

    long A93(C10361lt v) throws IOException;
}
