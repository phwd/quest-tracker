package defpackage;

import com.google.android.gms.signin.internal.zak;
import java.lang.ref.WeakReference;

/* renamed from: OA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OA1 extends JB1 {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f8605a;

    public OA1(JA1 ja1) {
        this.f8605a = new WeakReference(ja1);
    }

    @Override // defpackage.AbstractC5215vB1
    public final void L(zak zak) {
        JA1 ja1 = (JA1) this.f8605a.get();
        if (ja1 != null) {
            C2313eB1 eb1 = ja1.f8277a;
            eb1.e.sendMessage(eb1.e.obtainMessage(1, new RA1(ja1, ja1, zak)));
        }
    }
}
