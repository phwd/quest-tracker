package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Vd  reason: invalid class name */
public final class AnonymousClass0Vd {
    public final File A00;

    public AnonymousClass0Vd(Context context, String str) {
        this.A00 = new File(context.getFilesDir(), AnonymousClass006.A05("mqtt_analytics.", str));
    }
}
