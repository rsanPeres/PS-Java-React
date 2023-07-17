package br.com.banco.application.service.impl;

import br.com.banco.application.dto.UserDto;
import br.com.banco.application.service.IUserService;
import br.com.banco.domain.entity.User;
import br.com.banco.domain.exception.NotFoundByIdException;
import br.com.banco.domain.exception.NotFoundByNameException;
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
        if(user.getId().describeConstable().isPresent()){
            return mapper.map(user, UserDto.class);
        }else {
            throw new NotFoundByIdException("User not found by Id");
        }
    }

    @Override
    public UserDto getByName(String name) {
        User user = repository.getByName(name);
        if(user.getId().describeConstable().isPresent()){
            return mapper.map(user, UserDto.class);
        }else {
            throw new NotFoundByNameException("User not found by name");
        }
    }

    @Override
    public UserDto update(UserDto dto) {
        User user = repository.getByName(dto.firstName);
        if(user.getId().describeConstable().isPresent()) {
            user.setPassword(dto.password);
            user.setEmail(dto.email);
            user.setIncome(dto.income);
            repository.save(user);
            return mapper.map(user, UserDto.class);
        }else {
            throw new NotFoundByNameException("User not found by name");
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
