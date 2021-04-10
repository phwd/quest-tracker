package android.providers.settings;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class SettingsOperationProto extends GeneratedMessageLite<SettingsOperationProto, Builder> implements SettingsOperationProtoOrBuilder {
    private static final SettingsOperationProto DEFAULT_INSTANCE = new SettingsOperationProto();
    public static final int OPERATION_FIELD_NUMBER = 2;
    private static volatile Parser<SettingsOperationProto> PARSER = null;
    public static final int SETTING_FIELD_NUMBER = 3;
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    private int bitField0_;
    private String operation_ = "";
    private String setting_ = "";
    private long timestamp_ = 0;

    private SettingsOperationProto() {
    }

    @Override // android.providers.settings.SettingsOperationProtoOrBuilder
    public boolean hasTimestamp() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.providers.settings.SettingsOperationProtoOrBuilder
    public long getTimestamp() {
        return this.timestamp_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimestamp(long value) {
        this.bitField0_ |= 1;
        this.timestamp_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimestamp() {
        this.bitField0_ &= -2;
        this.timestamp_ = 0;
    }

    @Override // android.providers.settings.SettingsOperationProtoOrBuilder
    public boolean hasOperation() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.providers.settings.SettingsOperationProtoOrBuilder
    public String getOperation() {
        return this.operation_;
    }

    @Override // android.providers.settings.SettingsOperationProtoOrBuilder
    public ByteString getOperationBytes() {
        return ByteString.copyFromUtf8(this.operation_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOperation(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.operation_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOperation() {
        this.bitField0_ &= -3;
        this.operation_ = getDefaultInstance().getOperation();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOperationBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.operation_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.providers.settings.SettingsOperationProtoOrBuilder
    public boolean hasSetting() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.providers.settings.SettingsOperationProtoOrBuilder
    public String getSetting() {
        return this.setting_;
    }

    @Override // android.providers.settings.SettingsOperationProtoOrBuilder
    public ByteString getSettingBytes() {
        return ByteString.copyFromUtf8(this.setting_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSetting(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.setting_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSetting() {
        this.bitField0_ &= -5;
        this.setting_ = getDefaultInstance().getSetting();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.setting_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.timestamp_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getOperation());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getSetting());
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.timestamp_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getOperation());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getSetting());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static SettingsOperationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SettingsOperationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SettingsOperationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SettingsOperationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SettingsOperationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SettingsOperationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SettingsOperationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SettingsOperationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SettingsOperationProto parseFrom(InputStream input) throws IOException {
        return (SettingsOperationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingsOperationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingsOperationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SettingsOperationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SettingsOperationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingsOperationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingsOperationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SettingsOperationProto parseFrom(CodedInputStream input) throws IOException {
        return (SettingsOperationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingsOperationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingsOperationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SettingsOperationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SettingsOperationProto, Builder> implements SettingsOperationProtoOrBuilder {
        private Builder() {
            super(SettingsOperationProto.DEFAULT_INSTANCE);
        }

        @Override // android.providers.settings.SettingsOperationProtoOrBuilder
        public boolean hasTimestamp() {
            return ((SettingsOperationProto) this.instance).hasTimestamp();
        }

        @Override // android.providers.settings.SettingsOperationProtoOrBuilder
        public long getTimestamp() {
            return ((SettingsOperationProto) this.instance).getTimestamp();
        }

        public Builder setTimestamp(long value) {
            copyOnWrite();
            ((SettingsOperationProto) this.instance).setTimestamp(value);
            return this;
        }

        public Builder clearTimestamp() {
            copyOnWrite();
            ((SettingsOperationProto) this.instance).clearTimestamp();
            return this;
        }

        @Override // android.providers.settings.SettingsOperationProtoOrBuilder
        public boolean hasOperation() {
            return ((SettingsOperationProto) this.instance).hasOperation();
        }

        @Override // android.providers.settings.SettingsOperationProtoOrBuilder
        public String getOperation() {
            return ((SettingsOperationProto) this.instance).getOperation();
        }

        @Override // android.providers.settings.SettingsOperationProtoOrBuilder
        public ByteString getOperationBytes() {
            return ((SettingsOperationProto) this.instance).getOperationBytes();
        }

        public Builder setOperation(String value) {
            copyOnWrite();
            ((SettingsOperationProto) this.instance).setOperation(value);
            return this;
        }

        public Builder clearOperation() {
            copyOnWrite();
            ((SettingsOperationProto) this.instance).clearOperation();
            return this;
        }

        public Builder setOperationBytes(ByteString value) {
            copyOnWrite();
            ((SettingsOperationProto) this.instance).setOperationBytes(value);
            return this;
        }

        @Override // android.providers.settings.SettingsOperationProtoOrBuilder
        public boolean hasSetting() {
            return ((SettingsOperationProto) this.instance).hasSetting();
        }

        @Override // android.providers.settings.SettingsOperationProtoOrBuilder
        public String getSetting() {
            return ((SettingsOperationProto) this.instance).getSetting();
        }

        @Override // android.providers.settings.SettingsOperationProtoOrBuilder
        public ByteString getSettingBytes() {
            return ((SettingsOperationProto) this.instance).getSettingBytes();
        }

        public Builder setSetting(String value) {
            copyOnWrite();
            ((SettingsOperationProto) this.instance).setSetting(value);
            return this;
        }

        public Builder clearSetting() {
            copyOnWrite();
            ((SettingsOperationProto) this.instance).clearSetting();
            return this;
        }

        public Builder setSettingBytes(ByteString value) {
            copyOnWrite();
            ((SettingsOperationProto) this.instance).setSettingBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new SettingsOperationProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SettingsOperationProto other = (SettingsOperationProto) arg1;
                this.timestamp_ = visitor.visitLong(hasTimestamp(), this.timestamp_, other.hasTimestamp(), other.timestamp_);
                this.operation_ = visitor.visitString(hasOperation(), this.operation_, other.hasOperation(), other.operation_);
                this.setting_ = visitor.visitString(hasSetting(), this.setting_, other.hasSetting(), other.setting_);
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
                            this.timestamp_ = input.readInt64();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.operation_ = s;
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.setting_ = s2;
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
                    synchronized (SettingsOperationProto.class) {
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

    public static SettingsOperationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SettingsOperationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
