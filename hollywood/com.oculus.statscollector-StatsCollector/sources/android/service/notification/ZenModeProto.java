package android.service.notification;

import android.app.PolicyProto;
import android.content.ComponentNameProto;
import android.content.ComponentNameProtoOrBuilder;
import android.service.notification.ZenRuleProto;
import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

public final class ZenModeProto extends GeneratedMessageLite<ZenModeProto, Builder> implements ZenModeProtoOrBuilder {
    private static final ZenModeProto DEFAULT_INSTANCE = new ZenModeProto();
    public static final int ENABLED_ACTIVE_CONDITIONS_FIELD_NUMBER = 2;
    private static volatile Parser<ZenModeProto> PARSER = null;
    public static final int POLICY_FIELD_NUMBER = 5;
    public static final int SUPPRESSED_EFFECTS_FIELD_NUMBER = 3;
    public static final int SUPPRESSORS_FIELD_NUMBER = 4;
    public static final int ZEN_MODE_FIELD_NUMBER = 1;
    private int bitField0_;
    private Internal.ProtobufList<ZenRuleProto> enabledActiveConditions_ = emptyProtobufList();
    private PolicyProto policy_;
    private int suppressedEffects_ = 0;
    private Internal.ProtobufList<ComponentNameProto> suppressors_ = emptyProtobufList();
    private int zenMode_ = 0;

    private ZenModeProto() {
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public boolean hasZenMode() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public ZenMode getZenMode() {
        ZenMode result = ZenMode.forNumber(this.zenMode_);
        return result == null ? ZenMode.ZEN_MODE_OFF : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setZenMode(ZenMode value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.zenMode_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearZenMode() {
        this.bitField0_ &= -2;
        this.zenMode_ = 0;
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public List<ZenRuleProto> getEnabledActiveConditionsList() {
        return this.enabledActiveConditions_;
    }

    public List<? extends ZenRuleProtoOrBuilder> getEnabledActiveConditionsOrBuilderList() {
        return this.enabledActiveConditions_;
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public int getEnabledActiveConditionsCount() {
        return this.enabledActiveConditions_.size();
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public ZenRuleProto getEnabledActiveConditions(int index) {
        return this.enabledActiveConditions_.get(index);
    }

    public ZenRuleProtoOrBuilder getEnabledActiveConditionsOrBuilder(int index) {
        return this.enabledActiveConditions_.get(index);
    }

    private void ensureEnabledActiveConditionsIsMutable() {
        if (!this.enabledActiveConditions_.isModifiable()) {
            this.enabledActiveConditions_ = GeneratedMessageLite.mutableCopy(this.enabledActiveConditions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabledActiveConditions(int index, ZenRuleProto value) {
        if (value != null) {
            ensureEnabledActiveConditionsIsMutable();
            this.enabledActiveConditions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabledActiveConditions(int index, ZenRuleProto.Builder builderForValue) {
        ensureEnabledActiveConditionsIsMutable();
        this.enabledActiveConditions_.set(index, (ZenRuleProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEnabledActiveConditions(ZenRuleProto value) {
        if (value != null) {
            ensureEnabledActiveConditionsIsMutable();
            this.enabledActiveConditions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEnabledActiveConditions(int index, ZenRuleProto value) {
        if (value != null) {
            ensureEnabledActiveConditionsIsMutable();
            this.enabledActiveConditions_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEnabledActiveConditions(ZenRuleProto.Builder builderForValue) {
        ensureEnabledActiveConditionsIsMutable();
        this.enabledActiveConditions_.add((ZenRuleProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEnabledActiveConditions(int index, ZenRuleProto.Builder builderForValue) {
        ensureEnabledActiveConditionsIsMutable();
        this.enabledActiveConditions_.add(index, (ZenRuleProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllEnabledActiveConditions(Iterable<? extends ZenRuleProto> values) {
        ensureEnabledActiveConditionsIsMutable();
        AbstractMessageLite.addAll(values, this.enabledActiveConditions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEnabledActiveConditions() {
        this.enabledActiveConditions_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeEnabledActiveConditions(int index) {
        ensureEnabledActiveConditionsIsMutable();
        this.enabledActiveConditions_.remove(index);
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public boolean hasSuppressedEffects() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public int getSuppressedEffects() {
        return this.suppressedEffects_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSuppressedEffects(int value) {
        this.bitField0_ |= 2;
        this.suppressedEffects_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSuppressedEffects() {
        this.bitField0_ &= -3;
        this.suppressedEffects_ = 0;
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public List<ComponentNameProto> getSuppressorsList() {
        return this.suppressors_;
    }

    public List<? extends ComponentNameProtoOrBuilder> getSuppressorsOrBuilderList() {
        return this.suppressors_;
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public int getSuppressorsCount() {
        return this.suppressors_.size();
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public ComponentNameProto getSuppressors(int index) {
        return this.suppressors_.get(index);
    }

    public ComponentNameProtoOrBuilder getSuppressorsOrBuilder(int index) {
        return this.suppressors_.get(index);
    }

    private void ensureSuppressorsIsMutable() {
        if (!this.suppressors_.isModifiable()) {
            this.suppressors_ = GeneratedMessageLite.mutableCopy(this.suppressors_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSuppressors(int index, ComponentNameProto value) {
        if (value != null) {
            ensureSuppressorsIsMutable();
            this.suppressors_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSuppressors(int index, ComponentNameProto.Builder builderForValue) {
        ensureSuppressorsIsMutable();
        this.suppressors_.set(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSuppressors(ComponentNameProto value) {
        if (value != null) {
            ensureSuppressorsIsMutable();
            this.suppressors_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSuppressors(int index, ComponentNameProto value) {
        if (value != null) {
            ensureSuppressorsIsMutable();
            this.suppressors_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSuppressors(ComponentNameProto.Builder builderForValue) {
        ensureSuppressorsIsMutable();
        this.suppressors_.add((ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSuppressors(int index, ComponentNameProto.Builder builderForValue) {
        ensureSuppressorsIsMutable();
        this.suppressors_.add(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSuppressors(Iterable<? extends ComponentNameProto> values) {
        ensureSuppressorsIsMutable();
        AbstractMessageLite.addAll(values, this.suppressors_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSuppressors() {
        this.suppressors_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSuppressors(int index) {
        ensureSuppressorsIsMutable();
        this.suppressors_.remove(index);
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public boolean hasPolicy() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.notification.ZenModeProtoOrBuilder
    public PolicyProto getPolicy() {
        PolicyProto policyProto = this.policy_;
        return policyProto == null ? PolicyProto.getDefaultInstance() : policyProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPolicy(PolicyProto value) {
        if (value != null) {
            this.policy_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPolicy(PolicyProto.Builder builderForValue) {
        this.policy_ = (PolicyProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePolicy(PolicyProto value) {
        PolicyProto policyProto = this.policy_;
        if (policyProto == null || policyProto == PolicyProto.getDefaultInstance()) {
            this.policy_ = value;
        } else {
            this.policy_ = (PolicyProto) ((PolicyProto.Builder) PolicyProto.newBuilder(this.policy_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPolicy() {
        this.policy_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.zenMode_);
        }
        for (int i = 0; i < this.enabledActiveConditions_.size(); i++) {
            output.writeMessage(2, this.enabledActiveConditions_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(3, this.suppressedEffects_);
        }
        for (int i2 = 0; i2 < this.suppressors_.size(); i2++) {
            output.writeMessage(4, this.suppressors_.get(i2));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(5, getPolicy());
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.zenMode_);
        }
        for (int i = 0; i < this.enabledActiveConditions_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.enabledActiveConditions_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(3, this.suppressedEffects_);
        }
        for (int i2 = 0; i2 < this.suppressors_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.suppressors_.get(i2));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(5, getPolicy());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ZenModeProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ZenModeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ZenModeProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ZenModeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ZenModeProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ZenModeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ZenModeProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ZenModeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ZenModeProto parseFrom(InputStream input) throws IOException {
        return (ZenModeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ZenModeProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ZenModeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ZenModeProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ZenModeProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ZenModeProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ZenModeProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ZenModeProto parseFrom(CodedInputStream input) throws IOException {
        return (ZenModeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ZenModeProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ZenModeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ZenModeProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ZenModeProto, Builder> implements ZenModeProtoOrBuilder {
        private Builder() {
            super(ZenModeProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public boolean hasZenMode() {
            return ((ZenModeProto) this.instance).hasZenMode();
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public ZenMode getZenMode() {
            return ((ZenModeProto) this.instance).getZenMode();
        }

        public Builder setZenMode(ZenMode value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).setZenMode(value);
            return this;
        }

        public Builder clearZenMode() {
            copyOnWrite();
            ((ZenModeProto) this.instance).clearZenMode();
            return this;
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public List<ZenRuleProto> getEnabledActiveConditionsList() {
            return Collections.unmodifiableList(((ZenModeProto) this.instance).getEnabledActiveConditionsList());
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public int getEnabledActiveConditionsCount() {
            return ((ZenModeProto) this.instance).getEnabledActiveConditionsCount();
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public ZenRuleProto getEnabledActiveConditions(int index) {
            return ((ZenModeProto) this.instance).getEnabledActiveConditions(index);
        }

        public Builder setEnabledActiveConditions(int index, ZenRuleProto value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).setEnabledActiveConditions((ZenModeProto) index, (int) value);
            return this;
        }

        public Builder setEnabledActiveConditions(int index, ZenRuleProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenModeProto) this.instance).setEnabledActiveConditions((ZenModeProto) index, (int) builderForValue);
            return this;
        }

        public Builder addEnabledActiveConditions(ZenRuleProto value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addEnabledActiveConditions((ZenModeProto) value);
            return this;
        }

        public Builder addEnabledActiveConditions(int index, ZenRuleProto value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addEnabledActiveConditions((ZenModeProto) index, (int) value);
            return this;
        }

        public Builder addEnabledActiveConditions(ZenRuleProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addEnabledActiveConditions((ZenModeProto) builderForValue);
            return this;
        }

        public Builder addEnabledActiveConditions(int index, ZenRuleProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addEnabledActiveConditions((ZenModeProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllEnabledActiveConditions(Iterable<? extends ZenRuleProto> values) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addAllEnabledActiveConditions(values);
            return this;
        }

        public Builder clearEnabledActiveConditions() {
            copyOnWrite();
            ((ZenModeProto) this.instance).clearEnabledActiveConditions();
            return this;
        }

        public Builder removeEnabledActiveConditions(int index) {
            copyOnWrite();
            ((ZenModeProto) this.instance).removeEnabledActiveConditions(index);
            return this;
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public boolean hasSuppressedEffects() {
            return ((ZenModeProto) this.instance).hasSuppressedEffects();
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public int getSuppressedEffects() {
            return ((ZenModeProto) this.instance).getSuppressedEffects();
        }

        public Builder setSuppressedEffects(int value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).setSuppressedEffects(value);
            return this;
        }

        public Builder clearSuppressedEffects() {
            copyOnWrite();
            ((ZenModeProto) this.instance).clearSuppressedEffects();
            return this;
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public List<ComponentNameProto> getSuppressorsList() {
            return Collections.unmodifiableList(((ZenModeProto) this.instance).getSuppressorsList());
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public int getSuppressorsCount() {
            return ((ZenModeProto) this.instance).getSuppressorsCount();
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public ComponentNameProto getSuppressors(int index) {
            return ((ZenModeProto) this.instance).getSuppressors(index);
        }

        public Builder setSuppressors(int index, ComponentNameProto value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).setSuppressors((ZenModeProto) index, (int) value);
            return this;
        }

        public Builder setSuppressors(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenModeProto) this.instance).setSuppressors((ZenModeProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSuppressors(ComponentNameProto value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addSuppressors((ZenModeProto) value);
            return this;
        }

        public Builder addSuppressors(int index, ComponentNameProto value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addSuppressors((ZenModeProto) index, (int) value);
            return this;
        }

        public Builder addSuppressors(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addSuppressors((ZenModeProto) builderForValue);
            return this;
        }

        public Builder addSuppressors(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addSuppressors((ZenModeProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSuppressors(Iterable<? extends ComponentNameProto> values) {
            copyOnWrite();
            ((ZenModeProto) this.instance).addAllSuppressors(values);
            return this;
        }

        public Builder clearSuppressors() {
            copyOnWrite();
            ((ZenModeProto) this.instance).clearSuppressors();
            return this;
        }

        public Builder removeSuppressors(int index) {
            copyOnWrite();
            ((ZenModeProto) this.instance).removeSuppressors(index);
            return this;
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public boolean hasPolicy() {
            return ((ZenModeProto) this.instance).hasPolicy();
        }

        @Override // android.service.notification.ZenModeProtoOrBuilder
        public PolicyProto getPolicy() {
            return ((ZenModeProto) this.instance).getPolicy();
        }

        public Builder setPolicy(PolicyProto value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).setPolicy((ZenModeProto) value);
            return this;
        }

        public Builder setPolicy(PolicyProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenModeProto) this.instance).setPolicy((ZenModeProto) builderForValue);
            return this;
        }

        public Builder mergePolicy(PolicyProto value) {
            copyOnWrite();
            ((ZenModeProto) this.instance).mergePolicy(value);
            return this;
        }

        public Builder clearPolicy() {
            copyOnWrite();
            ((ZenModeProto) this.instance).clearPolicy();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ZenModeProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.enabledActiveConditions_.makeImmutable();
                this.suppressors_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ZenModeProto other = (ZenModeProto) arg1;
                this.zenMode_ = visitor.visitInt(hasZenMode(), this.zenMode_, other.hasZenMode(), other.zenMode_);
                this.enabledActiveConditions_ = visitor.visitList(this.enabledActiveConditions_, other.enabledActiveConditions_);
                this.suppressedEffects_ = visitor.visitInt(hasSuppressedEffects(), this.suppressedEffects_, other.hasSuppressedEffects(), other.suppressedEffects_);
                this.suppressors_ = visitor.visitList(this.suppressors_, other.suppressors_);
                this.policy_ = (PolicyProto) visitor.visitMessage(this.policy_, other.policy_);
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
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            if (ZenMode.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.zenMode_ = rawValue;
                            }
                        } else if (tag == 18) {
                            if (!this.enabledActiveConditions_.isModifiable()) {
                                this.enabledActiveConditions_ = GeneratedMessageLite.mutableCopy(this.enabledActiveConditions_);
                            }
                            this.enabledActiveConditions_.add((ZenRuleProto) input.readMessage(ZenRuleProto.parser(), extensionRegistry));
                        } else if (tag == 24) {
                            this.bitField0_ |= 2;
                            this.suppressedEffects_ = input.readInt32();
                        } else if (tag == 34) {
                            if (!this.suppressors_.isModifiable()) {
                                this.suppressors_ = GeneratedMessageLite.mutableCopy(this.suppressors_);
                            }
                            this.suppressors_.add((ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry));
                        } else if (tag == 42) {
                            PolicyProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (PolicyProto.Builder) this.policy_.toBuilder();
                            }
                            this.policy_ = (PolicyProto) input.readMessage(PolicyProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.policy_);
                                this.policy_ = (PolicyProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
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
                    synchronized (ZenModeProto.class) {
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

    public static ZenModeProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ZenModeProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
