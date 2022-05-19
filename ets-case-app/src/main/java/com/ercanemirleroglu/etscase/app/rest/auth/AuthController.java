package com.ercanemirleroglu.etscase.app.rest.auth;

import com.ercanemirleroglu.etscase.app.authentication.JwtTokenUtil;
import com.ercanemirleroglu.etscase.mw.model.request.LoginRequest;
import com.ercanemirleroglu.etscase.mw.model.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authentication = authenticate(loginRequest.getUsername(),
                loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails appUser = (UserDetails) authentication.getPrincipal();

        String token = jwtTokenUtil
                .generateToken(appUser.getUsername());

        return ResponseEntity.ok(new LoginResponse().setAccessToken(token));
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.getObject().authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
