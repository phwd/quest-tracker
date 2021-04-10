package defpackage;

import J.N;
import android.app.DatePickerDialog;
import android.content.Context;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadDialogBridge;
import org.chromium.chrome.browser.download.dialogs.DownloadLaterDialogView;
import org.chromium.components.prefs.PrefService;

/* renamed from: NH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NH implements AbstractC3087il0, QH, ZG {
    public UH0 F;
    public DownloadLaterDialogView G;
    public Context H;
    public C2746gl0 I;

    /* renamed from: J  reason: collision with root package name */
    public PrefService f8537J;
    public UH0 K;
    public ZH0 L;
    public LH M;
    public final AbstractC1637aH N;
    public int O = 0;

    public NH(AbstractC1637aH aHVar) {
        this.N = aHVar;
    }

    public final void a() {
        if (this.G.a() != null) {
            PrefService prefService = this.f8537J;
            N.MPBZLcVx(prefService.f10883a, "download.download_later_prompt_status", this.G.a().intValue());
        }
    }

    public final void b(long j) {
        a();
        LH lh = this.M;
        int i = this.O;
        DownloadDialogBridge downloadDialogBridge = (DownloadDialogBridge) lh;
        downloadDialogBridge.l = i;
        downloadDialogBridge.m = j;
        boolean M25tbLgz = N.M25tbLgz();
        long j2 = downloadDialogBridge.f;
        AbstractC3364kK0.g("Download.Later.UI.DialogChoice.Main", i, 4);
        if (M25tbLgz) {
            AbstractC3364kK0.g("Download.Later.UI.DialogChoice.Main.DataSaverOn", i, 4);
        } else {
            AbstractC3364kK0.g("Download.Later.UI.DialogChoice.Main.DataSaverOff", i, 4);
        }
        RH.a(1);
        if (j2 > 0 && i == 2) {
            AbstractC3364kK0.b("Download.Later.ScheduledDownloadSize", (int) Math.min(10240L, j2 / 1048576));
        }
        if (downloadDialogBridge.g == 1) {
            downloadDialogBridge.d();
        } else {
            downloadDialogBridge.h(false);
        }
    }

    public final void c(int i) {
        int i2 = this.O;
        this.O = i;
        if (i2 != 2 && i == 2) {
            this.K.m(AbstractC3258jl0.g, this.H.getResources().getString(R.string.f50940_resource_name_obfuscated_RES_2131952411));
            this.F.j(OH.d, true);
        } else if (i2 == 2 && i != 2) {
            this.K.m(AbstractC3258jl0.g, this.H.getResources().getString(R.string.f51820_resource_name_obfuscated_RES_2131952499));
            this.F.j(OH.d, false);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            this.I.b(uh0, 1);
        } else if (i == 1) {
            this.I.b(uh0, 2);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        if (i == 1) {
            long j = -1;
            if (this.O == 2) {
                this.I.b(this.K, 3);
                long currentTimeMillis = System.currentTimeMillis();
                UH0 uh02 = this.F;
                OH0 oh0 = AbstractC2500fH.f9912a;
                long a2 = AbstractC3183jH.a(uh02, oh0, currentTimeMillis);
                Map c = UH0.c(AbstractC2500fH.e);
                SH0 sh0 = AbstractC2500fH.d;
                JH0 jh0 = new JH0(null);
                jh0.f8282a = 0;
                HashMap hashMap = (HashMap) c;
                hashMap.put(sh0, jh0);
                Long valueOf = Long.valueOf(a2);
                LH0 lh0 = new LH0(null);
                lh0.f8415a = valueOf;
                hashMap.put(oh0, lh0);
                OH0 oh02 = AbstractC2500fH.b;
                Long valueOf2 = Long.valueOf(Math.min(currentTimeMillis, a2));
                LH0 lh02 = new LH0(null);
                lh02.f8415a = valueOf2;
                UH0 o = AbstractC2531fV.o(hashMap, oh02, lh02, c, null);
                AbstractC1637aH aHVar = this.N;
                Context context = this.H;
                C2329eH eHVar = (C2329eH) aHVar;
                Objects.requireNonNull(eHVar);
                long currentTimeMillis2 = System.currentTimeMillis();
                Long l = (Long) o.g(oh0);
                if (l != null) {
                    currentTimeMillis2 = l.longValue();
                }
                eHVar.e.setTimeInMillis(currentTimeMillis2);
                DatePickerDialog datePickerDialog = eHVar.f9844a;
                if (datePickerDialog != null) {
                    datePickerDialog.dismiss();
                }
                KI ki = eHVar.c;
                if (ki != null) {
                    ki.dismiss();
                }
                eHVar.f9844a = new DatePickerDialog(context, R.style.f72930_resource_name_obfuscated_RES_2132017866, null, eHVar.e.get(1), eHVar.e.get(2), eHVar.e.get(5));
                Long l2 = (Long) o.g(oh02);
                long longValue = l2 != null ? l2.longValue() : -1;
                Long l3 = (Long) o.g(AbstractC2500fH.c);
                if (l3 != null) {
                    j = l3.longValue();
                }
                if (longValue > 0) {
                    eHVar.f9844a.getDatePicker().setMinDate(longValue);
                }
                if (j > 0) {
                    eHVar.f9844a.getDatePicker().setMaxDate(j);
                }
                eHVar.f9844a.setButton(-1, context.getResources().getString(R.string.f50940_resource_name_obfuscated_RES_2131952411), new DialogInterface$OnClickListenerC1817bH(eHVar));
                eHVar.f9844a.setButton(-2, context.getResources().getString(R.string.f48470_resource_name_obfuscated_RES_2131952164), new DialogInterface$OnClickListenerC1988cH(eHVar));
                eHVar.f9844a.setOnDismissListener(new DialogInterface$OnDismissListenerC2159dH(eHVar));
                eHVar.c = new KI(context, eHVar, eHVar.e.get(11), eHVar.e.get(12));
                eHVar.f9844a.show();
                RH.a(3);
                return;
            }
            b(-1);
        } else if (i != 3) {
            a();
            DownloadDialogBridge downloadDialogBridge = (DownloadDialogBridge) this.M;
            Objects.requireNonNull(downloadDialogBridge);
            RH.a(2);
            downloadDialogBridge.c();
        }
    }
}
