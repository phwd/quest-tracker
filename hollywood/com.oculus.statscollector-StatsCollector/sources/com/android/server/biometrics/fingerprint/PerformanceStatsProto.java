package com.android.server.biometrics.fingerprint;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PerformanceStatsProto extends GeneratedMessageLite<PerformanceStatsProto, Builder> implements PerformanceStatsProtoOrBuilder {
    public static final int ACCEPT_FIELD_NUMBER = 1;
    public static final int ACQUIRE_FIELD_NUMBER = 3;
    private static final PerformanceStatsProto DEFAULT_INSTANCE = new PerformanceStatsProto();
    public static final int LOCKOUT_FIELD_NUMBER = 4;
    private static volatile Parser<PerformanceStatsProto> PARSER = null;
    public static final int PERMANENT_LOCKOUT_FIELD_NUMBER = 5;
    public static final int REJECT_FIELD_NUMBER = 2;
    private int accept_ = 0;
    private int acquire_ = 0;
    private int bitField0_;
    private int lockout_ = 0;
    private int permanentLockout_ = 0;
    private int reject_ = 0;

    private PerformanceStatsProto() {
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public boolean hasAccept() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public int getAccept() {
        return this.accept_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAccept(int value) {
        this.bitField0_ |= 1;
        this.accept_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAccept() {
        this.bitField0_ &= -2;
        this.accept_ = 0;
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public boolean hasReject() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public int getReject() {
        return this.reject_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReject(int value) {
        this.bitField0_ |= 2;
        this.reject_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReject() {
        this.bitField0_ &= -3;
        this.reject_ = 0;
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public boolean hasAcquire() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public int getAcquire() {
        return this.acquire_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAcquire(int value) {
        this.bitField0_ |= 4;
        this.acquire_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAcquire() {
        this.bitField0_ &= -5;
        this.acquire_ = 0;
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public boolean hasLockout() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public int getLockout() {
        return this.lockout_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLockout(int value) {
        this.bitField0_ |= 8;
        this.lockout_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLockout() {
        this.bitField0_ &= -9;
        this.lockout_ = 0;
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public boolean hasPermanentLockout() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
    public int getPermanentLockout() {
        return this.permanentLockout_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPermanentLockout(int value) {
        this.bitField0_ |= 16;
        this.permanentLockout_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPermanentLockout() {
        this.bitField0_ &= -17;
        this.permanentLockout_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.accept_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.reject_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.acquire_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.lockout_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.permanentLockout_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.accept_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.reject_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.acquire_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.lockout_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.permanentLockout_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PerformanceStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PerformanceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PerformanceStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PerformanceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PerformanceStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PerformanceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PerformanceStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PerformanceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PerformanceStatsProto parseFrom(InputStream input) throws IOException {
        return (PerformanceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PerformanceStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PerformanceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PerformanceStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PerformanceStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PerformanceStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PerformanceStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PerformanceStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (PerformanceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PerformanceStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PerformanceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PerformanceStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PerformanceStatsProto, Builder> implements PerformanceStatsProtoOrBuilder {
        private Builder() {
            super(PerformanceStatsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public boolean hasAccept() {
            return ((PerformanceStatsProto) this.instance).hasAccept();
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public int getAccept() {
            return ((PerformanceStatsProto) this.instance).getAccept();
        }

        public Builder setAccept(int value) {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).setAccept(value);
            return this;
        }

        public Builder clearAccept() {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).clearAccept();
            return this;
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public boolean hasReject() {
            return ((PerformanceStatsProto) this.instance).hasReject();
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public int getReject() {
            return ((PerformanceStatsProto) this.instance).getReject();
        }

        public Builder setReject(int value) {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).setReject(value);
            return this;
        }

        public Builder clearReject() {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).clearReject();
            return this;
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public boolean hasAcquire() {
            return ((PerformanceStatsProto) this.instance).hasAcquire();
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public int getAcquire() {
            return ((PerformanceStatsProto) this.instance).getAcquire();
        }

        public Builder setAcquire(int value) {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).setAcquire(value);
            return this;
        }

        public Builder clearAcquire() {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).clearAcquire();
            return this;
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public boolean hasLockout() {
            return ((PerformanceStatsProto) this.instance).hasLockout();
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public int getLockout() {
            return ((PerformanceStatsProto) this.instance).getLockout();
        }

        public Builder setLockout(int value) {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).setLockout(value);
            return this;
        }

        public Builder clearLockout() {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).clearLockout();
            return this;
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public boolean hasPermanentLockout() {
            return ((PerformanceStatsProto) this.instance).hasPermanentLockout();
        }

        @Override // com.android.server.biometrics.fingerprint.PerformanceStatsProtoOrBuilder
        public int getPermanentLockout() {
            return ((PerformanceStatsProto) this.instance).getPermanentLockout();
        }

        public Builder setPermanentLockout(int value) {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).setPermanentLockout(value);
            return this;
        }

        public Builder clearPermanentLockout() {
            copyOnWrite();
            ((PerformanceStatsProto) this.instance).clearPermanentLockout();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PerformanceStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PerformanceStatsProto other = (PerformanceStatsProto) arg1;
                this.accept_ = visitor.visitInt(hasAccept(), this.accept_, other.hasAccept(), other.accept_);
                this.reject_ = visitor.visitInt(hasReject(), this.reject_, other.hasReject(), other.reject_);
                this.acquire_ = visitor.visitInt(hasAcquire(), this.acquire_, other.hasAcquire(), other.acquire_);
                this.lockout_ = visitor.visitInt(hasLockout(), this.lockout_, other.hasLockout(), other.lockout_);
                this.permanentLockout_ = visitor.visitInt(hasPermanentLockout(), this.permanentLockout_, other.hasPermanentLockout(), other.permanentLockout_);
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
                            this.accept_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.reject_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.acquire_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.lockout_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.permanentLockout_ = input.readInt32();
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
                    synchronized (PerformanceStatsProto.class) {
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

    public static PerformanceStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PerformanceStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
