package br.com.banco.api.controller;

import br.com.banco.api.controller.request.CreateBankStatementRequest;
import br.com.banco.api.controller.request.HttpRequest;
import br.com.banco.api.controller.response.BankStatementResponse;
import br.com.banco.api.controller.response.HttpResponse;
import br.com.banco.application.dto.BankStatementDto;
import br.com.banco.application.service.IBankStatementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bankStatements")
public class BankStatementController {
    private final IBankStatementService service;
    private final ModelMapper mapper;
    private final HttpServletRequest request;

    @Autowired
    public BankStatementController(IBankStatementService service, ModelMapper mapper, HttpServletRequest request){
        this.service = service;
        this.mapper = mapper;
        this.request = request;
    }

    @GetMapping("/all/{nameOperator}")
    public HttpResponse<List<BankStatementResponse>> getAll(@PathVariable String nameOperator){
            HttpRequest<List<BankStatementResponse>> httpRequest = new HttpRequest<>();
            String url = request.getRequestURL().toString();
            HttpMethod method = HttpMethod.GET;
            List<BankStatementDto> statementsDto = service.getAll(nameOperator);
            return httpRequest.sendRequest(statementsDto.stream()
                    .map(x -> mapper.map(x, BankStatementResponse.class)).collect(Collectors.toList()), url, method);
    }

    @GetMapping("/creationDate/{date}")
    public HttpResponse<List<BankStatementResponse>> getByCreationDate(@PathVariable LocalDate date){
        HttpRequest<List<BankStatementResponse>> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.GET;
        List<BankStatementDto> statementsDto = service.getByCreationDate(date);
        return httpRequest.sendRequest(statementsDto.stream()
                .map(x -> mapper.map(x, BankStatementResponse.class)).collect(Collectors.toList()), url, method);
    }

    @GetMapping("/create")
    public HttpResponse<BankStatementResponse> getByStartAndEndDate(@RequestBody CreateBankStatementRequest requestB){
        HttpRequest<BankStatementResponse> httpRequest = new HttpRequest<>();
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.GET;
        BankStatementDto statementsDto = service.create(requestB.start, requestB.end, requestB.nameOperator);
        return httpRequest.sendRequest(mapper.map(statementsDto, BankStatementResponse.class), url, method);
    }
}
