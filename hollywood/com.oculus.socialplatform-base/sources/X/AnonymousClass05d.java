package X;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* renamed from: X.05d  reason: invalid class name */
public final class AnonymousClass05d extends AnonymousClass0HZ {
    public final int A00;

    public AnonymousClass05d(Context context, File file, String str, int i) {
        super(context, str, file, "^lib/([^/]+)/([^/]+\\.so)$");
        this.A00 = i;
    }

    @Override // X.AnonymousClass0HZ, X.AnonymousClass0T3
    public final AnonymousClass0lB A08() throws IOException {
        return new AnonymousClass0TD(this, this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0042  */
    @Override // X.AnonymousClass0T3
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] A09() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass05d.A09():byte[]");
    }
}
