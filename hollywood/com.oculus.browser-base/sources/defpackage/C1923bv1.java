package defpackage;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: bv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1923bv1 {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f9573a = new ArrayList();
    public long b = -1;
    public Interpolator c;
    public AbstractC2094cv1 d;
    public boolean e;
    public final AbstractC2264dv1 f = new C1752av1(this);

    public void a() {
        if (this.e) {
            Iterator it = this.f9573a.iterator();
            while (it.hasNext()) {
                ((Zu1) it.next()).b();
            }
            this.e = false;
        }
    }

    public void b() {
        View view;
        if (!this.e) {
            Iterator it = this.f9573a.iterator();
            while (it.hasNext()) {
                Zu1 zu1 = (Zu1) it.next();
                long j = this.b;
                if (j >= 0) {
                    zu1.c(j);
                }
                Interpolator interpolator = this.c;
                if (!(interpolator == null || (view = (View) zu1.f9382a.get()) == null)) {
                    view.animate().setInterpolator(interpolator);
                }
                if (this.d != null) {
                    zu1.d(this.f);
                }
                View view2 = (View) zu1.f9382a.get();
                if (view2 != null) {
                    view2.animate().start();
                }
            }
            this.e = true;
        }
    }
}
