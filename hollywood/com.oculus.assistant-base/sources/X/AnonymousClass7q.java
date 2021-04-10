package X;

import android.os.Bundle;
import com.oculus.assistant.R;
import java.util.List;

/* renamed from: X.7q  reason: invalid class name */
public final class AnonymousClass7q extends AbstractC1409yv {
    public static void A00(AZ az) {
        String str;
        W5 w5 = new W5("Device Controls");
        W6 w6 = new W6("\"Turn down the volume\"");
        List list = w5.A01;
        list.add(w6);
        list.add(new W6("\"Go to library\""));
        list.add(new W6("\"Go to search\""));
        list.add(new W6("\"Go to WiFi settings\""));
        list.add(new W6("\"Open the browser\""));
        List list2 = az.A00;
        list2.add(w5);
        W5 w52 = new W5("Search");
        if (C0398Vv.A06) {
            str = "\"Play Beat Saber\"";
        } else {
            str = "\"Play Dead and Buried\"";
        }
        W6 w62 = new W6(str);
        List list3 = w52.A01;
        list3.add(w62);
        list3.add(new W6("\"Show me adventure games\""));
        list3.add(new W6("\"Find a free game to play\""));
        list3.add(new W6("\"Show me some 360 videos\""));
        list2.add(w52);
        int i = 600;
        int i2 = 512;
        if (W0.A00().A00.getBoolean("simplified_help_screen_enabled", false)) {
            i = 512;
            i2 = 380;
        }
        az.A0C(R.string.what_can_i_say_title);
        az.A0A(R.string.what_can_i_say_primary_button);
        az.A0B(R.string.dialog_close);
        Bundle bundle = az.A01;
        bundle.putInt("width", i);
        bundle.putInt("height", i2);
        az.A0G("ADCI_DIALOG_42ab2c51-0538-492e-adac-b15a987a7e69");
    }
}
