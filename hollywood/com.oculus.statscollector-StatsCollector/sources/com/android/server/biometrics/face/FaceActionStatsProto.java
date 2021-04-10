package com.android.server.biometrics.face;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class FaceActionStatsProto extends GeneratedMessageLite<FaceActionStatsProto, Builder> implements FaceActionStatsProtoOrBuilder {
    public static final int ACCEPT_FIELD_NUMBER = 1;
    public static final int ACQUIRE_FIELD_NUMBER = 3;
    private static final FaceActionStatsProto DEFAULT_INSTANCE = new FaceActionStatsProto();
    public static final int LOCKOUT_FIELD_NUMBER = 4;
    public static final int LOCKOUT_PERMANENT_FIELD_NUMBER = 5;
    private static volatile Parser<FaceActionStatsProto> PARSER = null;
    public static final int REJECT_FIELD_NUMBER = 2;
    private int accept_ = 0;
    private int acquire_ = 0;
    private int bitField0_;
    private int lockoutPermanent_ = 0;
    private int lockout_ = 0;
    private int reject_ = 0;

    private FaceActionStatsProto() {
    }

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
    public boolean hasAccept() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
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

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
    public boolean hasReject() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
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

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
    public boolean hasAcquire() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
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

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
    public boolean hasLockout() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
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

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
    public boolean hasLockoutPermanent() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
    public int getLockoutPermanent() {
        return this.lockoutPermanent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLockoutPermanent(int value) {
        this.bitField0_ |= 16;
        this.lockoutPermanent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLockoutPermanent() {
        this.bitField0_ &= -17;
        this.lockoutPermanent_ = 0;
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
            output.writeInt32(5, this.lockoutPermanent_);
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
            size2 += CodedOutputStream.computeInt32Size(5, this.lockoutPermanent_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static FaceActionStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (FaceActionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FaceActionStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FaceActionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FaceActionStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (FaceActionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FaceActionStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FaceActionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FaceActionStatsProto parseFrom(InputStream input) throws IOException {
        return (FaceActionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FaceActionStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FaceActionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FaceActionStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (FaceActionStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static FaceActionStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FaceActionStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FaceActionStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (FaceActionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FaceActionStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FaceActionStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FaceActionStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FaceActionStatsProto, Builder> implements FaceActionStatsProtoOrBuilder {
        private Builder() {
            super(FaceActionStatsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public boolean hasAccept() {
            return ((FaceActionStatsProto) this.instance).hasAccept();
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public int getAccept() {
            return ((FaceActionStatsProto) this.instance).getAccept();
        }

        public Builder setAccept(int value) {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).setAccept(value);
            return this;
        }

        public Builder clearAccept() {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).clearAccept();
            return this;
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public boolean hasReject() {
            return ((FaceActionStatsProto) this.instance).hasReject();
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public int getReject() {
            return ((FaceActionStatsProto) this.instance).getReject();
        }

        public Builder setReject(int value) {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).setReject(value);
            return this;
        }

        public Builder clearReject() {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).clearReject();
            return this;
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public boolean hasAcquire() {
            return ((FaceActionStatsProto) this.instance).hasAcquire();
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public int getAcquire() {
            return ((FaceActionStatsProto) this.instance).getAcquire();
        }

        public Builder setAcquire(int value) {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).setAcquire(value);
            return this;
        }

        public Builder clearAcquire() {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).clearAcquire();
            return this;
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public boolean hasLockout() {
            return ((FaceActionStatsProto) this.instance).hasLockout();
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public int getLockout() {
            return ((FaceActionStatsProto) this.instance).getLockout();
        }

        public Builder setLockout(int value) {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).setLockout(value);
            return this;
        }

        public Builder clearLockout() {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).clearLockout();
            return this;
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public boolean hasLockoutPermanent() {
            return ((FaceActionStatsProto) this.instance).hasLockoutPermanent();
        }

        @Override // com.android.server.biometrics.face.FaceActionStatsProtoOrBuilder
        public int getLockoutPermanent() {
            return ((FaceActionStatsProto) this.instance).getLockoutPermanent();
        }

        public Builder setLockoutPermanent(int value) {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).setLockoutPermanent(value);
            return this;
        }

        public Builder clearLockoutPermanent() {
            copyOnWrite();
            ((FaceActionStatsProto) this.instance).clearLockoutPermanent();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new FaceActionStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                FaceActionStatsProto other = (FaceActionStatsProto) arg1;
                this.accept_ = visitor.visitInt(hasAccept(), this.accept_, other.hasAccept(), other.accept_);
                this.reject_ = visitor.visitInt(hasReject(), this.reject_, other.hasReject(), other.reject_);
                this.acquire_ = visitor.visitInt(hasAcquire(), this.acquire_, other.hasAcquire(), other.acquire_);
                this.lockout_ = visitor.visitInt(hasLockout(), this.lockout_, other.hasLockout(), other.lockout_);
                this.lockoutPermanent_ = visitor.visitInt(hasLockoutPermanent(), this.lockoutPermanent_, other.hasLockoutPermanent(), other.lockoutPermanent_);
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
                            this.lockoutPermanent_ = input.readInt32();
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
                    synchronized (FaceActionStatsProto.class) {
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

    public static FaceActionStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FaceActionStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
