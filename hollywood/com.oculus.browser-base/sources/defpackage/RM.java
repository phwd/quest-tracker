package defpackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.oculus.browser.R;

/* renamed from: RM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RM implements DialogInterface.OnClickListener {
    public final /* synthetic */ int F;
    public final /* synthetic */ SM G;

    public RM(SM sm, int i) {
        this.G = sm;
        this.F = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            int i2 = this.F;
            if (i2 == R.string.f58010_resource_name_obfuscated_RES_2131953118) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://support.google.com/drive/answer/2424384"));
                intent.setPackage(this.G.g.a().getPackageName());
                this.G.g.a().startActivity(intent);
            } else if (i2 == R.string.f63810_resource_name_obfuscated_RES_2131953698) {
                SM sm = this.G;
                sm.f8891a = 1;
                sm.a();
            }
        } else if (i == -2) {
            this.G.f8891a = 0;
        }
    }
}
