package org.chromium.components.page_info;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ConnectionInfoView implements View.OnClickListener {
    public final Context F;
    public AbstractC0847Nx G;
    public final LinearLayout H;
    public final WebContents I;

    /* renamed from: J  reason: collision with root package name */
    public final int f10867J;
    public final int K;
    public final long L;
    public final C4118on M;
    public TextView N;
    public TextView O;
    public ViewGroup P;
    public ViewGroup Q;
    public Button R;
    public String S;
    public AbstractC3292jw1 T;
    public final boolean U;

    public ConnectionInfoView(Context context, WebContents webContents, AbstractC0847Nx nx, AbstractC3292jw1 jw1) {
        boolean MJ8X0ZQd = N.MJ8X0ZQd("PageInfoV2");
        this.U = MJ8X0ZQd;
        this.F = context;
        this.G = nx;
        this.I = webContents;
        this.T = jw1;
        this.M = new C4118on(context);
        LinearLayout linearLayout = new LinearLayout(context);
        this.H = linearLayout;
        linearLayout.setOrientation(1);
        if (MJ8X0ZQd) {
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f23600_resource_name_obfuscated_RES_2131165979);
            this.f10867J = dimensionPixelSize;
            int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.f23610_resource_name_obfuscated_RES_2131165980);
            this.K = dimensionPixelSize2;
            linearLayout.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, 0);
        } else {
            int dimension = (int) context.getResources().getDimension(R.dimen.f17650_resource_name_obfuscated_RES_2131165384);
            this.f10867J = dimension;
            int dimension2 = (int) context.getResources().getDimension(R.dimen.f17640_resource_name_obfuscated_RES_2131165383);
            this.K = dimension2;
            linearLayout.setPadding(dimension, dimension, dimension, dimension - dimension2);
        }
        this.L = N.MJUBMbqq(this, webContents);
    }

    public final View a(int i, String str, String str2, int i2) {
        View view;
        if (this.U) {
            view = LayoutInflater.from(this.F).inflate(R.layout.f37420_resource_name_obfuscated_RES_2131624051, (ViewGroup) null);
        } else {
            view = LayoutInflater.from(this.F).inflate(R.layout.f37410_resource_name_obfuscated_RES_2131624050, (ViewGroup) null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.connection_info_icon);
        imageView.setImageResource(i);
        if (this.U) {
            imageView.setImageTintList(ColorStateList.valueOf(this.F.getResources().getColor(i2)));
        }
        if (!this.U) {
            TextView textView = (TextView) view.findViewById(R.id.connection_info_headline);
            textView.setText(str);
            if (TextUtils.isEmpty(str)) {
                textView.setVisibility(8);
            }
        }
        TextView textView2 = (TextView) view.findViewById(R.id.connection_info_description);
        textView2.setText(str2);
        if (TextUtils.isEmpty(str2)) {
            textView2.setVisibility(8);
        }
        this.H.addView(view);
        return view;
    }

    public final void addCertificateSection(int i, String str, String str2, String str3, int i2) {
        this.P = (ViewGroup) a(i, str, str2, i2).findViewById(R.id.connection_info_text_layout);
        if (str3 != null && !str3.isEmpty()) {
            TextView textView = new TextView(this.F);
            this.N = textView;
            textView.setText(str3);
            AbstractC3153j7.i(this.N, R.style.f72170_resource_name_obfuscated_RES_2132017790);
            this.N.setOnClickListener(this);
            this.N.setPadding(0, this.K, 0, 0);
            this.P.addView(this.N);
        }
    }

    public final void addDescriptionSection(int i, String str, String str2, int i2) {
        this.Q = (ViewGroup) a(i, str, str2, i2).findViewById(R.id.connection_info_text_layout);
    }

    public final void addMoreInfoLink(String str) {
        TextView textView = new TextView(this.F);
        this.O = textView;
        this.S = "https://support.google.com/chrome?p=android_connection_info";
        textView.setText(str);
        AbstractC3153j7.i(this.O, R.style.f72170_resource_name_obfuscated_RES_2132017790);
        this.O.setPadding(0, this.K, 0, 0);
        this.O.setOnClickListener(this);
        this.Q.addView(this.O);
    }

    public final void addResetCertDecisionsButton(String str) {
        ButtonCompat buttonCompat = new ButtonCompat(this.F, null, R.style.f68610_resource_name_obfuscated_RES_2132017434);
        this.R = buttonCompat;
        buttonCompat.setText(str);
        this.R.setOnClickListener(this);
        LinearLayout linearLayout = new LinearLayout(this.F);
        linearLayout.setOrientation(1);
        linearLayout.addView(this.R);
        linearLayout.setPadding(0, 0, 0, this.f10867J);
        this.H.addView(linearLayout);
    }

    public final void b() {
        this.G.a(3);
        try {
            Intent parseUri = Intent.parseUri(this.S, 1);
            parseUri.putExtra("create_new_tab", true);
            parseUri.putExtra("com.android.browser.application_id", this.F.getPackageName());
            this.F.startActivity(parseUri);
        } catch (Exception e) {
            AbstractC1220Ua0.f("ConnectionInfoView", "Bad URI %s", this.S, e);
        }
    }

    public void onClick(View view) {
        if (this.R == view) {
            N.MYkS$dAY(this.L, this, this.I);
            this.G.a(3);
        } else if (this.N == view) {
            byte[][] MW74qHgy = N.MW74qHgy(this.I);
            if (MW74qHgy != null) {
                AbstractC3292jw1 jw1 = this.T;
                this.M.f(MW74qHgy);
            }
        } else if (this.O == view) {
            AbstractC3292jw1 jw12 = this.T;
            b();
        }
    }

    public final void onReady() {
        this.G.e(this);
    }
}
