# Java Mini-Blockchain Ledger 🔗

A custom-built, decentralized blockchain engine developed in Java. This project implements the core mechanics of a blockchain network from scratch, applying advanced data structures and object-oriented programming principles.

## 🚀 Key Features

* **Proof of Work (PoW) Consensus:** Features a mining mechanism with adjustable difficulty, utilizing the `SHA-256` hashing algorithm to secure blocks and prevent tampering.
* **Asymmetric Cryptography:** Integrates digital wallets using `RSA` encryption (2048-bit). Each wallet generates a unique Public/Private key pair.
* **Secure Transactions:** Implements verifiable digital signatures. Transactions are strictly processed only if the sender's signature is cryptographically verified against their public key, ensuring complete fund security.
* **Immutable Ledger:** Utilizes linked-list architecture where each block is cryptographically bound to the previous one, rendering historical data immutable.

## 🛠️ Technology Stack
* **Language:** Java
* **Security:** `java.security` (MessageDigest, KeyPairGenerator, Signature, SecureRandom)
* **Encoding:** Base64, Hexadecimal manipulation
