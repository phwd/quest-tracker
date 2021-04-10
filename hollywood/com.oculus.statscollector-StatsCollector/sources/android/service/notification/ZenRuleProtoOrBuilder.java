package android.service.notification;

import android.content.ComponentNameProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ZenRuleProtoOrBuilder extends MessageLiteOrBuilder {
    ComponentNameProto getComponent();

    ConditionProto getCondition();

    String getConditionId();

    ByteString getConditionIdBytes();

    long getCreationTimeMs();

    boolean getEnabled();

    String getEnabler();

    ByteString getEnablerBytes();

    String getId();

    ByteString getIdBytes();

    boolean getIsSnoozing();

    boolean getModified();

    String getName();

    ByteString getNameBytes();

    ZenMode getZenMode();

    ZenPolicyProto getZenPolicy();

    boolean hasComponent();

    boolean hasCondition();

    boolean hasConditionId();

    boolean hasCreationTimeMs();

    boolean hasEnabled();

    boolean hasEnabler();

    boolean hasId();

    boolean hasIsSnoozing();

    boolean hasModified();

    boolean hasName();

    boolean hasZenMode();

    boolean hasZenPolicy();
}
