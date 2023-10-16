package br.com.lucasrosasc.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.lucasrosasc.todolist.user.IUserRepository;
//servlet é a base para frameworks web em Java
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();
        System.out.println("PATH" + servletPath);
        //verificação da rota
        if (servletPath.startsWith("/tasks/")) {
            // Pegar a autenticação (usuario e senha)
            var authorization = request.getHeader("Authorization");

            /*
             * método para separar(subString) o valor do hash do usuário
             * e remover os espaços
             */
            var authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecoded);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];
            System.out.println("Authorization: " + "\n" + username + "\n" + password);
            // Validar usuário
            var user = this.userRepository.findByUsername(username);

            if (user == null) {
                /* 401 código de erro + msg padrão */
                response.sendError(401, "Usuário sem autorização");
            } else {
                // Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    //Segue viagem
                    //request abaixo é para pegar o idUser do input para autorização
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
                // Segue viagem

            }

        } else {
            filterChain.doFilter(request, response);
        }

    }

}
