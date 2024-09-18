package api_rest_mockito.jUnit5.e.Mockito.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Setter
@Getter
public class StandardError {
   private LocalDateTime timestamp;
   private Integer status;
   private String error;
   private String path;
}
