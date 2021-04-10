package defpackage;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

/* renamed from: tP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnClickListenerC4910tP0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ C5250vP0 F;

    public DialogInterface$OnClickListenerC4910tP0(C5250vP0 vp0) {
        this.F = vp0;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(new Uri.Builder().scheme("package").opaquePart(this.F.b.getPackageName()).build());
        ((Activity) this.F.b).startActivity(intent);
    }
}
