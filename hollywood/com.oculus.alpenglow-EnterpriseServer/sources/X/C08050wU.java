package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0wU  reason: invalid class name and case insensitive filesystem */
public final class C08050wU {
    public final File A00;

    public C08050wU(Context context, String str) {
        this.A00 = new File(context.getFilesDir(), AnonymousClass006.A05("mqtt_analytics.", str));
    }
}
