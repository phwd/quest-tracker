package defpackage;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.MetricAffectingSpan;
import android.text.style.TextAppearanceSpan;

/* renamed from: W6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class W6 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9128a;
    public final float b;
    public SpannableStringBuilder c = new SpannableStringBuilder();
    public String d;
    public int e;
    public int f = 1;

    public W6(Context context) {
        this.f9128a = context;
        this.b = context.getResources().getDisplayMetrics().density;
    }

    public void a(String str, MetricAffectingSpan[] metricAffectingSpanArr) {
        int textSize;
        int indexOf;
        String obj = Html.fromHtml(str).toString();
        X6 x6 = (X6) this;
        if (x6.g && x6.h == 10 && (indexOf = obj.indexOf(" = ")) > 0) {
            obj = obj.substring(indexOf + 3);
        }
        for (MetricAffectingSpan metricAffectingSpan : metricAffectingSpanArr) {
            if ((metricAffectingSpan instanceof TextAppearanceSpan) && this.e < (textSize = (int) (((float) ((TextAppearanceSpan) metricAffectingSpan).getTextSize()) / this.b))) {
                this.e = textSize;
            }
        }
        int length = this.c.length();
        this.c.append((CharSequence) obj);
        int length2 = this.c.length();
        for (MetricAffectingSpan metricAffectingSpan2 : metricAffectingSpanArr) {
            this.c.setSpan(metricAffectingSpan2, length, length2, 33);
        }
    }
}
