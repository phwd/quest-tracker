package defpackage;

import java.util.Iterator;
import java.util.Objects;
import org.chromium.components.query_tiles.QueryTile;

/* renamed from: ls0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3621ls0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4305ps0 f10380a;

    public C3621ls0(C4305ps0 ps0) {
        this.f10380a = ps0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        int i;
        boolean z;
        int i2;
        C4305ps0 ps0 = this.f10380a;
        QueryTile queryTile = (QueryTile) obj;
        C4957ti1 ti1 = ps0.c;
        Objects.requireNonNull(ti1);
        String str = queryTile.f10884a;
        Iterator it = ti1.f11363a.iterator();
        while (true) {
            i = 0;
            if (it.hasNext()) {
                if (((QueryTile) it.next()).f10884a.equals(str)) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        AbstractC3100ip1.f10165a.a("Search.QueryTiles.Omnibox.Tile.Clicked.IsTopLevel", z);
        String str2 = queryTile.f10884a;
        while (true) {
            i2 = -1;
            if (i >= ti1.f11363a.size()) {
                break;
            }
            int a2 = ti1.a((QueryTile) ti1.f11363a.get(i), str2, i);
            if (a2 != -1) {
                i2 = a2;
                break;
            }
            i++;
        }
        AbstractC3100ip1.f10165a.d("Search.QueryTiles.Omnibox.Tile.Clicked", i2);
        ps0.b.onResult(queryTile);
    }
}
