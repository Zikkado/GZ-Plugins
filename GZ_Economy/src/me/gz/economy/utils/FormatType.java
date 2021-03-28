package me.gz.economy.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import me.gz.economy.main;

public class FormatType {
	
	public static enum TypeFormat {
		OP,
		Formal, 
		Numerica,

	}
	
	public static String Formatar(TypeFormat Type, double valor)
	{
	
		switch (Type) 
		{
		case OP: return OP(valor);
		
		case Formal: return FORMAL(valor);
		
		case Numerica: return Numerica(valor);
		
		default:
			return null;
		}
	}
	
	
	private static String OP(double valor) {
		

        List<String> classificacoes = Arrays.asList("", "K", "M", "B", "T", "Q", "QQ", "S", "SS", "O", "N", "D", "UN", "DD", "TD", "QD", "QQD", "SD", "SSD", "OD", "ND", "VD");
        DecimalFormat formatador = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.US));

        String numeroFormatado = formatador.format(valor);


        char separador = formatador.getDecimalFormatSymbols().getGroupingSeparator();

        String[] conjuntoDeTresCasas = numeroFormatado.split("" + separador);
        int tamanho = conjuntoDeTresCasas.length;
        if (tamanho <= 1) {

            return numeroFormatado;
        }

        String sigla = classificacoes.get(classificacoes.size() - 1);
        if (tamanho <= classificacoes.size()) {
            sigla = classificacoes.get(tamanho - 1);
        }
        try {
            Number numeroFinal = formatador.parse(conjuntoDeTresCasas[0] + formatador.getDecimalFormatSymbols().getDecimalSeparator() + conjuntoDeTresCasas[1]);
            return formatador.format(numeroFinal) + sigla.toUpperCase();
        } catch (Exception ex) {
            return "-1.0";
        }

    }
	
	private static String FORMAL(double valor) {
		
		List<String> classificacoes = Arrays.asList("", " Mil", " Milhões", " Bilhões", " Tilhões", " Quadrilhão", " Quintilhão", " Sextilhão", " Septilhões", " Octilhões", " Nonilhões", " Decilhão", " Undecilhão", " Duodecilhão", " Tredecilhão", "Quadracilhão", "QQD", "SD", "SSD", "OD", "ND", "VD");
		DecimalFormat formatador = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.US));
		
		String numeroFormatado = formatador.format(valor);
		
		
		char separador = formatador.getDecimalFormatSymbols().getGroupingSeparator();
		
		String[] conjuntoDeTresCasas = numeroFormatado.split("" + separador);
		int tamanho = conjuntoDeTresCasas.length;
		if (tamanho <= 1) {
			
			return numeroFormatado;
		}
		
		String sigla = classificacoes.get(classificacoes.size() - 1);
		if (tamanho <= classificacoes.size()) {
			sigla = classificacoes.get(tamanho - 1);
		}
		try {
			Number numeroFinal = formatador.parse(conjuntoDeTresCasas[0] + formatador.getDecimalFormatSymbols().getDecimalSeparator() + conjuntoDeTresCasas[1]);
			return formatador.format(numeroFinal) + sigla;
		} catch (Exception ex) {
			return "-1.0";
		}
		
	}
	
	private static String Numerica(double valor) {
    	NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
    	return main.CFG.getString("MoneyConfigs.Simbolo") + nf.format(valor);
	}
}


