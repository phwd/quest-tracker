package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UserPackageProto extends GeneratedMessageLite<UserPackageProto, Builder> implements UserPackageProtoOrBuilder {
    private static final UserPackageProto DEFAULT_INSTANCE = new UserPackageProto();
    public static final int PACKAGE_NAME_FIELD_NUMBER = 2;
    private static volatile Parser<UserPackageProto> PARSER = null;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private String packageName_ = "";
    private int userId_ = 0;

    private UserPackageProto() {
    }

    @Override // android.service.usb.UserPackageProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UserPackageProtoOrBuilder
    public int getUserId() {
        return this.userId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(int value) {
        this.bitField0_ |= 1;
        this.userId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserId() {
        this.bitField0_ &= -2;
        this.userId_ = 0;
    }

    @Override // android.service.usb.UserPackageProtoOrBuilder
    public boolean hasPackageName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UserPackageProtoOrBuilder
    public String getPackageName() {
        return this.packageName_;
    }

    @Override // android.service.usb.UserPackageProtoOrBuilder
    public ByteString getPackageNameBytes() {
        return ByteString.copyFromUtf8(this.packageName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.packageName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageName() {
        this.bitField0_ &= -3;
        this.packageName_ = getDefaultInstance().getPackageName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.packageName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.userId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getPackageName());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.userId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getPackageName());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UserPackageProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UserPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UserPackageProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UserPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UserPackageProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UserPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UserPackageProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UserPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UserPackageProto parseFrom(InputStream input) throws IOException {
        return (UserPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UserPackageProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UserPackageProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UserPackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UserPackageProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserPackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UserPackageProto parseFrom(CodedInputStream input) throws IOException {
        return (UserPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UserPackageProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UserPackageProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UserPackageProto, Builder> implements UserPackageProtoOrBuilder {
        private Builder() {
            super(UserPackageProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UserPackageProtoOrBuilder
        public boolean hasUserId() {
            return ((UserPackageProto) this.instance).hasUserId();
        }

        @Override // android.service.usb.UserPackageProtoOrBuilder
        public int getUserId() {
            return ((UserPackageProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((UserPackageProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((UserPackageProto) this.instance).clearUserId();
            return this;
        }

        @Override // android.service.usb.UserPackageProtoOrBuilder
        public boolean hasPackageName() {
            return ((UserPackageProto) this.instance).hasPackageName();
        }

        @Override // android.service.usb.UserPackageProtoOrBuilder
        public String getPackageName() {
            return ((UserPackageProto) this.instance).getPackageName();
        }

        @Override // android.service.usb.UserPackageProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ((UserPackageProto) this.instance).getPackageNameBytes();
        }

        public Builder setPackageName(String value) {
            copyOnWrite();
            ((UserPackageProto) this.instance).setPackageName(value);
            return this;
        }

        public Builder clearPackageName() {
            copyOnWrite();
            ((UserPackageProto) this.instance).clearPackageName();
            return this;
        }

        public Builder setPackageNameBytes(ByteString value) {
            copyOnWrite();
            ((UserPackageProto) this.instance).setPackageNameBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UserPackageProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UserPackageProto other = (UserPackageProto) arg1;
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
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
                            this.userId_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.packageName_ = s;
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
                    synchronized (UserPackageProto.class) {
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

    public static UserPackageProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UserPackageProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
