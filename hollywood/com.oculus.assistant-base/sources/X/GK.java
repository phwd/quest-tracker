package X;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

public final class GK extends ContentObserver {
    public final /* synthetic */ Uri A00;
    public final /* synthetic */ GO A01;
    public final /* synthetic */ C1420zC A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GK(GO go, Handler handler, Uri uri, C1420zC zCVar) {
        super(handler);
        this.A01 = go;
        this.A00 = uri;
        this.A02 = zCVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f1, code lost:
        if (((java.util.List) r9.get(r12)).size() <= 0) goto L_0x00f3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onChange(boolean r21, android.net.Uri r22) {
        /*
        // Method dump skipped, instructions count: 424
        */
        throw new UnsupportedOperationException("Method not decompiled: X.GK.onChange(boolean, android.net.Uri):void");
    }
}
