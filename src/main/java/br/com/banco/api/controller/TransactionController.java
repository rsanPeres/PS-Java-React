package br.com.banco.api.controller;

import br.com.banco.api.controller.request.CreateTransactionRequest;
import br.com.banco.api.controller.request.GetTransactionRequest;
import br.com.banco.api.controller.request.HttpRequest;
import br.com.banco.api.controller.response.CreateTransactionResponse;
import br.com.banco.api.controller.response.GetTransactionResponse;
import br.com.banco.api.controller.response.HttpResponse;
import br.com.banco.application.dto.TransactionDto;
import br.com.banco.application.dto.UserDto;
import br.com.banco.application.service.ITransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final ITransactionService service;
    private final ModelMapper mapper;
    private final HttpServletRequest request;

    @Autowired
    public TransactionController(ITransactionService service, ModelMapper mapper, HttpServletRequest request){
        this.service = service;
        this.mapper = mapper;
        this.request = request;
    }

    @PostMapping()
    public HttpResponse<CreateTransactionResponse> create(@RequestBody @Valid CreateTransactionRequest createRequest) {
        HttpRequest<CreateTransactionResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.POST;
        TransactionDto transactionDto = service.save(mapper.map(createRequest, TransactionDto.class));
        return httpRequest.sendRequest(mapper.map(transactionDto, CreateTransactionResponse.class), url, method);
    }

    @GetMapping()
    public HttpResponse<List<GetTransactionResponse>> getTransactionsByOperatorAndDateRange(@RequestBody @Valid GetTransactionRequest getRequest){
        HttpRequest<List<GetTransactionResponse>> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.GET;
        List<TransactionDto> listDto = service.getTransactionsByOperatorAndDateRange(getRequest.start, getRequest.end, new UserDto(getRequest.firstName, getRequest.lastName));
        return httpRequest.sendRequest(listDto.stream()
                .map(x -> mapper.map(x, GetTransactionResponse.class)).collect(Collectors.toList()), url, method);
    }
}
