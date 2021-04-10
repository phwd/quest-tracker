package defpackage;

import java.util.Locale;

/* renamed from: Vq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Vq1 {

    /* renamed from: a  reason: collision with root package name */
    public final CharSequence f9109a;
    public final CharSequence b;
    public final int c;
    public int d;
    public final int e;

    public Vq1(CharSequence charSequence, CharSequence charSequence2, int i, int i2, int i3) {
        this.f9109a = charSequence;
        this.b = charSequence2;
        this.c = i;
        this.d = i2;
        this.e = i3;
    }

    public String toString() {
        return String.format(Locale.US, "%s: text: %s; scrollType: %d; selectionState: %d", Vq1.class.getSimpleName(), this.f9109a, Integer.valueOf(this.c), Integer.valueOf(this.e));
    }
}
