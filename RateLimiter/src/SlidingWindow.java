import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindow implements RateLimiter {

    private Queue<Integer> slidingWindow;
    private int time;
    private int freq;

    public SlidingWindow(int time, int freq) {
        this.time = time;
        this.freq = freq;
        this.slidingWindow = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean grandAccess() {
        long currTime = System.currentTimeMillis();
        updateQueue(currTime);
        if (this.slidingWindow.size() < this.freq) {
            this.slidingWindow.offer((int) currTime / 1000);
            return true;
        }
        return false;
    }

    private void updateQueue(long currTime) {

        if (this.slidingWindow.isEmpty())
            return ;

        long time = (currTime - this.slidingWindow.peek()) / 1000;
        while (time >= this.time) {
            this.slidingWindow.poll();
            if (this.slidingWindow.isEmpty())
                return ;

            time = (currTime - this.slidingWindow.peek()) / 1000;
        }
    }
}
