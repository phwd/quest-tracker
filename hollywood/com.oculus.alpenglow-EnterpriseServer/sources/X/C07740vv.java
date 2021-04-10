package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0vv  reason: invalid class name and case insensitive filesystem */
public final class C07740vv extends AbstractC07830w5 {
    public final C07640vh A00;

    public C07740vv(Context context, AnonymousClass0v4 r8, Integer num, @Nullable C07710vp r10, C07640vh r11) {
        super(context, r8, num, r10);
        this.A00 = r11;
        AnonymousClass0ux A002 = this.A02.A00(EnumC07690vn.LAST_HOST);
        if (System.currentTimeMillis() - A002.A3x("zero_rating_last_host_timestamp", 0) < 86400000) {
            this.A06 = A002.A4Z("zero_rating_last_host", null);
        }
    }
}
