package com.android.server.wm;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class DockedStackDividerControllerProto extends GeneratedMessageLite<DockedStackDividerControllerProto, Builder> implements DockedStackDividerControllerProtoOrBuilder {
    private static final DockedStackDividerControllerProto DEFAULT_INSTANCE = new DockedStackDividerControllerProto();
    public static final int MINIMIZED_DOCK_FIELD_NUMBER = 1;
    private static volatile Parser<DockedStackDividerControllerProto> PARSER;
    private int bitField0_;
    private boolean minimizedDock_ = false;

    private DockedStackDividerControllerProto() {
    }

    @Override // com.android.server.wm.DockedStackDividerControllerProtoOrBuilder
    public boolean hasMinimizedDock() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.DockedStackDividerControllerProtoOrBuilder
    public boolean getMinimizedDock() {
        return this.minimizedDock_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinimizedDock(boolean value) {
        this.bitField0_ |= 1;
        this.minimizedDock_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinimizedDock() {
        this.bitField0_ &= -2;
        this.minimizedDock_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.minimizedDock_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.minimizedDock_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DockedStackDividerControllerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DockedStackDividerControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DockedStackDividerControllerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DockedStackDividerControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DockedStackDividerControllerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DockedStackDividerControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DockedStackDividerControllerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DockedStackDividerControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DockedStackDividerControllerProto parseFrom(InputStream input) throws IOException {
        return (DockedStackDividerControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DockedStackDividerControllerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DockedStackDividerControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DockedStackDividerControllerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DockedStackDividerControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DockedStackDividerControllerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DockedStackDividerControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DockedStackDividerControllerProto parseFrom(CodedInputStream input) throws IOException {
        return (DockedStackDividerControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DockedStackDividerControllerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DockedStackDividerControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DockedStackDividerControllerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DockedStackDividerControllerProto, Builder> implements DockedStackDividerControllerProtoOrBuilder {
        private Builder() {
            super(DockedStackDividerControllerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.DockedStackDividerControllerProtoOrBuilder
        public boolean hasMinimizedDock() {
            return ((DockedStackDividerControllerProto) this.instance).hasMinimizedDock();
        }

        @Override // com.android.server.wm.DockedStackDividerControllerProtoOrBuilder
        public boolean getMinimizedDock() {
            return ((DockedStackDividerControllerProto) this.instance).getMinimizedDock();
        }

        public Builder setMinimizedDock(boolean value) {
            copyOnWrite();
            ((DockedStackDividerControllerProto) this.instance).setMinimizedDock(value);
            return this;
        }

        public Builder clearMinimizedDock() {
            copyOnWrite();
            ((DockedStackDividerControllerProto) this.instance).clearMinimizedDock();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DockedStackDividerControllerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DockedStackDividerControllerProto other = (DockedStackDividerControllerProto) arg1;
                this.minimizedDock_ = visitor.visitBoolean(hasMinimizedDock(), this.minimizedDock_, other.hasMinimizedDock(), other.minimizedDock_);
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
                            this.minimizedDock_ = input.readBool();
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
                    synchronized (DockedStackDividerControllerProto.class) {
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

    public static DockedStackDividerControllerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DockedStackDividerControllerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
