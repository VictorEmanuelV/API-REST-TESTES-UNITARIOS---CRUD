package api_rest_mockito.jUnit5.e.Mockito.controller;

import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.dto.mapper.Mapper;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;
import api_rest_mockito.jUnit5.e.Mockito.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    public static final Long ID = 1L;
    public static final String NAME = "Victor";
    public static final String EMAIL = "Victor@gmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;

    @InjectMocks
    private UserController userController;
    @Mock
    private Mapper mapper;
    @Mock
    private UserService userService;
    private User user;
    private UserDto userDto;
    private Optional<User> optionalUser;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        StartUser();
    }
    @Test
    void whenFindByIdThenReturnSucess() {
        Mockito.when(userService.findByid(Mockito.anyLong())).thenReturn(user);
        Mockito.when(mapper.toDto(Mockito.any())).thenReturn(userDto);

        ResponseEntity<UserDto>response = userController.findById(ID);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(UserDto.class,response.getBody().getClass());

        Assertions.assertEquals(ID,response.getBody().getId());
        Assertions.assertEquals(NAME,response.getBody().getName());
        Assertions.assertEquals(EMAIL,response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD,response.getBody().getPassword());

    }

    @Test
    void whenfindAllThenReturnSucess() {
        Mockito.when(userService.findAll()).thenReturn(List.of(user));
        Mockito.when(mapper.toDtoList(Mockito.anyList())).thenReturn(List.of(userDto));
        ResponseEntity<List<UserDto>> response = userController.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(UserDto.class,response.getBody().get(INDEX).getClass());

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(ID,response.getBody().get(INDEX).getId());
        Assertions.assertEquals(NAME,response.getBody().get(INDEX).getName());
        Assertions.assertEquals(EMAIL,response.getBody().get(INDEX).getEmail());
        Assertions.assertEquals(PASSWORD,response.getBody().get(INDEX).getPassword());

    }

    @Test
    void whenCreateThenReturnSucess() {
       Mockito.when(userService.create(Mockito.any())).thenReturn(user);

        ResponseEntity<UserDto> response = userController.create(userDto);
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());

        Assertions.assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateByIdThenReturnSucess() {
        Mockito.when(userService.update(Mockito.any(),Mockito.anyLong())).thenReturn(user);
        Mockito.when(mapper.toDto(Mockito.any())).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.updateById(ID,userDto);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(UserDto.class,response.getBody().getClass());

        Assertions.assertEquals(ID,response.getBody().getId());
        Assertions.assertEquals(NAME,response.getBody().getName());
        Assertions.assertEquals(EMAIL,response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD,response.getBody().getPassword());
    }

    @Test
    void delete() {
        Mockito.doNothing().when(userService).delete(Mockito.anyLong());
        ResponseEntity<UserDto> response = userController.delete(Mockito.anyLong());

        Mockito.verify(userService,Mockito.times(1)).delete(Mockito.anyLong());

        Assertions.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class,response.getClass());



    }
    private void StartUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));

    }
}