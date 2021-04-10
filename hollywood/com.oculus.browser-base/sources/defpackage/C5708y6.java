package defpackage;

import android.telephony.TelephonyManager;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: y6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5708y6 {

    /* renamed from: a  reason: collision with root package name */
    public static volatile C5708y6 f11662a;
    public volatile String b;
    public volatile String c;
    public C5538x6 d;

    public static C5708y6 a() {
        C5708y6 y6Var = f11662a;
        if (y6Var == null) {
            synchronized (C5708y6.class) {
                y6Var = f11662a;
                if (y6Var == null) {
                    y6Var = new C5708y6();
                    ThreadUtils.g(new RunnableC5198v6(y6Var));
                    f11662a = y6Var;
                }
            }
        }
        return y6Var;
    }

    public static TelephonyManager b() {
        return (TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone");
    }
}
