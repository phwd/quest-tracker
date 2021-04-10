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

public final class Protocol$HelloResponse extends GeneratedMessageLite<Protocol$HelloResponse, Builder> implements Protocol$HelloResponseOrBuilder {
    private static final Protocol$HelloResponse DEFAULT_INSTANCE = new Protocol$HelloResponse();
    private static volatile Parser<Protocol$HelloResponse> PARSER;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private ByteString serverCertificate_;
    private ByteString signature_;
    private ByteString signedData_;

    private Protocol$HelloResponse() {
        ByteString byteString = ByteString.EMPTY;
        this.signedData_ = byteString;
        this.signature_ = byteString;
        this.serverCertificate_ = byteString;
    }

    public boolean hasSignedData() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSignedData(ByteString byteString) {
        if (byteString != null) {
            this.bitField0_ |= 1;
            this.signedData_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasSignature() {
        return (this.bitField0_ & 2) == 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSignature(ByteString byteString) {
        if (byteString != null) {
            this.bitField0_ |= 2;
            this.signature_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasServerCertificate() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServerCertificate(ByteString byteString) {
        if (byteString != null) {
            this.bitField0_ |= 4;
            this.serverCertificate_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBytes(1, this.signedData_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBytes(2, this.signature_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBytes(3, this.serverCertificate_);
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
            i2 = 0 + CodedOutputStream.computeBytesSize(1, this.signedData_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBytesSize(2, this.signature_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBytesSize(3, this.serverCertificate_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$HelloResponse, Builder> implements Protocol$HelloResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$HelloResponse.DEFAULT_INSTANCE);
        }

        public Builder setSignedData(ByteString byteString) {
            copyOnWrite();
            ((Protocol$HelloResponse) this.instance).setSignedData(byteString);
            return this;
        }

        public Builder setSignature(ByteString byteString) {
            copyOnWrite();
            ((Protocol$HelloResponse) this.instance).setSignature(byteString);
            return this;
        }

        public Builder setServerCertificate(ByteString byteString) {
            copyOnWrite();
            ((Protocol$HelloResponse) this.instance).setServerCertificate(byteString);
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
                return new Protocol$HelloResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasSignedData()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasSignature()) {
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
                Protocol$HelloResponse protocol$HelloResponse = (Protocol$HelloResponse) obj2;
                this.signedData_ = visitor.visitByteString(hasSignedData(), this.signedData_, protocol$HelloResponse.hasSignedData(), protocol$HelloResponse.signedData_);
                this.signature_ = visitor.visitByteString(hasSignature(), this.signature_, protocol$HelloResponse.hasSignature(), protocol$HelloResponse.signature_);
                this.serverCertificate_ = visitor.visitByteString(hasServerCertificate(), this.serverCertificate_, protocol$HelloResponse.hasServerCertificate(), protocol$HelloResponse.serverCertificate_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$HelloResponse.bitField0_;
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
                                this.signedData_ = codedInputStream.readBytes();
                            } else if (readTag == 18) {
                                this.bitField0_ |= 2;
                                this.signature_ = codedInputStream.readBytes();
                            } else if (readTag == 26) {
                                this.bitField0_ |= 4;
                                this.serverCertificate_ = codedInputStream.readBytes();
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
                    synchronized (Protocol$HelloResponse.class) {
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
