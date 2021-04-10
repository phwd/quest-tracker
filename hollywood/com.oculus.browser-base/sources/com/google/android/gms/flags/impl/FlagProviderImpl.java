package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FlagProviderImpl extends BF1 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f9669a = false;
    public SharedPreferences b;

    @Override // defpackage.AbstractC1633aF1
    public boolean getBooleanFlagValue(String str, boolean z, int i) {
        if (!this.f9669a) {
            return z;
        }
        SharedPreferences sharedPreferences = this.b;
        Boolean valueOf = Boolean.valueOf(z);
        try {
            valueOf = (Boolean) VF1.a(new CallableC1813bF1(sharedPreferences, str, valueOf));
        } catch (Exception e) {
            String valueOf2 = String.valueOf(e.getMessage());
            Log.w("FlagDataUtils", valueOf2.length() != 0 ? "Flag value not available, returning default: ".concat(valueOf2) : new String("Flag value not available, returning default: "));
        }
        return valueOf.booleanValue();
    }

    @Override // defpackage.AbstractC1633aF1
    public int getIntFlagValue(String str, int i, int i2) {
        if (!this.f9669a) {
            return i;
        }
        SharedPreferences sharedPreferences = this.b;
        Integer valueOf = Integer.valueOf(i);
        try {
            valueOf = (Integer) VF1.a(new CallableC1816bG1(sharedPreferences, str, valueOf));
        } catch (Exception e) {
            String valueOf2 = String.valueOf(e.getMessage());
            Log.w("FlagDataUtils", valueOf2.length() != 0 ? "Flag value not available, returning default: ".concat(valueOf2) : new String("Flag value not available, returning default: "));
        }
        return valueOf.intValue();
    }

    @Override // defpackage.AbstractC1633aF1
    public long getLongFlagValue(String str, long j, int i) {
        if (!this.f9669a) {
            return j;
        }
        SharedPreferences sharedPreferences = this.b;
        Long valueOf = Long.valueOf(j);
        try {
            valueOf = (Long) VF1.a(new DG1(sharedPreferences, str, valueOf));
        } catch (Exception e) {
            String valueOf2 = String.valueOf(e.getMessage());
            Log.w("FlagDataUtils", valueOf2.length() != 0 ? "Flag value not available, returning default: ".concat(valueOf2) : new String("Flag value not available, returning default: "));
        }
        return valueOf.longValue();
    }

    @Override // defpackage.AbstractC1633aF1
    public String getStringFlagValue(String str, String str2, int i) {
        if (!this.f9669a) {
            return str2;
        }
        try {
            return (String) VF1.a(new WG1(this.b, str, str2));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("FlagDataUtils", valueOf.length() != 0 ? "Flag value not available, returning default: ".concat(valueOf) : new String("Flag value not available, returning default: "));
            return str2;
        }
    }

    @Override // defpackage.AbstractC1633aF1
    public void init(VY vy) {
        Context context = (Context) BinderC0773Mq0.f(vy);
        if (!this.f9669a) {
            try {
                this.b = AbstractC2844hH1.a(context.createPackageContext("com.google.android.gms", 0));
                this.f9669a = true;
            } catch (PackageManager.NameNotFoundException unused) {
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.w("FlagProviderImpl", valueOf.length() != 0 ? "Could not retrieve sdk flags, continuing with defaults: ".concat(valueOf) : new String("Could not retrieve sdk flags, continuing with defaults: "));
            }
        }
    }
}
