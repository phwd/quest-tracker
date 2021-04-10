package defpackage;

import J.N;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.findinpage.FindToolbar$FindQuery;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: BQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class BQ extends LinearLayout {
    public TextView F;
    public FindToolbar$FindQuery G;
    public ImageButton H;
    public ImageButton I;

    /* renamed from: J  reason: collision with root package name */
    public ImageButton f7735J;
    public View K;
    public C4230pQ L;
    public AbstractC0124Ca1 M;
    public final AbstractC0612Ka1 N = new C4911tQ(this);
    public final AbstractC5783ya1 O = new C5081uQ(this);
    public Tab P;
    public final AbstractC1404Xa1 Q = new C4741sQ(this);
    public WindowAndroid R;
    public C3546lQ S;
    public EQ T;
    public String U = "";
    public boolean V;
    public boolean W;
    public int a0 = 2;
    public int b0 = 2;
    public Handler c0 = new Handler();
    public Runnable d0;
    public boolean e0;
    public boolean f0;

    public BQ(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void a(BQ bq, boolean z) {
        if (bq.S != null) {
            String obj = bq.G.getText().toString();
            if (obj.length() != 0) {
                bq.R.u0().d(bq.G);
                C3546lQ lQVar = bq.S;
                N.MiKuFRTN(lQVar.b, lQVar, obj, z, false);
                C3546lQ lQVar2 = bq.S;
                N.MNC06_Rq(lQVar2.b, lQVar2);
                bq.e0 = true;
            }
        }
    }

    public final void b() {
        ThreadUtils.a();
        if (j()) {
            int i = this.a0;
            if (i == 0) {
                this.G.requestFocus();
                o();
                return;
            }
            this.b0 = 0;
            if (i == 2) {
                k(1);
                g();
            }
        }
    }

    public void c() {
        n("", false);
        C4230pQ pQVar = this.L;
        if (pQVar != null) {
            pQVar.b(-1, new RectF[0], null);
        }
    }

    public final void d(boolean z) {
        ThreadUtils.a();
        this.b0 = 2;
        if (this.a0 == 0) {
            k(3);
            h(z);
        }
    }

    public void e(Rect rect) {
    }

    public int f(boolean z, boolean z2) {
        return getContext().getResources().getColor(z ? R.color.f12400_resource_name_obfuscated_RES_2131099930 : R.color.f11660_resource_name_obfuscated_RES_2131099856);
    }

    public void g() {
        ((AbstractC0246Ea1) this.M).c(this.N);
        for (TabModel tabModel : ((AbstractC0246Ea1) this.M).f7969a) {
            tabModel.n(this.O);
        }
        Tab j = ((AbstractC0246Ea1) this.M).j();
        this.P = j;
        j.A(this.Q);
        C3546lQ lQVar = new C3546lQ(this.P.l());
        this.S = lQVar;
        this.V = true;
        String M3t_h9OB = N.M3t_h9OB(lQVar.b, lQVar);
        if (M3t_h9OB.isEmpty() && !i()) {
            M3t_h9OB = this.U;
        }
        this.W = true;
        this.G.setText(M3t_h9OB);
        this.V = false;
        this.G.requestFocus();
        o();
        m(true);
        p(i());
        k(0);
    }

    public void h(boolean z) {
        m(false);
        AbstractC0124Ca1 ca1 = this.M;
        ((AbstractC0246Ea1) ca1).f.c(this.N);
        for (TabModel tabModel : ((AbstractC0246Ea1) this.M).f7969a) {
            tabModel.w(this.O);
        }
        this.P.I(this.Q);
        this.R.u0().d(this.G);
        if (this.G.getText().length() > 0) {
            c();
            C3546lQ lQVar = this.S;
            N.MWOuMqhA(lQVar.b, lQVar, z);
        }
        C3546lQ lQVar2 = this.S;
        N.MlPioXlo(lQVar2.b, lQVar2);
        lQVar2.b = 0;
        this.S = null;
        this.P = null;
        k(2);
    }

    public boolean i() {
        AbstractC0124Ca1 ca1 = this.M;
        return ca1 != null && ((AbstractC0246Ea1) ca1).r();
    }

    public boolean j() {
        Tab j = ((AbstractC0246Ea1) this.M).j();
        return (j == null || j.l() == null || j.isNativePage()) ? false : true;
    }

    public final void k(int i) {
        this.a0 = i;
        EQ eq = this.T;
        if (eq != null) {
            if (i == 2) {
                eq.a();
            } else if (i == 0) {
                eq.b();
            }
        }
        int i2 = this.a0;
        if (i2 == 2 && this.b0 == 0) {
            b();
        } else if (i2 == 0 && this.b0 == 2) {
            d(true);
        }
    }

    public void l(boolean z) {
        this.I.setEnabled(z);
        this.f7735J.setEnabled(z);
    }

    public final void m(boolean z) {
        C4230pQ pQVar;
        Tab tab;
        if (z && this.L == null && (tab = this.P) != null && tab.l() != null) {
            this.L = new C4230pQ(getContext(), this.P.u(), this.R, this.S);
        } else if (!z && (pQVar = this.L) != null) {
            pQVar.e0 = true;
            pQVar.T = null;
            Animator animator = pQVar.d0;
            if (animator != null && animator.isRunning()) {
                pQVar.d0.cancel();
            }
            Property property = View.TRANSLATION_X;
            float[] fArr = new float[1];
            int i = pQVar.M;
            if (LocalizationUtils.isLayoutRtl()) {
                i = -i;
            }
            fArr[0] = (float) i;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(pQVar, property, fArr);
            pQVar.d0 = ofFloat;
            ofFloat.setDuration(200L);
            pQVar.d0.setInterpolator(animation.InterpolatorC5286vf.f);
            pQVar.U.I0(pQVar.d0);
            pQVar.d0.addListener(new C3888nQ(pQVar));
            this.L = null;
        }
    }

    public final void n(String str, boolean z) {
        this.F.setText(str);
        this.F.setContentDescription(null);
        this.F.setTextColor(f(z, i()));
        this.F.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
    }

    public final void o() {
        if (!this.G.hasWindowFocus()) {
            this.f0 = true;
        } else {
            this.R.u0().i(this.G);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        setOrientation(0);
        setGravity(16);
        FindToolbar$FindQuery findToolbar$FindQuery = (FindToolbar$FindQuery) findViewById(R.id.find_query);
        this.G = findToolbar$FindQuery;
        findToolbar$FindQuery.f10674J = this;
        findToolbar$FindQuery.setInputType(177);
        this.G.setSelectAllOnFocus(true);
        this.G.setOnFocusChangeListener(new View$OnFocusChangeListenerC5251vQ(this));
        this.G.addTextChangedListener(new C5421wQ(this));
        this.G.setOnEditorActionListener(new C5591xQ(this));
        this.F = (TextView) findViewById(R.id.find_status);
        n("", false);
        ImageButton imageButton = (ImageButton) findViewById(R.id.find_prev_button);
        this.I = imageButton;
        imageButton.setOnClickListener(new View$OnClickListenerC5761yQ(this));
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.find_next_button);
        this.f7735J = imageButton2;
        imageButton2.setOnClickListener(new View$OnClickListenerC5931zQ(this));
        l(false);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.close_find_button);
        this.H = imageButton3;
        imageButton3.setOnClickListener(new AQ(this));
        this.K = findViewById(R.id.find_separator);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f0) {
            this.f0 = false;
            this.c0.postDelayed(new RunnableC4401qQ(this), 0);
        }
    }

    public void p(boolean z) {
    }
}
