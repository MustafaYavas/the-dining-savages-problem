import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        // semaphores initialized
        Semaphore mutex = new Semaphore(1);
        Semaphore emptyPot = new Semaphore(0);
        Semaphore fullPot = new Semaphore(0);

        MutableInteger servingCount = new MutableInteger(0); // in beginning no food in the pot
        Thread cookObj = new Cook(servingCount,mutex,emptyPot,fullPot);
        Thread[] savageObjs = new Savages[20];

        cookObj.start();

        for (int i = 0; i < savageObjs.length; i++) {
            savageObjs[i] = new Savages(servingCount,mutex,emptyPot,fullPot,"Savage-"+i);
        }

        for (int i = 0; i < savageObjs.length; i++) {
            savageObjs[i].start();
        }

    }
}
