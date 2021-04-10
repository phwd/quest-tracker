package org.chromium.components.page_info;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.previews.PreviewsAndroidBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.content_settings.CookieControlsBridge;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;
import org.chromium.components.omnibox.AutocompleteSchemeClassifier;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.widget.ChromeImageButton;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PageInfoController implements AbstractC5675xv0, AbstractC3087il0, R51, EA {
    public Context F;
    public final WindowAndroid G;
    public final WebContents H;
    public final AbstractC1922bv0 I;

    /* renamed from: J  reason: collision with root package name */
    public long f10868J;
    public View$OnClickListenerC0479Hv0 K;
    public C0599Ju0 L;
    public C5505wv0 M;
    public GURL N;
    public boolean O;
    public int P;
    public String Q;
    public AbstractC6022zx1 R;
    public Runnable S;
    public boolean T = N.MJ8X0ZQd("PageInfoV2");
    public final SB0 U;
    public final TB0 V;
    public AbstractC0174Cv0 W;
    public C0233Du0 X;
    public C6015zv0 Y;
    public C3117iv0 Z;
    public CookieControlsBridge a0;

    public PageInfoController(WebContents webContents, int i, String str, AbstractC1922bv0 bv0, TB0 tb0) {
        String str2;
        int i2;
        boolean z;
        View$OnClickListenerC0479Hv0 hv0;
        this.H = webContents;
        this.P = i;
        this.I = bv0;
        this.V = tb0;
        C0296Ev0 ev0 = new C0296Ev0();
        WindowAndroid I2 = webContents.I();
        this.G = I2;
        this.F = (Context) I2.f11022J.get();
        this.Q = str;
        ev0.i = new RunnableC0660Ku0(this);
        ev0.j = new RunnableC1147Su0(this);
        if (bv0.c()) {
            str2 = bv0.h;
        } else {
            str2 = HG.b(webContents.u());
        }
        GURL gurl = new GURL(str2 == null ? "" : str2);
        this.N = gurl;
        this.O = AbstractC5154ur1.b.contains(gurl.g());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(bv0.c() ? AbstractC5154ur1.k(this.N.h()) : N.M52RypMk(this.N.h()));
        AutocompleteSchemeClassifier autocompleteSchemeClassifier = bv0.f9572a;
        if (this.P == 3) {
            C0107Bs0 b = AbstractC0229Ds0.b(spannableStringBuilder.toString(), autocompleteSchemeClassifier);
            if (b.b > 0) {
                spannableStringBuilder.setSpan(new TextAppearanceSpan(this.F, R.style.f71740_resource_name_obfuscated_RES_2132017747), 0, b.b, 34);
            }
        }
        AbstractC0229Ds0.a(spannableStringBuilder, this.F.getResources(), autocompleteSchemeClassifier, this.P, this.O, !AbstractC1270Uv.e(this.F), true);
        ev0.o = spannableStringBuilder;
        String spannableStringBuilder2 = spannableStringBuilder.toString();
        C0107Bs0 b2 = AbstractC0229Ds0.b(spannableStringBuilder2.toString(), autocompleteSchemeClassifier);
        if (!b2.b()) {
            i2 = spannableStringBuilder2.length();
        } else {
            String a2 = b2.a(spannableStringBuilder2);
            if (a2.equals("http") || a2.equals("https")) {
                i2 = b2.c + b2.d;
            } else if (a2.equals("data")) {
                i2 = 0;
            } else {
                i2 = spannableStringBuilder2.length();
            }
        }
        ev0.q = i2;
        autocompleteSchemeClassifier.a();
        if (bv0.c) {
            ev0.k = new RunnableC1208Tu0(this);
            ev0.h = bv0.d;
        } else {
            ev0.d = false;
            ev0.h = false;
        }
        ev0.n = new RunnableC1269Uu0(this);
        C1330Vu0 vu0 = new C1330Vu0(this);
        C4985ts tsVar = (C4985ts) bv0;
        PreviewsAndroidBridge a3 = PreviewsAndroidBridge.a();
        ev0.g = tsVar.e == 3;
        ev0.f = tsVar.e();
        if (tsVar.e()) {
            ev0.f7986a = false;
            ev0.b = false;
            ev0.m = new RunnableC3962ns(tsVar, vu0, a3);
            z = true;
            ev0.p = FY0.a(tsVar.k.getString(R.string.f57260_resource_name_obfuscated_RES_2131953043, tsVar.i.u().d()), new EY0("<link>", "</link>", new C4467qp0(tsVar.k.getResources(), new C4133os())));
        } else {
            z = true;
        }
        C1391Wu0 wu0 = new C1391Wu0(this);
        if (!tsVar.c() || !AbstractC2254ds0.e()) {
            ev0.e = false;
        } else {
            ev0.l = new RunnableC4304ps(tsVar, wu0);
        }
        if (!this.O && !bv0.c() && !bv0.e()) {
            this.N.h();
            Objects.requireNonNull(N20.a());
        }
        ev0.c = false;
        if (this.T) {
            hv0 = new C0540Iv0(this.F, ev0);
        } else {
            hv0 = new View$OnClickListenerC0479Hv0(this.F, ev0);
        }
        this.K = hv0;
        if (i(this.F)) {
            this.K.setBackgroundColor(-1);
        }
        Profile profile = null;
        if (this.T) {
            this.L = new C0599Ju0(this.F);
            C0538Iu0 iu0 = new C0538Iu0();
            iu0.b = ev0.o;
            iu0.c = ev0.q;
            iu0.d = N.MpICpYBr(this.N);
            iu0.i = new RunnableC1452Xu0(this);
            C0599Ju0 ju0 = this.L;
            ju0.getClass();
            iu0.g = new RunnableC1513Yu0(ju0);
            iu0.h = ev0.j;
            iu0.f8256a = ev0.f7986a;
            iu0.e = ev0.f;
            iu0.f = AbstractC2870hT0.c(tsVar.k, R.drawable.f34660_resource_name_obfuscated_RES_2131231506, R.color.f12850_resource_name_obfuscated_RES_2131099975);
            C0599Ju0 ju02 = this.L;
            PageInfoView$ElidedUrlTextView pageInfoView$ElidedUrlTextView = (PageInfoView$ElidedUrlTextView) ju02.findViewById(R.id.page_info_url);
            ju02.F = pageInfoView$ElidedUrlTextView;
            ju02.a(pageInfoView$ElidedUrlTextView, iu0);
            PageInfoView$ElidedUrlTextView pageInfoView$ElidedUrlTextView2 = ju02.F;
            CharSequence charSequence = iu0.b;
            int i3 = iu0.c;
            pageInfoView$ElidedUrlTextView2.setText(charSequence);
            pageInfoView$ElidedUrlTextView2.L = i3;
            PageInfoView$ElidedUrlTextView pageInfoView$ElidedUrlTextView3 = ju02.F;
            pageInfoView$ElidedUrlTextView3.K = z ^ pageInfoView$ElidedUrlTextView3.K;
            if (pageInfoView$ElidedUrlTextView3.f10869J != null) {
                pageInfoView$ElidedUrlTextView3.g();
            }
            TextView textView = (TextView) ju02.findViewById(R.id.page_info_truncated_url);
            ju02.G = textView;
            ju02.a(textView, iu0);
            TextView textView2 = (TextView) ju02.findViewById(R.id.page_info_truncated_url);
            ju02.G = textView2;
            textView2.setText(iu0.d);
            ju02.findViewById(R.id.page_info_url_wrapper).setVisibility(iu0.f8256a ? 0 : 8);
            TextView textView3 = (TextView) ju02.findViewById(R.id.page_info_preview_message);
            ju02.H = textView3;
            textView3.setText(R.string.f57270_resource_name_obfuscated_RES_2131953044);
            ju02.H.setCompoundDrawablesRelative(iu0.f, null, null, null);
            ju02.findViewById(R.id.page_info_preview_message_wrapper).setVisibility(iu0.e ? 0 : 8);
            ((ChromeImageButton) ju02.findViewById(R.id.subpage_back_button)).setOnClickListener(new View$OnClickListenerC0294Eu0(iu0));
            String h = this.N.h();
            C1574Zu0 zu0 = new C1574Zu0(this);
            Resources resources = tsVar.k.getResources();
            N.MBZyBYDK(N.MUcnJuRZ(), tsVar.l, h, resources.getDimensionPixelSize(R.dimen.f23570_resource_name_obfuscated_RES_2131165976), new C4475qs(tsVar, zu0, resources, h));
            this.L.c(this.K, null, null);
            C0540Iv0 iv0 = (C0540Iv0) this.K;
            this.X = new C0233Du0(this, iv0.b0, webContents, bv0.b);
            this.Y = new C6015zv0(this, iv0.c0, bv0, this.N.h());
            this.Z = new C3117iv0(this, iv0.d0, bv0, this.N.h());
        } else {
            View$OnClickListenerC0479Hv0 hv02 = this.K;
            if ((!N.M09VlOh_("PageInfoPerformanceHints") || N.MO0TyD6h(tsVar.i, this.N) != 2) ? false : z) {
                hv02.P.setVisibility(0);
                hv02.Q.setVisibility(0);
            } else {
                hv02.P.setVisibility(8);
                hv02.Q.setVisibility(8);
            }
            FA fa = new FA();
            fa.f7997a = new C0720Lu0(this);
            this.K.W.I = fa;
        }
        View$OnClickListenerC0479Hv0 hv03 = this.K;
        if (bv0.g) {
            hv03.U.setVisibility(0);
        } else {
            hv03.U.setVisibility(8);
        }
        boolean z2 = ev0.h;
        Context context = this.F;
        String h2 = this.N.h();
        View$OnClickListenerC0479Hv0 hv04 = this.K;
        hv04.getClass();
        this.U = new SB0(context, I2, h2, z2, this, new C0781Mu0(hv04), tb0);
        this.f10868J = N.MuLM_ayx(this, webContents);
        EA ea = this.T ? this.Z : this;
        WebContents webContents2 = tsVar.i;
        if (tsVar.l.g()) {
            Profile profile2 = tsVar.l;
            profile = (Profile) N.MD_ez$kP(profile2.b, profile2);
        }
        CookieControlsBridge cookieControlsBridge = new CookieControlsBridge(ea, webContents2, profile);
        this.a0 = cookieControlsBridge;
        C3117iv0 iv02 = this.Z;
        if (iv02 != null) {
            iv02.H = cookieControlsBridge;
        }
        this.R = new C1751av0(this, webContents);
        Context context2 = this.F;
        View$OnClickListenerC0479Hv0 hv05 = this.K;
        C0599Ju0 ju03 = this.L;
        View containerView = webContents.F().getContainerView();
        boolean i4 = i(this.F);
        C2746gl0 gl0 = (C2746gl0) tsVar.j.get();
        C5505wv0 wv0 = new C5505wv0(context2, hv05, ju03, containerView, i4, gl0, this);
        this.M = wv0;
        if (i4) {
            wv0.e.show();
        } else {
            gl0.i(wv0.f, 0, false);
        }
    }

    public static void b(PageInfoController pageInfoController) {
        C5505wv0 wv0 = pageInfoController.M;
        if (wv0 != null) {
            wv0.b(false);
            pageInfoController.M = null;
        }
        CookieControlsBridge cookieControlsBridge = pageInfoController.a0;
        if (cookieControlsBridge != null) {
            long j = cookieControlsBridge.f10833a;
            if (j != 0) {
                N.MupWWV0Q(j, cookieControlsBridge);
                cookieControlsBridge.f10833a = 0;
            }
            pageInfoController.a0 = null;
        }
    }

    public static void l(Activity activity, WebContents webContents, String str, int i, AbstractC1922bv0 bv0, TB0 tb0) {
        Window window = activity.getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (decorView.isAttachedToWindow()) {
                if (i == 1) {
                    AbstractC3535lK0.a("MobileWebsiteSettingsOpenedFromMenu");
                } else if (i == 2) {
                    AbstractC3535lK0.a("MobileWebsiteSettingsOpenedFromToolbar");
                } else if (i == 3) {
                    AbstractC3535lK0.a("MobileWebsiteSettingsOpenedFromVR");
                }
                new WeakReference(new PageInfoController(webContents, LR0.a(webContents), str, bv0, tb0));
            }
        }
    }

    @Override // defpackage.EA
    public void a(int i, int i2) {
        CookieControlsView cookieControlsView = this.K.W;
        cookieControlsView.G.setText(cookieControlsView.getContext().getResources().getQuantityString(R.plurals.f42680_resource_name_obfuscated_RES_2131820560, i2, Integer.valueOf(i2)));
    }

    public final void addPermissionSection(String str, int i, int i2) {
        this.U.f8882a.add(new RB0(str, i, i2));
    }

    public void c() {
        if (this.W != null) {
            this.L.c(this.K, null, new RunnableC0964Pu0(this));
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
    }

    @Override // defpackage.EA
    public void e(int i, int i2) {
        CookieControlsView cookieControlsView = this.K.W;
        boolean z = false;
        boolean z2 = i2 != 0;
        if (i == 1) {
            z = true;
        }
        cookieControlsView.H = z;
        cookieControlsView.F.setChecked(z);
        cookieControlsView.F.setEnabled(!z2);
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        Runnable runnable = this.S;
        if (runnable != null) {
            runnable.run();
            this.S = null;
        }
        AbstractC0174Cv0 cv0 = this.W;
        if (cv0 != null) {
            cv0.d();
            this.W = null;
        }
        this.R.destroy();
        this.R = null;
        N.Mz6XBRgf(this.f10868J, this);
        this.f10868J = 0;
        this.F = null;
    }

    public BrowserContextHandle g() {
        return ((C4985ts) this.I).l;
    }

    public final boolean h() {
        return this.Q == null && !this.I.c() && !this.I.e() && !this.I.d() && !this.O;
    }

    public final boolean i(Context context) {
        if (DeviceFormFactor.a(context)) {
            return false;
        }
        AbstractC3292jw1 jw1 = this.I.b;
        if (jw1 != null) {
            Objects.requireNonNull(jw1);
        }
        return true;
    }

    public void j(AbstractC0174Cv0 cv0) {
        if (this.W == null) {
            this.W = cv0;
            String b = cv0.b();
            this.L.c(this.W.c(this.L), b, null);
        }
    }

    public void k(int i) {
        long j = this.f10868J;
        if (j != 0) {
            N.M5DCRkGK(j, this, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        if ((r8.I.e == 3) != false) goto L_0x0071;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setSecurityDescription(java.lang.String r9, java.lang.String r10) {
        /*
        // Method dump skipped, instructions count: 321
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.page_info.PageInfoController.setSecurityDescription(java.lang.String, java.lang.String):void");
    }

    public final void updatePermissionDisplay() {
        String str;
        C0357Fv0 a2 = this.U.a();
        if (this.T) {
            C6015zv0 zv0 = this.Y;
            Resources resources = zv0.G.getContext().getResources();
            String string = resources.getString(R.string.f57250_resource_name_obfuscated_RES_2131953042);
            zv0.I = string;
            C0113Bv0 bv0 = new C0113Bv0();
            bv0.d = string;
            bv0.b = R.drawable.f32860_resource_name_obfuscated_RES_2131231326;
            boolean z = true;
            bv0.g = true;
            bv0.f = new RunnableC5845yv0(zv0);
            List list = a2.b;
            int size = list.size();
            if (size == 0) {
                str = null;
            } else {
                C0418Gv0 gv0 = (C0418Gv0) list.get(0);
                Iterator it = list.iterator();
                loop0:
                while (true) {
                    boolean z2 = true;
                    while (true) {
                        if (it.hasNext()) {
                            C0418Gv0 gv02 = (C0418Gv0) it.next();
                            if (gv02.f != 0) {
                                str = resources.getString(R.string.f57170_resource_name_obfuscated_RES_2131953034, gv02.f8120a.toString(), resources.getString(gv02.f));
                                break loop0;
                            } else if (!z2 || gv0.b != gv02.b) {
                                z2 = false;
                            }
                        } else if (size == 1) {
                            str = resources.getString(gv0.b ? R.string.f57200_resource_name_obfuscated_RES_2131953037 : R.string.f57210_resource_name_obfuscated_RES_2131953038, gv0.f8120a.toString());
                        } else {
                            C0418Gv0 gv03 = (C0418Gv0) list.get(1);
                            if (size == 2) {
                                if (z2) {
                                    str = resources.getString(gv0.b ? R.string.f57220_resource_name_obfuscated_RES_2131953039 : R.string.f57230_resource_name_obfuscated_RES_2131953040, gv0.f8120a.toString(), gv03.f8120a.toString());
                                } else {
                                    Object[] objArr = new Object[2];
                                    objArr[0] = (gv0.b ? gv0.f8120a : gv03.f8120a).toString();
                                    objArr[1] = (gv0.b ? gv03.f8120a : gv0.f8120a).toString();
                                    str = resources.getString(R.string.f57240_resource_name_obfuscated_RES_2131953041, objArr);
                                }
                            } else if (z2) {
                                int i = size - 2;
                                str = resources.getQuantityString(gv0.b ? R.plurals.f42820_resource_name_obfuscated_RES_2131820574 : R.plurals.f42830_resource_name_obfuscated_RES_2131820575, i, gv0.f8120a.toString(), gv03.f8120a.toString(), Integer.valueOf(i));
                            } else {
                                int i2 = size - 2;
                                str = resources.getQuantityString(R.plurals.f42840_resource_name_obfuscated_RES_2131820576, i2, gv0.f8120a.toString(), gv03.f8120a.toString(), Integer.valueOf(i2));
                            }
                        }
                    }
                }
            }
            bv0.e = str;
            if (!zv0.H.c || str == null) {
                z = false;
            }
            bv0.f7772a = z;
            zv0.G.a(bv0);
            return;
        }
        this.K.k(a2);
    }
}
