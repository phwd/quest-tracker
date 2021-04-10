package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$PinStatusResponse extends GeneratedMessageLite<Protocol$PinStatusResponse, Builder> implements Protocol$PinStatusResponseOrBuilder {
    private static final Protocol$PinStatusResponse DEFAULT_INSTANCE = new Protocol$PinStatusResponse();
    private static volatile Parser<Protocol$PinStatusResponse> PARSER;
    private int bitField0_;
    private boolean deviceIsLocked_ = false;
    private int method_ = 0;
    private boolean pinIsSet_ = false;

    private Protocol$PinStatusResponse() {
    }

    public boolean hasPinIsSet() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPinIsSet(boolean z) {
        this.bitField0_ |= 1;
        this.pinIsSet_ = z;
    }

    public boolean hasDeviceIsLocked() {
        return (this.bitField0_ & 2) == 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceIsLocked(boolean z) {
        this.bitField0_ |= 2;
        this.deviceIsLocked_ = z;
    }

    public boolean hasMethod() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMethod(Protocol$CredentialLockMethod protocol$CredentialLockMethod) {
        if (protocol$CredentialLockMethod != null) {
            this.bitField0_ |= 4;
            this.method_ = protocol$CredentialLockMethod.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(1, this.pinIsSet_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBool(2, this.deviceIsLocked_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(3, this.method_);
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
            i2 = 0 + CodedOutputStream.computeBoolSize(1, this.pinIsSet_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBoolSize(2, this.deviceIsLocked_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeEnumSize(3, this.method_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$PinStatusResponse, Builder> implements Protocol$PinStatusResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$PinStatusResponse.DEFAULT_INSTANCE);
        }

        public Builder setPinIsSet(boolean z) {
            copyOnWrite();
            ((Protocol$PinStatusResponse) this.instance).setPinIsSet(z);
            return this;
        }

        public Builder setDeviceIsLocked(boolean z) {
            copyOnWrite();
            ((Protocol$PinStatusResponse) this.instance).setDeviceIsLocked(z);
            return this;
        }

        public Builder setMethod(Protocol$CredentialLockMethod protocol$CredentialLockMethod) {
            copyOnWrite();
            ((Protocol$PinStatusResponse) this.instance).setMethod(protocol$CredentialLockMethod);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$PinStatusResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$PinStatusResponse protocol$PinStatusResponse = (Protocol$PinStatusResponse) obj2;
                this.pinIsSet_ = visitor.visitBoolean(hasPinIsSet(), this.pinIsSet_, protocol$PinStatusResponse.hasPinIsSet(), protocol$PinStatusResponse.pinIsSet_);
                this.deviceIsLocked_ = visitor.visitBoolean(hasDeviceIsLocked(), this.deviceIsLocked_, protocol$PinStatusResponse.hasDeviceIsLocked(), protocol$PinStatusResponse.deviceIsLocked_);
                this.method_ = visitor.visitInt(hasMethod(), this.method_, protocol$PinStatusResponse.hasMethod(), protocol$PinStatusResponse.method_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$PinStatusResponse.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.bitField0_ |= 1;
                                this.pinIsSet_ = codedInputStream.readBool();
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.deviceIsLocked_ = codedInputStream.readBool();
                            } else if (readTag == 24) {
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$CredentialLockMethod.forNumber(readEnum) == null) {
                                    super.mergeVarintField(3, readEnum);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.method_ = readEnum;
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
                    synchronized (Protocol$PinStatusResponse.class) {
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
