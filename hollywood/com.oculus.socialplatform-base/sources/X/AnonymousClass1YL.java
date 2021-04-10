package X;

import android.content.Context;
import android.os.PowerManager;
import androidx.annotation.NonNull;

/* renamed from: X.1YL  reason: invalid class name */
public class AnonymousClass1YL extends AbstractC11341rp {
    public final PowerManager A00;
    public final /* synthetic */ AnonymousClass1rJ A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1YL(@NonNull AnonymousClass1rJ r3, Context context) {
        super(r3);
        this.A01 = r3;
        this.A00 = (PowerManager) context.getApplicationContext().getSystemService("power");
    }
}
