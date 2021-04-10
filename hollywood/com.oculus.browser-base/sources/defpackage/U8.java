package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import java.lang.reflect.Constructor;

/* renamed from: U8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U8 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class[] f9008a = {Context.class, AttributeSet.class};
    public static final int[] b = {16843375};
    public static final String[] c = {"android.widget.", "android.view.", "android.webkit."};
    public static final BW0 d = new BW0();
    public final Object[] e = new Object[2];

    public final View a(Context context, String str, String str2) {
        String str3;
        BW0 bw0 = d;
        Constructor<? extends U> constructor = (Constructor) bw0.getOrDefault(str, null);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception unused) {
                    return null;
                }
            } else {
                str3 = str;
            }
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(f9008a);
            bw0.put(str, constructor);
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.e);
    }

    public final void b(View view, String str) {
    }
}
