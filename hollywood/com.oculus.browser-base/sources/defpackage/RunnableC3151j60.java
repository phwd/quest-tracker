package defpackage;

import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: j60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3151j60 implements Runnable {
    public final /* synthetic */ View F;
    public final /* synthetic */ AtomicInteger G;
    public final /* synthetic */ Handler H;

    public RunnableC3151j60(C3493l60 l60, View view, AtomicInteger atomicInteger, Handler handler) {
        this.F = view;
        this.G = atomicInteger;
        this.H = handler;
    }

    public void run() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.F.getContext().getSystemService("input_method");
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            inputMethodManager.showSoftInput(this.F, 0);
        } catch (IllegalArgumentException e) {
            if (this.G.incrementAndGet() <= 10) {
                this.H.postDelayed(this, 100);
            } else {
                AbstractC1220Ua0.a("KeyboardVisibility", "Unable to open keyboard.  Giving up.", e);
            }
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskWrites);
    }
}
