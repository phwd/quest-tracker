package android.service.print;

import android.service.print.MarginsProto;
import android.service.print.ResolutionProto;
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

public final class PrintAttributesProto extends GeneratedMessageLite<PrintAttributesProto, Builder> implements PrintAttributesProtoOrBuilder {
    public static final int COLOR_MODE_FIELD_NUMBER = 5;
    private static final PrintAttributesProto DEFAULT_INSTANCE = new PrintAttributesProto();
    public static final int DUPLEX_MODE_FIELD_NUMBER = 6;
    public static final int IS_PORTRAIT_FIELD_NUMBER = 2;
    public static final int MEDIA_SIZE_FIELD_NUMBER = 1;
    public static final int MIN_MARGINS_FIELD_NUMBER = 4;
    private static volatile Parser<PrintAttributesProto> PARSER = null;
    public static final int RESOLUTION_FIELD_NUMBER = 3;
    private int bitField0_;
    private int colorMode_ = 0;
    private int duplexMode_ = 0;
    private boolean isPortrait_ = false;
    private ResolutionProto mediaSize_;
    private MarginsProto minMargins_;
    private ResolutionProto resolution_;

    private PrintAttributesProto() {
    }

    public enum ColorMode implements Internal.EnumLite {
        __COLOR_MODE_UNUSED(0),
        COLOR_MODE_MONOCHROME(1),
        COLOR_MODE_COLOR(2);
        
        public static final int COLOR_MODE_COLOR_VALUE = 2;
        public static final int COLOR_MODE_MONOCHROME_VALUE = 1;
        public static final int __COLOR_MODE_UNUSED_VALUE = 0;
        private static final Internal.EnumLiteMap<ColorMode> internalValueMap = new Internal.EnumLiteMap<ColorMode>() {
            /* class android.service.print.PrintAttributesProto.ColorMode.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ColorMode findValueByNumber(int number) {
                return ColorMode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ColorMode valueOf(int value2) {
            return forNumber(value2);
        }

        public static ColorMode forNumber(int value2) {
            if (value2 == 0) {
                return __COLOR_MODE_UNUSED;
            }
            if (value2 == 1) {
                return COLOR_MODE_MONOCHROME;
            }
            if (value2 != 2) {
                return null;
            }
            return COLOR_MODE_COLOR;
        }

        public static Internal.EnumLiteMap<ColorMode> internalGetValueMap() {
            return internalValueMap;
        }

        private ColorMode(int value2) {
            this.value = value2;
        }
    }

    public enum DuplexMode implements Internal.EnumLite {
        __DUPLEX_MODE_UNUSED(0),
        DUPLEX_MODE_NONE(1),
        DUPLEX_MODE_LONG_EDGE(2),
        DUPLEX_MODE_SHORT_EDGE(4);
        
        public static final int DUPLEX_MODE_LONG_EDGE_VALUE = 2;
        public static final int DUPLEX_MODE_NONE_VALUE = 1;
        public static final int DUPLEX_MODE_SHORT_EDGE_VALUE = 4;
        public static final int __DUPLEX_MODE_UNUSED_VALUE = 0;
        private static final Internal.EnumLiteMap<DuplexMode> internalValueMap = new Internal.EnumLiteMap<DuplexMode>() {
            /* class android.service.print.PrintAttributesProto.DuplexMode.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DuplexMode findValueByNumber(int number) {
                return DuplexMode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static DuplexMode valueOf(int value2) {
            return forNumber(value2);
        }

        public static DuplexMode forNumber(int value2) {
            if (value2 == 0) {
                return __DUPLEX_MODE_UNUSED;
            }
            if (value2 == 1) {
                return DUPLEX_MODE_NONE;
            }
            if (value2 == 2) {
                return DUPLEX_MODE_LONG_EDGE;
            }
            if (value2 != 4) {
                return null;
            }
            return DUPLEX_MODE_SHORT_EDGE;
        }

        public static Internal.EnumLiteMap<DuplexMode> internalGetValueMap() {
            return internalValueMap;
        }

        private DuplexMode(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public boolean hasMediaSize() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public ResolutionProto getMediaSize() {
        ResolutionProto resolutionProto = this.mediaSize_;
        return resolutionProto == null ? ResolutionProto.getDefaultInstance() : resolutionProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMediaSize(ResolutionProto value) {
        if (value != null) {
            this.mediaSize_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMediaSize(ResolutionProto.Builder builderForValue) {
        this.mediaSize_ = (ResolutionProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMediaSize(ResolutionProto value) {
        ResolutionProto resolutionProto = this.mediaSize_;
        if (resolutionProto == null || resolutionProto == ResolutionProto.getDefaultInstance()) {
            this.mediaSize_ = value;
        } else {
            this.mediaSize_ = (ResolutionProto) ((ResolutionProto.Builder) ResolutionProto.newBuilder(this.mediaSize_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMediaSize() {
        this.mediaSize_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public boolean hasIsPortrait() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public boolean getIsPortrait() {
        return this.isPortrait_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsPortrait(boolean value) {
        this.bitField0_ |= 2;
        this.isPortrait_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsPortrait() {
        this.bitField0_ &= -3;
        this.isPortrait_ = false;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public boolean hasResolution() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public ResolutionProto getResolution() {
        ResolutionProto resolutionProto = this.resolution_;
        return resolutionProto == null ? ResolutionProto.getDefaultInstance() : resolutionProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResolution(ResolutionProto value) {
        if (value != null) {
            this.resolution_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResolution(ResolutionProto.Builder builderForValue) {
        this.resolution_ = (ResolutionProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeResolution(ResolutionProto value) {
        ResolutionProto resolutionProto = this.resolution_;
        if (resolutionProto == null || resolutionProto == ResolutionProto.getDefaultInstance()) {
            this.resolution_ = value;
        } else {
            this.resolution_ = (ResolutionProto) ((ResolutionProto.Builder) ResolutionProto.newBuilder(this.resolution_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearResolution() {
        this.resolution_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public boolean hasMinMargins() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public MarginsProto getMinMargins() {
        MarginsProto marginsProto = this.minMargins_;
        return marginsProto == null ? MarginsProto.getDefaultInstance() : marginsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinMargins(MarginsProto value) {
        if (value != null) {
            this.minMargins_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinMargins(MarginsProto.Builder builderForValue) {
        this.minMargins_ = (MarginsProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMinMargins(MarginsProto value) {
        MarginsProto marginsProto = this.minMargins_;
        if (marginsProto == null || marginsProto == MarginsProto.getDefaultInstance()) {
            this.minMargins_ = value;
        } else {
            this.minMargins_ = (MarginsProto) ((MarginsProto.Builder) MarginsProto.newBuilder(this.minMargins_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinMargins() {
        this.minMargins_ = null;
        this.bitField0_ &= -9;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public boolean hasColorMode() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public ColorMode getColorMode() {
        ColorMode result = ColorMode.forNumber(this.colorMode_);
        return result == null ? ColorMode.__COLOR_MODE_UNUSED : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setColorMode(ColorMode value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.colorMode_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearColorMode() {
        this.bitField0_ &= -17;
        this.colorMode_ = 0;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public boolean hasDuplexMode() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.print.PrintAttributesProtoOrBuilder
    public DuplexMode getDuplexMode() {
        DuplexMode result = DuplexMode.forNumber(this.duplexMode_);
        return result == null ? DuplexMode.__DUPLEX_MODE_UNUSED : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDuplexMode(DuplexMode value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.duplexMode_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDuplexMode() {
        this.bitField0_ &= -33;
        this.duplexMode_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getMediaSize());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isPortrait_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getResolution());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getMinMargins());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeEnum(5, this.colorMode_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeEnum(6, this.duplexMode_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getMediaSize());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isPortrait_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getResolution());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getMinMargins());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeEnumSize(5, this.colorMode_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeEnumSize(6, this.duplexMode_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PrintAttributesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrintAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintAttributesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintAttributesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrintAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintAttributesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintAttributesProto parseFrom(InputStream input) throws IOException {
        return (PrintAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintAttributesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintAttributesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrintAttributesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintAttributesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintAttributesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintAttributesProto parseFrom(CodedInputStream input) throws IOException {
        return (PrintAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintAttributesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrintAttributesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrintAttributesProto, Builder> implements PrintAttributesProtoOrBuilder {
        private Builder() {
            super(PrintAttributesProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public boolean hasMediaSize() {
            return ((PrintAttributesProto) this.instance).hasMediaSize();
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public ResolutionProto getMediaSize() {
            return ((PrintAttributesProto) this.instance).getMediaSize();
        }

        public Builder setMediaSize(ResolutionProto value) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).setMediaSize((PrintAttributesProto) value);
            return this;
        }

        public Builder setMediaSize(ResolutionProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).setMediaSize((PrintAttributesProto) builderForValue);
            return this;
        }

        public Builder mergeMediaSize(ResolutionProto value) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).mergeMediaSize(value);
            return this;
        }

        public Builder clearMediaSize() {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).clearMediaSize();
            return this;
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public boolean hasIsPortrait() {
            return ((PrintAttributesProto) this.instance).hasIsPortrait();
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public boolean getIsPortrait() {
            return ((PrintAttributesProto) this.instance).getIsPortrait();
        }

        public Builder setIsPortrait(boolean value) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).setIsPortrait(value);
            return this;
        }

        public Builder clearIsPortrait() {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).clearIsPortrait();
            return this;
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public boolean hasResolution() {
            return ((PrintAttributesProto) this.instance).hasResolution();
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public ResolutionProto getResolution() {
            return ((PrintAttributesProto) this.instance).getResolution();
        }

        public Builder setResolution(ResolutionProto value) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).setResolution((PrintAttributesProto) value);
            return this;
        }

        public Builder setResolution(ResolutionProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).setResolution((PrintAttributesProto) builderForValue);
            return this;
        }

        public Builder mergeResolution(ResolutionProto value) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).mergeResolution(value);
            return this;
        }

        public Builder clearResolution() {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).clearResolution();
            return this;
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public boolean hasMinMargins() {
            return ((PrintAttributesProto) this.instance).hasMinMargins();
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public MarginsProto getMinMargins() {
            return ((PrintAttributesProto) this.instance).getMinMargins();
        }

        public Builder setMinMargins(MarginsProto value) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).setMinMargins((PrintAttributesProto) value);
            return this;
        }

        public Builder setMinMargins(MarginsProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).setMinMargins((PrintAttributesProto) builderForValue);
            return this;
        }

        public Builder mergeMinMargins(MarginsProto value) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).mergeMinMargins(value);
            return this;
        }

        public Builder clearMinMargins() {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).clearMinMargins();
            return this;
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public boolean hasColorMode() {
            return ((PrintAttributesProto) this.instance).hasColorMode();
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public ColorMode getColorMode() {
            return ((PrintAttributesProto) this.instance).getColorMode();
        }

        public Builder setColorMode(ColorMode value) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).setColorMode(value);
            return this;
        }

        public Builder clearColorMode() {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).clearColorMode();
            return this;
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public boolean hasDuplexMode() {
            return ((PrintAttributesProto) this.instance).hasDuplexMode();
        }

        @Override // android.service.print.PrintAttributesProtoOrBuilder
        public DuplexMode getDuplexMode() {
            return ((PrintAttributesProto) this.instance).getDuplexMode();
        }

        public Builder setDuplexMode(DuplexMode value) {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).setDuplexMode(value);
            return this;
        }

        public Builder clearDuplexMode() {
            copyOnWrite();
            ((PrintAttributesProto) this.instance).clearDuplexMode();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrintAttributesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrintAttributesProto other = (PrintAttributesProto) arg1;
                this.mediaSize_ = (ResolutionProto) visitor.visitMessage(this.mediaSize_, other.mediaSize_);
                this.isPortrait_ = visitor.visitBoolean(hasIsPortrait(), this.isPortrait_, other.hasIsPortrait(), other.isPortrait_);
                this.resolution_ = (ResolutionProto) visitor.visitMessage(this.resolution_, other.resolution_);
                this.minMargins_ = (MarginsProto) visitor.visitMessage(this.minMargins_, other.minMargins_);
                this.colorMode_ = visitor.visitInt(hasColorMode(), this.colorMode_, other.hasColorMode(), other.colorMode_);
                this.duplexMode_ = visitor.visitInt(hasDuplexMode(), this.duplexMode_, other.hasDuplexMode(), other.duplexMode_);
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
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            ResolutionProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ResolutionProto.Builder) this.mediaSize_.toBuilder();
                            }
                            this.mediaSize_ = (ResolutionProto) input.readMessage(ResolutionProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.mediaSize_);
                                this.mediaSize_ = (ResolutionProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.isPortrait_ = input.readBool();
                        } else if (tag == 26) {
                            ResolutionProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder2 = (ResolutionProto.Builder) this.resolution_.toBuilder();
                            }
                            this.resolution_ = (ResolutionProto) input.readMessage(ResolutionProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.resolution_);
                                this.resolution_ = (ResolutionProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 34) {
                            MarginsProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder3 = (MarginsProto.Builder) this.minMargins_.toBuilder();
                            }
                            this.minMargins_ = (MarginsProto) input.readMessage(MarginsProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.minMargins_);
                                this.minMargins_ = (MarginsProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (tag == 40) {
                            int rawValue = input.readEnum();
                            if (ColorMode.forNumber(rawValue) == null) {
                                super.mergeVarintField(5, rawValue);
                            } else {
                                this.bitField0_ = 16 | this.bitField0_;
                                this.colorMode_ = rawValue;
                            }
                        } else if (tag == 48) {
                            int rawValue2 = input.readEnum();
                            if (DuplexMode.forNumber(rawValue2) == null) {
                                super.mergeVarintField(6, rawValue2);
                            } else {
                                this.bitField0_ |= 32;
                                this.duplexMode_ = rawValue2;
                            }
                        } else if (!parseUnknownField(tag, input)) {
                            done = true;
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
                    synchronized (PrintAttributesProto.class) {
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

    public static PrintAttributesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrintAttributesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
