package it.monicelli;

import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Start {

	public static void main(String[] args) throws IOException {
		FileInputStream file = new FileInputStream("examples/mandelbrot.beauty.mc");
		ANTLRInputStream input = new ANTLRInputStream(file);
		Lexer lexer = new MonicelliLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MonicelliParser parser = new MonicelliParser(tokens);
		ParseTree tree = parser.programma();
		
		Listener l = new Listener();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(l, tree);
	}

}
