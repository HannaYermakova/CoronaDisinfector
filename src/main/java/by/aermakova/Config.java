package by.aermakova;

import org.reflections.Reflections;

public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> ifc);
    Reflections getScanner();
}
