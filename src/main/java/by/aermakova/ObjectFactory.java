package by.aermakova;

import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();
//    private Config config;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
//       config =  new JavaConfig("by.aermakova", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }

        for (Class<? extends ProxyConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T t = create(implClass);
        config(t);
        runInitMethod(implClass, t);
        t = wrapWithProxyIfNeeded(implClass, t);
        return t;
    }

    private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T t) {
        for (ProxyConfigurator configurator : proxyConfigurators) {
            t = (T) configurator.replaceWithObjectIfNeeded(t, implClass);
        }
        return t;
    }

    private <T> void runInitMethod(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> void config(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> T create(Class<T> implClass) throws InstantiationException,
            IllegalAccessException,
            InvocationTargetException,
            NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}