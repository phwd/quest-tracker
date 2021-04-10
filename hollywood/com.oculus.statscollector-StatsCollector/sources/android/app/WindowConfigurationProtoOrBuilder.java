package android.app;

import android.graphics.RectProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowConfigurationProtoOrBuilder extends MessageLiteOrBuilder {
    int getActivityType();

    RectProto getAppBounds();

    RectProto getBounds();

    int getWindowingMode();

    boolean hasActivityType();

    boolean hasAppBounds();

    boolean hasBounds();

    boolean hasWindowingMode();
}
