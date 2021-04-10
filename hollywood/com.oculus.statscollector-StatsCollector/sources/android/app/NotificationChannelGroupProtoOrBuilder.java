package android.app;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NotificationChannelGroupProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getAllowAppOverlay();

    NotificationChannelProto getChannels(int i);

    int getChannelsCount();

    List<NotificationChannelProto> getChannelsList();

    String getDescription();

    ByteString getDescriptionBytes();

    String getId();

    ByteString getIdBytes();

    boolean getIsBlocked();

    String getName();

    ByteString getNameBytes();

    boolean hasAllowAppOverlay();

    boolean hasDescription();

    boolean hasId();

    boolean hasIsBlocked();

    boolean hasName();
}
