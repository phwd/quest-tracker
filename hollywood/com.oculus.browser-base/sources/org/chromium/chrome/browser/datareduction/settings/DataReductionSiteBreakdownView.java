package org.chromium.chrome.browser.datareduction.settings;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Collections;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DataReductionSiteBreakdownView extends LinearLayout {
    public int F = 10;
    public TableLayout G;
    public TextView H;
    public TextView I;

    /* renamed from: J  reason: collision with root package name */
    public TextView f10650J;
    public TextView K;
    public List L;
    public boolean M;

    public DataReductionSiteBreakdownView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(List list) {
        this.L = list;
        c(this.I);
        c(this.f10650J);
        b(this.K);
        Collections.sort(this.L, new C1625aD(null));
        this.H.setContentDescription(getContext().getString(R.string.f50310_resource_name_obfuscated_RES_2131952348));
        if (this.L.size() == 0) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        d();
        UC.a(23);
    }

    public final void b(TextView textView) {
        Drawable drawable;
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        if (compoundDrawables[0] != null) {
            drawable = compoundDrawables[0];
        } else {
            drawable = compoundDrawables[2];
        }
        if (drawable == null) {
            this.M = true;
            return;
        }
        drawable.mutate();
        drawable.setAlpha(255);
        drawable.setColorFilter(new PorterDuffColorFilter(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
    }

    public final void c(TextView textView) {
        Drawable drawable;
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        if (compoundDrawables[0] != null) {
            drawable = compoundDrawables[0];
        } else {
            drawable = compoundDrawables[2];
        }
        if (drawable == null) {
            this.M = true;
            return;
        }
        drawable.mutate();
        drawable.setAlpha(0);
        drawable.clearColorFilter();
    }

    public final void d() {
        int i;
        TableLayout tableLayout = this.G;
        tableLayout.removeViews(1, tableLayout.getChildCount() - 1);
        char c = 0;
        int i2 = 0;
        int i3 = 0;
        long j = 0;
        long j2 = 0;
        while (i2 < this.L.size()) {
            if (i2 < this.F) {
                TableRow tableRow = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.f37750_resource_name_obfuscated_RES_2131624084, (ViewGroup) null);
                TextView textView = (TextView) tableRow.findViewById(R.id.site_hostname);
                TextView textView2 = (TextView) tableRow.findViewById(R.id.site_data_used);
                TextView textView3 = (TextView) tableRow.findViewById(R.id.site_data_saved);
                String str = ((HC) this.L.get(i2)).f8142a;
                if ("Other".equals(str)) {
                    str = getResources().getString(R.string.f50340_resource_name_obfuscated_RES_2131952351);
                }
                textView.setText(str);
                CharSequence a2 = YP.a(getContext(), ((HC) this.L.get(i2)).b);
                textView2.setText(a2);
                Resources resources = getResources();
                Object[] objArr = new Object[1];
                objArr[c] = a2;
                textView2.setContentDescription(resources.getString(R.string.f50410_resource_name_obfuscated_RES_2131952358, objArr));
                HC hc = (HC) this.L.get(i2);
                CharSequence a3 = YP.a(getContext(), Math.max(0L, hc.c - hc.b));
                textView3.setText(a3);
                textView3.setContentDescription(getResources().getString(R.string.f50360_resource_name_obfuscated_RES_2131952353, a3));
                this.G.addView(tableRow, i2 + 1);
                i = i2;
            } else {
                i3++;
                i = i2;
                j += ((HC) this.L.get(i)).b;
                j2 += ((HC) this.L.get(i)).a();
            }
            i2 = i + 1;
            c = 0;
        }
        if (i3 > 0) {
            TableRow tableRow2 = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.f37750_resource_name_obfuscated_RES_2131624084, (ViewGroup) null);
            TextView textView4 = (TextView) tableRow2.findViewById(R.id.site_hostname);
            TextView textView5 = (TextView) tableRow2.findViewById(R.id.site_data_used);
            TextView textView6 = (TextView) tableRow2.findViewById(R.id.site_data_saved);
            textView4.setText(getResources().getString(R.string.f50350_resource_name_obfuscated_RES_2131952352, Integer.valueOf(i3)));
            textView5.setText(Formatter.formatFileSize(getContext(), j));
            textView6.setText(Formatter.formatFileSize(getContext(), j2));
            int color = getContext().getResources().getColor(R.color.f11590_resource_name_obfuscated_RES_2131099849);
            textView4.setTextColor(color);
            textView5.setTextColor(color);
            textView6.setTextColor(color);
            tableRow2.setOnClickListener(new ZC(this));
            this.G.addView(tableRow2, this.F + 1);
        }
        this.G.requestLayout();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = (TableLayout) findViewById(R.id.data_reduction_proxy_breakdown_table);
        this.H = (TextView) findViewById(R.id.data_reduction_data_usage_breakdown_title);
        this.I = (TextView) findViewById(R.id.data_reduction_breakdown_site_title);
        this.f10650J = (TextView) findViewById(R.id.data_reduction_breakdown_used_title);
        this.K = (TextView) findViewById(R.id.data_reduction_breakdown_saved_title);
        this.I.setOnClickListener(new WC(this));
        this.f10650J.setOnClickListener(new XC(this));
        this.K.setOnClickListener(new YC(this));
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.M) {
            this.M = false;
            c(this.I);
            c(this.f10650J);
            b(this.K);
        }
    }
}
