import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.security.MessageDigest;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;


    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String totalData = this.previousHash + this.data + this.timeStamp + this.nonce;
        return StringUtil.applySha256(totalData);

    }

    public void mineBlock(int difficulty) {
        String target = "";
        for(int i = 0; i < difficulty; i++) {
            target += "0";
        }

        while (!this.hash.startsWith(target)) {
            this.nonce +=1;
            hash = this.calculateHash();
        }

        System.out.println("Block Mined!!! Hash is: " + this.hash);

    }

}