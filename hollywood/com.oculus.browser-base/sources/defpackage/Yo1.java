package defpackage;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* renamed from: Yo1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Yo1 {

    /* renamed from: a  reason: collision with root package name */
    public Xo1 f9298a;
    public final List b = new ArrayList();
    public final Context c;

    public Yo1(View view) {
        this.c = view.getContext();
        this.f9298a = a();
        view.addOnAttachStateChangeListener(new Wo1(this));
    }

    public final Xo1 a() {
        int i = this.c.getResources().getConfiguration().screenWidthDp;
        int i2 = this.c.getResources().getConfiguration().screenHeightDp;
        int i3 = 1;
        int i4 = i <= 320 ? 0 : i >= 600 ? 2 : 1;
        if (i2 <= 320) {
            i3 = 0;
        }
        return new Xo1(i4, i3);
    }

    public void b() {
        Xo1 a2 = a();
        this.f9298a = a2;
        for (AbstractC3180jG jGVar : this.b) {
            jGVar.a(a2);
        }
    }
}
