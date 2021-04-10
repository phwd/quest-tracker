package android.service.notification;

import android.service.notification.ConditionProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ConditionProtoOrBuilder extends MessageLiteOrBuilder {
    int getFlags();

    int getIcon();

    String getId();

    ByteString getIdBytes();

    String getLine1();

    ByteString getLine1Bytes();

    String getLine2();

    ByteString getLine2Bytes();

    ConditionProto.State getState();

    String getSummary();

    ByteString getSummaryBytes();

    boolean hasFlags();

    boolean hasIcon();

    boolean hasId();

    boolean hasLine1();

    boolean hasLine2();

    boolean hasState();

    boolean hasSummary();
}
