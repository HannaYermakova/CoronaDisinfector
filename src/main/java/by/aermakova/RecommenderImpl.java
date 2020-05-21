package by.aermakova;

@Singleton
@Deprecated
public class RecommenderImpl implements Recommender {
    @InjectProperty("wisky")
    private String drink;

    public RecommenderImpl() {
        System.out.println("recommender was created");
    }

    @Override
    public void recommend() {
        System.out.println("to protect from Corona drink " + drink);
    }
}
