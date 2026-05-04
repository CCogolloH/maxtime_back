package com.carloslogol.maxtime.back.service.impl;

import com.carloslogol.maxtime.back.config.CryptoConfig;
import com.carloslogol.maxtime.back.dto.secreto.SecretoRequestDTO;
import com.carloslogol.maxtime.back.dto.secreto.SecretoResponseDTO;
import com.carloslogol.maxtime.back.model.UsuarioSecreto;
import com.carloslogol.maxtime.back.repository.UsuarioSecretoRepository;
import com.carloslogol.maxtime.back.service.SecretoService;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class SecretoServiceImpl implements SecretoService {

    private final UsuarioSecretoRepository repository;
    private final CryptoConfig cryptoConfig;

    private static final int SALT_SIZE = 16;
    private static final int IV_SIZE = 12;
    private static final int GCM_TAG_LENGTH = 128;

    public SecretoServiceImpl(UsuarioSecretoRepository repository, CryptoConfig cryptoConfig) {
        this.repository = repository;
        this.cryptoConfig = cryptoConfig;
    }

    @Override
    public SecretoResponseDTO saveSecreto(SecretoRequestDTO request) {
        String perfil = request.getPerfil();
        String usuarioEncriptado = encrypt(request.getUsuario());
        String contrasenaEncriptada = encrypt(request.getContrasena());

        UsuarioSecreto secreto = UsuarioSecreto.builder()
                .perfil(perfil)
                .usuario(usuarioEncriptado)
                .contrasena(contrasenaEncriptada)
                .build();

        UsuarioSecreto saved = repository.save(secreto);

        return new SecretoResponseDTO(
                saved.getPerfil(),
                saved.getUsuario(),
                saved.getContrasena()
        );
    }

    @Override
    public UsuarioSecreto obtenerPorPerfil(String perfil) {
        return repository.findByPerfil(perfil);
    }

    private String encrypt(String plainText) {
        try {
            SecureRandom random = new SecureRandom();

            byte[] salt = new byte[SALT_SIZE];
            random.nextBytes(salt);

            byte[] iv = new byte[IV_SIZE];
            random.nextBytes(iv);

            byte[] keyBytes = deriveKey(cryptoConfig.getMasterKey(), salt);
            SecretKey key = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);

            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            int tagLenBytes = GCM_TAG_LENGTH / 8;
            int cipherLen = encrypted.length - tagLenBytes;

            byte[] ciphertext = new byte[cipherLen];
            byte[] tag = new byte[tagLenBytes];

            System.arraycopy(encrypted, 0, ciphertext, 0, cipherLen);
            System.arraycopy(encrypted, cipherLen, tag, 0, tagLenBytes);

            Base64.Encoder enc = Base64.getEncoder();
            return enc.encodeToString(salt) + "."
                    + enc.encodeToString(iv) + "."
                    + enc.encodeToString(tag) + "."
                    + enc.encodeToString(ciphertext);

        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar", e);
        }
    }

    private byte[] deriveKey(String masterKey, byte[] salt) {
        return org.bouncycastle.crypto.generators.SCrypt.generate(
                masterKey.getBytes(StandardCharsets.UTF_8),
                salt,
                16384,
                8,
                1,
                32
        );
    }
}