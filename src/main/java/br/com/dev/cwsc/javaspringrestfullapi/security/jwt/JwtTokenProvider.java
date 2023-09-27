package br.com.dev.cwsc.javaspringrestfullapi.security.jwt;

import br.com.dev.cwsc.javaspringrestfullapi.exceptions.InvalidJwtAuthenticationException;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.security.TokenVO;
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
    // Busca o valor no arquivo application.yml
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds; // 1h

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct // Métodos com essa annotation executam primeiro assim que o spring inicializa
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes()); // Encripta a secretKey
        algorithm = Algorithm.HMAC256(secretKey.getBytes()); // Algoritmo que utiliza a secretKey para encriptar e decriptar os tokens
    }

    public TokenVO createAccessToken(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getRefreshToken(username, roles, now);

        return new TokenVO(username, true, now, validity, accessToken, refreshToken);
    }


    public TokenVO refreshToken(String refreshToken) {
        if (refreshToken.contains("Bearer ")) refreshToken =
                refreshToken.substring("Bearer ".length());

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        return createAccessToken(username, roles);
    }

    // Gera um token de acesso
    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().build().toUriString(); // Define a URL de onde o token foi gerado (URL do servidor)
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .strip();
    }

    // Gera um token de acesso e define a validade para 3 horas
    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .strip();
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = getDecodedToken(token);
        UserDetails userDetails = this.userDetailsService
                .loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Decodifica o token e retorna um objeto que pode ser manipulado
    private DecodedJWT getDecodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes()); // Precisa do mesmo algoritmo usado na criaçao do token
        JWTVerifier verifier = JWT.require(alg).build();
        return verifier.verify(token); // Retorna o token decodificado caso a assinatura seja válida
    }

    // Verifica se o token tem um formato válido
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization"); // Extrai o token do cabeçalho da requisição

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    // Verifica se o token é válido e não expirou
    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = getDecodedToken(token);
        try {
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token!");
        }
    }
}