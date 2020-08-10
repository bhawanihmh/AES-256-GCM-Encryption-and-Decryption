# AES-GCM-Encryption-and-Decryption
Advanced Encryption Standard (AES) algorithm in Galois Counter Mode (GCM), known as AES-GCM.
</br>
AES-GCM is a block cipher mode of operation that provides high speed of authenticated encryption and data integrity. In GCM mode, the block encryption is transformed into stream encryption, and therefore no padding is needed. The Additional Authenticated Data (AAD) will not be encrypted but used in the computation of Authentication Tag. The authenticated encryption operation takes Initialization Vector (IV), Additional Authenticated Data (AAD), Secret Key and 128-bit plaintext and gives a 128-bit ciphertext and authentication tag
</br>
Before getting into the implementation of AES GCM encryption algorithm, let’s first understand the basic difference between AES CBC and AES GCM.
</br>
## Difference between AES-CBC and AES-GCM?
Both GCM and CBC Modes involves a block cipher and an exclusive-or (XOR) but internally they both work in a different manner. Let’s understand the difference between them.
</br>
In CBC mode, you encrypt a block of data by taking the current plaintext block and XOR’ing with the previous ciphertext block and which cannot be written in parallel, this significantly affects the performance of AES-CBC encryption and AES-CBC also is vulnerable to padding oracle attacks.
</br>
GCM mode maintains a counter for each block of data and sends the current value of the counter to the block cipher and the output of the block cipher is XOR’ed with the plain text to get the ciphertext. The counter mode of operation is designed to turn block ciphers into stream ciphers. AES GCM is written in parallel and each block with AES GCM can be encrypted independently, hence the performance is significantly higher than AES CBC.
</br>
## Installing Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files
We need to manually download the JCE Policy files for Java 6, Java 7 and Java 8.
</br>
1. JCE for different versions of Java can be downloaded from the Oracle download page: </br> https://www.oracle.com/technetwork/java/javase/downloads/jce-all-download-5170447.html
2. Download the Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy File for the JVM which you have installed.
	* jce_policy-6.zip for Java 6
	* UnlimitedJCEPolicyJDK7.zip for Java 7
	* jce_policy-8.zip for Java 8
3. Unzip the downloaded policy zip file.
4. Copy local_policy.jar and US_export_policy.jar to the $JAVA_HOME/jre/lib/security, these jars will be already present and we need to overwrite them.
</br>
From Java 9 onwards default JCE policy files bundled in this Java Runtime Environment allow for “unlimited” cryptographic strengths.
</br>

## Example Code
AESGCMTest.java


## Some links:
https://proandroiddev.com/security-best-practices-symmetric-encryption-with-aes-in-java-7616beaaade9
</br>
https://www.javainterviewpoint.com/aes-256-encryption-and-decryption/
</br>
https://nullbeans.com/how-to-encrypt-decrypt-files-byte-arrays-in-java-using-aes-gcm
</br>
