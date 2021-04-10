package defpackage;

import J.N;
import android.util.Printer;
import org.chromium.base.EarlyTraceEvent;
import org.chromium.base.TraceEvent;

/* renamed from: ym1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5819ym1 implements Printer {

    /* renamed from: a  reason: collision with root package name */
    public String f11697a;

    public C5819ym1(AbstractC5139um1 um1) {
    }

    public void a(String str) {
        int i;
        int i2;
        boolean f = EarlyTraceEvent.f();
        if (TraceEvent.F || f) {
            StringBuilder i3 = AbstractC2531fV.i("Looper.dispatch: ");
            int indexOf = str.indexOf(40, 18);
            if (indexOf == -1) {
                i = -1;
            } else {
                i = str.indexOf(41, indexOf);
            }
            String str2 = "";
            i3.append(i != -1 ? str.substring(indexOf + 1, i) : str2);
            i3.append("(");
            int indexOf2 = str.indexOf(125, 18);
            if (indexOf2 == -1) {
                i2 = -1;
            } else {
                i2 = str.indexOf(58, indexOf2);
            }
            if (i2 == -1) {
                i2 = str.length();
            }
            if (indexOf2 != -1) {
                str2 = str.substring(indexOf2 + 2, i2);
            }
            this.f11697a = AbstractC2531fV.h(i3, str2, ")");
            if (TraceEvent.F) {
                N.M_y76mct(this.f11697a);
            } else {
                EarlyTraceEvent.a(this.f11697a, true);
            }
        }
    }

    public void b(String str) {
        boolean f = EarlyTraceEvent.f();
        if ((TraceEvent.F || f) && this.f11697a != null) {
            if (TraceEvent.F) {
                N.MLJecZJ9(this.f11697a);
            } else {
                EarlyTraceEvent.g(this.f11697a, true);
            }
        }
        this.f11697a = null;
    }

    public void println(String str) {
        if (str.startsWith(">")) {
            a(str);
        } else {
            b(str);
        }
    }
}
