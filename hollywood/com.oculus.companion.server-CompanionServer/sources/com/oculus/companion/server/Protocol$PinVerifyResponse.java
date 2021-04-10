package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$PinVerifyResponse extends GeneratedMessageLite<Protocol$PinVerifyResponse, Builder> implements Protocol$PinVerifyResponseOrBuilder {
    private static final Protocol$PinVerifyResponse DEFAULT_INSTANCE = new Protocol$PinVerifyResponse();
    private static volatile Parser<Protocol$PinVerifyResponse> PARSER;
    private int bitField0_;
    private int cooldownSeconds_ = 0;
    private boolean correct_ = false;
    private byte memoizedIsInitialized = -1;
    private int triesLeft_ = 0;

    private Protocol$PinVerifyResponse() {
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

    public boolean hasCooldownSeconds() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean hasTriesLeft() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(1, this.correct_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeUInt32(2, this.cooldownSeconds_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeUInt32(3, this.triesLeft_);
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
            i2 += CodedOutputStream.computeUInt32Size(2, this.cooldownSeconds_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeUInt32Size(3, this.triesLeft_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$PinVerifyResponse, Builder> implements Protocol$PinVerifyResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$PinVerifyResponse.DEFAULT_INSTANCE);
        }

        public Builder setCorrect(boolean z) {
            copyOnWrite();
            ((Protocol$PinVerifyResponse) this.instance).setCorrect(z);
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
                return new Protocol$PinVerifyResponse();
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
                Protocol$PinVerifyResponse protocol$PinVerifyResponse = (Protocol$PinVerifyResponse) obj2;
                this.correct_ = visitor.visitBoolean(hasCorrect(), this.correct_, protocol$PinVerifyResponse.hasCorrect(), protocol$PinVerifyResponse.correct_);
                this.cooldownSeconds_ = visitor.visitInt(hasCooldownSeconds(), this.cooldownSeconds_, protocol$PinVerifyResponse.hasCooldownSeconds(), protocol$PinVerifyResponse.cooldownSeconds_);
                this.triesLeft_ = visitor.visitInt(hasTriesLeft(), this.triesLeft_, protocol$PinVerifyResponse.hasTriesLeft(), protocol$PinVerifyResponse.triesLeft_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$PinVerifyResponse.bitField0_;
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
                                this.cooldownSeconds_ = codedInputStream.readUInt32();
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.triesLeft_ = codedInputStream.readUInt32();
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
                    synchronized (Protocol$PinVerifyResponse.class) {
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
