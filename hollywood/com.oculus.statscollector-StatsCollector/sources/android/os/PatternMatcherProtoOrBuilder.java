package android.os;

import android.os.PatternMatcherProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PatternMatcherProtoOrBuilder extends MessageLiteOrBuilder {
    String getPattern();

    ByteString getPatternBytes();

    PatternMatcherProto.Type getType();

    boolean hasPattern();

    boolean hasType();
}
