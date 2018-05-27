package test;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import utils.CryptoAES;

public class main {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertificateException, KeyStoreException, IOException  {
		CryptoAES crypto = new CryptoAES();
		crypto.geraChave(new File("chave.simetrica"));

	}

}
