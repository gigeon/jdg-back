package jtw.jtwback.com;

import java.util.HashMap;
import java.util.Map;

public class BaseMap extends HashMap<String, Object>{

    public BaseMap set(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public Object getString(String key) {
        return this.get(key);
    }

    public BaseMap setMap(Map<String, String[]> inputMap) {
        for (String key : inputMap.keySet()) {
            this.put(key, inputMap.get(key)[0]);
        }
        return this;
    }


}