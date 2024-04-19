package ac.su.springmvcinclass.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity             // Repository 에서 DB에 접근할 때 사용하는 객체 => DAO
@Getter @Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // 요구사항에 따른 비즈니스 로직(BMI 계산) 을 위해서 데이터 읽어와야 함
    // Repository 에서는 DAO 를 쓰지만
    // Service 에서 Controller 및 View 로 전달할 때는 DTO 를 써서 해결
    @Column(nullable = true)
    private double height;

    @Column(nullable = true)
    private double weight;
}
