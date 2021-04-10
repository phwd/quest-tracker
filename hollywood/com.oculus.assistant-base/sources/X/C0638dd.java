package X;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.dd  reason: case insensitive filesystem */
public final class C0638dd implements AnonymousClass19 {
    public final Notification.Builder A00;
    public final Bundle A01 = new Bundle();
    public final AnonymousClass1C A02;
    public final List A03 = new ArrayList();

    public final Notification A00() {
        Notification.Builder builder;
        AnonymousClass1D r2 = this.A02.A05;
        if (r2 != null && (r2 instanceof C0637dc)) {
            new Notification.BigTextStyle(A2J()).setBigContentTitle(null).bigText(((C0637dc) r2).A00);
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 26 || i >= 24) {
            builder = this.A00;
        } else {
            builder = this.A00;
            builder.setExtras(this.A01);
        }
        return builder.build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0124 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0638dd(X.AnonymousClass1C r14) {
        /*
        // Method dump skipped, instructions count: 686
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0638dd.<init>(X.1C):void");
    }

    @Override // X.AnonymousClass19
    public final Notification.Builder A2J() {
        return this.A00;
    }
}
