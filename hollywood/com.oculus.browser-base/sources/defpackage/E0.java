package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.password_manager.AccountChooserDialog;

/* renamed from: E0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E0 implements View.OnClickListener {
    public final /* synthetic */ String F;
    public final /* synthetic */ F0 G;

    public E0(F0 f0, String str) {
        this.G = f0;
        this.F = str;
    }

    public void onClick(View view) {
        int i;
        AccountChooserDialog accountChooserDialog = this.G.F;
        String b = AbstractC1911br1.b(this.F);
        int i2 = AccountChooserDialog.F;
        Objects.requireNonNull(accountChooserDialog);
        Context context = view.getContext();
        Resources resources = context.getResources();
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.f39230_resource_name_obfuscated_RES_2131624232, (ViewGroup) null);
        textView.setText(b);
        textView.announceForAccessibility(b);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        textView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int width = view.getWidth();
        if (view.getLayoutDirection() == 1) {
            i = iArr[0];
        } else {
            i = (iArr[0] + width) - textView.getMeasuredWidth();
        }
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = ((iArr[1] - resources.getDimensionPixelSize(R.dimen.f24550_resource_name_obfuscated_RES_2131166074)) - (identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0)) - textView.getMeasuredHeight();
        int i3 = view.getLayoutDirection() == 1 ? 8388613 : 8388611;
        C1184Ti1 ti1 = new C1184Ti1(context, textView);
        ti1.b.setGravity(i3 | 48, i, dimensionPixelSize);
        ti1.b.setDuration(0);
        ti1.b.show();
    }
}
