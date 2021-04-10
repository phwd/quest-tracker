package X;

import android.content.IntentFilter;

/* renamed from: X.Wl  reason: case insensitive filesystem */
public class C0409Wl extends AbstractC0946pO {
    public IntentFilter A00;
    public final AnonymousClass0m A01;

    public C0409Wl(String str, JJ jj) {
        AnonymousClass0m r0 = new AnonymousClass0m(1);
        this.A01 = r0;
        if (str != null) {
            r0.put(str, jj);
            return;
        }
        throw new NullPointerException("Object is null!");
    }
}
