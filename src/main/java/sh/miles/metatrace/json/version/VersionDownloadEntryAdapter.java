package sh.miles.metatrace.json.version;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import sh.miles.metatrace.meta.version.VersionDownloadEntry;

import java.lang.reflect.Type;
import java.net.URI;

public class VersionDownloadEntryAdapter implements JsonSerializer<VersionDownloadEntry>, JsonDeserializer<VersionDownloadEntry> {

    @Override
    public JsonElement serialize(final VersionDownloadEntry entry, final Type type, final JsonSerializationContext context) {
        final var parent = new JsonObject();
        parent.addProperty("sha1", entry.sha1());
        parent.addProperty("size", entry.size());
        parent.addProperty("url", entry.url().toASCIIString());
        return parent;
    }

    @Override
    public VersionDownloadEntry deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final var parent = element.getAsJsonObject();
        final var sha1 = parent.get("sha1").getAsString();
        final var size = parent.get("size").getAsInt();
        final var url = URI.create(parent.get("url").getAsString());
        return new VersionDownloadEntry(sha1, size, url);
    }
}
