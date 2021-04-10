package android.service.notification;

import android.app.PolicyProto;
import android.content.ComponentNameProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ZenModeProtoOrBuilder extends MessageLiteOrBuilder {
    ZenRuleProto getEnabledActiveConditions(int i);

    int getEnabledActiveConditionsCount();

    List<ZenRuleProto> getEnabledActiveConditionsList();

    PolicyProto getPolicy();

    int getSuppressedEffects();

    ComponentNameProto getSuppressors(int i);

    int getSuppressorsCount();

    List<ComponentNameProto> getSuppressorsList();

    ZenMode getZenMode();

    boolean hasPolicy();

    boolean hasSuppressedEffects();

    boolean hasZenMode();
}
