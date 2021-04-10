package X;

/* renamed from: X.jn  reason: case insensitive filesystem */
public final class C0843jn implements D0 {
    public final String A00;

    @Override // X.D0
    public final Object[] A2j() {
        return new Object[]{"sqliteproc_metadata ", new String[]{"_id", "hash"}, "table_name = ?", new String[]{String.valueOf(this.A00)}, null, null, null};
    }

    public C0843jn(String str) {
        this.A00 = str;
    }
}
