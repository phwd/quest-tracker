package com.facebook.mobileconfig.override;

import androidx.annotation.Nullable;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.mobileconfig.impl.MobileConfigValueUtil;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.facebook.mobileconfig.metadata.MobileConfigParamsMapModule;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.mobileconfig.metadata.ParamsMapList;
import com.facebook.mobileconfig.override.OverrideModule;
import com.facebook.mobileconfig.ui.QEGKDefinitions;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_metadata_ParamsMapList_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigValueUtil_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class OverrideUtil {
    private static final String TAG = "OverrideUtil";
    ParamsMapList mParamsMapList;
    MobileConfigValueUtil mValueUtil;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_mobileconfig_override_OverrideUtil_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(OverrideModule.UL_id._UL__ULSEP_com_facebook_mobileconfig_override_OverrideUtil_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final OverrideUtil _UL__ULSEP_com_facebook_mobileconfig_override_OverrideUtil_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OverrideUtil) UL.factorymap.get(OverrideModule.UL_id._UL__ULSEP_com_facebook_mobileconfig_override_OverrideUtil_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OverrideUtil _UL__ULSEP_com_facebook_mobileconfig_override_OverrideUtil_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new OverrideUtil(MobileConfigParamsMapModule._UL__ULSEP_com_facebook_mobileconfig_metadata_ParamsMapList_ULSEP_ACCESS_METHOD(injectorLike), MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigValueUtil_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_override_OverrideUtil_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(OverrideModule.UL_id._UL__ULSEP_com_facebook_mobileconfig_override_OverrideUtil_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public OverrideUtil(ParamsMapList paramsMapList, MobileConfigValueUtil mobileConfigValueUtil) {
        this.mParamsMapList = paramsMapList;
        this.mValueUtil = mobileConfigValueUtil;
    }

    @Nullable
    public ParamsMapEntry getParamMapEntry(QEGKDefinitions.ParamDef paramDef) {
        for (ParamsMapEntry paramsMapEntry : this.mParamsMapList.entries) {
            if (MobileConfigValueUtil.getUniqueKeyForEntry(paramsMapEntry).equals(paramDef.getUniqueKey())) {
                return paramsMapEntry;
            }
        }
        return null;
    }

    public List<ParamsMapEntry> getAssociatedParams(QEGKDefinitions.UniverseDef universeDef) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (QEGKDefinitions.ExperimentDef experimentDef : universeDef.experiments) {
            for (QEGKDefinitions.ParamDef paramDef : experimentDef.params) {
                ParamsMapEntry paramMapEntry = getParamMapEntry(paramDef);
                if (paramMapEntry == null) {
                    BLog.e(TAG, "Unable to find param - config: %s param: %d ", paramDef.config, Integer.valueOf(paramDef.key));
                } else if (!hashSet.contains(Long.valueOf(paramMapEntry.getSpecifier()))) {
                    hashSet.add(Long.valueOf(paramMapEntry.getSpecifier()));
                    arrayList.add(paramMapEntry);
                }
            }
        }
        return arrayList;
    }

    public void overrideExperimentGroup(QEGKDefinitions.UniverseDef universeDef, String str, String str2) throws IllegalArgumentException {
        QEGKDefinitions.GroupDef groupDef;
        QEGKDefinitions.ExperimentDef experimentDef;
        long j;
        double d;
        Iterator<QEGKDefinitions.ExperimentDef> it = universeDef.experiments.iterator();
        while (true) {
            groupDef = null;
            if (!it.hasNext()) {
                experimentDef = null;
                break;
            }
            experimentDef = it.next();
            if (experimentDef.name.equals(str)) {
                Iterator<QEGKDefinitions.GroupDef> it2 = experimentDef.groups.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    QEGKDefinitions.GroupDef next = it2.next();
                    if (next.name.equals(str2)) {
                        groupDef = next;
                        break;
                    }
                }
            }
        }
        if (experimentDef == null) {
            throw new IllegalArgumentException("Unable to find experiment: " + str);
        } else if (groupDef != null) {
            overrideToDefault(universeDef);
            for (QEGKDefinitions.ParamDef paramDef : groupDef.params) {
                ParamsMapEntry paramMapEntry = getParamMapEntry(paramDef);
                if (paramMapEntry == null) {
                    BLog.e(TAG, "Unable to find param - config: %s key: %d ", paramDef.config, Integer.valueOf(paramDef.key));
                } else {
                    int i = paramMapEntry.paramType;
                    if (i == 1) {
                        this.mValueUtil.setOverride(paramMapEntry.getSpecifier(), ((Boolean) paramDef.value).booleanValue());
                    } else if (i == 2) {
                        if (paramDef.value instanceof Integer) {
                            j = ((Integer) paramDef.value).longValue();
                        } else {
                            j = ((Long) paramDef.value).longValue();
                        }
                        this.mValueUtil.setOverride(paramMapEntry.getSpecifier(), Long.valueOf(j).longValue());
                    } else if (i == 3) {
                        this.mValueUtil.setOverride(paramMapEntry.getSpecifier(), (String) paramDef.value);
                    } else if (i == 4) {
                        if (paramDef.value instanceof Integer) {
                            d = ((Integer) paramDef.value).doubleValue();
                        } else {
                            d = ((Double) paramDef.value).doubleValue();
                        }
                        this.mValueUtil.setOverride(paramMapEntry.getSpecifier(), Double.valueOf(d).doubleValue());
                    }
                }
            }
            this.mValueUtil.setQEOverride(universeDef.name, str, str2);
        } else {
            throw new IllegalArgumentException("Unable to find group: " + str2);
        }
    }

    public void removeOverride(QEGKDefinitions.UniverseDef universeDef) {
        if (universeDef.experiments == null || universeDef.experiments.isEmpty()) {
            throw new IllegalArgumentException("No experiment info");
        }
        HashSet hashSet = new HashSet();
        for (QEGKDefinitions.ExperimentDef experimentDef : universeDef.experiments) {
            for (QEGKDefinitions.ParamDef paramDef : experimentDef.params) {
                ParamsMapEntry paramMapEntry = getParamMapEntry(paramDef);
                if (paramMapEntry == null) {
                    BLog.e(TAG, "Unable to find param - config: %s key: %d ", paramDef.config, Integer.valueOf(paramDef.key));
                } else if (!hashSet.contains(Long.valueOf(paramMapEntry.getSpecifier()))) {
                    this.mValueUtil.removeOverride(paramMapEntry.getSpecifier());
                    hashSet.add(Long.valueOf(paramMapEntry.getSpecifier()));
                }
            }
        }
        this.mValueUtil.removeOverridesForQEUniverse(universeDef.name);
    }

    public void overrideToDefault(QEGKDefinitions.UniverseDef universeDef) {
        boolean z;
        long j;
        String str;
        double d;
        if (universeDef.experiments == null || universeDef.experiments.isEmpty()) {
            throw new IllegalArgumentException("No experiment info");
        } else if (universeDef.params != null) {
            HashSet hashSet = new HashSet();
            for (QEGKDefinitions.ParamDef paramDef : universeDef.params) {
                if (!hashSet.contains(paramDef.getUniqueKey())) {
                    ParamsMapEntry paramMapEntry = getParamMapEntry(paramDef);
                    if (paramMapEntry == null) {
                        BLog.e(TAG, "Unable to find param - config: %s key: %d ", paramDef.config, Integer.valueOf(paramDef.key));
                    } else {
                        Object obj = paramDef.value;
                        int i = paramMapEntry.paramType;
                        if (i == 1) {
                            MobileConfigValueUtil mobileConfigValueUtil = this.mValueUtil;
                            long specifier = paramMapEntry.getSpecifier();
                            if (obj == null || obj == JSONObject.NULL) {
                                z = MobileConfigValueUtil.getDefaultBoolean(paramMapEntry.getSpecifier());
                            } else {
                                z = ((Boolean) obj).booleanValue();
                            }
                            mobileConfigValueUtil.setOverride(specifier, z);
                        } else if (i == 2) {
                            if (obj instanceof Integer) {
                                obj = Long.valueOf(((Integer) obj).longValue());
                            }
                            MobileConfigValueUtil mobileConfigValueUtil2 = this.mValueUtil;
                            long specifier2 = paramMapEntry.getSpecifier();
                            if (obj == null || obj == JSONObject.NULL) {
                                j = MobileConfigValueUtil.getDefaultLong(paramMapEntry.getSpecifier());
                            } else {
                                j = ((Long) obj).longValue();
                            }
                            mobileConfigValueUtil2.setOverride(specifier2, j);
                        } else if (i == 3) {
                            MobileConfigValueUtil mobileConfigValueUtil3 = this.mValueUtil;
                            long specifier3 = paramMapEntry.getSpecifier();
                            if (obj == null || obj == JSONObject.NULL) {
                                str = MobileConfigValueUtil.getDefaultString(paramMapEntry.getSpecifier());
                            } else {
                                str = (String) obj;
                            }
                            mobileConfigValueUtil3.setOverride(specifier3, str);
                        } else if (i == 4) {
                            if (obj instanceof Integer) {
                                obj = Double.valueOf(((Integer) obj).doubleValue());
                            }
                            MobileConfigValueUtil mobileConfigValueUtil4 = this.mValueUtil;
                            long specifier4 = paramMapEntry.getSpecifier();
                            if (obj == null || obj == JSONObject.NULL) {
                                d = MobileConfigValueUtil.getDefaultDouble(paramMapEntry.getSpecifier());
                            } else {
                                d = ((Double) obj).doubleValue();
                            }
                            mobileConfigValueUtil4.setOverride(specifier4, d);
                        }
                        hashSet.add(paramDef.getUniqueKey());
                    }
                }
            }
            this.mValueUtil.setQEOverride(universeDef.name, "", "");
        } else {
            throw new IllegalArgumentException("No universe params");
        }
    }
}
