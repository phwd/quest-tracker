package com.android.server.wm;

import com.android.server.wm.WindowManagerTraceProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class WindowManagerTraceFileProto extends GeneratedMessageLite<WindowManagerTraceFileProto, Builder> implements WindowManagerTraceFileProtoOrBuilder {
    private static final WindowManagerTraceFileProto DEFAULT_INSTANCE = new WindowManagerTraceFileProto();
    public static final int ENTRY_FIELD_NUMBER = 2;
    public static final int MAGIC_NUMBER_FIELD_NUMBER = 1;
    private static volatile Parser<WindowManagerTraceFileProto> PARSER;
    private int bitField0_;
    private Internal.ProtobufList<WindowManagerTraceProto> entry_ = emptyProtobufList();
    private long magicNumber_ = 0;

    private WindowManagerTraceFileProto() {
    }

    public enum MagicNumber implements Internal.EnumLite {
        INVALID(0),
        MAGIC_NUMBER_L(MAGIC_NUMBER_L_VALUE),
        MAGIC_NUMBER_H(MAGIC_NUMBER_H_VALUE);
        
        public static final int INVALID_VALUE = 0;
        public static final int MAGIC_NUMBER_H_VALUE = 1162035538;
        public static final int MAGIC_NUMBER_L_VALUE = 1414416727;
        private static final Internal.EnumLiteMap<MagicNumber> internalValueMap = new Internal.EnumLiteMap<MagicNumber>() {
            /* class com.android.server.wm.WindowManagerTraceFileProto.MagicNumber.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MagicNumber findValueByNumber(int number) {
                return MagicNumber.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static MagicNumber valueOf(int value2) {
            return forNumber(value2);
        }

        public static MagicNumber forNumber(int value2) {
            if (value2 == 0) {
                return INVALID;
            }
            if (value2 == 1162035538) {
                return MAGIC_NUMBER_H;
            }
            if (value2 != 1414416727) {
                return null;
            }
            return MAGIC_NUMBER_L;
        }

        public static Internal.EnumLiteMap<MagicNumber> internalGetValueMap() {
            return internalValueMap;
        }

        private MagicNumber(int value2) {
            this.value = value2;
        }
    }

    @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
    public boolean hasMagicNumber() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
    public long getMagicNumber() {
        return this.magicNumber_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMagicNumber(long value) {
        this.bitField0_ |= 1;
        this.magicNumber_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMagicNumber() {
        this.bitField0_ &= -2;
        this.magicNumber_ = 0;
    }

    @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
    public List<WindowManagerTraceProto> getEntryList() {
        return this.entry_;
    }

    public List<? extends WindowManagerTraceProtoOrBuilder> getEntryOrBuilderList() {
        return this.entry_;
    }

    @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
    public int getEntryCount() {
        return this.entry_.size();
    }

    @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
    public WindowManagerTraceProto getEntry(int index) {
        return this.entry_.get(index);
    }

    public WindowManagerTraceProtoOrBuilder getEntryOrBuilder(int index) {
        return this.entry_.get(index);
    }

    private void ensureEntryIsMutable() {
        if (!this.entry_.isModifiable()) {
            this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEntry(int index, WindowManagerTraceProto value) {
        if (value != null) {
            ensureEntryIsMutable();
            this.entry_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEntry(int index, WindowManagerTraceProto.Builder builderForValue) {
        ensureEntryIsMutable();
        this.entry_.set(index, (WindowManagerTraceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEntry(WindowManagerTraceProto value) {
        if (value != null) {
            ensureEntryIsMutable();
            this.entry_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEntry(int index, WindowManagerTraceProto value) {
        if (value != null) {
            ensureEntryIsMutable();
            this.entry_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEntry(WindowManagerTraceProto.Builder builderForValue) {
        ensureEntryIsMutable();
        this.entry_.add((WindowManagerTraceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEntry(int index, WindowManagerTraceProto.Builder builderForValue) {
        ensureEntryIsMutable();
        this.entry_.add(index, (WindowManagerTraceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllEntry(Iterable<? extends WindowManagerTraceProto> values) {
        ensureEntryIsMutable();
        AbstractMessageLite.addAll(values, this.entry_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEntry() {
        this.entry_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeEntry(int index) {
        ensureEntryIsMutable();
        this.entry_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeFixed64(1, this.magicNumber_);
        }
        for (int i = 0; i < this.entry_.size(); i++) {
            output.writeMessage(2, this.entry_.get(i));
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
            size2 = 0 + CodedOutputStream.computeFixed64Size(1, this.magicNumber_);
        }
        for (int i = 0; i < this.entry_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.entry_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowManagerTraceFileProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowManagerTraceFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowManagerTraceFileProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowManagerTraceFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowManagerTraceFileProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowManagerTraceFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowManagerTraceFileProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowManagerTraceFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowManagerTraceFileProto parseFrom(InputStream input) throws IOException {
        return (WindowManagerTraceFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerTraceFileProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerTraceFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowManagerTraceFileProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowManagerTraceFileProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerTraceFileProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerTraceFileProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowManagerTraceFileProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowManagerTraceFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerTraceFileProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerTraceFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowManagerTraceFileProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowManagerTraceFileProto, Builder> implements WindowManagerTraceFileProtoOrBuilder {
        private Builder() {
            super(WindowManagerTraceFileProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
        public boolean hasMagicNumber() {
            return ((WindowManagerTraceFileProto) this.instance).hasMagicNumber();
        }

        @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
        public long getMagicNumber() {
            return ((WindowManagerTraceFileProto) this.instance).getMagicNumber();
        }

        public Builder setMagicNumber(long value) {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).setMagicNumber(value);
            return this;
        }

        public Builder clearMagicNumber() {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).clearMagicNumber();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
        public List<WindowManagerTraceProto> getEntryList() {
            return Collections.unmodifiableList(((WindowManagerTraceFileProto) this.instance).getEntryList());
        }

        @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
        public int getEntryCount() {
            return ((WindowManagerTraceFileProto) this.instance).getEntryCount();
        }

        @Override // com.android.server.wm.WindowManagerTraceFileProtoOrBuilder
        public WindowManagerTraceProto getEntry(int index) {
            return ((WindowManagerTraceFileProto) this.instance).getEntry(index);
        }

        public Builder setEntry(int index, WindowManagerTraceProto value) {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).setEntry((WindowManagerTraceFileProto) index, (int) value);
            return this;
        }

        public Builder setEntry(int index, WindowManagerTraceProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).setEntry((WindowManagerTraceFileProto) index, (int) builderForValue);
            return this;
        }

        public Builder addEntry(WindowManagerTraceProto value) {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).addEntry((WindowManagerTraceFileProto) value);
            return this;
        }

        public Builder addEntry(int index, WindowManagerTraceProto value) {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).addEntry((WindowManagerTraceFileProto) index, (int) value);
            return this;
        }

        public Builder addEntry(WindowManagerTraceProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).addEntry((WindowManagerTraceFileProto) builderForValue);
            return this;
        }

        public Builder addEntry(int index, WindowManagerTraceProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).addEntry((WindowManagerTraceFileProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllEntry(Iterable<? extends WindowManagerTraceProto> values) {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).addAllEntry(values);
            return this;
        }

        public Builder clearEntry() {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).clearEntry();
            return this;
        }

        public Builder removeEntry(int index) {
            copyOnWrite();
            ((WindowManagerTraceFileProto) this.instance).removeEntry(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowManagerTraceFileProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.entry_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowManagerTraceFileProto other = (WindowManagerTraceFileProto) arg1;
                this.magicNumber_ = visitor.visitLong(hasMagicNumber(), this.magicNumber_, other.hasMagicNumber(), other.magicNumber_);
                this.entry_ = visitor.visitList(this.entry_, other.entry_);
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
                            this.magicNumber_ = input.readFixed64();
                        } else if (tag == 18) {
                            if (!this.entry_.isModifiable()) {
                                this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
                            }
                            this.entry_.add((WindowManagerTraceProto) input.readMessage(WindowManagerTraceProto.parser(), extensionRegistry));
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
                    synchronized (WindowManagerTraceFileProto.class) {
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

    public static WindowManagerTraceFileProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowManagerTraceFileProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
