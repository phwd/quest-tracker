package defpackage;

import org.chromium.base.ContextUtils;

/* renamed from: cM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2002cM0 implements Runnable {
    public void run() {
        ContextUtils.getApplicationContext().getSharedPreferences("customtabs_client_bans", 0).edit();
    }
}
