package com.android.server.connectivity;

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

public final class DnsEvent extends GeneratedMessageLite<DnsEvent, Builder> implements DnsEventOrBuilder {
    private static final DnsEvent DEFAULT_INSTANCE = new DnsEvent();
    public static final int DNS_RETURN_CODE_FIELD_NUMBER = 1;
    public static final int DNS_TIME_FIELD_NUMBER = 2;
    private static volatile Parser<DnsEvent> PARSER;
    private Internal.IntList dnsReturnCode_ = emptyIntList();
    private Internal.LongList dnsTime_ = emptyLongList();

    private DnsEvent() {
    }

    @Override // com.android.server.connectivity.DnsEventOrBuilder
    public List<Integer> getDnsReturnCodeList() {
        return this.dnsReturnCode_;
    }

    @Override // com.android.server.connectivity.DnsEventOrBuilder
    public int getDnsReturnCodeCount() {
        return this.dnsReturnCode_.size();
    }

    @Override // com.android.server.connectivity.DnsEventOrBuilder
    public int getDnsReturnCode(int index) {
        return this.dnsReturnCode_.getInt(index);
    }

    private void ensureDnsReturnCodeIsMutable() {
        if (!this.dnsReturnCode_.isModifiable()) {
            this.dnsReturnCode_ = GeneratedMessageLite.mutableCopy(this.dnsReturnCode_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDnsReturnCode(int index, int value) {
        ensureDnsReturnCodeIsMutable();
        this.dnsReturnCode_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDnsReturnCode(int value) {
        ensureDnsReturnCodeIsMutable();
        this.dnsReturnCode_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDnsReturnCode(Iterable<? extends Integer> values) {
        ensureDnsReturnCodeIsMutable();
        AbstractMessageLite.addAll(values, this.dnsReturnCode_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDnsReturnCode() {
        this.dnsReturnCode_ = emptyIntList();
    }

    @Override // com.android.server.connectivity.DnsEventOrBuilder
    public List<Long> getDnsTimeList() {
        return this.dnsTime_;
    }

    @Override // com.android.server.connectivity.DnsEventOrBuilder
    public int getDnsTimeCount() {
        return this.dnsTime_.size();
    }

    @Override // com.android.server.connectivity.DnsEventOrBuilder
    public long getDnsTime(int index) {
        return this.dnsTime_.getLong(index);
    }

    private void ensureDnsTimeIsMutable() {
        if (!this.dnsTime_.isModifiable()) {
            this.dnsTime_ = GeneratedMessageLite.mutableCopy(this.dnsTime_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDnsTime(int index, long value) {
        ensureDnsTimeIsMutable();
        this.dnsTime_.setLong(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDnsTime(long value) {
        ensureDnsTimeIsMutable();
        this.dnsTime_.addLong(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDnsTime(Iterable<? extends Long> values) {
        ensureDnsTimeIsMutable();
        AbstractMessageLite.addAll(values, this.dnsTime_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDnsTime() {
        this.dnsTime_ = emptyLongList();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.dnsReturnCode_.size(); i++) {
            output.writeInt32(1, this.dnsReturnCode_.getInt(i));
        }
        for (int i2 = 0; i2 < this.dnsTime_.size(); i2++) {
            output.writeInt64(2, this.dnsTime_.getLong(i2));
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int dataSize = 0;
        for (int i = 0; i < this.dnsReturnCode_.size(); i++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.dnsReturnCode_.getInt(i));
        }
        int size2 = 0 + dataSize + (getDnsReturnCodeList().size() * 1);
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.dnsTime_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeInt64SizeNoTag(this.dnsTime_.getLong(i2));
        }
        int size3 = size2 + dataSize2 + (getDnsTimeList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DnsEvent parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DnsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DnsEvent parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DnsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DnsEvent parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DnsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DnsEvent parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DnsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DnsEvent parseFrom(InputStream input) throws IOException {
        return (DnsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DnsEvent parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DnsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DnsEvent parseDelimitedFrom(InputStream input) throws IOException {
        return (DnsEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DnsEvent parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DnsEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DnsEvent parseFrom(CodedInputStream input) throws IOException {
        return (DnsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DnsEvent parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DnsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DnsEvent prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DnsEvent, Builder> implements DnsEventOrBuilder {
        private Builder() {
            super(DnsEvent.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.connectivity.DnsEventOrBuilder
        public List<Integer> getDnsReturnCodeList() {
            return Collections.unmodifiableList(((DnsEvent) this.instance).getDnsReturnCodeList());
        }

        @Override // com.android.server.connectivity.DnsEventOrBuilder
        public int getDnsReturnCodeCount() {
            return ((DnsEvent) this.instance).getDnsReturnCodeCount();
        }

        @Override // com.android.server.connectivity.DnsEventOrBuilder
        public int getDnsReturnCode(int index) {
            return ((DnsEvent) this.instance).getDnsReturnCode(index);
        }

        public Builder setDnsReturnCode(int index, int value) {
            copyOnWrite();
            ((DnsEvent) this.instance).setDnsReturnCode(index, value);
            return this;
        }

        public Builder addDnsReturnCode(int value) {
            copyOnWrite();
            ((DnsEvent) this.instance).addDnsReturnCode(value);
            return this;
        }

        public Builder addAllDnsReturnCode(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((DnsEvent) this.instance).addAllDnsReturnCode(values);
            return this;
        }

        public Builder clearDnsReturnCode() {
            copyOnWrite();
            ((DnsEvent) this.instance).clearDnsReturnCode();
            return this;
        }

        @Override // com.android.server.connectivity.DnsEventOrBuilder
        public List<Long> getDnsTimeList() {
            return Collections.unmodifiableList(((DnsEvent) this.instance).getDnsTimeList());
        }

        @Override // com.android.server.connectivity.DnsEventOrBuilder
        public int getDnsTimeCount() {
            return ((DnsEvent) this.instance).getDnsTimeCount();
        }

        @Override // com.android.server.connectivity.DnsEventOrBuilder
        public long getDnsTime(int index) {
            return ((DnsEvent) this.instance).getDnsTime(index);
        }

        public Builder setDnsTime(int index, long value) {
            copyOnWrite();
            ((DnsEvent) this.instance).setDnsTime(index, value);
            return this;
        }

        public Builder addDnsTime(long value) {
            copyOnWrite();
            ((DnsEvent) this.instance).addDnsTime(value);
            return this;
        }

        public Builder addAllDnsTime(Iterable<? extends Long> values) {
            copyOnWrite();
            ((DnsEvent) this.instance).addAllDnsTime(values);
            return this;
        }

        public Builder clearDnsTime() {
            copyOnWrite();
            ((DnsEvent) this.instance).clearDnsTime();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DnsEvent();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.dnsReturnCode_.makeImmutable();
                this.dnsTime_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DnsEvent other = (DnsEvent) arg1;
                this.dnsReturnCode_ = visitor.visitIntList(this.dnsReturnCode_, other.dnsReturnCode_);
                this.dnsTime_ = visitor.visitLongList(this.dnsTime_, other.dnsTime_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                            if (!this.dnsReturnCode_.isModifiable()) {
                                this.dnsReturnCode_ = GeneratedMessageLite.mutableCopy(this.dnsReturnCode_);
                            }
                            this.dnsReturnCode_.addInt(input.readInt32());
                        } else if (tag == 10) {
                            int limit = input.pushLimit(input.readRawVarint32());
                            if (!this.dnsReturnCode_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                this.dnsReturnCode_ = GeneratedMessageLite.mutableCopy(this.dnsReturnCode_);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                this.dnsReturnCode_.addInt(input.readInt32());
                            }
                            input.popLimit(limit);
                        } else if (tag == 16) {
                            if (!this.dnsTime_.isModifiable()) {
                                this.dnsTime_ = GeneratedMessageLite.mutableCopy(this.dnsTime_);
                            }
                            this.dnsTime_.addLong(input.readInt64());
                        } else if (tag == 18) {
                            int limit2 = input.pushLimit(input.readRawVarint32());
                            if (!this.dnsTime_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                this.dnsTime_ = GeneratedMessageLite.mutableCopy(this.dnsTime_);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                this.dnsTime_.addLong(input.readInt64());
                            }
                            input.popLimit(limit2);
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
                    synchronized (DnsEvent.class) {
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

    public static DnsEvent getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DnsEvent> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
