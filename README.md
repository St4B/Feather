 <img src="https://github.com/St4B/Feather/blob/main/img/logo.png" width="100" height="100">

# Feather

A floating bubble built with Jetpack Compose. The motives behind creating
it can be found in this [post](https://engineering.theblueground.com/blog/).

## Installation

In the project's build.gradle file you need to add the maven central repository:

```kotlin
repositories {
    mavenCentral()
}
```

In the module's build.gradle file you need to add the following dependency:

```kotlin
implementation("com.quadible:feather:1.0.0")
```

## Usage

All we have to do is to create a Composable that will define the appearance of the bubble and provide
it as a content in the `FloatingDraggableItem` Composable.

```kotlin
FloatingDraggableItem {
    // Box defines the appearance of the bubble.
    // We could use whatever Composable we want.
    Box(
        modifier = Modifier
            .align(alignment = Alignment.BottomCenter)
            .size(size = 64.dp)
            .clip(shape = CircleShape),
    )
}
```

## Preview
![](img/preview.gif)