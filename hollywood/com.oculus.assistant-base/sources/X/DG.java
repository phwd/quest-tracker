package X;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.facebook.crudolib.sqliteproc.annotations.DefaultDataMigrator;
import com.facebook.crudolib.sqliteproc.annotations.DropAllTablesDataMigrator;
import com.facebook.crudolib.sqliteproc.annotations.DropTableDataMigrator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class DG {
    public final C0793hh A00;
    public final D1 A01;
    public final D2 A02;
    public final C0839jj A03;

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[ExcHandler: ClassNotFoundException | IllegalAccessException | InstantiationException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:9:0x002d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A01(android.database.sqlite.SQLiteDatabase r3, X.DU r4) {
        /*
            r0 = 0
            java.lang.Class r0 = java.lang.Class.forName(r0)
            java.lang.Object r2 = r0.newInstance()
            X.DW r2 = (X.DW) r2
            java.lang.String r1 = "migrate_data_savepoint"
            java.lang.String r0 = "SAVEPOINT "
            java.lang.String r0 = X.AnonymousClass08.A04(r0, r1)
            r3.execSQL(r0)
            r2.A3n(r3, r4)     // Catch:{ DV -> 0x0024, SQLException -> 0x001a }
            goto L_0x002d
        L_0x001a:
            java.lang.String r0 = ";ROLLBACK TRANSACTION TO SAVEPOINT "
            java.lang.String r0 = X.AnonymousClass08.A04(r0, r1)     // Catch:{ all -> 0x0031 }
            r3.execSQL(r0)     // Catch:{ all -> 0x0031 }
            goto L_0x002d
        L_0x0024:
            java.lang.String r0 = ";ROLLBACK TRANSACTION TO SAVEPOINT "
            java.lang.String r0 = X.AnonymousClass08.A04(r0, r1)
            r3.execSQL(r0)
        L_0x002d:
            X.DD.A00(r3, r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0036, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0036 }
            return
        L_0x0031:
            r0 = move-exception
            X.DD.A00(r3, r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0036, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0036 }
            throw r0     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0036, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0036 }
        L_0x0036:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.DG.A01(android.database.sqlite.SQLiteDatabase, X.DU):void");
    }

    public static void A03(SQLiteDatabase sQLiteDatabase, String str, D7[] d7Arr) {
        for (D7 d7 : d7Arr) {
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE ");
            if (d7.A00) {
                sb.append("UNIQUE ");
            }
            sb.append("INDEX ");
            sb.append(str);
            String[] strArr = d7.A01;
            int length = strArr.length;
            for (String str2 : strArr) {
                sb.append("_");
                sb.append(str2);
            }
            sb.append(" ON ");
            sb.append(str);
            sb.append("(");
            sb.append(strArr[0]);
            String[] strArr2 = d7.A02;
            String str3 = strArr2[0];
            if (!str3.isEmpty()) {
                sb.append(" ");
                sb.append(str3);
            }
            for (int i = 1; i < length; i++) {
                sb.append(',');
                sb.append(strArr[i]);
                if (!strArr2[i].isEmpty()) {
                    sb.append(" ");
                    sb.append(strArr2[i]);
                }
            }
            sb.append(")");
            sQLiteDatabase.execSQL(sb.toString());
        }
    }

    public static void A06(String str, D6[] d6Arr, Map map) {
        for (D6 d6 : d6Arr) {
            String str2 = d6.A02;
            if (str2 != null) {
                Set set = (Set) map.get(str2);
                if (set == null) {
                    set = new HashSet();
                    map.put(str2, set);
                }
                set.add(str);
            }
        }
    }

    public static boolean A08() {
        if (TextUtils.isEmpty(null) || DefaultDataMigrator.class.getName().equals(null) || DropTableDataMigrator.class.getName().equals(null) || DropAllTablesDataMigrator.class.getName().equals(null)) {
            return false;
        }
        return true;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
        */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0027 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b  */
    public static void A00(android.database.sqlite.SQLiteDatabase r10, X.C0840jk r11, X.D6[] r12, X.D7[] r13) {
        /*
        // Method dump skipped, instructions count: 173
        */
        throw new UnsupportedOperationException("Method not decompiled: X.DG.A00(android.database.sqlite.SQLiteDatabase, X.jk, X.D6[], X.D7[]):void");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
        */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0034 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025 A[Catch:{ all -> 0x0047 }] */
    public static void A02(android.database.sqlite.SQLiteDatabase r5, java.lang.String r6, X.D6[] r7, X.D7[] r8) {
        /*
            java.lang.String r0 = "createTableWithIndices"
            android.os.Trace.beginSection(r0)
            java.lang.String r0 = "CREATE TABLE "
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0047 }
            r4.<init>(r0)     // Catch:{ all -> 0x0047 }
            r4.append(r6)     // Catch:{ all -> 0x0047 }
            java.lang.String r0 = " ("
            r4.append(r0)     // Catch:{ all -> 0x0047 }
            int r3 = r7.length     // Catch:{ all -> 0x0047 }
            r2 = 0
        L_0x0016:
            if (r2 >= r3) goto L_0x0021
            r1 = r7[r2]     // Catch:{ all -> 0x0047 }
            boolean r0 = r1.A09     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x0030
            int r2 = r2 + 1
            goto L_0x0016
        L_0x0021:
            int r2 = r2 + 1
            if (r2 >= r3) goto L_0x0034
            r1 = r7[r2]     // Catch:{ all -> 0x0047 }
            boolean r0 = r1.A09     // Catch:{ all -> 0x0047 }
            if (r0 != 0) goto L_0x0021
            java.lang.String r0 = ", "
            r4.append(r0)     // Catch:{ all -> 0x0047 }
        L_0x0030:
            A07(r4, r1)     // Catch:{ all -> 0x0047 }
            goto L_0x0021
        L_0x0034:
            r0 = 41
            r4.append(r0)     // Catch:{ all -> 0x0047 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0047 }
            r5.execSQL(r0)     // Catch:{ all -> 0x0047 }
            A03(r5, r6, r8)     // Catch:{ all -> 0x0047 }
            android.os.Trace.endSection()
            return
        L_0x0047:
            r0 = move-exception
            android.os.Trace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.DG.A02(android.database.sqlite.SQLiteDatabase, java.lang.String, X.D6[], X.D7[]):void");
    }

    public static void A05(String str, String str2) {
        C0139Dd.A0A("SchemaMigrator", AnonymousClass08.A06("[", str, "]: ", str2));
    }

    public static void A07(StringBuilder sb, D6 d6) {
        sb.append(d6.A03);
        sb.append(" ");
        sb.append(d6.A06);
        sb.append(" ");
        String str = d6.A00;
        if (str != null) {
            sb.append("DEFAULT ");
            sb.append(str);
            sb.append(" ");
        }
        if (!d6.A0A) {
            sb.append("NOT NULL ");
        }
        if (d6.A0B) {
            sb.append("PRIMARY KEY ");
        }
        if (d6.A08) {
            sb.append("AUTOINCREMENT ");
        }
        String str2 = d6.A02;
        if (str2 != null || d6.A01 != null) {
            sb.append("REFERENCES ");
            sb.append(str2);
            sb.append("(");
            sb.append(d6.A01);
            sb.append(")");
            sb.append(" ON UPDATE ");
            sb.append(d6.A05);
            sb.append(" ON DELETE ");
            sb.append(d6.A04);
        }
    }

    public DG(D1 d1, C0793hh hhVar, C0839jj jjVar) {
        this.A01 = d1;
        this.A00 = hhVar;
        this.A02 = new D2(d1);
        this.A03 = jjVar;
    }

    public static void A04(D1 d1, String str, Set set, Map map) {
        if (!set.contains(str)) {
            if (map.containsKey(str)) {
                for (String str2 : (Set) map.get(str)) {
                    A04(d1, str2, set, map);
                }
            }
            d1.A2D().execSQL(AnonymousClass08.A04("DROP TABLE IF EXISTS ", str));
            SQLiteDatabase A2D = d1.A2D();
            A2D.delete("sqliteproc_schema", "table_name = ?", new String[]{str});
            A2D.delete("sqliteproc_metadata", "table_name = ?", new String[]{str});
            set.add(str);
        }
    }
}
