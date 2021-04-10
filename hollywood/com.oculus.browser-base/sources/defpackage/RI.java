package defpackage;

import J.N;
import java.util.List;
import java.util.Objects;

/* renamed from: RI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RI implements Runnable {
    public final TI F;

    public RI(TI ti) {
        this.F = ti;
    }

    public void run() {
        UI ui = this.F.e;
        List list = ui.L;
        H60 h60 = (H60) ui;
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = ((B60) list.get(i)).f7716a;
        }
        Objects.requireNonNull(T60.a());
        N.Mo7xRjdk(strArr);
        T60.d(8);
        h60.F.b();
    }
}
