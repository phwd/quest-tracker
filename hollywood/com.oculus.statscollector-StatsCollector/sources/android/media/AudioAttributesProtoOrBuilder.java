package android.media;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface AudioAttributesProtoOrBuilder extends MessageLiteOrBuilder {
    ContentType getContentType();

    int getFlags();

    String getTags(int i);

    ByteString getTagsBytes(int i);

    int getTagsCount();

    List<String> getTagsList();

    Usage getUsage();

    boolean hasContentType();

    boolean hasFlags();

    boolean hasUsage();
}
