package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$HmdExternalBatteryStatusResponse extends GeneratedMessageLite<Protocol$HmdExternalBatteryStatusResponse, Builder> implements Protocol$HmdExternalBatteryStatusResponseOrBuilder {
    private static final Protocol$HmdExternalBatteryStatusResponse DEFAULT_INSTANCE = new Protocol$HmdExternalBatteryStatusResponse();
    private static volatile Parser<Protocol$HmdExternalBatteryStatusResponse> PARSER;
    private long batteryLevel_ = 0;
    private int bitField0_;
    private int chargingStatus_ = 0;
    private long combinedBatteryLevel_ = 0;
    private boolean isChargerPlugged_ = false;
    private byte memoizedIsInitialized = -1;

    private Protocol$HmdExternalBatteryStatusResponse() {
    }

    public enum ChargingStatus implements Internal.EnumLite {
        UNKNOWN(0),
        CHARGING(1),
        NOT_CHARGING(2);
        
        private static final Internal.EnumLiteMap<ChargingStatus> internalValueMap = new Internal.EnumLiteMap<ChargingStatus>() {
            /* class com.oculus.companion.server.Protocol$HmdExternalBatteryStatusResponse.ChargingStatus.AnonymousClass1 */
        };
        private final int value;

        public final int getNumber() {
            return this.value;
        }

        public static ChargingStatus forNumber(int i) {
            if (i == 0) {
                return UNKNOWN;
            }
            if (i == 1) {
                return CHARGING;
            }
            if (i != 2) {
                return null;
            }
            return NOT_CHARGING;
        }

        private ChargingStatus(int i) {
            this.value = i;
        }
    }

    public boolean hasIsChargerPlugged() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsChargerPlugged(boolean z) {
        this.bitField0_ |= 1;
        this.isChargerPlugged_ = z;
    }

    public boolean hasBatteryLevel() {
        return (this.bitField0_ & 2) == 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryLevel(long j) {
        this.bitField0_ |= 2;
        this.batteryLevel_ = j;
    }

    public boolean hasCombinedBatteryLevel() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCombinedBatteryLevel(long j) {
        this.bitField0_ |= 4;
        this.combinedBatteryLevel_ = j;
    }

    public boolean hasChargingStatus() {
        return (this.bitField0_ & 8) == 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChargingStatus(ChargingStatus chargingStatus) {
        if (chargingStatus != null) {
            this.bitField0_ |= 8;
            this.chargingStatus_ = chargingStatus.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(1, this.isChargerPlugged_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt64(2, this.batteryLevel_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt64(3, this.combinedBatteryLevel_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeEnum(4, this.chargingStatus_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeBoolSize(1, this.isChargerPlugged_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeInt64Size(2, this.batteryLevel_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeInt64Size(3, this.combinedBatteryLevel_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeEnumSize(4, this.chargingStatus_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$HmdExternalBatteryStatusResponse, Builder> implements Protocol$HmdExternalBatteryStatusResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$HmdExternalBatteryStatusResponse.DEFAULT_INSTANCE);
        }

        public Builder setIsChargerPlugged(boolean z) {
            copyOnWrite();
            ((Protocol$HmdExternalBatteryStatusResponse) this.instance).setIsChargerPlugged(z);
            return this;
        }

        public Builder setBatteryLevel(long j) {
            copyOnWrite();
            ((Protocol$HmdExternalBatteryStatusResponse) this.instance).setBatteryLevel(j);
            return this;
        }

        public Builder setCombinedBatteryLevel(long j) {
            copyOnWrite();
            ((Protocol$HmdExternalBatteryStatusResponse) this.instance).setCombinedBatteryLevel(j);
            return this;
        }

        public Builder setChargingStatus(ChargingStatus chargingStatus) {
            copyOnWrite();
            ((Protocol$HmdExternalBatteryStatusResponse) this.instance).setChargingStatus(chargingStatus);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$HmdExternalBatteryStatusResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasIsChargerPlugged()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasBatteryLevel()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasCombinedBatteryLevel()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasChargingStatus()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 1;
                    }
                    return DEFAULT_INSTANCE;
                }
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$HmdExternalBatteryStatusResponse protocol$HmdExternalBatteryStatusResponse = (Protocol$HmdExternalBatteryStatusResponse) obj2;
                this.isChargerPlugged_ = visitor.visitBoolean(hasIsChargerPlugged(), this.isChargerPlugged_, protocol$HmdExternalBatteryStatusResponse.hasIsChargerPlugged(), protocol$HmdExternalBatteryStatusResponse.isChargerPlugged_);
                this.batteryLevel_ = visitor.visitLong(hasBatteryLevel(), this.batteryLevel_, protocol$HmdExternalBatteryStatusResponse.hasBatteryLevel(), protocol$HmdExternalBatteryStatusResponse.batteryLevel_);
                this.combinedBatteryLevel_ = visitor.visitLong(hasCombinedBatteryLevel(), this.combinedBatteryLevel_, protocol$HmdExternalBatteryStatusResponse.hasCombinedBatteryLevel(), protocol$HmdExternalBatteryStatusResponse.combinedBatteryLevel_);
                this.chargingStatus_ = visitor.visitInt(hasChargingStatus(), this.chargingStatus_, protocol$HmdExternalBatteryStatusResponse.hasChargingStatus(), protocol$HmdExternalBatteryStatusResponse.chargingStatus_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$HmdExternalBatteryStatusResponse.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.bitField0_ |= 1;
                                this.isChargerPlugged_ = codedInputStream.readBool();
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.batteryLevel_ = codedInputStream.readInt64();
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.combinedBatteryLevel_ = codedInputStream.readInt64();
                            } else if (readTag == 32) {
                                int readEnum = codedInputStream.readEnum();
                                if (ChargingStatus.forNumber(readEnum) == null) {
                                    super.mergeVarintField(4, readEnum);
                                } else {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.chargingStatus_ = readEnum;
                                }
                            } else if (!parseUnknownField(readTag, codedInputStream)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (PARSER == null) {
                    synchronized (Protocol$HmdExternalBatteryStatusResponse.class) {
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
}
