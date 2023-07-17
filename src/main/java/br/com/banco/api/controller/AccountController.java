package br.com.banco.api.controller;

import br.com.banco.api.controller.request.CreateAccountRequest;
import br.com.banco.api.controller.request.CreateBankStatementRequest;
import br.com.banco.api.controller.request.CreateTransactionRequest;
import br.com.banco.api.controller.request.HttpRequest;
import br.com.banco.api.controller.response.*;
import br.com.banco.application.dto.AccountDto;
import br.com.banco.application.dto.BankStatementDto;
import br.com.banco.application.dto.TransactionDto;
import br.com.banco.application.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final IAccountService service;
    private final ModelMapper mapper;
    private final HttpServletRequest request;

    @Autowired
    public AccountController(IAccountService service, ModelMapper mapper, HttpServletRequest request) {
        this.service = service;
        this.mapper = mapper;
        this.request = request;
    }

    @GetMapping("/{id}")
    public HttpResponse<GetAccountResponse> getById(@PathVariable @Valid Long id){
        HttpRequest<GetAccountResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.GET;
        AccountDto accountDto = service.getById(id);
        return httpRequest.sendRequest(mapper.map(accountDto, GetAccountResponse.class), url, method);
    }

    @GetMapping("/getByName/{nameOperator}")
    public HttpResponse<GetAccountResponse> getByOperatorName(@PathVariable @Valid String nameOperator){
        HttpRequest<GetAccountResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.GET;
        AccountDto accountDto = service.getByOperatorName(nameOperator);
        return httpRequest.sendRequest(mapper.map(accountDto, GetAccountResponse.class), url, method);
    }

    @GetMapping("/getByEmail/{email}")
    public HttpResponse<GetAccountResponse> getByOperatorEmail(@PathVariable @Valid String email){
        HttpRequest<GetAccountResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.GET;
        AccountDto accountDto = service.getByOperatorEmail(email);
        return httpRequest.sendRequest(mapper.map(accountDto, GetAccountResponse.class), url, method);
    }

    @GetMapping("/getTransactions/{operatorName}")
    public HttpResponse<List<GetTransactionResponse>> create(@PathVariable @Valid String operatorName){
        HttpRequest<List<GetTransactionResponse>> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.GET;
        List<TransactionDto> transactionDto = service.getTransactions(operatorName);
        return httpRequest.sendRequest(transactionDto.stream()
                .map(x -> mapper.map(x, GetTransactionResponse.class)).collect(Collectors.toList()), url, method);
    }

    @PostMapping()
    public HttpResponse<CreateAccountResponse> create(@RequestBody @Valid CreateAccountRequest createRequest){
        HttpRequest<CreateAccountResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.POST;
        AccountDto accountDto = service.save(mapper.map(createRequest, AccountDto.class));
        return httpRequest.sendRequest(mapper.map(accountDto, CreateAccountResponse.class), url, method);
    }

    @PostMapping("/addStatements/{operatorEmail}")
    public HttpResponse<GetAccountResponse> addStatements(@RequestBody @Valid CreateBankStatementRequest createRequest, @PathVariable @Valid String operatorEmail){
        HttpRequest<GetAccountResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.POST;
        AccountDto accountDto = service.addBankStatements(mapper.map(createRequest, BankStatementDto.class), operatorEmail);
        return httpRequest.sendRequest(mapper.map(accountDto, GetAccountResponse.class), url, method);
    }

    @PostMapping("/addTransactions/{operatorEmail}")
    public HttpResponse<GetAccountResponse> addTransactions(@RequestBody @Valid CreateTransactionRequest createRequest, @PathVariable @Valid String operatorEmail){
        HttpRequest<GetAccountResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.POST;
        AccountDto accountDto = service.addTransaction(mapper.map(createRequest, TransactionDto.class), operatorEmail);
        return httpRequest.sendRequest(mapper.map(accountDto, GetAccountResponse.class), url, method);
    }
}
