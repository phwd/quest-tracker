package X;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0cG  reason: invalid class name and case insensitive filesystem */
public final class C03420cG extends AnonymousClass0GR {
    @Nullable
    public AnonymousClass0FB A00;
    @NonNull
    public final AbstractC01240Fs A01;
    @NonNull
    public final String A02 = "30dc8dfd36b329b3ca88d547471dd359";
    @NonNull
    public final String A03 = "78b61b28a467b981c6fd885e4b280479";

    public C03420cG(@NonNull AnonymousClass0FB r4, @NonNull AbstractC01240Fs r5) {
        super(r5.version);
        this.A00 = r4;
        this.A01 = r5;
    }

    private void A01(AnonymousClass0GQ r4) {
        r4.A2Q("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        r4.A2Q(AnonymousClass006.A07("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '", this.A02, "')"));
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass0GR
    public final void A03(AnonymousClass0GQ r4) {
        Cursor A74 = r4.A74("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z = false;
            if (A74.moveToFirst() && A74.getInt(0) == 0) {
                z = true;
            }
            A74.close();
            AbstractC01240Fs r1 = this.A01;
            r1.A02(r4);
            if (!z) {
                C01250Ft A002 = r1.A00(r4);
                if (!A002.A01) {
                    throw new IllegalStateException(AnonymousClass006.A05("Pre-packaged database has an invalid schema: ", A002.A00));
                }
            }
            A01(r4);
            r1.A04(r4);
        } catch (Throwable th) {
            A74.close();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        if (r4 != null) goto L_0x000e;
     */
    @Override // X.AnonymousClass0GR
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A05(X.AnonymousClass0GQ r9, int r10, int r11) {
        /*
        // Method dump skipped, instructions count: 196
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03420cG.A05(X.0GQ, int, int):void");
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass0GR
    public final void A02(AnonymousClass0GQ r4) {
        String str;
        super.A02(r4);
        Cursor A74 = r4.A74("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
        try {
            boolean z = false;
            if (A74.moveToFirst() && A74.getInt(0) != 0) {
                z = true;
            }
            if (z) {
                String str2 = null;
                Cursor A73 = r4.A73(new C03370cB("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
                try {
                    if (A73.moveToFirst()) {
                        str2 = A73.getString(0);
                    }
                    A73.close();
                    if (!this.A02.equals(str2) && !this.A03.equals(str2)) {
                        str = "Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.";
                        throw new IllegalStateException(str);
                    }
                } catch (Throwable th) {
                    A73.close();
                    throw th;
                }
            } else {
                C01250Ft A002 = this.A01.A00(r4);
                if (A002.A01) {
                    A01(r4);
                } else {
                    str = AnonymousClass006.A05("Pre-packaged database has an invalid schema: ", A002.A00);
                    throw new IllegalStateException(str);
                }
            }
            this.A01.A05(r4);
            this.A00 = null;
        } finally {
            A74.close();
        }
    }

    @Override // X.AnonymousClass0GR
    public final void A04(AnonymousClass0GQ r1, int i, int i2) {
        A05(r1, i, i2);
    }
}
