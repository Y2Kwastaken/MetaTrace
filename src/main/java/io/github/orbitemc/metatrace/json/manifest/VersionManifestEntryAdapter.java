package io.github.orbitemc.metatrace.json.manifest;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.orbitemc.metatrace.meta.manifest.VersionManifestEntry;

import java.lang.reflect.Type;
import java.net.URI;

public class VersionManifestEntryAdapter implements JsonSerializer<VersionManifestEntry>, JsonDeserializer<VersionManifestEntry> {

    @Override
    public JsonElement serialize(final VersionManifestEntry entry, final Type type, final JsonSerializationContext context) {
        final var parent = new JsonObject();
        parent.addProperty("id", entry.id());
        parent.addProperty("type", entry.type());
        parent.addProperty("url", entry.url().toASCIIString());
        parent.addProperty("sha1", entry.sha1());
        return parent;
    }

    @Override
    public VersionManifestEntry deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final var parent = element.getAsJsonObject();
        final String id = parent.get("id").getAsString();
        final String releaseType = parent.get("type").getAsString();
        final URI url = URI.create(parent.get("url").getAsString());
        final String sha1 = parent.get("sha1").getAsString();
        return new VersionManifestEntry(id, releaseType, url, sha1);
    }
}
