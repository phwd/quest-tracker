package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import java.util.Objects;
import org.chromium.chrome.browser.download.home.rename.RenameDialogCustomView;

/* renamed from: RL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RL0 implements TextWatcher {
    public final /* synthetic */ RenameDialogCustomView F;

    public RL0(RenameDialogCustomView renameDialogCustomView) {
        this.F = renameDialogCustomView;
    }

    public void afterTextChanged(Editable editable) {
        RenameDialogCustomView renameDialogCustomView = this.F;
        int i = RenameDialogCustomView.F;
        Objects.requireNonNull(renameDialogCustomView);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
