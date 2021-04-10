package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$MirrorResponse extends GeneratedMessageLite<Protocol$MirrorResponse, Builder> implements Protocol$MirrorResponseOrBuilder {
    private static final Protocol$MirrorResponse DEFAULT_INSTANCE = new Protocol$MirrorResponse();
    private static volatile Parser<Protocol$MirrorResponse> PARSER;
    private int bitField0_;
    private String hostname_ = "";
    private String ipAddress_ = "";
    private String port_ = "";
    private boolean running_ = false;
    private String uri_ = "";

    private Protocol$MirrorResponse() {
    }

    public boolean hasRunning() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRunning(boolean z) {
        this.bitField0_ |= 1;
        this.running_ = z;
    }

    public boolean hasHostname() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getHostname() {
        return this.hostname_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHostname(String str) {
        if (str != null) {
            this.bitField0_ |= 2;
            this.hostname_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasIpAddress() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getIpAddress() {
        return this.ipAddress_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIpAddress(String str) {
        if (str != null) {
            this.bitField0_ |= 4;
            this.ipAddress_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasPort() {
        return (this.bitField0_ & 8) == 8;
    }

    public String getPort() {
        return this.port_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPort(String str) {
        if (str != null) {
            this.bitField0_ |= 8;
            this.port_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasUri() {
        return (this.bitField0_ & 16) == 16;
    }

    public String getUri() {
        return this.uri_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUri(String str) {
        if (str != null) {
            this.bitField0_ |= 16;
            this.uri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(1, this.running_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getHostname());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(3, getIpAddress());
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(4, getPort());
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeString(5, getUri());
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
            i2 = 0 + CodedOutputStream.computeBoolSize(1, this.running_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getHostname());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(3, getIpAddress());
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeStringSize(4, getPort());
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeStringSize(5, getUri());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$MirrorResponse, Builder> implements Protocol$MirrorResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$MirrorResponse.DEFAULT_INSTANCE);
        }

        public Builder setRunning(boolean z) {
            copyOnWrite();
            ((Protocol$MirrorResponse) this.instance).setRunning(z);
            return this;
        }

        public Builder setHostname(String str) {
            copyOnWrite();
            ((Protocol$MirrorResponse) this.instance).setHostname(str);
            return this;
        }

        public Builder setIpAddress(String str) {
            copyOnWrite();
            ((Protocol$MirrorResponse) this.instance).setIpAddress(str);
            return this;
        }

        public Builder setPort(String str) {
            copyOnWrite();
            ((Protocol$MirrorResponse) this.instance).setPort(str);
            return this;
        }

        public Builder setUri(String str) {
            copyOnWrite();
            ((Protocol$MirrorResponse) this.instance).setUri(str);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$MirrorResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$MirrorResponse protocol$MirrorResponse = (Protocol$MirrorResponse) obj2;
                this.running_ = visitor.visitBoolean(hasRunning(), this.running_, protocol$MirrorResponse.hasRunning(), protocol$MirrorResponse.running_);
                this.hostname_ = visitor.visitString(hasHostname(), this.hostname_, protocol$MirrorResponse.hasHostname(), protocol$MirrorResponse.hostname_);
                this.ipAddress_ = visitor.visitString(hasIpAddress(), this.ipAddress_, protocol$MirrorResponse.hasIpAddress(), protocol$MirrorResponse.ipAddress_);
                this.port_ = visitor.visitString(hasPort(), this.port_, protocol$MirrorResponse.hasPort(), protocol$MirrorResponse.port_);
                this.uri_ = visitor.visitString(hasUri(), this.uri_, protocol$MirrorResponse.hasUri(), protocol$MirrorResponse.uri_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$MirrorResponse.bitField0_;
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
                                this.running_ = codedInputStream.readBool();
                            } else if (readTag == 18) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.hostname_ = readString;
                            } else if (readTag == 26) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.ipAddress_ = readString2;
                            } else if (readTag == 34) {
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 8;
                                this.port_ = readString3;
                            } else if (readTag == 42) {
                                String readString4 = codedInputStream.readString();
                                this.bitField0_ |= 16;
                                this.uri_ = readString4;
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
                    synchronized (Protocol$MirrorResponse.class) {
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
