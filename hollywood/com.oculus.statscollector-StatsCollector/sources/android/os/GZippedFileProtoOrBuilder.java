package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface GZippedFileProtoOrBuilder extends MessageLiteOrBuilder {
    String getFilename();

    ByteString getFilenameBytes();

    ByteString getGzippedData();

    boolean hasFilename();

    boolean hasGzippedData();
}
