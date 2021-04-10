package X;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.rti.push.service.FbnsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: X.0uf  reason: invalid class name */
public final class AnonymousClass0uf extends AnonymousClass0ul {
    public static final List<String> A01 = new ArrayList(new ArrayList(((AnonymousClass153) ((AnonymousClass0v1) AnonymousClass151.A00)).A06));
    public final FbnsService A00;

    public AnonymousClass0uf(FbnsService fbnsService, C07800w2 r8, C07640vh r9) {
        super(fbnsService, r8, r9, AnonymousClass0vG.A00(AnonymousClass007.A01), AnonymousClass007.A00);
        this.A00 = fbnsService;
    }

    public static AnonymousClass0ut A00(AnonymousClass0uf r6, Intent intent) {
        AnonymousClass0ut r4;
        C08340x4 r1;
        String str;
        String str2 = intent.getPackage();
        if (!"com.facebook.rti.fbns.intent.RECEIVE".equals(intent.getAction())) {
            return AnonymousClass0ut.DATA_INVALID;
        }
        C07800w2 r2 = r6.A04;
        if (TextUtils.isEmpty(str2)) {
            r4 = AnonymousClass0ut.PACKAGE_INVALID;
        } else {
            Context context = r2.A00;
            if (!str2.equals(context.getPackageName())) {
                AnonymousClass153 r12 = (AnonymousClass153) ((AnonymousClass0v1) AnonymousClass151.A00);
                if (r12.A06.contains(str2) || r12.A02.equals(str2) || r12.A01.equals(str2)) {
                    switch (C08100wZ.A00(context, str2, 64).A02.intValue()) {
                        case 1:
                            r4 = AnonymousClass0ut.PACKAGE_NOT_INSTALLED;
                            break;
                        case 2:
                            r4 = AnonymousClass0ut.PACKAGE_DISABLED;
                            break;
                        case 3:
                            r4 = AnonymousClass0ut.PACKAGE_UNSUPPORTED;
                            break;
                        case 4:
                        default:
                            r4 = AnonymousClass0ut.PACKAGE_FAILED;
                            break;
                        case 5:
                            r4 = AnonymousClass0ut.PACKAGE_NOT_TRUSTED;
                            break;
                        case 6:
                            break;
                    }
                } else {
                    r4 = AnonymousClass0ut.PACKAGE_INCOMPATIBLE;
                }
            }
            r4 = AnonymousClass0ut.PACKAGE_TRUSTED;
        }
        if (r4 != AnonymousClass0ut.PACKAGE_TRUSTED) {
            FbnsService fbnsService = r6.A00;
            fbnsService.A01.A04(AnonymousClass007.A0H, r4.name(), str2);
            r1 = fbnsService.A02;
            str = "Error: isTrusted() failed";
        } else {
            r4 = r2.A01(intent, str2);
            if (!r4.isSucceeded()) {
                FbnsService fbnsService2 = r6.A00;
                fbnsService2.A01.A04(AnonymousClass007.A0I, null, str2);
                r1 = fbnsService2.A02;
                str = "Error: secure-broadcast failed";
            }
            return r4;
        }
        r1.log(str);
        return r4;
    }

    @Override // X.AnonymousClass0ul
    public final long A03(String str, String str2, boolean z) {
        long A03 = super.A03(str, str2, z);
        FbnsService fbnsService = this.A00;
        AnonymousClass0uj r5 = fbnsService.A01;
        Map<String, String> A002 = C09120yh.A00("event_type", AnonymousClass0vJ.A00(AnonymousClass007.A0E));
        if (!TextUtils.isEmpty(str)) {
            A002.put("event_extra_info", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            A002.put("dpn", str2);
        }
        A002.put("delivery_delay", String.valueOf(A03));
        AnonymousClass0uj.A01(r5, "fbns_message_event", A002);
        C08340x4 r4 = fbnsService.A02;
        r4.log("ACK from " + str2 + ": notifId = " + str + "; delay = " + A03);
        return A03;
    }
}
