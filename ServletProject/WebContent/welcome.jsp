<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

const encryptText = async () => {

	  const messageOriginalDOMString = document.getElementById("originalMessage").value;

	  //
	  // Encode the original data
	  //

	  const encoder = new TextEncoder();
	  const messageUTF8 = encoder.encode(messageOriginalDOMString);

	  //
	  // Configure the encryption algorithm to use
	  //

	  //Generate IV - 12bit Random values
	 //const iv = window.crypto.getRandomValues(new Uint8Array(12));
	 const iv = base64ToArrayBuffer('DuWObJpjGijl6mxB');
	  const algorithm = {
	    iv,
	    name: 'AES-GCM',
	  };
	  
	 // encIV.innerHTML = arrayBufferToBase64(iv);
	  
	  //
	  // Generate/fetch the cryptographic key
	  //

	  /* const key = await window.crypto.subtle.generateKey({
	      name: 'AES-GCM',
	      length: 128
	    },
	    true, [
	      'encrypt',
	      'decrypt'
	    ]
	  ); 
	  console.log('Key :: '+arrayBufferToBase64(key)); */
	  //
	  // Run the encryption algorithm with the key and data.
	  //

		
	  //
	  // Export Key
	  //
	  /* const exportedKey = await window.crypto.subtle.exportKey(
	    'raw',
	    key,
	  );
	  encKey.innerHTML = arrayBufferToBase64(exportedKey);
	  console.log('exportedKey :: '+arrayBufferToBase64(exportedKey)); */
	  // This is where to save the exported key to the back-end server,
	  // and then to fetch the exported key from the back-end server.

	  //
	  // Import Key
	  //
	  const key = base64ToArrayBuffer('0Usd2xqrLnjXJjcMKFb2T6SrWTag8FSXzAIfnHCIY24=');
	 // encKey.innerHTML = arrayBufferToBase64(key);
	  const importedKey = await window.crypto.subtle.importKey(
	    'raw',
	    key,
	    "AES-GCM",
	    true, [
	      "encrypt",
	      "decrypt"
	    ]
	  );
	  
	  /* const keys = await window.crypto.subtle.unwrapKey(
		'raw',
		keyArray,
		importedKey,
		algorithm,
	    {
	      name: 'AES-GCM',
	      length: 256
		},
	    true, [
	      'encrypt',
	      'decrypt',
	      'wrapKey',
	      'unwrapKey'
	    ]
	  );  */
	  //
	  // Run the decryption algorithm with the key and cyphertext.
	  //
	  const messageEncryptedUTF8 = await window.crypto.subtle.encrypt(
	    algorithm,
	    importedKey,
	    messageUTF8,
	  );
	  
	 //Sending to the server to display
	 $('#clientEncrypted').val(arrayBufferToBase64(messageEncryptedUTF8));
	 
	 document.encForm.submit();
	}
	
	const decryptText = async () => {

	  const encryptedMessage = document.getElementById("encryptedMessage").value;

	  //
	  // Encode the original data
	  //
	  const encoder = new TextEncoder();
	  const messageEncryptedUTF8 = base64ToArrayBuffer(encryptedMessage);
	  
	  //
	  // Configure the encryption algorithm to use
	  //

	 //const iv = window.crypto.getRandomValues(new Uint8Array(12));
	 const iv = base64ToArrayBuffer('DuWObJpjGijl6mxB');
	  const algorithm = {
	    iv,
	    name: 'AES-GCM',
	  };
	 // encIV.innerHTML = arrayBufferToBase64(iv);
	  
	  //
	  // Import Key
	  //
	  const key = base64ToArrayBuffer('0Usd2xqrLnjXJjcMKFb2T6SrWTag8FSXzAIfnHCIY24=');

	  const importedKey = await window.crypto.subtle.importKey(
	    'raw',
	    key,
	    "AES-GCM",
	    true, [
	      "encrypt",
	      "decrypt"
	    ]
	  );
	  console.log('importedKey :: '+arrayBufferToBase64(importedKey));
	  
	  //
	  // Run the decryption algorithm with the key and cyphertext.
	  //
	  	  
	  const messageDecryptedUTF8 = await window.crypto.subtle.decrypt(
	    algorithm,
	    importedKey,
	    messageEncryptedUTF8,
	  );

	  //
	  // Decode the decryped data.
	  //

	  const decoder = new TextDecoder();
	  const messageDecryptedDOMString = decoder.decode(messageDecryptedUTF8);
	 
	  $('#clientDecrypted').val(messageDecryptedDOMString);
	  document.decForm.submit();
	  
	}
	
	function arrayBufferToBase64(buffer) {
	    let binary = '';
	    let bytes = new Uint8Array(buffer);
	    let len = bytes.byteLength;
	    for (let i = 0; i < len; i++) {
	        binary += String.fromCharCode(bytes[i]);
	    }
	    return window.btoa(binary);
	}
	
	function base64ToArrayBuffer(base64) {
	    var binary_string = window.atob(base64);
	    var len = binary_string.length;
	    var bytes = new Uint8Array(len);
	    for (var i = 0; i < len; i++) {
	        bytes[i] = binary_string.charCodeAt(i);
	    }
	    return bytes.buffer;
	}
	
</script>
<body>
<h1>AES GCM Encryption/Decryption</h1>

<h4>Encryption</h4>
<form action="/aesgcm/encrypt" name="encForm" id="encForm">
<input type="text" id="originalMessage" name="plainText"/>
<input type="hidden" name="clientEncrypted" id="clientEncrypted"/>
<button name="button" onclick="encryptText()">Encrypt</button>
</form>
<h4>Decryption</h4>
<form action="/aesgcm/decrypt" name="decForm" id="decForm">
<input type="text" id="encryptedMessage" name="encryptedText"/>
<input type="hidden" name="clientDecrypted" id="clientDecrypted"/>
<button name="button" onclick="decryptText()">Decrypt</button>
</form>
</body>
</html>
