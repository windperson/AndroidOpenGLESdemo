package idv.windperson.androidopengles2demo;

/**
 * Created by windperson on 9/6/2016.
 */
public class Counter {
    private static int _upDown = 0;
    private static final Object _counterLock = new Object();

    public static void reset() {
        synchronized (_counterLock) {
            _upDown = 0;
        }
    }

    public static int getUpDownValue() {
        int value = 0;
        synchronized (_counterLock) {
            value = _upDown;
        }
        return value;
    }

    public static void getUpDownNextValue() {
        synchronized (_counterLock) {
            ++_upDown;
        }
    }

    public static void getUpDownPreviousValue() {
        synchronized (_counterLock) {
            --_upDown;
        }
    }
}
