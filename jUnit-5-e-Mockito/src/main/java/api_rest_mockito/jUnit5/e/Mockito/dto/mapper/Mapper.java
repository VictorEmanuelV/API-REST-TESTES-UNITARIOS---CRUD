package api_rest_mockito.jUnit5.e.Mockito.dto.mapper;

import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;

import java.util.List;

public interface Mapper {
    public User toUser(UserDto userDto);
    public UserDto toDto(User user);
    public List<UserDto> toDtoList(List<User> list);
}
