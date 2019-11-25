# Shamery

> *N.B.: This app is part of my university course work.*

Shamery is a concentration-type game where the players try to match up two shapes that go inside one another.

## Documentation

[Requirements Specification](docs/requirements.md)

[Rules](docs/rules.md)

[Architecture](docs/architecture.md)

[Työaikakirjanpito](docs/tuntikirjanpito.md) (hour tracking, in Finnish)

## Usage

### Run

Compile and run (with Maven):

```bash
mvn compile exec:java -Dexec.mainClass=fi.basse.shamery.Main
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
