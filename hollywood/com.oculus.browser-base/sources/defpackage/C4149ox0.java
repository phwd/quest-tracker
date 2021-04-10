package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.sync.ui.PassphraseTypeDialogFragment;

/* renamed from: ox0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4149ox0 extends ArrayAdapter {
    public final List F;
    public final /* synthetic */ PassphraseTypeDialogFragment G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4149ox0(PassphraseTypeDialogFragment passphraseTypeDialogFragment, List list, String[] strArr, C3978nx0 nx0) {
        super(passphraseTypeDialogFragment.u(), (int) R.layout.f40230_resource_name_obfuscated_RES_2131624332, strArr);
        this.G = passphraseTypeDialogFragment;
        this.F = list;
    }

    public long getItemId(int i) {
        return (long) ((Integer) this.F.get(i)).intValue();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        CheckedTextView checkedTextView = (CheckedTextView) super.getView(i, view, viewGroup);
        int intValue = ((Integer) this.F.get(i)).intValue();
        int l1 = this.G.l1();
        List a2 = AbstractC1757ax0.a(l1, this.G.K.getBoolean("arg_is_encrypt_everything_allowed"));
        checkedTextView.setChecked(intValue == l1);
        checkedTextView.setEnabled(((ArrayList) a2).contains(Integer.valueOf(intValue)));
        return checkedTextView;
    }

    public boolean hasStableIds() {
        return true;
    }
}
