package sh.miles.metatrace.json.manifest;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import sh.miles.metatrace.meta.manifest.VersionManifestLatest;

import java.lang.reflect.Type;

public class VersionManifestLatestAdapter implements JsonSerializer<VersionManifestLatest>, JsonDeserializer<VersionManifestLatest> {

    @Override
    public JsonElement serialize(final VersionManifestLatest latest, final Type type, final JsonSerializationContext context) {
        final var parent = new JsonObject();
        parent.addProperty("release", latest.release());
        parent.addProperty("snapshot", latest.snapshot());
        return parent;
    }

    @Override
    public VersionManifestLatest deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final var parent = element.getAsJsonObject();
        return new VersionManifestLatest(parent.get("release").getAsString(), parent.get("snapshot").getAsString());
    }
}
