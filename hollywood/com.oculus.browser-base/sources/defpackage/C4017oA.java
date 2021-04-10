package defpackage;

import J.N;
import com.oculus.os.Version;
import java.util.HashMap;
import java.util.Map;
import org.chromium.content_public.browser.WebContents;

/* renamed from: oA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4017oA implements AbstractC0486Hz {

    /* renamed from: a  reason: collision with root package name */
    public long f10536a;
    public boolean b;
    public WebContents c;
    public boolean d;
    public int e = 0;
    public Map f;
    public AbstractC0303Ez g;
    public long h;

    public C4017oA() {
        C0425Gz gz = new C0425Gz();
        this.g = gz;
        if (a()) {
            this.f10536a = N.MONRFPb7(this);
        }
    }

    public static final String f(int i) {
        if (i == 1) {
            return "OutcomeWasPanelOpened";
        }
        if (i == 2) {
            return "OutcomeWasQuickActionClicked";
        }
        if (i == 3) {
            return "OutcomeWasQuickAnswerSeen";
        }
        if (i != 4) {
            return null;
        }
        return "OutcomeWasCardsDataShown";
    }

    public final boolean a() {
        return !AbstractC5686xz.c(17);
    }

    public void b(int i, Object obj) {
        if (a()) {
            if (this.f == null) {
                this.f = new HashMap();
            }
            this.f.put(Integer.valueOf(i), obj);
        }
    }

    public final void c(int i, Object obj) {
        if (obj instanceof Boolean) {
            e(i, ((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Integer) {
            e(i, ((Integer) obj).intValue());
        } else if (obj instanceof Character) {
            e(i, Character.getNumericValue(((Character) obj).charValue()));
        }
    }

    public void d(int i, Object obj) {
        if (a()) {
            if (this.f == null) {
                this.f = new HashMap();
            }
            this.f.put(Integer.valueOf(i), obj);
        }
    }

    public final void e(int i, int i2) {
        String str;
        if (f(i) == null) {
            switch (i) {
                case 5:
                    str = "DurationAfterScrollMs";
                    break;
                case 6:
                    str = "ScreenTopDps";
                    break;
                case Version.VERSION_7:
                    str = "WasScreenBottom";
                    break;
                case Version.VERSION_8:
                    str = "PreviousWeekImpressionsCount";
                    break;
                case Version.VERSION_9:
                    str = "PreviousWeekCtrPercent";
                    break;
                case Version.VERSION_10:
                    str = "Previous28DayImpressionsCount";
                    break;
                case Version.VERSION_11:
                    str = "Previous28DayCtrPercent";
                    break;
                case Version.VERSION_12:
                    str = "DidOptIn";
                    break;
                case Version.VERSION_13:
                    str = "IsShortWord";
                    break;
                case Version.VERSION_14:
                    str = "IsLongWord";
                    break;
                case Version.VERSION_15:
                    str = "IsWordEdge";
                    break;
                case Version.VERSION_16:
                    str = "IsEntity";
                    break;
                case Version.VERSION_17:
                    str = "TapDurationMs";
                    break;
                case Version.VERSION_18:
                    str = "FontSize";
                    break;
                case Version.VERSION_19:
                    str = "IsSecondTapOverride";
                    break;
                case Version.VERSION_20:
                    str = "IsHttp";
                    break;
                case Version.VERSION_21:
                    str = "IsEntityEligible";
                    break;
                case Version.VERSION_22:
                    str = "IsLanguageMismatch";
                    break;
                case Version.VERSION_23:
                    str = "PortionOfElement";
                    break;
                case Version.VERSION_24:
                    str = "TapCount";
                    break;
                case Version.VERSION_25:
                    str = "OpenCount";
                    break;
                case Version.VERSION_26:
                    str = "QuickAnswerCount";
                    break;
                case Version.VERSION_27:
                    str = "EntityImpressionsCount";
                    break;
                case Version.VERSION_28:
                    str = "EntityOpensCount";
                    break;
                case Version.VERSION_29:
                    str = "QuickActionImpressionsCount";
                    break;
                case Version.VERSION_30:
                    str = "QuickActionsTaken";
                    break;
                case Version.VERSION_31:
                    str = "QuickActionsIgnored";
                    break;
                default:
                    str = null;
                    break;
            }
        } else {
            str = f(i);
        }
        N.Mwcn3_wo(this.f10536a, this, str, i2);
    }
}
