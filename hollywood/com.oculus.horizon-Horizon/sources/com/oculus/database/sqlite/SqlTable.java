package com.oculus.database.sqlite;

import X.AnonymousClass0pA;
import android.database.sqlite.SQLiteDatabase;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oculus.database.sqlite.SqlKeys;
import javax.annotation.Nullable;

public abstract class SqlTable {
    public final ImmutableList<SqlColumn> mColumns;
    @Nullable
    public final ImmutableList<SqlKeys.SqlKey> mKeys;
    public final String mName;

    /* renamed from: com.oculus.database.sqlite.SqlTable$1  reason: invalid class name */
    public class AnonymousClass1 implements Function<SqlColumn, String> {
        public final /* synthetic */ ImmutableMap val$appliedFunctions;
    }

    public final void A01(SQLiteDatabase sQLiteDatabase) {
        String str = this.mName;
        ImmutableList<SqlColumn> immutableList = this.mColumns;
        ImmutableList<SqlKeys.SqlKey> immutableList2 = this.mKeys;
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE");
        sb.append(" ");
        sb.append(str);
        sb.append(" (");
        sb.append(new Joiner(", ").join(new AnonymousClass0pA(immutableList, SqlColumn.COLUMN_TO_DEFINITION)));
        if (immutableList2 != null && !immutableList2.isEmpty()) {
            sb.append(", ");
            sb.append(new Joiner(", ").join(new AnonymousClass0pA(immutableList2, SqlKeys.KEY_TO_SQL)));
        }
        sb.append(")");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Lcom/google/common/collect/ImmutableList<Lcom/oculus/database/sqlite/SqlColumn;>;)V */
    public SqlTable(ImmutableList immutableList) {
        this.mName = "apk_updates";
        this.mColumns = immutableList;
        this.mKeys = null;
    }

    public SqlTable(String str, ImmutableList<SqlColumn> immutableList, ImmutableList<SqlKeys.SqlKey> immutableList2) {
        this.mName = str;
        this.mColumns = immutableList;
        this.mKeys = immutableList2;
    }
}
