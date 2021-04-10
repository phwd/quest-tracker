package java.time.temporal;

import java.time.DayOfWeek;

public final class TemporalAdjusters {
    public static TemporalAdjuster nextOrSame(DayOfWeek dayOfWeek) {
        return new TemporalAdjuster(dayOfWeek.getValue()) {
            /* class java.time.temporal.$$Lambda$TemporalAdjusters$A9OZwfMlHD1vy7nYt5NssACu7Q */
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.time.temporal.TemporalAdjuster
            public final Temporal adjustInto(Temporal temporal) {
                return TemporalAdjusters.lambda$nextOrSame$10(this.f$0, temporal);
            }
        };
    }

    static /* synthetic */ Temporal lambda$nextOrSame$10(int i, Temporal temporal) {
        int i2 = temporal.get(ChronoField.DAY_OF_WEEK);
        if (i2 == i) {
            return temporal;
        }
        int i3 = i2 - i;
        return temporal.plus((long) (i3 >= 0 ? 7 - i3 : -i3), ChronoUnit.DAYS);
    }

    public static TemporalAdjuster previousOrSame(DayOfWeek dayOfWeek) {
        return new TemporalAdjuster(dayOfWeek.getValue()) {
            /* class java.time.temporal.$$Lambda$TemporalAdjusters$TKkfUVRu_GUECdXqtmzzXrayVY8 */
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.time.temporal.TemporalAdjuster
            public final Temporal adjustInto(Temporal temporal) {
                return TemporalAdjusters.lambda$previousOrSame$12(this.f$0, temporal);
            }
        };
    }

    static /* synthetic */ Temporal lambda$previousOrSame$12(int i, Temporal temporal) {
        int i2 = temporal.get(ChronoField.DAY_OF_WEEK);
        if (i2 == i) {
            return temporal;
        }
        int i3 = i - i2;
        return temporal.minus((long) (i3 >= 0 ? 7 - i3 : -i3), ChronoUnit.DAYS);
    }
}
