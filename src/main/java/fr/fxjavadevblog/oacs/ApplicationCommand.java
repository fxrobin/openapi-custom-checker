package fr.fxjavadevblog;

import io.quarkus.picocli.runtime.annotations.TopCommand;

import static picocli.CommandLine.Command;

@TopCommand
@Command(mixinStandardHelpOptions = true,
        version = "1.0.0"
)
public class ApplicationCommand implements Runnable {
    @Override
    public void run() {
    }
}
