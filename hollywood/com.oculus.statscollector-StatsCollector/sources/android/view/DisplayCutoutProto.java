package android.view;

import android.graphics.RectProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class DisplayCutoutProto extends GeneratedMessageLite<DisplayCutoutProto, Builder> implements DisplayCutoutProtoOrBuilder {
    public static final int BOUND_BOTTOM_FIELD_NUMBER = 6;
    public static final int BOUND_LEFT_FIELD_NUMBER = 3;
    public static final int BOUND_RIGHT_FIELD_NUMBER = 5;
    public static final int BOUND_TOP_FIELD_NUMBER = 4;
    private static final DisplayCutoutProto DEFAULT_INSTANCE = new DisplayCutoutProto();
    public static final int INSETS_FIELD_NUMBER = 1;
    private static volatile Parser<DisplayCutoutProto> PARSER;
    private int bitField0_;
    private RectProto boundBottom_;
    private RectProto boundLeft_;
    private RectProto boundRight_;
    private RectProto boundTop_;
    private RectProto insets_;

    private DisplayCutoutProto() {
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public boolean hasInsets() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public RectProto getInsets() {
        RectProto rectProto = this.insets_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInsets(RectProto value) {
        if (value != null) {
            this.insets_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInsets(RectProto.Builder builderForValue) {
        this.insets_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeInsets(RectProto value) {
        RectProto rectProto = this.insets_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.insets_ = value;
        } else {
            this.insets_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.insets_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInsets() {
        this.insets_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public boolean hasBoundLeft() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public RectProto getBoundLeft() {
        RectProto rectProto = this.boundLeft_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBoundLeft(RectProto value) {
        if (value != null) {
            this.boundLeft_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBoundLeft(RectProto.Builder builderForValue) {
        this.boundLeft_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBoundLeft(RectProto value) {
        RectProto rectProto = this.boundLeft_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.boundLeft_ = value;
        } else {
            this.boundLeft_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.boundLeft_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBoundLeft() {
        this.boundLeft_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public boolean hasBoundTop() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public RectProto getBoundTop() {
        RectProto rectProto = this.boundTop_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBoundTop(RectProto value) {
        if (value != null) {
            this.boundTop_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBoundTop(RectProto.Builder builderForValue) {
        this.boundTop_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBoundTop(RectProto value) {
        RectProto rectProto = this.boundTop_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.boundTop_ = value;
        } else {
            this.boundTop_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.boundTop_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBoundTop() {
        this.boundTop_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public boolean hasBoundRight() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public RectProto getBoundRight() {
        RectProto rectProto = this.boundRight_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBoundRight(RectProto value) {
        if (value != null) {
            this.boundRight_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBoundRight(RectProto.Builder builderForValue) {
        this.boundRight_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBoundRight(RectProto value) {
        RectProto rectProto = this.boundRight_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.boundRight_ = value;
        } else {
            this.boundRight_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.boundRight_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBoundRight() {
        this.boundRight_ = null;
        this.bitField0_ &= -9;
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public boolean hasBoundBottom() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.view.DisplayCutoutProtoOrBuilder
    public RectProto getBoundBottom() {
        RectProto rectProto = this.boundBottom_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBoundBottom(RectProto value) {
        if (value != null) {
            this.boundBottom_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBoundBottom(RectProto.Builder builderForValue) {
        this.boundBottom_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBoundBottom(RectProto value) {
        RectProto rectProto = this.boundBottom_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.boundBottom_ = value;
        } else {
            this.boundBottom_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.boundBottom_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBoundBottom() {
        this.boundBottom_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getInsets());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(3, getBoundLeft());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(4, getBoundTop());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(5, getBoundRight());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(6, getBoundBottom());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getInsets());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(3, getBoundLeft());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(4, getBoundTop());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(5, getBoundRight());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(6, getBoundBottom());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DisplayCutoutProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DisplayCutoutProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayCutoutProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayCutoutProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayCutoutProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DisplayCutoutProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayCutoutProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayCutoutProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayCutoutProto parseFrom(InputStream input) throws IOException {
        return (DisplayCutoutProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayCutoutProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayCutoutProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayCutoutProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DisplayCutoutProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayCutoutProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayCutoutProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayCutoutProto parseFrom(CodedInputStream input) throws IOException {
        return (DisplayCutoutProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayCutoutProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayCutoutProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DisplayCutoutProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DisplayCutoutProto, Builder> implements DisplayCutoutProtoOrBuilder {
        private Builder() {
            super(DisplayCutoutProto.DEFAULT_INSTANCE);
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public boolean hasInsets() {
            return ((DisplayCutoutProto) this.instance).hasInsets();
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public RectProto getInsets() {
            return ((DisplayCutoutProto) this.instance).getInsets();
        }

        public Builder setInsets(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setInsets((DisplayCutoutProto) value);
            return this;
        }

        public Builder setInsets(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setInsets((DisplayCutoutProto) builderForValue);
            return this;
        }

        public Builder mergeInsets(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).mergeInsets(value);
            return this;
        }

        public Builder clearInsets() {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).clearInsets();
            return this;
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public boolean hasBoundLeft() {
            return ((DisplayCutoutProto) this.instance).hasBoundLeft();
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public RectProto getBoundLeft() {
            return ((DisplayCutoutProto) this.instance).getBoundLeft();
        }

        public Builder setBoundLeft(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setBoundLeft((DisplayCutoutProto) value);
            return this;
        }

        public Builder setBoundLeft(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setBoundLeft((DisplayCutoutProto) builderForValue);
            return this;
        }

        public Builder mergeBoundLeft(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).mergeBoundLeft(value);
            return this;
        }

        public Builder clearBoundLeft() {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).clearBoundLeft();
            return this;
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public boolean hasBoundTop() {
            return ((DisplayCutoutProto) this.instance).hasBoundTop();
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public RectProto getBoundTop() {
            return ((DisplayCutoutProto) this.instance).getBoundTop();
        }

        public Builder setBoundTop(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setBoundTop((DisplayCutoutProto) value);
            return this;
        }

        public Builder setBoundTop(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setBoundTop((DisplayCutoutProto) builderForValue);
            return this;
        }

        public Builder mergeBoundTop(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).mergeBoundTop(value);
            return this;
        }

        public Builder clearBoundTop() {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).clearBoundTop();
            return this;
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public boolean hasBoundRight() {
            return ((DisplayCutoutProto) this.instance).hasBoundRight();
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public RectProto getBoundRight() {
            return ((DisplayCutoutProto) this.instance).getBoundRight();
        }

        public Builder setBoundRight(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setBoundRight((DisplayCutoutProto) value);
            return this;
        }

        public Builder setBoundRight(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setBoundRight((DisplayCutoutProto) builderForValue);
            return this;
        }

        public Builder mergeBoundRight(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).mergeBoundRight(value);
            return this;
        }

        public Builder clearBoundRight() {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).clearBoundRight();
            return this;
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public boolean hasBoundBottom() {
            return ((DisplayCutoutProto) this.instance).hasBoundBottom();
        }

        @Override // android.view.DisplayCutoutProtoOrBuilder
        public RectProto getBoundBottom() {
            return ((DisplayCutoutProto) this.instance).getBoundBottom();
        }

        public Builder setBoundBottom(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setBoundBottom((DisplayCutoutProto) value);
            return this;
        }

        public Builder setBoundBottom(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).setBoundBottom((DisplayCutoutProto) builderForValue);
            return this;
        }

        public Builder mergeBoundBottom(RectProto value) {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).mergeBoundBottom(value);
            return this;
        }

        public Builder clearBoundBottom() {
            copyOnWrite();
            ((DisplayCutoutProto) this.instance).clearBoundBottom();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DisplayCutoutProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DisplayCutoutProto other = (DisplayCutoutProto) arg1;
                this.insets_ = (RectProto) visitor.visitMessage(this.insets_, other.insets_);
                this.boundLeft_ = (RectProto) visitor.visitMessage(this.boundLeft_, other.boundLeft_);
                this.boundTop_ = (RectProto) visitor.visitMessage(this.boundTop_, other.boundTop_);
                this.boundRight_ = (RectProto) visitor.visitMessage(this.boundRight_, other.boundRight_);
                this.boundBottom_ = (RectProto) visitor.visitMessage(this.boundBottom_, other.boundBottom_);
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
                            RectProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (RectProto.Builder) this.insets_.toBuilder();
                            }
                            this.insets_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.insets_);
                                this.insets_ = (RectProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 26) {
                            RectProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (RectProto.Builder) this.boundLeft_.toBuilder();
                            }
                            this.boundLeft_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.boundLeft_);
                                this.boundLeft_ = (RectProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 34) {
                            RectProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (RectProto.Builder) this.boundTop_.toBuilder();
                            }
                            this.boundTop_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.boundTop_);
                                this.boundTop_ = (RectProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 42) {
                            RectProto.Builder subBuilder4 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder4 = (RectProto.Builder) this.boundRight_.toBuilder();
                            }
                            this.boundRight_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) this.boundRight_);
                                this.boundRight_ = (RectProto) subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (tag == 50) {
                            RectProto.Builder subBuilder5 = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder5 = (RectProto.Builder) this.boundBottom_.toBuilder();
                            }
                            this.boundBottom_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder5 != null) {
                                subBuilder5.mergeFrom((GeneratedMessageLite) this.boundBottom_);
                                this.boundBottom_ = (RectProto) subBuilder5.buildPartial();
                            }
                            this.bitField0_ |= 16;
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
                    synchronized (DisplayCutoutProto.class) {
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

    public static DisplayCutoutProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DisplayCutoutProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
