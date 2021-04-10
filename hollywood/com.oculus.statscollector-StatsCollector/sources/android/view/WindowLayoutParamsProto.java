package android.view;

import android.graphics.PixelFormatProto;
import android.view.DisplayProto;
import com.android.os.AtomsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WindowLayoutParamsProto extends GeneratedMessageLite<WindowLayoutParamsProto, Builder> implements WindowLayoutParamsProtoOrBuilder {
    public static final int ALPHA_FIELD_NUMBER = 12;
    public static final int BUTTON_BRIGHTNESS_FIELD_NUMBER = 14;
    public static final int COLOR_MODE_FIELD_NUMBER = 23;
    private static final WindowLayoutParamsProto DEFAULT_INSTANCE = new WindowLayoutParamsProto();
    public static final int FLAGS_FIELD_NUMBER = 24;
    public static final int FORMAT_FIELD_NUMBER = 10;
    public static final int GRAVITY_FIELD_NUMBER = 8;
    public static final int HAS_SYSTEM_UI_LISTENERS_FIELD_NUMBER = 18;
    public static final int HEIGHT_FIELD_NUMBER = 5;
    public static final int HORIZONTAL_MARGIN_FIELD_NUMBER = 6;
    public static final int INPUT_FEATURE_FLAGS_FIELD_NUMBER = 19;
    public static final int NEEDS_MENU_KEY_FIELD_NUMBER = 22;
    private static volatile Parser<WindowLayoutParamsProto> PARSER = null;
    public static final int PREFERRED_DISPLAY_MODE_ID_FIELD_NUMBER = 17;
    public static final int PREFERRED_REFRESH_RATE_FIELD_NUMBER = 16;
    public static final int PRIVATE_FLAGS_FIELD_NUMBER = 26;
    public static final int ROTATION_ANIMATION_FIELD_NUMBER = 15;
    public static final int SCREEN_BRIGHTNESS_FIELD_NUMBER = 13;
    public static final int SOFT_INPUT_MODE_FIELD_NUMBER = 9;
    public static final int SUBTREE_SYSTEM_UI_VISIBILITY_FLAGS_FIELD_NUMBER = 28;
    public static final int SYSTEM_UI_VISIBILITY_FLAGS_FIELD_NUMBER = 27;
    public static final int TYPE_FIELD_NUMBER = 1;
    public static final int USER_ACTIVITY_TIMEOUT_FIELD_NUMBER = 20;
    public static final int VERTICAL_MARGIN_FIELD_NUMBER = 7;
    public static final int WIDTH_FIELD_NUMBER = 4;
    public static final int WINDOW_ANIMATIONS_FIELD_NUMBER = 11;
    public static final int X_FIELD_NUMBER = 2;
    public static final int Y_FIELD_NUMBER = 3;
    private float alpha_ = 0.0f;
    private int bitField0_;
    private float buttonBrightness_ = 0.0f;
    private int colorMode_ = -1;
    private int flags_ = 0;
    private int format_ = 0;
    private int gravity_ = 0;
    private boolean hasSystemUiListeners_ = false;
    private int height_ = 0;
    private float horizontalMargin_ = 0.0f;
    private int inputFeatureFlags_ = 0;
    private int needsMenuKey_ = 0;
    private int preferredDisplayModeId_ = 0;
    private float preferredRefreshRate_ = 0.0f;
    private int privateFlags_ = 0;
    private int rotationAnimation_ = -1;
    private float screenBrightness_ = 0.0f;
    private int softInputMode_ = 0;
    private int subtreeSystemUiVisibilityFlags_ = 0;
    private int systemUiVisibilityFlags_ = 0;
    private int type_ = 0;
    private long userActivityTimeout_ = 0;
    private float verticalMargin_ = 0.0f;
    private int width_ = 0;
    private int windowAnimations_ = 0;
    private int x_ = 0;
    private int y_ = 0;

    private WindowLayoutParamsProto() {
    }

    public enum RotationAnimation implements Internal.EnumLite {
        ROTATION_ANIMATION_UNSPECIFIED(-1),
        ROTATION_ANIMATION_CROSSFADE(1),
        ROTATION_ANIMATION_JUMPCUT(2),
        ROTATION_ANIMATION_SEAMLESS(3);
        
        public static final int ROTATION_ANIMATION_CROSSFADE_VALUE = 1;
        public static final int ROTATION_ANIMATION_JUMPCUT_VALUE = 2;
        public static final int ROTATION_ANIMATION_SEAMLESS_VALUE = 3;
        public static final int ROTATION_ANIMATION_UNSPECIFIED_VALUE = -1;
        private static final Internal.EnumLiteMap<RotationAnimation> internalValueMap = new Internal.EnumLiteMap<RotationAnimation>() {
            /* class android.view.WindowLayoutParamsProto.RotationAnimation.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public RotationAnimation findValueByNumber(int number) {
                return RotationAnimation.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static RotationAnimation valueOf(int value2) {
            return forNumber(value2);
        }

        public static RotationAnimation forNumber(int value2) {
            if (value2 == -1) {
                return ROTATION_ANIMATION_UNSPECIFIED;
            }
            if (value2 == 1) {
                return ROTATION_ANIMATION_CROSSFADE;
            }
            if (value2 == 2) {
                return ROTATION_ANIMATION_JUMPCUT;
            }
            if (value2 != 3) {
                return null;
            }
            return ROTATION_ANIMATION_SEAMLESS;
        }

        public static Internal.EnumLiteMap<RotationAnimation> internalGetValueMap() {
            return internalValueMap;
        }

        private RotationAnimation(int value2) {
            this.value = value2;
        }
    }

    public enum NeedsMenuState implements Internal.EnumLite {
        NEEDS_MENU_UNSET(0),
        NEEDS_MENU_SET_TRUE(1),
        NEEDS_MENU_SET_FALSE(2);
        
        public static final int NEEDS_MENU_SET_FALSE_VALUE = 2;
        public static final int NEEDS_MENU_SET_TRUE_VALUE = 1;
        public static final int NEEDS_MENU_UNSET_VALUE = 0;
        private static final Internal.EnumLiteMap<NeedsMenuState> internalValueMap = new Internal.EnumLiteMap<NeedsMenuState>() {
            /* class android.view.WindowLayoutParamsProto.NeedsMenuState.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NeedsMenuState findValueByNumber(int number) {
                return NeedsMenuState.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static NeedsMenuState valueOf(int value2) {
            return forNumber(value2);
        }

        public static NeedsMenuState forNumber(int value2) {
            if (value2 == 0) {
                return NEEDS_MENU_UNSET;
            }
            if (value2 == 1) {
                return NEEDS_MENU_SET_TRUE;
            }
            if (value2 != 2) {
                return null;
            }
            return NEEDS_MENU_SET_FALSE;
        }

        public static Internal.EnumLiteMap<NeedsMenuState> internalGetValueMap() {
            return internalValueMap;
        }

        private NeedsMenuState(int value2) {
            this.value = value2;
        }
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasType() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getType() {
        return this.type_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(int value) {
        this.bitField0_ |= 1;
        this.type_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearType() {
        this.bitField0_ &= -2;
        this.type_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasX() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getX() {
        return this.x_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setX(int value) {
        this.bitField0_ |= 2;
        this.x_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearX() {
        this.bitField0_ &= -3;
        this.x_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasY() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getY() {
        return this.y_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setY(int value) {
        this.bitField0_ |= 4;
        this.y_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearY() {
        this.bitField0_ &= -5;
        this.y_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasWidth() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getWidth() {
        return this.width_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWidth(int value) {
        this.bitField0_ |= 8;
        this.width_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWidth() {
        this.bitField0_ &= -9;
        this.width_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasHeight() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getHeight() {
        return this.height_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHeight(int value) {
        this.bitField0_ |= 16;
        this.height_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHeight() {
        this.bitField0_ &= -17;
        this.height_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasHorizontalMargin() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public float getHorizontalMargin() {
        return this.horizontalMargin_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHorizontalMargin(float value) {
        this.bitField0_ |= 32;
        this.horizontalMargin_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHorizontalMargin() {
        this.bitField0_ &= -33;
        this.horizontalMargin_ = 0.0f;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasVerticalMargin() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public float getVerticalMargin() {
        return this.verticalMargin_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVerticalMargin(float value) {
        this.bitField0_ |= 64;
        this.verticalMargin_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVerticalMargin() {
        this.bitField0_ &= -65;
        this.verticalMargin_ = 0.0f;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasGravity() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getGravity() {
        return this.gravity_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGravity(int value) {
        this.bitField0_ |= 128;
        this.gravity_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGravity() {
        this.bitField0_ &= -129;
        this.gravity_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasSoftInputMode() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getSoftInputMode() {
        return this.softInputMode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSoftInputMode(int value) {
        this.bitField0_ |= 256;
        this.softInputMode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSoftInputMode() {
        this.bitField0_ &= -257;
        this.softInputMode_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasFormat() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public PixelFormatProto.Format getFormat() {
        PixelFormatProto.Format result = PixelFormatProto.Format.forNumber(this.format_);
        return result == null ? PixelFormatProto.Format.UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFormat(PixelFormatProto.Format value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.format_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFormat() {
        this.bitField0_ &= -513;
        this.format_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasWindowAnimations() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getWindowAnimations() {
        return this.windowAnimations_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowAnimations(int value) {
        this.bitField0_ |= 1024;
        this.windowAnimations_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowAnimations() {
        this.bitField0_ &= -1025;
        this.windowAnimations_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasAlpha() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public float getAlpha() {
        return this.alpha_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlpha(float value) {
        this.bitField0_ |= 2048;
        this.alpha_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlpha() {
        this.bitField0_ &= -2049;
        this.alpha_ = 0.0f;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasScreenBrightness() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public float getScreenBrightness() {
        return this.screenBrightness_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenBrightness(float value) {
        this.bitField0_ |= 4096;
        this.screenBrightness_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenBrightness() {
        this.bitField0_ &= -4097;
        this.screenBrightness_ = 0.0f;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasButtonBrightness() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public float getButtonBrightness() {
        return this.buttonBrightness_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setButtonBrightness(float value) {
        this.bitField0_ |= 8192;
        this.buttonBrightness_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearButtonBrightness() {
        this.bitField0_ &= -8193;
        this.buttonBrightness_ = 0.0f;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasRotationAnimation() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public RotationAnimation getRotationAnimation() {
        RotationAnimation result = RotationAnimation.forNumber(this.rotationAnimation_);
        return result == null ? RotationAnimation.ROTATION_ANIMATION_UNSPECIFIED : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRotationAnimation(RotationAnimation value) {
        if (value != null) {
            this.bitField0_ |= 16384;
            this.rotationAnimation_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRotationAnimation() {
        this.bitField0_ &= -16385;
        this.rotationAnimation_ = -1;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasPreferredRefreshRate() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public float getPreferredRefreshRate() {
        return this.preferredRefreshRate_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPreferredRefreshRate(float value) {
        this.bitField0_ |= 32768;
        this.preferredRefreshRate_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPreferredRefreshRate() {
        this.bitField0_ &= -32769;
        this.preferredRefreshRate_ = 0.0f;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasPreferredDisplayModeId() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getPreferredDisplayModeId() {
        return this.preferredDisplayModeId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPreferredDisplayModeId(int value) {
        this.bitField0_ |= 65536;
        this.preferredDisplayModeId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPreferredDisplayModeId() {
        this.bitField0_ &= -65537;
        this.preferredDisplayModeId_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasHasSystemUiListeners() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean getHasSystemUiListeners() {
        return this.hasSystemUiListeners_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasSystemUiListeners(boolean value) {
        this.bitField0_ |= 131072;
        this.hasSystemUiListeners_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasSystemUiListeners() {
        this.bitField0_ &= -131073;
        this.hasSystemUiListeners_ = false;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasInputFeatureFlags() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getInputFeatureFlags() {
        return this.inputFeatureFlags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInputFeatureFlags(int value) {
        this.bitField0_ |= 262144;
        this.inputFeatureFlags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInputFeatureFlags() {
        this.bitField0_ &= -262145;
        this.inputFeatureFlags_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasUserActivityTimeout() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public long getUserActivityTimeout() {
        return this.userActivityTimeout_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserActivityTimeout(long value) {
        this.bitField0_ |= 524288;
        this.userActivityTimeout_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserActivityTimeout() {
        this.bitField0_ &= -524289;
        this.userActivityTimeout_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasNeedsMenuKey() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public NeedsMenuState getNeedsMenuKey() {
        NeedsMenuState result = NeedsMenuState.forNumber(this.needsMenuKey_);
        return result == null ? NeedsMenuState.NEEDS_MENU_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNeedsMenuKey(NeedsMenuState value) {
        if (value != null) {
            this.bitField0_ |= 1048576;
            this.needsMenuKey_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNeedsMenuKey() {
        this.bitField0_ &= -1048577;
        this.needsMenuKey_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasColorMode() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public DisplayProto.ColorMode getColorMode() {
        DisplayProto.ColorMode result = DisplayProto.ColorMode.forNumber(this.colorMode_);
        return result == null ? DisplayProto.ColorMode.COLOR_MODE_INVALID : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setColorMode(DisplayProto.ColorMode value) {
        if (value != null) {
            this.bitField0_ |= 2097152;
            this.colorMode_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearColorMode() {
        this.bitField0_ &= -2097153;
        this.colorMode_ = -1;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 4194304;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -4194305;
        this.flags_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasPrivateFlags() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getPrivateFlags() {
        return this.privateFlags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrivateFlags(int value) {
        this.bitField0_ |= 8388608;
        this.privateFlags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrivateFlags() {
        this.bitField0_ &= -8388609;
        this.privateFlags_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasSystemUiVisibilityFlags() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getSystemUiVisibilityFlags() {
        return this.systemUiVisibilityFlags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemUiVisibilityFlags(int value) {
        this.bitField0_ |= 16777216;
        this.systemUiVisibilityFlags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemUiVisibilityFlags() {
        this.bitField0_ &= -16777217;
        this.systemUiVisibilityFlags_ = 0;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public boolean hasSubtreeSystemUiVisibilityFlags() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // android.view.WindowLayoutParamsProtoOrBuilder
    public int getSubtreeSystemUiVisibilityFlags() {
        return this.subtreeSystemUiVisibilityFlags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSubtreeSystemUiVisibilityFlags(int value) {
        this.bitField0_ |= 33554432;
        this.subtreeSystemUiVisibilityFlags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSubtreeSystemUiVisibilityFlags() {
        this.bitField0_ &= -33554433;
        this.subtreeSystemUiVisibilityFlags_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.x_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.y_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.width_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.height_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeFloat(6, this.horizontalMargin_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeFloat(7, this.verticalMargin_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.gravity_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeInt32(9, this.softInputMode_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeEnum(10, this.format_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeInt32(11, this.windowAnimations_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeFloat(12, this.alpha_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeFloat(13, this.screenBrightness_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeFloat(14, this.buttonBrightness_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeEnum(15, this.rotationAnimation_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeFloat(16, this.preferredRefreshRate_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeInt32(17, this.preferredDisplayModeId_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeBool(18, this.hasSystemUiListeners_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeUInt32(19, this.inputFeatureFlags_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeInt64(20, this.userActivityTimeout_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeEnum(22, this.needsMenuKey_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeEnum(23, this.colorMode_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeUInt32(24, this.flags_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeUInt32(26, this.privateFlags_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeUInt32(27, this.systemUiVisibilityFlags_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeUInt32(28, this.subtreeSystemUiVisibilityFlags_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.x_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.y_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.width_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.height_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeFloatSize(6, this.horizontalMargin_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeFloatSize(7, this.verticalMargin_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.gravity_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeInt32Size(9, this.softInputMode_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeEnumSize(10, this.format_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeInt32Size(11, this.windowAnimations_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeFloatSize(12, this.alpha_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeFloatSize(13, this.screenBrightness_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeFloatSize(14, this.buttonBrightness_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeEnumSize(15, this.rotationAnimation_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeFloatSize(16, this.preferredRefreshRate_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeInt32Size(17, this.preferredDisplayModeId_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeBoolSize(18, this.hasSystemUiListeners_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeUInt32Size(19, this.inputFeatureFlags_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeInt64Size(20, this.userActivityTimeout_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size2 += CodedOutputStream.computeEnumSize(22, this.needsMenuKey_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size2 += CodedOutputStream.computeEnumSize(23, this.colorMode_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size2 += CodedOutputStream.computeUInt32Size(24, this.flags_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size2 += CodedOutputStream.computeUInt32Size(26, this.privateFlags_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size2 += CodedOutputStream.computeUInt32Size(27, this.systemUiVisibilityFlags_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size2 += CodedOutputStream.computeUInt32Size(28, this.subtreeSystemUiVisibilityFlags_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowLayoutParamsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowLayoutParamsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowLayoutParamsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowLayoutParamsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowLayoutParamsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowLayoutParamsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowLayoutParamsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowLayoutParamsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowLayoutParamsProto parseFrom(InputStream input) throws IOException {
        return (WindowLayoutParamsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowLayoutParamsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowLayoutParamsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowLayoutParamsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowLayoutParamsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowLayoutParamsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowLayoutParamsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowLayoutParamsProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowLayoutParamsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowLayoutParamsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowLayoutParamsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowLayoutParamsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowLayoutParamsProto, Builder> implements WindowLayoutParamsProtoOrBuilder {
        private Builder() {
            super(WindowLayoutParamsProto.DEFAULT_INSTANCE);
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasType() {
            return ((WindowLayoutParamsProto) this.instance).hasType();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getType() {
            return ((WindowLayoutParamsProto) this.instance).getType();
        }

        public Builder setType(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearType();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasX() {
            return ((WindowLayoutParamsProto) this.instance).hasX();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getX() {
            return ((WindowLayoutParamsProto) this.instance).getX();
        }

        public Builder setX(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setX(value);
            return this;
        }

        public Builder clearX() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearX();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasY() {
            return ((WindowLayoutParamsProto) this.instance).hasY();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getY() {
            return ((WindowLayoutParamsProto) this.instance).getY();
        }

        public Builder setY(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setY(value);
            return this;
        }

        public Builder clearY() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearY();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasWidth() {
            return ((WindowLayoutParamsProto) this.instance).hasWidth();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getWidth() {
            return ((WindowLayoutParamsProto) this.instance).getWidth();
        }

        public Builder setWidth(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setWidth(value);
            return this;
        }

        public Builder clearWidth() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearWidth();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasHeight() {
            return ((WindowLayoutParamsProto) this.instance).hasHeight();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getHeight() {
            return ((WindowLayoutParamsProto) this.instance).getHeight();
        }

        public Builder setHeight(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setHeight(value);
            return this;
        }

        public Builder clearHeight() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearHeight();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasHorizontalMargin() {
            return ((WindowLayoutParamsProto) this.instance).hasHorizontalMargin();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public float getHorizontalMargin() {
            return ((WindowLayoutParamsProto) this.instance).getHorizontalMargin();
        }

        public Builder setHorizontalMargin(float value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setHorizontalMargin(value);
            return this;
        }

        public Builder clearHorizontalMargin() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearHorizontalMargin();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasVerticalMargin() {
            return ((WindowLayoutParamsProto) this.instance).hasVerticalMargin();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public float getVerticalMargin() {
            return ((WindowLayoutParamsProto) this.instance).getVerticalMargin();
        }

        public Builder setVerticalMargin(float value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setVerticalMargin(value);
            return this;
        }

        public Builder clearVerticalMargin() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearVerticalMargin();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasGravity() {
            return ((WindowLayoutParamsProto) this.instance).hasGravity();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getGravity() {
            return ((WindowLayoutParamsProto) this.instance).getGravity();
        }

        public Builder setGravity(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setGravity(value);
            return this;
        }

        public Builder clearGravity() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearGravity();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasSoftInputMode() {
            return ((WindowLayoutParamsProto) this.instance).hasSoftInputMode();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getSoftInputMode() {
            return ((WindowLayoutParamsProto) this.instance).getSoftInputMode();
        }

        public Builder setSoftInputMode(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setSoftInputMode(value);
            return this;
        }

        public Builder clearSoftInputMode() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearSoftInputMode();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasFormat() {
            return ((WindowLayoutParamsProto) this.instance).hasFormat();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public PixelFormatProto.Format getFormat() {
            return ((WindowLayoutParamsProto) this.instance).getFormat();
        }

        public Builder setFormat(PixelFormatProto.Format value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setFormat(value);
            return this;
        }

        public Builder clearFormat() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearFormat();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasWindowAnimations() {
            return ((WindowLayoutParamsProto) this.instance).hasWindowAnimations();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getWindowAnimations() {
            return ((WindowLayoutParamsProto) this.instance).getWindowAnimations();
        }

        public Builder setWindowAnimations(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setWindowAnimations(value);
            return this;
        }

        public Builder clearWindowAnimations() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearWindowAnimations();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasAlpha() {
            return ((WindowLayoutParamsProto) this.instance).hasAlpha();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public float getAlpha() {
            return ((WindowLayoutParamsProto) this.instance).getAlpha();
        }

        public Builder setAlpha(float value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setAlpha(value);
            return this;
        }

        public Builder clearAlpha() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearAlpha();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasScreenBrightness() {
            return ((WindowLayoutParamsProto) this.instance).hasScreenBrightness();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public float getScreenBrightness() {
            return ((WindowLayoutParamsProto) this.instance).getScreenBrightness();
        }

        public Builder setScreenBrightness(float value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setScreenBrightness(value);
            return this;
        }

        public Builder clearScreenBrightness() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearScreenBrightness();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasButtonBrightness() {
            return ((WindowLayoutParamsProto) this.instance).hasButtonBrightness();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public float getButtonBrightness() {
            return ((WindowLayoutParamsProto) this.instance).getButtonBrightness();
        }

        public Builder setButtonBrightness(float value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setButtonBrightness(value);
            return this;
        }

        public Builder clearButtonBrightness() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearButtonBrightness();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasRotationAnimation() {
            return ((WindowLayoutParamsProto) this.instance).hasRotationAnimation();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public RotationAnimation getRotationAnimation() {
            return ((WindowLayoutParamsProto) this.instance).getRotationAnimation();
        }

        public Builder setRotationAnimation(RotationAnimation value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setRotationAnimation(value);
            return this;
        }

        public Builder clearRotationAnimation() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearRotationAnimation();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasPreferredRefreshRate() {
            return ((WindowLayoutParamsProto) this.instance).hasPreferredRefreshRate();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public float getPreferredRefreshRate() {
            return ((WindowLayoutParamsProto) this.instance).getPreferredRefreshRate();
        }

        public Builder setPreferredRefreshRate(float value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setPreferredRefreshRate(value);
            return this;
        }

        public Builder clearPreferredRefreshRate() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearPreferredRefreshRate();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasPreferredDisplayModeId() {
            return ((WindowLayoutParamsProto) this.instance).hasPreferredDisplayModeId();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getPreferredDisplayModeId() {
            return ((WindowLayoutParamsProto) this.instance).getPreferredDisplayModeId();
        }

        public Builder setPreferredDisplayModeId(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setPreferredDisplayModeId(value);
            return this;
        }

        public Builder clearPreferredDisplayModeId() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearPreferredDisplayModeId();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasHasSystemUiListeners() {
            return ((WindowLayoutParamsProto) this.instance).hasHasSystemUiListeners();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean getHasSystemUiListeners() {
            return ((WindowLayoutParamsProto) this.instance).getHasSystemUiListeners();
        }

        public Builder setHasSystemUiListeners(boolean value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setHasSystemUiListeners(value);
            return this;
        }

        public Builder clearHasSystemUiListeners() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearHasSystemUiListeners();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasInputFeatureFlags() {
            return ((WindowLayoutParamsProto) this.instance).hasInputFeatureFlags();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getInputFeatureFlags() {
            return ((WindowLayoutParamsProto) this.instance).getInputFeatureFlags();
        }

        public Builder setInputFeatureFlags(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setInputFeatureFlags(value);
            return this;
        }

        public Builder clearInputFeatureFlags() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearInputFeatureFlags();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasUserActivityTimeout() {
            return ((WindowLayoutParamsProto) this.instance).hasUserActivityTimeout();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public long getUserActivityTimeout() {
            return ((WindowLayoutParamsProto) this.instance).getUserActivityTimeout();
        }

        public Builder setUserActivityTimeout(long value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setUserActivityTimeout(value);
            return this;
        }

        public Builder clearUserActivityTimeout() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearUserActivityTimeout();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasNeedsMenuKey() {
            return ((WindowLayoutParamsProto) this.instance).hasNeedsMenuKey();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public NeedsMenuState getNeedsMenuKey() {
            return ((WindowLayoutParamsProto) this.instance).getNeedsMenuKey();
        }

        public Builder setNeedsMenuKey(NeedsMenuState value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setNeedsMenuKey(value);
            return this;
        }

        public Builder clearNeedsMenuKey() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearNeedsMenuKey();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasColorMode() {
            return ((WindowLayoutParamsProto) this.instance).hasColorMode();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public DisplayProto.ColorMode getColorMode() {
            return ((WindowLayoutParamsProto) this.instance).getColorMode();
        }

        public Builder setColorMode(DisplayProto.ColorMode value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setColorMode(value);
            return this;
        }

        public Builder clearColorMode() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearColorMode();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasFlags() {
            return ((WindowLayoutParamsProto) this.instance).hasFlags();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getFlags() {
            return ((WindowLayoutParamsProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearFlags();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasPrivateFlags() {
            return ((WindowLayoutParamsProto) this.instance).hasPrivateFlags();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getPrivateFlags() {
            return ((WindowLayoutParamsProto) this.instance).getPrivateFlags();
        }

        public Builder setPrivateFlags(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setPrivateFlags(value);
            return this;
        }

        public Builder clearPrivateFlags() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearPrivateFlags();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasSystemUiVisibilityFlags() {
            return ((WindowLayoutParamsProto) this.instance).hasSystemUiVisibilityFlags();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getSystemUiVisibilityFlags() {
            return ((WindowLayoutParamsProto) this.instance).getSystemUiVisibilityFlags();
        }

        public Builder setSystemUiVisibilityFlags(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setSystemUiVisibilityFlags(value);
            return this;
        }

        public Builder clearSystemUiVisibilityFlags() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearSystemUiVisibilityFlags();
            return this;
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public boolean hasSubtreeSystemUiVisibilityFlags() {
            return ((WindowLayoutParamsProto) this.instance).hasSubtreeSystemUiVisibilityFlags();
        }

        @Override // android.view.WindowLayoutParamsProtoOrBuilder
        public int getSubtreeSystemUiVisibilityFlags() {
            return ((WindowLayoutParamsProto) this.instance).getSubtreeSystemUiVisibilityFlags();
        }

        public Builder setSubtreeSystemUiVisibilityFlags(int value) {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).setSubtreeSystemUiVisibilityFlags(value);
            return this;
        }

        public Builder clearSubtreeSystemUiVisibilityFlags() {
            copyOnWrite();
            ((WindowLayoutParamsProto) this.instance).clearSubtreeSystemUiVisibilityFlags();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowLayoutParamsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowLayoutParamsProto other = (WindowLayoutParamsProto) arg1;
                this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                this.x_ = visitor.visitInt(hasX(), this.x_, other.hasX(), other.x_);
                this.y_ = visitor.visitInt(hasY(), this.y_, other.hasY(), other.y_);
                this.width_ = visitor.visitInt(hasWidth(), this.width_, other.hasWidth(), other.width_);
                this.height_ = visitor.visitInt(hasHeight(), this.height_, other.hasHeight(), other.height_);
                this.horizontalMargin_ = visitor.visitFloat(hasHorizontalMargin(), this.horizontalMargin_, other.hasHorizontalMargin(), other.horizontalMargin_);
                this.verticalMargin_ = visitor.visitFloat(hasVerticalMargin(), this.verticalMargin_, other.hasVerticalMargin(), other.verticalMargin_);
                this.gravity_ = visitor.visitInt(hasGravity(), this.gravity_, other.hasGravity(), other.gravity_);
                this.softInputMode_ = visitor.visitInt(hasSoftInputMode(), this.softInputMode_, other.hasSoftInputMode(), other.softInputMode_);
                this.format_ = visitor.visitInt(hasFormat(), this.format_, other.hasFormat(), other.format_);
                this.windowAnimations_ = visitor.visitInt(hasWindowAnimations(), this.windowAnimations_, other.hasWindowAnimations(), other.windowAnimations_);
                this.alpha_ = visitor.visitFloat(hasAlpha(), this.alpha_, other.hasAlpha(), other.alpha_);
                this.screenBrightness_ = visitor.visitFloat(hasScreenBrightness(), this.screenBrightness_, other.hasScreenBrightness(), other.screenBrightness_);
                this.buttonBrightness_ = visitor.visitFloat(hasButtonBrightness(), this.buttonBrightness_, other.hasButtonBrightness(), other.buttonBrightness_);
                this.rotationAnimation_ = visitor.visitInt(hasRotationAnimation(), this.rotationAnimation_, other.hasRotationAnimation(), other.rotationAnimation_);
                this.preferredRefreshRate_ = visitor.visitFloat(hasPreferredRefreshRate(), this.preferredRefreshRate_, other.hasPreferredRefreshRate(), other.preferredRefreshRate_);
                this.preferredDisplayModeId_ = visitor.visitInt(hasPreferredDisplayModeId(), this.preferredDisplayModeId_, other.hasPreferredDisplayModeId(), other.preferredDisplayModeId_);
                this.hasSystemUiListeners_ = visitor.visitBoolean(hasHasSystemUiListeners(), this.hasSystemUiListeners_, other.hasHasSystemUiListeners(), other.hasSystemUiListeners_);
                this.inputFeatureFlags_ = visitor.visitInt(hasInputFeatureFlags(), this.inputFeatureFlags_, other.hasInputFeatureFlags(), other.inputFeatureFlags_);
                this.userActivityTimeout_ = visitor.visitLong(hasUserActivityTimeout(), this.userActivityTimeout_, other.hasUserActivityTimeout(), other.userActivityTimeout_);
                this.needsMenuKey_ = visitor.visitInt(hasNeedsMenuKey(), this.needsMenuKey_, other.hasNeedsMenuKey(), other.needsMenuKey_);
                this.colorMode_ = visitor.visitInt(hasColorMode(), this.colorMode_, other.hasColorMode(), other.colorMode_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                this.privateFlags_ = visitor.visitInt(hasPrivateFlags(), this.privateFlags_, other.hasPrivateFlags(), other.privateFlags_);
                this.systemUiVisibilityFlags_ = visitor.visitInt(hasSystemUiVisibilityFlags(), this.systemUiVisibilityFlags_, other.hasSystemUiVisibilityFlags(), other.systemUiVisibilityFlags_);
                this.subtreeSystemUiVisibilityFlags_ = visitor.visitInt(hasSubtreeSystemUiVisibilityFlags(), this.subtreeSystemUiVisibilityFlags_, other.hasSubtreeSystemUiVisibilityFlags(), other.subtreeSystemUiVisibilityFlags_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.type_ = input.readInt32();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.x_ = input.readInt32();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.y_ = input.readInt32();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.width_ = input.readInt32();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.height_ = input.readInt32();
                                break;
                            case 53:
                                this.bitField0_ |= 32;
                                this.horizontalMargin_ = input.readFloat();
                                break;
                            case 61:
                                this.bitField0_ |= 64;
                                this.verticalMargin_ = input.readFloat();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.gravity_ = input.readInt32();
                                break;
                            case 72:
                                this.bitField0_ |= 256;
                                this.softInputMode_ = input.readInt32();
                                break;
                            case 80:
                                int rawValue = input.readEnum();
                                if (PixelFormatProto.Format.forNumber(rawValue) != null) {
                                    this.bitField0_ |= 512;
                                    this.format_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(10, rawValue);
                                    break;
                                }
                            case 88:
                                this.bitField0_ |= 1024;
                                this.windowAnimations_ = input.readInt32();
                                break;
                            case 101:
                                this.bitField0_ |= 2048;
                                this.alpha_ = input.readFloat();
                                break;
                            case 109:
                                this.bitField0_ |= 4096;
                                this.screenBrightness_ = input.readFloat();
                                break;
                            case 117:
                                this.bitField0_ |= 8192;
                                this.buttonBrightness_ = input.readFloat();
                                break;
                            case 120:
                                int rawValue2 = input.readEnum();
                                if (RotationAnimation.forNumber(rawValue2) != null) {
                                    this.bitField0_ |= 16384;
                                    this.rotationAnimation_ = rawValue2;
                                    break;
                                } else {
                                    super.mergeVarintField(15, rawValue2);
                                    break;
                                }
                            case 133:
                                this.bitField0_ |= 32768;
                                this.preferredRefreshRate_ = input.readFloat();
                                break;
                            case 136:
                                this.bitField0_ |= 65536;
                                this.preferredDisplayModeId_ = input.readInt32();
                                break;
                            case 144:
                                this.bitField0_ |= 131072;
                                this.hasSystemUiListeners_ = input.readBool();
                                break;
                            case 152:
                                this.bitField0_ |= 262144;
                                this.inputFeatureFlags_ = input.readUInt32();
                                break;
                            case 160:
                                this.bitField0_ |= 524288;
                                this.userActivityTimeout_ = input.readInt64();
                                break;
                            case AtomsProto.Atom.ASSIST_GESTURE_PROGRESS_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 176}*/:
                                int rawValue3 = input.readEnum();
                                if (NeedsMenuState.forNumber(rawValue3) != null) {
                                    this.bitField0_ |= 1048576;
                                    this.needsMenuKey_ = rawValue3;
                                    break;
                                } else {
                                    super.mergeVarintField(22, rawValue3);
                                    break;
                                }
                            case 184:
                                int rawValue4 = input.readEnum();
                                if (DisplayProto.ColorMode.forNumber(rawValue4) != null) {
                                    this.bitField0_ |= 2097152;
                                    this.colorMode_ = rawValue4;
                                    break;
                                } else {
                                    super.mergeVarintField(23, rawValue4);
                                    break;
                                }
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIORECORD_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 192}*/:
                                this.bitField0_ |= 4194304;
                                this.flags_ = input.readUInt32();
                                break;
                            case AtomsProto.Atom.CONTENT_CAPTURE_SESSION_EVENTS_FIELD_NUMBER /*{ENCODED_INT: 208}*/:
                                this.bitField0_ |= 8388608;
                                this.privateFlags_ = input.readUInt32();
                                break;
                            case AtomsProto.Atom.APP_PERMISSION_FRAGMENT_VIEWED_FIELD_NUMBER /*{ENCODED_INT: 216}*/:
                                this.bitField0_ |= 16777216;
                                this.systemUiVisibilityFlags_ = input.readUInt32();
                                break;
                            case AtomsProto.Atom.BACK_GESTURE_REPORTED_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 224}*/:
                                this.bitField0_ |= 33554432;
                                this.subtreeSystemUiVisibilityFlags_ = input.readUInt32();
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (WindowLayoutParamsProto.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static WindowLayoutParamsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowLayoutParamsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
