package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Log;

/* renamed from: qW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4413qW {

    /* renamed from: a  reason: collision with root package name */
    public static C4413qW f11145a;

    public C4413qW(Context context) {
        context.getApplicationContext();
    }

    public static AbstractBinderC5737yF1 a(PackageInfo packageInfo, AbstractBinderC5737yF1... yf1Arr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null) {
            return null;
        }
        if (signatureArr.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        BinderC5910zG1 zg1 = new BinderC5910zG1(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < yf1Arr.length; i++) {
            if (yf1Arr[i].equals(zg1)) {
                return yf1Arr[i];
            }
        }
        return null;
    }

    public static boolean b(PackageInfo packageInfo, boolean z) {
        AbstractBinderC5737yF1 yf1;
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            if (z) {
                yf1 = a(packageInfo, AbstractC1990cH1.f9596a);
            } else {
                yf1 = a(packageInfo, AbstractC1990cH1.f9596a[0]);
            }
            if (yf1 != null) {
                return true;
            }
        }
        return false;
    }
}
