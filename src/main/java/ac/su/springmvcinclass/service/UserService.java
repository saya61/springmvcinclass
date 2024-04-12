package ac.su.springmvcinclass.service;

import ac.su.springmvcinclass.domain.User;
import ac.su.springmvcinclass.exception.UserNotFoundException;
import ac.su.springmvcinclass.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        // 구체적인 조건,  처리 세부사항 등의 로직을
        // Service Layer 에서 처리
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);    // id 값이 새로 생성되므로, SQL create 수행
    }

    public User updateUser(long id, User updatedUser) {
        // 유저 검색
        User registeredUser = getUserById(id);
        registeredUser.setName(updatedUser.getName());
        registeredUser.setEmail(updatedUser.getEmail());
        return userRepository.save(registeredUser); // id 값이 이미 존재하므로 SQL update 문 수행
    }

    public User patchUser(long id, User patchedUser) {
        // PATCH 의 경우 user 데이터 일부 필드만 수신될 가능성 고려해서 Validation
        // 모든 필드 비어있을 가능성도 있음
        // 없는 유저 대상 exception 처리도 여전히 생존
        User registeredUser = getUserById(id);
        if (patchedUser.getName() != null) {
            registeredUser.setName(patchedUser.getName());
        }
        if (patchedUser.getEmail() != null) {
            registeredUser.setEmail(patchedUser.getEmail());
        }
        return userRepository.save(registeredUser);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
