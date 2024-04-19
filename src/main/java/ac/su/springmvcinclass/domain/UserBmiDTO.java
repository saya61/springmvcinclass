package ac.su.springmvcinclass.domain;

import lombok.Getter;

@Getter  // Setter 없어야 immutable 하게 다룰 수 있음.
public class UserBmiDTO {
    private Long id;
    private String name;
    private String email;
    // 아래 컬럼들 포함 여부는 optional
//    private double weight;
//    private double height;
    private double bmi;

    public UserBmiDTO() {}

    private UserBmiDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        // 저장된 컬럼 포함 여부 optional
//        this.weight = user.getWeight();
//        this.height = user.getHeight();
        this.bmi = calculateBmi(user.getWeight(), user.getHeight());
    }

    private double calculateBmi(double weight, double height) {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }

    public static UserBmiDTO fromEntity(User user) {
        return new UserBmiDTO(user);
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setEmail(this.email);
//        user.setWeight(this.weight);
//        user.setHeight(this.height);
        return user;
    }
}