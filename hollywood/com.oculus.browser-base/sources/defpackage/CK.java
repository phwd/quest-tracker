package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;

/* renamed from: CK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CK implements TextWatcher {
    public final /* synthetic */ C4729sK F;
    public final /* synthetic */ DK G;

    public CK(DK dk, C4729sK sKVar) {
        this.G = dk;
        this.F = sKVar;
    }

    public void afterTextChanged(Editable editable) {
        this.F.s = editable.toString();
        this.G.c(false);
        this.G.e(false);
        int i = DK.F;
        C4729sK sKVar = this.G.G;
        AbstractC4559rK rKVar = sKVar.j;
        if (rKVar == null ? false : rKVar.b(sKVar.s)) {
            this.G.c(true);
            if (this.G.G.e()) {
                DK dk = this.G;
                dk.H.onEditorAction(dk.f7881J, 5, new KeyEvent(0, 66));
            }
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.G.f7881J.hasFocus()) {
            this.F.n = null;
        }
    }
}
