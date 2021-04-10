package java.util.concurrent;

import libcore.icu.RelativeDateTimeFormatter;

public enum TimeUnit {
    NANOSECONDS {
        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long d) {
            return d;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long d) {
            return d / 1000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long d) {
            return d / TimeUnit.C2;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long d) {
            return d / TimeUnit.C3;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long d) {
            return d / TimeUnit.C4;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long d) {
            return d / TimeUnit.C5;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long d) {
            return d / TimeUnit.C6;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long d, TimeUnit u) {
            return u.toNanos(d);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.TimeUnit
        public int excessNanos(long d, long m) {
            return (int) (d - (TimeUnit.C2 * m));
        }
    },
    MICROSECONDS {
        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long d) {
            return x(d, 1000, 9223372036854775L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long d) {
            return d;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long d) {
            return d / 1000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long d) {
            return d / TimeUnit.C2;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long d) {
            return d / 60000000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long d) {
            return d / 3600000000L;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long d) {
            return d / 86400000000L;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long d, TimeUnit u) {
            return u.toMicros(d);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.TimeUnit
        public int excessNanos(long d, long m) {
            return (int) ((1000 * d) - (TimeUnit.C2 * m));
        }
    },
    MILLISECONDS {
        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long d) {
            return x(d, TimeUnit.C2, 9223372036854L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long d) {
            return x(d, 1000, 9223372036854775L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long d) {
            return d;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long d) {
            return d / 1000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long d) {
            return d / RelativeDateTimeFormatter.MINUTE_IN_MILLIS;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long d) {
            return d / RelativeDateTimeFormatter.HOUR_IN_MILLIS;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long d) {
            return d / 86400000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long d, TimeUnit u) {
            return u.toMillis(d);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.TimeUnit
        public int excessNanos(long d, long m) {
            return 0;
        }
    },
    SECONDS {
        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long d) {
            return x(d, TimeUnit.C3, 9223372036L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long d) {
            return x(d, TimeUnit.C2, 9223372036854L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long d) {
            return x(d, 1000, 9223372036854775L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long d) {
            return d;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long d) {
            return d / 60;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long d) {
            return d / 3600;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long d) {
            return d / 86400;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long d, TimeUnit u) {
            return u.toSeconds(d);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.TimeUnit
        public int excessNanos(long d, long m) {
            return 0;
        }
    },
    MINUTES {
        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long d) {
            return x(d, TimeUnit.C4, 153722867);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long d) {
            return x(d, 60000000, 153722867280L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long d) {
            return x(d, RelativeDateTimeFormatter.MINUTE_IN_MILLIS, 153722867280912L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long d) {
            return x(d, 60, 153722867280912930L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long d) {
            return d;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long d) {
            return d / 60;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long d) {
            return d / 1440;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long d, TimeUnit u) {
            return u.toMinutes(d);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.TimeUnit
        public int excessNanos(long d, long m) {
            return 0;
        }
    },
    HOURS {
        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long d) {
            return x(d, TimeUnit.C5, 2562047);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long d) {
            return x(d, 3600000000L, 2562047788L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long d) {
            return x(d, RelativeDateTimeFormatter.HOUR_IN_MILLIS, 2562047788015L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long d) {
            return x(d, 3600, 2562047788015215L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long d) {
            return x(d, 60, 153722867280912930L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long d) {
            return d;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long d) {
            return d / 24;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long d, TimeUnit u) {
            return u.toHours(d);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.TimeUnit
        public int excessNanos(long d, long m) {
            return 0;
        }
    },
    DAYS {
        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long d) {
            return x(d, TimeUnit.C6, 106751);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long d) {
            return x(d, 86400000000L, 106751991);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long d) {
            return x(d, 86400000, 106751991167L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long d) {
            return x(d, 86400, 106751991167300L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long d) {
            return x(d, 1440, 6405119470038038L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long d) {
            return x(d, 24, 384307168202282325L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long d) {
            return d;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long d, TimeUnit u) {
            return u.toDays(d);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.TimeUnit
        public int excessNanos(long d, long m) {
            return 0;
        }
    };
    
    static final long C0 = 1;
    static final long C1 = 1000;
    static final long C2 = 1000000;
    static final long C3 = 1000000000;
    static final long C4 = 60000000000L;
    static final long C5 = 3600000000000L;
    static final long C6 = 86400000000000L;
    static final long MAX = Long.MAX_VALUE;

    /* access modifiers changed from: package-private */
    public abstract int excessNanos(long j, long j2);

    static long x(long d, long m, long over) {
        if (d > over) {
            return Long.MAX_VALUE;
        }
        if (d < (-over)) {
            return Long.MIN_VALUE;
        }
        return d * m;
    }

    public long convert(long sourceDuration, TimeUnit sourceUnit) {
        throw new AbstractMethodError();
    }

    public long toNanos(long duration) {
        throw new AbstractMethodError();
    }

    public long toMicros(long duration) {
        throw new AbstractMethodError();
    }

    public long toMillis(long duration) {
        throw new AbstractMethodError();
    }

    public long toSeconds(long duration) {
        throw new AbstractMethodError();
    }

    public long toMinutes(long duration) {
        throw new AbstractMethodError();
    }

    public long toHours(long duration) {
        throw new AbstractMethodError();
    }

    public long toDays(long duration) {
        throw new AbstractMethodError();
    }

    public void timedWait(Object obj, long timeout) throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            obj.wait(ms, excessNanos(timeout, ms));
        }
    }

    public void timedJoin(Thread thread, long timeout) throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            thread.join(ms, excessNanos(timeout, ms));
        }
    }

    public void sleep(long timeout) throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            Thread.sleep(ms, excessNanos(timeout, ms));
        }
    }
}
