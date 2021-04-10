package com.oculus.extras.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.oculus.database.supplier.AbstractDatabaseSupplier;
import com.oculus.extras.Extras;
import com.oculus.extras.ExtrasBuilder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AbstractExtrasManager {
    private final AbstractDatabaseSupplier mDatabaseSupplier;
    private final String mIdColumnName;
    private final String mKeyColumnName;
    private final String mTableName;
    private final String mValueColumnName;

    public AbstractExtrasManager(AbstractDatabaseSupplier abstractDatabaseSupplier, String str, String str2, String str3, String str4) {
        this.mDatabaseSupplier = abstractDatabaseSupplier;
        this.mTableName = str;
        this.mIdColumnName = str2;
        this.mKeyColumnName = str3;
        this.mValueColumnName = str4;
    }

    public Extras getExtras(long j) {
        SQLiteDatabase sQLiteDatabase = this.mDatabaseSupplier.get();
        String[] strArr = {this.mKeyColumnName, this.mValueColumnName};
        Cursor query = sQLiteDatabase.query(this.mTableName, strArr, this.mIdColumnName + " = ?", new String[]{Long.toString(j)}, null, null, null);
        if (query == null) {
            return Extras.builder().build();
        }
        if (!query.moveToFirst()) {
            query.close();
            return Extras.builder().build();
        }
        HashMap hashMap = new HashMap();
        int columnIndex = query.getColumnIndex(this.mKeyColumnName);
        int columnIndex2 = query.getColumnIndex(this.mValueColumnName);
        while (!query.isAfterLast()) {
            String string = query.getString(columnIndex);
            String string2 = query.getString(columnIndex2);
            if (!(string == null || string2 == null)) {
                hashMap.put(string, string2);
            }
            query.moveToNext();
        }
        query.close();
        return new Extras(hashMap);
    }

    public void setExtras(long j, Extras extras) {
        setExtras(j, extras.getData());
    }

    public void setExtras(long j, ExtrasBuilder extrasBuilder) {
        setExtras(j, extrasBuilder.getData());
    }

    public void setExtras(long j, Map<String, String> map) {
        SQLiteDatabase sQLiteDatabase = this.mDatabaseSupplier.get();
        sQLiteDatabase.beginTransaction();
        try {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                sQLiteDatabase.delete(this.mTableName, this.mIdColumnName + " = ? AND " + this.mKeyColumnName + " = ?", new String[]{Long.toString(j), it.next()});
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value != null) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(this.mIdColumnName, Long.valueOf(j));
                    contentValues.put(this.mKeyColumnName, key);
                    contentValues.put(this.mValueColumnName, value);
                    sQLiteDatabase.insert(this.mTableName, null, contentValues);
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void removeExtras(long j, Set<String> set) {
        HashMap hashMap = new HashMap();
        for (String str : set) {
            hashMap.put(str, null);
        }
        setExtras(j, hashMap);
    }

    public void removeAllExtras(long j) {
        SQLiteDatabase sQLiteDatabase = this.mDatabaseSupplier.get();
        sQLiteDatabase.delete(this.mTableName, this.mIdColumnName + " = ?", new String[]{Long.toString(j)});
    }
}
