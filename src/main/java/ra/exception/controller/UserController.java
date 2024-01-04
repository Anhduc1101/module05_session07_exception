package ra.exception.controller;

import ra.exception.exception.CustomException;
import ra.exception.model.dto.request.UserRequest;
import ra.exception.model.dto.response.UserResponse;
import ra.exception.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> index(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int limit){
        Pageable pageable= PageRequest.of(page, limit);
        Page<UserResponse> userResponsePage=userService.findAll(pageable);
        return new ResponseEntity<>(userResponsePage, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> save(@Valid@RequestBody UserRequest userRequest, BindingResult result) throws CustomException {
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }
        UserResponse userResponse=userService.saveOrUpdate(userRequest);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }

//    @GetMapping("/users/{id}")
//   public ResponseEntity<?> findById(@PathVariable("id") Long id) throws CustomException {
//        UserResponse userResponse=userService.findById(id);
//        return new ResponseEntity<>(userResponse,HttpStatus.OK);
//    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") Long id) throws CustomException {
        UserResponse userResponse=userService.findById(id);
        if (userResponse!=null){
            userResponse.setStatus(!userResponse.getStatus());
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") Long id) throws CustomException {
        UserResponse userResponse=userService.findById(id);
        if (userResponse!=null){
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/users")
    public  ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest) {
        if (userRequest.getUserName() != null) {
           userRequest.setUserName(userRequest.getUserName());
        }
        if (userRequest.getFullName() != null) {
            userRequest.setFullName(userRequest.getFullName());
        }
        return new ResponseEntity<>(userRequest,HttpStatus.OK);
    }
}
