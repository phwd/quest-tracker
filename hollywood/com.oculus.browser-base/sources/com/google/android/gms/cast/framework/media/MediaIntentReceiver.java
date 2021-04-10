package com.google.android.gms.cast.framework.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import com.oculus.os.Version;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaIntentReceiver extends BroadcastReceiver {
    public static void b(C2922hn hnVar, long j) {
        ML0 c;
        if (j != 0 && (c = c(hnVar)) != null && !c.i() && !c.m()) {
            c.q(c.b() + j);
        }
    }

    public static ML0 c(C2922hn hnVar) {
        if (hnVar == null || !hnVar.a()) {
            return null;
        }
        return hnVar.f();
    }

    public void a() {
    }

    public void onReceive(Context context, Intent intent) {
        ML0 c;
        ML0 c2;
        ML0 c3;
        KeyEvent keyEvent;
        ML0 c4;
        String action = intent.getAction();
        if (action != null) {
            SS0 b = C1557Zm.c(context).b();
            char c5 = 65535;
            switch (action.hashCode()) {
                case -1699820260:
                    if (action.equals("com.google.android.gms.cast.framework.action.REWIND")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case -945151566:
                    if (action.equals("com.google.android.gms.cast.framework.action.SKIP_NEXT")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case -945080078:
                    if (action.equals("com.google.android.gms.cast.framework.action.SKIP_PREV")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case -668151673:
                    if (action.equals("com.google.android.gms.cast.framework.action.STOP_CASTING")) {
                        c5 = 3;
                        break;
                    }
                    break;
                case -124479363:
                    if (action.equals("com.google.android.gms.cast.framework.action.DISCONNECT")) {
                        c5 = 4;
                        break;
                    }
                    break;
                case 235550565:
                    if (action.equals("com.google.android.gms.cast.framework.action.TOGGLE_PLAYBACK")) {
                        c5 = 5;
                        break;
                    }
                    break;
                case 1362116196:
                    if (action.equals("com.google.android.gms.cast.framework.action.FORWARD")) {
                        c5 = 6;
                        break;
                    }
                    break;
                case 1997055314:
                    if (action.equals("android.intent.action.MEDIA_BUTTON")) {
                        c5 = 7;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    long longExtra = intent.getLongExtra("googlecast-extra_skip_step_ms", 0);
                    PS0 d = b.d();
                    if (d instanceof C2922hn) {
                        b((C2922hn) d, -longExtra);
                        return;
                    }
                    return;
                case 1:
                    PS0 d2 = b.d();
                    if ((d2 instanceof C2922hn) && (c = c((C2922hn) d2)) != null && !c.m()) {
                        SE0.e("Must be called from the main thread.");
                        if (!c.w()) {
                            ML0.s(17, null);
                            return;
                        } else {
                            c.t(new WC1(c, c.g, null));
                            return;
                        }
                    } else {
                        return;
                    }
                case 2:
                    PS0 d3 = b.d();
                    if ((d3 instanceof C2922hn) && (c2 = c((C2922hn) d3)) != null && !c2.m()) {
                        SE0.e("Must be called from the main thread.");
                        if (!c2.w()) {
                            ML0.s(17, null);
                            return;
                        } else {
                            c2.t(new IC1(c2, c2.g, null));
                            return;
                        }
                    } else {
                        return;
                    }
                case 3:
                    b.b(true);
                    return;
                case 4:
                    b.b(false);
                    return;
                case 5:
                    PS0 d4 = b.d();
                    if ((d4 instanceof C2922hn) && (c3 = c((C2922hn) d4)) != null) {
                        c3.r();
                        return;
                    }
                    return;
                case 6:
                    long longExtra2 = intent.getLongExtra("googlecast-extra_skip_step_ms", 0);
                    PS0 d5 = b.d();
                    if (d5 instanceof C2922hn) {
                        b((C2922hn) d5, longExtra2);
                        return;
                    }
                    return;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    PS0 d6 = b.d();
                    if ((d6 instanceof C2922hn) && intent.hasExtra("android.intent.extra.KEY_EVENT") && (keyEvent = (KeyEvent) intent.getExtras().get("android.intent.extra.KEY_EVENT")) != null && keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 85 && (c4 = c((C2922hn) d6)) != null) {
                        c4.r();
                        return;
                    }
                    return;
                default:
                    a();
                    return;
            }
        }
    }
}
