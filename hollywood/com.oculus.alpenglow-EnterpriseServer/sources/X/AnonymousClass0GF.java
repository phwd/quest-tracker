package X;

import android.database.Cursor;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.FileProvider;
import com.oculus.alpenglow.logging.LoggerConstants;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0GF  reason: invalid class name */
public final class AnonymousClass0GF {
    public final String A00;
    public final Map<String, AnonymousClass0GB> A01;
    public final Set<AnonymousClass0GC> A02;
    @Nullable
    public final Set<AnonymousClass0GE> A03;

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        if (r1.equals(r5.A00) == false) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r3 = 1
            if (r4 == r5) goto L_0x004d
            boolean r0 = r5 instanceof X.AnonymousClass0GF
            r2 = 0
            if (r0 == 0) goto L_0x0016
            X.0GF r5 = (X.AnonymousClass0GF) r5
            java.lang.String r1 = r4.A00
            if (r1 == 0) goto L_0x0017
            java.lang.String r0 = r5.A00
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x001c
        L_0x0016:
            return r2
        L_0x0017:
            java.lang.String r0 = r5.A00
            if (r0 == 0) goto L_0x001c
            return r2
        L_0x001c:
            java.util.Map<java.lang.String, X.0GB> r1 = r4.A01
            if (r1 == 0) goto L_0x0029
            java.util.Map<java.lang.String, X.0GB> r0 = r5.A01
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x002e
            return r2
        L_0x0029:
            java.util.Map<java.lang.String, X.0GB> r0 = r5.A01
            if (r0 == 0) goto L_0x002e
            return r2
        L_0x002e:
            java.util.Set<X.0GC> r1 = r4.A02
            if (r1 == 0) goto L_0x003b
            java.util.Set<X.0GC> r0 = r5.A02
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0040
            return r2
        L_0x003b:
            java.util.Set<X.0GC> r0 = r5.A02
            if (r0 == 0) goto L_0x0040
            return r2
        L_0x0040:
            java.util.Set<X.0GE> r1 = r4.A03
            if (r1 == 0) goto L_0x004d
            java.util.Set<X.0GE> r0 = r5.A03
            if (r0 == 0) goto L_0x004d
            boolean r0 = r1.equals(r0)
            return r0
        L_0x004d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0GF.equals(java.lang.Object):boolean");
    }

    /* JADX INFO: finally extract failed */
    public static AnonymousClass0GF A00(AnonymousClass0GQ r22, String str) {
        Cursor A74 = r22.A74(AnonymousClass006.A07("PRAGMA table_info(`", str, "`)"));
        HashMap hashMap = new HashMap();
        try {
            if (A74.getColumnCount() > 0) {
                int columnIndex = A74.getColumnIndex(FileProvider.ATTR_NAME);
                int columnIndex2 = A74.getColumnIndex(LoggerConstants.CONFIGURATION_FETCH_TYPE);
                int columnIndex3 = A74.getColumnIndex("notnull");
                int columnIndex4 = A74.getColumnIndex("pk");
                int columnIndex5 = A74.getColumnIndex("dflt_value");
                while (A74.moveToNext()) {
                    String string = A74.getString(columnIndex);
                    String string2 = A74.getString(columnIndex2);
                    boolean z = false;
                    if (A74.getInt(columnIndex3) != 0) {
                        z = true;
                    }
                    hashMap.put(string, new AnonymousClass0GB(string, string2, z, A74.getInt(columnIndex4), A74.getString(columnIndex5), 2));
                }
            }
            A74.close();
            HashSet hashSet = new HashSet();
            Cursor A742 = r22.A74(AnonymousClass006.A07("PRAGMA foreign_key_list(`", str, "`)"));
            try {
                int columnIndex6 = A742.getColumnIndex(OCMSLibraryContract.ASSETS_PATH_BY_ID);
                int columnIndex7 = A742.getColumnIndex("seq");
                int columnIndex8 = A742.getColumnIndex("table");
                int columnIndex9 = A742.getColumnIndex("on_delete");
                int columnIndex10 = A742.getColumnIndex("on_update");
                int columnIndex11 = A742.getColumnIndex(OCMSLibraryContract.ASSETS_PATH_BY_ID);
                int columnIndex12 = A742.getColumnIndex("seq");
                int columnIndex13 = A742.getColumnIndex("from");
                int columnIndex14 = A742.getColumnIndex("to");
                int count = A742.getCount();
                ArrayList<AnonymousClass0GD> arrayList = new ArrayList();
                for (int i = 0; i < count; i++) {
                    A742.moveToPosition(i);
                    arrayList.add(new AnonymousClass0GD(A742.getInt(columnIndex11), A742.getInt(columnIndex12), A742.getString(columnIndex13), A742.getString(columnIndex14)));
                }
                Collections.sort(arrayList);
                int count2 = A742.getCount();
                for (int i2 = 0; i2 < count2; i2++) {
                    A742.moveToPosition(i2);
                    if (A742.getInt(columnIndex7) == 0) {
                        int i3 = A742.getInt(columnIndex6);
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList arrayList3 = new ArrayList();
                        for (AnonymousClass0GD r8 : arrayList) {
                            if (r8.A01 == i3) {
                                arrayList2.add(r8.A02);
                                arrayList3.add(r8.A03);
                            }
                        }
                        hashSet.add(new AnonymousClass0GC(A742.getString(columnIndex8), A742.getString(columnIndex9), A742.getString(columnIndex10), arrayList2, arrayList3));
                    }
                }
                A742.close();
                Cursor A743 = r22.A74(AnonymousClass006.A07("PRAGMA index_list(`", str, "`)"));
                try {
                    int columnIndex15 = A743.getColumnIndex(FileProvider.ATTR_NAME);
                    int columnIndex16 = A743.getColumnIndex("origin");
                    int columnIndex17 = A743.getColumnIndex("unique");
                    HashSet hashSet2 = null;
                    if (!(columnIndex15 == -1 || columnIndex16 == -1 || columnIndex17 == -1)) {
                        HashSet hashSet3 = new HashSet();
                        while (A743.moveToNext()) {
                            if ("c".equals(A743.getString(columnIndex16))) {
                                String string3 = A743.getString(columnIndex15);
                                boolean z2 = true;
                                if (A743.getInt(columnIndex17) != 1) {
                                    z2 = false;
                                }
                                Cursor A744 = r22.A74(AnonymousClass006.A07("PRAGMA index_xinfo(`", string3, "`)"));
                                try {
                                    int columnIndex18 = A744.getColumnIndex("seqno");
                                    int columnIndex19 = A744.getColumnIndex("cid");
                                    int columnIndex20 = A744.getColumnIndex(FileProvider.ATTR_NAME);
                                    if (columnIndex18 == -1 || columnIndex19 == -1 || columnIndex20 == -1) {
                                        A744.close();
                                    } else {
                                        TreeMap treeMap = new TreeMap();
                                        while (A744.moveToNext()) {
                                            if (A744.getInt(columnIndex19) >= 0) {
                                                int i4 = A744.getInt(columnIndex18);
                                                treeMap.put(Integer.valueOf(i4), A744.getString(columnIndex20));
                                            }
                                        }
                                        ArrayList arrayList4 = new ArrayList(treeMap.size());
                                        arrayList4.addAll(treeMap.values());
                                        AnonymousClass0GE r0 = new AnonymousClass0GE(string3, z2, arrayList4);
                                        A744.close();
                                        hashSet3.add(r0);
                                    }
                                } catch (Throwable th) {
                                    A744.close();
                                    throw th;
                                }
                            }
                        }
                        A743.close();
                        hashSet2 = hashSet3;
                        return new AnonymousClass0GF(str, hashMap, hashSet, hashSet2);
                    }
                    A743.close();
                    return new AnonymousClass0GF(str, hashMap, hashSet, hashSet2);
                } catch (Throwable th2) {
                    A743.close();
                    throw th2;
                }
            } catch (Throwable th3) {
                A742.close();
                throw th3;
            }
        } catch (Throwable th4) {
            A74.close();
            throw th4;
        }
    }

    public final int hashCode() {
        int i;
        int i2;
        String str = this.A00;
        int i3 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i4 = i * 31;
        Map<String, AnonymousClass0GB> map = this.A01;
        if (map != null) {
            i2 = map.hashCode();
        } else {
            i2 = 0;
        }
        int i5 = (i4 + i2) * 31;
        Set<AnonymousClass0GC> set = this.A02;
        if (set != null) {
            i3 = set.hashCode();
        }
        return i5 + i3;
    }

    public final String toString() {
        return "TableInfo{name='" + this.A00 + '\'' + ", columns=" + this.A01 + ", foreignKeys=" + this.A02 + ", indices=" + this.A03 + '}';
    }

    public AnonymousClass0GF(String str, Map<String, AnonymousClass0GB> map, Set<AnonymousClass0GC> set, Set<AnonymousClass0GE> set2) {
        Set<AnonymousClass0GE> unmodifiableSet;
        this.A00 = str;
        this.A01 = Collections.unmodifiableMap(map);
        this.A02 = Collections.unmodifiableSet(set);
        if (set2 == null) {
            unmodifiableSet = null;
        } else {
            unmodifiableSet = Collections.unmodifiableSet(set2);
        }
        this.A03 = unmodifiableSet;
    }
}
