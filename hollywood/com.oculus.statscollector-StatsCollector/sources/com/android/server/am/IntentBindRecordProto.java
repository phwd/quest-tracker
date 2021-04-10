package com.android.server.am;

import android.content.IntentProto;
import com.android.server.am.AppBindRecordProto;
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

public final class IntentBindRecordProto extends GeneratedMessageLite<IntentBindRecordProto, Builder> implements IntentBindRecordProtoOrBuilder {
    public static final int APPS_FIELD_NUMBER = 8;
    public static final int AUTO_CREATE_FIELD_NUMBER = 3;
    public static final int BINDER_FIELD_NUMBER = 2;
    private static final IntentBindRecordProto DEFAULT_INSTANCE = new IntentBindRecordProto();
    public static final int DO_REBIND_FIELD_NUMBER = 7;
    public static final int HAS_BOUND_FIELD_NUMBER = 6;
    public static final int INTENT_FIELD_NUMBER = 1;
    private static volatile Parser<IntentBindRecordProto> PARSER = null;
    public static final int RECEIVED_FIELD_NUMBER = 5;
    public static final int REQUESTED_FIELD_NUMBER = 4;
    private Internal.ProtobufList<AppBindRecordProto> apps_ = emptyProtobufList();
    private boolean autoCreate_ = false;
    private String binder_ = "";
    private int bitField0_;
    private boolean doRebind_ = false;
    private boolean hasBound_ = false;
    private IntentProto intent_;
    private boolean received_ = false;
    private boolean requested_ = false;

    private IntentBindRecordProto() {
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean hasIntent() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public IntentProto getIntent() {
        IntentProto intentProto = this.intent_;
        return intentProto == null ? IntentProto.getDefaultInstance() : intentProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIntent(IntentProto value) {
        if (value != null) {
            this.intent_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIntent(IntentProto.Builder builderForValue) {
        this.intent_ = (IntentProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIntent(IntentProto value) {
        IntentProto intentProto = this.intent_;
        if (intentProto == null || intentProto == IntentProto.getDefaultInstance()) {
            this.intent_ = value;
        } else {
            this.intent_ = (IntentProto) ((IntentProto.Builder) IntentProto.newBuilder(this.intent_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIntent() {
        this.intent_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean hasBinder() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public String getBinder() {
        return this.binder_;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public ByteString getBinderBytes() {
        return ByteString.copyFromUtf8(this.binder_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBinder(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.binder_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBinder() {
        this.bitField0_ &= -3;
        this.binder_ = getDefaultInstance().getBinder();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBinderBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.binder_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean hasAutoCreate() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean getAutoCreate() {
        return this.autoCreate_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAutoCreate(boolean value) {
        this.bitField0_ |= 4;
        this.autoCreate_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAutoCreate() {
        this.bitField0_ &= -5;
        this.autoCreate_ = false;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean hasRequested() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean getRequested() {
        return this.requested_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequested(boolean value) {
        this.bitField0_ |= 8;
        this.requested_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRequested() {
        this.bitField0_ &= -9;
        this.requested_ = false;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean hasReceived() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean getReceived() {
        return this.received_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReceived(boolean value) {
        this.bitField0_ |= 16;
        this.received_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReceived() {
        this.bitField0_ &= -17;
        this.received_ = false;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean hasHasBound() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean getHasBound() {
        return this.hasBound_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasBound(boolean value) {
        this.bitField0_ |= 32;
        this.hasBound_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasBound() {
        this.bitField0_ &= -33;
        this.hasBound_ = false;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean hasDoRebind() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public boolean getDoRebind() {
        return this.doRebind_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDoRebind(boolean value) {
        this.bitField0_ |= 64;
        this.doRebind_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDoRebind() {
        this.bitField0_ &= -65;
        this.doRebind_ = false;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public List<AppBindRecordProto> getAppsList() {
        return this.apps_;
    }

    public List<? extends AppBindRecordProtoOrBuilder> getAppsOrBuilderList() {
        return this.apps_;
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public int getAppsCount() {
        return this.apps_.size();
    }

    @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
    public AppBindRecordProto getApps(int index) {
        return this.apps_.get(index);
    }

    public AppBindRecordProtoOrBuilder getAppsOrBuilder(int index) {
        return this.apps_.get(index);
    }

    private void ensureAppsIsMutable() {
        if (!this.apps_.isModifiable()) {
            this.apps_ = GeneratedMessageLite.mutableCopy(this.apps_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApps(int index, AppBindRecordProto value) {
        if (value != null) {
            ensureAppsIsMutable();
            this.apps_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApps(int index, AppBindRecordProto.Builder builderForValue) {
        ensureAppsIsMutable();
        this.apps_.set(index, (AppBindRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApps(AppBindRecordProto value) {
        if (value != null) {
            ensureAppsIsMutable();
            this.apps_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApps(int index, AppBindRecordProto value) {
        if (value != null) {
            ensureAppsIsMutable();
            this.apps_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApps(AppBindRecordProto.Builder builderForValue) {
        ensureAppsIsMutable();
        this.apps_.add((AppBindRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApps(int index, AppBindRecordProto.Builder builderForValue) {
        ensureAppsIsMutable();
        this.apps_.add(index, (AppBindRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllApps(Iterable<? extends AppBindRecordProto> values) {
        ensureAppsIsMutable();
        AbstractMessageLite.addAll(values, this.apps_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearApps() {
        this.apps_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeApps(int index) {
        ensureAppsIsMutable();
        this.apps_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getIntent());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getBinder());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(3, this.autoCreate_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.requested_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.received_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.hasBound_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.doRebind_);
        }
        for (int i = 0; i < this.apps_.size(); i++) {
            output.writeMessage(8, this.apps_.get(i));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getIntent());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getBinder());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(3, this.autoCreate_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.requested_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.received_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.hasBound_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.doRebind_);
        }
        for (int i = 0; i < this.apps_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(8, this.apps_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static IntentBindRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IntentBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntentBindRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntentBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntentBindRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IntentBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntentBindRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntentBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntentBindRecordProto parseFrom(InputStream input) throws IOException {
        return (IntentBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentBindRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntentBindRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IntentBindRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentBindRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentBindRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntentBindRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (IntentBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentBindRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IntentBindRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IntentBindRecordProto, Builder> implements IntentBindRecordProtoOrBuilder {
        private Builder() {
            super(IntentBindRecordProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean hasIntent() {
            return ((IntentBindRecordProto) this.instance).hasIntent();
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public IntentProto getIntent() {
            return ((IntentBindRecordProto) this.instance).getIntent();
        }

        public Builder setIntent(IntentProto value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setIntent((IntentBindRecordProto) value);
            return this;
        }

        public Builder setIntent(IntentProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setIntent((IntentBindRecordProto) builderForValue);
            return this;
        }

        public Builder mergeIntent(IntentProto value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).mergeIntent(value);
            return this;
        }

        public Builder clearIntent() {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).clearIntent();
            return this;
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean hasBinder() {
            return ((IntentBindRecordProto) this.instance).hasBinder();
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public String getBinder() {
            return ((IntentBindRecordProto) this.instance).getBinder();
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public ByteString getBinderBytes() {
            return ((IntentBindRecordProto) this.instance).getBinderBytes();
        }

        public Builder setBinder(String value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setBinder(value);
            return this;
        }

        public Builder clearBinder() {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).clearBinder();
            return this;
        }

        public Builder setBinderBytes(ByteString value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setBinderBytes(value);
            return this;
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean hasAutoCreate() {
            return ((IntentBindRecordProto) this.instance).hasAutoCreate();
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean getAutoCreate() {
            return ((IntentBindRecordProto) this.instance).getAutoCreate();
        }

        public Builder setAutoCreate(boolean value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setAutoCreate(value);
            return this;
        }

        public Builder clearAutoCreate() {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).clearAutoCreate();
            return this;
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean hasRequested() {
            return ((IntentBindRecordProto) this.instance).hasRequested();
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean getRequested() {
            return ((IntentBindRecordProto) this.instance).getRequested();
        }

        public Builder setRequested(boolean value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setRequested(value);
            return this;
        }

        public Builder clearRequested() {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).clearRequested();
            return this;
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean hasReceived() {
            return ((IntentBindRecordProto) this.instance).hasReceived();
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean getReceived() {
            return ((IntentBindRecordProto) this.instance).getReceived();
        }

        public Builder setReceived(boolean value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setReceived(value);
            return this;
        }

        public Builder clearReceived() {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).clearReceived();
            return this;
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean hasHasBound() {
            return ((IntentBindRecordProto) this.instance).hasHasBound();
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean getHasBound() {
            return ((IntentBindRecordProto) this.instance).getHasBound();
        }

        public Builder setHasBound(boolean value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setHasBound(value);
            return this;
        }

        public Builder clearHasBound() {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).clearHasBound();
            return this;
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean hasDoRebind() {
            return ((IntentBindRecordProto) this.instance).hasDoRebind();
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public boolean getDoRebind() {
            return ((IntentBindRecordProto) this.instance).getDoRebind();
        }

        public Builder setDoRebind(boolean value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setDoRebind(value);
            return this;
        }

        public Builder clearDoRebind() {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).clearDoRebind();
            return this;
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public List<AppBindRecordProto> getAppsList() {
            return Collections.unmodifiableList(((IntentBindRecordProto) this.instance).getAppsList());
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public int getAppsCount() {
            return ((IntentBindRecordProto) this.instance).getAppsCount();
        }

        @Override // com.android.server.am.IntentBindRecordProtoOrBuilder
        public AppBindRecordProto getApps(int index) {
            return ((IntentBindRecordProto) this.instance).getApps(index);
        }

        public Builder setApps(int index, AppBindRecordProto value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setApps((IntentBindRecordProto) index, (int) value);
            return this;
        }

        public Builder setApps(int index, AppBindRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).setApps((IntentBindRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addApps(AppBindRecordProto value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).addApps((IntentBindRecordProto) value);
            return this;
        }

        public Builder addApps(int index, AppBindRecordProto value) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).addApps((IntentBindRecordProto) index, (int) value);
            return this;
        }

        public Builder addApps(AppBindRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).addApps((IntentBindRecordProto) builderForValue);
            return this;
        }

        public Builder addApps(int index, AppBindRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).addApps((IntentBindRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllApps(Iterable<? extends AppBindRecordProto> values) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).addAllApps(values);
            return this;
        }

        public Builder clearApps() {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).clearApps();
            return this;
        }

        public Builder removeApps(int index) {
            copyOnWrite();
            ((IntentBindRecordProto) this.instance).removeApps(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IntentBindRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.apps_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IntentBindRecordProto other = (IntentBindRecordProto) arg1;
                this.intent_ = (IntentProto) visitor.visitMessage(this.intent_, other.intent_);
                this.binder_ = visitor.visitString(hasBinder(), this.binder_, other.hasBinder(), other.binder_);
                this.autoCreate_ = visitor.visitBoolean(hasAutoCreate(), this.autoCreate_, other.hasAutoCreate(), other.autoCreate_);
                this.requested_ = visitor.visitBoolean(hasRequested(), this.requested_, other.hasRequested(), other.requested_);
                this.received_ = visitor.visitBoolean(hasReceived(), this.received_, other.hasReceived(), other.received_);
                this.hasBound_ = visitor.visitBoolean(hasHasBound(), this.hasBound_, other.hasHasBound(), other.hasBound_);
                this.doRebind_ = visitor.visitBoolean(hasDoRebind(), this.doRebind_, other.hasDoRebind(), other.doRebind_);
                this.apps_ = visitor.visitList(this.apps_, other.apps_);
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
                            IntentProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (IntentProto.Builder) this.intent_.toBuilder();
                            }
                            this.intent_ = (IntentProto) input.readMessage(IntentProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.intent_);
                                this.intent_ = (IntentProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.binder_ = s;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.autoCreate_ = input.readBool();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.requested_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.received_ = input.readBool();
                        } else if (tag == 48) {
                            this.bitField0_ = 32 | this.bitField0_;
                            this.hasBound_ = input.readBool();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.doRebind_ = input.readBool();
                        } else if (tag == 66) {
                            if (!this.apps_.isModifiable()) {
                                this.apps_ = GeneratedMessageLite.mutableCopy(this.apps_);
                            }
                            this.apps_.add((AppBindRecordProto) input.readMessage(AppBindRecordProto.parser(), extensionRegistry));
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
                    synchronized (IntentBindRecordProto.class) {
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

    public static IntentBindRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IntentBindRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
