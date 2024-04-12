package ac.su.springmvcinclass.controller;

import ac.su.springmvcinclass.domain.User;
import ac.su.springmvcinclass.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUser() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
        // Json 타입으로 만들어짐
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // /api/users 의 엔드 포인트
    // GET(전체 List 출력) 과는 다르게 POST 는 새 유저 생성
    // 상태를 대표하는 데이터를 전송하는 ~ RESTful
    // -> PostMapping 에 동사 형태로 설명하는 것은 정확히 반대
    // Create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
         User updateUser = userService.updateUser(id, user);
         return ResponseEntity.ok(updateUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable long id, @RequestBody User user) {
        // 변경하고자 하는 필드를 targetFields = [field1, field2, ...]
        // 규약에 명시된 제약조건 외에는 자유도 있게 input output 설계 가능
        User patchedUser = userService.patchUser(id, user);
        return ResponseEntity.ok(patchedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
