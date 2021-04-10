package defpackage;

import android.util.Size;
import org.chromium.base.UnguessableToken;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;

/* renamed from: KD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KD0 {

    /* renamed from: a  reason: collision with root package name */
    public ID0 f8351a;
    public ID0 b;
    public final UnguessableToken c;
    public final C1980cE0 d;
    public final Size e;
    public final AbstractC5900zD0 f;
    public final TD0 g;
    public final BS0 h;

    public KD0(UnguessableToken unguessableToken, C1980cE0 ce0, Size size, AbstractC5900zD0 zd0, TD0 td0, BS0 bs0) {
        this.c = unguessableToken;
        this.d = ce0;
        this.e = size;
        this.f = zd0;
        if (zd0 != null) {
            ((PlayerCompositorDelegateImpl) zd0).c.add(new JD0(this));
        }
        this.g = td0;
        this.h = bs0;
    }

    public ID0 a(boolean z) {
        ID0 id0 = this.f8351a;
        ID0 id02 = id0 == null ? this.b : id0;
        if (!z && id02 != null) {
            return id02;
        }
        if (id0 != null) {
            id0.c();
            this.f8351a = null;
        }
        ID0 id03 = new ID0(this.c, this.d.f(), Math.round(((float) this.d.b()) / 2.0f), this.d.c(), this.e, this.f, this, this.h);
        this.f8351a = id03;
        if (this.b != null) {
            return id03;
        }
        id03.j = null;
        c(id03);
        return this.b;
    }

    public void b(ID0 id0) {
        boolean z = true;
        if (id0 == this.b) {
            ((SD0) this.g).g.m(UD0.f9013a, id0.d);
            return;
        }
        if (id0.j != null) {
            z = false;
        }
        if (z) {
            c(id0);
        }
    }

    public void c(ID0 id0) {
        ID0 id02 = this.b;
        if (id02 != null) {
            id02.c();
        }
        this.b = id0;
        this.f8351a = null;
        SD0 sd0 = (SD0) this.g;
        ID0 a2 = sd0.m.a(false);
        sd0.l.reset();
        sd0.b(sd0.l, 1.0f);
        sd0.g.m(UD0.b, a2.b);
        sd0.g.m(UD0.c, sd0.i.a());
        sd0.g.m(UD0.f9013a, a2.d);
    }
}
