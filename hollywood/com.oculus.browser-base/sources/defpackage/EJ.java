package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oculus.browser.R;

/* renamed from: EJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EJ implements N4, FJ {
    public final Context F;
    public final View G;
    public boolean H;
    public int I = -1;

    /* renamed from: J  reason: collision with root package name */
    public View.OnLayoutChangeListener f7953J;
    public CharSequence K;
    public O4 L;
    public ListAdapter M;
    public final LinearLayout N;
    public final ListView O;
    public final FrameLayout P;
    public Drawable Q;
    public int R;

    public EJ(Context context, View view) {
        this.F = context;
        this.G = view;
        view.setId(R.id.dropdown_popup_window);
        view.setTag(this);
        CJ cj = new CJ(this);
        this.f7953J = cj;
        view.addOnLayoutChangeListener(cj);
        DJ dj = new DJ(this);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.f38330_resource_name_obfuscated_RES_2131624142, (ViewGroup) null);
        this.N = linearLayout;
        this.O = (ListView) linearLayout.findViewById(R.id.dropdown_body_list);
        this.P = (FrameLayout) linearLayout.findViewById(R.id.dropdown_footer);
        ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 fv1 = new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(view);
        fv1.L = true;
        Drawable c = AbstractC3153j7.c(context.getResources(), R.drawable.f34630_resource_name_obfuscated_RES_2131231503);
        this.Q = c;
        O4 o4 = new O4(context, view, c, linearLayout, fv1);
        this.L = o4;
        o4.P.b(dj);
        O4 o42 = this.L;
        o42.Q = this;
        o42.K.setElevation((float) context.getResources().getDimensionPixelSize(R.dimen.f18870_resource_name_obfuscated_RES_2131165506));
        Rect rect = new Rect();
        this.Q.getPadding(rect);
        fv1.e(0, rect.bottom, 0, rect.top);
        this.R = rect.right + rect.left;
        O4 o43 = this.L;
        o43.Z = 1;
        o43.f0 = true;
        o43.K.setOutsideTouchable(true);
    }

    @Override // defpackage.N4
    public void a(boolean z, int i, int i2, int i3, int i4, Rect rect) {
        this.Q.setBounds(rect);
        O4 o4 = this.L;
        o4.K.setBackgroundDrawable(AbstractC3153j7.c(this.F.getResources(), R.drawable.f34630_resource_name_obfuscated_RES_2131231503));
    }

    public void b() {
        boolean c = this.L.c();
        O4 o4 = this.L;
        o4.d0 = false;
        o4.e0 = true;
        int i = this.F.getResources().getDisplayMetrics().widthPixels;
        int a2 = AbstractC2417ep1.a(this.M);
        if (this.P.getChildCount() > 0) {
            if (this.P.getLayoutParams() == null) {
                this.P.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            }
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.P.measure(makeMeasureSpec, makeMeasureSpec);
            a2 = Math.max(this.P.getMeasuredWidth(), a2);
        }
        int i2 = this.R;
        if (i < a2 + i2) {
            this.L.W = i - i2;
        } else if (this.G.getWidth() < a2) {
            this.L.W = a2 + this.R;
        } else {
            this.L.W = this.G.getWidth() + this.R;
        }
        this.L.d();
        this.O.setDividerHeight(0);
        this.O.setLayoutDirection(this.H ? 1 : 0);
        if (!c) {
            this.O.setContentDescription(this.K);
            this.O.sendAccessibilityEvent(32);
        }
        int i3 = this.I;
        if (i3 >= 0) {
            this.O.setSelection(i3);
            this.I = -1;
        }
    }
}
