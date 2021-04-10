package defpackage;

import android.util.Log;
import java.util.HashMap;

/* renamed from: tM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4903tM {

    /* renamed from: a  reason: collision with root package name */
    public String f11338a;
    public String b;
    public String c;
    public HashMap d = new HashMap();

    public C4903tM(String str, String str2, String str3) {
        this.b = str2;
        this.c = str3;
        this.f11338a = str;
    }

    public String a(String str) {
        if (this.d.containsKey(str)) {
            return (String) this.d.get(str);
        }
        Log.e("ExperimentationUniverseData", "QE parameter does not exist!");
        return null;
    }
}
