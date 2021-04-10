package android.os;

import android.os.IncidentHeaderProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface IncidentHeaderProtoOrBuilder extends MessageLiteOrBuilder {
    long getAlertId();

    IncidentHeaderProto.StatsdConfigKey getConfigKey();

    String getReason();

    ByteString getReasonBytes();

    ByteString getTriggerDetails();

    boolean hasAlertId();

    boolean hasConfigKey();

    boolean hasReason();

    boolean hasTriggerDetails();
}
