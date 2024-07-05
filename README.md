# MetaTrace

MetaTrace is a Minecraft metadata reader. It takes data from PistonMeta and
converts it into easily digestible and understandable data objects.
That can be used easily with other projects. It also provides some QOL
functions regarding reading and writing this data to files.

## Usage

Getting a version's metadata.

```java
final MinecraftVersion version = MetaTrace.getVersion("1.21");
```

Writing a version to a file.

```java
final MinecraftVersion version = MetaTrace.getVersion("1.21");
Cuvee.saveVersionToFile(version);
```

Reading a version from file.

```java
MetaTrace.readVersionFromFile(Path.of("1.20.6-meta.json"));
```
