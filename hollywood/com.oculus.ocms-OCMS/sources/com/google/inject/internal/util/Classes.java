package com.google.inject.internal.util;

import com.google.common.base.Preconditions;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class Classes {
    public static boolean isInnerClass(Class<?> cls) {
        return !Modifier.isStatic(cls.getModifiers()) && cls.getEnclosingClass() != null;
    }

    public static boolean isConcrete(Class<?> cls) {
        return !cls.isInterface() && !Modifier.isAbstract(cls.getModifiers());
    }

    public static String toString(Member member) {
        Class<? extends Member> memberType = memberType(member);
        if (memberType == Method.class) {
            return member.getDeclaringClass().getName() + "." + member.getName() + "()";
        } else if (memberType == Field.class) {
            return member.getDeclaringClass().getName() + "." + member.getName();
        } else if (memberType == Constructor.class) {
            return member.getDeclaringClass().getName() + ".<init>()";
        } else {
            throw new AssertionError();
        }
    }

    public static Class<? extends Member> memberType(Member member) {
        Preconditions.checkNotNull(member, "member");
        if (member instanceof Field) {
            return Field.class;
        }
        if (member instanceof Method) {
            return Method.class;
        }
        if (member instanceof Constructor) {
            return Constructor.class;
        }
        throw new IllegalArgumentException("Unsupported implementation class for Member, " + member.getClass());
    }
}
