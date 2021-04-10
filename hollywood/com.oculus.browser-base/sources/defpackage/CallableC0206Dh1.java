package defpackage;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import java.util.concurrent.Callable;

/* renamed from: Dh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class CallableC0206Dh1 implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final C0267Eh1 f7904a;
    public final EditorInfo b;

    public CallableC0206Dh1(C0267Eh1 eh1, EditorInfo editorInfo) {
        this.f7904a = eh1;
        this.b = editorInfo;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        C0267Eh1 eh1 = this.f7904a;
        EditorInfo editorInfo = this.b;
        eh1.L.f = false;
        InputConnection onCreateInputConnection = eh1.G.onCreateInputConnection(editorInfo);
        eh1.L.f = true;
        return onCreateInputConnection;
    }
}
