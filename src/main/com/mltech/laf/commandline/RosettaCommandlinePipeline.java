package com.mltech.laf.commandline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import com.mltech.laf.document.IDocument;
import com.mltech.laf.processing.pipeline.Pipeline;
import com.mltech.utils.Utils;


// TODO: unify with rest
// TODO: or move to plugins package
public class RosettaCommandlinePipeline {
	private Pipeline _pipeline;

	public static void main(String[] args) throws Exception {
		if (args.length != 3)
			exit();
		RosettaCommandlinePipeline rp = new RosettaCommandlinePipeline(args[0]);
		rp.parse(args[1], args[2]);
	}

	public RosettaCommandlinePipeline(String configPath) throws MalformedURLException {
		_pipeline = new Pipeline(new URL("file:" + configPath));
	}

	private void parse(String fileOrDirPath, String destDir) throws MalformedURLException, FileNotFoundException {
		File dir = new File(destDir);
		dir.mkdirs();
		long start = System.currentTimeMillis();
		int nbFiles = 0;
		File fileOrDir = new File(fileOrDirPath);
		if (fileOrDir.isFile()) {
			System.err.println("Working with file " + nbFiles + ":" + fileOrDir.getPath());
			parseFile(fileOrDir, destDir);
			nbFiles = 1;
		} else {
			for (File file : fileOrDir.listFiles()) {
				nbFiles++;
				System.err.println("Working with file " + nbFiles + ":" + file.getPath());
				parseFile(file, destDir);
				if (nbFiles > 50) break;
			}
		}
		
		long end = System.currentTimeMillis();
		System.err.println("Execution time for " + nbFiles + ": " + (end-start) + "ms");
	}

	private void parseFile(File file, String destDir) throws MalformedURLException, FileNotFoundException {
//		System.out.println(file.getAbsolutePath());
		String text = Utils.fileToString(new URL("file:" + file.getPath()));
		IDocument document = _pipeline.extract(text); // TODO: same treatment for
		// extract and export.
		// Document should be part of
		// the pipeline, not a
		// returned type
		// TODO: create setDocument method or generic extract method and call
		// parse() export() and extract() without args
		// TODO: unify parse() export() and extract() into one only method!! ->
		// pipeline.execute() returns String
		// TODO: pipeline.extract with StringStream as argument -> extract(String)
		// uses it / extract(File) also
		_pipeline.parse(document);

		File outFile = new File(destDir + "/" + file.getName() + ".out");
		System.err.println("Output to: " + outFile.getAbsolutePath());
		PrintWriter out = new PrintWriter(outFile);
		out.println(_pipeline.export(document));
		out.close();
//		System.out.println(_pipeline.export(document));
	}

	private static void exit() {
		System.err.println("Usage: command config_file source_file dest_dir");
		System.exit(0);
	}
}