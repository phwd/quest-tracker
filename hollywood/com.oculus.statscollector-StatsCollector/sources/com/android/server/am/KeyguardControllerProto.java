package com.android.server.am;

import com.android.server.am.KeyguardOccludedProto;
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

public final class KeyguardControllerProto extends GeneratedMessageLite<KeyguardControllerProto, Builder> implements KeyguardControllerProtoOrBuilder {
    public static final int AOD_SHOWING_FIELD_NUMBER = 3;
    private static final KeyguardControllerProto DEFAULT_INSTANCE = new KeyguardControllerProto();
    public static final int KEYGUARD_OCCLUDED_STATES_FIELD_NUMBER = 2;
    public static final int KEYGUARD_SHOWING_FIELD_NUMBER = 1;
    private static volatile Parser<KeyguardControllerProto> PARSER;
    private boolean aodShowing_ = false;
    private int bitField0_;
    private Internal.ProtobufList<KeyguardOccludedProto> keyguardOccludedStates_ = emptyProtobufList();
    private boolean keyguardShowing_ = false;

    private KeyguardControllerProto() {
    }

    @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
    public boolean hasKeyguardShowing() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
    public boolean getKeyguardShowing() {
        return this.keyguardShowing_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardShowing(boolean value) {
        this.bitField0_ |= 1;
        this.keyguardShowing_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardShowing() {
        this.bitField0_ &= -2;
        this.keyguardShowing_ = false;
    }

    @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
    public List<KeyguardOccludedProto> getKeyguardOccludedStatesList() {
        return this.keyguardOccludedStates_;
    }

    public List<? extends KeyguardOccludedProtoOrBuilder> getKeyguardOccludedStatesOrBuilderList() {
        return this.keyguardOccludedStates_;
    }

    @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
    public int getKeyguardOccludedStatesCount() {
        return this.keyguardOccludedStates_.size();
    }

    @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
    public KeyguardOccludedProto getKeyguardOccludedStates(int index) {
        return this.keyguardOccludedStates_.get(index);
    }

    public KeyguardOccludedProtoOrBuilder getKeyguardOccludedStatesOrBuilder(int index) {
        return this.keyguardOccludedStates_.get(index);
    }

    private void ensureKeyguardOccludedStatesIsMutable() {
        if (!this.keyguardOccludedStates_.isModifiable()) {
            this.keyguardOccludedStates_ = GeneratedMessageLite.mutableCopy(this.keyguardOccludedStates_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardOccludedStates(int index, KeyguardOccludedProto value) {
        if (value != null) {
            ensureKeyguardOccludedStatesIsMutable();
            this.keyguardOccludedStates_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardOccludedStates(int index, KeyguardOccludedProto.Builder builderForValue) {
        ensureKeyguardOccludedStatesIsMutable();
        this.keyguardOccludedStates_.set(index, (KeyguardOccludedProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKeyguardOccludedStates(KeyguardOccludedProto value) {
        if (value != null) {
            ensureKeyguardOccludedStatesIsMutable();
            this.keyguardOccludedStates_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKeyguardOccludedStates(int index, KeyguardOccludedProto value) {
        if (value != null) {
            ensureKeyguardOccludedStatesIsMutable();
            this.keyguardOccludedStates_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKeyguardOccludedStates(KeyguardOccludedProto.Builder builderForValue) {
        ensureKeyguardOccludedStatesIsMutable();
        this.keyguardOccludedStates_.add((KeyguardOccludedProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKeyguardOccludedStates(int index, KeyguardOccludedProto.Builder builderForValue) {
        ensureKeyguardOccludedStatesIsMutable();
        this.keyguardOccludedStates_.add(index, (KeyguardOccludedProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllKeyguardOccludedStates(Iterable<? extends KeyguardOccludedProto> values) {
        ensureKeyguardOccludedStatesIsMutable();
        AbstractMessageLite.addAll(values, this.keyguardOccludedStates_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardOccludedStates() {
        this.keyguardOccludedStates_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeKeyguardOccludedStates(int index) {
        ensureKeyguardOccludedStatesIsMutable();
        this.keyguardOccludedStates_.remove(index);
    }

    @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
    public boolean hasAodShowing() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
    public boolean getAodShowing() {
        return this.aodShowing_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAodShowing(boolean value) {
        this.bitField0_ |= 2;
        this.aodShowing_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAodShowing() {
        this.bitField0_ &= -3;
        this.aodShowing_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.keyguardShowing_);
        }
        for (int i = 0; i < this.keyguardOccludedStates_.size(); i++) {
            output.writeMessage(2, this.keyguardOccludedStates_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(3, this.aodShowing_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.keyguardShowing_);
        }
        for (int i = 0; i < this.keyguardOccludedStates_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.keyguardOccludedStates_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(3, this.aodShowing_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static KeyguardControllerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (KeyguardControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static KeyguardControllerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (KeyguardControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static KeyguardControllerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (KeyguardControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static KeyguardControllerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (KeyguardControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static KeyguardControllerProto parseFrom(InputStream input) throws IOException {
        return (KeyguardControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static KeyguardControllerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KeyguardControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static KeyguardControllerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (KeyguardControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static KeyguardControllerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KeyguardControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static KeyguardControllerProto parseFrom(CodedInputStream input) throws IOException {
        return (KeyguardControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static KeyguardControllerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KeyguardControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KeyguardControllerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<KeyguardControllerProto, Builder> implements KeyguardControllerProtoOrBuilder {
        private Builder() {
            super(KeyguardControllerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
        public boolean hasKeyguardShowing() {
            return ((KeyguardControllerProto) this.instance).hasKeyguardShowing();
        }

        @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
        public boolean getKeyguardShowing() {
            return ((KeyguardControllerProto) this.instance).getKeyguardShowing();
        }

        public Builder setKeyguardShowing(boolean value) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).setKeyguardShowing(value);
            return this;
        }

        public Builder clearKeyguardShowing() {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).clearKeyguardShowing();
            return this;
        }

        @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
        public List<KeyguardOccludedProto> getKeyguardOccludedStatesList() {
            return Collections.unmodifiableList(((KeyguardControllerProto) this.instance).getKeyguardOccludedStatesList());
        }

        @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
        public int getKeyguardOccludedStatesCount() {
            return ((KeyguardControllerProto) this.instance).getKeyguardOccludedStatesCount();
        }

        @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
        public KeyguardOccludedProto getKeyguardOccludedStates(int index) {
            return ((KeyguardControllerProto) this.instance).getKeyguardOccludedStates(index);
        }

        public Builder setKeyguardOccludedStates(int index, KeyguardOccludedProto value) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).setKeyguardOccludedStates((KeyguardControllerProto) index, (int) value);
            return this;
        }

        public Builder setKeyguardOccludedStates(int index, KeyguardOccludedProto.Builder builderForValue) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).setKeyguardOccludedStates((KeyguardControllerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addKeyguardOccludedStates(KeyguardOccludedProto value) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).addKeyguardOccludedStates((KeyguardControllerProto) value);
            return this;
        }

        public Builder addKeyguardOccludedStates(int index, KeyguardOccludedProto value) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).addKeyguardOccludedStates((KeyguardControllerProto) index, (int) value);
            return this;
        }

        public Builder addKeyguardOccludedStates(KeyguardOccludedProto.Builder builderForValue) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).addKeyguardOccludedStates((KeyguardControllerProto) builderForValue);
            return this;
        }

        public Builder addKeyguardOccludedStates(int index, KeyguardOccludedProto.Builder builderForValue) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).addKeyguardOccludedStates((KeyguardControllerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllKeyguardOccludedStates(Iterable<? extends KeyguardOccludedProto> values) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).addAllKeyguardOccludedStates(values);
            return this;
        }

        public Builder clearKeyguardOccludedStates() {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).clearKeyguardOccludedStates();
            return this;
        }

        public Builder removeKeyguardOccludedStates(int index) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).removeKeyguardOccludedStates(index);
            return this;
        }

        @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
        public boolean hasAodShowing() {
            return ((KeyguardControllerProto) this.instance).hasAodShowing();
        }

        @Override // com.android.server.am.KeyguardControllerProtoOrBuilder
        public boolean getAodShowing() {
            return ((KeyguardControllerProto) this.instance).getAodShowing();
        }

        public Builder setAodShowing(boolean value) {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).setAodShowing(value);
            return this;
        }

        public Builder clearAodShowing() {
            copyOnWrite();
            ((KeyguardControllerProto) this.instance).clearAodShowing();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new KeyguardControllerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.keyguardOccludedStates_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                KeyguardControllerProto other = (KeyguardControllerProto) arg1;
                this.keyguardShowing_ = visitor.visitBoolean(hasKeyguardShowing(), this.keyguardShowing_, other.hasKeyguardShowing(), other.keyguardShowing_);
                this.keyguardOccludedStates_ = visitor.visitList(this.keyguardOccludedStates_, other.keyguardOccludedStates_);
                this.aodShowing_ = visitor.visitBoolean(hasAodShowing(), this.aodShowing_, other.hasAodShowing(), other.aodShowing_);
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
                            this.keyguardShowing_ = input.readBool();
                        } else if (tag == 18) {
                            if (!this.keyguardOccludedStates_.isModifiable()) {
                                this.keyguardOccludedStates_ = GeneratedMessageLite.mutableCopy(this.keyguardOccludedStates_);
                            }
                            this.keyguardOccludedStates_.add((KeyguardOccludedProto) input.readMessage(KeyguardOccludedProto.parser(), extensionRegistry));
                        } else if (tag == 24) {
                            this.bitField0_ |= 2;
                            this.aodShowing_ = input.readBool();
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
                    synchronized (KeyguardControllerProto.class) {
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

    public static KeyguardControllerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KeyguardControllerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
