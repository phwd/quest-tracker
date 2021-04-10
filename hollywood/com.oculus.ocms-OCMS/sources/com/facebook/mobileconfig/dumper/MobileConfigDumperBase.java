package com.facebook.mobileconfig.dumper;

import androidx.annotation.Nullable;
import com.facebook.ipc.activity.ActivityConstants;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.mobileconfig.impl.MobileConfigDebugUtil;
import com.facebook.mobileconfig.impl.MobileConfigValueUtil;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.mobileconfig.metadata.ParamsMapList;
import com.facebook.mobileconfig.override.OverrideUtil;
import com.facebook.mobileconfig.troubleshooting.MobileConfigOverridesWriterHolder;
import com.facebook.mobileconfig.troubleshooting.MobileConfigQEInfoManagerHolder;
import com.facebook.mobileconfig.troubleshooting.MobileConfigQEInfoQueryParamsHolder;
import com.facebook.mobileconfig.troubleshooting.MobileConfigResponseResult;
import com.facebook.mobileconfig.troubleshooting.MobileConfigValueExplainerHolder;
import com.facebook.mobileconfig.ui.QEGKDefinitions;
import com.facebook.mobileconfig.ui.TroubleshootingResponse;
import com.facebook.mobileconfig.util.MobileConfigUtil;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.oculus.appmanager.installer.dumper.InstallerServiceDumper;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public abstract class MobileConfigDumperBase implements DumperPlugin {
    protected static final String CLEAR_CMD = "clear";
    protected static final String CLEAR_OVERRIDE = "clear_overrides";
    protected static final String GK_CMD = "gk";
    protected static final Options GK_OPTIONS = new Options();
    protected static final Option GK_OPTION_SESSIONLESS = new Option("s", "sessionless", false, "Whether or not the GK is sessionless, default is false");
    protected static final String GK_PRINT_CMD = "print";
    protected static final String GK_SET_CMD = "set";
    protected static final String GK_UNSET_CMD = "unset";
    protected static final String HELP_CMD = "help";
    protected static final String IMPORT_CMD = "import";
    protected static final String IMPORT_USER_CMD = "import_user";
    protected static final String NAME = "mobileconfig";
    protected static final String OVERRIDE_CMD = "override";
    protected static final String PRINT_CMD = "print";
    protected static final Options PRINT_OPTIONS = new Options();
    protected static final Option PRINT_OPTION_JSON = new Option("j", "json", false, "Print even more verbose JSON output");
    protected static final Option PRINT_OPTION_VERBOSE = new Option("v", "verbose", false, "Print verbose output");
    public static final int QEINFO_FETCH_TIMEOUT_MS = 20000;
    protected static final String QE_CMD = "qe";
    protected static final Options QE_OPTIONS = new Options();
    protected static final Option QE_OPTION_GROUPS = new Option("g", "groups", false, "Print groups");
    protected static final Option QE_OPTION_MINE = new Option("m", "mine", false, "Print only assigned universe");
    protected static final Option QE_OPTION_PARAMS = new Option("p", "params", false, "Print associated params");
    protected static final Option QE_OPTION_SESSIONLESS = new Option("s", "sessionless", false, "Whether or not the QE is sessionless, default is false");
    protected static final String QE_PRINT_CMD = "print";
    protected static final String QE_REMOVE_CMD = "remove";
    protected static final String QE_SET_CMD = "set";
    protected static final String QE_UNSET_CMD = "unset";
    protected static final String SCHEMA_CMD = "schema";
    protected static final String STATUS_CMD = "status";
    protected static final String TROUBLESHOOT_CMD = "troubleshoot";
    protected static final String UPDATE_CMD = "update";
    protected static final String USAGE_OVERRIDE_ERROR = "Usage: override <config_name> <param_name> <new_value>\n       override <config_name>.<param_name> <new_value>";
    protected static final String USAGE_TROUBLESHOOT_ERROR = "Usage: troubleshoot <config_name> <param_name>\n       troubleshoot <config_name>.<param_name>";
    protected List<String> mArgs;
    protected String mCommandName;
    protected DumperContext mDumperContext;
    protected PrintStream mWriter;

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public String getName() {
        return "mobileconfig";
    }

    static {
        PRINT_OPTIONS.addOption(PRINT_OPTION_VERBOSE);
        PRINT_OPTIONS.addOption(PRINT_OPTION_JSON);
        GK_OPTIONS.addOption(GK_OPTION_SESSIONLESS);
        QE_OPTIONS.addOption(QE_OPTION_GROUPS);
        QE_OPTIONS.addOption(QE_OPTION_MINE);
        QE_OPTIONS.addOption(QE_OPTION_PARAMS);
        QE_OPTIONS.addOption(QE_OPTION_SESSIONLESS);
    }

    /* access modifiers changed from: protected */
    public void initialize(DumperContext dumperContext) throws DumpException {
        this.mDumperContext = dumperContext;
        this.mWriter = dumperContext.getStdout();
        this.mArgs = dumperContext.getArgsAsList();
        this.mCommandName = (String) Iterables.getFirst(this.mArgs, "");
    }

    /* access modifiers changed from: protected */
    public void doUsage() {
        this.mWriter.format("%s <command>\n", "Usage: dumpapp mobileconfig");
        this.mWriter.format("%s status\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s print [-j|--json] [[-v|--verbose] [regex]]\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s update\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s clear\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s import <task_number>\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s import_user <id_or_unixname>\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s override <config_name> <param_name> <new_value>\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s override <config_name>.<param_name> <new_value>\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s clear_overrides\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s qe print [-g|--groups, -m|--mine, -p|--params, -s|sessionless] [universe_regex]\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s qe set <universe> <experiment:group>\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s qe unset <universe>\n", "       dumpapp mobileconfig");
        this.mWriter.format("%s qe remove <universe>\n", "       dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s status: Print whether or not MobileConfig is currently enabled.\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s print: Print all matching MobileConfig fields and their values\n", "dumpapp mobileconfig");
        this.mWriter.format("%s -v|--verbose: print specifier and loggingId\n", "    ");
        this.mWriter.format("%s -j|--json: print JSON format dump like consistency logging, which also contains value source\n", "    ");
        this.mWriter.println();
        this.mWriter.format("%s update: Triggers an update for all mobileconfigs\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s clear: Clears sessionless and current session data.\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s import: Import MobileConfig from a task.\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s import_user: Import user's mobileconfig through user id or unixname.\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s override: Set override value\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s clear_overrides: Delete all local overrides\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s qe print: Print QE universe information\n", "dumpapp mobileconfig");
        this.mWriter.format("%s -g|--groups: print all experiments and groups\n", "    ");
        this.mWriter.format("%s -m|--mine: print only universes with assigned experiments\n", "    ");
        this.mWriter.format("%s -p|--params: print associated params\n", "    ");
        this.mWriter.println();
        this.mWriter.format("%s qe set: Override into a given QE group\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s qe unset: Clear override for a given universe\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s qe remove: Force into unassigned state for a given universe\n", "dumpapp mobileconfig");
        this.mWriter.println();
        this.mWriter.format("%s troubleshoot config_name.param_name : Print troubleshooting info for the param \n", "dumpapp mobileconfig");
        this.mWriter.println();
    }

    private static boolean parseBoolean(String str) {
        return ActivityConstants.Extras.WATCH_FEED_INJECTION.equalsIgnoreCase(str) || "yes".equalsIgnoreCase(str) || "true".equalsIgnoreCase(str) || "on".equalsIgnoreCase(str);
    }

    private static String getNonPrefixClientDrivenConfigName(String str) {
        String[] strArr = {"QE_", "MC_"};
        for (String str2 : strArr) {
            if (str.indexOf(str2) == 0 && str.length() > str2.length()) {
                return str.substring(str2.length());
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public void doOverride(ParamsMapList paramsMapList, MobileConfigValueUtil mobileConfigValueUtil) throws DumpUsageException {
        String str;
        String str2;
        String str3;
        try {
            String[] args = this.mDumperContext.getParser().parse(new Options(), (String[]) Iterables.toArray(Iterables.skip(this.mArgs, 1), String.class)).getArgs();
            if (args.length <= 1) {
                this.mWriter.println(USAGE_OVERRIDE_ERROR);
                return;
            }
            if (args.length == 2) {
                String[] split = args[0].split("\\.");
                if (split.length != 2) {
                    this.mWriter.println(USAGE_OVERRIDE_ERROR);
                    return;
                }
                str = split[0];
                str2 = split[1];
                str3 = args[1];
            } else {
                str = args[0];
                str2 = args[1];
                str3 = args[2];
            }
            ParamsMapEntry paramsMapEntry = null;
            for (ParamsMapEntry paramsMapEntry2 : paramsMapList.entries) {
                if ((str.equalsIgnoreCase(paramsMapEntry2.configName) || str.equalsIgnoreCase(getNonPrefixClientDrivenConfigName(paramsMapEntry2.configName))) && str2.equalsIgnoreCase(paramsMapEntry2.paramName)) {
                    paramsMapEntry = paramsMapEntry2;
                }
            }
            if (paramsMapEntry == null) {
                PrintStream printStream = this.mWriter;
                printStream.println("Invalid config name/param name combination " + str + "/" + str2 + ". Check schema for correct name. If the config_name/param_name contain spaces. Replaces the spaces with underscores");
                this.mWriter.println(USAGE_OVERRIDE_ERROR);
                return;
            }
            int i = paramsMapEntry.paramType;
            long specifier = paramsMapEntry.getSpecifier();
            if (i == 1) {
                mobileConfigValueUtil.setOverride(specifier, parseBoolean(str3));
                this.mWriter.format("Override set for %s_%s(%s) to %b\n", str, str2, Integer.valueOf(i), Boolean.valueOf(mobileConfigValueUtil.getOverrideBoolean(specifier)));
            } else if (i == 2) {
                mobileConfigValueUtil.setOverride(specifier, Long.parseLong(str3));
                this.mWriter.format("Override set for %s_%s(%s) to %d\n", str, str2, Integer.valueOf(i), Long.valueOf(mobileConfigValueUtil.getOverrideLong(specifier)));
            } else if (i == 3) {
                mobileConfigValueUtil.setOverride(specifier, str3);
                this.mWriter.format("Override set for %s_%s(%s) to %s\n", str, str2, Integer.valueOf(i), mobileConfigValueUtil.getOverrideString(specifier));
            } else if (i != 4) {
                this.mWriter.println("Unsupported param type");
            } else {
                mobileConfigValueUtil.setOverride(specifier, Double.parseDouble(str3));
                this.mWriter.format("Override set for %s_%s(%s) to %f\n", str, str2, Integer.valueOf(i), Double.valueOf(mobileConfigValueUtil.getOverrideDouble(specifier)));
            }
        } catch (ParseException e) {
            throw new DumpUsageException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void doStatus(MobileConfigManagerHolder mobileConfigManagerHolder, @Nullable MobileConfigManagerHolder mobileConfigManagerHolder2) {
        if (mobileConfigManagerHolder == null || !mobileConfigManagerHolder.isValid()) {
            this.mWriter.println("session mobileconfig is not running");
        } else {
            this.mWriter.println("session mobileconfig is running");
        }
        if (mobileConfigManagerHolder2 == null || !mobileConfigManagerHolder2.isValid()) {
            this.mWriter.println("sessionless mobileconfig is not running");
        } else {
            this.mWriter.println("sessionless mobileconfig is running");
        }
    }

    /* access modifiers changed from: protected */
    public void doPrintSchema(MobileConfigManagerHolder mobileConfigManagerHolder) throws DumpUsageException {
        this.mWriter.println(mobileConfigManagerHolder.getSchemaString());
    }

    /* access modifiers changed from: protected */
    public void doPrint(MobileConfigDebugUtil mobileConfigDebugUtil, ParamsMapList paramsMapList) throws DumpUsageException {
        try {
            CommandLine parse = this.mDumperContext.getParser().parse(PRINT_OPTIONS, (String[]) Iterables.toArray(Iterables.skip(this.mArgs, 1), String.class));
            if (parse.hasOption(PRINT_OPTION_JSON.getOpt())) {
                String consistencyLoggingJson = mobileConfigDebugUtil.getConsistencyLoggingJson(paramsMapList.entries);
                PrintStream printStream = this.mWriter;
                if (consistencyLoggingJson == null) {
                    consistencyLoggingJson = "";
                }
                printStream.println(consistencyLoggingJson);
                return;
            }
            boolean hasOption = parse.hasOption(PRINT_OPTION_VERBOSE.getOpt());
            String str = (String) Iterables.get(parse.getArgList(), 0, "");
            ImmutableList.Builder builder = new ImmutableList.Builder();
            ImmutableList.Builder builder2 = new ImmutableList.Builder();
            for (ParamsMapEntry paramsMapEntry : paramsMapList.entries) {
                if (paramsMapEntry.isSessionless) {
                    builder.add((Object) paramsMapEntry);
                } else {
                    builder2.add((Object) paramsMapEntry);
                }
            }
            String simpleLogString = mobileConfigDebugUtil.getSimpleLogString(str, hasOption, "", true, builder.build());
            if (!simpleLogString.isEmpty()) {
                this.mWriter.println("Sessionless configs:");
                this.mWriter.println(simpleLogString);
            }
            String simpleLogString2 = mobileConfigDebugUtil.getSimpleLogString(str, hasOption, "", false, builder2.build());
            if (!simpleLogString2.isEmpty()) {
                this.mWriter.println("Session-based configs:");
                this.mWriter.println(simpleLogString2);
            }
        } catch (ParseException e) {
            throw new DumpUsageException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void doImport(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl) throws DumpUsageException {
        String str;
        if (mobileConfigManagerHolderImpl == null || !mobileConfigManagerHolderImpl.isValid()) {
            this.mWriter.println("MobileConfig is not running; no changes made.");
            return;
        }
        String taskNumberIfValid = MobileConfigUtil.getTaskNumberIfValid((String) Iterables.skip(this.mArgs, 1).iterator().next());
        if (taskNumberIfValid == null) {
            this.mWriter.println("Invalid task number provided");
            return;
        }
        String importOverridesFromTask = new MobileConfigOverridesWriterHolder(mobileConfigManagerHolderImpl).importOverridesFromTask(taskNumberIfValid);
        PrintStream printStream = this.mWriter;
        if (importOverridesFromTask.isEmpty()) {
            str = "Successfully imported overrides from task " + taskNumberIfValid + ". Restart the app for changes to take effect.";
        } else {
            str = "Failed to import overrides from task " + taskNumberIfValid + ". Error: " + importOverridesFromTask;
        }
        printStream.println(str);
    }

    /* access modifiers changed from: protected */
    public void doImportUser(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl) throws DumpUsageException {
        String str;
        if (mobileConfigManagerHolderImpl == null || !mobileConfigManagerHolderImpl.isValid()) {
            this.mWriter.println("MobileConfig is not running; no changes made.");
            return;
        }
        String userIDIfValid = MobileConfigUtil.getUserIDIfValid((String) Iterables.skip(this.mArgs, 1).iterator().next());
        if (userIDIfValid == null) {
            this.mWriter.println("Invalid user ID");
            return;
        }
        String importOverridesFromUser = new MobileConfigOverridesWriterHolder(mobileConfigManagerHolderImpl).importOverridesFromUser(userIDIfValid);
        PrintStream printStream = this.mWriter;
        if (importOverridesFromUser.isEmpty()) {
            str = "Successfully imported overrides from user " + userIDIfValid + ". Restart the app for changes to take effect.";
        } else {
            str = "Failed to import overrides from user " + userIDIfValid + ". Error: " + importOverridesFromUser;
        }
        printStream.println(str);
    }

    /* access modifiers changed from: protected */
    public void doClearOverrides(MobileConfigValueUtil mobileConfigValueUtil, MobileConfigManagerHolder mobileConfigManagerHolder) throws DumpUsageException {
        if (mobileConfigManagerHolder == null || !mobileConfigManagerHolder.isValid()) {
            this.mWriter.println("MobileConfig is not running; no changes made");
            return;
        }
        mobileConfigValueUtil.removeAllOverrides();
        mobileConfigManagerHolder.clearOverrides();
        this.mWriter.println("Deleted all overrides.");
    }

    /* access modifiers changed from: protected */
    public void doClearUserData(MobileConfigManagerHolder mobileConfigManagerHolder, @Nullable MobileConfigManagerHolder mobileConfigManagerHolder2) {
        if (mobileConfigManagerHolder == null || !mobileConfigManagerHolder.isValid()) {
            this.mWriter.println("Session mobileConfig is not running; no changes made.");
        } else {
            mobileConfigManagerHolder.clearCurrentUserData();
            this.mWriter.println("Session data have been cleared. Restart the app to make sure changes take effect.");
        }
        if (mobileConfigManagerHolder2 == null || !mobileConfigManagerHolder2.isValid()) {
            this.mWriter.println("Sessionless mobileConfig is not running; no changes made.");
            return;
        }
        mobileConfigManagerHolder2.clearCurrentUserData();
        this.mWriter.println("Sessionless data have been cleared. Restart the app to make sure changes take effect.");
    }

    /* access modifiers changed from: protected */
    public void doQE(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl, MobileConfigValueUtil mobileConfigValueUtil, MobileConfigDebugUtil mobileConfigDebugUtil, OverrideUtil overrideUtil) throws DumpUsageException {
        try {
            CommandLine parse = this.mDumperContext.getParser().parse(QE_OPTIONS, (String[]) Iterables.toArray(Iterables.skip(this.mArgs, 1), String.class));
            String[] args = parse.getArgs();
            String str = args[0];
            if (InstallerServiceDumper.CMD_PRINT.equals(str)) {
                doQEPrint(mobileConfigManagerHolderImpl, mobileConfigValueUtil, overrideUtil, mobileConfigDebugUtil, parse, args.length == 2 ? args[1] : "");
            } else if ("set".equals(str)) {
                if (args.length != 3) {
                    this.mWriter.println("Too few arguments, please see usage");
                    return;
                }
                String str2 = args[1];
                String[] split = args[2].split(":");
                if (split.length != 2) {
                    this.mWriter.println("Invalid experiment group identifier, please use <experiment>:<group>");
                } else {
                    doQEOverride(mobileConfigManagerHolderImpl, overrideUtil, str, str2, split[0], split[1]);
                }
            } else if (!QE_REMOVE_CMD.equals(str) && !"unset".equals(str)) {
                this.mWriter.println("Unsupported command, please see usage");
                doUsage();
            } else if (args.length != 2) {
                this.mWriter.println("Too few arguments, please see usage");
            } else {
                doQEOverride(mobileConfigManagerHolderImpl, overrideUtil, str, args[1], "", "");
            }
        } catch (ParseException e) {
            throw new DumpUsageException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void doQEPrint(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl, MobileConfigValueUtil mobileConfigValueUtil, OverrideUtil overrideUtil, MobileConfigDebugUtil mobileConfigDebugUtil, CommandLine commandLine, String str) {
        String str2;
        Iterator<QEGKDefinitions.ExperimentDef> it;
        MobileConfigValueUtil mobileConfigValueUtil2 = mobileConfigValueUtil;
        boolean hasOption = commandLine.hasOption(QE_OPTION_GROUPS.getOpt());
        boolean hasOption2 = commandLine.hasOption(QE_OPTION_MINE.getOpt());
        boolean hasOption3 = commandLine.hasOption(QE_OPTION_PARAMS.getOpt());
        boolean hasOption4 = commandLine.hasOption(QE_OPTION_SESSIONLESS.getOpt());
        if (!hasOption || !hasOption3) {
            MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder = new MobileConfigQEInfoQueryParamsHolder();
            if (str != null && !str.isEmpty()) {
                mobileConfigQEInfoQueryParamsHolder.setUniverseRegex(str);
            }
            mobileConfigQEInfoQueryParamsHolder.withCurrentExperimentGroup(true);
            char c = 0;
            mobileConfigQEInfoQueryParamsHolder.withGatekeepers(false);
            mobileConfigQEInfoQueryParamsHolder.withParams(hasOption3);
            mobileConfigQEInfoQueryParamsHolder.withGroupParams(false);
            mobileConfigQEInfoQueryParamsHolder.withGroups(hasOption);
            mobileConfigQEInfoQueryParamsHolder.withExperiments(hasOption || hasOption3);
            MobileConfigResponseResult mobileConfigResponseResult = new MobileConfigResponseResult();
            new MobileConfigQEInfoManagerHolder(mobileConfigManagerHolderImpl).getQEInfo(mobileConfigQEInfoQueryParamsHolder, mobileConfigResponseResult, "", "");
            try {
                mobileConfigResponseResult.waitFor(20000);
                if (!mobileConfigResponseResult.isDone()) {
                    this.mWriter.println("Timed out fetching QEInfo");
                } else if (!mobileConfigResponseResult.isSuccess()) {
                    PrintStream printStream = this.mWriter;
                    printStream.println("Failed to fetch QEInfo: " + mobileConfigResponseResult.getResponse());
                } else {
                    QEGKDefinitions readFromString = QEGKDefinitions.readFromString(mobileConfigResponseResult.getResponse());
                    if (readFromString == null) {
                        PrintStream printStream2 = this.mWriter;
                        printStream2.println("Failed to parse QEInfo: " + mobileConfigResponseResult.getResponse());
                        return;
                    }
                    for (QEGKDefinitions.UniverseDef universeDef : readFromString.universes) {
                        String overrideExperiment = mobileConfigValueUtil2.getOverrideExperiment(universeDef.name);
                        String overrideGroup = mobileConfigValueUtil2.getOverrideGroup(universeDef.name);
                        if (mobileConfigValueUtil2.hasOverrideForQEUniverse(universeDef.name)) {
                            if (overrideExperiment.isEmpty() || overrideGroup.isEmpty()) {
                                overrideExperiment = "";
                                overrideGroup = overrideExperiment;
                            }
                            str2 = OVERRIDE_CMD;
                        } else if (universeDef.currentExperiment != null && !universeDef.currentExperiment.isEmpty() && universeDef.currentGroup != null && !universeDef.currentGroup.isEmpty()) {
                            overrideExperiment = universeDef.currentExperiment;
                            overrideGroup = universeDef.currentGroup;
                            str2 = "server";
                        } else if (!hasOption2) {
                            overrideExperiment = "";
                            overrideGroup = overrideExperiment;
                            str2 = overrideGroup;
                        }
                        if (!overrideExperiment.isEmpty()) {
                            PrintStream printStream3 = this.mWriter;
                            Object[] objArr = new Object[4];
                            objArr[c] = universeDef.name;
                            objArr[1] = overrideExperiment;
                            objArr[2] = overrideGroup;
                            objArr[3] = str2;
                            printStream3.println(String.format("%s = %s:%s [%s]", objArr));
                        } else {
                            this.mWriter.println(String.format("%s = unassigned [%s]", universeDef.name, str2));
                        }
                        if (hasOption) {
                            Iterator<QEGKDefinitions.ExperimentDef> it2 = universeDef.experiments.iterator();
                            while (it2.hasNext()) {
                                QEGKDefinitions.ExperimentDef next = it2.next();
                                for (QEGKDefinitions.GroupDef groupDef : next.groups) {
                                    if (!overrideExperiment.equals(next.name) || !overrideGroup.equals(groupDef.name)) {
                                        it = it2;
                                        this.mWriter.println(String.format("  %s:%s", next.name, groupDef.name));
                                    } else {
                                        this.mWriter.println(String.format("%s%s:%s [%s]", "  ", overrideExperiment, overrideGroup, str2));
                                        it = it2;
                                    }
                                    it2 = it;
                                }
                            }
                        } else if (hasOption3) {
                            this.mWriter.println(mobileConfigDebugUtil.getSimpleLogString("", false, "  ", hasOption4, ImmutableList.copyOf((Collection) overrideUtil.getAssociatedParams(universeDef))));
                        }
                        mobileConfigValueUtil2 = mobileConfigValueUtil;
                        c = 0;
                    }
                }
            } catch (InterruptedException e) {
                PrintStream printStream4 = this.mWriter;
                printStream4.println("QEInfo fetch failed: interrupted: " + e.getMessage());
            }
        } else {
            this.mWriter.println("Only one of --groups or --params can be specified at the same time.");
        }
    }

    @Nullable
    private QEGKDefinitions.UniverseDef getUniverseInfo(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl, String str) {
        MobileConfigQEInfoQueryParamsHolder mobileConfigQEInfoQueryParamsHolder = new MobileConfigQEInfoQueryParamsHolder();
        mobileConfigQEInfoQueryParamsHolder.setUniverseRegex("^" + str + "$");
        mobileConfigQEInfoQueryParamsHolder.withUniverseParams(true);
        mobileConfigQEInfoQueryParamsHolder.withCurrentExperimentGroup(true);
        mobileConfigQEInfoQueryParamsHolder.withGatekeepers(false);
        mobileConfigQEInfoQueryParamsHolder.withParams(true);
        mobileConfigQEInfoQueryParamsHolder.withGroupParams(true);
        mobileConfigQEInfoQueryParamsHolder.withGroups(true);
        mobileConfigQEInfoQueryParamsHolder.withExperiments(true);
        MobileConfigResponseResult mobileConfigResponseResult = new MobileConfigResponseResult();
        new MobileConfigQEInfoManagerHolder(mobileConfigManagerHolderImpl).getQEInfo(mobileConfigQEInfoQueryParamsHolder, mobileConfigResponseResult, "", "");
        try {
            mobileConfigResponseResult.waitFor(20000);
            if (!mobileConfigResponseResult.isDone()) {
                this.mWriter.println("Timed out fetching QEInfo");
                return null;
            } else if (!mobileConfigResponseResult.isSuccess()) {
                PrintStream printStream = this.mWriter;
                printStream.println("Failed to fetch QEInfo: " + mobileConfigResponseResult.getResponse());
                return null;
            } else {
                QEGKDefinitions readFromString = QEGKDefinitions.readFromString(mobileConfigResponseResult.getResponse());
                if (readFromString == null) {
                    PrintStream printStream2 = this.mWriter;
                    printStream2.println("Failed to parse QEInfo: " + mobileConfigResponseResult.getResponse());
                    return null;
                } else if (readFromString.universes == null || readFromString.universes.isEmpty()) {
                    this.mWriter.println("No data returned from server");
                    return null;
                } else {
                    for (QEGKDefinitions.UniverseDef universeDef : readFromString.universes) {
                        if (universeDef.name.equals(str)) {
                            return universeDef;
                        }
                    }
                    PrintStream printStream3 = this.mWriter;
                    printStream3.println("Unable to find universe: " + str);
                    return null;
                }
            }
        } catch (InterruptedException e) {
            PrintStream printStream4 = this.mWriter;
            printStream4.println("QEInfo fetch failed: interrupted: " + e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void doQEOverride(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl, OverrideUtil overrideUtil, String str, String str2, String str3, String str4) {
        QEGKDefinitions.UniverseDef universeInfo = getUniverseInfo(mobileConfigManagerHolderImpl, str2);
        if (universeInfo != null) {
            try {
                if (str.equals("set")) {
                    overrideUtil.overrideExperimentGroup(universeInfo, str3, str4);
                    this.mWriter.println(String.format("Updated universe: %s to %s:%s", str2, str3, str4));
                } else if (str.equals(QE_REMOVE_CMD)) {
                    overrideUtil.overrideToDefault(universeInfo);
                    this.mWriter.println(String.format("Updated universe: %s to unassigned", str2));
                } else if (str.equals("unset")) {
                    overrideUtil.removeOverride(universeInfo);
                    String str5 = "unassigned";
                    if (universeInfo.currentExperiment != null && !universeInfo.currentExperiment.isEmpty() && universeInfo.currentGroup != null && !universeInfo.currentGroup.isEmpty()) {
                        str5 = universeInfo.currentExperiment + ":" + universeInfo.currentGroup;
                    }
                    this.mWriter.println(String.format("Restored universe: %s to %s", str2, str5));
                }
                this.mWriter.println("Override updated.");
            } catch (IllegalArgumentException e) {
                this.mWriter.println("Error overriding experiment: " + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doTroubleshoot(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl, ParamsMapList paramsMapList) throws DumpUsageException {
        String str;
        String str2;
        try {
            String[] args = this.mDumperContext.getParser().parse(new Options(), (String[]) Iterables.toArray(Iterables.skip(this.mArgs, 1), String.class)).getArgs();
            boolean z = false;
            if (args.length == 1) {
                String[] split = args[0].split("\\.");
                if (split.length != 2) {
                    this.mWriter.println(USAGE_TROUBLESHOOT_ERROR);
                    return;
                } else {
                    str = split[0];
                    str2 = split[1];
                }
            } else if (args.length == 2) {
                str = args[0];
                str2 = args[1];
            } else {
                this.mWriter.println(USAGE_TROUBLESHOOT_ERROR);
                return;
            }
            HashSet hashSet = new HashSet();
            hashSet.add(str2);
            Iterator<ParamsMapEntry> it = paramsMapList.entries.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ParamsMapEntry next = it.next();
                if (next.configName.equals(str) && next.paramName.equals(str2)) {
                    z = next.isSessionless;
                    break;
                }
            }
            TroubleshootingResponse deserialize = TroubleshootingResponse.deserialize(new MobileConfigValueExplainerHolder(mobileConfigManagerHolderImpl).getClientDrivenInfo(str, hashSet, z));
            String str3 = deserialize.text;
            if (deserialize.error != null && !deserialize.error.isEmpty()) {
                str3 = "== Server error ==\n\n" + deserialize.error + "\n\n" + str3;
            }
            this.mWriter.println(str3);
        } catch (ParseException e) {
            throw new DumpUsageException(e.getMessage());
        }
    }
}
