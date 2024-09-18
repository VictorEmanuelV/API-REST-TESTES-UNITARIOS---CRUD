package api_rest_mockito.jUnit5.e.Mockito.dto.mapper;

import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;

public interface Mapper {
    public User toUser(UserDto userDto);
    public UserDto toDto(User user);
}
