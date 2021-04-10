package X;

import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

/* renamed from: X.0Pt  reason: invalid class name and case insensitive filesystem */
public interface AbstractC01070Pt {
    boolean canResize(AnonymousClass0PZ v, @Nullable AnonymousClass0PO v2, @Nullable AnonymousClass0PL v3);

    boolean canTranscode(AnonymousClass0Oj v);

    String getIdentifier();

    AnonymousClass0Ps transcode(AnonymousClass0PZ v, OutputStream outputStream, @Nullable AnonymousClass0PO v2, @Nullable AnonymousClass0PL v3, @Nullable AnonymousClass0Oj v4, @Nullable Integer num) throws IOException;
}
