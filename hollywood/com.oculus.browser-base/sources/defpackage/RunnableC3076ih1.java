package defpackage;

import J.N;
import android.view.KeyEvent;
import java.util.Objects;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* renamed from: ih1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3076ih1 implements Runnable {
    public final /* synthetic */ KeyEvent F;
    public final /* synthetic */ C5464wh1 G;

    public RunnableC3076ih1(C5464wh1 wh1, KeyEvent keyEvent) {
        this.G = wh1;
        this.F = keyEvent;
    }

    public void run() {
        C5464wh1 wh1 = this.G;
        KeyEvent keyEvent = this.F;
        Objects.requireNonNull(wh1);
        int action = keyEvent.getAction();
        int unicodeChar = keyEvent.getUnicodeChar();
        boolean z = false;
        if (action == 0) {
            if (keyEvent.getKeyCode() == 67) {
                wh1.j = 0;
            } else {
                if ((Integer.MIN_VALUE & unicodeChar) != 0) {
                    int i = Integer.MAX_VALUE & unicodeChar;
                    int i2 = wh1.j;
                    if (i2 == 0) {
                        wh1.j = i;
                    } else if (i == i2) {
                        wh1.d(i2, 0);
                    } else {
                        wh1.d(i2, i);
                    }
                } else {
                    int i3 = wh1.j;
                    if (!(i3 == 0 || unicodeChar == 0)) {
                        int deadChar = KeyEvent.getDeadChar(i3, unicodeChar);
                        if (deadChar != 0) {
                            wh1.d(deadChar, 0);
                        } else {
                            wh1.d(wh1.j, 0);
                            ImeAdapterImpl imeAdapterImpl = wh1.f;
                            if (imeAdapterImpl.v0()) {
                                N.M_V5g5ie(imeAdapterImpl.F, imeAdapterImpl);
                            }
                        }
                    }
                }
                z = true;
            }
        }
        if (!z) {
            this.G.f.B0(this.F);
        }
    }
}
