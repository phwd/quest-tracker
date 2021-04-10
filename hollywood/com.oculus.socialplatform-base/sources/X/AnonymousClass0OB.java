package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.GuavaOptionalDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.HashMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableBiMapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableListDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableMapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedMapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedSetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.LinkedHashMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.MultimapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.TreeMultisetDeserializer;
import com.google.common.base.Optional;
import com.google.common.collect.EnumBiMap;
import com.google.common.collect.EnumHashBiMap;
import com.google.common.collect.EnumMultiset;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.TreeMultiset;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.0OB  reason: invalid class name */
public final class AnonymousClass0OB extends C02090hy {
    @Override // X.C02090hy, X.AbstractC04260pg
    public final JsonDeserializer<?> A37(AnonymousClass0C8 r12, AnonymousClass0HU r13, AbstractC04010oz r14, AnonymousClass0p6 r15, AbstractC04520qa r16, JsonDeserializer<?> jsonDeserializer) throws C02180iD {
        Method method;
        Class<?> cls = r12._class;
        if (ImmutableMultimap.class.isAssignableFrom(cls)) {
            ImmutableListMultimap.class.isAssignableFrom(cls);
            ImmutableSetMultimap.class.isAssignableFrom(cls);
        }
        if (AbstractC05440vj.class.isAssignableFrom(cls)) {
            Class<?> cls2 = r12._class;
            Method method2 = null;
            if (cls2 != LinkedListMultimap.class && cls2 != AnonymousClass0fD.class && cls2 != AbstractC05440vj.class) {
                List<String> list = MultimapDeserializer.A05;
                Iterator<String> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        Iterator<String> it2 = list.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            try {
                                method = cls2.getMethod(it2.next(), AbstractC05440vj.class);
                                if (method != null) {
                                    break;
                                }
                            } catch (NoSuchMethodException unused) {
                            }
                        }
                    } else {
                        try {
                            method = cls2.getMethod(it.next(), AbstractC05440vj.class);
                            if (method != null) {
                                break;
                            }
                        } catch (NoSuchMethodException unused2) {
                        }
                    }
                }
                method2 = method;
            }
            return new MultimapDeserializer(r12, r15, r16, jsonDeserializer, method2);
        }
        AnonymousClass0wT.class.isAssignableFrom(cls);
        return null;
    }

    @Override // X.C02090hy, X.AbstractC04260pg
    public final JsonDeserializer<?> A2z(AbstractC02190iF r3, AnonymousClass0HU r4, AbstractC04010oz r5) throws C02180iD {
        if (Optional.class.isAssignableFrom(r3._class)) {
            return new GuavaOptionalDeserializer(r3);
        }
        return super.A2z(r3, r4, r5);
    }

    @Override // X.C02090hy, X.AbstractC04260pg
    public final JsonDeserializer<?> A30(AnonymousClass03E r5, AnonymousClass0HU r6, AbstractC04010oz r7, AbstractC04520qa r8, JsonDeserializer<?> jsonDeserializer) throws C02180iD {
        Class<?> cls = r5._class;
        if (ImmutableCollection.class.isAssignableFrom(cls)) {
            if (ImmutableList.class.isAssignableFrom(cls)) {
                return new ImmutableListDeserializer(r5, r8, jsonDeserializer);
            }
            if (ImmutableMultiset.class.isAssignableFrom(cls)) {
                return new ImmutableMultisetDeserializer(r5, r8, jsonDeserializer);
            }
            if (!ImmutableSet.class.isAssignableFrom(cls)) {
                return new ImmutableListDeserializer(r5, r8, jsonDeserializer);
            }
            if (!ImmutableSortedSet.class.isAssignableFrom(cls)) {
                return new ImmutableSetDeserializer(r5, r8, jsonDeserializer);
            }
            if (Comparable.class.isAssignableFrom(r5.A04()._class)) {
                return new ImmutableSortedSetDeserializer(r5, r8, jsonDeserializer);
            }
            throw new IllegalArgumentException(AnonymousClass006.A09("Can not handle ImmutableSortedSet with elements that are not Comparable<?> (", cls.getName(), ")"));
        } else if (!AbstractC05490vp.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (LinkedHashMultiset.class.isAssignableFrom(cls)) {
                return new LinkedHashMultisetDeserializer(r5, r8, jsonDeserializer);
            }
            if (HashMultiset.class.isAssignableFrom(cls)) {
                return new HashMultisetDeserializer(r5, r8, jsonDeserializer);
            }
            EnumMultiset.class.isAssignableFrom(cls);
            if (TreeMultiset.class.isAssignableFrom(cls)) {
                return new TreeMultisetDeserializer(r5, r8, jsonDeserializer);
            }
            return new HashMultisetDeserializer(r5, r8, jsonDeserializer);
        }
    }

    @Override // X.C02090hy, X.AbstractC04260pg
    public final JsonDeserializer<?> A36(AnonymousClass03D r3, AnonymousClass0HU r4, AbstractC04010oz r5, AnonymousClass0p6 r6, AbstractC04520qa r7, JsonDeserializer<?> jsonDeserializer) throws C02180iD {
        Class<?> cls = r3._class;
        if (ImmutableMap.class.isAssignableFrom(cls)) {
            if (ImmutableSortedMap.class.isAssignableFrom(cls)) {
                return new ImmutableSortedMapDeserializer(r3, r6, r7, jsonDeserializer);
            }
            if (ImmutableBiMap.class.isAssignableFrom(cls)) {
                return new ImmutableBiMapDeserializer(r3, r6, r7, jsonDeserializer);
            }
            return new ImmutableMapDeserializer(r3, r6, r7, jsonDeserializer);
        } else if (!AbstractC05100td.class.isAssignableFrom(cls)) {
            return null;
        } else {
            EnumBiMap.class.isAssignableFrom(cls);
            EnumHashBiMap.class.isAssignableFrom(cls);
            HashBiMap.class.isAssignableFrom(cls);
            return null;
        }
    }
}
