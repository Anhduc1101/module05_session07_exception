package ra.exception.service;

import ra.exception.exception.CustomException;
import ra.exception.model.dto.request.UserRequest;
import ra.exception.model.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> findAll(Pageable pageable);
    UserResponse saveOrUpdate(UserRequest userRequest) throws CustomException;
    UserResponse findById(Long id) throws CustomException;
}
