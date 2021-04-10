package com.oculus.database.sqlite;

import X.AnonymousClass006;
import X.AnonymousClass0pA;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public final class SqlKeys {
    public static final Function<SqlKey, String> KEY_TO_SQL = new Function<SqlKey, String>() {
        /* class com.oculus.database.sqlite.SqlKeys.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
        @Override // com.google.common.base.Function
        public final String apply(SqlKey sqlKey) {
            return sqlKey.A4P();
        }
    };

    public static class ForeignKey implements SqlKey {
        public final ImmutableList<SqlColumn> foreignKeyColumns;
        public final String foreignTableName = "apk_updates";
        public final ImmutableList<SqlColumn> keyColumns;

        /* JADX WARN: Incorrect args count in method signature: (Lcom/google/common/collect/ImmutableList<Lcom/oculus/database/sqlite/SqlColumn;>;Ljava/lang/String;Lcom/google/common/collect/ImmutableList<Lcom/oculus/database/sqlite/SqlColumn;>;)V */
        public ForeignKey(ImmutableList immutableList, ImmutableList immutableList2) {
            this.keyColumns = immutableList;
            this.foreignKeyColumns = immutableList2;
        }

        @Override // com.oculus.database.sqlite.SqlKeys.SqlKey
        public final String A4P() {
            ImmutableList<SqlColumn> immutableList;
            ImmutableList<SqlColumn> immutableList2 = this.keyColumns;
            if (immutableList2 == null || immutableList2.isEmpty() || this.foreignTableName == null || (immutableList = this.foreignKeyColumns) == null || immutableList.isEmpty()) {
                throw new UnsupportedOperationException("All fields for foreign key must be specified");
            }
            Joiner joiner = new Joiner(", ");
            ImmutableList<SqlColumn> immutableList3 = this.keyColumns;
            Function<SqlColumn, String> function = SqlColumn.COLUMN_TO_NAME;
            return AnonymousClass006.A0B("FOREIGN KEY (", joiner.join(new AnonymousClass0pA(immutableList3, function)), ") REFERENCES ", this.foreignTableName, " (", new Joiner(", ").join(new AnonymousClass0pA(this.foreignKeyColumns, function)), ")");
        }
    }

    public static class PrimaryKey implements SqlKey {
        public final ImmutableList<SqlColumn> keyColumns;

        @Override // com.oculus.database.sqlite.SqlKeys.SqlKey
        public final String A4P() {
            ImmutableList<SqlColumn> immutableList = this.keyColumns;
            if (immutableList != null && !immutableList.isEmpty()) {
                return AnonymousClass006.A07("PRIMARY KEY (", new Joiner(", ").join(new AnonymousClass0pA(this.keyColumns, SqlColumn.COLUMN_TO_NAME)), ")");
            }
            throw new UnsupportedOperationException("Columns for primary key must be specified");
        }

        public PrimaryKey(ImmutableList<SqlColumn> immutableList) {
            this.keyColumns = immutableList;
        }
    }

    public interface SqlKey {
        String A4P();
    }
}
