package by.aermakova;

@Deprecated
public class CoronaDisinfector {

    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;

    public void start(Room room){
        announcer.announce("Начинаем дезинфекцию, всё вон!");
        policeman.makePeopleLeaveRoom();
        disinfect(room);
        announcer.announce("Рискните зайти обратно");
    }

    public void disinfect(Room room){
        System.out.println("Зачитывается молитва: 'корона изыди!' - молитва прочитана, вирус низвергнут в ад");
    }
}
