package android.view;

import android.app.WindowConfigurationProto;
import android.graphics.PointProto;
import android.graphics.RectProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface RemoteAnimationTargetProtoOrBuilder extends MessageLiteOrBuilder {
    RectProto getClipRect();

    RectProto getContentInsets();

    boolean getIsTranslucent();

    SurfaceControlProto getLeash();

    int getMode();

    PointProto getPosition();

    int getPrefixOrderIndex();

    RectProto getSourceContainerBounds();

    RectProto getStartBounds();

    SurfaceControlProto getStartLeash();

    int getTaskId();

    WindowConfigurationProto getWindowConfiguration();

    boolean hasClipRect();

    boolean hasContentInsets();

    boolean hasIsTranslucent();

    boolean hasLeash();

    boolean hasMode();

    boolean hasPosition();

    boolean hasPrefixOrderIndex();

    boolean hasSourceContainerBounds();

    boolean hasStartBounds();

    boolean hasStartLeash();

    boolean hasTaskId();

    boolean hasWindowConfiguration();
}
