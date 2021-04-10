package defpackage;

import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import java.io.IOException;

/* renamed from: aj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1707aj1 {

    /* renamed from: a  reason: collision with root package name */
    public final C2229dj1 f9446a;

    public C1707aj1(C2229dj1 dj1) {
        this.f9446a = dj1;
    }

    public boolean a(String str, PackageManager packageManager) {
        AbstractC3114iu0 iu0;
        C2229dj1 dj1 = this.f9446a;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                iu0 = new C2773gu0();
            } else {
                iu0 = new C2944hu0();
            }
            return iu0.a(str, packageManager, dj1);
        } catch (PackageManager.NameNotFoundException | IOException e) {
            Log.e("PackageIdentity", "Could not check if package matches token.", e);
            return false;
        }
    }
}
