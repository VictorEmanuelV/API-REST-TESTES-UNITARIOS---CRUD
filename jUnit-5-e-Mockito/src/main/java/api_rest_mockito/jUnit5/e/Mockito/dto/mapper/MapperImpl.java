package api_rest_mockito.jUnit5.e.Mockito.dto.mapper;

import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperImpl implements Mapper {
    @Override
    public User toUser(UserDto userDto) {
        return new ModelMapper().map(userDto, User.class);
    }

    @Override
    public UserDto toDto(User user) {
        return new ModelMapper().map(user, UserDto.class);
    }

    @Override
    public List<UserDto> toDtoList(List<User> list) {
        return
                list.stream().map
                        (user -> new ModelMapper().map
                                (user, UserDto.class)).collect(Collectors.toList());
    }
}
