package android.service.print;

import android.service.print.PrintSpoolerInternalStateProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PrintSpoolerStateProto extends GeneratedMessageLite<PrintSpoolerStateProto, Builder> implements PrintSpoolerStateProtoOrBuilder {
    private static final PrintSpoolerStateProto DEFAULT_INSTANCE = new PrintSpoolerStateProto();
    public static final int INTERNAL_STATE_FIELD_NUMBER = 3;
    public static final int IS_BOUND_FIELD_NUMBER = 2;
    public static final int IS_DESTROYED_FIELD_NUMBER = 1;
    private static volatile Parser<PrintSpoolerStateProto> PARSER;
    private int bitField0_;
    private PrintSpoolerInternalStateProto internalState_;
    private boolean isBound_ = false;
    private boolean isDestroyed_ = false;

    private PrintSpoolerStateProto() {
    }

    @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
    public boolean hasIsDestroyed() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
    public boolean getIsDestroyed() {
        return this.isDestroyed_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDestroyed(boolean value) {
        this.bitField0_ |= 1;
        this.isDestroyed_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDestroyed() {
        this.bitField0_ &= -2;
        this.isDestroyed_ = false;
    }

    @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
    public boolean hasIsBound() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
    public boolean getIsBound() {
        return this.isBound_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsBound(boolean value) {
        this.bitField0_ |= 2;
        this.isBound_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsBound() {
        this.bitField0_ &= -3;
        this.isBound_ = false;
    }

    @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
    public boolean hasInternalState() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
    public PrintSpoolerInternalStateProto getInternalState() {
        PrintSpoolerInternalStateProto printSpoolerInternalStateProto = this.internalState_;
        return printSpoolerInternalStateProto == null ? PrintSpoolerInternalStateProto.getDefaultInstance() : printSpoolerInternalStateProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInternalState(PrintSpoolerInternalStateProto value) {
        if (value != null) {
            this.internalState_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInternalState(PrintSpoolerInternalStateProto.Builder builderForValue) {
        this.internalState_ = (PrintSpoolerInternalStateProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeInternalState(PrintSpoolerInternalStateProto value) {
        PrintSpoolerInternalStateProto printSpoolerInternalStateProto = this.internalState_;
        if (printSpoolerInternalStateProto == null || printSpoolerInternalStateProto == PrintSpoolerInternalStateProto.getDefaultInstance()) {
            this.internalState_ = value;
        } else {
            this.internalState_ = (PrintSpoolerInternalStateProto) ((PrintSpoolerInternalStateProto.Builder) PrintSpoolerInternalStateProto.newBuilder(this.internalState_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInternalState() {
        this.internalState_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.isDestroyed_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isBound_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getInternalState());
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isDestroyed_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isBound_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getInternalState());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PrintSpoolerStateProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrintSpoolerStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintSpoolerStateProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintSpoolerStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintSpoolerStateProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrintSpoolerStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintSpoolerStateProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintSpoolerStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintSpoolerStateProto parseFrom(InputStream input) throws IOException {
        return (PrintSpoolerStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintSpoolerStateProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintSpoolerStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintSpoolerStateProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrintSpoolerStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintSpoolerStateProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintSpoolerStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintSpoolerStateProto parseFrom(CodedInputStream input) throws IOException {
        return (PrintSpoolerStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintSpoolerStateProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintSpoolerStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrintSpoolerStateProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrintSpoolerStateProto, Builder> implements PrintSpoolerStateProtoOrBuilder {
        private Builder() {
            super(PrintSpoolerStateProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
        public boolean hasIsDestroyed() {
            return ((PrintSpoolerStateProto) this.instance).hasIsDestroyed();
        }

        @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
        public boolean getIsDestroyed() {
            return ((PrintSpoolerStateProto) this.instance).getIsDestroyed();
        }

        public Builder setIsDestroyed(boolean value) {
            copyOnWrite();
            ((PrintSpoolerStateProto) this.instance).setIsDestroyed(value);
            return this;
        }

        public Builder clearIsDestroyed() {
            copyOnWrite();
            ((PrintSpoolerStateProto) this.instance).clearIsDestroyed();
            return this;
        }

        @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
        public boolean hasIsBound() {
            return ((PrintSpoolerStateProto) this.instance).hasIsBound();
        }

        @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
        public boolean getIsBound() {
            return ((PrintSpoolerStateProto) this.instance).getIsBound();
        }

        public Builder setIsBound(boolean value) {
            copyOnWrite();
            ((PrintSpoolerStateProto) this.instance).setIsBound(value);
            return this;
        }

        public Builder clearIsBound() {
            copyOnWrite();
            ((PrintSpoolerStateProto) this.instance).clearIsBound();
            return this;
        }

        @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
        public boolean hasInternalState() {
            return ((PrintSpoolerStateProto) this.instance).hasInternalState();
        }

        @Override // android.service.print.PrintSpoolerStateProtoOrBuilder
        public PrintSpoolerInternalStateProto getInternalState() {
            return ((PrintSpoolerStateProto) this.instance).getInternalState();
        }

        public Builder setInternalState(PrintSpoolerInternalStateProto value) {
            copyOnWrite();
            ((PrintSpoolerStateProto) this.instance).setInternalState((PrintSpoolerStateProto) value);
            return this;
        }

        public Builder setInternalState(PrintSpoolerInternalStateProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintSpoolerStateProto) this.instance).setInternalState((PrintSpoolerStateProto) builderForValue);
            return this;
        }

        public Builder mergeInternalState(PrintSpoolerInternalStateProto value) {
            copyOnWrite();
            ((PrintSpoolerStateProto) this.instance).mergeInternalState(value);
            return this;
        }

        public Builder clearInternalState() {
            copyOnWrite();
            ((PrintSpoolerStateProto) this.instance).clearInternalState();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrintSpoolerStateProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrintSpoolerStateProto other = (PrintSpoolerStateProto) arg1;
                this.isDestroyed_ = visitor.visitBoolean(hasIsDestroyed(), this.isDestroyed_, other.hasIsDestroyed(), other.isDestroyed_);
                this.isBound_ = visitor.visitBoolean(hasIsBound(), this.isBound_, other.hasIsBound(), other.isBound_);
                this.internalState_ = (PrintSpoolerInternalStateProto) visitor.visitMessage(this.internalState_, other.internalState_);
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
                            this.isDestroyed_ = input.readBool();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.isBound_ = input.readBool();
                        } else if (tag == 26) {
                            PrintSpoolerInternalStateProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (PrintSpoolerInternalStateProto.Builder) this.internalState_.toBuilder();
                            }
                            this.internalState_ = (PrintSpoolerInternalStateProto) input.readMessage(PrintSpoolerInternalStateProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.internalState_);
                                this.internalState_ = (PrintSpoolerInternalStateProto) subBuilder.buildPartial();
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
                    synchronized (PrintSpoolerStateProto.class) {
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

    public static PrintSpoolerStateProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrintSpoolerStateProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
