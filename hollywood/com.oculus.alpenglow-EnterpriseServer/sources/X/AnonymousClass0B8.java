package X;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* renamed from: X.0B8  reason: invalid class name */
public final class AnonymousClass0B8 extends ClickableSpan {
    public final int A00;
    public final int A01;
    public final AnonymousClass0BK A02;

    public final void onClick(@NonNull View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.A01);
        AnonymousClass0BK r0 = this.A02;
        r0.A00.performAction(this.A00, bundle);
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public AnonymousClass0B8(int i, AnonymousClass0BK r2, int i2) {
        this.A01 = i;
        this.A02 = r2;
        this.A00 = i2;
    }
}
