package java.util.concurrent;

public enum TimeUnit {
    NANOSECONDS {
        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long j) {
            return j;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long j) {
            return j / 1000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long j) {
            return j / 1000000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long j) {
            return j / 1000000000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long j) {
            return j / 60000000000L;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long j) {
            return j / 3600000000000L;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long j) {
            return j / 86400000000000L;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long j, TimeUnit timeUnit) {
            return timeUnit.toNanos(j);
        }
    },
    MICROSECONDS {
        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long j) {
            return j;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long j) {
            return TimeUnit.x(j, 1000, 9223372036854775L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long j) {
            return j / 1000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long j) {
            return j / 1000000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long j) {
            return j / 60000000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long j) {
            return j / 3600000000L;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long j) {
            return j / 86400000000L;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long j, TimeUnit timeUnit) {
            return timeUnit.toMicros(j);
        }
    },
    MILLISECONDS {
        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long j) {
            return j;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long j) {
            return TimeUnit.x(j, 1000000, 9223372036854L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long j) {
            return TimeUnit.x(j, 1000, 9223372036854775L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long j) {
            return j / 1000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long j) {
            return j / 60000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long j) {
            return j / 3600000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long j) {
            return j / 86400000;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long j, TimeUnit timeUnit) {
            return timeUnit.toMillis(j);
        }
    },
    SECONDS {
        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long j) {
            return j;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long j) {
            return TimeUnit.x(j, 1000000000, 9223372036L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long j) {
            return TimeUnit.x(j, 1000000, 9223372036854L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long j) {
            return TimeUnit.x(j, 1000, 9223372036854775L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long j) {
            return j / 60;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long j) {
            return j / 3600;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long j) {
            return j / 86400;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long j, TimeUnit timeUnit) {
            return timeUnit.toSeconds(j);
        }
    },
    MINUTES {
        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long j) {
            return j;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long j) {
            return TimeUnit.x(j, 60000000000L, 153722867);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long j) {
            return TimeUnit.x(j, 60000000, 153722867280L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long j) {
            return TimeUnit.x(j, 60000, 153722867280912L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long j) {
            return TimeUnit.x(j, 60, 153722867280912930L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long j) {
            return j / 60;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long j) {
            return j / 1440;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long j, TimeUnit timeUnit) {
            return timeUnit.toMinutes(j);
        }
    },
    HOURS {
        @Override // java.util.concurrent.TimeUnit
        public long toHours(long j) {
            return j;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long j) {
            return TimeUnit.x(j, 3600000000000L, 2562047);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long j) {
            return TimeUnit.x(j, 3600000000L, 2562047788L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long j) {
            return TimeUnit.x(j, 3600000, 2562047788015L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long j) {
            return TimeUnit.x(j, 3600, 2562047788015215L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long j) {
            return TimeUnit.x(j, 60, 153722867280912930L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toDays(long j) {
            return j / 24;
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long j, TimeUnit timeUnit) {
            return timeUnit.toHours(j);
        }
    },
    DAYS {
        @Override // java.util.concurrent.TimeUnit
        public long toDays(long j) {
            return j;
        }

        @Override // java.util.concurrent.TimeUnit
        public long toNanos(long j) {
            return TimeUnit.x(j, 86400000000000L, 106751);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMicros(long j) {
            return TimeUnit.x(j, 86400000000L, 106751991);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMillis(long j) {
            return TimeUnit.x(j, 86400000, 106751991167L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toSeconds(long j) {
            return TimeUnit.x(j, 86400, 106751991167300L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toMinutes(long j) {
            return TimeUnit.x(j, 1440, 6405119470038038L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long toHours(long j) {
            return TimeUnit.x(j, 24, 384307168202282325L);
        }

        @Override // java.util.concurrent.TimeUnit
        public long convert(long j, TimeUnit timeUnit) {
            return timeUnit.toDays(j);
        }
    };

    static long x(long j, long j2, long j3) {
        if (j > j3) {
            return Long.MAX_VALUE;
        }
        if (j < (-j3)) {
            return Long.MIN_VALUE;
        }
        return j * j2;
    }

    public long convert(long j, TimeUnit timeUnit) {
        throw new AbstractMethodError();
    }

    public long toNanos(long j) {
        throw new AbstractMethodError();
    }

    public long toMicros(long j) {
        throw new AbstractMethodError();
    }

    public long toMillis(long j) {
        throw new AbstractMethodError();
    }

    public long toSeconds(long j) {
        throw new AbstractMethodError();
    }

    public long toMinutes(long j) {
        throw new AbstractMethodError();
    }

    public long toHours(long j) {
        throw new AbstractMethodError();
    }

    public long toDays(long j) {
        throw new AbstractMethodError();
    }
}
