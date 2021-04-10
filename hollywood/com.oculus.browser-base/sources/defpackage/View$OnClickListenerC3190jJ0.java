package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import java.util.Objects;

/* renamed from: jJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC3190jJ0 implements View.OnClickListener {
    public final /* synthetic */ Context F;
    public final /* synthetic */ C3361kJ0 G;

    public View$OnClickListenerC3190jJ0(C3361kJ0 kj0, Context context) {
        this.G = kj0;
        this.F = context;
    }

    public void onClick(View view) {
        C3361kJ0 kj0 = this.G;
        String packageName = this.F.getPackageName();
        Objects.requireNonNull(kj0);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(new Uri.Builder().scheme("package").opaquePart(packageName).build());
        ((Activity) this.F).startActivity(intent);
    }
}
