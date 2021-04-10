package X;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* renamed from: X.04I  reason: invalid class name */
public final class AnonymousClass04I extends AnonymousClass08f {
    public final int A00;

    public AnonymousClass04I(Context context, File file, String str, int i) {
        super(context, str, file, "^lib/([^/]+)/([^/]+\\.so)$");
        this.A00 = i;
    }

    @Override // X.AnonymousClass08f, X.AnonymousClass0HU
    public final AbstractC03240cV A08() throws IOException {
        return new AnonymousClass0Hg(this, this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0042  */
    @Override // X.AnonymousClass0HU
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] A09() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass04I.A09():byte[]");
    }
}
