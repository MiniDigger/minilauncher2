package me.minidigger.minilauncher.tasks;

import com.google.gson.Gson;
import me.minidigger.minilauncher.Constants;
import me.minidigger.minilauncher.dtos.VersionsManifest;
import me.minidigger.minilauncher.util.FileUtils;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Fetches the versions manifest from mojang
 */
public class UpdateVersionsTask extends Thread {

    private Logger logger = Logger.getLogger(UpdateVersionsTask.class.getName());
    private Consumer<VersionsManifest> consumer;
    private Gson gson = new Gson();

    public UpdateVersionsTask(Consumer<VersionsManifest> consumer) {
        this.consumer = consumer;
        start();
    }

    @Override
    public void run() {
        consumer.accept(fetchVersionList());
    }

    public VersionsManifest fetchVersionList() {
        AtomicInteger releases = new AtomicInteger(0);
        AtomicInteger snapshot = new AtomicInteger(0);
        AtomicInteger beta = new AtomicInteger(0);
        AtomicInteger alpha = new AtomicInteger(0);

        logger.info("Fetching versions...");
        VersionsManifest versions = getVersionsManifest();

        versions.versions.parallelStream().forEach(version -> {
            switch (version.type) {
                case "release":
                    releases.incrementAndGet();
                    break;
                case "old_beta":
                    beta.incrementAndGet();
                    break;
                case "old_alpha":
                    alpha.incrementAndGet();
                    break;
                case "snapshot":
                    snapshot.incrementAndGet();
                    break;
                default:
                    logger.warning("Unknown versions type: " + version.type);
                    break;
            }
        });
        logger.info("Found " + versions.versions.size() + " versions!" +
                " (releases: " + releases.get() + ", snapshots: " + snapshot.get() + ", beta: " + beta.get() + ", alpha: " + alpha.get() + ")");
        return versions;
    }

    public VersionsManifest getVersionsManifest() {
        try {
            String json = FileUtils.httpGet(Constants.versionsManifestUrl, null);
            return gson.fromJson(json, VersionsManifest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
