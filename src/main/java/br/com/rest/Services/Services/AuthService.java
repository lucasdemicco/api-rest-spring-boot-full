package br.com.rest.Services.Services;

import br.com.rest.Domain.Dtos.V1.AccountCredentialsDto;
import br.com.rest.Domain.Dtos.V1.TokenDto;
import br.com.rest.Handler.Exceptions.InvalidJwtAuthenticateException;
import br.com.rest.Repositories.UserRepository;
import br.com.rest.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity signin(AccountCredentialsDto accountCredentialsDto) {
        try {
            var username = accountCredentialsDto.getUserName();
            var password = accountCredentialsDto.getPassword();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUserName(username);
            var tokenResponse = new TokenDto();

            if(user == null){
                throw new UsernameNotFoundException("Username; " + username );
            }
            tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw  new BadCredentialsException("username/password inválido");
        }
    }
}
