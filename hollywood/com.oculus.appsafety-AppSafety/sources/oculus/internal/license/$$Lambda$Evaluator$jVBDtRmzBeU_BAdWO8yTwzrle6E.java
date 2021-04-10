package oculus.internal.license;

import com.oculus.license.License;
import java.util.function.Function;

/* renamed from: oculus.internal.license.-$$Lambda$Evaluator$jVBDtRmzBeU_BAdWO8yTwzrle6E  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$Evaluator$jVBDtRmzBeU_BAdWO8yTwzrle6E implements Function {
    public static final /* synthetic */ $$Lambda$Evaluator$jVBDtRmzBeU_BAdWO8yTwzrle6E INSTANCE = new $$Lambda$Evaluator$jVBDtRmzBeU_BAdWO8yTwzrle6E();

    private /* synthetic */ $$Lambda$Evaluator$jVBDtRmzBeU_BAdWO8yTwzrle6E() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((License) obj).rules.stream();
    }
}
