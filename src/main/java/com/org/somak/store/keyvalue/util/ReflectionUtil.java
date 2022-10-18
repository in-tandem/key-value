package com.org.somak.store.keyvalue.util;

import com.org.somak.store.keyvalue.factory.ItemFactory;

import java.lang.reflect.InvocationTargetException;

public final class ReflectionUtil {

    public static final String FACTORY_PACKAGE_NAME="com.org.somak.store.keyvalue.factory";

    public static Object of(String fullyQualifiedClassName) throws ClassNotFoundException {
        try {
            return Class.forName(fullyQualifiedClassName).getConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
