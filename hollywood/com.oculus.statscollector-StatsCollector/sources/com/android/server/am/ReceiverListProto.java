package com.android.server.am;

import com.android.server.am.BroadcastFilterProto;
import com.android.server.am.BroadcastRecordProto;
import com.android.server.am.ProcessRecordProto;
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

public final class ReceiverListProto extends GeneratedMessageLite<ReceiverListProto, Builder> implements ReceiverListProtoOrBuilder {
    public static final int APP_FIELD_NUMBER = 1;
    public static final int CURRENT_FIELD_NUMBER = 5;
    private static final ReceiverListProto DEFAULT_INSTANCE = new ReceiverListProto();
    public static final int FILTERS_FIELD_NUMBER = 7;
    public static final int HEX_HASH_FIELD_NUMBER = 8;
    public static final int LINKED_TO_DEATH_FIELD_NUMBER = 6;
    private static volatile Parser<ReceiverListProto> PARSER = null;
    public static final int PID_FIELD_NUMBER = 2;
    public static final int UID_FIELD_NUMBER = 3;
    public static final int USER_FIELD_NUMBER = 4;
    private ProcessRecordProto app_;
    private int bitField0_;
    private BroadcastRecordProto current_;
    private Internal.ProtobufList<BroadcastFilterProto> filters_ = emptyProtobufList();
    private String hexHash_ = "";
    private boolean linkedToDeath_ = false;
    private int pid_ = 0;
    private int uid_ = 0;
    private int user_ = 0;

    private ReceiverListProto() {
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public boolean hasApp() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public ProcessRecordProto getApp() {
        ProcessRecordProto processRecordProto = this.app_;
        return processRecordProto == null ? ProcessRecordProto.getDefaultInstance() : processRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApp(ProcessRecordProto value) {
        if (value != null) {
            this.app_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApp(ProcessRecordProto.Builder builderForValue) {
        this.app_ = (ProcessRecordProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeApp(ProcessRecordProto value) {
        ProcessRecordProto processRecordProto = this.app_;
        if (processRecordProto == null || processRecordProto == ProcessRecordProto.getDefaultInstance()) {
            this.app_ = value;
        } else {
            this.app_ = (ProcessRecordProto) ((ProcessRecordProto.Builder) ProcessRecordProto.newBuilder(this.app_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearApp() {
        this.app_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public boolean hasPid() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public int getPid() {
        return this.pid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPid(int value) {
        this.bitField0_ |= 2;
        this.pid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPid() {
        this.bitField0_ &= -3;
        this.pid_ = 0;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 4;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -5;
        this.uid_ = 0;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public boolean hasUser() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public int getUser() {
        return this.user_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUser(int value) {
        this.bitField0_ |= 8;
        this.user_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUser() {
        this.bitField0_ &= -9;
        this.user_ = 0;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public boolean hasCurrent() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public BroadcastRecordProto getCurrent() {
        BroadcastRecordProto broadcastRecordProto = this.current_;
        return broadcastRecordProto == null ? BroadcastRecordProto.getDefaultInstance() : broadcastRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrent(BroadcastRecordProto value) {
        if (value != null) {
            this.current_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrent(BroadcastRecordProto.Builder builderForValue) {
        this.current_ = (BroadcastRecordProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCurrent(BroadcastRecordProto value) {
        BroadcastRecordProto broadcastRecordProto = this.current_;
        if (broadcastRecordProto == null || broadcastRecordProto == BroadcastRecordProto.getDefaultInstance()) {
            this.current_ = value;
        } else {
            this.current_ = (BroadcastRecordProto) ((BroadcastRecordProto.Builder) BroadcastRecordProto.newBuilder(this.current_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrent() {
        this.current_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public boolean hasLinkedToDeath() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public boolean getLinkedToDeath() {
        return this.linkedToDeath_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLinkedToDeath(boolean value) {
        this.bitField0_ |= 32;
        this.linkedToDeath_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLinkedToDeath() {
        this.bitField0_ &= -33;
        this.linkedToDeath_ = false;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public List<BroadcastFilterProto> getFiltersList() {
        return this.filters_;
    }

    public List<? extends BroadcastFilterProtoOrBuilder> getFiltersOrBuilderList() {
        return this.filters_;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public int getFiltersCount() {
        return this.filters_.size();
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public BroadcastFilterProto getFilters(int index) {
        return this.filters_.get(index);
    }

    public BroadcastFilterProtoOrBuilder getFiltersOrBuilder(int index) {
        return this.filters_.get(index);
    }

    private void ensureFiltersIsMutable() {
        if (!this.filters_.isModifiable()) {
            this.filters_ = GeneratedMessageLite.mutableCopy(this.filters_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilters(int index, BroadcastFilterProto value) {
        if (value != null) {
            ensureFiltersIsMutable();
            this.filters_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilters(int index, BroadcastFilterProto.Builder builderForValue) {
        ensureFiltersIsMutable();
        this.filters_.set(index, (BroadcastFilterProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFilters(BroadcastFilterProto value) {
        if (value != null) {
            ensureFiltersIsMutable();
            this.filters_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFilters(int index, BroadcastFilterProto value) {
        if (value != null) {
            ensureFiltersIsMutable();
            this.filters_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFilters(BroadcastFilterProto.Builder builderForValue) {
        ensureFiltersIsMutable();
        this.filters_.add((BroadcastFilterProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFilters(int index, BroadcastFilterProto.Builder builderForValue) {
        ensureFiltersIsMutable();
        this.filters_.add(index, (BroadcastFilterProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllFilters(Iterable<? extends BroadcastFilterProto> values) {
        ensureFiltersIsMutable();
        AbstractMessageLite.addAll(values, this.filters_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFilters() {
        this.filters_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeFilters(int index) {
        ensureFiltersIsMutable();
        this.filters_.remove(index);
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public boolean hasHexHash() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public String getHexHash() {
        return this.hexHash_;
    }

    @Override // com.android.server.am.ReceiverListProtoOrBuilder
    public ByteString getHexHashBytes() {
        return ByteString.copyFromUtf8(this.hexHash_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHexHash(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.hexHash_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHexHash() {
        this.bitField0_ &= -65;
        this.hexHash_ = getDefaultInstance().getHexHash();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHexHashBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.hexHash_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getApp());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.pid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.uid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.user_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getCurrent());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.linkedToDeath_);
        }
        for (int i = 0; i < this.filters_.size(); i++) {
            output.writeMessage(7, this.filters_.get(i));
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(8, getHexHash());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getApp());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.pid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.uid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.user_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getCurrent());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.linkedToDeath_);
        }
        for (int i = 0; i < this.filters_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(7, this.filters_.get(i));
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeStringSize(8, getHexHash());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ReceiverListProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ReceiverListProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ReceiverListProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ReceiverListProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ReceiverListProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ReceiverListProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ReceiverListProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ReceiverListProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ReceiverListProto parseFrom(InputStream input) throws IOException {
        return (ReceiverListProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ReceiverListProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ReceiverListProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ReceiverListProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ReceiverListProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ReceiverListProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ReceiverListProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ReceiverListProto parseFrom(CodedInputStream input) throws IOException {
        return (ReceiverListProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ReceiverListProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ReceiverListProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ReceiverListProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ReceiverListProto, Builder> implements ReceiverListProtoOrBuilder {
        private Builder() {
            super(ReceiverListProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public boolean hasApp() {
            return ((ReceiverListProto) this.instance).hasApp();
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public ProcessRecordProto getApp() {
            return ((ReceiverListProto) this.instance).getApp();
        }

        public Builder setApp(ProcessRecordProto value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setApp((ReceiverListProto) value);
            return this;
        }

        public Builder setApp(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setApp((ReceiverListProto) builderForValue);
            return this;
        }

        public Builder mergeApp(ProcessRecordProto value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).mergeApp(value);
            return this;
        }

        public Builder clearApp() {
            copyOnWrite();
            ((ReceiverListProto) this.instance).clearApp();
            return this;
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public boolean hasPid() {
            return ((ReceiverListProto) this.instance).hasPid();
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public int getPid() {
            return ((ReceiverListProto) this.instance).getPid();
        }

        public Builder setPid(int value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setPid(value);
            return this;
        }

        public Builder clearPid() {
            copyOnWrite();
            ((ReceiverListProto) this.instance).clearPid();
            return this;
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public boolean hasUid() {
            return ((ReceiverListProto) this.instance).hasUid();
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public int getUid() {
            return ((ReceiverListProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((ReceiverListProto) this.instance).clearUid();
            return this;
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public boolean hasUser() {
            return ((ReceiverListProto) this.instance).hasUser();
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public int getUser() {
            return ((ReceiverListProto) this.instance).getUser();
        }

        public Builder setUser(int value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setUser(value);
            return this;
        }

        public Builder clearUser() {
            copyOnWrite();
            ((ReceiverListProto) this.instance).clearUser();
            return this;
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public boolean hasCurrent() {
            return ((ReceiverListProto) this.instance).hasCurrent();
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public BroadcastRecordProto getCurrent() {
            return ((ReceiverListProto) this.instance).getCurrent();
        }

        public Builder setCurrent(BroadcastRecordProto value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setCurrent((ReceiverListProto) value);
            return this;
        }

        public Builder setCurrent(BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setCurrent((ReceiverListProto) builderForValue);
            return this;
        }

        public Builder mergeCurrent(BroadcastRecordProto value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).mergeCurrent(value);
            return this;
        }

        public Builder clearCurrent() {
            copyOnWrite();
            ((ReceiverListProto) this.instance).clearCurrent();
            return this;
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public boolean hasLinkedToDeath() {
            return ((ReceiverListProto) this.instance).hasLinkedToDeath();
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public boolean getLinkedToDeath() {
            return ((ReceiverListProto) this.instance).getLinkedToDeath();
        }

        public Builder setLinkedToDeath(boolean value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setLinkedToDeath(value);
            return this;
        }

        public Builder clearLinkedToDeath() {
            copyOnWrite();
            ((ReceiverListProto) this.instance).clearLinkedToDeath();
            return this;
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public List<BroadcastFilterProto> getFiltersList() {
            return Collections.unmodifiableList(((ReceiverListProto) this.instance).getFiltersList());
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public int getFiltersCount() {
            return ((ReceiverListProto) this.instance).getFiltersCount();
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public BroadcastFilterProto getFilters(int index) {
            return ((ReceiverListProto) this.instance).getFilters(index);
        }

        public Builder setFilters(int index, BroadcastFilterProto value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setFilters((ReceiverListProto) index, (int) value);
            return this;
        }

        public Builder setFilters(int index, BroadcastFilterProto.Builder builderForValue) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setFilters((ReceiverListProto) index, (int) builderForValue);
            return this;
        }

        public Builder addFilters(BroadcastFilterProto value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).addFilters((ReceiverListProto) value);
            return this;
        }

        public Builder addFilters(int index, BroadcastFilterProto value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).addFilters((ReceiverListProto) index, (int) value);
            return this;
        }

        public Builder addFilters(BroadcastFilterProto.Builder builderForValue) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).addFilters((ReceiverListProto) builderForValue);
            return this;
        }

        public Builder addFilters(int index, BroadcastFilterProto.Builder builderForValue) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).addFilters((ReceiverListProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllFilters(Iterable<? extends BroadcastFilterProto> values) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).addAllFilters(values);
            return this;
        }

        public Builder clearFilters() {
            copyOnWrite();
            ((ReceiverListProto) this.instance).clearFilters();
            return this;
        }

        public Builder removeFilters(int index) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).removeFilters(index);
            return this;
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public boolean hasHexHash() {
            return ((ReceiverListProto) this.instance).hasHexHash();
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public String getHexHash() {
            return ((ReceiverListProto) this.instance).getHexHash();
        }

        @Override // com.android.server.am.ReceiverListProtoOrBuilder
        public ByteString getHexHashBytes() {
            return ((ReceiverListProto) this.instance).getHexHashBytes();
        }

        public Builder setHexHash(String value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setHexHash(value);
            return this;
        }

        public Builder clearHexHash() {
            copyOnWrite();
            ((ReceiverListProto) this.instance).clearHexHash();
            return this;
        }

        public Builder setHexHashBytes(ByteString value) {
            copyOnWrite();
            ((ReceiverListProto) this.instance).setHexHashBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ReceiverListProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.filters_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ReceiverListProto other = (ReceiverListProto) arg1;
                this.app_ = (ProcessRecordProto) visitor.visitMessage(this.app_, other.app_);
                this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.user_ = visitor.visitInt(hasUser(), this.user_, other.hasUser(), other.user_);
                this.current_ = (BroadcastRecordProto) visitor.visitMessage(this.current_, other.current_);
                this.linkedToDeath_ = visitor.visitBoolean(hasLinkedToDeath(), this.linkedToDeath_, other.hasLinkedToDeath(), other.linkedToDeath_);
                this.filters_ = visitor.visitList(this.filters_, other.filters_);
                this.hexHash_ = visitor.visitString(hasHexHash(), this.hexHash_, other.hasHexHash(), other.hexHash_);
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
                            ProcessRecordProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ProcessRecordProto.Builder) this.app_.toBuilder();
                            }
                            this.app_ = (ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.app_);
                                this.app_ = (ProcessRecordProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.pid_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.uid_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.user_ = input.readInt32();
                        } else if (tag == 42) {
                            BroadcastRecordProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder2 = (BroadcastRecordProto.Builder) this.current_.toBuilder();
                            }
                            this.current_ = (BroadcastRecordProto) input.readMessage(BroadcastRecordProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.current_);
                                this.current_ = (BroadcastRecordProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ = 16 | this.bitField0_;
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.linkedToDeath_ = input.readBool();
                        } else if (tag == 58) {
                            if (!this.filters_.isModifiable()) {
                                this.filters_ = GeneratedMessageLite.mutableCopy(this.filters_);
                            }
                            this.filters_.add((BroadcastFilterProto) input.readMessage(BroadcastFilterProto.parser(), extensionRegistry));
                        } else if (tag == 66) {
                            String s = input.readString();
                            this.bitField0_ |= 64;
                            this.hexHash_ = s;
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
                    synchronized (ReceiverListProto.class) {
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

    public static ReceiverListProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ReceiverListProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
