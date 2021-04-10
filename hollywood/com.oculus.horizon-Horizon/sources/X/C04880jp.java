package X;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.rti.push.service.FbnsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: X.0jp  reason: invalid class name and case insensitive filesystem */
public final class C04880jp extends AbstractC02010Yp {
    public static final List<String> A01 = new ArrayList(new ArrayList(((AbstractC01570Vx) AnonymousClass0W2.A00).A06()));
    public final FbnsService A00;

    public C04880jp(FbnsService fbnsService, C01890Xx r8, AnonymousClass0nN r9) {
        super(fbnsService, r8, r9, C02030Yr.A00(fbnsService.A0I()), AnonymousClass007.A00);
        this.A00 = fbnsService;
    }

    @Override // X.AbstractC02010Yp
    public final void A03(String str, Intent intent) {
        FbnsService fbnsService = this.A00;
        String str2 = intent.getPackage();
        C02420aV r2 = fbnsService.A01;
        Map<String, String> A002 = AnonymousClass0VY.A00("event_type", AnonymousClass0aS.A00(AnonymousClass007.A0J));
        if (!TextUtils.isEmpty(str)) {
            A002.put("event_extra_info", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            A002.put("dpn", str2);
        }
        C02420aV.A01(r2, "fbns_message_event", A002);
        fbnsService.A02.log(AnonymousClass006.A08("Redeliver Notif: notifId = ", str, "; target = ", str2));
    }

    @Override // X.AbstractC02010Yp
    public final void A04(String str, String str2, EnumC01870Xu r9) {
        FbnsService fbnsService = this.A00;
        String obj = r9.toString();
        C02420aV r2 = fbnsService.A01;
        Map<String, String> A002 = AnonymousClass0VY.A00("event_type", AnonymousClass0aS.A00(AnonymousClass007.A0D));
        if (!TextUtils.isEmpty(str)) {
            A002.put("event_extra_info", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            A002.put("dpn", str2);
        }
        A002.put("result", obj);
        C02420aV.A01(r2, "fbns_message_event", A002);
        fbnsService.A02.log(AnonymousClass006.A05("Error: Fail to deliver notifId = ", str));
    }

    @Override // X.AbstractC02010Yp
    public final boolean A05(AbstractC02020Yq r5) {
        Intent intent = r5.A00;
        EnumC01870Xu A002 = A00(this, intent);
        if (A002.isPermanentFailure()) {
            C05990mI A012 = A01();
            String str = r5.A01;
            A012.A00(str);
            A04(str, intent.getPackage(), A002);
        } else if (!A002.isSucceeded()) {
            intent.getPackage();
        }
        return A002.isSucceeded();
    }

    public static EnumC01870Xu A00(C04880jp r6, Intent intent) {
        EnumC01870Xu r4;
        C04900jr r1;
        String str;
        String str2 = intent.getPackage();
        if (!"com.facebook.rti.fbns.intent.RECEIVE".equals(intent.getAction())) {
            return EnumC01870Xu.DATA_INVALID;
        }
        C01890Xx r2 = r6.A04;
        if (TextUtils.isEmpty(str2)) {
            r4 = EnumC01870Xu.PACKAGE_INVALID;
        } else {
            Context context = r2.A00;
            if (!str2.equals(context.getPackageName())) {
                AbstractC01570Vx r12 = (AbstractC01570Vx) AnonymousClass0W2.A00;
                if (r12.A06().contains(str2) || r12.A02().equals(str2) || r12.A01().equals(str2)) {
                    switch (AnonymousClass0WZ.A00(context, str2, 64).A02.intValue()) {
                        case 1:
                            r4 = EnumC01870Xu.PACKAGE_NOT_INSTALLED;
                            break;
                        case 2:
                            r4 = EnumC01870Xu.PACKAGE_DISABLED;
                            break;
                        case 3:
                            r4 = EnumC01870Xu.PACKAGE_UNSUPPORTED;
                            break;
                        case 4:
                        default:
                            r4 = EnumC01870Xu.PACKAGE_FAILED;
                            break;
                        case 5:
                            r4 = EnumC01870Xu.PACKAGE_NOT_TRUSTED;
                            break;
                        case 6:
                            break;
                    }
                } else {
                    r4 = EnumC01870Xu.PACKAGE_INCOMPATIBLE;
                }
            }
            r4 = EnumC01870Xu.PACKAGE_TRUSTED;
        }
        if (r4 != EnumC01870Xu.PACKAGE_TRUSTED) {
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

    @Override // X.AbstractC02010Yp
    public final long A02(String str, String str2, boolean z) {
        long A02 = super.A02(str, str2, z);
        FbnsService fbnsService = this.A00;
        C02420aV r5 = fbnsService.A01;
        Map<String, String> A002 = AnonymousClass0VY.A00("event_type", AnonymousClass0aS.A00(AnonymousClass007.A0E));
        if (!TextUtils.isEmpty(str)) {
            A002.put("event_extra_info", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            A002.put("dpn", str2);
        }
        A002.put("delivery_delay", String.valueOf(A02));
        C02420aV.A01(r5, "fbns_message_event", A002);
        C04900jr r4 = fbnsService.A02;
        StringBuilder sb = new StringBuilder("ACK from ");
        sb.append(str2);
        sb.append(": notifId = ");
        sb.append(str);
        sb.append("; delay = ");
        sb.append(A02);
        r4.log(sb.toString());
        return A02;
    }
}
