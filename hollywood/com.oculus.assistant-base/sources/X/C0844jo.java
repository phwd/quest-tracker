package X;

/* renamed from: X.jo  reason: case insensitive filesystem */
public final class C0844jo implements D0 {
    public final String A00;

    @Override // X.D0
    public final Object[] A2j() {
        return new Object[]{"sqliteproc_metadata ", new String[]{"_id", "index_hash"}, "table_name = ?", new String[]{String.valueOf(this.A00)}, null, null, null};
    }

    public C0844jo(String str) {
        this.A00 = str;
    }
}
