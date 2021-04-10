package defpackage;

import android.app.Activity;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;

/* renamed from: FO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FO extends K21 {

    /* renamed from: a  reason: collision with root package name */
    public final FeedStreamSurface f8014a;
    public final C1322Vq0 b = new C1322Vq0();
    public RecyclerView c;
    public int d;
    public String e;
    public DO f = new DO(this);

    public FO(Activity activity, boolean z, View$OnClickListenerC5098uY0 uy0, AbstractC0095Bm0 bm0, AbstractC4448qj qjVar, boolean z2, Q31 q31) {
        this.f8014a = new FeedStreamSurface(activity, z, uy0, bm0, qjVar, C2535fX.a(), z2, new OO(q31));
    }

    @Override // defpackage.K21
    public void a(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((TW) it.next()).b());
        }
        FeedStreamSurface feedStreamSurface = this.f8014a;
        Objects.requireNonNull(feedStreamSurface);
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            View view = (View) arrayList.get(i);
            StringBuilder i2 = AbstractC2531fV.i("Header");
            i2.append(view.hashCode());
            arrayList2.add(new C5587xO(i2.toString(), view));
        }
        for (int i3 = feedStreamSurface.p; i3 < feedStreamSurface.e.a(); i3++) {
            arrayList2.add(feedStreamSurface.e.c(i3));
        }
        feedStreamSurface.c(arrayList2);
        feedStreamSurface.p = arrayList.size();
    }
}
