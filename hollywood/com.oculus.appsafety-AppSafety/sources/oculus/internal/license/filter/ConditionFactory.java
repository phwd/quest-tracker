package oculus.internal.license.filter;

import com.oculus.license.FilterClass;
import com.oculus.license.FilterConfig;
import java.util.ArrayList;

public final class ConditionFactory {
    public Condition[] fromFilterConfigs(FilterConfig[] filterConfigs) throws UnrecognizedConditionException, InvalidConfigurationException {
        ArrayList<Condition> configs = new ArrayList<>();
        for (FilterConfig conditionConfig : filterConfigs) {
            int i = AnonymousClass1.$SwitchMap$com$oculus$license$FilterClass[conditionConfig.filterClass.ordinal()];
            if (i == 1) {
                configs.add(SecurityPrincipalFilter.create(conditionConfig.configuration));
            } else if (i == 2) {
                configs.add(SystemAttributeFilter.create(conditionConfig.configuration));
            } else {
                throw new UnrecognizedConditionException("Unrecognized Rule condition filter class.");
            }
        }
        return (Condition[]) configs.toArray(new Condition[0]);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: oculus.internal.license.filter.ConditionFactory$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$license$FilterClass = new int[FilterClass.values().length];

        static {
            try {
                $SwitchMap$com$oculus$license$FilterClass[FilterClass.SecurityPrincipalFilter.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$license$FilterClass[FilterClass.SystemAttributeFilter.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}
