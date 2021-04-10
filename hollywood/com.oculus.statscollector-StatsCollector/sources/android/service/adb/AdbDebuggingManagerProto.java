package android.service.adb;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AdbDebuggingManagerProto extends GeneratedMessageLite<AdbDebuggingManagerProto, Builder> implements AdbDebuggingManagerProtoOrBuilder {
    public static final int CONNECTED_TO_ADB_FIELD_NUMBER = 1;
    private static final AdbDebuggingManagerProto DEFAULT_INSTANCE = new AdbDebuggingManagerProto();
    public static final int KEYSTORE_FIELD_NUMBER = 5;
    public static final int LAST_KEY_RECEVIED_FIELD_NUMBER = 2;
    private static volatile Parser<AdbDebuggingManagerProto> PARSER = null;
    public static final int SYSTEM_KEYS_FIELD_NUMBER = 4;
    public static final int USER_KEYS_FIELD_NUMBER = 3;
    private int bitField0_;
    private boolean connectedToAdb_ = false;
    private String keystore_ = "";
    private String lastKeyRecevied_ = "";
    private String systemKeys_ = "";
    private String userKeys_ = "";

    private AdbDebuggingManagerProto() {
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public boolean hasConnectedToAdb() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public boolean getConnectedToAdb() {
        return this.connectedToAdb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnectedToAdb(boolean value) {
        this.bitField0_ |= 1;
        this.connectedToAdb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnectedToAdb() {
        this.bitField0_ &= -2;
        this.connectedToAdb_ = false;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public boolean hasLastKeyRecevied() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public String getLastKeyRecevied() {
        return this.lastKeyRecevied_;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public ByteString getLastKeyReceviedBytes() {
        return ByteString.copyFromUtf8(this.lastKeyRecevied_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastKeyRecevied(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.lastKeyRecevied_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastKeyRecevied() {
        this.bitField0_ &= -3;
        this.lastKeyRecevied_ = getDefaultInstance().getLastKeyRecevied();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastKeyReceviedBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.lastKeyRecevied_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public boolean hasUserKeys() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public String getUserKeys() {
        return this.userKeys_;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public ByteString getUserKeysBytes() {
        return ByteString.copyFromUtf8(this.userKeys_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserKeys(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.userKeys_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserKeys() {
        this.bitField0_ &= -5;
        this.userKeys_ = getDefaultInstance().getUserKeys();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserKeysBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.userKeys_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public boolean hasSystemKeys() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public String getSystemKeys() {
        return this.systemKeys_;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public ByteString getSystemKeysBytes() {
        return ByteString.copyFromUtf8(this.systemKeys_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemKeys(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.systemKeys_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemKeys() {
        this.bitField0_ &= -9;
        this.systemKeys_ = getDefaultInstance().getSystemKeys();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemKeysBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.systemKeys_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public boolean hasKeystore() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public String getKeystore() {
        return this.keystore_;
    }

    @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
    public ByteString getKeystoreBytes() {
        return ByteString.copyFromUtf8(this.keystore_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeystore(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.keystore_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeystore() {
        this.bitField0_ &= -17;
        this.keystore_ = getDefaultInstance().getKeystore();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeystoreBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.keystore_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.connectedToAdb_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getLastKeyRecevied());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getUserKeys());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getSystemKeys());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getKeystore());
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.connectedToAdb_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getLastKeyRecevied());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getUserKeys());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getSystemKeys());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getKeystore());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AdbDebuggingManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AdbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AdbDebuggingManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AdbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AdbDebuggingManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AdbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AdbDebuggingManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AdbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AdbDebuggingManagerProto parseFrom(InputStream input) throws IOException {
        return (AdbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AdbDebuggingManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AdbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AdbDebuggingManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AdbDebuggingManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AdbDebuggingManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AdbDebuggingManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AdbDebuggingManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (AdbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AdbDebuggingManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AdbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AdbDebuggingManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AdbDebuggingManagerProto, Builder> implements AdbDebuggingManagerProtoOrBuilder {
        private Builder() {
            super(AdbDebuggingManagerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public boolean hasConnectedToAdb() {
            return ((AdbDebuggingManagerProto) this.instance).hasConnectedToAdb();
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public boolean getConnectedToAdb() {
            return ((AdbDebuggingManagerProto) this.instance).getConnectedToAdb();
        }

        public Builder setConnectedToAdb(boolean value) {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).setConnectedToAdb(value);
            return this;
        }

        public Builder clearConnectedToAdb() {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).clearConnectedToAdb();
            return this;
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public boolean hasLastKeyRecevied() {
            return ((AdbDebuggingManagerProto) this.instance).hasLastKeyRecevied();
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public String getLastKeyRecevied() {
            return ((AdbDebuggingManagerProto) this.instance).getLastKeyRecevied();
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public ByteString getLastKeyReceviedBytes() {
            return ((AdbDebuggingManagerProto) this.instance).getLastKeyReceviedBytes();
        }

        public Builder setLastKeyRecevied(String value) {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).setLastKeyRecevied(value);
            return this;
        }

        public Builder clearLastKeyRecevied() {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).clearLastKeyRecevied();
            return this;
        }

        public Builder setLastKeyReceviedBytes(ByteString value) {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).setLastKeyReceviedBytes(value);
            return this;
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public boolean hasUserKeys() {
            return ((AdbDebuggingManagerProto) this.instance).hasUserKeys();
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public String getUserKeys() {
            return ((AdbDebuggingManagerProto) this.instance).getUserKeys();
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public ByteString getUserKeysBytes() {
            return ((AdbDebuggingManagerProto) this.instance).getUserKeysBytes();
        }

        public Builder setUserKeys(String value) {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).setUserKeys(value);
            return this;
        }

        public Builder clearUserKeys() {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).clearUserKeys();
            return this;
        }

        public Builder setUserKeysBytes(ByteString value) {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).setUserKeysBytes(value);
            return this;
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public boolean hasSystemKeys() {
            return ((AdbDebuggingManagerProto) this.instance).hasSystemKeys();
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public String getSystemKeys() {
            return ((AdbDebuggingManagerProto) this.instance).getSystemKeys();
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public ByteString getSystemKeysBytes() {
            return ((AdbDebuggingManagerProto) this.instance).getSystemKeysBytes();
        }

        public Builder setSystemKeys(String value) {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).setSystemKeys(value);
            return this;
        }

        public Builder clearSystemKeys() {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).clearSystemKeys();
            return this;
        }

        public Builder setSystemKeysBytes(ByteString value) {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).setSystemKeysBytes(value);
            return this;
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public boolean hasKeystore() {
            return ((AdbDebuggingManagerProto) this.instance).hasKeystore();
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public String getKeystore() {
            return ((AdbDebuggingManagerProto) this.instance).getKeystore();
        }

        @Override // android.service.adb.AdbDebuggingManagerProtoOrBuilder
        public ByteString getKeystoreBytes() {
            return ((AdbDebuggingManagerProto) this.instance).getKeystoreBytes();
        }

        public Builder setKeystore(String value) {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).setKeystore(value);
            return this;
        }

        public Builder clearKeystore() {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).clearKeystore();
            return this;
        }

        public Builder setKeystoreBytes(ByteString value) {
            copyOnWrite();
            ((AdbDebuggingManagerProto) this.instance).setKeystoreBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AdbDebuggingManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AdbDebuggingManagerProto other = (AdbDebuggingManagerProto) arg1;
                this.connectedToAdb_ = visitor.visitBoolean(hasConnectedToAdb(), this.connectedToAdb_, other.hasConnectedToAdb(), other.connectedToAdb_);
                this.lastKeyRecevied_ = visitor.visitString(hasLastKeyRecevied(), this.lastKeyRecevied_, other.hasLastKeyRecevied(), other.lastKeyRecevied_);
                this.userKeys_ = visitor.visitString(hasUserKeys(), this.userKeys_, other.hasUserKeys(), other.userKeys_);
                this.systemKeys_ = visitor.visitString(hasSystemKeys(), this.systemKeys_, other.hasSystemKeys(), other.systemKeys_);
                this.keystore_ = visitor.visitString(hasKeystore(), this.keystore_, other.hasKeystore(), other.keystore_);
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
                            this.connectedToAdb_ = input.readBool();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.lastKeyRecevied_ = s;
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.userKeys_ = s2;
                        } else if (tag == 34) {
                            String s3 = input.readString();
                            this.bitField0_ = 8 | this.bitField0_;
                            this.systemKeys_ = s3;
                        } else if (tag == 42) {
                            String s4 = input.readString();
                            this.bitField0_ |= 16;
                            this.keystore_ = s4;
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
                    synchronized (AdbDebuggingManagerProto.class) {
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

    public static AdbDebuggingManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AdbDebuggingManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
