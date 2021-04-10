package android.content;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface AuthorityEntryProtoOrBuilder extends MessageLiteOrBuilder {
    String getHost();

    ByteString getHostBytes();

    int getPort();

    boolean getWild();

    boolean hasHost();

    boolean hasPort();

    boolean hasWild();
}
