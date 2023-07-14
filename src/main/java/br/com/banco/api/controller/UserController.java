package br.com.banco.api.controller;

import br.com.banco.api.controller.request.CreateUserRequest;
import br.com.banco.api.controller.request.HttpRequest;
import br.com.banco.api.controller.request.UpdateUserRequest;
import br.com.banco.api.controller.response.CreateUserResponse;
import br.com.banco.api.controller.response.GetUserResponse;
import br.com.banco.api.controller.response.HttpResponse;
import br.com.banco.application.dto.UserDto;
import br.com.banco.application.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService service;
    private final ModelMapper mapper;
    private final HttpServletRequest request;

    @Autowired
    public UserController(IUserService service, ModelMapper mapper, HttpServletRequest request){
        this.service = service;
        this.mapper = mapper;
        this.request = request;
    }

    @GetMapping("/{id}")
    public HttpResponse<GetUserResponse> getById(@PathVariable Long id){
        HttpRequest<GetUserResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.GET;
        UserDto userDto = service.getById(id);
        return httpRequest.sendRequest(mapper.map(userDto, GetUserResponse.class), url, method);
    }

    @GetMapping("/getByName/{name}")
    public HttpResponse<GetUserResponse> getNameId(@PathVariable String name){
        HttpRequest<GetUserResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.GET;
        UserDto userDto = service.getByName(name);
        return httpRequest.sendRequest(mapper.map(userDto, GetUserResponse.class), url, method);
    }

    @PostMapping("/create")
    public HttpResponse<CreateUserResponse> create(@RequestBody CreateUserRequest createRequest){
        HttpRequest<CreateUserResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.POST;
        UserDto userDto = service.save(mapper.map(createRequest, UserDto.class));
        return httpRequest.sendRequest(mapper.map(userDto, CreateUserResponse.class), url, method);
    }

    @PutMapping("/update")
    public HttpResponse<GetUserResponse> create(@RequestBody UpdateUserRequest updateRequest){
        HttpRequest<GetUserResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.PUT;
        UserDto userDto = service.update(mapper.map(updateRequest, UserDto.class));
        return httpRequest.sendRequest(mapper.map(userDto, GetUserResponse.class), url, method);
    }

    @DeleteMapping("/delete/{id}")
    HttpResponse<Void> create(@PathVariable Long id) {
        HttpRequest<Void> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.DELETE;
        service.delete(id);
        return httpRequest.sendRequest(null, url, method);
    }
}
