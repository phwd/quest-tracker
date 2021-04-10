package defpackage;

import android.app.Activity;
import android.widget.FrameLayout;

/* renamed from: sg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4779sg {

    /* renamed from: a  reason: collision with root package name */
    public int f11291a;
    public boolean b;

    public void a(Activity activity) {
        if (this.b) {
            FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView();
            if ((frameLayout.getSystemUiVisibility() & 1024) == 0) {
                frameLayout.setSystemUiVisibility(this.f11291a);
            }
        }
    }

    public void b(Activity activity) {
        int systemUiVisibility = ((FrameLayout) activity.getWindow().getDecorView()).getSystemUiVisibility();
        this.f11291a = systemUiVisibility;
        this.b = (systemUiVisibility & 1024) != 0;
    }
}
