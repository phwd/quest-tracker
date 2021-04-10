package android.content;

import android.app.WindowConfigurationProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ConfigurationProtoOrBuilder extends MessageLiteOrBuilder {
    int getColorMode();

    int getDensityDpi();

    float getFontScale();

    int getHardKeyboardHidden();

    int getKeyboard();

    int getKeyboardHidden();

    String getLocaleList();

    ByteString getLocaleListBytes();

    @Deprecated
    LocaleProto getLocales(int i);

    @Deprecated
    int getLocalesCount();

    @Deprecated
    List<LocaleProto> getLocalesList();

    int getMcc();

    int getMnc();

    int getNavigation();

    int getNavigationHidden();

    int getOrientation();

    int getScreenHeightDp();

    int getScreenLayout();

    int getScreenWidthDp();

    int getSmallestScreenWidthDp();

    int getTouchscreen();

    int getUiMode();

    WindowConfigurationProto getWindowConfiguration();

    boolean hasColorMode();

    boolean hasDensityDpi();

    boolean hasFontScale();

    boolean hasHardKeyboardHidden();

    boolean hasKeyboard();

    boolean hasKeyboardHidden();

    boolean hasLocaleList();

    boolean hasMcc();

    boolean hasMnc();

    boolean hasNavigation();

    boolean hasNavigationHidden();

    boolean hasOrientation();

    boolean hasScreenHeightDp();

    boolean hasScreenLayout();

    boolean hasScreenWidthDp();

    boolean hasSmallestScreenWidthDp();

    boolean hasTouchscreen();

    boolean hasUiMode();

    boolean hasWindowConfiguration();
}
