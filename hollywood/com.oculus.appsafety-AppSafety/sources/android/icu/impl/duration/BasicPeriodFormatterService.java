package android.icu.impl.duration;

import android.icu.impl.duration.impl.PeriodFormatterDataService;
import android.icu.impl.duration.impl.ResourceBasedPeriodFormatterDataService;
import java.util.Collection;

public class BasicPeriodFormatterService implements PeriodFormatterService {
    private static BasicPeriodFormatterService instance;
    private PeriodFormatterDataService ds;

    public static BasicPeriodFormatterService getInstance() {
        if (instance == null) {
            instance = new BasicPeriodFormatterService(ResourceBasedPeriodFormatterDataService.getInstance());
        }
        return instance;
    }

    public BasicPeriodFormatterService(PeriodFormatterDataService ds2) {
        this.ds = ds2;
    }

    @Override // android.icu.impl.duration.PeriodFormatterService
    public DurationFormatterFactory newDurationFormatterFactory() {
        return new BasicDurationFormatterFactory(this);
    }

    @Override // android.icu.impl.duration.PeriodFormatterService
    public PeriodFormatterFactory newPeriodFormatterFactory() {
        return new BasicPeriodFormatterFactory(this.ds);
    }

    @Override // android.icu.impl.duration.PeriodFormatterService
    public PeriodBuilderFactory newPeriodBuilderFactory() {
        return new BasicPeriodBuilderFactory(this.ds);
    }

    @Override // android.icu.impl.duration.PeriodFormatterService
    public Collection<String> getAvailableLocaleNames() {
        return this.ds.getAvailableLocales();
    }
}
