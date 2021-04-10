package defpackage;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import java.util.Map;
import org.chromium.base.ContextUtils;

/* renamed from: OY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OY extends AbstractC5079uP {
    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        String string = Settings.Secure.getString(ContextUtils.getApplicationContext().getContentResolver(), "default_input_method");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return AbstractC0417Gv.c(Pair.create("Default IME", string));
    }
}
