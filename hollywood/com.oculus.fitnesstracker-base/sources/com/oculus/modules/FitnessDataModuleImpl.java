package com.oculus.modules;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.oculus.fitnesstracker.FitnessDataManager;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import com.oculus.fitnesstracker.provider.FitnessDataContract;
import com.oculus.modules.codegen.FitnessDataModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.ThreadExecutor;
import java.util.List;
import java.util.concurrent.Callable;

public class FitnessDataModuleImpl extends FitnessDataModule {
    private static final String TAG = DeviceEnvironmentModuleImpl.class.getSimpleName();
    private static Context sContext;
    private static FitnessDataManager sFitnessManager;

    public FitnessDataModuleImpl(Context context) {
        sContext = context;
        sFitnessManager = FitnessDataManager.getInstance(context);
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readDailyFitnessDataImpl(final double d, final double d2, final Resolver<List<FitnessDataModule.TenMinsFitnessData>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass1 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readDailyFitnessData(Double.valueOf(d).longValue(), Double.valueOf(d2).longValue()));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error getting fitness data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readDailyFitnessDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readWeeklyFitnessDataImpl(final double d, final double d2, final Resolver<List<FitnessDataModule.DailyFitnessData>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass2 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readWeeklyFitnessData(Double.valueOf(d).longValue(), Double.valueOf(d2).longValue()));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error getting fitness data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readWeeklyFitnessDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readMonthlyFitnessDataImpl(final double d, final double d2, final double d3, final Resolver<List<FitnessDataModule.WeeklyFitnessData>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass3 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readMonthlyFitnessData(Double.valueOf(d).longValue(), Double.valueOf(d2).longValue(), Double.valueOf(d3).intValue()));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error getting fitness data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readMonthlyFitnessDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readYearlyFitnessDataImpl(final double d, final double d2, final Resolver<List<FitnessDataModule.MonthlyFitnessData>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass4 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readYearlyFitnessData(Double.valueOf(d).longValue(), Double.valueOf(d2).longValue()));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error getting fitness data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readYearlyFitnessDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readAllTimeSummaryDataImpl(final double d, final double d2, final FitnessDataModule.ReadAllTimeSummaryDataPackageNames readAllTimeSummaryDataPackageNames, final double d3, final Resolver<FitnessDataModule.AllTimeSummaryData> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass5 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readAllTimeSummaryData(Double.valueOf(d).longValue(), Double.valueOf(d2).longValue(), readAllTimeSummaryDataPackageNames.packageNameList, Double.valueOf(d3).intValue()));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error getting all time summary data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readAllTimeSummaryDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readYearlySummaryDataImpl(final double d, final double d2, final FitnessDataModule.ReadYearlySummaryDataPackageNames readYearlySummaryDataPackageNames, final double d3, final Resolver<List<FitnessDataModule.MonthlySummaryData>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass6 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readYearlySummaryData(Double.valueOf(d).longValue(), Double.valueOf(d2).longValue(), readYearlySummaryDataPackageNames.packageNameList, Double.valueOf(d3).intValue()));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error getting fitness data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readYearlySummaryDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readMonthlySummaryDataImpl(final double d, final double d2, final FitnessDataModule.ReadMonthlySummaryDataPackageNames readMonthlySummaryDataPackageNames, final double d3, final Resolver<List<FitnessDataModule.WeeklySummaryData>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass7 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readMonthlySummaryData(Double.valueOf(d).longValue(), Double.valueOf(d2).longValue(), readMonthlySummaryDataPackageNames.packageNameList, Double.valueOf(d3).intValue()));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error getting fitness data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readMonthlySummaryDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readWeeklySummaryDataImpl(final double d, final double d2, final FitnessDataModule.ReadWeeklySummaryDataPackageNames readWeeklySummaryDataPackageNames, final Resolver<List<FitnessDataModule.DailySummaryData>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass8 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readWeeklySummaryData(Double.valueOf(d).longValue(), Double.valueOf(d2).longValue(), readWeeklySummaryDataPackageNames.packageNameList));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error getting fitness data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readWeeklySummaryDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readDailySummaryDataImpl(final double d, final FitnessDataModule.ReadDailySummaryDataPackageNames readDailySummaryDataPackageNames, final Resolver<FitnessDataModule.DailySummaryData> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass9 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readDailySummaryData(Double.valueOf(d).longValue(), readDailySummaryDataPackageNames.packageNameList));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error getting daily summary data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readDailySummaryDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void insertOrUpdateUserInfoImpl(FitnessDataModule.UserInfo userInfo) {
        try {
            sFitnessManager.insertOrUpdateUserInfo(userInfo);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Error writing userInfo data:" + e);
        }
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readUserInfoImpl(Resolver<FitnessDataModule.UserInfo> resolver) {
        try {
            resolver.resolve(sFitnessManager.readUserInfo());
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Error getting userInfo info:" + e);
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void insertUserMetricsImpl(FitnessDataModule.UserMetrics userMetrics) {
        try {
            sFitnessManager.insertUserMetrics(userMetrics);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Error writing userMetrics data:" + e);
        }
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readUserMetricsImpl(double d, Resolver<FitnessDataModule.UserMetrics> resolver) {
        try {
            resolver.resolve(sFitnessManager.readUserMetrics(Double.valueOf(d).longValue()));
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Error getting userMetrics data:" + e);
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void insertUserGoalsImpl(FitnessDataModule.UserGoals userGoals) {
        try {
            sFitnessManager.insertUserGoals(userGoals);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Error writing userGoals data:" + e);
        }
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readUserGoalsImpl(double d, Resolver<FitnessDataModule.UserGoals> resolver) {
        try {
            resolver.resolve(sFitnessManager.readUserGoals(Double.valueOf(d).longValue()));
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Error getting userGoals data:" + e);
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void readDailyProgressImpl(final double d, final double d2, final Resolver<List<FitnessDataModule.ProgressData>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass10 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.readDailyProgress(Double.valueOf(d).longValue(), Double.valueOf(d2).longValue()));
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error reading progress data: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::readDailyProgressImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void addSimulatedDailyDataImpl(double d, double d2, String str) {
        double d3;
        double d4;
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        if (d > 0.0d) {
            i = 1;
            d3 = (60.0d * d) / 3.0d;
            d4 = 0.0d;
        } else {
            d4 = d2 / FitnessDataManager.getUserBMR(currentTimeMillis);
            d3 = 1.0d;
        }
        Uri uriForEffort = FitnessDataContract.uriForEffort();
        ContentValues contentValues = new ContentValues();
        contentValues.put("effort", Double.valueOf(d4));
        contentValues.put(FitnessTrackerMoveContract.Calories.IS_ACTIVE, Integer.valueOf(i));
        contentValues.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, str);
        contentValues.put("pluginVersionMajor", (Integer) 23);
        contentValues.put("pluginVersionMinor", (Integer) 0);
        contentValues.put("pluginVersionPatch", (Integer) 0);
        contentValues.put("pluginVersionChange", (Integer) 0);
        ContentProviderClient acquireUnstableContentProviderClient = sContext.getContentResolver().acquireUnstableContentProviderClient(uriForEffort.getAuthority());
        while (d3 > 0.0d) {
            double d5 = (double) currentTimeMillis;
            double d6 = d3 - 1.0d;
            Double.isNaN(d5);
            contentValues.put("setDate", Double.valueOf(d5 - (3000.0d * d6)));
            Uri insert = acquireUnstableContentProviderClient.insert(uriForEffort, contentValues);
            if (insert == null || insert.compareTo(FitnessDataContract.uriForShutdown()) != 0) {
                d3 = d6;
            }
        }
        acquireUnstableContentProviderClient.close();
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void addSimulatedLongtermDataImpl(final FitnessDataModule.AddSimulatedLongtermDataApps addSimulatedLongtermDataApps, final Resolver<Void> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass11 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    FitnessDataModuleImpl.sFitnessManager.addSimulatedData(addSimulatedLongtermDataApps.appList);
                    resolver.resolve(null);
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error adding simulated data: " + e);
                    resolver.reject(e);
                }
                return null;
            }
        }, "FitnessDataModuleImpl::addSimulatedDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void wipeAllDataImpl(final Resolver<Void> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass12 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    FitnessDataModuleImpl.sFitnessManager.wipeAllData();
                    resolver.resolve(null);
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error erasing all data:" + e);
                }
                return null;
            }
        }, "FitnessDataModuleImpl::wipeAllDataImpl");
    }

    @Override // com.oculus.modules.codegen.FitnessDataModule
    public void getActiveYearsImpl(final Resolver<List<Double>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.FitnessDataModuleImpl.AnonymousClass13 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    resolver.resolve(FitnessDataModuleImpl.sFitnessManager.getActiveYears());
                    return null;
                } catch (Exception e) {
                    String str = FitnessDataModuleImpl.TAG;
                    Log.e(str, "Error checking data existence: " + e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "FitnessDataModuleImpl::getActiveYearsImpl");
    }
}
