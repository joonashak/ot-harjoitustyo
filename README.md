# Shamery

> *N.B.: This app is part of my university course work.*

Shamery is a concentration-type game where the players try to match up two shapes that go inside one another.

[Releases](https://github.com/joonashak/ot-harjoitustyo/releases)

## Documentation

[User Guide](docs/user_guide.md)

[Requirements Specification](docs/requirements.md)

[Rules](docs/rules.md)

[Architecture](docs/architecture.md)

[Testing Documentation](docs/testing.md)

[Työaikakirjanpito](docs/tuntikirjanpito.md) (hour tracking, in Finnish)

## Usage

### Run

Compile and run (with Maven):

```bash
mvn compile exec:java -Dexec.mainClass=fi.basse.shamery.Main
```

### Build

Generate executable JAR with dependencies:

```bash
mvn package
```

### Testing

Run unit tests:

```bash
mvn test
```

Generate test coverage report:

```bash
mvn jacoco:report
```

### Static Analysis

Run `checkstyle`:

```bash
mvn jxr:jxr checkstyle:checkstyle
```

## License

This work is licensed under the MIT license except for the fonts which are licensed under their respective licenses included in their directories ([Shamery/src/main/resources/fonts](Shamery/src/main/resources/fonts)).
