package android.util;

import android.util.EventLogTag;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface EventLogTagOrBuilder extends MessageLiteOrBuilder {
    String getTagName();

    ByteString getTagNameBytes();

    int getTagNumber();

    EventLogTag.ValueDescriptor getValueDescriptors(int i);

    int getValueDescriptorsCount();

    List<EventLogTag.ValueDescriptor> getValueDescriptorsList();

    boolean hasTagName();

    boolean hasTagNumber();
}
