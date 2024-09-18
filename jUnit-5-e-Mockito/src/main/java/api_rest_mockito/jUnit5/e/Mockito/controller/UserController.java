package api_rest_mockito.jUnit5.e.Mockito.controller;
import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.dto.mapper.Mapper;
import api_rest_mockito.jUnit5.e.Mockito.dto.mapper.MapperImpl;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;
import api_rest_mockito.jUnit5.e.Mockito.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserService userService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto>findById(@PathVariable Long id){
        UserDto dto = mapper.toDto(userService.findByid(id));
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<User> userList = userService.findAll();
        List<UserDto>listdto = mapper.toDtoList(userList);

        return ResponseEntity.ok().body(listdto);
    }
}
