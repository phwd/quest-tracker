package defpackage;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Messenger;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContextUtils;

/* renamed from: gW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2704gW {

    /* renamed from: a  reason: collision with root package name */
    public PendingIntent f10003a;
    public final Object b = new Object();

    public Bundle a(String str, Bundle bundle) {
        bundle.putString("sender", str);
        bundle.putString("subscription", str);
        if (bundle.getString("subtype") == null) {
            bundle.putString("subtype", str);
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        } else if (AbstractC4652ru0.a(ContextUtils.getApplicationContext(), "com.google.android.gms") >= 0) {
            LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
            Messenger messenger = new Messenger(new HandlerC2533fW(this, Looper.getMainLooper(), linkedBlockingQueue));
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            synchronized (this.b) {
                if (this.f10003a == null) {
                    Intent intent2 = new Intent();
                    intent2.setPackage("com.google.example.invalidpackage");
                    this.f10003a = PendingIntent.getBroadcast(ContextUtils.getApplicationContext(), 0, intent2, 0);
                }
            }
            intent.putExtra("app", this.f10003a);
            intent.putExtras(bundle);
            intent.putExtra("google.messenger", messenger);
            ContextUtils.getApplicationContext().startService(intent);
            try {
                Intent intent3 = (Intent) linkedBlockingQueue.poll(5000, TimeUnit.MILLISECONDS);
                if (intent3 == null) {
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                } else if (intent3.getStringExtra("registration_id") != null) {
                    return intent3.getExtras();
                } else {
                    String stringExtra = intent3.getStringExtra("error");
                    if (stringExtra != null) {
                        throw new IOException(stringExtra);
                    }
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                }
            } catch (InterruptedException e) {
                throw new IOException(e.getMessage());
            }
        } else {
            throw new IOException("Google Play Services missing");
        }
    }
}
