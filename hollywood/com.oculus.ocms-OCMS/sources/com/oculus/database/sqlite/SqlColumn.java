package com.oculus.database.sqlite;

import android.database.Cursor;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.oculus.database.sqlite.SqlExpression;

public class SqlColumn {
    public static final Function<SqlColumn, String> COLUMN_TO_DEFINITION = new Function<SqlColumn, String>() {
        /* class com.oculus.database.sqlite.SqlColumn.AnonymousClass1 */

        public String apply(SqlColumn sqlColumn) {
            return sqlColumn.getColumnDefinition();
        }
    };
    public static final Function<SqlColumn, String> COLUMN_TO_NAME = new Function<SqlColumn, String>() {
        /* class com.oculus.database.sqlite.SqlColumn.AnonymousClass2 */

        public String apply(SqlColumn sqlColumn) {
            return sqlColumn.getName();
        }
    };
    public static final SqlColumn ROW_ID = new SqlColumn("rowid", "INTEGER");
    private final String mName;
    private final String mType;

    public SqlColumn(String str, String str2) {
        this.mName = str;
        this.mType = str2;
    }

    public String toString() {
        return this.mName;
    }

    public String getName() {
        return this.mName;
    }

    public String getType() {
        return this.mType;
    }

    public String getColumnDefinition() {
        return this.mName + " " + this.mType;
    }

    public SqlExpression.Expression eq(String str) {
        return SqlExpression.eq(this.mName, str);
    }

    public SqlExpression.Expression lt(String str) {
        return SqlExpression.lt(this.mName, str);
    }

    public SqlExpression.Expression lte(String str) {
        return SqlExpression.lte(this.mName, str);
    }

    public SqlExpression.Expression gt(String str) {
        return SqlExpression.gt(this.mName, str);
    }

    public SqlExpression.Expression gte(String str) {
        return SqlExpression.gte(this.mName, str);
    }

    public String ascendingOrderBy() {
        return this.mName + " ASC";
    }

    public String descendingOrderBy() {
        return this.mName + " DESC";
    }

    public int indexIn(Cursor cursor) {
        return cursor.getColumnIndexOrThrow(this.mName);
    }

    public String getStringFromCursor(Cursor cursor) {
        return cursor.getString(indexIn(cursor));
    }

    public long getLongFromCursor(Cursor cursor) {
        return cursor.getLong(indexIn(cursor));
    }

    public short getShortFromCursor(Cursor cursor) {
        return cursor.getShort(indexIn(cursor));
    }

    public int getIntFromCursor(Cursor cursor) {
        return cursor.getInt(indexIn(cursor));
    }

    public float getFloatFromCursor(Cursor cursor) {
        return cursor.getFloat(indexIn(cursor));
    }

    public double getDoubleFromCursor(Cursor cursor) {
        return cursor.getDouble(indexIn(cursor));
    }

    public byte[] getBlobFromCursor(Cursor cursor) {
        return cursor.getBlob(indexIn(cursor));
    }

    public boolean isNullAtCursor(Cursor cursor) {
        return cursor.isNull(indexIn(cursor));
    }

    public static final Function<SqlColumn, String> columnToTrim(final String str) {
        return new Function<SqlColumn, String>() {
            /* class com.oculus.database.sqlite.SqlColumn.AnonymousClass3 */

            public String apply(SqlColumn sqlColumn) {
                return String.format(null, "trim(%s, '%s')", sqlColumn.getName(), str);
            }
        };
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SqlColumn sqlColumn = (SqlColumn) obj;
        if (!Objects.equal(this.mName, sqlColumn.mName) || !Objects.equal(this.mType, sqlColumn.mType)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.mName, this.mType);
    }
}
