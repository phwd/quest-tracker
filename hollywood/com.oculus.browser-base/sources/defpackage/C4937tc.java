package defpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.components.query_tiles.QueryTile;

/* renamed from: tc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4937tc extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4305ps0 f11351a;

    public C4937tc(C4305ps0 ps0) {
        this.f11351a = ps0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4305ps0 ps0 = this.f11351a;
        List list = (List) obj;
        C4957ti1 ti1 = ps0.c;
        Objects.requireNonNull(ti1);
        if (list != null && !list.isEmpty() && ti1.f11363a == null) {
            ti1.f11363a = list;
            AbstractC3364kK0.d("Search.QueryTiles.Omnibox.TileCount", list.size());
        }
        C1059Rh1 a2 = ps0.a();
        ArrayList arrayList = list == null ? new ArrayList() : new ArrayList(list);
        Objects.requireNonNull(a2);
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < a2.f8846a.size(); i++) {
            arrayList2.add((QueryTile) a2.f8846a.get(i));
        }
        boolean z = !arrayList2.isEmpty() && !arrayList.isEmpty() && !arrayList2.equals(arrayList);
        a2.f8846a.t(arrayList);
        C2909hi1 hi1 = a2.b;
        if (hi1.b.computeHorizontalScrollOffset() != 0) {
            hi1.b.U.N0(0);
        }
        C2909hi1 hi12 = a2.b;
        Objects.requireNonNull(hi12);
        if (z) {
            hi12.b.setLayoutAnimation(hi12.e);
            hi12.b.scheduleLayoutAnimation();
        }
    }
}
