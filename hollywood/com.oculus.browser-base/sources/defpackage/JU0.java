package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.sharing.shared_clipboard.SharedClipboardMessageHandler;

/* renamed from: JU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class JU0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final String f8291a;
    public final String b;
    public final String c;

    public JU0(String str, String str2, String str3) {
        this.f8291a = str;
        this.b = str2;
        this.c = str3;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        String str;
        String str2;
        CB0 cb0;
        Notification notification;
        String str3 = this.f8291a;
        String str4 = this.b;
        String str5 = this.c;
        Integer num = (Integer) obj;
        if (num.intValue() == 0) {
            ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).cancel("SharedClipboard", 11);
            return;
        }
        int intValue = num.intValue();
        Resources resources = ContextUtils.getApplicationContext().getResources();
        String string = resources.getString(R.string.f48330_resource_name_obfuscated_RES_2131952150);
        switch (intValue) {
            case 1:
            case 2:
            case 4:
                str = resources.getString(R.string.f48380_resource_name_obfuscated_RES_2131952155, string);
                break;
            case 3:
                str = resources.getString(R.string.f48410_resource_name_obfuscated_RES_2131952158);
                break;
            case 5:
            case 6:
                str = resources.getString(R.string.f48390_resource_name_obfuscated_RES_2131952156, string);
                break;
            default:
                str = resources.getString(R.string.f48390_resource_name_obfuscated_RES_2131952156, string);
                break;
        }
        int intValue2 = num.intValue();
        Resources resources2 = ContextUtils.getApplicationContext().getResources();
        switch (intValue2) {
            case 1:
                str2 = resources2.getString(R.string.f48350_resource_name_obfuscated_RES_2131952152, str3);
                break;
            case 2:
                str2 = resources2.getString(R.string.f48370_resource_name_obfuscated_RES_2131952154);
                break;
            case 3:
                str2 = resources2.getString(R.string.f48400_resource_name_obfuscated_RES_2131952157);
                break;
            case 4:
                str2 = resources2.getString(R.string.f48340_resource_name_obfuscated_RES_2131952151, str3);
                break;
            case 5:
            case 6:
                str2 = resources2.getString(R.string.f48360_resource_name_obfuscated_RES_2131952153);
                break;
            default:
                str2 = resources2.getString(R.string.f48360_resource_name_obfuscated_RES_2131952153);
                break;
        }
        if (num.intValue() == 4 || num.intValue() == 2) {
            Context applicationContext = ContextUtils.getApplicationContext();
            cb0 = CB0.a(applicationContext, 0, new Intent(applicationContext, SharedClipboardMessageHandler.TryAgainReceiver.class).putExtra("android.intent.extra.TEXT", str4).putExtra("SharedClipboard.EXTRA_DEVICE_GUID", str5).putExtra("SharedClipboard.EXTRA_DEVICE_CLIENT_NAME", str3), 134217728);
        } else {
            cb0 = null;
        }
        Context applicationContext2 = ContextUtils.getApplicationContext();
        Resources resources3 = applicationContext2.getResources();
        AbstractC3615lq0 x = AbstractC3786mq0.b(true, "sharing", null, new C0832Np0(18, "SharedClipboard", 11)).H(str).r("SharedClipboard").D(resources3.getColor(R.color.f12640_resource_name_obfuscated_RES_2131099954)).o(1).A(R.drawable.f30090_resource_name_obfuscated_RES_2131231049).F(str2).G(-1).x(true);
        if (cb0 != null) {
            x.B(cb0).e(R.drawable.f29670_resource_name_obfuscated_RES_2131231007, resources3.getString(R.string.f63810_resource_name_obfuscated_RES_2131953698), cb0, 11);
        }
        C3444kq0 z = x.z(str2);
        NotificationManager notificationManager = (NotificationManager) applicationContext2.getSystemService("notification");
        if (z == null || (notification = z.f10306a) == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
        } else {
            C0832Np0 np0 = z.b;
            notificationManager.notify(np0.b, np0.c, notification);
        }
        AbstractC3102iq0.f10166a.b(18, z.f10306a);
    }
}
