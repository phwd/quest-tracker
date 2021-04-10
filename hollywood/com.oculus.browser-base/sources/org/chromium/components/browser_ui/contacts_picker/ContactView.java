package org.chromium.components.browser_ui.contacts_picker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContactView extends TR0 {
    public Context b0;
    public IC0 c0;
    public C3209jS0 d0;
    public C3638ly e0;
    public TextView f0;
    public TextView g0;
    public TextView h0;
    public TextView i0;
    public TextView j0;
    public TextView k0;
    public TextView l0;
    public ImageView m0;
    public C2746gl0 n0;
    public UH0 o0;

    public ContactView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b0 = context;
        this.L = false;
    }

    @Override // defpackage.AbstractC3039iS0, defpackage.VR0
    public void b(List list) {
        C3638ly lyVar = this.e0;
        if (lyVar != null && list.contains(lyVar) != super.isChecked()) {
            super.toggle();
        }
    }

    @Override // defpackage.VR0
    public void f() {
    }

    public void m(C3638ly lyVar, Bitmap bitmap) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        l(null);
        String str7 = "";
        this.f0.setText(str7);
        this.g0.setText(str7);
        this.h0.setText(str7);
        this.i0.setText(str7);
        this.j0.setText(str7);
        this.k0.setText(str7);
        this.l0.setText(str7);
        this.m0.setVisibility(8);
        this.e0 = lyVar;
        this.f9084J = lyVar;
        setChecked(this.I.c.contains(lyVar));
        this.f0.setText(lyVar.G);
        boolean z = C0472Hs.I;
        boolean z2 = C0472Hs.K;
        boolean z3 = C0472Hs.L;
        Resources resources = this.b0.getResources();
        if (!z || lyVar.f10389J.size() == 0) {
            str2 = str7;
            str = str2;
        } else {
            str2 = lyVar.a(((C1033Qy0) lyVar.f10389J.get(0)).e[0]);
            int size = lyVar.f10389J.size();
            if (size > 1) {
                int i = size - 1;
                str = resources.getQuantityString(R.plurals.f42670_resource_name_obfuscated_RES_2131820559, i, Integer.valueOf(i));
            } else {
                str = str7;
            }
        }
        if (!z2 || lyVar.H.size() == 0) {
            str4 = str7;
            str3 = str4;
        } else {
            str4 = (String) lyVar.H.get(0);
            int size2 = lyVar.H.size();
            if (size2 > 1) {
                int i2 = size2 - 1;
                str3 = resources.getQuantityString(R.plurals.f42670_resource_name_obfuscated_RES_2131820559, i2, Integer.valueOf(i2));
            } else {
                str3 = str7;
            }
        }
        if (!z3 || lyVar.I.size() == 0) {
            str6 = str7;
            str5 = str6;
        } else {
            str6 = (String) lyVar.I.get(0);
            int size3 = lyVar.I.size();
            if (size3 > 1) {
                int i3 = size3 - 1;
                str5 = resources.getQuantityString(R.plurals.f42670_resource_name_obfuscated_RES_2131820559, i3, Integer.valueOf(i3));
            } else {
                str5 = str7;
            }
        }
        o(this.g0, str2);
        o(this.h0, str);
        o(this.i0, str4);
        o(this.j0, str3);
        o(this.k0, str6);
        o(this.l0, str5);
        if (lyVar.L) {
            this.m0.setVisibility(0);
        }
        if (bitmap == null || !C0472Hs.M) {
            KN0 kn0 = this.c0.P;
            if (lyVar.G.length() > 0) {
                StringBuilder i4 = AbstractC2531fV.i(str7);
                i4.append(lyVar.G.charAt(0));
                str7 = i4.toString();
                String[] split = lyVar.G.split(" ");
                if (split.length > 1) {
                    StringBuilder i5 = AbstractC2531fV.i(str7);
                    i5.append(split[split.length - 1].charAt(0));
                    str7 = i5.toString();
                }
            }
            this.a0 = new BitmapDrawable(getResources(), kn0.a(str7));
            j(false);
            return;
        }
        n(bitmap);
    }

    public void n(Bitmap bitmap) {
        HN0 hn0 = new HN0(this.b0.getResources(), bitmap);
        hn0.k = true;
        hn0.j = true;
        hn0.g = (float) (Math.min(hn0.m, hn0.l) / 2);
        hn0.d.setShader(hn0.e);
        hn0.invalidateSelf();
        this.a0 = hn0;
        j(false);
    }

    public final void o(TextView textView, String str) {
        textView.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        textView.setText(str);
    }

    @Override // defpackage.VR0
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.address_overflow_count || id == R.id.email_overflow_count || id == R.id.telephone_number_overflow_count) {
            onLongClick(this);
        } else {
            super.onClick(view);
        }
    }

    @Override // defpackage.TR0, defpackage.VR0
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f0 = (TextView) findViewById(R.id.title);
        this.g0 = (TextView) findViewById(R.id.address);
        this.h0 = (TextView) findViewById(R.id.address_overflow_count);
        this.i0 = (TextView) findViewById(R.id.email);
        this.j0 = (TextView) findViewById(R.id.email_overflow_count);
        this.k0 = (TextView) findViewById(R.id.telephone_number);
        this.l0 = (TextView) findViewById(R.id.telephone_number_overflow_count);
        this.m0 = (ImageView) findViewById(R.id.star);
        this.h0.setOnClickListener(this);
        this.j0.setOnClickListener(this);
        this.l0.setOnClickListener(this);
    }

    @Override // defpackage.VR0
    public boolean onLongClick(View view) {
        this.n0 = this.c0.I.v0();
        C5003ty tyVar = new C5003ty(this);
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, tyVar);
        hh0.e(AbstractC3258jl0.c, this.e0.G);
        hh0.e(AbstractC3258jl0.e, this.e0.b(C0472Hs.I, C0472Hs.K, C0472Hs.L));
        hh0.d(AbstractC3258jl0.g, this.b0.getResources(), R.string.f49160_resource_name_obfuscated_RES_2131952233);
        UH0 a2 = hh0.a();
        this.o0 = a2;
        a2.m(AbstractC3258jl0.d, this.a0);
        this.n0.i(this.o0, 0, false);
        return true;
    }
}
