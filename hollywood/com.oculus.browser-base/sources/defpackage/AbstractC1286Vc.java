package defpackage;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

/* renamed from: Vc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface AbstractC1286Vc {
    void a(int i, int i2);

    String b();

    void c(CharSequence charSequence, CharSequence charSequence2);

    void d();

    boolean dispatchKeyEvent(KeyEvent keyEvent);

    InputConnection e(InputConnection inputConnection);

    String f();

    boolean g();

    boolean h();

    void i(boolean z);

    void j(CharSequence charSequence);

    void k(boolean z);

    void onTextChanged(CharSequence charSequence, int i, int i2, int i3);
}
