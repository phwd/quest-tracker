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

public final class Protocol$WifiConnectRequest extends GeneratedMessageLite<Protocol$WifiConnectRequest, Builder> implements Protocol$WifiConnectRequestOrBuilder {
    private static final Protocol$WifiConnectRequest DEFAULT_INSTANCE = new Protocol$WifiConnectRequest();
    private static volatile Parser<Protocol$WifiConnectRequest> PARSER;
    private int auth_ = 1;
    private int bitField0_;
    private boolean hidden_ = false;
    private byte memoizedIsInitialized = -1;
    private String password_ = "";
    private String ssid_ = "";
    private String username_ = "";

    private Protocol$WifiConnectRequest() {
    }

    public boolean hasSsid() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getSsid() {
        return this.ssid_;
    }

    public boolean hasPassword() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getPassword() {
        return this.password_;
    }

    public boolean hasAuth() {
        return (this.bitField0_ & 4) == 4;
    }

    public Protocol$WifiAuthentication getAuth() {
        Protocol$WifiAuthentication forNumber = Protocol$WifiAuthentication.forNumber(this.auth_);
        return forNumber == null ? Protocol$WifiAuthentication.NONE : forNumber;
    }

    public boolean hasHidden() {
        return (this.bitField0_ & 8) == 8;
    }

    public boolean getHidden() {
        return this.hidden_;
    }

    public boolean hasUsername() {
        return (this.bitField0_ & 16) == 16;
    }

    public String getUsername() {
        return this.username_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getSsid());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getPassword());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(3, this.auth_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeBool(4, this.hidden_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeString(5, getUsername());
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
            i2 = 0 + CodedOutputStream.computeStringSize(1, getSsid());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getPassword());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeEnumSize(3, this.auth_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeBoolSize(4, this.hidden_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeStringSize(5, getUsername());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$WifiConnectRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$WifiConnectRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$WifiConnectRequest, Builder> implements Protocol$WifiConnectRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$WifiConnectRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$WifiConnectRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasSsid()) {
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
                Protocol$WifiConnectRequest protocol$WifiConnectRequest = (Protocol$WifiConnectRequest) obj2;
                this.ssid_ = visitor.visitString(hasSsid(), this.ssid_, protocol$WifiConnectRequest.hasSsid(), protocol$WifiConnectRequest.ssid_);
                this.password_ = visitor.visitString(hasPassword(), this.password_, protocol$WifiConnectRequest.hasPassword(), protocol$WifiConnectRequest.password_);
                this.auth_ = visitor.visitInt(hasAuth(), this.auth_, protocol$WifiConnectRequest.hasAuth(), protocol$WifiConnectRequest.auth_);
                this.hidden_ = visitor.visitBoolean(hasHidden(), this.hidden_, protocol$WifiConnectRequest.hasHidden(), protocol$WifiConnectRequest.hidden_);
                this.username_ = visitor.visitString(hasUsername(), this.username_, protocol$WifiConnectRequest.hasUsername(), protocol$WifiConnectRequest.username_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$WifiConnectRequest.bitField0_;
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
                                this.ssid_ = readString;
                            } else if (readTag == 18) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.password_ = readString2;
                            } else if (readTag == 24) {
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$WifiAuthentication.forNumber(readEnum) == null) {
                                    super.mergeVarintField(3, readEnum);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.auth_ = readEnum;
                                }
                            } else if (readTag == 32) {
                                this.bitField0_ |= 8;
                                this.hidden_ = codedInputStream.readBool();
                            } else if (readTag == 42) {
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 16;
                                this.username_ = readString3;
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
                    synchronized (Protocol$WifiConnectRequest.class) {
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
