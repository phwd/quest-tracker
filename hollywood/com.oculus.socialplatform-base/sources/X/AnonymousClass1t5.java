package X;

import android.content.Context;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.socialplatform.R;

/* renamed from: X.1t5  reason: invalid class name */
public class AnonymousClass1t5 extends AnonymousClass1sZ {
    public final /* synthetic */ C11591sO A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1t5(C11591sO r9, Context context, C11581sN r11, View view) {
        super(context, r11, view, true, R.attr.actionOverflowMenuStyle, 0);
        this.A00 = r9;
        super.A00 = NotificationCompat$WearableExtender.DEFAULT_CONTENT_ICON_GRAVITY;
        A04(r9.A0D);
    }

    @Override // X.AnonymousClass1sZ
    public final void A02() {
        C11591sO r1 = this.A00;
        C11581sN r0 = ((AnonymousClass1sT) r1).A03;
        if (r0 != null) {
            r0.close();
        }
        r1.A07 = null;
        super.A02();
    }
}
