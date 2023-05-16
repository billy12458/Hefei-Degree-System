package com.school.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "ali")
public class AliPayProperties {
    /**
     * 支付宝APPID
     */
    public String appId;
    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    public String merchantPrivateKey;
    // public String privateKey;
    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com 对应APPID下的支付宝公钥。
     */
    public String alipayPublicKey;
    // public String publicKey;
    /**
     * 接口格式规范
     */
    public String format;
    /**
     * 签名方式
     */
    public String signType;
    /**
     * 字符编码格式
     */
    public String charset;
    /**
     * 支付宝网关 https://openapi.alipay.com/gateway.do 这是正式地址
     */
    public String gatewayUrl;

    @Bean(value = "alipayClient")
    public AlipayClient getAlipayClient() {
        AlipayClient alipayClient = new DefaultAlipayClient(
                this.gatewayUrl,
                this.appId,
                this.merchantPrivateKey,
                this.format,
                this.charset,
                this.alipayPublicKey,
                this.signType);
        return alipayClient;
    }

}