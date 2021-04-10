package defpackage;

import android.content.Context;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.chromium.base.task.PostTask;

/* renamed from: X4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X4 implements S4 {
    public final Context F;
    public final SR G;
    public final Map H;
    public final Set I;

    public X4(Context context, V4 v4) {
        SR sr = new SR();
        HashMap hashMap = new HashMap();
        hashMap.put("google sans regular", f0("Google Sans", 400));
        hashMap.put("google sans medium", f0("Google Sans", 500));
        hashMap.put("google sans bold", f0("Google Sans", 700));
        this.F = context;
        this.G = sr;
        this.H = hashMap;
        this.I = new HashSet(hashMap.keySet());
    }

    public static String f0(String str, int i) {
        return String.format(Locale.US, "name=%s&weight=%d&besteffort=false", str, Integer.valueOf(i));
    }

    @Override // defpackage.S4
    public void P(String str, C2635g5 g5Var) {
        SA sa = VA.f9067a;
        PostTask.b(C3070if1.e, new T4(this, str, sa, AbstractC3880nM.a(sa), g5Var), 0);
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // defpackage.S4
    public void b0(C1952c5 c5Var) {
        Set set = this.I;
        String[] strArr = (String[]) set.toArray(new String[set.size()]);
        Arrays.sort(strArr);
        c5Var.a(strArr);
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
    }
}
