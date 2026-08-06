public class Main {
    public static void main(String[] args) {
        BlockChain bc = new BlockChain();

        Wallet walletA = new Wallet();
        Wallet walletB = new Wallet();

        System.out.println("Wallets initialized.");

        System.out.println("\nGiving Wallet A 100 initial coins...");
        bc.giveInitialCoins(walletA.publicKey, 100f);

        System.out.println("Wallet A balance: " + bc.getBalance(walletA.publicKey));
        System.out.println("Wallet B balance: " + bc.getBalance(walletB.publicKey));

        System.out.println("\nCreating a new transaction (50.5 coins from A to B)...");
        Transaction tx = new Transaction(walletA.publicKey, walletB.publicKey, 50.5f);
        tx.generateSignature(walletA.privateKey);

        boolean isProcessed = bc.processTransaction(tx);

        if (isProcessed) {
            System.out.println("Transaction valid and processed! Mining a new block...");
            String blockData = "TxID: " + tx.transactionId + " | Sent: " + tx.amount + " coins";
            bc.addBlock(blockData);
        } else {
            System.out.println("Transaction failed! Block not mined.");
        }

        System.out.println("\n--- Final Balances ---");
        System.out.println("Wallet A balance: " + bc.getBalance(walletA.publicKey));
        System.out.println("Wallet B balance: " + bc.getBalance(walletB.publicKey));

        System.out.println("\nIs the entire blockchain valid? " + bc.isChainValid());
    }
}
