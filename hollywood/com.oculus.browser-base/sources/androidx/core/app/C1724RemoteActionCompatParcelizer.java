package androidx.core.app;

import android.app.PendingIntent;
import android.text.TextUtils;
import androidx.core.graphics.drawable.C1725IconCompat;
import java.util.Objects;

/* renamed from: androidx.core.app.RemoteActionCompatParcelizer  reason: case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1724RemoteActionCompatParcelizer {
    public static C1723RemoteActionCompat read(Ls1 ls1) {
        C1723RemoteActionCompat remoteActionCompat = new C1723RemoteActionCompat();
        Ns1 ns1 = remoteActionCompat.f9467a;
        if (ls1.h(1)) {
            ns1 = ls1.k();
        }
        remoteActionCompat.f9467a = (C1725IconCompat) ns1;
        remoteActionCompat.b = ls1.g(remoteActionCompat.b, 2);
        remoteActionCompat.c = ls1.g(remoteActionCompat.c, 3);
        remoteActionCompat.d = (PendingIntent) ls1.j(remoteActionCompat.d, 4);
        remoteActionCompat.e = ls1.f(remoteActionCompat.e, 5);
        remoteActionCompat.f = ls1.f(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(C1723RemoteActionCompat remoteActionCompat, Ls1 ls1) {
        Objects.requireNonNull(ls1);
        C1725IconCompat iconCompat = remoteActionCompat.f9467a;
        ls1.l(1);
        ls1.o(iconCompat);
        CharSequence charSequence = remoteActionCompat.b;
        ls1.l(2);
        Ms1 ms1 = (Ms1) ls1;
        TextUtils.writeToParcel(charSequence, ms1.e, 0);
        CharSequence charSequence2 = remoteActionCompat.c;
        ls1.l(3);
        TextUtils.writeToParcel(charSequence2, ms1.e, 0);
        ls1.n(remoteActionCompat.d, 4);
        boolean z = remoteActionCompat.e;
        ls1.l(5);
        ms1.e.writeInt(z ? 1 : 0);
        boolean z2 = remoteActionCompat.f;
        ls1.l(6);
        ms1.e.writeInt(z2 ? 1 : 0);
    }
}
