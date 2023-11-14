package synApps.refit.diet.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {
    @Id
    @GeneratedValue
    private Long foodId;

    private String name;

    @Enumerated(EnumType.STRING)
    private FoodReference foodReference;

    private float gramPerMeal;

    private float calorie;

    private float carbohydrate;

    private float protein;

    private float fat;

    private float sugar;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Food(String name,
                 FoodReference foodReference,
                 float gramPerMeal,
                 float calorie,
                 float carbohydrate,
                 float protein,
                 float fat,
                 float sugar,
                 LocalDateTime createdAt,
                 LocalDateTime modifiedAt) {
        this.name = name;
        this.foodReference = foodReference;
        this.gramPerMeal = gramPerMeal;
        this.calorie = calorie;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void modifyFood(String name,
                           FoodReference foodReference,
                           float gramPerMeal,
                           float calorie,
                           float carbohydrate,
                           float protein,
                           float fat,
                           float sugar,
                           LocalDateTime modifiedAt) {
        this.name = name;
        this.foodReference = foodReference;
        this.gramPerMeal = gramPerMeal;
        this.calorie = calorie;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.modifiedAt = modifiedAt;
    }

    public static Food of(String name,
                          FoodReference foodReference,
                          float gramPerMeal,
                          float calorie,
                          float carbohydrate,
                          float protein,
                          float fat,
                          float sugar,
                          LocalDateTime createdAt,
                          LocalDateTime modifiedAt) {
        return new Food(name, foodReference, gramPerMeal, calorie, carbohydrate, protein, fat, sugar, createdAt, modifiedAt);
    }

}
