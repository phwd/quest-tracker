package android.service.print;

import com.google.protobuf.MessageLiteOrBuilder;

public interface MarginsProtoOrBuilder extends MessageLiteOrBuilder {
    int getBottomMils();

    int getLeftMils();

    int getRightMils();

    int getTopMils();

    boolean hasBottomMils();

    boolean hasLeftMils();

    boolean hasRightMils();

    boolean hasTopMils();
}
