package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import com.oculus.fitnesstracker.database.FitnessTrackerUserContract;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class FitnessDataModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "FitnessDataModule";

    public enum CaloriesUnitType {
        kcal,
        kj
    }

    public enum HeightUnitType {
        cm,
        ft
    }

    public enum WeightUnitType {
        kg,
        lb
    }

    /* access modifiers changed from: protected */
    public abstract void addSimulatedDailyDataImpl(double d, double d2, String str);

    /* access modifiers changed from: protected */
    public abstract void addSimulatedLongtermDataImpl(AddSimulatedLongtermDataApps addSimulatedLongtermDataApps, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void getActiveYearsImpl(Resolver<List<Double>> resolver);

    /* access modifiers changed from: protected */
    public abstract void insertOrUpdateUserInfoImpl(UserInfo userInfo);

    /* access modifiers changed from: protected */
    public abstract void insertUserGoalsImpl(UserGoals userGoals);

    /* access modifiers changed from: protected */
    public abstract void insertUserMetricsImpl(UserMetrics userMetrics);

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void readAllTimeSummaryDataImpl(double d, double d2, ReadAllTimeSummaryDataPackageNames readAllTimeSummaryDataPackageNames, double d3, Resolver<AllTimeSummaryData> resolver);

    /* access modifiers changed from: protected */
    public abstract void readDailyFitnessDataImpl(double d, double d2, Resolver<List<TenMinsFitnessData>> resolver);

    /* access modifiers changed from: protected */
    public abstract void readDailyProgressImpl(double d, double d2, Resolver<List<ProgressData>> resolver);

    /* access modifiers changed from: protected */
    public abstract void readDailySummaryDataImpl(double d, ReadDailySummaryDataPackageNames readDailySummaryDataPackageNames, Resolver<DailySummaryData> resolver);

    /* access modifiers changed from: protected */
    public abstract void readMonthlyFitnessDataImpl(double d, double d2, double d3, Resolver<List<WeeklyFitnessData>> resolver);

    /* access modifiers changed from: protected */
    public abstract void readMonthlySummaryDataImpl(double d, double d2, ReadMonthlySummaryDataPackageNames readMonthlySummaryDataPackageNames, double d3, Resolver<List<WeeklySummaryData>> resolver);

    /* access modifiers changed from: protected */
    public abstract void readUserGoalsImpl(double d, Resolver<UserGoals> resolver);

    /* access modifiers changed from: protected */
    public abstract void readUserInfoImpl(Resolver<UserInfo> resolver);

    /* access modifiers changed from: protected */
    public abstract void readUserMetricsImpl(double d, Resolver<UserMetrics> resolver);

    /* access modifiers changed from: protected */
    public abstract void readWeeklyFitnessDataImpl(double d, double d2, Resolver<List<DailyFitnessData>> resolver);

    /* access modifiers changed from: protected */
    public abstract void readWeeklySummaryDataImpl(double d, double d2, ReadWeeklySummaryDataPackageNames readWeeklySummaryDataPackageNames, Resolver<List<DailySummaryData>> resolver);

    /* access modifiers changed from: protected */
    public abstract void readYearlyFitnessDataImpl(double d, double d2, Resolver<List<MonthlyFitnessData>> resolver);

    /* access modifiers changed from: protected */
    public abstract void readYearlySummaryDataImpl(double d, double d2, ReadYearlySummaryDataPackageNames readYearlySummaryDataPackageNames, double d3, Resolver<List<MonthlySummaryData>> resolver);

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public abstract void wipeAllDataImpl(Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("addSimulatedDailyData", "dds"));
        arrayList.add(new Pair("addSimulatedLongtermData", "+jii"));
        arrayList.add(new Pair("getActiveYears", "+ii"));
        arrayList.add(new Pair("insertOrUpdateUserInfo", "j"));
        arrayList.add(new Pair("insertUserGoals", "j"));
        arrayList.add(new Pair("insertUserMetrics", "j"));
        arrayList.add(new Pair("readAllTimeSummaryData", "+ddjdii"));
        arrayList.add(new Pair("readDailyFitnessData", "+ddii"));
        arrayList.add(new Pair("readDailyProgress", "+ddii"));
        arrayList.add(new Pair("readDailySummaryData", "+djii"));
        arrayList.add(new Pair("readMonthlyFitnessData", "+dddii"));
        arrayList.add(new Pair("readMonthlySummaryData", "+ddjdii"));
        arrayList.add(new Pair("readUserGoals", "+dii"));
        arrayList.add(new Pair("readUserInfo", "+ii"));
        arrayList.add(new Pair("readUserMetrics", "+dii"));
        arrayList.add(new Pair("readWeeklyFitnessData", "+ddii"));
        arrayList.add(new Pair("readWeeklySummaryData", "+ddjii"));
        arrayList.add(new Pair("readYearlyFitnessData", "+ddii"));
        arrayList.add(new Pair("readYearlySummaryData", "+ddjdii"));
        arrayList.add(new Pair("wipeAllData", "+ii"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final void addSimulatedDailyData(double d, double d2, String str) {
        addSimulatedDailyDataImpl(d, d2, str);
    }

    /* access modifiers changed from: protected */
    public final void addSimulatedLongtermData(JSONObject jSONObject, int i, int i2) {
        addSimulatedLongtermDataImpl(AddSimulatedLongtermDataApps.makeFromJSONObject(jSONObject), ResolverFactory.createVoidResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getActiveYears(int i, int i2) {
        getActiveYearsImpl(ResolverFactory.createDoubleListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void insertOrUpdateUserInfo(JSONObject jSONObject) {
        insertOrUpdateUserInfoImpl(UserInfo.makeFromJSONObject(jSONObject));
    }

    /* access modifiers changed from: protected */
    public final void insertUserGoals(JSONObject jSONObject) {
        insertUserGoalsImpl(UserGoals.makeFromJSONObject(jSONObject));
    }

    /* access modifiers changed from: protected */
    public final void insertUserMetrics(JSONObject jSONObject) {
        insertUserMetricsImpl(UserMetrics.makeFromJSONObject(jSONObject));
    }

    /* access modifiers changed from: protected */
    public final void readAllTimeSummaryData(double d, double d2, JSONObject jSONObject, double d3, int i, int i2) {
        readAllTimeSummaryDataImpl(d, d2, ReadAllTimeSummaryDataPackageNames.makeFromJSONObject(jSONObject), d3, ResolverFactory.createParcelResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readDailyFitnessData(double d, double d2, int i, int i2) {
        readDailyFitnessDataImpl(d, d2, ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readDailyProgress(double d, double d2, int i, int i2) {
        readDailyProgressImpl(d, d2, ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readDailySummaryData(double d, JSONObject jSONObject, int i, int i2) {
        readDailySummaryDataImpl(d, ReadDailySummaryDataPackageNames.makeFromJSONObject(jSONObject), ResolverFactory.createParcelResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readMonthlyFitnessData(double d, double d2, double d3, int i, int i2) {
        readMonthlyFitnessDataImpl(d, d2, d3, ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readMonthlySummaryData(double d, double d2, JSONObject jSONObject, double d3, int i, int i2) {
        readMonthlySummaryDataImpl(d, d2, ReadMonthlySummaryDataPackageNames.makeFromJSONObject(jSONObject), d3, ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readUserGoals(double d, int i, int i2) {
        readUserGoalsImpl(d, ResolverFactory.createParcelResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readUserInfo(int i, int i2) {
        readUserInfoImpl(ResolverFactory.createParcelResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readUserMetrics(double d, int i, int i2) {
        readUserMetricsImpl(d, ResolverFactory.createParcelResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readWeeklyFitnessData(double d, double d2, int i, int i2) {
        readWeeklyFitnessDataImpl(d, d2, ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readWeeklySummaryData(double d, double d2, JSONObject jSONObject, int i, int i2) {
        readWeeklySummaryDataImpl(d, d2, ReadWeeklySummaryDataPackageNames.makeFromJSONObject(jSONObject), ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readYearlyFitnessData(double d, double d2, int i, int i2) {
        readYearlyFitnessDataImpl(d, d2, ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void readYearlySummaryData(double d, double d2, JSONObject jSONObject, double d3, int i, int i2) {
        readYearlySummaryDataImpl(d, d2, ReadYearlySummaryDataPackageNames.makeFromJSONObject(jSONObject), d3, ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void wipeAllData(int i, int i2) {
        wipeAllDataImpl(ResolverFactory.createVoidResolver(this, i, i2));
    }

    public static class AddSimulatedLongtermDataApps extends NativeModuleParcel {
        public List<String> appList;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("appList", NativeModuleParcel.convertStringListToJSONArray(this.appList));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.appList = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("appList"));
        }

        public static final AddSimulatedLongtermDataApps makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AddSimulatedLongtermDataApps addSimulatedLongtermDataApps = new AddSimulatedLongtermDataApps();
            addSimulatedLongtermDataApps.setFromJSONObject(jSONObject);
            return addSimulatedLongtermDataApps;
        }
    }

    public static class UserInfo extends NativeModuleParcel {
        public CaloriesUnitType caloriesUnit;
        public Double creationTime;
        public String dateOfBirth;
        public HeightUnitType heightUnit;
        public String sex;
        public WeightUnitType weightUnit;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerUserContract.User.CALORIES_UNIT, this.caloriesUnit == null ? JSONObject.NULL : this.caloriesUnit.name());
                jSONObject.put("creationTime", this.creationTime);
                jSONObject.put(FitnessTrackerUserContract.User.DATE_OF_BIRTH, this.dateOfBirth);
                jSONObject.put(FitnessTrackerUserContract.User.HEIGHT_UNIT, this.heightUnit == null ? JSONObject.NULL : this.heightUnit.name());
                jSONObject.put(FitnessTrackerUserContract.User.SEX, this.sex);
                jSONObject.put(FitnessTrackerUserContract.User.WEIGHT_UNIT, this.weightUnit == null ? JSONObject.NULL : this.weightUnit.name());
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            WeightUnitType weightUnitType = null;
            this.caloriesUnit = jSONObject.isNull(FitnessTrackerUserContract.User.CALORIES_UNIT) ? null : CaloriesUnitType.valueOf(jSONObject.optString(FitnessTrackerUserContract.User.CALORIES_UNIT));
            this.creationTime = jSONObject.isNull("creationTime") ? null : Double.valueOf(jSONObject.optDouble("creationTime", 0.0d));
            this.dateOfBirth = jSONObject.isNull(FitnessTrackerUserContract.User.DATE_OF_BIRTH) ? null : jSONObject.optString(FitnessTrackerUserContract.User.DATE_OF_BIRTH);
            this.heightUnit = jSONObject.isNull(FitnessTrackerUserContract.User.HEIGHT_UNIT) ? null : HeightUnitType.valueOf(jSONObject.optString(FitnessTrackerUserContract.User.HEIGHT_UNIT));
            this.sex = jSONObject.isNull(FitnessTrackerUserContract.User.SEX) ? null : jSONObject.optString(FitnessTrackerUserContract.User.SEX);
            if (!jSONObject.isNull(FitnessTrackerUserContract.User.WEIGHT_UNIT)) {
                weightUnitType = WeightUnitType.valueOf(jSONObject.optString(FitnessTrackerUserContract.User.WEIGHT_UNIT));
            }
            this.weightUnit = weightUnitType;
        }

        public static final UserInfo makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setFromJSONObject(jSONObject);
            return userInfo;
        }
    }

    public static class UserGoals extends NativeModuleParcel {
        public Double activeTimeGoal;
        public Double caloriesGoal;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, this.activeTimeGoal);
                jSONObject.put(FitnessTrackerUserContract.Goals.CALORIES_GOAL, this.caloriesGoal);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            Double d = null;
            this.activeTimeGoal = jSONObject.isNull(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL) ? null : Double.valueOf(jSONObject.optDouble(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, 0.0d));
            if (!jSONObject.isNull(FitnessTrackerUserContract.Goals.CALORIES_GOAL)) {
                d = Double.valueOf(jSONObject.optDouble(FitnessTrackerUserContract.Goals.CALORIES_GOAL, 0.0d));
            }
            this.caloriesGoal = d;
        }

        public static final UserGoals makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            UserGoals userGoals = new UserGoals();
            userGoals.setFromJSONObject(jSONObject);
            return userGoals;
        }
    }

    public static class UserMetrics extends NativeModuleParcel {
        public Double height;
        public Double weight;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerUserContract.Metrics.HEIGHT, this.height);
                jSONObject.put(FitnessTrackerUserContract.Metrics.WEIGHT, this.weight);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            Double d = null;
            this.height = jSONObject.isNull(FitnessTrackerUserContract.Metrics.HEIGHT) ? null : Double.valueOf(jSONObject.optDouble(FitnessTrackerUserContract.Metrics.HEIGHT, 0.0d));
            if (!jSONObject.isNull(FitnessTrackerUserContract.Metrics.WEIGHT)) {
                d = Double.valueOf(jSONObject.optDouble(FitnessTrackerUserContract.Metrics.WEIGHT, 0.0d));
            }
            this.weight = d;
        }

        public static final UserMetrics makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            UserMetrics userMetrics = new UserMetrics();
            userMetrics.setFromJSONObject(jSONObject);
            return userMetrics;
        }
    }

    public static class ReadAllTimeSummaryDataPackageNames extends NativeModuleParcel {
        public List<String> packageNameList;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packageNameList", NativeModuleParcel.convertStringListToJSONArray(this.packageNameList));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.packageNameList = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("packageNameList"));
        }

        public static final ReadAllTimeSummaryDataPackageNames makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ReadAllTimeSummaryDataPackageNames readAllTimeSummaryDataPackageNames = new ReadAllTimeSummaryDataPackageNames();
            readAllTimeSummaryDataPackageNames.setFromJSONObject(jSONObject);
            return readAllTimeSummaryDataPackageNames;
        }
    }

    public static class AllTimeSummaryData extends NativeModuleParcel {
        public double activeDays;
        public double activeMonths;
        public double activeTime;
        public double activeWeeks;
        public double activeYears;
        public double calories;
        public Double goalsHit;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("activeDays", this.activeDays);
                jSONObject.put("activeMonths", this.activeMonths);
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, this.activeTime);
                jSONObject.put("activeWeeks", this.activeWeeks);
                jSONObject.put("activeYears", this.activeYears);
                jSONObject.put("calories", this.calories);
                jSONObject.put("goalsHit", this.goalsHit);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.activeDays = jSONObject.optDouble("activeDays", 0.0d);
            this.activeMonths = jSONObject.optDouble("activeMonths", 0.0d);
            this.activeTime = jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME, 0.0d);
            this.activeWeeks = jSONObject.optDouble("activeWeeks", 0.0d);
            this.activeYears = jSONObject.optDouble("activeYears", 0.0d);
            this.calories = jSONObject.optDouble("calories", 0.0d);
            this.goalsHit = jSONObject.isNull("goalsHit") ? null : Double.valueOf(jSONObject.optDouble("goalsHit", 0.0d));
        }

        public static final AllTimeSummaryData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AllTimeSummaryData allTimeSummaryData = new AllTimeSummaryData();
            allTimeSummaryData.setFromJSONObject(jSONObject);
            return allTimeSummaryData;
        }
    }

    public static class TenMinsFitnessData extends NativeModuleParcel {
        public double activeTime;
        public double activeTimeProgress;
        public String activity;
        public double calories;
        public double caloriesProgress;
        public String packageName;
        public double startOfTenMins;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, this.activeTime);
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, this.activeTimeProgress);
                jSONObject.put(FitnessTrackerMoveContract.Session.ACTIVITY, this.activity);
                jSONObject.put("calories", this.calories);
                jSONObject.put(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, this.caloriesProgress);
                jSONObject.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, this.packageName);
                jSONObject.put("startOfTenMins", this.startOfTenMins);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.activeTime = jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME, 0.0d);
            this.activeTimeProgress = jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, 0.0d);
            this.activity = jSONObject.optString(FitnessTrackerMoveContract.Session.ACTIVITY);
            this.calories = jSONObject.optDouble("calories", 0.0d);
            this.caloriesProgress = jSONObject.optDouble(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, 0.0d);
            this.packageName = jSONObject.optString(FitnessTrackerMoveContract.Session.PACKAGE_NAME);
            this.startOfTenMins = jSONObject.optDouble("startOfTenMins", 0.0d);
        }

        public static final TenMinsFitnessData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            TenMinsFitnessData tenMinsFitnessData = new TenMinsFitnessData();
            tenMinsFitnessData.setFromJSONObject(jSONObject);
            return tenMinsFitnessData;
        }
    }

    public static class ProgressData extends NativeModuleParcel {
        public Double activeTimeProgress;
        public Double caloriesProgress;
        public String date;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, this.activeTimeProgress);
                jSONObject.put(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, this.caloriesProgress);
                jSONObject.put(FitnessTrackerMoveContract.Days.DATE, this.date);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            Double d = null;
            this.activeTimeProgress = jSONObject.isNull(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS) ? null : Double.valueOf(jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, 0.0d));
            if (!jSONObject.isNull(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS)) {
                d = Double.valueOf(jSONObject.optDouble(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, 0.0d));
            }
            this.caloriesProgress = d;
            this.date = jSONObject.optString(FitnessTrackerMoveContract.Days.DATE);
        }

        public static final ProgressData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ProgressData progressData = new ProgressData();
            progressData.setFromJSONObject(jSONObject);
            return progressData;
        }
    }

    public static class ReadDailySummaryDataPackageNames extends NativeModuleParcel {
        public List<String> packageNameList;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packageNameList", NativeModuleParcel.convertStringListToJSONArray(this.packageNameList));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.packageNameList = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("packageNameList"));
        }

        public static final ReadDailySummaryDataPackageNames makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ReadDailySummaryDataPackageNames readDailySummaryDataPackageNames = new ReadDailySummaryDataPackageNames();
            readDailySummaryDataPackageNames.setFromJSONObject(jSONObject);
            return readDailySummaryDataPackageNames;
        }
    }

    public static class DailySummaryData extends NativeModuleParcel {
        public double activeTime;
        public Double activeTimeGoal;
        public Double activeTimeProgress;
        public double calories;
        public Double caloriesGoal;
        public Double caloriesProgress;
        public double startOfDay;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, this.activeTime);
                if (this.activeTimeGoal != null) {
                    jSONObject.put(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, this.activeTimeGoal);
                }
                if (this.activeTimeProgress != null) {
                    jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, this.activeTimeProgress);
                }
                jSONObject.put("calories", this.calories);
                if (this.caloriesGoal != null) {
                    jSONObject.put(FitnessTrackerUserContract.Goals.CALORIES_GOAL, this.caloriesGoal);
                }
                if (this.caloriesProgress != null) {
                    jSONObject.put(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, this.caloriesProgress);
                }
                jSONObject.put("startOfDay", this.startOfDay);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.activeTime = jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME, 0.0d);
            Double d = null;
            this.activeTimeGoal = jSONObject.isNull(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL) ? null : Double.valueOf(jSONObject.optDouble(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, 0.0d));
            this.activeTimeProgress = jSONObject.isNull(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS) ? null : Double.valueOf(jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, 0.0d));
            this.calories = jSONObject.optDouble("calories", 0.0d);
            this.caloriesGoal = jSONObject.isNull(FitnessTrackerUserContract.Goals.CALORIES_GOAL) ? null : Double.valueOf(jSONObject.optDouble(FitnessTrackerUserContract.Goals.CALORIES_GOAL, 0.0d));
            if (!jSONObject.isNull(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS)) {
                d = Double.valueOf(jSONObject.optDouble(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, 0.0d));
            }
            this.caloriesProgress = d;
            this.startOfDay = jSONObject.optDouble("startOfDay", 0.0d);
        }

        public static final DailySummaryData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DailySummaryData dailySummaryData = new DailySummaryData();
            dailySummaryData.setFromJSONObject(jSONObject);
            return dailySummaryData;
        }
    }

    public static class WeeklyFitnessData extends NativeModuleParcel {
        public double activeTime;
        public String activity;
        public double calories;
        public String packageName;
        public String weekStart;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, this.activeTime);
                jSONObject.put(FitnessTrackerMoveContract.Session.ACTIVITY, this.activity);
                jSONObject.put("calories", this.calories);
                jSONObject.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, this.packageName);
                jSONObject.put("weekStart", this.weekStart);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.activeTime = jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME, 0.0d);
            this.activity = jSONObject.optString(FitnessTrackerMoveContract.Session.ACTIVITY);
            this.calories = jSONObject.optDouble("calories", 0.0d);
            this.packageName = jSONObject.optString(FitnessTrackerMoveContract.Session.PACKAGE_NAME);
            this.weekStart = jSONObject.optString("weekStart");
        }

        public static final WeeklyFitnessData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            WeeklyFitnessData weeklyFitnessData = new WeeklyFitnessData();
            weeklyFitnessData.setFromJSONObject(jSONObject);
            return weeklyFitnessData;
        }
    }

    public static class ReadMonthlySummaryDataPackageNames extends NativeModuleParcel {
        public List<String> packageNameList;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packageNameList", NativeModuleParcel.convertStringListToJSONArray(this.packageNameList));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.packageNameList = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("packageNameList"));
        }

        public static final ReadMonthlySummaryDataPackageNames makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ReadMonthlySummaryDataPackageNames readMonthlySummaryDataPackageNames = new ReadMonthlySummaryDataPackageNames();
            readMonthlySummaryDataPackageNames.setFromJSONObject(jSONObject);
            return readMonthlySummaryDataPackageNames;
        }
    }

    public static class WeeklySummaryData extends NativeModuleParcel {
        public double activeDays;
        public double activeTime;
        public double calories;
        public Double goalsHit;
        public String weekStart;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("activeDays", this.activeDays);
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, this.activeTime);
                jSONObject.put("calories", this.calories);
                jSONObject.put("goalsHit", this.goalsHit);
                jSONObject.put("weekStart", this.weekStart);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.activeDays = jSONObject.optDouble("activeDays", 0.0d);
            this.activeTime = jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME, 0.0d);
            this.calories = jSONObject.optDouble("calories", 0.0d);
            this.goalsHit = jSONObject.isNull("goalsHit") ? null : Double.valueOf(jSONObject.optDouble("goalsHit", 0.0d));
            this.weekStart = jSONObject.optString("weekStart");
        }

        public static final WeeklySummaryData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            WeeklySummaryData weeklySummaryData = new WeeklySummaryData();
            weeklySummaryData.setFromJSONObject(jSONObject);
            return weeklySummaryData;
        }
    }

    public static class DailyFitnessData extends NativeModuleParcel {
        public double activeTime;
        public String activity;
        public double calories;
        public String date;
        public String packageName;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, this.activeTime);
                jSONObject.put(FitnessTrackerMoveContract.Session.ACTIVITY, this.activity);
                jSONObject.put("calories", this.calories);
                jSONObject.put(FitnessTrackerMoveContract.Days.DATE, this.date);
                jSONObject.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, this.packageName);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.activeTime = jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME, 0.0d);
            this.activity = jSONObject.optString(FitnessTrackerMoveContract.Session.ACTIVITY);
            this.calories = jSONObject.optDouble("calories", 0.0d);
            this.date = jSONObject.optString(FitnessTrackerMoveContract.Days.DATE);
            this.packageName = jSONObject.optString(FitnessTrackerMoveContract.Session.PACKAGE_NAME);
        }

        public static final DailyFitnessData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DailyFitnessData dailyFitnessData = new DailyFitnessData();
            dailyFitnessData.setFromJSONObject(jSONObject);
            return dailyFitnessData;
        }
    }

    public static class ReadWeeklySummaryDataPackageNames extends NativeModuleParcel {
        public List<String> packageNameList;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packageNameList", NativeModuleParcel.convertStringListToJSONArray(this.packageNameList));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.packageNameList = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("packageNameList"));
        }

        public static final ReadWeeklySummaryDataPackageNames makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ReadWeeklySummaryDataPackageNames readWeeklySummaryDataPackageNames = new ReadWeeklySummaryDataPackageNames();
            readWeeklySummaryDataPackageNames.setFromJSONObject(jSONObject);
            return readWeeklySummaryDataPackageNames;
        }
    }

    public static class MonthlyFitnessData extends NativeModuleParcel {
        public double activeTime;
        public String activity;
        public double calories;
        public double month;
        public String packageName;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, this.activeTime);
                jSONObject.put(FitnessTrackerMoveContract.Session.ACTIVITY, this.activity);
                jSONObject.put("calories", this.calories);
                jSONObject.put("month", this.month);
                jSONObject.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, this.packageName);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.activeTime = jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME, 0.0d);
            this.activity = jSONObject.optString(FitnessTrackerMoveContract.Session.ACTIVITY);
            this.calories = jSONObject.optDouble("calories", 0.0d);
            this.month = jSONObject.optDouble("month", 0.0d);
            this.packageName = jSONObject.optString(FitnessTrackerMoveContract.Session.PACKAGE_NAME);
        }

        public static final MonthlyFitnessData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            MonthlyFitnessData monthlyFitnessData = new MonthlyFitnessData();
            monthlyFitnessData.setFromJSONObject(jSONObject);
            return monthlyFitnessData;
        }
    }

    public static class ReadYearlySummaryDataPackageNames extends NativeModuleParcel {
        public List<String> packageNameList;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packageNameList", NativeModuleParcel.convertStringListToJSONArray(this.packageNameList));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.packageNameList = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("packageNameList"));
        }

        public static final ReadYearlySummaryDataPackageNames makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ReadYearlySummaryDataPackageNames readYearlySummaryDataPackageNames = new ReadYearlySummaryDataPackageNames();
            readYearlySummaryDataPackageNames.setFromJSONObject(jSONObject);
            return readYearlySummaryDataPackageNames;
        }
    }

    public static class MonthlySummaryData extends NativeModuleParcel {
        public double activeDays;
        public double activeTime;
        public double calories;
        public Double goalsHit;
        public double month;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("activeDays", this.activeDays);
                jSONObject.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, this.activeTime);
                jSONObject.put("calories", this.calories);
                jSONObject.put("goalsHit", this.goalsHit);
                jSONObject.put("month", this.month);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.activeDays = jSONObject.optDouble("activeDays", 0.0d);
            this.activeTime = jSONObject.optDouble(FitnessTrackerMoveContract.Days.ACTIVE_TIME, 0.0d);
            this.calories = jSONObject.optDouble("calories", 0.0d);
            this.goalsHit = jSONObject.isNull("goalsHit") ? null : Double.valueOf(jSONObject.optDouble("goalsHit", 0.0d));
            this.month = jSONObject.optDouble("month", 0.0d);
        }

        public static final MonthlySummaryData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            MonthlySummaryData monthlySummaryData = new MonthlySummaryData();
            monthlySummaryData.setFromJSONObject(jSONObject);
            return monthlySummaryData;
        }
    }
}
