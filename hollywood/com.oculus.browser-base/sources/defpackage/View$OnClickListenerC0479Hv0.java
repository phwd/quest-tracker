package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.components.page_info.CookieControlsView;
import org.chromium.components.page_info.PageInfoView$ElidedUrlTextView;

/* renamed from: Hv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC0479Hv0 extends FrameLayout implements View.OnClickListener {
    public PageInfoView$ElidedUrlTextView F;
    public TextView G;
    public TextView H;
    public View I;

    /* renamed from: J  reason: collision with root package name */
    public Button f8188J;
    public Button K;
    public Button L;
    public Runnable M;
    public TextView N;
    public TextView O;
    public TextView P;
    public TextView Q;
    public TextView R;
    public View S;
    public LinearLayout T;
    public TextView U;
    public View V;
    public CookieControlsView W;

    public View$OnClickListenerC0479Hv0(Context context) {
        super(context);
    }

    public List a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.F);
        arrayList.add(this.N);
        arrayList.add(this.O);
        arrayList.add(this.P);
        arrayList.add(this.Q);
        arrayList.add(this.I);
        arrayList.add(this.G);
        arrayList.add(this.H);
        arrayList.add(this.U);
        arrayList.add(this.f8188J);
        arrayList.add(this.V);
        arrayList.add(this.W);
        arrayList.add(this.S);
        arrayList.add(this.R);
        for (int i = 0; i < this.T.getChildCount(); i++) {
            arrayList.add(this.T.getChildAt(i));
        }
        arrayList.add(this.K);
        return arrayList;
    }

    public void b(C0296Ev0 ev0) {
        i(ev0);
        g(ev0);
        c(ev0);
        e(ev0);
        TextView textView = (TextView) findViewById(R.id.page_info_lite_mode_https_image_compression_message);
        this.U = textView;
        j(textView, false, null);
        f(ev0);
        d(ev0);
        Button button = (Button) findViewById(R.id.page_info_instant_app_button);
        this.f8188J = button;
        j(button, ev0.c, null);
        h(ev0);
        Button button2 = (Button) findViewById(R.id.page_info_open_online_button);
        this.L = button2;
        j(button2, ev0.e, ev0.l);
    }

    public void c(C0296Ev0 ev0) {
        this.N = (TextView) findViewById(R.id.page_info_connection_summary);
        this.O = (TextView) findViewById(R.id.page_info_connection_message);
        j(this.N, false, null);
        j(this.O, ev0.b, null);
    }

    public void d(C0296Ev0 ev0) {
        this.V = findViewById(R.id.page_info_cookie_controls_separator);
        this.W = (CookieControlsView) findViewById(R.id.page_info_cookie_controls_view);
        j(this.V, ev0.h, null);
        j(this.W, ev0.h, null);
        this.M = ev0.n;
    }

    public void e(C0296Ev0 ev0) {
        this.P = (TextView) findViewById(R.id.page_info_performance_summary);
        this.Q = (TextView) findViewById(R.id.page_info_performance_message);
        j(this.P, false, null);
        j(this.Q, false, null);
    }

    public void f(C0296Ev0 ev0) {
        this.R = (TextView) findViewById(R.id.page_info_permissions_list_title);
        this.S = findViewById(R.id.page_info_permissions_separator);
        this.T = (LinearLayout) findViewById(R.id.page_info_permissions_list);
        j(this.R, false, null);
        j(this.S, false, null);
        j(this.T, false, null);
    }

    public void g(C0296Ev0 ev0) {
        this.G = (TextView) findViewById(R.id.page_info_preview_message);
        this.H = (TextView) findViewById(R.id.page_info_preview_load_original);
        this.I = findViewById(R.id.page_info_preview_separator);
        j(this.G, ev0.f, null);
        j(this.H, ev0.f, ev0.m);
        j(this.I, ev0.g, null);
        this.H.setText(ev0.p);
    }

    public void h(C0296Ev0 ev0) {
        Button button = (Button) findViewById(R.id.page_info_site_settings_button);
        this.K = button;
        j(button, ev0.d, ev0.k);
    }

    public void i(C0296Ev0 ev0) {
        PageInfoView$ElidedUrlTextView pageInfoView$ElidedUrlTextView = (PageInfoView$ElidedUrlTextView) findViewById(R.id.page_info_url);
        this.F = pageInfoView$ElidedUrlTextView;
        CharSequence charSequence = ev0.o;
        int i = ev0.q;
        pageInfoView$ElidedUrlTextView.setText(charSequence);
        pageInfoView$ElidedUrlTextView.L = i;
        if (ev0.j != null) {
            this.F.setOnLongClickListener(new View$OnLongClickListenerC0235Dv0(ev0));
        }
        j(this.F, ev0.f7986a, ev0.i);
    }

    public void j(View view, boolean z, Runnable runnable) {
        view.setVisibility(z ? 0 : 8);
        view.setTag(R.id.page_info_click_callback, runnable);
        if (runnable != null) {
            view.setOnClickListener(this);
        }
    }

    public void k(C0357Fv0 fv0) {
        this.T.removeAllViews();
        int i = 8;
        this.T.setVisibility(!fv0.b.isEmpty() ? 0 : 8);
        this.R.setVisibility(fv0.f8051a ? 0 : 8);
        View view = this.S;
        if (fv0.f8051a) {
            i = 0;
        }
        view.setVisibility(i);
        for (C0418Gv0 gv0 : fv0.b) {
            LinearLayout linearLayout = this.T;
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f40190_resource_name_obfuscated_RES_2131624328, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.page_info_permission_status)).setText(gv0.c);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.page_info_permission_icon);
            Context context = getContext();
            int i2 = gv0.d;
            int i3 = gv0.e;
            if (i3 == 0) {
                i3 = R.color.f11220_resource_name_obfuscated_RES_2131099812;
            }
            imageView.setImageDrawable(AbstractC2417ep1.f(context, i2, i3));
            if (gv0.f != 0) {
                TextView textView = (TextView) inflate.findViewById(R.id.page_info_permission_unavailable_message);
                textView.setVisibility(0);
                textView.setText(gv0.f);
            }
            if (gv0.g != 0) {
                TextView textView2 = (TextView) inflate.findViewById(R.id.page_info_permission_subtitle);
                textView2.setVisibility(0);
                textView2.setText(gv0.g);
            }
            Runnable runnable = gv0.h;
            if (runnable != null) {
                inflate.setTag(R.id.page_info_click_callback, runnable);
                inflate.setOnClickListener(this);
            }
            linearLayout.addView(inflate);
        }
    }

    public void l() {
        PageInfoView$ElidedUrlTextView pageInfoView$ElidedUrlTextView = this.F;
        pageInfoView$ElidedUrlTextView.K = !pageInfoView$ElidedUrlTextView.K;
        if (pageInfoView$ElidedUrlTextView.f10869J != null) {
            pageInfoView$ElidedUrlTextView.g();
        }
    }

    public void onClick(View view) {
        Object tag = view.getTag(R.id.page_info_click_callback);
        if (tag instanceof Runnable) {
            ((Runnable) tag).run();
            return;
        }
        throw new IllegalStateException("Unable to find click callback for view: " + view);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.M.run();
    }

    public View$OnClickListenerC0479Hv0(Context context, C0296Ev0 ev0) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.f40170_resource_name_obfuscated_RES_2131624326, (ViewGroup) this, true);
        b(ev0);
    }
}
