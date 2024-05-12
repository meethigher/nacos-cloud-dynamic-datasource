package top.meethigher;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.meethigher.ftp.client.pool.FTPClientPool;
import top.meethigher.ftp.client.pool.config.FTPPoolConfig;
import top.meethigher.ftp.client.pool.factory.FTPClientFactory;
import top.meethigher.ftp.client.pool.utils.FTPAutoReleaser;

@Configuration
@RefreshScope
public class TestConfig {


    @Value("${ftp-pool.host}")
    private String host;

    @Value("${ftp-pool.port}")
    private Integer port;

    @Value("${ftp-pool.username}")
    private String username;

    @Value("${ftp-pool.password}")
    private String password;


    @Bean
    @RefreshScope
    public FTPClientPool ftpClientPool() {
        FTPPoolConfig config = new FTPPoolConfig();
        config.setHost(host);
        config.setPort(port);
        config.setUsername(username);
        config.setPassword(password);
        return new FTPClientPool(new FTPClientFactory(config));
    }

    @Bean
    @RefreshScope
    public FTPAutoReleaser ftpAutoReleaser(@Qualifier("ftpClientPool") FTPClientPool ftpClientPool) {
        return new FTPAutoReleaser(ftpClientPool);
    }
}
