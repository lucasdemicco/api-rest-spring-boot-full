package br.com.rest.Security;

import br.com.rest.Domain.Dtos.V1.TokenDto;
import br.com.rest.Handler.Exceptions.InvalidJwtAuthenticateException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    //Caso não estejam presentes no properties seta os valores abaixo como default
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilleseconds = 3600000; //1 hora

    @Autowired
    private UserDetailsService detailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDto createAccessToken(String userName, List<String> roles){

        Date now = new Date(); //momento atual
        Date validity = new Date(now.getTime() + validityInMilleseconds); //momento daqui a 1 hora
        var accessToken = getAccessToken(userName, roles, now, validity);
        var refreshToken = getRefreshToken(userName, roles, now);
        return new TokenDto(userName, true, now, validity,  accessToken, refreshToken);
    }

    private String getRefreshToken(String userName, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + (validityInMilleseconds * 3));
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(userName)
                .sign(algorithm)
                .strip();
    }

    private String getAccessToken(String userName, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(userName)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .strip();
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.detailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier jwtVerifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }

    // Valida se Header enviou um Bearer token válido para autenticação
    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken == null || !bearerToken.startsWith("Bearer")){
            return null;
        }

        return bearerToken.substring("Bearer ".length()); //Retorna a String sem o 'Bearer '
    }

    public boolean validateToken(String token) throws InvalidJwtAuthenticateException {
        try{
            DecodedJWT decodedJWT = decodedToken(token);
            return !decodedJWT.getExpiresAt().before(new Date());
        }catch (Exception e){
            throw new InvalidJwtAuthenticateException("JWT inválido ou expirado");
        }
    }
}
