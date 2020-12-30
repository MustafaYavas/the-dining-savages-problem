import java.util.concurrent.Semaphore;

public class Cook extends Thread{
    private MutableInteger remainingServing;
    private Semaphore mutex;    // mutex protects remainingServing variable against race condition
    private Semaphore emptyPot; // cook will be signaled when the meal is finished
    private Semaphore fullPot;  // savages will be signalled whenthe meal is ready

    public Cook(MutableInteger remainingServing, Semaphore mutex, Semaphore emptyPot, Semaphore fullPot){
        this.remainingServing = remainingServing;
        this.mutex = mutex;
        this.emptyPot = emptyPot;
        this.fullPot = fullPot;
    }

    public void run(){
        while (true){
            try {
                emptyPot.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The cook is filling the pot");
            putServingsInPot(remainingServing.getServingCount()+5);

            fullPot.release();
        }
    }

    public void putServingsInPot(int servingCount){
        remainingServing.setServingCount(servingCount);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Pot is full!");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
