package ac.su.springmvcinclass.controller;

import ac.su.springmvcinclass.domain.UserBmiDTO;
import ac.su.springmvcinclass.domain.UserDTO;
import ac.su.springmvcinclass.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // View 엔진 없이 컨텐츠를 직접 응답
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUser() {
        List<UserDTO> allUsers = userService.getAllUsers();  // 1) 전체 리스트 User Entity 를 DTO 로 수신 필요
        return ResponseEntity.ok(allUsers);
        // Json 타입으로 만들어짐
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserDTOByID(id);  // 2) 유저 개별 데이터를 DTO 로 수신 필요
        return ResponseEntity.ok(user);
    }

    // /api/users 의 엔드 포인트
    // GET(전체 List 출력) 과는 다르게 POST 는 새 유저 생성
    // 상태를 대표하는 데이터를 전송하는 ~ RESTful
    // -> PostMapping 에 동사 형태로 설명하는 것은 정확히 반대
    // Create
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        UserDTO savedUser = userService.createUser(user);   // 3) 유저 생성 데이터도 DTO 로 Service 에 전달
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @RequestBody UserDTO user) {
         UserDTO updateUser = userService.updateUser(id, user);  // 4) 유저 업데이트도 DTO 수행 필요
         return ResponseEntity.ok(updateUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> patchUser(@PathVariable long id, @RequestBody UserDTO user) {
        // 변경하고자 하는 필드를 targetFields = [field1, field2, ...]
        // 규약에 명시된 제약조건 외에는 자유도 있게 input output 설계 가능
        UserDTO patchedUser = userService.patchUser(id, user);  // 5) 유저 업데이트도 DTO 수행 필요
        return ResponseEntity.ok(patchedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/response-entity-sample")
    public ResponseEntity<Map<String, String>> getCustomResponse() {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "add body msg test");

        HttpHeaders headers = new HttpHeaders();    // Spring framework 가져옴
        headers.add("Custom-Header", "add custom header content text");
        // 1) 응답 헤더는 현재 조회한 페이지를 기준으로 추가 Navigating 을 가이드 하는 용도
        //      ex) 추가로 통신에 활용되는 데이터 상태(본 페이지가 보안적으로 현재 요청에 의해 표시되기에 안전한지의 여부)
        //          CORS : Cross Origin Resource Sharing 허용 표시 여부,
        //          사이트 간에 데이터 = Resource 를 부분적으로 완성해가면서 Client 에서 원하는 최종 컨텐츠가 완성되는 경우
        //          -> 교차 출처 리소스 공유 어뷰징을 통해서 "특정 페이지의 날조, 사기적으로 조합"
        //          => 고객 행위 교란 시킬 수 있음

        // 2) 현재 조회 페이지 자체에 대해서 Data 가 아닌, Metadata 를 응답하는 위치로 이동
        //      ex) 페이지에서 응답하는 데이터 총량을 표시, 로그인 유저의 현재 권한 상태
        //      => Header 만 미리 받아보고, 후속동작을 수행하는 클라이언트 동작이 가능해짐
        //      Cross-Origin-Resource-Proxy 시, 로그인 ~

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .headers(headers)
                .body(responseBody);
    }

    @GetMapping("/bmi")
    public ResponseEntity<List<UserBmiDTO>> getAllUsersWithBmi() {
        List<UserBmiDTO> users = userService.findAllUserWithBmi();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}/bmi")
    public ResponseEntity<UserBmiDTO> getUserWithBmi(@PathVariable Long id) {
        UserBmiDTO user = userService.findUserBmiById(id);
        return ResponseEntity.ok(user);
    }
}
