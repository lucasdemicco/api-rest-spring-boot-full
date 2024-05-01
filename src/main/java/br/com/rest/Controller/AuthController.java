package br.com.rest.Controller;

import br.com.rest.Domain.Dtos.V1.AccountCredentialsDto;
import br.com.rest.Helper.StringHelper;
import br.com.rest.Services.Services.AuthService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin("/**")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signin")
    @ApiResponse(description = StringHelper.Ok, responseCode = StringHelper.Ok_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.NOT_FOUND, responseCode = StringHelper.NOT_FOUND_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.INTERNAL_SERVER_ERROR, responseCode = StringHelper.INTERNAL_SERVER_ERROR_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.UNAUTHORIZED, responseCode = StringHelper.UNAUTHORIZED_STATUS_CODE_VALUE, content = @Content)
    public ResponseEntity signin(@RequestBody AccountCredentialsDto accountCredentialsDto){
        if(accountCredentialsDto == null
                || (accountCredentialsDto.getUserName() == null || accountCredentialsDto.getUserName().isBlank())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Model de entrada inválido.");
        }

        var tokenResult = authService.signin(accountCredentialsDto);
        return tokenResult;
    }
}
