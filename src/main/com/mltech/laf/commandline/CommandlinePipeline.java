package com.mltech.laf.commandline;

import java.net.URL;

import com.mltech.laf.document.BitextDocument;
import com.mltech.laf.document.Document;
import com.mltech.laf.document.IDocument;
import com.mltech.laf.processing.pipeline.Pipeline;
import com.mltech.utils.Utils;



public class CommandlinePipeline {
	public static void main(String[] args) throws Exception {
		String text1 = null;
		String text2 = null;

		if (args.length != 2 && args.length != 3)
			exit();

		text1 = Utils.fileToString(new URL("file:" + args[1]));
		if (args.length == 3)
			text2 = Utils.fileToString(new URL("file:" + args[2]));

		Pipeline pipeline = new Pipeline(new URL("file:" + args[0]));
		IDocument document = null;
		if (args.length == 2) {
			System.err.println("Starting simple pipeline");
			document = new Document(text1);
		} else if (args.length == 3) {
			System.err.println("Starting bitext pipeline");
			document = new BitextDocument(text1, text2);
		}
		pipeline.parse(document);
		System.out.println(pipeline.export(document));
	}

	private static void exit() {
		System.err.println("Usage: pipelinexec type config_file text1 [text2]");
		System.exit(0);
	}
}