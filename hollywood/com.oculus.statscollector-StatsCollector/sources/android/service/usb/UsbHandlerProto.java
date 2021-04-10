package android.service.usb;

import android.service.usb.UsbAccessoryProto;
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
import java.util.List;

public final class UsbHandlerProto extends GeneratedMessageLite<UsbHandlerProto, Builder> implements UsbHandlerProtoOrBuilder {
    public static final int ADB_ENABLED_FIELD_NUMBER = 14;
    public static final int AUDIO_ACCESSORY_CONNECTED_FIELD_NUMBER = 13;
    public static final int CONFIGURED_FIELD_NUMBER = 6;
    public static final int CONNECTED_FIELD_NUMBER = 5;
    public static final int CURRENT_ACCESSORY_FIELD_NUMBER = 7;
    public static final int CURRENT_FUNCTIONS_APPLIED_FIELD_NUMBER = 2;
    public static final int CURRENT_FUNCTIONS_FIELD_NUMBER = 1;
    private static final UsbHandlerProto DEFAULT_INSTANCE = new UsbHandlerProto();
    public static final int HIDE_USB_NOTIFICATION_FIELD_NUMBER = 12;
    public static final int HOST_CONNECTED_FIELD_NUMBER = 8;
    public static final int KERNEL_FUNCTION_LIST_FIELD_NUMBER = 16;
    public static final int KERNEL_STATE_FIELD_NUMBER = 15;
    private static volatile Parser<UsbHandlerProto> PARSER = null;
    public static final int SCREEN_LOCKED_FIELD_NUMBER = 4;
    public static final int SCREEN_UNLOCKED_FUNCTIONS_FIELD_NUMBER = 3;
    public static final int SINK_POWER_FIELD_NUMBER = 10;
    public static final int SOURCE_POWER_FIELD_NUMBER = 9;
    public static final int USB_CHARGING_FIELD_NUMBER = 11;
    private static final Internal.ListAdapter.Converter<Integer, Function> currentFunctions_converter_ = new Internal.ListAdapter.Converter<Integer, Function>() {
        /* class android.service.usb.UsbHandlerProto.AnonymousClass1 */

        public Function convert(Integer from) {
            Function result = Function.forNumber(from.intValue());
            return result == null ? Function.FUNCTION_ADB : result;
        }
    };
    private static final Internal.ListAdapter.Converter<Integer, Function> screenUnlockedFunctions_converter_ = new Internal.ListAdapter.Converter<Integer, Function>() {
        /* class android.service.usb.UsbHandlerProto.AnonymousClass2 */

        public Function convert(Integer from) {
            Function result = Function.forNumber(from.intValue());
            return result == null ? Function.FUNCTION_ADB : result;
        }
    };
    private boolean adbEnabled_ = false;
    private boolean audioAccessoryConnected_ = false;
    private int bitField0_;
    private boolean configured_ = false;
    private boolean connected_ = false;
    private UsbAccessoryProto currentAccessory_;
    private boolean currentFunctionsApplied_ = false;
    private Internal.IntList currentFunctions_ = emptyIntList();
    private boolean hideUsbNotification_ = false;
    private boolean hostConnected_ = false;
    private String kernelFunctionList_ = "";
    private String kernelState_ = "";
    private boolean screenLocked_ = false;
    private Internal.IntList screenUnlockedFunctions_ = emptyIntList();
    private boolean sinkPower_ = false;
    private boolean sourcePower_ = false;
    private boolean usbCharging_ = false;

    private UsbHandlerProto() {
    }

    public enum Function implements Internal.EnumLite {
        FUNCTION_ADB(1),
        FUNCTION_ACCESSORY(2),
        FUNCTION_MTP(4),
        FUNCTION_MIDI(8),
        FUNCTION_PTP(16),
        FUNCTION_RNDIS(32),
        FUNCTION_AUDIO_SOURCE(64);
        
        public static final int FUNCTION_ACCESSORY_VALUE = 2;
        public static final int FUNCTION_ADB_VALUE = 1;
        public static final int FUNCTION_AUDIO_SOURCE_VALUE = 64;
        public static final int FUNCTION_MIDI_VALUE = 8;
        public static final int FUNCTION_MTP_VALUE = 4;
        public static final int FUNCTION_PTP_VALUE = 16;
        public static final int FUNCTION_RNDIS_VALUE = 32;
        private static final Internal.EnumLiteMap<Function> internalValueMap = new Internal.EnumLiteMap<Function>() {
            /* class android.service.usb.UsbHandlerProto.Function.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Function findValueByNumber(int number) {
                return Function.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Function valueOf(int value2) {
            return forNumber(value2);
        }

        public static Function forNumber(int value2) {
            if (value2 == 1) {
                return FUNCTION_ADB;
            }
            if (value2 == 2) {
                return FUNCTION_ACCESSORY;
            }
            if (value2 == 4) {
                return FUNCTION_MTP;
            }
            if (value2 == 8) {
                return FUNCTION_MIDI;
            }
            if (value2 == 16) {
                return FUNCTION_PTP;
            }
            if (value2 == 32) {
                return FUNCTION_RNDIS;
            }
            if (value2 != 64) {
                return null;
            }
            return FUNCTION_AUDIO_SOURCE;
        }

        public static Internal.EnumLiteMap<Function> internalGetValueMap() {
            return internalValueMap;
        }

        private Function(int value2) {
            this.value = value2;
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public List<Function> getCurrentFunctionsList() {
        return new Internal.ListAdapter(this.currentFunctions_, currentFunctions_converter_);
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public int getCurrentFunctionsCount() {
        return this.currentFunctions_.size();
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public Function getCurrentFunctions(int index) {
        return currentFunctions_converter_.convert(Integer.valueOf(this.currentFunctions_.getInt(index)));
    }

    private void ensureCurrentFunctionsIsMutable() {
        if (!this.currentFunctions_.isModifiable()) {
            this.currentFunctions_ = GeneratedMessageLite.mutableCopy(this.currentFunctions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentFunctions(int index, Function value) {
        if (value != null) {
            ensureCurrentFunctionsIsMutable();
            this.currentFunctions_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCurrentFunctions(Function value) {
        if (value != null) {
            ensureCurrentFunctionsIsMutable();
            this.currentFunctions_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCurrentFunctions(Iterable<? extends Function> values) {
        ensureCurrentFunctionsIsMutable();
        for (Function value : values) {
            this.currentFunctions_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentFunctions() {
        this.currentFunctions_ = emptyIntList();
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasCurrentFunctionsApplied() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getCurrentFunctionsApplied() {
        return this.currentFunctionsApplied_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentFunctionsApplied(boolean value) {
        this.bitField0_ |= 1;
        this.currentFunctionsApplied_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentFunctionsApplied() {
        this.bitField0_ &= -2;
        this.currentFunctionsApplied_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public List<Function> getScreenUnlockedFunctionsList() {
        return new Internal.ListAdapter(this.screenUnlockedFunctions_, screenUnlockedFunctions_converter_);
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public int getScreenUnlockedFunctionsCount() {
        return this.screenUnlockedFunctions_.size();
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public Function getScreenUnlockedFunctions(int index) {
        return screenUnlockedFunctions_converter_.convert(Integer.valueOf(this.screenUnlockedFunctions_.getInt(index)));
    }

    private void ensureScreenUnlockedFunctionsIsMutable() {
        if (!this.screenUnlockedFunctions_.isModifiable()) {
            this.screenUnlockedFunctions_ = GeneratedMessageLite.mutableCopy(this.screenUnlockedFunctions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenUnlockedFunctions(int index, Function value) {
        if (value != null) {
            ensureScreenUnlockedFunctionsIsMutable();
            this.screenUnlockedFunctions_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addScreenUnlockedFunctions(Function value) {
        if (value != null) {
            ensureScreenUnlockedFunctionsIsMutable();
            this.screenUnlockedFunctions_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllScreenUnlockedFunctions(Iterable<? extends Function> values) {
        ensureScreenUnlockedFunctionsIsMutable();
        for (Function value : values) {
            this.screenUnlockedFunctions_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenUnlockedFunctions() {
        this.screenUnlockedFunctions_ = emptyIntList();
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasScreenLocked() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getScreenLocked() {
        return this.screenLocked_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenLocked(boolean value) {
        this.bitField0_ |= 2;
        this.screenLocked_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenLocked() {
        this.bitField0_ &= -3;
        this.screenLocked_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasConnected() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getConnected() {
        return this.connected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnected(boolean value) {
        this.bitField0_ |= 4;
        this.connected_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnected() {
        this.bitField0_ &= -5;
        this.connected_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasConfigured() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getConfigured() {
        return this.configured_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigured(boolean value) {
        this.bitField0_ |= 8;
        this.configured_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigured() {
        this.bitField0_ &= -9;
        this.configured_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasCurrentAccessory() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public UsbAccessoryProto getCurrentAccessory() {
        UsbAccessoryProto usbAccessoryProto = this.currentAccessory_;
        return usbAccessoryProto == null ? UsbAccessoryProto.getDefaultInstance() : usbAccessoryProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentAccessory(UsbAccessoryProto value) {
        if (value != null) {
            this.currentAccessory_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentAccessory(UsbAccessoryProto.Builder builderForValue) {
        this.currentAccessory_ = (UsbAccessoryProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCurrentAccessory(UsbAccessoryProto value) {
        UsbAccessoryProto usbAccessoryProto = this.currentAccessory_;
        if (usbAccessoryProto == null || usbAccessoryProto == UsbAccessoryProto.getDefaultInstance()) {
            this.currentAccessory_ = value;
        } else {
            this.currentAccessory_ = (UsbAccessoryProto) ((UsbAccessoryProto.Builder) UsbAccessoryProto.newBuilder(this.currentAccessory_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentAccessory() {
        this.currentAccessory_ = null;
        this.bitField0_ &= -17;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasHostConnected() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getHostConnected() {
        return this.hostConnected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHostConnected(boolean value) {
        this.bitField0_ |= 32;
        this.hostConnected_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHostConnected() {
        this.bitField0_ &= -33;
        this.hostConnected_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasSourcePower() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getSourcePower() {
        return this.sourcePower_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourcePower(boolean value) {
        this.bitField0_ |= 64;
        this.sourcePower_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSourcePower() {
        this.bitField0_ &= -65;
        this.sourcePower_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasSinkPower() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getSinkPower() {
        return this.sinkPower_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSinkPower(boolean value) {
        this.bitField0_ |= 128;
        this.sinkPower_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSinkPower() {
        this.bitField0_ &= -129;
        this.sinkPower_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasUsbCharging() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getUsbCharging() {
        return this.usbCharging_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsbCharging(boolean value) {
        this.bitField0_ |= 256;
        this.usbCharging_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUsbCharging() {
        this.bitField0_ &= -257;
        this.usbCharging_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasHideUsbNotification() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getHideUsbNotification() {
        return this.hideUsbNotification_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHideUsbNotification(boolean value) {
        this.bitField0_ |= 512;
        this.hideUsbNotification_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHideUsbNotification() {
        this.bitField0_ &= -513;
        this.hideUsbNotification_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasAudioAccessoryConnected() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getAudioAccessoryConnected() {
        return this.audioAccessoryConnected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAudioAccessoryConnected(boolean value) {
        this.bitField0_ |= 1024;
        this.audioAccessoryConnected_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAudioAccessoryConnected() {
        this.bitField0_ &= -1025;
        this.audioAccessoryConnected_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasAdbEnabled() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean getAdbEnabled() {
        return this.adbEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdbEnabled(boolean value) {
        this.bitField0_ |= 2048;
        this.adbEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdbEnabled() {
        this.bitField0_ &= -2049;
        this.adbEnabled_ = false;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasKernelState() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public String getKernelState() {
        return this.kernelState_;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public ByteString getKernelStateBytes() {
        return ByteString.copyFromUtf8(this.kernelState_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelState(String value) {
        if (value != null) {
            this.bitField0_ |= 4096;
            this.kernelState_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKernelState() {
        this.bitField0_ &= -4097;
        this.kernelState_ = getDefaultInstance().getKernelState();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelStateBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4096;
            this.kernelState_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public boolean hasKernelFunctionList() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public String getKernelFunctionList() {
        return this.kernelFunctionList_;
    }

    @Override // android.service.usb.UsbHandlerProtoOrBuilder
    public ByteString getKernelFunctionListBytes() {
        return ByteString.copyFromUtf8(this.kernelFunctionList_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelFunctionList(String value) {
        if (value != null) {
            this.bitField0_ |= 8192;
            this.kernelFunctionList_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKernelFunctionList() {
        this.bitField0_ &= -8193;
        this.kernelFunctionList_ = getDefaultInstance().getKernelFunctionList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelFunctionListBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8192;
            this.kernelFunctionList_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.currentFunctions_.size(); i++) {
            output.writeEnum(1, this.currentFunctions_.getInt(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(2, this.currentFunctionsApplied_);
        }
        for (int i2 = 0; i2 < this.screenUnlockedFunctions_.size(); i2++) {
            output.writeEnum(3, this.screenUnlockedFunctions_.getInt(i2));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(4, this.screenLocked_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(5, this.connected_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(6, this.configured_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(7, getCurrentAccessory());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(8, this.hostConnected_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(9, this.sourcePower_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeBool(10, this.sinkPower_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeBool(11, this.usbCharging_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeBool(12, this.hideUsbNotification_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeBool(13, this.audioAccessoryConnected_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(14, this.adbEnabled_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeString(15, getKernelState());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeString(16, getKernelFunctionList());
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int dataSize = 0;
        for (int i = 0; i < this.currentFunctions_.size(); i++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.currentFunctions_.getInt(i));
        }
        int size2 = 0 + dataSize + (this.currentFunctions_.size() * 1);
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeBoolSize(2, this.currentFunctionsApplied_);
        }
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.screenUnlockedFunctions_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeEnumSizeNoTag(this.screenUnlockedFunctions_.getInt(i2));
        }
        int size3 = size2 + dataSize2 + (this.screenUnlockedFunctions_.size() * 1);
        if ((this.bitField0_ & 2) == 2) {
            size3 += CodedOutputStream.computeBoolSize(4, this.screenLocked_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size3 += CodedOutputStream.computeBoolSize(5, this.connected_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size3 += CodedOutputStream.computeBoolSize(6, this.configured_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size3 += CodedOutputStream.computeMessageSize(7, getCurrentAccessory());
        }
        if ((this.bitField0_ & 32) == 32) {
            size3 += CodedOutputStream.computeBoolSize(8, this.hostConnected_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size3 += CodedOutputStream.computeBoolSize(9, this.sourcePower_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size3 += CodedOutputStream.computeBoolSize(10, this.sinkPower_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size3 += CodedOutputStream.computeBoolSize(11, this.usbCharging_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size3 += CodedOutputStream.computeBoolSize(12, this.hideUsbNotification_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size3 += CodedOutputStream.computeBoolSize(13, this.audioAccessoryConnected_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size3 += CodedOutputStream.computeBoolSize(14, this.adbEnabled_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size3 += CodedOutputStream.computeStringSize(15, getKernelState());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size3 += CodedOutputStream.computeStringSize(16, getKernelFunctionList());
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static UsbHandlerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbHandlerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbHandlerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbHandlerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbHandlerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbHandlerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbHandlerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbHandlerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbHandlerProto parseFrom(InputStream input) throws IOException {
        return (UsbHandlerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbHandlerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbHandlerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbHandlerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbHandlerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbHandlerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbHandlerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbHandlerProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbHandlerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbHandlerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbHandlerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbHandlerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbHandlerProto, Builder> implements UsbHandlerProtoOrBuilder {
        private Builder() {
            super(UsbHandlerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public List<Function> getCurrentFunctionsList() {
            return ((UsbHandlerProto) this.instance).getCurrentFunctionsList();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public int getCurrentFunctionsCount() {
            return ((UsbHandlerProto) this.instance).getCurrentFunctionsCount();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public Function getCurrentFunctions(int index) {
            return ((UsbHandlerProto) this.instance).getCurrentFunctions(index);
        }

        public Builder setCurrentFunctions(int index, Function value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setCurrentFunctions(index, value);
            return this;
        }

        public Builder addCurrentFunctions(Function value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).addCurrentFunctions(value);
            return this;
        }

        public Builder addAllCurrentFunctions(Iterable<? extends Function> values) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).addAllCurrentFunctions(values);
            return this;
        }

        public Builder clearCurrentFunctions() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearCurrentFunctions();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasCurrentFunctionsApplied() {
            return ((UsbHandlerProto) this.instance).hasCurrentFunctionsApplied();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getCurrentFunctionsApplied() {
            return ((UsbHandlerProto) this.instance).getCurrentFunctionsApplied();
        }

        public Builder setCurrentFunctionsApplied(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setCurrentFunctionsApplied(value);
            return this;
        }

        public Builder clearCurrentFunctionsApplied() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearCurrentFunctionsApplied();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public List<Function> getScreenUnlockedFunctionsList() {
            return ((UsbHandlerProto) this.instance).getScreenUnlockedFunctionsList();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public int getScreenUnlockedFunctionsCount() {
            return ((UsbHandlerProto) this.instance).getScreenUnlockedFunctionsCount();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public Function getScreenUnlockedFunctions(int index) {
            return ((UsbHandlerProto) this.instance).getScreenUnlockedFunctions(index);
        }

        public Builder setScreenUnlockedFunctions(int index, Function value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setScreenUnlockedFunctions(index, value);
            return this;
        }

        public Builder addScreenUnlockedFunctions(Function value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).addScreenUnlockedFunctions(value);
            return this;
        }

        public Builder addAllScreenUnlockedFunctions(Iterable<? extends Function> values) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).addAllScreenUnlockedFunctions(values);
            return this;
        }

        public Builder clearScreenUnlockedFunctions() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearScreenUnlockedFunctions();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasScreenLocked() {
            return ((UsbHandlerProto) this.instance).hasScreenLocked();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getScreenLocked() {
            return ((UsbHandlerProto) this.instance).getScreenLocked();
        }

        public Builder setScreenLocked(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setScreenLocked(value);
            return this;
        }

        public Builder clearScreenLocked() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearScreenLocked();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasConnected() {
            return ((UsbHandlerProto) this.instance).hasConnected();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getConnected() {
            return ((UsbHandlerProto) this.instance).getConnected();
        }

        public Builder setConnected(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setConnected(value);
            return this;
        }

        public Builder clearConnected() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearConnected();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasConfigured() {
            return ((UsbHandlerProto) this.instance).hasConfigured();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getConfigured() {
            return ((UsbHandlerProto) this.instance).getConfigured();
        }

        public Builder setConfigured(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setConfigured(value);
            return this;
        }

        public Builder clearConfigured() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearConfigured();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasCurrentAccessory() {
            return ((UsbHandlerProto) this.instance).hasCurrentAccessory();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public UsbAccessoryProto getCurrentAccessory() {
            return ((UsbHandlerProto) this.instance).getCurrentAccessory();
        }

        public Builder setCurrentAccessory(UsbAccessoryProto value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setCurrentAccessory((UsbHandlerProto) value);
            return this;
        }

        public Builder setCurrentAccessory(UsbAccessoryProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setCurrentAccessory((UsbHandlerProto) builderForValue);
            return this;
        }

        public Builder mergeCurrentAccessory(UsbAccessoryProto value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).mergeCurrentAccessory(value);
            return this;
        }

        public Builder clearCurrentAccessory() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearCurrentAccessory();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasHostConnected() {
            return ((UsbHandlerProto) this.instance).hasHostConnected();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getHostConnected() {
            return ((UsbHandlerProto) this.instance).getHostConnected();
        }

        public Builder setHostConnected(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setHostConnected(value);
            return this;
        }

        public Builder clearHostConnected() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearHostConnected();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasSourcePower() {
            return ((UsbHandlerProto) this.instance).hasSourcePower();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getSourcePower() {
            return ((UsbHandlerProto) this.instance).getSourcePower();
        }

        public Builder setSourcePower(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setSourcePower(value);
            return this;
        }

        public Builder clearSourcePower() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearSourcePower();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasSinkPower() {
            return ((UsbHandlerProto) this.instance).hasSinkPower();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getSinkPower() {
            return ((UsbHandlerProto) this.instance).getSinkPower();
        }

        public Builder setSinkPower(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setSinkPower(value);
            return this;
        }

        public Builder clearSinkPower() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearSinkPower();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasUsbCharging() {
            return ((UsbHandlerProto) this.instance).hasUsbCharging();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getUsbCharging() {
            return ((UsbHandlerProto) this.instance).getUsbCharging();
        }

        public Builder setUsbCharging(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setUsbCharging(value);
            return this;
        }

        public Builder clearUsbCharging() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearUsbCharging();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasHideUsbNotification() {
            return ((UsbHandlerProto) this.instance).hasHideUsbNotification();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getHideUsbNotification() {
            return ((UsbHandlerProto) this.instance).getHideUsbNotification();
        }

        public Builder setHideUsbNotification(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setHideUsbNotification(value);
            return this;
        }

        public Builder clearHideUsbNotification() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearHideUsbNotification();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasAudioAccessoryConnected() {
            return ((UsbHandlerProto) this.instance).hasAudioAccessoryConnected();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getAudioAccessoryConnected() {
            return ((UsbHandlerProto) this.instance).getAudioAccessoryConnected();
        }

        public Builder setAudioAccessoryConnected(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setAudioAccessoryConnected(value);
            return this;
        }

        public Builder clearAudioAccessoryConnected() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearAudioAccessoryConnected();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasAdbEnabled() {
            return ((UsbHandlerProto) this.instance).hasAdbEnabled();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean getAdbEnabled() {
            return ((UsbHandlerProto) this.instance).getAdbEnabled();
        }

        public Builder setAdbEnabled(boolean value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setAdbEnabled(value);
            return this;
        }

        public Builder clearAdbEnabled() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearAdbEnabled();
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasKernelState() {
            return ((UsbHandlerProto) this.instance).hasKernelState();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public String getKernelState() {
            return ((UsbHandlerProto) this.instance).getKernelState();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public ByteString getKernelStateBytes() {
            return ((UsbHandlerProto) this.instance).getKernelStateBytes();
        }

        public Builder setKernelState(String value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setKernelState(value);
            return this;
        }

        public Builder clearKernelState() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearKernelState();
            return this;
        }

        public Builder setKernelStateBytes(ByteString value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setKernelStateBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public boolean hasKernelFunctionList() {
            return ((UsbHandlerProto) this.instance).hasKernelFunctionList();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public String getKernelFunctionList() {
            return ((UsbHandlerProto) this.instance).getKernelFunctionList();
        }

        @Override // android.service.usb.UsbHandlerProtoOrBuilder
        public ByteString getKernelFunctionListBytes() {
            return ((UsbHandlerProto) this.instance).getKernelFunctionListBytes();
        }

        public Builder setKernelFunctionList(String value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setKernelFunctionList(value);
            return this;
        }

        public Builder clearKernelFunctionList() {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).clearKernelFunctionList();
            return this;
        }

        public Builder setKernelFunctionListBytes(ByteString value) {
            copyOnWrite();
            ((UsbHandlerProto) this.instance).setKernelFunctionListBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbHandlerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.currentFunctions_.makeImmutable();
                this.screenUnlockedFunctions_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbHandlerProto other = (UsbHandlerProto) arg1;
                this.currentFunctions_ = visitor.visitIntList(this.currentFunctions_, other.currentFunctions_);
                this.currentFunctionsApplied_ = visitor.visitBoolean(hasCurrentFunctionsApplied(), this.currentFunctionsApplied_, other.hasCurrentFunctionsApplied(), other.currentFunctionsApplied_);
                this.screenUnlockedFunctions_ = visitor.visitIntList(this.screenUnlockedFunctions_, other.screenUnlockedFunctions_);
                this.screenLocked_ = visitor.visitBoolean(hasScreenLocked(), this.screenLocked_, other.hasScreenLocked(), other.screenLocked_);
                this.connected_ = visitor.visitBoolean(hasConnected(), this.connected_, other.hasConnected(), other.connected_);
                this.configured_ = visitor.visitBoolean(hasConfigured(), this.configured_, other.hasConfigured(), other.configured_);
                this.currentAccessory_ = (UsbAccessoryProto) visitor.visitMessage(this.currentAccessory_, other.currentAccessory_);
                this.hostConnected_ = visitor.visitBoolean(hasHostConnected(), this.hostConnected_, other.hasHostConnected(), other.hostConnected_);
                this.sourcePower_ = visitor.visitBoolean(hasSourcePower(), this.sourcePower_, other.hasSourcePower(), other.sourcePower_);
                this.sinkPower_ = visitor.visitBoolean(hasSinkPower(), this.sinkPower_, other.hasSinkPower(), other.sinkPower_);
                this.usbCharging_ = visitor.visitBoolean(hasUsbCharging(), this.usbCharging_, other.hasUsbCharging(), other.usbCharging_);
                this.hideUsbNotification_ = visitor.visitBoolean(hasHideUsbNotification(), this.hideUsbNotification_, other.hasHideUsbNotification(), other.hideUsbNotification_);
                this.audioAccessoryConnected_ = visitor.visitBoolean(hasAudioAccessoryConnected(), this.audioAccessoryConnected_, other.hasAudioAccessoryConnected(), other.audioAccessoryConnected_);
                this.adbEnabled_ = visitor.visitBoolean(hasAdbEnabled(), this.adbEnabled_, other.hasAdbEnabled(), other.adbEnabled_);
                this.kernelState_ = visitor.visitString(hasKernelState(), this.kernelState_, other.hasKernelState(), other.kernelState_);
                this.kernelFunctionList_ = visitor.visitString(hasKernelFunctionList(), this.kernelFunctionList_, other.hasKernelFunctionList(), other.kernelFunctionList_);
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
                            case 8:
                                if (!this.currentFunctions_.isModifiable()) {
                                    this.currentFunctions_ = GeneratedMessageLite.mutableCopy(this.currentFunctions_);
                                }
                                int rawValue = input.readEnum();
                                if (Function.forNumber(rawValue) != null) {
                                    this.currentFunctions_.addInt(rawValue);
                                    break;
                                } else {
                                    super.mergeVarintField(1, rawValue);
                                    break;
                                }
                            case 10:
                                if (!this.currentFunctions_.isModifiable()) {
                                    this.currentFunctions_ = GeneratedMessageLite.mutableCopy(this.currentFunctions_);
                                }
                                int oldLimit = input.pushLimit(input.readRawVarint32());
                                while (input.getBytesUntilLimit() > 0) {
                                    int rawValue2 = input.readEnum();
                                    if (Function.forNumber(rawValue2) == null) {
                                        super.mergeVarintField(1, rawValue2);
                                    } else {
                                        this.currentFunctions_.addInt(rawValue2);
                                    }
                                }
                                input.popLimit(oldLimit);
                                break;
                            case 16:
                                this.bitField0_ |= 1;
                                this.currentFunctionsApplied_ = input.readBool();
                                break;
                            case 24:
                                if (!this.screenUnlockedFunctions_.isModifiable()) {
                                    this.screenUnlockedFunctions_ = GeneratedMessageLite.mutableCopy(this.screenUnlockedFunctions_);
                                }
                                int rawValue3 = input.readEnum();
                                if (Function.forNumber(rawValue3) != null) {
                                    this.screenUnlockedFunctions_.addInt(rawValue3);
                                    break;
                                } else {
                                    super.mergeVarintField(3, rawValue3);
                                    break;
                                }
                            case 26:
                                if (!this.screenUnlockedFunctions_.isModifiable()) {
                                    this.screenUnlockedFunctions_ = GeneratedMessageLite.mutableCopy(this.screenUnlockedFunctions_);
                                }
                                int oldLimit2 = input.pushLimit(input.readRawVarint32());
                                while (input.getBytesUntilLimit() > 0) {
                                    int rawValue4 = input.readEnum();
                                    if (Function.forNumber(rawValue4) == null) {
                                        super.mergeVarintField(3, rawValue4);
                                    } else {
                                        this.screenUnlockedFunctions_.addInt(rawValue4);
                                    }
                                }
                                input.popLimit(oldLimit2);
                                break;
                            case 32:
                                this.bitField0_ |= 2;
                                this.screenLocked_ = input.readBool();
                                break;
                            case 40:
                                this.bitField0_ |= 4;
                                this.connected_ = input.readBool();
                                break;
                            case 48:
                                this.bitField0_ |= 8;
                                this.configured_ = input.readBool();
                                break;
                            case 58:
                                UsbAccessoryProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder = (UsbAccessoryProto.Builder) this.currentAccessory_.toBuilder();
                                }
                                this.currentAccessory_ = (UsbAccessoryProto) input.readMessage(UsbAccessoryProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.currentAccessory_);
                                    this.currentAccessory_ = (UsbAccessoryProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 64:
                                this.bitField0_ |= 32;
                                this.hostConnected_ = input.readBool();
                                break;
                            case 72:
                                this.bitField0_ |= 64;
                                this.sourcePower_ = input.readBool();
                                break;
                            case 80:
                                this.bitField0_ |= 128;
                                this.sinkPower_ = input.readBool();
                                break;
                            case 88:
                                this.bitField0_ |= 256;
                                this.usbCharging_ = input.readBool();
                                break;
                            case 96:
                                this.bitField0_ |= 512;
                                this.hideUsbNotification_ = input.readBool();
                                break;
                            case 104:
                                this.bitField0_ |= 1024;
                                this.audioAccessoryConnected_ = input.readBool();
                                break;
                            case 112:
                                this.bitField0_ |= 2048;
                                this.adbEnabled_ = input.readBool();
                                break;
                            case 122:
                                String s = input.readString();
                                this.bitField0_ |= 4096;
                                this.kernelState_ = s;
                                break;
                            case 130:
                                String s2 = input.readString();
                                this.bitField0_ |= 8192;
                                this.kernelFunctionList_ = s2;
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
                    synchronized (UsbHandlerProto.class) {
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

    public static UsbHandlerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbHandlerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
