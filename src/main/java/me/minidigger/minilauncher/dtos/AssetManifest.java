package me.minidigger.minilauncher.dtos;

import java.util.Map;

/**
 * Created by mbenndorf on 25.04.2017.
 */
public class AssetManifest {

    Map<String, AssetObject> objects;

    @Override
    public String toString() {
        return "AssetManifest{" +
                "objects=" + objects +
                '}';
    }

    public class AssetObject {
        String hash;
        int size;

        @Override
        public String toString() {
            return "AssetObject{" +
                    "hash='" + hash + '\'' +
                    ", size=" + size +
                    '}';
        }
    }
}
