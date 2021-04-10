package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import java.util.concurrent.Callable;

/* renamed from: JR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JR implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f8287a;
    public final /* synthetic */ DR b;
    public final /* synthetic */ int c;
    public final /* synthetic */ String d;

    public JR(Context context, DR dr, int i, String str) {
        this.f8287a = context;
        this.b = dr;
        this.c = i;
        this.d = str;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        QR b2 = RR.b(this.f8287a, this.b, this.c);
        Typeface typeface = b2.f8760a;
        if (typeface != null) {
            RR.f8830a.c(this.d, typeface);
        }
        return b2;
    }
}
