package jtw.jtwback.com;

import java.time.LocalDateTime;
import java.util.Objects;

public class Block {
    private int index;
    private LocalDateTime timestamp;
    private String data; // 민감 데이터 (예: 암호화된 텍스트)
    private String previousHash;
    private String hash;

    public Block(int index, String data, String previousHash) {
        this.index = index;
        this.timestamp = LocalDateTime.now();
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = index + timestamp.toString() + data + previousHash;
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(input);
    }

    // Getters
    public int getIndex() { return index; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getData() { return data; }
    public String getPreviousHash() { return previousHash; }
    public String getHash() { return hash; }
}
