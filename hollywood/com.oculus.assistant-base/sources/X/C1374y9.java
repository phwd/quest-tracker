package X;

import android.content.Context;
import android.content.Intent;

/* renamed from: X.y9  reason: case insensitive filesystem */
public final class C1374y9 implements JJ {
    @Override // X.JJ
    public final synchronized void A4I(Context context, Intent intent, JK jk) {
        C0514bB.A02(context, "context");
        C0514bB.A02(intent, "intent");
        C0514bB.A02(jk, "receiver");
        HandlerC0422Wz.A06.A0B(intent.getStringExtra("display_message_extra_key"));
    }
}
