package defpackage;

import android.widget.NumberPicker;
import java.util.Locale;

/* renamed from: Yl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1495Yl0 implements NumberPicker.Formatter {

    /* renamed from: a  reason: collision with root package name */
    public final String f9294a;

    public C1495Yl0(String str) {
        this.f9294a = str;
    }

    public String format(int i) {
        return String.format(Locale.getDefault(), this.f9294a, Integer.valueOf(i));
    }
}
