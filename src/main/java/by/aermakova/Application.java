package by.aermakova;

import java.util.Map;

public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> itc2Class){
        JavaConfig config = new JavaConfig(packageToScan, itc2Class);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);
        return context;
    }
}
