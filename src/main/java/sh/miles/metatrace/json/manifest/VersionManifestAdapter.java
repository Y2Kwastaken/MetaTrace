package sh.miles.metatrace.json.manifest;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import sh.miles.metatrace.meta.manifest.VersionManifest;
import sh.miles.metatrace.meta.manifest.VersionManifestEntry;
import sh.miles.metatrace.meta.manifest.VersionManifestLatest;

import java.lang.reflect.Type;
import java.util.List;

public class VersionManifestAdapter implements JsonSerializer<VersionManifest>, JsonDeserializer<VersionManifest> {

    private static final TypeToken<List<VersionManifestEntry>> TOKEN = new TypeToken<>() {
    };

    @Override
    public JsonElement serialize(final VersionManifest manifest, final Type type, final JsonSerializationContext context) {
        final var parent = new JsonObject();
        parent.add("latest", context.serialize(manifest.latest(), VersionManifestLatest.class));
        parent.add("versions", context.serialize(manifest.entries(), TOKEN.getType()));
        return parent;
    }

    @Override
    public VersionManifest deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final var parent = element.getAsJsonObject();
        final VersionManifestLatest latest = context.deserialize(parent.get("latest"), VersionManifestLatest.class);
        final List<VersionManifestEntry> entries = context.deserialize(parent.get("versions"), TOKEN.getType());
        return new VersionManifest(latest, entries);
    }
}
