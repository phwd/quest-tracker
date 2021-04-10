package android.icu.util;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.number.Padder;
import android.support.v4.os.EnvironmentCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Region implements Comparable<Region> {
    private static final String OUTLYING_OCEANIA_REGION_ID = "QO";
    private static final String UNKNOWN_REGION_ID = "ZZ";
    private static final String WORLD_ID = "001";
    private static ArrayList<Set<Region>> availableRegions = null;
    private static Map<Integer, Region> numericCodeMap = null;
    private static Map<String, Region> regionAliases = null;
    private static boolean regionDataIsLoaded = false;
    private static Map<String, Region> regionIDMap = null;
    private static ArrayList<Region> regions = null;
    private int code;
    private Set<Region> containedRegions = new TreeSet();
    private Region containingRegion = null;
    private String id;
    private List<Region> preferredValues = null;
    private RegionType type;

    public enum RegionType {
        UNKNOWN,
        TERRITORY,
        WORLD,
        CONTINENT,
        SUBCONTINENT,
        GROUPING,
        DEPRECATED
    }

    private Region() {
    }

    /* JADX INFO: Multiple debug info for r4v2 java.util.List<java.lang.String>: [D('idValidity' android.icu.util.UResourceBundle), D('allRegions' java.util.List<java.lang.String>)] */
    /* JADX INFO: Multiple debug info for r6v3 java.util.List<java.lang.String>: [D('regionRegular' android.icu.util.UResourceBundle), D('regionCodes' java.util.List<java.lang.String>)] */
    /* JADX INFO: Multiple debug info for r6v21 'regionCodes'  java.util.List<java.lang.String>: [D('regionRegular' android.icu.util.UResourceBundle), D('regionCodes' java.util.List<java.lang.String>)] */
    private static synchronized void loadRegionData() {
        UResourceBundle mapping;
        UResourceBundle codeMappings;
        List<String> regionCodes;
        UResourceBundle territoryAlias;
        Region r;
        Region r2;
        UResourceBundle regionMacro;
        UResourceBundle regionRegular;
        UResourceBundle regionList;
        UResourceBundle regionUnknown;
        List<String> regionCodes2;
        synchronized (Region.class) {
            if (!regionDataIsLoaded) {
                regionAliases = new HashMap();
                regionIDMap = new HashMap();
                numericCodeMap = new HashMap();
                availableRegions = new ArrayList<>(RegionType.values().length);
                UResourceBundle territoryAlias2 = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "metadata", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("alias").get("territory");
                UResourceBundle supplementalData = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
                UResourceBundle codeMappings2 = supplementalData.get("codeMappings");
                UResourceBundle regionList2 = supplementalData.get("idValidity").get("region");
                UResourceBundle regionRegular2 = regionList2.get("regular");
                UResourceBundle regionMacro2 = regionList2.get("macroregion");
                UResourceBundle regionUnknown2 = regionList2.get(EnvironmentCompat.MEDIA_UNKNOWN);
                UResourceBundle territoryContainment = supplementalData.get("territoryContainment");
                UResourceBundle worldContainment = territoryContainment.get(WORLD_ID);
                UResourceBundle groupingContainment = territoryContainment.get("grouping");
                List<String> continents = Arrays.asList(worldContainment.getStringArray());
                Enumeration<String> groupings = groupingContainment.getKeys();
                List<String> regionCodes3 = new ArrayList<>();
                List<String> allRegions = new ArrayList<>();
                allRegions.addAll(Arrays.asList(regionRegular2.getStringArray()));
                allRegions.addAll(Arrays.asList(regionMacro2.getStringArray()));
                allRegions.add(regionUnknown2.getString());
                Iterator<String> it = allRegions.iterator();
                while (it.hasNext()) {
                    String r3 = it.next();
                    int rangeMarkerLocation = r3.indexOf("~");
                    if (rangeMarkerLocation > 0) {
                        regionList = regionList2;
                        StringBuilder regionName = new StringBuilder(r3);
                        regionRegular = regionRegular2;
                        regionName.setLength(rangeMarkerLocation);
                        regionMacro = regionMacro2;
                        char lastChar = regionName.charAt(rangeMarkerLocation - 1);
                        for (char endRange = regionName.charAt(rangeMarkerLocation + 1); lastChar <= endRange; endRange = endRange) {
                            regionCodes3.add(regionName.toString());
                            lastChar = (char) (lastChar + 1);
                            regionName.setCharAt(rangeMarkerLocation - 1, lastChar);
                            regionUnknown2 = regionUnknown2;
                            regionCodes3 = regionCodes3;
                        }
                        regionCodes2 = regionCodes3;
                        regionUnknown = regionUnknown2;
                    } else {
                        regionList = regionList2;
                        regionRegular = regionRegular2;
                        regionMacro = regionMacro2;
                        regionCodes2 = regionCodes3;
                        regionUnknown = regionUnknown2;
                        regionCodes2.add(r3);
                    }
                    regionUnknown2 = regionUnknown;
                    it = it;
                    regionList2 = regionList;
                    allRegions = allRegions;
                    regionMacro2 = regionMacro;
                    regionCodes3 = regionCodes2;
                    regionRegular2 = regionRegular;
                }
                List<String> regionCodes4 = regionCodes3;
                regions = new ArrayList<>(regionCodes4.size());
                for (String id2 : regionCodes4) {
                    Region r4 = new Region();
                    r4.id = id2;
                    r4.type = RegionType.TERRITORY;
                    regionIDMap.put(id2, r4);
                    if (id2.matches("[0-9]{3}")) {
                        r4.code = Integer.valueOf(id2).intValue();
                        numericCodeMap.put(Integer.valueOf(r4.code), r4);
                        r4.type = RegionType.SUBCONTINENT;
                    } else {
                        r4.code = -1;
                    }
                    regions.add(r4);
                }
                int i = 0;
                while (i < territoryAlias2.getSize()) {
                    UResourceBundle res = territoryAlias2.get(i);
                    String aliasFrom = res.getKey();
                    String aliasTo = res.get("replacement").getString();
                    if (!regionIDMap.containsKey(aliasTo) || regionIDMap.containsKey(aliasFrom)) {
                        territoryAlias = territoryAlias2;
                        if (regionIDMap.containsKey(aliasFrom)) {
                            r = regionIDMap.get(aliasFrom);
                            regionCodes = regionCodes4;
                        } else {
                            r = new Region();
                            r.id = aliasFrom;
                            regionIDMap.put(aliasFrom, r);
                            if (aliasFrom.matches("[0-9]{3}")) {
                                r.code = Integer.valueOf(aliasFrom).intValue();
                                regionCodes = regionCodes4;
                                numericCodeMap.put(Integer.valueOf(r.code), r);
                            } else {
                                regionCodes = regionCodes4;
                                r.code = -1;
                            }
                            regions.add(r);
                        }
                        r.type = RegionType.DEPRECATED;
                        List<String> aliasToRegionStrings = Arrays.asList(aliasTo.split(Padder.FALLBACK_PADDING_STRING));
                        r.preferredValues = new ArrayList();
                        for (Iterator<String> it2 = aliasToRegionStrings.iterator(); it2.hasNext(); it2 = it2) {
                            String s = it2.next();
                            if (regionIDMap.containsKey(s)) {
                                r2 = r;
                                r.preferredValues.add(regionIDMap.get(s));
                            } else {
                                r2 = r;
                            }
                            aliasTo = aliasTo;
                            r = r2;
                        }
                    } else {
                        territoryAlias = territoryAlias2;
                        regionAliases.put(aliasFrom, regionIDMap.get(aliasTo));
                        regionCodes = regionCodes4;
                    }
                    i++;
                    territoryAlias2 = territoryAlias;
                    regionCodes4 = regionCodes;
                }
                int i2 = 0;
                while (i2 < codeMappings2.getSize()) {
                    UResourceBundle mapping2 = codeMappings2.get(i2);
                    if (mapping2.getType() == 8) {
                        String[] codeMappingStrings = mapping2.getStringArray();
                        String codeMappingID = codeMappingStrings[0];
                        Integer codeMappingNumber = Integer.valueOf(codeMappingStrings[1]);
                        String codeMapping3Letter = codeMappingStrings[2];
                        if (regionIDMap.containsKey(codeMappingID)) {
                            Region r5 = regionIDMap.get(codeMappingID);
                            r5.code = codeMappingNumber.intValue();
                            codeMappings = codeMappings2;
                            numericCodeMap.put(Integer.valueOf(r5.code), r5);
                            regionAliases.put(codeMapping3Letter, r5);
                        } else {
                            codeMappings = codeMappings2;
                        }
                    } else {
                        codeMappings = codeMappings2;
                    }
                    i2++;
                    codeMappings2 = codeMappings;
                }
                if (regionIDMap.containsKey(WORLD_ID)) {
                    regionIDMap.get(WORLD_ID).type = RegionType.WORLD;
                }
                if (regionIDMap.containsKey(UNKNOWN_REGION_ID)) {
                    regionIDMap.get(UNKNOWN_REGION_ID).type = RegionType.UNKNOWN;
                }
                for (String continent : continents) {
                    if (regionIDMap.containsKey(continent)) {
                        regionIDMap.get(continent).type = RegionType.CONTINENT;
                    }
                }
                while (groupings.hasMoreElements()) {
                    String grouping = groupings.nextElement();
                    if (regionIDMap.containsKey(grouping)) {
                        regionIDMap.get(grouping).type = RegionType.GROUPING;
                    }
                }
                if (regionIDMap.containsKey(OUTLYING_OCEANIA_REGION_ID)) {
                    regionIDMap.get(OUTLYING_OCEANIA_REGION_ID).type = RegionType.SUBCONTINENT;
                }
                for (int i3 = 0; i3 < territoryContainment.getSize(); i3++) {
                    UResourceBundle mapping3 = territoryContainment.get(i3);
                    String parent = mapping3.getKey();
                    if (!parent.equals("containedGroupings") && !parent.equals("deprecated")) {
                        if (!parent.equals("grouping")) {
                            Region parentRegion = regionIDMap.get(parent);
                            int j = 0;
                            while (j < mapping3.getSize()) {
                                Region childRegion = regionIDMap.get(mapping3.getString(j));
                                if (parentRegion == null || childRegion == null) {
                                    mapping = mapping3;
                                } else {
                                    parentRegion.containedRegions.add(childRegion);
                                    mapping = mapping3;
                                    if (parentRegion.getType() != RegionType.GROUPING) {
                                        childRegion.containingRegion = parentRegion;
                                    }
                                }
                                j++;
                                mapping3 = mapping;
                            }
                        }
                    }
                }
                for (int i4 = 0; i4 < RegionType.values().length; i4++) {
                    availableRegions.add(new TreeSet());
                }
                Iterator<Region> it3 = regions.iterator();
                while (it3.hasNext()) {
                    Region ar = it3.next();
                    Set<Region> currentSet = availableRegions.get(ar.type.ordinal());
                    currentSet.add(ar);
                    availableRegions.set(ar.type.ordinal(), currentSet);
                }
                regionDataIsLoaded = true;
            }
        }
    }

    public static Region getInstance(String id2) {
        if (id2 != null) {
            loadRegionData();
            Region r = regionIDMap.get(id2);
            if (r == null) {
                r = regionAliases.get(id2);
            }
            if (r == null) {
                throw new IllegalArgumentException("Unknown region id: " + id2);
            } else if (r.type == RegionType.DEPRECATED && r.preferredValues.size() == 1) {
                return r.preferredValues.get(0);
            } else {
                return r;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public static Region getInstance(int code2) {
        loadRegionData();
        Region r = numericCodeMap.get(Integer.valueOf(code2));
        if (r == null) {
            String pad = "";
            if (code2 < 10) {
                pad = "00";
            } else if (code2 < 100) {
                pad = AndroidHardcodedSystemProperties.JAVA_VERSION;
            }
            r = regionAliases.get(pad + Integer.toString(code2));
        }
        if (r == null) {
            throw new IllegalArgumentException("Unknown region code: " + code2);
        } else if (r.type == RegionType.DEPRECATED && r.preferredValues.size() == 1) {
            return r.preferredValues.get(0);
        } else {
            return r;
        }
    }

    public static Set<Region> getAvailable(RegionType type2) {
        loadRegionData();
        return Collections.unmodifiableSet(availableRegions.get(type2.ordinal()));
    }

    public Region getContainingRegion() {
        loadRegionData();
        return this.containingRegion;
    }

    public Region getContainingRegion(RegionType type2) {
        loadRegionData();
        Region region = this.containingRegion;
        if (region == null) {
            return null;
        }
        if (region.type.equals(type2)) {
            return this.containingRegion;
        }
        return this.containingRegion.getContainingRegion(type2);
    }

    public Set<Region> getContainedRegions() {
        loadRegionData();
        return Collections.unmodifiableSet(this.containedRegions);
    }

    public Set<Region> getContainedRegions(RegionType type2) {
        loadRegionData();
        Set<Region> result = new TreeSet<>();
        for (Region r : getContainedRegions()) {
            if (r.getType() == type2) {
                result.add(r);
            } else {
                result.addAll(r.getContainedRegions(type2));
            }
        }
        return Collections.unmodifiableSet(result);
    }

    public List<Region> getPreferredValues() {
        loadRegionData();
        if (this.type == RegionType.DEPRECATED) {
            return Collections.unmodifiableList(this.preferredValues);
        }
        return null;
    }

    public boolean contains(Region other) {
        loadRegionData();
        if (this.containedRegions.contains(other)) {
            return true;
        }
        for (Region cr : this.containedRegions) {
            if (cr.contains(other)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.id;
    }

    public int getNumericCode() {
        return this.code;
    }

    public RegionType getType() {
        return this.type;
    }

    public int compareTo(Region other) {
        return this.id.compareTo(other.id);
    }
}
