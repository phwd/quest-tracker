package defpackage;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import java.util.Objects;

/* renamed from: tq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnClickListenerC4980tq0 implements DialogInterface.OnClickListener {
    public final C0164Cq0 F;
    public final String G;
    public final Activity H;

    public DialogInterface$OnClickListenerC4980tq0(C0164Cq0 cq0, String str, Activity activity) {
        this.F = cq0;
        this.G = str;
        this.H = activity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C0164Cq0 cq0 = this.F;
        String str = this.G;
        Activity activity = this.H;
        Objects.requireNonNull(cq0);
        if (i == -1) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.putExtra("com.android.browser.application_id", activity.getPackageName());
            intent.putExtra("create_new_tab", true);
            intent.setPackage(cq0.b.getPackageName());
            activity.startActivity(intent);
        }
    }
}
