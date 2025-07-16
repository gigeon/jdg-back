package jtw.jtwback.com;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class utilMap {
    public static BaseMap fromDbMap(BaseMap map) {
        if(map != null) {
            BaseMap result = new BaseMap();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String camelKey = toCamelCase(entry.getKey());
                result.put(camelKey, entry.getValue());
            }
            return result;
        }
        return new BaseMap();
    }

    public static String toCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean nextUpperCase = false;

        for (char c : input.toCharArray()) {
            if (c == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    result.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }

        return result.toString();
    }

    public static List<BaseMap> fromDbList(List<BaseMap> list) {
        return list.stream()
                .map(utilMap::fromDbMap)
                .collect(Collectors.toList());
    }
}