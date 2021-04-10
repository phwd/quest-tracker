package android.os;

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

public final class ControllerActivityProto extends GeneratedMessageLite<ControllerActivityProto, Builder> implements ControllerActivityProtoOrBuilder {
    private static final ControllerActivityProto DEFAULT_INSTANCE = new ControllerActivityProto();
    public static final int IDLE_DURATION_MS_FIELD_NUMBER = 1;
    public static final int MONITORED_RAIL_CHARGE_MAH_FIELD_NUMBER = 5;
    private static volatile Parser<ControllerActivityProto> PARSER = null;
    public static final int POWER_MAH_FIELD_NUMBER = 3;
    public static final int RX_DURATION_MS_FIELD_NUMBER = 2;
    public static final int TX_FIELD_NUMBER = 4;
    private int bitField0_;
    private long idleDurationMs_ = 0;
    private double monitoredRailChargeMah_ = 0.0d;
    private long powerMah_ = 0;
    private long rxDurationMs_ = 0;
    private Internal.ProtobufList<TxLevel> tx_ = emptyProtobufList();

    public interface TxLevelOrBuilder extends MessageLiteOrBuilder {
        long getDurationMs();

        int getLevel();

        boolean hasDurationMs();

        boolean hasLevel();
    }

    private ControllerActivityProto() {
    }

    public static final class TxLevel extends GeneratedMessageLite<TxLevel, Builder> implements TxLevelOrBuilder {
        private static final TxLevel DEFAULT_INSTANCE = new TxLevel();
        public static final int DURATION_MS_FIELD_NUMBER = 2;
        public static final int LEVEL_FIELD_NUMBER = 1;
        private static volatile Parser<TxLevel> PARSER;
        private int bitField0_;
        private long durationMs_ = 0;
        private int level_ = 0;

        private TxLevel() {
        }

        @Override // android.os.ControllerActivityProto.TxLevelOrBuilder
        public boolean hasLevel() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.ControllerActivityProto.TxLevelOrBuilder
        public int getLevel() {
            return this.level_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLevel(int value) {
            this.bitField0_ |= 1;
            this.level_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLevel() {
            this.bitField0_ &= -2;
            this.level_ = 0;
        }

        @Override // android.os.ControllerActivityProto.TxLevelOrBuilder
        public boolean hasDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.ControllerActivityProto.TxLevelOrBuilder
        public long getDurationMs() {
            return this.durationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMs(long value) {
            this.bitField0_ |= 2;
            this.durationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMs() {
            this.bitField0_ &= -3;
            this.durationMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.level_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.durationMs_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.level_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.durationMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static TxLevel parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (TxLevel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TxLevel parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TxLevel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TxLevel parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (TxLevel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TxLevel parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TxLevel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TxLevel parseFrom(InputStream input) throws IOException {
            return (TxLevel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TxLevel parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TxLevel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TxLevel parseDelimitedFrom(InputStream input) throws IOException {
            return (TxLevel) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static TxLevel parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TxLevel) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TxLevel parseFrom(CodedInputStream input) throws IOException {
            return (TxLevel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TxLevel parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TxLevel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TxLevel prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TxLevel, Builder> implements TxLevelOrBuilder {
            private Builder() {
                super(TxLevel.DEFAULT_INSTANCE);
            }

            @Override // android.os.ControllerActivityProto.TxLevelOrBuilder
            public boolean hasLevel() {
                return ((TxLevel) this.instance).hasLevel();
            }

            @Override // android.os.ControllerActivityProto.TxLevelOrBuilder
            public int getLevel() {
                return ((TxLevel) this.instance).getLevel();
            }

            public Builder setLevel(int value) {
                copyOnWrite();
                ((TxLevel) this.instance).setLevel(value);
                return this;
            }

            public Builder clearLevel() {
                copyOnWrite();
                ((TxLevel) this.instance).clearLevel();
                return this;
            }

            @Override // android.os.ControllerActivityProto.TxLevelOrBuilder
            public boolean hasDurationMs() {
                return ((TxLevel) this.instance).hasDurationMs();
            }

            @Override // android.os.ControllerActivityProto.TxLevelOrBuilder
            public long getDurationMs() {
                return ((TxLevel) this.instance).getDurationMs();
            }

            public Builder setDurationMs(long value) {
                copyOnWrite();
                ((TxLevel) this.instance).setDurationMs(value);
                return this;
            }

            public Builder clearDurationMs() {
                copyOnWrite();
                ((TxLevel) this.instance).clearDurationMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new TxLevel();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    TxLevel other = (TxLevel) arg1;
                    this.level_ = visitor.visitInt(hasLevel(), this.level_, other.hasLevel(), other.level_);
                    this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
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
                                this.level_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.durationMs_ = input.readInt64();
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
                        synchronized (TxLevel.class) {
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

        public static TxLevel getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TxLevel> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public boolean hasIdleDurationMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public long getIdleDurationMs() {
        return this.idleDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdleDurationMs(long value) {
        this.bitField0_ |= 1;
        this.idleDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIdleDurationMs() {
        this.bitField0_ &= -2;
        this.idleDurationMs_ = 0;
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public boolean hasRxDurationMs() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public long getRxDurationMs() {
        return this.rxDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRxDurationMs(long value) {
        this.bitField0_ |= 2;
        this.rxDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRxDurationMs() {
        this.bitField0_ &= -3;
        this.rxDurationMs_ = 0;
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public boolean hasPowerMah() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public long getPowerMah() {
        return this.powerMah_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerMah(long value) {
        this.bitField0_ |= 4;
        this.powerMah_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPowerMah() {
        this.bitField0_ &= -5;
        this.powerMah_ = 0;
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public List<TxLevel> getTxList() {
        return this.tx_;
    }

    public List<? extends TxLevelOrBuilder> getTxOrBuilderList() {
        return this.tx_;
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public int getTxCount() {
        return this.tx_.size();
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public TxLevel getTx(int index) {
        return this.tx_.get(index);
    }

    public TxLevelOrBuilder getTxOrBuilder(int index) {
        return this.tx_.get(index);
    }

    private void ensureTxIsMutable() {
        if (!this.tx_.isModifiable()) {
            this.tx_ = GeneratedMessageLite.mutableCopy(this.tx_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTx(int index, TxLevel value) {
        if (value != null) {
            ensureTxIsMutable();
            this.tx_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTx(int index, TxLevel.Builder builderForValue) {
        ensureTxIsMutable();
        this.tx_.set(index, (TxLevel) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTx(TxLevel value) {
        if (value != null) {
            ensureTxIsMutable();
            this.tx_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTx(int index, TxLevel value) {
        if (value != null) {
            ensureTxIsMutable();
            this.tx_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTx(TxLevel.Builder builderForValue) {
        ensureTxIsMutable();
        this.tx_.add((TxLevel) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTx(int index, TxLevel.Builder builderForValue) {
        ensureTxIsMutable();
        this.tx_.add(index, (TxLevel) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTx(Iterable<? extends TxLevel> values) {
        ensureTxIsMutable();
        AbstractMessageLite.addAll(values, this.tx_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTx() {
        this.tx_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTx(int index) {
        ensureTxIsMutable();
        this.tx_.remove(index);
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public boolean hasMonitoredRailChargeMah() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.os.ControllerActivityProtoOrBuilder
    public double getMonitoredRailChargeMah() {
        return this.monitoredRailChargeMah_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMonitoredRailChargeMah(double value) {
        this.bitField0_ |= 8;
        this.monitoredRailChargeMah_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMonitoredRailChargeMah() {
        this.bitField0_ &= -9;
        this.monitoredRailChargeMah_ = 0.0d;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.idleDurationMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.rxDurationMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.powerMah_);
        }
        for (int i = 0; i < this.tx_.size(); i++) {
            output.writeMessage(4, this.tx_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeDouble(5, this.monitoredRailChargeMah_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.idleDurationMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.rxDurationMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.powerMah_);
        }
        for (int i = 0; i < this.tx_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.tx_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeDoubleSize(5, this.monitoredRailChargeMah_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ControllerActivityProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ControllerActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ControllerActivityProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ControllerActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ControllerActivityProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ControllerActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ControllerActivityProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ControllerActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ControllerActivityProto parseFrom(InputStream input) throws IOException {
        return (ControllerActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ControllerActivityProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ControllerActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ControllerActivityProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ControllerActivityProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ControllerActivityProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ControllerActivityProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ControllerActivityProto parseFrom(CodedInputStream input) throws IOException {
        return (ControllerActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ControllerActivityProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ControllerActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ControllerActivityProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ControllerActivityProto, Builder> implements ControllerActivityProtoOrBuilder {
        private Builder() {
            super(ControllerActivityProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public boolean hasIdleDurationMs() {
            return ((ControllerActivityProto) this.instance).hasIdleDurationMs();
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public long getIdleDurationMs() {
            return ((ControllerActivityProto) this.instance).getIdleDurationMs();
        }

        public Builder setIdleDurationMs(long value) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).setIdleDurationMs(value);
            return this;
        }

        public Builder clearIdleDurationMs() {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).clearIdleDurationMs();
            return this;
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public boolean hasRxDurationMs() {
            return ((ControllerActivityProto) this.instance).hasRxDurationMs();
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public long getRxDurationMs() {
            return ((ControllerActivityProto) this.instance).getRxDurationMs();
        }

        public Builder setRxDurationMs(long value) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).setRxDurationMs(value);
            return this;
        }

        public Builder clearRxDurationMs() {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).clearRxDurationMs();
            return this;
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public boolean hasPowerMah() {
            return ((ControllerActivityProto) this.instance).hasPowerMah();
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public long getPowerMah() {
            return ((ControllerActivityProto) this.instance).getPowerMah();
        }

        public Builder setPowerMah(long value) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).setPowerMah(value);
            return this;
        }

        public Builder clearPowerMah() {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).clearPowerMah();
            return this;
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public List<TxLevel> getTxList() {
            return Collections.unmodifiableList(((ControllerActivityProto) this.instance).getTxList());
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public int getTxCount() {
            return ((ControllerActivityProto) this.instance).getTxCount();
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public TxLevel getTx(int index) {
            return ((ControllerActivityProto) this.instance).getTx(index);
        }

        public Builder setTx(int index, TxLevel value) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).setTx((ControllerActivityProto) index, (int) value);
            return this;
        }

        public Builder setTx(int index, TxLevel.Builder builderForValue) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).setTx((ControllerActivityProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTx(TxLevel value) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).addTx((ControllerActivityProto) value);
            return this;
        }

        public Builder addTx(int index, TxLevel value) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).addTx((ControllerActivityProto) index, (int) value);
            return this;
        }

        public Builder addTx(TxLevel.Builder builderForValue) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).addTx((ControllerActivityProto) builderForValue);
            return this;
        }

        public Builder addTx(int index, TxLevel.Builder builderForValue) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).addTx((ControllerActivityProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTx(Iterable<? extends TxLevel> values) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).addAllTx(values);
            return this;
        }

        public Builder clearTx() {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).clearTx();
            return this;
        }

        public Builder removeTx(int index) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).removeTx(index);
            return this;
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public boolean hasMonitoredRailChargeMah() {
            return ((ControllerActivityProto) this.instance).hasMonitoredRailChargeMah();
        }

        @Override // android.os.ControllerActivityProtoOrBuilder
        public double getMonitoredRailChargeMah() {
            return ((ControllerActivityProto) this.instance).getMonitoredRailChargeMah();
        }

        public Builder setMonitoredRailChargeMah(double value) {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).setMonitoredRailChargeMah(value);
            return this;
        }

        public Builder clearMonitoredRailChargeMah() {
            copyOnWrite();
            ((ControllerActivityProto) this.instance).clearMonitoredRailChargeMah();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ControllerActivityProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.tx_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ControllerActivityProto other = (ControllerActivityProto) arg1;
                this.idleDurationMs_ = visitor.visitLong(hasIdleDurationMs(), this.idleDurationMs_, other.hasIdleDurationMs(), other.idleDurationMs_);
                this.rxDurationMs_ = visitor.visitLong(hasRxDurationMs(), this.rxDurationMs_, other.hasRxDurationMs(), other.rxDurationMs_);
                this.powerMah_ = visitor.visitLong(hasPowerMah(), this.powerMah_, other.hasPowerMah(), other.powerMah_);
                this.tx_ = visitor.visitList(this.tx_, other.tx_);
                this.monitoredRailChargeMah_ = visitor.visitDouble(hasMonitoredRailChargeMah(), this.monitoredRailChargeMah_, other.hasMonitoredRailChargeMah(), other.monitoredRailChargeMah_);
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
                            this.idleDurationMs_ = input.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.rxDurationMs_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.powerMah_ = input.readInt64();
                        } else if (tag == 34) {
                            if (!this.tx_.isModifiable()) {
                                this.tx_ = GeneratedMessageLite.mutableCopy(this.tx_);
                            }
                            this.tx_.add((TxLevel) input.readMessage(TxLevel.parser(), extensionRegistry));
                        } else if (tag == 41) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.monitoredRailChargeMah_ = input.readDouble();
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
                    synchronized (ControllerActivityProto.class) {
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

    public static ControllerActivityProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ControllerActivityProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
