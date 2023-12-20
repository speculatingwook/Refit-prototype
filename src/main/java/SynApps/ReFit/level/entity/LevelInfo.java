package synApps.refit.level.entity;


import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class LevelInfo {
    private final Integer level;
    private static HashMap<Integer, Integer> levelInfo = new HashMap<>();
    private static List<Integer> expValues = List.of(
            10, 22, 36, 52, 72, 95, 121, 150, 185, 224,
            267, 314, 365, 425, 490, 560, 635, 715, 815, 921,
            1033, 1151, 1275, 1415, 1562, 1716, 1877, 2045, 2220
    );

    public LevelInfo(Integer exp) {
        initializeInfo();
        this.level = calculateExpToLevel(exp) - 1;
    }

    private void initializeInfo() {
        for (int i = 1; i < expValues.size() + 1; i++) {
            levelInfo.put(expValues.get(i-1), i);
        }
    }

    private Integer calculateExpToLevel(Integer exp) {
        for (int i = 0; i < expValues.size(); i++) {
            if (exp < 10) {
                return 2;
            }
            if (exp > expValues.get(i)) {
                return levelInfo.get(expValues.get(i));
            }
        }
        return 0;
    }
}
