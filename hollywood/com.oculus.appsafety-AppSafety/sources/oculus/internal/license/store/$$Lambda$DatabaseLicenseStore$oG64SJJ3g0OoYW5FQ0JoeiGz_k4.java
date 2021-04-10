package oculus.internal.license.store;

import java.util.function.Predicate;

/* renamed from: oculus.internal.license.store.-$$Lambda$DatabaseLicenseStore$oG64SJJ3g0OoYW5FQ0JoeiGz_k4  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DatabaseLicenseStore$oG64SJJ3g0OoYW5FQ0JoeiGz_k4 implements Predicate {
    public static final /* synthetic */ $$Lambda$DatabaseLicenseStore$oG64SJJ3g0OoYW5FQ0JoeiGz_k4 INSTANCE = new $$Lambda$DatabaseLicenseStore$oG64SJJ3g0OoYW5FQ0JoeiGz_k4();

    private /* synthetic */ $$Lambda$DatabaseLicenseStore$oG64SJJ3g0OoYW5FQ0JoeiGz_k4() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return DatabaseLicenseStore.lambda$revokeLicenses$9((Long) obj);
    }
}
