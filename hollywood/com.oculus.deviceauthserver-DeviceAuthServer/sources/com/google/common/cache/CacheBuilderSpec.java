package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.LocalCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Beta
public final class CacheBuilderSpec {
    private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
    private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
    private static final ImmutableMap<String, ValueParser> VALUE_PARSERS = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser()).put("weakKeys", new KeyStrengthParser(LocalCache.Strength.WEAK)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(LocalCache.Strength.WEAK)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).build();
    @VisibleForTesting
    long accessExpirationDuration;
    @VisibleForTesting
    TimeUnit accessExpirationTimeUnit;
    @VisibleForTesting
    Integer concurrencyLevel;
    @VisibleForTesting
    Integer initialCapacity;
    @VisibleForTesting
    LocalCache.Strength keyStrength;
    @VisibleForTesting
    Long maximumSize;
    @VisibleForTesting
    Long maximumWeight;
    @VisibleForTesting
    Boolean recordStats;
    @VisibleForTesting
    long refreshDuration;
    @VisibleForTesting
    TimeUnit refreshTimeUnit;
    private final String specification;
    @VisibleForTesting
    LocalCache.Strength valueStrength;
    @VisibleForTesting
    long writeExpirationDuration;
    @VisibleForTesting
    TimeUnit writeExpirationTimeUnit;

    /* access modifiers changed from: private */
    public interface ValueParser {
        void parse(CacheBuilderSpec cacheBuilderSpec, String str, @Nullable String str2);
    }

    private CacheBuilderSpec(String specification2) {
        this.specification = specification2;
    }

    public static CacheBuilderSpec parse(String cacheBuilderSpecification) {
        CacheBuilderSpec spec = new CacheBuilderSpec(cacheBuilderSpecification);
        if (!cacheBuilderSpecification.isEmpty()) {
            for (String keyValuePair : KEYS_SPLITTER.split(cacheBuilderSpecification)) {
                List<String> keyAndValue = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(keyValuePair));
                Preconditions.checkArgument(!keyAndValue.isEmpty(), "blank key-value pair");
                Preconditions.checkArgument(keyAndValue.size() <= 2, "key-value pair %s with more than one equals sign", keyValuePair);
                String key = keyAndValue.get(0);
                ValueParser valueParser = VALUE_PARSERS.get(key);
                Preconditions.checkArgument(valueParser != null, "unknown key %s", key);
                valueParser.parse(spec, key, keyAndValue.size() == 1 ? null : keyAndValue.get(1));
            }
        }
        return spec;
    }

    public static CacheBuilderSpec disableCaching() {
        return parse("maximumSize=0");
    }

    /* access modifiers changed from: package-private */
    public CacheBuilder<Object, Object> toCacheBuilder() {
        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
        Integer num = this.initialCapacity;
        if (num != null) {
            builder.initialCapacity(num.intValue());
        }
        Long l = this.maximumSize;
        if (l != null) {
            builder.maximumSize(l.longValue());
        }
        Long l2 = this.maximumWeight;
        if (l2 != null) {
            builder.maximumWeight(l2.longValue());
        }
        Integer num2 = this.concurrencyLevel;
        if (num2 != null) {
            builder.concurrencyLevel(num2.intValue());
        }
        if (this.keyStrength != null) {
            if (AnonymousClass1.$SwitchMap$com$google$common$cache$LocalCache$Strength[this.keyStrength.ordinal()] == 1) {
                builder.weakKeys();
            } else {
                throw new AssertionError();
            }
        }
        if (this.valueStrength != null) {
            int i = AnonymousClass1.$SwitchMap$com$google$common$cache$LocalCache$Strength[this.valueStrength.ordinal()];
            if (i == 1) {
                builder.weakValues();
            } else if (i == 2) {
                builder.softValues();
            } else {
                throw new AssertionError();
            }
        }
        Boolean bool = this.recordStats;
        if (bool != null && bool.booleanValue()) {
            builder.recordStats();
        }
        TimeUnit timeUnit = this.writeExpirationTimeUnit;
        if (timeUnit != null) {
            builder.expireAfterWrite(this.writeExpirationDuration, timeUnit);
        }
        TimeUnit timeUnit2 = this.accessExpirationTimeUnit;
        if (timeUnit2 != null) {
            builder.expireAfterAccess(this.accessExpirationDuration, timeUnit2);
        }
        TimeUnit timeUnit3 = this.refreshTimeUnit;
        if (timeUnit3 != null) {
            builder.refreshAfterWrite(this.refreshDuration, timeUnit3);
        }
        return builder;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.cache.CacheBuilderSpec$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$cache$LocalCache$Strength = new int[LocalCache.Strength.values().length];

        static {
            try {
                $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(toParsableString()).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(this.refreshDuration, this.refreshTimeUnit));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec that = (CacheBuilderSpec) obj;
        if (!Objects.equal(this.initialCapacity, that.initialCapacity) || !Objects.equal(this.maximumSize, that.maximumSize) || !Objects.equal(this.maximumWeight, that.maximumWeight) || !Objects.equal(this.concurrencyLevel, that.concurrencyLevel) || !Objects.equal(this.keyStrength, that.keyStrength) || !Objects.equal(this.valueStrength, that.valueStrength) || !Objects.equal(this.recordStats, that.recordStats) || !Objects.equal(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(that.writeExpirationDuration, that.writeExpirationTimeUnit)) || !Objects.equal(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(that.accessExpirationDuration, that.accessExpirationTimeUnit)) || !Objects.equal(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(that.refreshDuration, that.refreshTimeUnit))) {
            return false;
        }
        return true;
    }

    @Nullable
    private static Long durationInNanos(long duration, @Nullable TimeUnit unit) {
        if (unit == null) {
            return null;
        }
        return Long.valueOf(unit.toNanos(duration));
    }

    static abstract class IntegerParser implements ValueParser {
        /* access modifiers changed from: protected */
        public abstract void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i);

        IntegerParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, String value) {
            Preconditions.checkArgument(value != null && !value.isEmpty(), "value of key %s omitted", key);
            try {
                parseInteger(spec, Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", key, value), e);
            }
        }
    }

    static abstract class LongParser implements ValueParser {
        /* access modifiers changed from: protected */
        public abstract void parseLong(CacheBuilderSpec cacheBuilderSpec, long j);

        LongParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, String value) {
            Preconditions.checkArgument(value != null && !value.isEmpty(), "value of key %s omitted", key);
            try {
                parseLong(spec, Long.parseLong(value));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", key, value), e);
            }
        }
    }

    static class InitialCapacityParser extends IntegerParser {
        InitialCapacityParser() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        public void parseInteger(CacheBuilderSpec spec, int value) {
            Preconditions.checkArgument(spec.initialCapacity == null, "initial capacity was already set to ", spec.initialCapacity);
            spec.initialCapacity = Integer.valueOf(value);
        }
    }

    static class MaximumSizeParser extends LongParser {
        MaximumSizeParser() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        public void parseLong(CacheBuilderSpec spec, long value) {
            Preconditions.checkArgument(spec.maximumSize == null, "maximum size was already set to ", spec.maximumSize);
            Preconditions.checkArgument(spec.maximumWeight == null, "maximum weight was already set to ", spec.maximumWeight);
            spec.maximumSize = Long.valueOf(value);
        }
    }

    static class MaximumWeightParser extends LongParser {
        MaximumWeightParser() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        public void parseLong(CacheBuilderSpec spec, long value) {
            Preconditions.checkArgument(spec.maximumWeight == null, "maximum weight was already set to ", spec.maximumWeight);
            Preconditions.checkArgument(spec.maximumSize == null, "maximum size was already set to ", spec.maximumSize);
            spec.maximumWeight = Long.valueOf(value);
        }
    }

    static class ConcurrencyLevelParser extends IntegerParser {
        ConcurrencyLevelParser() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        public void parseInteger(CacheBuilderSpec spec, int value) {
            Preconditions.checkArgument(spec.concurrencyLevel == null, "concurrency level was already set to ", spec.concurrencyLevel);
            spec.concurrencyLevel = Integer.valueOf(value);
        }
    }

    static class KeyStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public KeyStrengthParser(LocalCache.Strength strength2) {
            this.strength = strength2;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
            Preconditions.checkArgument(value == null, "key %s does not take values", key);
            Preconditions.checkArgument(spec.keyStrength == null, "%s was already set to %s", key, spec.keyStrength);
            spec.keyStrength = this.strength;
        }
    }

    static class ValueStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength2) {
            this.strength = strength2;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
            Preconditions.checkArgument(value == null, "key %s does not take values", key);
            Preconditions.checkArgument(spec.valueStrength == null, "%s was already set to %s", key, spec.valueStrength);
            spec.valueStrength = this.strength;
        }
    }

    static class RecordStatsParser implements ValueParser {
        RecordStatsParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
            boolean z = false;
            Preconditions.checkArgument(value == null, "recordStats does not take values");
            if (spec.recordStats == null) {
                z = true;
            }
            Preconditions.checkArgument(z, "recordStats already set");
            spec.recordStats = true;
        }
    }

    static abstract class DurationParser implements ValueParser {
        /* access modifiers changed from: protected */
        public abstract void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit);

        DurationParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, String value) {
            TimeUnit timeUnit;
            Preconditions.checkArgument(value != null && !value.isEmpty(), "value of key %s omitted", key);
            try {
                char lastChar = value.charAt(value.length() - 1);
                if (lastChar == 'd') {
                    timeUnit = TimeUnit.DAYS;
                } else if (lastChar == 'h') {
                    timeUnit = TimeUnit.HOURS;
                } else if (lastChar == 'm') {
                    timeUnit = TimeUnit.MINUTES;
                } else if (lastChar == 's') {
                    timeUnit = TimeUnit.SECONDS;
                } else {
                    throw new IllegalArgumentException(String.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", key, value));
                }
                parseDuration(spec, Long.parseLong(value.substring(0, value.length() - 1)), timeUnit);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", key, value));
            }
        }
    }

    static class AccessDurationParser extends DurationParser {
        AccessDurationParser() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
            Preconditions.checkArgument(spec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
            spec.accessExpirationDuration = duration;
            spec.accessExpirationTimeUnit = unit;
        }
    }

    static class WriteDurationParser extends DurationParser {
        WriteDurationParser() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
            Preconditions.checkArgument(spec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
            spec.writeExpirationDuration = duration;
            spec.writeExpirationTimeUnit = unit;
        }
    }

    static class RefreshDurationParser extends DurationParser {
        RefreshDurationParser() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
            Preconditions.checkArgument(spec.refreshTimeUnit == null, "refreshAfterWrite already set");
            spec.refreshDuration = duration;
            spec.refreshTimeUnit = unit;
        }
    }
}
