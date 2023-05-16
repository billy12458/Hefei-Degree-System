package com.school.springboot.utils;

import javax.crypto.BadPaddingException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings("all")
public class RSAUtils {

    @Autowired
    private RSA rsaBean;

    public String getEncryptedContent(String originalString) {
        return rsaBean.encryptHex(originalString, KeyType.PrivateKey);
    }

    @SneakyThrows(RuntimeException.class)
    public String getDecryptedPassword(String encryptedContent) throws DecoderException {
        // return rsaBean.decryptFromHex(encryptedContent, KeyType.PublicKey);
        // System.err.println(new String(rsaBean.decryptFromHex(encryptedContent, KeyType.PublicKey)));
        // System.err.println(Hex.decodeHex(new String(rsaBean.decryptFromHex(encryptedContent, KeyType.PublicKey))));
        // System.err.println(rsaBean.decryptFromHex(encryptedContent, KeyType.PublicKey));
        return new String(rsaBean.decryptFromHex(encryptedContent, KeyType.PublicKey));
        // return Base64.(rsaBean.decryptFromBase64(encryptedContent, KeyType.PublicKey));
    }

}
