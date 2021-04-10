package defpackage;

import android.view.View;
import android.widget.CompoundButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;

/* renamed from: kp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3440kp implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ ChipGroup F;

    public C3440kp(ChipGroup chipGroup, AbstractC3269jp jpVar) {
        this.F = chipGroup;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ChipGroup chipGroup = this.F;
        if (!chipGroup.Q) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < chipGroup.getChildCount(); i++) {
                View childAt = chipGroup.getChildAt(i);
                if ((childAt instanceof Chip) && ((Chip) childAt).isChecked()) {
                    arrayList.add(Integer.valueOf(childAt.getId()));
                    if (chipGroup.L) {
                        break;
                    }
                }
            }
            if (arrayList.isEmpty()) {
                ChipGroup chipGroup2 = this.F;
                if (chipGroup2.M) {
                    chipGroup2.a(compoundButton.getId(), true);
                    this.F.P = compoundButton.getId();
                    return;
                }
            }
            int id = compoundButton.getId();
            if (z) {
                ChipGroup chipGroup3 = this.F;
                int i2 = chipGroup3.P;
                if (!(i2 == -1 || i2 == id || !chipGroup3.L)) {
                    chipGroup3.a(i2, false);
                }
                this.F.P = id;
                return;
            }
            ChipGroup chipGroup4 = this.F;
            if (chipGroup4.P == id) {
                chipGroup4.P = -1;
            }
        }
    }
}
