package ra.exception.service;

import ra.exception.exception.CustomException;
import ra.exception.model.dto.request.UserRequest;
import ra.exception.model.dto.response.UserResponse;
import ra.exception.model.entity.User;
import ra.exception.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(user -> new UserResponse(user.getId(), user.getUserName(), user.getFullName(),user.getStatus()));
    }

    @Override
    public UserResponse saveOrUpdate(UserRequest userRequest) throws CustomException {
//        check trung userName
        if (userRepository.existsByUserName(userRequest.getUserName())){
            throw new CustomException("UserName was existed");
        }
        User user = User.builder()
                .userName(userRequest.getUserName())
                .fullName(userRequest.getFullName())
                .password(userRequest.getPassword()).status(userRequest.getStatus()).build();
        User newUser = userRepository.save(user);
        return UserResponse.builder().id(newUser.getId()).userName(newUser.getUserName()).fullName(newUser.getFullName()).status(newUser.getStatus()).build();
    }

    @Override
    public UserResponse findById(Long id) throws CustomException{
        Optional<User> optionalUser=userRepository.findById(id);
        if (optionalUser.isPresent()){
            User user=optionalUser.get();
            return UserResponse.builder().userName(user.getUserName()).fullName(user.getFullName()).status(user.getStatus()).build();
        }
        throw new CustomException("Not Found");
    }
}
