package org.chromium.components.query_tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QueryTile {

    /* renamed from: a  reason: collision with root package name */
    public final String f10884a;
    public final String b;
    public final String c;
    public final String d;
    public final List e;
    public final List f;
    public final List g;

    public QueryTile(String str, String str2, String str3, String str4, String[] strArr, String[] strArr2, List list) {
        this.f10884a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.f = Arrays.asList(strArr);
        this.g = strArr2 == null ? new ArrayList() : Arrays.asList(strArr2);
        this.e = Collections.unmodifiableList(list == null ? new ArrayList() : list);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
        // Method dump skipped, instructions count: 107
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.query_tiles.QueryTile.equals(java.lang.Object):boolean");
    }
}
