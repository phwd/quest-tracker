package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

/* renamed from: X.1SB  reason: invalid class name */
public class AnonymousClass1SB extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass1S4 A00;

    public AnonymousClass1SB(AnonymousClass1S4 r1) {
        this.A00 = r1;
    }

    public final void onReceive(@NonNull Context context, Intent intent) {
        AnonymousClass1S4 r2 = this.A00;
        boolean z = r2.A00;
        boolean A002 = AnonymousClass1S4.A00(context);
        r2.A00 = A002;
        if (z != A002) {
            AnonymousClass1SD r0 = r2.A02;
            if (A002) {
                synchronized (r0.A01) {
                    AnonymousClass1SC r3 = r0.A00;
                    for (AnonymousClass1h5 r1 : C08381eW.A03(r3.A02)) {
                        if (!r1.isComplete() && !r1.A5s()) {
                            r1.clear();
                            if (!r3.A00) {
                                r1.A1a();
                            } else {
                                r3.A01.add(r1);
                            }
                        }
                    }
                }
            }
        }
    }
}
