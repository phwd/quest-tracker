package com.oculus.database.sqlite;

import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oculus.database.sqlite.SqlKeys;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

public abstract class SqlTable {
    private final ImmutableList<SqlColumn> mColumns;
    @Nullable
    private final ImmutableList<SqlKeys.SqlKey> mKeys;
    private final String mName;

    public void appUpgrade(SQLiteDatabase sQLiteDatabase) {
    }

    public void open(SQLiteDatabase sQLiteDatabase) {
    }

    public SqlTable(String str, ImmutableList<SqlColumn> immutableList, SqlKeys.SqlKey sqlKey) {
        this(str, immutableList, ImmutableList.of(sqlKey));
    }

    public SqlTable(String str, ImmutableList<SqlColumn> immutableList, ImmutableList<SqlKeys.SqlKey> immutableList2) {
        this.mName = str;
        this.mColumns = immutableList;
        this.mKeys = immutableList2;
    }

    public SqlTable(String str, ImmutableList<SqlColumn> immutableList) {
        this.mName = str;
        this.mColumns = immutableList;
        this.mKeys = null;
    }

    public String getName() {
        return this.mName;
    }

    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(createTable(this.mName, this.mColumns, this.mKeys));
    }

    public void createIfNotExists(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(createTableIfNotExists(this.mName, this.mColumns, this.mKeys));
    }

    public void upgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(dropTableSQL(this.mName));
        create(sQLiteDatabase);
    }

    public void clear(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(this.mName, null, null);
    }

    public static String createTable(String str, ImmutableList<SqlColumn> immutableList, ImmutableList<SqlKeys.SqlKey> immutableList2) {
        return createTableInner(str, immutableList, immutableList2, "CREATE TABLE");
    }

    public static String createTemporaryTable(String str, ImmutableList<SqlColumn> immutableList, ImmutableList<SqlKeys.SqlKey> immutableList2) {
        return createTableInner(str, immutableList, immutableList2, "CREATE TEMPORARY TABLE");
    }

    public static String createTableIfNotExists(String str, ImmutableList<SqlColumn> immutableList, ImmutableList<SqlKeys.SqlKey> immutableList2) {
        return createTableInner(str, immutableList, immutableList2, "CREATE TABLE IF NOT EXISTS");
    }

    public static String createTableInner(String str, ImmutableList<SqlColumn> immutableList, ImmutableList<SqlKeys.SqlKey> immutableList2, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(" ");
        sb.append(str);
        sb.append(" (");
        sb.append(Joiner.on(", ").join(Collections2.transform(immutableList, SqlColumn.COLUMN_TO_DEFINITION)));
        if (immutableList2 != null && !immutableList2.isEmpty()) {
            sb.append(", ");
            sb.append(Joiner.on(", ").join(Collections2.transform(immutableList2, SqlKeys.KEY_TO_SQL)));
        }
        sb.append(")");
        return sb.toString();
    }

    public static String createTableSQL(String str, ImmutableList<SqlColumn> immutableList) {
        return createTable(str, immutableList, null);
    }

    public static String dropTableSQL(String str) {
        return "DROP TABLE IF EXISTS " + str;
    }

    public static String createIndexSQL(String str, String str2, ImmutableList<SqlColumn> immutableList) {
        return createIndexSQLInternal(str, str2, makeColumnsList(immutableList));
    }

    public static String createIndexSQLFromIndexedColumns(String str, String str2, ImmutableList<String> immutableList) {
        return createIndexSQLInternal(str, str2, Joiner.on(", ").join(immutableList));
    }

    private static String createIndexSQLInternal(String str, String str2, String str3) {
        return "CREATE INDEX IF NOT EXISTS " + str2 + " ON " + str + " (" + str3 + ")";
    }

    @VisibleForTesting
    static String makeColumnsList(List<SqlColumn> list) {
        return Joiner.on(", ").join(Collections2.transform(list, SqlColumn.COLUMN_TO_NAME));
    }

    @VisibleForTesting
    static String makeColumnsList(List<SqlColumn> list, final ImmutableMap<SqlColumn, Function<SqlColumn, String>> immutableMap) {
        return Joiner.on(", ").join(Collections2.transform(list, new Function<SqlColumn, String>() {
            /* class com.oculus.database.sqlite.SqlTable.AnonymousClass1 */

            @Nullable
            public String apply(SqlColumn sqlColumn) {
                Function<SqlColumn, String> function = (Function) ImmutableMap.this.get(sqlColumn);
                if (function == null) {
                    function = SqlColumn.COLUMN_TO_NAME;
                }
                return function.apply(sqlColumn);
            }
        }));
    }

    public static String dropIndexSQL(String str) {
        return "DROP INDEX IF EXISTS " + str;
    }

    public static String addColumnSQL(String str, SqlColumn sqlColumn) {
        return "ALTER TABLE " + str + " ADD COLUMN " + sqlColumn.getName() + " " + sqlColumn.getType();
    }

    public static void mapColumnsValues(SQLiteDatabase sQLiteDatabase, String str, ImmutableList<SqlColumn> immutableList, ImmutableMap<SqlColumn, Function<SqlColumn, String>> immutableMap, ImmutableList<SqlKeys.SqlKey> immutableList2) {
        String str2 = str + "_temp";
        String makeColumnsList = makeColumnsList(immutableList);
        String makeColumnsList2 = makeColumnsList(immutableList, immutableMap);
        Locale locale = null;
        String format = String.format(locale, "INSERT INTO %s SELECT %s FROM %s", str2, makeColumnsList, str);
        String format2 = String.format(locale, "INSERT INTO %s SELECT %s FROM %s", str, makeColumnsList2, str2);
        sQLiteDatabase.execSQL(createTemporaryTable(str2, immutableList, immutableList2));
        sQLiteDatabase.execSQL(format);
        sQLiteDatabase.execSQL(dropTableSQL(str));
        sQLiteDatabase.execSQL(createTable(str, immutableList, immutableList2));
        sQLiteDatabase.execSQL(format2);
        sQLiteDatabase.execSQL(dropTableSQL(str2));
    }

    public static void mapColumnsValues(SQLiteDatabase sQLiteDatabase, String str, ImmutableList<SqlColumn> immutableList, ImmutableMap<SqlColumn, Function<SqlColumn, String>> immutableMap, SqlKeys.SqlKey sqlKey) {
        mapColumnsValues(sQLiteDatabase, str, immutableList, immutableMap, ImmutableList.of(sqlKey));
    }

    public static void removeUnusedColumns(SQLiteDatabase sQLiteDatabase, String str, ImmutableList<SqlColumn> immutableList, ImmutableList<SqlKeys.SqlKey> immutableList2) {
        String str2 = str + "_temp";
        String makeColumnsList = makeColumnsList(immutableList);
        Locale locale = null;
        String format = String.format(locale, "INSERT INTO %s SELECT %s FROM %s", str2, makeColumnsList, str);
        String format2 = String.format(locale, "INSERT INTO %s SELECT %s FROM %s", str, makeColumnsList, str2);
        sQLiteDatabase.execSQL(createTemporaryTable(str2, immutableList, immutableList2));
        sQLiteDatabase.execSQL(format);
        sQLiteDatabase.execSQL(dropTableSQL(str));
        sQLiteDatabase.execSQL(createTable(str, immutableList, immutableList2));
        sQLiteDatabase.execSQL(format2);
        sQLiteDatabase.execSQL(dropTableSQL(str2));
    }

    public static void removeUnusedColumns(SQLiteDatabase sQLiteDatabase, String str, ImmutableList<SqlColumn> immutableList, SqlKeys.SqlKey sqlKey) {
        removeUnusedColumns(sQLiteDatabase, str, immutableList, ImmutableList.of(sqlKey));
    }

    /* access modifiers changed from: protected */
    public void mapColumnsValues(SQLiteDatabase sQLiteDatabase, ImmutableMap<SqlColumn, Function<SqlColumn, String>> immutableMap) {
        mapColumnsValues(sQLiteDatabase, this.mName, this.mColumns, immutableMap, this.mKeys);
    }

    /* access modifiers changed from: protected */
    public void removeUnusedColumns(SQLiteDatabase sQLiteDatabase) {
        removeUnusedColumns(sQLiteDatabase, this.mName, this.mColumns, this.mKeys);
    }

    public String addColumn(String str, String str2) {
        return "ALTER TABLE " + this.mName + " ADD COLUMN " + str + " " + str2;
    }

    public String addColumnWithDefault(String str, String str2, String str3) {
        return "ALTER TABLE " + this.mName + " ADD COLUMN " + str + " " + str2 + " DEFAULT " + str3;
    }
}
