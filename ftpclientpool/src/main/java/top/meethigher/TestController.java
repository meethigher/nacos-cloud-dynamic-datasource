package top.meethigher;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.meethigher.ftp.client.pool.FTPClientPool;
import top.meethigher.ftp.client.pool.config.FTPPoolConfig;
import top.meethigher.ftp.client.pool.utils.FTPAutoReleaser;
import top.meethigher.ftp.client.pool.utils.FTPHandler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class TestController {


    @Resource
    private FTPClientPool ftpClientPool;

    @Resource
    private FTPAutoReleaser ftpAutoReleaser;

    @GetMapping("/test")
    public String test() {
        FTPPoolConfig poolConfig = ftpClientPool.getPoolConfig();
        String host = poolConfig.getHost();
        int port = poolConfig.getPort();
        String username = poolConfig.getUsername();
        String password = poolConfig.getPassword();
        return String.join(",", host, String.valueOf(port), username, password);
    }

    @GetMapping("/list")
    public List list() {
        Optional<List> optional = null;
        try {
            optional = ftpAutoReleaser.execute(ftpClient -> {
                FTPFile[] ftpFiles = ftpClient.listFiles();
                List<String> list = new ArrayList<>();
                for (FTPFile ftpFile : ftpFiles) {
                    list.add(ftpFile.getName());
                }
                return Optional.of(list);
            });
        } catch (Exception e) {
            return null;
        }
        return optional.get();
    }
}
