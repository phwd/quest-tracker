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

public final class Protocol$CastDevice extends GeneratedMessageLite<Protocol$CastDevice, Builder> implements Protocol$CastDeviceOrBuilder {
    private static final Protocol$CastDevice DEFAULT_INSTANCE = new Protocol$CastDevice();
    private static volatile Parser<Protocol$CastDevice> PARSER;
    private int bitField0_;
    private String id_ = "";
    private String name_ = "";
    private int status_ = 0;
    private int type_ = 0;

    private Protocol$CastDevice() {
    }

    public enum Type implements Internal.EnumLite {
        UNKNOWN(0),
        MIRACAST(1),
        CHROMECAST(2);
        
        private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() {
            /* class com.oculus.companion.server.Protocol$CastDevice.Type.AnonymousClass1 */
        };
        private final int value;

        public final int getNumber() {
            return this.value;
        }

        public static Type forNumber(int i) {
            if (i == 0) {
                return UNKNOWN;
            }
            if (i == 1) {
                return MIRACAST;
            }
            if (i != 2) {
                return null;
            }
            return CHROMECAST;
        }

        private Type(int i) {
            this.value = i;
        }
    }

    public enum Status implements Internal.EnumLite {
        FOUND(0),
        CONNECTING_TO_PEER(1),
        CONNECTION_INITIATED(2),
        CONNECTION_SUCCESS(3),
        STARTING_SESSION(4),
        CASTING(5),
        DISCONNECTING(6),
        INVALID(7);
        
        private static final Internal.EnumLiteMap<Status> internalValueMap = new Internal.EnumLiteMap<Status>() {
            /* class com.oculus.companion.server.Protocol$CastDevice.Status.AnonymousClass1 */
        };
        private final int value;

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Status valueOf(int i) {
            return forNumber(i);
        }

        public static Status forNumber(int i) {
            switch (i) {
                case 0:
                    return FOUND;
                case 1:
                    return CONNECTING_TO_PEER;
                case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                    return CONNECTION_INITIATED;
                case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                    return CONNECTION_SUCCESS;
                case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                    return STARTING_SESSION;
                case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                    return CASTING;
                case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                    return DISCONNECTING;
                case 7:
                    return INVALID;
                default:
                    return null;
            }
        }

        private Status(int i) {
            this.value = i;
        }
    }

    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getName() {
        return this.name_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String str) {
        if (str != null) {
            this.bitField0_ |= 1;
            this.name_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasId() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getId() {
        return this.id_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(String str) {
        if (str != null) {
            this.bitField0_ |= 2;
            this.id_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasType() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(Type type) {
        if (type != null) {
            this.bitField0_ |= 4;
            this.type_ = type.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasStatus() {
        return (this.bitField0_ & 8) == 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatus(Status status) {
        if (status != null) {
            this.bitField0_ |= 8;
            this.status_ = status.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getId());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(3, this.type_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeEnum(4, this.status_);
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
            i2 = 0 + CodedOutputStream.computeStringSize(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getId());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeEnumSize(3, this.type_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeEnumSize(4, this.status_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$CastDevice, Builder> implements Protocol$CastDeviceOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$CastDevice.DEFAULT_INSTANCE);
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Protocol$CastDevice) this.instance).setName(str);
            return this;
        }

        public Builder setId(String str) {
            copyOnWrite();
            ((Protocol$CastDevice) this.instance).setId(str);
            return this;
        }

        public Builder setType(Type type) {
            copyOnWrite();
            ((Protocol$CastDevice) this.instance).setType(type);
            return this;
        }

        public Builder setStatus(Status status) {
            copyOnWrite();
            ((Protocol$CastDevice) this.instance).setStatus(status);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$CastDevice();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$CastDevice protocol$CastDevice = (Protocol$CastDevice) obj2;
                this.name_ = visitor.visitString(hasName(), this.name_, protocol$CastDevice.hasName(), protocol$CastDevice.name_);
                this.id_ = visitor.visitString(hasId(), this.id_, protocol$CastDevice.hasId(), protocol$CastDevice.id_);
                this.type_ = visitor.visitInt(hasType(), this.type_, protocol$CastDevice.hasType(), protocol$CastDevice.type_);
                this.status_ = visitor.visitInt(hasStatus(), this.status_, protocol$CastDevice.hasStatus(), protocol$CastDevice.status_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$CastDevice.bitField0_;
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
                            if (readTag == 10) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ = 1 | this.bitField0_;
                                this.name_ = readString;
                            } else if (readTag == 18) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.id_ = readString2;
                            } else if (readTag == 24) {
                                int readEnum = codedInputStream.readEnum();
                                if (Type.forNumber(readEnum) == null) {
                                    super.mergeVarintField(3, readEnum);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.type_ = readEnum;
                                }
                            } else if (readTag == 32) {
                                int readEnum2 = codedInputStream.readEnum();
                                if (Status.forNumber(readEnum2) == null) {
                                    super.mergeVarintField(4, readEnum2);
                                } else {
                                    this.bitField0_ |= 8;
                                    this.status_ = readEnum2;
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
                    synchronized (Protocol$CastDevice.class) {
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

    public static Protocol$CastDevice getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Protocol$CastDevice> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
