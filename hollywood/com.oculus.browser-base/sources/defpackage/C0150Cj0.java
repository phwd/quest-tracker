package defpackage;

import android.content.Context;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: Cj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0150Cj0 implements AbstractC3939nk0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f7834a;
    public final Q31 b;
    public Map c = new LinkedHashMap();
    public Map d = new LinkedHashMap();
    public AbstractC0211Dj0 e;

    public C0150Cj0(Context context, Q31 q31, AbstractC0211Dj0 dj0) {
        this.f7834a = context;
        this.b = q31;
        this.e = dj0;
    }

    public void a(int i) {
        this.d.remove(Integer.valueOf(i));
        this.e.a(i);
    }
}
