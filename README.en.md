# Alexia VN

[![es](https://img.shields.io/badge/lang-es-yellow.svg)](https://github.com/Fernando1612/AlexiaVN/blob/master/README.md)

Alexia visual novel is a visual novel engine with which you can create and play visual novels in a very simple way from your android cell phone. You can enter texts, images, sounds and videos to create a great story. It is very easy to use with intuitive instructions and it is not necessary to know how to program.

## Download in Play Store

https://play.google.com/store/apps/details?id=maceda.alejandro.alexiavnplayer

## QuickStart 

Create a blank project from the application by going to the menu at the top and pressing the button **Create blank project**. Finally, give your project a name and press accept.

https://user-images.githubusercontent.com/42527034/117889857-d8062380-b279-11eb-8cae-cca802bd1ade.mp4

Then, with the help of a file browser (we recommend ASTRO) or by connecting your cell phone to a computer, go to the **alexavn** folder.

Inside **alexavn** you will find a folder with the name of your project and inside that folder you will see the basic folders to create a visual novel. 

https://user-images.githubusercontent.com/42527034/117898276-3dfaa700-b28a-11eb-9f7a-be64e13296f1.mp4

Inside your project folder you will have to put the image you want as the cover of your visual novel.

Now, go to the **Scenes** folder and place the image that you will use as the background in your visual novel (in this folder you will put all the images that you are going to occupy).

Then go to the **Scripts** folder and create a file with the extension **.txt** (in this folder you will save the files that will be read by the application).

https://user-images.githubusercontent.com/42527034/117898321-566ac180-b28a-11eb-8ec2-3c1ba4add811.mp4

With the help of the text editor of your choice, open the file with the **.txt** extension and start writing your story.
First, put all the resources with which you want your novel to start, in this case we add the background and the text that we want to show on the screen using the **+** symbol as a separator.

![carbono](https://user-images.githubusercontent.com/42527034/117898505-b5c8d180-b28a-11eb-8c08-9a54efee1ddd.png)

Go back to where all the folders of your project are and with the help of your text editor open the file **config.avn**, inside that file you will put the title of your novel, the name of the file of your folder **Scripts** with which you want to start it and the image you want as the cover in that order and separated by a comma ",".

![config](https://user-images.githubusercontent.com/42527034/117898511-b95c5880-b28a-11eb-8940-9cfd4a69ee14.png)

Open **Alexia VN player**, find the file **config.avn** and click on it to load your novel.

Finally, click on the cover of your novel to start it. 

https://user-images.githubusercontent.com/42527034/117898415-8c0faa80-b28a-11eb-969b-d079a91c9084.mp4


## Folders and files

A basic Alexia VN project contains 10 folders:

1. Folder with the name of the project
2. BGM
3. Chars
4. FX
5. Menu
6. Scenes
7. Scripts
8. Textbox
9. Video
10. Voices

and 2 files:

1. config.avn
2. An image for the cover of the novel 

The structure of the folders has the following form.

![carpetasAlexia](https://user-images.githubusercontent.com/42527034/118007843-249d3d80-b312-11eb-8c45-9c146020c2ad.png)

### BGM

In the folder **BGM** the background music that goes in the visual novel is stored, the extension of the files can be .mp3, .Wav, .Flac, etc.

### Chars

In the **Chars** folder the images of the visual novel characters are stored, it is recommended to use images with a .png extension and no background.

### FX

In the **FX** folder the sound effects used in the visual novel are stored, the file extensions can be .mp3, .Wav, .Flac, etc.

### Menu

We are working on it...

### Scenes

In the **Scenes** folder stores most of the images to be used in the visual novel, such as backgrounds, scenes, etc. The file extension can be .jpp, .png, .bmp, etc. 

### Scripts

In the **Scripts** folder the files that are read by the application are stored, which will contain all the instructions, these files must have an extension **.txt**.

### Textbox

In the **Textbox** folder the images of the text boxes that will be used in the visual novel are stored, the images are recommended to save with **.png** extension and without a background for a better experience.

### Video

Videos to be used in the visual novel are stored in the **Video** folder. The video file extension can be .mp4, .avi, .mkv, .flv, etc.

### Voices

We are working on it...

### config.avn

The **config.avn** file should contain only **3** elements:

1. Title of the visual novel
2. File in the **Scripts** folder with which you want to start the novel
3. Cover image for the visual novel

With the help of a text editor, open the file and place the elements in that order, separated by commas.

![config](https://user-images.githubusercontent.com/42527034/117898511-b95c5880-b28a-11eb-8940-9cfd4a69ee14.png)

### Cover page

The **cover** file is an image which will be the cover of the visual novel and will be displayed when loading the **config.avn** file to the application. The file extension can be .jpp, .png, .bmp, etc.



### Note

The manipulation and visualization of the different folders and files can be done on the computer without any problem, you just have to connect the cell phone to it and browse through its files until you find the project.

## Instructions

The instructions that Alexia VN has is described below, these instructions go inside the **.txt** files that are inside the **Scripts** folder, note that the **+** symbol is used as separator.

### [BGM]

The play sounds instruction must carry out two parameters, the word **looping** and the name of the file to be played located in the **BGM** folder.

![bgm](https://user-images.githubusercontent.com/42527034/118008011-4dbdce00-b312-11eb-83e4-76c3de04c12b.png)

### [CHAR]

The instruction places the image of a character, it needs two parameters, the position which can be **left** to put it on the left of the screen, **center** to put it in the center of the screen or **right** to put it on the right of the screen and the name of the character image found in the **Chars** folder.

![char](https://user-images.githubusercontent.com/42527034/118008085-5d3d1700-b312-11eb-8f57-c5df62bbd427.png)

![Screenshot_2021-05-11-23-07-53-108_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118008554-ccb30680-b312-11eb-9f08-6c14435a53d6.jpg)

### [CHARBIG]

The instruction is similar to **[CHAR]** the difference is that **[CHARBIG]** makes the image of the character bigger, it has the same parameters as char.

![charbig](https://user-images.githubusercontent.com/42527034/118008594-d6d50500-b312-11eb-9ee0-56e62ce67b75.png)

![Screenshot_2021-05-11-23-07-18-180_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118008634-e2283080-b312-11eb-9557-07542647ed0e.jpg)

### [CHARBIGCLEAR]

The instruction is similar to **[CHARBIGCLEAR]**, it removes the large image of the character and needs the same parameter as **[CHARCLEAR]**.

![charbigclear](https://user-images.githubusercontent.com/42527034/118008721-f66c2d80-b312-11eb-9726-8158c2166b38.png)

### [CHARCLEAR]

The instruction removes the image of the character, it needs only one parameter which is the position where the character is, to remove it.

![charclear](https://user-images.githubusercontent.com/42527034/118008739-fd933b80-b312-11eb-96d6-e263b72b3220.png)

### [CHOICES]

The instruction is used to place buttons in which you can make decisions within the novel and thus take the different paths that exist, at least 7 parameters are needed:

1. Text that appears at the bottom
2. Text that appears on the button
3. Name of the new file to be read and it must be found in the **Scripts** folder
4. Button size in pixels
5. Button text size
6. Button background color in hexadecimal
7. Text color in hexadecimal

![choices1](https://user-images.githubusercontent.com/42527034/118009513-b9546b00-b313-11eb-9055-8191e34dbef0.png)

![Screenshot_2021-05-12-11-22-16-551_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118010430-a726fc80-b314-11eb-95e3-9278c56226a8.jpg)

To put more than one decision you must add the symbol **+** at the end and put the parameters from 2 to 7 again, as shown in the example.

![choices2](https://user-images.githubusercontent.com/42527034/118009566-c2453c80-b313-11eb-893a-071a5a640948.png)

![Screenshot_2021-05-12-11-23-58-810_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118010457-aee6a100-b314-11eb-9627-6bed16c63014.jpg)

You can continue like this up to n decisions by adding their respective parameters (Everything must be in a single line). The **[CHOISES]** and **[CHOISESIMAGE]** instruction must go to the end of each file in the **Scripts** folder to continue the story, except for the last file of the novel.

### [CHOICESIMAGE]

The instruction is very similar to **[CHOICES]**, the difference is that instead of using buttons you can now use images, at least 5 parameters are needed:

1. Text that appears at the bottom
2. Name of the image to use which must be in the **Scenes** folder
3. Name of the new file to be read and it must be found in the ** Scripts ** folder
4. Image width in pixels
5. Image height in pixels

![choicesimages1](https://user-images.githubusercontent.com/42527034/118013994-5addbb80-b318-11eb-9c8f-724bf7462d5b.png)

![Screenshot_2021-05-12-11-47-59-574_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014089-721ca900-b318-11eb-919a-d9c1be16bd30.jpg)

It is recommended to use images with **.png** extension and no background.

To put more than one decision, add the symbol **+** at the end and put the parameters from 2 to 5 again, as shown in the example.

![choicesimage2](https://user-images.githubusercontent.com/42527034/118014123-7a74e400-b318-11eb-929f-70a237bf38f9.png)

![Screenshot_2021-05-12-11-49-46-610_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014148-806ac500-b318-11eb-9702-a8873bb63718.jpg)

You can continue like this up to n decisions by adding their respective parameters (Everything must be in a single line). The **[CHOISES]** and **[CHOISESIMAGE]** instruction must go to the end of each file in the **Scripts** folder to continue the story, except for the last file of the novel.

### [CLEARNAMECHAR]

The instruction removes the text with the name of a character from the screen, it does not need any parameters.

![clearnamechar](https://user-images.githubusercontent.com/42527034/118014245-9a0c0c80-b318-11eb-9707-437dc49a47d9.png)

### [END]
The instruction ends the visual novel and displays the message "END OF NOVEL", it does not need any parameters.

![end](https://user-images.githubusercontent.com/42527034/118014257-9ed0c080-b318-11eb-8957-e3ea10670ff1.png)

### [FADEIN]

The instruction has the effect of "appearing", it can be an image or a video, it does not carry any parameters.

![fedein](https://user-images.githubusercontent.com/42527034/118017074-c8d7b200-b31b-11eb-8c79-cc700be1ee9a.png)

### [FADEOUT]

The instruction makes the effect of "disappear", it can be an image or a video, it does not have any parameters.

![fadeout](https://user-images.githubusercontent.com/42527034/118014350-bc9e2580-b318-11eb-946e-6eb5d38399db.png)

### [IGNORE]

The instruction allows to make annotations within the file, which will not appear in the novel, it needs a parameter, the annotation that you want to put in the file.

![ignore](https://user-images.githubusercontent.com/42527034/118014371-c2940680-b318-11eb-9946-903abceff1ff.png)

### [IMG]

The instruction places an image as the background, it needs a parameter which is the name of the image found in the **Scenes** folder.

![img](https://user-images.githubusercontent.com/42527034/118014391-c889e780-b318-11eb-9900-46d1a704c4e7.png)

![Screenshot_2021-05-11-23-08-13-035_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014438-d93a5d80-b318-11eb-8989-141d40b330f6.jpg)

### [NAMECHAR]

The instruction allows you to place a text with the name of a character, and it will be displayed at the bottom of the screen, you need four parameters, the name of the character, the text size, the text color and the background color (If not background color is desired, this parameter can be left empty).

![namechar](https://user-images.githubusercontent.com/42527034/118014511-ec4d2d80-b318-11eb-8584-e6aaaea29b5b.png)

![Screenshot_2021-05-12-11-57-01-394_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014848-42ba6c00-b319-11eb-995c-e6ce8c9ae0c3.jpg)

![Screenshot_2021-05-12-11-57-18-156_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014868-4817b680-b319-11eb-8a89-36b55639f7b5.jpg)

### [NPLAYER]

The instruction allows to use the information saved in **Setting** which is the name of the player of the novel, it will put a text followed by the name of the player and optionally more text can be added.

![nplayer](https://user-images.githubusercontent.com/42527034/118014909-54037880-b319-11eb-979c-e8defe400359.png)

![Screenshot_2021-05-12-11-59-21-676_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118015105-8ad98e80-b319-11eb-89e6-eeaddc720d09.jpg)

### [SHOWBG]

The instruction displays an image or title in full screen, it does not need any parameters, it is recommended to use this instruction after the **[TITLE]** instruction.

![showbg](https://user-images.githubusercontent.com/42527034/118015165-9a58d780-b319-11eb-82e2-3d721104c462.png)

### [SOUNDFX]

The instruction plays the sound effects saved in the **FX** folder, 2 parameters are needed which are the word **now** and the name of the effect file.

![soundfx](https://user-images.githubusercontent.com/42527034/118015185-a04eb880-b319-11eb-8754-b93ff2f75f7b.png)

### [STOPSOUND]

The instruction stops whatever sound is playing, it does not need parameters.

![stopsound](https://user-images.githubusercontent.com/42527034/118015206-a5136c80-b319-11eb-8a0e-6417dd0dae45.png)

### [STOPVIDEO]

The instruction removes the video playback, it does not need any parameters.

![stopvideo](https://user-images.githubusercontent.com/42527034/118015232-aa70b700-b319-11eb-8b47-ba6adaa845b9.png)

### [TEXT]

The instruction places the text at the bottom of the screen with an effect, you only need one parameter, the text you want to appear.

![text](https://user-images.githubusercontent.com/42527034/118015268-b492b580-b319-11eb-809c-576af7cb024e.png)

![Screenshot_2021-05-12-12-01-42-313_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118015415-dbe98280-b319-11eb-8070-44e72460d28a.jpg)

### [TEXTBOX]

The instruction places an image at the bottom of the screen, which serves to make the text look better, it needs a parameter, the name of the image found in the **Textbox** folder.

![textbox](https://user-images.githubusercontent.com/42527034/118015557-076c6d00-b31a-11eb-8c06-77532804fb1d.png)

![Screenshot_2021-05-12-12-07-46-097_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118016283-d7719980-b31a-11eb-9503-46814294d4af.jpg)

### [TEXTBOXCLEAR]

The instruction removes the image from the text box, it does not need any parameters.

![textboxclear](https://user-images.githubusercontent.com/42527034/118016350-e7897900-b31a-11eb-83c3-76085be46537.png)

### [TEXTBOXTRANS]

The instruction places a shadow at the bottom of the screen, which helps the text to be better visualized, it does not need any parameters.

![textboxtrans](https://user-images.githubusercontent.com/42527034/118016375-ee17f080-b31a-11eb-9912-f0939aebd5c5.png)

![Screenshot_2021-05-12-12-10-19-574_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118016508-19024480-b31b-11eb-9977-75472a4e9721.jpg)

### [TEXTCOLOR]

The instruction allows you to change the color of the text that is displayed on the screen, you need a parameter, whatever color you want color in hexadecimal.

![textcolor](https://user-images.githubusercontent.com/42527034/118016535-21f31600-b31b-11eb-8f23-f6d430603e81.png)

![Screenshot_2021-05-12-12-12-10-230_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118016701-549d0e80-b31b-11eb-9cae-dbd4a3376d39.jpg)

### [TITLE]

The instruction places a full screen text making an effect similar to **[FADEIN]**, it needs 3 parameters, the text to be displayed, the name of the text color in English or hexadecimal and the size of the text.

![title](https://user-images.githubusercontent.com/42527034/118016717-5b2b8600-b31b-11eb-9280-5f95ed648f88.png)

![Screenshot_2021-05-12-12-13-53-844_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118016872-8d3ce800-b31b-11eb-8ba7-32438ab62b64.jpg)

### [TITLEOFF]

The instruction removes the title from the screen making an effect similar to **[FADEOUT]**, it does not need any parameters.

![titleoff](https://user-images.githubusercontent.com/42527034/118016894-93cb5f80-b31b-11eb-9e0b-cc51211eae1a.png)

### [VIBRATE]

The instruction vibrates the phone for a period of time, it needs a parameter, which is the vibrate time in milliseconds. For example 1000 means that the phone will vibrate for 1 second.

![vibrate](https://user-images.githubusercontent.com/42527034/118016916-9928aa00-b31b-11eb-81e0-f5a76a21efd1.png)

### [VIDEO]

The instruction plays a video, it needs a parameter which is the name of the video found in the **Video** folder.

![video](https://user-images.githubusercontent.com/42527034/118016950-a2b21200-b31b-11eb-9329-415ae962cd2d.png)

### [WAIT]

The instruction stops everything for a period of time, it needs a parameter which is the time that you want to stop the novel and it is expressed in milliseconds. For example 2000 milliseconds stops it for 2 seconds.

![wait](https://user-images.githubusercontent.com/42527034/118016962-a6459900-b31b-11eb-973d-17d961a4293a.png)

### Note

For colors it is recommended to use the page https://www.color-hex.com/.

## Options

### Creation of new projects

To create new projects you must press the drop-down menu button found in the upper right, then click **Create blank project**, finally, you must enter the name of your project and press "ACCEPT", This will create a folder with the name of your project and within it will be the folders and files necessary to create your novel, except for the cover photo. The visualization of all the folders and files can be done using any file browser either from your cell phone or using a computer.

https://user-images.githubusercontent.com/42527034/118031036-af3e6680-b32b-11eb-939a-9a4f309ef0fa.mp4

#### Note

If you try to create a project with the same name, a message will appear on the screen saying that the project already exists and the folders will not be recreated.

### Load projects

To load a project you must press the **files** button that is located in the lower right part of the screen, once this is done a screen with the files of your device will be displayed you must look for the **alexavn** folder and enter it, then you must find the folder with the project you want to load and enter it, once there you must find the file **config.avn** and click on it, the main screen will open again showing the title and the cover of the project.

https://user-images.githubusercontent.com/42527034/118031239-f3ca0200-b32b-11eb-8748-bcf7e179b135.mp4

### Deletion of projects

When a project is loaded next to the title you will find the ** Delete ** button in blue, pressing it will show a message saying **Are you sure? It will be removed from the list**, by pressing **REMOVE** the project will be eliminated from the main screen but its folders are still saved on our device.

https://user-images.githubusercontent.com/42527034/118031380-14925780-b32c-11eb-926a-1eb369d34e20.mp4

### Setting

In the configuration you must assign the name of the player of the novel and his gender, this name can be used in the instruction **[NPLAYER]** for a better development of the visual novel, to save these data you just have to fill them in and press the **save** button. The information can be viewed on the **Preferences** screen.

https://user-images.githubusercontent.com/42527034/118031515-3d1a5180-b32c-11eb-8187-bc2089f56eb9.mp4

### Help

In the **Help** window, links to tutorials to use the application will be shown as well as a link to this documentation and a demo novel, to go to any of them you just have to click on the link and your web browser will open.

### Save game

To save the game there are 2 options, it can be from the screen where the novel is being played, in the upper right there is the **save** button with the image of a floppy disk, when pressing it a message will be displayed saying **Saving**. The data will be saved in the first slot of the **Save** screen. The second option is to press the **options** button that is next to the **save** button and has a gear image, the ** Save ** screen will open and you can save the progress of your game in any of the slots, saving the data will show the cover image of the novel and the date the data was saved.

https://user-images.githubusercontent.com/42527034/118032504-5ff93580-b32d-11eb-9a3e-edc5fd55796a.mp4

### Load game

To load a game you must first open a novel after this on the screen where the novel is playing you must click on the button **options** that is in the upper right and has a gear image, after pressing The button **Load** and you will see the screen with the slots of your saved games, finally, press the slot with the game you want to load and the novel will start playing from the saved point.

https://user-images.githubusercontent.com/42527034/118032562-6f787e80-b32d-11eb-90b0-6e5d6f8eddee.mp4

### Delete game

To delete the saved data of a game, you must go to the **Save** window and press and hold the slot of the game you want to delete, then a message will appear asking if **Do you want to delete the data?** press **Yes** to delete the data.

https://user-images.githubusercontent.com/42527034/118032643-84eda880-b32d-11eb-8e36-bdcb5f3a20f4.mp4

### Gallery

In the **Gallery** window, all the images saved in the **Scenes** folder will be displayed in the form of a carousel, to change the image you just have to slide your finger to the right or left.

https://user-images.githubusercontent.com/42527034/118033960-072a9c80-b32f-11eb-92d4-89c029f7624a.mp4

### Preferences

In the **Preferences** window, the information saved in **Setting** will be displayed.

![Screenshot_2021-05-12-14-23-30-190_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118032813-b1a1c000-b32d-11eb-8b4d-f1bde5569d01.jpg)

### About

In the **About** window, the information of the programmers will be displayed.

![Screenshot_2021-05-12-14-23-39-349_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118032838-b8303780-b32d-11eb-98b5-299fd5af9c7e.jpg)

#### Note

If you have any questions, suggestions or comments, do not hesitate to send an email to any of the programmers, the project is open source for the community, if you can help to improve it, do not hesitate to contact us.

### Share your project

To share your project with anyone, the first thing you should do is compress your project folder into a file with **.zip** extension, then send this file to the person you want can be by message, email, bluetooth, etc.

https://user-images.githubusercontent.com/42527034/118033104-09402b80-b32e-11eb-9e31-c33c6fac256d.mp4

The other person must have the Alexia VN application installed on their cell phone. Once the person receives the compressed file **.zip**, they must place said file inside the **alexvn** folder and then unzip it (This can be done from a computer or using an application such as **ASTRO** for cell phones), when the file is unzipped, a folder will appear with the name of the project.

https://user-images.githubusercontent.com/42527034/118033455-7784ee00-b32e-11eb-9167-10548d2addbc.mp4

Finally, the steps described in **Load projects** must be followed.

## Contributing code

You can submit pull requests on the [github repository] (https://github.com/Fernando1612/AlexiaVN).

## License

The project is licensed under GNU General Public License v2 or later. You can read it online at ([v2](http://www.gnu.org/licenses/gpl-2.0.html) or [v3](http://www.gnu.org/licenses/gpl.html)).







