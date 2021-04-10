package defpackage;

import android.app.PendingIntent;
import android.view.View;

/* renamed from: G3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class G3 implements View.OnClickListener {
    public final PendingIntent F;

    public G3(PendingIntent pendingIntent) {
        this.F = pendingIntent;
    }

    public void onClick(View view) {
        H3.d(this.F);
    }
}
