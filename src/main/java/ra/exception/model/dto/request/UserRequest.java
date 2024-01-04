package ra.exception.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequest {
    @NotEmpty(message = "fill in the blank")
    private String userName;
    private String fullName;
    @Size(min=3,message = "password at least 3 letters")
    private String password;
    @Column(columnDefinition = "Boolean default true")
    private Boolean status=true;

}
