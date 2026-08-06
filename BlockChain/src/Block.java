import java.util.Date;

public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;


    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String getHash() {
        return this.hash;
    }

    public String getPreviousHash() {
        return this.previousHash;
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
