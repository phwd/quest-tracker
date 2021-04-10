package defpackage;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.components.browser_ui.widget.FadingShadowView;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: NL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NL extends AbstractC4277pj {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8542a;
    public final Runnable b;
    public final Runnable c;
    public final Runnable d;
    public final int e;
    public ViewGroup f;
    public ViewGroup g;
    public WebContents h;
    public AbstractViewGroup$OnHierarchyChangeListenerC1520Yy i;
    public AbstractC1422Xg1 j;
    public FadingShadowView k;
    public Drawable l;
    public ImageView m;
    public int n = N.M37SqSAy("EphemeralTabUsingBottomSheet", "ephemeral_tab_open_mode", 0);

    public NL(Context context, Runnable runnable, Runnable runnable2, Runnable runnable3, int i2) {
        this.f8542a = context;
        this.b = runnable;
        this.c = runnable2;
        this.d = runnable3;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f25040_resource_name_obfuscated_RES_2131166123);
        this.e = dimensionPixelSize;
        this.j = new C1544Zg1(context, new C1483Yg1());
        this.g = new FrameLayout(context);
        C1544Zg1 zg1 = (C1544Zg1) this.j;
        Objects.requireNonNull(zg1);
        zg1.setLayoutParams(new FrameLayout.LayoutParams(-1, ((int) (((float) i2) * 0.9f)) - dimensionPixelSize));
        ViewGroup viewGroup = this.g;
        C1544Zg1 zg12 = (C1544Zg1) this.j;
        Objects.requireNonNull(zg12);
        viewGroup.addView(zg12);
        this.g.setPadding(0, dimensionPixelSize, 0, 0);
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f41470_resource_name_obfuscated_RES_2131624456, (ViewGroup) null);
        this.f = viewGroup2;
        FadingShadowView fadingShadowView = (FadingShadowView) viewGroup2.findViewById(R.id.shadow);
        this.k = fadingShadowView;
        fadingShadowView.a(context.getResources().getColor(R.color.f15370_resource_name_obfuscated_RES_2131100227), 0);
        ((ImageView) this.f.findViewById(R.id.open_in_new_tab)).setOnClickListener(new KL(this));
        this.f.findViewById(R.id.toolbar).setOnClickListener(new LL(this));
        this.f.findViewById(R.id.close).setOnClickListener(new ML(this));
        ImageView imageView = (ImageView) this.f.findViewById(R.id.favicon);
        this.m = imageView;
        this.l = imageView.getDrawable();
    }

    @Override // defpackage.AbstractC4277pj
    public void f() {
        ((C1544Zg1) this.j).b();
    }

    @Override // defpackage.AbstractC4277pj
    public View g() {
        return this.g;
    }

    @Override // defpackage.AbstractC4277pj
    public float h() {
        return this.n == 1 ? 0.9f : -1.0f;
    }

    @Override // defpackage.AbstractC4277pj
    public float i() {
        return this.n == 1 ? 0.6f : 0.0f;
    }

    @Override // defpackage.AbstractC4277pj
    public int j() {
        if (this.n == 0) {
            return (int) (((float) this.f8542a.getResources().getDimensionPixelSize(R.dimen.f26330_resource_name_obfuscated_RES_2131166252)) * 2.0f);
        }
        return -2;
    }

    @Override // defpackage.AbstractC4277pj
    public int k() {
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public int l() {
        return R.string.f51950_resource_name_obfuscated_RES_2131952512;
    }

    @Override // defpackage.AbstractC4277pj
    public int m() {
        return R.string.f51960_resource_name_obfuscated_RES_2131952513;
    }

    @Override // defpackage.AbstractC4277pj
    public int n() {
        return R.string.f51980_resource_name_obfuscated_RES_2131952515;
    }

    @Override // defpackage.AbstractC4277pj
    public int o() {
        return R.string.f51990_resource_name_obfuscated_RES_2131952516;
    }

    @Override // defpackage.AbstractC4277pj
    public View p() {
        return this.f;
    }

    @Override // defpackage.AbstractC4277pj
    public int q() {
        WebContents webContents = this.h;
        if (webContents == null) {
            return 0;
        }
        return ((WebContentsImpl) webContents).M.e();
    }

    @Override // defpackage.AbstractC4277pj
    public boolean r() {
        this.d.run();
        return true;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean v() {
        return true;
    }

    public void w(float f2) {
        ((ProgressBar) this.f.findViewById(R.id.progress_bar)).setProgress(Math.round(f2 * 100.0f));
    }

    public void x(boolean z) {
        ((ProgressBar) this.f.findViewById(R.id.progress_bar)).setVisibility(z ? 0 : 8);
    }
}
