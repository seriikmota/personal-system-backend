package br.ueg.personalsystem.service;

public interface ISignatureService {
    byte[] sign(String filename, byte[] documentBytes);
    String validateSignature(String filename, byte[] documentBytes);
}
