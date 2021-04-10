package android.service.notification;

import android.content.ComponentNameProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ManagedServiceInfoProto extends GeneratedMessageLite<ManagedServiceInfoProto, Builder> implements ManagedServiceInfoProtoOrBuilder {
    public static final int COMPONENT_FIELD_NUMBER = 1;
    private static final ManagedServiceInfoProto DEFAULT_INSTANCE = new ManagedServiceInfoProto();
    public static final int IS_GUEST_FIELD_NUMBER = 5;
    public static final int IS_SYSTEM_FIELD_NUMBER = 4;
    private static volatile Parser<ManagedServiceInfoProto> PARSER = null;
    public static final int SERVICE_FIELD_NUMBER = 3;
    public static final int USER_ID_FIELD_NUMBER = 2;
    private int bitField0_;
    private ComponentNameProto component_;
    private boolean isGuest_ = false;
    private boolean isSystem_ = false;
    private String service_ = "";
    private int userId_ = 0;

    private ManagedServiceInfoProto() {
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public boolean hasComponent() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public ComponentNameProto getComponent() {
        ComponentNameProto componentNameProto = this.component_;
        return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponent(ComponentNameProto value) {
        if (value != null) {
            this.component_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponent(ComponentNameProto.Builder builderForValue) {
        this.component_ = (ComponentNameProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeComponent(ComponentNameProto value) {
        ComponentNameProto componentNameProto = this.component_;
        if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
            this.component_ = value;
        } else {
            this.component_ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.component_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearComponent() {
        this.component_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public int getUserId() {
        return this.userId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(int value) {
        this.bitField0_ |= 2;
        this.userId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserId() {
        this.bitField0_ &= -3;
        this.userId_ = 0;
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public boolean hasService() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public String getService() {
        return this.service_;
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public ByteString getServiceBytes() {
        return ByteString.copyFromUtf8(this.service_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setService(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.service_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearService() {
        this.bitField0_ &= -5;
        this.service_ = getDefaultInstance().getService();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServiceBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.service_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public boolean hasIsSystem() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public boolean getIsSystem() {
        return this.isSystem_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsSystem(boolean value) {
        this.bitField0_ |= 8;
        this.isSystem_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsSystem() {
        this.bitField0_ &= -9;
        this.isSystem_ = false;
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public boolean hasIsGuest() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
    public boolean getIsGuest() {
        return this.isGuest_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsGuest(boolean value) {
        this.bitField0_ |= 16;
        this.isGuest_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsGuest() {
        this.bitField0_ &= -17;
        this.isGuest_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getComponent());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.userId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getService());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.isSystem_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.isGuest_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getComponent());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.userId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getService());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.isSystem_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.isGuest_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ManagedServiceInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ManagedServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ManagedServiceInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ManagedServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ManagedServiceInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ManagedServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ManagedServiceInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ManagedServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ManagedServiceInfoProto parseFrom(InputStream input) throws IOException {
        return (ManagedServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ManagedServiceInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ManagedServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ManagedServiceInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ManagedServiceInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ManagedServiceInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ManagedServiceInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ManagedServiceInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (ManagedServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ManagedServiceInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ManagedServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ManagedServiceInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ManagedServiceInfoProto, Builder> implements ManagedServiceInfoProtoOrBuilder {
        private Builder() {
            super(ManagedServiceInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public boolean hasComponent() {
            return ((ManagedServiceInfoProto) this.instance).hasComponent();
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public ComponentNameProto getComponent() {
            return ((ManagedServiceInfoProto) this.instance).getComponent();
        }

        public Builder setComponent(ComponentNameProto value) {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).setComponent((ManagedServiceInfoProto) value);
            return this;
        }

        public Builder setComponent(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).setComponent((ManagedServiceInfoProto) builderForValue);
            return this;
        }

        public Builder mergeComponent(ComponentNameProto value) {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).mergeComponent(value);
            return this;
        }

        public Builder clearComponent() {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).clearComponent();
            return this;
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public boolean hasUserId() {
            return ((ManagedServiceInfoProto) this.instance).hasUserId();
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public int getUserId() {
            return ((ManagedServiceInfoProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).clearUserId();
            return this;
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public boolean hasService() {
            return ((ManagedServiceInfoProto) this.instance).hasService();
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public String getService() {
            return ((ManagedServiceInfoProto) this.instance).getService();
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public ByteString getServiceBytes() {
            return ((ManagedServiceInfoProto) this.instance).getServiceBytes();
        }

        public Builder setService(String value) {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).setService(value);
            return this;
        }

        public Builder clearService() {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).clearService();
            return this;
        }

        public Builder setServiceBytes(ByteString value) {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).setServiceBytes(value);
            return this;
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public boolean hasIsSystem() {
            return ((ManagedServiceInfoProto) this.instance).hasIsSystem();
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public boolean getIsSystem() {
            return ((ManagedServiceInfoProto) this.instance).getIsSystem();
        }

        public Builder setIsSystem(boolean value) {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).setIsSystem(value);
            return this;
        }

        public Builder clearIsSystem() {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).clearIsSystem();
            return this;
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public boolean hasIsGuest() {
            return ((ManagedServiceInfoProto) this.instance).hasIsGuest();
        }

        @Override // android.service.notification.ManagedServiceInfoProtoOrBuilder
        public boolean getIsGuest() {
            return ((ManagedServiceInfoProto) this.instance).getIsGuest();
        }

        public Builder setIsGuest(boolean value) {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).setIsGuest(value);
            return this;
        }

        public Builder clearIsGuest() {
            copyOnWrite();
            ((ManagedServiceInfoProto) this.instance).clearIsGuest();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ManagedServiceInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ManagedServiceInfoProto other = (ManagedServiceInfoProto) arg1;
                this.component_ = (ComponentNameProto) visitor.visitMessage(this.component_, other.component_);
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.service_ = visitor.visitString(hasService(), this.service_, other.hasService(), other.service_);
                this.isSystem_ = visitor.visitBoolean(hasIsSystem(), this.isSystem_, other.hasIsSystem(), other.isSystem_);
                this.isGuest_ = visitor.visitBoolean(hasIsGuest(), this.isGuest_, other.hasIsGuest(), other.isGuest_);
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
                            ComponentNameProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ComponentNameProto.Builder) this.component_.toBuilder();
                            }
                            this.component_ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.component_);
                                this.component_ = (ComponentNameProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.userId_ = input.readInt32();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.service_ = s;
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.isSystem_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.isGuest_ = input.readBool();
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
                    synchronized (ManagedServiceInfoProto.class) {
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

    public static ManagedServiceInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ManagedServiceInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
