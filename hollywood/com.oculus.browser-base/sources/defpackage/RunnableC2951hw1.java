package defpackage;

import android.app.Notification;
import android.content.Context;
import com.oculus.browser.R;
import org.chromium.base.task.PostTask;

/* renamed from: hw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2951hw1 implements Runnable {
    public final Context F;

    public RunnableC2951hw1(Context context) {
        this.F = context;
    }

    public void run() {
        Context context = this.F;
        C0771Mp0 mp0 = new C0771Mp0(context);
        Notification c = AbstractC3786mq0.a(true, "vr").H(context.getResources().getString(R.string.f64590_resource_name_obfuscated_RES_2131953776)).F(context.getResources().getString(R.string.f64580_resource_name_obfuscated_RES_2131953775)).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017).m("msg").o(2).c();
        if (c == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
        } else {
            mp0.b.notify(7, c);
        }
        PostTask.b(Zo1.f9374a, new RunnableC3121iw1(mp0), 5000);
    }
}
