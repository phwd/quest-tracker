package com.oculus.database.sqlite;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;

public final class SqlKeys {
    public static final Function<SqlKey, String> KEY_TO_SQL = new Function<SqlKey, String>() {
        /* class com.oculus.database.sqlite.SqlKeys.AnonymousClass1 */

        public String apply(SqlKey sqlKey) {
            return sqlKey.getSql();
        }
    };

    public interface SqlKey {
        String getSql();
    }

    public static class PrimaryKey implements SqlKey {
        private final ImmutableList<SqlColumn> keyColumns;

        public PrimaryKey(ImmutableList<SqlColumn> immutableList) {
            this.keyColumns = immutableList;
        }

        @Override // com.oculus.database.sqlite.SqlKeys.SqlKey
        public String getSql() {
            StringBuilder sb = new StringBuilder();
            ImmutableList<SqlColumn> immutableList = this.keyColumns;
            if (immutableList == null || immutableList.isEmpty()) {
                throw new UnsupportedOperationException("Columns for primary key must be specified");
            }
            sb.append("PRIMARY KEY (");
            sb.append(Joiner.on(", ").join(Collections2.transform(this.keyColumns, SqlColumn.COLUMN_TO_NAME)));
            sb.append(")");
            return sb.toString();
        }
    }

    public static class ForeignKey implements SqlKey {
        private final ImmutableList<SqlColumn> foreignKeyColumns;
        private final String foreignTableName;
        private final ImmutableList<SqlColumn> keyColumns;

        public ForeignKey(ImmutableList<SqlColumn> immutableList, String str, ImmutableList<SqlColumn> immutableList2) {
            this.keyColumns = immutableList;
            this.foreignTableName = str;
            this.foreignKeyColumns = immutableList2;
        }

        @Override // com.oculus.database.sqlite.SqlKeys.SqlKey
        public String getSql() {
            ImmutableList<SqlColumn> immutableList;
            StringBuilder sb = new StringBuilder();
            ImmutableList<SqlColumn> immutableList2 = this.keyColumns;
            if (immutableList2 == null || immutableList2.isEmpty() || this.foreignTableName == null || (immutableList = this.foreignKeyColumns) == null || immutableList.isEmpty()) {
                throw new UnsupportedOperationException("All fields for foreign key must be specified");
            }
            sb.append("FOREIGN KEY (");
            sb.append(Joiner.on(", ").join(Collections2.transform(this.keyColumns, SqlColumn.COLUMN_TO_NAME)));
            sb.append(") REFERENCES ");
            sb.append(this.foreignTableName);
            sb.append(" (");
            sb.append(Joiner.on(", ").join(Collections2.transform(this.foreignKeyColumns, SqlColumn.COLUMN_TO_NAME)));
            sb.append(")");
            return sb.toString();
        }
    }
}
