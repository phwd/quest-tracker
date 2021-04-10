package X;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Trace;
import android.util.Pair;
import android.util.SparseArray;
import com.facebook.acra.AppComponentStats;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.crudolib.sqliteproc.annotations.DropAllTablesDataMigrator;
import com.facebook.crudolib.sqliteproc.annotations.DropTableDataMigrator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class DB extends SQLiteOpenHelper {
    public SQLiteDatabase A00;
    public final C0793hh A01;
    public final D1 A02 = new C0841jl(this);
    public final C0838ji A03;
    public final C0839jj A04;
    public final D9 A05;

    public static void A04(SQLiteDatabase sQLiteDatabase, String str, D6[] d6Arr) {
        sQLiteDatabase.delete("sqliteproc_schema", "table_name = ?", new String[]{str});
        ContentValues contentValues = new ContentValues();
        for (D6 d6 : d6Arr) {
            contentValues.put("table_name", str);
            contentValues.put(AppComponentStats.ATTRIBUTE_NAME, d6.A03);
            contentValues.put("type_name", d6.A06);
            contentValues.put("default_value", d6.A00);
            contentValues.put("is_nullable", Boolean.valueOf(d6.A0A));
            contentValues.put("is_primary", Boolean.valueOf(d6.A0B));
            contentValues.put("is_autoincrement", Boolean.valueOf(d6.A08));
            contentValues.put("is_deleted", Boolean.valueOf(d6.A09));
            contentValues.put("is_added", Boolean.valueOf(d6.A07));
            contentValues.put("foreign_table", d6.A02);
            contentValues.put("foreign_column", d6.A01);
            contentValues.put("on_foreign_key_update", d6.A05);
            contentValues.put("on_foreign_key_delete", d6.A04);
            sQLiteDatabase.insert("sqliteproc_schema", null, contentValues);
        }
    }

    public DB(Context context, C0793hh hhVar, D9 d9) {
        super(context, "AssistantTtsConfig.db", (SQLiteDatabase.CursorFactory) null, 4);
        this.A05 = d9;
        this.A01 = hhVar;
        this.A03 = new C0838ji();
        this.A04 = new C0839jj();
    }

    public static String A00(D2 d2, String str) {
        String str2;
        C00152e r2 = new C00152e(d2.A00(new C0843jn(str)));
        try {
            AbstractC0834je.A00(r2);
            Cursor cursor = r2.A01;
            if (cursor.moveToFirst()) {
                str2 = cursor.getString(1);
            } else {
                str2 = null;
            }
            return str2;
        } finally {
            r2.close();
        }
    }

    public static Set A01(D2 d2) {
        HashSet hashSet = new HashSet();
        Cursor A002 = d2.A00(new C0842jm());
        try {
            if (A002.moveToFirst()) {
                do {
                    hashSet.add(new AnonymousClass2f(A002).A01.getString(1));
                } while (A002.moveToNext());
            }
            A002.close();
            hashSet.remove("__database__");
            return hashSet;
        } catch (Throwable unused) {
        }
        throw th;
    }

    private void A02(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("PRAGMA defer_foreign_keys = ON");
        for (String str : A01(new D2(this.A02))) {
            sQLiteDatabase.execSQL(AnonymousClass08.A04("DROP TABLE IF EXISTS ", str));
        }
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sqliteproc_metadata");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sqliteproc_schema");
    }

    public static void A03(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("table_name", str);
        contentValues.put("hash", str2);
        contentValues.put("index_hash", str3);
        sQLiteDatabase.insertWithOnConflict("sqliteproc_metadata", null, contentValues, 5);
    }

    public static D6[] A05(D2 d2, String str) {
        AnonymousClass2c r1 = new AnonymousClass2c(d2.A00(new C0845jp(str)));
        try {
            AbstractC0834je.A00(r1);
            Cursor cursor = r1.A01;
            D6[] d6Arr = new D6[cursor.getCount()];
            int i = 0;
            while (true) {
                AbstractC0834je.A00(r1);
                if (!cursor.moveToNext()) {
                    return d6Arr;
                }
                if (!(cursor.getString(1) == null || cursor.getString(2) == null)) {
                    String string = cursor.getString(1);
                    String string2 = cursor.getString(2);
                    String string3 = cursor.getString(3);
                    boolean z = false;
                    if (cursor.getInt(4) != 0) {
                        z = true;
                    }
                    boolean z2 = false;
                    if (cursor.getInt(5) != 0) {
                        z2 = true;
                    }
                    boolean z3 = false;
                    if (cursor.getInt(6) != 0) {
                        z3 = true;
                    }
                    boolean z4 = false;
                    if (cursor.getInt(7) != 0) {
                        z4 = true;
                    }
                    boolean z5 = false;
                    if (cursor.getInt(8) != 0) {
                        z5 = true;
                    }
                    d6Arr[i] = new D6(string, string2, string3, z, z2, z3, z4, z5, cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12));
                }
                i++;
            }
        } finally {
            r1.close();
        }
    }

    public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
        this.A00 = sQLiteDatabase;
        sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (this.A00 == null) {
            onConfigure(sQLiteDatabase);
        }
        Trace.beginSection("onCreate");
        try {
            DG.A02(sQLiteDatabase, "sqliteproc_metadata", DI.A00, DI.A01);
            DG.A02(sQLiteDatabase, "sqliteproc_schema", DK.A00, DK.A01);
        } finally {
            Trace.endSection();
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (this.A00 == null) {
            onConfigure(sQLiteDatabase);
        }
        A02(sQLiteDatabase);
        onCreate(sQLiteDatabase);
        new HashSet().add("metadata_version_downgrade");
    }

    /* JADX INFO: finally extract failed */
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        SQLiteDatabase A2D;
        DF df;
        String str;
        if (this.A00 == null) {
            onConfigure(sQLiteDatabase);
        }
        D1 d1 = this.A02;
        A00(new D2(d1), "__database__");
        Trace.beginSection("migrate");
        try {
            DG dg = new DG(d1, this.A01, this.A04);
            D2 d2 = dg.A02;
            String A002 = A00(d2, "__database__");
            C0793hh hhVar = dg.A00;
            if (!"85e012d266b33234627b629590b4f3269f63b34c-fbb9ad2c879d477143c1697b72869dc43873d76e-".equals(A002)) {
                ArrayList arrayList = new ArrayList();
                SparseArray sparseArray = new SparseArray();
                Set<String> A012 = A01(d2);
                HashSet hashSet = new HashSet(4);
                D1 d12 = dg.A01;
                SQLiteDatabase A2D2 = d12.A2D();
                A2D2.beginTransaction();
                try {
                    A2D2.execSQL("PRAGMA defer_foreign_keys = ON");
                    C0840jk[] A022 = hhVar.A02();
                    int length = A022.length;
                    boolean z = false;
                    for (int i = 0; i < length; i++) {
                        C0840jk jkVar = A022[i];
                        D6[] A003 = hhVar.A00(i);
                        String str2 = jkVar.A01;
                        A012.remove(str2);
                        String A004 = A00(d2, str2);
                        if (A004 == null) {
                            C0139Dd.A0G("SchemaMigrator", "[%s] Creating new table", str2);
                            DG.A02(A2D2, str2, A003, hhVar.A01(i));
                            df = new DF(4, null);
                        } else {
                            String str3 = ((D9) jkVar).A00;
                            if (!str3.equals(A004)) {
                                if (C0139Dd.A01.A3Y(4)) {
                                    C0139Dd.A0B("SchemaMigrator", StringFormatUtil.formatStrLocaleSafe("[%s] Migrating to %s (from %s)", str2, str3, A004));
                                }
                                Trace.beginSection("migrateTable");
                                try {
                                    DE de = new DE(A05(d2, str2), A003);
                                    de.A02 = new ArrayList();
                                    de.A00 = new ArrayList();
                                    de.A01 = new ArrayList();
                                    de.A04 = new ArrayList();
                                    de.A05 = new ArrayList();
                                    de.A03 = new ArrayList();
                                    D6[] d6Arr = de.A08;
                                    int length2 = d6Arr.length;
                                    HashMap hashMap = new HashMap(length2);
                                    for (D6 d6 : d6Arr) {
                                        hashMap.put(d6.A03, d6);
                                    }
                                    D6[] d6Arr2 = de.A09;
                                    for (D6 d62 : d6Arr2) {
                                        D6 d63 = (D6) hashMap.remove(d62.A03);
                                        if (d63 == null) {
                                            de.A05.add(d62.A03);
                                        } else if (!d63.equals(d62)) {
                                            if (d62.A09 || !d63.A09) {
                                                de.A02.add(new D8(d62, d63));
                                            } else {
                                                de.A04.add(d63);
                                                if (DropAllTablesDataMigrator.class.getName().equals(null)) {
                                                    de.A06 = true;
                                                } else if (DropTableDataMigrator.class.getName().equals(null)) {
                                                    de.A07 = true;
                                                }
                                            }
                                        }
                                    }
                                    for (D6 d64 : hashMap.values()) {
                                        if (!d64.A09) {
                                            if (d64.A07) {
                                                de.A00.add(d64);
                                                if (!(d64.A02 == null || d64.A01 == null || (d64.A0A && d64.A00 == null))) {
                                                    de.A03.add(new Pair(d64.A03, "foreign_key_violation_added_column"));
                                                }
                                                if (DropAllTablesDataMigrator.class.getName().equals(null)) {
                                                    de.A06 = true;
                                                } else if (DropTableDataMigrator.class.getName().equals(null)) {
                                                    de.A07 = true;
                                                }
                                            } else {
                                                de.A01.add(d64.A03);
                                            }
                                        }
                                    }
                                    DA da = new DA(d6Arr2, d6Arr, de.A02, de.A00, de.A01, de.A04, de.A05, de.A03, de.A06, de.A07);
                                    List list = da.A02;
                                    List list2 = da.A00;
                                    List list3 = da.A06;
                                    List list4 = da.A07;
                                    List list5 = da.A01;
                                    List<Pair> list6 = da.A05;
                                    boolean z2 = da.A09;
                                    boolean z3 = da.A08;
                                    if (z3) {
                                        hashSet.add("data_migration");
                                    }
                                    if (!list4.isEmpty()) {
                                        StringBuilder sb = new StringBuilder("You must use @Deleted to remove columns: ");
                                        sb.append(list4);
                                        DG.A05(str2, sb.toString());
                                        z3 |= true;
                                        hashSet.add("removed_column_illegally");
                                    }
                                    if (!list5.isEmpty()) {
                                        StringBuilder sb2 = new StringBuilder("You must use @Added to add columns: ");
                                        sb2.append(list5);
                                        DG.A05(str2, sb2.toString());
                                        z3 |= true;
                                        hashSet.add("added_column_illegally");
                                    }
                                    if (!list.isEmpty()) {
                                        StringBuilder sb3 = new StringBuilder("Modification of columns is not permitted, use @Deleted and a new column instead: ");
                                        sb3.append(list);
                                        DG.A05(str2, sb3.toString());
                                        z3 |= true;
                                        hashSet.add("modified_column");
                                    }
                                    if (!list6.isEmpty()) {
                                        StringBuilder sb4 = new StringBuilder("Detected other columns introducing illegal changes: ");
                                        sb4.append(list6);
                                        DG.A05(str2, sb4.toString());
                                        z3 |= true;
                                        for (Pair pair : list6) {
                                            hashSet.add(pair.second);
                                        }
                                    }
                                    if (!z2 && !z3) {
                                        if (!list2.isEmpty()) {
                                            int size = list2.size();
                                            for (int i2 = 0; i2 < size; i2++) {
                                                D6 d65 = (D6) list2.get(i2);
                                                C0139Dd.A0I("SchemaMigrator", "[%s] Adding column %s", str2, d65.A03);
                                                StringBuilder sb5 = new StringBuilder("ALTER TABLE ");
                                                sb5.append(str2);
                                                sb5.append(' ');
                                                sb5.append("ADD COLUMN ");
                                                DG.A07(sb5, d65);
                                                A2D2.execSQL(sb5.toString());
                                            }
                                        }
                                        if (list3.isEmpty()) {
                                            String str4 = jkVar.A00;
                                            AnonymousClass2d r11 = new AnonymousClass2d(d2.A00(new C0844jo(str2)));
                                            try {
                                                AbstractC0834je.A00(r11);
                                                Cursor cursor = r11.A01;
                                                if (cursor.moveToFirst()) {
                                                    str = cursor.getString(1);
                                                } else {
                                                    str = null;
                                                }
                                                r11.close();
                                                if (!str4.equals(str)) {
                                                    A2D2.execSQL(AnonymousClass08.A04("SAVEPOINT ", "recreate_indices_savepoint"));
                                                    SQLException e = null;
                                                    try {
                                                        D7[] A013 = hhVar.A01(i);
                                                        Trace.beginSection("recreateIndices");
                                                        try {
                                                            Cursor rawQuery = A2D2.rawQuery("SELECT name FROM sqlite_master WHERE type == 'index' AND tbl_name == ?", new String[]{str2});
                                                            while (rawQuery.moveToNext()) {
                                                                try {
                                                                    A2D2.execSQL(AnonymousClass08.A04("DROP INDEX ", rawQuery.getString(0)));
                                                                } catch (Throwable th) {
                                                                    rawQuery.close();
                                                                    throw th;
                                                                }
                                                            }
                                                            rawQuery.close();
                                                            DG.A03(A2D2, str2, A013);
                                                            DD.A00(A2D2, "recreate_indices_savepoint");
                                                            if (e != null) {
                                                                hashSet.add("unique_constraint_failed_recreate_indices");
                                                                z3 = true;
                                                            }
                                                        } finally {
                                                            Trace.endSection();
                                                        }
                                                    } catch (SQLException e2) {
                                                        e = e2;
                                                        A2D2.execSQL(AnonymousClass08.A04(";ROLLBACK TRANSACTION TO SAVEPOINT ", "recreate_indices_savepoint"));
                                                    } catch (Throwable th2) {
                                                        DD.A00(A2D2, "recreate_indices_savepoint");
                                                        throw th2;
                                                    }
                                                }
                                            } catch (Throwable th3) {
                                                r11.close();
                                                throw th3;
                                            }
                                        }
                                        if (!list3.isEmpty() || !list2.isEmpty()) {
                                            df = new DF(6, da);
                                        } else {
                                            df = new DF(2, null);
                                        }
                                        Trace.endSection();
                                    }
                                    C0139Dd.A0G("SchemaMigrator", "[%s] Drop and recreate due to illegal operation or data migration policy.", str2);
                                    D7[] A014 = hhVar.A01(i);
                                    A2D2.execSQL(AnonymousClass08.A04("DROP TABLE IF EXISTS ", str2));
                                    DG.A02(A2D2, str2, A003, A014);
                                    if (z3) {
                                        df = new DF(5, null);
                                    } else {
                                        df = new DF(3, null);
                                    }
                                    Trace.endSection();
                                } finally {
                                    Trace.endSection();
                                }
                            } else {
                                df = new DF(1, null);
                            }
                        }
                        int i3 = df.A00;
                        if (i3 != 1) {
                            A04(A2D2, str2, A003);
                            A03(A2D2, str2, ((D9) jkVar).A00, jkVar.A00);
                        }
                        C0139Dd.A0H("SchemaMigrator", "Migrate table = %s; migrateResult = %s", str2, df);
                        if (i3 == 5) {
                            z = true;
                        } else if (i3 == 3) {
                            arrayList.add(str2);
                        } else if (i3 == 6) {
                            sparseArray.put(i, df.A01);
                        }
                    }
                    if (!z) {
                        if (!arrayList.isEmpty()) {
                            HashMap hashMap2 = new HashMap();
                            C0840jk[] A023 = hhVar.A02();
                            int length3 = A023.length;
                            for (int i4 = 0; i4 < length3; i4++) {
                                DG.A06(A023[i4].A01, hhVar.A00(i4), hashMap2);
                            }
                            HashSet hashSet2 = new HashSet(arrayList);
                            ArrayList arrayList2 = new ArrayList(arrayList);
                            while (!arrayList2.isEmpty()) {
                                Object remove = arrayList2.remove(0);
                                if (hashMap2.containsKey(remove)) {
                                    for (String str5 : (Set) hashMap2.get(remove)) {
                                        A2D2.execSQL(AnonymousClass08.A04("DELETE FROM ", str5));
                                        if (hashSet2.add(str5)) {
                                            arrayList2.add(str5);
                                        }
                                    }
                                }
                            }
                        }
                        int size2 = sparseArray.size();
                        for (int i5 = 0; i5 < size2; i5++) {
                            DA da2 = (DA) sparseArray.get(sparseArray.keyAt(i5));
                            Iterator it = da2.A06.iterator();
                            while (it.hasNext()) {
                                it.next();
                                if (DG.A08()) {
                                    DG.A01(A2D2, new DU(da2));
                                }
                            }
                            Iterator it2 = da2.A00.iterator();
                            while (it2.hasNext()) {
                                it2.next();
                                if (DG.A08()) {
                                    DG.A01(A2D2, new DU(da2));
                                }
                            }
                        }
                        int size3 = sparseArray.size();
                        for (int i6 = 0; i6 < size3; i6++) {
                            int keyAt = sparseArray.keyAt(i6);
                            if (!((DA) sparseArray.get(keyAt)).A06.isEmpty()) {
                                DG.A00(A2D2, A022[keyAt], hhVar.A00(keyAt), hhVar.A01(keyAt));
                            }
                        }
                    } else {
                        int size4 = sparseArray.size();
                        for (int i7 = 0; i7 < size4; i7++) {
                            int keyAt2 = sparseArray.keyAt(i7);
                            if (!((DA) sparseArray.get(keyAt2)).A06.isEmpty()) {
                                DG.A00(A2D2, A022[keyAt2], hhVar.A00(keyAt2), hhVar.A01(keyAt2));
                            }
                        }
                        for (C0840jk jkVar2 : A022) {
                            A2D2.execSQL(AnonymousClass08.A04("DELETE FROM ", jkVar2.A01));
                        }
                    }
                    A03(d12.A2D(), "__database__", "85e012d266b33234627b629590b4f3269f63b34c-fbb9ad2c879d477143c1697b72869dc43873d76e-", null);
                    for (String str6 : A012) {
                        A2D2.execSQL(AnonymousClass08.A04("DROP TABLE IF EXISTS ", str6));
                        A2D2.delete("sqliteproc_schema", "table_name = ?", new String[]{str6});
                        A2D2.delete("sqliteproc_metadata", "table_name = ?", new String[]{str6});
                    }
                    A2D2.setTransactionSuccessful();
                    A2D2.endTransaction();
                } catch (SQLiteException unused) {
                    A2D2.endTransaction();
                    C0139Dd.A0E("SchemaMigrator", "Failed to migrate database, so using fallback that safely drops/recreates all tables.");
                    A2D = d12.A2D();
                    A2D.beginTransaction();
                    HashSet hashSet3 = new HashSet();
                    HashMap hashMap3 = new HashMap();
                    for (String str7 : A01(d2)) {
                        DG.A06(str7, A05(d2, str7), hashMap3);
                    }
                    for (String str8 : A01(d2)) {
                        DG.A04(d12, str8, hashSet3, hashMap3);
                    }
                    C0840jk[] A024 = hhVar.A02();
                    for (int i8 = 0; i8 < A024.length; i8++) {
                        C0840jk jkVar3 = A024[i8];
                        String str9 = jkVar3.A01;
                        D7[] A015 = hhVar.A01(i8);
                        D6[] A005 = hhVar.A00(i8);
                        DG.A02(d12.A2D(), str9, A005, A015);
                        A04(d12.A2D(), str9, A005);
                        A03(d12.A2D(), str9, ((D9) jkVar3).A00, jkVar3.A00);
                    }
                    A03(d12.A2D(), "__database__", "85e012d266b33234627b629590b4f3269f63b34c-fbb9ad2c879d477143c1697b72869dc43873d76e-", null);
                    A2D.setTransactionSuccessful();
                    A2D.endTransaction();
                } catch (Throwable th4) {
                    A2D.endTransaction();
                    throw th4;
                }
            }
            Trace.endSection();
        } finally {
            Trace.endSection();
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (this.A00 == null) {
            onConfigure(sQLiteDatabase);
        }
        if (i < 3) {
            A02(sQLiteDatabase);
            onCreate(sQLiteDatabase);
            new HashSet().add("metadata_version_upgrade_old");
            return;
        }
        Trace.beginSection("upgrade_metadata");
        sQLiteDatabase.execSQL(AnonymousClass08.A04("SAVEPOINT ", "update_metadata_savepoint"));
        sQLiteDatabase.execSQL("PRAGMA defer_foreign_keys = ON");
        SQLException e = null;
        if (i == 3) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE sqliteproc_metadata ADD COLUMN index_hash TEXT;");
                sQLiteDatabase.execSQL(AnonymousClass08.A05("CREATE TABLE _temp_sqliteproc_schema (", "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, table_name TEXT NOT NULL, name TEXT NOT NULL, type_name TEXT NOT NULL, default_value TEXT, is_nullable INTEGER NOT NULL, is_primary INTEGER NOT NULL, is_autoincrement INTEGER NOT NULL, is_deleted INTEGER NOT NULL, is_added INTEGER NOT NULL, foreign_table TEXT, foreign_column TEXT, on_foreign_key_update TEXT, on_foreign_key_delete TEXT", ")"));
                sQLiteDatabase.execSQL(AnonymousClass08.A07("INSERT OR IGNORE INTO _temp_sqliteproc_schema (", "_id, table_name, name, type_name, default_value, is_nullable, is_primary, is_autoincrement, is_deleted, is_added, foreign_table, foreign_column, on_foreign_key_update, on_foreign_key_delete", ")SELECT ", "_id, table_name, name, type_name, default_value, is_nullable, is_primary, is_autoincrement, is_deleted, is_added, foreign_table, foreign_column, on_foreign_key_update, on_foreign_key_delete", " FROM sqliteproc_schema;"));
                sQLiteDatabase.execSQL("DROP TABLE sqliteproc_schema;");
                sQLiteDatabase.execSQL(AnonymousClass08.A05("CREATE TABLE sqliteproc_schema (", "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, table_name TEXT NOT NULL, name TEXT NOT NULL, type_name TEXT NOT NULL, default_value TEXT, is_nullable INTEGER NOT NULL, is_primary INTEGER NOT NULL, is_autoincrement INTEGER NOT NULL, is_deleted INTEGER NOT NULL, is_added INTEGER NOT NULL, foreign_table TEXT, foreign_column TEXT, on_foreign_key_update TEXT, on_foreign_key_delete TEXT", ")"));
                sQLiteDatabase.execSQL("CREATE UNIQUE INDEX sqliteproc_schema_name_table_name ON sqliteproc_schema(name, table_name)");
                sQLiteDatabase.execSQL(AnonymousClass08.A07("INSERT OR IGNORE INTO sqliteproc_schema (", "_id, table_name, name, type_name, default_value, is_nullable, is_primary, is_autoincrement, is_deleted, is_added, foreign_table, foreign_column, on_foreign_key_update, on_foreign_key_delete", ")SELECT ", "_id, table_name, name, type_name, default_value, is_nullable, is_primary, is_autoincrement, is_deleted, is_added, foreign_table, foreign_column, on_foreign_key_update, on_foreign_key_delete", " FROM _temp_sqliteproc_schema;"));
                sQLiteDatabase.execSQL("DROP TABLE _temp_sqliteproc_schema");
            } catch (SQLException e2) {
                e = e2;
                sQLiteDatabase.execSQL(AnonymousClass08.A04(";ROLLBACK TRANSACTION TO SAVEPOINT ", "update_metadata_savepoint"));
            } catch (Throwable th) {
                DD.A00(sQLiteDatabase, "update_metadata_savepoint");
                throw th;
            }
        }
        DD.A00(sQLiteDatabase, "update_metadata_savepoint");
        if (e != null) {
            A02(sQLiteDatabase);
            onCreate(sQLiteDatabase);
            new HashSet().add("metadata_version_upgrade_error");
        }
        Trace.endSection();
    }
}
