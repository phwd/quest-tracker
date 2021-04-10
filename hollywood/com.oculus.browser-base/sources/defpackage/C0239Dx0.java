package defpackage;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;

/* renamed from: Dx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0239Dx0 extends EK0 {
    @Override // defpackage.EK0
    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        int indexOfChild;
        ((JK0) view.getLayoutParams()).a();
        rect.set(0, 0, 0, 0);
        if (view.getId() == R.id.footer_command && recyclerView.indexOfChild(view) - 1 >= 0 && recyclerView.getChildAt(indexOfChild).getId() != R.id.footer_command) {
            rect.top = view.getContext().getResources().getDimensionPixelSize(R.dimen.f18710_resource_name_obfuscated_RES_2131165490) + view.getContext().getResources().getDimensionPixelSize(R.dimen.f20310_resource_name_obfuscated_RES_2131165650);
        }
    }

    @Override // defpackage.EK0
    public void h(Canvas canvas, RecyclerView recyclerView, VK0 vk0) {
        int childCount = recyclerView.getChildCount();
        int i = 0;
        while (i < childCount - 1) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt.getId() != R.id.footer_command) {
                i++;
                View childAt2 = recyclerView.getChildAt(i);
                if (childAt2.getId() == R.id.footer_command) {
                    WX a2 = WX.a(childAt2.getContext());
                    int dimensionPixelOffset = (childAt.getContext().getResources().getDimensionPixelOffset(R.dimen.f20310_resource_name_obfuscated_RES_2131165650) / 2) + childAt.getBottom();
                    a2.setBounds(recyclerView.getPaddingLeft() + recyclerView.getLeft(), dimensionPixelOffset, recyclerView.getRight() - recyclerView.getPaddingRight(), a2.getIntrinsicHeight() + dimensionPixelOffset);
                    a2.draw(canvas);
                }
            } else {
                return;
            }
        }
    }
}
