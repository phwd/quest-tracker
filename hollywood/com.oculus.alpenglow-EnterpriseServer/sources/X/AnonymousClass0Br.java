package X;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

/* renamed from: X.0Br  reason: invalid class name */
public class AnonymousClass0Br extends ContentObserver {
    public final /* synthetic */ AbstractC03680cx A00;

    public final boolean deliverSelfNotifications() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Br(AbstractC03680cx r2) {
        super(new Handler());
        this.A00 = r2;
    }

    public final void onChange(boolean z) {
        Cursor cursor;
        AbstractC03680cx r1 = this.A00;
        if (r1.A05 && (cursor = r1.A02) != null && !cursor.isClosed()) {
            r1.A06 = r1.A02.requery();
        }
    }
}
