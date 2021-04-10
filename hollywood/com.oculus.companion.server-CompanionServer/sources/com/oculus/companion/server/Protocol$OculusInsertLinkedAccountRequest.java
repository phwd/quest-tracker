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

public final class Protocol$OculusInsertLinkedAccountRequest extends GeneratedMessageLite<Protocol$OculusInsertLinkedAccountRequest, Builder> implements Protocol$OculusInsertLinkedAccountRequestOrBuilder {
    private static final Protocol$OculusInsertLinkedAccountRequest DEFAULT_INSTANCE = new Protocol$OculusInsertLinkedAccountRequest();
    private static volatile Parser<Protocol$OculusInsertLinkedAccountRequest> PARSER;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private String serviceProvider_ = "";
    private String token_ = "";
    private String userId_ = "";

    private Protocol$OculusInsertLinkedAccountRequest() {
    }

    public boolean hasServiceProvider() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getServiceProvider() {
        return this.serviceProvider_;
    }

    public boolean hasToken() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getToken() {
        return this.token_;
    }

    public boolean hasUserId() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getUserId() {
        return this.userId_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getServiceProvider());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getToken());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(3, getUserId());
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
            i2 = 0 + CodedOutputStream.computeStringSize(1, getServiceProvider());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getToken());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(3, getUserId());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$OculusInsertLinkedAccountRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$OculusInsertLinkedAccountRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$OculusInsertLinkedAccountRequest, Builder> implements Protocol$OculusInsertLinkedAccountRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$OculusInsertLinkedAccountRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$OculusInsertLinkedAccountRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasServiceProvider()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasToken()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasUserId()) {
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
                Protocol$OculusInsertLinkedAccountRequest protocol$OculusInsertLinkedAccountRequest = (Protocol$OculusInsertLinkedAccountRequest) obj2;
                this.serviceProvider_ = visitor.visitString(hasServiceProvider(), this.serviceProvider_, protocol$OculusInsertLinkedAccountRequest.hasServiceProvider(), protocol$OculusInsertLinkedAccountRequest.serviceProvider_);
                this.token_ = visitor.visitString(hasToken(), this.token_, protocol$OculusInsertLinkedAccountRequest.hasToken(), protocol$OculusInsertLinkedAccountRequest.token_);
                this.userId_ = visitor.visitString(hasUserId(), this.userId_, protocol$OculusInsertLinkedAccountRequest.hasUserId(), protocol$OculusInsertLinkedAccountRequest.userId_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$OculusInsertLinkedAccountRequest.bitField0_;
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
                                this.serviceProvider_ = readString;
                            } else if (readTag == 18) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.token_ = readString2;
                            } else if (readTag == 26) {
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.userId_ = readString3;
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
                    synchronized (Protocol$OculusInsertLinkedAccountRequest.class) {
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
