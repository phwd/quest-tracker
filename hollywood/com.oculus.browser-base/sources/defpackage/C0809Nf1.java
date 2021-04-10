package defpackage;

import android.graphics.Typeface;
import android.text.TextPaint;

/* renamed from: Nf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0809Nf1 extends AbstractC0931Pf1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TextPaint f8564a;
    public final /* synthetic */ AbstractC0931Pf1 b;
    public final /* synthetic */ C0870Of1 c;

    public C0809Nf1(C0870Of1 of1, TextPaint textPaint, AbstractC0931Pf1 pf1) {
        this.c = of1;
        this.f8564a = textPaint;
        this.b = pf1;
    }

    @Override // defpackage.AbstractC0931Pf1
    public void a(int i) {
        this.b.a(i);
    }

    @Override // defpackage.AbstractC0931Pf1
    public void b(Typeface typeface, boolean z) {
        this.c.d(this.f8564a, typeface);
        this.b.b(typeface, z);
    }
}
