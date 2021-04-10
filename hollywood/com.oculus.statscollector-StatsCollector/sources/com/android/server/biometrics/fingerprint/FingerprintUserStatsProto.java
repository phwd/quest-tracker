package com.android.server.biometrics.fingerprint;

import com.android.server.biometrics.fingerprint.PerformanceStatsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class FingerprintUserStatsProto extends GeneratedMessageLite<FingerprintUserStatsProto, Builder> implements FingerprintUserStatsProtoOrBuilder {
    public static final int CRYPTO_FIELD_NUMBER = 4;
    private static final FingerprintUserStatsProto DEFAULT_INSTANCE = new FingerprintUserStatsProto();
    public static final int NORMAL_FIELD_NUMBER = 3;
    public static final int NUM_FINGERPRINTS_FIELD_NUMBER = 2;
    private static volatile Parser<FingerprintUserStatsProto> PARSER = null;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private PerformanceStatsProto crypto_;
    private PerformanceStatsProto normal_;
    private int numFingerprints_ = 0;
    private int userId_ = 0;

    private FingerprintUserStatsProto() {
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
    public int getUserId() {
        return this.userId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(int value) {
        this.bitField0_ |= 1;
        this.userId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserId() {
        this.bitField0_ &= -2;
        this.userId_ = 0;
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
    public boolean hasNumFingerprints() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
    public int getNumFingerprints() {
        return this.numFingerprints_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumFingerprints(int value) {
        this.bitField0_ |= 2;
        this.numFingerprints_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumFingerprints() {
        this.bitField0_ &= -3;
        this.numFingerprints_ = 0;
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
    public boolean hasNormal() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
    public PerformanceStatsProto getNormal() {
        PerformanceStatsProto performanceStatsProto = this.normal_;
        return performanceStatsProto == null ? PerformanceStatsProto.getDefaultInstance() : performanceStatsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNormal(PerformanceStatsProto value) {
        if (value != null) {
            this.normal_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNormal(PerformanceStatsProto.Builder builderForValue) {
        this.normal_ = (PerformanceStatsProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNormal(PerformanceStatsProto value) {
        PerformanceStatsProto performanceStatsProto = this.normal_;
        if (performanceStatsProto == null || performanceStatsProto == PerformanceStatsProto.getDefaultInstance()) {
            this.normal_ = value;
        } else {
            this.normal_ = (PerformanceStatsProto) ((PerformanceStatsProto.Builder) PerformanceStatsProto.newBuilder(this.normal_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNormal() {
        this.normal_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
    public boolean hasCrypto() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
    public PerformanceStatsProto getCrypto() {
        PerformanceStatsProto performanceStatsProto = this.crypto_;
        return performanceStatsProto == null ? PerformanceStatsProto.getDefaultInstance() : performanceStatsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCrypto(PerformanceStatsProto value) {
        if (value != null) {
            this.crypto_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCrypto(PerformanceStatsProto.Builder builderForValue) {
        this.crypto_ = (PerformanceStatsProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCrypto(PerformanceStatsProto value) {
        PerformanceStatsProto performanceStatsProto = this.crypto_;
        if (performanceStatsProto == null || performanceStatsProto == PerformanceStatsProto.getDefaultInstance()) {
            this.crypto_ = value;
        } else {
            this.crypto_ = (PerformanceStatsProto) ((PerformanceStatsProto.Builder) PerformanceStatsProto.newBuilder(this.crypto_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCrypto() {
        this.crypto_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.userId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.numFingerprints_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getNormal());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getCrypto());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.userId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.numFingerprints_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getNormal());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getCrypto());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static FingerprintUserStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (FingerprintUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FingerprintUserStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FingerprintUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FingerprintUserStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (FingerprintUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FingerprintUserStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FingerprintUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FingerprintUserStatsProto parseFrom(InputStream input) throws IOException {
        return (FingerprintUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FingerprintUserStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FingerprintUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FingerprintUserStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (FingerprintUserStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static FingerprintUserStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FingerprintUserStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FingerprintUserStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (FingerprintUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FingerprintUserStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FingerprintUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FingerprintUserStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FingerprintUserStatsProto, Builder> implements FingerprintUserStatsProtoOrBuilder {
        private Builder() {
            super(FingerprintUserStatsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
        public boolean hasUserId() {
            return ((FingerprintUserStatsProto) this.instance).hasUserId();
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
        public int getUserId() {
            return ((FingerprintUserStatsProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).clearUserId();
            return this;
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
        public boolean hasNumFingerprints() {
            return ((FingerprintUserStatsProto) this.instance).hasNumFingerprints();
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
        public int getNumFingerprints() {
            return ((FingerprintUserStatsProto) this.instance).getNumFingerprints();
        }

        public Builder setNumFingerprints(int value) {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).setNumFingerprints(value);
            return this;
        }

        public Builder clearNumFingerprints() {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).clearNumFingerprints();
            return this;
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
        public boolean hasNormal() {
            return ((FingerprintUserStatsProto) this.instance).hasNormal();
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
        public PerformanceStatsProto getNormal() {
            return ((FingerprintUserStatsProto) this.instance).getNormal();
        }

        public Builder setNormal(PerformanceStatsProto value) {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).setNormal((FingerprintUserStatsProto) value);
            return this;
        }

        public Builder setNormal(PerformanceStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).setNormal((FingerprintUserStatsProto) builderForValue);
            return this;
        }

        public Builder mergeNormal(PerformanceStatsProto value) {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).mergeNormal(value);
            return this;
        }

        public Builder clearNormal() {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).clearNormal();
            return this;
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
        public boolean hasCrypto() {
            return ((FingerprintUserStatsProto) this.instance).hasCrypto();
        }

        @Override // com.android.server.biometrics.fingerprint.FingerprintUserStatsProtoOrBuilder
        public PerformanceStatsProto getCrypto() {
            return ((FingerprintUserStatsProto) this.instance).getCrypto();
        }

        public Builder setCrypto(PerformanceStatsProto value) {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).setCrypto((FingerprintUserStatsProto) value);
            return this;
        }

        public Builder setCrypto(PerformanceStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).setCrypto((FingerprintUserStatsProto) builderForValue);
            return this;
        }

        public Builder mergeCrypto(PerformanceStatsProto value) {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).mergeCrypto(value);
            return this;
        }

        public Builder clearCrypto() {
            copyOnWrite();
            ((FingerprintUserStatsProto) this.instance).clearCrypto();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new FingerprintUserStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                FingerprintUserStatsProto other = (FingerprintUserStatsProto) arg1;
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.numFingerprints_ = visitor.visitInt(hasNumFingerprints(), this.numFingerprints_, other.hasNumFingerprints(), other.numFingerprints_);
                this.normal_ = (PerformanceStatsProto) visitor.visitMessage(this.normal_, other.normal_);
                this.crypto_ = (PerformanceStatsProto) visitor.visitMessage(this.crypto_, other.crypto_);
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
                            this.userId_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.numFingerprints_ = input.readInt32();
                        } else if (tag == 26) {
                            PerformanceStatsProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (PerformanceStatsProto.Builder) this.normal_.toBuilder();
                            }
                            this.normal_ = (PerformanceStatsProto) input.readMessage(PerformanceStatsProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.normal_);
                                this.normal_ = (PerformanceStatsProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 34) {
                            PerformanceStatsProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder2 = (PerformanceStatsProto.Builder) this.crypto_.toBuilder();
                            }
                            this.crypto_ = (PerformanceStatsProto) input.readMessage(PerformanceStatsProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.crypto_);
                                this.crypto_ = (PerformanceStatsProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ = 8 | this.bitField0_;
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
                    synchronized (FingerprintUserStatsProto.class) {
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

    public static FingerprintUserStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FingerprintUserStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
