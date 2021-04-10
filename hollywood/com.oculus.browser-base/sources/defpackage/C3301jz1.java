package defpackage;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* renamed from: jz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3301jz1 extends C3130iz1 {
    public C3301jz1(C3985nz1 nz1, WindowInsets windowInsets) {
        super(nz1, windowInsets);
    }

    @Override // defpackage.C3814mz1
    public C3985nz1 a() {
        return C3985nz1.h(this.i.consumeDisplayCutout());
    }

    @Override // defpackage.C3814mz1
    public C2156dG e() {
        DisplayCutout displayCutout = this.i.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new C2156dG(displayCutout);
    }

    @Override // defpackage.C3814mz1
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3301jz1)) {
            return false;
        }
        return Objects.equals(this.i, ((C3301jz1) obj).i);
    }

    @Override // defpackage.C3814mz1
    public int hashCode() {
        return this.i.hashCode();
    }
}
