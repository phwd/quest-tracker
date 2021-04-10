package defpackage;

import android.content.SharedPreferences;

/* renamed from: LU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LU0 implements SharedPreferences.OnSharedPreferenceChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final OU0 f8419a;

    public LU0(OU0 ou0) {
        this.f8419a = ou0;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.f8419a.a(str);
    }
}
