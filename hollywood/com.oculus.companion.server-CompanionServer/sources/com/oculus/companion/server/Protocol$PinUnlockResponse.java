package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$PinUnlockResponse extends GeneratedMessageLite<Protocol$PinUnlockResponse, Builder> implements Protocol$PinUnlockResponseOrBuilder {
    private static final Protocol$PinUnlockResponse DEFAULT_INSTANCE = new Protocol$PinUnlockResponse();
    private static volatile Parser<Protocol$PinUnlockResponse> PARSER;
    private int bitField0_;
    private int cooldownTimeout_ = 0;
    private boolean correct_ = false;
    private byte memoizedIsInitialized = -1;

    private Protocol$PinUnlockResponse() {
    }

    public boolean hasCorrect() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCorrect(boolean z) {
        this.bitField0_ |= 1;
        this.correct_ = z;
    }

    public boolean hasCooldownTimeout() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(1, this.correct_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeUInt32(2, this.cooldownTimeout_);
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
            i2 = 0 + CodedOutputStream.computeBoolSize(1, this.correct_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeUInt32Size(2, this.cooldownTimeout_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$PinUnlockResponse, Builder> implements Protocol$PinUnlockResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$PinUnlockResponse.DEFAULT_INSTANCE);
        }

        public Builder setCorrect(boolean z) {
            copyOnWrite();
            ((Protocol$PinUnlockResponse) this.instance).setCorrect(z);
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
                return new Protocol$PinUnlockResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasCorrect()) {
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
                Protocol$PinUnlockResponse protocol$PinUnlockResponse = (Protocol$PinUnlockResponse) obj2;
                this.correct_ = visitor.visitBoolean(hasCorrect(), this.correct_, protocol$PinUnlockResponse.hasCorrect(), protocol$PinUnlockResponse.correct_);
                this.cooldownTimeout_ = visitor.visitInt(hasCooldownTimeout(), this.cooldownTimeout_, protocol$PinUnlockResponse.hasCooldownTimeout(), protocol$PinUnlockResponse.cooldownTimeout_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$PinUnlockResponse.bitField0_;
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
                                this.correct_ = codedInputStream.readBool();
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.cooldownTimeout_ = codedInputStream.readUInt32();
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
                    synchronized (Protocol$PinUnlockResponse.class) {
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
