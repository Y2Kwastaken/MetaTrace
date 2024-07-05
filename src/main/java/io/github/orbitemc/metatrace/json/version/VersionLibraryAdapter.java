package io.github.orbitemc.metatrace.json.version;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.orbitemc.metatrace.meta.version.VersionLibrary;
import io.github.orbitemc.metatrace.meta.version.VersionLibraryArtifact;

import java.lang.reflect.Type;

public class VersionLibraryAdapter implements JsonSerializer<VersionLibrary>, JsonDeserializer<VersionLibrary> {

    @Override
    public JsonElement serialize(final VersionLibrary library, final Type type, final JsonSerializationContext context) {
        final var object = new JsonObject();
        final var downloads = new JsonObject();
        downloads.add("artifact", context.serialize(library.artifact(), VersionLibraryArtifact.class));
        object.add("downloads", downloads);
        object.addProperty("name", library.name());
        return object;
    }

    @Override
    public VersionLibrary deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final var object = element.getAsJsonObject();
        final VersionLibraryArtifact artifact = context.deserialize(object.getAsJsonObject("downloads").getAsJsonObject("artifact"), VersionLibraryArtifact.class);
        final String name = object.get("name").getAsString();
        return new VersionLibrary(name, artifact);
    }
}
