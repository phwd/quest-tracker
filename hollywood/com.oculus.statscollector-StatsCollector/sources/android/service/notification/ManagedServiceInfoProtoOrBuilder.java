package android.service.notification;

import android.content.ComponentNameProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ManagedServiceInfoProtoOrBuilder extends MessageLiteOrBuilder {
    ComponentNameProto getComponent();

    boolean getIsGuest();

    boolean getIsSystem();

    String getService();

    ByteString getServiceBytes();

    int getUserId();

    boolean hasComponent();

    boolean hasIsGuest();

    boolean hasIsSystem();

    boolean hasService();

    boolean hasUserId();
}
