package java.nio.file.attribute;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class FileTime implements Comparable {
    private Instant instant;
    private final TimeUnit unit;
    private final long value;
    private String valueAsString;

    private static long scale(long j, long j2, long j3) {
        if (j > j3) {
            return Long.MAX_VALUE;
        }
        if (j < (-j3)) {
            return Long.MIN_VALUE;
        }
        return j * j2;
    }

    private FileTime(long j, TimeUnit timeUnit, Instant instant2) {
        this.value = j;
        this.unit = timeUnit;
        this.instant = instant2;
    }

    public static FileTime from(long j, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit, "unit");
        return new FileTime(j, timeUnit, null);
    }

    public long toMillis() {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null) {
            return timeUnit.toMillis(this.value);
        }
        long epochSecond = this.instant.getEpochSecond();
        int nano = this.instant.getNano();
        long j = epochSecond * 1000;
        if (((Math.abs(epochSecond) | 1000) >>> 31) == 0 || j / 1000 == epochSecond) {
            return j + ((long) (nano / 1000000));
        }
        return epochSecond < 0 ? Long.MIN_VALUE : Long.MAX_VALUE;
    }

    public Instant toInstant() {
        long j;
        int i;
        long j2;
        if (this.instant == null) {
            int i2 = 0;
            switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[this.unit.ordinal()]) {
                case 1:
                    j = scale(this.value, 86400, 106751991167300L);
                    break;
                case 2:
                    j = scale(this.value, 3600, 2562047788015215L);
                    break;
                case 3:
                    j = scale(this.value, 60, 153722867280912930L);
                    break;
                case 4:
                    j = this.value;
                    break;
                case 5:
                    j2 = Math.floorDiv(this.value, 1000);
                    i = ((int) Math.floorMod(this.value, 1000)) * 1000000;
                    i2 = i;
                    j = j2;
                    break;
                case 6:
                    j2 = Math.floorDiv(this.value, 1000000);
                    i = ((int) Math.floorMod(this.value, 1000000)) * 1000;
                    i2 = i;
                    j = j2;
                    break;
                case 7:
                    j2 = Math.floorDiv(this.value, 1000000000);
                    i = (int) Math.floorMod(this.value, 1000000000);
                    i2 = i;
                    j = j2;
                    break;
                default:
                    throw new AssertionError((Object) "Unit not handled");
            }
            if (j <= -31557014167219200L) {
                this.instant = Instant.MIN;
            } else if (j >= 31556889864403199L) {
                this.instant = Instant.MAX;
            } else {
                this.instant = Instant.ofEpochSecond(j, (long) i2);
            }
        }
        return this.instant;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.nio.file.attribute.FileTime$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit = new int[TimeUnit.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                java.util.concurrent.TimeUnit[] r0 = java.util.concurrent.TimeUnit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                java.nio.file.attribute.FileTime.AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit = r0
                int[] r0 = java.nio.file.attribute.FileTime.AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x0014 }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.DAYS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = java.nio.file.attribute.FileTime.AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x001f }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.HOURS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = java.nio.file.attribute.FileTime.AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x002a }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MINUTES     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = java.nio.file.attribute.FileTime.AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x0035 }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = java.nio.file.attribute.FileTime.AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x0040 }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = java.nio.file.attribute.FileTime.AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x004b }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MICROSECONDS     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = java.nio.file.attribute.FileTime.AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x0056 }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.nio.file.attribute.FileTime.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof FileTime) && compareTo((FileTime) obj) == 0;
    }

    public int hashCode() {
        return toInstant().hashCode();
    }

    private long toDays() {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null) {
            return timeUnit.toDays(this.value);
        }
        return TimeUnit.SECONDS.toDays(toInstant().getEpochSecond());
    }

    private long toExcessNanos(long j) {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null) {
            return timeUnit.toNanos(this.value - timeUnit.convert(j, TimeUnit.DAYS));
        }
        return TimeUnit.SECONDS.toNanos(toInstant().getEpochSecond() - TimeUnit.DAYS.toSeconds(j));
    }

    public int compareTo(FileTime fileTime) {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null && timeUnit == fileTime.unit) {
            return Long.compare(this.value, fileTime.value);
        }
        long epochSecond = toInstant().getEpochSecond();
        int compare = Long.compare(epochSecond, fileTime.toInstant().getEpochSecond());
        if (compare != 0) {
            return compare;
        }
        int compare2 = Long.compare((long) toInstant().getNano(), (long) fileTime.toInstant().getNano());
        if (compare2 != 0) {
            return compare2;
        }
        if (epochSecond != 31556889864403199L && epochSecond != -31557014167219200L) {
            return 0;
        }
        long days = toDays();
        long days2 = fileTime.toDays();
        if (days == days2) {
            return Long.compare(toExcessNanos(days), fileTime.toExcessNanos(days2));
        }
        return Long.compare(days, days2);
    }

    private StringBuilder append(StringBuilder sb, int i, int i2) {
        while (i > 0) {
            sb.append((char) ((i2 / i) + 48));
            i2 %= i;
            i /= 10;
        }
        return sb;
    }

    public String toString() {
        long j;
        long j2;
        int i;
        LocalDateTime localDateTime;
        if (this.valueAsString == null) {
            int i2 = 0;
            if (this.instant != null || this.unit.compareTo((Enum) TimeUnit.SECONDS) < 0) {
                j = toInstant().getEpochSecond();
                i2 = toInstant().getNano();
            } else {
                j = this.unit.toSeconds(this.value);
            }
            if (j >= -62167219200L) {
                long j3 = (j - 315569520000L) + 62167219200L;
                j2 = Math.floorDiv(j3, 315569520000L) + 1;
                localDateTime = LocalDateTime.ofEpochSecond(Math.floorMod(j3, 315569520000L) - 62167219200L, i2, ZoneOffset.UTC);
                i = localDateTime.getYear();
            } else {
                long j4 = j + 62167219200L;
                j2 = j4 / 315569520000L;
                localDateTime = LocalDateTime.ofEpochSecond((j4 % 315569520000L) - 62167219200L, i2, ZoneOffset.UTC);
                i = localDateTime.getYear();
            }
            int i3 = i + (((int) j2) * 10000);
            if (i3 <= 0) {
                i3--;
            }
            int nano = localDateTime.getNano();
            StringBuilder sb = new StringBuilder(64);
            sb.append(i3 < 0 ? "-" : "");
            int abs = Math.abs(i3);
            if (abs < 10000) {
                append(sb, 1000, Math.abs(abs));
            } else {
                sb.append(String.valueOf(abs));
            }
            sb.append('-');
            append(sb, 10, localDateTime.getMonthValue());
            sb.append('-');
            append(sb, 10, localDateTime.getDayOfMonth());
            sb.append('T');
            append(sb, 10, localDateTime.getHour());
            sb.append(':');
            append(sb, 10, localDateTime.getMinute());
            sb.append(':');
            append(sb, 10, localDateTime.getSecond());
            if (nano != 0) {
                sb.append('.');
                int i4 = 100000000;
                while (nano % 10 == 0) {
                    nano /= 10;
                    i4 /= 10;
                }
                append(sb, i4, nano);
            }
            sb.append('Z');
            this.valueAsString = sb.toString();
        }
        return this.valueAsString;
    }
}
