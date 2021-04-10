package oculus.internal.license.parsing;

import java.util.function.Function;
import oculus.internal.license.parsing.CodableLicense;

/* renamed from: oculus.internal.license.parsing.-$$Lambda$_fmM_PUI85Mw3m3N2a1UsTv_Oqc  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$_fmM_PUI85Mw3m3N2a1UsTv_Oqc implements Function {
    public static final /* synthetic */ $$Lambda$_fmM_PUI85Mw3m3N2a1UsTv_Oqc INSTANCE = new $$Lambda$_fmM_PUI85Mw3m3N2a1UsTv_Oqc();

    private /* synthetic */ $$Lambda$_fmM_PUI85Mw3m3N2a1UsTv_Oqc() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((CodableLicense.CodablePackageFilter) obj).toPackageFilter();
    }
}
