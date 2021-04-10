package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$Controller extends GeneratedMessageLite<Protocol$Controller, Builder> implements Protocol$ControllerOrBuilder {
    private static final Protocol$Controller DEFAULT_INSTANCE = new Protocol$Controller();
    private static volatile Parser<Protocol$Controller> PARSER;
    private int batteryLevel_ = 0;
    private int bitField0_;
    private String displayName_ = "";
    private String firmwareVersion_ = "";
    private String id_ = "";
    private byte memoizedIsInitialized = -1;
    private int rssi_ = 0;
    private int state_ = 1;
    private int type_ = 0;

    private Protocol$Controller() {
    }

    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getId() {
        return this.id_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(String str) {
        if (str != null) {
            this.bitField0_ |= 1;
            this.id_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasDisplayName() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getDisplayName() {
        return this.displayName_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayName(String str) {
        if (str != null) {
            this.bitField0_ |= 2;
            this.displayName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasState() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setState(Protocol$ControllerState protocol$ControllerState) {
        if (protocol$ControllerState != null) {
            this.bitField0_ |= 4;
            this.state_ = protocol$ControllerState.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasBatteryLevel() {
        return (this.bitField0_ & 8) == 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryLevel(int i) {
        this.bitField0_ |= 8;
        this.batteryLevel_ = i;
    }

    public boolean hasType() {
        return (this.bitField0_ & 16) == 16;
    }

    public Protocol$ControllerType getType() {
        Protocol$ControllerType forNumber = Protocol$ControllerType.forNumber(this.type_);
        return forNumber == null ? Protocol$ControllerType.PRIMARY : forNumber;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(Protocol$ControllerType protocol$ControllerType) {
        if (protocol$ControllerType != null) {
            this.bitField0_ |= 16;
            this.type_ = protocol$ControllerType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasRssi() {
        return (this.bitField0_ & 32) == 32;
    }

    public boolean hasFirmwareVersion() {
        return (this.bitField0_ & 64) == 64;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFirmwareVersion(String str) {
        if (str != null) {
            this.bitField0_ |= 64;
            this.firmwareVersion_ = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getDisplayName());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(3, this.state_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeUInt32(4, this.batteryLevel_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeEnum(5, this.type_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeInt32(7, this.rssi_);
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeString(8, getFirmwareVersion());
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
            i2 = 0 + CodedOutputStream.computeStringSize(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getDisplayName());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeEnumSize(3, this.state_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeUInt32Size(4, this.batteryLevel_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeEnumSize(5, this.type_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeInt32Size(7, this.rssi_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeStringSize(8, getFirmwareVersion());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$Controller, Builder> implements Protocol$ControllerOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$Controller.DEFAULT_INSTANCE);
        }

        public Builder setId(String str) {
            copyOnWrite();
            ((Protocol$Controller) this.instance).setId(str);
            return this;
        }

        public Builder setDisplayName(String str) {
            copyOnWrite();
            ((Protocol$Controller) this.instance).setDisplayName(str);
            return this;
        }

        public Builder setState(Protocol$ControllerState protocol$ControllerState) {
            copyOnWrite();
            ((Protocol$Controller) this.instance).setState(protocol$ControllerState);
            return this;
        }

        public Builder setBatteryLevel(int i) {
            copyOnWrite();
            ((Protocol$Controller) this.instance).setBatteryLevel(i);
            return this;
        }

        public Builder setType(Protocol$ControllerType protocol$ControllerType) {
            copyOnWrite();
            ((Protocol$Controller) this.instance).setType(protocol$ControllerType);
            return this;
        }

        public Builder setFirmwareVersion(String str) {
            copyOnWrite();
            ((Protocol$Controller) this.instance).setFirmwareVersion(str);
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
                return new Protocol$Controller();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasId()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                }
                if (booleanValue) {
                    this.memoizedIsInitialized = 1;
                }
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$Controller protocol$Controller = (Protocol$Controller) obj2;
                this.id_ = visitor.visitString(hasId(), this.id_, protocol$Controller.hasId(), protocol$Controller.id_);
                this.displayName_ = visitor.visitString(hasDisplayName(), this.displayName_, protocol$Controller.hasDisplayName(), protocol$Controller.displayName_);
                this.state_ = visitor.visitInt(hasState(), this.state_, protocol$Controller.hasState(), protocol$Controller.state_);
                this.batteryLevel_ = visitor.visitInt(hasBatteryLevel(), this.batteryLevel_, protocol$Controller.hasBatteryLevel(), protocol$Controller.batteryLevel_);
                this.type_ = visitor.visitInt(hasType(), this.type_, protocol$Controller.hasType(), protocol$Controller.type_);
                this.rssi_ = visitor.visitInt(hasRssi(), this.rssi_, protocol$Controller.hasRssi(), protocol$Controller.rssi_);
                this.firmwareVersion_ = visitor.visitString(hasFirmwareVersion(), this.firmwareVersion_, protocol$Controller.hasFirmwareVersion(), protocol$Controller.firmwareVersion_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$Controller.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 1;
                                this.id_ = readString;
                            } else if (readTag == 18) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.displayName_ = readString2;
                            } else if (readTag == 24) {
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$ControllerState.forNumber(readEnum) == null) {
                                    super.mergeVarintField(3, readEnum);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.state_ = readEnum;
                                }
                            } else if (readTag == 32) {
                                this.bitField0_ |= 8;
                                this.batteryLevel_ = codedInputStream.readUInt32();
                            } else if (readTag == 40) {
                                int readEnum2 = codedInputStream.readEnum();
                                if (Protocol$ControllerType.forNumber(readEnum2) == null) {
                                    super.mergeVarintField(5, readEnum2);
                                } else {
                                    this.bitField0_ |= 16;
                                    this.type_ = readEnum2;
                                }
                            } else if (readTag == 56) {
                                this.bitField0_ |= 32;
                                this.rssi_ = codedInputStream.readInt32();
                            } else if (readTag == 66) {
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 64;
                                this.firmwareVersion_ = readString3;
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
                    synchronized (Protocol$Controller.class) {
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

    public static Protocol$Controller getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Protocol$Controller> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
