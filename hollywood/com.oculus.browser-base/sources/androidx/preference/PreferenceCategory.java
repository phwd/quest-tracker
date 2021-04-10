package androidx.preference;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PreferenceCategory extends AbstractC2837hF0 {
    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, Ko1.a(context, R.attr.f7000_resource_name_obfuscated_RES_2130969146, 16842892), 0);
    }

    @Override // androidx.preference.Preference
    @Deprecated
    public void C(D d) {
        if (Build.VERSION.SDK_INT < 28) {
            AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo = d.b.getCollectionItemInfo();
            C c = collectionItemInfo != null ? new C(collectionItemInfo) : null;
            if (c != null) {
                d.j(C.a(((AccessibilityNodeInfo.CollectionItemInfo) c.f7778a).getRowIndex(), ((AccessibilityNodeInfo.CollectionItemInfo) c.f7778a).getRowSpan(), ((AccessibilityNodeInfo.CollectionItemInfo) c.f7778a).getColumnIndex(), ((AccessibilityNodeInfo.CollectionItemInfo) c.f7778a).getColumnSpan(), true, ((AccessibilityNodeInfo.CollectionItemInfo) c.f7778a).isSelected()));
            }
        }
    }

    @Override // androidx.preference.Preference
    public boolean X() {
        return !super.r();
    }

    @Override // androidx.preference.Preference
    public boolean r() {
        return false;
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        if (Build.VERSION.SDK_INT >= 28) {
            tf0.G.setAccessibilityHeading(true);
        }
    }
}
