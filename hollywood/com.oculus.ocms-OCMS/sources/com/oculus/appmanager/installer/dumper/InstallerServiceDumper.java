package com.oculus.appmanager.installer.dumper;

import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.installer.dumper.InstallerDumperModule;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID"})
public class InstallerServiceDumper implements DumperPlugin {
    public static final String CMD_PRINT = "print";
    public static final String NAME = "installer";
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private final InfoUtils mInfoUtils;
    private final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(InstallerDumperModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULSEP_BINDING_ID, injectorLike);
    }

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public String getName() {
        return NAME;
    }

    @AutoGeneratedAccessMethod
    public static final InstallerServiceDumper _UL__ULSEP_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (InstallerServiceDumper) UL.factorymap.get(InstallerDumperModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final InstallerServiceDumper _UL__ULSEP_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new InstallerServiceDumper(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(InstallerDumperModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public InstallerServiceDumper(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        this.mInfoUtils = InfoUtils._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_ACCESS_METHOD(injectorLike);
    }

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public void dump(DumperContext dumperContext) throws DumpException {
        PrintStream stdout = dumperContext.getStdout();
        List<String> argsAsList = dumperContext.getArgsAsList();
        if (argsAsList.size() < 1) {
            stdout.println("Missing command");
            printUsage(stdout);
            return;
        }
        char c = 0;
        String str = argsAsList.get(0);
        if (str.hashCode() != 106934957 || !str.equals(CMD_PRINT)) {
            c = 65535;
        }
        if (c == 0) {
            printUpdates(stdout);
        }
    }

    private static void printUsage(PrintStream printStream) {
        printStream.format("%s <command>\n", "Usage: " + "dumpapp installer");
        printStream.format("%s %s\n", "       " + "dumpapp installer", CMD_PRINT);
        printStream.println();
        printStream.format("%s %s: Prints all updates in database\n", "dumpapp installer", CMD_PRINT);
        printStream.println();
    }

    private void printUpdates(PrintStream printStream) {
        String str;
        long j;
        String str2;
        int i = 7;
        int i2 = 0;
        char c = 1;
        printStream.format("%-40s %12s %-3s %-18s %-20s %-22s %-18s\n", "identifier", "update type", "id", "state", "failure code", "state timestamp", "duration");
        ArrayList arrayList = new ArrayList(this.mInfoUtils.getAllUpdates());
        Collections.sort(arrayList, new Comparator<ApkUpdateInfo>() {
            /* class com.oculus.appmanager.installer.dumper.InstallerServiceDumper.AnonymousClass1 */

            public int compare(ApkUpdateInfo apkUpdateInfo, ApkUpdateInfo apkUpdateInfo2) {
                return (int) (apkUpdateInfo2.id - apkUpdateInfo.id);
            }
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ApkUpdateInfo apkUpdateInfo = (ApkUpdateInfo) it.next();
            int failureCode = apkUpdateInfo.getExtras().getFailureCode();
            if (failureCode == -1) {
                str = "none";
            } else {
                str = InstallerResultError.fromInt(failureCode).name();
            }
            if (apkUpdateInfo.getState().isFinal()) {
                j = apkUpdateInfo.getExtras().getStateTimestamp();
            } else {
                j = ((Clock) FbInjector.lazyInstance(i2, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now();
            }
            long createdTimestamp = j - apkUpdateInfo.getExtras().getCreatedTimestamp();
            Object[] objArr = new Object[i];
            objArr[i2] = apkUpdateInfo.identifier;
            objArr[c] = apkUpdateInfo.updateType.name();
            objArr[2] = Long.valueOf(apkUpdateInfo.id);
            objArr[3] = apkUpdateInfo.getState().name();
            objArr[4] = str;
            objArr[5] = this.mSimpleDateFormat.format(new Date(apkUpdateInfo.getExtras().getStateTimestamp()));
            if (createdTimestamp <= 0) {
                str2 = "unknown";
            } else {
                str2 = TimeUnit.MILLISECONDS.toSeconds(createdTimestamp) + "s (" + TimeUnit.MILLISECONDS.toMinutes(createdTimestamp) + "m)";
            }
            objArr[6] = str2;
            printStream.format("%-40s %12s %-3s %-18s %-20s %-22s %-18s\n", objArr);
            i = 7;
            i2 = 0;
            c = 1;
        }
    }
}
