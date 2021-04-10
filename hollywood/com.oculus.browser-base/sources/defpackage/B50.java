package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryModernView;

/* renamed from: B50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B50 extends C1605a60 {
    public final /* synthetic */ KeyboardAccessoryModernView b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public B50(KeyboardAccessoryModernView keyboardAccessoryModernView, int i) {
        super(i);
        this.b = keyboardAccessoryModernView;
    }

    @Override // defpackage.C1605a60
    public int j(View view, RecyclerView recyclerView, VK0 vk0) {
        int i = this.f9408a;
        boolean z = true;
        if (recyclerView.K(view) != recyclerView.T.b() - 1) {
            z = false;
        }
        if (!z) {
            return i;
        }
        if (view.getWidth() != 0 || !vk0.f) {
            int width = recyclerView.getWidth();
            int i2 = 0;
            for (int i3 = 0; i3 < recyclerView.getChildCount(); i3++) {
                View childAt = recyclerView.getChildAt(i3);
                int width2 = childAt.getWidth();
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    width2 = width2 + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                }
                i2 += width2;
            }
            return Math.max(((width - i2) - ((recyclerView.getChildCount() - 1) * this.f9408a)) - (recyclerView.getPaddingStart() + recyclerView.getPaddingEnd()), i);
        }
        view.post(new A50(recyclerView));
        return recyclerView.getWidth() - (this.b.getContext().getResources().getDimensionPixelSize(R.dimen.f20330_resource_name_obfuscated_RES_2131165652) * ((ViewGroup) view).getChildCount());
    }
}
