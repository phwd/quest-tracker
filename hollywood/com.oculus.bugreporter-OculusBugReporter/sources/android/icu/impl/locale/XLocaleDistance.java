package android.icu.impl.locale;

import android.icu.impl.ICUResourceBundle;
import android.icu.impl.Row;
import android.icu.impl.locale.XCldrStub;
import android.icu.impl.locale.XLikelySubtags;
import android.icu.text.LocaleDisplayNames;
import android.icu.text.PluralRules;
import android.icu.util.LocaleMatcher;
import android.icu.util.Output;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundleIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class XLocaleDistance {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ABOVE_THRESHOLD = 100;
    private static final Set<String> ALL_FINAL_REGIONS = XCldrStub.ImmutableSet.copyOf(CONTAINER_TO_CONTAINED_FINAL.get("001"));
    @Deprecated
    public static final String ANY = "�";
    static final XCldrStub.Multimap<String, String> CONTAINER_TO_CONTAINED = xGetContainment();
    static final XCldrStub.Multimap<String, String> CONTAINER_TO_CONTAINED_FINAL;
    private static final XLocaleDistance DEFAULT;
    static final boolean PRINT_OVERRIDES = false;
    private static final boolean TRACE_DISTANCE = false;
    static final LocaleDisplayNames english = LocaleDisplayNames.getInstance(ULocale.ENGLISH);
    private final int defaultLanguageDistance;
    private final int defaultRegionDistance;
    private final int defaultScriptDistance;
    private final DistanceTable languageDesired2Supported;
    private final RegionMapper regionMapper;

    public enum DistanceOption {
        REGION_FIRST,
        SCRIPT_FIRST
    }

    private interface IdMapper<K, V> {
        V toId(K k);
    }

    static {
        XCldrStub.Multimap<String, String> containerToFinalContainedBuilder = XCldrStub.TreeMultimap.create();
        for (Map.Entry<String, Set<String>> entry : CONTAINER_TO_CONTAINED.asMap().entrySet()) {
            String container = entry.getKey();
            for (String contained : entry.getValue()) {
                if (CONTAINER_TO_CONTAINED.get(contained) == null) {
                    containerToFinalContainedBuilder.put(container, contained);
                }
            }
        }
        CONTAINER_TO_CONTAINED_FINAL = XCldrStub.ImmutableMultimap.copyOf(containerToFinalContainedBuilder);
        String[][] variableOverrides = {new String[]{"$enUS", "AS+GU+MH+MP+PR+UM+US+VI"}, new String[]{"$cnsar", "HK+MO"}, new String[]{"$americas", "019"}, new String[]{"$maghreb", "MA+DZ+TN+LY+MR+EH"}};
        String[] paradigmRegions = {"en", "en-GB", "es", "es-419", "pt-BR", "pt-PT"};
        String[][] regionRuleOverrides = {new String[]{"ar_*_$maghreb", "ar_*_$maghreb", "96"}, new String[]{"ar_*_$!maghreb", "ar_*_$!maghreb", "96"}, new String[]{"ar_*_*", "ar_*_*", "95"}, new String[]{"en_*_$enUS", "en_*_$enUS", "96"}, new String[]{"en_*_$!enUS", "en_*_$!enUS", "96"}, new String[]{"en_*_*", "en_*_*", "95"}, new String[]{"es_*_$americas", "es_*_$americas", "96"}, new String[]{"es_*_$!americas", "es_*_$!americas", "96"}, new String[]{"es_*_*", "es_*_*", "95"}, new String[]{"pt_*_$americas", "pt_*_$americas", "96"}, new String[]{"pt_*_$!americas", "pt_*_$!americas", "96"}, new String[]{"pt_*_*", "pt_*_*", "95"}, new String[]{"zh_Hant_$cnsar", "zh_Hant_$cnsar", "96"}, new String[]{"zh_Hant_$!cnsar", "zh_Hant_$!cnsar", "96"}, new String[]{"zh_Hant_*", "zh_Hant_*", "95"}, new String[]{"*_*_*", "*_*_*", "96"}};
        RegionMapper.Builder rmb = new RegionMapper.Builder().addParadigms(paradigmRegions);
        for (String[] variableRule : variableOverrides) {
            rmb.add(variableRule[0], variableRule[1]);
        }
        StringDistanceTable defaultDistanceTable = new StringDistanceTable();
        RegionMapper defaultRegionMapper = rmb.build();
        XCldrStub.Splitter bar = XCldrStub.Splitter.on('_');
        List<Row.R4<List<String>, List<String>, Integer, Boolean>>[] sorted = {new ArrayList<>(), new ArrayList<>(), new ArrayList<>()};
        for (Row.R4<String, String, Integer, Boolean> info : xGetLanguageMatcherData()) {
            String desiredRaw = info.get0();
            List<String> desired = bar.splitToList(desiredRaw);
            List<String> supported = bar.splitToList(info.get1());
            Boolean oneway = info.get3();
            int distance = desiredRaw.equals("*_*") ? 50 : info.get2().intValue();
            int size = desired.size();
            if (size == 3) {
                variableOverrides = variableOverrides;
                paradigmRegions = paradigmRegions;
            } else {
                sorted[size - 1].add(Row.of(desired, supported, Integer.valueOf(distance), oneway));
                variableOverrides = variableOverrides;
                paradigmRegions = paradigmRegions;
            }
        }
        for (List<Row.R4<List<String>, List<String>, Integer, Boolean>> item1 : sorted) {
            for (Row.R4<List<String>, List<String>, Integer, Boolean> item2 : item1) {
                List<String> desired2 = item2.get0();
                List<String> supported2 = item2.get1();
                Integer distance2 = item2.get2();
                Boolean oneway2 = item2.get3();
                add(defaultDistanceTable, desired2, supported2, distance2.intValue());
                if (oneway2 != Boolean.TRUE && !desired2.equals(supported2)) {
                    add(defaultDistanceTable, supported2, desired2, distance2.intValue());
                }
                printMatchXml(desired2, supported2, distance2, oneway2);
            }
        }
        int length = regionRuleOverrides.length;
        for (int i = 0; i < length; i++) {
            String[] rule = regionRuleOverrides[i];
            List<String> desiredBase = new ArrayList<>(bar.splitToList(rule[0]));
            List<String> supportedBase = new ArrayList<>(bar.splitToList(rule[1]));
            Integer distance3 = Integer.valueOf(100 - Integer.parseInt(rule[2]));
            printMatchXml(desiredBase, supportedBase, distance3, false);
            Collection<String> desiredRegions = defaultRegionMapper.getIdsFromVariable(desiredBase.get(2));
            if (!desiredRegions.isEmpty()) {
                Collection<String> supportedRegions = defaultRegionMapper.getIdsFromVariable(supportedBase.get(2));
                if (!supportedRegions.isEmpty()) {
                    for (String desiredRegion2 : desiredRegions) {
                        desiredBase.set(2, desiredRegion2.toString());
                        for (Iterator<String> it = supportedRegions.iterator(); it.hasNext(); it = it) {
                            supportedBase.set(2, it.next().toString());
                            add(defaultDistanceTable, desiredBase, supportedBase, distance3.intValue());
                            add(defaultDistanceTable, supportedBase, desiredBase, distance3.intValue());
                        }
                        regionRuleOverrides = regionRuleOverrides;
                        length = length;
                    }
                } else {
                    throw new IllegalArgumentException("Bad region variable: " + supportedBase.get(2));
                }
            } else {
                throw new IllegalArgumentException("Bad region variable: " + desiredBase.get(2));
            }
        }
        List<String> supported3 = Arrays.asList("*", "*", "*");
        Iterator it2 = Arrays.asList("XA", "XB", "XC").iterator();
        while (it2.hasNext()) {
            List<String> desired3 = Arrays.asList("*", "*", (String) it2.next());
            add(defaultDistanceTable, desired3, supported3, 100);
            add(defaultDistanceTable, supported3, desired3, 100);
        }
        for (int i2 = 1; i2 <= 8; i2++) {
            List<String> desired4 = Arrays.asList("x" + String.valueOf(i2), "*", "*");
            add(defaultDistanceTable, desired4, supported3, 100);
            add(defaultDistanceTable, supported3, desired4, 100);
        }
        DEFAULT = new XLocaleDistance(defaultDistanceTable.compact(), defaultRegionMapper);
    }

    private static String fixAny(String string) {
        return "*".equals(string) ? ANY : string;
    }

    private static List<Row.R4<String, String, Integer, Boolean>> xGetLanguageMatcherData() {
        List<Row.R4<String, String, Integer, Boolean>> distanceList = new ArrayList<>();
        UResourceBundleIterator iter = ((ICUResourceBundle) LocaleMatcher.getICUSupplementalData().findTopLevel("languageMatchingNew").get("written")).getIterator();
        while (iter.hasNext()) {
            ICUResourceBundle item = (ICUResourceBundle) iter.next();
            distanceList.add((Row.R4) Row.of(item.getString(0), item.getString(1), Integer.valueOf(Integer.parseInt(item.getString(2))), Boolean.valueOf(item.getSize() > 3 && "1".equals(item.getString(3)))).freeze());
        }
        return Collections.unmodifiableList(distanceList);
    }

    private static Set<String> xGetParadigmLocales() {
        return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(((ICUResourceBundle) LocaleMatcher.getICUSupplementalData().findTopLevel("languageMatchingInfo").get("written").get("paradigmLocales")).getStringArray())));
    }

    private static Map<String, String> xGetMatchVariables() {
        ICUResourceBundle writtenMatchVariables = (ICUResourceBundle) LocaleMatcher.getICUSupplementalData().findTopLevel("languageMatchingInfo").get("written").get("matchVariable");
        HashMap<String, String> matchVariables = new HashMap<>();
        Enumeration<String> enumer = writtenMatchVariables.getKeys();
        while (enumer.hasMoreElements()) {
            String key = enumer.nextElement();
            matchVariables.put(key, writtenMatchVariables.getString(key));
        }
        return Collections.unmodifiableMap(matchVariables);
    }

    private static XCldrStub.Multimap<String, String> xGetContainment() {
        XCldrStub.TreeMultimap<String, String> containment = XCldrStub.TreeMultimap.create();
        containment.putAll("001", "019", "002", "150", "142", "009").putAll("011", "BF", "BJ", "CI", "CV", "GH", "GM", "GN", "GW", "LR", "ML", "MR", "NE", "NG", "SH", "SL", "SN", "TG").putAll("013", "BZ", "CR", "GT", "HN", "MX", "NI", "PA", "SV").putAll("014", "BI", "DJ", "ER", "ET", "KE", "KM", "MG", "MU", "MW", "MZ", "RE", "RW", "SC", "SO", "SS", "TZ", "UG", "YT", "ZM", "ZW").putAll("142", "145", "143", "030", "034", "035").putAll("143", "TM", "TJ", "KG", "KZ", "UZ").putAll("145", "AE", "AM", "AZ", "BH", "CY", "GE", "IL", "IQ", "JO", "KW", "LB", "OM", "PS", "QA", "SA", "SY", "TR", "YE", "NT", "YD").putAll("015", "DZ", "EG", "EH", "LY", "MA", "SD", "TN", "EA", "IC").putAll("150", "154", "155", "151", "039").putAll("151", "BG", "BY", "CZ", "HU", "MD", "PL", "RO", "RU", "SK", "UA", "SU").putAll("154", "GG", "IM", "JE", "AX", "DK", "EE", "FI", "FO", "GB", "IE", "IS", "LT", "LV", "NO", "SE", "SJ").putAll("155", "AT", "BE", "CH", "DE", "FR", "LI", "LU", "MC", "NL", "DD", "FX").putAll("017", "AO", "CD", "CF", "CG", "CM", "GA", "GQ", "ST", "TD", "ZR").putAll("018", "BW", "LS", "NA", "SZ", "ZA").putAll("019", "021", "013", "029", "005", "003", "419").putAll("002", "015", "011", "017", "014", "018").putAll("021", "BM", "CA", "GL", "PM", "US").putAll("029", "AG", "AI", "AW", "BB", "BL", "BQ", "BS", "CU", "CW", "DM", "DO", "GD", "GP", "HT", "JM", "KN", "KY", "LC", "MF", "MQ", "MS", "PR", "SX", "TC", "TT", "VC", "VG", "VI", "AN").putAll("003", "021", "013", "029").putAll("030", "CN", "HK", "JP", "KP", "KR", "MN", "MO", "TW").putAll("035", "BN", "ID", "KH", "LA", "MM", "MY", "PH", "SG", "TH", "TL", "VN", "BU", "TP").putAll("039", "AD", "AL", "BA", "ES", "GI", "GR", "HR", "IT", "ME", "MK", "MT", "RS", "PT", "SI", "SM", "VA", "XK", "CS", "YU").putAll("419", "013", "029", "005").putAll("005", "AR", "BO", "BR", "CL", "CO", "EC", "FK", "GF", "GY", "PE", "PY", "SR", "UY", "VE").putAll("053", "AU", "NF", "NZ").putAll("054", "FJ", "NC", "PG", "SB", "VU").putAll("057", "FM", "GU", "KI", "MH", "MP", "NR", "PW").putAll("061", "AS", "CK", "NU", "PF", "PN", "TK", "TO", "TV", "WF", "WS").putAll("034", "AF", "BD", "BT", "IN", "IR", "LK", "MV", "NP", "PK").putAll("009", "053", "054", "057", "061", "QO").putAll("QO", "AQ", "BV", "CC", "CX", "GS", "HM", "IO", "TF", "UM", "AC", "CP", "DG", "TA");
        XCldrStub.TreeMultimap<String, String> containmentResolved = XCldrStub.TreeMultimap.create();
        fill("001", containment, containmentResolved);
        return XCldrStub.ImmutableMultimap.copyOf(containmentResolved);
    }

    private static Set<String> fill(String region, XCldrStub.TreeMultimap<String, String> containment, XCldrStub.Multimap<String, String> toAddTo) {
        Set<String> contained = containment.get(region);
        if (contained == null) {
            return Collections.emptySet();
        }
        toAddTo.putAll(region, contained);
        for (String subregion : contained) {
            toAddTo.putAll(region, fill(subregion, containment, toAddTo));
        }
        return toAddTo.get(region);
    }

    @Deprecated
    public static abstract class DistanceTable {
        /* access modifiers changed from: package-private */
        public abstract Set<String> getCloser(int i);

        /* access modifiers changed from: package-private */
        public abstract int getDistance(String str, String str2, Output<DistanceTable> output, boolean z);

        /* access modifiers changed from: package-private */
        public abstract String toString(boolean z);

        public DistanceTable compact() {
            return this;
        }

        public DistanceNode getInternalNode(String any, String any2) {
            return null;
        }

        public Map<String, Set<String>> getInternalMatches() {
            return null;
        }

        public boolean isEmpty() {
            return true;
        }
    }

    @Deprecated
    public static class DistanceNode {
        final int distance;

        public DistanceNode(int distance2) {
            this.distance = distance2;
        }

        public DistanceTable getDistanceTable() {
            return null;
        }

        public boolean equals(Object obj) {
            return this == obj || (obj != null && obj.getClass() == getClass() && this.distance == ((DistanceNode) obj).distance);
        }

        public int hashCode() {
            return this.distance;
        }

        public String toString() {
            return "\ndistance: " + this.distance;
        }
    }

    /* access modifiers changed from: package-private */
    public static class IdMakerFull<T> implements IdMapper<T, Integer> {
        private final List<T> intToObject;
        final String name;
        private final Map<T, Integer> objectToInt;

        IdMakerFull(String name2) {
            this.objectToInt = new HashMap();
            this.intToObject = new ArrayList();
            this.name = name2;
        }

        IdMakerFull() {
            this("unnamed");
        }

        IdMakerFull(String name2, T zeroValue) {
            this(name2);
            add(zeroValue);
        }

        public Integer add(T source) {
            Integer result = this.objectToInt.get(source);
            if (result != null) {
                return result;
            }
            Integer newResult = Integer.valueOf(this.intToObject.size());
            this.objectToInt.put(source, newResult);
            this.intToObject.add(source);
            return newResult;
        }

        @Override // android.icu.impl.locale.XLocaleDistance.IdMapper
        public Integer toId(T source) {
            return this.objectToInt.get(source);
        }

        public T fromId(int id) {
            return this.intToObject.get(id);
        }

        public T intern(T source) {
            return fromId(add(source).intValue());
        }

        public int size() {
            return this.intToObject.size();
        }

        public Integer getOldAndAdd(T source) {
            Integer result = this.objectToInt.get(source);
            if (result == null) {
                this.objectToInt.put(source, Integer.valueOf(this.intToObject.size()));
                this.intToObject.add(source);
            }
            return result;
        }

        public String toString() {
            return size() + PluralRules.KEYWORD_RULE_SEPARATOR + ((Object) this.intToObject);
        }

        public boolean equals(Object obj) {
            return this == obj || (obj != null && obj.getClass() == getClass() && this.intToObject.equals(((IdMakerFull) obj).intToObject));
        }

        public int hashCode() {
            return this.intToObject.hashCode();
        }
    }

    /* access modifiers changed from: package-private */
    public static class StringDistanceNode extends DistanceNode {
        final DistanceTable distanceTable;

        public StringDistanceNode(int distance, DistanceTable distanceTable2) {
            super(distance);
            this.distanceTable = distanceTable2;
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceNode
        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj != null && obj.getClass() == getClass()) {
                    StringDistanceNode other = (StringDistanceNode) obj;
                    if (this.distance != other.distance || !Objects.equals(this.distanceTable, other.distanceTable) || !super.equals(other)) {
                        return false;
                    }
                }
                return false;
            }
            return true;
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceNode
        public int hashCode() {
            return this.distance ^ Objects.hashCode(this.distanceTable);
        }

        StringDistanceNode(int distance) {
            this(distance, new StringDistanceTable());
        }

        public void addSubtables(String desiredSub, String supportedSub, CopyIfEmpty r) {
            ((StringDistanceTable) this.distanceTable).addSubtables(desiredSub, supportedSub, r);
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceNode
        public String toString() {
            return "distance: " + this.distance + "\n" + ((Object) this.distanceTable);
        }

        public void copyTables(StringDistanceTable value) {
            if (value != null) {
                ((StringDistanceTable) this.distanceTable).copy(value);
            }
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceNode
        public DistanceTable getDistanceTable() {
            return this.distanceTable;
        }
    }

    public XLocaleDistance(DistanceTable datadistancetable2, RegionMapper regionMapper2) {
        this.languageDesired2Supported = datadistancetable2;
        this.regionMapper = regionMapper2;
        StringDistanceNode languageNode = (StringDistanceNode) ((StringDistanceTable) this.languageDesired2Supported).subtables.get(ANY).get(ANY);
        this.defaultLanguageDistance = languageNode.distance;
        StringDistanceNode scriptNode = (StringDistanceNode) ((StringDistanceTable) languageNode.distanceTable).subtables.get(ANY).get(ANY);
        this.defaultScriptDistance = scriptNode.distance;
        this.defaultRegionDistance = ((StringDistanceTable) scriptNode.distanceTable).subtables.get(ANY).get(ANY).distance;
    }

    /* access modifiers changed from: private */
    public static Map newMap() {
        return new TreeMap();
    }

    @Deprecated
    public static class StringDistanceTable extends DistanceTable {
        final Map<String, Map<String, DistanceNode>> subtables;

        StringDistanceTable(Map<String, Map<String, DistanceNode>> tables) {
            this.subtables = tables;
        }

        StringDistanceTable() {
            this(XLocaleDistance.newMap());
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceTable
        public boolean isEmpty() {
            return this.subtables.isEmpty();
        }

        public boolean equals(Object obj) {
            return this == obj || (obj != null && obj.getClass() == getClass() && this.subtables.equals(((StringDistanceTable) obj).subtables));
        }

        public int hashCode() {
            return this.subtables.hashCode();
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceTable
        public int getDistance(String desired, String supported, Output<DistanceTable> distanceTable, boolean starEquals) {
            Map<String, DistanceNode> sub2;
            boolean star = false;
            Map<String, DistanceNode> sub22 = this.subtables.get(desired);
            if (sub22 == null) {
                sub22 = this.subtables.get(XLocaleDistance.ANY);
                star = true;
            }
            DistanceNode value = sub22.get(supported);
            if (value == null) {
                value = sub22.get(XLocaleDistance.ANY);
                if (value == null && !star && (value = (sub2 = this.subtables.get(XLocaleDistance.ANY)).get(supported)) == null) {
                    value = sub2.get(XLocaleDistance.ANY);
                }
                star = true;
            }
            if (distanceTable != null) {
                distanceTable.value = (T) ((StringDistanceNode) value).distanceTable;
            }
            if (!starEquals || !star || !desired.equals(supported)) {
                return value.distance;
            }
            return 0;
        }

        public void copy(StringDistanceTable other) {
            for (Map.Entry<String, Map<String, DistanceNode>> e1 : other.subtables.entrySet()) {
                for (Map.Entry<String, DistanceNode> e2 : e1.getValue().entrySet()) {
                    addSubtable(e1.getKey(), e2.getKey(), e2.getValue().distance);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public DistanceNode addSubtable(String desired, String supported, int distance) {
            Map<String, DistanceNode> sub2 = this.subtables.get(desired);
            if (sub2 == null) {
                Map<String, Map<String, DistanceNode>> map = this.subtables;
                Map<String, DistanceNode> newMap = XLocaleDistance.newMap();
                sub2 = newMap;
                map.put(desired, newMap);
            }
            DistanceNode oldNode = sub2.get(supported);
            if (oldNode != null) {
                return oldNode;
            }
            StringDistanceNode newNode = new StringDistanceNode(distance);
            sub2.put(supported, newNode);
            return newNode;
        }

        private DistanceNode getNode(String desired, String supported) {
            Map<String, DistanceNode> sub2 = this.subtables.get(desired);
            if (sub2 == null) {
                return null;
            }
            return sub2.get(supported);
        }

        public void addSubtables(String desired, String supported, XCldrStub.Predicate<DistanceNode> action) {
            DistanceNode node = getNode(desired, supported);
            if (node == null) {
                Output<DistanceTable> node2 = new Output<>();
                node = addSubtable(desired, supported, getDistance(desired, supported, node2, true));
                if (node2.value != null) {
                    ((StringDistanceNode) node).copyTables(node2.value);
                }
            }
            action.test(node);
        }

        public void addSubtables(String desiredLang, String supportedLang, String desiredScript, String supportedScript, int percentage) {
            boolean haveKeys;
            boolean haveKeys2 = false;
            for (Map.Entry<String, Map<String, DistanceNode>> e1 : this.subtables.entrySet()) {
                boolean desiredIsKey = desiredLang.equals(e1.getKey());
                if (desiredIsKey || desiredLang.equals(XLocaleDistance.ANY)) {
                    for (Map.Entry<String, DistanceNode> e2 : e1.getValue().entrySet()) {
                        boolean supportedIsKey = supportedLang.equals(e2.getKey());
                        boolean haveKeys3 = haveKeys2 | (desiredIsKey && supportedIsKey);
                        if (supportedIsKey || supportedLang.equals(XLocaleDistance.ANY)) {
                            haveKeys = haveKeys3;
                            ((StringDistanceTable) e2.getValue().getDistanceTable()).addSubtable(desiredScript, supportedScript, percentage);
                        } else {
                            haveKeys = haveKeys3;
                        }
                        haveKeys2 = haveKeys;
                    }
                }
            }
            StringDistanceTable dt = new StringDistanceTable();
            dt.addSubtable(desiredScript, supportedScript, percentage);
            addSubtables(desiredLang, supportedLang, new CopyIfEmpty(dt));
        }

        public void addSubtables(String desiredLang, String supportedLang, String desiredScript, String supportedScript, String desiredRegion, String supportedRegion, int percentage) {
            boolean haveKeys = false;
            for (Map.Entry<String, Map<String, DistanceNode>> e1 : this.subtables.entrySet()) {
                boolean desiredIsKey = desiredLang.equals(e1.getKey());
                if (desiredIsKey || desiredLang.equals(XLocaleDistance.ANY)) {
                    for (Map.Entry<String, DistanceNode> e2 : e1.getValue().entrySet()) {
                        boolean supportedIsKey = supportedLang.equals(e2.getKey());
                        haveKeys |= desiredIsKey && supportedIsKey;
                        if (supportedIsKey || supportedLang.equals(XLocaleDistance.ANY)) {
                            ((StringDistanceTable) ((StringDistanceNode) e2.getValue()).distanceTable).addSubtables(desiredScript, supportedScript, desiredRegion, supportedRegion, percentage);
                        }
                    }
                }
            }
            StringDistanceTable dt = new StringDistanceTable();
            dt.addSubtable(desiredRegion, supportedRegion, percentage);
            addSubtables(desiredLang, supportedLang, new AddSub(desiredScript, supportedScript, dt));
        }

        public String toString() {
            return toString(false);
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceTable
        public String toString(boolean abbreviate) {
            return toString(abbreviate, "", new IdMakerFull<>("interner"), new StringBuilder()).toString();
        }

        public StringBuilder toString(boolean abbreviate, String indent, IdMakerFull<Object> intern, StringBuilder buffer) {
            String indent2;
            char c;
            String indent22 = indent.isEmpty() ? "" : "\t";
            Integer id = abbreviate ? intern.getOldAndAdd(this.subtables) : null;
            char c2 = '#';
            char c3 = '\n';
            if (id != null) {
                buffer.append(indent22);
                buffer.append('#');
                buffer.append((Object) id);
                buffer.append('\n');
            } else {
                for (Map.Entry<String, Map<String, DistanceNode>> e1 : this.subtables.entrySet()) {
                    Map<String, DistanceNode> subsubtable = e1.getValue();
                    buffer.append(indent22);
                    buffer.append(e1.getKey());
                    String indent3 = "\t";
                    Integer id2 = abbreviate ? intern.getOldAndAdd(subsubtable) : null;
                    if (id2 != null) {
                        buffer.append(indent3);
                        buffer.append(c2);
                        buffer.append((Object) id2);
                        buffer.append(c3);
                    } else {
                        for (Map.Entry<String, DistanceNode> e2 : subsubtable.entrySet()) {
                            DistanceNode value = e2.getValue();
                            buffer.append(indent3);
                            buffer.append(e2.getKey());
                            Integer id3 = abbreviate ? intern.getOldAndAdd(value) : null;
                            if (id3 != null) {
                                buffer.append('\t');
                                buffer.append(c2);
                                buffer.append((Object) id3);
                                buffer.append('\n');
                                indent2 = indent22;
                                c = '\n';
                            } else {
                                buffer.append('\t');
                                buffer.append(value.distance);
                                DistanceTable distanceTable = value.getDistanceTable();
                                if (distanceTable != null) {
                                    Integer id4 = abbreviate ? intern.getOldAndAdd(distanceTable) : null;
                                    if (id4 != null) {
                                        buffer.append('\t');
                                        buffer.append('#');
                                        buffer.append((Object) id4);
                                        buffer.append('\n');
                                        indent2 = indent22;
                                        c = '\n';
                                    } else {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(indent);
                                        indent2 = indent22;
                                        sb.append("\t\t\t");
                                        ((StringDistanceTable) distanceTable).toString(abbreviate, sb.toString(), intern, buffer);
                                        c = '\n';
                                        buffer.append('\n');
                                    }
                                } else {
                                    indent2 = indent22;
                                    c = '\n';
                                    buffer.append('\n');
                                }
                            }
                            indent3 = indent + '\t';
                            c2 = '#';
                            c3 = c;
                            indent22 = indent2;
                        }
                    }
                    indent22 = indent;
                    c2 = '#';
                    c3 = c3;
                }
            }
            return buffer;
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceTable
        public StringDistanceTable compact() {
            return new CompactAndImmutablizer().compact(this);
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceTable
        public Set<String> getCloser(int threshold) {
            Set<String> result = new HashSet<>();
            for (Map.Entry<String, Map<String, DistanceNode>> e1 : this.subtables.entrySet()) {
                String desired = e1.getKey();
                Iterator<Map.Entry<String, DistanceNode>> it = e1.getValue().entrySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getValue().distance < threshold) {
                            result.add(desired);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            return result;
        }

        public Integer getInternalDistance(String a, String b) {
            DistanceNode dnode;
            Map<String, DistanceNode> subsub = this.subtables.get(a);
            if (subsub == null || (dnode = subsub.get(b)) == null) {
                return null;
            }
            return Integer.valueOf(dnode.distance);
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceTable
        public DistanceNode getInternalNode(String a, String b) {
            Map<String, DistanceNode> subsub = this.subtables.get(a);
            if (subsub == null) {
                return null;
            }
            return subsub.get(b);
        }

        @Override // android.icu.impl.locale.XLocaleDistance.DistanceTable
        public Map<String, Set<String>> getInternalMatches() {
            Map<String, Set<String>> result = new LinkedHashMap<>();
            for (Map.Entry<String, Map<String, DistanceNode>> entry : this.subtables.entrySet()) {
                result.put(entry.getKey(), new LinkedHashSet<>(entry.getValue().keySet()));
            }
            return result;
        }
    }

    /* access modifiers changed from: package-private */
    public static class CopyIfEmpty implements XCldrStub.Predicate<DistanceNode> {
        private final StringDistanceTable toCopy;

        CopyIfEmpty(StringDistanceTable resetIfNotNull) {
            this.toCopy = resetIfNotNull;
        }

        public boolean test(DistanceNode node) {
            StringDistanceTable subtables = (StringDistanceTable) node.getDistanceTable();
            if (!subtables.subtables.isEmpty()) {
                return true;
            }
            subtables.copy(this.toCopy);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public static class AddSub implements XCldrStub.Predicate<DistanceNode> {
        private final String desiredSub;
        private final CopyIfEmpty r;
        private final String supportedSub;

        AddSub(String desiredSub2, String supportedSub2, StringDistanceTable distanceTableToCopy) {
            this.r = new CopyIfEmpty(distanceTableToCopy);
            this.desiredSub = desiredSub2;
            this.supportedSub = supportedSub2;
        }

        public boolean test(DistanceNode node) {
            if (node != null) {
                ((StringDistanceNode) node).addSubtables(this.desiredSub, this.supportedSub, this.r);
                return true;
            }
            throw new IllegalArgumentException("bad structure");
        }
    }

    public int distance(ULocale desired, ULocale supported, int threshold, DistanceOption distanceOption) {
        return distanceRaw(XLikelySubtags.LSR.fromMaximalized(desired), XLikelySubtags.LSR.fromMaximalized(supported), threshold, distanceOption);
    }

    public int distanceRaw(XLikelySubtags.LSR desired, XLikelySubtags.LSR supported, int threshold, DistanceOption distanceOption) {
        return distanceRaw(desired.language, supported.language, desired.script, supported.script, desired.region, supported.region, threshold, distanceOption);
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ee A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int distanceRaw(java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, java.lang.String r29, int r30, android.icu.impl.locale.XLocaleDistance.DistanceOption r31) {
        /*
        // Method dump skipped, instructions count: 244
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.locale.XLocaleDistance.distanceRaw(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, android.icu.impl.locale.XLocaleDistance$DistanceOption):int");
    }

    public static XLocaleDistance getDefault() {
        return DEFAULT;
    }

    private static void printMatchXml(List<String> list, List<String> list2, Integer distance, Boolean oneway) {
    }

    private static String fixedName(List<String> match) {
        List<String> alt = new ArrayList<>(match);
        int size = alt.size();
        StringBuilder result = new StringBuilder();
        if (size >= 3) {
            String region = alt.get(2);
            if (region.equals("*") || region.startsWith("$")) {
                result.append(region);
            } else {
                result.append(english.regionDisplayName(region));
            }
        }
        if (size >= 2) {
            String script = alt.get(1);
            if (script.equals("*")) {
                result.insert(0, script);
            } else {
                result.insert(0, english.scriptDisplayName(script));
            }
        }
        if (size >= 1) {
            String language = alt.get(0);
            if (language.equals("*")) {
                result.insert(0, language);
            } else {
                result.insert(0, english.languageDisplayName(language));
            }
        }
        return XCldrStub.CollectionUtilities.join(alt, "; ");
    }

    public static void add(StringDistanceTable languageDesired2Supported2, List<String> desired, List<String> supported, int percentage) {
        int size = desired.size();
        if (size != supported.size() || size < 1 || size > 3) {
            throw new IllegalArgumentException();
        }
        String desiredLang = fixAny(desired.get(0));
        String supportedLang = fixAny(supported.get(0));
        if (size == 1) {
            languageDesired2Supported2.addSubtable(desiredLang, supportedLang, percentage);
            return;
        }
        String desiredScript = fixAny(desired.get(1));
        String supportedScript = fixAny(supported.get(1));
        if (size == 2) {
            languageDesired2Supported2.addSubtables(desiredLang, supportedLang, desiredScript, supportedScript, percentage);
        } else {
            languageDesired2Supported2.addSubtables(desiredLang, supportedLang, desiredScript, supportedScript, fixAny(desired.get(2)), fixAny(supported.get(2)), percentage);
        }
    }

    public String toString() {
        return toString(false);
    }

    public String toString(boolean abbreviate) {
        return ((Object) this.regionMapper) + "\n" + this.languageDesired2Supported.toString(abbreviate);
    }

    static Set<String> getContainingMacrosFor(Collection<String> input, Set<String> output) {
        output.clear();
        for (Map.Entry<String, Set<String>> entry : CONTAINER_TO_CONTAINED.asMap().entrySet()) {
            if (input.containsAll(entry.getValue())) {
                output.add(entry.getKey());
            }
        }
        return output;
    }

    /* access modifiers changed from: package-private */
    public static class RegionMapper implements IdMapper<String, String> {
        final XCldrStub.Multimap<String, String> macroToPartitions;
        final Set<ULocale> paradigms;
        final Map<String, String> regionToPartition;
        final XCldrStub.Multimap<String, String> variableToPartition;

        private RegionMapper(XCldrStub.Multimap<String, String> variableToPartitionIn, Map<String, String> regionToPartitionIn, XCldrStub.Multimap<String, String> macroToPartitionsIn, Set<ULocale> paradigmsIn) {
            this.variableToPartition = XCldrStub.ImmutableMultimap.copyOf(variableToPartitionIn);
            this.regionToPartition = XCldrStub.ImmutableMap.copyOf(regionToPartitionIn);
            this.macroToPartitions = XCldrStub.ImmutableMultimap.copyOf(macroToPartitionsIn);
            this.paradigms = XCldrStub.ImmutableSet.copyOf(paradigmsIn);
        }

        public String toId(String region) {
            String result = this.regionToPartition.get(region);
            return result == null ? "" : result;
        }

        public Collection<String> getIdsFromVariable(String variable) {
            if (variable.equals("*")) {
                return Collections.singleton("*");
            }
            Collection<String> result = this.variableToPartition.get(variable);
            if (result != null && !result.isEmpty()) {
                return result;
            }
            throw new IllegalArgumentException("Variable not defined: " + variable);
        }

        public Set<String> regions() {
            return this.regionToPartition.keySet();
        }

        public Set<String> variables() {
            return this.variableToPartition.keySet();
        }

        public String toString() {
            XCldrStub.TreeMultimap<String, String> partitionToVariables = (XCldrStub.TreeMultimap) XCldrStub.Multimaps.invertFrom(this.variableToPartition, XCldrStub.TreeMultimap.create());
            XCldrStub.TreeMultimap<String, String> partitionToRegions = XCldrStub.TreeMultimap.create();
            for (Map.Entry<String, String> e : this.regionToPartition.entrySet()) {
                partitionToRegions.put(e.getValue(), e.getKey());
            }
            StringBuilder buffer = new StringBuilder();
            buffer.append("Partition ➠ Variables ➠ Regions (final)");
            for (Map.Entry<String, Set<String>> e2 : partitionToVariables.asMap().entrySet()) {
                buffer.append('\n');
                buffer.append(e2.getKey() + "\t" + ((Object) e2.getValue()) + "\t" + ((Object) partitionToRegions.get(e2.getKey())));
            }
            buffer.append("\nMacro ➠ Partitions");
            for (Map.Entry<String, Set<String>> e3 : this.macroToPartitions.asMap().entrySet()) {
                buffer.append('\n');
                buffer.append(e3.getKey() + "\t" + ((Object) e3.getValue()));
            }
            return buffer.toString();
        }

        static class Builder {
            private final Set<ULocale> paradigms = new LinkedHashSet();
            private final RegionSet regionSet = new RegionSet();
            private final XCldrStub.Multimap<String, String> regionToRawPartition = XCldrStub.TreeMultimap.create();

            Builder() {
            }

            /* access modifiers changed from: package-private */
            public void add(String variable, String barString) {
                for (String region : this.regionSet.parseSet(barString)) {
                    this.regionToRawPartition.put(region, variable);
                }
                String inverseVariable = "$!" + variable.substring(1);
                for (String region2 : this.regionSet.inverse()) {
                    this.regionToRawPartition.put(region2, inverseVariable);
                }
            }

            public Builder addParadigms(String... paradigmRegions) {
                for (String paradigm : paradigmRegions) {
                    this.paradigms.add(new ULocale(paradigm));
                }
                return this;
            }

            /* access modifiers changed from: package-private */
            public RegionMapper build() {
                IdMakerFull<Collection<String>> id = new IdMakerFull<>("partition");
                XCldrStub.Multimap<String, String> variableToPartitions = XCldrStub.TreeMultimap.create();
                Map<String, String> regionToPartition = new TreeMap<>();
                XCldrStub.Multimap<String, String> partitionToRegions = XCldrStub.TreeMultimap.create();
                for (Map.Entry<String, Set<String>> e : this.regionToRawPartition.asMap().entrySet()) {
                    String region = e.getKey();
                    Collection<String> rawPartition = e.getValue();
                    String partition = String.valueOf((char) (id.add(rawPartition).intValue() + 945));
                    regionToPartition.put(region, partition);
                    partitionToRegions.put(partition, region);
                    for (String variable : rawPartition) {
                        variableToPartitions.put(variable, partition);
                    }
                }
                XCldrStub.Multimap<String, String> macroToPartitions = XCldrStub.TreeMultimap.create();
                for (Map.Entry<String, Set<String>> e2 : XLocaleDistance.CONTAINER_TO_CONTAINED.asMap().entrySet()) {
                    String macro = e2.getKey();
                    for (Map.Entry<String, Set<String>> e22 : partitionToRegions.asMap().entrySet()) {
                        String partition2 = e22.getKey();
                        if (!Collections.disjoint(e2.getValue(), e22.getValue())) {
                            macroToPartitions.put(macro, partition2);
                        }
                    }
                }
                return new RegionMapper(variableToPartitions, regionToPartition, macroToPartitions, this.paradigms);
            }
        }
    }

    /* access modifiers changed from: private */
    public static class RegionSet {
        private Operation operation;
        private final Set<String> tempRegions;

        /* access modifiers changed from: private */
        public enum Operation {
            add,
            remove
        }

        private RegionSet() {
            this.tempRegions = new TreeSet();
            this.operation = null;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Set<String> parseSet(String barString) {
            this.operation = Operation.add;
            int last = 0;
            this.tempRegions.clear();
            int i = 0;
            while (i < barString.length()) {
                char c = barString.charAt(i);
                if (c == '+') {
                    add(barString, last, i);
                    last = i + 1;
                    this.operation = Operation.add;
                } else if (c == '-') {
                    add(barString, last, i);
                    last = i + 1;
                    this.operation = Operation.remove;
                }
                i++;
            }
            add(barString, last, i);
            return this.tempRegions;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Set<String> inverse() {
            TreeSet<String> result = new TreeSet<>(XLocaleDistance.ALL_FINAL_REGIONS);
            result.removeAll(this.tempRegions);
            return result;
        }

        private void add(String barString, int last, int i) {
            if (i > last) {
                changeSet(this.operation, barString.substring(last, i));
            }
        }

        private void changeSet(Operation operation2, String region) {
            Collection<String> contained = XLocaleDistance.CONTAINER_TO_CONTAINED_FINAL.get(region);
            if (contained == null || contained.isEmpty()) {
                if (Operation.add == operation2) {
                    this.tempRegions.add(region);
                } else {
                    this.tempRegions.remove(region);
                }
            } else if (Operation.add == operation2) {
                this.tempRegions.addAll(contained);
            } else {
                this.tempRegions.removeAll(contained);
            }
        }
    }

    public static <K, V> XCldrStub.Multimap<K, V> invertMap(Map<V, K> map) {
        return XCldrStub.Multimaps.invertFrom(XCldrStub.Multimaps.forMap(map), XCldrStub.LinkedHashMultimap.create());
    }

    public Set<ULocale> getParadigms() {
        return this.regionMapper.paradigms;
    }

    public int getDefaultLanguageDistance() {
        return this.defaultLanguageDistance;
    }

    public int getDefaultScriptDistance() {
        return this.defaultScriptDistance;
    }

    public int getDefaultRegionDistance() {
        return this.defaultRegionDistance;
    }

    /* access modifiers changed from: package-private */
    public static class CompactAndImmutablizer extends IdMakerFull<Object> {
        CompactAndImmutablizer() {
        }

        /* access modifiers changed from: package-private */
        public StringDistanceTable compact(StringDistanceTable item) {
            if (toId((Object) item) != null) {
                return (StringDistanceTable) intern(item);
            }
            return new StringDistanceTable(compact(item.subtables, 0));
        }

        /* access modifiers changed from: package-private */
        public <K, T> Map<K, T> compact(Map<K, T> item, int level) {
            if (toId((Object) item) != null) {
                return (Map) intern(item);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<K, T> entry : item.entrySet()) {
                T value = entry.getValue();
                if (value instanceof Map) {
                    linkedHashMap.put(entry.getKey(), compact(value, level + 1));
                } else {
                    linkedHashMap.put(entry.getKey(), compact((DistanceNode) value));
                }
            }
            return XCldrStub.ImmutableMap.copyOf(linkedHashMap);
        }

        /* access modifiers changed from: package-private */
        public DistanceNode compact(DistanceNode item) {
            if (toId((Object) item) != null) {
                return (DistanceNode) intern(item);
            }
            DistanceTable distanceTable = item.getDistanceTable();
            if (distanceTable == null || distanceTable.isEmpty()) {
                return new DistanceNode(item.distance);
            }
            return new StringDistanceNode(item.distance, compact((StringDistanceTable) ((StringDistanceNode) item).distanceTable));
        }
    }

    @Deprecated
    public StringDistanceTable internalGetDistanceTable() {
        return (StringDistanceTable) this.languageDesired2Supported;
    }

    public static void main(String[] args) {
        DistanceTable table = getDefault().languageDesired2Supported;
        if (!table.equals(table.compact())) {
            throw new IllegalArgumentException("Compaction isn't equal");
        }
    }
}
