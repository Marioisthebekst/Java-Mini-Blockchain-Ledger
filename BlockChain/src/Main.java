public class Main {
    public static void main(String[] args) {
        BlockChain bc = new BlockChain();

        Wallet walletA = new Wallet();
        Wallet walletB = new Wallet();

        System.out.println("Wallet A (Sender) initialized.");
        System.out.println("Wallet B (Recipient) initialized.");

        System.out.println("\nCreating a new transaction (50.5 coins from A to B)...");
        Transaction tx = new Transaction(walletA.publicKey, walletB.publicKey, 50.5f);

        System.out.println("Is transaction signature valid before signing? " + tx.verifySignature());

        tx.generateSignature(walletA.privateKey);
        System.out.println("Transaction signed by Wallet A.");

        System.out.println("Is transaction signature valid after signing? " + tx.verifySignature());

        if (tx.verifySignature()) {
            System.out.println("\n--- Mining a new block with the transaction ---");
            String blockData = "TxID: " + tx.transactionId + " | Sent: " + tx.amount + " coins";
            bc.addBlock(blockData);
        }
        System.out.println("\nIs the entire blockchain valid? " + bc.isChainValid());
    }
}