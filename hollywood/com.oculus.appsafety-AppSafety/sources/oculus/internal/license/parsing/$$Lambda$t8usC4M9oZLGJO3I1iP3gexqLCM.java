package oculus.internal.license.parsing;

import java.util.function.Function;
import oculus.internal.license.parsing.CodableLicense;

/* renamed from: oculus.internal.license.parsing.-$$Lambda$t8usC4M9oZLGJO3I1iP3gexqLCM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$t8usC4M9oZLGJO3I1iP3gexqLCM implements Function {
    public static final /* synthetic */ $$Lambda$t8usC4M9oZLGJO3I1iP3gexqLCM INSTANCE = new $$Lambda$t8usC4M9oZLGJO3I1iP3gexqLCM();

    private /* synthetic */ $$Lambda$t8usC4M9oZLGJO3I1iP3gexqLCM() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((CodableLicense.CodableRule) obj).toRule();
    }
}
