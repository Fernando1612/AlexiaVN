# Alexia VN

[![es](https://img.shields.io/badge/lang-es-yellow.svg)](https://github.com/Fernando1612/AlexiaVN/blob/master/README.md)

Alexia visual novel is a visual novel engine with which you can create and play visual novels in a very simple way from your android cell phone. You can enter texts, images, sounds and videos to create a great story. It is very easy to use with intuitive instructions and it is not necessary to know how to program.

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

The ** cover ** file is an image which will be the cover of the visual novel and will be displayed when loading the ** config.avn ** file to the application. The file extension can be .jpp, .png, .bmp, etc.



### Note

The manipulation and visualization of the different folders and files can be done on the computer without any problem, you just have to connect the cell phone to it and browse through its files until you find the project.







