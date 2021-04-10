package defpackage;

import java.util.Map;

/* renamed from: jj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3253jj1 implements WT {

    /* renamed from: a  reason: collision with root package name */
    public final String f10232a;

    public C3253jj1(String str) {
        this.f10232a = str;
    }

    @Override // defpackage.WT
    public Object apply(Object obj) {
        String str = this.f10232a;
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            if (((String) entry.getValue()).equals(str)) {
                return (String) entry.getKey();
            }
        }
        return null;
    }
}
