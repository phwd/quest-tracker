package android.os;

import android.os.WorkSourceProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PowerManagerProto extends GeneratedMessageLite<PowerManagerProto, Builder> implements PowerManagerProtoOrBuilder {
    private static final PowerManagerProto DEFAULT_INSTANCE = new PowerManagerProto();
    private static volatile Parser<PowerManagerProto> PARSER;

    public interface WakeLockOrBuilder extends MessageLiteOrBuilder {
        boolean getHeld();

        int getInternalCount();

        String getPackageName();

        ByteString getPackageNameBytes();

        String getTag();

        ByteString getTagBytes();

        WorkSourceProto getWorkSource();

        boolean hasHeld();

        boolean hasInternalCount();

        boolean hasPackageName();

        boolean hasTag();

        boolean hasWorkSource();
    }

    private PowerManagerProto() {
    }

    public enum UserActivityEvent implements Internal.EnumLite {
        USER_ACTIVITY_EVENT_OTHER(0),
        USER_ACTIVITY_EVENT_BUTTON(1),
        USER_ACTIVITY_EVENT_TOUCH(2),
        USER_ACTIVITY_EVENT_ACCESSIBILITY(3);
        
        public static final int USER_ACTIVITY_EVENT_ACCESSIBILITY_VALUE = 3;
        public static final int USER_ACTIVITY_EVENT_BUTTON_VALUE = 1;
        public static final int USER_ACTIVITY_EVENT_OTHER_VALUE = 0;
        public static final int USER_ACTIVITY_EVENT_TOUCH_VALUE = 2;
        private static final Internal.EnumLiteMap<UserActivityEvent> internalValueMap = new Internal.EnumLiteMap<UserActivityEvent>() {
            /* class android.os.PowerManagerProto.UserActivityEvent.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public UserActivityEvent findValueByNumber(int number) {
                return UserActivityEvent.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static UserActivityEvent valueOf(int value2) {
            return forNumber(value2);
        }

        public static UserActivityEvent forNumber(int value2) {
            if (value2 == 0) {
                return USER_ACTIVITY_EVENT_OTHER;
            }
            if (value2 == 1) {
                return USER_ACTIVITY_EVENT_BUTTON;
            }
            if (value2 == 2) {
                return USER_ACTIVITY_EVENT_TOUCH;
            }
            if (value2 != 3) {
                return null;
            }
            return USER_ACTIVITY_EVENT_ACCESSIBILITY;
        }

        public static Internal.EnumLiteMap<UserActivityEvent> internalGetValueMap() {
            return internalValueMap;
        }

        private UserActivityEvent(int value2) {
            this.value = value2;
        }
    }

    public static final class WakeLock extends GeneratedMessageLite<WakeLock, Builder> implements WakeLockOrBuilder {
        private static final WakeLock DEFAULT_INSTANCE = new WakeLock();
        public static final int HELD_FIELD_NUMBER = 3;
        public static final int INTERNAL_COUNT_FIELD_NUMBER = 4;
        public static final int PACKAGE_NAME_FIELD_NUMBER = 2;
        private static volatile Parser<WakeLock> PARSER = null;
        public static final int TAG_FIELD_NUMBER = 1;
        public static final int WORK_SOURCE_FIELD_NUMBER = 5;
        private int bitField0_;
        private boolean held_ = false;
        private int internalCount_ = 0;
        private String packageName_ = "";
        private String tag_ = "";
        private WorkSourceProto workSource_;

        private WakeLock() {
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public boolean hasTag() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public String getTag() {
            return this.tag_;
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public ByteString getTagBytes() {
            return ByteString.copyFromUtf8(this.tag_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTag(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.tag_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTag() {
            this.bitField0_ &= -2;
            this.tag_ = getDefaultInstance().getTag();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTagBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.tag_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public boolean hasPackageName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public String getPackageName() {
            return this.packageName_;
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageName(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackageName() {
            this.bitField0_ &= -3;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public boolean hasHeld() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public boolean getHeld() {
            return this.held_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHeld(boolean value) {
            this.bitField0_ |= 4;
            this.held_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHeld() {
            this.bitField0_ &= -5;
            this.held_ = false;
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public boolean hasInternalCount() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public int getInternalCount() {
            return this.internalCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInternalCount(int value) {
            this.bitField0_ |= 8;
            this.internalCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInternalCount() {
            this.bitField0_ &= -9;
            this.internalCount_ = 0;
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public boolean hasWorkSource() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.PowerManagerProto.WakeLockOrBuilder
        public WorkSourceProto getWorkSource() {
            WorkSourceProto workSourceProto = this.workSource_;
            return workSourceProto == null ? WorkSourceProto.getDefaultInstance() : workSourceProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWorkSource(WorkSourceProto value) {
            if (value != null) {
                this.workSource_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWorkSource(WorkSourceProto.Builder builderForValue) {
            this.workSource_ = (WorkSourceProto) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeWorkSource(WorkSourceProto value) {
            WorkSourceProto workSourceProto = this.workSource_;
            if (workSourceProto == null || workSourceProto == WorkSourceProto.getDefaultInstance()) {
                this.workSource_ = value;
            } else {
                this.workSource_ = (WorkSourceProto) ((WorkSourceProto.Builder) WorkSourceProto.newBuilder(this.workSource_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWorkSource() {
            this.workSource_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getTag());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getPackageName());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.held_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.internalCount_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, getWorkSource());
            }
            this.unknownFields.writeTo(output);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeStringSize(1, getTag());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getPackageName());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.held_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.internalCount_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, getWorkSource());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WakeLock parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WakeLock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeLock parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeLock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeLock parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WakeLock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeLock parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeLock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeLock parseFrom(InputStream input) throws IOException {
            return (WakeLock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeLock parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeLock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeLock parseDelimitedFrom(InputStream input) throws IOException {
            return (WakeLock) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeLock parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeLock) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeLock parseFrom(CodedInputStream input) throws IOException {
            return (WakeLock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeLock parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeLock) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WakeLock prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WakeLock, Builder> implements WakeLockOrBuilder {
            private Builder() {
                super(WakeLock.DEFAULT_INSTANCE);
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public boolean hasTag() {
                return ((WakeLock) this.instance).hasTag();
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public String getTag() {
                return ((WakeLock) this.instance).getTag();
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public ByteString getTagBytes() {
                return ((WakeLock) this.instance).getTagBytes();
            }

            public Builder setTag(String value) {
                copyOnWrite();
                ((WakeLock) this.instance).setTag(value);
                return this;
            }

            public Builder clearTag() {
                copyOnWrite();
                ((WakeLock) this.instance).clearTag();
                return this;
            }

            public Builder setTagBytes(ByteString value) {
                copyOnWrite();
                ((WakeLock) this.instance).setTagBytes(value);
                return this;
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public boolean hasPackageName() {
                return ((WakeLock) this.instance).hasPackageName();
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public String getPackageName() {
                return ((WakeLock) this.instance).getPackageName();
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public ByteString getPackageNameBytes() {
                return ((WakeLock) this.instance).getPackageNameBytes();
            }

            public Builder setPackageName(String value) {
                copyOnWrite();
                ((WakeLock) this.instance).setPackageName(value);
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((WakeLock) this.instance).clearPackageName();
                return this;
            }

            public Builder setPackageNameBytes(ByteString value) {
                copyOnWrite();
                ((WakeLock) this.instance).setPackageNameBytes(value);
                return this;
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public boolean hasHeld() {
                return ((WakeLock) this.instance).hasHeld();
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public boolean getHeld() {
                return ((WakeLock) this.instance).getHeld();
            }

            public Builder setHeld(boolean value) {
                copyOnWrite();
                ((WakeLock) this.instance).setHeld(value);
                return this;
            }

            public Builder clearHeld() {
                copyOnWrite();
                ((WakeLock) this.instance).clearHeld();
                return this;
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public boolean hasInternalCount() {
                return ((WakeLock) this.instance).hasInternalCount();
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public int getInternalCount() {
                return ((WakeLock) this.instance).getInternalCount();
            }

            public Builder setInternalCount(int value) {
                copyOnWrite();
                ((WakeLock) this.instance).setInternalCount(value);
                return this;
            }

            public Builder clearInternalCount() {
                copyOnWrite();
                ((WakeLock) this.instance).clearInternalCount();
                return this;
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public boolean hasWorkSource() {
                return ((WakeLock) this.instance).hasWorkSource();
            }

            @Override // android.os.PowerManagerProto.WakeLockOrBuilder
            public WorkSourceProto getWorkSource() {
                return ((WakeLock) this.instance).getWorkSource();
            }

            public Builder setWorkSource(WorkSourceProto value) {
                copyOnWrite();
                ((WakeLock) this.instance).setWorkSource((WakeLock) value);
                return this;
            }

            public Builder setWorkSource(WorkSourceProto.Builder builderForValue) {
                copyOnWrite();
                ((WakeLock) this.instance).setWorkSource((WakeLock) builderForValue);
                return this;
            }

            public Builder mergeWorkSource(WorkSourceProto value) {
                copyOnWrite();
                ((WakeLock) this.instance).mergeWorkSource(value);
                return this;
            }

            public Builder clearWorkSource() {
                copyOnWrite();
                ((WakeLock) this.instance).clearWorkSource();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WakeLock();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WakeLock other = (WakeLock) arg1;
                    this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                    this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
                    this.held_ = visitor.visitBoolean(hasHeld(), this.held_, other.hasHeld(), other.held_);
                    this.internalCount_ = visitor.visitInt(hasInternalCount(), this.internalCount_, other.hasInternalCount(), other.internalCount_);
                    this.workSource_ = (WorkSourceProto) visitor.visitMessage(this.workSource_, other.workSource_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.tag_ = s;
                            } else if (tag == 18) {
                                String s2 = input.readString();
                                this.bitField0_ |= 2;
                                this.packageName_ = s2;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.held_ = input.readBool();
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.internalCount_ = input.readInt32();
                            } else if (tag == 42) {
                                WorkSourceProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder = (WorkSourceProto.Builder) this.workSource_.toBuilder();
                                }
                                this.workSource_ = (WorkSourceProto) input.readMessage(WorkSourceProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.workSource_);
                                    this.workSource_ = (WorkSourceProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 16;
                            } else if (!parseUnknownField(tag, input)) {
                                done = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (WakeLock.class) {
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

        public static WakeLock getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WakeLock> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size2;
        return size2;
    }

    public static PowerManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PowerManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PowerManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerManagerProto parseFrom(InputStream input) throws IOException {
        return (PowerManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PowerManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (PowerManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PowerManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PowerManagerProto, Builder> implements PowerManagerProtoOrBuilder {
        private Builder() {
            super(PowerManagerProto.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PowerManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PowerManagerProto powerManagerProto = (PowerManagerProto) arg1;
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (!parseUnknownField(tag, input)) {
                            done = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (PowerManagerProto.class) {
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

    public static PowerManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PowerManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
