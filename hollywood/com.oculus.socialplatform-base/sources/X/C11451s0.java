package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.1s0  reason: invalid class name and case insensitive filesystem */
public class C11451s0 extends BroadcastReceiver {
    public final /* synthetic */ AbstractC11341rp A00;

    public C11451s0(AbstractC11341rp r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        AnonymousClass1rJ r1;
        AbstractC11341rp r12 = this.A00;
        if (!(r12 instanceof C11311rm)) {
            r1 = ((AnonymousClass1YL) r12).A01;
        } else {
            r1 = ((C11311rm) r12).A01;
        }
        AnonymousClass1rJ.A09(r1, true);
    }
}
