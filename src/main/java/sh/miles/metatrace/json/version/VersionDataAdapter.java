package sh.miles.metatrace.json.version;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import sh.miles.metatrace.meta.version.VersionData;
import sh.miles.metatrace.meta.version.VersionDownloadEntry;
import sh.miles.metatrace.meta.version.VersionLibrary;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionDataAdapter implements JsonSerializer<VersionData>, JsonDeserializer<VersionData> {

    @Override
    public JsonElement serialize(final VersionData data, final Type type, final JsonSerializationContext context) {
        final var parent = new JsonObject();

        final var downloads = new JsonObject();
        final var downloadEntries = data.downloadEntries();
        for (final String downloadKey : downloadEntries.keySet()) {
            downloads.add(downloadKey, context.serialize(downloadEntries.get(downloadKey), VersionDownloadEntry.class));
        }

        parent.add("downloads", downloads);

        final var librariesList = data.libraries();
        final var libraries = new JsonArray();
        for (final VersionLibrary versionLibrary : librariesList) {
            libraries.add(context.serialize(versionLibrary, VersionLibrary.class));
        }

        parent.add("libraries", libraries);
        return parent;
    }

    @Override
    public VersionData deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final var parent = element.getAsJsonObject();

        final Map<String, VersionDownloadEntry> downloadEntries = new HashMap<>();
        final var downloads = parent.getAsJsonObject("downloads");
        for (final String downloadKey : downloads.keySet()) {
            downloadEntries.put(downloadKey, context.deserialize(downloads.get(downloadKey), VersionDownloadEntry.class));
        }

        final List<VersionLibrary> libraries = new ArrayList<>();
        for (final JsonElement library : parent.getAsJsonArray("libraries")) {
            libraries.add(context.deserialize(library, VersionLibrary.class));
        }

        return new VersionData(downloadEntries, libraries);
    }
}
