package defpackage;

import android.os.Build;
import android.view.ViewGroup;

/* renamed from: ju1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3286ju1 {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f10246a = true;

    public static void a(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            viewGroup.suppressLayout(z);
        } else if (f10246a) {
            try {
                viewGroup.suppressLayout(z);
            } catch (NoSuchMethodError unused) {
                f10246a = false;
            }
        }
    }
}
