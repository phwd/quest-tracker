package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$WifiNetwork extends GeneratedMessageLite<Protocol$WifiNetwork, Builder> implements Protocol$WifiNetworkOrBuilder {
    private static final Protocol$WifiNetwork DEFAULT_INSTANCE = new Protocol$WifiNetwork();
    private static volatile Parser<Protocol$WifiNetwork> PARSER;
    private static final Internal.ListAdapter.Converter<Integer, Protocol$WifiAuthentication> auth_converter_ = new Internal.ListAdapter.Converter<Integer, Protocol$WifiAuthentication>() {
        /* class com.oculus.companion.server.Protocol$WifiNetwork.AnonymousClass1 */

        public Protocol$WifiAuthentication convert(Integer num) {
            Protocol$WifiAuthentication forNumber = Protocol$WifiAuthentication.forNumber(num.intValue());
            return forNumber == null ? Protocol$WifiAuthentication.NONE : forNumber;
        }
    };
    private Internal.IntList auth_ = GeneratedMessageLite.emptyIntList();
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int signalLevel_ = 0;
    private String ssid_ = "";

    private Protocol$WifiNetwork() {
    }

    public boolean hasSsid() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getSsid() {
        return this.ssid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSsid(String str) {
        if (str != null) {
            this.bitField0_ |= 1;
            this.ssid_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private void ensureAuthIsMutable() {
        if (!this.auth_.isModifiable()) {
            this.auth_ = GeneratedMessageLite.mutableCopy(this.auth_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAuth(Protocol$WifiAuthentication protocol$WifiAuthentication) {
        if (protocol$WifiAuthentication != null) {
            ensureAuthIsMutable();
            this.auth_.addInt(protocol$WifiAuthentication.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasSignalLevel() {
        return (this.bitField0_ & 2) == 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSignalLevel(int i) {
        this.bitField0_ |= 2;
        this.signalLevel_ = i;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getSsid());
        }
        for (int i = 0; i < this.auth_.size(); i++) {
            codedOutputStream.writeEnum(2, this.auth_.getInt(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(3, this.signalLevel_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeStringSize(1, getSsid()) + 0 : 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.auth_.size(); i3++) {
            i2 += CodedOutputStream.computeEnumSizeNoTag(this.auth_.getInt(i3));
        }
        int size = computeStringSize + i2 + (this.auth_.size() * 1);
        if ((this.bitField0_ & 2) == 2) {
            size += CodedOutputStream.computeInt32Size(3, this.signalLevel_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$WifiNetwork, Builder> implements Protocol$WifiNetworkOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$WifiNetwork.DEFAULT_INSTANCE);
        }

        public Builder setSsid(String str) {
            copyOnWrite();
            ((Protocol$WifiNetwork) this.instance).setSsid(str);
            return this;
        }

        public Builder addAuth(Protocol$WifiAuthentication protocol$WifiAuthentication) {
            copyOnWrite();
            ((Protocol$WifiNetwork) this.instance).addAuth(protocol$WifiAuthentication);
            return this;
        }

        public Builder setSignalLevel(int i) {
            copyOnWrite();
            ((Protocol$WifiNetwork) this.instance).setSignalLevel(i);
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
                return new Protocol$WifiNetwork();
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
                this.auth_.makeImmutable();
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$WifiNetwork protocol$WifiNetwork = (Protocol$WifiNetwork) obj2;
                this.ssid_ = visitor.visitString(hasSsid(), this.ssid_, protocol$WifiNetwork.hasSsid(), protocol$WifiNetwork.ssid_);
                this.auth_ = visitor.visitIntList(this.auth_, protocol$WifiNetwork.auth_);
                this.signalLevel_ = visitor.visitInt(hasSignalLevel(), this.signalLevel_, protocol$WifiNetwork.hasSignalLevel(), protocol$WifiNetwork.signalLevel_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$WifiNetwork.bitField0_;
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
                            } else if (readTag == 16) {
                                if (!this.auth_.isModifiable()) {
                                    this.auth_ = GeneratedMessageLite.mutableCopy(this.auth_);
                                }
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$WifiAuthentication.forNumber(readEnum) == null) {
                                    super.mergeVarintField(2, readEnum);
                                } else {
                                    this.auth_.addInt(readEnum);
                                }
                            } else if (readTag == 18) {
                                if (!this.auth_.isModifiable()) {
                                    this.auth_ = GeneratedMessageLite.mutableCopy(this.auth_);
                                }
                                int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    int readEnum2 = codedInputStream.readEnum();
                                    if (Protocol$WifiAuthentication.forNumber(readEnum2) == null) {
                                        super.mergeVarintField(2, readEnum2);
                                    } else {
                                        this.auth_.addInt(readEnum2);
                                    }
                                }
                                codedInputStream.popLimit(pushLimit);
                            } else if (readTag == 24) {
                                this.bitField0_ |= 2;
                                this.signalLevel_ = codedInputStream.readInt32();
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
                    synchronized (Protocol$WifiNetwork.class) {
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

    public static Protocol$WifiNetwork getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Protocol$WifiNetwork> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
