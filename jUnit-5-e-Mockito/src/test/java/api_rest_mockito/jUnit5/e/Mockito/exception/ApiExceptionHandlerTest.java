package api_rest_mockito.jUnit5.e.Mockito.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ApiExceptionHandlerTest {

    public static final String MESSAGE = "Objeto não econtrado";
    public static final String JA_CADASTRADO = "Email ja cadastrado";
    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;
    @Test
    void whenObjectNotFoundT() {
        ResponseEntity<StandardError> response
                = apiExceptionHandler.objectNotFound
                (new ObjectNotFoundException(MESSAGE),new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(StandardError.class,response.getBody().getClass());
        Assertions.assertEquals(MESSAGE,response.getBody().getError());
        Assertions.assertEquals(404,response.getBody().getStatus());
    }

    @Test
    void whenDataIntegrityViolationException() {
        ResponseEntity<StandardError> response
                = apiExceptionHandler.dataIntegratyViolationException
                (new DataIntegratyViolationException
                        (JA_CADASTRADO),new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(StandardError.class,response.getBody().getClass());
        Assertions.assertEquals(JA_CADASTRADO,response.getBody().getError());
        Assertions.assertEquals(400,response.getBody().getStatus());
    }
}