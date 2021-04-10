package defpackage;

import java.util.Calendar;
import java.util.Objects;

/* renamed from: Hm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0460Hm extends AbstractC2032cb {
    public final /* synthetic */ View$OnClickListenerC0521Im i;

    public C0460Hm(View$OnClickListenerC0521Im im, C0399Gm gm) {
        this.i = im;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return Calendar.getInstance();
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Calendar calendar = (Calendar) obj;
        this.i.b0 = calendar.get(1);
        this.i.c0 = calendar.get(2) + 1;
        Objects.requireNonNull(this.i);
    }
}
