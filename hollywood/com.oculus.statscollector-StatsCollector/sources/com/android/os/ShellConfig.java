package com.android.os;

import com.android.internal.os.StatsdConfigProto;
import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

public final class ShellConfig {

    public interface PulledAtomSubscriptionOrBuilder extends MessageLiteOrBuilder {
        int getFreqMillis();

        StatsdConfigProto.SimpleAtomMatcher getMatcher();

        boolean hasFreqMillis();

        boolean hasMatcher();
    }

    public interface ShellSubscriptionOrBuilder extends MessageLiteOrBuilder {
        PulledAtomSubscription getPulled(int i);

        int getPulledCount();

        List<PulledAtomSubscription> getPulledList();

        StatsdConfigProto.SimpleAtomMatcher getPushed(int i);

        int getPushedCount();

        List<StatsdConfigProto.SimpleAtomMatcher> getPushedList();
    }

    private ShellConfig() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class PulledAtomSubscription extends GeneratedMessageLite<PulledAtomSubscription, Builder> implements PulledAtomSubscriptionOrBuilder {
        private static final PulledAtomSubscription DEFAULT_INSTANCE = new PulledAtomSubscription();
        public static final int FREQ_MILLIS_FIELD_NUMBER = 2;
        public static final int MATCHER_FIELD_NUMBER = 1;
        private static volatile Parser<PulledAtomSubscription> PARSER;
        private int bitField0_;
        private int freqMillis_ = 0;
        private StatsdConfigProto.SimpleAtomMatcher matcher_;

        private PulledAtomSubscription() {
        }

        @Override // com.android.os.ShellConfig.PulledAtomSubscriptionOrBuilder
        public boolean hasMatcher() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.os.ShellConfig.PulledAtomSubscriptionOrBuilder
        public StatsdConfigProto.SimpleAtomMatcher getMatcher() {
            StatsdConfigProto.SimpleAtomMatcher simpleAtomMatcher = this.matcher_;
            return simpleAtomMatcher == null ? StatsdConfigProto.SimpleAtomMatcher.getDefaultInstance() : simpleAtomMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMatcher(StatsdConfigProto.SimpleAtomMatcher value) {
            if (value != null) {
                this.matcher_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMatcher(StatsdConfigProto.SimpleAtomMatcher.Builder builderForValue) {
            this.matcher_ = (StatsdConfigProto.SimpleAtomMatcher) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeMatcher(StatsdConfigProto.SimpleAtomMatcher value) {
            StatsdConfigProto.SimpleAtomMatcher simpleAtomMatcher = this.matcher_;
            if (simpleAtomMatcher == null || simpleAtomMatcher == StatsdConfigProto.SimpleAtomMatcher.getDefaultInstance()) {
                this.matcher_ = value;
            } else {
                this.matcher_ = (StatsdConfigProto.SimpleAtomMatcher) ((StatsdConfigProto.SimpleAtomMatcher.Builder) StatsdConfigProto.SimpleAtomMatcher.newBuilder(this.matcher_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMatcher() {
            this.matcher_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.os.ShellConfig.PulledAtomSubscriptionOrBuilder
        public boolean hasFreqMillis() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.os.ShellConfig.PulledAtomSubscriptionOrBuilder
        public int getFreqMillis() {
            return this.freqMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFreqMillis(int value) {
            this.bitField0_ |= 2;
            this.freqMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFreqMillis() {
            this.bitField0_ &= -3;
            this.freqMillis_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getMatcher());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.freqMillis_);
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getMatcher());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.freqMillis_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PulledAtomSubscription parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PulledAtomSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PulledAtomSubscription parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PulledAtomSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PulledAtomSubscription parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PulledAtomSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PulledAtomSubscription parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PulledAtomSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PulledAtomSubscription parseFrom(InputStream input) throws IOException {
            return (PulledAtomSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PulledAtomSubscription parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PulledAtomSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PulledAtomSubscription parseDelimitedFrom(InputStream input) throws IOException {
            return (PulledAtomSubscription) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PulledAtomSubscription parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PulledAtomSubscription) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PulledAtomSubscription parseFrom(CodedInputStream input) throws IOException {
            return (PulledAtomSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PulledAtomSubscription parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PulledAtomSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PulledAtomSubscription prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PulledAtomSubscription, Builder> implements PulledAtomSubscriptionOrBuilder {
            private Builder() {
                super(PulledAtomSubscription.DEFAULT_INSTANCE);
            }

            @Override // com.android.os.ShellConfig.PulledAtomSubscriptionOrBuilder
            public boolean hasMatcher() {
                return ((PulledAtomSubscription) this.instance).hasMatcher();
            }

            @Override // com.android.os.ShellConfig.PulledAtomSubscriptionOrBuilder
            public StatsdConfigProto.SimpleAtomMatcher getMatcher() {
                return ((PulledAtomSubscription) this.instance).getMatcher();
            }

            public Builder setMatcher(StatsdConfigProto.SimpleAtomMatcher value) {
                copyOnWrite();
                ((PulledAtomSubscription) this.instance).setMatcher((PulledAtomSubscription) value);
                return this;
            }

            public Builder setMatcher(StatsdConfigProto.SimpleAtomMatcher.Builder builderForValue) {
                copyOnWrite();
                ((PulledAtomSubscription) this.instance).setMatcher((PulledAtomSubscription) builderForValue);
                return this;
            }

            public Builder mergeMatcher(StatsdConfigProto.SimpleAtomMatcher value) {
                copyOnWrite();
                ((PulledAtomSubscription) this.instance).mergeMatcher(value);
                return this;
            }

            public Builder clearMatcher() {
                copyOnWrite();
                ((PulledAtomSubscription) this.instance).clearMatcher();
                return this;
            }

            @Override // com.android.os.ShellConfig.PulledAtomSubscriptionOrBuilder
            public boolean hasFreqMillis() {
                return ((PulledAtomSubscription) this.instance).hasFreqMillis();
            }

            @Override // com.android.os.ShellConfig.PulledAtomSubscriptionOrBuilder
            public int getFreqMillis() {
                return ((PulledAtomSubscription) this.instance).getFreqMillis();
            }

            public Builder setFreqMillis(int value) {
                copyOnWrite();
                ((PulledAtomSubscription) this.instance).setFreqMillis(value);
                return this;
            }

            public Builder clearFreqMillis() {
                copyOnWrite();
                ((PulledAtomSubscription) this.instance).clearFreqMillis();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PulledAtomSubscription();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PulledAtomSubscription other = (PulledAtomSubscription) arg1;
                    this.matcher_ = (StatsdConfigProto.SimpleAtomMatcher) visitor.visitMessage(this.matcher_, other.matcher_);
                    this.freqMillis_ = visitor.visitInt(hasFreqMillis(), this.freqMillis_, other.hasFreqMillis(), other.freqMillis_);
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
                                StatsdConfigProto.SimpleAtomMatcher.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (StatsdConfigProto.SimpleAtomMatcher.Builder) this.matcher_.toBuilder();
                                }
                                this.matcher_ = (StatsdConfigProto.SimpleAtomMatcher) input.readMessage(StatsdConfigProto.SimpleAtomMatcher.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.matcher_);
                                    this.matcher_ = (StatsdConfigProto.SimpleAtomMatcher) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.freqMillis_ = input.readInt32();
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
                        synchronized (PulledAtomSubscription.class) {
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

        public static PulledAtomSubscription getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PulledAtomSubscription> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ShellSubscription extends GeneratedMessageLite<ShellSubscription, Builder> implements ShellSubscriptionOrBuilder {
        private static final ShellSubscription DEFAULT_INSTANCE = new ShellSubscription();
        private static volatile Parser<ShellSubscription> PARSER = null;
        public static final int PULLED_FIELD_NUMBER = 2;
        public static final int PUSHED_FIELD_NUMBER = 1;
        private Internal.ProtobufList<PulledAtomSubscription> pulled_ = emptyProtobufList();
        private Internal.ProtobufList<StatsdConfigProto.SimpleAtomMatcher> pushed_ = emptyProtobufList();

        private ShellSubscription() {
        }

        @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
        public List<StatsdConfigProto.SimpleAtomMatcher> getPushedList() {
            return this.pushed_;
        }

        public List<? extends StatsdConfigProto.SimpleAtomMatcherOrBuilder> getPushedOrBuilderList() {
            return this.pushed_;
        }

        @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
        public int getPushedCount() {
            return this.pushed_.size();
        }

        @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
        public StatsdConfigProto.SimpleAtomMatcher getPushed(int index) {
            return this.pushed_.get(index);
        }

        public StatsdConfigProto.SimpleAtomMatcherOrBuilder getPushedOrBuilder(int index) {
            return this.pushed_.get(index);
        }

        private void ensurePushedIsMutable() {
            if (!this.pushed_.isModifiable()) {
                this.pushed_ = GeneratedMessageLite.mutableCopy(this.pushed_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPushed(int index, StatsdConfigProto.SimpleAtomMatcher value) {
            if (value != null) {
                ensurePushedIsMutable();
                this.pushed_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPushed(int index, StatsdConfigProto.SimpleAtomMatcher.Builder builderForValue) {
            ensurePushedIsMutable();
            this.pushed_.set(index, (StatsdConfigProto.SimpleAtomMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPushed(StatsdConfigProto.SimpleAtomMatcher value) {
            if (value != null) {
                ensurePushedIsMutable();
                this.pushed_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPushed(int index, StatsdConfigProto.SimpleAtomMatcher value) {
            if (value != null) {
                ensurePushedIsMutable();
                this.pushed_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPushed(StatsdConfigProto.SimpleAtomMatcher.Builder builderForValue) {
            ensurePushedIsMutable();
            this.pushed_.add((StatsdConfigProto.SimpleAtomMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPushed(int index, StatsdConfigProto.SimpleAtomMatcher.Builder builderForValue) {
            ensurePushedIsMutable();
            this.pushed_.add(index, (StatsdConfigProto.SimpleAtomMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllPushed(Iterable<? extends StatsdConfigProto.SimpleAtomMatcher> values) {
            ensurePushedIsMutable();
            AbstractMessageLite.addAll(values, this.pushed_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPushed() {
            this.pushed_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removePushed(int index) {
            ensurePushedIsMutable();
            this.pushed_.remove(index);
        }

        @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
        public List<PulledAtomSubscription> getPulledList() {
            return this.pulled_;
        }

        public List<? extends PulledAtomSubscriptionOrBuilder> getPulledOrBuilderList() {
            return this.pulled_;
        }

        @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
        public int getPulledCount() {
            return this.pulled_.size();
        }

        @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
        public PulledAtomSubscription getPulled(int index) {
            return this.pulled_.get(index);
        }

        public PulledAtomSubscriptionOrBuilder getPulledOrBuilder(int index) {
            return this.pulled_.get(index);
        }

        private void ensurePulledIsMutable() {
            if (!this.pulled_.isModifiable()) {
                this.pulled_ = GeneratedMessageLite.mutableCopy(this.pulled_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPulled(int index, PulledAtomSubscription value) {
            if (value != null) {
                ensurePulledIsMutable();
                this.pulled_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPulled(int index, PulledAtomSubscription.Builder builderForValue) {
            ensurePulledIsMutable();
            this.pulled_.set(index, (PulledAtomSubscription) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPulled(PulledAtomSubscription value) {
            if (value != null) {
                ensurePulledIsMutable();
                this.pulled_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPulled(int index, PulledAtomSubscription value) {
            if (value != null) {
                ensurePulledIsMutable();
                this.pulled_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPulled(PulledAtomSubscription.Builder builderForValue) {
            ensurePulledIsMutable();
            this.pulled_.add((PulledAtomSubscription) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPulled(int index, PulledAtomSubscription.Builder builderForValue) {
            ensurePulledIsMutable();
            this.pulled_.add(index, (PulledAtomSubscription) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllPulled(Iterable<? extends PulledAtomSubscription> values) {
            ensurePulledIsMutable();
            AbstractMessageLite.addAll(values, this.pulled_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPulled() {
            this.pulled_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removePulled(int index) {
            ensurePulledIsMutable();
            this.pulled_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.pushed_.size(); i++) {
                output.writeMessage(1, this.pushed_.get(i));
            }
            for (int i2 = 0; i2 < this.pulled_.size(); i2++) {
                output.writeMessage(2, this.pulled_.get(i2));
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
            for (int i = 0; i < this.pushed_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.pushed_.get(i));
            }
            for (int i2 = 0; i2 < this.pulled_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.pulled_.get(i2));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ShellSubscription parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ShellSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ShellSubscription parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ShellSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ShellSubscription parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ShellSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ShellSubscription parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ShellSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ShellSubscription parseFrom(InputStream input) throws IOException {
            return (ShellSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ShellSubscription parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ShellSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ShellSubscription parseDelimitedFrom(InputStream input) throws IOException {
            return (ShellSubscription) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ShellSubscription parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ShellSubscription) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ShellSubscription parseFrom(CodedInputStream input) throws IOException {
            return (ShellSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ShellSubscription parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ShellSubscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ShellSubscription prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ShellSubscription, Builder> implements ShellSubscriptionOrBuilder {
            private Builder() {
                super(ShellSubscription.DEFAULT_INSTANCE);
            }

            @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
            public List<StatsdConfigProto.SimpleAtomMatcher> getPushedList() {
                return Collections.unmodifiableList(((ShellSubscription) this.instance).getPushedList());
            }

            @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
            public int getPushedCount() {
                return ((ShellSubscription) this.instance).getPushedCount();
            }

            @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
            public StatsdConfigProto.SimpleAtomMatcher getPushed(int index) {
                return ((ShellSubscription) this.instance).getPushed(index);
            }

            public Builder setPushed(int index, StatsdConfigProto.SimpleAtomMatcher value) {
                copyOnWrite();
                ((ShellSubscription) this.instance).setPushed((ShellSubscription) index, (int) value);
                return this;
            }

            public Builder setPushed(int index, StatsdConfigProto.SimpleAtomMatcher.Builder builderForValue) {
                copyOnWrite();
                ((ShellSubscription) this.instance).setPushed((ShellSubscription) index, (int) builderForValue);
                return this;
            }

            public Builder addPushed(StatsdConfigProto.SimpleAtomMatcher value) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addPushed((ShellSubscription) value);
                return this;
            }

            public Builder addPushed(int index, StatsdConfigProto.SimpleAtomMatcher value) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addPushed((ShellSubscription) index, (int) value);
                return this;
            }

            public Builder addPushed(StatsdConfigProto.SimpleAtomMatcher.Builder builderForValue) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addPushed((ShellSubscription) builderForValue);
                return this;
            }

            public Builder addPushed(int index, StatsdConfigProto.SimpleAtomMatcher.Builder builderForValue) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addPushed((ShellSubscription) index, (int) builderForValue);
                return this;
            }

            public Builder addAllPushed(Iterable<? extends StatsdConfigProto.SimpleAtomMatcher> values) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addAllPushed(values);
                return this;
            }

            public Builder clearPushed() {
                copyOnWrite();
                ((ShellSubscription) this.instance).clearPushed();
                return this;
            }

            public Builder removePushed(int index) {
                copyOnWrite();
                ((ShellSubscription) this.instance).removePushed(index);
                return this;
            }

            @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
            public List<PulledAtomSubscription> getPulledList() {
                return Collections.unmodifiableList(((ShellSubscription) this.instance).getPulledList());
            }

            @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
            public int getPulledCount() {
                return ((ShellSubscription) this.instance).getPulledCount();
            }

            @Override // com.android.os.ShellConfig.ShellSubscriptionOrBuilder
            public PulledAtomSubscription getPulled(int index) {
                return ((ShellSubscription) this.instance).getPulled(index);
            }

            public Builder setPulled(int index, PulledAtomSubscription value) {
                copyOnWrite();
                ((ShellSubscription) this.instance).setPulled((ShellSubscription) index, (int) value);
                return this;
            }

            public Builder setPulled(int index, PulledAtomSubscription.Builder builderForValue) {
                copyOnWrite();
                ((ShellSubscription) this.instance).setPulled((ShellSubscription) index, (int) builderForValue);
                return this;
            }

            public Builder addPulled(PulledAtomSubscription value) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addPulled((ShellSubscription) value);
                return this;
            }

            public Builder addPulled(int index, PulledAtomSubscription value) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addPulled((ShellSubscription) index, (int) value);
                return this;
            }

            public Builder addPulled(PulledAtomSubscription.Builder builderForValue) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addPulled((ShellSubscription) builderForValue);
                return this;
            }

            public Builder addPulled(int index, PulledAtomSubscription.Builder builderForValue) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addPulled((ShellSubscription) index, (int) builderForValue);
                return this;
            }

            public Builder addAllPulled(Iterable<? extends PulledAtomSubscription> values) {
                copyOnWrite();
                ((ShellSubscription) this.instance).addAllPulled(values);
                return this;
            }

            public Builder clearPulled() {
                copyOnWrite();
                ((ShellSubscription) this.instance).clearPulled();
                return this;
            }

            public Builder removePulled(int index) {
                copyOnWrite();
                ((ShellSubscription) this.instance).removePulled(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ShellSubscription();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.pushed_.makeImmutable();
                    this.pulled_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ShellSubscription other = (ShellSubscription) arg1;
                    this.pushed_ = visitor.visitList(this.pushed_, other.pushed_);
                    this.pulled_ = visitor.visitList(this.pulled_, other.pulled_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                                if (!this.pushed_.isModifiable()) {
                                    this.pushed_ = GeneratedMessageLite.mutableCopy(this.pushed_);
                                }
                                this.pushed_.add((StatsdConfigProto.SimpleAtomMatcher) input.readMessage(StatsdConfigProto.SimpleAtomMatcher.parser(), extensionRegistry));
                            } else if (tag == 18) {
                                if (!this.pulled_.isModifiable()) {
                                    this.pulled_ = GeneratedMessageLite.mutableCopy(this.pulled_);
                                }
                                this.pulled_.add((PulledAtomSubscription) input.readMessage(PulledAtomSubscription.parser(), extensionRegistry));
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
                        synchronized (ShellSubscription.class) {
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

        public static ShellSubscription getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ShellSubscription> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
