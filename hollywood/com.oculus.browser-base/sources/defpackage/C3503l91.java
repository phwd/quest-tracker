package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.GridLayoutManager;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: l91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3503l91 extends View.AccessibilityDelegate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ E91 f10327a;
    public final /* synthetic */ I91 b;

    public C3503l91(I91 i91, E91 e91) {
        this.b = i91;
        this.f10327a = e91;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        TabListRecyclerView tabListRecyclerView = (TabListRecyclerView) this.f10327a;
        Objects.requireNonNull(tabListRecyclerView);
        ArrayList arrayList = new ArrayList();
        int K = tabListRecyclerView.K(view);
        if (K != -1) {
            int i = ((GridLayoutManager) tabListRecyclerView.U).H;
            Context context = tabListRecyclerView.getContext();
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction = new AccessibilityNodeInfo.AccessibilityAction(R.id.move_tab_left, context.getString(R.string.f45860_resource_name_obfuscated_RES_2131951903));
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction2 = new AccessibilityNodeInfo.AccessibilityAction(R.id.move_tab_right, context.getString(R.string.f45870_resource_name_obfuscated_RES_2131951904));
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction3 = new AccessibilityNodeInfo.AccessibilityAction(R.id.move_tab_up, context.getString(R.string.f45880_resource_name_obfuscated_RES_2131951905));
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction4 = new AccessibilityNodeInfo.AccessibilityAction(R.id.move_tab_down, context.getString(R.string.f45850_resource_name_obfuscated_RES_2131951902));
            arrayList.addAll(new ArrayList(Arrays.asList(accessibilityAction, accessibilityAction2, accessibilityAction3, accessibilityAction4)));
            int i2 = K % i;
            if (i2 == 0) {
                arrayList.remove(accessibilityAction);
            } else if (i2 == i - 1) {
                arrayList.remove(accessibilityAction2);
            }
            if (K < i) {
                arrayList.remove(accessibilityAction3);
            }
            if (tabListRecyclerView.T.b() - K <= i) {
                arrayList.remove(accessibilityAction4);
            }
            if (K == tabListRecyclerView.T.b() - 1) {
                arrayList.remove(accessibilityAction2);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            accessibilityNodeInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) it.next());
        }
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        Objects.requireNonNull((TabListRecyclerView) this.f10327a);
        if (!(i == R.id.move_tab_left || i == R.id.move_tab_right || i == R.id.move_tab_up || i == R.id.move_tab_down)) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        TabListRecyclerView tabListRecyclerView = (TabListRecyclerView) this.f10327a;
        int K = tabListRecyclerView.K(view);
        int i2 = ((GridLayoutManager) tabListRecyclerView.U).H;
        Pair pair = new Pair(Integer.valueOf(K), Integer.valueOf(i == R.id.move_tab_left ? K - 1 : i == R.id.move_tab_right ? K + 1 : i == R.id.move_tab_up ? K - i2 : i == R.id.move_tab_down ? K + i2 : -1));
        int intValue = ((Integer) pair.first).intValue();
        int intValue2 = ((Integer) pair.second).intValue();
        if (!this.b.i(intValue) || !this.b.i(intValue2)) {
            return false;
        }
        this.b.g.r(intValue, intValue2);
        AbstractC3535lK0.a("TabGrid.AccessibilityDelegate.Reordered");
        return true;
    }
}
