package defpackage;

import android.app.Activity;
import android.content.IntentSender;
import java.util.Objects;

/* renamed from: wD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5390wD0 implements E10, AbstractC5355w11 {

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f11529a;
    public final N9 b;
    public boolean c;
    public Integer d;
    public C2823hA1 e;
    public Integer f;
    public Integer g;

    public C5390wD0(Runnable runnable, N9 n9) {
        this.f11529a = runnable;
        this.b = n9;
        setEnabled(true);
    }

    public static void f(int i) {
        AbstractC3364kK0.g("GoogleUpdate.Inline.CallFailure", i, 4);
    }

    @Override // defpackage.E10
    public void a() {
        C3506lA1 a2 = this.b.a();
        C4710sD0 sd0 = new C4710sD0(this);
        Objects.requireNonNull(a2);
        a2.b.a(new Yz1(AbstractC0745Me1.f8492a, sd0));
        a2.b();
        a2.a(new C4880tD0(this));
    }

    @Override // defpackage.E10
    public Integer b() {
        return this.d;
    }

    @Override // defpackage.AbstractC5355w11
    public void c(Object obj) {
        String str;
        Iz1 iz1 = (Iz1) obj;
        StringBuilder i = AbstractC2531fV.i("onStateUpdate(");
        i.append(iz1.f8262a);
        i.append(", ");
        i.append(iz1.b);
        i.append(")");
        int i2 = 0;
        AbstractC1220Ua0.d("PlayInline", i.toString(), new Object[0]);
        if (iz1.f8262a != this.g.intValue()) {
            StringBuilder i3 = AbstractC2531fV.i("GoogleUpdate.Inline.StateChange.Error.");
            int i4 = iz1.f8262a;
            if (i4 == 10) {
                str = "RequiresUiIntent";
            } else if (i4 != 11) {
                switch (i4) {
                    case 0:
                        str = "Unknown";
                        break;
                    case 1:
                        str = "Pending";
                        break;
                    case 2:
                        str = "Downloading";
                        break;
                    case 3:
                        str = "Installing";
                        break;
                    case 4:
                        str = "Installed";
                        break;
                    case 5:
                        str = "Failed";
                        break;
                    case 6:
                        str = "Canceled";
                        break;
                    default:
                        str = "Untracked";
                        break;
                }
            } else {
                str = "Downloaded";
            }
            i3.append(str);
            String sb = i3.toString();
            int i5 = iz1.b;
            if (i5 != -100) {
                if (i5 != 0) {
                    i2 = 1;
                    if (i5 != 1) {
                        switch (i5) {
                            case -7:
                                i2 = 7;
                                break;
                            case -6:
                                i2 = 6;
                                break;
                            case -5:
                                i2 = 5;
                                break;
                            case -4:
                                i2 = 4;
                                break;
                            case -3:
                                i2 = 3;
                                break;
                            case -2:
                                i2 = 2;
                                break;
                            default:
                                i2 = 9;
                                break;
                        }
                    }
                }
            } else {
                i2 = 8;
            }
            AbstractC3364kK0.g(sb, i2, 10);
        }
        this.g = Integer.valueOf(iz1.f8262a);
        e();
    }

    @Override // defpackage.E10
    public void d(Activity activity) {
        try {
            boolean c2 = this.b.c(this.e, 0, activity, 8123);
            AbstractC1220Ua0.d("PlayInline", "startUpdateFlowForResult() returned " + c2, new Object[0]);
            if (!c2) {
                f(0);
            }
        } catch (IntentSender.SendIntentException unused) {
            this.g = 5;
            AbstractC1220Ua0.d("PlayInline", "startUpdateFlowForResult() threw an exception.", new Object[0]);
            f(1);
        }
    }

    public final void e() {
        Integer num;
        if (this.c && (num = this.f) != null && this.g != null) {
            int intValue = num.intValue();
            int intValue2 = this.g.intValue();
            int i = 5;
            if (intValue2 == 1 || intValue2 == 2) {
                i = 4;
            } else if (intValue2 == 5) {
                i = 6;
            } else if (intValue2 != 11) {
                i = 0;
            }
            if (i == 0 && intValue == 2) {
                i = 3;
            }
            Integer num2 = this.d;
            if (num2 == null || num2.intValue() != i) {
                AbstractC1220Ua0.d("PlayInline", AbstractC2531fV.w("Pushing inline update state to ", i), new Object[0]);
                this.d = Integer.valueOf(i);
                this.f11529a.run();
            }
        }
    }

    @Override // defpackage.E10
    public void setEnabled(boolean z) {
        if (this.c != z) {
            this.c = z;
            if (z) {
                this.d = 0;
                this.b.e(this);
                C3506lA1 d2 = this.b.d();
                C5050uD0 ud0 = new C5050uD0(this);
                Objects.requireNonNull(d2);
                d2.b.a(new Yz1(AbstractC0745Me1.f8492a, ud0));
                d2.b();
                d2.a(new C5220vD0(this));
                return;
            }
            this.b.b(this);
        }
    }
}
