package by.aermakova;

public interface ProxyConfigurator {
    Object replaceWithObjectIfNeeded(Object t, Class implClass);
}
