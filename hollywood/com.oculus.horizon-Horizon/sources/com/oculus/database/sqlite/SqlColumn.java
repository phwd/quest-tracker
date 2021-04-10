package com.oculus.database.sqlite;

import X.AnonymousClass006;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.oculus.downloader.extras.contract.ExtrasDatabase;
import java.util.Arrays;

public class SqlColumn {
    public static final Function<SqlColumn, String> COLUMN_TO_DEFINITION = new Function<SqlColumn, String>() {
        /* class com.oculus.database.sqlite.SqlColumn.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
        @Override // com.google.common.base.Function
        public final String apply(SqlColumn sqlColumn) {
            SqlColumn sqlColumn2 = sqlColumn;
            return AnonymousClass006.A07(sqlColumn2.mName, " ", sqlColumn2.mType);
        }
    };
    public static final Function<SqlColumn, String> COLUMN_TO_NAME = new Function<SqlColumn, String>() {
        /* class com.oculus.database.sqlite.SqlColumn.AnonymousClass2 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
        @Override // com.google.common.base.Function
        public final String apply(SqlColumn sqlColumn) {
            return sqlColumn.mName;
        }
    };
    public static final SqlColumn ROW_ID = new SqlColumn("rowid", ExtrasDatabase.ColumnsTypes.INTEGER);
    public final String mName;
    public final String mType;

    /* renamed from: com.oculus.database.sqlite.SqlColumn$3  reason: invalid class name */
    public class AnonymousClass3 implements Function<SqlColumn, String> {
        public final /* synthetic */ String val$trimString;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SqlColumn sqlColumn = (SqlColumn) obj;
            if (!Objects.equal(this.mName, sqlColumn.mName) || !Objects.equal(this.mType, sqlColumn.mType)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.mName, this.mType});
    }

    public SqlColumn(String str, String str2) {
        this.mName = str;
        this.mType = str2;
    }

    public final String toString() {
        return this.mName;
    }
}
