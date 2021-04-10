package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: yq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5830yq0 {

    /* renamed from: a  reason: collision with root package name */
    public final Map f11703a = new HashMap();
    public final List b = new ArrayList();

    public void a(String str, String str2) {
        if (str.equals("type")) {
            this.b.add(str2);
        } else {
            this.f11703a.put(str, str2);
        }
    }
}
