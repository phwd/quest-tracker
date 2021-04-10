package defpackage;

import android.content.Context;
import java.util.Objects;

/* renamed from: IA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class IA1 implements Runnable {
    public final /* synthetic */ JA1 F;

    public IA1(JA1 ja1) {
        this.F = ja1;
    }

    public final void run() {
        JA1 ja1 = this.F;
        UV uv = ja1.d;
        Context context = ja1.c;
        Objects.requireNonNull(uv);
        AbstractC3729mW.a(context);
    }
}
