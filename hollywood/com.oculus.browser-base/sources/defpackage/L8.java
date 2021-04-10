package defpackage;

import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* renamed from: L8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L8 extends AbstractC5414wM0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f8407a;
    public final /* synthetic */ int b;
    public final /* synthetic */ WeakReference c;
    public final /* synthetic */ M8 d;

    public L8(M8 m8, int i, int i2, WeakReference weakReference) {
        this.d = m8;
        this.f8407a = i;
        this.b = i2;
        this.c = weakReference;
    }

    @Override // defpackage.AbstractC5414wM0
    public void c(int i) {
    }

    @Override // defpackage.AbstractC5414wM0
    public void d(Typeface typeface) {
        int i;
        if (Build.VERSION.SDK_INT >= 28 && (i = this.f8407a) != -1) {
            typeface = Typeface.create(typeface, i, (this.b & 2) != 0);
        }
        M8 m8 = this.d;
        WeakReference weakReference = this.c;
        if (m8.l) {
            m8.k = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, m8.i);
            }
        }
    }
}
