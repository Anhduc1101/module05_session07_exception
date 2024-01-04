package ra.exception.model.dto.response;

import jakarta.persistence.Column;
import ra.exception.model.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String userName;
    private String fullName;
    @Column(columnDefinition = "Boolean default true")
    private Boolean status=true;

    public UserResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.fullName = user.getFullName();
        this.status = user.getStatus();
    }
}
