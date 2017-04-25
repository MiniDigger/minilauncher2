package me.minidigger.minilauncher.tasks;

import com.google.gson.Gson;
import me.minidigger.minilauncher.Constants;
import me.minidigger.minilauncher.dtos.Profile;
import me.minidigger.minilauncher.dtos.VersionsManifest;
import me.minidigger.minilauncher.manager.VersionManager;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by mbenndorf on 25.04.2017.
 */
public class DownloadLibsTask extends Thread {

    private Profile profile;
    private Gson gson = new Gson();
    private Logger logger = Logger.getLogger(DownloadLibsTask.class.getName());

    public DownloadLibsTask(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void run() {
        VersionsManifest.Version versionsManifest = VersionManager.getVersion(profile.version).orElseThrow(() -> new RuntimeException("Could not find version " + profile.version));
    }

    public void downloadLibraries() {
        File libsDir = new File(profile.gameDir + Constants.libsSuffix.replace("/", File.separator));
    }
}
