package android.util;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface EventLogTagMapProtoOrBuilder extends MessageLiteOrBuilder {
    EventLogTag getEventLogTags(int i);

    int getEventLogTagsCount();

    List<EventLogTag> getEventLogTagsList();
}
