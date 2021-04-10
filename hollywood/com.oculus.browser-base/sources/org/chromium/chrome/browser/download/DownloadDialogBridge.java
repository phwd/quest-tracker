package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.Map;
import org.chromium.chrome.browser.download.dialogs.DownloadLaterDialogView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.prefs.PrefService;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadDialogBridge implements TH, LH {

    /* renamed from: a  reason: collision with root package name */
    public long f10656a;
    public final VH b;
    public final NH c;
    public Context d;
    public C2746gl0 e;
    public long f;
    public int g;
    public String h;
    public PrefService i;
    public boolean j;
    public boolean k;
    public int l = 0;
    public long m = -1;

    public DownloadDialogBridge(long j2, NH nh, VH vh) {
        this.f10656a = j2;
        this.c = nh;
        this.b = vh;
    }

    public static PrefService a() {
        return Wr1.a(Profile.b());
    }

    public static int b() {
        return N.MzGf81GW(a().f10883a, "download.prompt_for_download_android");
    }

    public static DownloadDialogBridge create(long j2) {
        VH vh = new VH();
        C2329eH eHVar = new C2329eH();
        NH nh = new NH(eHVar);
        eHVar.d = nh;
        DownloadDialogBridge downloadDialogBridge = new DownloadDialogBridge(j2, nh, vh);
        nh.M = downloadDialogBridge;
        vh.F = downloadDialogBridge;
        return downloadDialogBridge;
    }

    public static void f(int i2) {
        N.MPBZLcVx(a().f10883a, "download.prompt_for_download_android", i2);
    }

    public final void c() {
        long j2 = this.f10656a;
        if (j2 != 0) {
            N.M9BtabC7(j2, this);
        }
    }

    public final void d() {
        long j2 = this.f10656a;
        if (j2 != 0) {
            N.Molx_ess(j2, this, this.h, this.l == 1, this.m);
        }
    }

    public void destroy() {
        this.f10656a = 0;
        NH nh = this.c;
        ZH0 zh0 = nh.L;
        if (zh0 != null) {
            zh0.b();
        }
        C2746gl0 gl0 = nh.I;
        if (gl0 != null) {
            gl0.b(nh.K, 4);
        }
        C2329eH eHVar = (C2329eH) nh.N;
        DatePickerDialog datePickerDialog = eHVar.f9844a;
        if (datePickerDialog != null) {
            datePickerDialog.dismiss();
        }
        KI ki = eHVar.c;
        if (ki != null) {
            ki.dismiss();
        }
        VH vh = this.b;
        C2746gl0 gl02 = vh.I;
        if (gl02 != null) {
            gl02.b(vh.G, 4);
        }
    }

    public void e(String str) {
        this.h = str;
        if (this.g == 6) {
            AbstractC3100ip1.f10165a.a("MobileDownload.Location.Dialog.SuggestionSelected", !str.equals(N.M4fixBWD()));
        }
        if (!this.j) {
            d();
            return;
        }
        this.j = false;
        g();
    }

    public final void g() {
        int MzGf81GW = N.MzGf81GW(this.i.f10883a, "download.download_later_prompt_status");
        Map c2 = UH0.c(OH.g);
        OH0 oh0 = OH.f8615a;
        NH nh = this.c;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = nh;
        c2.put(oh0, lh0);
        NH0 nh0 = OH.b;
        int i2 = this.l;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = i2;
        c2.put(nh0, jh0);
        NH0 nh02 = OH.c;
        JH0 jh02 = new JH0(null);
        jh02.f8282a = MzGf81GW;
        c2.put(nh02, jh02);
        if (this.k) {
            TH0 th0 = OH.e;
            String string = this.d.getResources().getString(R.string.f54520_resource_name_obfuscated_RES_2131952769);
            LH0 lh02 = new LH0(null);
            lh02.f8415a = string;
            c2.put(th0, lh02);
        }
        NH nh2 = this.c;
        Context context = this.d;
        C2746gl0 gl0 = this.e;
        PrefService prefService = this.i;
        UH0 uh0 = new UH0(c2, null);
        if (context == null || gl0 == null) {
            nh2.f(null, 8);
        } else {
            nh2.H = context;
            nh2.I = gl0;
            nh2.f8537J = prefService;
            nh2.F = uh0;
            DownloadLaterDialogView downloadLaterDialogView = (DownloadLaterDialogView) LayoutInflater.from(context).inflate(R.layout.f38050_resource_name_obfuscated_RES_2131624114, (ViewGroup) null);
            nh2.G = downloadLaterDialogView;
            nh2.L = new ZH0(nh2.F, downloadLaterDialogView, new MH(), true);
            HH0 hh0 = new HH0(AbstractC3258jl0.r);
            hh0.e(AbstractC3258jl0.f10235a, nh2);
            hh0.e(AbstractC3258jl0.f, nh2.G);
            hh0.d(AbstractC3258jl0.g, context.getResources(), R.string.f51820_resource_name_obfuscated_RES_2131952499);
            hh0.b(AbstractC3258jl0.q, true);
            hh0.d(AbstractC3258jl0.j, context.getResources(), R.string.f48470_resource_name_obfuscated_RES_2131952164);
            nh2.K = hh0.a();
            nh2.c(uh0.f(nh0));
            nh2.I.i(nh2.K, 0, false);
        }
        RH.a(0);
    }

    public final void h(boolean z) {
        this.j = z;
        this.l = this.c.O;
        this.b.a(this.d, this.e, this.f, this.g, this.h);
    }

    public final void showDialog(WindowAndroid windowAndroid, long j2, int i2, String str, boolean z) {
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity == null) {
            c();
        } else {
            AbstractC4550rH.f11194a.a(new C3013iH(this, activity, i2, j2, str, z));
        }
    }
}
