package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: Js  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC0594Js extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final int f8318a;
    public final String b;
    public final Map c = new HashMap();
    public final Context d;
    public final YM e;

    public HandlerC0594Js(Context context) {
        YM ym = YM.f9268a;
        this.d = context;
        this.e = ym;
        this.f8318a = 1;
        this.b = "";
    }

    public void handleMessage(Message message) {
    }

    public boolean sendMessageAtTime(Message message, long j) {
        boolean z;
        Messenger messenger = message.replyTo;
        if (!this.c.containsKey(messenger)) {
            Map map = this.c;
            if (TextUtils.isEmpty(this.b)) {
                z = this.e.a(this.d, this.f8318a, "");
            } else {
                z = this.e.a(this.d, this.f8318a, this.b);
            }
            map.put(messenger, Boolean.valueOf(z));
        }
        if (!((Boolean) this.c.get(messenger)).booleanValue()) {
            return false;
        }
        return super.sendMessageAtTime(message, j);
    }
}
