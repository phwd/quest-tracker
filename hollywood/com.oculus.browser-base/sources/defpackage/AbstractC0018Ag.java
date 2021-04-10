package defpackage;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaStatus;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.media.router.ChromeMediaRouterClient;

/* renamed from: Ag  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0018Ag {

    /* renamed from: a  reason: collision with root package name */
    public C2922hn f7686a;
    public final AbstractC5474wl b;
    public C2653gB c;
    public final GL0 d;
    public final List e = new ArrayList();

    public AbstractC0018Ag(AbstractC5474wl wlVar) {
        this.b = wlVar;
        this.d = new C5969zg(this, null);
    }

    public void a(C2922hn hnVar) {
        this.f7686a = hnVar;
        ML0 f = hnVar.f();
        if (f != null) {
            GL0 gl0 = this.d;
            SE0.e("Must be called from the main thread.");
            if (gl0 != null) {
                f.i.add(gl0);
            }
        }
    }

    public void b() {
        C2922hn hnVar = this.f7686a;
        if (hnVar != null) {
            ML0 f = hnVar.f();
            if (f != null) {
                GL0 gl0 = this.d;
                SE0.e("Must be called from the main thread.");
                if (gl0 != null) {
                    f.i.remove(gl0);
                }
            }
            this.f7686a = null;
        }
    }

    public void c() {
        AbstractC3776mn.a().b().b(true);
        AbstractC3776mn.a().d(null);
    }

    public List d() {
        ArrayList arrayList = new ArrayList();
        C2922hn hnVar = this.f7686a;
        if (hnVar != null && hnVar.a()) {
            CastDevice e2 = this.f7686a.e();
            if (e2.A(8)) {
                arrayList.add("audio_in");
            }
            if (e2.A(4)) {
                arrayList.add("audio_out");
            }
            if (e2.A(2)) {
                arrayList.add("video_in");
            }
            if (e2.A(1)) {
                arrayList.add("video_out");
            }
        }
        return arrayList;
    }

    public ML0 e() {
        if (h()) {
            return this.f7686a.f();
        }
        return null;
    }

    public String f() {
        if (!h()) {
            return null;
        }
        C2922hn hnVar = this.f7686a;
        Objects.requireNonNull(hnVar);
        SE0.e("Must be called from the main thread.");
        try {
            PI1 pi1 = (PI1) hnVar.b;
            Parcel d2 = pi1.d(3, pi1.c());
            String readString = d2.readString();
            d2.recycle();
            return readString;
        } catch (RemoteException unused) {
            NF1 nf1 = PS0.f8692a;
            Object[] objArr = {"getSessionId", DI1.class.getSimpleName()};
            if (!nf1.d()) {
                return null;
            }
            nf1.c("Unable to call %s on %s.", objArr);
            return null;
        }
    }

    public C1363Wh0 g() {
        C2653gB gBVar = this.c;
        if (gBVar != null) {
            return gBVar.b;
        }
        return null;
    }

    public boolean h() {
        C2922hn hnVar = this.f7686a;
        return hnVar != null && hnVar.a();
    }

    public void i(CastDevice castDevice, String str, String str2) {
        if ("urn:x-cast:com.google.cast.media".equals(str) && h()) {
            this.f7686a.f().a(this.f7686a.e(), "urn:x-cast:com.google.cast.media", str2);
        }
    }

    public void j() {
        for (AbstractC5799yg ygVar : this.e) {
            AbstractC5289vg vgVar = (AbstractC5289vg) ygVar;
            int g = vgVar.g();
            C5624xe0 a2 = AbstractC0196De0.a(g);
            if (a2 != null) {
                a2.b();
                AbstractC0196De0.f7901a.remove(g);
            }
            vgVar.F = null;
        }
    }

    public void k() {
        for (AbstractC5799yg ygVar : this.e) {
            AbstractC5289vg vgVar = (AbstractC5289vg) ygVar;
            Objects.requireNonNull(vgVar);
            C0013Ae0 ae0 = new C0013Ae0();
            ae0.b = false;
            C2653gB gBVar = vgVar.G.c;
            ae0.c = gBVar.d;
            ae0.d = gBVar.e;
            ae0.e = gBVar.f;
            ae0.j = 2;
            ae0.l = vgVar.f();
            ae0.f = R.drawable.f32290_resource_name_obfuscated_RES_2131231269;
            ae0.h = R.drawable.f28710_resource_name_obfuscated_RES_2131230911;
            ae0.k = vgVar.g();
            ae0.m = vgVar;
            vgVar.F = ae0;
            vgVar.h();
            ChromeMediaRouterClient chromeMediaRouterClient = ChromeMediaRouterClient.f10694a;
            C0074Be0 a2 = vgVar.F.a();
            Objects.requireNonNull(chromeMediaRouterClient);
            AbstractC1384Wr.a(a2);
        }
    }

    public void l() {
        MediaStatus d2;
        for (AbstractC5799yg ygVar : this.e) {
            AbstractC5289vg vgVar = (AbstractC5289vg) ygVar;
            if (!(vgVar.F == null || !vgVar.G.h() || (d2 = vgVar.G.e().d()) == null)) {
                int i = d2.f9645J;
                if (i == 3 || i == 2) {
                    C0013Ae0 ae0 = vgVar.F;
                    ae0.b = i != 2;
                    ae0.j = 3;
                } else {
                    vgVar.F.j = 2;
                }
                ChromeMediaRouterClient chromeMediaRouterClient = ChromeMediaRouterClient.f10694a;
                C0074Be0 a2 = vgVar.F.a();
                Objects.requireNonNull(chromeMediaRouterClient);
                AbstractC1384Wr.a(a2);
            }
        }
    }
}
