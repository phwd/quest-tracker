package defpackage;

import J.N;
import android.graphics.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.content.browser.accessibility.captioning.CaptioningController;

/* renamed from: lm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3602lm {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10371a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public final Map i = new WeakHashMap();

    public static String a(Integer num) {
        if (num == null) {
            return "";
        }
        return String.format("rgba(%s, %s, %s, %s)", Integer.valueOf(Color.red(num.intValue())), Integer.valueOf(Color.green(num.intValue())), Integer.valueOf(Color.blue(num.intValue())), new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US)).format(((double) Color.alpha(num.intValue())) / 255.0d));
    }

    public boolean b() {
        return !this.i.isEmpty();
    }

    public void c(CaptioningController captioningController) {
        boolean z = this.f10371a;
        if (z) {
            String str = this.b;
            String str2 = this.c;
            String str3 = this.d;
            String str4 = this.e;
            String str5 = this.f;
            String str6 = this.g;
            String str7 = this.h;
            long j = captioningController.b;
            if (j != 0) {
                N.MM3_AH7F(j, captioningController, z, Objects.toString(str, ""), Objects.toString(str2, ""), Objects.toString(str3, ""), Objects.toString(str4, ""), Objects.toString(str5, ""), Objects.toString(str6, ""), Objects.toString(str7, ""));
                return;
            }
            return;
        }
        long j2 = captioningController.b;
        if (j2 != 0) {
            N.MM3_AH7F(j2, captioningController, false, Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""));
        }
    }

    public final void d() {
        for (CaptioningController captioningController : this.i.keySet()) {
            c(captioningController);
        }
    }

    public void e(float f2) {
        this.h = new DecimalFormat("#%", new DecimalFormatSymbols(Locale.US)).format((double) f2);
        d();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void f(defpackage.C3773mm r19) {
        /*
        // Method dump skipped, instructions count: 164
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3602lm.f(mm):void");
    }
}
