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

public final class Protocol$HelloRequest extends GeneratedMessageLite<Protocol$HelloRequest, Builder> implements Protocol$HelloRequestOrBuilder {
    private static final Protocol$HelloRequest DEFAULT_INSTANCE = new Protocol$HelloRequest();
    private static volatile Parser<Protocol$HelloRequest> PARSER;
    private int bitField0_;
    private ByteString clientChallenge_;
    private ByteString clientPublicKey_;
    private ByteString knownCertFingerprint_;
    private byte memoizedIsInitialized = -1;

    private Protocol$HelloRequest() {
        ByteString byteString = ByteString.EMPTY;
        this.clientPublicKey_ = byteString;
        this.clientChallenge_ = byteString;
        this.knownCertFingerprint_ = byteString;
    }

    public boolean hasClientPublicKey() {
        return (this.bitField0_ & 1) == 1;
    }

    public ByteString getClientPublicKey() {
        return this.clientPublicKey_;
    }

    public boolean hasClientChallenge() {
        return (this.bitField0_ & 2) == 2;
    }

    public ByteString getClientChallenge() {
        return this.clientChallenge_;
    }

    public boolean hasKnownCertFingerprint() {
        return (this.bitField0_ & 4) == 4;
    }

    public ByteString getKnownCertFingerprint() {
        return this.knownCertFingerprint_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBytes(1, this.clientPublicKey_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBytes(2, this.clientChallenge_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBytes(3, this.knownCertFingerprint_);
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
            i2 = 0 + CodedOutputStream.computeBytesSize(1, this.clientPublicKey_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBytesSize(2, this.clientChallenge_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBytesSize(3, this.knownCertFingerprint_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$HelloRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$HelloRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$HelloRequest, Builder> implements Protocol$HelloRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$HelloRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$HelloRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasClientPublicKey()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasClientChallenge()) {
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
                Protocol$HelloRequest protocol$HelloRequest = (Protocol$HelloRequest) obj2;
                this.clientPublicKey_ = visitor.visitByteString(hasClientPublicKey(), this.clientPublicKey_, protocol$HelloRequest.hasClientPublicKey(), protocol$HelloRequest.clientPublicKey_);
                this.clientChallenge_ = visitor.visitByteString(hasClientChallenge(), this.clientChallenge_, protocol$HelloRequest.hasClientChallenge(), protocol$HelloRequest.clientChallenge_);
                this.knownCertFingerprint_ = visitor.visitByteString(hasKnownCertFingerprint(), this.knownCertFingerprint_, protocol$HelloRequest.hasKnownCertFingerprint(), protocol$HelloRequest.knownCertFingerprint_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$HelloRequest.bitField0_;
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
                                this.clientPublicKey_ = codedInputStream.readBytes();
                            } else if (readTag == 18) {
                                this.bitField0_ |= 2;
                                this.clientChallenge_ = codedInputStream.readBytes();
                            } else if (readTag == 26) {
                                this.bitField0_ |= 4;
                                this.knownCertFingerprint_ = codedInputStream.readBytes();
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
                    synchronized (Protocol$HelloRequest.class) {
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
