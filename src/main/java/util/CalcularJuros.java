package util;

public class CalcularJuros {
	
	
	/**
	 * Método que calcula juros compostos a partir de juros pré-definidos e por um, 
	 * @param double valor
	 * @param double juros
	 * @param int tempo
	 * @return double 
	 */
	public static double JurosCompostos(double valor, double juros, int tempo) {
		double valorTotal = valor*(Math.pow((1+juros),tempo));
		return valorTotal;
	}
}
