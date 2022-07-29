package fr.mrcubee.menu;

import java.lang.reflect.Constructor;

public class Reflections {

    public static <T> Constructor<T> getConstructor(final Class<T> clazz, final Class<?>... parameterClasses) {
        Constructor<T> constructor = null;

        if (clazz == null)
            return null;
        try {
            constructor = clazz.getDeclaredConstructor(parameterClasses);
        } catch (NoSuchMethodException ignored) {}
        return constructor;
    }

    public static <T> Constructor<T> getConstructor(final Class<T> clazz, final Object... parameters) {
        final Class<?>[] parameterClasses;

        if (parameters == null || parameters.length == 0)
            return getConstructor(clazz);
        parameterClasses = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++)
            parameterClasses[i] = parameters[i].getClass();
        return getConstructor(clazz, parameterClasses);
    }

}
