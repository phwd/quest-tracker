package android.service.appwidget;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WidgetProto extends GeneratedMessageLite<WidgetProto, Builder> implements WidgetProtoOrBuilder {
    private static final WidgetProto DEFAULT_INSTANCE = new WidgetProto();
    public static final int HOSTPACKAGE_FIELD_NUMBER = 3;
    public static final int ISCROSSPROFILE_FIELD_NUMBER = 1;
    public static final int ISHOSTSTOPPED_FIELD_NUMBER = 2;
    public static final int MAXHEIGHT_FIELD_NUMBER = 9;
    public static final int MAXWIDTH_FIELD_NUMBER = 8;
    public static final int MINHEIGHT_FIELD_NUMBER = 7;
    public static final int MINWIDTH_FIELD_NUMBER = 6;
    private static volatile Parser<WidgetProto> PARSER = null;
    public static final int PROVIDERCLASS_FIELD_NUMBER = 5;
    public static final int PROVIDERPACKAGE_FIELD_NUMBER = 4;
    private int bitField0_;
    private String hostPackage_ = "";
    private boolean isCrossProfile_ = false;
    private boolean isHostStopped_ = false;
    private int maxHeight_ = 0;
    private int maxWidth_ = 0;
    private int minHeight_ = 0;
    private int minWidth_ = 0;
    private String providerClass_ = "";
    private String providerPackage_ = "";

    private WidgetProto() {
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean hasIsCrossProfile() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean getIsCrossProfile() {
        return this.isCrossProfile_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsCrossProfile(boolean value) {
        this.bitField0_ |= 1;
        this.isCrossProfile_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsCrossProfile() {
        this.bitField0_ &= -2;
        this.isCrossProfile_ = false;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean hasIsHostStopped() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean getIsHostStopped() {
        return this.isHostStopped_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsHostStopped(boolean value) {
        this.bitField0_ |= 2;
        this.isHostStopped_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsHostStopped() {
        this.bitField0_ &= -3;
        this.isHostStopped_ = false;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean hasHostPackage() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public String getHostPackage() {
        return this.hostPackage_;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public ByteString getHostPackageBytes() {
        return ByteString.copyFromUtf8(this.hostPackage_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHostPackage(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.hostPackage_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHostPackage() {
        this.bitField0_ &= -5;
        this.hostPackage_ = getDefaultInstance().getHostPackage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHostPackageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.hostPackage_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean hasProviderPackage() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public String getProviderPackage() {
        return this.providerPackage_;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public ByteString getProviderPackageBytes() {
        return ByteString.copyFromUtf8(this.providerPackage_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProviderPackage(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.providerPackage_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProviderPackage() {
        this.bitField0_ &= -9;
        this.providerPackage_ = getDefaultInstance().getProviderPackage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProviderPackageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.providerPackage_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean hasProviderClass() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public String getProviderClass() {
        return this.providerClass_;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public ByteString getProviderClassBytes() {
        return ByteString.copyFromUtf8(this.providerClass_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProviderClass(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.providerClass_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProviderClass() {
        this.bitField0_ &= -17;
        this.providerClass_ = getDefaultInstance().getProviderClass();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProviderClassBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.providerClass_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean hasMinWidth() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public int getMinWidth() {
        return this.minWidth_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinWidth(int value) {
        this.bitField0_ |= 32;
        this.minWidth_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinWidth() {
        this.bitField0_ &= -33;
        this.minWidth_ = 0;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean hasMinHeight() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public int getMinHeight() {
        return this.minHeight_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinHeight(int value) {
        this.bitField0_ |= 64;
        this.minHeight_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinHeight() {
        this.bitField0_ &= -65;
        this.minHeight_ = 0;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean hasMaxWidth() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public int getMaxWidth() {
        return this.maxWidth_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxWidth(int value) {
        this.bitField0_ |= 128;
        this.maxWidth_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxWidth() {
        this.bitField0_ &= -129;
        this.maxWidth_ = 0;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public boolean hasMaxHeight() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.appwidget.WidgetProtoOrBuilder
    public int getMaxHeight() {
        return this.maxHeight_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxHeight(int value) {
        this.bitField0_ |= 256;
        this.maxHeight_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxHeight() {
        this.bitField0_ &= -257;
        this.maxHeight_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.isCrossProfile_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isHostStopped_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getHostPackage());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getProviderPackage());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getProviderClass());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.minWidth_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.minHeight_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.maxWidth_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeInt32(9, this.maxHeight_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isCrossProfile_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isHostStopped_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getHostPackage());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getProviderPackage());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getProviderClass());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.minWidth_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.minHeight_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.maxWidth_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeInt32Size(9, this.maxHeight_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WidgetProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WidgetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WidgetProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WidgetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WidgetProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WidgetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WidgetProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WidgetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WidgetProto parseFrom(InputStream input) throws IOException {
        return (WidgetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WidgetProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WidgetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WidgetProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WidgetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WidgetProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WidgetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WidgetProto parseFrom(CodedInputStream input) throws IOException {
        return (WidgetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WidgetProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WidgetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WidgetProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WidgetProto, Builder> implements WidgetProtoOrBuilder {
        private Builder() {
            super(WidgetProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean hasIsCrossProfile() {
            return ((WidgetProto) this.instance).hasIsCrossProfile();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean getIsCrossProfile() {
            return ((WidgetProto) this.instance).getIsCrossProfile();
        }

        public Builder setIsCrossProfile(boolean value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setIsCrossProfile(value);
            return this;
        }

        public Builder clearIsCrossProfile() {
            copyOnWrite();
            ((WidgetProto) this.instance).clearIsCrossProfile();
            return this;
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean hasIsHostStopped() {
            return ((WidgetProto) this.instance).hasIsHostStopped();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean getIsHostStopped() {
            return ((WidgetProto) this.instance).getIsHostStopped();
        }

        public Builder setIsHostStopped(boolean value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setIsHostStopped(value);
            return this;
        }

        public Builder clearIsHostStopped() {
            copyOnWrite();
            ((WidgetProto) this.instance).clearIsHostStopped();
            return this;
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean hasHostPackage() {
            return ((WidgetProto) this.instance).hasHostPackage();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public String getHostPackage() {
            return ((WidgetProto) this.instance).getHostPackage();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public ByteString getHostPackageBytes() {
            return ((WidgetProto) this.instance).getHostPackageBytes();
        }

        public Builder setHostPackage(String value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setHostPackage(value);
            return this;
        }

        public Builder clearHostPackage() {
            copyOnWrite();
            ((WidgetProto) this.instance).clearHostPackage();
            return this;
        }

        public Builder setHostPackageBytes(ByteString value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setHostPackageBytes(value);
            return this;
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean hasProviderPackage() {
            return ((WidgetProto) this.instance).hasProviderPackage();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public String getProviderPackage() {
            return ((WidgetProto) this.instance).getProviderPackage();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public ByteString getProviderPackageBytes() {
            return ((WidgetProto) this.instance).getProviderPackageBytes();
        }

        public Builder setProviderPackage(String value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setProviderPackage(value);
            return this;
        }

        public Builder clearProviderPackage() {
            copyOnWrite();
            ((WidgetProto) this.instance).clearProviderPackage();
            return this;
        }

        public Builder setProviderPackageBytes(ByteString value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setProviderPackageBytes(value);
            return this;
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean hasProviderClass() {
            return ((WidgetProto) this.instance).hasProviderClass();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public String getProviderClass() {
            return ((WidgetProto) this.instance).getProviderClass();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public ByteString getProviderClassBytes() {
            return ((WidgetProto) this.instance).getProviderClassBytes();
        }

        public Builder setProviderClass(String value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setProviderClass(value);
            return this;
        }

        public Builder clearProviderClass() {
            copyOnWrite();
            ((WidgetProto) this.instance).clearProviderClass();
            return this;
        }

        public Builder setProviderClassBytes(ByteString value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setProviderClassBytes(value);
            return this;
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean hasMinWidth() {
            return ((WidgetProto) this.instance).hasMinWidth();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public int getMinWidth() {
            return ((WidgetProto) this.instance).getMinWidth();
        }

        public Builder setMinWidth(int value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setMinWidth(value);
            return this;
        }

        public Builder clearMinWidth() {
            copyOnWrite();
            ((WidgetProto) this.instance).clearMinWidth();
            return this;
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean hasMinHeight() {
            return ((WidgetProto) this.instance).hasMinHeight();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public int getMinHeight() {
            return ((WidgetProto) this.instance).getMinHeight();
        }

        public Builder setMinHeight(int value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setMinHeight(value);
            return this;
        }

        public Builder clearMinHeight() {
            copyOnWrite();
            ((WidgetProto) this.instance).clearMinHeight();
            return this;
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean hasMaxWidth() {
            return ((WidgetProto) this.instance).hasMaxWidth();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public int getMaxWidth() {
            return ((WidgetProto) this.instance).getMaxWidth();
        }

        public Builder setMaxWidth(int value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setMaxWidth(value);
            return this;
        }

        public Builder clearMaxWidth() {
            copyOnWrite();
            ((WidgetProto) this.instance).clearMaxWidth();
            return this;
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public boolean hasMaxHeight() {
            return ((WidgetProto) this.instance).hasMaxHeight();
        }

        @Override // android.service.appwidget.WidgetProtoOrBuilder
        public int getMaxHeight() {
            return ((WidgetProto) this.instance).getMaxHeight();
        }

        public Builder setMaxHeight(int value) {
            copyOnWrite();
            ((WidgetProto) this.instance).setMaxHeight(value);
            return this;
        }

        public Builder clearMaxHeight() {
            copyOnWrite();
            ((WidgetProto) this.instance).clearMaxHeight();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WidgetProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WidgetProto other = (WidgetProto) arg1;
                this.isCrossProfile_ = visitor.visitBoolean(hasIsCrossProfile(), this.isCrossProfile_, other.hasIsCrossProfile(), other.isCrossProfile_);
                this.isHostStopped_ = visitor.visitBoolean(hasIsHostStopped(), this.isHostStopped_, other.hasIsHostStopped(), other.isHostStopped_);
                this.hostPackage_ = visitor.visitString(hasHostPackage(), this.hostPackage_, other.hasHostPackage(), other.hostPackage_);
                this.providerPackage_ = visitor.visitString(hasProviderPackage(), this.providerPackage_, other.hasProviderPackage(), other.providerPackage_);
                this.providerClass_ = visitor.visitString(hasProviderClass(), this.providerClass_, other.hasProviderClass(), other.providerClass_);
                this.minWidth_ = visitor.visitInt(hasMinWidth(), this.minWidth_, other.hasMinWidth(), other.minWidth_);
                this.minHeight_ = visitor.visitInt(hasMinHeight(), this.minHeight_, other.hasMinHeight(), other.minHeight_);
                this.maxWidth_ = visitor.visitInt(hasMaxWidth(), this.maxWidth_, other.hasMaxWidth(), other.maxWidth_);
                this.maxHeight_ = visitor.visitInt(hasMaxHeight(), this.maxHeight_, other.hasMaxHeight(), other.maxHeight_);
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
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.isCrossProfile_ = input.readBool();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.isHostStopped_ = input.readBool();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.hostPackage_ = s;
                        } else if (tag == 34) {
                            String s2 = input.readString();
                            this.bitField0_ = 8 | this.bitField0_;
                            this.providerPackage_ = s2;
                        } else if (tag == 42) {
                            String s3 = input.readString();
                            this.bitField0_ = 16 | this.bitField0_;
                            this.providerClass_ = s3;
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.minWidth_ = input.readInt32();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.minHeight_ = input.readInt32();
                        } else if (tag == 64) {
                            this.bitField0_ |= 128;
                            this.maxWidth_ = input.readInt32();
                        } else if (tag == 72) {
                            this.bitField0_ |= 256;
                            this.maxHeight_ = input.readInt32();
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
                    synchronized (WidgetProto.class) {
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

    public static WidgetProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WidgetProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
