package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$ControllerDetectionState extends GeneratedMessageLite<Protocol$ControllerDetectionState, Builder> implements Protocol$ControllerDetectionStateOrBuilder {
    private static final Protocol$ControllerDetectionState DEFAULT_INSTANCE = new Protocol$ControllerDetectionState();
    private static volatile Parser<Protocol$ControllerDetectionState> PARSER;
    private int bitField0_;
    private int errorCode_ = 0;
    private byte memoizedIsInitialized = -1;
    private int successReason_ = 0;
    private int type_ = 0;

    private Protocol$ControllerDetectionState() {
    }

    public boolean hasType() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(Protocol$ControllerType protocol$ControllerType) {
        if (protocol$ControllerType != null) {
            this.bitField0_ |= 1;
            this.type_ = protocol$ControllerType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasErrorCode() {
        return (this.bitField0_ & 2) == 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setErrorCode(Protocol$ErrorCode protocol$ErrorCode) {
        if (protocol$ErrorCode != null) {
            this.bitField0_ |= 2;
            this.errorCode_ = protocol$ErrorCode.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasSuccessReason() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSuccessReason(Protocol$VerifySuccessReason protocol$VerifySuccessReason) {
        if (protocol$VerifySuccessReason != null) {
            this.bitField0_ |= 4;
            this.successReason_ = protocol$VerifySuccessReason.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeEnum(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeEnum(2, this.errorCode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(3, this.successReason_);
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
            i2 += CodedOutputStream.computeEnumSize(2, this.errorCode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeEnumSize(3, this.successReason_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$ControllerDetectionState, Builder> implements Protocol$ControllerDetectionStateOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$ControllerDetectionState.DEFAULT_INSTANCE);
        }

        public Builder setType(Protocol$ControllerType protocol$ControllerType) {
            copyOnWrite();
            ((Protocol$ControllerDetectionState) this.instance).setType(protocol$ControllerType);
            return this;
        }

        public Builder setErrorCode(Protocol$ErrorCode protocol$ErrorCode) {
            copyOnWrite();
            ((Protocol$ControllerDetectionState) this.instance).setErrorCode(protocol$ErrorCode);
            return this;
        }

        public Builder setSuccessReason(Protocol$VerifySuccessReason protocol$VerifySuccessReason) {
            copyOnWrite();
            ((Protocol$ControllerDetectionState) this.instance).setSuccessReason(protocol$VerifySuccessReason);
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
                return new Protocol$ControllerDetectionState();
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
                Protocol$ControllerDetectionState protocol$ControllerDetectionState = (Protocol$ControllerDetectionState) obj2;
                this.type_ = visitor.visitInt(hasType(), this.type_, protocol$ControllerDetectionState.hasType(), protocol$ControllerDetectionState.type_);
                this.errorCode_ = visitor.visitInt(hasErrorCode(), this.errorCode_, protocol$ControllerDetectionState.hasErrorCode(), protocol$ControllerDetectionState.errorCode_);
                this.successReason_ = visitor.visitInt(hasSuccessReason(), this.successReason_, protocol$ControllerDetectionState.hasSuccessReason(), protocol$ControllerDetectionState.successReason_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$ControllerDetectionState.bitField0_;
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
                                int readEnum2 = codedInputStream.readEnum();
                                if (Protocol$ErrorCode.forNumber(readEnum2) == null) {
                                    super.mergeVarintField(2, readEnum2);
                                } else {
                                    this.bitField0_ |= 2;
                                    this.errorCode_ = readEnum2;
                                }
                            } else if (readTag == 24) {
                                int readEnum3 = codedInputStream.readEnum();
                                if (Protocol$VerifySuccessReason.forNumber(readEnum3) == null) {
                                    super.mergeVarintField(3, readEnum3);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.successReason_ = readEnum3;
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
                    synchronized (Protocol$ControllerDetectionState.class) {
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

    public static Parser<Protocol$ControllerDetectionState> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
