package jtw.jtwback.com;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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