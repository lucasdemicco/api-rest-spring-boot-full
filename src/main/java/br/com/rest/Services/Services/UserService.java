package br.com.rest.Services.Services;

import br.com.rest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    UserRepository repository;

    //Quando usamos Autowired e declaramos a injeção por construtor indica que será Required
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Iniciando busca de usuário por 'username'");
        var result = repository.findByUserName(username);
        if(result == null){
            final String ERROR_MESSAGE = "usuário não identificado";
            logger.warning(ERROR_MESSAGE + "com parâmetro: " + username);
            throw new UsernameNotFoundException(ERROR_MESSAGE);
        }

        return result;
    }
}
