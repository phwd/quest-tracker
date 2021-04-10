package com.oculus.database.supplier;

import X.AbstractC07380s1;
import X.AnonymousClass006;
import android.database.sqlite.SQLiteDatabase;
import com.google.common.collect.ImmutableList;
import com.oculus.database.sqlite.SqlTable;

public class TablesDbSchemaPart extends SharedSQLiteSchemaPart {
    public final ImmutableList<SqlTable> mTables;

    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public final void A01(SQLiteDatabase sQLiteDatabase) {
        AbstractC07380s1<SqlTable> A0K = this.mTables.iterator();
        while (A0K.hasNext()) {
            A0K.next();
        }
    }

    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public final void A02(SQLiteDatabase sQLiteDatabase) {
        AbstractC07380s1<SqlTable> A0K = this.mTables.iterator();
        while (A0K.hasNext()) {
            A0K.next();
        }
    }

    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public final void A03(SQLiteDatabase sQLiteDatabase) {
        AbstractC07380s1<SqlTable> A0K = this.mTables.iterator();
        while (A0K.hasNext()) {
            A0K.next().A01(sQLiteDatabase);
        }
    }

    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public void A04(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        AbstractC07380s1<SqlTable> A0K = this.mTables.iterator();
        while (A0K.hasNext()) {
            SqlTable next = A0K.next();
            sQLiteDatabase.execSQL(AnonymousClass006.A05("DROP TABLE IF EXISTS ", next.mName));
            next.A01(sQLiteDatabase);
        }
    }

    public TablesDbSchemaPart(String str, int i, ImmutableList<SqlTable> immutableList) {
        super(str, i);
        this.mTables = immutableList;
    }
}
