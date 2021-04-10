package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: hH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2844hH1 {

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f10061a;

    public static SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (f10061a == null) {
                f10061a = (SharedPreferences) VF1.a(new CH1(context));
            }
            sharedPreferences = f10061a;
        }
        return sharedPreferences;
    }
}
