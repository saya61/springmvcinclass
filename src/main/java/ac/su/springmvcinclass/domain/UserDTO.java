package ac.su.springmvcinclass.domain;

import lombok.Getter;

@Getter  // Setter 없어도 됨
public class UserDTO {
    // final 키워드는 View 패러미터 수신용 기본 생성자와 충돌하므로 쓰지 않는다.
    private Long id;
    private String name;
    private String email;

    public UserDTO() {} // 기본 생성자 삭제 시, Request 로부터 제대로 된 수신 데이터 주입이 일어날 수 없음.

    private UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public static UserDTO fromEntity(User user) {
        return new UserDTO(user);
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setEmail(this.email);
        return user;
    }
}
