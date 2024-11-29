# RotP Example Addon

This repository is a template for you to use if you want to get started on making Stand addons for Ripples of the Past Minecraft mod.

There are multiple branches that cover some of the most requested/used features of Minecraft modding - if you want to use those in your addon but not sure how to implement those, check these branches out too. And feel free to DM me on [Discord](https://discord.gg/4GcjnMnXP4) if you want to suggest me to add more comments to a part of the code you don't understand, or cover another topic in a specific branch.

## Setting up the modding workspace

To set up the GitHub repository and not have to worry about that later, you can:
- Fork this repository on your GitHub account (you can rename that fork to whatever you want).
- Clone the fork created on your account to your PC.

There are guides on the Internet that cover the next steps more extensively, the only exception is that you would use this template instead of Forge's MDK. In short:
- Install an IDE that you will be using for the development - two most common options are IntelliJ IDEA Community Edition and Eclipse.
- In addition, make sure to install JDK 8 - it is needed to make mods for Minecraft 1.16.5. 
    - Windows x64 download link: [https://javadl.oracle.com/webapps/download/GetFile/1.8.0_311-b11/4d5417147a92418ea8b615e228bb6935/windows-i586/jdk-8u311-windows-x64.exe](https://javadl.oracle.com/webapps/download/GetFile/1.8.0_311-b11/4d5417147a92418ea8b615e228bb6935/windows-i586/jdk-8u311-windows-x64.exe)
- Open this template in your IDE (For Eclipse users - import it as a Gradle Project).
- If you get an error after opening the project, try changing Gradle's Java Home path to the directory where Java 8 was installed (on Windows that should be C:\Program Files\Java\jdk1.8.0_311) in the project settings.

## License

[GNU GPL v3.0](https://choosealicense.com/licenses/gpl-3.0/) 
