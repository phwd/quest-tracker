package X;

import android.content.Context;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.core.widget.NestedScrollView;
import com.oculus.alpenglow.R;

/* renamed from: X.0N6  reason: invalid class name */
public final class AnonymousClass0N6 extends DialogC04410er implements DialogInterface {
    public final AnonymousClass02i A00 = new AnonymousClass02i(getContext(), this, getWindow());

    public static int A00(@NonNull Context context, @StyleRes int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A00.A0N;
        if (nestedScrollView == null || !nestedScrollView.A0G(keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A00.A0N;
        if (nestedScrollView == null || !nestedScrollView.A0G(keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    public AnonymousClass0N6(@NonNull Context context, @StyleRes int i) {
        super(context, A00(context, i));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0165, code lost:
        if (r7.getVisibility() == 8) goto L_0x0167;
     */
    /* JADX WARNING: Removed duplicated region for block: B:117:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01c4  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01ff  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0207  */
    @Override // X.DialogC04410er
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate(android.os.Bundle r15) {
        /*
        // Method dump skipped, instructions count: 759
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0N6.onCreate(android.os.Bundle):void");
    }

    @Override // X.DialogC04410er, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        AnonymousClass02i r0 = this.A00;
        r0.A0R = charSequence;
        TextView textView = r0.A0M;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
