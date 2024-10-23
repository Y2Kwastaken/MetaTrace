package sh.miles.metatrace.json.version;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import sh.miles.metatrace.meta.version.VersionLibraryArtifact;

import java.lang.reflect.Type;
import java.net.URI;

public class VersionLibraryArtifactAdapter implements JsonSerializer<VersionLibraryArtifact>, JsonDeserializer<VersionLibraryArtifact> {

    @Override
    public JsonElement serialize(final VersionLibraryArtifact artifact, final Type type, final JsonSerializationContext context) {
        final var parent = new JsonObject();
        parent.addProperty("path", artifact.path());
        parent.addProperty("sha1", artifact.sha1());
        parent.addProperty("size", artifact.size());
        parent.addProperty("url", artifact.url().toASCIIString());
        return parent;
    }

    @Override
    public VersionLibraryArtifact deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final var parent = element.getAsJsonObject();
        final var path = parent.get("path").getAsString();
        final var sha1 = parent.get("sha1").getAsString();
        final var size = parent.get("size").getAsInt();
        final var url = URI.create(parent.get("url").getAsString());
        return new VersionLibraryArtifact(path, sha1, size, url);
    }
}
