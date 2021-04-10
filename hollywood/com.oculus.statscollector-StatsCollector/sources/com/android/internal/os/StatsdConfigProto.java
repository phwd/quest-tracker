package com.android.internal.os;

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

public final class StatsdConfigProto {

    public interface AlarmOrBuilder extends MessageLiteOrBuilder {
        long getId();

        long getOffsetMillis();

        long getPeriodMillis();

        boolean hasId();

        boolean hasOffsetMillis();

        boolean hasPeriodMillis();
    }

    public interface AlertOrBuilder extends MessageLiteOrBuilder {
        long getId();

        long getMetricId();

        int getNumBuckets();

        int getRefractoryPeriodSecs();

        double getTriggerIfSumGt();

        boolean hasId();

        boolean hasMetricId();

        boolean hasNumBuckets();

        boolean hasRefractoryPeriodSecs();

        boolean hasTriggerIfSumGt();
    }

    public interface AtomMatcherOrBuilder extends MessageLiteOrBuilder {
        AtomMatcher.Combination getCombination();

        AtomMatcher.ContentsCase getContentsCase();

        long getId();

        SimpleAtomMatcher getSimpleAtomMatcher();

        boolean hasCombination();

        boolean hasId();

        boolean hasSimpleAtomMatcher();
    }

    public interface BroadcastSubscriberDetailsOrBuilder extends MessageLiteOrBuilder {
        String getCookie(int i);

        ByteString getCookieBytes(int i);

        int getCookieCount();

        List<String> getCookieList();

        long getSubscriberId();

        boolean hasSubscriberId();
    }

    public interface CountMetricOrBuilder extends MessageLiteOrBuilder {
        TimeUnit getBucket();

        long getCondition();

        FieldMatcher getDimensionsInCondition();

        FieldMatcher getDimensionsInWhat();

        long getId();

        MetricConditionLink getLinks(int i);

        int getLinksCount();

        List<MetricConditionLink> getLinksList();

        long getWhat();

        boolean hasBucket();

        boolean hasCondition();

        boolean hasDimensionsInCondition();

        boolean hasDimensionsInWhat();

        boolean hasId();

        boolean hasWhat();
    }

    public interface DurationMetricOrBuilder extends MessageLiteOrBuilder {
        DurationMetric.AggregationType getAggregationType();

        TimeUnit getBucket();

        long getCondition();

        FieldMatcher getDimensionsInCondition();

        FieldMatcher getDimensionsInWhat();

        long getId();

        MetricConditionLink getLinks(int i);

        int getLinksCount();

        List<MetricConditionLink> getLinksList();

        long getWhat();

        boolean hasAggregationType();

        boolean hasBucket();

        boolean hasCondition();

        boolean hasDimensionsInCondition();

        boolean hasDimensionsInWhat();

        boolean hasId();

        boolean hasWhat();
    }

    public interface EventActivationOrBuilder extends MessageLiteOrBuilder {
        ActivationType getActivationType();

        long getAtomMatcherId();

        long getDeactivationAtomMatcherId();

        long getTtlSeconds();

        boolean hasActivationType();

        boolean hasAtomMatcherId();

        boolean hasDeactivationAtomMatcherId();

        boolean hasTtlSeconds();
    }

    public interface EventMetricOrBuilder extends MessageLiteOrBuilder {
        long getCondition();

        long getId();

        MetricConditionLink getLinks(int i);

        int getLinksCount();

        List<MetricConditionLink> getLinksList();

        long getWhat();

        boolean hasCondition();

        boolean hasId();

        boolean hasWhat();
    }

    public interface FieldFilterOrBuilder extends MessageLiteOrBuilder {
        FieldMatcher getFields();

        boolean getIncludeAll();

        boolean hasFields();

        boolean hasIncludeAll();
    }

    public interface FieldMatcherOrBuilder extends MessageLiteOrBuilder {
        FieldMatcher getChild(int i);

        int getChildCount();

        List<FieldMatcher> getChildList();

        int getField();

        Position getPosition();

        boolean hasField();

        boolean hasPosition();
    }

    public interface FieldValueMatcherOrBuilder extends MessageLiteOrBuilder {
        StringListMatcher getEqAnyString();

        boolean getEqBool();

        long getEqInt();

        String getEqString();

        ByteString getEqStringBytes();

        int getField();

        float getGtFloat();

        long getGtInt();

        long getGteInt();

        float getLtFloat();

        long getLtInt();

        long getLteInt();

        MessageMatcher getMatchesTuple();

        StringListMatcher getNeqAnyString();

        Position getPosition();

        FieldValueMatcher.ValueMatcherCase getValueMatcherCase();

        boolean hasEqAnyString();

        boolean hasEqBool();

        boolean hasEqInt();

        boolean hasEqString();

        boolean hasField();

        boolean hasGtFloat();

        boolean hasGtInt();

        boolean hasGteInt();

        boolean hasLtFloat();

        boolean hasLtInt();

        boolean hasLteInt();

        boolean hasMatchesTuple();

        boolean hasNeqAnyString();

        boolean hasPosition();
    }

    public interface GaugeMetricOrBuilder extends MessageLiteOrBuilder {
        TimeUnit getBucket();

        long getCondition();

        FieldMatcher getDimensionsInCondition();

        FieldMatcher getDimensionsInWhat();

        FieldFilter getGaugeFieldsFilter();

        long getId();

        MetricConditionLink getLinks(int i);

        int getLinksCount();

        List<MetricConditionLink> getLinksList();

        long getMaxNumGaugeAtomsPerBucket();

        int getMaxPullDelaySec();

        long getMinBucketSizeNanos();

        GaugeMetric.SamplingType getSamplingType();

        boolean getSplitBucketForAppUpgrade();

        long getTriggerEvent();

        long getWhat();

        boolean hasBucket();

        boolean hasCondition();

        boolean hasDimensionsInCondition();

        boolean hasDimensionsInWhat();

        boolean hasGaugeFieldsFilter();

        boolean hasId();

        boolean hasMaxNumGaugeAtomsPerBucket();

        boolean hasMaxPullDelaySec();

        boolean hasMinBucketSizeNanos();

        boolean hasSamplingType();

        boolean hasSplitBucketForAppUpgrade();

        boolean hasTriggerEvent();

        boolean hasWhat();
    }

    public interface IncidentdDetailsOrBuilder extends MessageLiteOrBuilder {
        String getAlertDescription();

        ByteString getAlertDescriptionBytes();

        IncidentdDetails.Destination getDest();

        String getReceiverCls();

        ByteString getReceiverClsBytes();

        String getReceiverPkg();

        ByteString getReceiverPkgBytes();

        int getSection(int i);

        int getSectionCount();

        List<Integer> getSectionList();

        boolean hasAlertDescription();

        boolean hasDest();

        boolean hasReceiverCls();

        boolean hasReceiverPkg();
    }

    public interface MessageMatcherOrBuilder extends MessageLiteOrBuilder {
        FieldValueMatcher getFieldValueMatcher(int i);

        int getFieldValueMatcherCount();

        List<FieldValueMatcher> getFieldValueMatcherList();
    }

    public interface MetricActivationOrBuilder extends MessageLiteOrBuilder {
        @Deprecated
        ActivationType getActivationType();

        EventActivation getEventActivation(int i);

        int getEventActivationCount();

        List<EventActivation> getEventActivationList();

        long getMetricId();

        @Deprecated
        boolean hasActivationType();

        boolean hasMetricId();
    }

    public interface MetricConditionLinkOrBuilder extends MessageLiteOrBuilder {
        long getCondition();

        FieldMatcher getFieldsInCondition();

        FieldMatcher getFieldsInWhat();

        boolean hasCondition();

        boolean hasFieldsInCondition();

        boolean hasFieldsInWhat();
    }

    public interface PerfettoDetailsOrBuilder extends MessageLiteOrBuilder {
        ByteString getTraceConfig();

        boolean hasTraceConfig();
    }

    public interface PerfprofdDetailsOrBuilder extends MessageLiteOrBuilder {
        ByteString getPerfprofdConfig();

        boolean hasPerfprofdConfig();
    }

    public interface PredicateOrBuilder extends MessageLiteOrBuilder {
        Predicate.Combination getCombination();

        Predicate.ContentsCase getContentsCase();

        long getId();

        SimplePredicate getSimplePredicate();

        boolean hasCombination();

        boolean hasId();

        boolean hasSimplePredicate();
    }

    public interface SimpleAtomMatcherOrBuilder extends MessageLiteOrBuilder {
        int getAtomId();

        FieldValueMatcher getFieldValueMatcher(int i);

        int getFieldValueMatcherCount();

        List<FieldValueMatcher> getFieldValueMatcherList();

        boolean hasAtomId();
    }

    public interface SimplePredicateOrBuilder extends MessageLiteOrBuilder {
        boolean getCountNesting();

        FieldMatcher getDimensions();

        SimplePredicate.InitialValue getInitialValue();

        long getStart();

        long getStop();

        long getStopAll();

        boolean hasCountNesting();

        boolean hasDimensions();

        boolean hasInitialValue();

        boolean hasStart();

        boolean hasStop();

        boolean hasStopAll();
    }

    public interface StatsdConfigOrBuilder extends MessageLiteOrBuilder {
        Alarm getAlarm(int i);

        int getAlarmCount();

        List<Alarm> getAlarmList();

        Alert getAlert(int i);

        int getAlertCount();

        List<Alert> getAlertList();

        String getAllowedLogSource(int i);

        ByteString getAllowedLogSourceBytes(int i);

        int getAllowedLogSourceCount();

        List<String> getAllowedLogSourceList();

        StatsdConfig.Annotation getAnnotation(int i);

        int getAnnotationCount();

        List<StatsdConfig.Annotation> getAnnotationList();

        AtomMatcher getAtomMatcher(int i);

        int getAtomMatcherCount();

        List<AtomMatcher> getAtomMatcherList();

        CountMetric getCountMetric(int i);

        int getCountMetricCount();

        List<CountMetric> getCountMetricList();

        DurationMetric getDurationMetric(int i);

        int getDurationMetricCount();

        List<DurationMetric> getDurationMetricList();

        EventMetric getEventMetric(int i);

        int getEventMetricCount();

        List<EventMetric> getEventMetricList();

        GaugeMetric getGaugeMetric(int i);

        int getGaugeMetricCount();

        List<GaugeMetric> getGaugeMetricList();

        boolean getHashStringsInMetricReport();

        long getId();

        boolean getInstallerInMetricReport();

        MetricActivation getMetricActivation(int i);

        int getMetricActivationCount();

        List<MetricActivation> getMetricActivationList();

        long getNoReportMetric(int i);

        int getNoReportMetricCount();

        List<Long> getNoReportMetricList();

        boolean getPersistLocally();

        Predicate getPredicate(int i);

        int getPredicateCount();

        List<Predicate> getPredicateList();

        Subscription getSubscription(int i);

        int getSubscriptionCount();

        List<Subscription> getSubscriptionList();

        long getTtlInSeconds();

        ValueMetric getValueMetric(int i);

        int getValueMetricCount();

        List<ValueMetric> getValueMetricList();

        boolean getVersionStringsInMetricReport();

        boolean hasHashStringsInMetricReport();

        boolean hasId();

        boolean hasInstallerInMetricReport();

        boolean hasPersistLocally();

        boolean hasTtlInSeconds();

        boolean hasVersionStringsInMetricReport();
    }

    public interface StringListMatcherOrBuilder extends MessageLiteOrBuilder {
        String getStrValue(int i);

        ByteString getStrValueBytes(int i);

        int getStrValueCount();

        List<String> getStrValueList();
    }

    public interface SubscriptionOrBuilder extends MessageLiteOrBuilder {
        BroadcastSubscriberDetails getBroadcastSubscriberDetails();

        long getId();

        IncidentdDetails getIncidentdDetails();

        PerfettoDetails getPerfettoDetails();

        PerfprofdDetails getPerfprofdDetails();

        float getProbabilityOfInforming();

        long getRuleId();

        Subscription.RuleType getRuleType();

        Subscription.SubscriberInformationCase getSubscriberInformationCase();

        boolean hasBroadcastSubscriberDetails();

        boolean hasId();

        boolean hasIncidentdDetails();

        boolean hasPerfettoDetails();

        boolean hasPerfprofdDetails();

        boolean hasProbabilityOfInforming();

        boolean hasRuleId();

        boolean hasRuleType();
    }

    public interface ValueMetricOrBuilder extends MessageLiteOrBuilder {
        ValueMetric.AggregationType getAggregationType();

        TimeUnit getBucket();

        long getCondition();

        FieldMatcher getDimensionsInCondition();

        FieldMatcher getDimensionsInWhat();

        long getId();

        MetricConditionLink getLinks(int i);

        int getLinksCount();

        List<MetricConditionLink> getLinksList();

        int getMaxPullDelaySec();

        long getMinBucketSizeNanos();

        boolean getSkipZeroDiffOutput();

        boolean getSplitBucketForAppUpgrade();

        boolean getUseAbsoluteValueOnReset();

        boolean getUseDiff();

        boolean getUseZeroDefaultBase();

        ValueMetric.ValueDirection getValueDirection();

        FieldMatcher getValueField();

        long getWhat();

        boolean hasAggregationType();

        boolean hasBucket();

        boolean hasCondition();

        boolean hasDimensionsInCondition();

        boolean hasDimensionsInWhat();

        boolean hasId();

        boolean hasMaxPullDelaySec();

        boolean hasMinBucketSizeNanos();

        boolean hasSkipZeroDiffOutput();

        boolean hasSplitBucketForAppUpgrade();

        boolean hasUseAbsoluteValueOnReset();

        boolean hasUseDiff();

        boolean hasUseZeroDefaultBase();

        boolean hasValueDirection();

        boolean hasValueField();

        boolean hasWhat();
    }

    private StatsdConfigProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum Position implements Internal.EnumLite {
        POSITION_UNKNOWN(0),
        FIRST(1),
        LAST(2),
        ANY(3),
        ALL(4);
        
        public static final int ALL_VALUE = 4;
        public static final int ANY_VALUE = 3;
        public static final int FIRST_VALUE = 1;
        public static final int LAST_VALUE = 2;
        public static final int POSITION_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<Position> internalValueMap = new Internal.EnumLiteMap<Position>() {
            /* class com.android.internal.os.StatsdConfigProto.Position.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Position findValueByNumber(int number) {
                return Position.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Position valueOf(int value2) {
            return forNumber(value2);
        }

        public static Position forNumber(int value2) {
            if (value2 == 0) {
                return POSITION_UNKNOWN;
            }
            if (value2 == 1) {
                return FIRST;
            }
            if (value2 == 2) {
                return LAST;
            }
            if (value2 == 3) {
                return ANY;
            }
            if (value2 != 4) {
                return null;
            }
            return ALL;
        }

        public static Internal.EnumLiteMap<Position> internalGetValueMap() {
            return internalValueMap;
        }

        private Position(int value2) {
            this.value = value2;
        }
    }

    public enum TimeUnit implements Internal.EnumLite {
        TIME_UNIT_UNSPECIFIED(0),
        ONE_MINUTE(1),
        FIVE_MINUTES(2),
        TEN_MINUTES(3),
        THIRTY_MINUTES(4),
        ONE_HOUR(5),
        THREE_HOURS(6),
        SIX_HOURS(7),
        TWELVE_HOURS(8),
        ONE_DAY(9),
        CTS(1000);
        
        public static final int CTS_VALUE = 1000;
        public static final int FIVE_MINUTES_VALUE = 2;
        public static final int ONE_DAY_VALUE = 9;
        public static final int ONE_HOUR_VALUE = 5;
        public static final int ONE_MINUTE_VALUE = 1;
        public static final int SIX_HOURS_VALUE = 7;
        public static final int TEN_MINUTES_VALUE = 3;
        public static final int THIRTY_MINUTES_VALUE = 4;
        public static final int THREE_HOURS_VALUE = 6;
        public static final int TIME_UNIT_UNSPECIFIED_VALUE = 0;
        public static final int TWELVE_HOURS_VALUE = 8;
        private static final Internal.EnumLiteMap<TimeUnit> internalValueMap = new Internal.EnumLiteMap<TimeUnit>() {
            /* class com.android.internal.os.StatsdConfigProto.TimeUnit.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TimeUnit findValueByNumber(int number) {
                return TimeUnit.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TimeUnit valueOf(int value2) {
            return forNumber(value2);
        }

        public static TimeUnit forNumber(int value2) {
            if (value2 == 1000) {
                return CTS;
            }
            switch (value2) {
                case 0:
                    return TIME_UNIT_UNSPECIFIED;
                case 1:
                    return ONE_MINUTE;
                case 2:
                    return FIVE_MINUTES;
                case 3:
                    return TEN_MINUTES;
                case 4:
                    return THIRTY_MINUTES;
                case 5:
                    return ONE_HOUR;
                case 6:
                    return THREE_HOURS;
                case 7:
                    return SIX_HOURS;
                case 8:
                    return TWELVE_HOURS;
                case 9:
                    return ONE_DAY;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<TimeUnit> internalGetValueMap() {
            return internalValueMap;
        }

        private TimeUnit(int value2) {
            this.value = value2;
        }
    }

    public enum LogicalOperation implements Internal.EnumLite {
        LOGICAL_OPERATION_UNSPECIFIED(0),
        AND(1),
        OR(2),
        NOT(3),
        NAND(4),
        NOR(5);
        
        public static final int AND_VALUE = 1;
        public static final int LOGICAL_OPERATION_UNSPECIFIED_VALUE = 0;
        public static final int NAND_VALUE = 4;
        public static final int NOR_VALUE = 5;
        public static final int NOT_VALUE = 3;
        public static final int OR_VALUE = 2;
        private static final Internal.EnumLiteMap<LogicalOperation> internalValueMap = new Internal.EnumLiteMap<LogicalOperation>() {
            /* class com.android.internal.os.StatsdConfigProto.LogicalOperation.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LogicalOperation findValueByNumber(int number) {
                return LogicalOperation.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LogicalOperation valueOf(int value2) {
            return forNumber(value2);
        }

        public static LogicalOperation forNumber(int value2) {
            if (value2 == 0) {
                return LOGICAL_OPERATION_UNSPECIFIED;
            }
            if (value2 == 1) {
                return AND;
            }
            if (value2 == 2) {
                return OR;
            }
            if (value2 == 3) {
                return NOT;
            }
            if (value2 == 4) {
                return NAND;
            }
            if (value2 != 5) {
                return null;
            }
            return NOR;
        }

        public static Internal.EnumLiteMap<LogicalOperation> internalGetValueMap() {
            return internalValueMap;
        }

        private LogicalOperation(int value2) {
            this.value = value2;
        }
    }

    public enum ActivationType implements Internal.EnumLite {
        ACTIVATION_TYPE_UNKNOWN(0),
        ACTIVATE_IMMEDIATELY(1),
        ACTIVATE_ON_BOOT(2);
        
        public static final int ACTIVATE_IMMEDIATELY_VALUE = 1;
        public static final int ACTIVATE_ON_BOOT_VALUE = 2;
        public static final int ACTIVATION_TYPE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<ActivationType> internalValueMap = new Internal.EnumLiteMap<ActivationType>() {
            /* class com.android.internal.os.StatsdConfigProto.ActivationType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ActivationType findValueByNumber(int number) {
                return ActivationType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ActivationType valueOf(int value2) {
            return forNumber(value2);
        }

        public static ActivationType forNumber(int value2) {
            if (value2 == 0) {
                return ACTIVATION_TYPE_UNKNOWN;
            }
            if (value2 == 1) {
                return ACTIVATE_IMMEDIATELY;
            }
            if (value2 != 2) {
                return null;
            }
            return ACTIVATE_ON_BOOT;
        }

        public static Internal.EnumLiteMap<ActivationType> internalGetValueMap() {
            return internalValueMap;
        }

        private ActivationType(int value2) {
            this.value = value2;
        }
    }

    public static final class FieldMatcher extends GeneratedMessageLite<FieldMatcher, Builder> implements FieldMatcherOrBuilder {
        public static final int CHILD_FIELD_NUMBER = 3;
        private static final FieldMatcher DEFAULT_INSTANCE = new FieldMatcher();
        public static final int FIELD_FIELD_NUMBER = 1;
        private static volatile Parser<FieldMatcher> PARSER = null;
        public static final int POSITION_FIELD_NUMBER = 2;
        private int bitField0_;
        private Internal.ProtobufList<FieldMatcher> child_ = emptyProtobufList();
        private int field_ = 0;
        private int position_ = 0;

        private FieldMatcher() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
        public boolean hasField() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
        public int getField() {
            return this.field_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setField(int value) {
            this.bitField0_ |= 1;
            this.field_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearField() {
            this.bitField0_ &= -2;
            this.field_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
        public boolean hasPosition() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
        public Position getPosition() {
            Position result = Position.forNumber(this.position_);
            return result == null ? Position.POSITION_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPosition(Position value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.position_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPosition() {
            this.bitField0_ &= -3;
            this.position_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
        public List<FieldMatcher> getChildList() {
            return this.child_;
        }

        public List<? extends FieldMatcherOrBuilder> getChildOrBuilderList() {
            return this.child_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
        public int getChildCount() {
            return this.child_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
        public FieldMatcher getChild(int index) {
            return this.child_.get(index);
        }

        public FieldMatcherOrBuilder getChildOrBuilder(int index) {
            return this.child_.get(index);
        }

        private void ensureChildIsMutable() {
            if (!this.child_.isModifiable()) {
                this.child_ = GeneratedMessageLite.mutableCopy(this.child_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChild(int index, FieldMatcher value) {
            if (value != null) {
                ensureChildIsMutable();
                this.child_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChild(int index, Builder builderForValue) {
            ensureChildIsMutable();
            this.child_.set(index, (FieldMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChild(FieldMatcher value) {
            if (value != null) {
                ensureChildIsMutable();
                this.child_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChild(int index, FieldMatcher value) {
            if (value != null) {
                ensureChildIsMutable();
                this.child_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChild(Builder builderForValue) {
            ensureChildIsMutable();
            this.child_.add((FieldMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChild(int index, Builder builderForValue) {
            ensureChildIsMutable();
            this.child_.add(index, (FieldMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllChild(Iterable<? extends FieldMatcher> values) {
            ensureChildIsMutable();
            AbstractMessageLite.addAll(values, this.child_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChild() {
            this.child_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeChild(int index) {
            ensureChildIsMutable();
            this.child_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.field_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.position_);
            }
            for (int i = 0; i < this.child_.size(); i++) {
                output.writeMessage(3, this.child_.get(i));
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.field_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.position_);
            }
            for (int i = 0; i < this.child_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(3, this.child_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static FieldMatcher parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FieldMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldMatcher parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldMatcher parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FieldMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldMatcher parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldMatcher parseFrom(InputStream input) throws IOException {
            return (FieldMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldMatcher parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldMatcher parseDelimitedFrom(InputStream input) throws IOException {
            return (FieldMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldMatcher parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldMatcher parseFrom(CodedInputStream input) throws IOException {
            return (FieldMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldMatcher parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FieldMatcher prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FieldMatcher, Builder> implements FieldMatcherOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(FieldMatcher.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
            public boolean hasField() {
                return ((FieldMatcher) this.instance).hasField();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
            public int getField() {
                return ((FieldMatcher) this.instance).getField();
            }

            public Builder setField(int value) {
                copyOnWrite();
                ((FieldMatcher) this.instance).setField(value);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((FieldMatcher) this.instance).clearField();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
            public boolean hasPosition() {
                return ((FieldMatcher) this.instance).hasPosition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
            public Position getPosition() {
                return ((FieldMatcher) this.instance).getPosition();
            }

            public Builder setPosition(Position value) {
                copyOnWrite();
                ((FieldMatcher) this.instance).setPosition(value);
                return this;
            }

            public Builder clearPosition() {
                copyOnWrite();
                ((FieldMatcher) this.instance).clearPosition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
            public List<FieldMatcher> getChildList() {
                return Collections.unmodifiableList(((FieldMatcher) this.instance).getChildList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
            public int getChildCount() {
                return ((FieldMatcher) this.instance).getChildCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldMatcherOrBuilder
            public FieldMatcher getChild(int index) {
                return ((FieldMatcher) this.instance).getChild(index);
            }

            public Builder setChild(int index, FieldMatcher value) {
                copyOnWrite();
                ((FieldMatcher) this.instance).setChild((FieldMatcher) index, (int) value);
                return this;
            }

            public Builder setChild(int index, Builder builderForValue) {
                copyOnWrite();
                ((FieldMatcher) this.instance).setChild((FieldMatcher) index, (int) builderForValue);
                return this;
            }

            public Builder addChild(FieldMatcher value) {
                copyOnWrite();
                ((FieldMatcher) this.instance).addChild(value);
                return this;
            }

            public Builder addChild(int index, FieldMatcher value) {
                copyOnWrite();
                ((FieldMatcher) this.instance).addChild((FieldMatcher) index, (int) value);
                return this;
            }

            public Builder addChild(Builder builderForValue) {
                copyOnWrite();
                ((FieldMatcher) this.instance).addChild((FieldMatcher) builderForValue);
                return this;
            }

            public Builder addChild(int index, Builder builderForValue) {
                copyOnWrite();
                ((FieldMatcher) this.instance).addChild((FieldMatcher) index, (int) builderForValue);
                return this;
            }

            public Builder addAllChild(Iterable<? extends FieldMatcher> values) {
                copyOnWrite();
                ((FieldMatcher) this.instance).addAllChild(values);
                return this;
            }

            public Builder clearChild() {
                copyOnWrite();
                ((FieldMatcher) this.instance).clearChild();
                return this;
            }

            public Builder removeChild(int index) {
                copyOnWrite();
                ((FieldMatcher) this.instance).removeChild(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new FieldMatcher();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.child_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    FieldMatcher other = (FieldMatcher) arg1;
                    this.field_ = visitor.visitInt(hasField(), this.field_, other.hasField(), other.field_);
                    this.position_ = visitor.visitInt(hasPosition(), this.position_, other.hasPosition(), other.position_);
                    this.child_ = visitor.visitList(this.child_, other.child_);
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
                                this.bitField0_ |= 1;
                                this.field_ = input.readInt32();
                            } else if (tag == 16) {
                                int rawValue = input.readEnum();
                                if (Position.forNumber(rawValue) == null) {
                                    super.mergeVarintField(2, rawValue);
                                } else {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.position_ = rawValue;
                                }
                            } else if (tag == 26) {
                                if (!this.child_.isModifiable()) {
                                    this.child_ = GeneratedMessageLite.mutableCopy(this.child_);
                                }
                                this.child_.add((FieldMatcher) input.readMessage(parser(), extensionRegistry));
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
                        synchronized (FieldMatcher.class) {
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

        public static FieldMatcher getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldMatcher> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class FieldValueMatcher extends GeneratedMessageLite<FieldValueMatcher, Builder> implements FieldValueMatcherOrBuilder {
        private static final FieldValueMatcher DEFAULT_INSTANCE = new FieldValueMatcher();
        public static final int EQ_ANY_STRING_FIELD_NUMBER = 13;
        public static final int EQ_BOOL_FIELD_NUMBER = 3;
        public static final int EQ_INT_FIELD_NUMBER = 5;
        public static final int EQ_STRING_FIELD_NUMBER = 4;
        public static final int FIELD_FIELD_NUMBER = 1;
        public static final int GTE_INT_FIELD_NUMBER = 11;
        public static final int GT_FLOAT_FIELD_NUMBER = 9;
        public static final int GT_INT_FIELD_NUMBER = 7;
        public static final int LTE_INT_FIELD_NUMBER = 10;
        public static final int LT_FLOAT_FIELD_NUMBER = 8;
        public static final int LT_INT_FIELD_NUMBER = 6;
        public static final int MATCHES_TUPLE_FIELD_NUMBER = 12;
        public static final int NEQ_ANY_STRING_FIELD_NUMBER = 14;
        private static volatile Parser<FieldValueMatcher> PARSER = null;
        public static final int POSITION_FIELD_NUMBER = 2;
        private int bitField0_;
        private int field_ = 0;
        private int position_ = 0;
        private int valueMatcherCase_ = 0;
        private Object valueMatcher_;

        private FieldValueMatcher() {
        }

        public enum ValueMatcherCase implements Internal.EnumLite {
            EQ_BOOL(3),
            EQ_STRING(4),
            EQ_INT(5),
            LT_INT(6),
            GT_INT(7),
            LT_FLOAT(8),
            GT_FLOAT(9),
            LTE_INT(10),
            GTE_INT(11),
            MATCHES_TUPLE(12),
            EQ_ANY_STRING(13),
            NEQ_ANY_STRING(14),
            VALUEMATCHER_NOT_SET(0);
            
            private final int value;

            private ValueMatcherCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static ValueMatcherCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static ValueMatcherCase forNumber(int value2) {
                if (value2 == 0) {
                    return VALUEMATCHER_NOT_SET;
                }
                switch (value2) {
                    case 3:
                        return EQ_BOOL;
                    case 4:
                        return EQ_STRING;
                    case 5:
                        return EQ_INT;
                    case 6:
                        return LT_INT;
                    case 7:
                        return GT_INT;
                    case 8:
                        return LT_FLOAT;
                    case 9:
                        return GT_FLOAT;
                    case 10:
                        return LTE_INT;
                    case 11:
                        return GTE_INT;
                    case 12:
                        return MATCHES_TUPLE;
                    case 13:
                        return EQ_ANY_STRING;
                    case 14:
                        return NEQ_ANY_STRING;
                    default:
                        return null;
                }
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public ValueMatcherCase getValueMatcherCase() {
            return ValueMatcherCase.forNumber(this.valueMatcherCase_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValueMatcher() {
            this.valueMatcherCase_ = 0;
            this.valueMatcher_ = null;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasField() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public int getField() {
            return this.field_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setField(int value) {
            this.bitField0_ |= 1;
            this.field_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearField() {
            this.bitField0_ &= -2;
            this.field_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasPosition() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public Position getPosition() {
            Position result = Position.forNumber(this.position_);
            return result == null ? Position.POSITION_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPosition(Position value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.position_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPosition() {
            this.bitField0_ &= -3;
            this.position_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasEqBool() {
            return this.valueMatcherCase_ == 3;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean getEqBool() {
            if (this.valueMatcherCase_ == 3) {
                return ((Boolean) this.valueMatcher_).booleanValue();
            }
            return false;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEqBool(boolean value) {
            this.valueMatcherCase_ = 3;
            this.valueMatcher_ = Boolean.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEqBool() {
            if (this.valueMatcherCase_ == 3) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasEqString() {
            return this.valueMatcherCase_ == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public String getEqString() {
            if (this.valueMatcherCase_ == 4) {
                return (String) this.valueMatcher_;
            }
            return "";
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public ByteString getEqStringBytes() {
            String ref = "";
            if (this.valueMatcherCase_ == 4) {
                ref = (String) this.valueMatcher_;
            }
            return ByteString.copyFromUtf8(ref);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEqString(String value) {
            if (value != null) {
                this.valueMatcherCase_ = 4;
                this.valueMatcher_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEqString() {
            if (this.valueMatcherCase_ == 4) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEqStringBytes(ByteString value) {
            if (value != null) {
                this.valueMatcherCase_ = 4;
                this.valueMatcher_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasEqInt() {
            return this.valueMatcherCase_ == 5;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public long getEqInt() {
            if (this.valueMatcherCase_ == 5) {
                return ((Long) this.valueMatcher_).longValue();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEqInt(long value) {
            this.valueMatcherCase_ = 5;
            this.valueMatcher_ = Long.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEqInt() {
            if (this.valueMatcherCase_ == 5) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasLtInt() {
            return this.valueMatcherCase_ == 6;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public long getLtInt() {
            if (this.valueMatcherCase_ == 6) {
                return ((Long) this.valueMatcher_).longValue();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLtInt(long value) {
            this.valueMatcherCase_ = 6;
            this.valueMatcher_ = Long.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLtInt() {
            if (this.valueMatcherCase_ == 6) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasGtInt() {
            return this.valueMatcherCase_ == 7;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public long getGtInt() {
            if (this.valueMatcherCase_ == 7) {
                return ((Long) this.valueMatcher_).longValue();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setGtInt(long value) {
            this.valueMatcherCase_ = 7;
            this.valueMatcher_ = Long.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearGtInt() {
            if (this.valueMatcherCase_ == 7) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasLtFloat() {
            return this.valueMatcherCase_ == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public float getLtFloat() {
            if (this.valueMatcherCase_ == 8) {
                return ((Float) this.valueMatcher_).floatValue();
            }
            return 0.0f;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLtFloat(float value) {
            this.valueMatcherCase_ = 8;
            this.valueMatcher_ = Float.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLtFloat() {
            if (this.valueMatcherCase_ == 8) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasGtFloat() {
            return this.valueMatcherCase_ == 9;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public float getGtFloat() {
            if (this.valueMatcherCase_ == 9) {
                return ((Float) this.valueMatcher_).floatValue();
            }
            return 0.0f;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setGtFloat(float value) {
            this.valueMatcherCase_ = 9;
            this.valueMatcher_ = Float.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearGtFloat() {
            if (this.valueMatcherCase_ == 9) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasLteInt() {
            return this.valueMatcherCase_ == 10;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public long getLteInt() {
            if (this.valueMatcherCase_ == 10) {
                return ((Long) this.valueMatcher_).longValue();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLteInt(long value) {
            this.valueMatcherCase_ = 10;
            this.valueMatcher_ = Long.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLteInt() {
            if (this.valueMatcherCase_ == 10) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasGteInt() {
            return this.valueMatcherCase_ == 11;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public long getGteInt() {
            if (this.valueMatcherCase_ == 11) {
                return ((Long) this.valueMatcher_).longValue();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setGteInt(long value) {
            this.valueMatcherCase_ = 11;
            this.valueMatcher_ = Long.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearGteInt() {
            if (this.valueMatcherCase_ == 11) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasMatchesTuple() {
            return this.valueMatcherCase_ == 12;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public MessageMatcher getMatchesTuple() {
            if (this.valueMatcherCase_ == 12) {
                return (MessageMatcher) this.valueMatcher_;
            }
            return MessageMatcher.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMatchesTuple(MessageMatcher value) {
            if (value != null) {
                this.valueMatcher_ = value;
                this.valueMatcherCase_ = 12;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMatchesTuple(MessageMatcher.Builder builderForValue) {
            this.valueMatcher_ = builderForValue.build();
            this.valueMatcherCase_ = 12;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeMatchesTuple(MessageMatcher value) {
            if (this.valueMatcherCase_ != 12 || this.valueMatcher_ == MessageMatcher.getDefaultInstance()) {
                this.valueMatcher_ = value;
            } else {
                this.valueMatcher_ = ((MessageMatcher.Builder) MessageMatcher.newBuilder((MessageMatcher) this.valueMatcher_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.valueMatcherCase_ = 12;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMatchesTuple() {
            if (this.valueMatcherCase_ == 12) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasEqAnyString() {
            return this.valueMatcherCase_ == 13;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public StringListMatcher getEqAnyString() {
            if (this.valueMatcherCase_ == 13) {
                return (StringListMatcher) this.valueMatcher_;
            }
            return StringListMatcher.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEqAnyString(StringListMatcher value) {
            if (value != null) {
                this.valueMatcher_ = value;
                this.valueMatcherCase_ = 13;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEqAnyString(StringListMatcher.Builder builderForValue) {
            this.valueMatcher_ = builderForValue.build();
            this.valueMatcherCase_ = 13;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeEqAnyString(StringListMatcher value) {
            if (this.valueMatcherCase_ != 13 || this.valueMatcher_ == StringListMatcher.getDefaultInstance()) {
                this.valueMatcher_ = value;
            } else {
                this.valueMatcher_ = ((StringListMatcher.Builder) StringListMatcher.newBuilder((StringListMatcher) this.valueMatcher_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.valueMatcherCase_ = 13;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEqAnyString() {
            if (this.valueMatcherCase_ == 13) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public boolean hasNeqAnyString() {
            return this.valueMatcherCase_ == 14;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
        public StringListMatcher getNeqAnyString() {
            if (this.valueMatcherCase_ == 14) {
                return (StringListMatcher) this.valueMatcher_;
            }
            return StringListMatcher.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNeqAnyString(StringListMatcher value) {
            if (value != null) {
                this.valueMatcher_ = value;
                this.valueMatcherCase_ = 14;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNeqAnyString(StringListMatcher.Builder builderForValue) {
            this.valueMatcher_ = builderForValue.build();
            this.valueMatcherCase_ = 14;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeNeqAnyString(StringListMatcher value) {
            if (this.valueMatcherCase_ != 14 || this.valueMatcher_ == StringListMatcher.getDefaultInstance()) {
                this.valueMatcher_ = value;
            } else {
                this.valueMatcher_ = ((StringListMatcher.Builder) StringListMatcher.newBuilder((StringListMatcher) this.valueMatcher_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.valueMatcherCase_ = 14;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNeqAnyString() {
            if (this.valueMatcherCase_ == 14) {
                this.valueMatcherCase_ = 0;
                this.valueMatcher_ = null;
            }
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.field_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.position_);
            }
            if (this.valueMatcherCase_ == 3) {
                output.writeBool(3, ((Boolean) this.valueMatcher_).booleanValue());
            }
            if (this.valueMatcherCase_ == 4) {
                output.writeString(4, getEqString());
            }
            if (this.valueMatcherCase_ == 5) {
                output.writeInt64(5, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 6) {
                output.writeInt64(6, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 7) {
                output.writeInt64(7, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 8) {
                output.writeFloat(8, ((Float) this.valueMatcher_).floatValue());
            }
            if (this.valueMatcherCase_ == 9) {
                output.writeFloat(9, ((Float) this.valueMatcher_).floatValue());
            }
            if (this.valueMatcherCase_ == 10) {
                output.writeInt64(10, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 11) {
                output.writeInt64(11, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 12) {
                output.writeMessage(12, (MessageMatcher) this.valueMatcher_);
            }
            if (this.valueMatcherCase_ == 13) {
                output.writeMessage(13, (StringListMatcher) this.valueMatcher_);
            }
            if (this.valueMatcherCase_ == 14) {
                output.writeMessage(14, (StringListMatcher) this.valueMatcher_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.field_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.position_);
            }
            if (this.valueMatcherCase_ == 3) {
                size2 += CodedOutputStream.computeBoolSize(3, ((Boolean) this.valueMatcher_).booleanValue());
            }
            if (this.valueMatcherCase_ == 4) {
                size2 += CodedOutputStream.computeStringSize(4, getEqString());
            }
            if (this.valueMatcherCase_ == 5) {
                size2 += CodedOutputStream.computeInt64Size(5, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 6) {
                size2 += CodedOutputStream.computeInt64Size(6, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 7) {
                size2 += CodedOutputStream.computeInt64Size(7, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 8) {
                size2 += CodedOutputStream.computeFloatSize(8, ((Float) this.valueMatcher_).floatValue());
            }
            if (this.valueMatcherCase_ == 9) {
                size2 += CodedOutputStream.computeFloatSize(9, ((Float) this.valueMatcher_).floatValue());
            }
            if (this.valueMatcherCase_ == 10) {
                size2 += CodedOutputStream.computeInt64Size(10, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 11) {
                size2 += CodedOutputStream.computeInt64Size(11, ((Long) this.valueMatcher_).longValue());
            }
            if (this.valueMatcherCase_ == 12) {
                size2 += CodedOutputStream.computeMessageSize(12, (MessageMatcher) this.valueMatcher_);
            }
            if (this.valueMatcherCase_ == 13) {
                size2 += CodedOutputStream.computeMessageSize(13, (StringListMatcher) this.valueMatcher_);
            }
            if (this.valueMatcherCase_ == 14) {
                size2 += CodedOutputStream.computeMessageSize(14, (StringListMatcher) this.valueMatcher_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static FieldValueMatcher parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FieldValueMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldValueMatcher parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldValueMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldValueMatcher parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FieldValueMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldValueMatcher parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldValueMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldValueMatcher parseFrom(InputStream input) throws IOException {
            return (FieldValueMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldValueMatcher parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldValueMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldValueMatcher parseDelimitedFrom(InputStream input) throws IOException {
            return (FieldValueMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldValueMatcher parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldValueMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldValueMatcher parseFrom(CodedInputStream input) throws IOException {
            return (FieldValueMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldValueMatcher parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldValueMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FieldValueMatcher prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FieldValueMatcher, Builder> implements FieldValueMatcherOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(FieldValueMatcher.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public ValueMatcherCase getValueMatcherCase() {
                return ((FieldValueMatcher) this.instance).getValueMatcherCase();
            }

            public Builder clearValueMatcher() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearValueMatcher();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasField() {
                return ((FieldValueMatcher) this.instance).hasField();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public int getField() {
                return ((FieldValueMatcher) this.instance).getField();
            }

            public Builder setField(int value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setField(value);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearField();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasPosition() {
                return ((FieldValueMatcher) this.instance).hasPosition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public Position getPosition() {
                return ((FieldValueMatcher) this.instance).getPosition();
            }

            public Builder setPosition(Position value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setPosition(value);
                return this;
            }

            public Builder clearPosition() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearPosition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasEqBool() {
                return ((FieldValueMatcher) this.instance).hasEqBool();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean getEqBool() {
                return ((FieldValueMatcher) this.instance).getEqBool();
            }

            public Builder setEqBool(boolean value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setEqBool(value);
                return this;
            }

            public Builder clearEqBool() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearEqBool();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasEqString() {
                return ((FieldValueMatcher) this.instance).hasEqString();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public String getEqString() {
                return ((FieldValueMatcher) this.instance).getEqString();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public ByteString getEqStringBytes() {
                return ((FieldValueMatcher) this.instance).getEqStringBytes();
            }

            public Builder setEqString(String value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setEqString(value);
                return this;
            }

            public Builder clearEqString() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearEqString();
                return this;
            }

            public Builder setEqStringBytes(ByteString value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setEqStringBytes(value);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasEqInt() {
                return ((FieldValueMatcher) this.instance).hasEqInt();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public long getEqInt() {
                return ((FieldValueMatcher) this.instance).getEqInt();
            }

            public Builder setEqInt(long value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setEqInt(value);
                return this;
            }

            public Builder clearEqInt() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearEqInt();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasLtInt() {
                return ((FieldValueMatcher) this.instance).hasLtInt();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public long getLtInt() {
                return ((FieldValueMatcher) this.instance).getLtInt();
            }

            public Builder setLtInt(long value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setLtInt(value);
                return this;
            }

            public Builder clearLtInt() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearLtInt();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasGtInt() {
                return ((FieldValueMatcher) this.instance).hasGtInt();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public long getGtInt() {
                return ((FieldValueMatcher) this.instance).getGtInt();
            }

            public Builder setGtInt(long value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setGtInt(value);
                return this;
            }

            public Builder clearGtInt() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearGtInt();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasLtFloat() {
                return ((FieldValueMatcher) this.instance).hasLtFloat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public float getLtFloat() {
                return ((FieldValueMatcher) this.instance).getLtFloat();
            }

            public Builder setLtFloat(float value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setLtFloat(value);
                return this;
            }

            public Builder clearLtFloat() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearLtFloat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasGtFloat() {
                return ((FieldValueMatcher) this.instance).hasGtFloat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public float getGtFloat() {
                return ((FieldValueMatcher) this.instance).getGtFloat();
            }

            public Builder setGtFloat(float value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setGtFloat(value);
                return this;
            }

            public Builder clearGtFloat() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearGtFloat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasLteInt() {
                return ((FieldValueMatcher) this.instance).hasLteInt();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public long getLteInt() {
                return ((FieldValueMatcher) this.instance).getLteInt();
            }

            public Builder setLteInt(long value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setLteInt(value);
                return this;
            }

            public Builder clearLteInt() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearLteInt();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasGteInt() {
                return ((FieldValueMatcher) this.instance).hasGteInt();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public long getGteInt() {
                return ((FieldValueMatcher) this.instance).getGteInt();
            }

            public Builder setGteInt(long value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setGteInt(value);
                return this;
            }

            public Builder clearGteInt() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearGteInt();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasMatchesTuple() {
                return ((FieldValueMatcher) this.instance).hasMatchesTuple();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public MessageMatcher getMatchesTuple() {
                return ((FieldValueMatcher) this.instance).getMatchesTuple();
            }

            public Builder setMatchesTuple(MessageMatcher value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setMatchesTuple((FieldValueMatcher) value);
                return this;
            }

            public Builder setMatchesTuple(MessageMatcher.Builder builderForValue) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setMatchesTuple((FieldValueMatcher) builderForValue);
                return this;
            }

            public Builder mergeMatchesTuple(MessageMatcher value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).mergeMatchesTuple(value);
                return this;
            }

            public Builder clearMatchesTuple() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearMatchesTuple();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasEqAnyString() {
                return ((FieldValueMatcher) this.instance).hasEqAnyString();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public StringListMatcher getEqAnyString() {
                return ((FieldValueMatcher) this.instance).getEqAnyString();
            }

            public Builder setEqAnyString(StringListMatcher value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setEqAnyString((FieldValueMatcher) value);
                return this;
            }

            public Builder setEqAnyString(StringListMatcher.Builder builderForValue) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setEqAnyString((FieldValueMatcher) builderForValue);
                return this;
            }

            public Builder mergeEqAnyString(StringListMatcher value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).mergeEqAnyString(value);
                return this;
            }

            public Builder clearEqAnyString() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearEqAnyString();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public boolean hasNeqAnyString() {
                return ((FieldValueMatcher) this.instance).hasNeqAnyString();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldValueMatcherOrBuilder
            public StringListMatcher getNeqAnyString() {
                return ((FieldValueMatcher) this.instance).getNeqAnyString();
            }

            public Builder setNeqAnyString(StringListMatcher value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setNeqAnyString((FieldValueMatcher) value);
                return this;
            }

            public Builder setNeqAnyString(StringListMatcher.Builder builderForValue) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).setNeqAnyString((FieldValueMatcher) builderForValue);
                return this;
            }

            public Builder mergeNeqAnyString(StringListMatcher value) {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).mergeNeqAnyString(value);
                return this;
            }

            public Builder clearNeqAnyString() {
                copyOnWrite();
                ((FieldValueMatcher) this.instance).clearNeqAnyString();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            StringListMatcher.Builder subBuilder;
            int i = 5;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new FieldValueMatcher();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    FieldValueMatcher other = (FieldValueMatcher) arg1;
                    this.field_ = visitor.visitInt(hasField(), this.field_, other.hasField(), other.field_);
                    this.position_ = visitor.visitInt(hasPosition(), this.position_, other.hasPosition(), other.position_);
                    boolean z = false;
                    switch (other.getValueMatcherCase()) {
                        case EQ_BOOL:
                            if (this.valueMatcherCase_ == 3) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofBoolean(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case EQ_STRING:
                            if (this.valueMatcherCase_ == 4) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofString(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case EQ_INT:
                            if (this.valueMatcherCase_ == 5) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofLong(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case LT_INT:
                            if (this.valueMatcherCase_ == 6) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofLong(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case GT_INT:
                            if (this.valueMatcherCase_ == 7) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofLong(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case LT_FLOAT:
                            if (this.valueMatcherCase_ == 8) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofFloat(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case GT_FLOAT:
                            if (this.valueMatcherCase_ == 9) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofFloat(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case LTE_INT:
                            if (this.valueMatcherCase_ == 10) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofLong(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case GTE_INT:
                            if (this.valueMatcherCase_ == 11) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofLong(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case MATCHES_TUPLE:
                            if (this.valueMatcherCase_ == 12) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofMessage(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case EQ_ANY_STRING:
                            if (this.valueMatcherCase_ == 13) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofMessage(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case NEQ_ANY_STRING:
                            if (this.valueMatcherCase_ == 14) {
                                z = true;
                            }
                            this.valueMatcher_ = visitor.visitOneofMessage(z, this.valueMatcher_, other.valueMatcher_);
                            break;
                        case VALUEMATCHER_NOT_SET:
                            if (this.valueMatcherCase_ != 0) {
                                z = true;
                            }
                            visitor.visitOneofNotSet(z);
                            break;
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i2 = other.valueMatcherCase_;
                        if (i2 != 0) {
                            this.valueMatcherCase_ = i2;
                        }
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.field_ = input.readInt32();
                                    break;
                                case 16:
                                    int rawValue = input.readEnum();
                                    if (Position.forNumber(rawValue) != null) {
                                        this.bitField0_ |= 2;
                                        this.position_ = rawValue;
                                        break;
                                    } else {
                                        super.mergeVarintField(2, rawValue);
                                        break;
                                    }
                                case 24:
                                    this.valueMatcherCase_ = 3;
                                    this.valueMatcher_ = Boolean.valueOf(input.readBool());
                                    break;
                                case 34:
                                    String s = input.readString();
                                    this.valueMatcherCase_ = 4;
                                    this.valueMatcher_ = s;
                                    break;
                                case 40:
                                    this.valueMatcherCase_ = i;
                                    this.valueMatcher_ = Long.valueOf(input.readInt64());
                                    break;
                                case 48:
                                    this.valueMatcherCase_ = 6;
                                    this.valueMatcher_ = Long.valueOf(input.readInt64());
                                    break;
                                case 56:
                                    this.valueMatcherCase_ = 7;
                                    this.valueMatcher_ = Long.valueOf(input.readInt64());
                                    break;
                                case 69:
                                    this.valueMatcherCase_ = 8;
                                    this.valueMatcher_ = Float.valueOf(input.readFloat());
                                    break;
                                case 77:
                                    this.valueMatcherCase_ = 9;
                                    this.valueMatcher_ = Float.valueOf(input.readFloat());
                                    break;
                                case 80:
                                    this.valueMatcherCase_ = 10;
                                    this.valueMatcher_ = Long.valueOf(input.readInt64());
                                    break;
                                case 88:
                                    this.valueMatcherCase_ = 11;
                                    this.valueMatcher_ = Long.valueOf(input.readInt64());
                                    break;
                                case 98:
                                    MessageMatcher.Builder subBuilder2 = null;
                                    if (this.valueMatcherCase_ == 12) {
                                        subBuilder2 = (MessageMatcher.Builder) ((MessageMatcher) this.valueMatcher_).toBuilder();
                                    }
                                    this.valueMatcher_ = input.readMessage(MessageMatcher.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((GeneratedMessageLite) ((MessageMatcher) this.valueMatcher_));
                                        this.valueMatcher_ = subBuilder2.buildPartial();
                                    }
                                    this.valueMatcherCase_ = 12;
                                    break;
                                case 106:
                                    StringListMatcher.Builder subBuilder3 = null;
                                    if (this.valueMatcherCase_ == 13) {
                                        subBuilder3 = (StringListMatcher.Builder) ((StringListMatcher) this.valueMatcher_).toBuilder();
                                    }
                                    this.valueMatcher_ = input.readMessage(StringListMatcher.parser(), extensionRegistry);
                                    if (subBuilder3 != null) {
                                        subBuilder3.mergeFrom((GeneratedMessageLite) ((StringListMatcher) this.valueMatcher_));
                                        this.valueMatcher_ = subBuilder3.buildPartial();
                                    }
                                    this.valueMatcherCase_ = 13;
                                    break;
                                case 114:
                                    if (this.valueMatcherCase_ == 14) {
                                        subBuilder = (StringListMatcher.Builder) ((StringListMatcher) this.valueMatcher_).toBuilder();
                                    } else {
                                        subBuilder = null;
                                    }
                                    this.valueMatcher_ = input.readMessage(StringListMatcher.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) ((StringListMatcher) this.valueMatcher_));
                                        this.valueMatcher_ = subBuilder.buildPartial();
                                    }
                                    this.valueMatcherCase_ = 14;
                                    break;
                                default:
                                    if (parseUnknownField(tag, input)) {
                                        break;
                                    } else {
                                        done = true;
                                        break;
                                    }
                            }
                            i = 5;
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
                        synchronized (FieldValueMatcher.class) {
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

        public static FieldValueMatcher getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldValueMatcher> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class MessageMatcher extends GeneratedMessageLite<MessageMatcher, Builder> implements MessageMatcherOrBuilder {
        private static final MessageMatcher DEFAULT_INSTANCE = new MessageMatcher();
        public static final int FIELD_VALUE_MATCHER_FIELD_NUMBER = 1;
        private static volatile Parser<MessageMatcher> PARSER;
        private Internal.ProtobufList<FieldValueMatcher> fieldValueMatcher_ = emptyProtobufList();

        private MessageMatcher() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.MessageMatcherOrBuilder
        public List<FieldValueMatcher> getFieldValueMatcherList() {
            return this.fieldValueMatcher_;
        }

        public List<? extends FieldValueMatcherOrBuilder> getFieldValueMatcherOrBuilderList() {
            return this.fieldValueMatcher_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MessageMatcherOrBuilder
        public int getFieldValueMatcherCount() {
            return this.fieldValueMatcher_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.MessageMatcherOrBuilder
        public FieldValueMatcher getFieldValueMatcher(int index) {
            return this.fieldValueMatcher_.get(index);
        }

        public FieldValueMatcherOrBuilder getFieldValueMatcherOrBuilder(int index) {
            return this.fieldValueMatcher_.get(index);
        }

        private void ensureFieldValueMatcherIsMutable() {
            if (!this.fieldValueMatcher_.isModifiable()) {
                this.fieldValueMatcher_ = GeneratedMessageLite.mutableCopy(this.fieldValueMatcher_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFieldValueMatcher(int index, FieldValueMatcher value) {
            if (value != null) {
                ensureFieldValueMatcherIsMutable();
                this.fieldValueMatcher_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFieldValueMatcher(int index, FieldValueMatcher.Builder builderForValue) {
            ensureFieldValueMatcherIsMutable();
            this.fieldValueMatcher_.set(index, (FieldValueMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFieldValueMatcher(FieldValueMatcher value) {
            if (value != null) {
                ensureFieldValueMatcherIsMutable();
                this.fieldValueMatcher_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFieldValueMatcher(int index, FieldValueMatcher value) {
            if (value != null) {
                ensureFieldValueMatcherIsMutable();
                this.fieldValueMatcher_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFieldValueMatcher(FieldValueMatcher.Builder builderForValue) {
            ensureFieldValueMatcherIsMutable();
            this.fieldValueMatcher_.add((FieldValueMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFieldValueMatcher(int index, FieldValueMatcher.Builder builderForValue) {
            ensureFieldValueMatcherIsMutable();
            this.fieldValueMatcher_.add(index, (FieldValueMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllFieldValueMatcher(Iterable<? extends FieldValueMatcher> values) {
            ensureFieldValueMatcherIsMutable();
            AbstractMessageLite.addAll(values, this.fieldValueMatcher_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFieldValueMatcher() {
            this.fieldValueMatcher_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeFieldValueMatcher(int index) {
            ensureFieldValueMatcherIsMutable();
            this.fieldValueMatcher_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.fieldValueMatcher_.size(); i++) {
                output.writeMessage(1, this.fieldValueMatcher_.get(i));
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
            for (int i = 0; i < this.fieldValueMatcher_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.fieldValueMatcher_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static MessageMatcher parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MessageMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MessageMatcher parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MessageMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MessageMatcher parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MessageMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MessageMatcher parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MessageMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MessageMatcher parseFrom(InputStream input) throws IOException {
            return (MessageMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MessageMatcher parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MessageMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MessageMatcher parseDelimitedFrom(InputStream input) throws IOException {
            return (MessageMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MessageMatcher parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MessageMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MessageMatcher parseFrom(CodedInputStream input) throws IOException {
            return (MessageMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MessageMatcher parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MessageMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MessageMatcher prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MessageMatcher, Builder> implements MessageMatcherOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(MessageMatcher.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.MessageMatcherOrBuilder
            public List<FieldValueMatcher> getFieldValueMatcherList() {
                return Collections.unmodifiableList(((MessageMatcher) this.instance).getFieldValueMatcherList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.MessageMatcherOrBuilder
            public int getFieldValueMatcherCount() {
                return ((MessageMatcher) this.instance).getFieldValueMatcherCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.MessageMatcherOrBuilder
            public FieldValueMatcher getFieldValueMatcher(int index) {
                return ((MessageMatcher) this.instance).getFieldValueMatcher(index);
            }

            public Builder setFieldValueMatcher(int index, FieldValueMatcher value) {
                copyOnWrite();
                ((MessageMatcher) this.instance).setFieldValueMatcher((MessageMatcher) index, (int) value);
                return this;
            }

            public Builder setFieldValueMatcher(int index, FieldValueMatcher.Builder builderForValue) {
                copyOnWrite();
                ((MessageMatcher) this.instance).setFieldValueMatcher((MessageMatcher) index, (int) builderForValue);
                return this;
            }

            public Builder addFieldValueMatcher(FieldValueMatcher value) {
                copyOnWrite();
                ((MessageMatcher) this.instance).addFieldValueMatcher((MessageMatcher) value);
                return this;
            }

            public Builder addFieldValueMatcher(int index, FieldValueMatcher value) {
                copyOnWrite();
                ((MessageMatcher) this.instance).addFieldValueMatcher((MessageMatcher) index, (int) value);
                return this;
            }

            public Builder addFieldValueMatcher(FieldValueMatcher.Builder builderForValue) {
                copyOnWrite();
                ((MessageMatcher) this.instance).addFieldValueMatcher((MessageMatcher) builderForValue);
                return this;
            }

            public Builder addFieldValueMatcher(int index, FieldValueMatcher.Builder builderForValue) {
                copyOnWrite();
                ((MessageMatcher) this.instance).addFieldValueMatcher((MessageMatcher) index, (int) builderForValue);
                return this;
            }

            public Builder addAllFieldValueMatcher(Iterable<? extends FieldValueMatcher> values) {
                copyOnWrite();
                ((MessageMatcher) this.instance).addAllFieldValueMatcher(values);
                return this;
            }

            public Builder clearFieldValueMatcher() {
                copyOnWrite();
                ((MessageMatcher) this.instance).clearFieldValueMatcher();
                return this;
            }

            public Builder removeFieldValueMatcher(int index) {
                copyOnWrite();
                ((MessageMatcher) this.instance).removeFieldValueMatcher(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new MessageMatcher();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.fieldValueMatcher_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    this.fieldValueMatcher_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.fieldValueMatcher_, ((MessageMatcher) arg1).fieldValueMatcher_);
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
                                if (!this.fieldValueMatcher_.isModifiable()) {
                                    this.fieldValueMatcher_ = GeneratedMessageLite.mutableCopy(this.fieldValueMatcher_);
                                }
                                this.fieldValueMatcher_.add((FieldValueMatcher) input.readMessage(FieldValueMatcher.parser(), extensionRegistry));
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
                        synchronized (MessageMatcher.class) {
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

        public static MessageMatcher getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MessageMatcher> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class StringListMatcher extends GeneratedMessageLite<StringListMatcher, Builder> implements StringListMatcherOrBuilder {
        private static final StringListMatcher DEFAULT_INSTANCE = new StringListMatcher();
        private static volatile Parser<StringListMatcher> PARSER = null;
        public static final int STR_VALUE_FIELD_NUMBER = 1;
        private Internal.ProtobufList<String> strValue_ = GeneratedMessageLite.emptyProtobufList();

        private StringListMatcher() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.StringListMatcherOrBuilder
        public List<String> getStrValueList() {
            return this.strValue_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StringListMatcherOrBuilder
        public int getStrValueCount() {
            return this.strValue_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StringListMatcherOrBuilder
        public String getStrValue(int index) {
            return this.strValue_.get(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StringListMatcherOrBuilder
        public ByteString getStrValueBytes(int index) {
            return ByteString.copyFromUtf8(this.strValue_.get(index));
        }

        private void ensureStrValueIsMutable() {
            if (!this.strValue_.isModifiable()) {
                this.strValue_ = GeneratedMessageLite.mutableCopy(this.strValue_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStrValue(int index, String value) {
            if (value != null) {
                ensureStrValueIsMutable();
                this.strValue_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addStrValue(String value) {
            if (value != null) {
                ensureStrValueIsMutable();
                this.strValue_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllStrValue(Iterable<String> values) {
            ensureStrValueIsMutable();
            AbstractMessageLite.addAll(values, this.strValue_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStrValue() {
            this.strValue_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addStrValueBytes(ByteString value) {
            if (value != null) {
                ensureStrValueIsMutable();
                this.strValue_.add(value.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.strValue_.size(); i++) {
                output.writeString(1, this.strValue_.get(i));
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
            for (int i = 0; i < this.strValue_.size(); i++) {
                dataSize += CodedOutputStream.computeStringSizeNoTag(this.strValue_.get(i));
            }
            int size2 = 0 + dataSize + (getStrValueList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static StringListMatcher parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StringListMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StringListMatcher parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StringListMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StringListMatcher parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StringListMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StringListMatcher parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StringListMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StringListMatcher parseFrom(InputStream input) throws IOException {
            return (StringListMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StringListMatcher parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StringListMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StringListMatcher parseDelimitedFrom(InputStream input) throws IOException {
            return (StringListMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StringListMatcher parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StringListMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StringListMatcher parseFrom(CodedInputStream input) throws IOException {
            return (StringListMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StringListMatcher parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StringListMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StringListMatcher prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StringListMatcher, Builder> implements StringListMatcherOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(StringListMatcher.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.StringListMatcherOrBuilder
            public List<String> getStrValueList() {
                return Collections.unmodifiableList(((StringListMatcher) this.instance).getStrValueList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StringListMatcherOrBuilder
            public int getStrValueCount() {
                return ((StringListMatcher) this.instance).getStrValueCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StringListMatcherOrBuilder
            public String getStrValue(int index) {
                return ((StringListMatcher) this.instance).getStrValue(index);
            }

            @Override // com.android.internal.os.StatsdConfigProto.StringListMatcherOrBuilder
            public ByteString getStrValueBytes(int index) {
                return ((StringListMatcher) this.instance).getStrValueBytes(index);
            }

            public Builder setStrValue(int index, String value) {
                copyOnWrite();
                ((StringListMatcher) this.instance).setStrValue(index, value);
                return this;
            }

            public Builder addStrValue(String value) {
                copyOnWrite();
                ((StringListMatcher) this.instance).addStrValue(value);
                return this;
            }

            public Builder addAllStrValue(Iterable<String> values) {
                copyOnWrite();
                ((StringListMatcher) this.instance).addAllStrValue(values);
                return this;
            }

            public Builder clearStrValue() {
                copyOnWrite();
                ((StringListMatcher) this.instance).clearStrValue();
                return this;
            }

            public Builder addStrValueBytes(ByteString value) {
                copyOnWrite();
                ((StringListMatcher) this.instance).addStrValueBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StringListMatcher();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.strValue_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    this.strValue_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.strValue_, ((StringListMatcher) arg1).strValue_);
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
                            } else if (tag == 10) {
                                String s = input.readString();
                                if (!this.strValue_.isModifiable()) {
                                    this.strValue_ = GeneratedMessageLite.mutableCopy(this.strValue_);
                                }
                                this.strValue_.add(s);
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
                        synchronized (StringListMatcher.class) {
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

        public static StringListMatcher getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StringListMatcher> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class SimpleAtomMatcher extends GeneratedMessageLite<SimpleAtomMatcher, Builder> implements SimpleAtomMatcherOrBuilder {
        public static final int ATOM_ID_FIELD_NUMBER = 1;
        private static final SimpleAtomMatcher DEFAULT_INSTANCE = new SimpleAtomMatcher();
        public static final int FIELD_VALUE_MATCHER_FIELD_NUMBER = 2;
        private static volatile Parser<SimpleAtomMatcher> PARSER;
        private int atomId_ = 0;
        private int bitField0_;
        private Internal.ProtobufList<FieldValueMatcher> fieldValueMatcher_ = emptyProtobufList();

        private SimpleAtomMatcher() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
        public boolean hasAtomId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
        public int getAtomId() {
            return this.atomId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAtomId(int value) {
            this.bitField0_ |= 1;
            this.atomId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAtomId() {
            this.bitField0_ &= -2;
            this.atomId_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
        public List<FieldValueMatcher> getFieldValueMatcherList() {
            return this.fieldValueMatcher_;
        }

        public List<? extends FieldValueMatcherOrBuilder> getFieldValueMatcherOrBuilderList() {
            return this.fieldValueMatcher_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
        public int getFieldValueMatcherCount() {
            return this.fieldValueMatcher_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
        public FieldValueMatcher getFieldValueMatcher(int index) {
            return this.fieldValueMatcher_.get(index);
        }

        public FieldValueMatcherOrBuilder getFieldValueMatcherOrBuilder(int index) {
            return this.fieldValueMatcher_.get(index);
        }

        private void ensureFieldValueMatcherIsMutable() {
            if (!this.fieldValueMatcher_.isModifiable()) {
                this.fieldValueMatcher_ = GeneratedMessageLite.mutableCopy(this.fieldValueMatcher_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFieldValueMatcher(int index, FieldValueMatcher value) {
            if (value != null) {
                ensureFieldValueMatcherIsMutable();
                this.fieldValueMatcher_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFieldValueMatcher(int index, FieldValueMatcher.Builder builderForValue) {
            ensureFieldValueMatcherIsMutable();
            this.fieldValueMatcher_.set(index, (FieldValueMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFieldValueMatcher(FieldValueMatcher value) {
            if (value != null) {
                ensureFieldValueMatcherIsMutable();
                this.fieldValueMatcher_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFieldValueMatcher(int index, FieldValueMatcher value) {
            if (value != null) {
                ensureFieldValueMatcherIsMutable();
                this.fieldValueMatcher_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFieldValueMatcher(FieldValueMatcher.Builder builderForValue) {
            ensureFieldValueMatcherIsMutable();
            this.fieldValueMatcher_.add((FieldValueMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFieldValueMatcher(int index, FieldValueMatcher.Builder builderForValue) {
            ensureFieldValueMatcherIsMutable();
            this.fieldValueMatcher_.add(index, (FieldValueMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllFieldValueMatcher(Iterable<? extends FieldValueMatcher> values) {
            ensureFieldValueMatcherIsMutable();
            AbstractMessageLite.addAll(values, this.fieldValueMatcher_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFieldValueMatcher() {
            this.fieldValueMatcher_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeFieldValueMatcher(int index) {
            ensureFieldValueMatcherIsMutable();
            this.fieldValueMatcher_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.atomId_);
            }
            for (int i = 0; i < this.fieldValueMatcher_.size(); i++) {
                output.writeMessage(2, this.fieldValueMatcher_.get(i));
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.atomId_);
            }
            for (int i = 0; i < this.fieldValueMatcher_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.fieldValueMatcher_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static SimpleAtomMatcher parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SimpleAtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SimpleAtomMatcher parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SimpleAtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SimpleAtomMatcher parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SimpleAtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SimpleAtomMatcher parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SimpleAtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SimpleAtomMatcher parseFrom(InputStream input) throws IOException {
            return (SimpleAtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SimpleAtomMatcher parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SimpleAtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SimpleAtomMatcher parseDelimitedFrom(InputStream input) throws IOException {
            return (SimpleAtomMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SimpleAtomMatcher parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SimpleAtomMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SimpleAtomMatcher parseFrom(CodedInputStream input) throws IOException {
            return (SimpleAtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SimpleAtomMatcher parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SimpleAtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SimpleAtomMatcher prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SimpleAtomMatcher, Builder> implements SimpleAtomMatcherOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(SimpleAtomMatcher.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
            public boolean hasAtomId() {
                return ((SimpleAtomMatcher) this.instance).hasAtomId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
            public int getAtomId() {
                return ((SimpleAtomMatcher) this.instance).getAtomId();
            }

            public Builder setAtomId(int value) {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).setAtomId(value);
                return this;
            }

            public Builder clearAtomId() {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).clearAtomId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
            public List<FieldValueMatcher> getFieldValueMatcherList() {
                return Collections.unmodifiableList(((SimpleAtomMatcher) this.instance).getFieldValueMatcherList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
            public int getFieldValueMatcherCount() {
                return ((SimpleAtomMatcher) this.instance).getFieldValueMatcherCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimpleAtomMatcherOrBuilder
            public FieldValueMatcher getFieldValueMatcher(int index) {
                return ((SimpleAtomMatcher) this.instance).getFieldValueMatcher(index);
            }

            public Builder setFieldValueMatcher(int index, FieldValueMatcher value) {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).setFieldValueMatcher((SimpleAtomMatcher) index, (int) value);
                return this;
            }

            public Builder setFieldValueMatcher(int index, FieldValueMatcher.Builder builderForValue) {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).setFieldValueMatcher((SimpleAtomMatcher) index, (int) builderForValue);
                return this;
            }

            public Builder addFieldValueMatcher(FieldValueMatcher value) {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).addFieldValueMatcher((SimpleAtomMatcher) value);
                return this;
            }

            public Builder addFieldValueMatcher(int index, FieldValueMatcher value) {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).addFieldValueMatcher((SimpleAtomMatcher) index, (int) value);
                return this;
            }

            public Builder addFieldValueMatcher(FieldValueMatcher.Builder builderForValue) {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).addFieldValueMatcher((SimpleAtomMatcher) builderForValue);
                return this;
            }

            public Builder addFieldValueMatcher(int index, FieldValueMatcher.Builder builderForValue) {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).addFieldValueMatcher((SimpleAtomMatcher) index, (int) builderForValue);
                return this;
            }

            public Builder addAllFieldValueMatcher(Iterable<? extends FieldValueMatcher> values) {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).addAllFieldValueMatcher(values);
                return this;
            }

            public Builder clearFieldValueMatcher() {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).clearFieldValueMatcher();
                return this;
            }

            public Builder removeFieldValueMatcher(int index) {
                copyOnWrite();
                ((SimpleAtomMatcher) this.instance).removeFieldValueMatcher(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new SimpleAtomMatcher();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.fieldValueMatcher_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    SimpleAtomMatcher other = (SimpleAtomMatcher) arg1;
                    this.atomId_ = visitor.visitInt(hasAtomId(), this.atomId_, other.hasAtomId(), other.atomId_);
                    this.fieldValueMatcher_ = visitor.visitList(this.fieldValueMatcher_, other.fieldValueMatcher_);
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
                                this.bitField0_ |= 1;
                                this.atomId_ = input.readInt32();
                            } else if (tag == 18) {
                                if (!this.fieldValueMatcher_.isModifiable()) {
                                    this.fieldValueMatcher_ = GeneratedMessageLite.mutableCopy(this.fieldValueMatcher_);
                                }
                                this.fieldValueMatcher_.add((FieldValueMatcher) input.readMessage(FieldValueMatcher.parser(), extensionRegistry));
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
                        synchronized (SimpleAtomMatcher.class) {
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

        public static SimpleAtomMatcher getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SimpleAtomMatcher> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AtomMatcher extends GeneratedMessageLite<AtomMatcher, Builder> implements AtomMatcherOrBuilder {
        public static final int COMBINATION_FIELD_NUMBER = 3;
        private static final AtomMatcher DEFAULT_INSTANCE = new AtomMatcher();
        public static final int ID_FIELD_NUMBER = 1;
        private static volatile Parser<AtomMatcher> PARSER = null;
        public static final int SIMPLE_ATOM_MATCHER_FIELD_NUMBER = 2;
        private int bitField0_;
        private int contentsCase_ = 0;
        private Object contents_;
        private long id_ = 0;

        public interface CombinationOrBuilder extends MessageLiteOrBuilder {
            long getMatcher(int i);

            int getMatcherCount();

            List<Long> getMatcherList();

            LogicalOperation getOperation();

            boolean hasOperation();
        }

        private AtomMatcher() {
        }

        public static final class Combination extends GeneratedMessageLite<Combination, Builder> implements CombinationOrBuilder {
            private static final Combination DEFAULT_INSTANCE = new Combination();
            public static final int MATCHER_FIELD_NUMBER = 2;
            public static final int OPERATION_FIELD_NUMBER = 1;
            private static volatile Parser<Combination> PARSER;
            private int bitField0_;
            private Internal.LongList matcher_ = emptyLongList();
            private int operation_ = 0;

            private Combination() {
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
            public boolean hasOperation() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
            public LogicalOperation getOperation() {
                LogicalOperation result = LogicalOperation.forNumber(this.operation_);
                return result == null ? LogicalOperation.LOGICAL_OPERATION_UNSPECIFIED : result;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setOperation(LogicalOperation value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.operation_ = value.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearOperation() {
                this.bitField0_ &= -2;
                this.operation_ = 0;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
            public List<Long> getMatcherList() {
                return this.matcher_;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
            public int getMatcherCount() {
                return this.matcher_.size();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
            public long getMatcher(int index) {
                return this.matcher_.getLong(index);
            }

            private void ensureMatcherIsMutable() {
                if (!this.matcher_.isModifiable()) {
                    this.matcher_ = GeneratedMessageLite.mutableCopy(this.matcher_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setMatcher(int index, long value) {
                ensureMatcherIsMutable();
                this.matcher_.setLong(index, value);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addMatcher(long value) {
                ensureMatcherIsMutable();
                this.matcher_.addLong(value);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllMatcher(Iterable<? extends Long> values) {
                ensureMatcherIsMutable();
                AbstractMessageLite.addAll(values, this.matcher_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearMatcher() {
                this.matcher_ = emptyLongList();
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeEnum(1, this.operation_);
                }
                for (int i = 0; i < this.matcher_.size(); i++) {
                    output.writeInt64(2, this.matcher_.getLong(i));
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
                    size2 = 0 + CodedOutputStream.computeEnumSize(1, this.operation_);
                }
                int dataSize = 0;
                for (int i = 0; i < this.matcher_.size(); i++) {
                    dataSize += CodedOutputStream.computeInt64SizeNoTag(this.matcher_.getLong(i));
                }
                int size3 = size2 + dataSize + (getMatcherList().size() * 1) + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Combination parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Combination parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Combination parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Combination parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Combination parseFrom(InputStream input) throws IOException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Combination parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Combination parseDelimitedFrom(InputStream input) throws IOException {
                return (Combination) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Combination parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Combination) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Combination parseFrom(CodedInputStream input) throws IOException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Combination parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Combination prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Combination, Builder> implements CombinationOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(Combination.DEFAULT_INSTANCE);
                }

                @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
                public boolean hasOperation() {
                    return ((Combination) this.instance).hasOperation();
                }

                @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
                public LogicalOperation getOperation() {
                    return ((Combination) this.instance).getOperation();
                }

                public Builder setOperation(LogicalOperation value) {
                    copyOnWrite();
                    ((Combination) this.instance).setOperation(value);
                    return this;
                }

                public Builder clearOperation() {
                    copyOnWrite();
                    ((Combination) this.instance).clearOperation();
                    return this;
                }

                @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
                public List<Long> getMatcherList() {
                    return Collections.unmodifiableList(((Combination) this.instance).getMatcherList());
                }

                @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
                public int getMatcherCount() {
                    return ((Combination) this.instance).getMatcherCount();
                }

                @Override // com.android.internal.os.StatsdConfigProto.AtomMatcher.CombinationOrBuilder
                public long getMatcher(int index) {
                    return ((Combination) this.instance).getMatcher(index);
                }

                public Builder setMatcher(int index, long value) {
                    copyOnWrite();
                    ((Combination) this.instance).setMatcher(index, value);
                    return this;
                }

                public Builder addMatcher(long value) {
                    copyOnWrite();
                    ((Combination) this.instance).addMatcher(value);
                    return this;
                }

                public Builder addAllMatcher(Iterable<? extends Long> values) {
                    copyOnWrite();
                    ((Combination) this.instance).addAllMatcher(values);
                    return this;
                }

                public Builder clearMatcher() {
                    copyOnWrite();
                    ((Combination) this.instance).clearMatcher();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Combination();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        this.matcher_.makeImmutable();
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Combination other = (Combination) arg1;
                        this.operation_ = visitor.visitInt(hasOperation(), this.operation_, other.hasOperation(), other.operation_);
                        this.matcher_ = visitor.visitLongList(this.matcher_, other.matcher_);
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
                                    int rawValue = input.readEnum();
                                    if (LogicalOperation.forNumber(rawValue) == null) {
                                        super.mergeVarintField(1, rawValue);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.operation_ = rawValue;
                                    }
                                } else if (tag == 16) {
                                    if (!this.matcher_.isModifiable()) {
                                        this.matcher_ = GeneratedMessageLite.mutableCopy(this.matcher_);
                                    }
                                    this.matcher_.addLong(input.readInt64());
                                } else if (tag == 18) {
                                    int limit = input.pushLimit(input.readRawVarint32());
                                    if (!this.matcher_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                        this.matcher_ = GeneratedMessageLite.mutableCopy(this.matcher_);
                                    }
                                    while (input.getBytesUntilLimit() > 0) {
                                        this.matcher_.addLong(input.readInt64());
                                    }
                                    input.popLimit(limit);
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
                            synchronized (Combination.class) {
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

            public static Combination getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Combination> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public enum ContentsCase implements Internal.EnumLite {
            SIMPLE_ATOM_MATCHER(2),
            COMBINATION(3),
            CONTENTS_NOT_SET(0);
            
            private final int value;

            private ContentsCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static ContentsCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static ContentsCase forNumber(int value2) {
                if (value2 == 0) {
                    return CONTENTS_NOT_SET;
                }
                if (value2 == 2) {
                    return SIMPLE_ATOM_MATCHER;
                }
                if (value2 != 3) {
                    return null;
                }
                return COMBINATION;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
        public ContentsCase getContentsCase() {
            return ContentsCase.forNumber(this.contentsCase_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearContents() {
            this.contentsCase_ = 0;
            this.contents_ = null;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
        public boolean hasSimpleAtomMatcher() {
            return this.contentsCase_ == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
        public SimpleAtomMatcher getSimpleAtomMatcher() {
            if (this.contentsCase_ == 2) {
                return (SimpleAtomMatcher) this.contents_;
            }
            return SimpleAtomMatcher.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSimpleAtomMatcher(SimpleAtomMatcher value) {
            if (value != null) {
                this.contents_ = value;
                this.contentsCase_ = 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSimpleAtomMatcher(SimpleAtomMatcher.Builder builderForValue) {
            this.contents_ = builderForValue.build();
            this.contentsCase_ = 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeSimpleAtomMatcher(SimpleAtomMatcher value) {
            if (this.contentsCase_ != 2 || this.contents_ == SimpleAtomMatcher.getDefaultInstance()) {
                this.contents_ = value;
            } else {
                this.contents_ = ((SimpleAtomMatcher.Builder) SimpleAtomMatcher.newBuilder((SimpleAtomMatcher) this.contents_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.contentsCase_ = 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSimpleAtomMatcher() {
            if (this.contentsCase_ == 2) {
                this.contentsCase_ = 0;
                this.contents_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
        public boolean hasCombination() {
            return this.contentsCase_ == 3;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
        public Combination getCombination() {
            if (this.contentsCase_ == 3) {
                return (Combination) this.contents_;
            }
            return Combination.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCombination(Combination value) {
            if (value != null) {
                this.contents_ = value;
                this.contentsCase_ = 3;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCombination(Combination.Builder builderForValue) {
            this.contents_ = builderForValue.build();
            this.contentsCase_ = 3;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeCombination(Combination value) {
            if (this.contentsCase_ != 3 || this.contents_ == Combination.getDefaultInstance()) {
                this.contents_ = value;
            } else {
                this.contents_ = ((Combination.Builder) Combination.newBuilder((Combination) this.contents_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.contentsCase_ = 3;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCombination() {
            if (this.contentsCase_ == 3) {
                this.contentsCase_ = 0;
                this.contents_ = null;
            }
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if (this.contentsCase_ == 2) {
                output.writeMessage(2, (SimpleAtomMatcher) this.contents_);
            }
            if (this.contentsCase_ == 3) {
                output.writeMessage(3, (Combination) this.contents_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if (this.contentsCase_ == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, (SimpleAtomMatcher) this.contents_);
            }
            if (this.contentsCase_ == 3) {
                size2 += CodedOutputStream.computeMessageSize(3, (Combination) this.contents_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static AtomMatcher parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AtomMatcher parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AtomMatcher parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AtomMatcher parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AtomMatcher parseFrom(InputStream input) throws IOException {
            return (AtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AtomMatcher parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AtomMatcher parseDelimitedFrom(InputStream input) throws IOException {
            return (AtomMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AtomMatcher parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AtomMatcher) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AtomMatcher parseFrom(CodedInputStream input) throws IOException {
            return (AtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AtomMatcher parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AtomMatcher) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AtomMatcher prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AtomMatcher, Builder> implements AtomMatcherOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(AtomMatcher.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
            public ContentsCase getContentsCase() {
                return ((AtomMatcher) this.instance).getContentsCase();
            }

            public Builder clearContents() {
                copyOnWrite();
                ((AtomMatcher) this.instance).clearContents();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
            public boolean hasId() {
                return ((AtomMatcher) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
            public long getId() {
                return ((AtomMatcher) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((AtomMatcher) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((AtomMatcher) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
            public boolean hasSimpleAtomMatcher() {
                return ((AtomMatcher) this.instance).hasSimpleAtomMatcher();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
            public SimpleAtomMatcher getSimpleAtomMatcher() {
                return ((AtomMatcher) this.instance).getSimpleAtomMatcher();
            }

            public Builder setSimpleAtomMatcher(SimpleAtomMatcher value) {
                copyOnWrite();
                ((AtomMatcher) this.instance).setSimpleAtomMatcher((AtomMatcher) value);
                return this;
            }

            public Builder setSimpleAtomMatcher(SimpleAtomMatcher.Builder builderForValue) {
                copyOnWrite();
                ((AtomMatcher) this.instance).setSimpleAtomMatcher((AtomMatcher) builderForValue);
                return this;
            }

            public Builder mergeSimpleAtomMatcher(SimpleAtomMatcher value) {
                copyOnWrite();
                ((AtomMatcher) this.instance).mergeSimpleAtomMatcher(value);
                return this;
            }

            public Builder clearSimpleAtomMatcher() {
                copyOnWrite();
                ((AtomMatcher) this.instance).clearSimpleAtomMatcher();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
            public boolean hasCombination() {
                return ((AtomMatcher) this.instance).hasCombination();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AtomMatcherOrBuilder
            public Combination getCombination() {
                return ((AtomMatcher) this.instance).getCombination();
            }

            public Builder setCombination(Combination value) {
                copyOnWrite();
                ((AtomMatcher) this.instance).setCombination((AtomMatcher) value);
                return this;
            }

            public Builder setCombination(Combination.Builder builderForValue) {
                copyOnWrite();
                ((AtomMatcher) this.instance).setCombination((AtomMatcher) builderForValue);
                return this;
            }

            public Builder mergeCombination(Combination value) {
                copyOnWrite();
                ((AtomMatcher) this.instance).mergeCombination(value);
                return this;
            }

            public Builder clearCombination() {
                copyOnWrite();
                ((AtomMatcher) this.instance).clearCombination();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean z = true;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new AtomMatcher();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    AtomMatcher other = (AtomMatcher) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    int i = AnonymousClass1.$SwitchMap$com$android$internal$os$StatsdConfigProto$AtomMatcher$ContentsCase[other.getContentsCase().ordinal()];
                    if (i == 1) {
                        if (this.contentsCase_ != 2) {
                            z = false;
                        }
                        this.contents_ = visitor.visitOneofMessage(z, this.contents_, other.contents_);
                    } else if (i == 2) {
                        if (this.contentsCase_ != 3) {
                            z = false;
                        }
                        this.contents_ = visitor.visitOneofMessage(z, this.contents_, other.contents_);
                    } else if (i == 3) {
                        if (this.contentsCase_ == 0) {
                            z = false;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i2 = other.contentsCase_;
                        if (i2 != 0) {
                            this.contentsCase_ = i2;
                        }
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
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt64();
                            } else if (tag == 18) {
                                SimpleAtomMatcher.Builder subBuilder = null;
                                if (this.contentsCase_ == 2) {
                                    subBuilder = (SimpleAtomMatcher.Builder) ((SimpleAtomMatcher) this.contents_).toBuilder();
                                }
                                this.contents_ = input.readMessage(SimpleAtomMatcher.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) ((SimpleAtomMatcher) this.contents_));
                                    this.contents_ = subBuilder.buildPartial();
                                }
                                this.contentsCase_ = 2;
                            } else if (tag == 26) {
                                Combination.Builder subBuilder2 = null;
                                if (this.contentsCase_ == 3) {
                                    subBuilder2 = (Combination.Builder) ((Combination) this.contents_).toBuilder();
                                }
                                this.contents_ = input.readMessage(Combination.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) ((Combination) this.contents_));
                                    this.contents_ = subBuilder2.buildPartial();
                                }
                                this.contentsCase_ = 3;
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
                        synchronized (AtomMatcher.class) {
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

        public static AtomMatcher getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AtomMatcher> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class SimplePredicate extends GeneratedMessageLite<SimplePredicate, Builder> implements SimplePredicateOrBuilder {
        public static final int COUNT_NESTING_FIELD_NUMBER = 3;
        private static final SimplePredicate DEFAULT_INSTANCE = new SimplePredicate();
        public static final int DIMENSIONS_FIELD_NUMBER = 6;
        public static final int INITIAL_VALUE_FIELD_NUMBER = 5;
        private static volatile Parser<SimplePredicate> PARSER = null;
        public static final int START_FIELD_NUMBER = 1;
        public static final int STOP_ALL_FIELD_NUMBER = 4;
        public static final int STOP_FIELD_NUMBER = 2;
        private int bitField0_;
        private boolean countNesting_ = true;
        private FieldMatcher dimensions_;
        private int initialValue_ = 1;
        private long start_ = 0;
        private long stopAll_ = 0;
        private long stop_ = 0;

        private SimplePredicate() {
        }

        public enum InitialValue implements Internal.EnumLite {
            UNKNOWN(0),
            FALSE(1);
            
            public static final int FALSE_VALUE = 1;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<InitialValue> internalValueMap = new Internal.EnumLiteMap<InitialValue>() {
                /* class com.android.internal.os.StatsdConfigProto.SimplePredicate.InitialValue.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public InitialValue findValueByNumber(int number) {
                    return InitialValue.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static InitialValue valueOf(int value2) {
                return forNumber(value2);
            }

            public static InitialValue forNumber(int value2) {
                if (value2 == 0) {
                    return UNKNOWN;
                }
                if (value2 != 1) {
                    return null;
                }
                return FALSE;
            }

            public static Internal.EnumLiteMap<InitialValue> internalGetValueMap() {
                return internalValueMap;
            }

            private InitialValue(int value2) {
                this.value = value2;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public boolean hasStart() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public long getStart() {
            return this.start_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStart(long value) {
            this.bitField0_ |= 1;
            this.start_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStart() {
            this.bitField0_ &= -2;
            this.start_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public boolean hasStop() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public long getStop() {
            return this.stop_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStop(long value) {
            this.bitField0_ |= 2;
            this.stop_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStop() {
            this.bitField0_ &= -3;
            this.stop_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public boolean hasCountNesting() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public boolean getCountNesting() {
            return this.countNesting_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCountNesting(boolean value) {
            this.bitField0_ |= 4;
            this.countNesting_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCountNesting() {
            this.bitField0_ &= -5;
            this.countNesting_ = true;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public boolean hasStopAll() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public long getStopAll() {
            return this.stopAll_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStopAll(long value) {
            this.bitField0_ |= 8;
            this.stopAll_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStopAll() {
            this.bitField0_ &= -9;
            this.stopAll_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public boolean hasInitialValue() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public InitialValue getInitialValue() {
            InitialValue result = InitialValue.forNumber(this.initialValue_);
            return result == null ? InitialValue.FALSE : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInitialValue(InitialValue value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.initialValue_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInitialValue() {
            this.bitField0_ &= -17;
            this.initialValue_ = 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public boolean hasDimensions() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
        public FieldMatcher getDimensions() {
            FieldMatcher fieldMatcher = this.dimensions_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensions(FieldMatcher value) {
            if (value != null) {
                this.dimensions_ = value;
                this.bitField0_ |= 32;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensions(FieldMatcher.Builder builderForValue) {
            this.dimensions_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDimensions(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.dimensions_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.dimensions_ = value;
            } else {
                this.dimensions_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.dimensions_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDimensions() {
            this.dimensions_ = null;
            this.bitField0_ &= -33;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.start_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.stop_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.countNesting_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.stopAll_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeEnum(5, this.initialValue_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(6, getDimensions());
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.start_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.stop_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.countNesting_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.stopAll_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeEnumSize(5, this.initialValue_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(6, getDimensions());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static SimplePredicate parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SimplePredicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SimplePredicate parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SimplePredicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SimplePredicate parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SimplePredicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SimplePredicate parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SimplePredicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SimplePredicate parseFrom(InputStream input) throws IOException {
            return (SimplePredicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SimplePredicate parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SimplePredicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SimplePredicate parseDelimitedFrom(InputStream input) throws IOException {
            return (SimplePredicate) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SimplePredicate parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SimplePredicate) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SimplePredicate parseFrom(CodedInputStream input) throws IOException {
            return (SimplePredicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SimplePredicate parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SimplePredicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SimplePredicate prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SimplePredicate, Builder> implements SimplePredicateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(SimplePredicate.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public boolean hasStart() {
                return ((SimplePredicate) this.instance).hasStart();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public long getStart() {
                return ((SimplePredicate) this.instance).getStart();
            }

            public Builder setStart(long value) {
                copyOnWrite();
                ((SimplePredicate) this.instance).setStart(value);
                return this;
            }

            public Builder clearStart() {
                copyOnWrite();
                ((SimplePredicate) this.instance).clearStart();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public boolean hasStop() {
                return ((SimplePredicate) this.instance).hasStop();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public long getStop() {
                return ((SimplePredicate) this.instance).getStop();
            }

            public Builder setStop(long value) {
                copyOnWrite();
                ((SimplePredicate) this.instance).setStop(value);
                return this;
            }

            public Builder clearStop() {
                copyOnWrite();
                ((SimplePredicate) this.instance).clearStop();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public boolean hasCountNesting() {
                return ((SimplePredicate) this.instance).hasCountNesting();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public boolean getCountNesting() {
                return ((SimplePredicate) this.instance).getCountNesting();
            }

            public Builder setCountNesting(boolean value) {
                copyOnWrite();
                ((SimplePredicate) this.instance).setCountNesting(value);
                return this;
            }

            public Builder clearCountNesting() {
                copyOnWrite();
                ((SimplePredicate) this.instance).clearCountNesting();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public boolean hasStopAll() {
                return ((SimplePredicate) this.instance).hasStopAll();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public long getStopAll() {
                return ((SimplePredicate) this.instance).getStopAll();
            }

            public Builder setStopAll(long value) {
                copyOnWrite();
                ((SimplePredicate) this.instance).setStopAll(value);
                return this;
            }

            public Builder clearStopAll() {
                copyOnWrite();
                ((SimplePredicate) this.instance).clearStopAll();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public boolean hasInitialValue() {
                return ((SimplePredicate) this.instance).hasInitialValue();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public InitialValue getInitialValue() {
                return ((SimplePredicate) this.instance).getInitialValue();
            }

            public Builder setInitialValue(InitialValue value) {
                copyOnWrite();
                ((SimplePredicate) this.instance).setInitialValue(value);
                return this;
            }

            public Builder clearInitialValue() {
                copyOnWrite();
                ((SimplePredicate) this.instance).clearInitialValue();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public boolean hasDimensions() {
                return ((SimplePredicate) this.instance).hasDimensions();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SimplePredicateOrBuilder
            public FieldMatcher getDimensions() {
                return ((SimplePredicate) this.instance).getDimensions();
            }

            public Builder setDimensions(FieldMatcher value) {
                copyOnWrite();
                ((SimplePredicate) this.instance).setDimensions((SimplePredicate) value);
                return this;
            }

            public Builder setDimensions(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((SimplePredicate) this.instance).setDimensions((SimplePredicate) builderForValue);
                return this;
            }

            public Builder mergeDimensions(FieldMatcher value) {
                copyOnWrite();
                ((SimplePredicate) this.instance).mergeDimensions(value);
                return this;
            }

            public Builder clearDimensions() {
                copyOnWrite();
                ((SimplePredicate) this.instance).clearDimensions();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new SimplePredicate();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    SimplePredicate other = (SimplePredicate) arg1;
                    this.start_ = visitor.visitLong(hasStart(), this.start_, other.hasStart(), other.start_);
                    this.stop_ = visitor.visitLong(hasStop(), this.stop_, other.hasStop(), other.stop_);
                    this.countNesting_ = visitor.visitBoolean(hasCountNesting(), this.countNesting_, other.hasCountNesting(), other.countNesting_);
                    this.stopAll_ = visitor.visitLong(hasStopAll(), this.stopAll_, other.hasStopAll(), other.stopAll_);
                    this.initialValue_ = visitor.visitInt(hasInitialValue(), this.initialValue_, other.hasInitialValue(), other.initialValue_);
                    this.dimensions_ = (FieldMatcher) visitor.visitMessage(this.dimensions_, other.dimensions_);
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
                                this.bitField0_ |= 1;
                                this.start_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.stop_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.countNesting_ = input.readBool();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.stopAll_ = input.readInt64();
                            } else if (tag == 40) {
                                int rawValue = input.readEnum();
                                if (InitialValue.forNumber(rawValue) == null) {
                                    super.mergeVarintField(5, rawValue);
                                } else {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.initialValue_ = rawValue;
                                }
                            } else if (tag == 50) {
                                FieldMatcher.Builder subBuilder = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder = (FieldMatcher.Builder) this.dimensions_.toBuilder();
                                }
                                this.dimensions_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.dimensions_);
                                    this.dimensions_ = (FieldMatcher) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 32;
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
                        synchronized (SimplePredicate.class) {
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

        public static SimplePredicate getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SimplePredicate> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Predicate extends GeneratedMessageLite<Predicate, Builder> implements PredicateOrBuilder {
        public static final int COMBINATION_FIELD_NUMBER = 3;
        private static final Predicate DEFAULT_INSTANCE = new Predicate();
        public static final int ID_FIELD_NUMBER = 1;
        private static volatile Parser<Predicate> PARSER = null;
        public static final int SIMPLE_PREDICATE_FIELD_NUMBER = 2;
        private int bitField0_;
        private int contentsCase_ = 0;
        private Object contents_;
        private long id_ = 0;

        public interface CombinationOrBuilder extends MessageLiteOrBuilder {
            LogicalOperation getOperation();

            long getPredicate(int i);

            int getPredicateCount();

            List<Long> getPredicateList();

            boolean hasOperation();
        }

        private Predicate() {
        }

        public static final class Combination extends GeneratedMessageLite<Combination, Builder> implements CombinationOrBuilder {
            private static final Combination DEFAULT_INSTANCE = new Combination();
            public static final int OPERATION_FIELD_NUMBER = 1;
            private static volatile Parser<Combination> PARSER = null;
            public static final int PREDICATE_FIELD_NUMBER = 2;
            private int bitField0_;
            private int operation_ = 0;
            private Internal.LongList predicate_ = emptyLongList();

            private Combination() {
            }

            @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
            public boolean hasOperation() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
            public LogicalOperation getOperation() {
                LogicalOperation result = LogicalOperation.forNumber(this.operation_);
                return result == null ? LogicalOperation.LOGICAL_OPERATION_UNSPECIFIED : result;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setOperation(LogicalOperation value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.operation_ = value.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearOperation() {
                this.bitField0_ &= -2;
                this.operation_ = 0;
            }

            @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
            public List<Long> getPredicateList() {
                return this.predicate_;
            }

            @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
            public int getPredicateCount() {
                return this.predicate_.size();
            }

            @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
            public long getPredicate(int index) {
                return this.predicate_.getLong(index);
            }

            private void ensurePredicateIsMutable() {
                if (!this.predicate_.isModifiable()) {
                    this.predicate_ = GeneratedMessageLite.mutableCopy(this.predicate_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPredicate(int index, long value) {
                ensurePredicateIsMutable();
                this.predicate_.setLong(index, value);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addPredicate(long value) {
                ensurePredicateIsMutable();
                this.predicate_.addLong(value);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllPredicate(Iterable<? extends Long> values) {
                ensurePredicateIsMutable();
                AbstractMessageLite.addAll(values, this.predicate_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPredicate() {
                this.predicate_ = emptyLongList();
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeEnum(1, this.operation_);
                }
                for (int i = 0; i < this.predicate_.size(); i++) {
                    output.writeInt64(2, this.predicate_.getLong(i));
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
                    size2 = 0 + CodedOutputStream.computeEnumSize(1, this.operation_);
                }
                int dataSize = 0;
                for (int i = 0; i < this.predicate_.size(); i++) {
                    dataSize += CodedOutputStream.computeInt64SizeNoTag(this.predicate_.getLong(i));
                }
                int size3 = size2 + dataSize + (getPredicateList().size() * 1) + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Combination parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Combination parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Combination parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Combination parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Combination parseFrom(InputStream input) throws IOException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Combination parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Combination parseDelimitedFrom(InputStream input) throws IOException {
                return (Combination) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Combination parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Combination) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Combination parseFrom(CodedInputStream input) throws IOException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Combination parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Combination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Combination prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Combination, Builder> implements CombinationOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(Combination.DEFAULT_INSTANCE);
                }

                @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
                public boolean hasOperation() {
                    return ((Combination) this.instance).hasOperation();
                }

                @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
                public LogicalOperation getOperation() {
                    return ((Combination) this.instance).getOperation();
                }

                public Builder setOperation(LogicalOperation value) {
                    copyOnWrite();
                    ((Combination) this.instance).setOperation(value);
                    return this;
                }

                public Builder clearOperation() {
                    copyOnWrite();
                    ((Combination) this.instance).clearOperation();
                    return this;
                }

                @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
                public List<Long> getPredicateList() {
                    return Collections.unmodifiableList(((Combination) this.instance).getPredicateList());
                }

                @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
                public int getPredicateCount() {
                    return ((Combination) this.instance).getPredicateCount();
                }

                @Override // com.android.internal.os.StatsdConfigProto.Predicate.CombinationOrBuilder
                public long getPredicate(int index) {
                    return ((Combination) this.instance).getPredicate(index);
                }

                public Builder setPredicate(int index, long value) {
                    copyOnWrite();
                    ((Combination) this.instance).setPredicate(index, value);
                    return this;
                }

                public Builder addPredicate(long value) {
                    copyOnWrite();
                    ((Combination) this.instance).addPredicate(value);
                    return this;
                }

                public Builder addAllPredicate(Iterable<? extends Long> values) {
                    copyOnWrite();
                    ((Combination) this.instance).addAllPredicate(values);
                    return this;
                }

                public Builder clearPredicate() {
                    copyOnWrite();
                    ((Combination) this.instance).clearPredicate();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Combination();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        this.predicate_.makeImmutable();
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Combination other = (Combination) arg1;
                        this.operation_ = visitor.visitInt(hasOperation(), this.operation_, other.hasOperation(), other.operation_);
                        this.predicate_ = visitor.visitLongList(this.predicate_, other.predicate_);
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
                                    int rawValue = input.readEnum();
                                    if (LogicalOperation.forNumber(rawValue) == null) {
                                        super.mergeVarintField(1, rawValue);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.operation_ = rawValue;
                                    }
                                } else if (tag == 16) {
                                    if (!this.predicate_.isModifiable()) {
                                        this.predicate_ = GeneratedMessageLite.mutableCopy(this.predicate_);
                                    }
                                    this.predicate_.addLong(input.readInt64());
                                } else if (tag == 18) {
                                    int limit = input.pushLimit(input.readRawVarint32());
                                    if (!this.predicate_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                        this.predicate_ = GeneratedMessageLite.mutableCopy(this.predicate_);
                                    }
                                    while (input.getBytesUntilLimit() > 0) {
                                        this.predicate_.addLong(input.readInt64());
                                    }
                                    input.popLimit(limit);
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
                            synchronized (Combination.class) {
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

            public static Combination getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Combination> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public enum ContentsCase implements Internal.EnumLite {
            SIMPLE_PREDICATE(2),
            COMBINATION(3),
            CONTENTS_NOT_SET(0);
            
            private final int value;

            private ContentsCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static ContentsCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static ContentsCase forNumber(int value2) {
                if (value2 == 0) {
                    return CONTENTS_NOT_SET;
                }
                if (value2 == 2) {
                    return SIMPLE_PREDICATE;
                }
                if (value2 != 3) {
                    return null;
                }
                return COMBINATION;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
        public ContentsCase getContentsCase() {
            return ContentsCase.forNumber(this.contentsCase_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearContents() {
            this.contentsCase_ = 0;
            this.contents_ = null;
        }

        @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
        public boolean hasSimplePredicate() {
            return this.contentsCase_ == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
        public SimplePredicate getSimplePredicate() {
            if (this.contentsCase_ == 2) {
                return (SimplePredicate) this.contents_;
            }
            return SimplePredicate.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSimplePredicate(SimplePredicate value) {
            if (value != null) {
                this.contents_ = value;
                this.contentsCase_ = 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSimplePredicate(SimplePredicate.Builder builderForValue) {
            this.contents_ = builderForValue.build();
            this.contentsCase_ = 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeSimplePredicate(SimplePredicate value) {
            if (this.contentsCase_ != 2 || this.contents_ == SimplePredicate.getDefaultInstance()) {
                this.contents_ = value;
            } else {
                this.contents_ = ((SimplePredicate.Builder) SimplePredicate.newBuilder((SimplePredicate) this.contents_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.contentsCase_ = 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSimplePredicate() {
            if (this.contentsCase_ == 2) {
                this.contentsCase_ = 0;
                this.contents_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
        public boolean hasCombination() {
            return this.contentsCase_ == 3;
        }

        @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
        public Combination getCombination() {
            if (this.contentsCase_ == 3) {
                return (Combination) this.contents_;
            }
            return Combination.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCombination(Combination value) {
            if (value != null) {
                this.contents_ = value;
                this.contentsCase_ = 3;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCombination(Combination.Builder builderForValue) {
            this.contents_ = builderForValue.build();
            this.contentsCase_ = 3;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeCombination(Combination value) {
            if (this.contentsCase_ != 3 || this.contents_ == Combination.getDefaultInstance()) {
                this.contents_ = value;
            } else {
                this.contents_ = ((Combination.Builder) Combination.newBuilder((Combination) this.contents_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.contentsCase_ = 3;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCombination() {
            if (this.contentsCase_ == 3) {
                this.contentsCase_ = 0;
                this.contents_ = null;
            }
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if (this.contentsCase_ == 2) {
                output.writeMessage(2, (SimplePredicate) this.contents_);
            }
            if (this.contentsCase_ == 3) {
                output.writeMessage(3, (Combination) this.contents_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if (this.contentsCase_ == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, (SimplePredicate) this.contents_);
            }
            if (this.contentsCase_ == 3) {
                size2 += CodedOutputStream.computeMessageSize(3, (Combination) this.contents_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Predicate parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Predicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Predicate parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Predicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Predicate parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Predicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Predicate parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Predicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Predicate parseFrom(InputStream input) throws IOException {
            return (Predicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Predicate parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Predicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Predicate parseDelimitedFrom(InputStream input) throws IOException {
            return (Predicate) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Predicate parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Predicate) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Predicate parseFrom(CodedInputStream input) throws IOException {
            return (Predicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Predicate parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Predicate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Predicate prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Predicate, Builder> implements PredicateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Predicate.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
            public ContentsCase getContentsCase() {
                return ((Predicate) this.instance).getContentsCase();
            }

            public Builder clearContents() {
                copyOnWrite();
                ((Predicate) this.instance).clearContents();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
            public boolean hasId() {
                return ((Predicate) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
            public long getId() {
                return ((Predicate) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((Predicate) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Predicate) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
            public boolean hasSimplePredicate() {
                return ((Predicate) this.instance).hasSimplePredicate();
            }

            @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
            public SimplePredicate getSimplePredicate() {
                return ((Predicate) this.instance).getSimplePredicate();
            }

            public Builder setSimplePredicate(SimplePredicate value) {
                copyOnWrite();
                ((Predicate) this.instance).setSimplePredicate((Predicate) value);
                return this;
            }

            public Builder setSimplePredicate(SimplePredicate.Builder builderForValue) {
                copyOnWrite();
                ((Predicate) this.instance).setSimplePredicate((Predicate) builderForValue);
                return this;
            }

            public Builder mergeSimplePredicate(SimplePredicate value) {
                copyOnWrite();
                ((Predicate) this.instance).mergeSimplePredicate(value);
                return this;
            }

            public Builder clearSimplePredicate() {
                copyOnWrite();
                ((Predicate) this.instance).clearSimplePredicate();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
            public boolean hasCombination() {
                return ((Predicate) this.instance).hasCombination();
            }

            @Override // com.android.internal.os.StatsdConfigProto.PredicateOrBuilder
            public Combination getCombination() {
                return ((Predicate) this.instance).getCombination();
            }

            public Builder setCombination(Combination value) {
                copyOnWrite();
                ((Predicate) this.instance).setCombination((Predicate) value);
                return this;
            }

            public Builder setCombination(Combination.Builder builderForValue) {
                copyOnWrite();
                ((Predicate) this.instance).setCombination((Predicate) builderForValue);
                return this;
            }

            public Builder mergeCombination(Combination value) {
                copyOnWrite();
                ((Predicate) this.instance).mergeCombination(value);
                return this;
            }

            public Builder clearCombination() {
                copyOnWrite();
                ((Predicate) this.instance).clearCombination();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean z = true;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Predicate();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Predicate other = (Predicate) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    int i = AnonymousClass1.$SwitchMap$com$android$internal$os$StatsdConfigProto$Predicate$ContentsCase[other.getContentsCase().ordinal()];
                    if (i == 1) {
                        if (this.contentsCase_ != 2) {
                            z = false;
                        }
                        this.contents_ = visitor.visitOneofMessage(z, this.contents_, other.contents_);
                    } else if (i == 2) {
                        if (this.contentsCase_ != 3) {
                            z = false;
                        }
                        this.contents_ = visitor.visitOneofMessage(z, this.contents_, other.contents_);
                    } else if (i == 3) {
                        if (this.contentsCase_ == 0) {
                            z = false;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i2 = other.contentsCase_;
                        if (i2 != 0) {
                            this.contentsCase_ = i2;
                        }
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
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt64();
                            } else if (tag == 18) {
                                SimplePredicate.Builder subBuilder = null;
                                if (this.contentsCase_ == 2) {
                                    subBuilder = (SimplePredicate.Builder) ((SimplePredicate) this.contents_).toBuilder();
                                }
                                this.contents_ = input.readMessage(SimplePredicate.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) ((SimplePredicate) this.contents_));
                                    this.contents_ = subBuilder.buildPartial();
                                }
                                this.contentsCase_ = 2;
                            } else if (tag == 26) {
                                Combination.Builder subBuilder2 = null;
                                if (this.contentsCase_ == 3) {
                                    subBuilder2 = (Combination.Builder) ((Combination) this.contents_).toBuilder();
                                }
                                this.contents_ = input.readMessage(Combination.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) ((Combination) this.contents_));
                                    this.contents_ = subBuilder2.buildPartial();
                                }
                                this.contentsCase_ = 3;
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
                        synchronized (Predicate.class) {
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

        public static Predicate getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Predicate> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class MetricConditionLink extends GeneratedMessageLite<MetricConditionLink, Builder> implements MetricConditionLinkOrBuilder {
        public static final int CONDITION_FIELD_NUMBER = 1;
        private static final MetricConditionLink DEFAULT_INSTANCE = new MetricConditionLink();
        public static final int FIELDS_IN_CONDITION_FIELD_NUMBER = 3;
        public static final int FIELDS_IN_WHAT_FIELD_NUMBER = 2;
        private static volatile Parser<MetricConditionLink> PARSER;
        private int bitField0_;
        private long condition_ = 0;
        private FieldMatcher fieldsInCondition_;
        private FieldMatcher fieldsInWhat_;

        private MetricConditionLink() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
        public boolean hasCondition() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
        public long getCondition() {
            return this.condition_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCondition(long value) {
            this.bitField0_ |= 1;
            this.condition_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCondition() {
            this.bitField0_ &= -2;
            this.condition_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
        public boolean hasFieldsInWhat() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
        public FieldMatcher getFieldsInWhat() {
            FieldMatcher fieldMatcher = this.fieldsInWhat_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFieldsInWhat(FieldMatcher value) {
            if (value != null) {
                this.fieldsInWhat_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFieldsInWhat(FieldMatcher.Builder builderForValue) {
            this.fieldsInWhat_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeFieldsInWhat(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.fieldsInWhat_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.fieldsInWhat_ = value;
            } else {
                this.fieldsInWhat_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.fieldsInWhat_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFieldsInWhat() {
            this.fieldsInWhat_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
        public boolean hasFieldsInCondition() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
        public FieldMatcher getFieldsInCondition() {
            FieldMatcher fieldMatcher = this.fieldsInCondition_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFieldsInCondition(FieldMatcher value) {
            if (value != null) {
                this.fieldsInCondition_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFieldsInCondition(FieldMatcher.Builder builderForValue) {
            this.fieldsInCondition_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeFieldsInCondition(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.fieldsInCondition_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.fieldsInCondition_ = value;
            } else {
                this.fieldsInCondition_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.fieldsInCondition_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFieldsInCondition() {
            this.fieldsInCondition_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.condition_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getFieldsInWhat());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getFieldsInCondition());
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.condition_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getFieldsInWhat());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getFieldsInCondition());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static MetricConditionLink parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MetricConditionLink) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MetricConditionLink parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MetricConditionLink) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MetricConditionLink parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MetricConditionLink) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MetricConditionLink parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MetricConditionLink) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MetricConditionLink parseFrom(InputStream input) throws IOException {
            return (MetricConditionLink) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MetricConditionLink parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MetricConditionLink) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MetricConditionLink parseDelimitedFrom(InputStream input) throws IOException {
            return (MetricConditionLink) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MetricConditionLink parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MetricConditionLink) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MetricConditionLink parseFrom(CodedInputStream input) throws IOException {
            return (MetricConditionLink) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MetricConditionLink parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MetricConditionLink) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MetricConditionLink prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MetricConditionLink, Builder> implements MetricConditionLinkOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(MetricConditionLink.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
            public boolean hasCondition() {
                return ((MetricConditionLink) this.instance).hasCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
            public long getCondition() {
                return ((MetricConditionLink) this.instance).getCondition();
            }

            public Builder setCondition(long value) {
                copyOnWrite();
                ((MetricConditionLink) this.instance).setCondition(value);
                return this;
            }

            public Builder clearCondition() {
                copyOnWrite();
                ((MetricConditionLink) this.instance).clearCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
            public boolean hasFieldsInWhat() {
                return ((MetricConditionLink) this.instance).hasFieldsInWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
            public FieldMatcher getFieldsInWhat() {
                return ((MetricConditionLink) this.instance).getFieldsInWhat();
            }

            public Builder setFieldsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((MetricConditionLink) this.instance).setFieldsInWhat((MetricConditionLink) value);
                return this;
            }

            public Builder setFieldsInWhat(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((MetricConditionLink) this.instance).setFieldsInWhat((MetricConditionLink) builderForValue);
                return this;
            }

            public Builder mergeFieldsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((MetricConditionLink) this.instance).mergeFieldsInWhat(value);
                return this;
            }

            public Builder clearFieldsInWhat() {
                copyOnWrite();
                ((MetricConditionLink) this.instance).clearFieldsInWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
            public boolean hasFieldsInCondition() {
                return ((MetricConditionLink) this.instance).hasFieldsInCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricConditionLinkOrBuilder
            public FieldMatcher getFieldsInCondition() {
                return ((MetricConditionLink) this.instance).getFieldsInCondition();
            }

            public Builder setFieldsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((MetricConditionLink) this.instance).setFieldsInCondition((MetricConditionLink) value);
                return this;
            }

            public Builder setFieldsInCondition(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((MetricConditionLink) this.instance).setFieldsInCondition((MetricConditionLink) builderForValue);
                return this;
            }

            public Builder mergeFieldsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((MetricConditionLink) this.instance).mergeFieldsInCondition(value);
                return this;
            }

            public Builder clearFieldsInCondition() {
                copyOnWrite();
                ((MetricConditionLink) this.instance).clearFieldsInCondition();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new MetricConditionLink();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    MetricConditionLink other = (MetricConditionLink) arg1;
                    this.condition_ = visitor.visitLong(hasCondition(), this.condition_, other.hasCondition(), other.condition_);
                    this.fieldsInWhat_ = (FieldMatcher) visitor.visitMessage(this.fieldsInWhat_, other.fieldsInWhat_);
                    this.fieldsInCondition_ = (FieldMatcher) visitor.visitMessage(this.fieldsInCondition_, other.fieldsInCondition_);
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
                                this.bitField0_ |= 1;
                                this.condition_ = input.readInt64();
                            } else if (tag == 18) {
                                FieldMatcher.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (FieldMatcher.Builder) this.fieldsInWhat_.toBuilder();
                                }
                                this.fieldsInWhat_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.fieldsInWhat_);
                                    this.fieldsInWhat_ = (FieldMatcher) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                FieldMatcher.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder2 = (FieldMatcher.Builder) this.fieldsInCondition_.toBuilder();
                                }
                                this.fieldsInCondition_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.fieldsInCondition_);
                                    this.fieldsInCondition_ = (FieldMatcher) subBuilder2.buildPartial();
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
                        synchronized (MetricConditionLink.class) {
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

        public static MetricConditionLink getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MetricConditionLink> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class FieldFilter extends GeneratedMessageLite<FieldFilter, Builder> implements FieldFilterOrBuilder {
        private static final FieldFilter DEFAULT_INSTANCE = new FieldFilter();
        public static final int FIELDS_FIELD_NUMBER = 2;
        public static final int INCLUDE_ALL_FIELD_NUMBER = 1;
        private static volatile Parser<FieldFilter> PARSER;
        private int bitField0_;
        private FieldMatcher fields_;
        private boolean includeAll_ = false;

        private FieldFilter() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldFilterOrBuilder
        public boolean hasIncludeAll() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldFilterOrBuilder
        public boolean getIncludeAll() {
            return this.includeAll_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIncludeAll(boolean value) {
            this.bitField0_ |= 1;
            this.includeAll_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIncludeAll() {
            this.bitField0_ &= -2;
            this.includeAll_ = false;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldFilterOrBuilder
        public boolean hasFields() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.FieldFilterOrBuilder
        public FieldMatcher getFields() {
            FieldMatcher fieldMatcher = this.fields_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFields(FieldMatcher value) {
            if (value != null) {
                this.fields_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFields(FieldMatcher.Builder builderForValue) {
            this.fields_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeFields(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.fields_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.fields_ = value;
            } else {
                this.fields_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.fields_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFields() {
            this.fields_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.includeAll_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getFields());
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.includeAll_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getFields());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static FieldFilter parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldFilter parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldFilter parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldFilter parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldFilter parseFrom(InputStream input) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldFilter parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldFilter parseDelimitedFrom(InputStream input) throws IOException {
            return (FieldFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldFilter parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldFilter parseFrom(CodedInputStream input) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldFilter parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FieldFilter prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FieldFilter, Builder> implements FieldFilterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(FieldFilter.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldFilterOrBuilder
            public boolean hasIncludeAll() {
                return ((FieldFilter) this.instance).hasIncludeAll();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldFilterOrBuilder
            public boolean getIncludeAll() {
                return ((FieldFilter) this.instance).getIncludeAll();
            }

            public Builder setIncludeAll(boolean value) {
                copyOnWrite();
                ((FieldFilter) this.instance).setIncludeAll(value);
                return this;
            }

            public Builder clearIncludeAll() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearIncludeAll();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldFilterOrBuilder
            public boolean hasFields() {
                return ((FieldFilter) this.instance).hasFields();
            }

            @Override // com.android.internal.os.StatsdConfigProto.FieldFilterOrBuilder
            public FieldMatcher getFields() {
                return ((FieldFilter) this.instance).getFields();
            }

            public Builder setFields(FieldMatcher value) {
                copyOnWrite();
                ((FieldFilter) this.instance).setFields((FieldFilter) value);
                return this;
            }

            public Builder setFields(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((FieldFilter) this.instance).setFields((FieldFilter) builderForValue);
                return this;
            }

            public Builder mergeFields(FieldMatcher value) {
                copyOnWrite();
                ((FieldFilter) this.instance).mergeFields(value);
                return this;
            }

            public Builder clearFields() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearFields();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new FieldFilter();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    FieldFilter other = (FieldFilter) arg1;
                    this.includeAll_ = visitor.visitBoolean(hasIncludeAll(), this.includeAll_, other.hasIncludeAll(), other.includeAll_);
                    this.fields_ = (FieldMatcher) visitor.visitMessage(this.fields_, other.fields_);
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
                                this.bitField0_ |= 1;
                                this.includeAll_ = input.readBool();
                            } else if (tag == 18) {
                                FieldMatcher.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (FieldMatcher.Builder) this.fields_.toBuilder();
                                }
                                this.fields_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.fields_);
                                    this.fields_ = (FieldMatcher) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
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
                        synchronized (FieldFilter.class) {
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

        public static FieldFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class EventMetric extends GeneratedMessageLite<EventMetric, Builder> implements EventMetricOrBuilder {
        public static final int CONDITION_FIELD_NUMBER = 3;
        private static final EventMetric DEFAULT_INSTANCE = new EventMetric();
        public static final int ID_FIELD_NUMBER = 1;
        public static final int LINKS_FIELD_NUMBER = 4;
        private static volatile Parser<EventMetric> PARSER = null;
        public static final int WHAT_FIELD_NUMBER = 2;
        private int bitField0_;
        private long condition_ = 0;
        private long id_ = 0;
        private Internal.ProtobufList<MetricConditionLink> links_ = emptyProtobufList();
        private long what_ = 0;

        private EventMetric() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
        public boolean hasWhat() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
        public long getWhat() {
            return this.what_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWhat(long value) {
            this.bitField0_ |= 2;
            this.what_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWhat() {
            this.bitField0_ &= -3;
            this.what_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
        public boolean hasCondition() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
        public long getCondition() {
            return this.condition_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCondition(long value) {
            this.bitField0_ |= 4;
            this.condition_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCondition() {
            this.bitField0_ &= -5;
            this.condition_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
        public List<MetricConditionLink> getLinksList() {
            return this.links_;
        }

        public List<? extends MetricConditionLinkOrBuilder> getLinksOrBuilderList() {
            return this.links_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
        public int getLinksCount() {
            return this.links_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
        public MetricConditionLink getLinks(int index) {
            return this.links_.get(index);
        }

        public MetricConditionLinkOrBuilder getLinksOrBuilder(int index) {
            return this.links_.get(index);
        }

        private void ensureLinksIsMutable() {
            if (!this.links_.isModifiable()) {
                this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.set(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add((MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllLinks(Iterable<? extends MetricConditionLink> values) {
            ensureLinksIsMutable();
            AbstractMessageLite.addAll(values, this.links_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLinks() {
            this.links_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeLinks(int index) {
            ensureLinksIsMutable();
            this.links_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.what_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.condition_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                output.writeMessage(4, this.links_.get(i));
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.what_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.condition_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.links_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static EventMetric parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (EventMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EventMetric parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EventMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EventMetric parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (EventMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EventMetric parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EventMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EventMetric parseFrom(InputStream input) throws IOException {
            return (EventMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EventMetric parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EventMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EventMetric parseDelimitedFrom(InputStream input) throws IOException {
            return (EventMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static EventMetric parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EventMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EventMetric parseFrom(CodedInputStream input) throws IOException {
            return (EventMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EventMetric parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EventMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(EventMetric prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<EventMetric, Builder> implements EventMetricOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(EventMetric.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
            public boolean hasId() {
                return ((EventMetric) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
            public long getId() {
                return ((EventMetric) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((EventMetric) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((EventMetric) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
            public boolean hasWhat() {
                return ((EventMetric) this.instance).hasWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
            public long getWhat() {
                return ((EventMetric) this.instance).getWhat();
            }

            public Builder setWhat(long value) {
                copyOnWrite();
                ((EventMetric) this.instance).setWhat(value);
                return this;
            }

            public Builder clearWhat() {
                copyOnWrite();
                ((EventMetric) this.instance).clearWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
            public boolean hasCondition() {
                return ((EventMetric) this.instance).hasCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
            public long getCondition() {
                return ((EventMetric) this.instance).getCondition();
            }

            public Builder setCondition(long value) {
                copyOnWrite();
                ((EventMetric) this.instance).setCondition(value);
                return this;
            }

            public Builder clearCondition() {
                copyOnWrite();
                ((EventMetric) this.instance).clearCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
            public List<MetricConditionLink> getLinksList() {
                return Collections.unmodifiableList(((EventMetric) this.instance).getLinksList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
            public int getLinksCount() {
                return ((EventMetric) this.instance).getLinksCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventMetricOrBuilder
            public MetricConditionLink getLinks(int index) {
                return ((EventMetric) this.instance).getLinks(index);
            }

            public Builder setLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((EventMetric) this.instance).setLinks((EventMetric) index, (int) value);
                return this;
            }

            public Builder setLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((EventMetric) this.instance).setLinks((EventMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addLinks(MetricConditionLink value) {
                copyOnWrite();
                ((EventMetric) this.instance).addLinks((EventMetric) value);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((EventMetric) this.instance).addLinks((EventMetric) index, (int) value);
                return this;
            }

            public Builder addLinks(MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((EventMetric) this.instance).addLinks((EventMetric) builderForValue);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((EventMetric) this.instance).addLinks((EventMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addAllLinks(Iterable<? extends MetricConditionLink> values) {
                copyOnWrite();
                ((EventMetric) this.instance).addAllLinks(values);
                return this;
            }

            public Builder clearLinks() {
                copyOnWrite();
                ((EventMetric) this.instance).clearLinks();
                return this;
            }

            public Builder removeLinks(int index) {
                copyOnWrite();
                ((EventMetric) this.instance).removeLinks(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new EventMetric();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.links_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    EventMetric other = (EventMetric) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    this.what_ = visitor.visitLong(hasWhat(), this.what_, other.hasWhat(), other.what_);
                    this.condition_ = visitor.visitLong(hasCondition(), this.condition_, other.hasCondition(), other.condition_);
                    this.links_ = visitor.visitList(this.links_, other.links_);
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
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.what_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.condition_ = input.readInt64();
                            } else if (tag == 34) {
                                if (!this.links_.isModifiable()) {
                                    this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
                                }
                                this.links_.add((MetricConditionLink) input.readMessage(MetricConditionLink.parser(), extensionRegistry));
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
                        synchronized (EventMetric.class) {
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

        public static EventMetric getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<EventMetric> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class CountMetric extends GeneratedMessageLite<CountMetric, Builder> implements CountMetricOrBuilder {
        public static final int BUCKET_FIELD_NUMBER = 5;
        public static final int CONDITION_FIELD_NUMBER = 3;
        private static final CountMetric DEFAULT_INSTANCE = new CountMetric();
        public static final int DIMENSIONS_IN_CONDITION_FIELD_NUMBER = 7;
        public static final int DIMENSIONS_IN_WHAT_FIELD_NUMBER = 4;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int LINKS_FIELD_NUMBER = 6;
        private static volatile Parser<CountMetric> PARSER = null;
        public static final int WHAT_FIELD_NUMBER = 2;
        private int bitField0_;
        private int bucket_ = 0;
        private long condition_ = 0;
        private FieldMatcher dimensionsInCondition_;
        private FieldMatcher dimensionsInWhat_;
        private long id_ = 0;
        private Internal.ProtobufList<MetricConditionLink> links_ = emptyProtobufList();
        private long what_ = 0;

        private CountMetric() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public boolean hasWhat() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public long getWhat() {
            return this.what_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWhat(long value) {
            this.bitField0_ |= 2;
            this.what_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWhat() {
            this.bitField0_ &= -3;
            this.what_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public boolean hasCondition() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public long getCondition() {
            return this.condition_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCondition(long value) {
            this.bitField0_ |= 4;
            this.condition_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCondition() {
            this.bitField0_ &= -5;
            this.condition_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public boolean hasDimensionsInWhat() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public FieldMatcher getDimensionsInWhat() {
            FieldMatcher fieldMatcher = this.dimensionsInWhat_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInWhat(FieldMatcher value) {
            if (value != null) {
                this.dimensionsInWhat_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInWhat(FieldMatcher.Builder builderForValue) {
            this.dimensionsInWhat_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDimensionsInWhat(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.dimensionsInWhat_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.dimensionsInWhat_ = value;
            } else {
                this.dimensionsInWhat_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.dimensionsInWhat_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDimensionsInWhat() {
            this.dimensionsInWhat_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public boolean hasDimensionsInCondition() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public FieldMatcher getDimensionsInCondition() {
            FieldMatcher fieldMatcher = this.dimensionsInCondition_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInCondition(FieldMatcher value) {
            if (value != null) {
                this.dimensionsInCondition_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInCondition(FieldMatcher.Builder builderForValue) {
            this.dimensionsInCondition_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDimensionsInCondition(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.dimensionsInCondition_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.dimensionsInCondition_ = value;
            } else {
                this.dimensionsInCondition_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.dimensionsInCondition_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDimensionsInCondition() {
            this.dimensionsInCondition_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public boolean hasBucket() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public TimeUnit getBucket() {
            TimeUnit result = TimeUnit.forNumber(this.bucket_);
            return result == null ? TimeUnit.TIME_UNIT_UNSPECIFIED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBucket(TimeUnit value) {
            if (value != null) {
                this.bitField0_ |= 32;
                this.bucket_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBucket() {
            this.bitField0_ &= -33;
            this.bucket_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public List<MetricConditionLink> getLinksList() {
            return this.links_;
        }

        public List<? extends MetricConditionLinkOrBuilder> getLinksOrBuilderList() {
            return this.links_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public int getLinksCount() {
            return this.links_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
        public MetricConditionLink getLinks(int index) {
            return this.links_.get(index);
        }

        public MetricConditionLinkOrBuilder getLinksOrBuilder(int index) {
            return this.links_.get(index);
        }

        private void ensureLinksIsMutable() {
            if (!this.links_.isModifiable()) {
                this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.set(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add((MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllLinks(Iterable<? extends MetricConditionLink> values) {
            ensureLinksIsMutable();
            AbstractMessageLite.addAll(values, this.links_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLinks() {
            this.links_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeLinks(int index) {
            ensureLinksIsMutable();
            this.links_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.what_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.condition_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getDimensionsInWhat());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeEnum(5, this.bucket_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                output.writeMessage(6, this.links_.get(i));
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(7, getDimensionsInCondition());
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.what_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.condition_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getDimensionsInWhat());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeEnumSize(5, this.bucket_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(6, this.links_.get(i));
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(7, getDimensionsInCondition());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static CountMetric parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CountMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CountMetric parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CountMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CountMetric parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CountMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CountMetric parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CountMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CountMetric parseFrom(InputStream input) throws IOException {
            return (CountMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CountMetric parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CountMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CountMetric parseDelimitedFrom(InputStream input) throws IOException {
            return (CountMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CountMetric parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CountMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CountMetric parseFrom(CodedInputStream input) throws IOException {
            return (CountMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CountMetric parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CountMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CountMetric prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CountMetric, Builder> implements CountMetricOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(CountMetric.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public boolean hasId() {
                return ((CountMetric) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public long getId() {
                return ((CountMetric) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((CountMetric) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((CountMetric) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public boolean hasWhat() {
                return ((CountMetric) this.instance).hasWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public long getWhat() {
                return ((CountMetric) this.instance).getWhat();
            }

            public Builder setWhat(long value) {
                copyOnWrite();
                ((CountMetric) this.instance).setWhat(value);
                return this;
            }

            public Builder clearWhat() {
                copyOnWrite();
                ((CountMetric) this.instance).clearWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public boolean hasCondition() {
                return ((CountMetric) this.instance).hasCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public long getCondition() {
                return ((CountMetric) this.instance).getCondition();
            }

            public Builder setCondition(long value) {
                copyOnWrite();
                ((CountMetric) this.instance).setCondition(value);
                return this;
            }

            public Builder clearCondition() {
                copyOnWrite();
                ((CountMetric) this.instance).clearCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public boolean hasDimensionsInWhat() {
                return ((CountMetric) this.instance).hasDimensionsInWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public FieldMatcher getDimensionsInWhat() {
                return ((CountMetric) this.instance).getDimensionsInWhat();
            }

            public Builder setDimensionsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((CountMetric) this.instance).setDimensionsInWhat((CountMetric) value);
                return this;
            }

            public Builder setDimensionsInWhat(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((CountMetric) this.instance).setDimensionsInWhat((CountMetric) builderForValue);
                return this;
            }

            public Builder mergeDimensionsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((CountMetric) this.instance).mergeDimensionsInWhat(value);
                return this;
            }

            public Builder clearDimensionsInWhat() {
                copyOnWrite();
                ((CountMetric) this.instance).clearDimensionsInWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public boolean hasDimensionsInCondition() {
                return ((CountMetric) this.instance).hasDimensionsInCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public FieldMatcher getDimensionsInCondition() {
                return ((CountMetric) this.instance).getDimensionsInCondition();
            }

            public Builder setDimensionsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((CountMetric) this.instance).setDimensionsInCondition((CountMetric) value);
                return this;
            }

            public Builder setDimensionsInCondition(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((CountMetric) this.instance).setDimensionsInCondition((CountMetric) builderForValue);
                return this;
            }

            public Builder mergeDimensionsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((CountMetric) this.instance).mergeDimensionsInCondition(value);
                return this;
            }

            public Builder clearDimensionsInCondition() {
                copyOnWrite();
                ((CountMetric) this.instance).clearDimensionsInCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public boolean hasBucket() {
                return ((CountMetric) this.instance).hasBucket();
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public TimeUnit getBucket() {
                return ((CountMetric) this.instance).getBucket();
            }

            public Builder setBucket(TimeUnit value) {
                copyOnWrite();
                ((CountMetric) this.instance).setBucket(value);
                return this;
            }

            public Builder clearBucket() {
                copyOnWrite();
                ((CountMetric) this.instance).clearBucket();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public List<MetricConditionLink> getLinksList() {
                return Collections.unmodifiableList(((CountMetric) this.instance).getLinksList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public int getLinksCount() {
                return ((CountMetric) this.instance).getLinksCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.CountMetricOrBuilder
            public MetricConditionLink getLinks(int index) {
                return ((CountMetric) this.instance).getLinks(index);
            }

            public Builder setLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((CountMetric) this.instance).setLinks((CountMetric) index, (int) value);
                return this;
            }

            public Builder setLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((CountMetric) this.instance).setLinks((CountMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addLinks(MetricConditionLink value) {
                copyOnWrite();
                ((CountMetric) this.instance).addLinks((CountMetric) value);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((CountMetric) this.instance).addLinks((CountMetric) index, (int) value);
                return this;
            }

            public Builder addLinks(MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((CountMetric) this.instance).addLinks((CountMetric) builderForValue);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((CountMetric) this.instance).addLinks((CountMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addAllLinks(Iterable<? extends MetricConditionLink> values) {
                copyOnWrite();
                ((CountMetric) this.instance).addAllLinks(values);
                return this;
            }

            public Builder clearLinks() {
                copyOnWrite();
                ((CountMetric) this.instance).clearLinks();
                return this;
            }

            public Builder removeLinks(int index) {
                copyOnWrite();
                ((CountMetric) this.instance).removeLinks(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new CountMetric();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.links_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    CountMetric other = (CountMetric) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    this.what_ = visitor.visitLong(hasWhat(), this.what_, other.hasWhat(), other.what_);
                    this.condition_ = visitor.visitLong(hasCondition(), this.condition_, other.hasCondition(), other.condition_);
                    this.dimensionsInWhat_ = (FieldMatcher) visitor.visitMessage(this.dimensionsInWhat_, other.dimensionsInWhat_);
                    this.dimensionsInCondition_ = (FieldMatcher) visitor.visitMessage(this.dimensionsInCondition_, other.dimensionsInCondition_);
                    this.bucket_ = visitor.visitInt(hasBucket(), this.bucket_, other.hasBucket(), other.bucket_);
                    this.links_ = visitor.visitList(this.links_, other.links_);
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
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.what_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.condition_ = input.readInt64();
                            } else if (tag == 34) {
                                FieldMatcher.Builder subBuilder = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder = (FieldMatcher.Builder) this.dimensionsInWhat_.toBuilder();
                                }
                                this.dimensionsInWhat_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.dimensionsInWhat_);
                                    this.dimensionsInWhat_ = (FieldMatcher) subBuilder.buildPartial();
                                }
                                this.bitField0_ = 8 | this.bitField0_;
                            } else if (tag == 40) {
                                int rawValue = input.readEnum();
                                if (TimeUnit.forNumber(rawValue) == null) {
                                    super.mergeVarintField(5, rawValue);
                                } else {
                                    this.bitField0_ |= 32;
                                    this.bucket_ = rawValue;
                                }
                            } else if (tag == 50) {
                                if (!this.links_.isModifiable()) {
                                    this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
                                }
                                this.links_.add((MetricConditionLink) input.readMessage(MetricConditionLink.parser(), extensionRegistry));
                            } else if (tag == 58) {
                                FieldMatcher.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder2 = (FieldMatcher.Builder) this.dimensionsInCondition_.toBuilder();
                                }
                                this.dimensionsInCondition_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.dimensionsInCondition_);
                                    this.dimensionsInCondition_ = (FieldMatcher) subBuilder2.buildPartial();
                                }
                                this.bitField0_ = 16 | this.bitField0_;
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
                        synchronized (CountMetric.class) {
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

        public static CountMetric getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CountMetric> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DurationMetric extends GeneratedMessageLite<DurationMetric, Builder> implements DurationMetricOrBuilder {
        public static final int AGGREGATION_TYPE_FIELD_NUMBER = 5;
        public static final int BUCKET_FIELD_NUMBER = 7;
        public static final int CONDITION_FIELD_NUMBER = 3;
        private static final DurationMetric DEFAULT_INSTANCE = new DurationMetric();
        public static final int DIMENSIONS_IN_CONDITION_FIELD_NUMBER = 8;
        public static final int DIMENSIONS_IN_WHAT_FIELD_NUMBER = 6;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int LINKS_FIELD_NUMBER = 4;
        private static volatile Parser<DurationMetric> PARSER = null;
        public static final int WHAT_FIELD_NUMBER = 2;
        private int aggregationType_ = 1;
        private int bitField0_;
        private int bucket_ = 0;
        private long condition_ = 0;
        private FieldMatcher dimensionsInCondition_;
        private FieldMatcher dimensionsInWhat_;
        private long id_ = 0;
        private Internal.ProtobufList<MetricConditionLink> links_ = emptyProtobufList();
        private long what_ = 0;

        private DurationMetric() {
        }

        public enum AggregationType implements Internal.EnumLite {
            SUM(1),
            MAX_SPARSE(2);
            
            public static final int MAX_SPARSE_VALUE = 2;
            public static final int SUM_VALUE = 1;
            private static final Internal.EnumLiteMap<AggregationType> internalValueMap = new Internal.EnumLiteMap<AggregationType>() {
                /* class com.android.internal.os.StatsdConfigProto.DurationMetric.AggregationType.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public AggregationType findValueByNumber(int number) {
                    return AggregationType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static AggregationType valueOf(int value2) {
                return forNumber(value2);
            }

            public static AggregationType forNumber(int value2) {
                if (value2 == 1) {
                    return SUM;
                }
                if (value2 != 2) {
                    return null;
                }
                return MAX_SPARSE;
            }

            public static Internal.EnumLiteMap<AggregationType> internalGetValueMap() {
                return internalValueMap;
            }

            private AggregationType(int value2) {
                this.value = value2;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public boolean hasWhat() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public long getWhat() {
            return this.what_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWhat(long value) {
            this.bitField0_ |= 2;
            this.what_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWhat() {
            this.bitField0_ &= -3;
            this.what_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public boolean hasCondition() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public long getCondition() {
            return this.condition_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCondition(long value) {
            this.bitField0_ |= 4;
            this.condition_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCondition() {
            this.bitField0_ &= -5;
            this.condition_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public List<MetricConditionLink> getLinksList() {
            return this.links_;
        }

        public List<? extends MetricConditionLinkOrBuilder> getLinksOrBuilderList() {
            return this.links_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public int getLinksCount() {
            return this.links_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public MetricConditionLink getLinks(int index) {
            return this.links_.get(index);
        }

        public MetricConditionLinkOrBuilder getLinksOrBuilder(int index) {
            return this.links_.get(index);
        }

        private void ensureLinksIsMutable() {
            if (!this.links_.isModifiable()) {
                this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.set(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add((MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllLinks(Iterable<? extends MetricConditionLink> values) {
            ensureLinksIsMutable();
            AbstractMessageLite.addAll(values, this.links_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLinks() {
            this.links_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeLinks(int index) {
            ensureLinksIsMutable();
            this.links_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public boolean hasAggregationType() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public AggregationType getAggregationType() {
            AggregationType result = AggregationType.forNumber(this.aggregationType_);
            return result == null ? AggregationType.SUM : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAggregationType(AggregationType value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.aggregationType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAggregationType() {
            this.bitField0_ &= -9;
            this.aggregationType_ = 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public boolean hasDimensionsInWhat() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public FieldMatcher getDimensionsInWhat() {
            FieldMatcher fieldMatcher = this.dimensionsInWhat_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInWhat(FieldMatcher value) {
            if (value != null) {
                this.dimensionsInWhat_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInWhat(FieldMatcher.Builder builderForValue) {
            this.dimensionsInWhat_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDimensionsInWhat(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.dimensionsInWhat_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.dimensionsInWhat_ = value;
            } else {
                this.dimensionsInWhat_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.dimensionsInWhat_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDimensionsInWhat() {
            this.dimensionsInWhat_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public boolean hasDimensionsInCondition() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public FieldMatcher getDimensionsInCondition() {
            FieldMatcher fieldMatcher = this.dimensionsInCondition_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInCondition(FieldMatcher value) {
            if (value != null) {
                this.dimensionsInCondition_ = value;
                this.bitField0_ |= 32;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInCondition(FieldMatcher.Builder builderForValue) {
            this.dimensionsInCondition_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDimensionsInCondition(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.dimensionsInCondition_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.dimensionsInCondition_ = value;
            } else {
                this.dimensionsInCondition_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.dimensionsInCondition_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDimensionsInCondition() {
            this.dimensionsInCondition_ = null;
            this.bitField0_ &= -33;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public boolean hasBucket() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
        public TimeUnit getBucket() {
            TimeUnit result = TimeUnit.forNumber(this.bucket_);
            return result == null ? TimeUnit.TIME_UNIT_UNSPECIFIED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBucket(TimeUnit value) {
            if (value != null) {
                this.bitField0_ |= 64;
                this.bucket_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBucket() {
            this.bitField0_ &= -65;
            this.bucket_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.what_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.condition_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                output.writeMessage(4, this.links_.get(i));
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(5, this.aggregationType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(6, getDimensionsInWhat());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeEnum(7, this.bucket_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(8, getDimensionsInCondition());
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.what_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.condition_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.links_.get(i));
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(5, this.aggregationType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(6, getDimensionsInWhat());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeEnumSize(7, this.bucket_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(8, getDimensionsInCondition());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static DurationMetric parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DurationMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DurationMetric parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DurationMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DurationMetric parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DurationMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DurationMetric parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DurationMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DurationMetric parseFrom(InputStream input) throws IOException {
            return (DurationMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DurationMetric parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DurationMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DurationMetric parseDelimitedFrom(InputStream input) throws IOException {
            return (DurationMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DurationMetric parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DurationMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DurationMetric parseFrom(CodedInputStream input) throws IOException {
            return (DurationMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DurationMetric parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DurationMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DurationMetric prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DurationMetric, Builder> implements DurationMetricOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(DurationMetric.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public boolean hasId() {
                return ((DurationMetric) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public long getId() {
                return ((DurationMetric) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((DurationMetric) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((DurationMetric) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public boolean hasWhat() {
                return ((DurationMetric) this.instance).hasWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public long getWhat() {
                return ((DurationMetric) this.instance).getWhat();
            }

            public Builder setWhat(long value) {
                copyOnWrite();
                ((DurationMetric) this.instance).setWhat(value);
                return this;
            }

            public Builder clearWhat() {
                copyOnWrite();
                ((DurationMetric) this.instance).clearWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public boolean hasCondition() {
                return ((DurationMetric) this.instance).hasCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public long getCondition() {
                return ((DurationMetric) this.instance).getCondition();
            }

            public Builder setCondition(long value) {
                copyOnWrite();
                ((DurationMetric) this.instance).setCondition(value);
                return this;
            }

            public Builder clearCondition() {
                copyOnWrite();
                ((DurationMetric) this.instance).clearCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public List<MetricConditionLink> getLinksList() {
                return Collections.unmodifiableList(((DurationMetric) this.instance).getLinksList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public int getLinksCount() {
                return ((DurationMetric) this.instance).getLinksCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public MetricConditionLink getLinks(int index) {
                return ((DurationMetric) this.instance).getLinks(index);
            }

            public Builder setLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((DurationMetric) this.instance).setLinks((DurationMetric) index, (int) value);
                return this;
            }

            public Builder setLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((DurationMetric) this.instance).setLinks((DurationMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addLinks(MetricConditionLink value) {
                copyOnWrite();
                ((DurationMetric) this.instance).addLinks((DurationMetric) value);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((DurationMetric) this.instance).addLinks((DurationMetric) index, (int) value);
                return this;
            }

            public Builder addLinks(MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((DurationMetric) this.instance).addLinks((DurationMetric) builderForValue);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((DurationMetric) this.instance).addLinks((DurationMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addAllLinks(Iterable<? extends MetricConditionLink> values) {
                copyOnWrite();
                ((DurationMetric) this.instance).addAllLinks(values);
                return this;
            }

            public Builder clearLinks() {
                copyOnWrite();
                ((DurationMetric) this.instance).clearLinks();
                return this;
            }

            public Builder removeLinks(int index) {
                copyOnWrite();
                ((DurationMetric) this.instance).removeLinks(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public boolean hasAggregationType() {
                return ((DurationMetric) this.instance).hasAggregationType();
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public AggregationType getAggregationType() {
                return ((DurationMetric) this.instance).getAggregationType();
            }

            public Builder setAggregationType(AggregationType value) {
                copyOnWrite();
                ((DurationMetric) this.instance).setAggregationType(value);
                return this;
            }

            public Builder clearAggregationType() {
                copyOnWrite();
                ((DurationMetric) this.instance).clearAggregationType();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public boolean hasDimensionsInWhat() {
                return ((DurationMetric) this.instance).hasDimensionsInWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public FieldMatcher getDimensionsInWhat() {
                return ((DurationMetric) this.instance).getDimensionsInWhat();
            }

            public Builder setDimensionsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((DurationMetric) this.instance).setDimensionsInWhat((DurationMetric) value);
                return this;
            }

            public Builder setDimensionsInWhat(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((DurationMetric) this.instance).setDimensionsInWhat((DurationMetric) builderForValue);
                return this;
            }

            public Builder mergeDimensionsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((DurationMetric) this.instance).mergeDimensionsInWhat(value);
                return this;
            }

            public Builder clearDimensionsInWhat() {
                copyOnWrite();
                ((DurationMetric) this.instance).clearDimensionsInWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public boolean hasDimensionsInCondition() {
                return ((DurationMetric) this.instance).hasDimensionsInCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public FieldMatcher getDimensionsInCondition() {
                return ((DurationMetric) this.instance).getDimensionsInCondition();
            }

            public Builder setDimensionsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((DurationMetric) this.instance).setDimensionsInCondition((DurationMetric) value);
                return this;
            }

            public Builder setDimensionsInCondition(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((DurationMetric) this.instance).setDimensionsInCondition((DurationMetric) builderForValue);
                return this;
            }

            public Builder mergeDimensionsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((DurationMetric) this.instance).mergeDimensionsInCondition(value);
                return this;
            }

            public Builder clearDimensionsInCondition() {
                copyOnWrite();
                ((DurationMetric) this.instance).clearDimensionsInCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public boolean hasBucket() {
                return ((DurationMetric) this.instance).hasBucket();
            }

            @Override // com.android.internal.os.StatsdConfigProto.DurationMetricOrBuilder
            public TimeUnit getBucket() {
                return ((DurationMetric) this.instance).getBucket();
            }

            public Builder setBucket(TimeUnit value) {
                copyOnWrite();
                ((DurationMetric) this.instance).setBucket(value);
                return this;
            }

            public Builder clearBucket() {
                copyOnWrite();
                ((DurationMetric) this.instance).clearBucket();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new DurationMetric();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.links_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    DurationMetric other = (DurationMetric) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    this.what_ = visitor.visitLong(hasWhat(), this.what_, other.hasWhat(), other.what_);
                    this.condition_ = visitor.visitLong(hasCondition(), this.condition_, other.hasCondition(), other.condition_);
                    this.links_ = visitor.visitList(this.links_, other.links_);
                    this.aggregationType_ = visitor.visitInt(hasAggregationType(), this.aggregationType_, other.hasAggregationType(), other.aggregationType_);
                    this.dimensionsInWhat_ = (FieldMatcher) visitor.visitMessage(this.dimensionsInWhat_, other.dimensionsInWhat_);
                    this.dimensionsInCondition_ = (FieldMatcher) visitor.visitMessage(this.dimensionsInCondition_, other.dimensionsInCondition_);
                    this.bucket_ = visitor.visitInt(hasBucket(), this.bucket_, other.hasBucket(), other.bucket_);
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
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.what_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.condition_ = input.readInt64();
                            } else if (tag == 34) {
                                if (!this.links_.isModifiable()) {
                                    this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
                                }
                                this.links_.add((MetricConditionLink) input.readMessage(MetricConditionLink.parser(), extensionRegistry));
                            } else if (tag == 40) {
                                int rawValue = input.readEnum();
                                if (AggregationType.forNumber(rawValue) == null) {
                                    super.mergeVarintField(5, rawValue);
                                } else {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.aggregationType_ = rawValue;
                                }
                            } else if (tag == 50) {
                                FieldMatcher.Builder subBuilder = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder = (FieldMatcher.Builder) this.dimensionsInWhat_.toBuilder();
                                }
                                this.dimensionsInWhat_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.dimensionsInWhat_);
                                    this.dimensionsInWhat_ = (FieldMatcher) subBuilder.buildPartial();
                                }
                                this.bitField0_ = 16 | this.bitField0_;
                            } else if (tag == 56) {
                                int rawValue2 = input.readEnum();
                                if (TimeUnit.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(7, rawValue2);
                                } else {
                                    this.bitField0_ |= 64;
                                    this.bucket_ = rawValue2;
                                }
                            } else if (tag == 66) {
                                FieldMatcher.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder2 = (FieldMatcher.Builder) this.dimensionsInCondition_.toBuilder();
                                }
                                this.dimensionsInCondition_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.dimensionsInCondition_);
                                    this.dimensionsInCondition_ = (FieldMatcher) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 32;
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
                        synchronized (DurationMetric.class) {
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

        public static DurationMetric getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DurationMetric> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class GaugeMetric extends GeneratedMessageLite<GaugeMetric, Builder> implements GaugeMetricOrBuilder {
        public static final int BUCKET_FIELD_NUMBER = 6;
        public static final int CONDITION_FIELD_NUMBER = 4;
        private static final GaugeMetric DEFAULT_INSTANCE = new GaugeMetric();
        public static final int DIMENSIONS_IN_CONDITION_FIELD_NUMBER = 8;
        public static final int DIMENSIONS_IN_WHAT_FIELD_NUMBER = 5;
        public static final int GAUGE_FIELDS_FILTER_FIELD_NUMBER = 3;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int LINKS_FIELD_NUMBER = 7;
        public static final int MAX_NUM_GAUGE_ATOMS_PER_BUCKET_FIELD_NUMBER = 11;
        public static final int MAX_PULL_DELAY_SEC_FIELD_NUMBER = 13;
        public static final int MIN_BUCKET_SIZE_NANOS_FIELD_NUMBER = 10;
        private static volatile Parser<GaugeMetric> PARSER = null;
        public static final int SAMPLING_TYPE_FIELD_NUMBER = 9;
        public static final int SPLIT_BUCKET_FOR_APP_UPGRADE_FIELD_NUMBER = 14;
        public static final int TRIGGER_EVENT_FIELD_NUMBER = 12;
        public static final int WHAT_FIELD_NUMBER = 2;
        private int bitField0_;
        private int bucket_ = 0;
        private long condition_ = 0;
        private FieldMatcher dimensionsInCondition_;
        private FieldMatcher dimensionsInWhat_;
        private FieldFilter gaugeFieldsFilter_;
        private long id_ = 0;
        private Internal.ProtobufList<MetricConditionLink> links_ = emptyProtobufList();
        private long maxNumGaugeAtomsPerBucket_ = 10;
        private int maxPullDelaySec_ = 10;
        private long minBucketSizeNanos_ = 0;
        private int samplingType_ = 1;
        private boolean splitBucketForAppUpgrade_ = true;
        private long triggerEvent_ = 0;
        private long what_ = 0;

        private GaugeMetric() {
        }

        public enum SamplingType implements Internal.EnumLite {
            RANDOM_ONE_SAMPLE(1),
            ALL_CONDITION_CHANGES(2),
            CONDITION_CHANGE_TO_TRUE(3),
            FIRST_N_SAMPLES(4);
            
            public static final int ALL_CONDITION_CHANGES_VALUE = 2;
            public static final int CONDITION_CHANGE_TO_TRUE_VALUE = 3;
            public static final int FIRST_N_SAMPLES_VALUE = 4;
            public static final int RANDOM_ONE_SAMPLE_VALUE = 1;
            private static final Internal.EnumLiteMap<SamplingType> internalValueMap = new Internal.EnumLiteMap<SamplingType>() {
                /* class com.android.internal.os.StatsdConfigProto.GaugeMetric.SamplingType.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public SamplingType findValueByNumber(int number) {
                    return SamplingType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static SamplingType valueOf(int value2) {
                return forNumber(value2);
            }

            public static SamplingType forNumber(int value2) {
                if (value2 == 1) {
                    return RANDOM_ONE_SAMPLE;
                }
                if (value2 == 2) {
                    return ALL_CONDITION_CHANGES;
                }
                if (value2 == 3) {
                    return CONDITION_CHANGE_TO_TRUE;
                }
                if (value2 != 4) {
                    return null;
                }
                return FIRST_N_SAMPLES;
            }

            public static Internal.EnumLiteMap<SamplingType> internalGetValueMap() {
                return internalValueMap;
            }

            private SamplingType(int value2) {
                this.value = value2;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasWhat() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public long getWhat() {
            return this.what_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWhat(long value) {
            this.bitField0_ |= 2;
            this.what_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWhat() {
            this.bitField0_ &= -3;
            this.what_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasTriggerEvent() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public long getTriggerEvent() {
            return this.triggerEvent_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTriggerEvent(long value) {
            this.bitField0_ |= 4;
            this.triggerEvent_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTriggerEvent() {
            this.bitField0_ &= -5;
            this.triggerEvent_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasGaugeFieldsFilter() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public FieldFilter getGaugeFieldsFilter() {
            FieldFilter fieldFilter = this.gaugeFieldsFilter_;
            return fieldFilter == null ? FieldFilter.getDefaultInstance() : fieldFilter;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setGaugeFieldsFilter(FieldFilter value) {
            if (value != null) {
                this.gaugeFieldsFilter_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setGaugeFieldsFilter(FieldFilter.Builder builderForValue) {
            this.gaugeFieldsFilter_ = (FieldFilter) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeGaugeFieldsFilter(FieldFilter value) {
            FieldFilter fieldFilter = this.gaugeFieldsFilter_;
            if (fieldFilter == null || fieldFilter == FieldFilter.getDefaultInstance()) {
                this.gaugeFieldsFilter_ = value;
            } else {
                this.gaugeFieldsFilter_ = (FieldFilter) ((FieldFilter.Builder) FieldFilter.newBuilder(this.gaugeFieldsFilter_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearGaugeFieldsFilter() {
            this.gaugeFieldsFilter_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasCondition() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public long getCondition() {
            return this.condition_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCondition(long value) {
            this.bitField0_ |= 16;
            this.condition_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCondition() {
            this.bitField0_ &= -17;
            this.condition_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasDimensionsInWhat() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public FieldMatcher getDimensionsInWhat() {
            FieldMatcher fieldMatcher = this.dimensionsInWhat_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInWhat(FieldMatcher value) {
            if (value != null) {
                this.dimensionsInWhat_ = value;
                this.bitField0_ |= 32;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInWhat(FieldMatcher.Builder builderForValue) {
            this.dimensionsInWhat_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDimensionsInWhat(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.dimensionsInWhat_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.dimensionsInWhat_ = value;
            } else {
                this.dimensionsInWhat_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.dimensionsInWhat_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDimensionsInWhat() {
            this.dimensionsInWhat_ = null;
            this.bitField0_ &= -33;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasDimensionsInCondition() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public FieldMatcher getDimensionsInCondition() {
            FieldMatcher fieldMatcher = this.dimensionsInCondition_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInCondition(FieldMatcher value) {
            if (value != null) {
                this.dimensionsInCondition_ = value;
                this.bitField0_ |= 64;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInCondition(FieldMatcher.Builder builderForValue) {
            this.dimensionsInCondition_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 64;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDimensionsInCondition(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.dimensionsInCondition_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.dimensionsInCondition_ = value;
            } else {
                this.dimensionsInCondition_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.dimensionsInCondition_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 64;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDimensionsInCondition() {
            this.dimensionsInCondition_ = null;
            this.bitField0_ &= -65;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasBucket() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public TimeUnit getBucket() {
            TimeUnit result = TimeUnit.forNumber(this.bucket_);
            return result == null ? TimeUnit.TIME_UNIT_UNSPECIFIED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBucket(TimeUnit value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.bucket_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBucket() {
            this.bitField0_ &= -129;
            this.bucket_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public List<MetricConditionLink> getLinksList() {
            return this.links_;
        }

        public List<? extends MetricConditionLinkOrBuilder> getLinksOrBuilderList() {
            return this.links_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public int getLinksCount() {
            return this.links_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public MetricConditionLink getLinks(int index) {
            return this.links_.get(index);
        }

        public MetricConditionLinkOrBuilder getLinksOrBuilder(int index) {
            return this.links_.get(index);
        }

        private void ensureLinksIsMutable() {
            if (!this.links_.isModifiable()) {
                this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.set(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add((MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllLinks(Iterable<? extends MetricConditionLink> values) {
            ensureLinksIsMutable();
            AbstractMessageLite.addAll(values, this.links_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLinks() {
            this.links_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeLinks(int index) {
            ensureLinksIsMutable();
            this.links_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasSamplingType() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public SamplingType getSamplingType() {
            SamplingType result = SamplingType.forNumber(this.samplingType_);
            return result == null ? SamplingType.RANDOM_ONE_SAMPLE : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSamplingType(SamplingType value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.samplingType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSamplingType() {
            this.bitField0_ &= -257;
            this.samplingType_ = 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasMinBucketSizeNanos() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public long getMinBucketSizeNanos() {
            return this.minBucketSizeNanos_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMinBucketSizeNanos(long value) {
            this.bitField0_ |= 512;
            this.minBucketSizeNanos_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMinBucketSizeNanos() {
            this.bitField0_ &= -513;
            this.minBucketSizeNanos_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasMaxNumGaugeAtomsPerBucket() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public long getMaxNumGaugeAtomsPerBucket() {
            return this.maxNumGaugeAtomsPerBucket_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxNumGaugeAtomsPerBucket(long value) {
            this.bitField0_ |= 1024;
            this.maxNumGaugeAtomsPerBucket_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxNumGaugeAtomsPerBucket() {
            this.bitField0_ &= -1025;
            this.maxNumGaugeAtomsPerBucket_ = 10;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasMaxPullDelaySec() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public int getMaxPullDelaySec() {
            return this.maxPullDelaySec_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxPullDelaySec(int value) {
            this.bitField0_ |= 2048;
            this.maxPullDelaySec_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxPullDelaySec() {
            this.bitField0_ &= -2049;
            this.maxPullDelaySec_ = 10;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean hasSplitBucketForAppUpgrade() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
        public boolean getSplitBucketForAppUpgrade() {
            return this.splitBucketForAppUpgrade_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSplitBucketForAppUpgrade(boolean value) {
            this.bitField0_ |= 4096;
            this.splitBucketForAppUpgrade_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSplitBucketForAppUpgrade() {
            this.bitField0_ &= -4097;
            this.splitBucketForAppUpgrade_ = true;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.what_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(3, getGaugeFieldsFilter());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt64(4, this.condition_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(5, getDimensionsInWhat());
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeEnum(6, this.bucket_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                output.writeMessage(7, this.links_.get(i));
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeMessage(8, getDimensionsInCondition());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeEnum(9, this.samplingType_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt64(10, this.minBucketSizeNanos_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt64(11, this.maxNumGaugeAtomsPerBucket_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(12, this.triggerEvent_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(13, this.maxPullDelaySec_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeBool(14, this.splitBucketForAppUpgrade_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.what_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(3, getGaugeFieldsFilter());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt64Size(4, this.condition_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(5, getDimensionsInWhat());
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeEnumSize(6, this.bucket_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(7, this.links_.get(i));
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeMessageSize(8, getDimensionsInCondition());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeEnumSize(9, this.samplingType_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt64Size(10, this.minBucketSizeNanos_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt64Size(11, this.maxNumGaugeAtomsPerBucket_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(12, this.triggerEvent_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt32Size(13, this.maxPullDelaySec_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeBoolSize(14, this.splitBucketForAppUpgrade_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static GaugeMetric parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (GaugeMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GaugeMetric parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GaugeMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GaugeMetric parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (GaugeMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GaugeMetric parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GaugeMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GaugeMetric parseFrom(InputStream input) throws IOException {
            return (GaugeMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GaugeMetric parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GaugeMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GaugeMetric parseDelimitedFrom(InputStream input) throws IOException {
            return (GaugeMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static GaugeMetric parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GaugeMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GaugeMetric parseFrom(CodedInputStream input) throws IOException {
            return (GaugeMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GaugeMetric parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GaugeMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(GaugeMetric prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<GaugeMetric, Builder> implements GaugeMetricOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(GaugeMetric.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasId() {
                return ((GaugeMetric) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public long getId() {
                return ((GaugeMetric) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasWhat() {
                return ((GaugeMetric) this.instance).hasWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public long getWhat() {
                return ((GaugeMetric) this.instance).getWhat();
            }

            public Builder setWhat(long value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setWhat(value);
                return this;
            }

            public Builder clearWhat() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasTriggerEvent() {
                return ((GaugeMetric) this.instance).hasTriggerEvent();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public long getTriggerEvent() {
                return ((GaugeMetric) this.instance).getTriggerEvent();
            }

            public Builder setTriggerEvent(long value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setTriggerEvent(value);
                return this;
            }

            public Builder clearTriggerEvent() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearTriggerEvent();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasGaugeFieldsFilter() {
                return ((GaugeMetric) this.instance).hasGaugeFieldsFilter();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public FieldFilter getGaugeFieldsFilter() {
                return ((GaugeMetric) this.instance).getGaugeFieldsFilter();
            }

            public Builder setGaugeFieldsFilter(FieldFilter value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setGaugeFieldsFilter((GaugeMetric) value);
                return this;
            }

            public Builder setGaugeFieldsFilter(FieldFilter.Builder builderForValue) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setGaugeFieldsFilter((GaugeMetric) builderForValue);
                return this;
            }

            public Builder mergeGaugeFieldsFilter(FieldFilter value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).mergeGaugeFieldsFilter(value);
                return this;
            }

            public Builder clearGaugeFieldsFilter() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearGaugeFieldsFilter();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasCondition() {
                return ((GaugeMetric) this.instance).hasCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public long getCondition() {
                return ((GaugeMetric) this.instance).getCondition();
            }

            public Builder setCondition(long value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setCondition(value);
                return this;
            }

            public Builder clearCondition() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasDimensionsInWhat() {
                return ((GaugeMetric) this.instance).hasDimensionsInWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public FieldMatcher getDimensionsInWhat() {
                return ((GaugeMetric) this.instance).getDimensionsInWhat();
            }

            public Builder setDimensionsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setDimensionsInWhat((GaugeMetric) value);
                return this;
            }

            public Builder setDimensionsInWhat(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setDimensionsInWhat((GaugeMetric) builderForValue);
                return this;
            }

            public Builder mergeDimensionsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).mergeDimensionsInWhat(value);
                return this;
            }

            public Builder clearDimensionsInWhat() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearDimensionsInWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasDimensionsInCondition() {
                return ((GaugeMetric) this.instance).hasDimensionsInCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public FieldMatcher getDimensionsInCondition() {
                return ((GaugeMetric) this.instance).getDimensionsInCondition();
            }

            public Builder setDimensionsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setDimensionsInCondition((GaugeMetric) value);
                return this;
            }

            public Builder setDimensionsInCondition(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setDimensionsInCondition((GaugeMetric) builderForValue);
                return this;
            }

            public Builder mergeDimensionsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).mergeDimensionsInCondition(value);
                return this;
            }

            public Builder clearDimensionsInCondition() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearDimensionsInCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasBucket() {
                return ((GaugeMetric) this.instance).hasBucket();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public TimeUnit getBucket() {
                return ((GaugeMetric) this.instance).getBucket();
            }

            public Builder setBucket(TimeUnit value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setBucket(value);
                return this;
            }

            public Builder clearBucket() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearBucket();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public List<MetricConditionLink> getLinksList() {
                return Collections.unmodifiableList(((GaugeMetric) this.instance).getLinksList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public int getLinksCount() {
                return ((GaugeMetric) this.instance).getLinksCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public MetricConditionLink getLinks(int index) {
                return ((GaugeMetric) this.instance).getLinks(index);
            }

            public Builder setLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setLinks((GaugeMetric) index, (int) value);
                return this;
            }

            public Builder setLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setLinks((GaugeMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addLinks(MetricConditionLink value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).addLinks((GaugeMetric) value);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).addLinks((GaugeMetric) index, (int) value);
                return this;
            }

            public Builder addLinks(MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((GaugeMetric) this.instance).addLinks((GaugeMetric) builderForValue);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((GaugeMetric) this.instance).addLinks((GaugeMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addAllLinks(Iterable<? extends MetricConditionLink> values) {
                copyOnWrite();
                ((GaugeMetric) this.instance).addAllLinks(values);
                return this;
            }

            public Builder clearLinks() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearLinks();
                return this;
            }

            public Builder removeLinks(int index) {
                copyOnWrite();
                ((GaugeMetric) this.instance).removeLinks(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasSamplingType() {
                return ((GaugeMetric) this.instance).hasSamplingType();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public SamplingType getSamplingType() {
                return ((GaugeMetric) this.instance).getSamplingType();
            }

            public Builder setSamplingType(SamplingType value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setSamplingType(value);
                return this;
            }

            public Builder clearSamplingType() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearSamplingType();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasMinBucketSizeNanos() {
                return ((GaugeMetric) this.instance).hasMinBucketSizeNanos();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public long getMinBucketSizeNanos() {
                return ((GaugeMetric) this.instance).getMinBucketSizeNanos();
            }

            public Builder setMinBucketSizeNanos(long value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setMinBucketSizeNanos(value);
                return this;
            }

            public Builder clearMinBucketSizeNanos() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearMinBucketSizeNanos();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasMaxNumGaugeAtomsPerBucket() {
                return ((GaugeMetric) this.instance).hasMaxNumGaugeAtomsPerBucket();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public long getMaxNumGaugeAtomsPerBucket() {
                return ((GaugeMetric) this.instance).getMaxNumGaugeAtomsPerBucket();
            }

            public Builder setMaxNumGaugeAtomsPerBucket(long value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setMaxNumGaugeAtomsPerBucket(value);
                return this;
            }

            public Builder clearMaxNumGaugeAtomsPerBucket() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearMaxNumGaugeAtomsPerBucket();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasMaxPullDelaySec() {
                return ((GaugeMetric) this.instance).hasMaxPullDelaySec();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public int getMaxPullDelaySec() {
                return ((GaugeMetric) this.instance).getMaxPullDelaySec();
            }

            public Builder setMaxPullDelaySec(int value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setMaxPullDelaySec(value);
                return this;
            }

            public Builder clearMaxPullDelaySec() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearMaxPullDelaySec();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean hasSplitBucketForAppUpgrade() {
                return ((GaugeMetric) this.instance).hasSplitBucketForAppUpgrade();
            }

            @Override // com.android.internal.os.StatsdConfigProto.GaugeMetricOrBuilder
            public boolean getSplitBucketForAppUpgrade() {
                return ((GaugeMetric) this.instance).getSplitBucketForAppUpgrade();
            }

            public Builder setSplitBucketForAppUpgrade(boolean value) {
                copyOnWrite();
                ((GaugeMetric) this.instance).setSplitBucketForAppUpgrade(value);
                return this;
            }

            public Builder clearSplitBucketForAppUpgrade() {
                copyOnWrite();
                ((GaugeMetric) this.instance).clearSplitBucketForAppUpgrade();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new GaugeMetric();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.links_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    GaugeMetric other = (GaugeMetric) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    this.what_ = visitor.visitLong(hasWhat(), this.what_, other.hasWhat(), other.what_);
                    this.triggerEvent_ = visitor.visitLong(hasTriggerEvent(), this.triggerEvent_, other.hasTriggerEvent(), other.triggerEvent_);
                    this.gaugeFieldsFilter_ = (FieldFilter) visitor.visitMessage(this.gaugeFieldsFilter_, other.gaugeFieldsFilter_);
                    this.condition_ = visitor.visitLong(hasCondition(), this.condition_, other.hasCondition(), other.condition_);
                    this.dimensionsInWhat_ = (FieldMatcher) visitor.visitMessage(this.dimensionsInWhat_, other.dimensionsInWhat_);
                    this.dimensionsInCondition_ = (FieldMatcher) visitor.visitMessage(this.dimensionsInCondition_, other.dimensionsInCondition_);
                    this.bucket_ = visitor.visitInt(hasBucket(), this.bucket_, other.hasBucket(), other.bucket_);
                    this.links_ = visitor.visitList(this.links_, other.links_);
                    this.samplingType_ = visitor.visitInt(hasSamplingType(), this.samplingType_, other.hasSamplingType(), other.samplingType_);
                    this.minBucketSizeNanos_ = visitor.visitLong(hasMinBucketSizeNanos(), this.minBucketSizeNanos_, other.hasMinBucketSizeNanos(), other.minBucketSizeNanos_);
                    this.maxNumGaugeAtomsPerBucket_ = visitor.visitLong(hasMaxNumGaugeAtomsPerBucket(), this.maxNumGaugeAtomsPerBucket_, other.hasMaxNumGaugeAtomsPerBucket(), other.maxNumGaugeAtomsPerBucket_);
                    this.maxPullDelaySec_ = visitor.visitInt(hasMaxPullDelaySec(), this.maxPullDelaySec_, other.hasMaxPullDelaySec(), other.maxPullDelaySec_);
                    this.splitBucketForAppUpgrade_ = visitor.visitBoolean(hasSplitBucketForAppUpgrade(), this.splitBucketForAppUpgrade_, other.hasSplitBucketForAppUpgrade(), other.splitBucketForAppUpgrade_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.id_ = input.readInt64();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.what_ = input.readInt64();
                                    break;
                                case 26:
                                    FieldFilter.Builder subBuilder = null;
                                    if ((this.bitField0_ & 8) == 8) {
                                        subBuilder = (FieldFilter.Builder) this.gaugeFieldsFilter_.toBuilder();
                                    }
                                    this.gaugeFieldsFilter_ = (FieldFilter) input.readMessage(FieldFilter.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.gaugeFieldsFilter_);
                                        this.gaugeFieldsFilter_ = (FieldFilter) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 8;
                                    break;
                                case 32:
                                    this.bitField0_ |= 16;
                                    this.condition_ = input.readInt64();
                                    break;
                                case 42:
                                    FieldMatcher.Builder subBuilder2 = null;
                                    if ((this.bitField0_ & 32) == 32) {
                                        subBuilder2 = (FieldMatcher.Builder) this.dimensionsInWhat_.toBuilder();
                                    }
                                    this.dimensionsInWhat_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((GeneratedMessageLite) this.dimensionsInWhat_);
                                        this.dimensionsInWhat_ = (FieldMatcher) subBuilder2.buildPartial();
                                    }
                                    this.bitField0_ |= 32;
                                    break;
                                case 48:
                                    int rawValue = input.readEnum();
                                    if (TimeUnit.forNumber(rawValue) != null) {
                                        this.bitField0_ |= 128;
                                        this.bucket_ = rawValue;
                                        break;
                                    } else {
                                        super.mergeVarintField(6, rawValue);
                                        break;
                                    }
                                case 58:
                                    if (!this.links_.isModifiable()) {
                                        this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
                                    }
                                    this.links_.add((MetricConditionLink) input.readMessage(MetricConditionLink.parser(), extensionRegistry));
                                    break;
                                case 66:
                                    FieldMatcher.Builder subBuilder3 = null;
                                    if ((this.bitField0_ & 64) == 64) {
                                        subBuilder3 = (FieldMatcher.Builder) this.dimensionsInCondition_.toBuilder();
                                    }
                                    this.dimensionsInCondition_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                    if (subBuilder3 != null) {
                                        subBuilder3.mergeFrom((GeneratedMessageLite) this.dimensionsInCondition_);
                                        this.dimensionsInCondition_ = (FieldMatcher) subBuilder3.buildPartial();
                                    }
                                    this.bitField0_ |= 64;
                                    break;
                                case 72:
                                    int rawValue2 = input.readEnum();
                                    if (SamplingType.forNumber(rawValue2) != null) {
                                        this.bitField0_ |= 256;
                                        this.samplingType_ = rawValue2;
                                        break;
                                    } else {
                                        super.mergeVarintField(9, rawValue2);
                                        break;
                                    }
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.minBucketSizeNanos_ = input.readInt64();
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.maxNumGaugeAtomsPerBucket_ = input.readInt64();
                                    break;
                                case 96:
                                    this.bitField0_ |= 4;
                                    this.triggerEvent_ = input.readInt64();
                                    break;
                                case 104:
                                    this.bitField0_ |= 2048;
                                    this.maxPullDelaySec_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 4096;
                                    this.splitBucketForAppUpgrade_ = input.readBool();
                                    break;
                                default:
                                    if (parseUnknownField(tag, input)) {
                                        break;
                                    } else {
                                        done = true;
                                        break;
                                    }
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
                        synchronized (GaugeMetric.class) {
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

        public static GaugeMetric getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GaugeMetric> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ValueMetric extends GeneratedMessageLite<ValueMetric, Builder> implements ValueMetricOrBuilder {
        public static final int AGGREGATION_TYPE_FIELD_NUMBER = 8;
        public static final int BUCKET_FIELD_NUMBER = 6;
        public static final int CONDITION_FIELD_NUMBER = 4;
        private static final ValueMetric DEFAULT_INSTANCE = new ValueMetric();
        public static final int DIMENSIONS_IN_CONDITION_FIELD_NUMBER = 9;
        public static final int DIMENSIONS_IN_WHAT_FIELD_NUMBER = 5;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int LINKS_FIELD_NUMBER = 7;
        public static final int MAX_PULL_DELAY_SEC_FIELD_NUMBER = 16;
        public static final int MIN_BUCKET_SIZE_NANOS_FIELD_NUMBER = 10;
        private static volatile Parser<ValueMetric> PARSER = null;
        public static final int SKIP_ZERO_DIFF_OUTPUT_FIELD_NUMBER = 14;
        public static final int SPLIT_BUCKET_FOR_APP_UPGRADE_FIELD_NUMBER = 17;
        public static final int USE_ABSOLUTE_VALUE_ON_RESET_FIELD_NUMBER = 11;
        public static final int USE_DIFF_FIELD_NUMBER = 12;
        public static final int USE_ZERO_DEFAULT_BASE_FIELD_NUMBER = 15;
        public static final int VALUE_DIRECTION_FIELD_NUMBER = 13;
        public static final int VALUE_FIELD_FIELD_NUMBER = 3;
        public static final int WHAT_FIELD_NUMBER = 2;
        private int aggregationType_ = 1;
        private int bitField0_;
        private int bucket_ = 0;
        private long condition_ = 0;
        private FieldMatcher dimensionsInCondition_;
        private FieldMatcher dimensionsInWhat_;
        private long id_ = 0;
        private Internal.ProtobufList<MetricConditionLink> links_ = emptyProtobufList();
        private int maxPullDelaySec_ = 10;
        private long minBucketSizeNanos_ = 0;
        private boolean skipZeroDiffOutput_ = true;
        private boolean splitBucketForAppUpgrade_ = true;
        private boolean useAbsoluteValueOnReset_ = false;
        private boolean useDiff_ = false;
        private boolean useZeroDefaultBase_ = false;
        private int valueDirection_ = 1;
        private FieldMatcher valueField_;
        private long what_ = 0;

        private ValueMetric() {
        }

        public enum AggregationType implements Internal.EnumLite {
            SUM(1),
            MIN(2),
            MAX(3),
            AVG(4);
            
            public static final int AVG_VALUE = 4;
            public static final int MAX_VALUE = 3;
            public static final int MIN_VALUE = 2;
            public static final int SUM_VALUE = 1;
            private static final Internal.EnumLiteMap<AggregationType> internalValueMap = new Internal.EnumLiteMap<AggregationType>() {
                /* class com.android.internal.os.StatsdConfigProto.ValueMetric.AggregationType.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public AggregationType findValueByNumber(int number) {
                    return AggregationType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static AggregationType valueOf(int value2) {
                return forNumber(value2);
            }

            public static AggregationType forNumber(int value2) {
                if (value2 == 1) {
                    return SUM;
                }
                if (value2 == 2) {
                    return MIN;
                }
                if (value2 == 3) {
                    return MAX;
                }
                if (value2 != 4) {
                    return null;
                }
                return AVG;
            }

            public static Internal.EnumLiteMap<AggregationType> internalGetValueMap() {
                return internalValueMap;
            }

            private AggregationType(int value2) {
                this.value = value2;
            }
        }

        public enum ValueDirection implements Internal.EnumLite {
            UNKNOWN(0),
            INCREASING(1),
            DECREASING(2),
            ANY(3);
            
            public static final int ANY_VALUE = 3;
            public static final int DECREASING_VALUE = 2;
            public static final int INCREASING_VALUE = 1;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<ValueDirection> internalValueMap = new Internal.EnumLiteMap<ValueDirection>() {
                /* class com.android.internal.os.StatsdConfigProto.ValueMetric.ValueDirection.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ValueDirection findValueByNumber(int number) {
                    return ValueDirection.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ValueDirection valueOf(int value2) {
                return forNumber(value2);
            }

            public static ValueDirection forNumber(int value2) {
                if (value2 == 0) {
                    return UNKNOWN;
                }
                if (value2 == 1) {
                    return INCREASING;
                }
                if (value2 == 2) {
                    return DECREASING;
                }
                if (value2 != 3) {
                    return null;
                }
                return ANY;
            }

            public static Internal.EnumLiteMap<ValueDirection> internalGetValueMap() {
                return internalValueMap;
            }

            private ValueDirection(int value2) {
                this.value = value2;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasWhat() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public long getWhat() {
            return this.what_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWhat(long value) {
            this.bitField0_ |= 2;
            this.what_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWhat() {
            this.bitField0_ &= -3;
            this.what_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasValueField() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public FieldMatcher getValueField() {
            FieldMatcher fieldMatcher = this.valueField_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValueField(FieldMatcher value) {
            if (value != null) {
                this.valueField_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValueField(FieldMatcher.Builder builderForValue) {
            this.valueField_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeValueField(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.valueField_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.valueField_ = value;
            } else {
                this.valueField_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.valueField_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValueField() {
            this.valueField_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasCondition() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public long getCondition() {
            return this.condition_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCondition(long value) {
            this.bitField0_ |= 8;
            this.condition_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCondition() {
            this.bitField0_ &= -9;
            this.condition_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasDimensionsInWhat() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public FieldMatcher getDimensionsInWhat() {
            FieldMatcher fieldMatcher = this.dimensionsInWhat_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInWhat(FieldMatcher value) {
            if (value != null) {
                this.dimensionsInWhat_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInWhat(FieldMatcher.Builder builderForValue) {
            this.dimensionsInWhat_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDimensionsInWhat(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.dimensionsInWhat_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.dimensionsInWhat_ = value;
            } else {
                this.dimensionsInWhat_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.dimensionsInWhat_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDimensionsInWhat() {
            this.dimensionsInWhat_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasDimensionsInCondition() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public FieldMatcher getDimensionsInCondition() {
            FieldMatcher fieldMatcher = this.dimensionsInCondition_;
            return fieldMatcher == null ? FieldMatcher.getDefaultInstance() : fieldMatcher;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInCondition(FieldMatcher value) {
            if (value != null) {
                this.dimensionsInCondition_ = value;
                this.bitField0_ |= 32;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDimensionsInCondition(FieldMatcher.Builder builderForValue) {
            this.dimensionsInCondition_ = (FieldMatcher) builderForValue.build();
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDimensionsInCondition(FieldMatcher value) {
            FieldMatcher fieldMatcher = this.dimensionsInCondition_;
            if (fieldMatcher == null || fieldMatcher == FieldMatcher.getDefaultInstance()) {
                this.dimensionsInCondition_ = value;
            } else {
                this.dimensionsInCondition_ = (FieldMatcher) ((FieldMatcher.Builder) FieldMatcher.newBuilder(this.dimensionsInCondition_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDimensionsInCondition() {
            this.dimensionsInCondition_ = null;
            this.bitField0_ &= -33;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasBucket() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public TimeUnit getBucket() {
            TimeUnit result = TimeUnit.forNumber(this.bucket_);
            return result == null ? TimeUnit.TIME_UNIT_UNSPECIFIED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBucket(TimeUnit value) {
            if (value != null) {
                this.bitField0_ |= 64;
                this.bucket_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBucket() {
            this.bitField0_ &= -65;
            this.bucket_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public List<MetricConditionLink> getLinksList() {
            return this.links_;
        }

        public List<? extends MetricConditionLinkOrBuilder> getLinksOrBuilderList() {
            return this.links_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public int getLinksCount() {
            return this.links_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public MetricConditionLink getLinks(int index) {
            return this.links_.get(index);
        }

        public MetricConditionLinkOrBuilder getLinksOrBuilder(int index) {
            return this.links_.get(index);
        }

        private void ensureLinksIsMutable() {
            if (!this.links_.isModifiable()) {
                this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.set(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink value) {
            if (value != null) {
                ensureLinksIsMutable();
                this.links_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add((MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addLinks(int index, MetricConditionLink.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add(index, (MetricConditionLink) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllLinks(Iterable<? extends MetricConditionLink> values) {
            ensureLinksIsMutable();
            AbstractMessageLite.addAll(values, this.links_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLinks() {
            this.links_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeLinks(int index) {
            ensureLinksIsMutable();
            this.links_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasAggregationType() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public AggregationType getAggregationType() {
            AggregationType result = AggregationType.forNumber(this.aggregationType_);
            return result == null ? AggregationType.SUM : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAggregationType(AggregationType value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.aggregationType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAggregationType() {
            this.bitField0_ &= -129;
            this.aggregationType_ = 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasMinBucketSizeNanos() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public long getMinBucketSizeNanos() {
            return this.minBucketSizeNanos_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMinBucketSizeNanos(long value) {
            this.bitField0_ |= 256;
            this.minBucketSizeNanos_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMinBucketSizeNanos() {
            this.bitField0_ &= -257;
            this.minBucketSizeNanos_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasUseAbsoluteValueOnReset() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean getUseAbsoluteValueOnReset() {
            return this.useAbsoluteValueOnReset_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUseAbsoluteValueOnReset(boolean value) {
            this.bitField0_ |= 512;
            this.useAbsoluteValueOnReset_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUseAbsoluteValueOnReset() {
            this.bitField0_ &= -513;
            this.useAbsoluteValueOnReset_ = false;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasUseDiff() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean getUseDiff() {
            return this.useDiff_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUseDiff(boolean value) {
            this.bitField0_ |= 1024;
            this.useDiff_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUseDiff() {
            this.bitField0_ &= -1025;
            this.useDiff_ = false;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasUseZeroDefaultBase() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean getUseZeroDefaultBase() {
            return this.useZeroDefaultBase_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUseZeroDefaultBase(boolean value) {
            this.bitField0_ |= 2048;
            this.useZeroDefaultBase_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUseZeroDefaultBase() {
            this.bitField0_ &= -2049;
            this.useZeroDefaultBase_ = false;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasValueDirection() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public ValueDirection getValueDirection() {
            ValueDirection result = ValueDirection.forNumber(this.valueDirection_);
            return result == null ? ValueDirection.INCREASING : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValueDirection(ValueDirection value) {
            if (value != null) {
                this.bitField0_ |= 4096;
                this.valueDirection_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValueDirection() {
            this.bitField0_ &= -4097;
            this.valueDirection_ = 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasSkipZeroDiffOutput() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean getSkipZeroDiffOutput() {
            return this.skipZeroDiffOutput_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSkipZeroDiffOutput(boolean value) {
            this.bitField0_ |= 8192;
            this.skipZeroDiffOutput_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSkipZeroDiffOutput() {
            this.bitField0_ &= -8193;
            this.skipZeroDiffOutput_ = true;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasMaxPullDelaySec() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public int getMaxPullDelaySec() {
            return this.maxPullDelaySec_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxPullDelaySec(int value) {
            this.bitField0_ |= 16384;
            this.maxPullDelaySec_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxPullDelaySec() {
            this.bitField0_ &= -16385;
            this.maxPullDelaySec_ = 10;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean hasSplitBucketForAppUpgrade() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
        public boolean getSplitBucketForAppUpgrade() {
            return this.splitBucketForAppUpgrade_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSplitBucketForAppUpgrade(boolean value) {
            this.bitField0_ |= 32768;
            this.splitBucketForAppUpgrade_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSplitBucketForAppUpgrade() {
            this.bitField0_ &= -32769;
            this.splitBucketForAppUpgrade_ = true;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.what_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getValueField());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.condition_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, getDimensionsInWhat());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeEnum(6, this.bucket_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                output.writeMessage(7, this.links_.get(i));
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeEnum(8, this.aggregationType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(9, getDimensionsInCondition());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(10, this.minBucketSizeNanos_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeBool(11, this.useAbsoluteValueOnReset_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeBool(12, this.useDiff_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeEnum(13, this.valueDirection_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeBool(14, this.skipZeroDiffOutput_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeBool(15, this.useZeroDefaultBase_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeInt32(16, this.maxPullDelaySec_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeBool(17, this.splitBucketForAppUpgrade_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.what_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getValueField());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.condition_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, getDimensionsInWhat());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeEnumSize(6, this.bucket_);
            }
            for (int i = 0; i < this.links_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(7, this.links_.get(i));
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeEnumSize(8, this.aggregationType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(9, getDimensionsInCondition());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(10, this.minBucketSizeNanos_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeBoolSize(11, this.useAbsoluteValueOnReset_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeBoolSize(12, this.useDiff_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeEnumSize(13, this.valueDirection_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeBoolSize(14, this.skipZeroDiffOutput_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeBoolSize(15, this.useZeroDefaultBase_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeInt32Size(16, this.maxPullDelaySec_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeBoolSize(17, this.splitBucketForAppUpgrade_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ValueMetric parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ValueMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ValueMetric parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ValueMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ValueMetric parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ValueMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ValueMetric parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ValueMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ValueMetric parseFrom(InputStream input) throws IOException {
            return (ValueMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ValueMetric parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ValueMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ValueMetric parseDelimitedFrom(InputStream input) throws IOException {
            return (ValueMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ValueMetric parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ValueMetric) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ValueMetric parseFrom(CodedInputStream input) throws IOException {
            return (ValueMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ValueMetric parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ValueMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ValueMetric prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ValueMetric, Builder> implements ValueMetricOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(ValueMetric.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasId() {
                return ((ValueMetric) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public long getId() {
                return ((ValueMetric) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasWhat() {
                return ((ValueMetric) this.instance).hasWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public long getWhat() {
                return ((ValueMetric) this.instance).getWhat();
            }

            public Builder setWhat(long value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setWhat(value);
                return this;
            }

            public Builder clearWhat() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasValueField() {
                return ((ValueMetric) this.instance).hasValueField();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public FieldMatcher getValueField() {
                return ((ValueMetric) this.instance).getValueField();
            }

            public Builder setValueField(FieldMatcher value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setValueField((ValueMetric) value);
                return this;
            }

            public Builder setValueField(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((ValueMetric) this.instance).setValueField((ValueMetric) builderForValue);
                return this;
            }

            public Builder mergeValueField(FieldMatcher value) {
                copyOnWrite();
                ((ValueMetric) this.instance).mergeValueField(value);
                return this;
            }

            public Builder clearValueField() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearValueField();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasCondition() {
                return ((ValueMetric) this.instance).hasCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public long getCondition() {
                return ((ValueMetric) this.instance).getCondition();
            }

            public Builder setCondition(long value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setCondition(value);
                return this;
            }

            public Builder clearCondition() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasDimensionsInWhat() {
                return ((ValueMetric) this.instance).hasDimensionsInWhat();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public FieldMatcher getDimensionsInWhat() {
                return ((ValueMetric) this.instance).getDimensionsInWhat();
            }

            public Builder setDimensionsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setDimensionsInWhat((ValueMetric) value);
                return this;
            }

            public Builder setDimensionsInWhat(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((ValueMetric) this.instance).setDimensionsInWhat((ValueMetric) builderForValue);
                return this;
            }

            public Builder mergeDimensionsInWhat(FieldMatcher value) {
                copyOnWrite();
                ((ValueMetric) this.instance).mergeDimensionsInWhat(value);
                return this;
            }

            public Builder clearDimensionsInWhat() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearDimensionsInWhat();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasDimensionsInCondition() {
                return ((ValueMetric) this.instance).hasDimensionsInCondition();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public FieldMatcher getDimensionsInCondition() {
                return ((ValueMetric) this.instance).getDimensionsInCondition();
            }

            public Builder setDimensionsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setDimensionsInCondition((ValueMetric) value);
                return this;
            }

            public Builder setDimensionsInCondition(FieldMatcher.Builder builderForValue) {
                copyOnWrite();
                ((ValueMetric) this.instance).setDimensionsInCondition((ValueMetric) builderForValue);
                return this;
            }

            public Builder mergeDimensionsInCondition(FieldMatcher value) {
                copyOnWrite();
                ((ValueMetric) this.instance).mergeDimensionsInCondition(value);
                return this;
            }

            public Builder clearDimensionsInCondition() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearDimensionsInCondition();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasBucket() {
                return ((ValueMetric) this.instance).hasBucket();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public TimeUnit getBucket() {
                return ((ValueMetric) this.instance).getBucket();
            }

            public Builder setBucket(TimeUnit value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setBucket(value);
                return this;
            }

            public Builder clearBucket() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearBucket();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public List<MetricConditionLink> getLinksList() {
                return Collections.unmodifiableList(((ValueMetric) this.instance).getLinksList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public int getLinksCount() {
                return ((ValueMetric) this.instance).getLinksCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public MetricConditionLink getLinks(int index) {
                return ((ValueMetric) this.instance).getLinks(index);
            }

            public Builder setLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setLinks((ValueMetric) index, (int) value);
                return this;
            }

            public Builder setLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((ValueMetric) this.instance).setLinks((ValueMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addLinks(MetricConditionLink value) {
                copyOnWrite();
                ((ValueMetric) this.instance).addLinks((ValueMetric) value);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink value) {
                copyOnWrite();
                ((ValueMetric) this.instance).addLinks((ValueMetric) index, (int) value);
                return this;
            }

            public Builder addLinks(MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((ValueMetric) this.instance).addLinks((ValueMetric) builderForValue);
                return this;
            }

            public Builder addLinks(int index, MetricConditionLink.Builder builderForValue) {
                copyOnWrite();
                ((ValueMetric) this.instance).addLinks((ValueMetric) index, (int) builderForValue);
                return this;
            }

            public Builder addAllLinks(Iterable<? extends MetricConditionLink> values) {
                copyOnWrite();
                ((ValueMetric) this.instance).addAllLinks(values);
                return this;
            }

            public Builder clearLinks() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearLinks();
                return this;
            }

            public Builder removeLinks(int index) {
                copyOnWrite();
                ((ValueMetric) this.instance).removeLinks(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasAggregationType() {
                return ((ValueMetric) this.instance).hasAggregationType();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public AggregationType getAggregationType() {
                return ((ValueMetric) this.instance).getAggregationType();
            }

            public Builder setAggregationType(AggregationType value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setAggregationType(value);
                return this;
            }

            public Builder clearAggregationType() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearAggregationType();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasMinBucketSizeNanos() {
                return ((ValueMetric) this.instance).hasMinBucketSizeNanos();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public long getMinBucketSizeNanos() {
                return ((ValueMetric) this.instance).getMinBucketSizeNanos();
            }

            public Builder setMinBucketSizeNanos(long value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setMinBucketSizeNanos(value);
                return this;
            }

            public Builder clearMinBucketSizeNanos() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearMinBucketSizeNanos();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasUseAbsoluteValueOnReset() {
                return ((ValueMetric) this.instance).hasUseAbsoluteValueOnReset();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean getUseAbsoluteValueOnReset() {
                return ((ValueMetric) this.instance).getUseAbsoluteValueOnReset();
            }

            public Builder setUseAbsoluteValueOnReset(boolean value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setUseAbsoluteValueOnReset(value);
                return this;
            }

            public Builder clearUseAbsoluteValueOnReset() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearUseAbsoluteValueOnReset();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasUseDiff() {
                return ((ValueMetric) this.instance).hasUseDiff();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean getUseDiff() {
                return ((ValueMetric) this.instance).getUseDiff();
            }

            public Builder setUseDiff(boolean value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setUseDiff(value);
                return this;
            }

            public Builder clearUseDiff() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearUseDiff();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasUseZeroDefaultBase() {
                return ((ValueMetric) this.instance).hasUseZeroDefaultBase();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean getUseZeroDefaultBase() {
                return ((ValueMetric) this.instance).getUseZeroDefaultBase();
            }

            public Builder setUseZeroDefaultBase(boolean value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setUseZeroDefaultBase(value);
                return this;
            }

            public Builder clearUseZeroDefaultBase() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearUseZeroDefaultBase();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasValueDirection() {
                return ((ValueMetric) this.instance).hasValueDirection();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public ValueDirection getValueDirection() {
                return ((ValueMetric) this.instance).getValueDirection();
            }

            public Builder setValueDirection(ValueDirection value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setValueDirection(value);
                return this;
            }

            public Builder clearValueDirection() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearValueDirection();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasSkipZeroDiffOutput() {
                return ((ValueMetric) this.instance).hasSkipZeroDiffOutput();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean getSkipZeroDiffOutput() {
                return ((ValueMetric) this.instance).getSkipZeroDiffOutput();
            }

            public Builder setSkipZeroDiffOutput(boolean value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setSkipZeroDiffOutput(value);
                return this;
            }

            public Builder clearSkipZeroDiffOutput() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearSkipZeroDiffOutput();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasMaxPullDelaySec() {
                return ((ValueMetric) this.instance).hasMaxPullDelaySec();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public int getMaxPullDelaySec() {
                return ((ValueMetric) this.instance).getMaxPullDelaySec();
            }

            public Builder setMaxPullDelaySec(int value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setMaxPullDelaySec(value);
                return this;
            }

            public Builder clearMaxPullDelaySec() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearMaxPullDelaySec();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean hasSplitBucketForAppUpgrade() {
                return ((ValueMetric) this.instance).hasSplitBucketForAppUpgrade();
            }

            @Override // com.android.internal.os.StatsdConfigProto.ValueMetricOrBuilder
            public boolean getSplitBucketForAppUpgrade() {
                return ((ValueMetric) this.instance).getSplitBucketForAppUpgrade();
            }

            public Builder setSplitBucketForAppUpgrade(boolean value) {
                copyOnWrite();
                ((ValueMetric) this.instance).setSplitBucketForAppUpgrade(value);
                return this;
            }

            public Builder clearSplitBucketForAppUpgrade() {
                copyOnWrite();
                ((ValueMetric) this.instance).clearSplitBucketForAppUpgrade();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ValueMetric();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.links_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ValueMetric other = (ValueMetric) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    this.what_ = visitor.visitLong(hasWhat(), this.what_, other.hasWhat(), other.what_);
                    this.valueField_ = (FieldMatcher) visitor.visitMessage(this.valueField_, other.valueField_);
                    this.condition_ = visitor.visitLong(hasCondition(), this.condition_, other.hasCondition(), other.condition_);
                    this.dimensionsInWhat_ = (FieldMatcher) visitor.visitMessage(this.dimensionsInWhat_, other.dimensionsInWhat_);
                    this.dimensionsInCondition_ = (FieldMatcher) visitor.visitMessage(this.dimensionsInCondition_, other.dimensionsInCondition_);
                    this.bucket_ = visitor.visitInt(hasBucket(), this.bucket_, other.hasBucket(), other.bucket_);
                    this.links_ = visitor.visitList(this.links_, other.links_);
                    this.aggregationType_ = visitor.visitInt(hasAggregationType(), this.aggregationType_, other.hasAggregationType(), other.aggregationType_);
                    this.minBucketSizeNanos_ = visitor.visitLong(hasMinBucketSizeNanos(), this.minBucketSizeNanos_, other.hasMinBucketSizeNanos(), other.minBucketSizeNanos_);
                    this.useAbsoluteValueOnReset_ = visitor.visitBoolean(hasUseAbsoluteValueOnReset(), this.useAbsoluteValueOnReset_, other.hasUseAbsoluteValueOnReset(), other.useAbsoluteValueOnReset_);
                    this.useDiff_ = visitor.visitBoolean(hasUseDiff(), this.useDiff_, other.hasUseDiff(), other.useDiff_);
                    this.useZeroDefaultBase_ = visitor.visitBoolean(hasUseZeroDefaultBase(), this.useZeroDefaultBase_, other.hasUseZeroDefaultBase(), other.useZeroDefaultBase_);
                    this.valueDirection_ = visitor.visitInt(hasValueDirection(), this.valueDirection_, other.hasValueDirection(), other.valueDirection_);
                    this.skipZeroDiffOutput_ = visitor.visitBoolean(hasSkipZeroDiffOutput(), this.skipZeroDiffOutput_, other.hasSkipZeroDiffOutput(), other.skipZeroDiffOutput_);
                    this.maxPullDelaySec_ = visitor.visitInt(hasMaxPullDelaySec(), this.maxPullDelaySec_, other.hasMaxPullDelaySec(), other.maxPullDelaySec_);
                    this.splitBucketForAppUpgrade_ = visitor.visitBoolean(hasSplitBucketForAppUpgrade(), this.splitBucketForAppUpgrade_, other.hasSplitBucketForAppUpgrade(), other.splitBucketForAppUpgrade_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.id_ = input.readInt64();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.what_ = input.readInt64();
                                    break;
                                case 26:
                                    FieldMatcher.Builder subBuilder = null;
                                    if ((this.bitField0_ & 4) == 4) {
                                        subBuilder = (FieldMatcher.Builder) this.valueField_.toBuilder();
                                    }
                                    this.valueField_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.valueField_);
                                        this.valueField_ = (FieldMatcher) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 4;
                                    break;
                                case 32:
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.condition_ = input.readInt64();
                                    break;
                                case 42:
                                    FieldMatcher.Builder subBuilder2 = null;
                                    if ((this.bitField0_ & 16) == 16) {
                                        subBuilder2 = (FieldMatcher.Builder) this.dimensionsInWhat_.toBuilder();
                                    }
                                    this.dimensionsInWhat_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((GeneratedMessageLite) this.dimensionsInWhat_);
                                        this.dimensionsInWhat_ = (FieldMatcher) subBuilder2.buildPartial();
                                    }
                                    this.bitField0_ |= 16;
                                    break;
                                case 48:
                                    int rawValue = input.readEnum();
                                    if (TimeUnit.forNumber(rawValue) != null) {
                                        this.bitField0_ |= 64;
                                        this.bucket_ = rawValue;
                                        break;
                                    } else {
                                        super.mergeVarintField(6, rawValue);
                                        break;
                                    }
                                case 58:
                                    if (!this.links_.isModifiable()) {
                                        this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
                                    }
                                    this.links_.add((MetricConditionLink) input.readMessage(MetricConditionLink.parser(), extensionRegistry));
                                    break;
                                case 64:
                                    int rawValue2 = input.readEnum();
                                    if (AggregationType.forNumber(rawValue2) != null) {
                                        this.bitField0_ |= 128;
                                        this.aggregationType_ = rawValue2;
                                        break;
                                    } else {
                                        super.mergeVarintField(8, rawValue2);
                                        break;
                                    }
                                case 74:
                                    FieldMatcher.Builder subBuilder3 = null;
                                    if ((this.bitField0_ & 32) == 32) {
                                        subBuilder3 = (FieldMatcher.Builder) this.dimensionsInCondition_.toBuilder();
                                    }
                                    this.dimensionsInCondition_ = (FieldMatcher) input.readMessage(FieldMatcher.parser(), extensionRegistry);
                                    if (subBuilder3 != null) {
                                        subBuilder3.mergeFrom((GeneratedMessageLite) this.dimensionsInCondition_);
                                        this.dimensionsInCondition_ = (FieldMatcher) subBuilder3.buildPartial();
                                    }
                                    this.bitField0_ |= 32;
                                    break;
                                case 80:
                                    this.bitField0_ |= 256;
                                    this.minBucketSizeNanos_ = input.readInt64();
                                    break;
                                case 88:
                                    this.bitField0_ |= 512;
                                    this.useAbsoluteValueOnReset_ = input.readBool();
                                    break;
                                case 96:
                                    this.bitField0_ |= 1024;
                                    this.useDiff_ = input.readBool();
                                    break;
                                case 104:
                                    int rawValue3 = input.readEnum();
                                    if (ValueDirection.forNumber(rawValue3) != null) {
                                        this.bitField0_ |= 4096;
                                        this.valueDirection_ = rawValue3;
                                        break;
                                    } else {
                                        super.mergeVarintField(13, rawValue3);
                                        break;
                                    }
                                case 112:
                                    this.bitField0_ |= 8192;
                                    this.skipZeroDiffOutput_ = input.readBool();
                                    break;
                                case 120:
                                    this.bitField0_ |= 2048;
                                    this.useZeroDefaultBase_ = input.readBool();
                                    break;
                                case 128:
                                    this.bitField0_ |= 16384;
                                    this.maxPullDelaySec_ = input.readInt32();
                                    break;
                                case 136:
                                    this.bitField0_ |= 32768;
                                    this.splitBucketForAppUpgrade_ = input.readBool();
                                    break;
                                default:
                                    if (parseUnknownField(tag, input)) {
                                        break;
                                    } else {
                                        done = true;
                                        break;
                                    }
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
                        synchronized (ValueMetric.class) {
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

        public static ValueMetric getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ValueMetric> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Alert extends GeneratedMessageLite<Alert, Builder> implements AlertOrBuilder {
        private static final Alert DEFAULT_INSTANCE = new Alert();
        public static final int ID_FIELD_NUMBER = 1;
        public static final int METRIC_ID_FIELD_NUMBER = 2;
        public static final int NUM_BUCKETS_FIELD_NUMBER = 3;
        private static volatile Parser<Alert> PARSER = null;
        public static final int REFRACTORY_PERIOD_SECS_FIELD_NUMBER = 4;
        public static final int TRIGGER_IF_SUM_GT_FIELD_NUMBER = 5;
        private int bitField0_;
        private long id_ = 0;
        private long metricId_ = 0;
        private int numBuckets_ = 0;
        private int refractoryPeriodSecs_ = 0;
        private double triggerIfSumGt_ = 0.0d;

        private Alert() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public boolean hasMetricId() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public long getMetricId() {
            return this.metricId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMetricId(long value) {
            this.bitField0_ |= 2;
            this.metricId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMetricId() {
            this.bitField0_ &= -3;
            this.metricId_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public boolean hasNumBuckets() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public int getNumBuckets() {
            return this.numBuckets_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNumBuckets(int value) {
            this.bitField0_ |= 4;
            this.numBuckets_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNumBuckets() {
            this.bitField0_ &= -5;
            this.numBuckets_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public boolean hasRefractoryPeriodSecs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public int getRefractoryPeriodSecs() {
            return this.refractoryPeriodSecs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRefractoryPeriodSecs(int value) {
            this.bitField0_ |= 8;
            this.refractoryPeriodSecs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRefractoryPeriodSecs() {
            this.bitField0_ &= -9;
            this.refractoryPeriodSecs_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public boolean hasTriggerIfSumGt() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
        public double getTriggerIfSumGt() {
            return this.triggerIfSumGt_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTriggerIfSumGt(double value) {
            this.bitField0_ |= 16;
            this.triggerIfSumGt_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTriggerIfSumGt() {
            this.bitField0_ &= -17;
            this.triggerIfSumGt_ = 0.0d;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.metricId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.numBuckets_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.refractoryPeriodSecs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeDouble(5, this.triggerIfSumGt_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.metricId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.numBuckets_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.refractoryPeriodSecs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeDoubleSize(5, this.triggerIfSumGt_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Alert parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Alert parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Alert parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Alert parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Alert parseFrom(InputStream input) throws IOException {
            return (Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Alert parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Alert parseDelimitedFrom(InputStream input) throws IOException {
            return (Alert) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Alert parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Alert) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Alert parseFrom(CodedInputStream input) throws IOException {
            return (Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Alert parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Alert prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Alert, Builder> implements AlertOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Alert.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public boolean hasId() {
                return ((Alert) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public long getId() {
                return ((Alert) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((Alert) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Alert) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public boolean hasMetricId() {
                return ((Alert) this.instance).hasMetricId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public long getMetricId() {
                return ((Alert) this.instance).getMetricId();
            }

            public Builder setMetricId(long value) {
                copyOnWrite();
                ((Alert) this.instance).setMetricId(value);
                return this;
            }

            public Builder clearMetricId() {
                copyOnWrite();
                ((Alert) this.instance).clearMetricId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public boolean hasNumBuckets() {
                return ((Alert) this.instance).hasNumBuckets();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public int getNumBuckets() {
                return ((Alert) this.instance).getNumBuckets();
            }

            public Builder setNumBuckets(int value) {
                copyOnWrite();
                ((Alert) this.instance).setNumBuckets(value);
                return this;
            }

            public Builder clearNumBuckets() {
                copyOnWrite();
                ((Alert) this.instance).clearNumBuckets();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public boolean hasRefractoryPeriodSecs() {
                return ((Alert) this.instance).hasRefractoryPeriodSecs();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public int getRefractoryPeriodSecs() {
                return ((Alert) this.instance).getRefractoryPeriodSecs();
            }

            public Builder setRefractoryPeriodSecs(int value) {
                copyOnWrite();
                ((Alert) this.instance).setRefractoryPeriodSecs(value);
                return this;
            }

            public Builder clearRefractoryPeriodSecs() {
                copyOnWrite();
                ((Alert) this.instance).clearRefractoryPeriodSecs();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public boolean hasTriggerIfSumGt() {
                return ((Alert) this.instance).hasTriggerIfSumGt();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlertOrBuilder
            public double getTriggerIfSumGt() {
                return ((Alert) this.instance).getTriggerIfSumGt();
            }

            public Builder setTriggerIfSumGt(double value) {
                copyOnWrite();
                ((Alert) this.instance).setTriggerIfSumGt(value);
                return this;
            }

            public Builder clearTriggerIfSumGt() {
                copyOnWrite();
                ((Alert) this.instance).clearTriggerIfSumGt();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Alert();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Alert other = (Alert) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    this.metricId_ = visitor.visitLong(hasMetricId(), this.metricId_, other.hasMetricId(), other.metricId_);
                    this.numBuckets_ = visitor.visitInt(hasNumBuckets(), this.numBuckets_, other.hasNumBuckets(), other.numBuckets_);
                    this.refractoryPeriodSecs_ = visitor.visitInt(hasRefractoryPeriodSecs(), this.refractoryPeriodSecs_, other.hasRefractoryPeriodSecs(), other.refractoryPeriodSecs_);
                    this.triggerIfSumGt_ = visitor.visitDouble(hasTriggerIfSumGt(), this.triggerIfSumGt_, other.hasTriggerIfSumGt(), other.triggerIfSumGt_);
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
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.metricId_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.numBuckets_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.refractoryPeriodSecs_ = input.readInt32();
                            } else if (tag == 41) {
                                this.bitField0_ |= 16;
                                this.triggerIfSumGt_ = input.readDouble();
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
                        synchronized (Alert.class) {
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

        public static Alert getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Alert> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Alarm extends GeneratedMessageLite<Alarm, Builder> implements AlarmOrBuilder {
        private static final Alarm DEFAULT_INSTANCE = new Alarm();
        public static final int ID_FIELD_NUMBER = 1;
        public static final int OFFSET_MILLIS_FIELD_NUMBER = 2;
        private static volatile Parser<Alarm> PARSER = null;
        public static final int PERIOD_MILLIS_FIELD_NUMBER = 3;
        private int bitField0_;
        private long id_ = 0;
        private long offsetMillis_ = 0;
        private long periodMillis_ = 0;

        private Alarm() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
        public boolean hasOffsetMillis() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
        public long getOffsetMillis() {
            return this.offsetMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOffsetMillis(long value) {
            this.bitField0_ |= 2;
            this.offsetMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOffsetMillis() {
            this.bitField0_ &= -3;
            this.offsetMillis_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
        public boolean hasPeriodMillis() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
        public long getPeriodMillis() {
            return this.periodMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPeriodMillis(long value) {
            this.bitField0_ |= 4;
            this.periodMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPeriodMillis() {
            this.bitField0_ &= -5;
            this.periodMillis_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.offsetMillis_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.periodMillis_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.offsetMillis_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.periodMillis_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Alarm parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Alarm parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Alarm parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Alarm parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Alarm parseFrom(InputStream input) throws IOException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Alarm parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Alarm parseDelimitedFrom(InputStream input) throws IOException {
            return (Alarm) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Alarm parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Alarm) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Alarm parseFrom(CodedInputStream input) throws IOException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Alarm parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Alarm prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Alarm, Builder> implements AlarmOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Alarm.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
            public boolean hasId() {
                return ((Alarm) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
            public long getId() {
                return ((Alarm) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((Alarm) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Alarm) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
            public boolean hasOffsetMillis() {
                return ((Alarm) this.instance).hasOffsetMillis();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
            public long getOffsetMillis() {
                return ((Alarm) this.instance).getOffsetMillis();
            }

            public Builder setOffsetMillis(long value) {
                copyOnWrite();
                ((Alarm) this.instance).setOffsetMillis(value);
                return this;
            }

            public Builder clearOffsetMillis() {
                copyOnWrite();
                ((Alarm) this.instance).clearOffsetMillis();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
            public boolean hasPeriodMillis() {
                return ((Alarm) this.instance).hasPeriodMillis();
            }

            @Override // com.android.internal.os.StatsdConfigProto.AlarmOrBuilder
            public long getPeriodMillis() {
                return ((Alarm) this.instance).getPeriodMillis();
            }

            public Builder setPeriodMillis(long value) {
                copyOnWrite();
                ((Alarm) this.instance).setPeriodMillis(value);
                return this;
            }

            public Builder clearPeriodMillis() {
                copyOnWrite();
                ((Alarm) this.instance).clearPeriodMillis();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Alarm();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Alarm other = (Alarm) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    this.offsetMillis_ = visitor.visitLong(hasOffsetMillis(), this.offsetMillis_, other.hasOffsetMillis(), other.offsetMillis_);
                    this.periodMillis_ = visitor.visitLong(hasPeriodMillis(), this.periodMillis_, other.hasPeriodMillis(), other.periodMillis_);
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
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.offsetMillis_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.periodMillis_ = input.readInt64();
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
                        synchronized (Alarm.class) {
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

        public static Alarm getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Alarm> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class IncidentdDetails extends GeneratedMessageLite<IncidentdDetails, Builder> implements IncidentdDetailsOrBuilder {
        public static final int ALERT_DESCRIPTION_FIELD_NUMBER = 5;
        private static final IncidentdDetails DEFAULT_INSTANCE = new IncidentdDetails();
        public static final int DEST_FIELD_NUMBER = 2;
        private static volatile Parser<IncidentdDetails> PARSER = null;
        public static final int RECEIVER_CLS_FIELD_NUMBER = 4;
        public static final int RECEIVER_PKG_FIELD_NUMBER = 3;
        public static final int SECTION_FIELD_NUMBER = 1;
        private String alertDescription_ = "";
        private int bitField0_;
        private int dest_ = 0;
        private String receiverCls_ = "";
        private String receiverPkg_ = "";
        private Internal.IntList section_ = emptyIntList();

        private IncidentdDetails() {
        }

        public enum Destination implements Internal.EnumLite {
            AUTOMATIC(0),
            EXPLICIT(1);
            
            public static final int AUTOMATIC_VALUE = 0;
            public static final int EXPLICIT_VALUE = 1;
            private static final Internal.EnumLiteMap<Destination> internalValueMap = new Internal.EnumLiteMap<Destination>() {
                /* class com.android.internal.os.StatsdConfigProto.IncidentdDetails.Destination.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Destination findValueByNumber(int number) {
                    return Destination.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Destination valueOf(int value2) {
                return forNumber(value2);
            }

            public static Destination forNumber(int value2) {
                if (value2 == 0) {
                    return AUTOMATIC;
                }
                if (value2 != 1) {
                    return null;
                }
                return EXPLICIT;
            }

            public static Internal.EnumLiteMap<Destination> internalGetValueMap() {
                return internalValueMap;
            }

            private Destination(int value2) {
                this.value = value2;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public List<Integer> getSectionList() {
            return this.section_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public int getSectionCount() {
            return this.section_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public int getSection(int index) {
            return this.section_.getInt(index);
        }

        private void ensureSectionIsMutable() {
            if (!this.section_.isModifiable()) {
                this.section_ = GeneratedMessageLite.mutableCopy(this.section_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSection(int index, int value) {
            ensureSectionIsMutable();
            this.section_.setInt(index, value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSection(int value) {
            ensureSectionIsMutable();
            this.section_.addInt(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllSection(Iterable<? extends Integer> values) {
            ensureSectionIsMutable();
            AbstractMessageLite.addAll(values, this.section_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSection() {
            this.section_ = emptyIntList();
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public boolean hasDest() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public Destination getDest() {
            Destination result = Destination.forNumber(this.dest_);
            return result == null ? Destination.AUTOMATIC : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDest(Destination value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.dest_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDest() {
            this.bitField0_ &= -2;
            this.dest_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public boolean hasReceiverPkg() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public String getReceiverPkg() {
            return this.receiverPkg_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public ByteString getReceiverPkgBytes() {
            return ByteString.copyFromUtf8(this.receiverPkg_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReceiverPkg(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.receiverPkg_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearReceiverPkg() {
            this.bitField0_ &= -3;
            this.receiverPkg_ = getDefaultInstance().getReceiverPkg();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReceiverPkgBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.receiverPkg_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public boolean hasReceiverCls() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public String getReceiverCls() {
            return this.receiverCls_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public ByteString getReceiverClsBytes() {
            return ByteString.copyFromUtf8(this.receiverCls_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReceiverCls(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.receiverCls_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearReceiverCls() {
            this.bitField0_ &= -5;
            this.receiverCls_ = getDefaultInstance().getReceiverCls();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReceiverClsBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.receiverCls_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public boolean hasAlertDescription() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public String getAlertDescription() {
            return this.alertDescription_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
        public ByteString getAlertDescriptionBytes() {
            return ByteString.copyFromUtf8(this.alertDescription_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlertDescription(String value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.alertDescription_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAlertDescription() {
            this.bitField0_ &= -9;
            this.alertDescription_ = getDefaultInstance().getAlertDescription();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlertDescriptionBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.alertDescription_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.section_.size(); i++) {
                output.writeInt32(1, this.section_.getInt(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(2, this.dest_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(3, getReceiverPkg());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(4, getReceiverCls());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeString(5, getAlertDescription());
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
            for (int i = 0; i < this.section_.size(); i++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.section_.getInt(i));
            }
            int size2 = 0 + dataSize + (getSectionList().size() * 1);
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeEnumSize(2, this.dest_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(3, getReceiverPkg());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(4, getReceiverCls());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeStringSize(5, getAlertDescription());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static IncidentdDetails parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (IncidentdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IncidentdDetails parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IncidentdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IncidentdDetails parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (IncidentdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IncidentdDetails parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IncidentdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IncidentdDetails parseFrom(InputStream input) throws IOException {
            return (IncidentdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static IncidentdDetails parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IncidentdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static IncidentdDetails parseDelimitedFrom(InputStream input) throws IOException {
            return (IncidentdDetails) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static IncidentdDetails parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IncidentdDetails) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static IncidentdDetails parseFrom(CodedInputStream input) throws IOException {
            return (IncidentdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static IncidentdDetails parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IncidentdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(IncidentdDetails prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<IncidentdDetails, Builder> implements IncidentdDetailsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(IncidentdDetails.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public List<Integer> getSectionList() {
                return Collections.unmodifiableList(((IncidentdDetails) this.instance).getSectionList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public int getSectionCount() {
                return ((IncidentdDetails) this.instance).getSectionCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public int getSection(int index) {
                return ((IncidentdDetails) this.instance).getSection(index);
            }

            public Builder setSection(int index, int value) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).setSection(index, value);
                return this;
            }

            public Builder addSection(int value) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).addSection(value);
                return this;
            }

            public Builder addAllSection(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).addAllSection(values);
                return this;
            }

            public Builder clearSection() {
                copyOnWrite();
                ((IncidentdDetails) this.instance).clearSection();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public boolean hasDest() {
                return ((IncidentdDetails) this.instance).hasDest();
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public Destination getDest() {
                return ((IncidentdDetails) this.instance).getDest();
            }

            public Builder setDest(Destination value) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).setDest(value);
                return this;
            }

            public Builder clearDest() {
                copyOnWrite();
                ((IncidentdDetails) this.instance).clearDest();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public boolean hasReceiverPkg() {
                return ((IncidentdDetails) this.instance).hasReceiverPkg();
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public String getReceiverPkg() {
                return ((IncidentdDetails) this.instance).getReceiverPkg();
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public ByteString getReceiverPkgBytes() {
                return ((IncidentdDetails) this.instance).getReceiverPkgBytes();
            }

            public Builder setReceiverPkg(String value) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).setReceiverPkg(value);
                return this;
            }

            public Builder clearReceiverPkg() {
                copyOnWrite();
                ((IncidentdDetails) this.instance).clearReceiverPkg();
                return this;
            }

            public Builder setReceiverPkgBytes(ByteString value) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).setReceiverPkgBytes(value);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public boolean hasReceiverCls() {
                return ((IncidentdDetails) this.instance).hasReceiverCls();
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public String getReceiverCls() {
                return ((IncidentdDetails) this.instance).getReceiverCls();
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public ByteString getReceiverClsBytes() {
                return ((IncidentdDetails) this.instance).getReceiverClsBytes();
            }

            public Builder setReceiverCls(String value) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).setReceiverCls(value);
                return this;
            }

            public Builder clearReceiverCls() {
                copyOnWrite();
                ((IncidentdDetails) this.instance).clearReceiverCls();
                return this;
            }

            public Builder setReceiverClsBytes(ByteString value) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).setReceiverClsBytes(value);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public boolean hasAlertDescription() {
                return ((IncidentdDetails) this.instance).hasAlertDescription();
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public String getAlertDescription() {
                return ((IncidentdDetails) this.instance).getAlertDescription();
            }

            @Override // com.android.internal.os.StatsdConfigProto.IncidentdDetailsOrBuilder
            public ByteString getAlertDescriptionBytes() {
                return ((IncidentdDetails) this.instance).getAlertDescriptionBytes();
            }

            public Builder setAlertDescription(String value) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).setAlertDescription(value);
                return this;
            }

            public Builder clearAlertDescription() {
                copyOnWrite();
                ((IncidentdDetails) this.instance).clearAlertDescription();
                return this;
            }

            public Builder setAlertDescriptionBytes(ByteString value) {
                copyOnWrite();
                ((IncidentdDetails) this.instance).setAlertDescriptionBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new IncidentdDetails();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.section_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    IncidentdDetails other = (IncidentdDetails) arg1;
                    this.section_ = visitor.visitIntList(this.section_, other.section_);
                    this.dest_ = visitor.visitInt(hasDest(), this.dest_, other.hasDest(), other.dest_);
                    this.receiverPkg_ = visitor.visitString(hasReceiverPkg(), this.receiverPkg_, other.hasReceiverPkg(), other.receiverPkg_);
                    this.receiverCls_ = visitor.visitString(hasReceiverCls(), this.receiverCls_, other.hasReceiverCls(), other.receiverCls_);
                    this.alertDescription_ = visitor.visitString(hasAlertDescription(), this.alertDescription_, other.hasAlertDescription(), other.alertDescription_);
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
                                if (!this.section_.isModifiable()) {
                                    this.section_ = GeneratedMessageLite.mutableCopy(this.section_);
                                }
                                this.section_.addInt(input.readInt32());
                            } else if (tag == 10) {
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.section_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.section_ = GeneratedMessageLite.mutableCopy(this.section_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.section_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                            } else if (tag == 16) {
                                int rawValue = input.readEnum();
                                if (Destination.forNumber(rawValue) == null) {
                                    super.mergeVarintField(2, rawValue);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.dest_ = rawValue;
                                }
                            } else if (tag == 26) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.receiverPkg_ = s;
                            } else if (tag == 34) {
                                String s2 = input.readString();
                                this.bitField0_ |= 4;
                                this.receiverCls_ = s2;
                            } else if (tag == 42) {
                                String s3 = input.readString();
                                this.bitField0_ = 8 | this.bitField0_;
                                this.alertDescription_ = s3;
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
                        synchronized (IncidentdDetails.class) {
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

        public static IncidentdDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<IncidentdDetails> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PerfettoDetails extends GeneratedMessageLite<PerfettoDetails, Builder> implements PerfettoDetailsOrBuilder {
        private static final PerfettoDetails DEFAULT_INSTANCE = new PerfettoDetails();
        private static volatile Parser<PerfettoDetails> PARSER = null;
        public static final int TRACE_CONFIG_FIELD_NUMBER = 1;
        private int bitField0_;
        private ByteString traceConfig_ = ByteString.EMPTY;

        private PerfettoDetails() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.PerfettoDetailsOrBuilder
        public boolean hasTraceConfig() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.PerfettoDetailsOrBuilder
        public ByteString getTraceConfig() {
            return this.traceConfig_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTraceConfig(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.traceConfig_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTraceConfig() {
            this.bitField0_ &= -2;
            this.traceConfig_ = getDefaultInstance().getTraceConfig();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.traceConfig_);
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
                size2 = 0 + CodedOutputStream.computeBytesSize(1, this.traceConfig_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PerfettoDetails parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PerfettoDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PerfettoDetails parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PerfettoDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PerfettoDetails parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PerfettoDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PerfettoDetails parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PerfettoDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PerfettoDetails parseFrom(InputStream input) throws IOException {
            return (PerfettoDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PerfettoDetails parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PerfettoDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PerfettoDetails parseDelimitedFrom(InputStream input) throws IOException {
            return (PerfettoDetails) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PerfettoDetails parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PerfettoDetails) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PerfettoDetails parseFrom(CodedInputStream input) throws IOException {
            return (PerfettoDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PerfettoDetails parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PerfettoDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PerfettoDetails prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PerfettoDetails, Builder> implements PerfettoDetailsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PerfettoDetails.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.PerfettoDetailsOrBuilder
            public boolean hasTraceConfig() {
                return ((PerfettoDetails) this.instance).hasTraceConfig();
            }

            @Override // com.android.internal.os.StatsdConfigProto.PerfettoDetailsOrBuilder
            public ByteString getTraceConfig() {
                return ((PerfettoDetails) this.instance).getTraceConfig();
            }

            public Builder setTraceConfig(ByteString value) {
                copyOnWrite();
                ((PerfettoDetails) this.instance).setTraceConfig(value);
                return this;
            }

            public Builder clearTraceConfig() {
                copyOnWrite();
                ((PerfettoDetails) this.instance).clearTraceConfig();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PerfettoDetails();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PerfettoDetails other = (PerfettoDetails) arg1;
                    this.traceConfig_ = visitor.visitByteString(hasTraceConfig(), this.traceConfig_, other.hasTraceConfig(), other.traceConfig_);
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
                            } else if (tag == 10) {
                                this.bitField0_ |= 1;
                                this.traceConfig_ = input.readBytes();
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
                        synchronized (PerfettoDetails.class) {
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

        public static PerfettoDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PerfettoDetails> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PerfprofdDetails extends GeneratedMessageLite<PerfprofdDetails, Builder> implements PerfprofdDetailsOrBuilder {
        private static final PerfprofdDetails DEFAULT_INSTANCE = new PerfprofdDetails();
        private static volatile Parser<PerfprofdDetails> PARSER = null;
        public static final int PERFPROFD_CONFIG_FIELD_NUMBER = 1;
        private int bitField0_;
        private ByteString perfprofdConfig_ = ByteString.EMPTY;

        private PerfprofdDetails() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.PerfprofdDetailsOrBuilder
        public boolean hasPerfprofdConfig() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.PerfprofdDetailsOrBuilder
        public ByteString getPerfprofdConfig() {
            return this.perfprofdConfig_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPerfprofdConfig(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.perfprofdConfig_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPerfprofdConfig() {
            this.bitField0_ &= -2;
            this.perfprofdConfig_ = getDefaultInstance().getPerfprofdConfig();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.perfprofdConfig_);
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
                size2 = 0 + CodedOutputStream.computeBytesSize(1, this.perfprofdConfig_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PerfprofdDetails parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PerfprofdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PerfprofdDetails parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PerfprofdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PerfprofdDetails parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PerfprofdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PerfprofdDetails parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PerfprofdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PerfprofdDetails parseFrom(InputStream input) throws IOException {
            return (PerfprofdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PerfprofdDetails parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PerfprofdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PerfprofdDetails parseDelimitedFrom(InputStream input) throws IOException {
            return (PerfprofdDetails) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PerfprofdDetails parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PerfprofdDetails) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PerfprofdDetails parseFrom(CodedInputStream input) throws IOException {
            return (PerfprofdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PerfprofdDetails parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PerfprofdDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PerfprofdDetails prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PerfprofdDetails, Builder> implements PerfprofdDetailsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PerfprofdDetails.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.PerfprofdDetailsOrBuilder
            public boolean hasPerfprofdConfig() {
                return ((PerfprofdDetails) this.instance).hasPerfprofdConfig();
            }

            @Override // com.android.internal.os.StatsdConfigProto.PerfprofdDetailsOrBuilder
            public ByteString getPerfprofdConfig() {
                return ((PerfprofdDetails) this.instance).getPerfprofdConfig();
            }

            public Builder setPerfprofdConfig(ByteString value) {
                copyOnWrite();
                ((PerfprofdDetails) this.instance).setPerfprofdConfig(value);
                return this;
            }

            public Builder clearPerfprofdConfig() {
                copyOnWrite();
                ((PerfprofdDetails) this.instance).clearPerfprofdConfig();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PerfprofdDetails();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PerfprofdDetails other = (PerfprofdDetails) arg1;
                    this.perfprofdConfig_ = visitor.visitByteString(hasPerfprofdConfig(), this.perfprofdConfig_, other.hasPerfprofdConfig(), other.perfprofdConfig_);
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
                            } else if (tag == 10) {
                                this.bitField0_ |= 1;
                                this.perfprofdConfig_ = input.readBytes();
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
                        synchronized (PerfprofdDetails.class) {
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

        public static PerfprofdDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PerfprofdDetails> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class BroadcastSubscriberDetails extends GeneratedMessageLite<BroadcastSubscriberDetails, Builder> implements BroadcastSubscriberDetailsOrBuilder {
        public static final int COOKIE_FIELD_NUMBER = 2;
        private static final BroadcastSubscriberDetails DEFAULT_INSTANCE = new BroadcastSubscriberDetails();
        private static volatile Parser<BroadcastSubscriberDetails> PARSER = null;
        public static final int SUBSCRIBER_ID_FIELD_NUMBER = 1;
        private int bitField0_;
        private Internal.ProtobufList<String> cookie_ = GeneratedMessageLite.emptyProtobufList();
        private long subscriberId_ = 0;

        private BroadcastSubscriberDetails() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
        public boolean hasSubscriberId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
        public long getSubscriberId() {
            return this.subscriberId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSubscriberId(long value) {
            this.bitField0_ |= 1;
            this.subscriberId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSubscriberId() {
            this.bitField0_ &= -2;
            this.subscriberId_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
        public List<String> getCookieList() {
            return this.cookie_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
        public int getCookieCount() {
            return this.cookie_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
        public String getCookie(int index) {
            return this.cookie_.get(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
        public ByteString getCookieBytes(int index) {
            return ByteString.copyFromUtf8(this.cookie_.get(index));
        }

        private void ensureCookieIsMutable() {
            if (!this.cookie_.isModifiable()) {
                this.cookie_ = GeneratedMessageLite.mutableCopy(this.cookie_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCookie(int index, String value) {
            if (value != null) {
                ensureCookieIsMutable();
                this.cookie_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addCookie(String value) {
            if (value != null) {
                ensureCookieIsMutable();
                this.cookie_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllCookie(Iterable<String> values) {
            ensureCookieIsMutable();
            AbstractMessageLite.addAll(values, this.cookie_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCookie() {
            this.cookie_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addCookieBytes(ByteString value) {
            if (value != null) {
                ensureCookieIsMutable();
                this.cookie_.add(value.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.subscriberId_);
            }
            for (int i = 0; i < this.cookie_.size(); i++) {
                output.writeString(2, this.cookie_.get(i));
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.subscriberId_);
            }
            int dataSize = 0;
            for (int i = 0; i < this.cookie_.size(); i++) {
                dataSize += CodedOutputStream.computeStringSizeNoTag(this.cookie_.get(i));
            }
            int size3 = size2 + dataSize + (getCookieList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static BroadcastSubscriberDetails parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BroadcastSubscriberDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BroadcastSubscriberDetails parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BroadcastSubscriberDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BroadcastSubscriberDetails parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BroadcastSubscriberDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BroadcastSubscriberDetails parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BroadcastSubscriberDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BroadcastSubscriberDetails parseFrom(InputStream input) throws IOException {
            return (BroadcastSubscriberDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BroadcastSubscriberDetails parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BroadcastSubscriberDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BroadcastSubscriberDetails parseDelimitedFrom(InputStream input) throws IOException {
            return (BroadcastSubscriberDetails) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BroadcastSubscriberDetails parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BroadcastSubscriberDetails) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BroadcastSubscriberDetails parseFrom(CodedInputStream input) throws IOException {
            return (BroadcastSubscriberDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BroadcastSubscriberDetails parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BroadcastSubscriberDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BroadcastSubscriberDetails prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BroadcastSubscriberDetails, Builder> implements BroadcastSubscriberDetailsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(BroadcastSubscriberDetails.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
            public boolean hasSubscriberId() {
                return ((BroadcastSubscriberDetails) this.instance).hasSubscriberId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
            public long getSubscriberId() {
                return ((BroadcastSubscriberDetails) this.instance).getSubscriberId();
            }

            public Builder setSubscriberId(long value) {
                copyOnWrite();
                ((BroadcastSubscriberDetails) this.instance).setSubscriberId(value);
                return this;
            }

            public Builder clearSubscriberId() {
                copyOnWrite();
                ((BroadcastSubscriberDetails) this.instance).clearSubscriberId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
            public List<String> getCookieList() {
                return Collections.unmodifiableList(((BroadcastSubscriberDetails) this.instance).getCookieList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
            public int getCookieCount() {
                return ((BroadcastSubscriberDetails) this.instance).getCookieCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
            public String getCookie(int index) {
                return ((BroadcastSubscriberDetails) this.instance).getCookie(index);
            }

            @Override // com.android.internal.os.StatsdConfigProto.BroadcastSubscriberDetailsOrBuilder
            public ByteString getCookieBytes(int index) {
                return ((BroadcastSubscriberDetails) this.instance).getCookieBytes(index);
            }

            public Builder setCookie(int index, String value) {
                copyOnWrite();
                ((BroadcastSubscriberDetails) this.instance).setCookie(index, value);
                return this;
            }

            public Builder addCookie(String value) {
                copyOnWrite();
                ((BroadcastSubscriberDetails) this.instance).addCookie(value);
                return this;
            }

            public Builder addAllCookie(Iterable<String> values) {
                copyOnWrite();
                ((BroadcastSubscriberDetails) this.instance).addAllCookie(values);
                return this;
            }

            public Builder clearCookie() {
                copyOnWrite();
                ((BroadcastSubscriberDetails) this.instance).clearCookie();
                return this;
            }

            public Builder addCookieBytes(ByteString value) {
                copyOnWrite();
                ((BroadcastSubscriberDetails) this.instance).addCookieBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new BroadcastSubscriberDetails();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.cookie_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    BroadcastSubscriberDetails other = (BroadcastSubscriberDetails) arg1;
                    this.subscriberId_ = visitor.visitLong(hasSubscriberId(), this.subscriberId_, other.hasSubscriberId(), other.subscriberId_);
                    this.cookie_ = visitor.visitList(this.cookie_, other.cookie_);
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
                                this.bitField0_ |= 1;
                                this.subscriberId_ = input.readInt64();
                            } else if (tag == 18) {
                                String s = input.readString();
                                if (!this.cookie_.isModifiable()) {
                                    this.cookie_ = GeneratedMessageLite.mutableCopy(this.cookie_);
                                }
                                this.cookie_.add(s);
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
                        synchronized (BroadcastSubscriberDetails.class) {
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

        public static BroadcastSubscriberDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BroadcastSubscriberDetails> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Subscription extends GeneratedMessageLite<Subscription, Builder> implements SubscriptionOrBuilder {
        public static final int BROADCAST_SUBSCRIBER_DETAILS_FIELD_NUMBER = 6;
        private static final Subscription DEFAULT_INSTANCE = new Subscription();
        public static final int ID_FIELD_NUMBER = 1;
        public static final int INCIDENTD_DETAILS_FIELD_NUMBER = 4;
        private static volatile Parser<Subscription> PARSER = null;
        public static final int PERFETTO_DETAILS_FIELD_NUMBER = 5;
        public static final int PERFPROFD_DETAILS_FIELD_NUMBER = 8;
        public static final int PROBABILITY_OF_INFORMING_FIELD_NUMBER = 7;
        public static final int RULE_ID_FIELD_NUMBER = 3;
        public static final int RULE_TYPE_FIELD_NUMBER = 2;
        private int bitField0_;
        private long id_ = 0;
        private float probabilityOfInforming_ = 1.1f;
        private long ruleId_ = 0;
        private int ruleType_ = 0;
        private int subscriberInformationCase_ = 0;
        private Object subscriberInformation_;

        private Subscription() {
        }

        public enum RuleType implements Internal.EnumLite {
            RULE_TYPE_UNSPECIFIED(0),
            ALARM(1),
            ALERT(2);
            
            public static final int ALARM_VALUE = 1;
            public static final int ALERT_VALUE = 2;
            public static final int RULE_TYPE_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<RuleType> internalValueMap = new Internal.EnumLiteMap<RuleType>() {
                /* class com.android.internal.os.StatsdConfigProto.Subscription.RuleType.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public RuleType findValueByNumber(int number) {
                    return RuleType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static RuleType valueOf(int value2) {
                return forNumber(value2);
            }

            public static RuleType forNumber(int value2) {
                if (value2 == 0) {
                    return RULE_TYPE_UNSPECIFIED;
                }
                if (value2 == 1) {
                    return ALARM;
                }
                if (value2 != 2) {
                    return null;
                }
                return ALERT;
            }

            public static Internal.EnumLiteMap<RuleType> internalGetValueMap() {
                return internalValueMap;
            }

            private RuleType(int value2) {
                this.value = value2;
            }
        }

        public enum SubscriberInformationCase implements Internal.EnumLite {
            INCIDENTD_DETAILS(4),
            PERFETTO_DETAILS(5),
            BROADCAST_SUBSCRIBER_DETAILS(6),
            PERFPROFD_DETAILS(8),
            SUBSCRIBERINFORMATION_NOT_SET(0);
            
            private final int value;

            private SubscriberInformationCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static SubscriberInformationCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static SubscriberInformationCase forNumber(int value2) {
                if (value2 == 0) {
                    return SUBSCRIBERINFORMATION_NOT_SET;
                }
                if (value2 == 8) {
                    return PERFPROFD_DETAILS;
                }
                if (value2 == 4) {
                    return INCIDENTD_DETAILS;
                }
                if (value2 == 5) {
                    return PERFETTO_DETAILS;
                }
                if (value2 != 6) {
                    return null;
                }
                return BROADCAST_SUBSCRIBER_DETAILS;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public SubscriberInformationCase getSubscriberInformationCase() {
            return SubscriberInformationCase.forNumber(this.subscriberInformationCase_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSubscriberInformation() {
            this.subscriberInformationCase_ = 0;
            this.subscriberInformation_ = null;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public boolean hasRuleType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public RuleType getRuleType() {
            RuleType result = RuleType.forNumber(this.ruleType_);
            return result == null ? RuleType.RULE_TYPE_UNSPECIFIED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRuleType(RuleType value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.ruleType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRuleType() {
            this.bitField0_ &= -3;
            this.ruleType_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public boolean hasRuleId() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public long getRuleId() {
            return this.ruleId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRuleId(long value) {
            this.bitField0_ |= 4;
            this.ruleId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRuleId() {
            this.bitField0_ &= -5;
            this.ruleId_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public boolean hasIncidentdDetails() {
            return this.subscriberInformationCase_ == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public IncidentdDetails getIncidentdDetails() {
            if (this.subscriberInformationCase_ == 4) {
                return (IncidentdDetails) this.subscriberInformation_;
            }
            return IncidentdDetails.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIncidentdDetails(IncidentdDetails value) {
            if (value != null) {
                this.subscriberInformation_ = value;
                this.subscriberInformationCase_ = 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIncidentdDetails(IncidentdDetails.Builder builderForValue) {
            this.subscriberInformation_ = builderForValue.build();
            this.subscriberInformationCase_ = 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeIncidentdDetails(IncidentdDetails value) {
            if (this.subscriberInformationCase_ != 4 || this.subscriberInformation_ == IncidentdDetails.getDefaultInstance()) {
                this.subscriberInformation_ = value;
            } else {
                this.subscriberInformation_ = ((IncidentdDetails.Builder) IncidentdDetails.newBuilder((IncidentdDetails) this.subscriberInformation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.subscriberInformationCase_ = 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIncidentdDetails() {
            if (this.subscriberInformationCase_ == 4) {
                this.subscriberInformationCase_ = 0;
                this.subscriberInformation_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public boolean hasPerfettoDetails() {
            return this.subscriberInformationCase_ == 5;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public PerfettoDetails getPerfettoDetails() {
            if (this.subscriberInformationCase_ == 5) {
                return (PerfettoDetails) this.subscriberInformation_;
            }
            return PerfettoDetails.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPerfettoDetails(PerfettoDetails value) {
            if (value != null) {
                this.subscriberInformation_ = value;
                this.subscriberInformationCase_ = 5;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPerfettoDetails(PerfettoDetails.Builder builderForValue) {
            this.subscriberInformation_ = builderForValue.build();
            this.subscriberInformationCase_ = 5;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergePerfettoDetails(PerfettoDetails value) {
            if (this.subscriberInformationCase_ != 5 || this.subscriberInformation_ == PerfettoDetails.getDefaultInstance()) {
                this.subscriberInformation_ = value;
            } else {
                this.subscriberInformation_ = ((PerfettoDetails.Builder) PerfettoDetails.newBuilder((PerfettoDetails) this.subscriberInformation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.subscriberInformationCase_ = 5;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPerfettoDetails() {
            if (this.subscriberInformationCase_ == 5) {
                this.subscriberInformationCase_ = 0;
                this.subscriberInformation_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public boolean hasBroadcastSubscriberDetails() {
            return this.subscriberInformationCase_ == 6;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public BroadcastSubscriberDetails getBroadcastSubscriberDetails() {
            if (this.subscriberInformationCase_ == 6) {
                return (BroadcastSubscriberDetails) this.subscriberInformation_;
            }
            return BroadcastSubscriberDetails.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBroadcastSubscriberDetails(BroadcastSubscriberDetails value) {
            if (value != null) {
                this.subscriberInformation_ = value;
                this.subscriberInformationCase_ = 6;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBroadcastSubscriberDetails(BroadcastSubscriberDetails.Builder builderForValue) {
            this.subscriberInformation_ = builderForValue.build();
            this.subscriberInformationCase_ = 6;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBroadcastSubscriberDetails(BroadcastSubscriberDetails value) {
            if (this.subscriberInformationCase_ != 6 || this.subscriberInformation_ == BroadcastSubscriberDetails.getDefaultInstance()) {
                this.subscriberInformation_ = value;
            } else {
                this.subscriberInformation_ = ((BroadcastSubscriberDetails.Builder) BroadcastSubscriberDetails.newBuilder((BroadcastSubscriberDetails) this.subscriberInformation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.subscriberInformationCase_ = 6;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBroadcastSubscriberDetails() {
            if (this.subscriberInformationCase_ == 6) {
                this.subscriberInformationCase_ = 0;
                this.subscriberInformation_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public boolean hasPerfprofdDetails() {
            return this.subscriberInformationCase_ == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public PerfprofdDetails getPerfprofdDetails() {
            if (this.subscriberInformationCase_ == 8) {
                return (PerfprofdDetails) this.subscriberInformation_;
            }
            return PerfprofdDetails.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPerfprofdDetails(PerfprofdDetails value) {
            if (value != null) {
                this.subscriberInformation_ = value;
                this.subscriberInformationCase_ = 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPerfprofdDetails(PerfprofdDetails.Builder builderForValue) {
            this.subscriberInformation_ = builderForValue.build();
            this.subscriberInformationCase_ = 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergePerfprofdDetails(PerfprofdDetails value) {
            if (this.subscriberInformationCase_ != 8 || this.subscriberInformation_ == PerfprofdDetails.getDefaultInstance()) {
                this.subscriberInformation_ = value;
            } else {
                this.subscriberInformation_ = ((PerfprofdDetails.Builder) PerfprofdDetails.newBuilder((PerfprofdDetails) this.subscriberInformation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.subscriberInformationCase_ = 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPerfprofdDetails() {
            if (this.subscriberInformationCase_ == 8) {
                this.subscriberInformationCase_ = 0;
                this.subscriberInformation_ = null;
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public boolean hasProbabilityOfInforming() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
        public float getProbabilityOfInforming() {
            return this.probabilityOfInforming_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProbabilityOfInforming(float value) {
            this.bitField0_ |= 128;
            this.probabilityOfInforming_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProbabilityOfInforming() {
            this.bitField0_ &= -129;
            this.probabilityOfInforming_ = 1.1f;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.ruleType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.ruleId_);
            }
            if (this.subscriberInformationCase_ == 4) {
                output.writeMessage(4, (IncidentdDetails) this.subscriberInformation_);
            }
            if (this.subscriberInformationCase_ == 5) {
                output.writeMessage(5, (PerfettoDetails) this.subscriberInformation_);
            }
            if (this.subscriberInformationCase_ == 6) {
                output.writeMessage(6, (BroadcastSubscriberDetails) this.subscriberInformation_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeFloat(7, this.probabilityOfInforming_);
            }
            if (this.subscriberInformationCase_ == 8) {
                output.writeMessage(8, (PerfprofdDetails) this.subscriberInformation_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.ruleType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.ruleId_);
            }
            if (this.subscriberInformationCase_ == 4) {
                size2 += CodedOutputStream.computeMessageSize(4, (IncidentdDetails) this.subscriberInformation_);
            }
            if (this.subscriberInformationCase_ == 5) {
                size2 += CodedOutputStream.computeMessageSize(5, (PerfettoDetails) this.subscriberInformation_);
            }
            if (this.subscriberInformationCase_ == 6) {
                size2 += CodedOutputStream.computeMessageSize(6, (BroadcastSubscriberDetails) this.subscriberInformation_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeFloatSize(7, this.probabilityOfInforming_);
            }
            if (this.subscriberInformationCase_ == 8) {
                size2 += CodedOutputStream.computeMessageSize(8, (PerfprofdDetails) this.subscriberInformation_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Subscription parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Subscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Subscription parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Subscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Subscription parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Subscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Subscription parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Subscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Subscription parseFrom(InputStream input) throws IOException {
            return (Subscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Subscription parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Subscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Subscription parseDelimitedFrom(InputStream input) throws IOException {
            return (Subscription) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Subscription parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Subscription) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Subscription parseFrom(CodedInputStream input) throws IOException {
            return (Subscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Subscription parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Subscription) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Subscription prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Subscription, Builder> implements SubscriptionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Subscription.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public SubscriberInformationCase getSubscriberInformationCase() {
                return ((Subscription) this.instance).getSubscriberInformationCase();
            }

            public Builder clearSubscriberInformation() {
                copyOnWrite();
                ((Subscription) this.instance).clearSubscriberInformation();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public boolean hasId() {
                return ((Subscription) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public long getId() {
                return ((Subscription) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((Subscription) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Subscription) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public boolean hasRuleType() {
                return ((Subscription) this.instance).hasRuleType();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public RuleType getRuleType() {
                return ((Subscription) this.instance).getRuleType();
            }

            public Builder setRuleType(RuleType value) {
                copyOnWrite();
                ((Subscription) this.instance).setRuleType(value);
                return this;
            }

            public Builder clearRuleType() {
                copyOnWrite();
                ((Subscription) this.instance).clearRuleType();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public boolean hasRuleId() {
                return ((Subscription) this.instance).hasRuleId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public long getRuleId() {
                return ((Subscription) this.instance).getRuleId();
            }

            public Builder setRuleId(long value) {
                copyOnWrite();
                ((Subscription) this.instance).setRuleId(value);
                return this;
            }

            public Builder clearRuleId() {
                copyOnWrite();
                ((Subscription) this.instance).clearRuleId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public boolean hasIncidentdDetails() {
                return ((Subscription) this.instance).hasIncidentdDetails();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public IncidentdDetails getIncidentdDetails() {
                return ((Subscription) this.instance).getIncidentdDetails();
            }

            public Builder setIncidentdDetails(IncidentdDetails value) {
                copyOnWrite();
                ((Subscription) this.instance).setIncidentdDetails((Subscription) value);
                return this;
            }

            public Builder setIncidentdDetails(IncidentdDetails.Builder builderForValue) {
                copyOnWrite();
                ((Subscription) this.instance).setIncidentdDetails((Subscription) builderForValue);
                return this;
            }

            public Builder mergeIncidentdDetails(IncidentdDetails value) {
                copyOnWrite();
                ((Subscription) this.instance).mergeIncidentdDetails(value);
                return this;
            }

            public Builder clearIncidentdDetails() {
                copyOnWrite();
                ((Subscription) this.instance).clearIncidentdDetails();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public boolean hasPerfettoDetails() {
                return ((Subscription) this.instance).hasPerfettoDetails();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public PerfettoDetails getPerfettoDetails() {
                return ((Subscription) this.instance).getPerfettoDetails();
            }

            public Builder setPerfettoDetails(PerfettoDetails value) {
                copyOnWrite();
                ((Subscription) this.instance).setPerfettoDetails((Subscription) value);
                return this;
            }

            public Builder setPerfettoDetails(PerfettoDetails.Builder builderForValue) {
                copyOnWrite();
                ((Subscription) this.instance).setPerfettoDetails((Subscription) builderForValue);
                return this;
            }

            public Builder mergePerfettoDetails(PerfettoDetails value) {
                copyOnWrite();
                ((Subscription) this.instance).mergePerfettoDetails(value);
                return this;
            }

            public Builder clearPerfettoDetails() {
                copyOnWrite();
                ((Subscription) this.instance).clearPerfettoDetails();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public boolean hasBroadcastSubscriberDetails() {
                return ((Subscription) this.instance).hasBroadcastSubscriberDetails();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public BroadcastSubscriberDetails getBroadcastSubscriberDetails() {
                return ((Subscription) this.instance).getBroadcastSubscriberDetails();
            }

            public Builder setBroadcastSubscriberDetails(BroadcastSubscriberDetails value) {
                copyOnWrite();
                ((Subscription) this.instance).setBroadcastSubscriberDetails((Subscription) value);
                return this;
            }

            public Builder setBroadcastSubscriberDetails(BroadcastSubscriberDetails.Builder builderForValue) {
                copyOnWrite();
                ((Subscription) this.instance).setBroadcastSubscriberDetails((Subscription) builderForValue);
                return this;
            }

            public Builder mergeBroadcastSubscriberDetails(BroadcastSubscriberDetails value) {
                copyOnWrite();
                ((Subscription) this.instance).mergeBroadcastSubscriberDetails(value);
                return this;
            }

            public Builder clearBroadcastSubscriberDetails() {
                copyOnWrite();
                ((Subscription) this.instance).clearBroadcastSubscriberDetails();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public boolean hasPerfprofdDetails() {
                return ((Subscription) this.instance).hasPerfprofdDetails();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public PerfprofdDetails getPerfprofdDetails() {
                return ((Subscription) this.instance).getPerfprofdDetails();
            }

            public Builder setPerfprofdDetails(PerfprofdDetails value) {
                copyOnWrite();
                ((Subscription) this.instance).setPerfprofdDetails((Subscription) value);
                return this;
            }

            public Builder setPerfprofdDetails(PerfprofdDetails.Builder builderForValue) {
                copyOnWrite();
                ((Subscription) this.instance).setPerfprofdDetails((Subscription) builderForValue);
                return this;
            }

            public Builder mergePerfprofdDetails(PerfprofdDetails value) {
                copyOnWrite();
                ((Subscription) this.instance).mergePerfprofdDetails(value);
                return this;
            }

            public Builder clearPerfprofdDetails() {
                copyOnWrite();
                ((Subscription) this.instance).clearPerfprofdDetails();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public boolean hasProbabilityOfInforming() {
                return ((Subscription) this.instance).hasProbabilityOfInforming();
            }

            @Override // com.android.internal.os.StatsdConfigProto.SubscriptionOrBuilder
            public float getProbabilityOfInforming() {
                return ((Subscription) this.instance).getProbabilityOfInforming();
            }

            public Builder setProbabilityOfInforming(float value) {
                copyOnWrite();
                ((Subscription) this.instance).setProbabilityOfInforming(value);
                return this;
            }

            public Builder clearProbabilityOfInforming() {
                copyOnWrite();
                ((Subscription) this.instance).clearProbabilityOfInforming();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean z = true;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Subscription();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Subscription other = (Subscription) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    this.ruleType_ = visitor.visitInt(hasRuleType(), this.ruleType_, other.hasRuleType(), other.ruleType_);
                    this.ruleId_ = visitor.visitLong(hasRuleId(), this.ruleId_, other.hasRuleId(), other.ruleId_);
                    this.probabilityOfInforming_ = visitor.visitFloat(hasProbabilityOfInforming(), this.probabilityOfInforming_, other.hasProbabilityOfInforming(), other.probabilityOfInforming_);
                    int i = AnonymousClass1.$SwitchMap$com$android$internal$os$StatsdConfigProto$Subscription$SubscriberInformationCase[other.getSubscriberInformationCase().ordinal()];
                    if (i == 1) {
                        if (this.subscriberInformationCase_ != 4) {
                            z = false;
                        }
                        this.subscriberInformation_ = visitor.visitOneofMessage(z, this.subscriberInformation_, other.subscriberInformation_);
                    } else if (i == 2) {
                        if (this.subscriberInformationCase_ != 5) {
                            z = false;
                        }
                        this.subscriberInformation_ = visitor.visitOneofMessage(z, this.subscriberInformation_, other.subscriberInformation_);
                    } else if (i == 3) {
                        if (this.subscriberInformationCase_ != 6) {
                            z = false;
                        }
                        this.subscriberInformation_ = visitor.visitOneofMessage(z, this.subscriberInformation_, other.subscriberInformation_);
                    } else if (i == 4) {
                        if (this.subscriberInformationCase_ != 8) {
                            z = false;
                        }
                        this.subscriberInformation_ = visitor.visitOneofMessage(z, this.subscriberInformation_, other.subscriberInformation_);
                    } else if (i == 5) {
                        if (this.subscriberInformationCase_ == 0) {
                            z = false;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i2 = other.subscriberInformationCase_;
                        if (i2 != 0) {
                            this.subscriberInformationCase_ = i2;
                        }
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
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt64();
                            } else if (tag == 16) {
                                int rawValue = input.readEnum();
                                if (RuleType.forNumber(rawValue) == null) {
                                    super.mergeVarintField(2, rawValue);
                                } else {
                                    this.bitField0_ |= 2;
                                    this.ruleType_ = rawValue;
                                }
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.ruleId_ = input.readInt64();
                            } else if (tag == 34) {
                                IncidentdDetails.Builder subBuilder = null;
                                if (this.subscriberInformationCase_ == 4) {
                                    subBuilder = (IncidentdDetails.Builder) ((IncidentdDetails) this.subscriberInformation_).toBuilder();
                                }
                                this.subscriberInformation_ = input.readMessage(IncidentdDetails.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) ((IncidentdDetails) this.subscriberInformation_));
                                    this.subscriberInformation_ = subBuilder.buildPartial();
                                }
                                this.subscriberInformationCase_ = 4;
                            } else if (tag == 42) {
                                PerfettoDetails.Builder subBuilder2 = null;
                                if (this.subscriberInformationCase_ == 5) {
                                    subBuilder2 = (PerfettoDetails.Builder) ((PerfettoDetails) this.subscriberInformation_).toBuilder();
                                }
                                this.subscriberInformation_ = input.readMessage(PerfettoDetails.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) ((PerfettoDetails) this.subscriberInformation_));
                                    this.subscriberInformation_ = subBuilder2.buildPartial();
                                }
                                this.subscriberInformationCase_ = 5;
                            } else if (tag == 50) {
                                BroadcastSubscriberDetails.Builder subBuilder3 = null;
                                if (this.subscriberInformationCase_ == 6) {
                                    subBuilder3 = (BroadcastSubscriberDetails.Builder) ((BroadcastSubscriberDetails) this.subscriberInformation_).toBuilder();
                                }
                                this.subscriberInformation_ = input.readMessage(BroadcastSubscriberDetails.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) ((BroadcastSubscriberDetails) this.subscriberInformation_));
                                    this.subscriberInformation_ = subBuilder3.buildPartial();
                                }
                                this.subscriberInformationCase_ = 6;
                            } else if (tag == 61) {
                                this.bitField0_ |= 128;
                                this.probabilityOfInforming_ = input.readFloat();
                            } else if (tag == 66) {
                                PerfprofdDetails.Builder subBuilder4 = null;
                                if (this.subscriberInformationCase_ == 8) {
                                    subBuilder4 = (PerfprofdDetails.Builder) ((PerfprofdDetails) this.subscriberInformation_).toBuilder();
                                }
                                this.subscriberInformation_ = input.readMessage(PerfprofdDetails.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) ((PerfprofdDetails) this.subscriberInformation_));
                                    this.subscriberInformation_ = subBuilder4.buildPartial();
                                }
                                this.subscriberInformationCase_ = 8;
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
                        synchronized (Subscription.class) {
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

        public static Subscription getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Subscription> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.android.internal.os.StatsdConfigProto$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$os$StatsdConfigProto$AtomMatcher$ContentsCase = new int[AtomMatcher.ContentsCase.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$os$StatsdConfigProto$Predicate$ContentsCase = new int[Predicate.ContentsCase.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$os$StatsdConfigProto$Subscription$SubscriberInformationCase = new int[Subscription.SubscriberInformationCase.values().length];

        static {
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$Subscription$SubscriberInformationCase[Subscription.SubscriberInformationCase.INCIDENTD_DETAILS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$Subscription$SubscriberInformationCase[Subscription.SubscriberInformationCase.PERFETTO_DETAILS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$Subscription$SubscriberInformationCase[Subscription.SubscriberInformationCase.BROADCAST_SUBSCRIBER_DETAILS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$Subscription$SubscriberInformationCase[Subscription.SubscriberInformationCase.PERFPROFD_DETAILS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$Subscription$SubscriberInformationCase[Subscription.SubscriberInformationCase.SUBSCRIBERINFORMATION_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$Predicate$ContentsCase[Predicate.ContentsCase.SIMPLE_PREDICATE.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$Predicate$ContentsCase[Predicate.ContentsCase.COMBINATION.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$Predicate$ContentsCase[Predicate.ContentsCase.CONTENTS_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$AtomMatcher$ContentsCase[AtomMatcher.ContentsCase.SIMPLE_ATOM_MATCHER.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$AtomMatcher$ContentsCase[AtomMatcher.ContentsCase.COMBINATION.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$AtomMatcher$ContentsCase[AtomMatcher.ContentsCase.CONTENTS_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase = new int[FieldValueMatcher.ValueMatcherCase.values().length];
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.EQ_BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.EQ_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.EQ_INT.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.LT_INT.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.GT_INT.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.LT_FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.GT_FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.LTE_INT.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.GTE_INT.ordinal()] = 9;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.MATCHES_TUPLE.ordinal()] = 10;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.EQ_ANY_STRING.ordinal()] = 11;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.NEQ_ANY_STRING.ordinal()] = 12;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$android$internal$os$StatsdConfigProto$FieldValueMatcher$ValueMatcherCase[FieldValueMatcher.ValueMatcherCase.VALUEMATCHER_NOT_SET.ordinal()] = 13;
            } catch (NoSuchFieldError e24) {
            }
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e26) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e27) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e28) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e29) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e30) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e31) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e32) {
            }
        }
    }

    public static final class EventActivation extends GeneratedMessageLite<EventActivation, Builder> implements EventActivationOrBuilder {
        public static final int ACTIVATION_TYPE_FIELD_NUMBER = 4;
        public static final int ATOM_MATCHER_ID_FIELD_NUMBER = 1;
        public static final int DEACTIVATION_ATOM_MATCHER_ID_FIELD_NUMBER = 3;
        private static final EventActivation DEFAULT_INSTANCE = new EventActivation();
        private static volatile Parser<EventActivation> PARSER = null;
        public static final int TTL_SECONDS_FIELD_NUMBER = 2;
        private int activationType_ = 0;
        private long atomMatcherId_ = 0;
        private int bitField0_;
        private long deactivationAtomMatcherId_ = 0;
        private long ttlSeconds_ = 0;

        private EventActivation() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
        public boolean hasAtomMatcherId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
        public long getAtomMatcherId() {
            return this.atomMatcherId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAtomMatcherId(long value) {
            this.bitField0_ |= 1;
            this.atomMatcherId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAtomMatcherId() {
            this.bitField0_ &= -2;
            this.atomMatcherId_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
        public boolean hasTtlSeconds() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
        public long getTtlSeconds() {
            return this.ttlSeconds_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTtlSeconds(long value) {
            this.bitField0_ |= 2;
            this.ttlSeconds_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTtlSeconds() {
            this.bitField0_ &= -3;
            this.ttlSeconds_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
        public boolean hasDeactivationAtomMatcherId() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
        public long getDeactivationAtomMatcherId() {
            return this.deactivationAtomMatcherId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDeactivationAtomMatcherId(long value) {
            this.bitField0_ |= 4;
            this.deactivationAtomMatcherId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDeactivationAtomMatcherId() {
            this.bitField0_ &= -5;
            this.deactivationAtomMatcherId_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
        public boolean hasActivationType() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
        public ActivationType getActivationType() {
            ActivationType result = ActivationType.forNumber(this.activationType_);
            return result == null ? ActivationType.ACTIVATION_TYPE_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActivationType(ActivationType value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.activationType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActivationType() {
            this.bitField0_ &= -9;
            this.activationType_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.atomMatcherId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.ttlSeconds_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.deactivationAtomMatcherId_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.activationType_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.atomMatcherId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.ttlSeconds_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.deactivationAtomMatcherId_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.activationType_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static EventActivation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (EventActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EventActivation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EventActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EventActivation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (EventActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EventActivation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EventActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EventActivation parseFrom(InputStream input) throws IOException {
            return (EventActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EventActivation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EventActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EventActivation parseDelimitedFrom(InputStream input) throws IOException {
            return (EventActivation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static EventActivation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EventActivation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EventActivation parseFrom(CodedInputStream input) throws IOException {
            return (EventActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EventActivation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EventActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(EventActivation prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<EventActivation, Builder> implements EventActivationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(EventActivation.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
            public boolean hasAtomMatcherId() {
                return ((EventActivation) this.instance).hasAtomMatcherId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
            public long getAtomMatcherId() {
                return ((EventActivation) this.instance).getAtomMatcherId();
            }

            public Builder setAtomMatcherId(long value) {
                copyOnWrite();
                ((EventActivation) this.instance).setAtomMatcherId(value);
                return this;
            }

            public Builder clearAtomMatcherId() {
                copyOnWrite();
                ((EventActivation) this.instance).clearAtomMatcherId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
            public boolean hasTtlSeconds() {
                return ((EventActivation) this.instance).hasTtlSeconds();
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
            public long getTtlSeconds() {
                return ((EventActivation) this.instance).getTtlSeconds();
            }

            public Builder setTtlSeconds(long value) {
                copyOnWrite();
                ((EventActivation) this.instance).setTtlSeconds(value);
                return this;
            }

            public Builder clearTtlSeconds() {
                copyOnWrite();
                ((EventActivation) this.instance).clearTtlSeconds();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
            public boolean hasDeactivationAtomMatcherId() {
                return ((EventActivation) this.instance).hasDeactivationAtomMatcherId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
            public long getDeactivationAtomMatcherId() {
                return ((EventActivation) this.instance).getDeactivationAtomMatcherId();
            }

            public Builder setDeactivationAtomMatcherId(long value) {
                copyOnWrite();
                ((EventActivation) this.instance).setDeactivationAtomMatcherId(value);
                return this;
            }

            public Builder clearDeactivationAtomMatcherId() {
                copyOnWrite();
                ((EventActivation) this.instance).clearDeactivationAtomMatcherId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
            public boolean hasActivationType() {
                return ((EventActivation) this.instance).hasActivationType();
            }

            @Override // com.android.internal.os.StatsdConfigProto.EventActivationOrBuilder
            public ActivationType getActivationType() {
                return ((EventActivation) this.instance).getActivationType();
            }

            public Builder setActivationType(ActivationType value) {
                copyOnWrite();
                ((EventActivation) this.instance).setActivationType(value);
                return this;
            }

            public Builder clearActivationType() {
                copyOnWrite();
                ((EventActivation) this.instance).clearActivationType();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new EventActivation();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    EventActivation other = (EventActivation) arg1;
                    this.atomMatcherId_ = visitor.visitLong(hasAtomMatcherId(), this.atomMatcherId_, other.hasAtomMatcherId(), other.atomMatcherId_);
                    this.ttlSeconds_ = visitor.visitLong(hasTtlSeconds(), this.ttlSeconds_, other.hasTtlSeconds(), other.ttlSeconds_);
                    this.deactivationAtomMatcherId_ = visitor.visitLong(hasDeactivationAtomMatcherId(), this.deactivationAtomMatcherId_, other.hasDeactivationAtomMatcherId(), other.deactivationAtomMatcherId_);
                    this.activationType_ = visitor.visitInt(hasActivationType(), this.activationType_, other.hasActivationType(), other.activationType_);
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
                                this.bitField0_ |= 1;
                                this.atomMatcherId_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.ttlSeconds_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.deactivationAtomMatcherId_ = input.readInt64();
                            } else if (tag == 32) {
                                int rawValue = input.readEnum();
                                if (ActivationType.forNumber(rawValue) == null) {
                                    super.mergeVarintField(4, rawValue);
                                } else {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.activationType_ = rawValue;
                                }
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
                        synchronized (EventActivation.class) {
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

        public static EventActivation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<EventActivation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class MetricActivation extends GeneratedMessageLite<MetricActivation, Builder> implements MetricActivationOrBuilder {
        public static final int ACTIVATION_TYPE_FIELD_NUMBER = 3;
        private static final MetricActivation DEFAULT_INSTANCE = new MetricActivation();
        public static final int EVENT_ACTIVATION_FIELD_NUMBER = 2;
        public static final int METRIC_ID_FIELD_NUMBER = 1;
        private static volatile Parser<MetricActivation> PARSER;
        private int activationType_ = 0;
        private int bitField0_;
        private Internal.ProtobufList<EventActivation> eventActivation_ = emptyProtobufList();
        private long metricId_ = 0;

        private MetricActivation() {
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
        public boolean hasMetricId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
        public long getMetricId() {
            return this.metricId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMetricId(long value) {
            this.bitField0_ |= 1;
            this.metricId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMetricId() {
            this.bitField0_ &= -2;
            this.metricId_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
        @Deprecated
        public boolean hasActivationType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
        @Deprecated
        public ActivationType getActivationType() {
            ActivationType result = ActivationType.forNumber(this.activationType_);
            return result == null ? ActivationType.ACTIVATION_TYPE_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActivationType(ActivationType value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.activationType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActivationType() {
            this.bitField0_ &= -3;
            this.activationType_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
        public List<EventActivation> getEventActivationList() {
            return this.eventActivation_;
        }

        public List<? extends EventActivationOrBuilder> getEventActivationOrBuilderList() {
            return this.eventActivation_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
        public int getEventActivationCount() {
            return this.eventActivation_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
        public EventActivation getEventActivation(int index) {
            return this.eventActivation_.get(index);
        }

        public EventActivationOrBuilder getEventActivationOrBuilder(int index) {
            return this.eventActivation_.get(index);
        }

        private void ensureEventActivationIsMutable() {
            if (!this.eventActivation_.isModifiable()) {
                this.eventActivation_ = GeneratedMessageLite.mutableCopy(this.eventActivation_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEventActivation(int index, EventActivation value) {
            if (value != null) {
                ensureEventActivationIsMutable();
                this.eventActivation_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEventActivation(int index, EventActivation.Builder builderForValue) {
            ensureEventActivationIsMutable();
            this.eventActivation_.set(index, (EventActivation) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEventActivation(EventActivation value) {
            if (value != null) {
                ensureEventActivationIsMutable();
                this.eventActivation_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEventActivation(int index, EventActivation value) {
            if (value != null) {
                ensureEventActivationIsMutable();
                this.eventActivation_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEventActivation(EventActivation.Builder builderForValue) {
            ensureEventActivationIsMutable();
            this.eventActivation_.add((EventActivation) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEventActivation(int index, EventActivation.Builder builderForValue) {
            ensureEventActivationIsMutable();
            this.eventActivation_.add(index, (EventActivation) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllEventActivation(Iterable<? extends EventActivation> values) {
            ensureEventActivationIsMutable();
            AbstractMessageLite.addAll(values, this.eventActivation_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEventActivation() {
            this.eventActivation_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeEventActivation(int index) {
            ensureEventActivationIsMutable();
            this.eventActivation_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.metricId_);
            }
            for (int i = 0; i < this.eventActivation_.size(); i++) {
                output.writeMessage(2, this.eventActivation_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(3, this.activationType_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.metricId_);
            }
            for (int i = 0; i < this.eventActivation_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.eventActivation_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(3, this.activationType_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static MetricActivation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MetricActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MetricActivation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MetricActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MetricActivation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MetricActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MetricActivation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MetricActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MetricActivation parseFrom(InputStream input) throws IOException {
            return (MetricActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MetricActivation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MetricActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MetricActivation parseDelimitedFrom(InputStream input) throws IOException {
            return (MetricActivation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MetricActivation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MetricActivation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MetricActivation parseFrom(CodedInputStream input) throws IOException {
            return (MetricActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MetricActivation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MetricActivation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MetricActivation prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MetricActivation, Builder> implements MetricActivationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(MetricActivation.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
            public boolean hasMetricId() {
                return ((MetricActivation) this.instance).hasMetricId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
            public long getMetricId() {
                return ((MetricActivation) this.instance).getMetricId();
            }

            public Builder setMetricId(long value) {
                copyOnWrite();
                ((MetricActivation) this.instance).setMetricId(value);
                return this;
            }

            public Builder clearMetricId() {
                copyOnWrite();
                ((MetricActivation) this.instance).clearMetricId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
            @Deprecated
            public boolean hasActivationType() {
                return ((MetricActivation) this.instance).hasActivationType();
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
            @Deprecated
            public ActivationType getActivationType() {
                return ((MetricActivation) this.instance).getActivationType();
            }

            @Deprecated
            public Builder setActivationType(ActivationType value) {
                copyOnWrite();
                ((MetricActivation) this.instance).setActivationType(value);
                return this;
            }

            @Deprecated
            public Builder clearActivationType() {
                copyOnWrite();
                ((MetricActivation) this.instance).clearActivationType();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
            public List<EventActivation> getEventActivationList() {
                return Collections.unmodifiableList(((MetricActivation) this.instance).getEventActivationList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
            public int getEventActivationCount() {
                return ((MetricActivation) this.instance).getEventActivationCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.MetricActivationOrBuilder
            public EventActivation getEventActivation(int index) {
                return ((MetricActivation) this.instance).getEventActivation(index);
            }

            public Builder setEventActivation(int index, EventActivation value) {
                copyOnWrite();
                ((MetricActivation) this.instance).setEventActivation((MetricActivation) index, (int) value);
                return this;
            }

            public Builder setEventActivation(int index, EventActivation.Builder builderForValue) {
                copyOnWrite();
                ((MetricActivation) this.instance).setEventActivation((MetricActivation) index, (int) builderForValue);
                return this;
            }

            public Builder addEventActivation(EventActivation value) {
                copyOnWrite();
                ((MetricActivation) this.instance).addEventActivation((MetricActivation) value);
                return this;
            }

            public Builder addEventActivation(int index, EventActivation value) {
                copyOnWrite();
                ((MetricActivation) this.instance).addEventActivation((MetricActivation) index, (int) value);
                return this;
            }

            public Builder addEventActivation(EventActivation.Builder builderForValue) {
                copyOnWrite();
                ((MetricActivation) this.instance).addEventActivation((MetricActivation) builderForValue);
                return this;
            }

            public Builder addEventActivation(int index, EventActivation.Builder builderForValue) {
                copyOnWrite();
                ((MetricActivation) this.instance).addEventActivation((MetricActivation) index, (int) builderForValue);
                return this;
            }

            public Builder addAllEventActivation(Iterable<? extends EventActivation> values) {
                copyOnWrite();
                ((MetricActivation) this.instance).addAllEventActivation(values);
                return this;
            }

            public Builder clearEventActivation() {
                copyOnWrite();
                ((MetricActivation) this.instance).clearEventActivation();
                return this;
            }

            public Builder removeEventActivation(int index) {
                copyOnWrite();
                ((MetricActivation) this.instance).removeEventActivation(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new MetricActivation();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.eventActivation_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    MetricActivation other = (MetricActivation) arg1;
                    this.metricId_ = visitor.visitLong(hasMetricId(), this.metricId_, other.hasMetricId(), other.metricId_);
                    this.activationType_ = visitor.visitInt(hasActivationType(), this.activationType_, other.hasActivationType(), other.activationType_);
                    this.eventActivation_ = visitor.visitList(this.eventActivation_, other.eventActivation_);
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
                                this.bitField0_ |= 1;
                                this.metricId_ = input.readInt64();
                            } else if (tag == 18) {
                                if (!this.eventActivation_.isModifiable()) {
                                    this.eventActivation_ = GeneratedMessageLite.mutableCopy(this.eventActivation_);
                                }
                                this.eventActivation_.add((EventActivation) input.readMessage(EventActivation.parser(), extensionRegistry));
                            } else if (tag == 24) {
                                int rawValue = input.readEnum();
                                if (ActivationType.forNumber(rawValue) == null) {
                                    super.mergeVarintField(3, rawValue);
                                } else {
                                    this.bitField0_ |= 2;
                                    this.activationType_ = rawValue;
                                }
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
                        synchronized (MetricActivation.class) {
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

        public static MetricActivation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MetricActivation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class StatsdConfig extends GeneratedMessageLite<StatsdConfig, Builder> implements StatsdConfigOrBuilder {
        public static final int ALARM_FIELD_NUMBER = 10;
        public static final int ALERT_FIELD_NUMBER = 9;
        public static final int ALLOWED_LOG_SOURCE_FIELD_NUMBER = 12;
        public static final int ANNOTATION_FIELD_NUMBER = 14;
        public static final int ATOM_MATCHER_FIELD_NUMBER = 7;
        public static final int COUNT_METRIC_FIELD_NUMBER = 3;
        private static final StatsdConfig DEFAULT_INSTANCE = new StatsdConfig();
        public static final int DURATION_METRIC_FIELD_NUMBER = 6;
        public static final int EVENT_METRIC_FIELD_NUMBER = 2;
        public static final int GAUGE_METRIC_FIELD_NUMBER = 5;
        public static final int HASH_STRINGS_IN_METRIC_REPORT_FIELD_NUMBER = 16;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int INSTALLER_IN_METRIC_REPORT_FIELD_NUMBER = 19;
        public static final int METRIC_ACTIVATION_FIELD_NUMBER = 17;
        public static final int NO_REPORT_METRIC_FIELD_NUMBER = 13;
        private static volatile Parser<StatsdConfig> PARSER = null;
        public static final int PERSIST_LOCALLY_FIELD_NUMBER = 20;
        public static final int PREDICATE_FIELD_NUMBER = 8;
        public static final int SUBSCRIPTION_FIELD_NUMBER = 11;
        public static final int TTL_IN_SECONDS_FIELD_NUMBER = 15;
        public static final int VALUE_METRIC_FIELD_NUMBER = 4;
        public static final int VERSION_STRINGS_IN_METRIC_REPORT_FIELD_NUMBER = 18;
        private Internal.ProtobufList<Alarm> alarm_ = emptyProtobufList();
        private Internal.ProtobufList<Alert> alert_ = emptyProtobufList();
        private Internal.ProtobufList<String> allowedLogSource_ = GeneratedMessageLite.emptyProtobufList();
        private Internal.ProtobufList<Annotation> annotation_ = emptyProtobufList();
        private Internal.ProtobufList<AtomMatcher> atomMatcher_ = emptyProtobufList();
        private int bitField0_;
        private Internal.ProtobufList<CountMetric> countMetric_ = emptyProtobufList();
        private Internal.ProtobufList<DurationMetric> durationMetric_ = emptyProtobufList();
        private Internal.ProtobufList<EventMetric> eventMetric_ = emptyProtobufList();
        private Internal.ProtobufList<GaugeMetric> gaugeMetric_ = emptyProtobufList();
        private boolean hashStringsInMetricReport_ = true;
        private long id_ = 0;
        private boolean installerInMetricReport_ = false;
        private Internal.ProtobufList<MetricActivation> metricActivation_ = emptyProtobufList();
        private Internal.LongList noReportMetric_ = emptyLongList();
        private boolean persistLocally_ = false;
        private Internal.ProtobufList<Predicate> predicate_ = emptyProtobufList();
        private Internal.ProtobufList<Subscription> subscription_ = emptyProtobufList();
        private long ttlInSeconds_ = 0;
        private Internal.ProtobufList<ValueMetric> valueMetric_ = emptyProtobufList();
        private boolean versionStringsInMetricReport_ = false;

        public interface AnnotationOrBuilder extends MessageLiteOrBuilder {
            int getFieldInt32();

            long getFieldInt64();

            boolean hasFieldInt32();

            boolean hasFieldInt64();
        }

        private StatsdConfig() {
        }

        public static final class Annotation extends GeneratedMessageLite<Annotation, Builder> implements AnnotationOrBuilder {
            private static final Annotation DEFAULT_INSTANCE = new Annotation();
            public static final int FIELD_INT32_FIELD_NUMBER = 2;
            public static final int FIELD_INT64_FIELD_NUMBER = 1;
            private static volatile Parser<Annotation> PARSER;
            private int bitField0_;
            private int fieldInt32_ = 0;
            private long fieldInt64_ = 0;

            private Annotation() {
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfig.AnnotationOrBuilder
            public boolean hasFieldInt64() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfig.AnnotationOrBuilder
            public long getFieldInt64() {
                return this.fieldInt64_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setFieldInt64(long value) {
                this.bitField0_ |= 1;
                this.fieldInt64_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearFieldInt64() {
                this.bitField0_ &= -2;
                this.fieldInt64_ = 0;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfig.AnnotationOrBuilder
            public boolean hasFieldInt32() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfig.AnnotationOrBuilder
            public int getFieldInt32() {
                return this.fieldInt32_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setFieldInt32(int value) {
                this.bitField0_ |= 2;
                this.fieldInt32_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearFieldInt32() {
                this.bitField0_ &= -3;
                this.fieldInt32_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt64(1, this.fieldInt64_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.fieldInt32_);
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
                    size2 = 0 + CodedOutputStream.computeInt64Size(1, this.fieldInt64_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.fieldInt32_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Annotation parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Annotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Annotation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Annotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Annotation parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Annotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Annotation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Annotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Annotation parseFrom(InputStream input) throws IOException {
                return (Annotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Annotation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Annotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Annotation parseDelimitedFrom(InputStream input) throws IOException {
                return (Annotation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Annotation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Annotation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Annotation parseFrom(CodedInputStream input) throws IOException {
                return (Annotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Annotation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Annotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Annotation prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Annotation, Builder> implements AnnotationOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(Annotation.DEFAULT_INSTANCE);
                }

                @Override // com.android.internal.os.StatsdConfigProto.StatsdConfig.AnnotationOrBuilder
                public boolean hasFieldInt64() {
                    return ((Annotation) this.instance).hasFieldInt64();
                }

                @Override // com.android.internal.os.StatsdConfigProto.StatsdConfig.AnnotationOrBuilder
                public long getFieldInt64() {
                    return ((Annotation) this.instance).getFieldInt64();
                }

                public Builder setFieldInt64(long value) {
                    copyOnWrite();
                    ((Annotation) this.instance).setFieldInt64(value);
                    return this;
                }

                public Builder clearFieldInt64() {
                    copyOnWrite();
                    ((Annotation) this.instance).clearFieldInt64();
                    return this;
                }

                @Override // com.android.internal.os.StatsdConfigProto.StatsdConfig.AnnotationOrBuilder
                public boolean hasFieldInt32() {
                    return ((Annotation) this.instance).hasFieldInt32();
                }

                @Override // com.android.internal.os.StatsdConfigProto.StatsdConfig.AnnotationOrBuilder
                public int getFieldInt32() {
                    return ((Annotation) this.instance).getFieldInt32();
                }

                public Builder setFieldInt32(int value) {
                    copyOnWrite();
                    ((Annotation) this.instance).setFieldInt32(value);
                    return this;
                }

                public Builder clearFieldInt32() {
                    copyOnWrite();
                    ((Annotation) this.instance).clearFieldInt32();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Annotation();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Annotation other = (Annotation) arg1;
                        this.fieldInt64_ = visitor.visitLong(hasFieldInt64(), this.fieldInt64_, other.hasFieldInt64(), other.fieldInt64_);
                        this.fieldInt32_ = visitor.visitInt(hasFieldInt32(), this.fieldInt32_, other.hasFieldInt32(), other.fieldInt32_);
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
                                    this.bitField0_ |= 1;
                                    this.fieldInt64_ = input.readInt64();
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.fieldInt32_ = input.readInt32();
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
                            synchronized (Annotation.class) {
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

            public static Annotation getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Annotation> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<EventMetric> getEventMetricList() {
            return this.eventMetric_;
        }

        public List<? extends EventMetricOrBuilder> getEventMetricOrBuilderList() {
            return this.eventMetric_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getEventMetricCount() {
            return this.eventMetric_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public EventMetric getEventMetric(int index) {
            return this.eventMetric_.get(index);
        }

        public EventMetricOrBuilder getEventMetricOrBuilder(int index) {
            return this.eventMetric_.get(index);
        }

        private void ensureEventMetricIsMutable() {
            if (!this.eventMetric_.isModifiable()) {
                this.eventMetric_ = GeneratedMessageLite.mutableCopy(this.eventMetric_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEventMetric(int index, EventMetric value) {
            if (value != null) {
                ensureEventMetricIsMutable();
                this.eventMetric_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEventMetric(int index, EventMetric.Builder builderForValue) {
            ensureEventMetricIsMutable();
            this.eventMetric_.set(index, (EventMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEventMetric(EventMetric value) {
            if (value != null) {
                ensureEventMetricIsMutable();
                this.eventMetric_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEventMetric(int index, EventMetric value) {
            if (value != null) {
                ensureEventMetricIsMutable();
                this.eventMetric_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEventMetric(EventMetric.Builder builderForValue) {
            ensureEventMetricIsMutable();
            this.eventMetric_.add((EventMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEventMetric(int index, EventMetric.Builder builderForValue) {
            ensureEventMetricIsMutable();
            this.eventMetric_.add(index, (EventMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllEventMetric(Iterable<? extends EventMetric> values) {
            ensureEventMetricIsMutable();
            AbstractMessageLite.addAll(values, this.eventMetric_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEventMetric() {
            this.eventMetric_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeEventMetric(int index) {
            ensureEventMetricIsMutable();
            this.eventMetric_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<CountMetric> getCountMetricList() {
            return this.countMetric_;
        }

        public List<? extends CountMetricOrBuilder> getCountMetricOrBuilderList() {
            return this.countMetric_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getCountMetricCount() {
            return this.countMetric_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public CountMetric getCountMetric(int index) {
            return this.countMetric_.get(index);
        }

        public CountMetricOrBuilder getCountMetricOrBuilder(int index) {
            return this.countMetric_.get(index);
        }

        private void ensureCountMetricIsMutable() {
            if (!this.countMetric_.isModifiable()) {
                this.countMetric_ = GeneratedMessageLite.mutableCopy(this.countMetric_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCountMetric(int index, CountMetric value) {
            if (value != null) {
                ensureCountMetricIsMutable();
                this.countMetric_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCountMetric(int index, CountMetric.Builder builderForValue) {
            ensureCountMetricIsMutable();
            this.countMetric_.set(index, (CountMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addCountMetric(CountMetric value) {
            if (value != null) {
                ensureCountMetricIsMutable();
                this.countMetric_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addCountMetric(int index, CountMetric value) {
            if (value != null) {
                ensureCountMetricIsMutable();
                this.countMetric_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addCountMetric(CountMetric.Builder builderForValue) {
            ensureCountMetricIsMutable();
            this.countMetric_.add((CountMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addCountMetric(int index, CountMetric.Builder builderForValue) {
            ensureCountMetricIsMutable();
            this.countMetric_.add(index, (CountMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllCountMetric(Iterable<? extends CountMetric> values) {
            ensureCountMetricIsMutable();
            AbstractMessageLite.addAll(values, this.countMetric_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCountMetric() {
            this.countMetric_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeCountMetric(int index) {
            ensureCountMetricIsMutable();
            this.countMetric_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<ValueMetric> getValueMetricList() {
            return this.valueMetric_;
        }

        public List<? extends ValueMetricOrBuilder> getValueMetricOrBuilderList() {
            return this.valueMetric_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getValueMetricCount() {
            return this.valueMetric_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public ValueMetric getValueMetric(int index) {
            return this.valueMetric_.get(index);
        }

        public ValueMetricOrBuilder getValueMetricOrBuilder(int index) {
            return this.valueMetric_.get(index);
        }

        private void ensureValueMetricIsMutable() {
            if (!this.valueMetric_.isModifiable()) {
                this.valueMetric_ = GeneratedMessageLite.mutableCopy(this.valueMetric_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValueMetric(int index, ValueMetric value) {
            if (value != null) {
                ensureValueMetricIsMutable();
                this.valueMetric_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValueMetric(int index, ValueMetric.Builder builderForValue) {
            ensureValueMetricIsMutable();
            this.valueMetric_.set(index, (ValueMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addValueMetric(ValueMetric value) {
            if (value != null) {
                ensureValueMetricIsMutable();
                this.valueMetric_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addValueMetric(int index, ValueMetric value) {
            if (value != null) {
                ensureValueMetricIsMutable();
                this.valueMetric_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addValueMetric(ValueMetric.Builder builderForValue) {
            ensureValueMetricIsMutable();
            this.valueMetric_.add((ValueMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addValueMetric(int index, ValueMetric.Builder builderForValue) {
            ensureValueMetricIsMutable();
            this.valueMetric_.add(index, (ValueMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllValueMetric(Iterable<? extends ValueMetric> values) {
            ensureValueMetricIsMutable();
            AbstractMessageLite.addAll(values, this.valueMetric_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValueMetric() {
            this.valueMetric_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeValueMetric(int index) {
            ensureValueMetricIsMutable();
            this.valueMetric_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<GaugeMetric> getGaugeMetricList() {
            return this.gaugeMetric_;
        }

        public List<? extends GaugeMetricOrBuilder> getGaugeMetricOrBuilderList() {
            return this.gaugeMetric_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getGaugeMetricCount() {
            return this.gaugeMetric_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public GaugeMetric getGaugeMetric(int index) {
            return this.gaugeMetric_.get(index);
        }

        public GaugeMetricOrBuilder getGaugeMetricOrBuilder(int index) {
            return this.gaugeMetric_.get(index);
        }

        private void ensureGaugeMetricIsMutable() {
            if (!this.gaugeMetric_.isModifiable()) {
                this.gaugeMetric_ = GeneratedMessageLite.mutableCopy(this.gaugeMetric_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setGaugeMetric(int index, GaugeMetric value) {
            if (value != null) {
                ensureGaugeMetricIsMutable();
                this.gaugeMetric_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setGaugeMetric(int index, GaugeMetric.Builder builderForValue) {
            ensureGaugeMetricIsMutable();
            this.gaugeMetric_.set(index, (GaugeMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addGaugeMetric(GaugeMetric value) {
            if (value != null) {
                ensureGaugeMetricIsMutable();
                this.gaugeMetric_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addGaugeMetric(int index, GaugeMetric value) {
            if (value != null) {
                ensureGaugeMetricIsMutable();
                this.gaugeMetric_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addGaugeMetric(GaugeMetric.Builder builderForValue) {
            ensureGaugeMetricIsMutable();
            this.gaugeMetric_.add((GaugeMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addGaugeMetric(int index, GaugeMetric.Builder builderForValue) {
            ensureGaugeMetricIsMutable();
            this.gaugeMetric_.add(index, (GaugeMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllGaugeMetric(Iterable<? extends GaugeMetric> values) {
            ensureGaugeMetricIsMutable();
            AbstractMessageLite.addAll(values, this.gaugeMetric_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearGaugeMetric() {
            this.gaugeMetric_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeGaugeMetric(int index) {
            ensureGaugeMetricIsMutable();
            this.gaugeMetric_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<DurationMetric> getDurationMetricList() {
            return this.durationMetric_;
        }

        public List<? extends DurationMetricOrBuilder> getDurationMetricOrBuilderList() {
            return this.durationMetric_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getDurationMetricCount() {
            return this.durationMetric_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public DurationMetric getDurationMetric(int index) {
            return this.durationMetric_.get(index);
        }

        public DurationMetricOrBuilder getDurationMetricOrBuilder(int index) {
            return this.durationMetric_.get(index);
        }

        private void ensureDurationMetricIsMutable() {
            if (!this.durationMetric_.isModifiable()) {
                this.durationMetric_ = GeneratedMessageLite.mutableCopy(this.durationMetric_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMetric(int index, DurationMetric value) {
            if (value != null) {
                ensureDurationMetricIsMutable();
                this.durationMetric_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMetric(int index, DurationMetric.Builder builderForValue) {
            ensureDurationMetricIsMutable();
            this.durationMetric_.set(index, (DurationMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDurationMetric(DurationMetric value) {
            if (value != null) {
                ensureDurationMetricIsMutable();
                this.durationMetric_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDurationMetric(int index, DurationMetric value) {
            if (value != null) {
                ensureDurationMetricIsMutable();
                this.durationMetric_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDurationMetric(DurationMetric.Builder builderForValue) {
            ensureDurationMetricIsMutable();
            this.durationMetric_.add((DurationMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDurationMetric(int index, DurationMetric.Builder builderForValue) {
            ensureDurationMetricIsMutable();
            this.durationMetric_.add(index, (DurationMetric) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllDurationMetric(Iterable<? extends DurationMetric> values) {
            ensureDurationMetricIsMutable();
            AbstractMessageLite.addAll(values, this.durationMetric_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMetric() {
            this.durationMetric_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeDurationMetric(int index) {
            ensureDurationMetricIsMutable();
            this.durationMetric_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<AtomMatcher> getAtomMatcherList() {
            return this.atomMatcher_;
        }

        public List<? extends AtomMatcherOrBuilder> getAtomMatcherOrBuilderList() {
            return this.atomMatcher_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getAtomMatcherCount() {
            return this.atomMatcher_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public AtomMatcher getAtomMatcher(int index) {
            return this.atomMatcher_.get(index);
        }

        public AtomMatcherOrBuilder getAtomMatcherOrBuilder(int index) {
            return this.atomMatcher_.get(index);
        }

        private void ensureAtomMatcherIsMutable() {
            if (!this.atomMatcher_.isModifiable()) {
                this.atomMatcher_ = GeneratedMessageLite.mutableCopy(this.atomMatcher_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAtomMatcher(int index, AtomMatcher value) {
            if (value != null) {
                ensureAtomMatcherIsMutable();
                this.atomMatcher_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAtomMatcher(int index, AtomMatcher.Builder builderForValue) {
            ensureAtomMatcherIsMutable();
            this.atomMatcher_.set(index, (AtomMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAtomMatcher(AtomMatcher value) {
            if (value != null) {
                ensureAtomMatcherIsMutable();
                this.atomMatcher_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAtomMatcher(int index, AtomMatcher value) {
            if (value != null) {
                ensureAtomMatcherIsMutable();
                this.atomMatcher_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAtomMatcher(AtomMatcher.Builder builderForValue) {
            ensureAtomMatcherIsMutable();
            this.atomMatcher_.add((AtomMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAtomMatcher(int index, AtomMatcher.Builder builderForValue) {
            ensureAtomMatcherIsMutable();
            this.atomMatcher_.add(index, (AtomMatcher) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllAtomMatcher(Iterable<? extends AtomMatcher> values) {
            ensureAtomMatcherIsMutable();
            AbstractMessageLite.addAll(values, this.atomMatcher_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAtomMatcher() {
            this.atomMatcher_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeAtomMatcher(int index) {
            ensureAtomMatcherIsMutable();
            this.atomMatcher_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<Predicate> getPredicateList() {
            return this.predicate_;
        }

        public List<? extends PredicateOrBuilder> getPredicateOrBuilderList() {
            return this.predicate_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getPredicateCount() {
            return this.predicate_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public Predicate getPredicate(int index) {
            return this.predicate_.get(index);
        }

        public PredicateOrBuilder getPredicateOrBuilder(int index) {
            return this.predicate_.get(index);
        }

        private void ensurePredicateIsMutable() {
            if (!this.predicate_.isModifiable()) {
                this.predicate_ = GeneratedMessageLite.mutableCopy(this.predicate_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPredicate(int index, Predicate value) {
            if (value != null) {
                ensurePredicateIsMutable();
                this.predicate_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPredicate(int index, Predicate.Builder builderForValue) {
            ensurePredicateIsMutable();
            this.predicate_.set(index, (Predicate) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPredicate(Predicate value) {
            if (value != null) {
                ensurePredicateIsMutable();
                this.predicate_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPredicate(int index, Predicate value) {
            if (value != null) {
                ensurePredicateIsMutable();
                this.predicate_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPredicate(Predicate.Builder builderForValue) {
            ensurePredicateIsMutable();
            this.predicate_.add((Predicate) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addPredicate(int index, Predicate.Builder builderForValue) {
            ensurePredicateIsMutable();
            this.predicate_.add(index, (Predicate) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllPredicate(Iterable<? extends Predicate> values) {
            ensurePredicateIsMutable();
            AbstractMessageLite.addAll(values, this.predicate_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPredicate() {
            this.predicate_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removePredicate(int index) {
            ensurePredicateIsMutable();
            this.predicate_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<Alert> getAlertList() {
            return this.alert_;
        }

        public List<? extends AlertOrBuilder> getAlertOrBuilderList() {
            return this.alert_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getAlertCount() {
            return this.alert_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public Alert getAlert(int index) {
            return this.alert_.get(index);
        }

        public AlertOrBuilder getAlertOrBuilder(int index) {
            return this.alert_.get(index);
        }

        private void ensureAlertIsMutable() {
            if (!this.alert_.isModifiable()) {
                this.alert_ = GeneratedMessageLite.mutableCopy(this.alert_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlert(int index, Alert value) {
            if (value != null) {
                ensureAlertIsMutable();
                this.alert_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlert(int index, Alert.Builder builderForValue) {
            ensureAlertIsMutable();
            this.alert_.set(index, (Alert) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAlert(Alert value) {
            if (value != null) {
                ensureAlertIsMutable();
                this.alert_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAlert(int index, Alert value) {
            if (value != null) {
                ensureAlertIsMutable();
                this.alert_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAlert(Alert.Builder builderForValue) {
            ensureAlertIsMutable();
            this.alert_.add((Alert) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAlert(int index, Alert.Builder builderForValue) {
            ensureAlertIsMutable();
            this.alert_.add(index, (Alert) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllAlert(Iterable<? extends Alert> values) {
            ensureAlertIsMutable();
            AbstractMessageLite.addAll(values, this.alert_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAlert() {
            this.alert_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeAlert(int index) {
            ensureAlertIsMutable();
            this.alert_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<Alarm> getAlarmList() {
            return this.alarm_;
        }

        public List<? extends AlarmOrBuilder> getAlarmOrBuilderList() {
            return this.alarm_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getAlarmCount() {
            return this.alarm_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public Alarm getAlarm(int index) {
            return this.alarm_.get(index);
        }

        public AlarmOrBuilder getAlarmOrBuilder(int index) {
            return this.alarm_.get(index);
        }

        private void ensureAlarmIsMutable() {
            if (!this.alarm_.isModifiable()) {
                this.alarm_ = GeneratedMessageLite.mutableCopy(this.alarm_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlarm(int index, Alarm value) {
            if (value != null) {
                ensureAlarmIsMutable();
                this.alarm_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlarm(int index, Alarm.Builder builderForValue) {
            ensureAlarmIsMutable();
            this.alarm_.set(index, (Alarm) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAlarm(Alarm value) {
            if (value != null) {
                ensureAlarmIsMutable();
                this.alarm_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAlarm(int index, Alarm value) {
            if (value != null) {
                ensureAlarmIsMutable();
                this.alarm_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAlarm(Alarm.Builder builderForValue) {
            ensureAlarmIsMutable();
            this.alarm_.add((Alarm) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAlarm(int index, Alarm.Builder builderForValue) {
            ensureAlarmIsMutable();
            this.alarm_.add(index, (Alarm) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllAlarm(Iterable<? extends Alarm> values) {
            ensureAlarmIsMutable();
            AbstractMessageLite.addAll(values, this.alarm_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAlarm() {
            this.alarm_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeAlarm(int index) {
            ensureAlarmIsMutable();
            this.alarm_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<Subscription> getSubscriptionList() {
            return this.subscription_;
        }

        public List<? extends SubscriptionOrBuilder> getSubscriptionOrBuilderList() {
            return this.subscription_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getSubscriptionCount() {
            return this.subscription_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public Subscription getSubscription(int index) {
            return this.subscription_.get(index);
        }

        public SubscriptionOrBuilder getSubscriptionOrBuilder(int index) {
            return this.subscription_.get(index);
        }

        private void ensureSubscriptionIsMutable() {
            if (!this.subscription_.isModifiable()) {
                this.subscription_ = GeneratedMessageLite.mutableCopy(this.subscription_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSubscription(int index, Subscription value) {
            if (value != null) {
                ensureSubscriptionIsMutable();
                this.subscription_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSubscription(int index, Subscription.Builder builderForValue) {
            ensureSubscriptionIsMutable();
            this.subscription_.set(index, (Subscription) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSubscription(Subscription value) {
            if (value != null) {
                ensureSubscriptionIsMutable();
                this.subscription_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSubscription(int index, Subscription value) {
            if (value != null) {
                ensureSubscriptionIsMutable();
                this.subscription_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSubscription(Subscription.Builder builderForValue) {
            ensureSubscriptionIsMutable();
            this.subscription_.add((Subscription) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSubscription(int index, Subscription.Builder builderForValue) {
            ensureSubscriptionIsMutable();
            this.subscription_.add(index, (Subscription) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllSubscription(Iterable<? extends Subscription> values) {
            ensureSubscriptionIsMutable();
            AbstractMessageLite.addAll(values, this.subscription_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSubscription() {
            this.subscription_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeSubscription(int index) {
            ensureSubscriptionIsMutable();
            this.subscription_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<String> getAllowedLogSourceList() {
            return this.allowedLogSource_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getAllowedLogSourceCount() {
            return this.allowedLogSource_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public String getAllowedLogSource(int index) {
            return this.allowedLogSource_.get(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public ByteString getAllowedLogSourceBytes(int index) {
            return ByteString.copyFromUtf8(this.allowedLogSource_.get(index));
        }

        private void ensureAllowedLogSourceIsMutable() {
            if (!this.allowedLogSource_.isModifiable()) {
                this.allowedLogSource_ = GeneratedMessageLite.mutableCopy(this.allowedLogSource_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAllowedLogSource(int index, String value) {
            if (value != null) {
                ensureAllowedLogSourceIsMutable();
                this.allowedLogSource_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllowedLogSource(String value) {
            if (value != null) {
                ensureAllowedLogSourceIsMutable();
                this.allowedLogSource_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllAllowedLogSource(Iterable<String> values) {
            ensureAllowedLogSourceIsMutable();
            AbstractMessageLite.addAll(values, this.allowedLogSource_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAllowedLogSource() {
            this.allowedLogSource_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllowedLogSourceBytes(ByteString value) {
            if (value != null) {
                ensureAllowedLogSourceIsMutable();
                this.allowedLogSource_.add(value.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<Long> getNoReportMetricList() {
            return this.noReportMetric_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getNoReportMetricCount() {
            return this.noReportMetric_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public long getNoReportMetric(int index) {
            return this.noReportMetric_.getLong(index);
        }

        private void ensureNoReportMetricIsMutable() {
            if (!this.noReportMetric_.isModifiable()) {
                this.noReportMetric_ = GeneratedMessageLite.mutableCopy(this.noReportMetric_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNoReportMetric(int index, long value) {
            ensureNoReportMetricIsMutable();
            this.noReportMetric_.setLong(index, value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addNoReportMetric(long value) {
            ensureNoReportMetricIsMutable();
            this.noReportMetric_.addLong(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllNoReportMetric(Iterable<? extends Long> values) {
            ensureNoReportMetricIsMutable();
            AbstractMessageLite.addAll(values, this.noReportMetric_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNoReportMetric() {
            this.noReportMetric_ = emptyLongList();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<Annotation> getAnnotationList() {
            return this.annotation_;
        }

        public List<? extends AnnotationOrBuilder> getAnnotationOrBuilderList() {
            return this.annotation_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getAnnotationCount() {
            return this.annotation_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public Annotation getAnnotation(int index) {
            return this.annotation_.get(index);
        }

        public AnnotationOrBuilder getAnnotationOrBuilder(int index) {
            return this.annotation_.get(index);
        }

        private void ensureAnnotationIsMutable() {
            if (!this.annotation_.isModifiable()) {
                this.annotation_ = GeneratedMessageLite.mutableCopy(this.annotation_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAnnotation(int index, Annotation value) {
            if (value != null) {
                ensureAnnotationIsMutable();
                this.annotation_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAnnotation(int index, Annotation.Builder builderForValue) {
            ensureAnnotationIsMutable();
            this.annotation_.set(index, (Annotation) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAnnotation(Annotation value) {
            if (value != null) {
                ensureAnnotationIsMutable();
                this.annotation_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAnnotation(int index, Annotation value) {
            if (value != null) {
                ensureAnnotationIsMutable();
                this.annotation_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAnnotation(Annotation.Builder builderForValue) {
            ensureAnnotationIsMutable();
            this.annotation_.add((Annotation) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAnnotation(int index, Annotation.Builder builderForValue) {
            ensureAnnotationIsMutable();
            this.annotation_.add(index, (Annotation) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllAnnotation(Iterable<? extends Annotation> values) {
            ensureAnnotationIsMutable();
            AbstractMessageLite.addAll(values, this.annotation_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAnnotation() {
            this.annotation_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeAnnotation(int index) {
            ensureAnnotationIsMutable();
            this.annotation_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean hasTtlInSeconds() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public long getTtlInSeconds() {
            return this.ttlInSeconds_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTtlInSeconds(long value) {
            this.bitField0_ |= 2;
            this.ttlInSeconds_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTtlInSeconds() {
            this.bitField0_ &= -3;
            this.ttlInSeconds_ = 0;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean hasHashStringsInMetricReport() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean getHashStringsInMetricReport() {
            return this.hashStringsInMetricReport_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHashStringsInMetricReport(boolean value) {
            this.bitField0_ |= 4;
            this.hashStringsInMetricReport_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHashStringsInMetricReport() {
            this.bitField0_ &= -5;
            this.hashStringsInMetricReport_ = true;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public List<MetricActivation> getMetricActivationList() {
            return this.metricActivation_;
        }

        public List<? extends MetricActivationOrBuilder> getMetricActivationOrBuilderList() {
            return this.metricActivation_;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public int getMetricActivationCount() {
            return this.metricActivation_.size();
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public MetricActivation getMetricActivation(int index) {
            return this.metricActivation_.get(index);
        }

        public MetricActivationOrBuilder getMetricActivationOrBuilder(int index) {
            return this.metricActivation_.get(index);
        }

        private void ensureMetricActivationIsMutable() {
            if (!this.metricActivation_.isModifiable()) {
                this.metricActivation_ = GeneratedMessageLite.mutableCopy(this.metricActivation_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMetricActivation(int index, MetricActivation value) {
            if (value != null) {
                ensureMetricActivationIsMutable();
                this.metricActivation_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMetricActivation(int index, MetricActivation.Builder builderForValue) {
            ensureMetricActivationIsMutable();
            this.metricActivation_.set(index, (MetricActivation) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addMetricActivation(MetricActivation value) {
            if (value != null) {
                ensureMetricActivationIsMutable();
                this.metricActivation_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addMetricActivation(int index, MetricActivation value) {
            if (value != null) {
                ensureMetricActivationIsMutable();
                this.metricActivation_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addMetricActivation(MetricActivation.Builder builderForValue) {
            ensureMetricActivationIsMutable();
            this.metricActivation_.add((MetricActivation) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addMetricActivation(int index, MetricActivation.Builder builderForValue) {
            ensureMetricActivationIsMutable();
            this.metricActivation_.add(index, (MetricActivation) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllMetricActivation(Iterable<? extends MetricActivation> values) {
            ensureMetricActivationIsMutable();
            AbstractMessageLite.addAll(values, this.metricActivation_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMetricActivation() {
            this.metricActivation_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeMetricActivation(int index) {
            ensureMetricActivationIsMutable();
            this.metricActivation_.remove(index);
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean hasVersionStringsInMetricReport() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean getVersionStringsInMetricReport() {
            return this.versionStringsInMetricReport_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVersionStringsInMetricReport(boolean value) {
            this.bitField0_ |= 8;
            this.versionStringsInMetricReport_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVersionStringsInMetricReport() {
            this.bitField0_ &= -9;
            this.versionStringsInMetricReport_ = false;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean hasInstallerInMetricReport() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean getInstallerInMetricReport() {
            return this.installerInMetricReport_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInstallerInMetricReport(boolean value) {
            this.bitField0_ |= 16;
            this.installerInMetricReport_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInstallerInMetricReport() {
            this.bitField0_ &= -17;
            this.installerInMetricReport_ = false;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean hasPersistLocally() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
        public boolean getPersistLocally() {
            return this.persistLocally_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPersistLocally(boolean value) {
            this.bitField0_ |= 32;
            this.persistLocally_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPersistLocally() {
            this.bitField0_ &= -33;
            this.persistLocally_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.id_);
            }
            for (int i = 0; i < this.eventMetric_.size(); i++) {
                output.writeMessage(2, this.eventMetric_.get(i));
            }
            for (int i2 = 0; i2 < this.countMetric_.size(); i2++) {
                output.writeMessage(3, this.countMetric_.get(i2));
            }
            for (int i3 = 0; i3 < this.valueMetric_.size(); i3++) {
                output.writeMessage(4, this.valueMetric_.get(i3));
            }
            for (int i4 = 0; i4 < this.gaugeMetric_.size(); i4++) {
                output.writeMessage(5, this.gaugeMetric_.get(i4));
            }
            for (int i5 = 0; i5 < this.durationMetric_.size(); i5++) {
                output.writeMessage(6, this.durationMetric_.get(i5));
            }
            for (int i6 = 0; i6 < this.atomMatcher_.size(); i6++) {
                output.writeMessage(7, this.atomMatcher_.get(i6));
            }
            for (int i7 = 0; i7 < this.predicate_.size(); i7++) {
                output.writeMessage(8, this.predicate_.get(i7));
            }
            for (int i8 = 0; i8 < this.alert_.size(); i8++) {
                output.writeMessage(9, this.alert_.get(i8));
            }
            for (int i9 = 0; i9 < this.alarm_.size(); i9++) {
                output.writeMessage(10, this.alarm_.get(i9));
            }
            for (int i10 = 0; i10 < this.subscription_.size(); i10++) {
                output.writeMessage(11, this.subscription_.get(i10));
            }
            for (int i11 = 0; i11 < this.allowedLogSource_.size(); i11++) {
                output.writeString(12, this.allowedLogSource_.get(i11));
            }
            for (int i12 = 0; i12 < this.noReportMetric_.size(); i12++) {
                output.writeInt64(13, this.noReportMetric_.getLong(i12));
            }
            for (int i13 = 0; i13 < this.annotation_.size(); i13++) {
                output.writeMessage(14, this.annotation_.get(i13));
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(15, this.ttlInSeconds_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(16, this.hashStringsInMetricReport_);
            }
            for (int i14 = 0; i14 < this.metricActivation_.size(); i14++) {
                output.writeMessage(17, this.metricActivation_.get(i14));
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(18, this.versionStringsInMetricReport_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBool(19, this.installerInMetricReport_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBool(20, this.persistLocally_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
            }
            for (int i = 0; i < this.eventMetric_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.eventMetric_.get(i));
            }
            for (int i2 = 0; i2 < this.countMetric_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(3, this.countMetric_.get(i2));
            }
            for (int i3 = 0; i3 < this.valueMetric_.size(); i3++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.valueMetric_.get(i3));
            }
            for (int i4 = 0; i4 < this.gaugeMetric_.size(); i4++) {
                size2 += CodedOutputStream.computeMessageSize(5, this.gaugeMetric_.get(i4));
            }
            for (int i5 = 0; i5 < this.durationMetric_.size(); i5++) {
                size2 += CodedOutputStream.computeMessageSize(6, this.durationMetric_.get(i5));
            }
            for (int i6 = 0; i6 < this.atomMatcher_.size(); i6++) {
                size2 += CodedOutputStream.computeMessageSize(7, this.atomMatcher_.get(i6));
            }
            for (int i7 = 0; i7 < this.predicate_.size(); i7++) {
                size2 += CodedOutputStream.computeMessageSize(8, this.predicate_.get(i7));
            }
            for (int i8 = 0; i8 < this.alert_.size(); i8++) {
                size2 += CodedOutputStream.computeMessageSize(9, this.alert_.get(i8));
            }
            for (int i9 = 0; i9 < this.alarm_.size(); i9++) {
                size2 += CodedOutputStream.computeMessageSize(10, this.alarm_.get(i9));
            }
            for (int i10 = 0; i10 < this.subscription_.size(); i10++) {
                size2 += CodedOutputStream.computeMessageSize(11, this.subscription_.get(i10));
            }
            int dataSize = 0;
            for (int i11 = 0; i11 < this.allowedLogSource_.size(); i11++) {
                dataSize += CodedOutputStream.computeStringSizeNoTag(this.allowedLogSource_.get(i11));
            }
            int size3 = size2 + dataSize + (getAllowedLogSourceList().size() * 1);
            int dataSize2 = 0;
            for (int i12 = 0; i12 < this.noReportMetric_.size(); i12++) {
                dataSize2 += CodedOutputStream.computeInt64SizeNoTag(this.noReportMetric_.getLong(i12));
            }
            int size4 = size3 + dataSize2 + (getNoReportMetricList().size() * 1);
            for (int i13 = 0; i13 < this.annotation_.size(); i13++) {
                size4 += CodedOutputStream.computeMessageSize(14, this.annotation_.get(i13));
            }
            if ((this.bitField0_ & 2) == 2) {
                size4 += CodedOutputStream.computeInt64Size(15, this.ttlInSeconds_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size4 += CodedOutputStream.computeBoolSize(16, this.hashStringsInMetricReport_);
            }
            for (int i14 = 0; i14 < this.metricActivation_.size(); i14++) {
                size4 += CodedOutputStream.computeMessageSize(17, this.metricActivation_.get(i14));
            }
            if ((this.bitField0_ & 8) == 8) {
                size4 += CodedOutputStream.computeBoolSize(18, this.versionStringsInMetricReport_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size4 += CodedOutputStream.computeBoolSize(19, this.installerInMetricReport_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size4 += CodedOutputStream.computeBoolSize(20, this.persistLocally_);
            }
            int size5 = size4 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size5;
            return size5;
        }

        public static StatsdConfig parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StatsdConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StatsdConfig parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StatsdConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StatsdConfig parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StatsdConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StatsdConfig parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StatsdConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StatsdConfig parseFrom(InputStream input) throws IOException {
            return (StatsdConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StatsdConfig parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StatsdConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StatsdConfig parseDelimitedFrom(InputStream input) throws IOException {
            return (StatsdConfig) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StatsdConfig parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StatsdConfig) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StatsdConfig parseFrom(CodedInputStream input) throws IOException {
            return (StatsdConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StatsdConfig parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StatsdConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StatsdConfig prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StatsdConfig, Builder> implements StatsdConfigOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(StatsdConfig.DEFAULT_INSTANCE);
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean hasId() {
                return ((StatsdConfig) this.instance).hasId();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public long getId() {
                return ((StatsdConfig) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearId();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<EventMetric> getEventMetricList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getEventMetricList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getEventMetricCount() {
                return ((StatsdConfig) this.instance).getEventMetricCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public EventMetric getEventMetric(int index) {
                return ((StatsdConfig) this.instance).getEventMetric(index);
            }

            public Builder setEventMetric(int index, EventMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setEventMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setEventMetric(int index, EventMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setEventMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addEventMetric(EventMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addEventMetric((StatsdConfig) value);
                return this;
            }

            public Builder addEventMetric(int index, EventMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addEventMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addEventMetric(EventMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addEventMetric((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addEventMetric(int index, EventMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addEventMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllEventMetric(Iterable<? extends EventMetric> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllEventMetric(values);
                return this;
            }

            public Builder clearEventMetric() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearEventMetric();
                return this;
            }

            public Builder removeEventMetric(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeEventMetric(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<CountMetric> getCountMetricList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getCountMetricList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getCountMetricCount() {
                return ((StatsdConfig) this.instance).getCountMetricCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public CountMetric getCountMetric(int index) {
                return ((StatsdConfig) this.instance).getCountMetric(index);
            }

            public Builder setCountMetric(int index, CountMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setCountMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setCountMetric(int index, CountMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setCountMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addCountMetric(CountMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addCountMetric((StatsdConfig) value);
                return this;
            }

            public Builder addCountMetric(int index, CountMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addCountMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addCountMetric(CountMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addCountMetric((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addCountMetric(int index, CountMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addCountMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllCountMetric(Iterable<? extends CountMetric> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllCountMetric(values);
                return this;
            }

            public Builder clearCountMetric() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearCountMetric();
                return this;
            }

            public Builder removeCountMetric(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeCountMetric(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<ValueMetric> getValueMetricList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getValueMetricList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getValueMetricCount() {
                return ((StatsdConfig) this.instance).getValueMetricCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public ValueMetric getValueMetric(int index) {
                return ((StatsdConfig) this.instance).getValueMetric(index);
            }

            public Builder setValueMetric(int index, ValueMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setValueMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setValueMetric(int index, ValueMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setValueMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addValueMetric(ValueMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addValueMetric((StatsdConfig) value);
                return this;
            }

            public Builder addValueMetric(int index, ValueMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addValueMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addValueMetric(ValueMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addValueMetric((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addValueMetric(int index, ValueMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addValueMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllValueMetric(Iterable<? extends ValueMetric> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllValueMetric(values);
                return this;
            }

            public Builder clearValueMetric() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearValueMetric();
                return this;
            }

            public Builder removeValueMetric(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeValueMetric(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<GaugeMetric> getGaugeMetricList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getGaugeMetricList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getGaugeMetricCount() {
                return ((StatsdConfig) this.instance).getGaugeMetricCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public GaugeMetric getGaugeMetric(int index) {
                return ((StatsdConfig) this.instance).getGaugeMetric(index);
            }

            public Builder setGaugeMetric(int index, GaugeMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setGaugeMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setGaugeMetric(int index, GaugeMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setGaugeMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addGaugeMetric(GaugeMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addGaugeMetric((StatsdConfig) value);
                return this;
            }

            public Builder addGaugeMetric(int index, GaugeMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addGaugeMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addGaugeMetric(GaugeMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addGaugeMetric((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addGaugeMetric(int index, GaugeMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addGaugeMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllGaugeMetric(Iterable<? extends GaugeMetric> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllGaugeMetric(values);
                return this;
            }

            public Builder clearGaugeMetric() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearGaugeMetric();
                return this;
            }

            public Builder removeGaugeMetric(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeGaugeMetric(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<DurationMetric> getDurationMetricList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getDurationMetricList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getDurationMetricCount() {
                return ((StatsdConfig) this.instance).getDurationMetricCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public DurationMetric getDurationMetric(int index) {
                return ((StatsdConfig) this.instance).getDurationMetric(index);
            }

            public Builder setDurationMetric(int index, DurationMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setDurationMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setDurationMetric(int index, DurationMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setDurationMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addDurationMetric(DurationMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addDurationMetric((StatsdConfig) value);
                return this;
            }

            public Builder addDurationMetric(int index, DurationMetric value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addDurationMetric((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addDurationMetric(DurationMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addDurationMetric((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addDurationMetric(int index, DurationMetric.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addDurationMetric((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllDurationMetric(Iterable<? extends DurationMetric> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllDurationMetric(values);
                return this;
            }

            public Builder clearDurationMetric() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearDurationMetric();
                return this;
            }

            public Builder removeDurationMetric(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeDurationMetric(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<AtomMatcher> getAtomMatcherList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getAtomMatcherList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getAtomMatcherCount() {
                return ((StatsdConfig) this.instance).getAtomMatcherCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public AtomMatcher getAtomMatcher(int index) {
                return ((StatsdConfig) this.instance).getAtomMatcher(index);
            }

            public Builder setAtomMatcher(int index, AtomMatcher value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setAtomMatcher((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setAtomMatcher(int index, AtomMatcher.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setAtomMatcher((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAtomMatcher(AtomMatcher value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAtomMatcher((StatsdConfig) value);
                return this;
            }

            public Builder addAtomMatcher(int index, AtomMatcher value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAtomMatcher((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addAtomMatcher(AtomMatcher.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAtomMatcher((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addAtomMatcher(int index, AtomMatcher.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAtomMatcher((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllAtomMatcher(Iterable<? extends AtomMatcher> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllAtomMatcher(values);
                return this;
            }

            public Builder clearAtomMatcher() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearAtomMatcher();
                return this;
            }

            public Builder removeAtomMatcher(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeAtomMatcher(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<Predicate> getPredicateList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getPredicateList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getPredicateCount() {
                return ((StatsdConfig) this.instance).getPredicateCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public Predicate getPredicate(int index) {
                return ((StatsdConfig) this.instance).getPredicate(index);
            }

            public Builder setPredicate(int index, Predicate value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setPredicate((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setPredicate(int index, Predicate.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setPredicate((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addPredicate(Predicate value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addPredicate((StatsdConfig) value);
                return this;
            }

            public Builder addPredicate(int index, Predicate value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addPredicate((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addPredicate(Predicate.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addPredicate((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addPredicate(int index, Predicate.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addPredicate((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllPredicate(Iterable<? extends Predicate> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllPredicate(values);
                return this;
            }

            public Builder clearPredicate() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearPredicate();
                return this;
            }

            public Builder removePredicate(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removePredicate(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<Alert> getAlertList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getAlertList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getAlertCount() {
                return ((StatsdConfig) this.instance).getAlertCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public Alert getAlert(int index) {
                return ((StatsdConfig) this.instance).getAlert(index);
            }

            public Builder setAlert(int index, Alert value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setAlert((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setAlert(int index, Alert.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setAlert((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAlert(Alert value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAlert((StatsdConfig) value);
                return this;
            }

            public Builder addAlert(int index, Alert value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAlert((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addAlert(Alert.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAlert((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addAlert(int index, Alert.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAlert((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllAlert(Iterable<? extends Alert> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllAlert(values);
                return this;
            }

            public Builder clearAlert() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearAlert();
                return this;
            }

            public Builder removeAlert(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeAlert(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<Alarm> getAlarmList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getAlarmList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getAlarmCount() {
                return ((StatsdConfig) this.instance).getAlarmCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public Alarm getAlarm(int index) {
                return ((StatsdConfig) this.instance).getAlarm(index);
            }

            public Builder setAlarm(int index, Alarm value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setAlarm((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setAlarm(int index, Alarm.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setAlarm((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAlarm(Alarm value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAlarm((StatsdConfig) value);
                return this;
            }

            public Builder addAlarm(int index, Alarm value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAlarm((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addAlarm(Alarm.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAlarm((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addAlarm(int index, Alarm.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAlarm((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllAlarm(Iterable<? extends Alarm> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllAlarm(values);
                return this;
            }

            public Builder clearAlarm() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearAlarm();
                return this;
            }

            public Builder removeAlarm(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeAlarm(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<Subscription> getSubscriptionList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getSubscriptionList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getSubscriptionCount() {
                return ((StatsdConfig) this.instance).getSubscriptionCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public Subscription getSubscription(int index) {
                return ((StatsdConfig) this.instance).getSubscription(index);
            }

            public Builder setSubscription(int index, Subscription value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setSubscription((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setSubscription(int index, Subscription.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setSubscription((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addSubscription(Subscription value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addSubscription((StatsdConfig) value);
                return this;
            }

            public Builder addSubscription(int index, Subscription value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addSubscription((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addSubscription(Subscription.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addSubscription((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addSubscription(int index, Subscription.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addSubscription((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllSubscription(Iterable<? extends Subscription> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllSubscription(values);
                return this;
            }

            public Builder clearSubscription() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearSubscription();
                return this;
            }

            public Builder removeSubscription(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeSubscription(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<String> getAllowedLogSourceList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getAllowedLogSourceList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getAllowedLogSourceCount() {
                return ((StatsdConfig) this.instance).getAllowedLogSourceCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public String getAllowedLogSource(int index) {
                return ((StatsdConfig) this.instance).getAllowedLogSource(index);
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public ByteString getAllowedLogSourceBytes(int index) {
                return ((StatsdConfig) this.instance).getAllowedLogSourceBytes(index);
            }

            public Builder setAllowedLogSource(int index, String value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setAllowedLogSource(index, value);
                return this;
            }

            public Builder addAllowedLogSource(String value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllowedLogSource(value);
                return this;
            }

            public Builder addAllAllowedLogSource(Iterable<String> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllAllowedLogSource(values);
                return this;
            }

            public Builder clearAllowedLogSource() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearAllowedLogSource();
                return this;
            }

            public Builder addAllowedLogSourceBytes(ByteString value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllowedLogSourceBytes(value);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<Long> getNoReportMetricList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getNoReportMetricList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getNoReportMetricCount() {
                return ((StatsdConfig) this.instance).getNoReportMetricCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public long getNoReportMetric(int index) {
                return ((StatsdConfig) this.instance).getNoReportMetric(index);
            }

            public Builder setNoReportMetric(int index, long value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setNoReportMetric(index, value);
                return this;
            }

            public Builder addNoReportMetric(long value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addNoReportMetric(value);
                return this;
            }

            public Builder addAllNoReportMetric(Iterable<? extends Long> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllNoReportMetric(values);
                return this;
            }

            public Builder clearNoReportMetric() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearNoReportMetric();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<Annotation> getAnnotationList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getAnnotationList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getAnnotationCount() {
                return ((StatsdConfig) this.instance).getAnnotationCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public Annotation getAnnotation(int index) {
                return ((StatsdConfig) this.instance).getAnnotation(index);
            }

            public Builder setAnnotation(int index, Annotation value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setAnnotation((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setAnnotation(int index, Annotation.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setAnnotation((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAnnotation(Annotation value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAnnotation((StatsdConfig) value);
                return this;
            }

            public Builder addAnnotation(int index, Annotation value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAnnotation((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addAnnotation(Annotation.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAnnotation((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addAnnotation(int index, Annotation.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAnnotation((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllAnnotation(Iterable<? extends Annotation> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllAnnotation(values);
                return this;
            }

            public Builder clearAnnotation() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearAnnotation();
                return this;
            }

            public Builder removeAnnotation(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeAnnotation(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean hasTtlInSeconds() {
                return ((StatsdConfig) this.instance).hasTtlInSeconds();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public long getTtlInSeconds() {
                return ((StatsdConfig) this.instance).getTtlInSeconds();
            }

            public Builder setTtlInSeconds(long value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setTtlInSeconds(value);
                return this;
            }

            public Builder clearTtlInSeconds() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearTtlInSeconds();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean hasHashStringsInMetricReport() {
                return ((StatsdConfig) this.instance).hasHashStringsInMetricReport();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean getHashStringsInMetricReport() {
                return ((StatsdConfig) this.instance).getHashStringsInMetricReport();
            }

            public Builder setHashStringsInMetricReport(boolean value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setHashStringsInMetricReport(value);
                return this;
            }

            public Builder clearHashStringsInMetricReport() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearHashStringsInMetricReport();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public List<MetricActivation> getMetricActivationList() {
                return Collections.unmodifiableList(((StatsdConfig) this.instance).getMetricActivationList());
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public int getMetricActivationCount() {
                return ((StatsdConfig) this.instance).getMetricActivationCount();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public MetricActivation getMetricActivation(int index) {
                return ((StatsdConfig) this.instance).getMetricActivation(index);
            }

            public Builder setMetricActivation(int index, MetricActivation value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setMetricActivation((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder setMetricActivation(int index, MetricActivation.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setMetricActivation((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addMetricActivation(MetricActivation value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addMetricActivation((StatsdConfig) value);
                return this;
            }

            public Builder addMetricActivation(int index, MetricActivation value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addMetricActivation((StatsdConfig) index, (int) value);
                return this;
            }

            public Builder addMetricActivation(MetricActivation.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addMetricActivation((StatsdConfig) builderForValue);
                return this;
            }

            public Builder addMetricActivation(int index, MetricActivation.Builder builderForValue) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addMetricActivation((StatsdConfig) index, (int) builderForValue);
                return this;
            }

            public Builder addAllMetricActivation(Iterable<? extends MetricActivation> values) {
                copyOnWrite();
                ((StatsdConfig) this.instance).addAllMetricActivation(values);
                return this;
            }

            public Builder clearMetricActivation() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearMetricActivation();
                return this;
            }

            public Builder removeMetricActivation(int index) {
                copyOnWrite();
                ((StatsdConfig) this.instance).removeMetricActivation(index);
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean hasVersionStringsInMetricReport() {
                return ((StatsdConfig) this.instance).hasVersionStringsInMetricReport();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean getVersionStringsInMetricReport() {
                return ((StatsdConfig) this.instance).getVersionStringsInMetricReport();
            }

            public Builder setVersionStringsInMetricReport(boolean value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setVersionStringsInMetricReport(value);
                return this;
            }

            public Builder clearVersionStringsInMetricReport() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearVersionStringsInMetricReport();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean hasInstallerInMetricReport() {
                return ((StatsdConfig) this.instance).hasInstallerInMetricReport();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean getInstallerInMetricReport() {
                return ((StatsdConfig) this.instance).getInstallerInMetricReport();
            }

            public Builder setInstallerInMetricReport(boolean value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setInstallerInMetricReport(value);
                return this;
            }

            public Builder clearInstallerInMetricReport() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearInstallerInMetricReport();
                return this;
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean hasPersistLocally() {
                return ((StatsdConfig) this.instance).hasPersistLocally();
            }

            @Override // com.android.internal.os.StatsdConfigProto.StatsdConfigOrBuilder
            public boolean getPersistLocally() {
                return ((StatsdConfig) this.instance).getPersistLocally();
            }

            public Builder setPersistLocally(boolean value) {
                copyOnWrite();
                ((StatsdConfig) this.instance).setPersistLocally(value);
                return this;
            }

            public Builder clearPersistLocally() {
                copyOnWrite();
                ((StatsdConfig) this.instance).clearPersistLocally();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StatsdConfig();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.eventMetric_.makeImmutable();
                    this.countMetric_.makeImmutable();
                    this.valueMetric_.makeImmutable();
                    this.gaugeMetric_.makeImmutable();
                    this.durationMetric_.makeImmutable();
                    this.atomMatcher_.makeImmutable();
                    this.predicate_.makeImmutable();
                    this.alert_.makeImmutable();
                    this.alarm_.makeImmutable();
                    this.subscription_.makeImmutable();
                    this.allowedLogSource_.makeImmutable();
                    this.noReportMetric_.makeImmutable();
                    this.annotation_.makeImmutable();
                    this.metricActivation_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    StatsdConfig other = (StatsdConfig) arg1;
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
                    this.eventMetric_ = visitor.visitList(this.eventMetric_, other.eventMetric_);
                    this.countMetric_ = visitor.visitList(this.countMetric_, other.countMetric_);
                    this.valueMetric_ = visitor.visitList(this.valueMetric_, other.valueMetric_);
                    this.gaugeMetric_ = visitor.visitList(this.gaugeMetric_, other.gaugeMetric_);
                    this.durationMetric_ = visitor.visitList(this.durationMetric_, other.durationMetric_);
                    this.atomMatcher_ = visitor.visitList(this.atomMatcher_, other.atomMatcher_);
                    this.predicate_ = visitor.visitList(this.predicate_, other.predicate_);
                    this.alert_ = visitor.visitList(this.alert_, other.alert_);
                    this.alarm_ = visitor.visitList(this.alarm_, other.alarm_);
                    this.subscription_ = visitor.visitList(this.subscription_, other.subscription_);
                    this.allowedLogSource_ = visitor.visitList(this.allowedLogSource_, other.allowedLogSource_);
                    this.noReportMetric_ = visitor.visitLongList(this.noReportMetric_, other.noReportMetric_);
                    this.annotation_ = visitor.visitList(this.annotation_, other.annotation_);
                    this.ttlInSeconds_ = visitor.visitLong(hasTtlInSeconds(), this.ttlInSeconds_, other.hasTtlInSeconds(), other.ttlInSeconds_);
                    this.hashStringsInMetricReport_ = visitor.visitBoolean(hasHashStringsInMetricReport(), this.hashStringsInMetricReport_, other.hasHashStringsInMetricReport(), other.hashStringsInMetricReport_);
                    this.metricActivation_ = visitor.visitList(this.metricActivation_, other.metricActivation_);
                    this.versionStringsInMetricReport_ = visitor.visitBoolean(hasVersionStringsInMetricReport(), this.versionStringsInMetricReport_, other.hasVersionStringsInMetricReport(), other.versionStringsInMetricReport_);
                    this.installerInMetricReport_ = visitor.visitBoolean(hasInstallerInMetricReport(), this.installerInMetricReport_, other.hasInstallerInMetricReport(), other.installerInMetricReport_);
                    this.persistLocally_ = visitor.visitBoolean(hasPersistLocally(), this.persistLocally_, other.hasPersistLocally(), other.persistLocally_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.id_ = input.readInt64();
                                    break;
                                case 18:
                                    if (!this.eventMetric_.isModifiable()) {
                                        this.eventMetric_ = GeneratedMessageLite.mutableCopy(this.eventMetric_);
                                    }
                                    this.eventMetric_.add((EventMetric) input.readMessage(EventMetric.parser(), extensionRegistry));
                                    break;
                                case 26:
                                    if (!this.countMetric_.isModifiable()) {
                                        this.countMetric_ = GeneratedMessageLite.mutableCopy(this.countMetric_);
                                    }
                                    this.countMetric_.add((CountMetric) input.readMessage(CountMetric.parser(), extensionRegistry));
                                    break;
                                case 34:
                                    if (!this.valueMetric_.isModifiable()) {
                                        this.valueMetric_ = GeneratedMessageLite.mutableCopy(this.valueMetric_);
                                    }
                                    this.valueMetric_.add((ValueMetric) input.readMessage(ValueMetric.parser(), extensionRegistry));
                                    break;
                                case 42:
                                    if (!this.gaugeMetric_.isModifiable()) {
                                        this.gaugeMetric_ = GeneratedMessageLite.mutableCopy(this.gaugeMetric_);
                                    }
                                    this.gaugeMetric_.add((GaugeMetric) input.readMessage(GaugeMetric.parser(), extensionRegistry));
                                    break;
                                case 50:
                                    if (!this.durationMetric_.isModifiable()) {
                                        this.durationMetric_ = GeneratedMessageLite.mutableCopy(this.durationMetric_);
                                    }
                                    this.durationMetric_.add((DurationMetric) input.readMessage(DurationMetric.parser(), extensionRegistry));
                                    break;
                                case 58:
                                    if (!this.atomMatcher_.isModifiable()) {
                                        this.atomMatcher_ = GeneratedMessageLite.mutableCopy(this.atomMatcher_);
                                    }
                                    this.atomMatcher_.add((AtomMatcher) input.readMessage(AtomMatcher.parser(), extensionRegistry));
                                    break;
                                case 66:
                                    if (!this.predicate_.isModifiable()) {
                                        this.predicate_ = GeneratedMessageLite.mutableCopy(this.predicate_);
                                    }
                                    this.predicate_.add((Predicate) input.readMessage(Predicate.parser(), extensionRegistry));
                                    break;
                                case 74:
                                    if (!this.alert_.isModifiable()) {
                                        this.alert_ = GeneratedMessageLite.mutableCopy(this.alert_);
                                    }
                                    this.alert_.add((Alert) input.readMessage(Alert.parser(), extensionRegistry));
                                    break;
                                case 82:
                                    if (!this.alarm_.isModifiable()) {
                                        this.alarm_ = GeneratedMessageLite.mutableCopy(this.alarm_);
                                    }
                                    this.alarm_.add((Alarm) input.readMessage(Alarm.parser(), extensionRegistry));
                                    break;
                                case 90:
                                    if (!this.subscription_.isModifiable()) {
                                        this.subscription_ = GeneratedMessageLite.mutableCopy(this.subscription_);
                                    }
                                    this.subscription_.add((Subscription) input.readMessage(Subscription.parser(), extensionRegistry));
                                    break;
                                case 98:
                                    String s = input.readString();
                                    if (!this.allowedLogSource_.isModifiable()) {
                                        this.allowedLogSource_ = GeneratedMessageLite.mutableCopy(this.allowedLogSource_);
                                    }
                                    this.allowedLogSource_.add(s);
                                    break;
                                case 104:
                                    if (!this.noReportMetric_.isModifiable()) {
                                        this.noReportMetric_ = GeneratedMessageLite.mutableCopy(this.noReportMetric_);
                                    }
                                    this.noReportMetric_.addLong(input.readInt64());
                                    break;
                                case 106:
                                    int limit = input.pushLimit(input.readRawVarint32());
                                    if (!this.noReportMetric_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                        this.noReportMetric_ = GeneratedMessageLite.mutableCopy(this.noReportMetric_);
                                    }
                                    while (input.getBytesUntilLimit() > 0) {
                                        this.noReportMetric_.addLong(input.readInt64());
                                    }
                                    input.popLimit(limit);
                                    break;
                                case 114:
                                    if (!this.annotation_.isModifiable()) {
                                        this.annotation_ = GeneratedMessageLite.mutableCopy(this.annotation_);
                                    }
                                    this.annotation_.add((Annotation) input.readMessage(Annotation.parser(), extensionRegistry));
                                    break;
                                case 120:
                                    this.bitField0_ |= 2;
                                    this.ttlInSeconds_ = input.readInt64();
                                    break;
                                case 128:
                                    this.bitField0_ |= 4;
                                    this.hashStringsInMetricReport_ = input.readBool();
                                    break;
                                case 138:
                                    if (!this.metricActivation_.isModifiable()) {
                                        this.metricActivation_ = GeneratedMessageLite.mutableCopy(this.metricActivation_);
                                    }
                                    this.metricActivation_.add((MetricActivation) input.readMessage(MetricActivation.parser(), extensionRegistry));
                                    break;
                                case 144:
                                    this.bitField0_ |= 8;
                                    this.versionStringsInMetricReport_ = input.readBool();
                                    break;
                                case 152:
                                    this.bitField0_ |= 16;
                                    this.installerInMetricReport_ = input.readBool();
                                    break;
                                case 160:
                                    this.bitField0_ |= 32;
                                    this.persistLocally_ = input.readBool();
                                    break;
                                default:
                                    if (parseUnknownField(tag, input)) {
                                        break;
                                    } else {
                                        done = true;
                                        break;
                                    }
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
                        synchronized (StatsdConfig.class) {
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

        public static StatsdConfig getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StatsdConfig> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
