package X;

import android.content.Context;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.alpenglow.R;

/* renamed from: X.0Mo  reason: invalid class name */
public class AnonymousClass0Mo extends C04210eR {
    public final /* synthetic */ AnonymousClass0Mm A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Mo(AnonymousClass0Mm r9, Context context, C04280eZ r11, View view) {
        super(context, r11, view, true, R.attr.actionOverflowMenuStyle, 0);
        this.A00 = r9;
        super.A00 = NotificationCompat$WearableExtender.DEFAULT_CONTENT_ICON_GRAVITY;
        A04(r9.A0D);
    }

    @Override // X.C04210eR
    public final void A02() {
        AnonymousClass0Mm r1 = this.A00;
        C04280eZ r0 = ((AbstractC04310ee) r1).A03;
        if (r0 != null) {
            r0.close();
        }
        r1.A00 = null;
        super.A02();
    }
}
