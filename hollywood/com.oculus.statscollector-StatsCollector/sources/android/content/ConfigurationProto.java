package android.content;

import android.app.WindowConfigurationProto;
import android.content.LocaleProto;
import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

public final class ConfigurationProto extends GeneratedMessageLite<ConfigurationProto, Builder> implements ConfigurationProtoOrBuilder {
    public static final int COLOR_MODE_FIELD_NUMBER = 6;
    private static final ConfigurationProto DEFAULT_INSTANCE = new ConfigurationProto();
    public static final int DENSITY_DPI_FIELD_NUMBER = 18;
    public static final int FONT_SCALE_FIELD_NUMBER = 1;
    public static final int HARD_KEYBOARD_HIDDEN_FIELD_NUMBER = 10;
    public static final int KEYBOARD_FIELD_NUMBER = 8;
    public static final int KEYBOARD_HIDDEN_FIELD_NUMBER = 9;
    public static final int LOCALES_FIELD_NUMBER = 4;
    public static final int LOCALE_LIST_FIELD_NUMBER = 20;
    public static final int MCC_FIELD_NUMBER = 2;
    public static final int MNC_FIELD_NUMBER = 3;
    public static final int NAVIGATION_FIELD_NUMBER = 11;
    public static final int NAVIGATION_HIDDEN_FIELD_NUMBER = 12;
    public static final int ORIENTATION_FIELD_NUMBER = 13;
    private static volatile Parser<ConfigurationProto> PARSER = null;
    public static final int SCREEN_HEIGHT_DP_FIELD_NUMBER = 16;
    public static final int SCREEN_LAYOUT_FIELD_NUMBER = 5;
    public static final int SCREEN_WIDTH_DP_FIELD_NUMBER = 15;
    public static final int SMALLEST_SCREEN_WIDTH_DP_FIELD_NUMBER = 17;
    public static final int TOUCHSCREEN_FIELD_NUMBER = 7;
    public static final int UI_MODE_FIELD_NUMBER = 14;
    public static final int WINDOW_CONFIGURATION_FIELD_NUMBER = 19;
    private int bitField0_;
    private int colorMode_ = 0;
    private int densityDpi_ = 0;
    private float fontScale_ = 0.0f;
    private int hardKeyboardHidden_ = 0;
    private int keyboardHidden_ = 0;
    private int keyboard_ = 0;
    private String localeList_ = "";
    private Internal.ProtobufList<LocaleProto> locales_ = emptyProtobufList();
    private int mcc_ = 0;
    private int mnc_ = 0;
    private int navigationHidden_ = 0;
    private int navigation_ = 0;
    private int orientation_ = 0;
    private int screenHeightDp_ = 0;
    private int screenLayout_ = 0;
    private int screenWidthDp_ = 0;
    private int smallestScreenWidthDp_ = 0;
    private int touchscreen_ = 0;
    private int uiMode_ = 0;
    private WindowConfigurationProto windowConfiguration_;

    private ConfigurationProto() {
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasFontScale() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public float getFontScale() {
        return this.fontScale_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFontScale(float value) {
        this.bitField0_ |= 1;
        this.fontScale_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFontScale() {
        this.bitField0_ &= -2;
        this.fontScale_ = 0.0f;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasMcc() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getMcc() {
        return this.mcc_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMcc(int value) {
        this.bitField0_ |= 2;
        this.mcc_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMcc() {
        this.bitField0_ &= -3;
        this.mcc_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasMnc() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getMnc() {
        return this.mnc_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMnc(int value) {
        this.bitField0_ |= 4;
        this.mnc_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMnc() {
        this.bitField0_ &= -5;
        this.mnc_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    @Deprecated
    public List<LocaleProto> getLocalesList() {
        return this.locales_;
    }

    @Deprecated
    public List<? extends LocaleProtoOrBuilder> getLocalesOrBuilderList() {
        return this.locales_;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    @Deprecated
    public int getLocalesCount() {
        return this.locales_.size();
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    @Deprecated
    public LocaleProto getLocales(int index) {
        return this.locales_.get(index);
    }

    @Deprecated
    public LocaleProtoOrBuilder getLocalesOrBuilder(int index) {
        return this.locales_.get(index);
    }

    private void ensureLocalesIsMutable() {
        if (!this.locales_.isModifiable()) {
            this.locales_ = GeneratedMessageLite.mutableCopy(this.locales_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLocales(int index, LocaleProto value) {
        if (value != null) {
            ensureLocalesIsMutable();
            this.locales_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLocales(int index, LocaleProto.Builder builderForValue) {
        ensureLocalesIsMutable();
        this.locales_.set(index, (LocaleProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLocales(LocaleProto value) {
        if (value != null) {
            ensureLocalesIsMutable();
            this.locales_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLocales(int index, LocaleProto value) {
        if (value != null) {
            ensureLocalesIsMutable();
            this.locales_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLocales(LocaleProto.Builder builderForValue) {
        ensureLocalesIsMutable();
        this.locales_.add((LocaleProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLocales(int index, LocaleProto.Builder builderForValue) {
        ensureLocalesIsMutable();
        this.locales_.add(index, (LocaleProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllLocales(Iterable<? extends LocaleProto> values) {
        ensureLocalesIsMutable();
        AbstractMessageLite.addAll(values, this.locales_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLocales() {
        this.locales_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeLocales(int index) {
        ensureLocalesIsMutable();
        this.locales_.remove(index);
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasScreenLayout() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getScreenLayout() {
        return this.screenLayout_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenLayout(int value) {
        this.bitField0_ |= 8;
        this.screenLayout_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenLayout() {
        this.bitField0_ &= -9;
        this.screenLayout_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasColorMode() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getColorMode() {
        return this.colorMode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setColorMode(int value) {
        this.bitField0_ |= 16;
        this.colorMode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearColorMode() {
        this.bitField0_ &= -17;
        this.colorMode_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasTouchscreen() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getTouchscreen() {
        return this.touchscreen_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTouchscreen(int value) {
        this.bitField0_ |= 32;
        this.touchscreen_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTouchscreen() {
        this.bitField0_ &= -33;
        this.touchscreen_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasKeyboard() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getKeyboard() {
        return this.keyboard_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyboard(int value) {
        this.bitField0_ |= 64;
        this.keyboard_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyboard() {
        this.bitField0_ &= -65;
        this.keyboard_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasKeyboardHidden() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getKeyboardHidden() {
        return this.keyboardHidden_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyboardHidden(int value) {
        this.bitField0_ |= 128;
        this.keyboardHidden_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyboardHidden() {
        this.bitField0_ &= -129;
        this.keyboardHidden_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasHardKeyboardHidden() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getHardKeyboardHidden() {
        return this.hardKeyboardHidden_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHardKeyboardHidden(int value) {
        this.bitField0_ |= 256;
        this.hardKeyboardHidden_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHardKeyboardHidden() {
        this.bitField0_ &= -257;
        this.hardKeyboardHidden_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasNavigation() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getNavigation() {
        return this.navigation_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNavigation(int value) {
        this.bitField0_ |= 512;
        this.navigation_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNavigation() {
        this.bitField0_ &= -513;
        this.navigation_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasNavigationHidden() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getNavigationHidden() {
        return this.navigationHidden_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNavigationHidden(int value) {
        this.bitField0_ |= 1024;
        this.navigationHidden_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNavigationHidden() {
        this.bitField0_ &= -1025;
        this.navigationHidden_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasOrientation() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getOrientation() {
        return this.orientation_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOrientation(int value) {
        this.bitField0_ |= 2048;
        this.orientation_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOrientation() {
        this.bitField0_ &= -2049;
        this.orientation_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasUiMode() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getUiMode() {
        return this.uiMode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUiMode(int value) {
        this.bitField0_ |= 4096;
        this.uiMode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUiMode() {
        this.bitField0_ &= -4097;
        this.uiMode_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasScreenWidthDp() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getScreenWidthDp() {
        return this.screenWidthDp_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenWidthDp(int value) {
        this.bitField0_ |= 8192;
        this.screenWidthDp_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenWidthDp() {
        this.bitField0_ &= -8193;
        this.screenWidthDp_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasScreenHeightDp() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getScreenHeightDp() {
        return this.screenHeightDp_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenHeightDp(int value) {
        this.bitField0_ |= 16384;
        this.screenHeightDp_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenHeightDp() {
        this.bitField0_ &= -16385;
        this.screenHeightDp_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasSmallestScreenWidthDp() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getSmallestScreenWidthDp() {
        return this.smallestScreenWidthDp_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSmallestScreenWidthDp(int value) {
        this.bitField0_ |= 32768;
        this.smallestScreenWidthDp_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSmallestScreenWidthDp() {
        this.bitField0_ &= -32769;
        this.smallestScreenWidthDp_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasDensityDpi() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public int getDensityDpi() {
        return this.densityDpi_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDensityDpi(int value) {
        this.bitField0_ |= 65536;
        this.densityDpi_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDensityDpi() {
        this.bitField0_ &= -65537;
        this.densityDpi_ = 0;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasWindowConfiguration() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public WindowConfigurationProto getWindowConfiguration() {
        WindowConfigurationProto windowConfigurationProto = this.windowConfiguration_;
        return windowConfigurationProto == null ? WindowConfigurationProto.getDefaultInstance() : windowConfigurationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowConfiguration(WindowConfigurationProto value) {
        if (value != null) {
            this.windowConfiguration_ = value;
            this.bitField0_ |= 131072;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowConfiguration(WindowConfigurationProto.Builder builderForValue) {
        this.windowConfiguration_ = (WindowConfigurationProto) builderForValue.build();
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindowConfiguration(WindowConfigurationProto value) {
        WindowConfigurationProto windowConfigurationProto = this.windowConfiguration_;
        if (windowConfigurationProto == null || windowConfigurationProto == WindowConfigurationProto.getDefaultInstance()) {
            this.windowConfiguration_ = value;
        } else {
            this.windowConfiguration_ = (WindowConfigurationProto) ((WindowConfigurationProto.Builder) WindowConfigurationProto.newBuilder(this.windowConfiguration_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowConfiguration() {
        this.windowConfiguration_ = null;
        this.bitField0_ &= -131073;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public boolean hasLocaleList() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public String getLocaleList() {
        return this.localeList_;
    }

    @Override // android.content.ConfigurationProtoOrBuilder
    public ByteString getLocaleListBytes() {
        return ByteString.copyFromUtf8(this.localeList_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLocaleList(String value) {
        if (value != null) {
            this.bitField0_ |= 262144;
            this.localeList_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLocaleList() {
        this.bitField0_ &= -262145;
        this.localeList_ = getDefaultInstance().getLocaleList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLocaleListBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 262144;
            this.localeList_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeFloat(1, this.fontScale_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeUInt32(2, this.mcc_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeUInt32(3, this.mnc_);
        }
        for (int i = 0; i < this.locales_.size(); i++) {
            output.writeMessage(4, this.locales_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeUInt32(5, this.screenLayout_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeUInt32(6, this.colorMode_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeUInt32(7, this.touchscreen_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeUInt32(8, this.keyboard_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeUInt32(9, this.keyboardHidden_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeUInt32(10, this.hardKeyboardHidden_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeUInt32(11, this.navigation_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeUInt32(12, this.navigationHidden_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeUInt32(13, this.orientation_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeUInt32(14, this.uiMode_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeUInt32(15, this.screenWidthDp_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeUInt32(16, this.screenHeightDp_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeUInt32(17, this.smallestScreenWidthDp_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeUInt32(18, this.densityDpi_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeMessage(19, getWindowConfiguration());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeString(20, getLocaleList());
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
            size2 = 0 + CodedOutputStream.computeFloatSize(1, this.fontScale_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeUInt32Size(2, this.mcc_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeUInt32Size(3, this.mnc_);
        }
        for (int i = 0; i < this.locales_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.locales_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeUInt32Size(5, this.screenLayout_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeUInt32Size(6, this.colorMode_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeUInt32Size(7, this.touchscreen_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeUInt32Size(8, this.keyboard_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeUInt32Size(9, this.keyboardHidden_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeUInt32Size(10, this.hardKeyboardHidden_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeUInt32Size(11, this.navigation_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeUInt32Size(12, this.navigationHidden_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeUInt32Size(13, this.orientation_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeUInt32Size(14, this.uiMode_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeUInt32Size(15, this.screenWidthDp_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeUInt32Size(16, this.screenHeightDp_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeUInt32Size(17, this.smallestScreenWidthDp_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeUInt32Size(18, this.densityDpi_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeMessageSize(19, getWindowConfiguration());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeStringSize(20, getLocaleList());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ConfigurationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigurationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigurationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigurationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigurationProto parseFrom(InputStream input) throws IOException {
        return (ConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigurationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConfigurationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigurationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConfigurationProto parseFrom(CodedInputStream input) throws IOException {
        return (ConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigurationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConfigurationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ConfigurationProto, Builder> implements ConfigurationProtoOrBuilder {
        private Builder() {
            super(ConfigurationProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasFontScale() {
            return ((ConfigurationProto) this.instance).hasFontScale();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public float getFontScale() {
            return ((ConfigurationProto) this.instance).getFontScale();
        }

        public Builder setFontScale(float value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setFontScale(value);
            return this;
        }

        public Builder clearFontScale() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearFontScale();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasMcc() {
            return ((ConfigurationProto) this.instance).hasMcc();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getMcc() {
            return ((ConfigurationProto) this.instance).getMcc();
        }

        public Builder setMcc(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setMcc(value);
            return this;
        }

        public Builder clearMcc() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearMcc();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasMnc() {
            return ((ConfigurationProto) this.instance).hasMnc();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getMnc() {
            return ((ConfigurationProto) this.instance).getMnc();
        }

        public Builder setMnc(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setMnc(value);
            return this;
        }

        public Builder clearMnc() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearMnc();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        @Deprecated
        public List<LocaleProto> getLocalesList() {
            return Collections.unmodifiableList(((ConfigurationProto) this.instance).getLocalesList());
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        @Deprecated
        public int getLocalesCount() {
            return ((ConfigurationProto) this.instance).getLocalesCount();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        @Deprecated
        public LocaleProto getLocales(int index) {
            return ((ConfigurationProto) this.instance).getLocales(index);
        }

        @Deprecated
        public Builder setLocales(int index, LocaleProto value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setLocales((ConfigurationProto) index, (int) value);
            return this;
        }

        @Deprecated
        public Builder setLocales(int index, LocaleProto.Builder builderForValue) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setLocales((ConfigurationProto) index, (int) builderForValue);
            return this;
        }

        @Deprecated
        public Builder addLocales(LocaleProto value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).addLocales((ConfigurationProto) value);
            return this;
        }

        @Deprecated
        public Builder addLocales(int index, LocaleProto value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).addLocales((ConfigurationProto) index, (int) value);
            return this;
        }

        @Deprecated
        public Builder addLocales(LocaleProto.Builder builderForValue) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).addLocales((ConfigurationProto) builderForValue);
            return this;
        }

        @Deprecated
        public Builder addLocales(int index, LocaleProto.Builder builderForValue) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).addLocales((ConfigurationProto) index, (int) builderForValue);
            return this;
        }

        @Deprecated
        public Builder addAllLocales(Iterable<? extends LocaleProto> values) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).addAllLocales(values);
            return this;
        }

        @Deprecated
        public Builder clearLocales() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearLocales();
            return this;
        }

        @Deprecated
        public Builder removeLocales(int index) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).removeLocales(index);
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasScreenLayout() {
            return ((ConfigurationProto) this.instance).hasScreenLayout();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getScreenLayout() {
            return ((ConfigurationProto) this.instance).getScreenLayout();
        }

        public Builder setScreenLayout(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setScreenLayout(value);
            return this;
        }

        public Builder clearScreenLayout() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearScreenLayout();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasColorMode() {
            return ((ConfigurationProto) this.instance).hasColorMode();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getColorMode() {
            return ((ConfigurationProto) this.instance).getColorMode();
        }

        public Builder setColorMode(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setColorMode(value);
            return this;
        }

        public Builder clearColorMode() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearColorMode();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasTouchscreen() {
            return ((ConfigurationProto) this.instance).hasTouchscreen();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getTouchscreen() {
            return ((ConfigurationProto) this.instance).getTouchscreen();
        }

        public Builder setTouchscreen(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setTouchscreen(value);
            return this;
        }

        public Builder clearTouchscreen() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearTouchscreen();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasKeyboard() {
            return ((ConfigurationProto) this.instance).hasKeyboard();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getKeyboard() {
            return ((ConfigurationProto) this.instance).getKeyboard();
        }

        public Builder setKeyboard(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setKeyboard(value);
            return this;
        }

        public Builder clearKeyboard() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearKeyboard();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasKeyboardHidden() {
            return ((ConfigurationProto) this.instance).hasKeyboardHidden();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getKeyboardHidden() {
            return ((ConfigurationProto) this.instance).getKeyboardHidden();
        }

        public Builder setKeyboardHidden(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setKeyboardHidden(value);
            return this;
        }

        public Builder clearKeyboardHidden() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearKeyboardHidden();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasHardKeyboardHidden() {
            return ((ConfigurationProto) this.instance).hasHardKeyboardHidden();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getHardKeyboardHidden() {
            return ((ConfigurationProto) this.instance).getHardKeyboardHidden();
        }

        public Builder setHardKeyboardHidden(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setHardKeyboardHidden(value);
            return this;
        }

        public Builder clearHardKeyboardHidden() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearHardKeyboardHidden();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasNavigation() {
            return ((ConfigurationProto) this.instance).hasNavigation();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getNavigation() {
            return ((ConfigurationProto) this.instance).getNavigation();
        }

        public Builder setNavigation(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setNavigation(value);
            return this;
        }

        public Builder clearNavigation() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearNavigation();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasNavigationHidden() {
            return ((ConfigurationProto) this.instance).hasNavigationHidden();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getNavigationHidden() {
            return ((ConfigurationProto) this.instance).getNavigationHidden();
        }

        public Builder setNavigationHidden(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setNavigationHidden(value);
            return this;
        }

        public Builder clearNavigationHidden() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearNavigationHidden();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasOrientation() {
            return ((ConfigurationProto) this.instance).hasOrientation();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getOrientation() {
            return ((ConfigurationProto) this.instance).getOrientation();
        }

        public Builder setOrientation(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setOrientation(value);
            return this;
        }

        public Builder clearOrientation() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearOrientation();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasUiMode() {
            return ((ConfigurationProto) this.instance).hasUiMode();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getUiMode() {
            return ((ConfigurationProto) this.instance).getUiMode();
        }

        public Builder setUiMode(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setUiMode(value);
            return this;
        }

        public Builder clearUiMode() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearUiMode();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasScreenWidthDp() {
            return ((ConfigurationProto) this.instance).hasScreenWidthDp();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getScreenWidthDp() {
            return ((ConfigurationProto) this.instance).getScreenWidthDp();
        }

        public Builder setScreenWidthDp(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setScreenWidthDp(value);
            return this;
        }

        public Builder clearScreenWidthDp() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearScreenWidthDp();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasScreenHeightDp() {
            return ((ConfigurationProto) this.instance).hasScreenHeightDp();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getScreenHeightDp() {
            return ((ConfigurationProto) this.instance).getScreenHeightDp();
        }

        public Builder setScreenHeightDp(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setScreenHeightDp(value);
            return this;
        }

        public Builder clearScreenHeightDp() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearScreenHeightDp();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasSmallestScreenWidthDp() {
            return ((ConfigurationProto) this.instance).hasSmallestScreenWidthDp();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getSmallestScreenWidthDp() {
            return ((ConfigurationProto) this.instance).getSmallestScreenWidthDp();
        }

        public Builder setSmallestScreenWidthDp(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setSmallestScreenWidthDp(value);
            return this;
        }

        public Builder clearSmallestScreenWidthDp() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearSmallestScreenWidthDp();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasDensityDpi() {
            return ((ConfigurationProto) this.instance).hasDensityDpi();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public int getDensityDpi() {
            return ((ConfigurationProto) this.instance).getDensityDpi();
        }

        public Builder setDensityDpi(int value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setDensityDpi(value);
            return this;
        }

        public Builder clearDensityDpi() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearDensityDpi();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasWindowConfiguration() {
            return ((ConfigurationProto) this.instance).hasWindowConfiguration();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public WindowConfigurationProto getWindowConfiguration() {
            return ((ConfigurationProto) this.instance).getWindowConfiguration();
        }

        public Builder setWindowConfiguration(WindowConfigurationProto value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setWindowConfiguration((ConfigurationProto) value);
            return this;
        }

        public Builder setWindowConfiguration(WindowConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setWindowConfiguration((ConfigurationProto) builderForValue);
            return this;
        }

        public Builder mergeWindowConfiguration(WindowConfigurationProto value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).mergeWindowConfiguration(value);
            return this;
        }

        public Builder clearWindowConfiguration() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearWindowConfiguration();
            return this;
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public boolean hasLocaleList() {
            return ((ConfigurationProto) this.instance).hasLocaleList();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public String getLocaleList() {
            return ((ConfigurationProto) this.instance).getLocaleList();
        }

        @Override // android.content.ConfigurationProtoOrBuilder
        public ByteString getLocaleListBytes() {
            return ((ConfigurationProto) this.instance).getLocaleListBytes();
        }

        public Builder setLocaleList(String value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setLocaleList(value);
            return this;
        }

        public Builder clearLocaleList() {
            copyOnWrite();
            ((ConfigurationProto) this.instance).clearLocaleList();
            return this;
        }

        public Builder setLocaleListBytes(ByteString value) {
            copyOnWrite();
            ((ConfigurationProto) this.instance).setLocaleListBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ConfigurationProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.locales_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ConfigurationProto other = (ConfigurationProto) arg1;
                this.fontScale_ = visitor.visitFloat(hasFontScale(), this.fontScale_, other.hasFontScale(), other.fontScale_);
                this.mcc_ = visitor.visitInt(hasMcc(), this.mcc_, other.hasMcc(), other.mcc_);
                this.mnc_ = visitor.visitInt(hasMnc(), this.mnc_, other.hasMnc(), other.mnc_);
                this.locales_ = visitor.visitList(this.locales_, other.locales_);
                this.screenLayout_ = visitor.visitInt(hasScreenLayout(), this.screenLayout_, other.hasScreenLayout(), other.screenLayout_);
                this.colorMode_ = visitor.visitInt(hasColorMode(), this.colorMode_, other.hasColorMode(), other.colorMode_);
                this.touchscreen_ = visitor.visitInt(hasTouchscreen(), this.touchscreen_, other.hasTouchscreen(), other.touchscreen_);
                this.keyboard_ = visitor.visitInt(hasKeyboard(), this.keyboard_, other.hasKeyboard(), other.keyboard_);
                this.keyboardHidden_ = visitor.visitInt(hasKeyboardHidden(), this.keyboardHidden_, other.hasKeyboardHidden(), other.keyboardHidden_);
                this.hardKeyboardHidden_ = visitor.visitInt(hasHardKeyboardHidden(), this.hardKeyboardHidden_, other.hasHardKeyboardHidden(), other.hardKeyboardHidden_);
                this.navigation_ = visitor.visitInt(hasNavigation(), this.navigation_, other.hasNavigation(), other.navigation_);
                this.navigationHidden_ = visitor.visitInt(hasNavigationHidden(), this.navigationHidden_, other.hasNavigationHidden(), other.navigationHidden_);
                this.orientation_ = visitor.visitInt(hasOrientation(), this.orientation_, other.hasOrientation(), other.orientation_);
                this.uiMode_ = visitor.visitInt(hasUiMode(), this.uiMode_, other.hasUiMode(), other.uiMode_);
                this.screenWidthDp_ = visitor.visitInt(hasScreenWidthDp(), this.screenWidthDp_, other.hasScreenWidthDp(), other.screenWidthDp_);
                this.screenHeightDp_ = visitor.visitInt(hasScreenHeightDp(), this.screenHeightDp_, other.hasScreenHeightDp(), other.screenHeightDp_);
                this.smallestScreenWidthDp_ = visitor.visitInt(hasSmallestScreenWidthDp(), this.smallestScreenWidthDp_, other.hasSmallestScreenWidthDp(), other.smallestScreenWidthDp_);
                this.densityDpi_ = visitor.visitInt(hasDensityDpi(), this.densityDpi_, other.hasDensityDpi(), other.densityDpi_);
                this.windowConfiguration_ = (WindowConfigurationProto) visitor.visitMessage(this.windowConfiguration_, other.windowConfiguration_);
                this.localeList_ = visitor.visitString(hasLocaleList(), this.localeList_, other.hasLocaleList(), other.localeList_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 13:
                                this.bitField0_ |= 1;
                                this.fontScale_ = input.readFloat();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.mcc_ = input.readUInt32();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.mnc_ = input.readUInt32();
                                break;
                            case 34:
                                if (!this.locales_.isModifiable()) {
                                    this.locales_ = GeneratedMessageLite.mutableCopy(this.locales_);
                                }
                                this.locales_.add((LocaleProto) input.readMessage(LocaleProto.parser(), extensionRegistry));
                                break;
                            case 40:
                                this.bitField0_ |= 8;
                                this.screenLayout_ = input.readUInt32();
                                break;
                            case 48:
                                this.bitField0_ |= 16;
                                this.colorMode_ = input.readUInt32();
                                break;
                            case 56:
                                this.bitField0_ |= 32;
                                this.touchscreen_ = input.readUInt32();
                                break;
                            case 64:
                                this.bitField0_ |= 64;
                                this.keyboard_ = input.readUInt32();
                                break;
                            case 72:
                                this.bitField0_ |= 128;
                                this.keyboardHidden_ = input.readUInt32();
                                break;
                            case 80:
                                this.bitField0_ |= 256;
                                this.hardKeyboardHidden_ = input.readUInt32();
                                break;
                            case 88:
                                this.bitField0_ |= 512;
                                this.navigation_ = input.readUInt32();
                                break;
                            case 96:
                                this.bitField0_ |= 1024;
                                this.navigationHidden_ = input.readUInt32();
                                break;
                            case 104:
                                this.bitField0_ |= 2048;
                                this.orientation_ = input.readUInt32();
                                break;
                            case 112:
                                this.bitField0_ |= 4096;
                                this.uiMode_ = input.readUInt32();
                                break;
                            case 120:
                                this.bitField0_ |= 8192;
                                this.screenWidthDp_ = input.readUInt32();
                                break;
                            case 128:
                                this.bitField0_ |= 16384;
                                this.screenHeightDp_ = input.readUInt32();
                                break;
                            case 136:
                                this.bitField0_ |= 32768;
                                this.smallestScreenWidthDp_ = input.readUInt32();
                                break;
                            case 144:
                                this.bitField0_ |= 65536;
                                this.densityDpi_ = input.readUInt32();
                                break;
                            case 154:
                                WindowConfigurationProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 131072) == 131072) {
                                    subBuilder = (WindowConfigurationProto.Builder) this.windowConfiguration_.toBuilder();
                                }
                                this.windowConfiguration_ = (WindowConfigurationProto) input.readMessage(WindowConfigurationProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.windowConfiguration_);
                                    this.windowConfiguration_ = (WindowConfigurationProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 131072;
                                break;
                            case 162:
                                String s = input.readString();
                                this.bitField0_ |= 262144;
                                this.localeList_ = s;
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
                    synchronized (ConfigurationProto.class) {
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

    public static ConfigurationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigurationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
