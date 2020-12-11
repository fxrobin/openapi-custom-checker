package fr.fxjavadevblog.oacc;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Parameters;

import static picocli.CommandLine.Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.stream.Stream;

import org.yaml.snakeyaml.Yaml;

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
			System.out.printf("Checking file : %s%n", file.getAbsolutePath());
			this.prettyPrint(yaml,0);			
		} catch (FileNotFoundException e) {
			log.error("File not found : " + file.getAbsolutePath());
		}
		
	}

	private String generateSpace(int level)
	{
		char[] padding = new char[level * 4];
		for(int i = 0; i < padding.length ; i++) padding[i] = ' ';
		return new String(padding);
	}
	
	private void prettyPrint(Map<?, ?> yaml, int level) {
		
		yaml.entrySet().forEach(
				
				e -> {
					String padding = this.generateSpace(level);
					String content = padding + "- " + e.getKey() + " : ";
					if (e.getValue() instanceof Map)
					{
						log.info(content);
					
						@SuppressWarnings({ "unchecked", "rawtypes" })
						Map map = (Map) (Map <String, Object>) e.getValue();
						prettyPrint(map, level + 1);
					}
					else
					{
						content += e.getValue();
						log.info(content);
					}
				}
				
			);
	
		
	}
}
