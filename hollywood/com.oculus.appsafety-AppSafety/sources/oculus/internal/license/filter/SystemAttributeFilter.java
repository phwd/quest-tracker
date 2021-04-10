package oculus.internal.license.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oculus.internal.license.EvaluationContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SystemAttributeFilter implements Condition {
    private final LogicalTest logicalEvaluation;
    private final HashSet<String> requestedAttributes;

    public SystemAttributeFilter(List<String> requestedAttributes2, LogicalTest logicalEvaluation2) {
        this.requestedAttributes = new HashSet<>(requestedAttributes2);
        this.logicalEvaluation = logicalEvaluation2;
    }

    public static SystemAttributeFilter create(JSONObject jsonObject) throws InvalidConfigurationException {
        Configuration config = Configuration.fromJson(jsonObject);
        return new SystemAttributeFilter(config.systemAttributes, config.logicalTest);
    }

    @Override // oculus.internal.license.filter.Condition
    public boolean test(EvaluationContext evaluationContext) {
        Set<String> queryableSystemAttributes = Collections.unmodifiableSet(evaluationContext.getSystemAttributes());
        int i = AnonymousClass1.$SwitchMap$oculus$internal$license$filter$LogicalTest[this.logicalEvaluation.ordinal()];
        if (i == 1) {
            return queryableSystemAttributes.containsAll(this.requestedAttributes);
        }
        if (i == 2) {
            HashSet<String> anyIntersections = new HashSet<>(queryableSystemAttributes);
            anyIntersections.retainAll(this.requestedAttributes);
            return true ^ anyIntersections.isEmpty();
        } else if (i != 3) {
            return false;
        } else {
            HashSet<String> noneIntersection = new HashSet<>(queryableSystemAttributes);
            noneIntersection.retainAll(this.requestedAttributes);
            return noneIntersection.isEmpty();
        }
    }

    /* renamed from: oculus.internal.license.filter.SystemAttributeFilter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$oculus$internal$license$filter$LogicalTest = new int[LogicalTest.values().length];

        static {
            try {
                $SwitchMap$oculus$internal$license$filter$LogicalTest[LogicalTest.all.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$oculus$internal$license$filter$LogicalTest[LogicalTest.any.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$oculus$internal$license$filter$LogicalTest[LogicalTest.none.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class Configuration {
        final LogicalTest logicalTest;
        final List<String> systemAttributes;

        Configuration(LogicalTest logicalTest2, List<String> systemAttributes2) {
            this.logicalTest = logicalTest2;
            this.systemAttributes = systemAttributes2;
        }

        static Configuration fromJson(JSONObject obj) throws InvalidConfigurationException {
            try {
                JSONArray attrs = obj.getJSONArray("system_attributes");
                List<String> attrStrings = new ArrayList<>();
                for (int i = 0; i < attrs.length(); i++) {
                    attrStrings.add(attrs.getString(i));
                }
                return new Configuration(LogicalTest.fromString(obj.getString("logical_test")), attrStrings);
            } catch (IllegalArgumentException | JSONException e) {
                throw new InvalidConfigurationException(e);
            }
        }
    }
}
