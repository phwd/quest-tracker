package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$ControllerVerifyConnectableRequest extends GeneratedMessageLite<Protocol$ControllerVerifyConnectableRequest, Builder> implements Protocol$ControllerVerifyConnectableRequestOrBuilder {
    private static final Protocol$ControllerVerifyConnectableRequest DEFAULT_INSTANCE = new Protocol$ControllerVerifyConnectableRequest();
    private static volatile Parser<Protocol$ControllerVerifyConnectableRequest> PARSER;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int recentConnectionTimeLimitS_ = 0;
    private int timeoutMs_ = 0;
    private int type_ = 0;

    private Protocol$ControllerVerifyConnectableRequest() {
    }

    public boolean hasType() {
        return (this.bitField0_ & 1) == 1;
    }

    public Protocol$ControllerType getType() {
        Protocol$ControllerType forNumber = Protocol$ControllerType.forNumber(this.type_);
        return forNumber == null ? Protocol$ControllerType.PRIMARY : forNumber;
    }

    public boolean hasTimeoutMs() {
        return (this.bitField0_ & 2) == 2;
    }

    public int getTimeoutMs() {
        return this.timeoutMs_;
    }

    public boolean hasRecentConnectionTimeLimitS() {
        return (this.bitField0_ & 4) == 4;
    }

    public int getRecentConnectionTimeLimitS() {
        return this.recentConnectionTimeLimitS_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeEnum(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.timeoutMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt32(3, this.recentConnectionTimeLimitS_);
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
            i2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeInt32Size(2, this.timeoutMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeInt32Size(3, this.recentConnectionTimeLimitS_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$ControllerVerifyConnectableRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$ControllerVerifyConnectableRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$ControllerVerifyConnectableRequest, Builder> implements Protocol$ControllerVerifyConnectableRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$ControllerVerifyConnectableRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$ControllerVerifyConnectableRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasType()) {
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
                Protocol$ControllerVerifyConnectableRequest protocol$ControllerVerifyConnectableRequest = (Protocol$ControllerVerifyConnectableRequest) obj2;
                this.type_ = visitor.visitInt(hasType(), this.type_, protocol$ControllerVerifyConnectableRequest.hasType(), protocol$ControllerVerifyConnectableRequest.type_);
                this.timeoutMs_ = visitor.visitInt(hasTimeoutMs(), this.timeoutMs_, protocol$ControllerVerifyConnectableRequest.hasTimeoutMs(), protocol$ControllerVerifyConnectableRequest.timeoutMs_);
                this.recentConnectionTimeLimitS_ = visitor.visitInt(hasRecentConnectionTimeLimitS(), this.recentConnectionTimeLimitS_, protocol$ControllerVerifyConnectableRequest.hasRecentConnectionTimeLimitS(), protocol$ControllerVerifyConnectableRequest.recentConnectionTimeLimitS_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$ControllerVerifyConnectableRequest.bitField0_;
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
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$ControllerType.forNumber(readEnum) == null) {
                                    super.mergeVarintField(1, readEnum);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.type_ = readEnum;
                                }
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.timeoutMs_ = codedInputStream.readInt32();
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.recentConnectionTimeLimitS_ = codedInputStream.readInt32();
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
                    synchronized (Protocol$ControllerVerifyConnectableRequest.class) {
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
