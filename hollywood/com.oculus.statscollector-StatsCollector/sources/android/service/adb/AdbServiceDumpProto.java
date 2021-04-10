package android.service.adb;

import android.service.adb.AdbDebuggingManagerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AdbServiceDumpProto extends GeneratedMessageLite<AdbServiceDumpProto, Builder> implements AdbServiceDumpProtoOrBuilder {
    public static final int DEBUGGING_MANAGER_FIELD_NUMBER = 1;
    private static final AdbServiceDumpProto DEFAULT_INSTANCE = new AdbServiceDumpProto();
    private static volatile Parser<AdbServiceDumpProto> PARSER;
    private int bitField0_;
    private AdbDebuggingManagerProto debuggingManager_;

    private AdbServiceDumpProto() {
    }

    @Override // android.service.adb.AdbServiceDumpProtoOrBuilder
    public boolean hasDebuggingManager() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.adb.AdbServiceDumpProtoOrBuilder
    public AdbDebuggingManagerProto getDebuggingManager() {
        AdbDebuggingManagerProto adbDebuggingManagerProto = this.debuggingManager_;
        return adbDebuggingManagerProto == null ? AdbDebuggingManagerProto.getDefaultInstance() : adbDebuggingManagerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDebuggingManager(AdbDebuggingManagerProto value) {
        if (value != null) {
            this.debuggingManager_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDebuggingManager(AdbDebuggingManagerProto.Builder builderForValue) {
        this.debuggingManager_ = (AdbDebuggingManagerProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDebuggingManager(AdbDebuggingManagerProto value) {
        AdbDebuggingManagerProto adbDebuggingManagerProto = this.debuggingManager_;
        if (adbDebuggingManagerProto == null || adbDebuggingManagerProto == AdbDebuggingManagerProto.getDefaultInstance()) {
            this.debuggingManager_ = value;
        } else {
            this.debuggingManager_ = (AdbDebuggingManagerProto) ((AdbDebuggingManagerProto.Builder) AdbDebuggingManagerProto.newBuilder(this.debuggingManager_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDebuggingManager() {
        this.debuggingManager_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getDebuggingManager());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getDebuggingManager());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AdbServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AdbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AdbServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AdbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AdbServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AdbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AdbServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AdbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AdbServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (AdbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AdbServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AdbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AdbServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AdbServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AdbServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AdbServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AdbServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (AdbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AdbServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AdbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AdbServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AdbServiceDumpProto, Builder> implements AdbServiceDumpProtoOrBuilder {
        private Builder() {
            super(AdbServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.adb.AdbServiceDumpProtoOrBuilder
        public boolean hasDebuggingManager() {
            return ((AdbServiceDumpProto) this.instance).hasDebuggingManager();
        }

        @Override // android.service.adb.AdbServiceDumpProtoOrBuilder
        public AdbDebuggingManagerProto getDebuggingManager() {
            return ((AdbServiceDumpProto) this.instance).getDebuggingManager();
        }

        public Builder setDebuggingManager(AdbDebuggingManagerProto value) {
            copyOnWrite();
            ((AdbServiceDumpProto) this.instance).setDebuggingManager((AdbServiceDumpProto) value);
            return this;
        }

        public Builder setDebuggingManager(AdbDebuggingManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((AdbServiceDumpProto) this.instance).setDebuggingManager((AdbServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeDebuggingManager(AdbDebuggingManagerProto value) {
            copyOnWrite();
            ((AdbServiceDumpProto) this.instance).mergeDebuggingManager(value);
            return this;
        }

        public Builder clearDebuggingManager() {
            copyOnWrite();
            ((AdbServiceDumpProto) this.instance).clearDebuggingManager();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AdbServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AdbServiceDumpProto other = (AdbServiceDumpProto) arg1;
                this.debuggingManager_ = (AdbDebuggingManagerProto) visitor.visitMessage(this.debuggingManager_, other.debuggingManager_);
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
                            AdbDebuggingManagerProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (AdbDebuggingManagerProto.Builder) this.debuggingManager_.toBuilder();
                            }
                            this.debuggingManager_ = (AdbDebuggingManagerProto) input.readMessage(AdbDebuggingManagerProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.debuggingManager_);
                                this.debuggingManager_ = (AdbDebuggingManagerProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
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
                    synchronized (AdbServiceDumpProto.class) {
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

    public static AdbServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AdbServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
