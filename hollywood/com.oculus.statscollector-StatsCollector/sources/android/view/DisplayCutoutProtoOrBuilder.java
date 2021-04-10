package android.view;

import android.graphics.RectProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface DisplayCutoutProtoOrBuilder extends MessageLiteOrBuilder {
    RectProto getBoundBottom();

    RectProto getBoundLeft();

    RectProto getBoundRight();

    RectProto getBoundTop();

    RectProto getInsets();

    boolean hasBoundBottom();

    boolean hasBoundLeft();

    boolean hasBoundRight();

    boolean hasBoundTop();

    boolean hasInsets();
}
