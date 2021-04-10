package jp.co.omronsoft.iwnnime.ml.iwnn;

import java.util.Calendar;

public class IWnnSituationManager {
    private static final int CALENDAR_FIELD_NOTUSE = 0;
    private static final int SITUATION_MORNING_END_HOUR = 10;
    private static final int SITUATION_MORNING_END_MINUTE = 30;
    private static final int SITUATION_MORNING_START_HOUR = 5;
    private static final int SITUATION_MORNING_START_MINUTE = 0;
    private static final int SITUATION_NIGHT_END_HOUR = 17;
    private static final int SITUATION_NIGHT_END_MINUTE = 0;
    private static final int SITUATION_NIGHT_START_HOUR = 5;
    private static final int SITUATION_NIGHT_START_MINUTE = 30;
    private static final int SITUATION_NOON_END_HOUR = 17;
    private static final int SITUATION_NOON_END_MINUTE = 30;
    private static final int SITUATION_NOON_START_HOUR = 10;
    private static final int SITUATION_NOON_START_MINUTE = 0;
    private static final int STATE_FIELD_ADDRESS = 32;
    private static final int STATE_FIELD_EMAIL = 33;
    private static final int STATE_FIELD_HEAD = 2;
    private static final int STATE_FIELD_PERSON = 0;
    private static final int STATE_FIELD_SITUATION_ENABLE = 400;
    private static final int STATE_MONTH_APRIL = 15;
    private static final int STATE_MONTH_AUGUST = 19;
    private static final int STATE_MONTH_DECEMBER = 23;
    private static final int STATE_MONTH_FEBRUARY = 13;
    private static final int STATE_MONTH_JANUARY = 12;
    private static final int STATE_MONTH_JULY = 18;
    private static final int STATE_MONTH_JUNE = 17;
    private static final int STATE_MONTH_MARCH = 14;
    private static final int STATE_MONTH_MAY = 16;
    private static final int STATE_MONTH_NOVEMBER = 22;
    private static final int STATE_MONTH_OCTOBER = 21;
    private static final int STATE_MONTH_SEPTEMBER = 20;
    private static final int STATE_SITUATION_DISABLE = 0;
    private static final int STATE_TIME_MORNING = 5;
    private static final int STATE_TIME_NIGHT = 7;
    private static final int STATE_TIME_NOON = 6;
    private static final int STATE_TIME_SITUATION_ENABLE = 200;
    private Calendar mCompareCalendar = null;
    private IWnnCore mCore = null;
    private Calendar mCurrentCalendar = null;

    public IWnnSituationManager(IWnnCore iWnnCore) {
        this.mCore = iWnnCore;
        this.mCompareCalendar = Calendar.getInstance();
        this.mCompareCalendar.set(13, 0);
    }

    public boolean updateState() {
        this.mCurrentCalendar = Calendar.getInstance();
        resetCalendar(this.mCompareCalendar);
        boolean pullSituationState = this.mCore.pullSituationState();
        if (pullSituationState) {
            updateSituation();
        }
        return pullSituationState ? this.mCore.pushSituationState() : pullSituationState;
    }

    private void updateSituation() {
        int situationBiasStatus = this.mCore.getSituationBiasStatus();
        int i = situationBiasStatus & 1;
        int i2 = STATE_FIELD_SITUATION_ENABLE;
        int i3 = i != 0 ? STATE_FIELD_SITUATION_ENABLE : 0;
        int i4 = (situationBiasStatus & 4) != 0 ? STATE_FIELD_SITUATION_ENABLE : 0;
        if ((situationBiasStatus & 8) == 0) {
            i2 = 0;
        }
        this.mCore.setSituationBiasValue(5, 0);
        this.mCore.setSituationBiasValue(6, 0);
        this.mCore.setSituationBiasValue(7, 0);
        this.mCore.setSituationBiasValue(12, 0);
        this.mCore.setSituationBiasValue(13, 0);
        this.mCore.setSituationBiasValue(14, 0);
        this.mCore.setSituationBiasValue(15, 0);
        this.mCore.setSituationBiasValue(16, 0);
        this.mCore.setSituationBiasValue(17, 0);
        this.mCore.setSituationBiasValue(18, 0);
        this.mCore.setSituationBiasValue(19, 0);
        this.mCore.setSituationBiasValue(20, 0);
        this.mCore.setSituationBiasValue(21, 0);
        this.mCore.setSituationBiasValue(22, 0);
        this.mCore.setSituationBiasValue(23, 0);
        this.mCore.setSituationBiasValue(0, i3);
        this.mCore.setSituationBiasValue(2, 0);
        this.mCore.setSituationBiasValue(32, i4);
        this.mCore.setSituationBiasValue(33, i2);
        if (this.mCurrentCalendar != null) {
            if (isMorning()) {
                this.mCore.setSituationBiasValue(5, 200);
            }
            if (isNoon()) {
                this.mCore.setSituationBiasValue(6, 200);
            }
            if (isNight()) {
                this.mCore.setSituationBiasValue(7, 200);
            }
            this.mCore.setSituationBiasValue(this.mCurrentCalendar.get(2) + 12, 200);
        }
    }

    private boolean isMorning() {
        this.mCompareCalendar.set(11, 5);
        this.mCompareCalendar.set(12, 0);
        if (this.mCurrentCalendar.before(this.mCompareCalendar)) {
            return false;
        }
        this.mCompareCalendar.set(11, 10);
        this.mCompareCalendar.set(12, 30);
        if (this.mCurrentCalendar.before(this.mCompareCalendar)) {
            return true;
        }
        return false;
    }

    private boolean isNoon() {
        this.mCompareCalendar.set(11, 10);
        this.mCompareCalendar.set(12, 0);
        if (this.mCurrentCalendar.before(this.mCompareCalendar)) {
            return false;
        }
        this.mCompareCalendar.set(11, 17);
        this.mCompareCalendar.set(12, 30);
        if (this.mCurrentCalendar.before(this.mCompareCalendar)) {
            return true;
        }
        return false;
    }

    private boolean isNight() {
        this.mCompareCalendar.set(11, 5);
        this.mCompareCalendar.set(12, 30);
        if (this.mCurrentCalendar.before(this.mCompareCalendar)) {
            return true;
        }
        this.mCompareCalendar.set(11, 17);
        this.mCompareCalendar.set(12, 0);
        if (this.mCurrentCalendar.before(this.mCompareCalendar)) {
            return false;
        }
        return true;
    }

    private void resetCalendar(Calendar calendar) {
        calendar.set(1, this.mCurrentCalendar.get(1));
        calendar.set(2, this.mCurrentCalendar.get(2));
        calendar.set(5, this.mCurrentCalendar.get(5));
        calendar.setTimeZone(this.mCurrentCalendar.getTimeZone());
    }
}
