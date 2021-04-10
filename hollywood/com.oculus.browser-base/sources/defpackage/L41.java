package defpackage;

import android.util.Property;
import androidx.appcompat.widget.SwitchCompat;

/* renamed from: L41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L41 extends Property {
    public L41(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((SwitchCompat) obj).h0);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        SwitchCompat switchCompat = (SwitchCompat) obj;
        switchCompat.h0 = ((Float) obj2).floatValue();
        switchCompat.invalidate();
    }
}
