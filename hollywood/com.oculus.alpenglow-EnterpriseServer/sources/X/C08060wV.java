package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0wV  reason: invalid class name and case insensitive filesystem */
public final class C08060wV {
    public final C08150wf A00;
    public final File A01;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Ljava/lang/String;LX/0zk<Ljava/lang/String;>;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V */
    public C08060wV(Context context, String str, AbstractC09610zk r6, String str2) {
        this.A00 = new C08150wf(AnonymousClass006.A07("725056107548211", "|", "0e20c3123a90c76c02c901b7415ff67f"), new C08070wW(r6), str2);
        this.A01 = new File(context.getFilesDir(), AnonymousClass006.A05("mqtt_analytics.", str));
    }
}
