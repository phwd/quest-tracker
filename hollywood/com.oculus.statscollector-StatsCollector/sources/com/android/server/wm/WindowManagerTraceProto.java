package com.android.server.wm;

import com.android.server.wm.WindowManagerServiceDumpProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WindowManagerTraceProto extends GeneratedMessageLite<WindowManagerTraceProto, Builder> implements WindowManagerTraceProtoOrBuilder {
    private static final WindowManagerTraceProto DEFAULT_INSTANCE = new WindowManagerTraceProto();
    public static final int ELAPSED_REALTIME_NANOS_FIELD_NUMBER = 1;
    private static volatile Parser<WindowManagerTraceProto> PARSER = null;
    public static final int WHERE_FIELD_NUMBER = 2;
    public static final int WINDOW_MANAGER_SERVICE_FIELD_NUMBER = 3;
    private int bitField0_;
    private long elapsedRealtimeNanos_ = 0;
    private String where_ = "";
    private WindowManagerServiceDumpProto windowManagerService_;

    private WindowManagerTraceProto() {
    }

    @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
    public boolean hasElapsedRealtimeNanos() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
    public long getElapsedRealtimeNanos() {
        return this.elapsedRealtimeNanos_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setElapsedRealtimeNanos(long value) {
        this.bitField0_ |= 1;
        this.elapsedRealtimeNanos_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearElapsedRealtimeNanos() {
        this.bitField0_ &= -2;
        this.elapsedRealtimeNanos_ = 0;
    }

    @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
    public boolean hasWhere() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
    public String getWhere() {
        return this.where_;
    }

    @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
    public ByteString getWhereBytes() {
        return ByteString.copyFromUtf8(this.where_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhere(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.where_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWhere() {
        this.bitField0_ &= -3;
        this.where_ = getDefaultInstance().getWhere();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhereBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.where_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
    public boolean hasWindowManagerService() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
    public WindowManagerServiceDumpProto getWindowManagerService() {
        WindowManagerServiceDumpProto windowManagerServiceDumpProto = this.windowManagerService_;
        return windowManagerServiceDumpProto == null ? WindowManagerServiceDumpProto.getDefaultInstance() : windowManagerServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowManagerService(WindowManagerServiceDumpProto value) {
        if (value != null) {
            this.windowManagerService_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowManagerService(WindowManagerServiceDumpProto.Builder builderForValue) {
        this.windowManagerService_ = (WindowManagerServiceDumpProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindowManagerService(WindowManagerServiceDumpProto value) {
        WindowManagerServiceDumpProto windowManagerServiceDumpProto = this.windowManagerService_;
        if (windowManagerServiceDumpProto == null || windowManagerServiceDumpProto == WindowManagerServiceDumpProto.getDefaultInstance()) {
            this.windowManagerService_ = value;
        } else {
            this.windowManagerService_ = (WindowManagerServiceDumpProto) ((WindowManagerServiceDumpProto.Builder) WindowManagerServiceDumpProto.newBuilder(this.windowManagerService_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowManagerService() {
        this.windowManagerService_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeFixed64(1, this.elapsedRealtimeNanos_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getWhere());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getWindowManagerService());
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
            size2 = 0 + CodedOutputStream.computeFixed64Size(1, this.elapsedRealtimeNanos_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getWhere());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getWindowManagerService());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowManagerTraceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowManagerTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowManagerTraceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowManagerTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowManagerTraceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowManagerTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowManagerTraceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowManagerTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowManagerTraceProto parseFrom(InputStream input) throws IOException {
        return (WindowManagerTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerTraceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowManagerTraceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowManagerTraceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerTraceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerTraceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowManagerTraceProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowManagerTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerTraceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowManagerTraceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowManagerTraceProto, Builder> implements WindowManagerTraceProtoOrBuilder {
        private Builder() {
            super(WindowManagerTraceProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
        public boolean hasElapsedRealtimeNanos() {
            return ((WindowManagerTraceProto) this.instance).hasElapsedRealtimeNanos();
        }

        @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
        public long getElapsedRealtimeNanos() {
            return ((WindowManagerTraceProto) this.instance).getElapsedRealtimeNanos();
        }

        public Builder setElapsedRealtimeNanos(long value) {
            copyOnWrite();
            ((WindowManagerTraceProto) this.instance).setElapsedRealtimeNanos(value);
            return this;
        }

        public Builder clearElapsedRealtimeNanos() {
            copyOnWrite();
            ((WindowManagerTraceProto) this.instance).clearElapsedRealtimeNanos();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
        public boolean hasWhere() {
            return ((WindowManagerTraceProto) this.instance).hasWhere();
        }

        @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
        public String getWhere() {
            return ((WindowManagerTraceProto) this.instance).getWhere();
        }

        @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
        public ByteString getWhereBytes() {
            return ((WindowManagerTraceProto) this.instance).getWhereBytes();
        }

        public Builder setWhere(String value) {
            copyOnWrite();
            ((WindowManagerTraceProto) this.instance).setWhere(value);
            return this;
        }

        public Builder clearWhere() {
            copyOnWrite();
            ((WindowManagerTraceProto) this.instance).clearWhere();
            return this;
        }

        public Builder setWhereBytes(ByteString value) {
            copyOnWrite();
            ((WindowManagerTraceProto) this.instance).setWhereBytes(value);
            return this;
        }

        @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
        public boolean hasWindowManagerService() {
            return ((WindowManagerTraceProto) this.instance).hasWindowManagerService();
        }

        @Override // com.android.server.wm.WindowManagerTraceProtoOrBuilder
        public WindowManagerServiceDumpProto getWindowManagerService() {
            return ((WindowManagerTraceProto) this.instance).getWindowManagerService();
        }

        public Builder setWindowManagerService(WindowManagerServiceDumpProto value) {
            copyOnWrite();
            ((WindowManagerTraceProto) this.instance).setWindowManagerService((WindowManagerTraceProto) value);
            return this;
        }

        public Builder setWindowManagerService(WindowManagerServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerTraceProto) this.instance).setWindowManagerService((WindowManagerTraceProto) builderForValue);
            return this;
        }

        public Builder mergeWindowManagerService(WindowManagerServiceDumpProto value) {
            copyOnWrite();
            ((WindowManagerTraceProto) this.instance).mergeWindowManagerService(value);
            return this;
        }

        public Builder clearWindowManagerService() {
            copyOnWrite();
            ((WindowManagerTraceProto) this.instance).clearWindowManagerService();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowManagerTraceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowManagerTraceProto other = (WindowManagerTraceProto) arg1;
                this.elapsedRealtimeNanos_ = visitor.visitLong(hasElapsedRealtimeNanos(), this.elapsedRealtimeNanos_, other.hasElapsedRealtimeNanos(), other.elapsedRealtimeNanos_);
                this.where_ = visitor.visitString(hasWhere(), this.where_, other.hasWhere(), other.where_);
                this.windowManagerService_ = (WindowManagerServiceDumpProto) visitor.visitMessage(this.windowManagerService_, other.windowManagerService_);
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
                        } else if (tag == 9) {
                            this.bitField0_ |= 1;
                            this.elapsedRealtimeNanos_ = input.readFixed64();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.where_ = s;
                        } else if (tag == 26) {
                            WindowManagerServiceDumpProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (WindowManagerServiceDumpProto.Builder) this.windowManagerService_.toBuilder();
                            }
                            this.windowManagerService_ = (WindowManagerServiceDumpProto) input.readMessage(WindowManagerServiceDumpProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.windowManagerService_);
                                this.windowManagerService_ = (WindowManagerServiceDumpProto) subBuilder.buildPartial();
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
                    synchronized (WindowManagerTraceProto.class) {
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

    public static WindowManagerTraceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowManagerTraceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
