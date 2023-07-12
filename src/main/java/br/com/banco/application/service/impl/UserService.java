package br.com.banco.application.service.impl;

import br.com.banco.application.dto.UserDto;
import br.com.banco.application.service.IUserService;
import br.com.banco.domain.entity.User;
import br.com.banco.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepository repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto save(UserDto dto) {
        repository.save(mapper.map(dto, User.class));
        return dto;
    }

    @Override
    public UserDto getById(Long id) {
        User user = repository.getById(id);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getByName(String name) {
        User user = repository.getByName(name);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto update(UserDto dto) {
        User user = repository.getByName(dto.firstName);
        user.setPassword(dto.password);
        user.setEmail(dto.email);
        user.setIncome(dto.income);
        repository.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
