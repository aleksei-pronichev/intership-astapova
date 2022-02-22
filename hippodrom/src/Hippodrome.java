import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;

    public static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            this.move();
            this.print();
            Thread.sleep(500);
        }
    }
    public void move(){
        for (Horse horse : horses) {
            horse.move();
        }
    }
    public void print(){
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (Horse horse : horses) {
            if (winner.getDistance() < horse.getDistance()) {
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + this.getWinner().getName() + "!");
    }

    public static void main(String[] args) {
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Favorit", 3, 0);
        Horse horse2 = new Horse("Boomer", 3, 0);
        Horse horse3 = new Horse("Zoomer", 3, 0);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        game = new Hippodrome(horses);

        try {
            game.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        game.printWinner();

    }

}
