package android.icu.impl.duration;

import android.icu.impl.duration.impl.PeriodFormatterData;
import android.icu.impl.duration.impl.PeriodFormatterDataService;
import java.util.Locale;

public class BasicPeriodFormatterFactory implements PeriodFormatterFactory {
    private Customizations customizations = new Customizations();
    private boolean customizationsInUse;
    private PeriodFormatterData data;
    private final PeriodFormatterDataService ds;
    private String localeName = Locale.getDefault().toString();

    BasicPeriodFormatterFactory(PeriodFormatterDataService ds2) {
        this.ds = ds2;
    }

    public static BasicPeriodFormatterFactory getDefault() {
        return (BasicPeriodFormatterFactory) BasicPeriodFormatterService.getInstance().newPeriodFormatterFactory();
    }

    @Override // android.icu.impl.duration.PeriodFormatterFactory
    public PeriodFormatterFactory setLocale(String localeName2) {
        this.data = null;
        this.localeName = localeName2;
        return this;
    }

    @Override // android.icu.impl.duration.PeriodFormatterFactory
    public PeriodFormatterFactory setDisplayLimit(boolean display) {
        updateCustomizations().displayLimit = display;
        return this;
    }

    public boolean getDisplayLimit() {
        return this.customizations.displayLimit;
    }

    @Override // android.icu.impl.duration.PeriodFormatterFactory
    public PeriodFormatterFactory setDisplayPastFuture(boolean display) {
        updateCustomizations().displayDirection = display;
        return this;
    }

    public boolean getDisplayPastFuture() {
        return this.customizations.displayDirection;
    }

    @Override // android.icu.impl.duration.PeriodFormatterFactory
    public PeriodFormatterFactory setSeparatorVariant(int variant) {
        updateCustomizations().separatorVariant = (byte) variant;
        return this;
    }

    public int getSeparatorVariant() {
        return this.customizations.separatorVariant;
    }

    @Override // android.icu.impl.duration.PeriodFormatterFactory
    public PeriodFormatterFactory setUnitVariant(int variant) {
        updateCustomizations().unitVariant = (byte) variant;
        return this;
    }

    public int getUnitVariant() {
        return this.customizations.unitVariant;
    }

    @Override // android.icu.impl.duration.PeriodFormatterFactory
    public PeriodFormatterFactory setCountVariant(int variant) {
        updateCustomizations().countVariant = (byte) variant;
        return this;
    }

    public int getCountVariant() {
        return this.customizations.countVariant;
    }

    @Override // android.icu.impl.duration.PeriodFormatterFactory
    public PeriodFormatter getFormatter() {
        this.customizationsInUse = true;
        return new BasicPeriodFormatter(this, this.localeName, getData(), this.customizations);
    }

    private Customizations updateCustomizations() {
        if (this.customizationsInUse) {
            this.customizations = this.customizations.copy();
            this.customizationsInUse = false;
        }
        return this.customizations;
    }

    /* access modifiers changed from: package-private */
    public PeriodFormatterData getData() {
        if (this.data == null) {
            this.data = this.ds.get(this.localeName);
        }
        return this.data;
    }

    /* access modifiers changed from: package-private */
    public PeriodFormatterData getData(String locName) {
        return this.ds.get(locName);
    }

    /* access modifiers changed from: package-private */
    public static class Customizations {
        byte countVariant = 0;
        boolean displayDirection = true;
        boolean displayLimit = true;
        byte separatorVariant = 2;
        byte unitVariant = 0;

        Customizations() {
        }

        public Customizations copy() {
            Customizations result = new Customizations();
            result.displayLimit = this.displayLimit;
            result.displayDirection = this.displayDirection;
            result.separatorVariant = this.separatorVariant;
            result.unitVariant = this.unitVariant;
            result.countVariant = this.countVariant;
            return result;
        }
    }
}
