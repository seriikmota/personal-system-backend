package br.ueg.personalsystem.base.security;

public interface IAuthenticationProvider {
    Credential getAuthentication(final String token);
}
