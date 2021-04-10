package org.chromium.components.browser_ui.widget.selectable_list;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.components.browser_ui.widget.FadingShadowView;
import org.chromium.components.browser_ui.widget.LoadingView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SelectableListLayout extends FrameLayout implements AbstractC3180jG, AbstractC3039iS0 {
    public static final /* synthetic */ int F = 0;
    public AbstractC5750yK0 G;
    public ViewStub H;
    public TextView I;

    /* renamed from: J  reason: collision with root package name */
    public View f10827J;
    public LoadingView K;
    public RecyclerView L;
    public AbstractView$OnClickListenerC2014cS0 M;
    public FadingShadowView N;
    public boolean O;
    public int P;
    public Yo1 Q;
    public final AK0 R = new XR0(this);

    public SelectableListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void c(SelectableListLayout selectableListLayout) {
        boolean z = false;
        int i = selectableListLayout.G.b() == 0 ? 0 : 8;
        selectableListLayout.I.setVisibility(i);
        selectableListLayout.f10827J.setVisibility(i);
        if (selectableListLayout.G.b() == 0) {
            selectableListLayout.L.setVisibility(8);
        } else {
            selectableListLayout.L.setVisibility(0);
        }
        AbstractView$OnClickListenerC2014cS0 cs0 = selectableListLayout.M;
        if (selectableListLayout.G.b() != 0) {
            z = true;
        }
        cs0.T(z);
    }

    public static int d(Xo1 xo1, Resources resources) {
        if (xo1.f9235a != 2) {
            return 0;
        }
        int i = resources.getConfiguration().screenWidthDp;
        float f = resources.getDisplayMetrics().density;
        return (int) Math.max(f * 16.0f, (float) ((int) ((((float) (i - 600)) / 2.0f) * f)));
    }

    @Override // defpackage.AbstractC3180jG
    public void a(Xo1 xo1) {
        int d = d(xo1, getResources());
        RecyclerView recyclerView = this.L;
        int paddingTop = recyclerView.getPaddingTop();
        int paddingBottom = this.L.getPaddingBottom();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        recyclerView.setPaddingRelative(d, paddingTop, d, paddingBottom);
    }

    @Override // defpackage.AbstractC3039iS0
    public void b(List list) {
        g();
    }

    public RecyclerView e(AbstractC5750yK0 yk0, RecyclerView recyclerView) {
        this.G = yk0;
        if (recyclerView == null) {
            RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.selectable_list_recycler_view);
            this.L = recyclerView2;
            recyclerView2.t0(new LinearLayoutManager(getContext()));
        } else {
            this.L = recyclerView;
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.list_content);
            frameLayout.removeView((RecyclerView) frameLayout.findViewById(R.id.selectable_list_recycler_view));
            frameLayout.addView(this.L, 0);
        }
        this.L.q0(this.G);
        AbstractC5750yK0 yk02 = this.G;
        yk02.F.registerObserver(this.R);
        RecyclerView recyclerView3 = this.L;
        recyclerView3.e0 = true;
        recyclerView3.i(new YR0(this));
        RecyclerView recyclerView4 = this.L;
        EW0 ew0 = recyclerView4.y0;
        return recyclerView4;
    }

    public AbstractView$OnClickListenerC2014cS0 f(int i, C3209jS0 js0, int i2, int i3, int i4, AbstractC4790sj1 sj1, boolean z, boolean z2) {
        this.H.setLayoutResource(i);
        AbstractView$OnClickListenerC2014cS0 cs0 = (AbstractView$OnClickListenerC2014cS0) this.H.inflate();
        this.M = cs0;
        cs0.P(js0, i2, i3, i4, z2);
        FadingShadowView fadingShadowView = (FadingShadowView) findViewById(R.id.shadow);
        this.N = fadingShadowView;
        fadingShadowView.a(getResources().getColor(R.color.f15370_resource_name_obfuscated_RES_2131100227), 0);
        this.O = z;
        js0.d.b(this);
        g();
        return this.M;
    }

    public final void g() {
        RecyclerView recyclerView;
        if (this.M != null && (recyclerView = this.L) != null) {
            int i = 0;
            boolean z = recyclerView.canScrollVertically(-1) || (this.M.w0.d() && this.O);
            FadingShadowView fadingShadowView = this.N;
            if (!z) {
                i = 8;
            }
            fadingShadowView.setVisibility(i);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Yo1 yo1 = this.Q;
        if (yo1 != null) {
            yo1.b();
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.f41360_resource_name_obfuscated_RES_2131624445, this);
        this.I = (TextView) findViewById(R.id.empty_view);
        this.f10827J = findViewById(R.id.empty_view_wrapper);
        LoadingView loadingView = (LoadingView) findViewById(R.id.loading_view);
        this.K = loadingView;
        loadingView.c();
        this.H = (ViewStub) findViewById(R.id.action_bar_stub);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }
}
