import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class Transaction {
    public PublicKey sender;
    public PublicKey recipient;
    public float amount;
    public byte[] signature;
    public String transactionId;

    public Transaction(PublicKey sender, PublicKey recipient, float amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.transactionId = StringUtil.applySha256(getData());
    }

    public void generateSignature(PrivateKey privateKey) {
        try {
            String data = getData();
            Signature rsa = Signature.getInstance("SHA256withRSA");
            rsa.initSign(privateKey);
            rsa.update(data.getBytes());
            this.signature = rsa.sign();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public boolean verifySignature() {
        if (this.signature == null) {
            return false;
        }

        try {
            String data = getData();
            Signature rsa = Signature.getInstance("SHA256withRSA");
            rsa.initVerify(sender);
            rsa.update(data.getBytes());
            return rsa.verify(this.signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getData() {
        return Base64.getEncoder().encodeToString(sender.getEncoded()) +
                Base64.getEncoder().encodeToString(recipient.getEncoded()) +
                Float.toString(amount);
    }
}
