package fr.fxjavadevblog.oacc;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Parameters;

import static picocli.CommandLine.Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.yaml.snakeyaml.Yaml;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;


@SuppressWarnings("unused")
@TopCommand
@Command(mixinStandardHelpOptions = true, version = "1.0.0")

@Slf4j
public class ApplicationCommand implements Runnable {

	@Parameters(paramLabel = "FILE(s)", description = "YAML OpenAPI file(s)")
	File[] inputFiles;

	@Override
	public void run() {
		Stream.of(inputFiles).forEach(this::analyse);
	}

	private void analyse(File file) {
		
		try {
			
			Map <String, Object> yaml = new Yaml().load(new FileInputStream(file));		
			log.info("Checking file : {}", file.getAbsolutePath());		
			JSONObject json = new JSONObject(yaml);		
			log.info(json.toString());
			
			
			DocumentContext documentContext = JsonPath.parse(json.toString());
			
			// this works!
			log.info("Test v1 {}", documentContext.read("$.basePath").equals("/v1"));
			
			
			// TODO : mettre les regexp dans un fichier PROPERTIES
			// TODO : mettre les JSONPath = REGEXP_REF dans un fichier de Properties
			// TODO : les parcourir, les matcher.
			// TODO : Happy Face.
			
		} catch (FileNotFoundException e) {
			log.error("File not found : " + file.getAbsolutePath());
		} 

		
	}
}
