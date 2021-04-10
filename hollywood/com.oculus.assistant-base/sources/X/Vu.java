package X;

import android.app.Application;
import android.content.Intent;
import android.os.SystemClock;
import com.facebook.proxygen.TraceFieldType;
import java.util.StringTokenizer;

public final class Vu {
    public static int A00(String str, String str2) {
        int countTokens;
        int i = 0;
        if (str == null) {
            countTokens = 0;
        } else {
            countTokens = new StringTokenizer(str).countTokens();
        }
        if (str2 != null) {
            i = new StringTokenizer(str2).countTokens();
        }
        return ((int) Math.ceil((double) ((((float) countTokens) + ((float) i)) / 2.0f))) * 1000;
    }

    public static Intent A01() {
        Intent intent = new Intent();
        intent.setAction("com.oculus.assistant.ACTION_SHOW_DIALOG");
        intent.setPackage(BX.A00().getPackageName());
        intent.putExtra(TraceFieldType.StartTime, SystemClock.elapsedRealtime());
        intent.putExtra("dialog_id", "dlg-voice-selection");
        return intent;
    }

    public static Intent A02(String str) {
        Intent intent = new Intent();
        intent.setAction("com.oculus.assistant.ACTIVATE_ASSISTANT");
        intent.setPackage(BX.A00().getPackageName());
        intent.putExtra("source", str);
        intent.putExtra(TraceFieldType.StartTime, SystemClock.elapsedRealtime());
        return intent;
    }

    public static void A03(Intent intent) {
        YA ya;
        Application A00 = BX.A00();
        A00.getPackageName();
        Intent A002 = YU.A00();
        A002.putExtras(intent);
        A002.setAction(intent.getAction());
        J2 A003 = J2.A00();
        synchronized (A003) {
            ya = A003.A01;
            if (ya == null) {
                X3 x3 = A003.A04;
                if (x3 == null) {
                    x3 = new X3(J2.A0D, J2.A0C, J2.A0E);
                    A003.A04 = x3;
                }
                ya = new YA(x3, A003.A08);
                A003.A01 = ya;
            }
        }
        ya.A01(A002, A00);
    }
}
