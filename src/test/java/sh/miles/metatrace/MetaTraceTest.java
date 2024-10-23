package sh.miles.metatrace;

import com.google.gson.Gson;
import sh.miles.metatrace.meta.MinecraftVersion;
import sh.miles.metatrace.meta.manifest.VersionManifest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetaTraceTest {

    @Test
    void should_Retrieve_Manifest() {
        final VersionManifest manifest = assertDoesNotThrow(() -> MetaTrace.getVersionManifest());
        assertNotNull(manifest);
    }

    @Test
    void should_Retrieve_MinecraftVersion() {
        final MinecraftVersion version = assertDoesNotThrow(() -> MetaTrace.getVersion("1.20.6"));
        assertNotNull(version);
    }

    @Test
    void should_Error_On_Invalid_Version() {
        assertThrows(IllegalArgumentException.class, () -> MetaTrace.getVersion("This isn't a valid version lol"));
    }

    @Test
    void should_Serialize_Correctly() {
        final Gson gson = MetaTrace.GSON.newBuilder().setPrettyPrinting().create();
        final MinecraftVersion version = assertDoesNotThrow(() -> MetaTrace.getVersion("1.20.6"));
        assertNotNull(version);
        final String jsonString = assertDoesNotThrow(() -> gson.toJson(version, MinecraftVersion.class));
        assertNotNull(jsonString);
        final MinecraftVersion deserializedVersion = assertDoesNotThrow(() -> gson.fromJson(jsonString, MinecraftVersion.class));
        assertNotNull(deserializedVersion);
        assertEquals(version, deserializedVersion);
    }

}
