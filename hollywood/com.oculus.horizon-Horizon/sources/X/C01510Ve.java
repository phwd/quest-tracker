package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Ve  reason: invalid class name and case insensitive filesystem */
public final class C01510Ve {
    public final C01480Va A00;
    public final File A01;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Ljava/lang/String;LX/0WY<Ljava/lang/String;>;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V */
    public C01510Ve(Context context, String str, AnonymousClass0WY r6, String str2) {
        this.A00 = new C01480Va(AnonymousClass006.A07("725056107548211", "|", "0e20c3123a90c76c02c901b7415ff67f"), new C01490Vb(r6), str2);
        this.A01 = new File(context.getFilesDir(), AnonymousClass006.A05("mqtt_analytics.", str));
    }
}
