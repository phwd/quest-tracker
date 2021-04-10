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

public final class Protocol$HelloSignedData extends GeneratedMessageLite<Protocol$HelloSignedData, Builder> implements Protocol$HelloSignedDataOrBuilder {
    private static final Protocol$HelloSignedData DEFAULT_INSTANCE = new Protocol$HelloSignedData();
    private static volatile Parser<Protocol$HelloSignedData> PARSER;
    private ByteString authenticationChallenge_;
    private int bitField0_;
    private boolean deviceNeedsToBeUnlocked_;
    private byte memoizedIsInitialized = -1;
    private ByteString serverPublicKey_;

    private Protocol$HelloSignedData() {
        ByteString byteString = ByteString.EMPTY;
        this.serverPublicKey_ = byteString;
        this.authenticationChallenge_ = byteString;
        this.deviceNeedsToBeUnlocked_ = false;
    }

    public boolean hasServerPublicKey() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServerPublicKey(ByteString byteString) {
        if (byteString != null) {
            this.bitField0_ |= 1;
            this.serverPublicKey_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasAuthenticationChallenge() {
        return (this.bitField0_ & 2) == 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAuthenticationChallenge(ByteString byteString) {
        if (byteString != null) {
            this.bitField0_ |= 2;
            this.authenticationChallenge_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasDeviceNeedsToBeUnlocked() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceNeedsToBeUnlocked(boolean z) {
        this.bitField0_ |= 4;
        this.deviceNeedsToBeUnlocked_ = z;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBytes(1, this.serverPublicKey_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBytes(2, this.authenticationChallenge_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBool(3, this.deviceNeedsToBeUnlocked_);
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
            i2 = 0 + CodedOutputStream.computeBytesSize(1, this.serverPublicKey_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBytesSize(2, this.authenticationChallenge_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBoolSize(3, this.deviceNeedsToBeUnlocked_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$HelloSignedData, Builder> implements Protocol$HelloSignedDataOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$HelloSignedData.DEFAULT_INSTANCE);
        }

        public Builder setServerPublicKey(ByteString byteString) {
            copyOnWrite();
            ((Protocol$HelloSignedData) this.instance).setServerPublicKey(byteString);
            return this;
        }

        public Builder setAuthenticationChallenge(ByteString byteString) {
            copyOnWrite();
            ((Protocol$HelloSignedData) this.instance).setAuthenticationChallenge(byteString);
            return this;
        }

        public Builder setDeviceNeedsToBeUnlocked(boolean z) {
            copyOnWrite();
            ((Protocol$HelloSignedData) this.instance).setDeviceNeedsToBeUnlocked(z);
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
                return new Protocol$HelloSignedData();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasServerPublicKey()) {
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
                Protocol$HelloSignedData protocol$HelloSignedData = (Protocol$HelloSignedData) obj2;
                this.serverPublicKey_ = visitor.visitByteString(hasServerPublicKey(), this.serverPublicKey_, protocol$HelloSignedData.hasServerPublicKey(), protocol$HelloSignedData.serverPublicKey_);
                this.authenticationChallenge_ = visitor.visitByteString(hasAuthenticationChallenge(), this.authenticationChallenge_, protocol$HelloSignedData.hasAuthenticationChallenge(), protocol$HelloSignedData.authenticationChallenge_);
                this.deviceNeedsToBeUnlocked_ = visitor.visitBoolean(hasDeviceNeedsToBeUnlocked(), this.deviceNeedsToBeUnlocked_, protocol$HelloSignedData.hasDeviceNeedsToBeUnlocked(), protocol$HelloSignedData.deviceNeedsToBeUnlocked_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$HelloSignedData.bitField0_;
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
                                this.bitField0_ |= 1;
                                this.serverPublicKey_ = codedInputStream.readBytes();
                            } else if (readTag == 18) {
                                this.bitField0_ |= 2;
                                this.authenticationChallenge_ = codedInputStream.readBytes();
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.deviceNeedsToBeUnlocked_ = codedInputStream.readBool();
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
                    synchronized (Protocol$HelloSignedData.class) {
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
