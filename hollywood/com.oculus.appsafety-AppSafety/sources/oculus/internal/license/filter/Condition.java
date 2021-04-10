package oculus.internal.license.filter;

import oculus.internal.license.EvaluationContext;

public interface Condition {
    boolean test(EvaluationContext evaluationContext);
}
