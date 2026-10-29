# AGP 9 Build Fixes (Evidence from Session)

## KTS Syntax
`settings.gradle.kts` must use Kotlin DSL comments (`//` or `/* */`). `#` produces `Unexpected type specification` build failure.

## Plugin Removal (AGP 9.0+)
`org.jetbrains.kotlin.android` is deprecated/removed. Build errors: `plugin is no longer required for Kotlin support since AGP 9.0`. Remove from `plugins {}` and `libs.versions.toml`.

## Kotlin Options Replacement
`kotlinOptions { jvmTarget = "17" }` is invalid without the Kotlin plugin. Replace with:
```kotlin
kotlin {
    jvmToolchain(17)
}
```
inside the `android` block.

## Dependency Resolution Conflict
When Dependabot bumps AGP (`8.7.0` -> `9.5.0`) and Kotlin (`2.1.10` -> `2.4.21`) separately, `gradle/libs.versions.toml` conflicts. Resolve manually by combining: `agp = "9.5.0"` + `kotlin = "2.4.21"`, then push.

## Gradle Wrapper
Generate `gradlew` only after KTS/plugin fixes validated (`gradlew wrapper --gradle-version 9.8.2`). Never commit `.gradle/` or `build/` artifacts.
