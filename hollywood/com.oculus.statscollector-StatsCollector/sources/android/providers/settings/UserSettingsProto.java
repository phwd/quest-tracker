package android.providers.settings;

import android.providers.settings.SecureSettingsProto;
import android.providers.settings.SystemSettingsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UserSettingsProto extends GeneratedMessageLite<UserSettingsProto, Builder> implements UserSettingsProtoOrBuilder {
    private static final UserSettingsProto DEFAULT_INSTANCE = new UserSettingsProto();
    private static volatile Parser<UserSettingsProto> PARSER = null;
    public static final int SECURE_SETTINGS_FIELD_NUMBER = 2;
    public static final int SYSTEM_SETTINGS_FIELD_NUMBER = 3;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private SecureSettingsProto secureSettings_;
    private SystemSettingsProto systemSettings_;
    private int userId_ = 0;

    private UserSettingsProto() {
    }

    @Override // android.providers.settings.UserSettingsProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.providers.settings.UserSettingsProtoOrBuilder
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

    @Override // android.providers.settings.UserSettingsProtoOrBuilder
    public boolean hasSecureSettings() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.providers.settings.UserSettingsProtoOrBuilder
    public SecureSettingsProto getSecureSettings() {
        SecureSettingsProto secureSettingsProto = this.secureSettings_;
        return secureSettingsProto == null ? SecureSettingsProto.getDefaultInstance() : secureSettingsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSecureSettings(SecureSettingsProto value) {
        if (value != null) {
            this.secureSettings_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSecureSettings(SecureSettingsProto.Builder builderForValue) {
        this.secureSettings_ = (SecureSettingsProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSecureSettings(SecureSettingsProto value) {
        SecureSettingsProto secureSettingsProto = this.secureSettings_;
        if (secureSettingsProto == null || secureSettingsProto == SecureSettingsProto.getDefaultInstance()) {
            this.secureSettings_ = value;
        } else {
            this.secureSettings_ = (SecureSettingsProto) ((SecureSettingsProto.Builder) SecureSettingsProto.newBuilder(this.secureSettings_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSecureSettings() {
        this.secureSettings_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.providers.settings.UserSettingsProtoOrBuilder
    public boolean hasSystemSettings() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.providers.settings.UserSettingsProtoOrBuilder
    public SystemSettingsProto getSystemSettings() {
        SystemSettingsProto systemSettingsProto = this.systemSettings_;
        return systemSettingsProto == null ? SystemSettingsProto.getDefaultInstance() : systemSettingsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemSettings(SystemSettingsProto value) {
        if (value != null) {
            this.systemSettings_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemSettings(SystemSettingsProto.Builder builderForValue) {
        this.systemSettings_ = (SystemSettingsProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSystemSettings(SystemSettingsProto value) {
        SystemSettingsProto systemSettingsProto = this.systemSettings_;
        if (systemSettingsProto == null || systemSettingsProto == SystemSettingsProto.getDefaultInstance()) {
            this.systemSettings_ = value;
        } else {
            this.systemSettings_ = (SystemSettingsProto) ((SystemSettingsProto.Builder) SystemSettingsProto.newBuilder(this.systemSettings_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemSettings() {
        this.systemSettings_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.userId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getSecureSettings());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getSystemSettings());
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
            size2 += CodedOutputStream.computeMessageSize(2, getSecureSettings());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getSystemSettings());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UserSettingsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UserSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UserSettingsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UserSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UserSettingsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UserSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UserSettingsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UserSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UserSettingsProto parseFrom(InputStream input) throws IOException {
        return (UserSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UserSettingsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UserSettingsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UserSettingsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UserSettingsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserSettingsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UserSettingsProto parseFrom(CodedInputStream input) throws IOException {
        return (UserSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UserSettingsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UserSettingsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UserSettingsProto, Builder> implements UserSettingsProtoOrBuilder {
        private Builder() {
            super(UserSettingsProto.DEFAULT_INSTANCE);
        }

        @Override // android.providers.settings.UserSettingsProtoOrBuilder
        public boolean hasUserId() {
            return ((UserSettingsProto) this.instance).hasUserId();
        }

        @Override // android.providers.settings.UserSettingsProtoOrBuilder
        public int getUserId() {
            return ((UserSettingsProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((UserSettingsProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((UserSettingsProto) this.instance).clearUserId();
            return this;
        }

        @Override // android.providers.settings.UserSettingsProtoOrBuilder
        public boolean hasSecureSettings() {
            return ((UserSettingsProto) this.instance).hasSecureSettings();
        }

        @Override // android.providers.settings.UserSettingsProtoOrBuilder
        public SecureSettingsProto getSecureSettings() {
            return ((UserSettingsProto) this.instance).getSecureSettings();
        }

        public Builder setSecureSettings(SecureSettingsProto value) {
            copyOnWrite();
            ((UserSettingsProto) this.instance).setSecureSettings((UserSettingsProto) value);
            return this;
        }

        public Builder setSecureSettings(SecureSettingsProto.Builder builderForValue) {
            copyOnWrite();
            ((UserSettingsProto) this.instance).setSecureSettings((UserSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeSecureSettings(SecureSettingsProto value) {
            copyOnWrite();
            ((UserSettingsProto) this.instance).mergeSecureSettings(value);
            return this;
        }

        public Builder clearSecureSettings() {
            copyOnWrite();
            ((UserSettingsProto) this.instance).clearSecureSettings();
            return this;
        }

        @Override // android.providers.settings.UserSettingsProtoOrBuilder
        public boolean hasSystemSettings() {
            return ((UserSettingsProto) this.instance).hasSystemSettings();
        }

        @Override // android.providers.settings.UserSettingsProtoOrBuilder
        public SystemSettingsProto getSystemSettings() {
            return ((UserSettingsProto) this.instance).getSystemSettings();
        }

        public Builder setSystemSettings(SystemSettingsProto value) {
            copyOnWrite();
            ((UserSettingsProto) this.instance).setSystemSettings((UserSettingsProto) value);
            return this;
        }

        public Builder setSystemSettings(SystemSettingsProto.Builder builderForValue) {
            copyOnWrite();
            ((UserSettingsProto) this.instance).setSystemSettings((UserSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeSystemSettings(SystemSettingsProto value) {
            copyOnWrite();
            ((UserSettingsProto) this.instance).mergeSystemSettings(value);
            return this;
        }

        public Builder clearSystemSettings() {
            copyOnWrite();
            ((UserSettingsProto) this.instance).clearSystemSettings();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UserSettingsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UserSettingsProto other = (UserSettingsProto) arg1;
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.secureSettings_ = (SecureSettingsProto) visitor.visitMessage(this.secureSettings_, other.secureSettings_);
                this.systemSettings_ = (SystemSettingsProto) visitor.visitMessage(this.systemSettings_, other.systemSettings_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.userId_ = input.readInt32();
                        } else if (tag == 18) {
                            SecureSettingsProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = (SecureSettingsProto.Builder) this.secureSettings_.toBuilder();
                            }
                            this.secureSettings_ = (SecureSettingsProto) input.readMessage(SecureSettingsProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.secureSettings_);
                                this.secureSettings_ = (SecureSettingsProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            SystemSettingsProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder2 = (SystemSettingsProto.Builder) this.systemSettings_.toBuilder();
                            }
                            this.systemSettings_ = (SystemSettingsProto) input.readMessage(SystemSettingsProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.systemSettings_);
                                this.systemSettings_ = (SystemSettingsProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 4;
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
                    synchronized (UserSettingsProto.class) {
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

    public static UserSettingsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UserSettingsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
