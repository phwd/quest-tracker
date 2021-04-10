package defpackage;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.widget.PopupWindow;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Az  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0059Az {

    /* renamed from: a  reason: collision with root package name */
    public View f7711a;
    public C1796bA b;
    public C1175Tf1 c;
    public C4390qK0 d;
    public String e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public Point j;
    public PopupWindow.OnDismissListener k;
    public boolean l;

    public final Rect a() {
        int dimensionPixelOffset = this.f7711a.getResources().getDimensionPixelOffset(R.dimen.f17770_resource_name_obfuscated_RES_2131165396);
        int i2 = 1;
        if (!this.h) {
            if (d()) {
                i2 = -1;
            }
            int i3 = dimensionPixelOffset * 10 * i2;
            Point point = this.j;
            int i4 = point.x;
            int i5 = point.y;
            return new Rect(i4, i5 + i3, i4, i5 + i3);
        }
        C1796bA bAVar = this.b;
        int[] iArr = new int[2];
        bAVar.s0.findViewById(16908290).getLocationInWindow(iArr);
        int i6 = iArr[0];
        int i7 = iArr[1];
        float f2 = bAVar.S;
        float f3 = bAVar.F;
        int i8 = ((int) (f2 / f3)) + i6;
        int i9 = ((int) (bAVar.T / f3)) + i7;
        Rect rect = new Rect(i8, i9, ((int) (bAVar.Q / f3)) + i8, ((int) (bAVar.Y / f3)) + i9);
        rect.top -= dimensionPixelOffset;
        return rect;
    }

    public boolean b() {
        return this.f && "IPH_ContextualSearchTappedButShouldLongpress".equals(this.e);
    }

    public final void c(String str, Profile profile, boolean z) {
        this.h = z;
        if (!this.f && profile != null && this.f7711a != null) {
            if (!z || this.b != null) {
                this.e = str;
                if (!z || this.b.O()) {
                    Tm1 a2 = Um1.a(profile);
                    if (a2.shouldTriggerHelpUI(this.e)) {
                        String str2 = this.e;
                        str2.hashCode();
                        char c2 = 65535;
                        int i2 = 2;
                        int i3 = 0;
                        switch (str2.hashCode()) {
                            case -913282022:
                                if (str2.equals("IPH_ContextualSearchPromoteTap")) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                            case -889906730:
                                if (str2.equals("IPH_ContextualSearchTappedButShouldLongpress")) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                            case -712754779:
                                if (str2.equals("IPH_ContextualSearchPromotePanelOpen")) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                            case -385230107:
                                if (str2.equals("IPH_ContextualSearchWebSearch")) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                            case 1040592733:
                                if (str2.equals("IPH_ContextualSearchTranslationEnable")) {
                                    c2 = 4;
                                    break;
                                }
                                break;
                        }
                        switch (c2) {
                            case 0:
                                i3 = R.string.f50010_resource_name_obfuscated_RES_2131952318;
                                break;
                            case 1:
                                if (!this.i) {
                                    i3 = R.string.f50020_resource_name_obfuscated_RES_2131952319;
                                    break;
                                } else {
                                    i3 = R.string.f50030_resource_name_obfuscated_RES_2131952320;
                                    break;
                                }
                            case 2:
                                i3 = R.string.f49990_resource_name_obfuscated_RES_2131952316;
                                break;
                            case 3:
                                i3 = R.string.f50000_resource_name_obfuscated_RES_2131952317;
                                break;
                            case 4:
                                i3 = R.string.f49980_resource_name_obfuscated_RES_2131952315;
                                break;
                        }
                        this.d = new C4390qK0(a());
                        C1175Tf1 tf1 = new C1175Tf1(this.f7711a.getContext(), this.f7711a, i3, i3, true, this.d, C0283Ep.h().d());
                        this.c = tf1;
                        tf1.e(true);
                        this.c.I.P.b(new C6026zz(this, a2));
                        PopupWindow.OnDismissListener onDismissListener = this.k;
                        if (onDismissListener != null) {
                            this.c.I.P.b(onDismissListener);
                            this.k = null;
                        }
                        if (!this.h) {
                            C1175Tf1 tf12 = this.c;
                            if (d()) {
                                i2 = 1;
                            }
                            tf12.I.Y = i2;
                        }
                        this.c.f();
                        this.f = true;
                        this.g = true;
                    }
                }
            }
        }
    }

    public final boolean d() {
        return this.j.y < this.f7711a.getHeight() / 3;
    }
}
