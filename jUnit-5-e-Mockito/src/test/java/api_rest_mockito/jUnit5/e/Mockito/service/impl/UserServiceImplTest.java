package api_rest_mockito.jUnit5.e.Mockito.service.impl;

import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.dto.mapper.Mapper;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;
import api_rest_mockito.jUnit5.e.Mockito.exception.ObjectNotFoundException;
import api_rest_mockito.jUnit5.e.Mockito.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    public static final Long ID = 1L;
    public static final String NAME = "Victor";
    public static final String EMAIL = "Victor@gmail.com";
    public static final String PASSWORD = "123";
    public static final String MESSAGE = "Objeto não encontrado";
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private Mapper mapper;

    private User user;
    private UserDto userDto;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        StartUser();
    }

    @Test
    void whenFindByidThenReturnUser() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(optionalUser);
        User response = userService.findByid(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class,response.getClass());
        Assertions.assertEquals(ID,response.getId());
        Assertions.assertEquals(NAME,response.getName());
        Assertions.assertEquals(EMAIL,response.getEmail());

    }
    @Test
    void whenFindByidThenReturnObjectNotFoundException(){
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(MESSAGE));
        try {
            userService.findByid(ID);
        }catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class,ex.getClass());
            Assertions.assertEquals(MESSAGE,ex.getMessage());
        }
    }

    @Test
    void findAll() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userService.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class,response.get(0).getClass());
        Assertions.assertEquals(1,response.size());

        Assertions.assertEquals(ID,response.get(0).getId());
        Assertions.assertEquals(NAME,response.get(0).getName());
        Assertions.assertEquals(EMAIL,response.get(0).getEmail());
        Assertions.assertEquals(PASSWORD,response.get(0).getPassword());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void StartUser(){
         user = new User(ID, NAME, EMAIL, PASSWORD);
         userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
         optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));

    }
}