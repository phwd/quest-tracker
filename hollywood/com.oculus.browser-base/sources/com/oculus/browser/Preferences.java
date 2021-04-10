package com.oculus.browser;

import android.content.SharedPreferences;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Preferences {

    /* renamed from: a  reason: collision with root package name */
    public static Preferences f9707a;
    public SharedPreferences b;

    public Preferences(SharedPreferences sharedPreferences) {
        this.b = sharedPreferences;
    }

    public static Preferences getInstance() {
        return f9707a;
    }

    public boolean a() {
        return this.b != null;
    }

    public boolean getBoolean(String str, boolean z) {
        return this.b.getBoolean(str, z);
    }

    public int getInt(String str, int i) {
        return this.b.getInt(str, i);
    }

    public String getString(String str, String str2) {
        return this.b.getString(str, str2);
    }

    public boolean hasKey(String str) {
        return this.b.contains(str);
    }

    public void setBoolean(String str, boolean z) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public void setInt(String str, int i) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public void setString(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString(str, str2);
        edit.apply();
    }
}
