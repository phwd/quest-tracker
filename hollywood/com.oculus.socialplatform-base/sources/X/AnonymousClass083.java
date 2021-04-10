package X;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.RequiresApi;
import java.util.List;

@RequiresApi(16)
/* renamed from: X.083  reason: invalid class name */
public class AnonymousClass083 extends AccessibilityNodeProvider {
    public final AnonymousClass084 A00;

    public AnonymousClass083(AnonymousClass084 r1) {
        this.A00 = r1;
    }

    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        return null;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
        return null;
    }

    public final boolean performAction(int i, int i2, Bundle bundle) {
        return false;
    }
}
