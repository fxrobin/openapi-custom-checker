package fr.fxjavadevblog.oacs;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import lombok.extern.slf4j.Slf4j;

import static picocli.CommandLine.Command;

@TopCommand
@Command(mixinStandardHelpOptions = true,
        version = "1.0.0"
)

@Slf4j
public class ApplicationCommand implements Runnable {
    @Override
    public void run() {
        log.info("Running the app");
    }
}
