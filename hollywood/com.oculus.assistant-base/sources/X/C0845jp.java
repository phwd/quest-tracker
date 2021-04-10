package X;

import com.facebook.acra.AppComponentStats;

/* renamed from: X.jp  reason: case insensitive filesystem */
public final class C0845jp implements D0 {
    public final String A00;

    @Override // X.D0
    public final Object[] A2j() {
        return new Object[]{"sqliteproc_schema ", new String[]{"_id", AppComponentStats.ATTRIBUTE_NAME, "type_name", "default_value", "is_nullable", "is_primary", "is_autoincrement", "is_deleted", "is_added", "foreign_table", "foreign_column", "on_foreign_key_update", "on_foreign_key_delete"}, "table_name = ?", new String[]{String.valueOf(this.A00)}, null, null, null};
    }

    public C0845jp(String str) {
        this.A00 = str;
    }
}
