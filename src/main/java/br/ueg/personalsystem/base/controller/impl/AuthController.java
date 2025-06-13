package br.ueg.personalsystem.base.controller.impl;

import br.ueg.personalsystem.base.config.Constants;
import br.ueg.personalsystem.base.controller.IAuthController;
import br.ueg.personalsystem.base.dto.AuthDTO;
import br.ueg.personalsystem.base.dto.CredentialDTO;
import br.ueg.personalsystem.base.service.impl.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/auth")
public class AuthController extends AbstractController implements IAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO authDTO) {
        CredentialDTO credential = authService.login(authDTO);
        return ResponseEntity.ok(credential);
    }

    @GetMapping(path = "/refresh")
    public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
        CredentialDTO credential = authService.refresh(refreshToken);
        return ResponseEntity.ok(credential);
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader(Constants.HEADER_AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(Constants.HEADER_AUTHORIZATION_BEARER)) {
            authService.logout(authHeader);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        authService.sendPasswordRecoveryEmail(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/verify-code")
    public ResponseEntity<?> verifyCode(@RequestParam String email, @RequestParam String code) {
        authService.validateResetCode(email, code);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/change-password")
    public ResponseEntity<?> changePassword(@RequestParam String email, @RequestParam String code, @RequestParam String password) {
        authService.changePassword(email, code, password);
        return ResponseEntity.ok().build();
    }
}
