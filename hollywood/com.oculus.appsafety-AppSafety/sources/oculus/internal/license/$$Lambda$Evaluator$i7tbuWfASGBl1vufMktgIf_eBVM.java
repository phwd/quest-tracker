package oculus.internal.license;

import java.util.function.BinaryOperator;
import oculus.internal.license.Evaluator;

/* renamed from: oculus.internal.license.-$$Lambda$Evaluator$i7tbuWfASGBl1vufMktgIf_eBVM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$Evaluator$i7tbuWfASGBl1vufMktgIf_eBVM implements BinaryOperator {
    public static final /* synthetic */ $$Lambda$Evaluator$i7tbuWfASGBl1vufMktgIf_eBVM INSTANCE = new $$Lambda$Evaluator$i7tbuWfASGBl1vufMktgIf_eBVM();

    private /* synthetic */ $$Lambda$Evaluator$i7tbuWfASGBl1vufMktgIf_eBVM() {
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return Evaluator.reduceJudgements((Evaluator.RuleJudgement) obj, (Evaluator.RuleJudgement) obj2);
    }
}
