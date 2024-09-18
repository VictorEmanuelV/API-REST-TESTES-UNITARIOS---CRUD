package api_rest_mockito.jUnit5.e.Mockito.service;

import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;

import java.util.List;

public interface UserService {
    public User findByid(Long id);
    public List<User> findAll();
    public User create(UserDto dto);
    public User update(UserDto userDto,Long id);
    public void delete(Long id);
}
