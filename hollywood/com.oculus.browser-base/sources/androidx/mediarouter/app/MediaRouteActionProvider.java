package androidx.mediarouter.app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaRouteActionProvider extends H2 {
    public final C3246jh0 c;
    public final C1874bf0 d;
    public C0629Kg0 e = C0629Kg0.f8380a;
    public C0930Pf0 f = C0930Pf0.f8704a;
    public MediaRouteButton g;

    public MediaRouteActionProvider(Context context) {
        super(context);
        this.c = C3246jh0.e(context);
        this.d = new C1874bf0(this);
    }

    @Override // defpackage.H2
    public boolean b() {
        return this.c.i(this.e, 1);
    }

    @Override // defpackage.H2
    public View c() {
        if (this.g != null) {
            Log.e("MRActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item...");
        }
        MediaRouteButton mediaRouteButton = new MediaRouteButton(this.f8130a, null);
        this.g = mediaRouteButton;
        if (true != mediaRouteButton.a0) {
            mediaRouteButton.a0 = true;
            mediaRouteButton.h();
        }
        this.g.e(this.e);
        MediaRouteButton mediaRouteButton2 = this.g;
        if (mediaRouteButton2.W) {
            mediaRouteButton2.W = false;
            mediaRouteButton2.c();
            mediaRouteButton2.b();
        }
        MediaRouteButton mediaRouteButton3 = this.g;
        C0930Pf0 pf0 = this.f;
        Objects.requireNonNull(mediaRouteButton3);
        if (pf0 != null) {
            mediaRouteButton3.M = pf0;
            this.g.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
            return this.g;
        }
        throw new IllegalArgumentException("factory must not be null");
    }

    @Override // defpackage.H2
    public boolean e() {
        MediaRouteButton mediaRouteButton = this.g;
        if (mediaRouteButton != null) {
            return mediaRouteButton.f();
        }
        return false;
    }

    public void i() {
        if (this.b != null && g()) {
            C0756Mi0 mi0 = this.b;
            b();
            C4616ri0 ri0 = mi0.f8497a.n;
            ri0.i = true;
            ri0.p(true);
        }
    }
}
