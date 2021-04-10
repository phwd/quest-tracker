package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import java.util.concurrent.Executor;

/* renamed from: HD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class HD {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8145a = new Object();
    public static AbstractC2032cb b;

    public static String a(Context context, String str) {
        if (str == null) {
            return context.getString(R.string.f54730_resource_name_obfuscated_RES_2131952790);
        }
        return context.getString(R.string.f54720_resource_name_obfuscated_RES_2131952789, str);
    }

    public static void b() {
        synchronized (f8145a) {
            if (b == null) {
                ED ed = new ED();
                b = ed;
                Executor executor = AbstractC2032cb.f9616a;
                ed.f();
                ((ExecutorC1463Ya) executor).execute(ed.e);
            }
        }
    }
}
