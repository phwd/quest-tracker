package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.task.PostTask;

/* renamed from: Eh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0267Eh1 extends View {
    public final Handler F;
    public final View G;
    public final AtomicBoolean H;
    public final AtomicBoolean I;

    /* renamed from: J  reason: collision with root package name */
    public final AtomicReference f7975J;
    public final AtomicReference K;
    public final C0145Ch1 L;

    public C0267Eh1(Context context, Handler handler, View view, C0145Ch1 ch1) {
        super(context);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        this.H = atomicBoolean;
        AtomicBoolean atomicBoolean2 = new AtomicBoolean();
        this.I = atomicBoolean2;
        AtomicReference atomicReference = new AtomicReference();
        this.f7975J = atomicReference;
        AtomicReference atomicReference2 = new AtomicReference();
        this.K = atomicReference2;
        this.F = handler;
        this.G = view;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setVisibility(0);
        atomicBoolean.set(view.hasFocus());
        atomicBoolean2.set(view.hasWindowFocus());
        atomicReference.set(view.getWindowToken());
        atomicReference2.set(view.getRootView());
        this.L = ch1;
    }

    public boolean checkInputConnectionProxy(View view) {
        return this.G == view;
    }

    public Handler getHandler() {
        return this.F;
    }

    public View getRootView() {
        if (this.I.get()) {
            return (View) this.K.get();
        }
        return null;
    }

    public IBinder getWindowToken() {
        return (IBinder) this.f7975J.get();
    }

    public boolean hasWindowFocus() {
        return this.I.get();
    }

    public boolean isFocused() {
        return this.H.get();
    }

    public boolean onCheckIsTextEditor() {
        return true;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return (InputConnection) PostTask.d(Zo1.d, new CallableC0206Dh1(this, editorInfo));
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }
}
