package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;

/* renamed from: Nc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0798Nc implements AbstractC2435ev1 {

    /* renamed from: a  reason: collision with root package name */
    public List f8558a = new ArrayList();
    public A31 b;
    public final /* synthetic */ Context c;
    public final /* synthetic */ C4935tb0 d;
    public final /* synthetic */ C0859Oc e;

    public C0798Nc(C0859Oc oc, Context context, C4935tb0 tb0) {
        this.e = oc;
        this.c = context;
        this.d = tb0;
    }

    @Override // defpackage.AbstractC2435ev1
    public void a(Callback callback) {
        A31 a31 = this.b;
        if (a31 != null) {
            callback.onResult(a31);
        } else {
            this.f8558a.add(callback);
        }
    }

    @Override // defpackage.AbstractC2435ev1
    public void b() {
        P21 f0 = P21.f0();
        try {
            C5836ys0 ys0 = new C5836ys0(this.c);
            f0.close();
            ys0.setVisibility(8);
            ys0.setClipToPadding(false);
            C6006zs0 zs0 = new C6006zs0(this.d);
            ys0.q0(zs0);
            zs0.v(0, new C5447wc(), new C0323Fg(new C0250Ec()));
            zs0.v(1, new C0311Fc(), new C1826bK());
            zs0.v(2, new C0372Gc(), new C0323Fg(new C0433Hc()));
            zs0.v(3, new C0494Ic(), new C0323Fg(new C0555Jc()));
            zs0.v(4, new C0616Kc(), new C0323Fg(new C0677Lc()));
            zs0.v(5, new C0737Mc(), new C0323Fg(new C5617xc()));
            C5787yc ycVar = new C5787yc(this);
            C4305ps0 ps0 = this.e.G;
            ps0.getClass();
            zs0.v(6, ycVar, new C5957zc(ps0));
            zs0.v(8, new C0006Ac(), new C0067Bc());
            zs0.v(7, new C0128Cc(), new C0189Dc());
            this.b = new A31((ViewGroup) ((ViewStub) this.e.F.getRootView().findViewById(R.id.omnibox_results_container_stub)).inflate(), ys0);
            for (int i = 0; i < this.f8558a.size(); i++) {
                ((Callback) this.f8558a.get(i)).onResult(this.b);
            }
            this.f8558a = null;
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
