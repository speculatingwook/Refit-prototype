package synApps.refit.diet.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FoodReference {
    REF1("ref1","testUrl.com", 0),
    REF2("ref2","testUrl2.com",1),
    OTHER("other", "default", 99);

    private final String name;
    private final String url;
    private final int serialNumber;

    FoodReference(String name, String url, int serialNumber) {
        this.name = name;
        this.url = url;
        this.serialNumber = serialNumber;
    }

    public static FoodReference of(String name) {
        return Arrays.stream(FoodReference.values())
                .filter(r->r.getName().equals(name))
                .findAny()
                .orElse(OTHER);
    }

}
