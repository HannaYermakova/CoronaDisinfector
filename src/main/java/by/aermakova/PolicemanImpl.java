package by.aermakova;

import javax.annotation.PostConstruct;

public class PolicemanImpl implements Policeman {
    @InjectByType
    private Recommender recommender;

    @PostConstruct
    public void init() {
        System.out.println(recommender.getClass());
    }

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("пиф паф, бах бах, кыш, кыш!");

    }
}
