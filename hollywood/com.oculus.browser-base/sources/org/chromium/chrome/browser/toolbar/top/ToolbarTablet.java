package org.chromium.chrome.browser.toolbar.top;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.LevelListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.omnibox.LocationBarTablet;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.HomeButton;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.content_public.browser.NavigationEntry;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ToolbarTablet extends Wj1 implements View.OnClickListener, View.OnLongClickListener, AbstractC5710y61 {
    public static final /* synthetic */ int U = 0;
    public HomeButton V;
    public ImageButton W;
    public ImageButton a0;
    public ImageButton b0;
    public ImageButton c0;
    public ImageButton d0;
    public ToggleTabStackButton e0;
    public View.OnClickListener f0;
    public boolean g0;
    public boolean h0;
    public boolean i0;
    public ImageButton[] j0;
    public ImageButton k0;
    public boolean l0;
    public C1375Wm0 m0;
    public Boolean n0;
    public C3909na0 o0;
    public final int p0 = getResources().getDimensionPixelOffset(R.dimen.f25800_resource_name_obfuscated_RES_2131166199);
    public final int q0 = getResources().getDimensionPixelOffset(R.dimen.f26320_resource_name_obfuscated_RES_2131166251);
    public boolean r0;
    public AnimatorSet s0;

    public ToolbarTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.Wj1
    public void A() {
        AbstractC1377Wn0 e = this.f9169J.e();
        e.e(new C5646xl1(e));
    }

    @Override // defpackage.Wj1
    public void B() {
        boolean p = p();
        Boolean bool = this.n0;
        if (bool == null || bool.booleanValue() != p) {
            this.L.i(AbstractC2934hr.a(getResources(), p), p());
            this.n0 = Boolean.valueOf(p);
        }
        AbstractC1377Wn0 e = this.f9169J.e();
        e.e(new C5646xl1(e));
    }

    @Override // defpackage.Wj1
    public void F(View.OnClickListener onClickListener) {
        this.f0 = onClickListener;
    }

    @Override // defpackage.Wj1
    public void K(C3909na0 na0) {
        this.o0 = na0;
    }

    @Override // defpackage.Wj1
    public void L(View.OnClickListener onClickListener) {
        this.e0.S = onClickListener;
    }

    @Override // defpackage.Wj1
    public void N(C5880z61 z61) {
        z61.f11721a.b(this);
        ToggleTabStackButton toggleTabStackButton = this.e0;
        toggleTabStackButton.R = z61;
        z61.a(toggleTabStackButton);
    }

    @Override // defpackage.Wj1
    public void P(boolean z, boolean z2, boolean z3, C5976zi0 zi0) {
        if (!this.h0 || !z) {
            this.g0 = false;
            AbstractView$OnClickListenerC5272va0 va0 = this.o0.F;
            Objects.requireNonNull(va0);
            va0.setVisibility(0);
            zi0.d(false);
            return;
        }
        this.g0 = true;
        this.W.setEnabled(false);
        this.a0.setEnabled(false);
        this.b0.setEnabled(false);
        AbstractView$OnClickListenerC5272va0 va02 = this.o0.F;
        Objects.requireNonNull(va02);
        va02.setVisibility(4);
        zi0.d(true);
    }

    @Override // defpackage.Wj1
    public void S(boolean z) {
        boolean z2 = z && !this.g0;
        this.W.setEnabled(z2);
        this.W.setFocusable(z2);
    }

    @Override // defpackage.Wj1
    public void T(boolean z, boolean z2) {
        if (z) {
            this.c0.setImageResource(R.drawable.f28580_resource_name_obfuscated_RES_2131230898);
            ImageButton imageButton = this.c0;
            Context context = getContext();
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            imageButton.setImageTintList(context.getColorStateList(R.color.f10010_resource_name_obfuscated_RES_2131099691));
            this.c0.setContentDescription(getContext().getString(R.string.f51850_resource_name_obfuscated_RES_2131952502));
        } else {
            this.c0.setImageResource(R.drawable.f28570_resource_name_obfuscated_RES_2131230897);
            this.c0.setImageTintList(l());
            this.c0.setContentDescription(getContext().getString(R.string.f45530_resource_name_obfuscated_RES_2131951870));
        }
        this.c0.setEnabled(z2);
    }

    @Override // defpackage.Wj1
    public void U() {
        ((C4251pa0) this.o0.G).F.t();
    }

    @Override // defpackage.Wj1
    public void V(boolean z) {
        boolean z2 = z && !this.g0;
        this.a0.setEnabled(z2);
        this.a0.setFocusable(z2);
    }

    @Override // defpackage.Wj1
    public void W(C0517Ik ik) {
        if (this.k0 == null) {
            this.k0 = (ImageButton) ((ViewStub) findViewById(R.id.optional_button_stub)).inflate();
        }
        boolean z = ik.e;
        this.l0 = z;
        if (z) {
            this.k0.setImageTintList(l());
        } else {
            this.k0.setImageTintList(null);
        }
        this.k0.setOnClickListener(ik.c);
        this.k0.setImageDrawable(ik.b);
        this.k0.setContentDescription(getContext().getResources().getString(ik.d));
        this.k0.setVisibility(0);
        this.k0.setEnabled(ik.f);
    }

    @Override // defpackage.Wj1
    public void X(boolean z) {
        if (z) {
            this.b0.getDrawable().setLevel(getResources().getInteger(R.integer.f36110_resource_name_obfuscated_RES_2131492908));
            this.b0.setContentDescription(getContext().getString(R.string.f45280_resource_name_obfuscated_RES_2131951845));
        } else {
            this.b0.getDrawable().setLevel(getResources().getInteger(R.integer.f36100_resource_name_obfuscated_RES_2131492907));
            this.b0.setContentDescription(getContext().getString(R.string.f45270_resource_name_obfuscated_RES_2131951844));
        }
        this.b0.setEnabled(!this.g0);
    }

    public final void Y(boolean z, View view) {
        Tab d = this.f9169J.d();
        if (!(d == null || d.l() == null)) {
            C1375Wm0 wm0 = new C1375Wm0(Profile.a(d.l()), getContext(), d.l().f(), z ? 2 : 1);
            this.m0 = wm0;
            if (!wm0.Q) {
                Object obj = ThreadUtils.f10596a;
                wm0.Q = true;
                wm0.P = new C3542lO();
                HashSet hashSet = new HashSet();
                for (int i = 0; i < wm0.f9172J.b(); i++) {
                    NavigationEntry a2 = wm0.f9172J.a(i);
                    if (a2.g == null) {
                        String h = a2.b.h();
                        if (!hashSet.contains(h)) {
                            wm0.P.c(wm0.F, h, wm0.M, new C1131Sm0(wm0, h));
                            hashSet.add(h);
                        }
                    }
                }
            }
            if (!wm0.H.isShowing()) {
                AbstractC3535lK0.a(wm0.a("Popup"));
            }
            if (!(wm0.H.getAnchorView() == null || wm0.N == null)) {
                wm0.H.getAnchorView().removeOnLayoutChangeListener(wm0.N);
            }
            wm0.H.setAnchorView(view);
            if (wm0.L == 0) {
                view.addOnLayoutChangeListener(wm0.N);
                wm0.b();
                return;
            }
            wm0.H.show();
        }
    }

    public final void Z(boolean z) {
        int i = z || this.V.getVisibility() == 0 ? this.p0 : this.q0;
        int paddingTop = getPaddingTop();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setPaddingRelative(i, paddingTop, getPaddingEnd(), getPaddingBottom());
    }

    @Override // defpackage.AbstractC0995Qg1, defpackage.Wj1
    public void a(int i, boolean z) {
        setBackgroundColor(i);
        ((C4251pa0) this.o0.G).F.getBackground().setColorFilter(Pj1.c(getResources(), i, p()), PorterDuff.Mode.SRC_IN);
        this.o0.H();
    }

    @Override // defpackage.AbstractC5710y61
    public void b(int i, boolean z) {
        this.e0.setContentDescription(getResources().getQuantityString(R.plurals.f42640_resource_name_obfuscated_RES_2131820556, i, Integer.valueOf(i)));
    }

    @Override // defpackage.AbstractC1056Rg1, defpackage.Wj1
    public void c(ColorStateList colorStateList, boolean z) {
        this.V.setImageTintList(colorStateList);
        this.W.setImageTintList(colorStateList);
        this.a0.setImageTintList(colorStateList);
        this.d0.setImageTintList(colorStateList);
        this.b0.setImageTintList(colorStateList);
        ToggleTabStackButton toggleTabStackButton = this.e0;
        toggleTabStackButton.setImageDrawable(z ? toggleTabStackButton.Q : toggleTabStackButton.P);
        ImageButton imageButton = this.k0;
        if (imageButton != null && this.l0) {
            imageButton.setImageTintList(colorStateList);
        }
    }

    @Override // defpackage.Wj1
    public void d() {
        super.d();
        AnimatorSet animatorSet = this.s0;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.s0.cancel();
            this.s0 = null;
        }
    }

    @Override // defpackage.Wj1
    public HomeButton g() {
        return this.V;
    }

    @Override // defpackage.Wj1
    public AbstractC3225ja0 h() {
        return this.o0;
    }

    @Override // defpackage.Wj1
    public View j() {
        return this.k0;
    }

    @Override // defpackage.Wj1
    public void n() {
        ImageButton imageButton = this.k0;
        if (imageButton != null && imageButton.getVisibility() != 8) {
            this.k0.setVisibility(8);
        }
    }

    @Override // defpackage.Wj1
    public void o(Sj1 sj1, C5476wl1 wl1, C5976zi0 zi0) {
        this.f9169J = sj1;
        this.K = wl1;
        this.R = zi0;
        zi0.e(true);
    }

    public void onClick(View view) {
        Tab tab;
        Tab tab2;
        if (this.V == view) {
            E();
        } else if (this.W == view) {
            r();
            C5476wl1 wl1 = this.K;
            if (wl1 != null && wl1.a()) {
                AbstractC3535lK0.a("MobileToolbarBack");
            }
        } else if (this.a0 == view) {
            r();
            C5476wl1 wl12 = this.K;
            if (!(wl12 == null || (tab2 = (Tab) wl12.f11566a.get()) == null || !tab2.k())) {
                tab2.j();
                wl12.f.run();
            }
            AbstractC3535lK0.a("MobileToolbarForward");
        } else if (this.b0 == view) {
            r();
            C5476wl1 wl13 = this.K;
            if (wl13 != null && (tab = (Tab) wl13.f11566a.get()) != null) {
                if (tab.d()) {
                    tab.t();
                    AbstractC3535lK0.a("MobileToolbarStop");
                } else {
                    tab.q();
                    AbstractC3535lK0.a("MobileToolbarReload");
                }
                wl13.f.run();
            }
        } else {
            ImageButton imageButton = this.c0;
            if (imageButton == view) {
                View.OnClickListener onClickListener = this.f0;
                if (onClickListener != null) {
                    onClickListener.onClick(imageButton);
                    AbstractC3535lK0.a("MobileToolbarToggleBookmark");
                }
            } else if (this.d0 == view) {
                DownloadUtils.a(getContext(), this.f9169J.d());
                AbstractC3535lK0.a("MobileToolbarDownloadPage");
            }
        }
    }

    @Override // defpackage.Wj1
    public void onFinishInflate() {
        super.onFinishInflate();
        this.V = (HomeButton) findViewById(R.id.home_button);
        this.W = (ImageButton) findViewById(R.id.back_button);
        this.a0 = (ImageButton) findViewById(R.id.forward_button);
        this.b0 = (ImageButton) findViewById(R.id.refresh_button);
        LevelListDrawable levelListDrawable = new LevelListDrawable();
        int integer = getResources().getInteger(R.integer.f36100_resource_name_obfuscated_RES_2131492907);
        int integer2 = getResources().getInteger(R.integer.f36110_resource_name_obfuscated_RES_2131492908);
        levelListDrawable.addLevel(integer, integer, AbstractC2417ep1.f(getContext(), R.drawable.f28670_resource_name_obfuscated_RES_2131230907, R.color.f11390_resource_name_obfuscated_RES_2131099829));
        levelListDrawable.addLevel(integer2, integer2, AbstractC2417ep1.f(getContext(), R.drawable.f28430_resource_name_obfuscated_RES_2131230883, R.color.f11390_resource_name_obfuscated_RES_2131099829));
        this.b0.setImageDrawable(levelListDrawable);
        this.h0 = C0283Ep.h().d() && NU0.f8549a.d("accessibility_tab_switcher", true);
        ToggleTabStackButton toggleTabStackButton = (ToggleTabStackButton) findViewById(R.id.tab_switcher_button);
        this.e0 = toggleTabStackButton;
        boolean z = this.h0;
        toggleTabStackButton.setVisibility((z || z) ? 0 : 8);
        this.c0 = (ImageButton) findViewById(R.id.bookmark_button);
        this.d0 = (ImageButton) findViewById(R.id.save_offline_button);
        this.r0 = false;
        this.i0 = true;
        this.j0 = new ImageButton[]{this.W, this.a0, this.b0};
    }

    @Override // defpackage.Wj1
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.r0 = true;
        super.onLayout(z, i, i2, i3, i4);
    }

    public boolean onLongClick(View view) {
        String str;
        Context context = getContext();
        Resources resources = context.getResources();
        ImageButton imageButton = this.b0;
        if (view == imageButton) {
            str = imageButton.getDrawable().getLevel() == resources.getInteger(R.integer.f36100_resource_name_obfuscated_RES_2131492907) ? resources.getString(R.string.f54800_resource_name_obfuscated_RES_2131952797) : resources.getString(R.string.f54870_resource_name_obfuscated_RES_2131952804);
        } else if (view == this.c0) {
            str = resources.getString(R.string.f54470_resource_name_obfuscated_RES_2131952764);
        } else {
            str = view == this.d0 ? resources.getString(R.string.f54510_resource_name_obfuscated_RES_2131952768) : null;
        }
        return C1184Ti1.c(context, view, str);
    }

    @Override // defpackage.Wj1
    public void onMeasure(int i, int i2) {
        AnimatorSet animatorSet;
        boolean z = View.MeasureSpec.getSize(i) >= ((int) ((((float) 600) * YF.b(getContext()).e) + 0.5f));
        if (this.i0 != z) {
            this.i0 = z;
            if (this.r0) {
                AnimatorSet animatorSet2 = this.s0;
                if (animatorSet2 != null) {
                    animatorSet2.cancel();
                }
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    for (ImageButton imageButton : this.j0) {
                        arrayList.add(((C4251pa0) this.o0.G).F.A(imageButton));
                    }
                    C4251pa0 pa0 = (C4251pa0) this.o0.G;
                    int i3 = this.V.getVisibility() == 0 ? 0 : this.p0 - this.q0;
                    LocationBarTablet locationBarTablet = pa0.F;
                    locationBarTablet.w0 = i3;
                    ArrayList arrayList2 = new ArrayList();
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(locationBarTablet, locationBarTablet.i0, 0.0f);
                    ofFloat.setDuration(225L);
                    ofFloat.setInterpolator(animation.InterpolatorC5286vf.e);
                    ofFloat.addListener(new C0306Fa0(locationBarTablet));
                    arrayList2.add(ofFloat);
                    if (locationBarTablet.F.getVisibility() != 0) {
                        arrayList2.add(locationBarTablet.A(locationBarTablet.k0));
                    }
                    if (locationBarTablet.D()) {
                        arrayList2.add(locationBarTablet.A(locationBarTablet.l0));
                    } else if (!(locationBarTablet.G.getVisibility() == 0 && locationBarTablet.G.getAlpha() == 1.0f)) {
                        arrayList2.add(locationBarTablet.A(locationBarTablet.G));
                    }
                    arrayList.addAll(arrayList2);
                    animatorSet = new AnimatorSet();
                    animatorSet.playTogether(arrayList);
                    animatorSet.addListener(new Dl1(this));
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    for (ImageButton imageButton2 : this.j0) {
                        arrayList3.add(((C4251pa0) this.o0.G).F.z(imageButton2));
                    }
                    C4251pa0 pa02 = (C4251pa0) this.o0.G;
                    int i4 = this.V.getVisibility() == 0 ? 0 : this.p0 - this.q0;
                    LocationBarTablet locationBarTablet2 = pa02.F;
                    locationBarTablet2.w0 = i4;
                    ArrayList arrayList4 = new ArrayList();
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(locationBarTablet2, locationBarTablet2.i0, 1.0f);
                    ofFloat2.setStartDelay(75);
                    ofFloat2.setDuration(225L);
                    ofFloat2.setInterpolator(animation.InterpolatorC5286vf.e);
                    ofFloat2.addListener(new C0367Ga0(locationBarTablet2));
                    arrayList4.add(ofFloat2);
                    if (locationBarTablet2.F.getVisibility() != 0) {
                        arrayList4.add(locationBarTablet2.z(locationBarTablet2.k0));
                    }
                    if (locationBarTablet2.D() && locationBarTablet2.l0.getVisibility() == 0) {
                        arrayList4.add(locationBarTablet2.z(locationBarTablet2.l0));
                    } else if (!locationBarTablet2.I.hasFocus() || locationBarTablet2.F.getVisibility() == 0) {
                        arrayList4.add(locationBarTablet2.z(locationBarTablet2.G));
                    }
                    arrayList3.addAll(arrayList4);
                    animatorSet = new AnimatorSet();
                    animatorSet.playTogether(arrayList3);
                    animatorSet.addListener(new El1(this));
                }
                this.s0 = animatorSet;
                animatorSet.start();
            } else {
                for (ImageButton imageButton3 : this.j0) {
                    imageButton3.setVisibility(z ? 0 : 8);
                }
                LocationBarTablet locationBarTablet3 = ((C4251pa0) this.o0.G).F;
                locationBarTablet3.p0 = z;
                locationBarTablet3.t();
                Z(z);
            }
        }
        super.onMeasure(i, i2);
    }

    public void onWindowFocusChanged(boolean z) {
        C1375Wm0 wm0;
        if (z && (wm0 = this.m0) != null) {
            wm0.H.dismiss();
            this.m0 = null;
        }
        super.onWindowFocusChanged(z);
    }

    @Override // defpackage.Wj1
    public boolean q() {
        return !this.N;
    }

    @Override // defpackage.Wj1
    public void s(boolean z) {
        boolean z2 = true;
        int i = 0;
        if (!z || !NU0.f8549a.d("accessibility_tab_switcher", true)) {
            z2 = false;
        }
        this.h0 = z2;
        ToggleTabStackButton toggleTabStackButton = this.e0;
        if (!z2 && !z2) {
            i = 8;
        }
        toggleTabStackButton.setVisibility(i);
    }

    public boolean showContextMenuForChild(View view) {
        ImageButton imageButton = this.W;
        if (imageButton == view) {
            Y(false, imageButton);
            return true;
        }
        ImageButton imageButton2 = this.a0;
        if (imageButton2 != view) {
            return super.showContextMenuForChild(view);
        }
        Y(true, imageButton2);
        return true;
    }

    @Override // defpackage.Wj1
    public void u(boolean z) {
        this.V.setVisibility(z ? 0 : 8);
    }

    @Override // defpackage.Wj1
    public void w() {
        super.w();
        this.V.setOnClickListener(this);
        this.V.setOnKeyListener(new C5816yl1(this));
        this.W.setOnClickListener(this);
        this.W.setLongClickable(true);
        this.W.setOnKeyListener(new C5986zl1(this));
        this.a0.setOnClickListener(this);
        this.a0.setLongClickable(true);
        this.a0.setOnKeyListener(new Al1(this));
        this.b0.setOnClickListener(this);
        this.b0.setOnLongClickListener(this);
        this.b0.setOnKeyListener(new Bl1(this));
        this.c0.setOnClickListener(this);
        this.c0.setOnLongClickListener(this);
        C5976zi0 zi0 = this.R;
        Cl1 cl1 = new Cl1(this);
        MenuButton menuButton = zi0.e;
        if (menuButton != null) {
            menuButton.setOnKeyListener(cl1);
        }
        this.d0.setOnClickListener(this);
        this.d0.setOnLongClickListener(this);
    }
}
