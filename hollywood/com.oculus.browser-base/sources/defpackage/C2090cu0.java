package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelListView;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelWrapper;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.ChromeImageView;

/* renamed from: cu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2090cu0 extends AbstractC2300e70 implements AbstractC1282Va1 {
    public AccessibilityTabModelWrapper V;
    public final float W;
    public final C0813Nh X;
    public final SceneLayer Y = new SceneLayer();
    public final AbstractC2400ek Z;
    public final AbstractC2230dk a0;

    public C2090cu0(Context context, K70 k70, F70 f70, AbstractC2400ek ekVar) {
        super(context, k70, f70);
        this.X = new C0813Nh(context);
        this.W = context.getResources().getDisplayMetrics().density;
        this.Z = ekVar;
        this.a0 = new C1919bu0(this);
    }

    @Override // defpackage.AbstractC2300e70
    public void D(long j, int i, boolean z) {
        this.V.b();
    }

    @Override // defpackage.AbstractC2300e70
    public void E(long j, int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
        Q(i, false);
    }

    @Override // defpackage.AbstractC2300e70
    public void F(int i) {
        Q(i, false);
    }

    @Override // defpackage.AbstractC2300e70
    public void G(boolean z) {
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.V;
        if (accessibilityTabModelWrapper != null) {
            accessibilityTabModelWrapper.b();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void H(long j, int i) {
        P(j, false);
    }

    @Override // defpackage.AbstractC2300e70
    public void K(long j, boolean z) {
        TabModel l = ((AbstractC0246Ea1) this.L).l(z);
        while (l.getCount() > 0) {
            AbstractC1160Ta1.b(l, 0);
        }
        if (z) {
            this.L.e(!z);
        }
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.V;
        if (accessibilityTabModelWrapper != null) {
            accessibilityTabModelWrapper.b();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void O(AbstractC0124Ca1 ca1, TabContentManager tabContentManager) {
        this.L = ca1;
        N(tabContentManager);
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.V;
        if (accessibilityTabModelWrapper != null) {
            accessibilityTabModelWrapper.c(ca1);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void P(long j, boolean z) {
        this.Q = false;
        this.R = true;
        this.S = -1;
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.V;
        if (accessibilityTabModelWrapper != null) {
            accessibilityTabModelWrapper.b();
            i();
            AbstractC2400ek ekVar = this.Z;
            ((C1551Zj) ekVar).Y.b(this.a0);
            X();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void Q(int i, boolean z) {
        AbstractC2400ek ekVar = this.Z;
        ((C1551Zj) ekVar).Y.c(this.a0);
        this.N.e(i, z);
        this.Q = true;
        this.S = i;
        h();
    }

    public final void X() {
        FrameLayout.LayoutParams layoutParams;
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.V;
        if (accessibilityTabModelWrapper != null && (layoutParams = (FrameLayout.LayoutParams) accessibilityTabModelWrapper.getLayoutParams()) != null) {
            layoutParams.bottomMargin = (int) (this.I * this.W);
            layoutParams.topMargin = ((C1551Zj) this.Z).T;
            this.V.setLayoutParams(layoutParams);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void a(ViewGroup viewGroup) {
        if (this.V == null) {
            AccessibilityTabModelWrapper accessibilityTabModelWrapper = (AccessibilityTabModelWrapper) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.f36570_resource_name_obfuscated_RES_2131623966, (ViewGroup) null);
            this.V = accessibilityTabModelWrapper;
            Context context = accessibilityTabModelWrapper.getContext();
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            accessibilityTabModelWrapper.N = context.getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829);
            accessibilityTabModelWrapper.P = accessibilityTabModelWrapper.getContext().getColorStateList(R.color.f11100_resource_name_obfuscated_RES_2131099800);
            accessibilityTabModelWrapper.O = accessibilityTabModelWrapper.getContext().getColorStateList(R.color.f15610_resource_name_obfuscated_RES_2131100251);
            accessibilityTabModelWrapper.Q = accessibilityTabModelWrapper.getContext().getColorStateList(R.color.f15640_resource_name_obfuscated_RES_2131100254);
            ChromeImageView chromeImageView = new ChromeImageView(accessibilityTabModelWrapper.getContext());
            accessibilityTabModelWrapper.L = chromeImageView;
            chromeImageView.setImageResource(R.drawable.f28500_resource_name_obfuscated_RES_2131230890);
            accessibilityTabModelWrapper.L.setScaleY(-1.0f);
            accessibilityTabModelWrapper.L.setContentDescription(accessibilityTabModelWrapper.getResources().getString(R.string.f45980_resource_name_obfuscated_RES_2131951915));
            ChromeImageView chromeImageView2 = new ChromeImageView(accessibilityTabModelWrapper.getContext());
            accessibilityTabModelWrapper.M = chromeImageView2;
            chromeImageView2.setImageResource(R.drawable.f33140_resource_name_obfuscated_RES_2131231354);
            accessibilityTabModelWrapper.M.setScaleY(-1.0f);
            accessibilityTabModelWrapper.M.setContentDescription(accessibilityTabModelWrapper.getResources().getString(R.string.f45960_resource_name_obfuscated_RES_2131951913));
            accessibilityTabModelWrapper.setDividerDrawable(null);
            ((ListView) accessibilityTabModelWrapper.findViewById(R.id.list_view)).setDivider(null);
            accessibilityTabModelWrapper.H = accessibilityTabModelWrapper.findViewById(R.id.tab_wrapper);
            TabLayout tabLayout = (TabLayout) accessibilityTabModelWrapper.findViewById(R.id.tab_layout);
            accessibilityTabModelWrapper.I = tabLayout;
            D81 l = tabLayout.l();
            l.e = accessibilityTabModelWrapper.L;
            l.e();
            accessibilityTabModelWrapper.f10606J = l;
            accessibilityTabModelWrapper.I.a(l);
            D81 l2 = accessibilityTabModelWrapper.I.l();
            l2.e = accessibilityTabModelWrapper.M;
            l2.e();
            accessibilityTabModelWrapper.K = l2;
            accessibilityTabModelWrapper.I.a(l2);
            TabLayout tabLayout2 = accessibilityTabModelWrapper.I;
            T t = new T(accessibilityTabModelWrapper);
            if (!tabLayout2.m0.contains(t)) {
                tabLayout2.m0.add(t);
            }
            accessibilityTabModelWrapper.G = (AccessibilityTabModelListView) accessibilityTabModelWrapper.findViewById(R.id.list_view);
            accessibilityTabModelWrapper.a().I = this;
            this.V.c(this.L);
            X();
        }
        if (viewGroup != null && this.V.getParent() == null) {
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.overview_list_layout_holder);
            viewGroup2.setVisibility(0);
            viewGroup2.addView(this.V);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public boolean b() {
        return DeviceFormFactor.a(this.f9833J);
    }

    @Override // defpackage.AbstractC2300e70
    public void f() {
        AbstractC2400ek ekVar = this.Z;
        if (ekVar != null) {
            ((C1551Zj) ekVar).Y.c(this.a0);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void g() {
        ViewGroup viewGroup;
        AbstractC0124Ca1 ca1 = this.L;
        if (ca1 != null) {
            ca1.d();
        }
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.V;
        if (accessibilityTabModelWrapper != null && (viewGroup = (ViewGroup) accessibilityTabModelWrapper.getParent()) != null) {
            viewGroup.setVisibility(8);
            viewGroup.removeView(this.V);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public VL n() {
        return this.X;
    }

    @Override // defpackage.AbstractC2300e70
    public int p() {
        return 1;
    }

    @Override // defpackage.AbstractC2300e70
    public SceneLayer q() {
        return this.Y;
    }

    @Override // defpackage.AbstractC2300e70
    public int r() {
        return 0;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean u() {
        return true;
    }

    @Override // defpackage.AbstractC1282Va1
    public void w(boolean z) {
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.V;
        if (accessibilityTabModelWrapper != null) {
            int i = !z ? 0 : 4;
            if (accessibilityTabModelWrapper.getImportantForAccessibility() != i) {
                this.V.setImportantForAccessibility(i);
                this.V.sendAccessibilityEvent(2048);
            }
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void y(float f, float f2, int i) {
        X();
    }
}
