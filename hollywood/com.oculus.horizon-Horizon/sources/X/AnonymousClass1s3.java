package X;

import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

/* renamed from: X.1s3  reason: invalid class name */
public interface AnonymousClass1s3 {
    boolean canResize(AnonymousClass1qQ v, @Nullable AnonymousClass1pN v2, @Nullable AnonymousClass1p9 v3);

    boolean canTranscode(AnonymousClass1tL v);

    String getIdentifier();

    C10371se transcode(AnonymousClass1qQ v, OutputStream outputStream, @Nullable AnonymousClass1pN v2, @Nullable AnonymousClass1p9 v3, @Nullable AnonymousClass1tL v4, @Nullable Integer num) throws IOException;
}
