import java.util.ArrayList;

public class BlockChain {
    private ArrayList<Block> chain;
    private int difficulty;
    public BlockChain() {
        this.chain = new ArrayList<Block>();
        this.difficulty = 6;
        addGenesisBlock();
    }

    private void addGenesisBlock() {
        this.chain.add(new Block("Genesis Block", "0"));
    }

    public Block getLatestBlock() {
        return this.chain.get(chain.size() - 1);
    }

    public void addBlock(String data) {
        String previousHash = getLatestBlock().hash;
        Block newBlock = new Block(data, previousHash);
        newBlock.mineBlock(this.difficulty);
        this.chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < this.chain.size(); i++) {
            if(!this.chain.get(i).calculateHash().equals(this.chain.get(i).hash) ||
            !this.chain.get(i).previousHash.equals(this.chain.get(i - 1).hash)) {
                return false;
            }
        }
        return true;
    }
}