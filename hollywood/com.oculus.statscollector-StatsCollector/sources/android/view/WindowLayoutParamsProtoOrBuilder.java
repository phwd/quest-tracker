package android.view;

import android.graphics.PixelFormatProto;
import android.view.DisplayProto;
import android.view.WindowLayoutParamsProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowLayoutParamsProtoOrBuilder extends MessageLiteOrBuilder {
    float getAlpha();

    float getButtonBrightness();

    DisplayProto.ColorMode getColorMode();

    int getFlags();

    PixelFormatProto.Format getFormat();

    int getGravity();

    boolean getHasSystemUiListeners();

    int getHeight();

    float getHorizontalMargin();

    int getInputFeatureFlags();

    WindowLayoutParamsProto.NeedsMenuState getNeedsMenuKey();

    int getPreferredDisplayModeId();

    float getPreferredRefreshRate();

    int getPrivateFlags();

    WindowLayoutParamsProto.RotationAnimation getRotationAnimation();

    float getScreenBrightness();

    int getSoftInputMode();

    int getSubtreeSystemUiVisibilityFlags();

    int getSystemUiVisibilityFlags();

    int getType();

    long getUserActivityTimeout();

    float getVerticalMargin();

    int getWidth();

    int getWindowAnimations();

    int getX();

    int getY();

    boolean hasAlpha();

    boolean hasButtonBrightness();

    boolean hasColorMode();

    boolean hasFlags();

    boolean hasFormat();

    boolean hasGravity();

    boolean hasHasSystemUiListeners();

    boolean hasHeight();

    boolean hasHorizontalMargin();

    boolean hasInputFeatureFlags();

    boolean hasNeedsMenuKey();

    boolean hasPreferredDisplayModeId();

    boolean hasPreferredRefreshRate();

    boolean hasPrivateFlags();

    boolean hasRotationAnimation();

    boolean hasScreenBrightness();

    boolean hasSoftInputMode();

    boolean hasSubtreeSystemUiVisibilityFlags();

    boolean hasSystemUiVisibilityFlags();

    boolean hasType();

    boolean hasUserActivityTimeout();

    boolean hasVerticalMargin();

    boolean hasWidth();

    boolean hasWindowAnimations();

    boolean hasX();

    boolean hasY();
}
