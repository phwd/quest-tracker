package X;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* renamed from: X.04H  reason: invalid class name */
public final class AnonymousClass04H extends AnonymousClass08f {
    public AnonymousClass04H(Context context) {
        super(context, "lib-assets", new File(context.getApplicationInfo().sourceDir), "^assets/lib/([^/]+)/([^/]+\\.so)$");
    }

    @Override // X.AnonymousClass0HU
    public final byte[] A09() throws IOException {
        return C03190cO.A01(((AnonymousClass08f) this).A00, this.A03);
    }
}
