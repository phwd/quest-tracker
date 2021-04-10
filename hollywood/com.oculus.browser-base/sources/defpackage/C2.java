package defpackage;

import android.util.Property;
import android.view.ViewGroup;

/* renamed from: C2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2 extends Property {
    public C2(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Integer.valueOf(((ViewGroup.MarginLayoutParams) ((C3802mv1) obj).b.getLayoutParams()).topMargin);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        C3802mv1 mv1 = (C3802mv1) obj;
        int intValue = ((Integer) obj2).intValue();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) mv1.b.getLayoutParams();
        marginLayoutParams.topMargin = intValue;
        mv1.b.setLayoutParams(marginLayoutParams);
    }
}
