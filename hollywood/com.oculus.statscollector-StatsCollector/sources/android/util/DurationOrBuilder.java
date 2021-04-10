package android.util;

import com.google.protobuf.MessageLiteOrBuilder;

public interface DurationOrBuilder extends MessageLiteOrBuilder {
    long getEndMs();

    long getStartMs();

    boolean hasEndMs();

    boolean hasStartMs();
}
