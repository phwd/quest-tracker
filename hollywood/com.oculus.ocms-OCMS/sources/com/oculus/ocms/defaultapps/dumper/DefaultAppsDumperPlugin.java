package com.oculus.ocms.defaultapps.dumper;

import android.app.job.JobScheduler;
import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.horizon.api.common.Item;
import com.oculus.ocms.defaultapps.DefaultAppsModule;
import com.oculus.ocms.defaultapps.DefaultAppsSetupListener;
import com.oculus.ocms.defaultapps.dumper.DefaultAppsDumperModule;
import com.oculus.ocms.defaultapps.net.DefaultAppsResponse;
import com.oculus.util.constants.JobSchedulerIds;
import java.io.PrintStream;
import java.util.Iterator;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsSetupListener_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DefaultAppsDumperPlugin implements DumperPlugin {
    private static final String CMD_INSTALL = "install";
    private static final String CMD_LIST_APP_IDS = "list";
    private static final String NAME = "setupinstall";
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_ocms_defaultapps_dumper_DefaultAppsDumperPlugin_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(DefaultAppsDumperModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_dumper_DefaultAppsDumperPlugin_ULSEP_BINDING_ID, injectorLike);
    }

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public String getName() {
        return NAME;
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_ocms_defaultapps_dumper_DefaultAppsDumperPlugin_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(DefaultAppsDumperModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_dumper_DefaultAppsDumperPlugin_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final DefaultAppsDumperPlugin _UL__ULSEP_com_oculus_ocms_defaultapps_dumper_DefaultAppsDumperPlugin_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (DefaultAppsDumperPlugin) UL.factorymap.get(DefaultAppsDumperModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_dumper_DefaultAppsDumperPlugin_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final DefaultAppsDumperPlugin _UL__ULSEP_com_oculus_ocms_defaultapps_dumper_DefaultAppsDumperPlugin_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new DefaultAppsDumperPlugin(injectorLike);
    }

    @Inject
    public DefaultAppsDumperPlugin(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(2, injectorLike);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        if (r7.equals("install") == false) goto L_0x0042;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dump(com.facebook.stetho.dumpapp.DumperContext r7) throws com.facebook.stetho.dumpapp.DumpException {
        /*
            r6 = this;
            java.io.PrintStream r0 = r7.getStdout()
            java.util.List r7 = r7.getArgsAsList()
            int r1 = r7.size()
            r2 = 1
            if (r1 >= r2) goto L_0x0018
            java.lang.String r7 = "Missing command"
            r0.println(r7)
            printUsage(r0)
            return
        L_0x0018:
            r1 = 0
            java.lang.Object r7 = r7.get(r1)
            java.lang.String r7 = (java.lang.String) r7
            r3 = -1
            int r4 = r7.hashCode()
            r5 = 3322014(0x32b09e, float:4.655133E-39)
            if (r4 == r5) goto L_0x0038
            r5 = 1957569947(0x74ae259b, float:1.1037871E32)
            if (r4 == r5) goto L_0x002f
            goto L_0x0042
        L_0x002f:
            java.lang.String r4 = "install"
            boolean r7 = r7.equals(r4)
            if (r7 == 0) goto L_0x0042
            goto L_0x0043
        L_0x0038:
            java.lang.String r1 = "list"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0042
            r1 = 1
            goto L_0x0043
        L_0x0042:
            r1 = -1
        L_0x0043:
            if (r1 == 0) goto L_0x004c
            if (r1 == r2) goto L_0x0048
            goto L_0x004f
        L_0x0048:
            r6.listAppIds(r0)
            goto L_0x004f
        L_0x004c:
            r6.installApps()
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.ocms.defaultapps.dumper.DefaultAppsDumperPlugin.dump(com.facebook.stetho.dumpapp.DumperContext):void");
    }

    private static void printUsage(PrintStream printStream) {
        String str = "       " + "dumpapp setupinstall";
        printStream.format("%s <command>\n", "Usage: " + "dumpapp setupinstall");
        printStream.format("%s %s\n", str, CMD_LIST_APP_IDS);
        printStream.format("%s %s\n", str, "install");
        printStream.println();
        printStream.format("%s %s: Lists the Setup Install App Ids\n", "dumpapp setupinstall", CMD_LIST_APP_IDS);
        printStream.format("%s %s: Installs the Setup Install Apps\n", "dumpapp setupinstall", "install");
        printStream.println();
    }

    private void listAppIds(PrintStream printStream) {
        DefaultAppsResponse fetchSetupApps = ((DefaultAppsSetupListener) FbInjector.lazyInstance(1, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsSetupListener_ULSEP_BINDING_ID, this._UL_mInjectionContext)).fetchSetupApps(true);
        if (fetchSetupApps == null) {
            printStream.format("Setup Install search query returned null\n", new Object[0]);
            return;
        }
        printStream.format("Setup Install Apps:\n", new Object[0]);
        Iterator<Item> it = fetchSetupApps.node.default_applications.iterator();
        while (it.hasNext()) {
            printStream.format("%s\n", it.next().id);
        }
    }

    private void installApps() {
        JobScheduler jobScheduler = (JobScheduler) ((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(JobSchedulerIds.STANDALONE_SETUP);
        }
        ((DefaultAppsSetupListener) FbInjector.lazyInstance(1, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsSetupListener_ULSEP_BINDING_ID, this._UL_mInjectionContext)).scheduleSetupJob();
    }
}
