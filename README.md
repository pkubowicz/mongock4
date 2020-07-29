Can I safely switch from [mongobee](https://github.com/mongobee/mongobee) to [Mongock](https://github.com/cloudyrock/mongock)? Can I then switch back?

Mongock is a fork of mongobee, but does it keep backward compatibility?

## Success criteria
1. I don't need to re-write my migrations, I just change the package form which I import `@ChangeLog` annotation
2. I can switch library back and forth but each migration is always applied to the database exactly once

## Libraries
- mongobee
- Mongock 3
- Mongock 4

This project contains a single migration that is implemented in each library, keeping the logic the same and migration ID the same.

The migration is written in such a way that it will fail if applied twice, so it tests that libraries behave properly.

## How to test by yourself
- have a local MongoDB instance running (the app will create a database called `mongock4`)
- `./drop.sh`
- run `./gradlew bR -Pprofile=<profile>` and watch the console output
  - profile can be one of: mongobee, mongock3, mongock4
- change profile and run again to see if it works

## Results

Tested against MongoDB 4.2.8.

### mongobee → mongock3 → mongock4

```
./drop.sh && ./gradlew -q bR -Pprofile=mongobee && ./gradlew -q bR -Pprofile=mongock3 && ./gradlew -q bR -Pprofile=mongock4
```

Works fine.

### mongobee → mongock4

```
./drop.sh && ./gradlew -q bR -Pprofile=mongobee && ./gradlew -q bR -Pprofile=mongock4
```

Works fine.

### mongobee → mongock3 → mongobee

```
./drop.sh && ./gradlew -q bR -Pprofile=mongobee && ./gradlew -q bR -Pprofile=mongock3 && ./gradlew -q bR -Pprofile=mongobee
```

Works fine, but you have to use a different collection for locks, otherwise mongock3 → mongobee will fail with
```
Index with name: mongobeelock_key_idx already exists with a different name
```
(see [Mongock3Configuration.java](mongock3/src/main/java/com/example/mongock3/Mongock3Configuration.java))

### mongock4 → mongobee

```
./drop.sh && ./gradlew -q bR -Pprofile=mongock4 && ./gradlew -q bR -Pprofile=mongobee
```

Also fails with
```
Index with name: mongobeelock_key_idx already exists with a different name
```

### mongobee → mongock3 → mongock4 → mongock3 → mongobee

```
./drop.sh && ./gradlew -q bR -Pprofile=mongobee && ./gradlew -q bR -Pprofile=mongock3 \
&& ./gradlew -q bR -Pprofile=mongock4 && ./gradlew -q bR -Pprofile=mongock3 \
&& ./gradlew -q bR -Pprofile=mongobee
```

Works fine.
