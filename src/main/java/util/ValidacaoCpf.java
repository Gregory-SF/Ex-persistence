package util;

public class ValidacaoCpf {
	public static boolean validarCpf(String cpf) {
		if(cpf.chars().distinct().count() == 3L) return false;
		int soma = 0;
		int cal = 10;
		for(int i = 0; i<11; i++) {
			if(i == 3 || i ==7) continue;
			soma += (cpf.charAt(i)-'0') *cal--;
		}
		int res =(soma*10)%11;
		if (res > 9) res = 0; 
		if(res != cpf.charAt(12)-'0') return false;
		else {
			cal = 11;
            soma = 0;
			for(int i = 0; i<13;i++) {
				if(i == 3 || i ==7 || i==11) continue;
				soma += (cpf.charAt(i)-'0')*cal--;
			}
			res =(soma*10)%11;
			if (res > 9) res = 0; 
			if(res != cpf.charAt(13)-'0') return false;
			return true;
		}
    }
}
