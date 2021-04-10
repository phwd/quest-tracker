package oculus.internal.license.store;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbWrapper {
    final SQLiteDatabase db;

    public DbWrapper(SQLiteDatabase db2) {
        this.db = db2;
    }

    public void beginTransaction() {
        this.db.beginTransaction();
    }

    public void setTransactionSuccessful() {
        this.db.setTransactionSuccessful();
    }

    public void endTransaction() {
        this.db.endTransaction();
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return this.db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return this.db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    public long insert(String table, String nullColumnHack, ContentValues values) {
        return this.db.insert(table, nullColumnHack, values);
    }

    public long insertOrThrow(String table, String nullColumnHack, ContentValues values) {
        return this.db.insertOrThrow(table, nullColumnHack, values);
    }

    public void execSQL(String sql) {
        this.db.execSQL(sql);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        return this.db.rawQuery(sql, selectionArgs);
    }

    public int delete(String table, String whereClause, String[] whereArgs) {
        return this.db.delete(table, whereClause, whereArgs);
    }

    public void close() {
        this.db.close();
    }
}
