<p align="center" >
   <img src="screenshots/logo.png" width=300px alt="SwiftDate" title="SwiftDate">
 </p>
 
<h3 align="center"><strong>Java Time Fun</strong></h3>
<p align="center">Java Time Kotlin extension functions.</p>
<p align="center">
  <a href="https://github.com/sami-eljabali/java-time-fun/actions?query=branch%3Amain"><img alt="Build Status" src="https://github.com/sami-eljabali/java-time-fun/actions/workflows/main.yml/badge.svg"/></a> 
  <a href="https://repo1.maven.org/maven2/org/eljabali/sami/javatimefun/javatimefun/"><img alt="Maven Central" src="https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Forg%2Feljabali%2Fsami%2Fjavatimefun%2Fjavatimefun%2Fmaven-metadata.xml"/></a> 
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3.10-orange.svg?style=flat&logo=kotlin"/></a>
  <a href="https://mailchi.mp/kotlinweekly/kotlin-weekly-396"><img alt="Kotlin Weekly" src="https://skydoves.github.io/badges/kotlin-weekly.svg"/></a>
</p> <br>

```diff
- val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
- val date = LocalDate.parse(dateText, dateTimeFormatter)
+ val date = dateText.toLocalDate("yyyyMMdd")

- val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
- print(dateFormatter.format(date))
+ print(date.print("MM/dd/yyyy"))

- if (ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now()) < 18) {
+ if (dateOfBirth.getYearDifference(LocalDates.today) < 18) {

- val zoneId = ZoneId.of("America/Los_Angeles")
+ val zoneId = ZoneIds.AMERICA_LOS_ANGELES
```

## ✨ Features
### Parsing
_Convert strings into Java Time objects with ease_
```kotlin
val result = "01:30 AM".toLocalTime()
val result = "2021-06-07".toLocalDate()
val result = "06/07/2021".toLocalDate("MM/dd/yyyy")
val result = "06/07/2021".toLocalDate("yyyy-MM-dd", "MM/dd/yyyy")
val result = "2024-11-15T12:34:56.123456Z".toLocalDateTime() // handles fractional seconds that Java Time doesn't
val result = "2021-10-04T10:10:00+0000".toZonedDateTime()
```
### Creation
_Create new date/time instances using factory functions_
```kotlin
val result = LocalTimeFactory.new(hour = 7, minute = 30)
val result = LocalDateFactory.new(year = 2024, month = 11, day = 15)
val result = LocalDateTimeFactory.new(year = 2024, month = 11, day = 15)
val result = ZonedDateTimeFactory.new(year = 2024, month = 11, day = 15, zoneId = ZoneIds.AMERICA_LOS_ANGELES)
```

### Conversion from Legacy Date Types
_Easily convert legacy date objects to Java Time_
```kotlin
val result = Date().toLocalDateTime()
val result = GregorianCalendar().toZonedDateTime()
```

### Comparisons
_Compare dates and times at various granularities_
```kotlin
// Year
val result = dateA.compareYear(dateB)
val result = dateA.isBeforeYear(dateB)

// Month
val result = dateA.compareMonth(dateB)
val result = zonedDateA.getMonthDifference(zonedDateB) // auto-conversion to same time zone for expected results
val result = dateA.isEqualMonth(dateB)

// Day
val result = dateA.compareDay(dateB)
val result = dateA.getDayDifference(dateB)
val result = dateA.isAfterEqualDay(dateB)

// Time
val result = dateA.compareTime(dateB)
val result = dateA.getMinuteDifference(dateB)
val result = dateA.isAfterEqualTime(dateB)
```

### Formatting
_Print dates and times using a custom format_
```kotlin
val date = "2021-07-06".toZonedDateTime()
val result = date.print(format = "MM/dd/yyyy")
```

### Attributes & Mutations
_Query and transform date/time attributes_
```kotlin
val result = date.isAtStartOfDay()
val result = date.getDaysInMonth()

val result = date.atStartOfDay()
val result = date.atStartOfMonth()
val result = date.getLast(DayOfWeek.FRIDAY)
val result = date.getNext(DayOfWeek.MONDAY)
```

### Preset Dates
_Quickly access commonly used dates_
```kotlin
val result = LocalDates.startOfYear()
val result = LocalDateTimes.tomorrow()
val result = ZonedDateTimes.nextMonday()
```

## 🔨 Installation
Add the following to your module’s `build.gradle`:
```gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation("org.eljabali.sami.javatimefun:javatimefun:4.0.3")
}  
```

<details>
<summary>For Android</summary>

In addition to the above, you need to desugar your module:
- Ensure you're using [Gradle Plugin](https://developer.android.com/studio/releases/gradle-plugin#updating-plugin) 4.0.0+.
- Update module `build.gradle`:
```gradle
android {
    defaultConfig {
        // Required when setting minSdkVersion to 20 or lower
        multiDexEnabled true
    }

    compileOptions {
        // Flag to enable support for the new language APIs
        coreLibraryDesugaringEnabled true
        // Sets Java compatibility to Java 8
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:1.1.5'
}
```
For more information on Android desugaring click [here](https://developer.android.com/studio/write/java8-support#library-desugaring).

</details>

## 😏 Find this library useful?
If you like what you see, please star the repository __[as others have](https://github.com/sami-eljabali/java-time-fun/stargazers)__! ⭐️

## 🍪 Extras
[DateTimeFormats](https://gist.github.com/sami-eljabali/d9476427db7f39645d8f94f8a0707751) to help with day to day printing/parsing of datetimes!
