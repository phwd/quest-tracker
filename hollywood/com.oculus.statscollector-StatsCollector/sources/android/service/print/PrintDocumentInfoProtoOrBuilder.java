package android.service.print;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PrintDocumentInfoProtoOrBuilder extends MessageLiteOrBuilder {
    int getContentType();

    long getDataSize();

    String getName();

    ByteString getNameBytes();

    int getPageCount();

    boolean hasContentType();

    boolean hasDataSize();

    boolean hasName();

    boolean hasPageCount();
}
