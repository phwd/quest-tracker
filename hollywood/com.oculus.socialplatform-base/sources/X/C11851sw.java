package X;

import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import java.util.List;

/* renamed from: X.1sw  reason: invalid class name and case insensitive filesystem */
public class C11851sw implements AnonymousClass1tS {
    public final /* synthetic */ View$OnKeyListenerC11671sd A00;

    public C11851sw(View$OnKeyListenerC11671sd r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1tS
    public final void A7C(@NonNull C11581sN r8, @NonNull MenuItem menuItem) {
        View$OnKeyListenerC11671sd r1 = this.A00;
        Handler handler = r1.A0F;
        AnonymousClass1tW r0 = null;
        handler.removeCallbacksAndMessages(null);
        List<AnonymousClass1tW> list = r1.A0M;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (r8 == list.get(i).A01) {
                if (i != -1) {
                    int i2 = i + 1;
                    if (i2 < list.size()) {
                        r0 = list.get(i2);
                    }
                    handler.postAtTime(new AnonymousClass1t3(this, r0, menuItem, r8), r8, SystemClock.uptimeMillis() + 200);
                    return;
                } else {
                    return;
                }
            }
        }
    }

    @Override // X.AnonymousClass1tS
    public final void A7D(@NonNull C11581sN r2, @NonNull MenuItem menuItem) {
        this.A00.A0F.removeCallbacksAndMessages(r2);
    }
}
