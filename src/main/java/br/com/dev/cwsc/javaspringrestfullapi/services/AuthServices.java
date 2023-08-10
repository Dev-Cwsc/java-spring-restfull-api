package br.com.dev.cwsc.javaspringrestfullapi.services;

import br.com.dev.cwsc.javaspringrestfullapi.model.User;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.security.AccountCredentialsVO;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.security.TokenVO;
import br.com.dev.cwsc.javaspringrestfullapi.repositories.UserRepository;
import br.com.dev.cwsc.javaspringrestfullapi.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes") // Bloqueia as mensagens de aviso relacionadas a tipos brutos durante a compilação
    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            String username = data.getUsername();
            String password = data.getPassword();
            authenticationManager.authenticate( // Tenta fazer o login
                    new UsernamePasswordAuthenticationToken(username, password));

            User user = repository.findByUsername(username);

            TokenVO tokenResponse;

            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        User user = repository.findByUsername(username);
        TokenVO tokenResponse;

        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }

        return ResponseEntity.ok(tokenResponse);
    }
}
