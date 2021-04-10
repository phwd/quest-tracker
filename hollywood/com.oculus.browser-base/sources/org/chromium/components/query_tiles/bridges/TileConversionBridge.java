package org.chromium.components.query_tiles.bridges;

import java.util.ArrayList;
import java.util.List;
import org.chromium.components.query_tiles.QueryTile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TileConversionBridge {
    public static List createList() {
        return new ArrayList();
    }

    public static QueryTile createTileAndMaybeAddToList(List list, String str, String str2, String str3, String str4, String[] strArr, String[] strArr2, List list2) {
        QueryTile queryTile = new QueryTile(str, str2, str3, str4, strArr, strArr2, list2);
        if (list != null) {
            list.add(queryTile);
        }
        return queryTile;
    }
}
