package me.minidigger.minilauncher.manager;

import me.minidigger.minilauncher.dtos.VersionsManifest;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mbenndorf on 25.04.2017.
 */
public class VersionManager {

    private static VersionsManifest versionsManifest;
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void init(VersionsManifest versionsManifest) {
        VersionManager.versionsManifest = versionsManifest;
        latch.countDown();
    }

    public static Optional<VersionsManifest.Version> getVersion(String name) {
        if (!await()) return Optional.empty();
        return versionsManifest.versions.stream().filter(version -> version.id.equals(name)).findAny();
    }

    private static boolean await() {
        try {
            latch.await();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
