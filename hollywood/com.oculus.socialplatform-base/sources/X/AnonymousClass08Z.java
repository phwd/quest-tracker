package X;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

/* renamed from: X.08Z  reason: invalid class name */
public class AnonymousClass08Z extends ContentObserver {
    public final /* synthetic */ AbstractC05360vQ A00;

    public final boolean deliverSelfNotifications() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass08Z(AbstractC05360vQ r2) {
        super(new Handler());
        this.A00 = r2;
    }

    public final void onChange(boolean z) {
        Cursor cursor;
        AbstractC05360vQ r1 = this.A00;
        if (r1.A05 && (cursor = r1.A02) != null && !cursor.isClosed()) {
            r1.A06 = r1.A02.requery();
        }
    }
}
