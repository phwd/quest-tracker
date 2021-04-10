package defpackage;

import java.util.List;
import org.chromium.components.query_tiles.QueryTile;

/* renamed from: ti1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4957ti1 {

    /* renamed from: a  reason: collision with root package name */
    public List f11363a;

    public C4957ti1(String str) {
    }

    public final int a(QueryTile queryTile, String str, int i) {
        if (str.equals(queryTile.f10884a)) {
            return i;
        }
        int i2 = (i + 1) * 100;
        for (int i3 = 0; i3 < queryTile.e.size(); i3++) {
            int a2 = a((QueryTile) queryTile.e.get(i3), str, i2 + i3);
            if (a2 != -1) {
                return a2;
            }
        }
        return -1;
    }
}
