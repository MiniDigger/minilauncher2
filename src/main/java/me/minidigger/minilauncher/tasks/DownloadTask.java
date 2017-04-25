package me.minidigger.minilauncher.tasks;

import me.minidigger.minilauncher.dtos.Profile;
import me.minidigger.minilauncher.dtos.VersionsManifest;
import me.minidigger.minilauncher.manager.VersionManager;

/**
 * Created by mbenndorf on 25.04.2017.
 */
public class DownloadTask extends Thread {

    private Profile profile;

    public DownloadTask(Profile profile) {
        start();
    }

    @Override
    public void run() {
        VersionsManifest.Version versionsManifest = VersionManager.getVersion(profile.version).orElseThrow(()
                -> new RuntimeException("Could not find version " + profile.version));
    }
}
