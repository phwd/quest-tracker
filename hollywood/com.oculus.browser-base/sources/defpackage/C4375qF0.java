package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceScreen;

/* renamed from: qF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4375qF0 {

    /* renamed from: a  reason: collision with root package name */
    public Context f11127a;
    public long b = 0;
    public SharedPreferences c;
    public SharedPreferences.Editor d;
    public boolean e;
    public String f;
    public PreferenceScreen g;
    public AbstractC4204pF0 h;
    public AbstractC3862nF0 i;
    public AbstractC4033oF0 j;

    public C4375qF0(Context context) {
        this.f11127a = context;
        this.f = context.getPackageName() + "_preferences";
        this.c = null;
    }

    public PreferenceScreen a(Context context) {
        PreferenceScreen preferenceScreen = new PreferenceScreen(context, null);
        preferenceScreen.w(this);
        return preferenceScreen;
    }

    public SharedPreferences.Editor b() {
        if (!this.e) {
            return c().edit();
        }
        if (this.d == null) {
            this.d = c().edit();
        }
        return this.d;
    }

    public SharedPreferences c() {
        if (this.c == null) {
            this.c = this.f11127a.getSharedPreferences(this.f, 0);
        }
        return this.c;
    }
}
