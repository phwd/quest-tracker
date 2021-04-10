package X;

import android.content.Intent;
import android.os.Build;

/* renamed from: X.3T  reason: invalid class name */
public final class AnonymousClass3T extends AbstractC1409yv {
    @Override // X.AbstractC1409yv, X.AnonymousClass8U
    public final void A1I(AnonymousClass8F r4, AnonymousClass8H r5) {
        String str;
        super.A1I(r4, r5);
        C00799i.A00.logFulfillment("Shutting down");
        YP.A00().A03();
        if (Build.VERSION.SDK_INT >= 28) {
            str = "com.android.internal.intent.action.REQUEST_SHUTDOWN";
        } else {
            str = "android.intent.action.ACTION_REQUEST_SHUTDOWN";
        }
        Intent intent = new Intent(str);
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        intent.setFlags(8388608);
        intent.setFlags(268435456);
        BX.A00().startActivity(intent);
    }
}
