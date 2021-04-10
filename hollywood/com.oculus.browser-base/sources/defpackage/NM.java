package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* renamed from: NM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class NM implements Runnable {
    public final SM F;

    public NM(SM sm) {
        this.F = sm;
    }

    public void run() {
        SM sm = this.F;
        sm.f8891a = 0;
        if (!sm.b.equals(Uri.EMPTY)) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/csv");
            intent.putExtra("android.intent.extra.STREAM", sm.b);
            intent.putExtra("android.intent.extra.SUBJECT", sm.g.a().getResources().getString(R.string.f58030_resource_name_obfuscated_RES_2131953120));
            try {
                Intent createChooser = Intent.createChooser(intent, null);
                createChooser.addFlags(268435456);
                ContextUtils.getApplicationContext().startActivity(createChooser);
            } catch (ActivityNotFoundException unused) {
                sm.b(R.string.f58020_resource_name_obfuscated_RES_2131953119, null, R.string.f58010_resource_name_obfuscated_RES_2131953118, 3);
            }
            sm.b = null;
        }
    }
}
