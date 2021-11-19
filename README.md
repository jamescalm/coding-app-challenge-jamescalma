# coding-app-challenge-jamescalma
This app is a List Sound Tracks from iTunes

In the basic setup, I used kotlin version 1.4.32 and gradle version 4.1.0.

I used Retrofit for retrieving JSON data and OkHttp to handle Http connections. I used them both because all of our apps
in my current company uses this libraries and I'm familiar with them

I used Dagger as dependency injector for the project since most of our projects uses this injector. Though some of our apps
uses Koin as injector.

I think MVVM is a moreflexible and modern approach to coding. As such I tried to apply MVVM architectural structure to the app
to the my understanding and knowledge of MVVM. I would want to learn more of MVVM if ever I am employed to Appetizer as a developer,
specially from other devs with experience.

Some other Libraries I used is Glide for Image loader and koi for toast.

I also used LiveData in receiving data from the API and setting up for the persistence function I applied to the app.

I also used navigation component in passing arguments from one fragment to another.

Overview of the output of the app is that it list 50 tracks from an API endpoint provided by iTunes. When the user clicks the track
on the track list, it will show more details of the track to the user. Upon clicking the prize button, a mock toast will appear
letting the user know he/she added the track to cart.

For the persistence function, I decided to save the last selected track by the user. I used both the trackId and collectionId since not
all tracks has a trackId. I saved them both in the sharedPreferences and when the users opens the app, I get the data from shared
Preference and show the last track selected by the users.

You can look at the git logs of the project to see when I started the project and also incorporated basic pushing to an online
repo. Used Git Extensions as repository manager since I'm familiar with using it.