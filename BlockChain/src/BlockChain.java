import java.security.PublicKey;
import java.util.ArrayList;

public class BlockChain {
    private ArrayList<Block> chain;
    private int difficulty;
    private AccountState accountState;
    public BlockChain() {
        this.chain = new ArrayList<Block>();
        this.difficulty = 6;
        this.accountState = new AccountState();
        addGenesisBlock();
    }

    private void addGenesisBlock() {
        this.chain.add(new Block("Genesis Block", "0"));
    }

    public Block getLatestBlock() {
        return this.chain.get(chain.size() - 1);
    }

    public void addBlock(String data) {
        String previousHash = getLatestBlock().getHash();
        Block newBlock = new Block(data, previousHash);
        newBlock.mineBlock(this.difficulty);
        this.chain.add(newBlock);
    }

    public boolean processTransaction(Transaction tx) {
        if (!tx.verifySignature()) {
            System.out.println("Transaction Signature failed to verify");
            return false;
        }
        if (!accountState.hasEnoughBalance(tx.sender, tx.amount)) {
            System.out.println("Not enough balance for transaction.");
            return false;
        }
        accountState.applyTransaction(tx);
        return true;
    }

    public float getBalance(PublicKey address) {
        return accountState.getBalance(address);
    }

    public void giveInitialCoins(PublicKey recipient, float amount) {
        Transaction initialTx = new Transaction(null, recipient, amount);
        this.accountState.applyTransaction(initialTx);
    }

    public boolean isChainValid() {
        for (int i = 1; i < this.chain.size(); i++) {
            if(!this.chain.get(i).calculateHash().equals(this.chain.get(i).getHash()) ||
            !this.chain.get(i).getPreviousHash().equals(this.chain.get(i - 1).getHash())) {
                return false;
            }
        }
        return true;
    }
}
