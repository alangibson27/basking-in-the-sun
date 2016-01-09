package uk.co.baskinginthesun.stepdefs;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HugoServer {

    private static Process hugoProcess;

    public static synchronized int startSite() throws IOException, InterruptedException {
        if (hugoProcess == null) {
            final ProcessBuilder pb = new ProcessBuilder();
            pb.command("hugo",
                       "server",
                       "--source=Site",
                       "--destination=../Tests/target/hugo1212",
                       "--buildDrafts=true",
                       "--theme=hinatabokko",
                       "--port=1212")
                    .directory(projectRoot().toFile())
                    .inheritIO();
            hugoProcess = pb.start();

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    hugoProcess.destroyForcibly();
                }
            });
            Thread.sleep(5000);
        }
        return 1212;
    }

    private static Path projectRoot() {
        final Path userDir = Paths.get(System.getProperty("user.dir"));
        if ("Tests".equals(userDir.getName(userDir.getNameCount() - 1).toString())) {
            return userDir.getParent();
        } else {
            return userDir;
        }
    }
}
