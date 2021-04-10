package defpackage;

import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* renamed from: mg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3757mg1 {

    /* renamed from: a  reason: collision with root package name */
    public final TextPaint f10438a = new TextPaint(1);
    public final AbstractC0931Pf1 b = new C3415kg1(this);
    public float c;
    public boolean d = true;
    public WeakReference e = new WeakReference(null);
    public C0870Of1 f;

    public C3757mg1(AbstractC3586lg1 lg1) {
        this.e = new WeakReference(lg1);
    }

    public float a(String str) {
        float f2;
        if (!this.d) {
            return this.c;
        }
        if (str == null) {
            f2 = 0.0f;
        } else {
            f2 = this.f10438a.measureText((CharSequence) str, 0, str.length());
        }
        this.c = f2;
        this.d = false;
        return f2;
    }
}
