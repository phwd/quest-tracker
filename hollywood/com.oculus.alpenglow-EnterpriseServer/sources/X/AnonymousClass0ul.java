package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.text.TextUtils;
import com.facebook.rti.push.service.FbnsService;
import java.util.Map;

/* renamed from: X.0ul  reason: invalid class name */
public abstract class AnonymousClass0ul {
    public BroadcastReceiver A00;
    public AnonymousClass0um A01;
    public final Context A02;
    public final C07640vh A03;
    public final C07800w2 A04;
    public final Integer A05;
    public final String A06;

    public final AnonymousClass0um A01() {
        String str;
        AnonymousClass0um r2 = this.A01;
        if (r2 != null) {
            return r2;
        }
        Context context = this.A02;
        String str2 = this.A06;
        if (1 - this.A05.intValue() != 0) {
            str = "FBNS_LITE";
        } else {
            str = "FBNS";
        }
        AnonymousClass0um r22 = new AnonymousClass0um(context, AnonymousClass006.A00(str2, '_', str), this.A03);
        this.A01 = r22;
        return r22;
    }

    public final void A02(String str, String str2, AnonymousClass0ut r8) {
        if (this instanceof AnonymousClass0uf) {
            FbnsService fbnsService = ((AnonymousClass0uf) this).A00;
            String r4 = r8.toString();
            AnonymousClass0uj r2 = fbnsService.A01;
            Map<String, String> A002 = C09120yh.A00("event_type", AnonymousClass0vJ.A00(AnonymousClass007.A0D));
            if (!TextUtils.isEmpty(str)) {
                A002.put("event_extra_info", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                A002.put("dpn", str2);
            }
            A002.put("result", r4);
            AnonymousClass0uj.A01(r2, "fbns_message_event", A002);
            fbnsService.A02.log(AnonymousClass006.A05("Error: Fail to deliver notifId = ", str));
        }
    }

    public AnonymousClass0ul(Context context, C07800w2 r2, C07640vh r3, String str, Integer num) {
        this.A02 = context;
        this.A04 = r2;
        this.A03 = r3;
        this.A06 = str;
        this.A05 = num;
    }

    public long A03(String str, String str2, boolean z) {
        return A01().A00(str);
    }
}
