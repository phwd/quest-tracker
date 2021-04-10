package defpackage;

import android.util.Property;
import org.chromium.components.browser_ui.widget.NumberRollView;

/* renamed from: oq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4128oq0 extends Property {
    public C4128oq0(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((NumberRollView) obj).I);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        float floatValue = ((Float) obj2).floatValue();
        Property property = NumberRollView.F;
        ((NumberRollView) obj).b(floatValue);
    }
}
