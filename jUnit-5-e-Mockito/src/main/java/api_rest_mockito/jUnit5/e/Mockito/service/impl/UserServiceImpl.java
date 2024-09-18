package api_rest_mockito.jUnit5.e.Mockito.service.impl;

import api_rest_mockito.jUnit5.e.Mockito.dto.UserDto;
import api_rest_mockito.jUnit5.e.Mockito.dto.mapper.Mapper;
import api_rest_mockito.jUnit5.e.Mockito.entity.User;
import api_rest_mockito.jUnit5.e.Mockito.exception.DataIntegratyViolationException;
import api_rest_mockito.jUnit5.e.Mockito.exception.ObjectNotFoundException;
import api_rest_mockito.jUnit5.e.Mockito.repository.UserRepository;
import api_rest_mockito.jUnit5.e.Mockito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Mapper mapper;
    @Override
    public User findByid(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User create(UserDto dto) {
        findByEmail(dto);
        User user = mapper.toUser(dto);
        return userRepository.save(user);
    }

    @Override
    public User update(UserDto userDto,Long id) {
        userDto.setId(id);
        findByEmail(userDto);

        User user = mapper.toUser(userDto);
       return userRepository.save(user);

    }

    @Override
    public void delete(Long id) {
        findByid(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDto dto){
        Optional<User> user= userRepository.findByEmail(dto.getEmail());
        if (user.isPresent() && !user.get().getId().equals(dto.getId())){
            throw new DataIntegratyViolationException("Email ja cadastrado no Sistema.");
        }
    }
}
