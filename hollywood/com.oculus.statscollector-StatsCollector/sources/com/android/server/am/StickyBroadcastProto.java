package com.android.server.am;

import android.content.IntentProto;
import android.content.IntentProtoOrBuilder;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class StickyBroadcastProto extends GeneratedMessageLite<StickyBroadcastProto, Builder> implements StickyBroadcastProtoOrBuilder {
    public static final int ACTIONS_FIELD_NUMBER = 2;
    private static final StickyBroadcastProto DEFAULT_INSTANCE = new StickyBroadcastProto();
    private static volatile Parser<StickyBroadcastProto> PARSER = null;
    public static final int USER_FIELD_NUMBER = 1;
    private Internal.ProtobufList<StickyAction> actions_ = emptyProtobufList();
    private int bitField0_;
    private int user_ = 0;

    public interface StickyActionOrBuilder extends MessageLiteOrBuilder {
        IntentProto getIntents(int i);

        int getIntentsCount();

        List<IntentProto> getIntentsList();

        String getName();

        ByteString getNameBytes();

        boolean hasName();
    }

    private StickyBroadcastProto() {
    }

    public static final class StickyAction extends GeneratedMessageLite<StickyAction, Builder> implements StickyActionOrBuilder {
        private static final StickyAction DEFAULT_INSTANCE = new StickyAction();
        public static final int INTENTS_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<StickyAction> PARSER;
        private int bitField0_;
        private Internal.ProtobufList<IntentProto> intents_ = emptyProtobufList();
        private String name_ = "";

        private StickyAction() {
        }

        @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.name_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearName() {
            this.bitField0_ &= -2;
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.name_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
        public List<IntentProto> getIntentsList() {
            return this.intents_;
        }

        public List<? extends IntentProtoOrBuilder> getIntentsOrBuilderList() {
            return this.intents_;
        }

        @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
        public int getIntentsCount() {
            return this.intents_.size();
        }

        @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
        public IntentProto getIntents(int index) {
            return this.intents_.get(index);
        }

        public IntentProtoOrBuilder getIntentsOrBuilder(int index) {
            return this.intents_.get(index);
        }

        private void ensureIntentsIsMutable() {
            if (!this.intents_.isModifiable()) {
                this.intents_ = GeneratedMessageLite.mutableCopy(this.intents_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntents(int index, IntentProto value) {
            if (value != null) {
                ensureIntentsIsMutable();
                this.intents_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntents(int index, IntentProto.Builder builderForValue) {
            ensureIntentsIsMutable();
            this.intents_.set(index, (IntentProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addIntents(IntentProto value) {
            if (value != null) {
                ensureIntentsIsMutable();
                this.intents_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addIntents(int index, IntentProto value) {
            if (value != null) {
                ensureIntentsIsMutable();
                this.intents_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addIntents(IntentProto.Builder builderForValue) {
            ensureIntentsIsMutable();
            this.intents_.add((IntentProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addIntents(int index, IntentProto.Builder builderForValue) {
            ensureIntentsIsMutable();
            this.intents_.add(index, (IntentProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllIntents(Iterable<? extends IntentProto> values) {
            ensureIntentsIsMutable();
            AbstractMessageLite.addAll(values, this.intents_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIntents() {
            this.intents_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeIntents(int index) {
            ensureIntentsIsMutable();
            this.intents_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            for (int i = 0; i < this.intents_.size(); i++) {
                output.writeMessage(2, this.intents_.get(i));
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
            }
            for (int i = 0; i < this.intents_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.intents_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static StickyAction parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StickyAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StickyAction parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StickyAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StickyAction parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StickyAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StickyAction parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StickyAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StickyAction parseFrom(InputStream input) throws IOException {
            return (StickyAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StickyAction parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StickyAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StickyAction parseDelimitedFrom(InputStream input) throws IOException {
            return (StickyAction) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StickyAction parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StickyAction) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StickyAction parseFrom(CodedInputStream input) throws IOException {
            return (StickyAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StickyAction parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StickyAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StickyAction prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StickyAction, Builder> implements StickyActionOrBuilder {
            private Builder() {
                super(StickyAction.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
            public boolean hasName() {
                return ((StickyAction) this.instance).hasName();
            }

            @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
            public String getName() {
                return ((StickyAction) this.instance).getName();
            }

            @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
            public ByteString getNameBytes() {
                return ((StickyAction) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((StickyAction) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((StickyAction) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((StickyAction) this.instance).setNameBytes(value);
                return this;
            }

            @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
            public List<IntentProto> getIntentsList() {
                return Collections.unmodifiableList(((StickyAction) this.instance).getIntentsList());
            }

            @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
            public int getIntentsCount() {
                return ((StickyAction) this.instance).getIntentsCount();
            }

            @Override // com.android.server.am.StickyBroadcastProto.StickyActionOrBuilder
            public IntentProto getIntents(int index) {
                return ((StickyAction) this.instance).getIntents(index);
            }

            public Builder setIntents(int index, IntentProto value) {
                copyOnWrite();
                ((StickyAction) this.instance).setIntents((StickyAction) index, (int) value);
                return this;
            }

            public Builder setIntents(int index, IntentProto.Builder builderForValue) {
                copyOnWrite();
                ((StickyAction) this.instance).setIntents((StickyAction) index, (int) builderForValue);
                return this;
            }

            public Builder addIntents(IntentProto value) {
                copyOnWrite();
                ((StickyAction) this.instance).addIntents((StickyAction) value);
                return this;
            }

            public Builder addIntents(int index, IntentProto value) {
                copyOnWrite();
                ((StickyAction) this.instance).addIntents((StickyAction) index, (int) value);
                return this;
            }

            public Builder addIntents(IntentProto.Builder builderForValue) {
                copyOnWrite();
                ((StickyAction) this.instance).addIntents((StickyAction) builderForValue);
                return this;
            }

            public Builder addIntents(int index, IntentProto.Builder builderForValue) {
                copyOnWrite();
                ((StickyAction) this.instance).addIntents((StickyAction) index, (int) builderForValue);
                return this;
            }

            public Builder addAllIntents(Iterable<? extends IntentProto> values) {
                copyOnWrite();
                ((StickyAction) this.instance).addAllIntents(values);
                return this;
            }

            public Builder clearIntents() {
                copyOnWrite();
                ((StickyAction) this.instance).clearIntents();
                return this;
            }

            public Builder removeIntents(int index) {
                copyOnWrite();
                ((StickyAction) this.instance).removeIntents(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StickyAction();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.intents_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    StickyAction other = (StickyAction) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.intents_ = visitor.visitList(this.intents_, other.intents_);
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
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.name_ = s;
                            } else if (tag == 18) {
                                if (!this.intents_.isModifiable()) {
                                    this.intents_ = GeneratedMessageLite.mutableCopy(this.intents_);
                                }
                                this.intents_.add((IntentProto) input.readMessage(IntentProto.parser(), extensionRegistry));
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
                        synchronized (StickyAction.class) {
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

        public static StickyAction getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StickyAction> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
    public boolean hasUser() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
    public int getUser() {
        return this.user_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUser(int value) {
        this.bitField0_ |= 1;
        this.user_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUser() {
        this.bitField0_ &= -2;
        this.user_ = 0;
    }

    @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
    public List<StickyAction> getActionsList() {
        return this.actions_;
    }

    public List<? extends StickyActionOrBuilder> getActionsOrBuilderList() {
        return this.actions_;
    }

    @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
    public int getActionsCount() {
        return this.actions_.size();
    }

    @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
    public StickyAction getActions(int index) {
        return this.actions_.get(index);
    }

    public StickyActionOrBuilder getActionsOrBuilder(int index) {
        return this.actions_.get(index);
    }

    private void ensureActionsIsMutable() {
        if (!this.actions_.isModifiable()) {
            this.actions_ = GeneratedMessageLite.mutableCopy(this.actions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActions(int index, StickyAction value) {
        if (value != null) {
            ensureActionsIsMutable();
            this.actions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActions(int index, StickyAction.Builder builderForValue) {
        ensureActionsIsMutable();
        this.actions_.set(index, (StickyAction) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActions(StickyAction value) {
        if (value != null) {
            ensureActionsIsMutable();
            this.actions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActions(int index, StickyAction value) {
        if (value != null) {
            ensureActionsIsMutable();
            this.actions_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActions(StickyAction.Builder builderForValue) {
        ensureActionsIsMutable();
        this.actions_.add((StickyAction) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActions(int index, StickyAction.Builder builderForValue) {
        ensureActionsIsMutable();
        this.actions_.add(index, (StickyAction) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActions(Iterable<? extends StickyAction> values) {
        ensureActionsIsMutable();
        AbstractMessageLite.addAll(values, this.actions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActions() {
        this.actions_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeActions(int index) {
        ensureActionsIsMutable();
        this.actions_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.user_);
        }
        for (int i = 0; i < this.actions_.size(); i++) {
            output.writeMessage(2, this.actions_.get(i));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.user_);
        }
        for (int i = 0; i < this.actions_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.actions_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static StickyBroadcastProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (StickyBroadcastProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StickyBroadcastProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StickyBroadcastProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StickyBroadcastProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (StickyBroadcastProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StickyBroadcastProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StickyBroadcastProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StickyBroadcastProto parseFrom(InputStream input) throws IOException {
        return (StickyBroadcastProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StickyBroadcastProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StickyBroadcastProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StickyBroadcastProto parseDelimitedFrom(InputStream input) throws IOException {
        return (StickyBroadcastProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static StickyBroadcastProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StickyBroadcastProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StickyBroadcastProto parseFrom(CodedInputStream input) throws IOException {
        return (StickyBroadcastProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StickyBroadcastProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StickyBroadcastProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StickyBroadcastProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StickyBroadcastProto, Builder> implements StickyBroadcastProtoOrBuilder {
        private Builder() {
            super(StickyBroadcastProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
        public boolean hasUser() {
            return ((StickyBroadcastProto) this.instance).hasUser();
        }

        @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
        public int getUser() {
            return ((StickyBroadcastProto) this.instance).getUser();
        }

        public Builder setUser(int value) {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).setUser(value);
            return this;
        }

        public Builder clearUser() {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).clearUser();
            return this;
        }

        @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
        public List<StickyAction> getActionsList() {
            return Collections.unmodifiableList(((StickyBroadcastProto) this.instance).getActionsList());
        }

        @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
        public int getActionsCount() {
            return ((StickyBroadcastProto) this.instance).getActionsCount();
        }

        @Override // com.android.server.am.StickyBroadcastProtoOrBuilder
        public StickyAction getActions(int index) {
            return ((StickyBroadcastProto) this.instance).getActions(index);
        }

        public Builder setActions(int index, StickyAction value) {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).setActions((StickyBroadcastProto) index, (int) value);
            return this;
        }

        public Builder setActions(int index, StickyAction.Builder builderForValue) {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).setActions((StickyBroadcastProto) index, (int) builderForValue);
            return this;
        }

        public Builder addActions(StickyAction value) {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).addActions((StickyBroadcastProto) value);
            return this;
        }

        public Builder addActions(int index, StickyAction value) {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).addActions((StickyBroadcastProto) index, (int) value);
            return this;
        }

        public Builder addActions(StickyAction.Builder builderForValue) {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).addActions((StickyBroadcastProto) builderForValue);
            return this;
        }

        public Builder addActions(int index, StickyAction.Builder builderForValue) {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).addActions((StickyBroadcastProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllActions(Iterable<? extends StickyAction> values) {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).addAllActions(values);
            return this;
        }

        public Builder clearActions() {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).clearActions();
            return this;
        }

        public Builder removeActions(int index) {
            copyOnWrite();
            ((StickyBroadcastProto) this.instance).removeActions(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new StickyBroadcastProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.actions_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                StickyBroadcastProto other = (StickyBroadcastProto) arg1;
                this.user_ = visitor.visitInt(hasUser(), this.user_, other.hasUser(), other.user_);
                this.actions_ = visitor.visitList(this.actions_, other.actions_);
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
                            this.user_ = input.readInt32();
                        } else if (tag == 18) {
                            if (!this.actions_.isModifiable()) {
                                this.actions_ = GeneratedMessageLite.mutableCopy(this.actions_);
                            }
                            this.actions_.add((StickyAction) input.readMessage(StickyAction.parser(), extensionRegistry));
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
                    synchronized (StickyBroadcastProto.class) {
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

    public static StickyBroadcastProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StickyBroadcastProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
