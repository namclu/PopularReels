# PopularReels
An Android app to discover the latest Hollywood movie releases (in progress)

## Getting Started

Get started by cloning the project to your local machine:

```
$ git clone https://github.com/namclu/PopularReels.git
```

## Prerequisites

- Android Studio
- minSdkVersion 17
- targetSdkVersion 25

## Installing

1. Open Android Studio
2. If the repository is cloned to your local machine, select ```Open an existing Android Studio project``` 
and select the location from which the responsitory was cloned to.
3. If the repository has not been cloned to your local machine, select ```Check out project from Version Control > GitHub```

```
Git Repository URL: https://github.com/namclu/PopularReels
Parent Directory: <Location where you want to store the repository>
Directory Name: <Name of the repository>
```
4. Build and Run

## Obtain API key
1. To generate an API key, follow the instructions below:

- https://developers.themoviedb.org/3/getting-started

2. One you've obtained an API key, place the following line of code in gradle.properties:

- MOVIES_DB_API_KEY = "[Add API key here]"

## Author(s)

- Nam Lu - [namclu](https://github.com/namclu)

## Tools and Libraries

- [Retrofit](https://github.com/square/retrofit) - Networking library
- [Glide](https://github.com/bumptech/glide) - Image loading and cacheing
- [The Movie Database API](https://www.themoviedb.org/documentation/api) - API to find movies, TV shows, and people

## Screenshots

<img src="/screenshots/popular_reels_01.gif" width="280" height="480">
