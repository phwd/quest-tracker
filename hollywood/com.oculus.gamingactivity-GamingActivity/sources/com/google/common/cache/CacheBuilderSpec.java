package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.LocalCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
public final class CacheBuilderSpec {
    private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
    private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
    private static final ImmutableMap<String, ValueParser> VALUE_PARSERS = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser()).put("weakKeys", new KeyStrengthParser(LocalCache.Strength.WEAK)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(LocalCache.Strength.WEAK)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).build();
    @VisibleForTesting
    long accessExpirationDuration;
    @VisibleForTesting
    @MonotonicNonNullDecl
    TimeUnit accessExpirationTimeUnit;
    @VisibleForTesting
    @MonotonicNonNullDecl
    Integer concurrencyLevel;
    @VisibleForTesting
    @MonotonicNonNullDecl
    Integer initialCapacity;
    @VisibleForTesting
    @MonotonicNonNullDecl
    LocalCache.Strength keyStrength;
    @VisibleForTesting
    @MonotonicNonNullDecl
    Long maximumSize;
    @VisibleForTesting
    @MonotonicNonNullDecl
    Long maximumWeight;
    @VisibleForTesting
    @MonotonicNonNullDecl
    Boolean recordStats;
    @VisibleForTesting
    long refreshDuration;
    @VisibleForTesting
    @MonotonicNonNullDecl
    TimeUnit refreshTimeUnit;
    private final String specification;
    @VisibleForTesting
    @MonotonicNonNullDecl
    LocalCache.Strength valueStrength;
    @VisibleForTesting
    long writeExpirationDuration;
    @VisibleForTesting
    @MonotonicNonNullDecl
    TimeUnit writeExpirationTimeUnit;

    /* access modifiers changed from: private */
    public interface ValueParser {
        void parse(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2);
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
        if (this.initialCapacity != null) {
            builder.initialCapacity(this.initialCapacity.intValue());
        }
        if (this.maximumSize != null) {
            builder.maximumSize(this.maximumSize.longValue());
        }
        if (this.maximumWeight != null) {
            builder.maximumWeight(this.maximumWeight.longValue());
        }
        if (this.concurrencyLevel != null) {
            builder.concurrencyLevel(this.concurrencyLevel.intValue());
        }
        if (this.keyStrength != null) {
            switch (this.keyStrength) {
                default:
                    throw new AssertionError();
                case WEAK:
                    builder.weakKeys();
                    break;
            }
        }
        if (this.valueStrength != null) {
            switch (this.valueStrength) {
                case WEAK:
                    builder.weakValues();
                    break;
                case SOFT:
                    builder.softValues();
                    break;
                default:
                    throw new AssertionError();
            }
        }
        if (this.recordStats != null && this.recordStats.booleanValue()) {
            builder.recordStats();
        }
        if (this.writeExpirationTimeUnit != null) {
            builder.expireAfterWrite(this.writeExpirationDuration, this.writeExpirationTimeUnit);
        }
        if (this.accessExpirationTimeUnit != null) {
            builder.expireAfterAccess(this.accessExpirationDuration, this.accessExpirationTimeUnit);
        }
        if (this.refreshTimeUnit != null) {
            builder.refreshAfterWrite(this.refreshDuration, this.refreshTimeUnit);
        }
        return builder;
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

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec that = (CacheBuilderSpec) obj;
        return Objects.equal(this.initialCapacity, that.initialCapacity) && Objects.equal(this.maximumSize, that.maximumSize) && Objects.equal(this.maximumWeight, that.maximumWeight) && Objects.equal(this.concurrencyLevel, that.concurrencyLevel) && Objects.equal(this.keyStrength, that.keyStrength) && Objects.equal(this.valueStrength, that.valueStrength) && Objects.equal(this.recordStats, that.recordStats) && Objects.equal(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(that.writeExpirationDuration, that.writeExpirationTimeUnit)) && Objects.equal(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(that.accessExpirationDuration, that.accessExpirationTimeUnit)) && Objects.equal(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(that.refreshDuration, that.refreshTimeUnit));
    }

    @NullableDecl
    private static Long durationInNanos(long duration, @NullableDecl TimeUnit unit) {
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
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", new Object[]{key, value}), e);
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
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", new Object[]{key, value}), e);
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
            boolean z;
            boolean z2 = true;
            if (spec.maximumSize == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "maximum size was already set to ", spec.maximumSize);
            if (spec.maximumWeight != null) {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "maximum weight was already set to ", spec.maximumWeight);
            spec.maximumSize = Long.valueOf(value);
        }
    }

    static class MaximumWeightParser extends LongParser {
        MaximumWeightParser() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        public void parseLong(CacheBuilderSpec spec, long value) {
            boolean z;
            boolean z2 = true;
            if (spec.maximumWeight == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "maximum weight was already set to ", spec.maximumWeight);
            if (spec.maximumSize != null) {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "maximum size was already set to ", spec.maximumSize);
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
        public void parse(CacheBuilderSpec spec, String key, @NullableDecl String value) {
            boolean z;
            boolean z2 = true;
            if (value == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "key %s does not take values", key);
            if (spec.keyStrength != null) {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "%s was already set to %s", key, spec.keyStrength);
            spec.keyStrength = this.strength;
        }
    }

    static class ValueStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength2) {
            this.strength = strength2;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, @NullableDecl String value) {
            boolean z = true;
            Preconditions.checkArgument(value == null, "key %s does not take values", key);
            if (spec.valueStrength != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "%s was already set to %s", key, spec.valueStrength);
            spec.valueStrength = this.strength;
        }
    }

    static class RecordStatsParser implements ValueParser {
        RecordStatsParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, @NullableDecl String value) {
            boolean z;
            boolean z2 = false;
            if (value == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "recordStats does not take values");
            if (spec.recordStats == null) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "recordStats already set");
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
            boolean z;
            TimeUnit timeUnit;
            if (value == null || value.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            Preconditions.checkArgument(z, "value of key %s omitted", key);
            try {
                switch (value.charAt(value.length() - 1)) {
                    case 'd':
                        timeUnit = TimeUnit.DAYS;
                        break;
                    case 'h':
                        timeUnit = TimeUnit.HOURS;
                        break;
                    case 'm':
                        timeUnit = TimeUnit.MINUTES;
                        break;
                    case 's':
                        timeUnit = TimeUnit.SECONDS;
                        break;
                    default:
                        throw new IllegalArgumentException(CacheBuilderSpec.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", new Object[]{key, value}));
                }
                parseDuration(spec, Long.parseLong(value.substring(0, value.length() - 1)), timeUnit);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", new Object[]{key, value}));
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

    /* access modifiers changed from: private */
    public static String format(String format, Object... args) {
        return String.format(Locale.ROOT, format, args);
    }
}
