package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

/* renamed from: uJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5068uJ0 implements View.OnClickListener {
    public final /* synthetic */ C5238vJ0 F;

    public View$OnClickListenerC5068uJ0(C5238vJ0 vj0) {
        this.F = vj0;
    }

    public void onClick(View view) {
        String packageName = this.F.f11474a.getPackageName();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(new Uri.Builder().scheme("package").opaquePart(packageName).build());
        ((Activity) this.F.f11474a).startActivity(intent);
    }
}
