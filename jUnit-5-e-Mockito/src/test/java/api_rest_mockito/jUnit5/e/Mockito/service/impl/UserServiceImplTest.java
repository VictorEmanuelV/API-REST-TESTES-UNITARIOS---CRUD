package api_rest_mockito.jUnit5.e.Mockito.service.impl;

import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.dto.mapper.Mapper;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;
import api_rest_mockito.jUnit5.e.Mockito.exception.DataIntegratyViolationException;
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

@SpringBootTest
class UserServiceImplTest {
    public static final Long ID = 1L;
    public static final String NAME = "Victor";
    public static final String EMAIL = "Victor@gmail.com";
    public static final String PASSWORD = "123";
    public static final String MESSAGE = "Objeto não encontrado";
    public static final int INDEX = 0;
    public static final String JA_CADASTRADO_NO_SISTEMA = "Email ja cadastrado no Sistema.";
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
        Assertions.assertEquals(User.class,response.get(INDEX).getClass());
        Assertions.assertEquals(1,response.size());

        Assertions.assertEquals(ID,response.get(INDEX).getId());
        Assertions.assertEquals(NAME,response.get(INDEX).getName());
        Assertions.assertEquals(EMAIL,response.get(INDEX).getEmail());
        Assertions.assertEquals(PASSWORD,response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnUser() {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        User response = userService.create(userDto);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class,response.getClass());
        Assertions.assertEquals(ID,response.getId());
        Assertions.assertEquals(NAME,response.getName());
        Assertions.assertEquals(EMAIL,response.getEmail());
        Assertions.assertEquals(PASSWORD,response.getPassword());
    }

    @Test
    void whenCreateThenReturnDataIntegratyViolationException(){
        Mockito.when(userRepository.findByEmail(Mockito.any())).thenThrow
                (new DataIntegratyViolationException(JA_CADASTRADO_NO_SISTEMA));
        try {
            userService.create(userDto);
        }catch (Exception ex){
            Assertions.assertEquals(DataIntegratyViolationException.class,ex.getClass());
            Assertions.assertEquals(JA_CADASTRADO_NO_SISTEMA,ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnUser(){
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        User response = userService.update(userDto,ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ID,response.getId());
        Assertions.assertEquals(NAME,response.getName());
        Assertions.assertEquals(EMAIL,response.getEmail());
        Assertions.assertEquals(PASSWORD,response.getPassword());



    }
    @Test
    void whenUpdateThenReturnDataIntegratyViolationException() {
        Mockito.when(userRepository.save(Mockito.any())).thenThrow(new DataIntegratyViolationException(JA_CADASTRADO_NO_SISTEMA));
        try {
            userService.update(userDto,ID);
        }catch (Exception ex){
            Assertions.assertEquals(JA_CADASTRADO_NO_SISTEMA,ex.getMessage());
            Assertions.assertEquals(DataIntegratyViolationException.class,ex.getClass());
        }
    }

    @Test
    void whenDeleteThenReturnSucess() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(optionalUser);
        Mockito.doNothing().when(userRepository).deleteById(Mockito.anyLong());
        userService.delete(ID);

        Mockito.verify(userRepository,Mockito.times(1)).deleteById(Mockito.anyLong());

    }
    @Test
    void whenDeleteThenReturnObjectNotFoundException(){
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(MESSAGE));
        try {
            userService.delete(ID);
        }catch(Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class,ex.getClass());
            Assertions.assertEquals(MESSAGE,ex.getMessage());
        }
    }

    private void StartUser(){
         user = new User(ID, NAME, EMAIL, PASSWORD);
         userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
         optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));

    }
}