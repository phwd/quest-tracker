package com.android.server.power;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WirelessChargerDetectorProto extends GeneratedMessageLite<WirelessChargerDetectorProto, Builder> implements WirelessChargerDetectorProtoOrBuilder {
    private static final WirelessChargerDetectorProto DEFAULT_INSTANCE = new WirelessChargerDetectorProto();
    public static final int DETECTION_START_TIME_MS_FIELD_NUMBER = 5;
    public static final int FIRST_SAMPLE_FIELD_NUMBER = 9;
    public static final int IS_AT_REST_FIELD_NUMBER = 2;
    public static final int IS_DETECTION_IN_PROGRESS_FIELD_NUMBER = 4;
    public static final int IS_MUST_UPDATE_REST_POSITION_FIELD_NUMBER = 6;
    public static final int IS_POWERED_WIRELESSLY_FIELD_NUMBER = 1;
    public static final int LAST_SAMPLE_FIELD_NUMBER = 10;
    public static final int MOVING_SAMPLES_FIELD_NUMBER = 8;
    private static volatile Parser<WirelessChargerDetectorProto> PARSER = null;
    public static final int REST_FIELD_NUMBER = 3;
    public static final int TOTAL_SAMPLES_FIELD_NUMBER = 7;
    private int bitField0_;
    private long detectionStartTimeMs_ = 0;
    private VectorProto firstSample_;
    private boolean isAtRest_ = false;
    private boolean isDetectionInProgress_ = false;
    private boolean isMustUpdateRestPosition_ = false;
    private boolean isPoweredWirelessly_ = false;
    private VectorProto lastSample_;
    private int movingSamples_ = 0;
    private VectorProto rest_;
    private int totalSamples_ = 0;

    public interface VectorProtoOrBuilder extends MessageLiteOrBuilder {
        float getX();

        float getY();

        float getZ();

        boolean hasX();

        boolean hasY();

        boolean hasZ();
    }

    private WirelessChargerDetectorProto() {
    }

    public static final class VectorProto extends GeneratedMessageLite<VectorProto, Builder> implements VectorProtoOrBuilder {
        private static final VectorProto DEFAULT_INSTANCE = new VectorProto();
        private static volatile Parser<VectorProto> PARSER = null;
        public static final int X_FIELD_NUMBER = 1;
        public static final int Y_FIELD_NUMBER = 2;
        public static final int Z_FIELD_NUMBER = 3;
        private int bitField0_;
        private float x_ = 0.0f;
        private float y_ = 0.0f;
        private float z_ = 0.0f;

        private VectorProto() {
        }

        @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
        public boolean hasX() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
        public float getX() {
            return this.x_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setX(float value) {
            this.bitField0_ |= 1;
            this.x_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearX() {
            this.bitField0_ &= -2;
            this.x_ = 0.0f;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
        public boolean hasY() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
        public float getY() {
            return this.y_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setY(float value) {
            this.bitField0_ |= 2;
            this.y_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearY() {
            this.bitField0_ &= -3;
            this.y_ = 0.0f;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
        public boolean hasZ() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
        public float getZ() {
            return this.z_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setZ(float value) {
            this.bitField0_ |= 4;
            this.z_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearZ() {
            this.bitField0_ &= -5;
            this.z_ = 0.0f;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeFloat(1, this.x_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeFloat(2, this.y_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeFloat(3, this.z_);
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
                size2 = 0 + CodedOutputStream.computeFloatSize(1, this.x_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeFloatSize(2, this.y_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeFloatSize(3, this.z_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static VectorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (VectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static VectorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (VectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static VectorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (VectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static VectorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (VectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static VectorProto parseFrom(InputStream input) throws IOException {
            return (VectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static VectorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (VectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static VectorProto parseDelimitedFrom(InputStream input) throws IOException {
            return (VectorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static VectorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (VectorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static VectorProto parseFrom(CodedInputStream input) throws IOException {
            return (VectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static VectorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (VectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(VectorProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<VectorProto, Builder> implements VectorProtoOrBuilder {
            private Builder() {
                super(VectorProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
            public boolean hasX() {
                return ((VectorProto) this.instance).hasX();
            }

            @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
            public float getX() {
                return ((VectorProto) this.instance).getX();
            }

            public Builder setX(float value) {
                copyOnWrite();
                ((VectorProto) this.instance).setX(value);
                return this;
            }

            public Builder clearX() {
                copyOnWrite();
                ((VectorProto) this.instance).clearX();
                return this;
            }

            @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
            public boolean hasY() {
                return ((VectorProto) this.instance).hasY();
            }

            @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
            public float getY() {
                return ((VectorProto) this.instance).getY();
            }

            public Builder setY(float value) {
                copyOnWrite();
                ((VectorProto) this.instance).setY(value);
                return this;
            }

            public Builder clearY() {
                copyOnWrite();
                ((VectorProto) this.instance).clearY();
                return this;
            }

            @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
            public boolean hasZ() {
                return ((VectorProto) this.instance).hasZ();
            }

            @Override // com.android.server.power.WirelessChargerDetectorProto.VectorProtoOrBuilder
            public float getZ() {
                return ((VectorProto) this.instance).getZ();
            }

            public Builder setZ(float value) {
                copyOnWrite();
                ((VectorProto) this.instance).setZ(value);
                return this;
            }

            public Builder clearZ() {
                copyOnWrite();
                ((VectorProto) this.instance).clearZ();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new VectorProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    VectorProto other = (VectorProto) arg1;
                    this.x_ = visitor.visitFloat(hasX(), this.x_, other.hasX(), other.x_);
                    this.y_ = visitor.visitFloat(hasY(), this.y_, other.hasY(), other.y_);
                    this.z_ = visitor.visitFloat(hasZ(), this.z_, other.hasZ(), other.z_);
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
                            } else if (tag == 13) {
                                this.bitField0_ |= 1;
                                this.x_ = input.readFloat();
                            } else if (tag == 21) {
                                this.bitField0_ |= 2;
                                this.y_ = input.readFloat();
                            } else if (tag == 29) {
                                this.bitField0_ |= 4;
                                this.z_ = input.readFloat();
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
                        synchronized (VectorProto.class) {
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

        public static VectorProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<VectorProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasIsPoweredWirelessly() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean getIsPoweredWirelessly() {
        return this.isPoweredWirelessly_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsPoweredWirelessly(boolean value) {
        this.bitField0_ |= 1;
        this.isPoweredWirelessly_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsPoweredWirelessly() {
        this.bitField0_ &= -2;
        this.isPoweredWirelessly_ = false;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasIsAtRest() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean getIsAtRest() {
        return this.isAtRest_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsAtRest(boolean value) {
        this.bitField0_ |= 2;
        this.isAtRest_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsAtRest() {
        this.bitField0_ &= -3;
        this.isAtRest_ = false;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasRest() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public VectorProto getRest() {
        VectorProto vectorProto = this.rest_;
        return vectorProto == null ? VectorProto.getDefaultInstance() : vectorProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRest(VectorProto value) {
        if (value != null) {
            this.rest_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRest(VectorProto.Builder builderForValue) {
        this.rest_ = (VectorProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRest(VectorProto value) {
        VectorProto vectorProto = this.rest_;
        if (vectorProto == null || vectorProto == VectorProto.getDefaultInstance()) {
            this.rest_ = value;
        } else {
            this.rest_ = (VectorProto) ((VectorProto.Builder) VectorProto.newBuilder(this.rest_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRest() {
        this.rest_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasIsDetectionInProgress() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean getIsDetectionInProgress() {
        return this.isDetectionInProgress_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDetectionInProgress(boolean value) {
        this.bitField0_ |= 8;
        this.isDetectionInProgress_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDetectionInProgress() {
        this.bitField0_ &= -9;
        this.isDetectionInProgress_ = false;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasDetectionStartTimeMs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public long getDetectionStartTimeMs() {
        return this.detectionStartTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDetectionStartTimeMs(long value) {
        this.bitField0_ |= 16;
        this.detectionStartTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDetectionStartTimeMs() {
        this.bitField0_ &= -17;
        this.detectionStartTimeMs_ = 0;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasIsMustUpdateRestPosition() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean getIsMustUpdateRestPosition() {
        return this.isMustUpdateRestPosition_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsMustUpdateRestPosition(boolean value) {
        this.bitField0_ |= 32;
        this.isMustUpdateRestPosition_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsMustUpdateRestPosition() {
        this.bitField0_ &= -33;
        this.isMustUpdateRestPosition_ = false;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasTotalSamples() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public int getTotalSamples() {
        return this.totalSamples_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalSamples(int value) {
        this.bitField0_ |= 64;
        this.totalSamples_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalSamples() {
        this.bitField0_ &= -65;
        this.totalSamples_ = 0;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasMovingSamples() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public int getMovingSamples() {
        return this.movingSamples_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMovingSamples(int value) {
        this.bitField0_ |= 128;
        this.movingSamples_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMovingSamples() {
        this.bitField0_ &= -129;
        this.movingSamples_ = 0;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasFirstSample() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public VectorProto getFirstSample() {
        VectorProto vectorProto = this.firstSample_;
        return vectorProto == null ? VectorProto.getDefaultInstance() : vectorProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFirstSample(VectorProto value) {
        if (value != null) {
            this.firstSample_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFirstSample(VectorProto.Builder builderForValue) {
        this.firstSample_ = (VectorProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFirstSample(VectorProto value) {
        VectorProto vectorProto = this.firstSample_;
        if (vectorProto == null || vectorProto == VectorProto.getDefaultInstance()) {
            this.firstSample_ = value;
        } else {
            this.firstSample_ = (VectorProto) ((VectorProto.Builder) VectorProto.newBuilder(this.firstSample_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFirstSample() {
        this.firstSample_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public boolean hasLastSample() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
    public VectorProto getLastSample() {
        VectorProto vectorProto = this.lastSample_;
        return vectorProto == null ? VectorProto.getDefaultInstance() : vectorProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastSample(VectorProto value) {
        if (value != null) {
            this.lastSample_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastSample(VectorProto.Builder builderForValue) {
        this.lastSample_ = (VectorProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLastSample(VectorProto value) {
        VectorProto vectorProto = this.lastSample_;
        if (vectorProto == null || vectorProto == VectorProto.getDefaultInstance()) {
            this.lastSample_ = value;
        } else {
            this.lastSample_ = (VectorProto) ((VectorProto.Builder) VectorProto.newBuilder(this.lastSample_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastSample() {
        this.lastSample_ = null;
        this.bitField0_ &= -513;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.isPoweredWirelessly_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isAtRest_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getRest());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.isDetectionInProgress_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.detectionStartTimeMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.isMustUpdateRestPosition_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.totalSamples_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.movingSamples_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getFirstSample());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(10, getLastSample());
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isPoweredWirelessly_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isAtRest_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getRest());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.isDetectionInProgress_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.detectionStartTimeMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.isMustUpdateRestPosition_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.totalSamples_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.movingSamples_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getFirstSample());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(10, getLastSample());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WirelessChargerDetectorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WirelessChargerDetectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WirelessChargerDetectorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WirelessChargerDetectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WirelessChargerDetectorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WirelessChargerDetectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WirelessChargerDetectorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WirelessChargerDetectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WirelessChargerDetectorProto parseFrom(InputStream input) throws IOException {
        return (WirelessChargerDetectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WirelessChargerDetectorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WirelessChargerDetectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WirelessChargerDetectorProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WirelessChargerDetectorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WirelessChargerDetectorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WirelessChargerDetectorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WirelessChargerDetectorProto parseFrom(CodedInputStream input) throws IOException {
        return (WirelessChargerDetectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WirelessChargerDetectorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WirelessChargerDetectorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WirelessChargerDetectorProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WirelessChargerDetectorProto, Builder> implements WirelessChargerDetectorProtoOrBuilder {
        private Builder() {
            super(WirelessChargerDetectorProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasIsPoweredWirelessly() {
            return ((WirelessChargerDetectorProto) this.instance).hasIsPoweredWirelessly();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean getIsPoweredWirelessly() {
            return ((WirelessChargerDetectorProto) this.instance).getIsPoweredWirelessly();
        }

        public Builder setIsPoweredWirelessly(boolean value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setIsPoweredWirelessly(value);
            return this;
        }

        public Builder clearIsPoweredWirelessly() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearIsPoweredWirelessly();
            return this;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasIsAtRest() {
            return ((WirelessChargerDetectorProto) this.instance).hasIsAtRest();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean getIsAtRest() {
            return ((WirelessChargerDetectorProto) this.instance).getIsAtRest();
        }

        public Builder setIsAtRest(boolean value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setIsAtRest(value);
            return this;
        }

        public Builder clearIsAtRest() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearIsAtRest();
            return this;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasRest() {
            return ((WirelessChargerDetectorProto) this.instance).hasRest();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public VectorProto getRest() {
            return ((WirelessChargerDetectorProto) this.instance).getRest();
        }

        public Builder setRest(VectorProto value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setRest((WirelessChargerDetectorProto) value);
            return this;
        }

        public Builder setRest(VectorProto.Builder builderForValue) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setRest((WirelessChargerDetectorProto) builderForValue);
            return this;
        }

        public Builder mergeRest(VectorProto value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).mergeRest(value);
            return this;
        }

        public Builder clearRest() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearRest();
            return this;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasIsDetectionInProgress() {
            return ((WirelessChargerDetectorProto) this.instance).hasIsDetectionInProgress();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean getIsDetectionInProgress() {
            return ((WirelessChargerDetectorProto) this.instance).getIsDetectionInProgress();
        }

        public Builder setIsDetectionInProgress(boolean value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setIsDetectionInProgress(value);
            return this;
        }

        public Builder clearIsDetectionInProgress() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearIsDetectionInProgress();
            return this;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasDetectionStartTimeMs() {
            return ((WirelessChargerDetectorProto) this.instance).hasDetectionStartTimeMs();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public long getDetectionStartTimeMs() {
            return ((WirelessChargerDetectorProto) this.instance).getDetectionStartTimeMs();
        }

        public Builder setDetectionStartTimeMs(long value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setDetectionStartTimeMs(value);
            return this;
        }

        public Builder clearDetectionStartTimeMs() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearDetectionStartTimeMs();
            return this;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasIsMustUpdateRestPosition() {
            return ((WirelessChargerDetectorProto) this.instance).hasIsMustUpdateRestPosition();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean getIsMustUpdateRestPosition() {
            return ((WirelessChargerDetectorProto) this.instance).getIsMustUpdateRestPosition();
        }

        public Builder setIsMustUpdateRestPosition(boolean value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setIsMustUpdateRestPosition(value);
            return this;
        }

        public Builder clearIsMustUpdateRestPosition() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearIsMustUpdateRestPosition();
            return this;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasTotalSamples() {
            return ((WirelessChargerDetectorProto) this.instance).hasTotalSamples();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public int getTotalSamples() {
            return ((WirelessChargerDetectorProto) this.instance).getTotalSamples();
        }

        public Builder setTotalSamples(int value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setTotalSamples(value);
            return this;
        }

        public Builder clearTotalSamples() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearTotalSamples();
            return this;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasMovingSamples() {
            return ((WirelessChargerDetectorProto) this.instance).hasMovingSamples();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public int getMovingSamples() {
            return ((WirelessChargerDetectorProto) this.instance).getMovingSamples();
        }

        public Builder setMovingSamples(int value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setMovingSamples(value);
            return this;
        }

        public Builder clearMovingSamples() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearMovingSamples();
            return this;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasFirstSample() {
            return ((WirelessChargerDetectorProto) this.instance).hasFirstSample();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public VectorProto getFirstSample() {
            return ((WirelessChargerDetectorProto) this.instance).getFirstSample();
        }

        public Builder setFirstSample(VectorProto value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setFirstSample((WirelessChargerDetectorProto) value);
            return this;
        }

        public Builder setFirstSample(VectorProto.Builder builderForValue) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setFirstSample((WirelessChargerDetectorProto) builderForValue);
            return this;
        }

        public Builder mergeFirstSample(VectorProto value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).mergeFirstSample(value);
            return this;
        }

        public Builder clearFirstSample() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearFirstSample();
            return this;
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public boolean hasLastSample() {
            return ((WirelessChargerDetectorProto) this.instance).hasLastSample();
        }

        @Override // com.android.server.power.WirelessChargerDetectorProtoOrBuilder
        public VectorProto getLastSample() {
            return ((WirelessChargerDetectorProto) this.instance).getLastSample();
        }

        public Builder setLastSample(VectorProto value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setLastSample((WirelessChargerDetectorProto) value);
            return this;
        }

        public Builder setLastSample(VectorProto.Builder builderForValue) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).setLastSample((WirelessChargerDetectorProto) builderForValue);
            return this;
        }

        public Builder mergeLastSample(VectorProto value) {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).mergeLastSample(value);
            return this;
        }

        public Builder clearLastSample() {
            copyOnWrite();
            ((WirelessChargerDetectorProto) this.instance).clearLastSample();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WirelessChargerDetectorProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WirelessChargerDetectorProto other = (WirelessChargerDetectorProto) arg1;
                this.isPoweredWirelessly_ = visitor.visitBoolean(hasIsPoweredWirelessly(), this.isPoweredWirelessly_, other.hasIsPoweredWirelessly(), other.isPoweredWirelessly_);
                this.isAtRest_ = visitor.visitBoolean(hasIsAtRest(), this.isAtRest_, other.hasIsAtRest(), other.isAtRest_);
                this.rest_ = (VectorProto) visitor.visitMessage(this.rest_, other.rest_);
                this.isDetectionInProgress_ = visitor.visitBoolean(hasIsDetectionInProgress(), this.isDetectionInProgress_, other.hasIsDetectionInProgress(), other.isDetectionInProgress_);
                this.detectionStartTimeMs_ = visitor.visitLong(hasDetectionStartTimeMs(), this.detectionStartTimeMs_, other.hasDetectionStartTimeMs(), other.detectionStartTimeMs_);
                this.isMustUpdateRestPosition_ = visitor.visitBoolean(hasIsMustUpdateRestPosition(), this.isMustUpdateRestPosition_, other.hasIsMustUpdateRestPosition(), other.isMustUpdateRestPosition_);
                this.totalSamples_ = visitor.visitInt(hasTotalSamples(), this.totalSamples_, other.hasTotalSamples(), other.totalSamples_);
                this.movingSamples_ = visitor.visitInt(hasMovingSamples(), this.movingSamples_, other.hasMovingSamples(), other.movingSamples_);
                this.firstSample_ = (VectorProto) visitor.visitMessage(this.firstSample_, other.firstSample_);
                this.lastSample_ = (VectorProto) visitor.visitMessage(this.lastSample_, other.lastSample_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.isPoweredWirelessly_ = input.readBool();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.isAtRest_ = input.readBool();
                                break;
                            case 26:
                                VectorProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (VectorProto.Builder) this.rest_.toBuilder();
                                }
                                this.rest_ = (VectorProto) input.readMessage(VectorProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.rest_);
                                    this.rest_ = (VectorProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.isDetectionInProgress_ = input.readBool();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.detectionStartTimeMs_ = input.readInt64();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.isMustUpdateRestPosition_ = input.readBool();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.totalSamples_ = input.readInt32();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.movingSamples_ = input.readInt32();
                                break;
                            case 74:
                                VectorProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder2 = (VectorProto.Builder) this.firstSample_.toBuilder();
                                }
                                this.firstSample_ = (VectorProto) input.readMessage(VectorProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.firstSample_);
                                    this.firstSample_ = (VectorProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 82:
                                VectorProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder3 = (VectorProto.Builder) this.lastSample_.toBuilder();
                                }
                                this.lastSample_ = (VectorProto) input.readMessage(VectorProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.lastSample_);
                                    this.lastSample_ = (VectorProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (WirelessChargerDetectorProto.class) {
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

    public static WirelessChargerDetectorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WirelessChargerDetectorProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
