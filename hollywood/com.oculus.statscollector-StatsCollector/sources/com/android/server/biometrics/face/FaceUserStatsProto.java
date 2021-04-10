package com.android.server.biometrics.face;

import com.android.server.biometrics.face.FaceActionStatsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class FaceUserStatsProto extends GeneratedMessageLite<FaceUserStatsProto, Builder> implements FaceUserStatsProtoOrBuilder {
    public static final int CRYPTO_FIELD_NUMBER = 4;
    private static final FaceUserStatsProto DEFAULT_INSTANCE = new FaceUserStatsProto();
    public static final int NORMAL_FIELD_NUMBER = 3;
    public static final int NUM_FACES_FIELD_NUMBER = 2;
    private static volatile Parser<FaceUserStatsProto> PARSER = null;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private FaceActionStatsProto crypto_;
    private FaceActionStatsProto normal_;
    private int numFaces_ = 0;
    private int userId_ = 0;

    private FaceUserStatsProto() {
    }

    @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
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

    @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
    public boolean hasNumFaces() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
    public int getNumFaces() {
        return this.numFaces_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumFaces(int value) {
        this.bitField0_ |= 2;
        this.numFaces_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumFaces() {
        this.bitField0_ &= -3;
        this.numFaces_ = 0;
    }

    @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
    public boolean hasNormal() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
    public FaceActionStatsProto getNormal() {
        FaceActionStatsProto faceActionStatsProto = this.normal_;
        return faceActionStatsProto == null ? FaceActionStatsProto.getDefaultInstance() : faceActionStatsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNormal(FaceActionStatsProto value) {
        if (value != null) {
            this.normal_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNormal(FaceActionStatsProto.Builder builderForValue) {
        this.normal_ = (FaceActionStatsProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNormal(FaceActionStatsProto value) {
        FaceActionStatsProto faceActionStatsProto = this.normal_;
        if (faceActionStatsProto == null || faceActionStatsProto == FaceActionStatsProto.getDefaultInstance()) {
            this.normal_ = value;
        } else {
            this.normal_ = (FaceActionStatsProto) ((FaceActionStatsProto.Builder) FaceActionStatsProto.newBuilder(this.normal_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNormal() {
        this.normal_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
    public boolean hasCrypto() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
    public FaceActionStatsProto getCrypto() {
        FaceActionStatsProto faceActionStatsProto = this.crypto_;
        return faceActionStatsProto == null ? FaceActionStatsProto.getDefaultInstance() : faceActionStatsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCrypto(FaceActionStatsProto value) {
        if (value != null) {
            this.crypto_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCrypto(FaceActionStatsProto.Builder builderForValue) {
        this.crypto_ = (FaceActionStatsProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCrypto(FaceActionStatsProto value) {
        FaceActionStatsProto faceActionStatsProto = this.crypto_;
        if (faceActionStatsProto == null || faceActionStatsProto == FaceActionStatsProto.getDefaultInstance()) {
            this.crypto_ = value;
        } else {
            this.crypto_ = (FaceActionStatsProto) ((FaceActionStatsProto.Builder) FaceActionStatsProto.newBuilder(this.crypto_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
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
            output.writeInt32(2, this.numFaces_);
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
            size2 += CodedOutputStream.computeInt32Size(2, this.numFaces_);
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

    public static FaceUserStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (FaceUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FaceUserStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FaceUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FaceUserStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (FaceUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FaceUserStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FaceUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FaceUserStatsProto parseFrom(InputStream input) throws IOException {
        return (FaceUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FaceUserStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FaceUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FaceUserStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (FaceUserStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static FaceUserStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FaceUserStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FaceUserStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (FaceUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FaceUserStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FaceUserStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FaceUserStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FaceUserStatsProto, Builder> implements FaceUserStatsProtoOrBuilder {
        private Builder() {
            super(FaceUserStatsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
        public boolean hasUserId() {
            return ((FaceUserStatsProto) this.instance).hasUserId();
        }

        @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
        public int getUserId() {
            return ((FaceUserStatsProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).clearUserId();
            return this;
        }

        @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
        public boolean hasNumFaces() {
            return ((FaceUserStatsProto) this.instance).hasNumFaces();
        }

        @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
        public int getNumFaces() {
            return ((FaceUserStatsProto) this.instance).getNumFaces();
        }

        public Builder setNumFaces(int value) {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).setNumFaces(value);
            return this;
        }

        public Builder clearNumFaces() {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).clearNumFaces();
            return this;
        }

        @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
        public boolean hasNormal() {
            return ((FaceUserStatsProto) this.instance).hasNormal();
        }

        @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
        public FaceActionStatsProto getNormal() {
            return ((FaceUserStatsProto) this.instance).getNormal();
        }

        public Builder setNormal(FaceActionStatsProto value) {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).setNormal((FaceUserStatsProto) value);
            return this;
        }

        public Builder setNormal(FaceActionStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).setNormal((FaceUserStatsProto) builderForValue);
            return this;
        }

        public Builder mergeNormal(FaceActionStatsProto value) {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).mergeNormal(value);
            return this;
        }

        public Builder clearNormal() {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).clearNormal();
            return this;
        }

        @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
        public boolean hasCrypto() {
            return ((FaceUserStatsProto) this.instance).hasCrypto();
        }

        @Override // com.android.server.biometrics.face.FaceUserStatsProtoOrBuilder
        public FaceActionStatsProto getCrypto() {
            return ((FaceUserStatsProto) this.instance).getCrypto();
        }

        public Builder setCrypto(FaceActionStatsProto value) {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).setCrypto((FaceUserStatsProto) value);
            return this;
        }

        public Builder setCrypto(FaceActionStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).setCrypto((FaceUserStatsProto) builderForValue);
            return this;
        }

        public Builder mergeCrypto(FaceActionStatsProto value) {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).mergeCrypto(value);
            return this;
        }

        public Builder clearCrypto() {
            copyOnWrite();
            ((FaceUserStatsProto) this.instance).clearCrypto();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new FaceUserStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                FaceUserStatsProto other = (FaceUserStatsProto) arg1;
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.numFaces_ = visitor.visitInt(hasNumFaces(), this.numFaces_, other.hasNumFaces(), other.numFaces_);
                this.normal_ = (FaceActionStatsProto) visitor.visitMessage(this.normal_, other.normal_);
                this.crypto_ = (FaceActionStatsProto) visitor.visitMessage(this.crypto_, other.crypto_);
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
                            this.numFaces_ = input.readInt32();
                        } else if (tag == 26) {
                            FaceActionStatsProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (FaceActionStatsProto.Builder) this.normal_.toBuilder();
                            }
                            this.normal_ = (FaceActionStatsProto) input.readMessage(FaceActionStatsProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.normal_);
                                this.normal_ = (FaceActionStatsProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 34) {
                            FaceActionStatsProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder2 = (FaceActionStatsProto.Builder) this.crypto_.toBuilder();
                            }
                            this.crypto_ = (FaceActionStatsProto) input.readMessage(FaceActionStatsProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.crypto_);
                                this.crypto_ = (FaceActionStatsProto) subBuilder2.buildPartial();
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
                    synchronized (FaceUserStatsProto.class) {
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

    public static FaceUserStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FaceUserStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
