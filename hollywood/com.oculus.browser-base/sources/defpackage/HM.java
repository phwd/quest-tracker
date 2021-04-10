package defpackage;

import J.N;
import android.app.Activity;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.HashSet;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;
import org.chromium.chrome.browser.profiles.Profile;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: HM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HM implements Z9 {
    public final K21 F;
    public int G = -1;
    public final Activity H;
    public final boolean I;

    public HM(K21 k21, Activity activity, boolean z) {
        this.F = k21;
        this.H = activity;
        this.I = z;
        this.G = 0;
        FO fo = (FO) k21;
        fo.e = null;
        RecyclerView recyclerView = fo.f8014a.g;
        fo.c = recyclerView;
        recyclerView.setId(R.id.feed_stream_recycler_view);
        fo.c.setClipToPadding(false);
        fo.c.i(new CO(fo));
        AbstractC5750yK0 yk0 = fo.c.T;
        yk0.F.registerObserver(fo.f);
        f();
        a();
        ApplicationStatus.g(this, activity);
    }

    private boolean b() {
        int e = ApplicationStatus.e(this.H);
        int i = this.G;
        return (i == 0 || i == 4) && (e == 2 || e == 3);
    }

    public void a() {
        f();
        if (b() && e()) {
            this.G = 2;
        }
    }

    public void c() {
        if (this.G != 5) {
            d();
            this.G = 5;
            ApplicationStatus.h(this);
            FO fo = (FO) this.F;
            fo.e = null;
            FeedStreamSurface feedStreamSurface = fo.f8014a;
            if (feedStreamSurface.m) {
                feedStreamSurface.b();
            }
            HashSet hashSet = FeedStreamSurface.c;
            if (hashSet != null) {
                hashSet.remove(feedStreamSurface);
            }
            BO bo = feedStreamSurface.i;
            if (bo != null) {
                RecyclerView recyclerView = bo.F;
                if (recyclerView != null && recyclerView.getViewTreeObserver().isAlive()) {
                    bo.F.getViewTreeObserver().removeOnPreDrawListener(bo);
                }
                bo.F = null;
                bo.f7734J = null;
                bo.G = null;
                feedStreamSurface.i = null;
            }
            C0461Hm0 hm0 = (C0461Hm0) feedStreamSurface.h;
            ((C5757yO) hm0.f8180J).b.remove(hm0);
            hm0.F.f(0, hm0.f8180J.a());
            hm0.K.q0(null);
            hm0.K.t0(null);
            hm0.f8180J = null;
        }
    }

    public void d() {
        String str;
        View u;
        int i = this.G;
        if (i != 4 && i != 0 && i != 5) {
            if (i == 2) {
                this.G = 3;
            }
            this.G = 4;
            FO fo = (FO) this.F;
            fo.e = null;
            if (fo.f8014a.m) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) fo.c.U;
                if (linearLayoutManager != null) {
                    int k1 = linearLayoutManager.k1();
                    int l1 = linearLayoutManager.l1();
                    if (!(k1 == -1 || (u = linearLayoutManager.u(k1)) == null)) {
                        int top = u.getTop();
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("pos", k1);
                            jSONObject.put("lpos", l1);
                            jSONObject.put("off", top);
                            str = jSONObject.toString();
                        } catch (JSONException unused) {
                        }
                        fo.e = str;
                    }
                }
                str = "";
                fo.e = str;
            }
            FeedStreamSurface feedStreamSurface = fo.f8014a;
            if (feedStreamSurface.o) {
                feedStreamSurface.o = false;
                feedStreamSurface.d();
            }
        }
    }

    public final boolean e() {
        return !this.I || N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "ntp_snippets.list_visible");
    }

    public void f() {
        if (b() && e()) {
            this.G = 1;
            FeedStreamSurface feedStreamSurface = ((FO) this.F).f8014a;
            if (!feedStreamSurface.o) {
                feedStreamSurface.o = true;
                feedStreamSurface.d();
            }
        }
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 2) {
            f();
        } else if (i == 3) {
            a();
        } else if (i != 4) {
            if (i == 5) {
                d();
            } else if (i == 6) {
                c();
            }
        } else if (this.G == 2) {
            this.G = 3;
        }
    }
}
