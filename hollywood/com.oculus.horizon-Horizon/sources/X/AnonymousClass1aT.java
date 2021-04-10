package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigservice.DeviceConfigDebugHelper;
import com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1aT  reason: invalid class name */
public abstract class AnonymousClass1aT {
    public final List<String[]> A09(AnonymousClass1ZX r18, String[] strArr) {
        List<String[]> A02;
        AnonymousClass0Rj r1;
        int i;
        Boolean bool;
        Long l;
        String str;
        Double d;
        MobileConfigAppAwareAccessorDecorator mobileConfigAppAwareAccessorDecorator = (MobileConfigAppAwareAccessorDecorator) this;
        synchronized (mobileConfigAppAwareAccessorDecorator) {
            A02 = mobileConfigAppAwareAccessorDecorator.mAccessor.A02(strArr);
            A02.size();
            int ordinal = AnonymousClass1b5.VALUE.ordinal();
            int ordinal2 = AnonymousClass1b5.CONFIG_PARAM_NAME.ordinal();
            int ordinal3 = AnonymousClass1b5.TYPE.ordinal();
            int ordinal4 = AnonymousClass1b5.SOURCE.ordinal();
            DeviceConfigDebugHelper A00 = DeviceConfigDebugHelper.A00();
            for (String[] strArr2 : A02) {
                String str2 = strArr2[ordinal2];
                String str3 = strArr2[ordinal3];
                String str4 = strArr2[ordinal4];
                try {
                    r1 = AnonymousClass0Rj.fromInt(Integer.parseInt(str4));
                } catch (NumberFormatException unused) {
                    AnonymousClass0NO.A0E(MobileConfigAppAwareAccessorDecorator.TAG, "Could not convert source string '%s'", str4);
                    r1 = AnonymousClass0Rj.UNKNOWN;
                }
                MobileConfigAppAwareAccessorDecorator.A05(r18.A01, str2, r1);
                if (A00 != null) {
                    try {
                        i = Integer.parseInt(str3);
                    } catch (NumberFormatException unused2) {
                        i = 0;
                    }
                    if (i == 1) {
                        synchronized (A00) {
                            bool = A00.mOverriddenBooleanValues.get(str2);
                        }
                        if (bool != null) {
                            strArr2[ordinal] = Boolean.toString(bool.booleanValue());
                        }
                    } else if (i == 2) {
                        synchronized (A00) {
                            l = A00.mOverriddenLongValues.get(str2);
                        }
                        if (l != null) {
                            strArr2[ordinal] = Long.toString(l.longValue());
                        }
                    } else if (i == 3) {
                        synchronized (A00) {
                            str = A00.mOverriddenStringValues.get(str2);
                        }
                        if (str != null) {
                            strArr2[ordinal] = str;
                        }
                    } else if (i != 4) {
                        AnonymousClass0NO.A0E(MobileConfigAppAwareAccessorDecorator.TAG, "Unknown type %s for %s.", str3, str2);
                    } else {
                        synchronized (A00) {
                            d = A00.mOverriddenDoubleValues.get(str2);
                        }
                        if (d != null) {
                            strArr2[ordinal] = Double.toString(d.doubleValue());
                        }
                    }
                }
            }
        }
        return A02;
    }
}
