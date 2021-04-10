package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: UU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UU0 {

    /* renamed from: a  reason: collision with root package name */
    public C5375w80 f9030a;
    public List b;
    public List c;

    public UU0(C5375w80 w80, List list) {
        this.f9030a = w80;
        this.b = list;
    }

    public List a() {
        if (this.b == null && this.c == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List list = this.b;
        if (list != null) {
            arrayList.addAll(list);
        }
        List list2 = this.c;
        if (list2 != null) {
            arrayList.addAll(list2);
        }
        return Collections.unmodifiableList(arrayList);
    }
}
