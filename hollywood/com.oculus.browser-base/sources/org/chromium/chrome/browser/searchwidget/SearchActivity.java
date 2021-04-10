package org.chromium.chrome.browser.searchwidget;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SearchActivity extends AbstractActivityC0731Ma implements AbstractC4928tY0, AbstractC3035iQ0 {
    public static final Object o0 = new Object();
    public static C2693gQ0 p0;
    public Tab A0;
    public C1078Rq0 B0 = new C1078Rq0();
    public ViewGroup q0;
    public boolean r0;
    public String s0;
    public int t0;
    public String u0;
    public byte[] v0;
    public SearchActivityLocationBarLayout w0;
    public C3909na0 x0;
    public View$OnClickListenerC5098uY0 y0;
    public C3376kQ0 z0;

    public static C2693gQ0 A0() {
        synchronized (o0) {
            if (p0 == null) {
                p0 = new C2693gQ0();
            }
        }
        return p0;
    }

    public final /* synthetic */ boolean B0(String str, int i, String str2, byte[] bArr) {
        C0(str, i, str2, bArr);
        return true;
    }

    public void C0(String str, int i, String str2, byte[] bArr) {
        Intent intent;
        if (!this.r0) {
            this.s0 = str;
            this.t0 = i;
            this.u0 = str2;
            this.v0 = bArr;
            return;
        }
        if (TextUtils.isEmpty(str)) {
            intent = null;
        } else {
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(AbstractC1911br1.a(str).i()));
            intent2.setFlags(268959744);
            intent2.setClass(this, Lr.class);
            if (!(TextUtils.isEmpty(str2) || bArr == null || bArr.length == 0)) {
                intent2.putExtra("com.android.chrome.post_data_type", str2);
                intent2.putExtra("com.android.chrome.post_data", bArr);
            }
            S20.a(intent2);
            intent = intent2;
        }
        if (intent != null) {
            try {
                startActivity(intent, ActivityOptions.makeCustomAnimation(this, 17432576, 17432577).toBundle());
            } catch (ActivityNotFoundException unused) {
            }
            AbstractC3535lK0.a("SearchWidget.SearchMade");
            LocaleManager.getInstance().c();
            finish();
        }
    }

    @Override // defpackage.AbstractC4928tY0
    public View$OnClickListenerC5098uY0 U() {
        return this.y0;
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public C2746gl0 i0() {
        return new C2746gl0(new J9(this), 0);
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public C2971i3 j0() {
        return new C2010cQ0(this, this);
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public View n0() {
        return this.w0;
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma, defpackage.AbstractActivityC5319vq
    public void onDestroy() {
        Tab tab = this.A0;
        if (tab != null && tab.isInitialized()) {
            this.A0.destroy();
        }
        this.W.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        z0();
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public boolean p0(Intent intent) {
        Objects.requireNonNull(A0());
        return true;
    }

    @Override // defpackage.AbstractC3083ik, defpackage.AbstractActivityC0731Ma
    public void u() {
        super.u();
        C2351eQ0 eq0 = new C2351eQ0(this);
        WebContents a2 = AbstractC5342vx1.a(Profile.b(), false);
        C2128d61 d61 = new C2128d61();
        d61.e = this.b0;
        d61.d(1);
        d61.k = a2;
        d61.l = eq0;
        Tab a3 = d61.a();
        this.A0 = a3;
        a3.c(new LoadUrlParams("about:blank", 0));
        this.z0.b = this.A0;
        this.B0.m(Profile.a(a2));
        C1659aQ0 aq0 = new C1659aQ0(this);
        Objects.requireNonNull(A0());
        LocaleManager.getInstance().f(this, aq0);
    }

    @Override // defpackage.AbstractC3083ik
    public boolean w() {
        return true;
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public void y0() {
        this.y0 = new View$OnClickListenerC5098uY0(this, (ViewGroup) findViewById(16908290), null);
        this.z0 = new C3376kQ0(getResources());
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.f41260_resource_name_obfuscated_RES_2131624435, (ViewGroup) null, false);
        viewGroup.setOnClickListener(new View$OnClickListenerC2522fQ0(this));
        this.q0 = viewGroup;
        setContentView(viewGroup);
        SearchActivityLocationBarLayout searchActivityLocationBarLayout = (SearchActivityLocationBarLayout) this.q0.findViewById(R.id.search_location_bar);
        this.w0 = searchActivityLocationBarLayout;
        searchActivityLocationBarLayout.h0 = this;
        C3909na0 na0 = new C3909na0(this.w0, this.q0.findViewById(R.id.toolbar), this.B0, this.z0, null, new Uy1(getWindow()), this.b0, null, this.c0, null, null, this.Y, new YP0(this));
        this.x0 = na0;
        na0.i(true);
        z0();
        Objects.requireNonNull(A0());
        this.W.post(new ZP0(this));
        r0();
    }

    public final void z0() {
        SearchActivityLocationBarLayout searchActivityLocationBarLayout = this.w0;
        boolean d = U20.d(getIntent(), "org.chromium.chrome.browser.searchwidget.START_VOICE_SEARCH", false);
        String n = U20.n(getIntent(), "query");
        Oq1 oq1 = searchActivityLocationBarLayout.f11487J;
        if (n == null) {
            n = "";
        }
        oq1.k(Pq1.c(n), 0, 0);
        if (searchActivityLocationBarLayout.i0 || (d && !searchActivityLocationBarLayout.S)) {
            searchActivityLocationBarLayout.j0 = true;
        } else {
            searchActivityLocationBarLayout.y(d);
        }
    }
}
