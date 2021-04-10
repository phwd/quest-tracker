package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* renamed from: fT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2528fT0 {
    public Intent a(Context context, String str, Bundle bundle) {
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        if (str != null) {
            l.putExtra("show_fragment", str);
        }
        if (bundle != null) {
            l.putExtra("show_fragment_args", bundle);
        }
        return l;
    }

    public void b(Context context, Class cls, Bundle bundle) {
        U20.q(context, a(context, cls != null ? cls.getName() : null, bundle));
    }
}
