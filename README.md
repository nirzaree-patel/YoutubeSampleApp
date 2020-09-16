# Youtube Sample App
Sample app for Bell Interview

**Following tasks are completed.**

**Level 1**
Screen 1 - Authentication screen.
The application should offer user Google sign in. Using the Gmail account, the user can sign-in
to the app and proceed to the initial screen.
Screen 2 - User's playlists screen.
The main screen of the app should display all user's public playlists from Youtube. This can be
implemented recursively or with pagination.
Each playlist should contain the following info:
- thumbnail
- title
- number of videos in it
**Level 2** (Optional)
All fetched info from the Youtubes'API (playlists, videos etc) should be cached in the local
database.


Remaining optional tasks
Screen 3 - Playlist details screen
If a user taps on a playlist, a user should see a new screen with playlist details and all videos in
it. Tracks have to be fetched recursively or using pagination.
Playlist details:
- title
- number of videos
- thumbnail
- (Play All) button (this is needed only for Ninja-level)
Each track should contain the following info:
- thumbnail
- title
- author
- duration
Level 3(Optional)
Screen 4 - A user can search for videos via the search field. Pagination has to be implemented.
All logical classes of the app are covered with unit tests.
Ninja-level(Optional)
A User can playback separate videos and whole playlists from appropriate screens. This has to
be done in a separate presented screen, similar to Youtube Music app. (Screen 4 - on "Play All"
button tap, Screen 3 - on video tap)


**Note:**
-Please add public playlist in your account to get expected results.
-Due to time constraint(other pre-booked appointments) the few optional task are not completed.