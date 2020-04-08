﻿### Android Development Final Project by Victoria Rhine

The link for the demo is [found here](https://www.youtube.com/watch?v=O5pkehJopIw&feature=youtu.be).
Link to my github project is [found here](https://github.com/wou-computerscience/android-assignments-Victoria-Rhine/tree/master/vicreality).

I built an AR app! This was a much more difficult process than expected and it's still not perfect. I wasn't able to find a single video or tutorial that got me from point A to B exclusively. So my code is unique but borrows ideas from everywhere. 

First I began by installing the SceneForm plugin. Then I used Google Poly to download 3D models. I imported the models into Android Studio which I divided into 2 themes: Pokemon characters and Random Items. Setting up the layout wasn't very difficult, I made the images clickable and added an AR fragment that would display the virtual scene. Getting that scene to work was another story.

I had to set up user permissions for the camera.  In the emulator settings I made the camera render a virtual scene since I didn't have a device to test on. Then there were some settings that were needed in the "phone" also. I tried several emulators and APIs before finding one that would even render a virtual scene without crashing. I finally settled on the Pixel with API 27, Android 8.1 Google APIs. Then I updated the SDK file for Sceneform and had my virtual scene. After more Googling, I learned how to move around the scene, which a user can move around in a 360 view and along every axis. 

I struggled with the code for placing an object for a very, very long time. When I finally had all of my code together, I couldn't get the object to render but knew I was going through every function because I was getting to my error message, and because I debugged countless times. I took a break from that and made a splash page and menu. 

I wound up throwing in the towel and turning in my project, then came back to the code and changed the way I was passing in the object path to my model renderer which finally allowed me to place models. Then I changed my models from the Pokemon characters and random items to all animals so that I could have a cohesive theme and because the Google Poly images weren't rendering with the correct materials (something I didn't realize was an issue because I had never rendered them successfully!) As you can see in the video, the objects are rendered all black, without texture. I've looked into this extensively and it may be an issue with the emulator and/or version of Sceneform. I'm fairly certain the path to my materials are correct. I'll keep trying to get this working but wanted to turn in this almost perfectly working version before I started messing around with the Sceneform files and breaking things.

I hope you enjoy seeing this project in action! It was really challenging but I'm glad I took on something to new to me because it was a great learning process. 

 - Splash page and custom logo (0 - 1 point)
 - Menu screen with user options (0 - 1 point)
 - 4 activities (0 - 1 point)
 - Requires user-granted permissions (0 -1 point) - permission is in AndroidManifest and I had already accepted it on the emulator I demoed on
 - Use of camera (0 - 1 point) - in the emulator settings, the camera is set to a virtual scene but a real phone would use its camera here
 - Fragment (0 - 2 points) - not sure about this. the virtual scene works as an arfragment.
 - AR (0 -4 points)  - obviously not fully functional but some elements are in place
 - ???
