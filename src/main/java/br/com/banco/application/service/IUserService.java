package br.com.banco.application.service;

import br.com.banco.application.dto.UserDto;

public interface IUserService {
    UserDto save(UserDto dto);
    UserDto getById(Long id);
    UserDto getByName(String name);
    UserDto update(UserDto dto);
    void delete(Long id);
}
