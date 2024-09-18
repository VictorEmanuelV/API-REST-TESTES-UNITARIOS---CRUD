package api_rest_mockito.jUnit5.e.Mockito.service;

import api_rest_mockito.jUnit5.e.Mockito.entity.User;

public interface UserService {
    public User findByid(Long id);
}
