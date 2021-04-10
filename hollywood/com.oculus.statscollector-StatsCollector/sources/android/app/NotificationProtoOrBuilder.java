package android.app;

import android.app.NotificationProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface NotificationProtoOrBuilder extends MessageLiteOrBuilder {
    int getActionLength();

    String getCategory();

    ByteString getCategoryBytes();

    String getChannelId();

    ByteString getChannelIdBytes();

    int getColor();

    int getFlags();

    String getGroupKey();

    ByteString getGroupKeyBytes();

    boolean getHasTickerText();

    NotificationProto getPublicVersion();

    String getSortKey();

    ByteString getSortKeyBytes();

    NotificationProto.Visibility getVisibility();

    boolean hasActionLength();

    boolean hasCategory();

    boolean hasChannelId();

    boolean hasColor();

    boolean hasFlags();

    boolean hasGroupKey();

    boolean hasHasTickerText();

    boolean hasPublicVersion();

    boolean hasSortKey();

    boolean hasVisibility();
}
