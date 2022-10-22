import java.util.HashMap;
import java.util.Map;

public class UserSlidingWindow {

    private Map<Integer, SlidingWindow> map = new HashMap<>();

    public UserSlidingWindow(int id) {
        map.put(id, new SlidingWindow(1, 10));
    }

    public void accessApplication(int id) {

        if (map.get(id).grandAccess())
            System.out.println("Success");
        else
            System.out.println("Too many request");
    }
}
