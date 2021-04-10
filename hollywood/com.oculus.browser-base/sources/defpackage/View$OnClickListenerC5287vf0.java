package defpackage;

import android.app.PendingIntent;
import android.util.Log;
import android.view.View;

/* renamed from: vf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5287vf0 implements View.OnClickListener {
    public final /* synthetic */ DialogC0504If0 F;

    public View$OnClickListenerC5287vf0(DialogC0504If0 if0) {
        this.F = if0;
    }

    public void onClick(View view) {
        PendingIntent sessionActivity;
        C0985Qd0 qd0 = this.F.w0;
        if (qd0 != null && (sessionActivity = ((C0741Md0) qd0.f8773a).f8489a.getSessionActivity()) != null) {
            try {
                sessionActivity.send();
                this.F.dismiss();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("MediaRouteCtrlDialog", sessionActivity + " was not sent, it had been canceled.");
            }
        }
    }
}
