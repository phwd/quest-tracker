package android.app;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class PolicyProto extends GeneratedMessageLite<PolicyProto, Builder> implements PolicyProtoOrBuilder {
    private static final PolicyProto DEFAULT_INSTANCE = new PolicyProto();
    private static volatile Parser<PolicyProto> PARSER = null;
    public static final int PRIORITY_CALL_SENDER_FIELD_NUMBER = 2;
    public static final int PRIORITY_CATEGORIES_FIELD_NUMBER = 1;
    public static final int PRIORITY_MESSAGE_SENDER_FIELD_NUMBER = 3;
    public static final int SUPPRESSED_VISUAL_EFFECTS_FIELD_NUMBER = 4;
    private static final Internal.ListAdapter.Converter<Integer, Category> priorityCategories_converter_ = new Internal.ListAdapter.Converter<Integer, Category>() {
        /* class android.app.PolicyProto.AnonymousClass1 */

        public Category convert(Integer from) {
            Category result = Category.forNumber(from.intValue());
            return result == null ? Category.CATEGORY_UNKNOWN : result;
        }
    };
    private static final Internal.ListAdapter.Converter<Integer, SuppressedVisualEffect> suppressedVisualEffects_converter_ = new Internal.ListAdapter.Converter<Integer, SuppressedVisualEffect>() {
        /* class android.app.PolicyProto.AnonymousClass2 */

        public SuppressedVisualEffect convert(Integer from) {
            SuppressedVisualEffect result = SuppressedVisualEffect.forNumber(from.intValue());
            return result == null ? SuppressedVisualEffect.SVE_UNKNOWN : result;
        }
    };
    private int bitField0_;
    private int priorityCallSender_ = 0;
    private Internal.IntList priorityCategories_ = emptyIntList();
    private int priorityMessageSender_ = 0;
    private Internal.IntList suppressedVisualEffects_ = emptyIntList();

    private PolicyProto() {
    }

    public enum Category implements Internal.EnumLite {
        CATEGORY_UNKNOWN(0),
        REMINDERS(1),
        EVENTS(2),
        MESSAGES(3),
        CALLS(4),
        REPEAT_CALLERS(5),
        ALARMS(6),
        MEDIA(7),
        SYSTEM(8);
        
        public static final int ALARMS_VALUE = 6;
        public static final int CALLS_VALUE = 4;
        public static final int CATEGORY_UNKNOWN_VALUE = 0;
        public static final int EVENTS_VALUE = 2;
        public static final int MEDIA_VALUE = 7;
        public static final int MESSAGES_VALUE = 3;
        public static final int REMINDERS_VALUE = 1;
        public static final int REPEAT_CALLERS_VALUE = 5;
        public static final int SYSTEM_VALUE = 8;
        private static final Internal.EnumLiteMap<Category> internalValueMap = new Internal.EnumLiteMap<Category>() {
            /* class android.app.PolicyProto.Category.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Category findValueByNumber(int number) {
                return Category.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Category valueOf(int value2) {
            return forNumber(value2);
        }

        public static Category forNumber(int value2) {
            switch (value2) {
                case 0:
                    return CATEGORY_UNKNOWN;
                case 1:
                    return REMINDERS;
                case 2:
                    return EVENTS;
                case 3:
                    return MESSAGES;
                case 4:
                    return CALLS;
                case 5:
                    return REPEAT_CALLERS;
                case 6:
                    return ALARMS;
                case 7:
                    return MEDIA;
                case 8:
                    return SYSTEM;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Category> internalGetValueMap() {
            return internalValueMap;
        }

        private Category(int value2) {
            this.value = value2;
        }
    }

    public enum Sender implements Internal.EnumLite {
        ANY(0),
        CONTACTS(1),
        STARRED(2);
        
        public static final int ANY_VALUE = 0;
        public static final int CONTACTS_VALUE = 1;
        public static final int STARRED_VALUE = 2;
        private static final Internal.EnumLiteMap<Sender> internalValueMap = new Internal.EnumLiteMap<Sender>() {
            /* class android.app.PolicyProto.Sender.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Sender findValueByNumber(int number) {
                return Sender.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Sender valueOf(int value2) {
            return forNumber(value2);
        }

        public static Sender forNumber(int value2) {
            if (value2 == 0) {
                return ANY;
            }
            if (value2 == 1) {
                return CONTACTS;
            }
            if (value2 != 2) {
                return null;
            }
            return STARRED;
        }

        public static Internal.EnumLiteMap<Sender> internalGetValueMap() {
            return internalValueMap;
        }

        private Sender(int value2) {
            this.value = value2;
        }
    }

    public enum SuppressedVisualEffect implements Internal.EnumLite {
        SVE_UNKNOWN(0),
        SCREEN_OFF(1),
        SCREEN_ON(2),
        FULL_SCREEN_INTENT(3),
        LIGHTS(4),
        PEEK(5),
        STATUS_BAR(6),
        BADGE(7),
        AMBIENT(8),
        NOTIFICATION_LIST(9);
        
        public static final int AMBIENT_VALUE = 8;
        public static final int BADGE_VALUE = 7;
        public static final int FULL_SCREEN_INTENT_VALUE = 3;
        public static final int LIGHTS_VALUE = 4;
        public static final int NOTIFICATION_LIST_VALUE = 9;
        public static final int PEEK_VALUE = 5;
        public static final int SCREEN_OFF_VALUE = 1;
        public static final int SCREEN_ON_VALUE = 2;
        public static final int STATUS_BAR_VALUE = 6;
        public static final int SVE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<SuppressedVisualEffect> internalValueMap = new Internal.EnumLiteMap<SuppressedVisualEffect>() {
            /* class android.app.PolicyProto.SuppressedVisualEffect.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SuppressedVisualEffect findValueByNumber(int number) {
                return SuppressedVisualEffect.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SuppressedVisualEffect valueOf(int value2) {
            return forNumber(value2);
        }

        public static SuppressedVisualEffect forNumber(int value2) {
            switch (value2) {
                case 0:
                    return SVE_UNKNOWN;
                case 1:
                    return SCREEN_OFF;
                case 2:
                    return SCREEN_ON;
                case 3:
                    return FULL_SCREEN_INTENT;
                case 4:
                    return LIGHTS;
                case 5:
                    return PEEK;
                case 6:
                    return STATUS_BAR;
                case 7:
                    return BADGE;
                case 8:
                    return AMBIENT;
                case 9:
                    return NOTIFICATION_LIST;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<SuppressedVisualEffect> internalGetValueMap() {
            return internalValueMap;
        }

        private SuppressedVisualEffect(int value2) {
            this.value = value2;
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // android.app.PolicyProtoOrBuilder
    public List<Category> getPriorityCategoriesList() {
        return new Internal.ListAdapter(this.priorityCategories_, priorityCategories_converter_);
    }

    @Override // android.app.PolicyProtoOrBuilder
    public int getPriorityCategoriesCount() {
        return this.priorityCategories_.size();
    }

    @Override // android.app.PolicyProtoOrBuilder
    public Category getPriorityCategories(int index) {
        return priorityCategories_converter_.convert(Integer.valueOf(this.priorityCategories_.getInt(index)));
    }

    private void ensurePriorityCategoriesIsMutable() {
        if (!this.priorityCategories_.isModifiable()) {
            this.priorityCategories_ = GeneratedMessageLite.mutableCopy(this.priorityCategories_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPriorityCategories(int index, Category value) {
        if (value != null) {
            ensurePriorityCategoriesIsMutable();
            this.priorityCategories_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPriorityCategories(Category value) {
        if (value != null) {
            ensurePriorityCategoriesIsMutable();
            this.priorityCategories_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPriorityCategories(Iterable<? extends Category> values) {
        ensurePriorityCategoriesIsMutable();
        for (Category value : values) {
            this.priorityCategories_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPriorityCategories() {
        this.priorityCategories_ = emptyIntList();
    }

    @Override // android.app.PolicyProtoOrBuilder
    public boolean hasPriorityCallSender() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.app.PolicyProtoOrBuilder
    public Sender getPriorityCallSender() {
        Sender result = Sender.forNumber(this.priorityCallSender_);
        return result == null ? Sender.ANY : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPriorityCallSender(Sender value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.priorityCallSender_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPriorityCallSender() {
        this.bitField0_ &= -2;
        this.priorityCallSender_ = 0;
    }

    @Override // android.app.PolicyProtoOrBuilder
    public boolean hasPriorityMessageSender() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.app.PolicyProtoOrBuilder
    public Sender getPriorityMessageSender() {
        Sender result = Sender.forNumber(this.priorityMessageSender_);
        return result == null ? Sender.ANY : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPriorityMessageSender(Sender value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.priorityMessageSender_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPriorityMessageSender() {
        this.bitField0_ &= -3;
        this.priorityMessageSender_ = 0;
    }

    @Override // android.app.PolicyProtoOrBuilder
    public List<SuppressedVisualEffect> getSuppressedVisualEffectsList() {
        return new Internal.ListAdapter(this.suppressedVisualEffects_, suppressedVisualEffects_converter_);
    }

    @Override // android.app.PolicyProtoOrBuilder
    public int getSuppressedVisualEffectsCount() {
        return this.suppressedVisualEffects_.size();
    }

    @Override // android.app.PolicyProtoOrBuilder
    public SuppressedVisualEffect getSuppressedVisualEffects(int index) {
        return suppressedVisualEffects_converter_.convert(Integer.valueOf(this.suppressedVisualEffects_.getInt(index)));
    }

    private void ensureSuppressedVisualEffectsIsMutable() {
        if (!this.suppressedVisualEffects_.isModifiable()) {
            this.suppressedVisualEffects_ = GeneratedMessageLite.mutableCopy(this.suppressedVisualEffects_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSuppressedVisualEffects(int index, SuppressedVisualEffect value) {
        if (value != null) {
            ensureSuppressedVisualEffectsIsMutable();
            this.suppressedVisualEffects_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSuppressedVisualEffects(SuppressedVisualEffect value) {
        if (value != null) {
            ensureSuppressedVisualEffectsIsMutable();
            this.suppressedVisualEffects_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSuppressedVisualEffects(Iterable<? extends SuppressedVisualEffect> values) {
        ensureSuppressedVisualEffectsIsMutable();
        for (SuppressedVisualEffect value : values) {
            this.suppressedVisualEffects_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSuppressedVisualEffects() {
        this.suppressedVisualEffects_ = emptyIntList();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.priorityCategories_.size(); i++) {
            output.writeEnum(1, this.priorityCategories_.getInt(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(2, this.priorityCallSender_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(3, this.priorityMessageSender_);
        }
        for (int i2 = 0; i2 < this.suppressedVisualEffects_.size(); i2++) {
            output.writeEnum(4, this.suppressedVisualEffects_.getInt(i2));
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int dataSize = 0;
        for (int i = 0; i < this.priorityCategories_.size(); i++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.priorityCategories_.getInt(i));
        }
        int size2 = 0 + dataSize + (this.priorityCategories_.size() * 1);
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeEnumSize(2, this.priorityCallSender_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(3, this.priorityMessageSender_);
        }
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.suppressedVisualEffects_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeEnumSizeNoTag(this.suppressedVisualEffects_.getInt(i2));
        }
        int size3 = size2 + dataSize2 + (this.suppressedVisualEffects_.size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PolicyProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PolicyProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PolicyProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PolicyProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PolicyProto parseFrom(InputStream input) throws IOException {
        return (PolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PolicyProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PolicyProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PolicyProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PolicyProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PolicyProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PolicyProto parseFrom(CodedInputStream input) throws IOException {
        return (PolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PolicyProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PolicyProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PolicyProto, Builder> implements PolicyProtoOrBuilder {
        private Builder() {
            super(PolicyProto.DEFAULT_INSTANCE);
        }

        @Override // android.app.PolicyProtoOrBuilder
        public List<Category> getPriorityCategoriesList() {
            return ((PolicyProto) this.instance).getPriorityCategoriesList();
        }

        @Override // android.app.PolicyProtoOrBuilder
        public int getPriorityCategoriesCount() {
            return ((PolicyProto) this.instance).getPriorityCategoriesCount();
        }

        @Override // android.app.PolicyProtoOrBuilder
        public Category getPriorityCategories(int index) {
            return ((PolicyProto) this.instance).getPriorityCategories(index);
        }

        public Builder setPriorityCategories(int index, Category value) {
            copyOnWrite();
            ((PolicyProto) this.instance).setPriorityCategories(index, value);
            return this;
        }

        public Builder addPriorityCategories(Category value) {
            copyOnWrite();
            ((PolicyProto) this.instance).addPriorityCategories(value);
            return this;
        }

        public Builder addAllPriorityCategories(Iterable<? extends Category> values) {
            copyOnWrite();
            ((PolicyProto) this.instance).addAllPriorityCategories(values);
            return this;
        }

        public Builder clearPriorityCategories() {
            copyOnWrite();
            ((PolicyProto) this.instance).clearPriorityCategories();
            return this;
        }

        @Override // android.app.PolicyProtoOrBuilder
        public boolean hasPriorityCallSender() {
            return ((PolicyProto) this.instance).hasPriorityCallSender();
        }

        @Override // android.app.PolicyProtoOrBuilder
        public Sender getPriorityCallSender() {
            return ((PolicyProto) this.instance).getPriorityCallSender();
        }

        public Builder setPriorityCallSender(Sender value) {
            copyOnWrite();
            ((PolicyProto) this.instance).setPriorityCallSender(value);
            return this;
        }

        public Builder clearPriorityCallSender() {
            copyOnWrite();
            ((PolicyProto) this.instance).clearPriorityCallSender();
            return this;
        }

        @Override // android.app.PolicyProtoOrBuilder
        public boolean hasPriorityMessageSender() {
            return ((PolicyProto) this.instance).hasPriorityMessageSender();
        }

        @Override // android.app.PolicyProtoOrBuilder
        public Sender getPriorityMessageSender() {
            return ((PolicyProto) this.instance).getPriorityMessageSender();
        }

        public Builder setPriorityMessageSender(Sender value) {
            copyOnWrite();
            ((PolicyProto) this.instance).setPriorityMessageSender(value);
            return this;
        }

        public Builder clearPriorityMessageSender() {
            copyOnWrite();
            ((PolicyProto) this.instance).clearPriorityMessageSender();
            return this;
        }

        @Override // android.app.PolicyProtoOrBuilder
        public List<SuppressedVisualEffect> getSuppressedVisualEffectsList() {
            return ((PolicyProto) this.instance).getSuppressedVisualEffectsList();
        }

        @Override // android.app.PolicyProtoOrBuilder
        public int getSuppressedVisualEffectsCount() {
            return ((PolicyProto) this.instance).getSuppressedVisualEffectsCount();
        }

        @Override // android.app.PolicyProtoOrBuilder
        public SuppressedVisualEffect getSuppressedVisualEffects(int index) {
            return ((PolicyProto) this.instance).getSuppressedVisualEffects(index);
        }

        public Builder setSuppressedVisualEffects(int index, SuppressedVisualEffect value) {
            copyOnWrite();
            ((PolicyProto) this.instance).setSuppressedVisualEffects(index, value);
            return this;
        }

        public Builder addSuppressedVisualEffects(SuppressedVisualEffect value) {
            copyOnWrite();
            ((PolicyProto) this.instance).addSuppressedVisualEffects(value);
            return this;
        }

        public Builder addAllSuppressedVisualEffects(Iterable<? extends SuppressedVisualEffect> values) {
            copyOnWrite();
            ((PolicyProto) this.instance).addAllSuppressedVisualEffects(values);
            return this;
        }

        public Builder clearSuppressedVisualEffects() {
            copyOnWrite();
            ((PolicyProto) this.instance).clearSuppressedVisualEffects();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PolicyProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.priorityCategories_.makeImmutable();
                this.suppressedVisualEffects_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PolicyProto other = (PolicyProto) arg1;
                this.priorityCategories_ = visitor.visitIntList(this.priorityCategories_, other.priorityCategories_);
                this.priorityCallSender_ = visitor.visitInt(hasPriorityCallSender(), this.priorityCallSender_, other.hasPriorityCallSender(), other.priorityCallSender_);
                this.priorityMessageSender_ = visitor.visitInt(hasPriorityMessageSender(), this.priorityMessageSender_, other.hasPriorityMessageSender(), other.priorityMessageSender_);
                this.suppressedVisualEffects_ = visitor.visitIntList(this.suppressedVisualEffects_, other.suppressedVisualEffects_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
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
                        } else if (tag == 8) {
                            if (!this.priorityCategories_.isModifiable()) {
                                this.priorityCategories_ = GeneratedMessageLite.mutableCopy(this.priorityCategories_);
                            }
                            int rawValue = input.readEnum();
                            if (Category.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.priorityCategories_.addInt(rawValue);
                            }
                        } else if (tag == 10) {
                            if (!this.priorityCategories_.isModifiable()) {
                                this.priorityCategories_ = GeneratedMessageLite.mutableCopy(this.priorityCategories_);
                            }
                            int oldLimit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                int rawValue2 = input.readEnum();
                                if (Category.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(1, rawValue2);
                                } else {
                                    this.priorityCategories_.addInt(rawValue2);
                                }
                            }
                            input.popLimit(oldLimit);
                        } else if (tag == 16) {
                            int rawValue3 = input.readEnum();
                            if (Sender.forNumber(rawValue3) == null) {
                                super.mergeVarintField(2, rawValue3);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.priorityCallSender_ = rawValue3;
                            }
                        } else if (tag == 24) {
                            int rawValue4 = input.readEnum();
                            if (Sender.forNumber(rawValue4) == null) {
                                super.mergeVarintField(3, rawValue4);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.priorityMessageSender_ = rawValue4;
                            }
                        } else if (tag == 32) {
                            if (!this.suppressedVisualEffects_.isModifiable()) {
                                this.suppressedVisualEffects_ = GeneratedMessageLite.mutableCopy(this.suppressedVisualEffects_);
                            }
                            int rawValue5 = input.readEnum();
                            if (SuppressedVisualEffect.forNumber(rawValue5) == null) {
                                super.mergeVarintField(4, rawValue5);
                            } else {
                                this.suppressedVisualEffects_.addInt(rawValue5);
                            }
                        } else if (tag == 34) {
                            if (!this.suppressedVisualEffects_.isModifiable()) {
                                this.suppressedVisualEffects_ = GeneratedMessageLite.mutableCopy(this.suppressedVisualEffects_);
                            }
                            int oldLimit2 = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                int rawValue6 = input.readEnum();
                                if (SuppressedVisualEffect.forNumber(rawValue6) == null) {
                                    super.mergeVarintField(4, rawValue6);
                                } else {
                                    this.suppressedVisualEffects_.addInt(rawValue6);
                                }
                            }
                            input.popLimit(oldLimit2);
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
                    synchronized (PolicyProto.class) {
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

    public static PolicyProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PolicyProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
