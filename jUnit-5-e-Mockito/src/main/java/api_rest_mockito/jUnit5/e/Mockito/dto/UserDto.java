package api_rest_mockito.jUnit5.e.Mockito.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
}
