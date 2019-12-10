# Shamery

> *N.B.: This app is part of my university course work.*

Shamery is a concentration-type game where the players try to match up two shapes that go inside one another.

[Releases](https://github.com/joonashak/ot-harjoitustyo/releases/tag/viikko5)

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

### Build

Generate executable JAR with dependencies:

```bash
mvn package
```

> **NOTE:** Current tests have a design flaw due to which they may fail sometimes. If this blocks `mvn package` from completing, simply rerunning the command should suffice. (This notice is here in case I don't have time to fix the issue before this week's deadline.)

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
