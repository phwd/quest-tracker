package android.view;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class DisplayInfoProto extends GeneratedMessageLite<DisplayInfoProto, Builder> implements DisplayInfoProtoOrBuilder {
    public static final int APP_HEIGHT_FIELD_NUMBER = 4;
    public static final int APP_WIDTH_FIELD_NUMBER = 3;
    private static final DisplayInfoProto DEFAULT_INSTANCE = new DisplayInfoProto();
    public static final int LOGICAL_HEIGHT_FIELD_NUMBER = 2;
    public static final int LOGICAL_WIDTH_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 5;
    private static volatile Parser<DisplayInfoProto> PARSER;
    private int appHeight_ = 0;
    private int appWidth_ = 0;
    private int bitField0_;
    private int logicalHeight_ = 0;
    private int logicalWidth_ = 0;
    private String name_ = "";

    private DisplayInfoProto() {
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public boolean hasLogicalWidth() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public int getLogicalWidth() {
        return this.logicalWidth_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLogicalWidth(int value) {
        this.bitField0_ |= 1;
        this.logicalWidth_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLogicalWidth() {
        this.bitField0_ &= -2;
        this.logicalWidth_ = 0;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public boolean hasLogicalHeight() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public int getLogicalHeight() {
        return this.logicalHeight_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLogicalHeight(int value) {
        this.bitField0_ |= 2;
        this.logicalHeight_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLogicalHeight() {
        this.bitField0_ &= -3;
        this.logicalHeight_ = 0;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public boolean hasAppWidth() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public int getAppWidth() {
        return this.appWidth_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppWidth(int value) {
        this.bitField0_ |= 4;
        this.appWidth_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppWidth() {
        this.bitField0_ &= -5;
        this.appWidth_ = 0;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public boolean hasAppHeight() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public int getAppHeight() {
        return this.appHeight_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppHeight(int value) {
        this.bitField0_ |= 8;
        this.appHeight_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppHeight() {
        this.bitField0_ &= -9;
        this.appHeight_ = 0;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.view.DisplayInfoProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -17;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.logicalWidth_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.logicalHeight_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.appWidth_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.appHeight_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getName());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.logicalWidth_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.logicalHeight_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.appWidth_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.appHeight_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getName());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DisplayInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DisplayInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DisplayInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayInfoProto parseFrom(InputStream input) throws IOException {
        return (DisplayInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DisplayInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (DisplayInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DisplayInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DisplayInfoProto, Builder> implements DisplayInfoProtoOrBuilder {
        private Builder() {
            super(DisplayInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public boolean hasLogicalWidth() {
            return ((DisplayInfoProto) this.instance).hasLogicalWidth();
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public int getLogicalWidth() {
            return ((DisplayInfoProto) this.instance).getLogicalWidth();
        }

        public Builder setLogicalWidth(int value) {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).setLogicalWidth(value);
            return this;
        }

        public Builder clearLogicalWidth() {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).clearLogicalWidth();
            return this;
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public boolean hasLogicalHeight() {
            return ((DisplayInfoProto) this.instance).hasLogicalHeight();
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public int getLogicalHeight() {
            return ((DisplayInfoProto) this.instance).getLogicalHeight();
        }

        public Builder setLogicalHeight(int value) {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).setLogicalHeight(value);
            return this;
        }

        public Builder clearLogicalHeight() {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).clearLogicalHeight();
            return this;
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public boolean hasAppWidth() {
            return ((DisplayInfoProto) this.instance).hasAppWidth();
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public int getAppWidth() {
            return ((DisplayInfoProto) this.instance).getAppWidth();
        }

        public Builder setAppWidth(int value) {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).setAppWidth(value);
            return this;
        }

        public Builder clearAppWidth() {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).clearAppWidth();
            return this;
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public boolean hasAppHeight() {
            return ((DisplayInfoProto) this.instance).hasAppHeight();
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public int getAppHeight() {
            return ((DisplayInfoProto) this.instance).getAppHeight();
        }

        public Builder setAppHeight(int value) {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).setAppHeight(value);
            return this;
        }

        public Builder clearAppHeight() {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).clearAppHeight();
            return this;
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public boolean hasName() {
            return ((DisplayInfoProto) this.instance).hasName();
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public String getName() {
            return ((DisplayInfoProto) this.instance).getName();
        }

        @Override // android.view.DisplayInfoProtoOrBuilder
        public ByteString getNameBytes() {
            return ((DisplayInfoProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((DisplayInfoProto) this.instance).setNameBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DisplayInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DisplayInfoProto other = (DisplayInfoProto) arg1;
                this.logicalWidth_ = visitor.visitInt(hasLogicalWidth(), this.logicalWidth_, other.hasLogicalWidth(), other.logicalWidth_);
                this.logicalHeight_ = visitor.visitInt(hasLogicalHeight(), this.logicalHeight_, other.hasLogicalHeight(), other.logicalHeight_);
                this.appWidth_ = visitor.visitInt(hasAppWidth(), this.appWidth_, other.hasAppWidth(), other.appWidth_);
                this.appHeight_ = visitor.visitInt(hasAppHeight(), this.appHeight_, other.hasAppHeight(), other.appHeight_);
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
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
                            this.logicalWidth_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.logicalHeight_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.appWidth_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.appHeight_ = input.readInt32();
                        } else if (tag == 42) {
                            String s = input.readString();
                            this.bitField0_ = 16 | this.bitField0_;
                            this.name_ = s;
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
                    synchronized (DisplayInfoProto.class) {
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

    public static DisplayInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DisplayInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
