package defpackage;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* renamed from: b4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HandlerC1778b4 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public WeakReference f9511a;

    public HandlerC1778b4(DialogInterface dialogInterface) {
        this.f9511a = new WeakReference(dialogInterface);
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i == -3 || i == -2 || i == -1) {
            ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f9511a.get(), message.what);
        } else if (i == 1) {
            ((DialogInterface) message.obj).dismiss();
        }
    }
}
