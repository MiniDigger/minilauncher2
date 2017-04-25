package me.minidigger.minilauncher.dtos;

import java.util.List;

/**
 * Created by mbenndorf on 25.04.2017.
 */
public class VersionsManifest {

    public Latest latest;
    public List<Version> versions;

    @Override
    public String toString() {
        return "VersionsManifest{" +
                "latest=" + latest +
                ", versions=" + versions +
                '}';
    }

    public class Latest {
        public String snapshot;
        public String release;

        @Override
        public String toString() {
            return "Latest{" +
                    "snapshot='" + snapshot + '\'' +
                    ", release='" + release + '\'' +
                    '}';
        }
    }

    public class Version {
        public String id;
        public String type;
        public String time;
        public String releaseTime;
        public String url;

        @Override
        public String toString() {
            return "Version{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", time='" + time + '\'' +
                    ", releaseTime='" + releaseTime + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
