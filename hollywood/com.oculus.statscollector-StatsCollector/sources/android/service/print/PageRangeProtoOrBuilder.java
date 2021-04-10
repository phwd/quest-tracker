package android.service.print;

import com.google.protobuf.MessageLiteOrBuilder;

public interface PageRangeProtoOrBuilder extends MessageLiteOrBuilder {
    int getEnd();

    int getStart();

    boolean hasEnd();

    boolean hasStart();
}
