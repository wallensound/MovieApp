# MovieApp

## Summary
**MovieApp is my personal project that showcases my Android development skills. I built it with Kotlin and Jetpack Compose following the MVVM architecture, utilizing Retrofit and Coroutines for API implementation and Koin for Dependency Injection.**

All the data shown on every screen are gathered asynchronously with coroutines from the TMDB API.

The application consists of 4 main screens in total, the landing screen: **Trending**, **Details**, **Search** and lastly **Account**

### Walkthrough Video

https://user-images.githubusercontent.com/88088759/233361330-ab87edc3-5a67-46b4-8f8c-0251ba2108e8.mp4

## Trending Screen
The Trending screen consists of one LazyColumn and four LazyRows. The rating widget is probably my favourite part of this screen due to the animation I created for it, where the circle fills up. 

![ezgif com-video-to-gif](https://user-images.githubusercontent.com/88088759/233360912-0fa60f93-8d08-453c-afe8-124c623ae2cc.gif)

Each movie and tv show are clickable and will navigate you to either the DetailsScreen or the DetailsScreenTV (TV containing some adjustments specific to the Data received for TV shows from the API).

<img src="https://user-images.githubusercontent.com/88088759/233365425-bf3ab03b-33c3-4cf6-b078-4b8b9b4eb4a4.jpg" width="350"> 

## Details Screen
In the details screen you get more details for each movie or tv show as the name might suggest. Containing title, genres, release date, summary, a LazyRow for actors and a LazyRow for similar movies where each of the movies are clickable.

At the top of the screen there's also a "Add to watchlist" button where clicking it will add the movie or tv show to your TMDB accounts watchlist, this does however require you to have a valid Session ID from TMDB which you can get by validating a token in the AccountScreen. If it successfully adds the movie to your watchlist the heart will be changed from white to red.

<img src="https://user-images.githubusercontent.com/88088759/233365715-d02aca5e-a752-484a-a189-dcebcc8a4d41.jpg" width="350">

## Search Screen
In the search screen you can search through the TMDB database for movies and tv shows. Also being able to filter for each media type, or if you want to not filter at all. I've also made sure the searching feels as smooth as possible by making the Keyboards IME action trigger a search, but you can also use the search Icon on the screen if you want too as well.

<img src="https://user-images.githubusercontent.com/88088759/233365854-022c92b1-e5c2-421f-bed3-5a975d3db76e.jpg" width="350">

## Account Screen
Lastly the account screen, probably being the most complex atleast behind the scenes, having to do both get and post calls to the API to validate the requestToken to then get a valid sessionId from TMDB. After you have validated the session however you won't have to do it again until the sessionId expires. Even if you close the app, you will still be "logged in". I achieved this by using best practices for data storage, in this case, having very simple data I used "Preferences DataStore" to save the Id of both the session and the account. Using Koin to Inject the DataStore it's avalible to all the viewmodels as a singleton.

While "logged in" you will be able to view your watchlists for each media type. It's displayed using a LazyGrid.

<img src="https://user-images.githubusercontent.com/88088759/233341420-fec6b216-6d8c-469b-a48c-6cbe0a97fdb7.jpg" width="350">
