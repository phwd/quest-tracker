package defpackage;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;

/* renamed from: Il1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Il1 {
    public static void a(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
            return;
        }
        Ll1 ll1 = Ll1.F;
        if (ll1 != null && ll1.H == view) {
            Ll1.c(null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            Ll1 ll12 = Ll1.G;
            if (ll12 != null && ll12.H == view) {
                ll12.b();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new Ll1(view, charSequence);
    }
}
