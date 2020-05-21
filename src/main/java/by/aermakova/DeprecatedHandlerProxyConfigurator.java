package by.aermakova;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithObjectIfNeeded(Object t, Class implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)) {
            if (implClass.getInterfaces().length == 0) {
                return Enhancer.create(implClass, (InvocationHandler) (o, method, args) -> getInvocationHandlerLogic(t, method, args));
            }
            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), (o, method, args) -> getInvocationHandlerLogic(t, method, args));
        } else return t;
    }

    private Object getInvocationHandlerLogic(Object t, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("************** что же ты творишь... **************");
        return method.invoke(t, args);
    }
}