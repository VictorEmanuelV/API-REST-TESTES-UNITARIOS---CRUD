package api_rest_mockito.jUnit5.e.Mockito.controller;
import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.dto.mapper.Mapper;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;
import api_rest_mockito.jUnit5.e.Mockito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
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

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto){
        User user = userService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> updateById(@PathVariable Long id,@RequestBody UserDto dto){
        User user  = userService.update(dto,id);
        return ResponseEntity.ok().body(mapper.toDto(user));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
