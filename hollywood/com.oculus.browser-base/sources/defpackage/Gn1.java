package defpackage;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: Gn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Gn1 implements Runnable {
    public final Jn1 F;

    public Gn1(Jn1 jn1) {
        this.F = jn1;
    }

    public void run() {
        Jn1 jn1 = this.F;
        Objects.requireNonNull(jn1);
        ArrayList arrayList = new ArrayList();
        int i = Dn1.b().getInt("count", 0);
        for (int i2 = 0; i2 < i; i2++) {
            SharedPreferences b = Dn1.b();
            arrayList.add(b.getString("term_" + i2, ""));
        }
        PostTask.b(Zo1.f9374a, new In1(jn1, arrayList), 0);
    }
}
