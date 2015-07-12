package it.monicelli;

import org.antlr.v4.runtime.tree.TerminalNode;

import it.monicelli.MonicelliParser.Chiamata_funzioneContext;
import it.monicelli.MonicelliParser.CicloContext;
import it.monicelli.MonicelliParser.Come_fosseContext;
import it.monicelli.MonicelliParser.Condizione_cicloContext;
import it.monicelli.MonicelliParser.Condizione_multiplaContext;
import it.monicelli.MonicelliParser.Condizione_multipla_primo_ramoContext;
import it.monicelli.MonicelliParser.Condizione_multipla_ramo_finaleContext;
import it.monicelli.MonicelliParser.Condizione_multipla_ramo_intermedioContext;
import it.monicelli.MonicelliParser.Definizione_funzioneContext;
import it.monicelli.MonicelliParser.Definizione_parametriContext;
import it.monicelli.MonicelliParser.Definizione_parametroContext;
import it.monicelli.MonicelliParser.DichiarazioneContext;
import it.monicelli.MonicelliParser.DirezioneContext;
import it.monicelli.MonicelliParser.Funzione_principaleContext;
import it.monicelli.MonicelliParser.IdentificatoreContext;
import it.monicelli.MonicelliParser.IstruzioneContext;
import it.monicelli.MonicelliParser.LetturaContext;
import it.monicelli.MonicelliParser.MenoContext;
import it.monicelli.MonicelliParser.Operatore_condizioneContext;
import it.monicelli.MonicelliParser.Passaggio_parametriContext;
import it.monicelli.MonicelliParser.Passaggio_parametroContext;
import it.monicelli.MonicelliParser.PerContext;
import it.monicelli.MonicelliParser.PiuContext;
import it.monicelli.MonicelliParser.ProgrammaContext;
import it.monicelli.MonicelliParser.Ritorna_valoreContext;
import it.monicelli.MonicelliParser.ScritturaContext;
import it.monicelli.MonicelliParser.ValoreContext;
import it.monicelli.MonicelliParser.VariabileContext;

public class Listener extends MonicelliBaseListener {
	private String java = "";
	private Integer spaziIndentazione = 0;
	
	private String variabileCondizioneMultipla = null;
	
	@Override
	public void enterProgramma(ProgrammaContext ctx) {
		aggiungiCodice("package it.monicelli;");
		aCapo();
		aCapo();
		aggiungiCodice("import java.io.IOException;");
		aCapo();
		aggiungiCodice("import java.io.BufferedReader;");
		aCapo();
		aggiungiCodice("import java.io.InputStreamReader;");
		aCapo();
		aCapo();
		aggiungiCodice("public class Monicelli {");
		spaziIndentazione++;
		aCapo();
		aggiungiCodice("static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));");
		aCapo();
		aCapo();
	}
	
	@Override
	public void exitProgramma(ProgrammaContext ctx) {
		spaziIndentazione--;
		aCapo();
		aggiungiCodice("}");
		
		System.out.println(java);
	}
	
	@Override
	public void enterFunzione_principale(Funzione_principaleContext ctx) {
		aggiungiCodice("public static void main(String[] args) throws IOException {");
		spaziIndentazione++;
		aCapo();
	}
	
	@Override
	public void enterDefinizione_funzione(Definizione_funzioneContext ctx) {
		aggiungiCodice("private static " + getTipo(ctx.TIPO()) + " ");
	}
	
	@Override
	public void exitDefinizione_funzione(Definizione_funzioneContext ctx) {
		spaziIndentazione--;
		aCapo();
		aggiungiCodice("}");
		aCapo();
		aCapo();
	}
	
	@Override
	public void enterDefinizione_parametri(Definizione_parametriContext ctx) {
		aggiungiCodice("(");
	}
	
	@Override
	public void exitDefinizione_parametri(Definizione_parametriContext ctx) {
		aggiungiCodice(") {");
		spaziIndentazione++;
		aCapo();
	}
	
	@Override
	public void enterDefinizione_parametro(Definizione_parametroContext ctx) {
		aggiungiCodice(getTipo(ctx.TIPO()) + " ");
	}
	
	@Override
	public void exitDefinizione_parametro(Definizione_parametroContext ctx) {
		if (ctx.VIRGOLA() != null)
			aggiungiCodice(", ");
	}
	
	@Override
	public void enterPassaggio_parametri(Passaggio_parametriContext ctx) {
		aggiungiCodice("(");
	}
	
	@Override
	public void exitPassaggio_parametri(Passaggio_parametriContext ctx) {
		aggiungiCodice(")");
	}
	
	@Override
	public void exitPassaggio_parametro(Passaggio_parametroContext ctx) {
		if (ctx.VIRGOLA() != null)
			aggiungiCodice(", ");
	}
	
	@Override
	public void enterCondizione_multipla(Condizione_multiplaContext ctx) {
		this.variabileCondizioneMultipla = ctx.IDENTIFICATORE().getText();
		
		aCapo();
	}
	
	@Override
	public void exitCondizione_multipla(Condizione_multiplaContext ctx) {
		this.variabileCondizioneMultipla = null;
		
		spaziIndentazione--;
		aCapo();
		aggiungiCodice("}");
		aCapo();
		aCapo();
	}
	
	@Override
	public void enterRitorna_valore(Ritorna_valoreContext ctx) {
		aggiungiCodice("return ");
	}
	
	@Override
	public void enterCondizione_multipla_primo_ramo(Condizione_multipla_primo_ramoContext ctx) {
		aggiungiCodice("if (" + variabileCondizioneMultipla);
		
		if (ctx.operatore_condizione() == null) {
			aggiungiCodice(" == ");
		}
	}
	
	@Override
	public void exitCondizione_multipla_primo_ramo(Condizione_multipla_primo_ramoContext ctx) {
		aggiungiCodice(") {");
		spaziIndentazione++;
		aCapo();
	}
	
	@Override
	public void enterCondizione_multipla_ramo_intermedio(Condizione_multipla_ramo_intermedioContext ctx) {
		spaziIndentazione--;
		aCapo();
		aggiungiCodice("} else if (" + variabileCondizioneMultipla);
		
		if (ctx.operatore_condizione() == null) {
			aggiungiCodice(" == ");
		}
	}
	
	@Override
	public void exitCondizione_multipla_ramo_intermedio(Condizione_multipla_ramo_intermedioContext ctx) {
		aggiungiCodice(") {");
		spaziIndentazione++;
		aCapo();
	}
	
	@Override
	public void enterCondizione_multipla_ramo_finale(Condizione_multipla_ramo_finaleContext ctx) {
		spaziIndentazione--;
		aCapo();
		aggiungiCodice("} else {");
		spaziIndentazione++;
		aCapo();
	}
	
	@Override
	public void exitFunzione_principale(Funzione_principaleContext ctx) {
		spaziIndentazione--;
		aCapo();
		aggiungiCodice("}");
		aCapo();
		aCapo();
	}
	
	@Override
	public void exitIstruzione(IstruzioneContext ctx) {
		aggiungiCodice(";");
		aCapo();
	}
	
	@Override
	public void enterDichiarazione(DichiarazioneContext ctx) {
		String tipoJava = getTipo(ctx.TIPO());
		
		aggiungiCodice(tipoJava + " ");
	}
	
	@Override
	public void enterVariabile(VariabileContext ctx) {
		aggiungiCodice(ctx.IDENTIFICATORE().getText());
	}
	
	@Override
	public void enterIdentificatore(IdentificatoreContext ctx) {
		aggiungiCodice(ctx.IDENTIFICATORE().getText());
	}
	
	@Override
	public void enterValore(ValoreContext ctx) {
		aggiungiCodice(ctx.getText());
	}
	
	@Override
	public void enterCome_fosse(Come_fosseContext ctx) {
		aggiungiCodice(" = ");
	}
	
	@Override
	public void enterPiu(PiuContext ctx) {
		aggiungiCodice(" + ");
	}
	
	@Override
	public void enterMeno(MenoContext ctx) {
		aggiungiCodice(" - ");
	}
	
	@Override
	public void enterPer(PerContext ctx) {
		aggiungiCodice(" * ");
	}
	
	@Override
	public void exitLettura(LetturaContext ctx) {
		aggiungiCodice(" = Integer.parseInt(in.readLine())");
	}
	
	@Override
	public void enterScrittura(ScritturaContext ctx) {
		aggiungiCodice("System.out.print(");
	}
	
	@Override
	public void exitScrittura(ScritturaContext ctx) {
		aggiungiCodice(")");
	}
	
	@Override
	public void enterCiclo(CicloContext ctx) {
		aCapo();
		aggiungiCodice("do {");
		spaziIndentazione++;
		aCapo();
	}
	
	@Override
	public void enterCondizione_ciclo(Condizione_cicloContext ctx) {
		spaziIndentazione--;
		aCapo();
		aggiungiCodice("} while (");
	}
	
	@Override
	public void exitCondizione_ciclo(Condizione_cicloContext ctx) {
		java += ")";
	}
	
	@Override
	public void enterDirezione(DirezioneContext ctx) {
		String direzioneJava = "";
		
		switch (ctx.DIREZIONE().getText()) {
		case "sinistra":
			direzioneJava = "<<";
			break;
		case "destra":
			direzioneJava = ">>";
		}

		aggiungiCodice(" " + direzioneJava + " ");
	}
	
	@Override
	public void enterOperatore_condizione(Operatore_condizioneContext ctx) {
		String operatoreJava = "";
		
		switch (ctx.OPERATORE_CONDIZIONE().getText()) {
		case "maggiore di":
			operatoreJava = ">";
			break;
		case "minore di":
			operatoreJava = "<";
			break;
		case "maggiore uguale a":
		case "maggiore uguale di":
			operatoreJava = ">=";
			break;
		case "minore uguale a":
		case "minore uguale di":
			operatoreJava = "<=";
			break;
		}

		aggiungiCodice(" " + operatoreJava + " ");
	}
	
	private void aCapo() {
		java += "\n";
		for(int i=0; i<spaziIndentazione; i++)
			java += "    ";
	}
	
	private void aggiungiCodice(String codice) {
		java += codice;
	}

	private String getTipo(TerminalNode tipo) {
		String tipoJava = "";
		String tipoStr = tipo == null ? "" : tipo.getText();		
				
		switch (tipoStr) {
		case "Necchi":
			tipoJava = "int";
			break;
		case "Mascetti":
			tipoJava = "char";
			break;
		case "Perozzi":
			tipoJava = "float";
			break;
		case "Melandri":
			tipoJava = "boolean";
			break;
		case "Sassaroli":
			tipoJava = "double";
			break;
		default:
			tipoJava = "void";
			break;
		}
		
		return tipoJava;
	}

}
