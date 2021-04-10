package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;

/* renamed from: Uv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Uv1 {

    /* renamed from: a  reason: collision with root package name */
    public final List f9054a = new ArrayList();
    public final float b = 0.3f;
    public final float c = 0.8f;

    public final void a(List list, Qv1 qv1, float f) {
        boolean z;
        Iterator it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((AutocompleteMatch) it.next()).d.equals(qv1.f8794a)) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            float f2 = qv1.b;
            if (f2 >= f || f2 <= 0.0f) {
                GURL d = AbstractC0444Hf1.a().d(qv1.f8794a);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C1347Wc(0, 0));
                list.add(new AutocompleteMatch(20, null, true, 0, 1, qv1.f8794a, arrayList, null, arrayList, null, null, d, GURL.emptyGURL(), null, false, null, null, -1, null, null, false, null));
            }
        }
    }
}
