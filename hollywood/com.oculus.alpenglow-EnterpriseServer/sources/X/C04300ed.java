package X;

import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import java.util.List;

/* renamed from: X.0ed  reason: invalid class name and case insensitive filesystem */
public class C04300ed implements AbstractC004304x {
    public final /* synthetic */ View$OnKeyListenerC01900Mx A00;

    public C04300ed(View$OnKeyListenerC01900Mx r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC004304x
    public final void A6B(@NonNull C04280eZ r8, @NonNull MenuItem menuItem) {
        View$OnKeyListenerC01900Mx r1 = this.A00;
        Handler handler = r1.A0F;
        AnonymousClass03R r0 = null;
        handler.removeCallbacksAndMessages(null);
        List<AnonymousClass03R> list = r1.A0L;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (r8 == list.get(i).A01) {
                if (i != -1) {
                    int i2 = i + 1;
                    if (i2 < list.size()) {
                        r0 = list.get(i2);
                    }
                    handler.postAtTime(new AnonymousClass03Q(this, r0, menuItem, r8), r8, SystemClock.uptimeMillis() + 200);
                    return;
                } else {
                    return;
                }
            }
        }
    }

    @Override // X.AbstractC004304x
    public final void A6C(@NonNull C04280eZ r2, @NonNull MenuItem menuItem) {
        this.A00.A0F.removeCallbacksAndMessages(r2);
    }
}
