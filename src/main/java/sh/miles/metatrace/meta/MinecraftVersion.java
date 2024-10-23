package sh.miles.metatrace.meta;

import sh.miles.metatrace.meta.manifest.VersionManifestEntry;
import sh.miles.metatrace.meta.version.VersionData;
import org.jetbrains.annotations.NotNull;


/**
 * Represents a MinecraftVersion and contains relevant information
 *
 * @param version the version manifest data
 * @param data    the data required to continuer
 * @since 1.0.0-SNAPSHOT
 */
public record MinecraftVersion(@NotNull VersionManifestEntry version, @NotNull VersionData data) {
}
