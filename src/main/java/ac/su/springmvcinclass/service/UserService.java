package ac.su.springmvcinclass.service;

import ac.su.springmvcinclass.domain.User;
import ac.su.springmvcinclass.domain.UserBmiDTO;
import ac.su.springmvcinclass.domain.UserDTO;
import ac.su.springmvcinclass.exception.UserNotFoundException;
import ac.su.springmvcinclass.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static List<UserDTO> convertToUserDTOList(List<User> userList) {
        return userList.stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsers() {
        // 구체적인 조건,  처리 세부사항 등의 로직을
        // Service Layer 에서 처리
        List<User> allUserList = userRepository.findAll();
        return convertToUserDTOList(allUserList);
    }


    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserDTO getUserDTOByID(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        // DB 에서 읽어온 데이터를 한차례 컨버팅해서 변환
        return UserDTO.fromEntity(existingUser);
    }

    public UserDTO createUser(UserDTO newUser) {
        // 1) 데이터 저장을 위해서 1차 DTO to Entity 변환 수행
        User savedUser = userRepository.save(newUser.toEntity());
        // 2) 데이터 반환을 위해서 2차 Entity to DTO 변환 수행
        return UserDTO.fromEntity(savedUser);    // id 값이 새로 생성되므로, SQL create 수행
    }

    public UserDTO updateUser(long id, UserDTO updatedUser) {
        // 유저 검색
        User registeredUser = getUserById(id);
        registeredUser.setName(updatedUser.getName());
        registeredUser.setEmail(updatedUser.getEmail());
        User savedUser = userRepository.save(registeredUser);
        return UserDTO.fromEntity(savedUser); // id 값이 이미 존재하므로 SQL update 문 수행
    }

    public UserDTO patchUser(long id, UserDTO patchedUser) {
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
        User savedUser = userRepository.save(registeredUser);
        return UserDTO.fromEntity(savedUser);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public List<UserBmiDTO> findAllUserWithBmi() {
        List<User> allUsers = userRepository.findAll();
        return convertToUserBmiDTOList(allUsers);
    }

    public static List<UserBmiDTO> convertToUserBmiDTOList(List<User> userList) {
        return userList.stream()
                .map(UserBmiDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public UserBmiDTO findBmiUserById(Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserBmiDTO.fromEntity(foundUser);
    }

    public UserBmiDTO findUserBmiById(Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserBmiDTO.fromEntity(foundUser);
    }
}
