package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.util.List;

public final class Protocol$ControllerScanRequest extends GeneratedMessageLite<Protocol$ControllerScanRequest, Builder> implements Protocol$ControllerScanRequestOrBuilder {
    private static final Protocol$ControllerScanRequest DEFAULT_INSTANCE = new Protocol$ControllerScanRequest();
    private static volatile Parser<Protocol$ControllerScanRequest> PARSER;
    private static final Internal.ListAdapter.Converter<Integer, Protocol$ControllerType> types_converter_ = new Internal.ListAdapter.Converter<Integer, Protocol$ControllerType>() {
        /* class com.oculus.companion.server.Protocol$ControllerScanRequest.AnonymousClass1 */

        public Protocol$ControllerType convert(Integer num) {
            Protocol$ControllerType forNumber = Protocol$ControllerType.forNumber(num.intValue());
            return forNumber == null ? Protocol$ControllerType.PRIMARY : forNumber;
        }
    };
    private Internal.IntList types_ = GeneratedMessageLite.emptyIntList();

    private Protocol$ControllerScanRequest() {
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public List<Protocol$ControllerType> getTypesList() {
        return new Internal.ListAdapter(this.types_, types_converter_);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.types_.size(); i++) {
            codedOutputStream.writeEnum(1, this.types_.getInt(i));
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
        for (int i3 = 0; i3 < this.types_.size(); i3++) {
            i2 += CodedOutputStream.computeEnumSizeNoTag(this.types_.getInt(i3));
        }
        int size = 0 + i2 + (this.types_.size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size;
        return size;
    }

    public static Protocol$ControllerScanRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$ControllerScanRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$ControllerScanRequest, Builder> implements Protocol$ControllerScanRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$ControllerScanRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$ControllerScanRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                this.types_.makeImmutable();
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                this.types_ = ((GeneratedMessageLite.Visitor) obj).visitIntList(this.types_, ((Protocol$ControllerScanRequest) obj2).types_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                                if (!this.types_.isModifiable()) {
                                    this.types_ = GeneratedMessageLite.mutableCopy(this.types_);
                                }
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$ControllerType.forNumber(readEnum) == null) {
                                    super.mergeVarintField(1, readEnum);
                                } else {
                                    this.types_.addInt(readEnum);
                                }
                            } else if (readTag == 10) {
                                if (!this.types_.isModifiable()) {
                                    this.types_ = GeneratedMessageLite.mutableCopy(this.types_);
                                }
                                int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    int readEnum2 = codedInputStream.readEnum();
                                    if (Protocol$ControllerType.forNumber(readEnum2) == null) {
                                        super.mergeVarintField(1, readEnum2);
                                    } else {
                                        this.types_.addInt(readEnum2);
                                    }
                                }
                                codedInputStream.popLimit(pushLimit);
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
                    synchronized (Protocol$ControllerScanRequest.class) {
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
}
