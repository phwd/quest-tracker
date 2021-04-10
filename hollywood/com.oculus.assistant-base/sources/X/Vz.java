package X;

import android.app.NotificationManager;
import android.content.Context;
import com.oculus.assistant.R;

public final class Vz {
    public static void A00(Context context, int i, int i2, String str, int i3) {
        if (i2 == R.string.notif_title_listening) {
            C00799i.A00.logAttentionSystem("notification");
            YP.A00().A02();
        }
        AnonymousClass1C r2 = new AnonymousClass1C(context);
        r2.A07 = AnonymousClass1C.A00(context.getString(R.string.notif_voice_commands));
        r2.A06 = AnonymousClass1C.A00(str);
        r2.A00 = 1;
        r2.A01.icon = i3;
        ((NotificationManager) context.getSystemService("notification")).notify(i, new C0638dd(r2).A00());
    }
}
