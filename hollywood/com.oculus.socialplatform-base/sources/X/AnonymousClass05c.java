package X;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* renamed from: X.05c  reason: invalid class name */
public final class AnonymousClass05c extends AnonymousClass0HZ {
    public AnonymousClass05c(Context context) {
        super(context, "lib-assets", new File(context.getApplicationInfo().sourceDir), "^assets/lib/([^/]+)/([^/]+\\.so)$");
    }

    @Override // X.AnonymousClass0T3
    public final byte[] A09() throws IOException {
        return AnonymousClass0l4.A01(((AnonymousClass0HZ) this).A00, this.A03);
    }
}
