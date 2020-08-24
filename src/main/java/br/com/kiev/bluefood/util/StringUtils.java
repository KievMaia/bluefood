package br.com.kiev.bluefood.util;

public class StringUtils {

	//Verifica se a String está vazia.
	public static boolean isEmpty(String str) {
		
		if (str == null) {
			return true;
		}
		//Se for uma String somente com espaços em branco, o .trim().length() pega os espaços referente ao tamanho e iguala a zero
		//tornando-a vazia.
		return str.trim().length() == 0;
	}
	
	//Criptografa a String passada, no caso a senha.
	public static String encrypt(String rawString) {
		if(isEmpty(rawString)) {
			return null;
		}
		
		//Interface do Spring Security que encripta a senha.
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//return encoder.encode(rawString);
		return rawString;
	}
}
